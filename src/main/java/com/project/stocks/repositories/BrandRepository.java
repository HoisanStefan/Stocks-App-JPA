package com.project.stocks.repositories;

import com.project.stocks.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Optional<Brand> findById(Integer id);
    List<Brand> findByName(String name);
}
