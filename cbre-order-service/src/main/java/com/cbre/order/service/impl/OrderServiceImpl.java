package com.cbre.order.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbre.order.service.entity.Order;
import com.cbre.order.service.entity.Order.OrderStatus;
import com.cbre.order.service.kafka.OrderProducer;
import com.cbre.order.service.repository.OrderRepository;
import com.cbre.order.service.service.OrderService;


@Service
public class OrderServiceImpl  implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	 @Autowired
	 private OrderProducer orderProducer;


	@Override
	public Order createOrder(Order order) {	
		order.setStatus(OrderStatus.PENDING);
		
        Order createdOrder = orderRepository.save(order);

        String orderDetails = "Order ID: " + createdOrder.getId() + ", Client: " + createdOrder.getClientName();
        orderProducer.sendOrder(orderDetails);

        return createdOrder;
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.orElse(null);
	}

}
