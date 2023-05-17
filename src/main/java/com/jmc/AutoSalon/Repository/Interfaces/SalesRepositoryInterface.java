package com.jmc.AutoSalon.Repository.Interfaces;

import java.sql.Date;
import java.sql.SQLException;

public interface SalesRepositoryInterface {

    void insertSale(int userId, int carId, Date purchaseDate, double price) throws SQLException ;
    void decrement_quantity(int carId) throws SQLException;

    boolean isQuantityZero(int carId) throws SQLException;
}
