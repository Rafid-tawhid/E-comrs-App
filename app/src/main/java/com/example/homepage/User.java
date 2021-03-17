package com.example.homepage;

public class User {

    String name;
    String type;
    String price;

    public User(String name, String type, String price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
