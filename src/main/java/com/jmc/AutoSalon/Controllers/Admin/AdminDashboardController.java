package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Repository.Interfaces.SalesRepositoryInterface;
import com.jmc.AutoSalon.Repository.RepositorySales;
import com.jmc.AutoSalon.Services.ConnectionUtil;
import com.jmc.AutoSalon.Services.Interfaces.SalesServiceInterface;
import com.jmc.AutoSalon.Services.Interfaces.TestDriveServiceInterface;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.TestDriveService;
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
    private TestDriveServiceInterface testDriveService;

    @FXML
    private Label username_lbl;
    @FXML
    private Label active_clients;
    @FXML
    private Label cars_available;
    @FXML
    private LineChart<String, Number> reservationsChart;
    @FXML
    private XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();

    @FXML
    private AnchorPane main_pane;

    private SalesServiceInterface sales_service;
    private UserServiceInterface userService;

    @FXML
    private PieChart pie_chart;

    @FXML
    private AnchorPane line_pane;

    public AdminDashboardController(){
        this.sales_service = new salesService();
        this.userService = new userService();
        this.testDriveService = new TestDriveService();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_pie();
        try {
            bindData();
            displayActiveClients();
            displayNumberOfCars();
            initializeChart();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeChart() throws SQLException {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Day of Week");
        yAxis.setLabel("Number of Reservations");
        this.reservationsChart = new LineChart<String,Number>(xAxis, yAxis);

        this.testDriveService.fill_xy_chart(this.series);
        this.reservationsChart.getData().add(this.series);


        this.line_pane.getChildren().add(reservationsChart);

    }

    private void add_pie(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        //pie_chart = new PieChart(pieChartData); // Initialize the pie_chart field
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


}
