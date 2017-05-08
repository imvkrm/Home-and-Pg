package com.example.vikram.myo;

/**
 * Created by vikram on 7/15/2016.
 */
public class ChdImp {
    private String tittle,price;
    private int imageUrl;

    public ChdImp(String tittle,String price, int imageUrl) {
        this.tittle = tittle;
        this.price=price;
        this.imageUrl = imageUrl;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String tittle) {
        this.price = price;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

}
