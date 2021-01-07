package com.quest.buyout.model;

import java.util.Arrays;

/**
 * Customer will place this order from their cart
 * as for gps location permission in the android client
 * order status eg pending, transit, delivered
 */

/**
 * Third party developers will be allowed to have access to our api
 */

public class Order {

    private String _id;
    private String productId;
    private String customerId;
    private String productName;
    private String addressId;

    private String cartId;
    private String quantity;
    private String size;
    private String color;

    private String orderValue;
    private String userGpsLocation;
    private String orderTime;
    private String status;

    private String delivery;
    private String productGroup;
    private String[] images;

    // for customerView
    private String customerName;
    private String customerPhoneNumber;
    private String customerLocation;

    /**
     * for thirdParty's
     * if thirdParty field of an order is not empty,
     * show thirdPartyOrderValue instead of orderValue at the client's side
     */

    private String thirdPartyId = "";
    private String thirdPartyIncrease = "";
    private String thirdPartyOrderValue = "";



    public Order() {}
    public Order(String productId,
                 String customerId,
                 String addressId,
                 String cartId,
                 String quantity,
                 String size,
                 String color,
                 String userGpsLocation) {
        this.productId = productId;
        this.customerId = customerId;
        this.addressId = addressId;
        this.cartId = cartId;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
        this.userGpsLocation = userGpsLocation;
        productGroup = "";

        customerName = "";
        customerPhoneNumber = "";
        customerLocation = "";
    }


    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getAddressId() { return addressId; }
    public void setAddressId(String addressId) { this.addressId = addressId; }

    public String getCartId() { return cartId; }
    public void setCartId(String cartId) { this.cartId = cartId; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getUserGpsLocation() { return userGpsLocation; }
    public void setUserGpsLocation(String userGpsLocation) { this.userGpsLocation = userGpsLocation; }

    public String getOrderTime() { return orderTime; }
    public void setOrderTime(String orderTime) { this.orderTime = orderTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDelivery() { return delivery; }
    public void setDelivery(String delivery) { this.delivery = delivery; }

    public String getOrderValue() { return orderValue; }
    public void setOrderValue(String orderValue) { this.orderValue = orderValue; }

    public String getProductGroup() { return productGroup; }
    public void setProductGroup(String productGroup) { this.productGroup = productGroup; }

    public String[] getImages() { return images; }
    public void setImages(String[] images) { this.images = images; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerPhoneNumber() { return customerPhoneNumber; }
    public void setCustomerPhoneNumber(String customerPhoneNumber) { this.customerPhoneNumber = customerPhoneNumber; }

    public String getCustomerLocation() { return customerLocation; }
    public void setCustomerLocation(String customerLocation) { this.customerLocation = customerLocation; }

    // for thirdParties

    public String getThirdPartyId() { return thirdPartyId; }
    public void setThirdPartyId(String thirdPartyId) { this.thirdPartyId = thirdPartyId; }

    public String getThirdPartyIncrease() { return thirdPartyIncrease; }
    public void setThirdPartyIncrease(String thirdPartyIncrease) { this.thirdPartyIncrease = thirdPartyIncrease; }

    public String getThirdPartyOrderValue() { return thirdPartyOrderValue; }
    public void setThirdPartyOrderValue(String thirdPartyOrderValue) { this.thirdPartyOrderValue = thirdPartyOrderValue; }


    @Override
    public String toString() {
        return "Order{" +
                "_id='" + _id + '\'' +
                ", productId='" + productId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productName='" + productName + '\'' +
                ", addressId='" + addressId + '\'' +
                ", cartId='" + cartId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", orderValue='" + orderValue + '\'' +
                ", userGpsLocation='" + userGpsLocation + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", status='" + status + '\'' +
                ", delivery='" + delivery + '\'' +
                ", productGroup='" + productGroup + '\'' +
                ", images=" + Arrays.toString(images) +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", customerLocation='" + customerLocation + '\'' +
                ", thirdPartyId='" + thirdPartyId + '\'' +
                ", thirdPartyIncrease='" + thirdPartyIncrease + '\'' +
                ", thirdPartyOrderValue='" + thirdPartyOrderValue + '\'' +
                '}';
    }
}


