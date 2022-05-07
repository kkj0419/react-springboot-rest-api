package com.example.gccoffe.controller;

import com.example.gccoffe.model.OrderItem;

import java.util.List;

public record CreateOrderRequest(
        String email, String address, String postcode, List<OrderItem> orderItems
) {
}
