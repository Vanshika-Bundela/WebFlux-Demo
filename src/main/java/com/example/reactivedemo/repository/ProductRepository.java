package com.example.reactivedemo.repository;

import com.example.reactivedemo.model.Product;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductRepository {

    private final Map<String, Product> storage = new ConcurrentHashMap<>();

    public Flux<Product> findAll() {
        return Flux.fromIterable(storage.values());
    }

    public Mono<Product> findById(String id) {
        return Mono.justOrEmpty(storage.get(id));
    }

    public void save(Product p) {
        storage.put(p.getId(), p);
    }
}