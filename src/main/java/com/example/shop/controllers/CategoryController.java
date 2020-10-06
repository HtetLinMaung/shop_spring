package com.example.shop.controllers;

import com.example.shop.models.Category;
import com.example.shop.models.Product;
import com.example.shop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/categories")
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.status(201).body(null);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(path = "{id}")
    public Category getCategoryById(@PathVariable("id") String id) {
        return categoryService.getCategoryById(Integer.parseInt(id)).orElse(null);
    }

    @GetMapping(path = "{id}/products")
    public List<Product> getProductsByCategoryId(@PathVariable("id") String id) {
        return categoryService.getProductsByCategoryId(Integer.parseInt(id));
    }

    @PutMapping(path = "{id}")
    public void updateCategoryById(@PathVariable("id") String id, @RequestBody Category category) {
        categoryService.updateCategoryById(Integer.parseInt(id), category);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") String id) {
        categoryService.deleteCategoryById(Integer.parseInt(id));
        return ResponseEntity.status(204).body(null);
    }
}
