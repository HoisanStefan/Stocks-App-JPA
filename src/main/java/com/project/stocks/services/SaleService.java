package com.project.stocks.services;

import com.project.stocks.entities.Sale;

import java.util.List;
import java.util.Map;

public interface SaleService {
    Integer countSales();
    List<Sale> getAllSales();
    Sale insertSale(Map<String, Object> sale);
    Sale getSaleById(Integer id);
    boolean deleteSale(Integer id);
}
