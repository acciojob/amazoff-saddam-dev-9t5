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

    public boolean addOrderPartnerPair(String orderId, String partnerId) {
        if(!deliveryPartners.isEmpty() && deliveryPartners.containsKey(partnerId)) {
            DeliveryPartner deliveryPartner = deliveryPartners.get(partnerId);
            if(deliveryPartner.getOrderIdList().isEmpty()) {
                deliveryPartner.getOrderIdList().add(orderId);
            }else if(!deliveryPartner.getOrderIdList().contains(orderId)) {
                deliveryPartner.getOrderIdList().add(orderId);
            }
            return true;
        }
        return false;
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        if(deliveryPartners.containsKey(partnerId)) {
            deliveryPartner = deliveryPartners.get(partnerId);
        }
        return deliveryPartner;
    }

    public int getOrderCountByPartnerId(String partnerId) {
        return deliveryPartners.get(partnerId).getOrderIdList().size();
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return deliveryPartners.get(partnerId).getOrderIdList();
    }

    public List<String> getAllAssignedOrder() {
        List<String> assignedOrder = new ArrayList<>();
        for (List<String> partnerOrders: this.orderPartnerPair.values()) {
            assignedOrder.addAll(partnerOrders);
        }
        return assignedOrder;
    }

    public String deletePartnerById(String partnerId) {
        deliveryPartners.remove(partnerId);
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

    public void unsetOrderIdFromPartnerId(String orderId, String deliveryPartnerId) {
        if(deliveryPartners.containsKey(deliveryPartnerId)) {
            deliveryPartners.get(deliveryPartnerId).getOrderIdList().remove(orderId);
        }
    }
}
