package com.jmc.AutoSalon.Models.dto;

import com.jmc.AutoSalon.Services.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class carFilter {
    private String name = "Audi";

    private String model = "SUV";
    PreparedStatement preparedStatement;
    ArrayList<String>array = new ArrayList<String>();
    public static Connection conn;
    String sql = "SELECT * FROM cars WHERE 1 = 1 ";
    public void checkFilters() throws SQLException {
        if(!this.name.isEmpty()) {
            this.sql += "AND c_name LIKE ? ";
            array.add("name");
        }
        if(!this.model.isEmpty()) {
            this.sql += "AND car_model LIKE ? ";
            array.add("model");
        }
        conn = ConnectionUtil.getConnection();
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
                }
                count ++;
            }
        }

    }
    public ResultSet sqlQueryCars() throws SQLException {
        checkFilters();
        System.out.println(this.sql);
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
    public void resetSql() { this.sql = "SELECT * FROM cars WHERE 1 = 1 "; }

}
