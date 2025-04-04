package com.cbre.order.execution.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbre.order.execution.service.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
