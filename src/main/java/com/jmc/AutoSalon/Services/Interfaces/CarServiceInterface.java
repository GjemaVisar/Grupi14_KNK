package com.jmc.AutoSalon.Services.Interfaces;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.User;
import javafx.scene.control.TableView;

import java.sql.Date;
import java.sql.SQLException;

public interface CarServiceInterface {
    Cars insert_car(String name, String model, String type,
                    double price, String color, double maxSpeed, int yearMade, int quantity, String carImage) throws SQLException;

    Cars update_car(int serial,String type, String price, String speed, String quantity, String image) throws SQLException;

    void fillCarTable(TableView<Cars> tbl) throws SQLException;

    void deleteCar(int id) throws SQLException;
}
