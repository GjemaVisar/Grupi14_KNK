package com.jmc.AutoSalon.Models;

import java.util.Date;

public class Cars {

    private int serial_no;
    private String name;
    private double price;
    private  String manufacturer;
    private double max_speed;

    private int year_made;
    private String car_image;
    private Date inserted_on;
    private Date updated_on;

    public Cars(int serial_no, String name, double price, String manufacturer,double max_speed,
                String car_image, Date inserted_on, Date updated_on){
        this.serial_no=serial_no;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.max_speed = max_speed;
        this.car_image = car_image;
        this.inserted_on = inserted_on;
        this.updated_on = updated_on;
    }

    public void setCarName(String name){
        this.name = name;
    }
    public String getCarName(){
        return this.name;
    }

    public void setCarPrice(double price){
        this.price = price;
    }
    public double getCarPrice(){
        return this.price;
    }

    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    public String getManufacturer(){
        return this.manufacturer;
    }



    public void setMax_speed(double speed){
        this.max_speed = speed;
    }

    public double getMax_speed(){
        return this.max_speed;
    }

    public void setCar_image(String image){
        this.car_image = image;
    }
    public String getCar_image(){
        return this.car_image;
    }

    public void setDate_inserted(Date insert_date){
        this.inserted_on = insert_date;
    }
    public Date getDate_inserted(){
        return this.inserted_on;
    }

    public void setDate_updated(Date updated_on){
        this.updated_on = updated_on;
    }
    public Date getDate_updated(){
        return this.updated_on;
    }
}
