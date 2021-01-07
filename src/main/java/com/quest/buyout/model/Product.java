package com.quest.buyout.model;


// customer cannot create a product
// he can only order

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashMap;

// give products a discount string

/**
 * stock
 * user can only buy within stock range
 * sales[quantity, amount]
 */


// products will have productGroup
// productGroup="category, subCategory, similar, boughtTogether";
/*
        ** eg for iphone productGroup ="smart phone,iphone,iphone,iphone accessory"
    * smart phone        // category
    * iphone             // subCategory ie. users also viewed
    * iphone x             // specific  ie. similar products
    * iphone accessory   // boughtTogether
    *
        ** eg for black belt productGroup ="man,belt,black belt,black belt accessory"
     * man                   // category
     * belt                  // subCategory ie. users also viewed
     * black belt            // specific    ie. similar products
     * black belt accessory  // boughtTogether
     *
        ** eg for nike air strike footwear productGroup ="man woman,footwear,nike shoe,nike shoe accessory"
     * man woman            // category
     * footwear             // subCategory ie users also viewed
     * nike shoe            // specific     ie. similar products
     * nike shoe accessory  // boughtTogether


*     ** eg for sandals productGroup ="man,sandals,black sandals,sandals accessory"
 * man                   // category
 * sandals               // subCategory ie users also viewed
 * black sandals         // specific    ie similar products
 * sandals accessory     // boughtTogether


     * // use similar to determine similar products
     * // use boughtTogether to determine bought together
 */


/*
    * Products that are accessory to other products should be indicated in it's status
    * eg status = "product, iphone accessory"
    * Users also viewed  productGroup[1] == subCategory
    * Similar products productGroup[2]
    * Seller recommended
 */

/**
 * Products should have sellers price; sPrice
 * Our price; price
 */

public class Product {

    private String _id;
    private String name;
    private String about;
    private String material;

    private String availColors;
    private String availSize;
    private String sPrice = "";
    private String price;

    private String stock;

    //not a JsonProperty, updated when an order is made
    private double[] sales = {0.0, 0.0};
    private String category;
    private String subCategory;
    private String sellerId;
    private String discount;

    private String searchQuery;
    private String status;
    private String[] images;
    private String productGroup;

    public Product(){}
    public Product(@JsonProperty("name") String name,
                   @JsonProperty("about") String about,
                   @JsonProperty("material") String material,
                   String availColors,
                   String availSize,
                   @JsonProperty("price") String price,
                   @JsonProperty("stock") String stock,
                   @JsonProperty("category") String category,
                   @JsonProperty("subCategory") String subCategory,
                   @JsonProperty("sellerId") String sellerId,
                   @JsonProperty("discount") String discount,
                   @JsonProperty("searchQuery") String searchQuery,
                   @JsonProperty("status") String status,
                   @JsonProperty("productGroup") String productGroup,
                   String[] images) {
        this.name = name;
        this.about = about;
        this.material = material;
        this.availColors = availColors;

        this.availSize = availSize;
        this.price = price;
        this.stock = stock;
        this.category = category;

        this.subCategory = subCategory;
        this.sellerId = sellerId;
        this.discount = discount;
        this.searchQuery = searchQuery;

        this.status = status;
        this.productGroup = productGroup;
        this.images = images;
    }


    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public String getAvailColors() { return availColors; }
    public void setAvailColors(String availColors) { this.availColors = availColors; }

    public String getAvailSize() { return availSize; }
    public void setAvailSize(String availSize) { this.availSize = availSize; }

    public String getsPrice() { return sPrice; }
    public void setsPrice(String sPrice) { this.sPrice = sPrice; }

    public String getPrice() { return price; }
    public void setPrice(String  price) { this.price = price; }

    public String  getStock() { return stock; }
    public void setStock(String stock) { this.stock = stock; }

    public double[] getSales() { return sales; }
    public void setSales(double[] sales) { this.sales = sales; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSubCategory() { return subCategory; }
    public void setSubCategory(String subCategory) { this.subCategory = subCategory; }

    public String getSellerId() { return sellerId; }
    public void setSellerId(String sellerId) { this.sellerId = sellerId; }

    public String getDiscount() { return discount; }
    public void setDiscount(String discount) { this.discount = discount; }

    public String getSearchQuery() { return searchQuery; }
    public void setSearchQuery(String searchQuery) { this.searchQuery = searchQuery; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String[] getImages() { return images; }
    public void setImages(String[] images) { this.images = images; }

    public String getProductGroup() { return productGroup; }
    public void setProductGroup(String productGroup) { this.productGroup = productGroup; }


    @Override
    public String toString() {
        return "Product{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", material='" + material + '\'' +
                ", availColors='" + availColors + '\'' +
                ", availSize='" + availSize + '\'' +
                ", sPrice='" + sPrice + '\'' +
                ", price='" + price + '\'' +
                ", stock='" + stock + '\'' +
                ", sales=" + Arrays.toString(sales) +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", discount='" + discount + '\'' +
                ", searchQuery='" + searchQuery + '\'' +
                ", status='" + status + '\'' +
                ", images=" + Arrays.toString(images) +
                ", productGroup='" + productGroup + '\'' +
                '}';
    }
}




