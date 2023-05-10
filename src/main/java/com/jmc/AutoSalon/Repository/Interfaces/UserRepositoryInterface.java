package com.jmc.AutoSalon.Repository.Interfaces;

import Models.User;
import Models.dto.CreateUserDto;

import java.sql.SQLException;

public interface UserRepositoryInterface {
    public User insert(CreateUserDto user) throws SQLException;
    //public User getByUsername(String username) throws SQLException;
}
