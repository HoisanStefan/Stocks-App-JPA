package com.project.stocks.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.stocks.entities.Product;
import com.project.stocks.entities.Resupply;
import com.project.stocks.repositories.ResupplyRepository;
import com.project.stocks.repositories.impl.ResupplyRepositoryImpl;
import com.project.stocks.services.ProductService;
import com.project.stocks.services.ProviderService;
import com.project.stocks.services.ResupplyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ResupplyServiceImpl implements ResupplyService {
    @Autowired
    private ResupplyRepositoryImpl resupplyRepositoryImpl;

    @Autowired
    private ResupplyRepository resupplyRepository;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ProductService productService;

    @Override
    public Integer countResupplies() {
        //access DAO layer
        Integer count = this.resupplyRepositoryImpl.count();
        return count;
    }

    @Override
    public List<Resupply> getAllResupplies() {
        return this.resupplyRepository.findAll();
    }

    @Override
    @Operation(summary = "Inserts a new Resupply row and updates the corresponding Product",
            description = "We check if the given request body is valid and then we retrieve the corresponding " +
                    "entities from the database. We then create a Resupply object and populate it with data." +
                    "At last, we update the product in the database in insert the Resupply object")
    public Resupply insertResupply(Map<String, Object> resupply) {
        try {
            Resupply insertResupply = new Resupply();
            Integer product_id = (Integer) resupply.get("product_id");
            Integer provider_id = (Integer) resupply.get("provider_id");
            Integer quantity = (Integer) resupply.get("quantity");

            if (provider_id == null) {
                throw new Exception("Argument 'provider_id' must be provided : " + resupply);
            }

            if (product_id == null) {
                throw new Exception("Argument 'product_id' must be provided : " + resupply);
            }

            if (quantity == null) {
                throw new Exception("Argument 'quantity' must be provided : " + resupply);
            }

            ObjectMapper objMapper = new ObjectMapper();

            Product product = productService.getProductById(product_id);

            if (!Objects.equals(product.getProvider().getId(), provider_id)) {
                throw new Exception("The product with 'product_id'=" + product_id +
                " isn't supplied by the provider with 'provider_id'=" + provider_id + System.lineSeparator() + product);
            }

            product.setQuantity(product.getQuantity() + quantity);

            Map<String, Object> mappedProduct = objMapper.convertValue(product, Map.class);
            mappedProduct.put("resupply", true);
            productService.updateProduct(product.getId(), mappedProduct);

            insertResupply.setProvider(providerService.getProviderById(provider_id));
            insertResupply.setProduct(product);
            insertResupply.setQuantity(quantity);

            return this.resupplyRepository.save(insertResupply);
        } catch (Exception e) {
            System.out.println("A intervenit o eroare:");
            if (e.getCause() == null) {
                System.out.println(e.getMessage());
            } else {
                System.out.println(e.getCause());
            }
            return new Resupply();
        }
    }

    @Override
    public Resupply getResupplyById(Integer id) {
        Optional<Resupply> resupply = this.resupplyRepository.findById(id);
        return resupply.orElse(new Resupply());
    }

    @Override
    @Operation(summary = "Deletes an entity of type Resupply", description = "Checks if there is an entity of type " +
            "Resupply with the given ID and deletes it if found")
    public boolean deleteResupply(Integer id) {
        try {
            if (this.resupplyRepository.findById(id).isPresent()) {
                this.resupplyRepository.deleteById(id);
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
