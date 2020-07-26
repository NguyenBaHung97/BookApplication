package com.hthh.bookapp.model;

import com.google.gson.annotations.SerializedName;

public class ChapStory {
    @SerializedName("id")
    private String id;
    @SerializedName("name_chap")
    private String name_chap;
    @SerializedName("date")
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_chap() {
        return name_chap;
    }

    public void setName_chap(String name_chap) {
        this.name_chap = name_chap;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
