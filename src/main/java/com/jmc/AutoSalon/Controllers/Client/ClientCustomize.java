package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.CarModelClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private ComboBox<String> ngjyrat;

    @FXML
    private CheckBox viti20;

    @FXML
    private CheckBox viti21;

    @FXML
    private CheckBox viti22;

    @FXML
    private CheckBox viti23;
    @FXML
    private TableView<CarModelClass> tabelaStock;

    @FXML
    private TableColumn<CarModelClass, Integer> numriSerikColumn;

    @FXML
    private TableColumn<CarModelClass, String> emriColumn;

    @FXML
    private TableColumn<CarModelClass, String> modeliColumn;

    @FXML
    private TableColumn<CarModelClass, String> ngjyraColumn;

    @FXML
    private TableColumn<CarModelClass, Integer> vitiColumn;

    @FXML
    private TableColumn<CarModelClass, Double> cmimiColumn;

    @FXML
    private TableColumn<CarModelClass, Double> maksimumiColumn;

    @FXML
    private TableColumn<CarModelClass, String> tipiColumn;

    @FXML
    private TableColumn<CarModelClass, LocalDate> shtuarColumn;

    @FXML
    private TableColumn<CarModelClass, LocalDate> perditesuarColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llojiMakines.getItems().addAll("Audi", "BMW", "MercedesBenz");
        modeliMakines.getItems().addAll("SUV", "LUXURY", "SEDAN");
        ngjyrat.getItems().addAll("White", "Black", "Blue", "Grey");
        numriSerikColumn.setCellValueFactory(
                new PropertyValueFactory<CarModelClass, Integer>("id"));
        emriColumn.setCellValueFactory(
                new PropertyValueFactory<CarModelClass, String>("name"));
        modeliColumn.setCellValueFactory(
                new PropertyValueFactory<CarModelClass, String>("model"));
        ngjyraColumn.setCellValueFactory(
                new PropertyValueFactory<CarModelClass, String>("carType"));
        vitiColumn.setCellValueFactory(
                new PropertyValueFactory<CarModelClass, Integer>("price"));
        cmimiColumn.setCellValueFactory(
                new PropertyValueFactory<CarModelClass, Double>("color"));
        maksimumiColumn.setCellValueFactory(
                new PropertyValueFactory<CarModelClass, Double>("maxSpeed"));
        tipiColumn.setCellValueFactory(
                new PropertyValueFactory<CarModelClass, String>("year"));
        shtuarColumn.setCellValueFactory(
                new PropertyValueFactory<CarModelClass, LocalDate>("insertedOn"));
        perditesuarColumn.setCellValueFactory(
                new PropertyValueFactory<CarModelClass, LocalDate>("updatedOn"));


    }

    @FXML
    private void handleDergoButton(ActionEvent event) {
        String selectedCarName = llojiMakines.getValue();
        String selectedModel = modeliMakines.getValue();
        String selectedColor = ngjyrat.getValue();
        int selectedYear = 0;
        if (viti20.isSelected()) selectedYear = 2020;
        else if (viti21.isSelected()) selectedYear = 2021;
        else if (viti22.isSelected()) selectedYear = 2022;
        else if (viti23.isSelected()) selectedYear = 2023;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3333/knk2023", "root", "root");
            String sql = "SELECT * FROM stock WHERE c_name=? AND car_model=? AND color=? AND year_c=? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, selectedCarName);
            statement.setString(2, selectedModel);
            statement.setString(3, selectedColor);
            statement.setInt(4, selectedYear);
            ResultSet result = statement.executeQuery();

            ObservableList<CarModelClass> carList = FXCollections.observableArrayList();
            while (result.next()) {
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

                CarModelClass car = new CarModelClass(carId, carName, carModel, carType, carPrice, carColor, carMaxSpeed, carYear, carImage, carInsertedOn, carUpdatedOn);
                carList.add(car);
                tabelaStock.getItems().add(car);
                System.out.println(car);
            }
            tabelaStock.setItems(carList);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setOnMousePressed(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            Node node = ((Node) event.getTarget()).getParent();
            TableRow<CarModelClass> row;
            if (node instanceof TableRow) {
                row = (TableRow) node;
            } else {
                row = (TableRow) node.getParent();
            }

            CarModelClass car = row.getItem();
            System.out.println(car.getCarImage());
        }
    }

}
