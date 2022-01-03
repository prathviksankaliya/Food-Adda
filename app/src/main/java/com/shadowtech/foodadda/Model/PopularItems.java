package com.shadowtech.foodadda.Model;

public class PopularItems {
    private String name;
    private int image;

    public PopularItems(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public PopularItems() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
