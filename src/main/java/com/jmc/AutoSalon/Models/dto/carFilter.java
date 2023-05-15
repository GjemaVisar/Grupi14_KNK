package com.jmc.AutoSalon.Models.dto;

import com.jmc.AutoSalon.Services.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class carFilter {
    private String name;

    private String model;

    private String type;
    private double price = -1;
    private  String color;
    private double maxSpeed = -1;
    private int yearMade = -1;
    PreparedStatement preparedStatement;
    ArrayList<String>array = new ArrayList<String>();
    public static Connection conn;
    String sql = "SELECT * FROM cars WHERE 1 = 1 ";
    public void checkFilters() throws SQLException {
        System.out.println(!this.color.isEmpty());
        if(!this.name.isEmpty()) {
            this.sql += "AND c_name LIKE ? ";
            array.add("name");
            System.out.println("name was added");
        }
        if(!this.model.isEmpty()) {
            this.sql += "AND car_model LIKE ? ";
            array.add("model");
            System.out.println("model was added");
        }
        if(!this.type.isEmpty()) {
            this.sql += "AND car_type LIKE ? ";
            array.add("type");
            System.out.println("type was added");
        }
        if(this.price != -1 ) {
            this.sql += "AND price_c = ? ";
            array.add("price");
            System.out.println("price was added");
        }
        if(!this.color.isEmpty()) {
            this.sql += "AND color LIKE ? ";
            array.add("color");
        }
        if(this.maxSpeed != -1 ) {
            this.sql += "AND max_speed = ? ";
            array.add("maxSpeed");
        }
        if(this.yearMade != -1 ) {
            this.sql += "AND year_c = ? ";
            array.add("yearMade");
        }
        conn = ConnectionUtil.getConnection();
        System.out.println(this.sql);
        preparedStatement = conn.prepareStatement(this.sql);
        addStatements();
    }
    public void addStatements() throws SQLException {
        int count = 1;
        if(!array.isEmpty()) {
            for (String a:
                    this.array) {
                switch(a) {
                    case "name":
                        preparedStatement.setString(count, this.getName());
                        break;
                    case "model":
                        preparedStatement.setString(count, this.getModel());
                        break;
                    case "type":
                        preparedStatement.setString(count, this.getType());
                        break;
                    case "price":
                        preparedStatement.setDouble(count, this.getPrice());
                        break;
                    case "color":
                        preparedStatement.setString(count, this.getColor());
                        break;
                    case "maxSpeed":
                        preparedStatement.setDouble(count, this.getMaxSpeed());
                        break;
                    case "yearMade":
                        preparedStatement.setInt(count, this.getYearMade());
                        break;
                }
                count ++;
            }
        }

    }
    public ResultSet sqlQueryCars() throws SQLException {
        checkFilters();

        ResultSet result = preparedStatement.executeQuery();
        return result;
    }
    public static void closeConnection() throws SQLException {
        conn.close();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getYearMade() {
        return yearMade;
    }

    public void setYearMade(int yearMade) {
        this.yearMade = yearMade;
    }

}
