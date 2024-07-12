package com.crud.springbootcrud.repo;

import com.crud.springbootcrud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
