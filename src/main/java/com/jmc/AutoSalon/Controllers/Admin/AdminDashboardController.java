package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Repository.Interfaces.SalesRepositoryInterface;
import com.jmc.AutoSalon.Repository.RepositorySales;
import com.jmc.AutoSalon.Services.Interfaces.SalesServiceInterface;
import com.jmc.AutoSalon.Services.salesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private PieChart pie_chart;

    @FXML
    private AnchorPane main_pane;

    private SalesServiceInterface sales_service;

    public AdminDashboardController(){
        this.sales_service = new salesService();
    }

    public PieChart get_pie(){
        return this.pie_chart;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.add_pie();

    }

    private void add_pie(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
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
}
