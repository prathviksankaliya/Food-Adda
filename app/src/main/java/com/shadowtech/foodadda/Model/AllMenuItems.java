package com.shadowtech.foodadda.Model;

public class AllMenuItems {

   private int allitemimage;
   private String allitemname , allitemspecial , allitemrating ,allitemprice , delivery;


    public AllMenuItems(int allitemimage, String allitemname, String allitemspecial, String allitemrating, String allitemprice, String delivery) {
        this.allitemimage = allitemimage;
        this.allitemname = allitemname;
        this.allitemspecial = allitemspecial;
        this.allitemrating = allitemrating;
        this.allitemprice = allitemprice;
        this.delivery = delivery;
    }

    public AllMenuItems() {
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public int getAllitemimage() {
        return allitemimage;
    }

    public void setAllitemimage(int allitemimage) {
        this.allitemimage = allitemimage;
    }

    public String getAllitemname() {
        return allitemname;
    }

    public void setAllitemname(String allitemname) {
        this.allitemname = allitemname;
    }

    public String getAllitemspecial() {
        return allitemspecial;
    }

    public void setAllitemspecial(String allitemspecial) {
        this.allitemspecial = allitemspecial;
    }

    public String getAllitemrating() {
        return allitemrating;
    }

    public void setAllitemrating(String allitemrating) {
        this.allitemrating = allitemrating;
    }

    public String getAllitemprice() {
        return allitemprice;
    }

    public void setAllitemprice(String allitemprice) {
        this.allitemprice = allitemprice;
    }
}
