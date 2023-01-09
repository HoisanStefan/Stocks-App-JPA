package com.project.stocks.services;

import com.project.stocks.entities.Product;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Integer countProducts();
    List<Product> getAllProducts();

    @Operation(summary = "Inserting a new Product in the database, recalculating its price if an offer is provided",
            description = "We first check the input and if there is any offer_id provided. We throw exceptions when" +
                    "the input is invalid. Then, we recalculate the product's price taking into account the price" +
                    "provided and if there is any offer. At last, we insert the product in the DB")
    Product insertProduct(Map<String, Object> product);
    Product getProductById(Integer id);
    List<Product> getProductByName(String name);

    @Operation(summary = "Updates a Product referenced by a given ID, also updates the price if offer is given",
            description = "We first check if the input is valid, then we update the price if a new one is provided," +
                    "after that, we recalculate the new price with the current offer (or the new one if provided)." +
                    "If no offer is provided, the offer will not be changed. At last, the product is updated in the DB")
    Product updateProduct(Integer id, Map<String, Object> product);

    @Operation(summary = "Deleting all references for the given offer",
            description = "We use a jdbcTemplate query to retrieve a list of the products that references the offer" +
                    "and then we delete that reference from every product, returning a list with the updated" +
                    "products. We also need to update the price, since the offer no longer applies")
    List<Product> deleteOfferFromProducts(Integer id);
}
