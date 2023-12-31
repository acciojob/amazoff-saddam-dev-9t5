package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DeliveryPartnerRepository {
    Map<String, DeliveryPartner> deliveryPartners = new HashMap<>();
    Map<String, List<String>> orderPartnerPair = new HashMap<>();
    public String addPartner(String partnerId) {
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        this.deliveryPartners.put(partnerId, deliveryPartner);
        return "New delivery partner added successfully";
    }

    public String addOrderPartnerPair(String orderId, String partnerId) {
        List<String> orderList = new ArrayList<>();
        if(this.orderPartnerPair.containsKey(partnerId)) {
            orderList = this.orderPartnerPair.get(partnerId);
            orderList.remove(orderId);
        }
        orderList.add(orderId);
        this.orderPartnerPair.put(partnerId, orderList);
        DeliveryPartner deliveryPartner = this.deliveryPartners.get(partnerId);
        deliveryPartner.setNumberOfOrders(orderList.size());
        return "New order-partner pair added successfully";
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return this.deliveryPartners.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId) {
        return this.deliveryPartners.get(partnerId).getNumberOfOrders();
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return this.orderPartnerPair.get(partnerId);
    }

    public List<String> getAllAssignedOrder() {
        List<String> assignedOrder = new ArrayList<>();
        for (List<String> partnerOrders: this.orderPartnerPair.values()) {
            assignedOrder.addAll(partnerOrders);
        }
        return assignedOrder;
    }

    public String deletePartnerById(String partnerId) {
        this.deliveryPartners.remove(partnerId);
        this.orderPartnerPair.remove(partnerId);
        return partnerId + " removed successfully";
    }

    public void deleteOrderById(String orderId) {
        for(String partnerId: this.orderPartnerPair.keySet()) {
            for (String order: this.orderPartnerPair.get(partnerId)) {
                if (orderId.equals(order)) {
                    this.orderPartnerPair.get(partnerId).remove(order);
                    return;
                }
            }
        }
    }
}
