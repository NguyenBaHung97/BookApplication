package com.hthh.bookapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Story implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("name_story")
    private String name_story;
    @SerializedName("chap_story")
    private String chap_story;
    @SerializedName("image_story")
    private String image_story;
    @SerializedName("id_type")
    private String id_type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_story() {
        return name_story;
    }

    public void setName_story(String name_story) {
        this.name_story = name_story;
    }

    public String getChap_story() {
        return chap_story;
    }

    public void setChap_story(String chap_story) {
        this.chap_story = chap_story;
    }

    public String getImage_story() {
        return image_story;
    }

    public void setImage_story(String image_story) {
        this.image_story = image_story;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public Story(String id, String name_story, String chap_story, String image_story, String id_type) {
        this.id = id;
        this.name_story = name_story;
        this.chap_story = chap_story;
        this.image_story = image_story;
        this.id_type = id_type;
    }
}
