package com.example.vikram.myo;

/**
 * Created by vikram on 7/15/2016.
 */
public class BsnImp {
    private String tittle;
    private int imageUrl;

    public BsnImp(String tittle, int imageUrl) {
        this.tittle = tittle;
        this.imageUrl = imageUrl;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

}
