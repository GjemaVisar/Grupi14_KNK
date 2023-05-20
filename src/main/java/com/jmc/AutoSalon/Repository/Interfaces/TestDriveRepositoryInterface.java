package com.jmc.AutoSalon.Repository.Interfaces;

import com.jmc.AutoSalon.Services.ConnectionUtil;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public interface TestDriveRepositoryInterface {
    void insertTestDrive(int user_id, int car_id, Date date_picked);
    boolean is_date_car_busy(int car_id,Date date_picked);
    public void displayReservationsPerDayOfWeek(XYChart.Series<String,Number> series) throws SQLException;
}
