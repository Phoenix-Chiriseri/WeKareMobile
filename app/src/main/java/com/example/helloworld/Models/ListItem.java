package com.example.helloworld.Models;

public class ListItem {

    private int imageResource;
    private String title;

    public ListItem(int imageResource, String title) {
        this.imageResource = imageResource;
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

}
