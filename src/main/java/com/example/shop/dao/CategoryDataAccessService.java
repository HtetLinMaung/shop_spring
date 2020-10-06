package com.example.shop.dao;

import com.example.shop.models.Category;
import com.example.shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("categoryDao")
public class CategoryDataAccessService implements CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertCategory(Category category) {
        final var sql = "INSERT INTO category (name) VALUES (?)";
        return jdbcTemplate.update(sql, category.getName());
    }

    @Override
    public List<Category> getAllCategories() {
        final var sql = "SELECT id, name, created_at, updated_at, deleted_at FROM category WHERE deleted_at IS NULL";
        return jdbcTemplate.query(sql, (resultSet, i)-> {
            var id = resultSet.getInt("id");
            var name = resultSet.getString("name");
            var created_at = resultSet.getTimestamp("created_at");
            var updated_at = resultSet.getTimestamp("updated_at");
            var deleted_at = resultSet.getTimestamp("deleted_at");
            return new Category(id, name, created_at, updated_at, deleted_at);
        });
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        final var sql = "SELECT id, name, created_at, updated_at, deleted_at FROM category WHERE deleted_at IS NULL AND id = ?";
        var category = jdbcTemplate.queryForObject(sql, new Object[] {id}, (resultSet, i) -> {
            var categoryId = resultSet.getInt("id");
            var name = resultSet.getString("name");
            var created_at = resultSet.getTimestamp("created_at");
            var updated_at = resultSet.getTimestamp("updated_at");
            var deleted_at = resultSet.getTimestamp("deleted_at");
            return new Category(categoryId, name, created_at, updated_at, deleted_at);
        });
        if (category == null) {
            return Optional.empty();
        }
        return Optional.of(category);
    }

    @Override
    public List<Product> getProductsByCategoryId(int id) {
        final var sql = "SELECT p.id, p.name, p.description, p.price, p.discount_percent, p.image_url, p.created_at, p.updated_at, p.deleted_at FROM product AS p LEFT JOIN product_category_pivot AS pv ON p.id = pv.product_id WHERE p.deleted_at IS NULL AND pv.category_id = ?";
        return jdbcTemplate.query(sql, new Object[] {id}, (resultSet, i) -> {
            var productId = resultSet.getInt("id");
            var name = resultSet.getString("name");
            var description = resultSet.getString("description");
            var price = resultSet.getDouble("price");
            var discount_percent = resultSet.getDouble("discount_percent");
            var image_url = resultSet.getString("image_url");
            var created_at = resultSet.getTimestamp("created_at");
            var updated_at = resultSet.getTimestamp("updated_at");
            var deleted_at = resultSet.getTimestamp("deleted_at");
            return new Product(productId, name, description, price, discount_percent, image_url, created_at, updated_at, deleted_at);
        });
    }

    @Override
    public int updateCategoryById(int id, Category category) {
        final var sql = "UPDATE category SET name = ?, updated_at = NOW() WHERE id = ?";
        return jdbcTemplate.update(sql, category.getName(), id);
    }

    @Override
    public int deleteCategoryById(int id) {
        final var sql = "UPDATE category SET deleted_at = NOW() WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
