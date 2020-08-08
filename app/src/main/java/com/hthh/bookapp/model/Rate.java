package com.hthh.bookapp.model;

import com.google.gson.annotations.SerializedName;

public class Rate {
    @SerializedName("id_rate")
    private String id_rate;
    @SerializedName("id_story")
    private String id_story;
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("email")
    private String email;
    @SerializedName("comment")
    private String comment;
    @SerializedName("rating")
    private String rating;

    public String getId_rate() {
        return id_rate;
    }

    public void setId_rate(String id_rate) {
        this.id_rate = id_rate;
    }

    public String getId_story() {
        return id_story;
    }

    public void setId_story(String id_story) {
        this.id_story = id_story;
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
