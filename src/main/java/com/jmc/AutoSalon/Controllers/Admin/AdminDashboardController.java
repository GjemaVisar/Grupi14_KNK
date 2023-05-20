package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Repository.Interfaces.SalesRepositoryInterface;
import com.jmc.AutoSalon.Repository.RepositorySales;
import com.jmc.AutoSalon.Services.ConnectionUtil;
import com.jmc.AutoSalon.Services.Interfaces.SalesServiceInterface;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.salesService;
import com.jmc.AutoSalon.Services.userService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    public Label username_lbl;
    public Label active_clients;
    public Label cars_available;
    @FXML
    private LineChart<String, Number> reservationsChart;

    @FXML
    private AnchorPane main_pane;

    private SalesServiceInterface sales_service;
    private UserServiceInterface userService;
    private PieChart pie_chart;


    public AdminDashboardController(){
        this.sales_service = new salesService();
        this.userService = new userService();
    }

//    public LineChart<String, Integer> getReservationsChart(){
//        return this.reservationsChart;
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeChart();
        add_pie();

        try {
            bindData();
            displayActiveClients();
            displayNumberOfCars();
            displayReservationsPerDayOfWeek();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Day of Week");
        yAxis.setLabel("Number of Reservations");
        reservationsChart = new LineChart<>(xAxis, yAxis);
        main_pane.getChildren().add(reservationsChart);
    }

    private void add_pie(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        pie_chart = new PieChart(pieChartData); // Initialize the pie_chart field
        try {
            this.sales_service.create_pie_chart(pieChartData);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for(PieChart.Data d: pieChartData){
            System.out.println(d);
        }
        this.pie_chart.setData(pieChartData);
    }

    private void bindData() throws SQLException {
        username_lbl.textProperty().bind(Bindings.concat("Hi, ").concat(this.userService.get_username()));
    }

    public void displayActiveClients(){
        String sql = "SELECT COUNT(id) FROM users ";
        int countClients = 0;

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                countClients  = resultSet.getInt("COUNT(id)");
            }
            active_clients.setText(String.valueOf(countClients));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void displayNumberOfCars(){
        String sql = "SELECT COUNT(numri_serik) FROM cars";
        int countCars = 0;

        try(Connection conn = ConnectionUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                countCars = resultSet.getInt("COUNT(numri_serik)");
            }
            cars_available.setText(String.valueOf(countCars));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void displayReservationsPerDayOfWeek() throws SQLException {
        String sql = "SELECT COUNT(*) AS Total, DAYNAME(data_rezervimit) AS DayOfWeek " +
                "FROM testing_appointment " +
                "WHERE data_rezervimit >= ? " +
                "GROUP BY WEEKDAY(data_rezervimit)";

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            LocalDate lastWeekMonday = LocalDate.now().minusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            statement.setString(1, lastWeekMonday.format(DateTimeFormatter.ISO_DATE));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String dayOfWeek = resultSet.getString("DayOfWeek");
                int total = resultSet.getInt("Total");

                // Adjust the day of week labels to start from Monday
                int dayIndex = (DayOfWeek.valueOf(dayOfWeek.toUpperCase()).getValue() + 5) % 7;
                String adjustedDayOfWeek = DayOfWeek.of(dayIndex + 1).toString();

                series.getData().add(new XYChart.Data<>(adjustedDayOfWeek, total));
            }

            reservationsChart.getData().add(series);
        }
    }
}
