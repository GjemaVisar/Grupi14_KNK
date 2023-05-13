package com.jmc.AutoSalon.Services.Interfaces;

import com.jmc.AutoSalon.Models.Cars;

import java.sql.Date;
import java.sql.SQLException;

public interface CarServiceInterface {
    Cars insert_car(String name, String model, String type,
                    double price, String color, double max_speed, int year_made, String car_image) throws SQLException;
}