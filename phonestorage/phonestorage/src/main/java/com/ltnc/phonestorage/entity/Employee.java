package com.ltnc.phonestorage.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @NotBlank
    @Size(min = 3 ,max = 60)
    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @NotBlank
    @Email
    @Column(name = "employee_email", nullable = false, unique = true)
    private String employeeEmail;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Order> orders; 

    @Override
    public String toString() {
        return employeeName;
    }

    public Employee() {
    }

    public Employee(String employeeName, String employeeEmail) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
}
