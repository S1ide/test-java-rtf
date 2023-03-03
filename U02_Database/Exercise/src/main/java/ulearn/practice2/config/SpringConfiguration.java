package ulearn.practice2.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ulearn.practice2.Practice2Application;
import ulearn.practice2.model.Customer;
import ulearn.practice2.repository.CustomerRepository;

import java.util.List;

@Configuration
@ComponentScan("ulearn.practice2")
public class SpringConfiguration {
    @Autowired
    private CustomerRepository repository;
    private final Logger logger = LoggerFactory.getLogger(Practice2Application.class);

    @PostConstruct
    public void init() {
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
