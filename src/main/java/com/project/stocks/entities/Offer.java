package com.project.stocks.entities;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Range(min = 1)
    private Integer percentage;

    public Offer() {}

    public Offer(Integer percentage) {
        this.percentage = percentage;
    }

    public Offer(Integer id, Integer percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", percentage=" + percentage +
                '}';
    }
}
