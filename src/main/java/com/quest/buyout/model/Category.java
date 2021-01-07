package com.quest.buyout.model;


// this will contain all the categories of our products
public class Category {
    private String _id;
    private String name;
    private String image;

    public Category(){}
    public Category(String name, String image){
        this.name = name;
        this.image = image;
    }

    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    @Override
    public String toString() {
        return "Category{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
