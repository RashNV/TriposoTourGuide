package com.example.kylixmedusa;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class ReviewModel implements Serializable {
    @Exclude
    private String key;
    private String name;
    private String email;
    private String description;

    public ReviewModel(){

    }

    public ReviewModel(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}

