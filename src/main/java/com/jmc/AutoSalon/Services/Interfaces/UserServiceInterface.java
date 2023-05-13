package com.jmc.AutoSalon.Services.Interfaces;

import com.jmc.AutoSalon.Models.User;

import java.sql.SQLException;

public interface UserServiceInterface {
    User login(String username,String password) throws SQLException;
    User signup(String username,String password) throws SQLException;
    User createClient(String username, String password) throws SQLException;
}
