package com.project.stocks.controllers;

import com.project.stocks.entities.Product;
import com.project.stocks.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Adds a new product in database")
    public ResponseEntity<Product> addProduct(@RequestBody Map<String, Object> input) {
        Product product = this.productService.insertProduct(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping(value = "/product/{id}") // http://localhost:8080/product
    @Operation(summary = "Updates a new Product entity")
    public Product modifyProduct(@PathVariable(value = "id") int id, @RequestBody Map<String, Object> input) {
        return productService.updateProduct(id, input);
    }

    @GetMapping(value = "/products")  // http://localhost:8080/products
    @Operation(summary = "Retrieves all products from database")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/product/{id}")  // http://localhost:8080/product
    @Operation(summary = "Retrieves a brand from database with the given ID",
            description = "If there's no entity identified by the given ID, it returns a null object")
    public Product getProductById(@PathVariable(value = "id") int id) {
        return productService.getProductById(id);
    }

    @GetMapping(value = "/product")  // http://localhost:8080/product
    @Operation(summary = "Retrieves a product from database with the given name",
            description = "If there's no entity identified by the given name, it returns a null object")
    public List<Product> getProductByName(@RequestBody Map<String, String> input) {
        return productService.getProductByName(input.get("name"));
    }
}
