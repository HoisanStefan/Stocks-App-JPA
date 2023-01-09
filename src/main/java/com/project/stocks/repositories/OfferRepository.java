package com.project.stocks.repositories;

import com.project.stocks.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Optional<Offer> findById(Integer id);
    void deleteById(Integer id);
}
