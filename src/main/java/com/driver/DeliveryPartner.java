package com.driver;

import java.util.List;

public class DeliveryPartner {

    private String id;
    private int numberOfOrders;

    private List<String> orderIdList;

    public DeliveryPartner() {

    }
    public DeliveryPartner(String id) {
        this.id = id;
        this.numberOfOrders = 0;
    }

    public String getId() {
        return id;
    }

    public Integer getNumberOfOrders(){
        return numberOfOrders;
    }

    public List<String> getOrderIdList() {
        return orderIdList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public void setOrderIdList(String orderId) {
        this.orderIdList.add(orderId);
    }
}