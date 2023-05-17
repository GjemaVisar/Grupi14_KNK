package com.jmc.AutoSalon.Services.Interfaces;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.User;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.List;

public interface UserServiceInterface {
    User login(String username,String password) throws SQLException;
    User signup(String username, String password) throws SQLException;
    User createClient(String username, String password) throws SQLException;

    void fillUserTable(TableView<User> tbl,Boolean statusi) throws SQLException;

    void deleteUser(int id) throws SQLException;

    int get_user_id() ;

    String get_username() throws SQLException;

    List<Cars> your_car() throws SQLException;
}
