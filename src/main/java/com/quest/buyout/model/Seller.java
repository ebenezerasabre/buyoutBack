package com.quest.buyout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seller {
    private String _id;
    private String brandName;
    private String phoneNumber;
    private String rights;
    private String profilePic;

    private Double totalAmountMade = 0.0;
    private String dateRegistered = "";
    private String dateCurrentPurchase = "";

    public Seller(){}
    public Seller(@JsonProperty("brandName") String brandName,
                  @JsonProperty("phoneNumber") String phoneNumber,
                  @JsonProperty("rights") String rights,
                  @JsonProperty("profilePic") String profilePic) {
        this.brandName = brandName;
        this.phoneNumber = phoneNumber;
        this.rights = rights;
        this.profilePic = profilePic;
    }

    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

    public String getBrandName() { return brandName; }

    public void setBrandName(String brandName) { this.brandName = brandName; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRights() { return rights; }

    public void setRights(String rights) { this.rights = rights; }

    public String getProfilePic() { return profilePic; }

    public void setProfilePic(String profilePic) { this.profilePic = profilePic; }

    public Double getTotalAmountMade() { return totalAmountMade; }

    public void setTotalAmountMade(Double totalAmountMade) { this.totalAmountMade = totalAmountMade; }

    public String getDateRegistered() { return dateRegistered; }

    public void setDateRegistered(String dateRegistered) {   this.dateRegistered = dateRegistered; }

    public String getDateCurrentPurchase() { return dateCurrentPurchase; }

    public void setDateCurrentPurchase(String dateCurrentPurchase) { this.dateCurrentPurchase = dateCurrentPurchase; }

    @Override
    public String toString() {
        return "Seller{" +
                "_id='" + _id + '\'' +
                ", brandName='" + brandName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", rights='" + rights + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", totalAmountMade='" + totalAmountMade + '\'' +
                ", dateRegistered='" + dateRegistered + '\'' +
                ", dateCurrentPurchase='" + dateCurrentPurchase + '\'' +
                '}';
    }
}
