package com.quest.buyout.model;

public class History {
    private String _id;
    private String productId;
    private String customerId;
    private String setDate; // date customer's order was completed

    public History(){}
    public History(String productId, String customerId){
        this.productId = productId;
        this.customerId = customerId;
    }

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSetDate() {
        return setDate;
    }

    public void setSetDate(String setDate) {
        this.setDate = setDate;
    }

    @Override
    public String toString() {
        return "History{" +
                "_id='" + _id + '\'' +
                ", productId='" + productId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", setDate='" + setDate + '\'' +
                '}';
    }
}
