package com.jmc.AutoSalon.Models;

import java.sql.Date;

public class Cars {

    private int serial;
    private String name;

    private String model;

    private String type;
    private double price;
    private  String color;
    private double maxSpeed;
    private int yearMade;
    private String carImage;
    private Date insertedOn;
    private Date updatedOn;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getmaxSpeed() {
        return this.maxSpeed;
    }

    public void setmaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getyearMade() {
        return this.yearMade;
    }

    public void setyearMade(int yearMade) {
        this.yearMade = yearMade;
    }

    public String getcarImage() {
        return this.carImage;
    }

    public void setcarImage(String carImage) {
        this.carImage = carImage;
    }

    public Date getinsertedOn() {
        return this.insertedOn;
    }

    public void setinsertedOn(Date insertedOn) {
        this.insertedOn = insertedOn;
    }

    public Date getupdatedOn() {
        return this.updatedOn;
    }

    public void setupdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Cars(int serial, String name, String model, String type, double price, String color,double maxSpeed, int yearMade,
                String carImage, Date insertedOn, Date updatedOn){
        this.serial = serial;
        this.name = name;
        this.model = model;
        this.type = type;
        this.price = price;
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.yearMade = yearMade;
        this.carImage = carImage;
        this.insertedOn = insertedOn;
        this.updatedOn = updatedOn;
    }
    @Override
    public String toString() {
        return this.serial + " " + this.name + " " + this.type + " " +
                this.model + " (" + this.yearMade + ") - " + this.color + " - $" + this.price;
    }


}
