package com.shadowtech.foodadda.Model;

public class AddToCart {

    private int id,user_id,quantity,total_price;
    private String food_name,food_img,date,message;

    public AddToCart(int id, int user_id, int quantity, int total_price, String food_name, String food_img, String date,String message) {
        this.id = id;
        this.user_id = user_id;
        this.quantity = quantity;
        this.total_price = total_price;
        this.food_name = food_name;
        this.food_img = food_img;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal_price() {
        return total_price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_img() {
        return food_img;
    }

    public void setFood_img(String food_img) {
        this.food_img = food_img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
