package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrmLearnApplicationTests {

    @Autowired
    private CountryService countryService;

    @Test
    void testGetAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        assertNotNull(countries);
        assertTrue(countries.size() > 0);
    }

    @Test
    void testFindCountryByCode() throws CountryNotFoundException {
        Country country = countryService.findCountryByCode("IN");
        assertNotNull(country);
        assertEquals("IN", country.getCode());
        assertEquals("India", country.getName());
    }

    @Test
    void testAddAndDeleteCountry() throws CountryNotFoundException {
        // Add
        Country newCountry = new Country("XX", "Test Country");
        countryService.addCountry(newCountry);
        
        // Verify
        Country found = countryService.findCountryByCode("XX");
        assertNotNull(found);
        assertEquals("Test Country", found.getName());
        
        // Delete
        countryService.deleteCountry("XX");
        
        // Verify deletion
        assertThrows(CountryNotFoundException.class, () -> {
            countryService.findCountryByCode("XX");
        });
    }

    @Test
    void testUpdateCountry() throws CountryNotFoundException {
        // Add first
        Country newCountry = new Country("YY", "Original Name");
        countryService.addCountry(newCountry);
        
        // Update
        countryService.updateCountry("YY", "Updated Name");
        
        // Verify
        Country updated = countryService.findCountryByCode("YY");
        assertEquals("Updated Name", updated.getName());
        
        // Cleanup
        countryService.deleteCountry("YY");
    }

    @Test
    void testFindCountriesByNameContaining() {
        List<Country> countries = countryService.findCountriesByNameContaining("India");
        assertNotNull(countries);
        assertTrue(countries.size() > 0);
    }
}