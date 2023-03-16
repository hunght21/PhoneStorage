package com.ltnc.phonestorage.service;

import com.ltnc.phonestorage.entity.Order;
import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order saveOrder(Order order);

    Order getOrderById(Long orderId);

    Order updateOrder(Order order);

    double countTotalPrice(Order order);

    void deleteOrderById(Long orderId);

}
