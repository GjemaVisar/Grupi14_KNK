package com.jmc.AutoSalon.Services.Interfaces;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.SQLException;

public interface SalesServiceInterface {
    void create_pie_chart(ObservableList<PieChart.Data> list) throws SQLException;
}
