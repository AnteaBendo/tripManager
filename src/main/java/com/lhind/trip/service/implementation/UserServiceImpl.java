package com.lhind.trip.service.implementation;

import com.lhind.trip.dto.user.*;
import com.lhind.trip.enums.Role;
import com.lhind.trip.exception.UserException;
import com.lhind.trip.mapper.UserMapper;
import com.lhind.trip.model.User;
import com.lhind.trip.repository.UserRepository;
import com.lhind.trip.security.entity.AuthenticationRequest;
import com.lhind.trip.security.entity.AuthenticationResponse;
import com.lhind.trip.security.jwt.JwtUtil;
import com.lhind.trip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;


@Service
public class UserServiceImpl extends BaseServiceImpl<UserCreateDto, UserUpdateDto, UserResponseDto, User> implements UserService{

    public UserServiceImpl(JpaRepository<User, Long> repository) {
        super(repository);
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserResponseDto save(UserCreateDto createDto) {
        User user = mapper.createDtoToEntity(createDto);
        user.setPassword(passwordEncoder.encode(createDto.getPassword()));
        return mapper.entityToResponseDto(repository.save(user));
    }

    @Override
    public UserResponseDto update(UserUpdateDto updateDto) {
        User user = userRepository.getByEmail(getPrincipalUserName())
                .orElseThrow(() -> new UserException("User not found"));
        user.setFirstName(updateDto.getFirstName());
        user.setLastName(updateDto.getLastName());
        user.setEmail(updateDto.getEmail());
        return mapper.entityToResponseDto(repository.save(user));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.getByEmail(email)
                .orElseThrow(() -> new UserException("User not found"));
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            ));
            UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());
            String token = jwtUtil.generateToken(userDetails);

            Role role = null;

            Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
            if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                role = Role.ADMIN;
            }
            if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                role = Role.USER;
            }

            return new AuthenticationResponse(token, role);
        }catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Override
    public void updatePassword(UserUpdatePasswordDto passwordDto) {
        User user = userRepository.getByEmail(getPrincipalUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
        repository.save(user);
    }

    @Override
    public UserResponseDto updateFromAdmin(Long id, UserUpdateFromAdminDto update) {
        User user = userMapper.updateFromAdminToEntity(update);
        user.setId(id);
        return mapper.entityToResponseDto(repository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.getByEmail(s)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRole()))
                ))
                .orElseThrow(() -> new BadCredentialsException("Bad credentials"));
    }

    private String getPrincipalUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  ((UserDetails)principal).getUsername();
    }

    public String getPrincipalAuthority() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  ((UserDetails)principal).getAuthorities().toString();
    }
}
