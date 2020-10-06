package com.example.shop.dao;

import com.example.shop.models.ProductCategory;

public interface ProductCategoryDao {
    int insertProductCategory(ProductCategory productCategory);

    int updateProductCategoryById(int id, ProductCategory productCategory);

    int deleteProductCategoryById(int id);
}
