package com.example.kylixmedusa;

import java.io.Serializable;

public class EventModel implements Serializable {
    private String id;
    private String name;
    private String province;
    private String city;
    private String cName;
    private String phone;
    private String email;
    private String price;
    private String photo;
    private String description;

    public EventModel() {
    }

    public EventModel(String id, String name, String province, String city, String cName, String phone, String email, String price,  String description) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.city = city;
        this.cName = cName;
        this.phone = phone;
        this.email = email;
        this.price = price;
        this.photo = photo;
        this.description = description;
    }

    public void update(String name, String province, String city, String cName, String phone, String email, String price,  String description) {
        this.name = name;
        this.province = province;
        this.city = city;
        this.cName = cName;
        this.phone = phone;
        this.email = email;
        this.price = price;
        this.photo = photo;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
