package com.project.stocks.services;

import com.project.stocks.entities.Resupply;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Map;

public interface ResupplyService {
    Integer countResupplies();
    List<Resupply> getAllResupplies();

    @Operation(summary = "Inserts a new Resupply row and updates the corresponding Product",
            description = "We check if the given request body is valid and then we retrieve the corresponding " +
                    "entities from the database. We then create a Resupply object and populate it with data." +
                    "At last, we update the product in the database in insert the Resupply object")
    Resupply insertResupply(Map<String, Object> resupply);
    Resupply getResupplyById(Integer id);

    @Operation(summary = "Deletes an entity of type Resupply", description = "Checks if there is an entity of type " +
            "Resupply with the given ID and deletes it if found")
    boolean deleteResupply(Integer id);
}
