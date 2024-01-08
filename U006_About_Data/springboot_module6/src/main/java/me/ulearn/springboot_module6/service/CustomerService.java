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
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public ResponseEntity<Customer> updateCustomer(Long id, Customer customer) {
        return customerRepository.existsById(id)
                ? new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Customer> getCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<Product>> getProducts(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(customer -> new ResponseEntity<>(customer.getCart(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Product> saveProduct(Long id, Product product) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty() || !productRepository.existsById(product.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Customer customer = optionalCustomer.get();
        List<Product> products = customer.getCart();

        if (products.contains(product)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        products.add(product);

        customerRepository.save(customer);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ResponseEntity<Product> removeProduct(Long id, Product product) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Customer customer = optionalCustomer.get();

        if (customer.getCart().remove(product)) {
            customerRepository.save(customer);

            return new ResponseEntity<>(product, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
//#endregion Task