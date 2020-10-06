package com.example.shop.dao;

import java.util.List;
import java.util.Optional;

import com.example.shop.models.Category;
import com.example.shop.models.Product;

public interface ProductDao {
    int insertProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(int id);

    List<Category> getCategoriesByProductId(int id);

    int updateProductById(int id, Product product);

    int deleteProductById(int id);
}
