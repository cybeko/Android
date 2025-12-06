package com.example.proj4_recyclerview;

public class WishItem {
    public int image;
    public String name;
    public int price;
    public boolean checked;

    public WishItem(int image, String name, int price) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.checked = false;
    }
}
