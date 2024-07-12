package com.crud.springbootcrud.controller;

import com.crud.springbootcrud.entity.Product;
import com.crud.springbootcrud.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/findAll")
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        Optional<Product> product = productRepo.findById(id);
        return product.orElse(null);
    }

    @PostMapping("/insert")
    public Product insertProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProductById(@RequestBody Product product, @PathVariable int id) {
        Optional<Product> existingProductOptional = productRepo.findById(id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            return productRepo.save(existingProduct);
        }
        return null; // Handle not found case
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable int id) {
        productRepo.deleteById(id);
    }
}
