package com.jmc.AutoSalon.Repository.Interfaces;

import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Models.dto.CreateUserDto;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public interface UserRepositoryInterface {
    public User insert(CreateUserDto user) throws SQLException;
    public User getAllUsers(TableView<User> tbl,Boolean statusi) throws SQLException;
    public void deleteUser(int id) throws SQLException;
}
