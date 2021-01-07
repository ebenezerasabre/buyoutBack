package com.quest.buyout.model;

public class OrderShow {

    private String quantity;
    private String size;
    private String color;
    private String orderValue;

    private String userGpsLocation;
    private String orderTime;
    private String status;
    private String delivery;

    private String productGroup;

    public OrderShow() { }
    public OrderShow(String quantity,
                     String size,
                     String color,
                     String orderValue,
                     String userGpsLocation,
                     String orderTime,
                     String status,
                     String delivery,
                     String productGroup) {
        this.quantity = quantity;
        this.size = size;
        this.color = color;
        this.orderValue = orderValue;
        this.userGpsLocation = userGpsLocation;
        this.orderTime = orderTime;
        this.status = status;
        this.delivery = delivery;
        this.productGroup = productGroup;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public String getUserGpsLocation() {
        return userGpsLocation;
    }

    public void setUserGpsLocation(String userGpsLocation) {
        this.userGpsLocation = userGpsLocation;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    @Override
    public String toString() {
        return "OrderShow{" +
                "quantity='" + quantity + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", orderValue='" + orderValue + '\'' +
                ", userGpsLocation='" + userGpsLocation + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", status='" + status + '\'' +
                ", delivery='" + delivery + '\'' +
                ", productGroup='" + productGroup + '\'' +
                '}';
    }
}
