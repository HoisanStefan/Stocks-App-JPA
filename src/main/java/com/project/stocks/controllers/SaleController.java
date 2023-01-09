package com.project.stocks.controllers;

import com.project.stocks.entities.Sale;
import com.project.stocks.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping(value = "/sale") // http://localhost:8080/sale
    public ResponseEntity<Sale> addSale(@RequestBody Map<String, Object> input) {
        Sale sale = this.saleService.insertSale(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }

    @DeleteMapping(value = "/sale/{id}")  // http://localhost:8080/sale
    public boolean deleteSale(@PathVariable(value = "id") int id) {
        return saleService.deleteSale(id);
    }

    @GetMapping(value = "/sales")  // http://localhost:8080/sales
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping(value = "/sale/{id}")  // http://localhost:8080/sale
    public Sale getSaleById(@PathVariable(value = "id") int id) {
        return saleService.getSaleById(id);
    }
}
