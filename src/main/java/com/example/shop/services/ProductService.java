package com.example.shop.services;

import java.util.List;
import java.util.Optional;

import com.example.shop.dao.ProductDao;
import com.example.shop.models.Category;
import com.example.shop.models.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(@Qualifier("productDao") ProductDao productDao) {
        this.productDao = productDao;
    }

    public int addProduct(Product product) {
        return productDao.insertProduct(product);
    }

    public List<Product> getAllProduct() {
        return productDao.getAllProducts();
    }

    public Optional<Product> getProductById(int id) {
        return productDao.getProductById(id);
    }

    public List<Category> getCategoriesByProductId(int id) {
        return productDao.getCategoriesByProductId(id);
    }

    public int updateProductById(int id, Product product) {
        return productDao.updateProductById(id, product);
    }

    public int deleteProductById(int id) {
        return productDao.deleteProductById(id);
    }
}
