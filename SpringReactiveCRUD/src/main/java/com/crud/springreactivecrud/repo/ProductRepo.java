package com.crud.springreactivecrud.repo;

import com.crud.springreactivecrud.entity.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepo extends ReactiveCrudRepository<Product, Integer> {


    @Query("INSERT INTO products (id, name, price) VALUES (DEFAULT, $2, $3)")
    Mono<Product> save(Integer id, String name, String price);
}
