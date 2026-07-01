package com.cognizant.springlearn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START - main method");
        SpringApplication.run(SpringLearnApplication.class, args);

        SpringLearnApplication app = new SpringLearnApplication();
        app.displayDate();
        app.displayCountry();
        app.displayCountries();

        LOGGER.info("END - main method");
    }

    public void displayDate() {
        LOGGER.info("START - displayDate method");

        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
            SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
            Date date = format.parse("31/12/2018");

            LOGGER.debug("Parsed Date: {}", date);
            LOGGER.debug("Formatted Date: {}", format.format(date));
            LOGGER.info("END - displayDate method");
        } catch (Exception e) {
            LOGGER.error("Error in displayDate: {}", e.getMessage());
        }
    }

    public void displayCountry() {
        LOGGER.info("START - displayCountry method");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        LOGGER.debug("Country : {}", country.toString());

        Country anotherCountry = context.getBean("country", Country.class);
        LOGGER.debug("Another Country Reference : {}", anotherCountry.toString());

        if (country == anotherCountry) {
            LOGGER.debug("Both references point to the SAME object - Singleton scope");
        } else {
            LOGGER.debug("References point to DIFFERENT objects - Prototype scope");
        }

        LOGGER.info("END - displayCountry method");
    }

    @SuppressWarnings("unchecked")
    public void displayCountries() {
        LOGGER.info("START - displayCountries method");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = (List<Country>) context.getBean("countryList");

        LOGGER.debug("Country List Size: {}", countryList.size());
        for (Country country : countryList) {
            LOGGER.debug("Country: {}", country.toString());
        }

        LOGGER.info("END - displayCountries method");
    }
}