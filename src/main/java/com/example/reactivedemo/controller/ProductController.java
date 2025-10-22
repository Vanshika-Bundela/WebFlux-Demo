package com.example.reactivedemo.controller;

import com.example.reactivedemo.model.Product;
import com.example.reactivedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public Flux<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    // Add new product
    @PostMapping
    public Mono<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return Mono.just("Product added successfully");
    }

    // Stream products one by one
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> streamProducts() {
        return productService.getAllProducts()
                .delayElements(Duration.ofSeconds(1));
    }
}
