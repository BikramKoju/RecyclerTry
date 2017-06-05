package com.example.bikramkoju.recyclertry.income;

import com.example.bikramkoju.recyclertry.database.DatabaseHelper;

/**
 * Created by Bikramkoju on 5/18/2017.
 */

public class IncomeDetail {
    private String name;
    private int price;
    private int thumbnail;
    private int id;

    public IncomeDetail(){

    }

    public IncomeDetail(String name, int price, int thumbnail, int id){
        this.id=id;
        this.name=name;
        this.price=price;
        this.thumbnail=thumbnail;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
