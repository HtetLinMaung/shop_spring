package com.example.shop.services;

import com.example.shop.dao.CategoryDao;
import com.example.shop.models.Category;
import com.example.shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(@Qualifier("categoryDao") CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public int addCategory(Category category) {
        return categoryDao.insertCategory(category);
    }

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }

    public List<Product> getProductsByCategoryId(int id) {
        return categoryDao.getProductsByCategoryId(id);
    }

    public int updateCategoryById(int id, Category category) {
        return categoryDao.updateCategoryById(id, category);
    }

    public int deleteCategoryById(int id) {
        return categoryDao.deleteCategoryById(id);
    }
}
