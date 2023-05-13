package com.jmc.AutoSalon.Repository;

import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Models.dto.CreateUserDto;
import com.jmc.AutoSalon.Services.ConnectionUtil;
import com.jmc.AutoSalon.Repository.Interfaces.UserRepositoryInterface;

import java.sql.*;
import java.time.LocalDate;

public class RepositoryUser implements UserRepositoryInterface {
    @Override
    public User insert(CreateUserDto user) throws SQLException {
        String sql = "INSERT INTO users(username,salted_password,is_admin,date_registered) values (?,?,?,?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getSaltedPassword());
            statement.setBoolean(3,user.get_admin_status());
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.executeUpdate();

            return RepositoryUser.getByUsername(user.getUsername());
        }
    }

    public static User getByUsername(String username) throws SQLException {
        String sql = "Select * from users where username = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String saltedHash = resultSet.getString("salted_password");
                boolean is_admin = resultSet.getBoolean("is_admin");
                Date date_registered = resultSet.getDate("date_registered");
                return new User(id, username, saltedHash, is_admin,date_registered);
            } else {
                return null;
            }
        }
    }
}