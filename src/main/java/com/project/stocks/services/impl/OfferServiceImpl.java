package com.project.stocks.services.impl;

import com.project.stocks.entities.Offer;
import com.project.stocks.repositories.OfferRepository;
import com.project.stocks.repositories.impl.OfferRepositoryImpl;
import com.project.stocks.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepositoryImpl offerRepositoryImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public Integer countOffers() {
        //access DAO layer
        Integer count = this.offerRepositoryImpl.count();
        return count;
    }

    @Override
    public List<Offer> getAllOffers() {
        return this.offerRepository.findAll();
    }

    @Override
    public Offer insertOffer(Integer percentage) {
        try {
            return this.offerRepository.save(new Offer(percentage));
        } catch (Exception e) {
            System.out.println("A intervenit o eroare:");
            System.out.println(e.getCause());
            return new Offer();
        }
    }

    @Override
    public Offer updateOffer(Integer id, Integer percentage) {
        try {
            Optional<Offer> offer = this.offerRepository.findById(id);
            if (offer.isPresent()) {
                Offer updatedOffer = offer.get();
                updatedOffer.setPercentage(percentage);
                return this.offerRepository.save(updatedOffer);
            } else {
                return new Offer();
            }
        } catch (Exception e) {
            System.out.println("A intervenit o eroare:");
            System.out.println(e.getCause());
            return new Offer();
        }
    }

    @Override
    public boolean deleteOffer(Integer id){
        try {
            if (this.offerRepository.findById(id).isPresent()) {
                this.productServiceImpl.deleteOfferFromProducts(id);
                this.offerRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("A intervenit o eroare:");
            System.out.println(e.getCause());
            return false;
        }
    }

    @Override
    public Offer getOfferById(Integer id) {
        Optional<Offer> offer = this.offerRepository.findById(id);
        return offer.orElse(new Offer());
    }
}
