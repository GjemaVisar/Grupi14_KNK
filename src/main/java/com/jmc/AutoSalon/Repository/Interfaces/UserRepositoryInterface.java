package com.jmc.AutoSalon.Repository.Interfaces;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Models.dto.CreateUserDto;
import javafx.scene.control.TableView;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface UserRepositoryInterface {
    public User insert(CreateUserDto user) throws SQLException;

    public User getAllUsers(TableView<User> tbl, Boolean statusi) throws SQLException;

    public void deleteUser(int id) throws SQLException;


    String getByUsernameId(int id) throws SQLException;

    List<Cars> your_car(int id) throws SQLException;
}

