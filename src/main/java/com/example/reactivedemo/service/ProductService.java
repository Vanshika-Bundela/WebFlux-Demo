package com.example.reactivedemo.service;

import com.example.reactivedemo.model.Product;
import com.example.reactivedemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

        // Initialize some products without @PostConstruct
        productRepository.save(new Product("1", "Laptop", 1200.00));
        productRepository.save(new Product("2", "Phone", 800.00));
        productRepository.save(new Product("3", "Tablet", 500.00));
    }

    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Mono<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
