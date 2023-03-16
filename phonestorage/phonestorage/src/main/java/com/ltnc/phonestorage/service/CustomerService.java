package com.ltnc.phonestorage.service;



import java.util.List;

import com.ltnc.phonestorage.entity.Customer;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer customer);

    Customer getCustomerById(Integer customerId);

    Customer updateCustomer(Customer customer);

    void deleteCustomerById(Integer customerId);

}
