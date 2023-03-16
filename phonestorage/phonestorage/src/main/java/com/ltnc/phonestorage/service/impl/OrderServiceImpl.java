package com.ltnc.phonestorage.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltnc.phonestorage.entity.Order;
import com.ltnc.phonestorage.entity.Product;
import com.ltnc.phonestorage.repository.OrderRepository;
import com.ltnc.phonestorage.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order saveOrder(Order order) {
        order.setFinalPrice(countTotalPrice(order));
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).get();
    }

    @Override
    public Order updateOrder(Order order) {
        // idejau nauja eilute, tam kad issaugoti Total Price po updeito
        order.setFinalPrice(countTotalPrice(order));
        return orderRepository.save(order);
    }

    @Override
    public double countTotalPrice(Order order) {
        return order
                .getListOfProducts().stream()
                .mapToDouble(Product::getPartRetailPrice).sum();
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}

