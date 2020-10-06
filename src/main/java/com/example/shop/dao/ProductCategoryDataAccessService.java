package com.example.shop.dao;

import com.example.shop.models.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("productCategoryDao")
public class ProductCategoryDataAccessService implements ProductCategoryDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductCategoryDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertProductCategory(ProductCategory productCategory) {
        final var sql = "INSERT INTO product_category_pivot (product_id, category_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql, productCategory.getProduct_id(), productCategory.getCategory_id());
    }

    @Override
    public int deleteProductCategoryById(int id) {
        final var sql = "DELETE FROM product_category_pivot WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int deleteProductCategoryByColumn(String name, int value) {
        final var sql = String.format("DELETE FROM product_category_pivot WHERE %s = ?", name);
        return jdbcTemplate.update(sql, value);
    }
}
