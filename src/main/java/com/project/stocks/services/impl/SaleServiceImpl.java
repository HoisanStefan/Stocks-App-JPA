package com.project.stocks.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.stocks.entities.Product;
import com.project.stocks.entities.Sale;
import com.project.stocks.repositories.SaleRepository;
import com.project.stocks.repositories.impl.SaleRepositoryImpl;
import com.project.stocks.services.ProductService;
import com.project.stocks.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepositoryImpl saleRepositoryImpl;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Integer countSales() {
        //access DAO layer
        Integer count = this.saleRepositoryImpl.count();
        return count;
    }

    @Override
    public List<Sale> getAllSales() {
        return this.saleRepository.findAll();
    }

    @Override
    public Sale insertSale(Map<String, Object> sale) {
        try {
            Sale insertSale = new Sale();
            Integer product_id = (Integer) sale.get("product_id");
            Integer quantity = (Integer) sale.get("quantity");

            if (product_id == null) {
                throw new Exception("Argument 'product_id' must be provided : " + sale);
            }

            if (quantity == null) {
                throw new Exception("Argument 'quantity' must be provided : " + sale);
            }

            Product product = productService.getProductById(product_id);
            if (product.getQuantity() < quantity) {
                throw new Exception("Sale quantity is greater than the current stock for the product : " + sale +
                        System.lineSeparator() + product);
            }

            ObjectMapper objMapper = new ObjectMapper();
            product.setQuantity(product.getQuantity() - quantity);
            Map<String, Object> mappedProduct = objMapper.convertValue(product, Map.class);
            mappedProduct.put("resupply", true);
            productService.updateProduct(product.getId(), mappedProduct);

            insertSale.setQuantity(quantity);
            insertSale.setPrice_per_unit(product.getPrice());
            insertSale.setTotal_value(product.getPrice() * quantity);
            insertSale.setProduct(product);

            return this.saleRepository.save(insertSale);
        } catch (Exception e) {
            System.out.println("A intervenit o eroare:");
            if (e.getCause() == null) {
                System.out.println(e.getMessage());
            } else {
                System.out.println(e.getCause());
            }
            return new Sale();
        }
    }

    @Override
    public Sale getSaleById(Integer id) {
        Optional<Sale> sale = this.saleRepository.findById(id);
        return sale.orElse(new Sale());
    }

    @Override
    public boolean deleteSale(Integer id) {
        try {
            if (this.saleRepository.findById(id).isPresent()) {
                this.saleRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("A intervenit o eroare:");
            System.out.println(e.getCause());
            return false;
        }
    }
}
