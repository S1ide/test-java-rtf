package me.ulearn.springboot_module6.controller;

import lombok.RequiredArgsConstructor;
import me.ulearn.springboot_module6.entity.Product;
import me.ulearn.springboot_module6.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//#region Task
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public Product postProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> putProduct(@PathVariable Long id, @RequestBody Product product) {
        return service.updateProduct(id, product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }

    @GetMapping
    public Iterable<Product> getProducts() {
        return service.getProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.removeProduct(id);
    }
}
//#endregion Task