package com.cbre.order.service.service;

import java.util.List;

import com.cbre.order.service.entity.Order;

public interface OrderService  {

	Order createOrder(Order order);
	List<Order> getAllOrders();
	Order getOrderById(Long id);
}
