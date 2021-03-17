package com.example.homepage;

public class ProductUpload {

    String ProductName;
    String Type;
    String Price;
    String imgUri;

    public ProductUpload() {
    }

    public ProductUpload(String productName, String type, String price, String imgUri) {
        ProductName = productName;
        Type = type;
        Price = price;
        this.imgUri = imgUri;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPrice() {
        return Price;
    }


    public void setPrice(String price) {
        Price = price;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }
}
