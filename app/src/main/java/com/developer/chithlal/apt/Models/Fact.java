package com.developer.chithlal.apt.Models;

public class Fact {
    private String title ;
    private String description;
    private String imgURL;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Fact(String title, String description, String imgURL) {
        this.title = title;
        this.description = description;
        this.imgURL = imgURL;
    }
}
