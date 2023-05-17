package com.jmc.AutoSalon.Controllers.Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private PieChart pieChart;

    @FXML
    private Slider percentageSlider;
    @FXML
    private Button ourSales;
    @FXML
    private Label titleLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<PieChart.Data>pieCharData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Audi", 30),
                        new PieChart.Data("BMW", 20),
                        new PieChart.Data("MercedesBenz", 15));

        pieChart.getData().addAll(pieCharData);

    }
    @FXML
    private void handleOurSales(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Client/Statistikat.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}