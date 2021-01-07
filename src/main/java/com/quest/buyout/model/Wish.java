package com.quest.buyout.model;

public class Wish {

    private String _id;
    private String productId;
    private String customerId;
    private String productName;      // let server

    private String price;          // let server
    private String material;      // let server
    private String image;

    public Wish() {
        this._id = "";
        this.productId  = "";
        this.customerId = "";
        this.productName = "";
        this.price = "";
        this.material = "";
        this.image = "";
    }

    public Wish(String _id,
                String productId,
                String customerId,
                String productName,
                String price,
                String material,
                String image) {
        this._id = _id;
        this.productId = productId;
        this.customerId = customerId;
        this.productName = productName;
        this.price = price;
        this.material = material;
        this.image = image;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "_id='" + _id + '\'' +
                ", productId='" + productId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productName='" + productName + '\'' +
                ", price='" + price + '\'' +
                ", material='" + material + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
