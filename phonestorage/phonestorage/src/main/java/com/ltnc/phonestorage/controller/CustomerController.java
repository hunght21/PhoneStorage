package com.ltnc.phonestorage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ltnc.phonestorage.entity.Customer;
import com.ltnc.phonestorage.service.CustomerService;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/index")
    public String index(){
        return "index";
    }

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }


    @GetMapping("/customers")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @GetMapping("/customers/new")
    public String createCustomerForm(Model model) {


        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "create_customer";
    }

    @PostMapping("/customers")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomerForm(@PathVariable(value = "id") Integer customerId, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(customerId));
        return "edit_customer";

    }

    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable(value = "id") Integer customerId,
                                 @ModelAttribute("customer") Customer customer,
                                 Model model) {

        Customer existingCustomer = customerService.getCustomerById(customerId);
        existingCustomer.setCustomerId(customerId);
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setCustomerEmail(customer.getCustomerEmail());

        customerService.updateCustomer(existingCustomer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable(value = "id") Integer customerId) {
        customerService.deleteCustomerById(customerId);
        return "redirect:/customers";
    }
}



