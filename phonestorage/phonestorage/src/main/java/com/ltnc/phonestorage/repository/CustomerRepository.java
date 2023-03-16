package com.ltnc.phonestorage.repository;

import com.ltnc.phonestorage.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
