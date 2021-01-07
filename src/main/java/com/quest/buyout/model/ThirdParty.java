package com.quest.buyout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThirdParty {
    // allowing third parties to access this api
    /**
     * Allowing thirdParty to have access to our api
     * Give order a part propertye eg ewquest, bigbuy, etc
     * How will third parties get their payment ?
     */

    String id;
    String companyName;
    String percentageIncrease;
    String dateEndorsed;

    public ThirdParty() {
    }

    public ThirdParty(@JsonProperty("companyName") String companyName,
                      @JsonProperty("percentageIncrease") String percentageIncrease,
                      @JsonProperty("dateEndorsed") String dateEndorsed) {
        this.companyName = companyName;
        this.percentageIncrease = percentageIncrease;
        this.dateEndorsed = dateEndorsed;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getPercentageIncrease() { return percentageIncrease; }
    public void setPercentageIncrease(String percentageIncrease) { this.percentageIncrease = percentageIncrease; }

    public String getDateEndorsed() { return dateEndorsed; }
    public void setDateEndorsed(String dateEndorsed) { this.dateEndorsed = dateEndorsed; }

    @Override
    public String toString() {
        return "ThirdParty{" +
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", percentageIncrease='" + percentageIncrease + '\'' +
                ", dateEndorsed='" + dateEndorsed + '\'' +
                '}';
    }


}




