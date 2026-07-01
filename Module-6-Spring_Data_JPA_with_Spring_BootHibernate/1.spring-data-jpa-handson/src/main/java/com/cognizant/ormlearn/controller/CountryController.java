package com.cognizant.ormlearn.controller;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    // GET all countries
    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    // GET country by code
    @GetMapping("/{code}")
    public Country getCountryByCode(@PathVariable String code) throws CountryNotFoundException {
        return countryService.findCountryByCode(code);
    }

    // POST add new country
    @PostMapping
    public String addCountry(@RequestBody Country country) {
        countryService.addCountry(country);
        return "Country added successfully!";
    }

    // PUT update country
    @PutMapping("/{code}")
    public String updateCountry(@PathVariable String code, @RequestParam String name) throws CountryNotFoundException {
        countryService.updateCountry(code, name);
        return "Country updated successfully!";
    }

    // DELETE country
    @DeleteMapping("/{code}")
    public String deleteCountry(@PathVariable String code) throws CountryNotFoundException {
        countryService.deleteCountry(code);
        return "Country deleted successfully!";
    }

    // GET countries by partial name
    @GetMapping("/search")
    public List<Country> searchCountries(@RequestParam String name) {
        return countryService.findCountriesByNameContaining(name);
    }
}