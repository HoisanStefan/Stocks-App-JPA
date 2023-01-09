package com.project.stocks.services.impl;

import com.project.stocks.entities.Brand;
import com.project.stocks.repositories.BrandRepository;
import com.project.stocks.repositories.impl.BrandRepositoryImpl;
import com.project.stocks.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepositoryImpl brandRepositoryImpl;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Integer countBrands() {
        //access DAO layer
        Integer count = this.brandRepositoryImpl.count();
        return count;
    }

    @Override
    public List<Brand> getAllBrands() {
        return this.brandRepository.findAll();
    }

    @Override
    public Brand getBrandById(Integer id) {
        Optional<Brand> brand = this.brandRepository.findById(id);
        return brand.orElse(new Brand());
    }

    @Override
    public Brand insertBrand(String name) {
        try {
            return this.brandRepository.save(new Brand(name));
        } catch (Exception e) {
            System.out.println("A intervenit o eroare:");
            System.out.println(e.getCause());
            return new Brand();
        }
    }

    @Override
    public List<Brand> getBrandByName(String name) {
        return this.brandRepository.findByName(name);
    }
}
