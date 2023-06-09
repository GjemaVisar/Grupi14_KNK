package com.jmc.AutoSalon.Models;

import java.time.LocalDate;

public class CarModelClass {
    private int id;
    private String name;
    private String model;
    private String carType;
    private double price;
    private String color;
    private double maxSpeed;
    private int year;
    private String carImage;
    private LocalDate insertedOn;
    private LocalDate updatedOn;

    public CarModelClass(int id, String c_name, String model, String carType, double price, String color, double maxSpeed, int year, String carImage, LocalDate insertedOn, LocalDate updatedOn) {
        this.id = id;
        this.name = c_name;
        this.model = model;
        this.carType = carType;
        this.price = price;
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.year = year;
        this.carImage = carImage;
        this.insertedOn = insertedOn;
        this.updatedOn = updatedOn;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getCarType() {
        return carType;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public int getYear() {
        return year;
    }

    public String getCarImage() {
        return carImage;
    }

    public LocalDate getInsertedOn() {
        return insertedOn;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + carType + " " + model + " (" + year + ") - " + color + " - $" + price;
    }
}
