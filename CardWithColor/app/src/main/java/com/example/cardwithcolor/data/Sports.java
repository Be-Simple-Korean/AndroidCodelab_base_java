package com.example.cardwithcolor.data;

import java.io.Serializable;

public class Sports implements Serializable {
    private String title;
    private String info;
    private final int imageResource;

    public Sports(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }
}
