package com.project.stocks.services.impl;

import com.project.stocks.entities.Offer;
import com.project.stocks.entities.Product;
import com.project.stocks.repositories.OfferRepository;
import com.project.stocks.repositories.ProductRepository;
import com.project.stocks.repositories.impl.ProductRepositoryImpl;
import com.project.stocks.services.BrandService;
import com.project.stocks.services.ProductService;
import com.project.stocks.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Autowired
    private ProductRepositoryImpl productRepositoryImpl;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public Integer countProducts() {
        Integer count = this.productRepositoryImpl.count();
        return count;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product insertProduct(Map<String, Object> product) {
        try {
            Product insertedProduct = new Product();
            Double reduction = 0.0;

            insertedProduct.setProvider(providerService.getProviderById((Integer) product.get("provider_id")));
            insertedProduct.setBrand(brandService.getBrandById((Integer) product.get("brand_id")));

            if (product.get("offer_id") != null) {
                Optional<Offer> offer = offerRepository.findById((Integer) product.get("offer_id"));
                if (offer.isPresent()) {
                    insertedProduct.setOffer(offer.get());
                } else {
                    throw new Exception("Offer with offer_id: " + product.get("offer_id") + " doesn't exist");
                }
            }

            if (product.get("price_provider") == null) {
                throw new Exception("Argument 'price_provider' must be provided : " + product);
            }

            if (product.get("name") == null) {
                throw new Exception("Argument 'name' must be provided : " + product);
            }

            insertedProduct.setPriceProvider((Double) product.get("price_provider"));

            if (insertedProduct.getOffer() != null) {
                Integer temp = insertedProduct.getOffer().getPercentage();
                reduction = (double) temp;
            }

            Double newReduction = reduction / 100;
            Double newPrice = insertedProduct.getPriceProvider() * 1.3;
            newPrice = newPrice * (1.0 - newReduction);
            insertedProduct.setQuantity(0);
            insertedProduct.setPrice(Double.parseDouble(df.format(newPrice)));
            insertedProduct.setName((String) product.get("name"));

            return this.productRepository.save(insertedProduct);
        } catch (Exception e) {
            System.out.println("A intervenit o eroare:");
            if (e.getCause() == null) {
                System.out.println(e.getMessage());
            } else {
                System.out.println(e.getCause());
            }
            return new Product();
        }
    }

    @Override
    public Product getProductById(Integer id) {
        Optional<Product> product = this.productRepository.findById(id);
        return product.orElse(new Product());
    }

    @Override
    public List<Product> getProductByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public Product updateProduct(Integer id, Map<String, Object> product) {
        try {
            Optional<Product> fetchProduct = this.productRepository.findById(id);

            if (fetchProduct.isEmpty()) {
                throw new Exception("The product with id=" + id + ", doesn't exist");
            }

            Product newProduct = fetchProduct.get();
            Integer price_reduction = 0;

            if (product.get("price_provider") != null) {
                newProduct.setPriceProvider((Double) product.get("price_provider"));
                newProduct.setPrice((Double) product.get("price_provider") * 1.3);
            }

            if (product.get("resupply") != null) {
                newProduct.setQuantity((Integer) product.get("quantity"));
            }

            if (product.get("offer_id") != null) {
                Optional<Offer> offer = offerRepository.findById((Integer) product.get("offer_id"));
                if (offer.isPresent()) {
                    newProduct.setOffer(offer.get());
                } else {
                    throw new Exception("The offer with 'offer_id'=" + product.get("offer_id") + " doesn't exist");
                }
            } else {
                newProduct.setOffer(null);
                newProduct.setPrice(newProduct.getPriceProvider() * 1.3);
            }

            if (newProduct.getOffer() != null) {
                price_reduction = newProduct.getOffer().getPercentage();
            }

            if (product.get("resupply") == null) {
                Double reduction = (double) price_reduction;
                reduction = reduction / 100;
                Double newPrice = newProduct.getPriceProvider() * 1.3;
                newPrice = newPrice * (1.0 - reduction);
                newProduct.setPrice(Double.parseDouble(df.format(newPrice)));
            }

            if (product.get("name") != null) {
                newProduct.setName((String) product.get("name"));
            }

            return this.productRepository.save(newProduct);
        } catch (Exception e) {
            System.out.println("A intervenit o eroare:");
            if (e.getCause() == null) {
                System.out.println(e.getMessage());
            } else {
                System.out.println(e.getCause());
            }
            return new Product();
        }
    }

    @Override
    public List<Product> deleteOfferFromProducts(Integer id) {
        List<Product> products = this.productRepositoryImpl.getProductsByOfferId(id);

        for (Product p : products) {
            p.setOffer(null);
            p.setPrice(p.getPriceProvider() * 1.3);
            this.productRepository.save(p);
        }
        return products;
    }
}
