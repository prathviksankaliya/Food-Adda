package com.shadowtech.foodadda.Model;

public class Recommeneditems {

    private int image;
    private String itemname , rating , price ;

    public Recommeneditems(int imgname, String itemname, String rating, String price) {
        this.image = imgname;
        this.itemname = itemname;
        this.rating = rating;
        this.price = price;
    }

    public Recommeneditems() {

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
