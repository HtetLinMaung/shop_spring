package com.example.shop.models;

import java.sql.Timestamp;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private double discount_percent;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
    private String image_url;


    public Product(int id, String name, String description, double price, double discount_percent, String image_url, Timestamp created_at,
            Timestamp updated_at, Timestamp deleted_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount_percent = discount_percent;
        this.image_url = image_url;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public double getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(double discount_percent) {
        this.discount_percent = discount_percent;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

