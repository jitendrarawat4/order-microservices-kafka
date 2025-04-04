package com.cbre.order.service.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.propagation.Propagator;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class OrderProducer {

	private static final String TOPIC = "order-topic";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;


	public void sendOrder(String orderDetails) {
		String key = "order-key";


			ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, orderDetails);
			

			kafkaTemplate.send(record);
			log.info("Sent order to Kafka with tracing: {}", orderDetails);
	
	}
}
