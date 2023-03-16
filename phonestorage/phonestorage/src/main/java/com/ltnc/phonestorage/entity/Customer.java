package com.ltnc.phonestorage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @NotBlank
    @Size(min = 3 ,max = 60)
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @NotBlank
    @Email
    @Column(name = "customer_email", nullable = false, unique = true)
    private String customerEmail;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders; 

    @Override
    public String toString() {
        return customerName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Customer() {
    }

    public Customer(String customerName, String customerEmail) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
