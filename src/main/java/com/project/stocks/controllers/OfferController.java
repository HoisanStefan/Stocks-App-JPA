package com.project.stocks.controllers;

import com.project.stocks.entities.Offer;
import com.project.stocks.services.OfferService;
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
    public ResponseEntity<Offer> addOffer(@RequestBody @Valid Offer input) {
        Offer offer = this.offerService.insertOffer(input.getPercentage());
        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    @GetMapping(value = "/offers")  // http://localhost:8080/offers
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping(value = "/offer/{id}")  // http://localhost:8080/offer
    public Offer getOfferById(@PathVariable(value = "id") int id) {
        return offerService.getOfferById(id);
    }

    @PutMapping(value = "/offer") // http://localhost:8080/offer
    public Offer updateOffer(@RequestBody @Valid Offer input) {
        return offerService.updateOffer(input.getId(), input.getPercentage());
    }

    @DeleteMapping(value = "/offer/{id}")  // http://localhost:8080/offer
    public boolean deleteOffer(@PathVariable(value = "id") int id) {
        return offerService.deleteOffer(id);
    }
}
