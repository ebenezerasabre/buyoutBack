package com.quest.buyout.model;

public class Logistics {

    private String orderTime;
    private String status;
    private String delivery;


    public Logistics(String orderTime,
                     String status,
                     String delivery) {
        this.orderTime = orderTime;
        this.status = status;
        this.delivery = delivery;
    }

    public String getOrderTime() { return orderTime; }
    public void setOrderTime(String orderTime) { this.orderTime = orderTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDelivery() { return delivery; }
    public void setDelivery(String delivery) { this.delivery = delivery; }

    @Override
    public String toString() {
        return "Logistics{" +
                "orderTime='" + orderTime + '\'' +
                ", status='" + status + '\'' +
                ", delivery='" + delivery + '\'' +
                '}';
    }
}
