package com.project.stocks.repositories;

import com.project.stocks.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    Optional<Sale> findById(Integer id);
    void deleteById(Integer id);
}
