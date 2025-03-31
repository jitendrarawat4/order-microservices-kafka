package com.cbre.order.execution.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbre.order.execution.service.entity.Order;
import com.cbre.order.execution.service.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderExecutionService {

	@Autowired
	private OrderRepository orderRepository;

	@Transactional
	public void processOrderSequentially(Order order) {

		if (order.getStatus() == Order.OrderStatus.PROCESSING) {
			
			
        //Business Logic eg Inventory check; Order execute
			
			try {
	            Thread.sleep(10000);
	        } catch (InterruptedException e) {
	            log.error("Sleep interrupted", e);
	        }
			
			
			order.setStatus(Order.OrderStatus.COMPLETED);
			orderRepository.save(order);

			log.info("Completed Order ID: " + order.getId());
		}
	}
}
