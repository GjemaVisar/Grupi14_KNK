package com.jmc.AutoSalon.Repository.Interfaces;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.nio.channels.Pipe;
import java.sql.Date;
import java.sql.SQLException;

public interface SalesRepositoryInterface {

    void insertSale(int userId, int carId, Date purchaseDate, double price) throws SQLException ;
    void decrement_quantity(int carId) throws SQLException;

    boolean isQuantityZero(int carId) throws SQLException;

    void get_sales_Data(ObservableList<PieChart.Data> list) throws SQLException;

}
