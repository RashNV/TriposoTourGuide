package com.example.kylixmedusa;

import java.io.Serializable;

public class TravelModel implements Serializable {
    private String id;
    private String name;
    private String province;
    private String type;
    private String photo;
    private String location;
    private String description;

    public TravelModel() {
    }


    public TravelModel(String id, String name, String province, String type, String description) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.type = type;
        this.description = description;
    }

    public void update( String name, String province, String type, String description) {

        this.name = name;
        this.province = province;
        this.type = type;
        this.description = description;
    }


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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
