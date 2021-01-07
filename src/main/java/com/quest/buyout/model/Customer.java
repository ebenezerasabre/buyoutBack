package com.quest.buyout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User creates this when he creates an account for the first time
 * email optional
 * fields which remain empty will be set to empty
 */
//each customer creates a document when he registers
public class Customer {

    private String _id;

    // fill this when user creates account
    private String firstName = "";
    private String lastName = "";
    private String phoneNumber = "";
    private String password = "";

    private String email = "";
    private int rating = 3; //
    private String dateAccountWasCreated = "";
    private String trackUserAddress = "";

    private String userImage = "";




    //rating increases or decreases when user orders and pay for the order or not
    // it will determine whom to serve first
    // and what orders are to be ignored

    /**
     * + or - when customer orders and pay for the orders or not
     * It determines customer priority list
     * Shows what orders to ignore / delete
     */

    public Customer(){}
    public Customer(@JsonProperty("firstName") String firstName,
                    @JsonProperty("lastName") String lastName,
                    @JsonProperty("phoneNumber") String phoneNumber,
                    @JsonProperty("password") String password
                ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDateAccountWasCreated() { return dateAccountWasCreated; }
    public void setDateAccountWasCreated(String dateAccountWasCreated) { this.dateAccountWasCreated = dateAccountWasCreated; }

    public String getTrackUserAddress() { return trackUserAddress; }
    public void setTrackUserAddress(String trackUserAddress) { this.trackUserAddress = trackUserAddress; }

    public String getUserImage() { return userImage; }
    public void setUserImage(String userImage) { this.userImage = userImage; }

    @Override
    public String toString() {
        return "Customer{" +
                "_id='" + _id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", rating=" + rating +
                ", dateAccountWasCreated='" + dateAccountWasCreated + '\'' +
                ", trackUserAddress='" + trackUserAddress + '\'' +
                ", userImage='" + userImage + '\'' +
                '}';
    }
}


