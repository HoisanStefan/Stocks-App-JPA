package com.project.stocks.services;

import com.project.stocks.entities.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Integer countProducts();
    List<Product> getAllProducts();
    Product insertProduct(Map<String, Object> product);
    Product getProductById(Integer id);
    List<Product> getProductByName(String name);
    Product updateProduct(Integer id, Map<String, Object> product);
    List<Product> deleteOfferFromProducts(Integer id);
}
