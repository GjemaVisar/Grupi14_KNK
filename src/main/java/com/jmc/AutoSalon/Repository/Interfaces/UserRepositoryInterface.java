package com.jmc.AutoSalon.Repository.Interfaces;

import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Models.dto.CreateUserDto;

import java.sql.SQLException;

public interface UserRepositoryInterface {
    public User insert(CreateUserDto user) throws SQLException;
    //public User getByUsername(String username) throws SQLException;
}
