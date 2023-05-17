package com.jmc.AutoSalon.Repository.Interfaces;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Models.dto.CreateCarsDto;
import com.jmc.AutoSalon.Services.ConnectionUtil;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface CarRepositoryInterface {
    public Cars insert(CreateCarsDto cars) throws SQLException;
    public Cars update(CreateCarsDto cars,int id) throws SQLException;

    public Cars getAllCars(TableView<Cars> tbl) throws SQLException;

    public void deleteCar(int id) throws SQLException;

}
