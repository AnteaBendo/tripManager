package com.lhind.trip.controller;

import com.lhind.trip.dto.airport.AirportResponseDto;
import com.lhind.trip.dto.flight.FlightCreateDto;
import com.lhind.trip.dto.flight.FlightResponseDto;
import com.lhind.trip.dto.flight.FlightUpdateDto;
import com.lhind.trip.dto.trip.TripCreateDto;
import com.lhind.trip.dto.trip.TripUpdateDto;
import com.lhind.trip.dto.user.UserUpdateDto;
import com.lhind.trip.dto.user.UserUpdatePasswordDto;
import com.lhind.trip.exception.FlightException;
import com.lhind.trip.model.User;
import com.lhind.trip.security.entity.AuthenticationRequest;
import com.lhind.trip.security.entity.AuthenticationResponse;
import com.lhind.trip.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final TripService tripService;
    private final FlightService flightService;
    private final AirportService airportService;
    private final CityService cityService;


    @RequestMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {

        String role =  authResult.getAuthorities().toString();

        if(role.contains("ROLE_ADMIN")){
            response.sendRedirect("/admin");
        }
        else if(role.contains("ROLE_USER")) {
            response.sendRedirect("/trips");
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/password")
    public String changePasswordPage(Model model){
        model.addAttribute("updateDto", new UserUpdatePasswordDto());
        model.addAttribute("role", userService.getPrincipalAuthority());
        return "/user/changePassword";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/password")
    public String changePassword(UserUpdatePasswordDto passwordDto){
        userService.updatePassword(passwordDto);
        return "redirect:/success";
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public String getUpdateUserPage(Model model, Principal principal){
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        model.addAttribute("role", userService.getPrincipalAuthority());
        return "user/updatePersInfo";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/update")
    public String updateUser(UserUpdateDto updateDto){
        userService.update(updateDto);
        return "redirect:/trips";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/trips")
    public String getTrips(Model model){
        model.addAttribute("trips", tripService.findByUser());
        return "user/mainUser";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/trips/{id}")
    public String getTrip(@PathVariable Long id, Model model){
        model.addAttribute("trip", tripService.findById(id));
        model.addAttribute("flightCreateDto", new FlightCreateDto());
        model.addAttribute("airportList", airportService.findAll());
        return "user/trip";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/trips/create")
    public String getCreateTripPage(Model model){
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("tripCreateDto", new TripCreateDto());
        return "user/createTrip";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/trips/create")
    public String addTrip(TripCreateDto createDto) {
        tripService.save(createDto);
        return "redirect:/trips";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/trips/{id}/update")
    public String getUpdateTripPage(@PathVariable Long id, Model model){
        model.addAttribute("trip", tripService.findById(id));
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("tripUpdateDto", new TripUpdateDto());
        return "user/updateTrip";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/trips/{id}/update")
    public String updateTrip(@PathVariable Long id, TripUpdateDto updateDto){
        tripService.update(updateDto, id);
        return "redirect:/trips";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/trips/{id}/delete")
    public String deleteTrip(@PathVariable Long id){
        tripService.delete(id);
        return "redirect:/trips";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/trips/{id}/approval")
    public String askApproval(@PathVariable Long id){
        tripService.changeStatusToWainting(id);
        return "redirect:/trips";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/trips/{trip}/flights")
    public String createFlight(@PathVariable Long trip, FlightCreateDto flightCreateDto, Model model){
        try{
            flightService.save(flightCreateDto, trip);
        }catch(Exception e){
            model.addAttribute("error", "Please insert valid dates!!");
            model.addAttribute("trip", tripService.findById(trip));
            model.addAttribute("flightCreateDto", new FlightCreateDto());
            model.addAttribute("airportList", airportService.findAll());
            return "user/trip";
        }
        return "redirect:/trips/" + trip;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/trips/{trip}/flights/{flight}/delete")
    public String deleteFlight(@PathVariable Long trip, @PathVariable Long flight){
        flightService.delete(flight);
        return "redirect:/trips/" + trip;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/trips/{trip}/flights/{flight}")
    public String getUpdatePageFlight(@PathVariable Long trip, @PathVariable Long flight, Model model){
        model.addAttribute("trip", tripService.findById(trip));
        model.addAttribute("flight", flightService.findById(flight));
        model.addAttribute("airportList", airportService.findAll());
        model.addAttribute("flightUpdateDto", new FlightUpdateDto());
        return "user/updateFlight";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/trips/{trip}/flights/{flight}")
    public String updateFlight(@PathVariable Long trip, @PathVariable Long flight, FlightUpdateDto update){
        flightService.update(update, flight);
        return "redirect:/trips/" + trip;
    }

}
