package com.hthh.bookapp.model;

import com.google.gson.annotations.SerializedName;

public class StoryData {
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private String data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
