package com.example.wajahat.foodbazaar_fb.Data;

public class Categories {
    private int id;
    private String name;
    private String sub_categories;

    public Categories(int id, String name, String sub_categories){
        this.id=id;
        this.name=name;
        this.sub_categories=sub_categories;
    }
    public Categories(){

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

    public String getSub_categories() {
        return sub_categories;
    }

    public void setSub_categories(String sub_categories) {
        this.sub_categories = sub_categories;
    }
}
