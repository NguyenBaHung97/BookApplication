package com.hthh.bookapp.model;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private User data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
