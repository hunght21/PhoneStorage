package com.ltnc.phonestorage.repository;

import com.ltnc.phonestorage.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
