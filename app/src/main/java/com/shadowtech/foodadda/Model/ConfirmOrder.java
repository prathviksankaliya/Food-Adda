package com.shadowtech.foodadda.Model;

public class ConfirmOrder {
    private int id, Image, TotalPrice, Quntity;
    private String UserName, UserPhone, FoodName;

    public ConfirmOrder(int id, int image, int totalPrice, int quntity, String userName, String userPhone, String foodName) {
        this.id = id;
        Image = image;
        TotalPrice = totalPrice;
        Quntity = quntity;
        UserName = userName;
        UserPhone = userPhone;
        FoodName = foodName;

    }

    public ConfirmOrder(int image, int totalPrice, int quntity, String userName, String userPhone, String foodName) {
        Image = image;
        TotalPrice = totalPrice;
        Quntity = quntity;
        UserName = userName;
        UserPhone = userPhone;
        FoodName = foodName;

    }

    public ConfirmOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getQuntity() {
        return Quntity;
    }

    public void setQuntity(int quntity) {
        Quntity = quntity;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

}
