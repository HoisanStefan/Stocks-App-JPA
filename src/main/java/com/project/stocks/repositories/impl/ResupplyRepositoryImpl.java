package com.project.stocks.repositories.impl;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ResupplyRepositoryImpl {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ResupplyRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Operation(summary = "Retrieves the number of rows from the given table")
    public Integer count() {
        Integer rowCount = this.jdbcTemplate.queryForObject("select count(*) from resupplies", Integer.class);
        return rowCount;
    }
}
