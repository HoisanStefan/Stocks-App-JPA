package com.project.stocks.entities;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(unique=true)
    private String name;

    @Range(min = 0)
    private Integer quantity;

    @Range(min = 0)
    private Double price;

    @NotNull
    @Range(min = 0)
    @Column(name = "price_provider")
    private Double priceProvider;

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private Offer offer;

    public Product() {}

    public Product(Product product) {
        this.name = product.name;
        this.quantity = 0;
        this.priceProvider = product.priceProvider;
        this.price = product.price;
        this.brand = product.brand;
        this.offer = product.offer;
        this.provider = product.provider;
    }

    public Product(Provider provider) {
        this.provider = provider;
    }

    public Product(Brand brand) {
        this.brand = brand;
    }

    public Product(Offer offer) {
        this.offer = offer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceProvider() {
        return priceProvider;
    }

    public void setPriceProvider(double priceProvider) {
        this.priceProvider = priceProvider;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", priceProvider=" + priceProvider +
                ", provider=" + provider +
                ", brand=" + brand +
                ", offer=" + offer +
                '}';
    }
}
