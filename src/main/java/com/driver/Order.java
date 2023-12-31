package com.driver;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Order {

    private String id;
    private int deliveryTime;

    public Order() {

    }
    public Order(String id, String deliveryTime) {
        this.id = id;
        LocalTime time = LocalTime.parse(deliveryTime, DateTimeFormatter.ofPattern("HH:mm"));
        this.deliveryTime = time.getHour() * 60 + time.getMinute();
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
