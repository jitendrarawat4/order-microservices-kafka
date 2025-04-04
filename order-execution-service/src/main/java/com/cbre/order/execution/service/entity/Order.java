package com.cbre.order.execution.service.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderType;
	private String description;
	private String clientName;
	private String assignedVendor;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Timestamp createdAt;
	
	private Timestamp processedAt;

	public enum OrderStatus {
		PENDING, PROCESSING, COMPLETED, CANCELLED
	}

}
