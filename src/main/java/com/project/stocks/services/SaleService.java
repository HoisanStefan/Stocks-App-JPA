package com.project.stocks.services;

import com.project.stocks.entities.Sale;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Map;

public interface SaleService {
    Integer countSales();
    List<Sale> getAllSales();

    @Operation(summary = "Inserts a new Sale row and updates the corresponding Product",
            description = "We check if the given request body is valid and then we retrieve the corresponding " +
                    "entities from the database. We then create a Sale object and populate it with data." +
                    "At last, we update the product in the database in insert the Sale object")
    Sale insertSale(Map<String, Object> sale);
    Sale getSaleById(Integer id);

    @Operation(summary = "Deletes an entity of type Sale", description = "Checks if there is an entity of type " +
            "Sale with the given ID and deletes it if found")
    boolean deleteSale(Integer id);
}
