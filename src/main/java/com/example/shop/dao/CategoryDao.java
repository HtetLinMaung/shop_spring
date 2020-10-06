package com.example.shop.dao;

import java.util.List;
import java.util.Optional;

import com.example.shop.models.Category;
import com.example.shop.models.Product;

public interface CategoryDao {
    int insertCategory(Category category);

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(int id);

//    List<Category> getCategoriesByProductId(int id);
    List<Product> getProductsByCategoryId(int id);

    int updateCategoryById(int id, Category category);

    int deleteCategoryById(int id);
}
