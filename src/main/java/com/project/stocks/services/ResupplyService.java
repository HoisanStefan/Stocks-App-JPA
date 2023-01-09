package com.project.stocks.services;

import com.project.stocks.entities.Resupply;

import java.util.List;
import java.util.Map;

public interface ResupplyService {
    Integer countResupplies();
    List<Resupply> getAllResupplies();
    Resupply insertResupply(Map<String, Object> resupply);
    Resupply getResupplyById(Integer id);
    boolean deleteResupply(Integer id);
}
