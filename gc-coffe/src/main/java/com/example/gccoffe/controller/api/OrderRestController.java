package com.example.gccoffe.controller.api;

import com.example.gccoffe.controller.CreateOrderRequest;
import com.example.gccoffe.model.Email;
import com.example.gccoffe.model.Order;
import com.example.gccoffe.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/api/v1/orders")
    public Order createdOrder(CreateOrderRequest orderRequest) {
        return orderService.creatOrder(
                new Email(orderRequest.email()),
                orderRequest.address(),
                orderRequest.postcode(),
                orderRequest.orderItems()
        );
    }
}
