package com.jmc.AutoSalon.Models.dto;

import java.sql.Date;

public class CreateCarsDto {
    private String name;
    private String model;
    private  String type;
    private double price;
    private double maxSpeed;

    private String color;

    private int yearMade;

    private int quantity;

    private String carImage;
    private java.sql.Date insertedOn;
    private java.sql.Date updatedOn;


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CreateCarsDto(String name, String model, String type,
                         double price, String color, double maxSpeed, int yearMade,int quantity, String carImage, Date insertedOn, Date updatedOn) {
        this.name = name;
        this.model = model;
        this.type = type;
        this.price = price;
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.yearMade = yearMade;
        this.quantity = quantity;
        this.carImage = carImage;
        this.insertedOn = insertedOn;
        this.updatedOn = updatedOn;
    }

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

    public double getmaxSpeed() {
        return maxSpeed;
    }

    public void setmaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getyearMade() {
        return yearMade;
    }

    public void setyearMade(int yearMade) {
        this.yearMade = yearMade;
    }

    public String getcarImage() {
        return carImage;
    }

    public void setcarImage(String carImage) {
        this.carImage = carImage;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public Date getinsertedOn() {
        return insertedOn;
    }

    public void setinsertedOn(Date insertedOn) {
        this.insertedOn = insertedOn;
    }

    public Date getupdatedOn() {
        return updatedOn;
    }

    public void setupdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}


