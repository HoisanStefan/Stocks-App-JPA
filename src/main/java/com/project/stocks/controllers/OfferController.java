package com.project.stocks.controllers;

import com.project.stocks.entities.Offer;
import com.project.stocks.services.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController {
    @Autowired
    private OfferService offerService;

    @PostMapping(value = "/offer") // http://localhost:8080/offer
    @Operation(summary = "Adds a new offer in database")
    public ResponseEntity<Offer> addOffer(@RequestBody @Valid Offer input) {
        Offer offer = this.offerService.insertOffer(input.getPercentage());
        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    @GetMapping(value = "/offers")  // http://localhost:8080/offers
    @Operation(summary = "Retrieves all offers from database")
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping(value = "/offer/{id}")  // http://localhost:8080/offer
    @Operation(summary = "Retrieves an offer from database with the given ID",
            description = "If there's no entity identified by the given ID, it returns a null object")
    public Offer getOfferById(@PathVariable(value = "id") int id) {
        return offerService.getOfferById(id);
    }

    @PutMapping(value = "/offer") // http://localhost:8080/offer
    @Operation(summary = "Updates a new Offer entity")
    public Offer updateOffer(@RequestBody @Valid Offer input) {
        return offerService.updateOffer(input.getId(), input.getPercentage());
    }

    @DeleteMapping(value = "/offer/{id}")  // http://localhost:8080/offer
    @Operation(summary = "Searches an entity of type Offer by ID and deletes it if found")
    public boolean deleteOffer(@PathVariable(value = "id") int id) {
        return offerService.deleteOffer(id);
    }
}
