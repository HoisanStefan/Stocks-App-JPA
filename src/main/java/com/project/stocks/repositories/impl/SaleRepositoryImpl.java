package com.project.stocks.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SaleRepositoryImpl {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SaleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer count() {
        Integer rowCount = this.jdbcTemplate.queryForObject("select count(*) from sales", Integer.class);
        return rowCount;
    }
}
