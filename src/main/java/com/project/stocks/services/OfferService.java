package com.project.stocks.services;

import com.project.stocks.entities.Offer;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

public interface OfferService {
    Integer countOffers();
    List<Offer> getAllOffers();
    Offer insertOffer(Integer percentage);
    Offer getOfferById(Integer id);

    @Operation(summary = "Checks if Offer with given id exists and updates it with the given percentage")
    Offer updateOffer(Integer id, Integer percentage);

    @Operation(summary = "Deletes the offer from products and then deletes the offer",
            description = "We first search all the references to the current offer in the products table." +
                    "After we found all the products referencing the offer, we can safely delete the offer")
    boolean deleteOffer(Integer id);
}
