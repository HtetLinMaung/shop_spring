package com.example.shop.controllers;

import java.util.List;

import com.example.shop.models.Category;
import com.example.shop.models.Product;
import com.example.shop.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/products")
@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProduct();
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
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
    public void updateProductById(@PathVariable("id") String id, @RequestBody Product product) {
        productService.updateProductById(Integer.parseInt(id), product);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") String id) {
        productService.deleteProductById(Integer.parseInt(id));
        return ResponseEntity.status(204).body(null);
    }
}
