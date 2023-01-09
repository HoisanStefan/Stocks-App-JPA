package com.project.stocks.services;

import com.project.stocks.entities.Offer;

import java.util.List;

public interface OfferService {
    Integer countOffers();
    List<Offer> getAllOffers();
    Offer insertOffer(Integer percentage);
    Offer getOfferById(Integer id);

    Offer updateOffer(Integer id, Integer percentage);

    boolean deleteOffer(Integer id);
}
