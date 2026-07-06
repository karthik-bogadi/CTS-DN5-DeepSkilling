package com.cognizant.springlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLearnApplication {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {

        LOGGER.info("START");

        displayCountries();

        LOGGER.info("END");
    }

    public static void displayCountries() {

        LOGGER.info("displayCountries() START");

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");

        List<Country> countries =
                (List<Country>) context.getBean("countryList");

        countries.forEach(country -> LOGGER.debug(country.toString()));

        context.close();

        LOGGER.info("displayCountries() END");
    }
}