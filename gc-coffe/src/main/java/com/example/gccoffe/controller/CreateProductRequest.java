package com.example.gccoffe.controller;

import com.example.gccoffe.model.Category;

public record CreateProductRequest(String productName, Category category,long price, String description) {
}
