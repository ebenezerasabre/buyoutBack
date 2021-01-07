package com.quest.buyout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Review {
    private String _id;
    private String productId;
    private String customerId;
    private String customerName;      // let server

    private String sellerId = "";    // let server
    private String msg;
    private String time;             // let server
    private String stars;

    private String[] thumbs = {"", ""};       // let server

    public Review(){}
    public Review(@JsonProperty("productId") String productId,
                  @JsonProperty("customerId") String customerId,
                  @JsonProperty("msg") String msg,
                  @JsonProperty("stars") String stars)
    {
        this.productId = productId;
        this.customerId = customerId;
        this.msg = msg;
        this.stars = stars;
    }

    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

    public String getProductId() { return productId; }

    public void setProductId(String productId) { this.productId = productId; }

    public String getCustomerId() { return customerId; }

    public String getCustomerName() { return customerName; }

    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getMsg() { return msg; }

    public void setMsg(String msg) { this.msg = msg; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getStars() { return stars; }

    public void setStars(String stars) { this.stars = stars; }

    public String getSellerId() { return sellerId; }

    public void setSellerId(String sellerId) { this.sellerId = sellerId; }

    public String[] getThumbs() { return thumbs; }

    public void setThumbs(String[] thumbs) { this.thumbs = thumbs; }


    @Override
    public String toString() {
        return "Review{" +
                "_id='" + _id + '\'' +
                ", productId='" + productId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                ", stars='" + stars + '\'' +
                ", thumbs=" + Arrays.toString(thumbs) +
                '}';
    }
}
