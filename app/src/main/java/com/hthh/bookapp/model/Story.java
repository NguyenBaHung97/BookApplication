package com.hthh.bookapp.model;

import java.util.List;

public class Story {
    private int id;
    private String name;
    private List<String> classify;

    public Story(int id, String name, List<String> classify) {
        this.id = id;
        this.name = name;
        this.classify = classify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getClassify() {
        return classify;
    }

    public void setClassify(List<String> classify) {
        this.classify = classify;
    }
}
