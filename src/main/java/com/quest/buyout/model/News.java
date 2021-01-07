package com.quest.buyout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class News {
    private String _id;
    private String source;
    private String date;
    private String msg;
    private String img;
    private String[] thumbs = {"", ""};       // let server

    public News(){}
    public News( @JsonProperty("source") String source,
                 @JsonProperty("msg") String msg,
                 @JsonProperty("img") String img) {
        this.source = source;
        this.msg = msg;
        this.img = img;
    }

    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

    public String getSource() { return source; }

    public void setSource(String source) { this.source = source; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getMsg() { return msg; }

    public void setMsg(String msg) { this.msg = msg; }

    public String getImg() { return img; }

    public void setImg(String img) { this.img = img; }

    public String[] getThumbs() { return thumbs; }

    public void setThumbs(String[] thumbs) { this.thumbs = thumbs; }

    @Override
    public String toString() {
        return "News{" +
                "_id='" + _id + '\'' +
                ", source='" + source + '\'' +
                ", date='" + date + '\'' +
                ", msg='" + msg + '\'' +
                ", img='" + img + '\'' +
                ", thumbs=" + Arrays.toString(thumbs) +
                '}';
    }
}
