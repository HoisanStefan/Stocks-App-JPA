package com.project.stocks.controllers;

import com.project.stocks.entities.Resupply;
import com.project.stocks.services.ResupplyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ResupplyController {
    @Autowired
    private ResupplyService resupplyService;

    @PostMapping(value = "/resupply") // http://localhost:8080/resupply
    @Operation(summary = "Adds a new resupply in database")
    public ResponseEntity<Resupply> addResupply(@RequestBody Map<String, Object> input) {
        Resupply resupply = this.resupplyService.insertResupply(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(resupply);
    }

    @DeleteMapping(value = "/resupply/{id}")  // http://localhost:8080/resupply
    @Operation(summary = "Searches an entity of type Resupply by ID and deletes it if found")
    public boolean deleteResupply(@PathVariable(value = "id") int id) {
        return resupplyService.deleteResupply(id);
    }

    @GetMapping(value = "/resupplies")  // http://localhost:8080/resupplies
    @Operation(summary = "Retrieves all resupplies from database")
    public List<Resupply> getAllResupplies() {
        return resupplyService.getAllResupplies();
    }

    @GetMapping(value = "/resupply/{id}")  // http://localhost:8080/resupply
    @Operation(summary = "Retrieves a resupply from database with the given ID",
            description = "If there's no entity identified by the given ID, it returns a null object")
    public Resupply getResupplyById(@PathVariable(value = "id") int id) {
        return resupplyService.getResupplyById(id);
    }
}
