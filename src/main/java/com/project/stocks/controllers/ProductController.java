package com.project.stocks.controllers;

import com.project.stocks.entities.Product;
import com.project.stocks.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/product") // http://localhost:8080/product
    public ResponseEntity<Product> addProduct(@RequestBody Map<String, Object> input) {
        Product product = this.productService.insertProduct(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping(value = "/product/{id}") // http://localhost:8080/product
    public Product modifyProduct(@PathVariable(value = "id") int id, @RequestBody Map<String, Object> input) {
        return productService.updateProduct(id, input);
    }

    @GetMapping(value = "/products")  // http://localhost:8080/products
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/product/{id}")  // http://localhost:8080/product
    public Product getProductById(@PathVariable(value = "id") int id) {
        return productService.getProductById(id);
    }

    @GetMapping(value = "/product")  // http://localhost:8080/product
    public List<Product> getProductByName(@RequestBody Map<String, String> input) {
        return productService.getProductByName(input.get("name"));
    }
}
