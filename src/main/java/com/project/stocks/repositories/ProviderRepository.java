package com.project.stocks.repositories;

import com.project.stocks.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    Optional<Provider> findById(Integer id);
    List<Provider> findByName(String name);
}
