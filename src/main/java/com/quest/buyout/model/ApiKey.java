package com.quest.buyout.model;

public class ApiKey {
    String _id;
    String companyName;
    String apiKey;
    boolean authorized;
    String dataAuthorized;

    public ApiKey() {
    }

    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public boolean isAuthorized() { return authorized; }
    public void setAuthorized(boolean authorized) { this.authorized = authorized; }

    public String getDataAuthorized() { return dataAuthorized; }
    public void setDataAuthorized(String dataAuthorized) { this.dataAuthorized = dataAuthorized; }

    @Override
    public String toString() {
        return "ApiKey{" +
                "_id='" + _id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", authorized=" + authorized +
                ", dataAuthorized='" + dataAuthorized + '\'' +
                '}';
    }


}
