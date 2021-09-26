package com.example.hotelmanagement.model;

public class Hotel {

    private String hotelID;
    private String hotelName;
    private String district;
    private String type;
    private String address;
    private String price;
    private String contact;

    public Hotel() {
    }

    public Hotel(String hotelID, String hotelName, String district, String type, String address, String price, String contact) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.district = district;
        this.type = type;
        this.address = address;
        this.price = price;
        this.contact = contact;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
