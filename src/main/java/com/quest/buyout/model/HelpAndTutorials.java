package com.quest.buyout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HelpAndTutorials {
   private String _id;
   private String title;
   private String msg;
   private String img;

    public HelpAndTutorials() {
    }

    public HelpAndTutorials(@JsonProperty("title") String title,
                            @JsonProperty("msg") String msg,
                            @JsonProperty("img") String img) {
        this.title = title;
        this.msg = msg;
        this.img = img;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "HelpAndTutorials{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", msg='" + msg + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
