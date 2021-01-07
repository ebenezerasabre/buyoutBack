package com.quest.buyout.model;

import java.util.Arrays;

public class ProductShow {

    private String productId;
    private String name;
    private String price;
    private String stock;

    private String category;
    private String discount;
    private String status;
    private String[] images;

    public ProductShow() {
    }

    public ProductShow(String productId,
                       String name,
                       String price,
                       String stock,
                       String category,
                       String discount,
                       String status,
                       String[] images) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.discount = discount;
        this.status = status;
        this.images = images;
    }


    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getStock() { return stock; }
    public void setStock(String stock) { this.stock = stock; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDiscount() { return discount; }
    public void setDiscount(String discount) { this.discount = discount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String[] getImages() { return images; }
    public void setImages(String[] images) { this.images = images; }

    @Override
    public String toString() {
        return "ProductShow{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", stock='" + stock + '\'' +
                ", category='" + category + '\'' +
                ", discount='" + discount + '\'' +
                ", status='" + status + '\'' +
                ", images=" + Arrays.toString(images) +
                '}';
    }
}
