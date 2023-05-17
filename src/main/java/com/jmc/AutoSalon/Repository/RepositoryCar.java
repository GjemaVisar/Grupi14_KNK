package com.jmc.AutoSalon.Repository;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Models.dto.CreateCarsDto;
import com.jmc.AutoSalon.Repository.Interfaces.CarRepositoryInterface;
import com.jmc.AutoSalon.Services.CarAuthService;
import com.jmc.AutoSalon.Services.ConnectionUtil;

import java.sql.*;
import java.time.LocalDate;

public class RepositoryCar implements CarRepositoryInterface {
    @Override
    public Cars insert(CreateCarsDto cars) throws SQLException {
        String sql = "INSERT INTO cars(c_name,car_model,car_type,price_c,color,max_speed,year_c,quantity,car_image,inserted_on,updated_on)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?) ";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cars.getName());
            statement.setString(2, cars.getModel());
            statement.setString(3,cars.getType());
            statement.setDouble(4, cars.getPrice());
            statement.setString(5, cars.getColor());
            statement.setDouble(6, cars.getmaxSpeed());
            statement.setInt(7,cars.getyearMade());
            statement.setInt(8,cars.getQuantity());
            statement.setString(9, cars.getcarImage());
            statement.setDate(10, cars.getinsertedOn());
            statement.setDate(11, cars.getupdatedOn());
            statement.executeUpdate();

            return RepositoryCar.getByCarName(cars.getName(), cars.getModel(), cars.getType(), cars.getyearMade());
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
                Double maxSpeed = resultSet.getDouble("max_speed");
                int yearMade = resultSet.getInt("year_c");
                int quantity = resultSet.getInt("quantity");
                String carImage = resultSet.getString("car_image");
                Date insertedOn = resultSet.getDate("inserted_on");
                Date updatedOn = resultSet.getDate("updated_on");
                return new Cars(id,car_name,model,type,price,color,maxSpeed,yearMade,quantity,carImage,insertedOn,updatedOn);
            } else {
                return null;
            }
        }
    }
}
