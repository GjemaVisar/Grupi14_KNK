package com.jmc.AutoSalon.Repository;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Models.dto.CreateUserDto;
import com.jmc.AutoSalon.Services.ConnectionUtil;
import com.jmc.AutoSalon.Repository.Interfaces.UserRepositoryInterface;
import javafx.scene.control.TableView;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public User getAllUsers(TableView<User> tbl,Boolean statusi) throws SQLException{
        String sql = "Select * from users where is_admin = ?";
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement stm = connection.prepareStatement(sql)){
            stm.setBoolean(1,statusi);
            ResultSet resultSet = stm.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("salted_password");
                Boolean status = resultSet.getBoolean("is_admin");
                Date date_registered = resultSet.getDate("date_registered");
                User user = new User(id,username,password,status,date_registered);
                tbl.getItems().add(user);
            }
        }catch(SQLException se){
            Model.getInstance().getViewFactory().showAlert("Retrieval error","Cannot retrieve users!");
        }
        return null;
    }
    @Override
    public void deleteUser(int id) throws SQLException{
        String sql = "Delete from users where id=?";
        try(Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
        return;
    }

    @Override
    public String getByUsernameId(int id) throws SQLException {
        String sql = "Select username from users where id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                return username;
            } else {
                return null;
            }
        }
    }
    @Override
    public List<Cars> your_car(int id) throws SQLException{
        List<Cars> carsList = new ArrayList<>();

        String sql = "Select c.c_name, c.car_model , c.car_type, c.year_c , c.price_c FROM sales s INNER JOIN cars c ON s.car_id = c.numri_serik WHERE s.user_id = ? " ;
        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String car_name = resultSet.getString("c_name");
                String car_model = resultSet.getString("car_model");
                String car_type = resultSet.getString("car_type");
                int car_year = resultSet.getInt("year_c");
                double car_price = resultSet.getDouble("price_c");

                Cars cars = new Cars(car_name, car_model, car_type, car_year, car_price);
                carsList.add(cars);
            }
        }
        return carsList;
    }

}