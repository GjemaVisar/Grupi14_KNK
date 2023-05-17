package com.jmc.AutoSalon.Repository;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Models.dto.CreateCarsDto;
import com.jmc.AutoSalon.Repository.Interfaces.CarRepositoryInterface;
import com.jmc.AutoSalon.Services.CarAuthService;
import com.jmc.AutoSalon.Services.ConnectionUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

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

    @Override
    public Cars update(CreateCarsDto cars,int id) throws SQLException{
        String sql = "UPDATE cars SET c_name=?, car_model=?, car_type=?, price_c = ?, max_speed=?, year_c = ?," +
                "quantity=?,car_image=?,updated_on=? WHERE numri_serik=?";
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,cars.getName());
            statement.setString(2,cars.getModel());
            statement.setString(3,cars.getType());
            statement.setDouble(4,cars.getPrice());
            statement.setDouble(5,cars.getmaxSpeed());
            statement.setInt(6,cars.getyearMade());
            statement.setInt(7,cars.getQuantity());
            statement.setString(8,cars.getcarImage());
            statement.setDate(9,cars.getupdatedOn());
            statement.setInt(10,id);
            statement.executeUpdate();
            return RepositoryCar.getByCarName(cars.getName(),cars.getModel(),cars.getType(),cars.getyearMade());
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
                return null;
    }

    @Override
    public Cars getAllCars(TableView<Cars> tbl) throws SQLException {
        String sql = "SELECT * FROM cars";
        try(Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);){
            ResultSet  res = stm.executeQuery();
            while(res.next()){
                int id = res.getInt("numri_serik");
                String name = res.getString("c_name");
                String model = res.getString("car_model");
                String type = res.getString("car_type");
                Double price = res.getDouble("price_c");
                String color = res.getString("color");
                Double speed = res.getDouble("max_speed");
                int year = res.getInt("year_c");
                int quantity = res.getInt("quantity");
                String image = res.getString("car_image");
                Date inserted = res.getDate("inserted_on");
                Date updated = res.getDate("updated_on");
                Cars cars = new Cars(id,name,model,type,price,color,speed,year,quantity,image,inserted,updated);
                tbl.getItems().add(cars);
            }
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        return null;
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
    public static Cars getById(int id) throws SQLException{
        String sql = "Select * from cars where numri_serik = ?";
        try(Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql)){
            stm.setInt(1,id);
            ResultSet res = stm.executeQuery();
            if(res.next()){
                String car_name = res.getString("c_name");
                String car_model = res.getString("car_model");
                String car_type = res.getString("car_type");
                Double price = res.getDouble("price_c");
                String color = res.getString("color");
                Double maxSpeed = res.getDouble("max_speed");
                int yearMade = res.getInt("year_c");
                int quantity = res.getInt("quantity");
                String carImage = res.getString("car_image");
                Date insertedOn = res.getDate("inserted_on");
                Date updatedOn = res.getDate("updated_on");
                return new Cars(id,car_name,car_model,car_type,price,color,maxSpeed,yearMade,quantity,carImage,insertedOn,updatedOn);
            }else{
                return null;
            }

        }
}
    @Override
    public void deleteCar(int id) throws SQLException{
        String sql = "Delete from cars where numri_serik=?";
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        return;
    }
}

