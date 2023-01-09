package com.project.stocks.controllers;

import com.project.stocks.entities.Resupply;
import com.project.stocks.services.ResupplyService;
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
    public ResponseEntity<Resupply> addResupply(@RequestBody Map<String, Object> input) {
        Resupply resupply = this.resupplyService.insertResupply(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(resupply);
    }

    @DeleteMapping(value = "/resupply/{id}")  // http://localhost:8080/resupply
    public boolean deleteResupply(@PathVariable(value = "id") int id) {
        return resupplyService.deleteResupply(id);
    }

    @GetMapping(value = "/resupplies")  // http://localhost:8080/resupplies
    public List<Resupply> getAllResupplies() {
        return resupplyService.getAllResupplies();
    }

    @GetMapping(value = "/resupply/{id}")  // http://localhost:8080/resupply
    public Resupply getResupplyById(@PathVariable(value = "id") int id) {
        return resupplyService.getResupplyById(id);
    }
}
