package com.jmc.AutoSalon.Repository.Interfaces;

import java.sql.Date;
import java.sql.SQLException;

public interface SalesRepositoryInterface {

    public void insertSale(int userId, int carId, Date purchaseDate, double price) throws SQLException ;

}
