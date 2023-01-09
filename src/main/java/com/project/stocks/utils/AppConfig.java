package com.project.stocks.utils;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    @Operation(summary = "Creating bean for injecting the datasource in the jdbcTemplate constructor")
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:file:./h2db");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }

    @Bean
    @Operation(summary = "Creating bean for injecting the jdbcTemplate in repositories")
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}