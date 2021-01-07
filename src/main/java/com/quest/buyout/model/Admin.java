package com.quest.buyout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// admin creation should be restricted
//
public class Admin {
    private String _id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String email;
    private String userImage;

    public Admin() {}
    public Admin(@JsonProperty("firstName") String firstName,
                 @JsonProperty("lastName")String lastName,
                 @JsonProperty("phoneNumber")String phoneNumber,
                 @JsonProperty("password")String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUserImage() { return userImage; }
    public void setUserImage(String userImage) { this.userImage = userImage; }


    @Override
    public String toString() {
        return "Admin{" +
                "_id='" + _id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userImage='" + userImage + '\'' +
                '}';
    }
}
