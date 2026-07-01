package com.cognizant.springlearn.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.springlearn.Country;

@Repository
public class CountryDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryDao.class);
    private static List<Country> COUNTRY_LIST;

    public CountryDao() {
        LOGGER.info("Start");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        @SuppressWarnings("unchecked")
        List<Country> countryList = (List<Country>) context.getBean("countryList");
        COUNTRY_LIST = countryList;
        LOGGER.info("End");
    }

    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        LOGGER.info("End");
        return COUNTRY_LIST;
    }
}
