package com.hthh.bookapp.model;

import com.google.gson.annotations.SerializedName;

public class LinkImage {
    @SerializedName("image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
