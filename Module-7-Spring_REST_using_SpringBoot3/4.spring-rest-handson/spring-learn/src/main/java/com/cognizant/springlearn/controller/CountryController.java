package com.cognizant.springlearn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.exception.CountryNotFoundException;

import javax.validation.Valid;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.info("End");
        return countries;
    }

    @GetMapping("/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("Start");
        Country country = countryService.getCountry(code);
        LOGGER.info("End");
        return country;
    }

    @PostMapping
    public Country addCountry(@RequestBody @Valid Country country) {
        LOGGER.info("Start");
        Country addedCountry = countryService.addCountry(country);
        LOGGER.info("End");
        return addedCountry;
    }

    @PutMapping
    public Country updateCountry(@RequestBody @Valid Country country) throws CountryNotFoundException {
        LOGGER.info("Start");
        Country updatedCountry = countryService.updateCountry(country);
        LOGGER.info("End");
        return updatedCountry;
    }

    @DeleteMapping("/{code}")
    public void deleteCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("Start");
        countryService.deleteCountry(code);
        LOGGER.info("End");
    }
}