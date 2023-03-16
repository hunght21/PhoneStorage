package com.ltnc.phonestorage.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ltnc.phonestorage.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
