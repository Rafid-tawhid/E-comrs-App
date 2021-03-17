package com.example.homepage;

public class SliderData {

    // image url is used to
    // store the url of image
    private String imgUrl;
    private int img;

    public SliderData(int img) {

        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    // Constructor method.
    public SliderData(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    // Getter method
    public String getImgUrl() {
        return imgUrl;
    }

    // Setter method
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
