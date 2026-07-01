package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    private List<Country> countryList;

    @SuppressWarnings("unchecked")
    private List<Country> getCountryListFromXML() {
        if (countryList == null) {
            LOGGER.debug("Loading country list from XML");
            ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
            countryList = (List<Country>) context.getBean("countryList");
        }
        return countryList;
    }

    public List<Country> getAllCountries() {
        LOGGER.info("START - getAllCountries method");
        List<Country> countries = getCountryListFromXML();
        LOGGER.debug("Returning {} countries", countries.size());
        LOGGER.info("END - getAllCountries method");
        return countries;
    }

    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START - getCountry method with code: {}", code);

        List<Country> countries = getCountryListFromXML();

        Country country = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.error("Country not found with code: {}", code);
                    return new CountryNotFoundException();
                });

        LOGGER.debug("Found country: {}", country);
        LOGGER.info("END - getCountry method");
        return country;
    }

    public Country getCountryByCode(String code) {
        LOGGER.info("START - getCountryByCode method with code: {}", code);

        List<Country> countries = getCountryListFromXML();

        Country country = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        LOGGER.debug("Found country: {}", country);
        LOGGER.info("END - getCountryByCode method");
        return country;
    }
}