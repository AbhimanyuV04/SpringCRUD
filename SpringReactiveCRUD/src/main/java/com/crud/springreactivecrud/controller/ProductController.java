package com.crud.springreactivecrud.controller;

import com.crud.springreactivecrud.entity.Product;
import com.crud.springreactivecrud.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.random.RandomGenerator;

@RestController
@RequestMapping("/product")
public class ProductController {

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

    @PostMapping("/insert")
    public void insertProduct(@RequestBody Product product) {
        product.setId(RandomGenerator.getDefault().nextInt());
        productRepo.save(product);
    }

    @PutMapping("/{id}")
    public Mono<Product> updateProductById(@RequestBody Product product, @PathVariable int id) {
        return productRepo.findById(id)
                .map(
                        (c) -> {
                            c.setName(product.getName());
                            c.setPrice(product.getPrice());

                            return c;
                        }).flatMap(c -> productRepo.save(c));

    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable int id) {
        productRepo.deleteById(id);
    }


}
