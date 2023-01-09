package com.project.stocks.repositories;

import com.project.stocks.entities.Resupply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResupplyRepository extends JpaRepository<Resupply, Integer> {
    Optional<Resupply> findById(Integer id);
    void deleteById(Integer id);
}
