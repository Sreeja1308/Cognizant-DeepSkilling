package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;

    public static void main(String[] args) {
        LOGGER.info("Inside main");
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);

        try {
            // Hands-on 1: Test get all countries
            testGetAllCountries();

            // Hands-on 6: Test find country by code
            testFindCountryByCode();

            // Hands-on 7: Test add new country
            testAddCountry();

            // Hands-on 8: Test update country
            testUpdateCountry();

            // Hands-on 9: Test delete country
            testDeleteCountry();

            // Hands-on 5: Test find countries by partial name
            testFindCountriesByNameContaining();

        } catch (CountryNotFoundException e) {
            LOGGER.error("Error: {}", e.getMessage());
        }
    }

    // Hands-on 1: Get all countries
    private static void testGetAllCountries() {
        LOGGER.info("Start testGetAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("Total countries: {}", countries.size());
        LOGGER.info("End testGetAllCountries");
    }

    // Hands-on 6: Find a country based on country code
    private static void testFindCountryByCode() throws CountryNotFoundException {
        LOGGER.info("Start testFindCountryByCode");
        Country country = countryService.findCountryByCode("IN");
        LOGGER.debug("Country found: {}", country);
        LOGGER.info("End testFindCountryByCode");
    }

    // Hands-on 7: Add a new country
    private static void testAddCountry() throws CountryNotFoundException {
        LOGGER.info("Start testAddCountry");
        
        // Create new country
        Country newCountry = new Country("XX", "Test Country");
        
        // Add the country
        countryService.addCountry(newCountry);
        LOGGER.debug("Country added successfully");
        
        // Verify the country was added
        Country addedCountry = countryService.findCountryByCode("XX");
        LOGGER.debug("Verified added country: {}", addedCountry);
        
        LOGGER.info("End testAddCountry");
    }

    // Hands-on 8: Update a country based on code
    private static void testUpdateCountry() throws CountryNotFoundException {
        LOGGER.info("Start testUpdateCountry");
        
        // Update country name
        countryService.updateCountry("XX", "Updated Test Country");
        LOGGER.debug("Country updated successfully");
        
        // Verify the update
        Country updatedCountry = countryService.findCountryByCode("XX");
        LOGGER.debug("Verified updated country: {}", updatedCountry);
        
        LOGGER.info("End testUpdateCountry");
    }

    // Hands-on 9: Delete a country based on code
    private static void testDeleteCountry() throws CountryNotFoundException {
        LOGGER.info("Start testDeleteCountry");
        
        // Delete the country
        countryService.deleteCountry("XX");
        LOGGER.debug("Country deleted successfully");
        
        // Try to find the deleted country (should throw exception)
        try {
            countryService.findCountryByCode("XX");
        } catch (CountryNotFoundException e) {
            LOGGER.debug("Country not found as expected: {}", e.getMessage());
        }
        
        LOGGER.info("End testDeleteCountry");
    }

    // Hands-on 5: Find list of countries matching a partial country name
    private static void testFindCountriesByNameContaining() {
        LOGGER.info("Start testFindCountriesByNameContaining");
        
        List<Country> countries = countryService.findCountriesByNameContaining("India");
        LOGGER.debug("Countries containing 'India': {}", countries);
        
        List<Country> countries2 = countryService.findCountriesByNameContaining("United");
        LOGGER.debug("Countries containing 'United': {}", countries2);
        
        LOGGER.info("End testFindCountriesByNameContaining");
    }
}