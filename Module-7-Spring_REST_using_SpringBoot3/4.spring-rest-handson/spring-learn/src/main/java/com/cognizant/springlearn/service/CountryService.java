package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.exception.CountryNotFoundException;

@Service
public class CountryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
    private List<Country> countryList;

    @SuppressWarnings("unchecked")
    private List<Country> getCountryListFromXML() {
        if (countryList == null) {
            ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
            countryList = (List<Country>) context.getBean("countryList");
        }
        return countryList;
    }

    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = getCountryListFromXML();
        LOGGER.info("End");
        return countries;
    }

    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("Start");
        List<Country> countries = getCountryListFromXML();
        Country country = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(CountryNotFoundException::new);
        LOGGER.info("End");
        return country;
    }

    public Country addCountry(Country country) {
        LOGGER.info("Start");
        List<Country> countries = getCountryListFromXML();
        countries.add(country);
        LOGGER.info("End");
        return country;
    }

    public Country updateCountry(Country country) throws CountryNotFoundException {
        LOGGER.info("Start");
        List<Country> countries = getCountryListFromXML();
        Country existingCountry = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(country.getCode()))
                .findFirst()
                .orElseThrow(CountryNotFoundException::new);
        existingCountry.setName(country.getName());
        LOGGER.info("End");
        return existingCountry;
    }

    public void deleteCountry(String code) throws CountryNotFoundException {
        LOGGER.info("Start");
        List<Country> countries = getCountryListFromXML();
        Country countryToRemove = countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(CountryNotFoundException::new);
        countries.remove(countryToRemove);
        LOGGER.info("End");
    }
}