package com.jmc.AutoSalon.Repository;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Models.dto.CreateCarsDto;
import com.jmc.AutoSalon.Repository.Interfaces.CarRepositoryInterface;
import com.jmc.AutoSalon.Services.ConnectionUtil;

import java.sql.*;
import java.time.LocalDate;

public class RepositoryCar implements CarRepositoryInterface {
    @Override
    public Cars insert(CreateCarsDto cars) throws SQLException {
        String sql = "INSERT INTO cars(c_name,car_model,car_type,price_c,color,max_speed,year_c,car_image,inserted_on,updated_on)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?) ";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cars.getName());
            statement.setString(2, cars.getModel());
            statement.setString(3,cars.getType());
            statement.setDouble(4, cars.getPrice());
            statement.setString(5, cars.getColor());
            statement.setDouble(6, cars.getMax_speed());
            statement.setInt(7,cars.getYear_made());
            statement.setString(8, cars.getCar_image());
            statement.setDate(9, cars.getInserted_on());
            statement.setDate(10, cars.getUpdated_on());
            statement.executeUpdate();

            return RepositoryCar.getByCarName(cars.getName(), cars.getModel(), cars.getType(), cars.getYear_made());
        }
    }



    public static Cars getByCarName(String name,String model,String type,int year) throws SQLException{
        String sql = "Select * from cars where c_name = ? and car_model=? and car_type=? and year_c =?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, model);
            statement.setString(3, type);
            statement.setInt(4, year);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("numri_serik");
                String car_name = resultSet.getString("c_name");
                String car_model = resultSet.getString("car_model");
                String car_type = resultSet.getString("car_type");
                Double price = resultSet.getDouble("price_c");
                String color = resultSet.getString("color");
                Double max_speed = resultSet.getDouble("max_speed");
                int year_made = resultSet.getInt("year_c");
                String car_image = resultSet.getString("car_image");
                Date inserted_on = resultSet.getDate("inserted_on");
                Date updated_on = resultSet.getDate("updated_on");
                return new Cars(id,car_name,model,type,price,color,max_speed,year_made,car_image,inserted_on,updated_on);
            } else {
                return null;
            }
        }
    }
}
