package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class OrderRepository {
    Map<String, Order> orders = new HashMap<>();

    public void unsetPartnerIdByOrderId(String orderId) {
        if(orders.containsKey(orderId)) {
            orders.get(orderId).setDeliveryPartnerId("");
        }
    }

    public String addOrder(Order order) {
        String orderId = order.getId();
        orders.put(orderId, order);
        return "New order added successfully";
    }

    public boolean addOrderPartnerPair(String orderId, String partnerId) {
        if(!orders.isEmpty() && orders.containsKey(orderId)) {
            Order order = orders.get(orderId);
            if(order.getDeliveryPartnerId().isEmpty()) {
                order.setDeliveryPartnerId(partnerId);
                return true;
            }
        }
        return false;
    }

    public List<String> getAllOrders() {
        List<String> orderList = new ArrayList<>();
        for (Order order: orders.values()) {
            orderList.add(order.getId());
        }
        return orderList;
    }

    public List<String> getCountOfUnassignedOrders() {
        List<String> orderList = new ArrayList<>();
        for (Order order: orders.values()) {
            if(order.getDeliveryPartnerId().isEmpty()) {
                orderList.add(order.getId());
            }
        }
        return orderList;
    }

    public Order getOrderById(String orderId) {
        Order order = new Order();
        if(orders.containsKey(orderId)) {
            order = orders.get(orderId);
        }
        return order;
    }

    public String deleteOrderById(String orderId) {
        this.orders.remove(orderId);
        return orderId + " removed successfully";
    }
}
