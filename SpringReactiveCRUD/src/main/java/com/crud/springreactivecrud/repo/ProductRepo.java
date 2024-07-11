package com.crud.springreactivecrud.repo;

import com.crud.springreactivecrud.entity.Product;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepo extends ReactiveCrudRepository<Product, Integer> {
}
