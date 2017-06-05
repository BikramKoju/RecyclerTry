package com.example.bikramkoju.recyclertry.expense;

/**
 * Created by Bikramkoju on 5/18/2017.
 */

public class ExpenseDetail {

    private String name;
    private int price;
    private int thumbnail;
    private int id;

    public ExpenseDetail(){

    }

    public ExpenseDetail(String name, int price, int thumbnail, int id){
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
