package com.cognizant.springlearn;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cognizant.springlearn.controller.CountryController;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired
    private CountryController countryController;

    @Autowired
    private MockMvc mvc;

    /**
     * Test 1: Verify CountryController is loaded
     */
    @Test
    void contextLoads() {
        assertNotNull(countryController);
    }

    /**
     * Test 2: Test get country service
     * Verify status is OK and response contains correct country data
     */
    @Test
    void testGetCountry() throws Exception {
        ResultActions actions = mvc.perform(get("/country"));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").exists())
                .andExpect(jsonPath("$.code").value("IN"))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").value("India"));
    }

    /**
     * Test 3: Test get all countries service
     * Verify status is OK and response contains 4 countries
     */
    @Test
    void testGetAllCountries() throws Exception {
        ResultActions actions = mvc.perform(get("/countries"));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].code").value("IN"))
                .andExpect(jsonPath("$[1].code").value("US"))
                .andExpect(jsonPath("$[2].code").value("DE"))
                .andExpect(jsonPath("$[3].code").value("JP"));
    }

    /**
     * Test 4: Test get country by code - Valid code
     */
    @Test
    void testGetCountryByCode_Valid() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/IN"));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("IN"))
                .andExpect(jsonPath("$.name").value("India"));
    }

    /**
     * Test 5: Test get country by code - Case insensitive
     */
    @Test
    void testGetCountryByCode_CaseInsensitive() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/in"));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("IN"))
                .andExpect(jsonPath("$.name").value("India"));
    }

    /**
     * Test 6: Test get country by code - Invalid code (Exception scenario)
     */
    @Test
    void testGetCountryByCode_Invalid() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/AZ"));

        actions.andExpect(status().isNotFound())
                .andExpect(status().reason("Country not found"));
    }

    /**
     * Test 7: Test get country by code - Another invalid code
     */
    @Test
    void testGetCountryException() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/XX"));

        actions.andExpect(status().isNotFound())
                .andExpect(status().reason("Country not found"));
    }
}