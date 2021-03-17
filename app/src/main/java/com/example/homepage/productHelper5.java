package com.example.homepage;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class productHelper5 {

    int image;
    String title;
    String oldprice;
    String newprice;
    int rating;

    public productHelper5() {
    }

    public productHelper5(int image, String title, String oldprice, String newprice, int rating) {
        this.image = image;
        this.title = title;
        this.oldprice = oldprice;
        this.newprice = newprice;
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOldprice() {
        return oldprice;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }

    public String getNewprice() {
        return newprice;
    }

    public void setNewprice(String newprice) {
        this.newprice = newprice;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}