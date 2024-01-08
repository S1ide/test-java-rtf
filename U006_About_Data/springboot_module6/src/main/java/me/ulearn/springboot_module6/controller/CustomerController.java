package me.ulearn.springboot_module6.controller;

import lombok.RequiredArgsConstructor;
import me.ulearn.springboot_module6.entity.Customer;
import me.ulearn.springboot_module6.entity.Product;
import me.ulearn.springboot_module6.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//#region Task
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @PostMapping
    public Customer postCustomer(@RequestBody Customer customer) {
        return service.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> putCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return service.updateCustomer(id, customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        return service.getCustomer(id);
    }

    @GetMapping("/{id}/cart")
    public ResponseEntity<List<Product>> getProducts(@PathVariable Long id) {
        return service.getProducts(id);
    }

    @PostMapping("/{id}/cart")
    public ResponseEntity<Product> postProduct(@PathVariable Long id, @RequestBody Product product) {
        return service.saveProduct(id, product);
    }

    @DeleteMapping("/{id}/cart")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id, @RequestBody Product product) {
        return service.removeProduct(id, product);
    }
}
//#endregion Task