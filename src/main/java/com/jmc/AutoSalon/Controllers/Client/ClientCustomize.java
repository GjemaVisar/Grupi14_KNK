package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClientCustomize implements Initializable {

    public ImageView carImg;
    public TextArea descBox;
    public Pagination mainPage;
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
    private TableView<Cars> tabelaStock;

    @FXML
    private TableColumn<Cars, Integer> numriSerikColumn;

    @FXML
    private TableColumn<Cars, String> emriColumn;

    @FXML
    private TableColumn<Cars, String> modeliColumn;

    @FXML
    private TableColumn<Cars, String> ngjyraColumn;

    @FXML
    private TableColumn<Cars, Integer> vitiColumn;

    @FXML
    private TableColumn<Cars, Double> cmimiColumn;

    @FXML
    private TableColumn<Cars, Double> maksimumiColumn;

    @FXML
    private TableColumn<Cars, Integer> tipiColumn;

    @FXML
    private TableColumn<Cars, Date> shtuarColumn;

    @FXML
    private TableColumn<Cars, Date> perditesuarColumn;
    ObservableList<Cars>carsTable = FXCollections.observableArrayList();

    private final int rowsPerPage = 8;

    private Node createPage(int pageIndex) {
        mainPage.setPageCount(carsTable.size() / rowsPerPage + 1);
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, carsTable.size());
        tabelaStock.setItems(FXCollections.observableArrayList(carsTable.subList(fromIndex, toIndex)));
        return tabelaStock;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llojiMakines.getItems().addAll("Audi", "BMW", "MercedesBenz");
        modeliMakines.getItems().addAll("SUV", "LUXURY", "SEDAN");
        ngjyrat.getItems().addAll("White", "Black", "Blue", "Grey");
        numriSerikColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Integer>("serial"));
        emriColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, String>("name"));
        modeliColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, String>("model"));
        ngjyraColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, String>("color"));
        vitiColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Integer>("yearMade"));
        cmimiColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Double>("price"));
        maksimumiColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Double>("maxSpeed"));
        tipiColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Integer>("type"));
        shtuarColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Date>("insertedOn"));
        perditesuarColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Date>("updatedOn"));


    }

    @FXML
    private void handleDergoButton(ActionEvent event) {
        if(!carsTable.isEmpty()) { carsTable.clear(); }
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
            String sql = "SELECT * FROM cars WHERE c_name=? AND car_model=? AND color=? AND year_c=? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, selectedCarName);
            statement.setString(2, selectedModel);
            statement.setString(3, selectedColor);
            statement.setInt(4, selectedYear);
            ResultSet result = statement.executeQuery();

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
                Date carInsertedOn = result.getDate("inserted_on");
                Date carUpdatedOn = result.getDate("updated_on");


                Cars car = new Cars(carId, carName, carModel, carType, carPrice, carColor, carMaxSpeed, carYear, carImage, carInsertedOn, carUpdatedOn);
                carsTable.add(car);
                System.out.println(car);
            }
            conn.close();
            mainPage.setPageFactory(this::createPage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setOnMousePressed(MouseEvent event) throws IOException {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
            Node node = ((Node) event.getTarget()).getParent();
            TableRow<Cars> row;
            if (node instanceof TableRow) {
                row = (TableRow) node;
            } else {
                row = (TableRow) node.getParent();
            }

            Cars car = row.getItem();
            if(car != null){
                String model = car.getModel();
                if(model == "SEDAN") {
                    model = "Sedan";
                } else if (model == "SUV") {
                    model = "suv";
                }
                System.out.println(car + " " + car.getCarImage());
                carImg.setImage(new Image((getClass().getResource("/Images/" + model + "/" + car.getCarImage())).toString()));
                descBox.setText(car.toString());
            }


        }
    }

}
