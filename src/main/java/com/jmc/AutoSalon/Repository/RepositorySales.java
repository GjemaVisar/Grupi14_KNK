package com.jmc.AutoSalon.Repository;

import com.jmc.AutoSalon.Repository.Interfaces.SalesRepositoryInterface;
import com.jmc.AutoSalon.Services.ConnectionUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RepositorySales implements SalesRepositoryInterface {


    public void insertSale(int userId, int carId, Date purchaseDate, double price) throws SQLException {
        String sql = "INSERT INTO sales (user_id, car_id, data_blerjes, price) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, carId);
            statement.setDate(3, purchaseDate);
            statement.setDouble(4, price);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
