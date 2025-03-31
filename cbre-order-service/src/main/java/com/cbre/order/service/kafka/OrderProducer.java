package com.cbre.order.service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "order-topic";

    // Send order details to Kafka
    public void sendOrder(String orderDetails) {
    	String key = "order-key";
        kafkaTemplate.send(TOPIC,key, orderDetails);
        log.info("Sent order: " + orderDetails);
    }
}
