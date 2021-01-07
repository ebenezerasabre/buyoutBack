package com.quest.buyout.model;

import java.util.Arrays;

/**
 * When user purchase a product, the item should be removed from the
 * Cart and placed in Orderhistory
 * This request should be made when user add an item to cart
 */

// place order from cart or product details
// remove from cart
// A click on cart should open product details

public class Cart {

    private String _id;         // let server
    private String productId;
    private String productName;
    private String customerId;
    private String material;

    private String quantity;
    private String orderValue; // let server
    private String size;
    private String color;

    private String gpsLocation;
    private String sellerId;    // let server
    private String thirdPartyId = "";

    private String[] images;    // let server


    public Cart( String productId,
                 String productName,
                 String customerId,
                 String material,
                 String quantity,
                 String orderValue,
                 String size,
                 String color,
                 String getGpsLocation,
                 String sellerId,
                 String thirdPartyId,
                 String[] images
                 ) {
        this.productId = productId;
        this.productName = productName;
        this.customerId = customerId;
        this.material = material;

        this.quantity = quantity;
        this.orderValue = orderValue;
        this.size = size;
        this.color = color;

        this.gpsLocation = getGpsLocation;
        this.sellerId = sellerId;
        this.thirdPartyId = thirdPartyId;
        this.images = images;
    }

    public Cart(){}


    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOrderValue() {
        return orderValue;
    }
    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
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

    public String getGpsLocation() {
        return gpsLocation;
    }
    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public String getSellerId() {
        return sellerId;
    }
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String[] getImages() {
        return images;
    }
    public void setImages(String[] images) {
        this.images = images;
    }

    public String getThirdPartyId() { return thirdPartyId; }
    public void setThirdPartyId(String thirdPartyId) { this.thirdPartyId = thirdPartyId; }

    @Override
    public String toString() {
        return "Cart{" +
                "_id='" + _id + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", material='" + material + '\'' +
                ", quantity='" + quantity + '\'' +
                ", orderValue='" + orderValue + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", gpsLocation='" + gpsLocation + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", thirdPartyId='" + thirdPartyId + '\'' +
                ", images=" + Arrays.toString(images) +
                '}';
    }



}


