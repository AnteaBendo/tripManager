package com.lhind.trip.controller;

import com.lhind.trip.dto.airport.AirportCreateDto;
import com.lhind.trip.dto.airport.AirportResponseDto;
import com.lhind.trip.dto.airport.AirportUpdateDto;
import com.lhind.trip.dto.city.CityCreateDto;
import com.lhind.trip.dto.city.CityResponseDto;
import com.lhind.trip.dto.city.CityUpdateDto;
import com.lhind.trip.dto.country.CountryCreateDto;
import com.lhind.trip.dto.country.CountryUpdateDto;
import com.lhind.trip.dto.trip.TripResponseDto;
import com.lhind.trip.dto.trip.TripUpdateStatusDto;
import com.lhind.trip.dto.user.UserCreateDto;
import com.lhind.trip.dto.user.UserResponseDto;
import com.lhind.trip.dto.user.UserUpdateFromAdminDto;
import com.lhind.trip.service.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final TripService tripService;
    private final CountryService countryService;
    private final CityService cityService;
    private final AirportService airportService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("createdTrips", tripService.findTripsWithStatusCREATED());
        return "admin/mainAdmin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "/admin/users/allUsers";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users/add")
    public String addUserPage(Model model){
        model.addAttribute("createDto", new UserCreateDto());
        return "/admin/users/createUser";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/users/add")
    public String addUser(UserCreateDto createDto){
        userService.save(createDto);
        return "redirect:/admin/users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users/{id}")
    public String getUpdateUserPage(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("updateDto", new UserUpdateFromAdminDto());
        return "/admin/users/updateUser";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, UserUpdateFromAdminDto updateDto){
        userService.updateFromAdmin(id, updateDto);
        return "redirect:/admin/users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/airports")
    public String getAllAirports(Model model){
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("usage", "All Airports");
        model.addAttribute("city", null);
        return "/admin/airports/allAirports";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/airports/add")
    public String getCreateAirportPage(Model model){
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("createDto", new AirportCreateDto());
        return "/admin/airports/createAirport";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/airports/add")
    public String addAirport(AirportCreateDto createDto){
        airportService.save(createDto);
        return "redirect:/admin/airports";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/airports/{id}")
    public String getUpdateAirportPage(@PathVariable Long id, Model model){
        model.addAttribute("airport",airportService.findById(id));
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("updateDto", new AirportUpdateDto());
        return "/admin/airports/updateAirport";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/airports/{id}")
    public String updateAirport(@PathVariable Long id, AirportUpdateDto updateDto){
        airportService.update(updateDto, id);
        return "redirect:/admin/airports";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/airports/{id}/delete")
    public String deleteAirport(@PathVariable Long id){
        airportService.delete(id);
        return "redirect:/admin/airports";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/cities")
    public String getAllCities(Model model){
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("usage", "All Cities");
        model.addAttribute("country", null);
        return "/admin/cities/allCities";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/cities/add")
    public String getCreateCityPage(Model model){
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("createDto", new CityCreateDto());
        return "/admin/cities/createCity";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/cities/add")
    public String addCity(CityCreateDto createDto){
        cityService.save(createDto);
        return "redirect:/admin/cities";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/cities/{id}")
    public String getUpdateCityPage(@PathVariable Long id, Model model){
        model.addAttribute("city", cityService.findById(id));
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("updateDto", new CityUpdateDto());
        return "/admin/cities/updateCity";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/cities/{id}/airports")
    public String getAirportsOfCity(@PathVariable Long id, Model model){
        model.addAttribute("airports", airportService.findByCityId(id));
        model.addAttribute("usage", "Airports of ");
        model.addAttribute("city", cityService.findById(id));
        return "/admin/airports/allAirports";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/cities/{id}")
    public String updateCity(@PathVariable Long id, CityUpdateDto updateDto){
        cityService.update(updateDto, id);
        return "redirect:/admin/cities";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/cities/{id}/delete")
    public String deleteCity(@PathVariable Long id){
        cityService.delete(id);
        return "redirect:/admin/cities";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/countries")
    public String getAllCountries(Model model){
        model.addAttribute("countries", countryService.findAll());
        return "/admin/countries/allCountries";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/countries/add")
    public String getCreateCountryPage(Model model){
        model.addAttribute("createDto", new CountryCreateDto());
        return "/admin/countries/createCountry";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/countries/add")
    public String addCountry(CountryCreateDto createDto){
        countryService.save(createDto);
        return "redirect:/admin/countries";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/countries/{id}")
    public String getUpdateCountryPage(@PathVariable Long id, Model model){
        model.addAttribute("country", countryService.findById(id));
        model.addAttribute("updateDto", new CountryUpdateDto());
        return "/admin/countries/updateCountry";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/countries/{id}")
    public String updateCountry(@PathVariable Long id, CountryUpdateDto updateDto){
        countryService.update(updateDto, id);
        return "redirect:/admin/countries";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/countries/{id}/cities")
    public String getCitiesOfCountry(@PathVariable Long id, Model model){
        model.addAttribute("country", countryService.findById(id));
        model.addAttribute("cities", cityService.findByCountryId(id));
        model.addAttribute("usage", "Cities of ");
        return "/admin/cities/allCities";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/countries/{id}/delete")
    public String deleteCountry(@PathVariable Long id){
        countryService.delete(id);
        return "redirect:/admin/countries";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/approval/{id}")
    public String approveTrip(@PathVariable Long id){
        tripService.changeStatusToApproved(id);
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/decline/{id}")
    public String declineTrip(@PathVariable Long id){
        tripService.delete(id);
        return "redirect:/admin";
    }

}
