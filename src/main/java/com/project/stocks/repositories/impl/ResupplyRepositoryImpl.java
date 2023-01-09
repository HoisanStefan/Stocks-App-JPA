package com.project.stocks.repositories.impl;

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

    public Integer count() {
        Integer rowCount = this.jdbcTemplate.queryForObject("select count(*) from resupplies", Integer.class);
        return rowCount;
    }
}
