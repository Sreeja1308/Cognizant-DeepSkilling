package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    // Hands-on 1: Get all countries
    @Transactional
    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryRepository.findAll();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End");
        return countries;
    }

    // Hands-on 6: Find a country based on country code
    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        LOGGER.info("Start");
        Optional<Country> result = countryRepository.findById(countryCode);
        
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country with code " + countryCode + " not found");
        }
        
        Country country = result.get();
        LOGGER.debug("Country: {}", country);
        LOGGER.info("End");
        return country;
    }

    // Hands-on 7: Add a new country
    @Transactional
    public void addCountry(Country country) {
        LOGGER.info("Start");
        countryRepository.save(country);
        LOGGER.info("End");
    }

    // Hands-on 8: Update a country based on code
    @Transactional
    public void updateCountry(String code, String name) throws CountryNotFoundException {
        LOGGER.info("Start");
        Optional<Country> result = countryRepository.findById(code);
        
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country with code " + code + " not found");
        }
        
        Country country = result.get();
        country.setName(name);
        countryRepository.save(country);
        LOGGER.info("End");
    }

    // Hands-on 9: Delete a country based on code
    @Transactional
    public void deleteCountry(String countryCode) throws CountryNotFoundException {
        LOGGER.info("Start");
        Optional<Country> result = countryRepository.findById(countryCode);
        
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country with code " + countryCode + " not found");
        }
        
        countryRepository.deleteById(countryCode);
        LOGGER.info("End");
    }

    // Hands-on 5: Find list of countries matching a partial country name
    @Transactional
    public List<Country> findCountriesByNameContaining(String partialName) {
        LOGGER.info("Start");
        List<Country> countries = countryRepository.findByNameContaining(partialName);
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End");
        return countries;
    }
}