package com.hthh.bookapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoryOfBookcase implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("name_chap")
    private String name_chap;
    @SerializedName("linkimage")
    private String linkimage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_chap() {
        return name_chap;
    }

    public void setName_chap(String name_chap) {
        this.name_chap = name_chap;
    }

    public String getLinkimage() {
        return linkimage;
    }

    public void setLinkimage(String linkimage) {
        this.linkimage = linkimage;
    }
}
