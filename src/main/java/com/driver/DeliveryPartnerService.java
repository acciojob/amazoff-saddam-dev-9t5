package com.driver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryPartnerService {
    DeliveryPartnerRepository deliveryPartnerRepository = new DeliveryPartnerRepository();
    public String addPartner(String partnerId) {
        return deliveryPartnerRepository.addPartner(partnerId);
    }

    public String addOrderPartnerPair(String orderId, String partnerId) {
        return deliveryPartnerRepository.addOrderPartnerPair(orderId, partnerId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return deliveryPartnerRepository.getPartnerById(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId) {
        return deliveryPartnerRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return deliveryPartnerRepository.getOrdersByPartnerId(partnerId);
    }

    public String deletePartnerById(String partnerId) {
        return deliveryPartnerRepository.deletePartnerById(partnerId);
    }
}
