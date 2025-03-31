package com.cbre.order.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbre.order.service.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
