package com.example.shop.controllers;

import java.util.List;

import com.example.shop.models.Category;
import com.example.shop.models.Product;
import com.example.shop.models.ProductRequest;
import com.example.shop.models.ProductCategory;
import com.example.shop.services.ProductCategoryService;
import com.example.shop.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/products")
@RestController
public class ProductController {
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductController(ProductService productService, ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProduct();
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest request) {
        final var product = new Product(0, request.getName(), request.getDescription(), request.getPrice(), request.getDiscount_price(), request.getImage_url(), null, null, null);
        var id = productService.addProduct(product);

        request.getCategories().forEach(category_id -> {
            productCategoryService.addProductCategory(new ProductCategory(0, id, category_id));
        });
        return ResponseEntity.status(201).body(null);
    }

    @GetMapping(path = "{id}")
    public Product getProductById(@PathVariable("id") String id) {
        return productService.getProductById(Integer.parseInt(id)).orElse(null);
    }

    @GetMapping(path = "{id}/categories")
    public List<Category> getCategoriesByProductId(@PathVariable("id") String id) {
        return productService.getCategoriesByProductId(Integer.parseInt(id));
    }

    @PutMapping(path = "{id}")
    public void updateProductById(@PathVariable("id") String id, @RequestBody ProductRequest request) {
        final var product = new Product(0, request.getName(), request.getDescription(), request.getPrice(), request.getDiscount_price(), request.getImage_url(), null, null, null);
        final var productId = Integer.parseInt(id);
        productService.updateProductById(productId, product);
        productCategoryService.deleteProductCategoryByColumn("product_id", productId);
        request.getCategories().forEach(category_id -> {
            productCategoryService.addProductCategory(new ProductCategory(0, productId, category_id));
        });
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") String id) {
        productService.deleteProductById(Integer.parseInt(id));
        return ResponseEntity.status(204).body(null);
    }
}
