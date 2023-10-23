package com.example.helloworld.Models;

public class ListItem {

    private int imageResource;
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public ListItem(int imageResource, String title, String description) {
        this.imageResource = imageResource;
        this.title = title;
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

}
