package com.project.stocks.repositories.impl;

import com.project.stocks.entities.Offer;
import com.project.stocks.entities.Product;
import com.project.stocks.repositories.OfferRepository;
import com.project.stocks.services.BrandService;
import com.project.stocks.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepositoryImpl {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer count() {
        Integer rowCount = this.jdbcTemplate.queryForObject("select count(*) from products", Integer.class);
        return rowCount;
    }

    public List<Product> getProductsByOfferId(Integer id) {
        List<Product> products = this.jdbcTemplate.query("select * from products where offer_id=" + id, new RowMapper<Product>(){
            @Override
            public Product mapRow(ResultSet rs, int rownumber) throws SQLException {
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setQuantity(rs.getInt(3));
                p.setPrice(rs.getDouble(4));
                p.setPriceProvider(rs.getDouble(5));
                p.setProvider(providerService.getProviderById(rs.getInt(6)));
                p.setBrand(brandService.getBrandById(rs.getInt(7)));
                p.setOffer(offerRepository.findById(rs.getInt(8)).orElse(new Offer()));

                return p;
            }
        });
        return products;
    }
}
