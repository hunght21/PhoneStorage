package com.ltnc.phonestorage.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ltnc.phonestorage.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
