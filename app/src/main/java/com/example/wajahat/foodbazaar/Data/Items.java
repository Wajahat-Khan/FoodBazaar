package com.example.wajahat.foodbazaar.Data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Items {
    @PrimaryKey
    private int id;
    private String name;
private String short_description;
private String long_description;
private int price;
private boolean is_avail;
private String picture;
private String video;
private String sub_category;
private String major_category;
private float rating;
private String ingredients;

public Items(int id, String name, String short_description, int price, String picture, boolean is_avail, String long_description, String video,
             String category, String major_category, float rating, String ingredients){
    this.id=id;
    this.name=name;
    this.short_description=short_description;
    this.price=price;
    this.picture=picture;
    this.is_avail=is_avail;
    this.long_description=long_description;
    this.video=video;
    this.sub_category=category;
    this.major_category=major_category;
    this.rating=rating;
    this.ingredients=ingredients;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isIs_avail() {
        return is_avail;
    }

    public void setIs_avail(boolean is_avail) {
        this.is_avail = is_avail;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getMajor_category() {
        return major_category;
    }

    public void setMajor_category(String major_category) {
        this.major_category = major_category;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}

