package com.ltnc.phonestorage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ltnc.phonestorage.entity.Customer;
import com.ltnc.phonestorage.entity.Employee;
import com.ltnc.phonestorage.entity.Order;
import com.ltnc.phonestorage.entity.Product;
import com.ltnc.phonestorage.service.CustomerService;
import com.ltnc.phonestorage.service.EmployeeService;
import com.ltnc.phonestorage.service.OrderService;
import com.ltnc.phonestorage.service.ProductService;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProductService productService;


    public OrderController(OrderService orderService, CustomerService customerService, EmployeeService employeeService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.productService = productService;
    }

    @GetMapping("/orders")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @GetMapping("/orders/new")
    public String createOrderForm(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);

        List<Customer> allCustomers = customerService.getAllCustomers();
        model.addAttribute("customers", allCustomers);

        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);

        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);

        return "create_order";
    }

    @PostMapping("/orders")
    public String saveOrder(@ModelAttribute("order") Order order) {
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/orders/edit/{id}")
    public String editOrderForm(@PathVariable(value = "id") Long orderId, Model model) {

        List<Customer> allCustomers = customerService.getAllCustomers();
        model.addAttribute("customers", allCustomers);

        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);

        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);

        model.addAttribute("order", orderService.getOrderById(orderId));
        return "edit_order";

    }

    @PostMapping("/orders/{id}")
    public String updateOrder(@PathVariable(value = "id") Long orderId,
                              @ModelAttribute("order") Order order,
                              Model model) {
        orderService.updateOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/orders/{id}")
    public String deleteOrder(@PathVariable(value = "id") Long orderId) {
        orderService.deleteOrderById(orderId);
        return "redirect:/orders";
    }
}


