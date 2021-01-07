package com.quest.buyout.model;

public class AddAddress {
    private String _id;
    private String customerId;
    private String location;
    private String buildingNo;

    private String landmark;
    private String nickName;
    private String tag;
    private String dateCreated;
    private String dateModified;

    public AddAddress(){}
    public AddAddress(String customerId,
                      String location,
                      String buildingNo,
                      String landmark,
                      String nickName,
                      String tag) {
        this.customerId = customerId;
        this.location = location;
        this.buildingNo = buildingNo;
        this.landmark = landmark;
        this.nickName = nickName;
        this.tag = tag;
    }

    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getBuildingNo() { return buildingNo; }
    public void setBuildingNo(String buildingNo) { this.buildingNo = buildingNo; }

    public String getLandmark() { return landmark; }
    public void setLandmark(String landmark) { this.landmark = landmark; }

    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public String getDateCreated() { return dateCreated; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }

    public String getDateModified() { return dateModified; }
    public void setDateModified(String dateModified) { this.dateModified = dateModified; }


    @Override
    public String toString() {
        return "AddAddress{" +
                "_id='" + _id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", location='" + location + '\'' +
                ", buildingNo='" + buildingNo + '\'' +
                ", landmark='" + landmark + '\'' +
                ", nickName='" + nickName + '\'' +
                ", tag='" + tag + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateModified='" + dateModified + '\'' +
                '}';
    }
}
