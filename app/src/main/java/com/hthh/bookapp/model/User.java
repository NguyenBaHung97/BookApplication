package com.hthh.bookapp.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("email")
    private String email;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
