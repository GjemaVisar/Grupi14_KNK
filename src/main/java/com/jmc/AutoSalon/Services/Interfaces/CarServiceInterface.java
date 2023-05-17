package com.jmc.AutoSalon.Services.Interfaces;

import com.jmc.AutoSalon.Models.Cars;

import java.sql.Date;
import java.sql.SQLException;

public interface CarServiceInterface {
    Cars insert_car(String name, String model, String type,
                    double price, String color, double maxSpeed, int yearMade,int quantity, String carImage) throws SQLException;
}
