package com.jmc.AutoSalon.Repository;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Repository.Interfaces.SalesRepositoryInterface;
import com.jmc.AutoSalon.Services.ConnectionUtil;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.*;

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

    public void decrement_quantity(int carId) throws SQLException{
        String sql = "UPDATE cars set quantity = quantity -1 WHERE numri_serik = ?";
        try(Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);){
            stm.setInt(1,carId);
            stm.executeUpdate();
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }

    }

    //metoda per me e shiku a eshte quantity i makines se selektuar = 0
    public boolean isQuantityZero(int carId) throws SQLException{
        String sql = "SELECT quantity FROM cars WHERE numri_serik = ?";
        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,carId);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()){
                    int quantity = resultSet.getInt("quantity");
                    return quantity == 0;
                }

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void get_sales_Data(ObservableList<PieChart.Data> list) throws  SQLException{
        String sql = "SELECT c.c_name as name, sum(s.price) as profit FROM cars c inner join sales s on c.numri_serik = s.car_id group by c.c_name";
        try(Connection conn = ConnectionUtil.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);){
            ResultSet res = stm.executeQuery();
            while(res.next()){
                String name = res.getString("name");
                Double profit = res.getDouble("profit");
                PieChart.Data data = new PieChart.Data(name,profit);
                list.add(data);
            }

        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
    }
}
