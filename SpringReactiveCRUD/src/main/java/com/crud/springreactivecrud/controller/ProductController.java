package com.crud.springreactivecrud.controller;

import com.crud.springreactivecrud.entity.Product;
import com.crud.springreactivecrud.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.random.RandomGenerator;

import static java.util.random.RandomGenerator.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final Random random = new Random();

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/findAll")
    public Flux<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable int id) {
        return productRepo.findById(id);
    }

;
    @PostMapping("/insert")
    public Mono<ResponseEntity<Product>> insertProduct(@RequestBody Product product) {
        return productRepo.save(product)
                .map(savedProduct -> ResponseEntity.status(HttpStatus.CREATED).body(savedProduct))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }



    @PutMapping("/update/{id}")
    public Mono<Product> updateProductById(@RequestBody Product product, @PathVariable int id) {
        return productRepo.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    return existingProduct;
                })
                .flatMap(productRepo::save);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteProductById(@PathVariable int id) {
        return productRepo.deleteById(id)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .onErrorResume(error -> {
                    System.err.println("Error deleting product: " + error.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }
}
