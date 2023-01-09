package com.project.stocks.controllers;

import com.project.stocks.entities.Brand;
import com.project.stocks.services.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping(value = "/brand") // http://localhost:8080/brand
    @Operation(summary = "Adds a new brand in database")
    public ResponseEntity<Brand> addBrand(@RequestBody @Valid Brand input) {
        Brand brand = this.brandService.insertBrand(input.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(brand);
    }

    @GetMapping(value = "/brand/{id}")  // http://localhost:8080/brand
    @Operation(summary = "Retrieves a brand from database with the given ID",
            description = "If there's no entity identified by the given ID, it returns a null object")
    public Brand getBrandById(@PathVariable(value = "id") int id) {
        return brandService.getBrandById(id);
    }


    @GetMapping(value = "/brands")  // http://localhost:8080/brands
    @Operation(summary = "Retrieves all brands from database")
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping(value = "/brand")  // http://localhost:8080/brand
    @Operation(summary = "Retrieves a brand from database with the given name",
            description = "If there's no entity identified by the given ID, it returns a null object")
    public List<Brand> getBrandByName(@RequestBody @Valid Brand input) {
        return brandService.getBrandByName(input.getName());
    }
}
