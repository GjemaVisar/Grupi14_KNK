package com.jmc.AutoSalon.Services.Interfaces;

import javafx.scene.chart.XYChart;

import java.sql.Date;
import java.sql.SQLException;

public interface TestDriveServiceInterface  {
    void add_test_drive(int user, int car, Date date_picked);
    void fill_xy_chart(XYChart.Series<String,Number> series) throws SQLException;
}
