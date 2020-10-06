package com.example.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.shop.models.Category;
import com.example.shop.models.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDataAccessService implements ProductDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertProduct(Product product) {
        final var sql = "INSERT INTO product (name, description, price, discount_percent, image_url) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setDouble(3, product.getPrice());
                ps.setDouble(4, product.getDiscount_percent());
                ps.setString(5, product.getImage_url());
                return ps;
            }
        }, keyHolder);
         Long id = (Long) keyHolder.getKeys().get("id");
         return id.intValue();
    }

    @Override
    public List<Product> getAllProducts() {
        final var sql = "SELECT id, name, description, price, discount_percent, image_url, created_at, updated_at, deleted_at FROM product WHERE deleted_at IS NULL";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            var id = resultSet.getInt("id");
            var name = resultSet.getString("name");
            var description = resultSet.getString("description");
            var price = resultSet.getDouble("price");
            var discount_percent = resultSet.getDouble("discount_percent");
            var created_at = resultSet.getTimestamp("created_at");
            var updated_at = resultSet.getTimestamp("updated_at");
            var deleted_at = resultSet.getTimestamp("deleted_at");
            var image_url = resultSet.getString("image_url");

            return new Product(id, name, description, price, discount_percent, image_url, created_at, updated_at, deleted_at);
        });
    }

    @Override
    public Optional<Product> getProductById(int id) {
        final var sql = "SELECT id, name, description, price, discount_percent, image_url, created_at, updated_at, deleted_at FROM product WHERE id = ? AND deleted_at IS NULL";
        var product = jdbcTemplate.queryForObject(sql, new Object[] { id }, (resultSet, i) -> {
            var productId = resultSet.getInt("id");
            var name = resultSet.getString("name");
            var description = resultSet.getString("description");
            var price = resultSet.getDouble("price");
            var discount_percent = resultSet.getDouble("discount_percent");
            var created_at = resultSet.getTimestamp("created_at");
            var updated_at = resultSet.getTimestamp("updated_at");
            var deleted_at = resultSet.getTimestamp("deleted_at");
            var image_url = resultSet.getString("image_url");
            return new Product(productId, name, description, price, discount_percent, image_url, created_at, updated_at, deleted_at);
        });
        if (product == null) {
            return Optional.empty();
        }
        return Optional.of(product);
    }

        @Override
    public List<Category> getCategoriesByProductId(int id) {
        final var sql = "SELECT c.id, c.name, c.created_at, c.updated_at, c.deleted_at FROM category AS c LEFT JOIN product_category_pivot AS pv ON c.id = pv.category_id WHERE c.deleted_at IS NULL AND pv.product_id = ?";
        return jdbcTemplate.query(sql, new Object[] {id}, (resultSet, i) -> {
            var categoryId = resultSet.getInt("id");
            var name = resultSet.getString("name");
            var created_at = resultSet.getTimestamp("created_at");
            var updated_at = resultSet.getTimestamp("updated_at");
            var deleted_at = resultSet.getTimestamp("deleted_at");
            return new Category(categoryId, name, created_at, updated_at, deleted_at);
        });
    }

    @Override
    public int updateProductById(int id, Product product) {
        final var sql = "UPDATE product SET name = ?, description = ?, price = ?, discount_percent = ?, image_url = ?, updated_at = NOW() WHERE id = ?";

        return jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(),
                product.getDiscount_percent(), product.getImage_url(), id);
    }

    @Override
    public int deleteProductById(int id) {
        final var sql = "UPDATE product SET deleted_at = NOW() WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
