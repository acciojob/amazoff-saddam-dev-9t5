package com.driver;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository = new OrderRepository();
    DeliveryPartnerRepository deliveryPartnerRepository = new DeliveryPartnerRepository();
    public String addOrder(Order order) {
        return orderRepository.addOrder(order);
    }

    public List<String> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Order getOrderById(String orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public int getCountOfUnassignedOrders() {
        List<String> allOrder = orderRepository.getAllOrders();
        List<String> assignedOrder = deliveryPartnerRepository.getAllAssignedOrder();
        allOrder.removeAll(assignedOrder);
        return allOrder.size();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        int numberOfOrderLeftAfterGivenTime = 0;
        LocalTime givenLocalTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        int givenTime = givenLocalTime.getHour() * 60 + givenLocalTime.getMinute();
        List<String> orderIds = deliveryPartnerRepository.getOrdersByPartnerId(partnerId);
        for (String orderId: orderIds) {
            Order order = orderRepository.getOrderById(orderId);
            if(order.getDeliveryTime() > givenTime) numberOfOrderLeftAfterGivenTime++;
        }
        return numberOfOrderLeftAfterGivenTime;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        List<String> orderIds = deliveryPartnerRepository.getOrdersByPartnerId(partnerId);
        int lastDeliveryTime = 0;
        for (String orderId: orderIds) {
            Order order = orderRepository.getOrderById(orderId);
            lastDeliveryTime = Math.max(lastDeliveryTime, order.getDeliveryTime());
        }
        return convertToString(lastDeliveryTime);
    }

    private String convertToString(int totalMinutes) {
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        LocalTime time = LocalTime.of(hours, minutes);
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String deleteOrderById(String orderId) {
        deliveryPartnerRepository.deleteOrderById(orderId);
        return orderRepository.deleteOrderById(orderId);
    }
}
