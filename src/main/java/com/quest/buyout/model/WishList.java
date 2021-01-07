package com.quest.buyout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

 /*
    Clicking on wishList should take user to product details
 */

public class WishList {
    private String _id;
    private String productId;
    private String customerId;
    private String thirdPartyId = "";

    public WishList(){}
    public WishList(String productId, String customerId, String thirdPartyId){
            this.productId = productId;
            this.customerId = customerId;
            this.thirdPartyId = thirdPartyId;
        }

    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getThirdPartyId() { return thirdPartyId; }
    public void setThirdPartyId(String thirdPartyId) { this.thirdPartyId = thirdPartyId; }


}


