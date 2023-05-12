package com.jmc.AutoSalon.Controllers.Client;

import Models.CarModelClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClientCustomize implements Initializable {

    @FXML
    private AnchorPane costumizePane;

    @FXML
    private ComboBox<String> llojiMakines;

    @FXML
    private ComboBox<String> modeliMakines;

    @FXML
    private ColorPicker ngjyrat;

    @FXML
    private CheckBox viti20;

    @FXML
    private CheckBox viti21;

    @FXML
    private CheckBox viti22;

    @FXML
    private CheckBox viti23;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llojiMakines.getItems().addAll("Audi", "BMW", "MercedesBenz");
        modeliMakines.getItems().addAll("SUV", "LUXURY", "SEDAN");
    }

    @FXML
    private void handleDergoButton(ActionEvent event) {
        String selectedCarName = llojiMakines.getValue();
        String selectedModel = modeliMakines.getValue();
        String selectedColor = ngjyrat.getValue().toString();
        int selectedYear = 0;
        if (viti20.isSelected()) selectedYear = 2020;
        else if (viti21.isSelected()) selectedYear = 2021;
        else if (viti22.isSelected()) selectedYear = 2022;
        else if (viti23.isSelected()) selectedYear = 2023;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3333/knk2023", "root", "root");
            String sql = "SELECT * FROM stock WHERE c_name=? AND car_model=? AND color=? AND year_c=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, selectedCarName);
            statement.setString(2, selectedModel);
            statement.setString(3, selectedColor);
            statement.setInt(4, selectedYear);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int carId = result.getInt("numri_serik");
                String carName = result.getString("c_name");
                String carModel = result.getString("car_model");
                String carType = result.getString("car_type");
                double carPrice = result.getDouble("price_c");
                String carColor = result.getString("color");
                double carMaxSpeed = result.getDouble("max_speed");
                int carYear = result.getInt("year_c");
                String carImage = result.getString("car_image");
                LocalDate carInsertedOn = result.getDate("inserted_on").toLocalDate();
                LocalDate carUpdatedOn = result.getDate("updated_on").toLocalDate();

                CarModelClass selectedCar = new CarModelClass(carId, carName, carModel, carType, carPrice, carColor, carMaxSpeed, carYear, carImage, carInsertedOn, carUpdatedOn);
                System.out.println("Selected car: " + selectedCar);
            } else {
                System.out.println("No matching car found in stock.");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
