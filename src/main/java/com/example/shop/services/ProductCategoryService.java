package com.example.shop.services;

import com.example.shop.dao.ProductCategoryDao;
import com.example.shop.models.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {
    private final ProductCategoryDao productCategoryDao;

    @Autowired
    public ProductCategoryService(@Qualifier("productCategoryDao") ProductCategoryDao productCategoryDao) {
        this.productCategoryDao = productCategoryDao;
    }

    public int addProductCategory(ProductCategory productCategory) {
        return productCategoryDao.insertProductCategory(productCategory);
    }

    public int deleteProductCategoryById(int id) {
        return productCategoryDao.deleteProductCategoryById(id);
    }

    public int deleteProductCategoryByColumn(String name, int id) {
        return productCategoryDao.deleteProductCategoryByColumn(name, id);
    }
}
