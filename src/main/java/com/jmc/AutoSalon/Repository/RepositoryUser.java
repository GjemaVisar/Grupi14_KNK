package com.jmc.AutoSalon.Repository;

import Models.User;
import Models.dto.CreateUserDto;
import Services.ConnectionUtil;
import com.jmc.AutoSalon.Repository.Interfaces.UserRepositoryInterface;

import java.sql.*;

public class RepositoryUser implements UserRepositoryInterface {
    @Override
    public User insert(CreateUserDto user) throws SQLException {
        String sql = "INSERT INTO users(username,salted_hash,salt) values (?,?,?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getSaltedPassword());
            statement.setString(3, user.getSalt());
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
                String saltedHash = resultSet.getString("salted_hash");
                String salt = resultSet.getString("salt");
                boolean is_admin = resultSet.getBoolean("is_admin");
                return new User(id, username, saltedHash, salt, is_admin);
            } else {
                return null;
            }
        }
    }
}