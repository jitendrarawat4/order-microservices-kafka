package com.cbre.order.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbre.order.service.entity.Order;
import com.cbre.order.service.exception.OrderNotFoundException;
import com.cbre.order.service.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
    private OrderService orderService;

	@GetMapping
    public ResponseEntity<Object> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            throw new OrderNotFoundException("No orders found.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
        	throw new OrderNotFoundException("Order with ID " + id + " not found.");
        }
    }

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}