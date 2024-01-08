package me.ulearn.springboot_module6.service;

import lombok.RequiredArgsConstructor;
import me.ulearn.springboot_module6.entity.Customer;
import me.ulearn.springboot_module6.entity.Product;
import me.ulearn.springboot_module6.repo.CustomerRepository;
import me.ulearn.springboot_module6.repo.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//#region Task
public class CustomerService {
    public Customer createCustomer(Customer customer) {

    }

    public ResponseEntity<Customer> updateCustomer(Long id, Customer customer) {

    }

    public ResponseEntity<Customer> getCustomer(Long id) {

    }

    public ResponseEntity<List<Product>> getProducts(Long id) {

    }

    public ResponseEntity<Product> saveProduct(Long id, Product product) {

    }

    public ResponseEntity<Product> removeProduct(Long id, Product product) {

    }
}
//#endregion Task