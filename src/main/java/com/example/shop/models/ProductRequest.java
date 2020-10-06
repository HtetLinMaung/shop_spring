package com.example.shop.models;

import java.util.List;

public class ProductRequest {
    private final String name;
    private final String description;
    private final double price;
    private final double discount_price;
    private final String image_url;
    private final List<Integer> categories;

    public ProductRequest(String name, String description, double price, double discount_price, String image_url, List<Integer> categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount_price = discount_price;
        this.image_url = image_url;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount_price() {
        return discount_price;
    }

    public String getImage_url() {
        return image_url;
    }

    public List<Integer> getCategories() {
        return categories;
    }
}
