package com.project.stocks.controllers;

import com.project.stocks.entities.Provider;
import com.project.stocks.services.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @PostMapping(value = "/provider") // http://localhost:8080/provider
    @Operation(summary = "Adds a new provider in database")
    public ResponseEntity<Provider> addProvider(@RequestBody @Valid Provider input) {
        Provider provider = this.providerService.insertProvider(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(provider);
    }

    @GetMapping(value = "/provider/{id}")  // http://localhost:8080/provider
    @Operation(summary = "Retrieves a provider from database with the given ID",
            description = "If there's no entity identified by the given ID, it returns a null object")
    public Provider getProviderById(@PathVariable(value = "id") int id) {
        return providerService.getProviderById(id);
    }

    @GetMapping(value = "/providers")  // http://localhost:8080/providers
    @Operation(summary = "Retrieves all providers from database")
    public List<Provider> getAllProviders() {
        return providerService.getAllProviders();
    }

    @GetMapping(value = "/provider")  // http://localhost:8080/provider
    @Operation(summary = "Retrieves a provider from database with the given name",
            description = "If there's no entity identified by the given name, it returns a null object")
    public List<Provider> getProviderByName(@RequestBody @Valid Provider input) {
        return providerService.getProviderByName(input.getName());
    }
}
