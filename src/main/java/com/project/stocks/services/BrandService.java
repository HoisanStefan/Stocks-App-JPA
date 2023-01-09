package com.project.stocks.services;

import com.project.stocks.entities.Brand;

import java.util.List;

public interface BrandService {
    Integer countBrands();
    List<Brand> getAllBrands();
    Brand insertBrand(String name);
    Brand getBrandById(Integer id);
    List<Brand> getBrandByName(String name);
}
