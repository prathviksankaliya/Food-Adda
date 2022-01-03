package com.shadowtech.foodadda.Model;

public class AddToCart {

    private int image, TotalPrice, Quantity, id;
    private String Name;


    public AddToCart(int image, int totalPrice, int quantity, int id, String name) {
        this.image = image;
        TotalPrice = totalPrice;
        Quantity = quantity;
        this.id = id;
        Name = name;
    }

    public AddToCart() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
