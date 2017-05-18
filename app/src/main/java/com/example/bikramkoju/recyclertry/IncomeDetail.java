package com.example.bikramkoju.recyclertry;

/**
 * Created by Bikramkoju on 5/18/2017.
 */

public class IncomeDetail {
    private String name;
    private int price;
    private int thumbnail;

    public IncomeDetail(){

    }

    public IncomeDetail(String name, int price, int thumbnail){
        this.name=name;
        this.price=price;
        this.thumbnail=thumbnail;
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