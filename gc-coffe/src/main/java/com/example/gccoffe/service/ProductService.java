package com.example.gccoffe.service;

import com.example.gccoffe.model.Category;
import com.example.gccoffe.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductsByCategory(Category category);

    List<Product> getAllProducts();

    Product createdProduct(String productName, Category category, long price);

    Product createdProduct(String productName, Category category, long price, String description);

}
