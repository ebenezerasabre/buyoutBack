package com.quest.buyout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AboutUs {
    String _id;
    String title;
    String msg;

    public AboutUs(){}
    public AboutUs(@JsonProperty("title") String title,
                   @JsonProperty("msg") String msg) {
        this.title = title;
        this.msg = msg;
    }

    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getMsg() { return msg; }

    public void setMsg(String msg) { this.msg = msg; }

    @Override
    public String toString() {
        return "AboutUs{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}

//    Helvetica Neue


