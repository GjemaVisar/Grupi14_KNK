package com.jmc.AutoSalon.Models;

import java.sql.Date;
import java.time.LocalDate;

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

    public int getSerial(){
        return serial;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getYearMade() {
        return yearMade;
    }

    public void setYearMade(int yearMade) {
        this.yearMade = yearMade;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public Date getInsertedOn() {
        return insertedOn;
    }

    public void setInsertedOn(Date insertedOn) {
        this.insertedOn = insertedOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Cars(int serial, String name, String model, String type, double price, String color, double maxSpeed, int yearMade,
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
