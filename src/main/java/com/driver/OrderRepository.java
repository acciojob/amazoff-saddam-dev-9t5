package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class OrderRepository {
    Map<String, Order> orders = new HashMap<>();
    public String addOrder(Order order) {
        String orderId = order.getId();
        orders.put(orderId, order);
        return "New order added successfully";
    }

    public List<String> getAllOrders() {
        List<String> orderList = new ArrayList<>();
        for (Order order: orders.values()) {
            orderList.add(order.getId());
        }
        return orderList;
    }

    public Order getOrderById(String orderId) {
        return this.orders.get(orderId);
    }

    public String deleteOrderById(String orderId) {
        this.orders.remove(orderId);
        return orderId + " removed successfully";
    }
}
