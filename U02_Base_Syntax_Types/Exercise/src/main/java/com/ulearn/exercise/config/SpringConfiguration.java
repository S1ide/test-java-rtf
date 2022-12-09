package com.ulearn.exercise.config;


import com.ulearn.exercise.ExerciseApplication;
import com.ulearn.exercise.entity.Customer;
import com.ulearn.exercise.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.ulearn.exercise.repository")
@EntityScan(basePackages = "com.ulearn.exercise.entity")
@ComponentScan(basePackages = "com.ulearn.exercise")
public class SpringConfiguration {
    @Autowired private CustomerRepository repository;
    private final Logger logger = LoggerFactory.getLogger(ExerciseApplication.class);

    @PostConstruct
    public void init(){
        queryAllCustomers();
        createCustomer();
        queryAllCustomers();
    }

    private void createCustomer() {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("John");
        newCustomer.setLastName("Doe");
        logger.info("Saving new customer...");
        this.repository.save(newCustomer);
    }

    private void queryAllCustomers() {
        List<Customer> allCustomers = this.repository.findAll();
        logger.info("Number of customers: " + allCustomers.size());
    }
}
