package com.jmc.AutoSalon.Models;

import java.sql.Date;

public class Cars {

    private int serial_no;
    private String name;

    private String model;

    private String type;
    private double price;
    private  String color;
    private double max_speed;
    private int year_made;
    private String car_image;
    private java.sql.Date inserted_on;
    private java.sql.Date updated_on;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(double max_speed) {
        this.max_speed = max_speed;
    }

    public int getYear_made() {
        return year_made;
    }

    public void setYear_made(int year_made) {
        this.year_made = year_made;
    }

    public String getCar_image() {
        return car_image;
    }

    public void setCar_image(String car_image) {
        this.car_image = car_image;
    }

    public Date getInserted_on() {
        return inserted_on;
    }

    public void setInserted_on(Date inserted_on) {
        this.inserted_on = inserted_on;
    }

    public Date getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Date updated_on) {
        this.updated_on = updated_on;
    }

    public Cars(int serial_no, String name, String model, String type, double price, String color,double max_speed, int year_made,
                String car_image, java.sql.Date inserted_on, java.sql.Date updated_on){
        this.serial_no = serial_no;
        this.name = name;
        this.model = model;
        this.type = type;
        this.price = price;
        this.color = color;
        this.max_speed = max_speed;
        this.year_made = year_made;
        this.car_image = car_image;
        this.inserted_on = inserted_on;
        this.updated_on = updated_on;
    }


}
