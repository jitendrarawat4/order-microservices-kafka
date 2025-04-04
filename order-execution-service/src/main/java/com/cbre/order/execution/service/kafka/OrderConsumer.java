package com.cbre.order.execution.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cbre.order.execution.service.entity.Order;
import com.cbre.order.execution.service.repository.OrderRepository;
import com.cbre.order.execution.service.service.OrderExecutionService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderConsumer {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderExecutionService orderExecutionService;

	@KafkaListener(topics = "order-topic", groupId = "order-execution-group")
	public void consumeOrder(ConsumerRecord<String, String> record) {
		String orderDetails = record.value();
		log.info("Consuming order: {}", orderDetails);

		Long orderId = extractOrderIdFromMessage(orderDetails);
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		
			if (order.getStatus() == Order.OrderStatus.PENDING) {
				order.setStatus(Order.OrderStatus.PROCESSING);
				log.info("Processing Order ID: {}", order.getId());
				orderRepository.save(order);
				orderExecutionService.processOrderSequentially(order);
			}
		
	}

	private Long extractOrderIdFromMessage(String orderDetails) {
		return Long.parseLong(orderDetails.split(":")[1].split(",")[0].trim());
	}
}
