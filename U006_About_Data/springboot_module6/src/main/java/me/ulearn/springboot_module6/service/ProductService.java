package me.ulearn.springboot_module6.service;

import lombok.RequiredArgsConstructor;
import me.ulearn.springboot_module6.entity.Product;
import me.ulearn.springboot_module6.repo.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

//#region Task
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public ResponseEntity<Product> updateProduct(Long id, Product product) {
        return productRepository.existsById(id)
                ? new ResponseEntity<>(productRepository.save(product), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Product> getProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }
}
//#endregion Task