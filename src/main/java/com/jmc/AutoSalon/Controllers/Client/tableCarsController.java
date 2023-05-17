package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Models.dto.carFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class tableCarsController implements Initializable {
    @FXML
    public TableView<Cars> mainTable;
    @FXML
    public TextField emriTxt;
    @FXML
    public TextField modelTxt;
    @FXML
    public Pagination mainPage;
    @FXML
    public TextField typeTxt;
    @FXML
    public TextField priceTxt;
    @FXML
    public TextField colorTxt;
    @FXML
    public TextField speedTxt;
    @FXML
    public TextField yearTxt;
    @FXML
    public Button kerkoBtn;
    private final int rowsPerPage = 6;
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
    private Node createPage(int pageIndex) {
        mainPage.setPageCount(carsTable.size() / rowsPerPage + 1);
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, carsTable.size());
        mainTable.setItems(FXCollections.observableArrayList(carsTable.subList(fromIndex, toIndex)));
        return mainTable;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    public void searchHandler(ActionEvent actionEvent) {
        /*
         public TextField emriTxt;
    public TextField modelTxt;
    public Pagination mainPage;
    public TextField typeTxt;
    public TextField priceTxt;
    public TextField colorTxt;
    public TextField speedTxt;
    public TextField yearTxt;*/
        if(emriTxt.getText() == null && modelTxt.getText() == null && typeTxt.getText() == null && priceTxt.getText() == null
                && colorTxt.getText() == null && speedTxt.getText() == null && yearTxt.getText() == null) {
            Model.getInstance().getViewFactory().showAlert("Error","Fill atleast 1 field.");
        } else {
            try {
                if(!carsTable.isEmpty()) { carsTable.clear(); }
                carFilter filter = new carFilter();
                filter.setName(emriTxt.getText());
                filter.setModel(modelTxt.getText());
                filter.setType(typeTxt.getText());
                if(!priceTxt.getText().isEmpty()) { filter.setPrice(Double.parseDouble(priceTxt.getText())); }
                filter.setColor(colorTxt.getText());
                if(!speedTxt.getText().isEmpty()) { filter.setMaxSpeed(Double.parseDouble(speedTxt.getText())); }
                if(!yearTxt.getText().isEmpty()) { filter.setYearMade(Integer.parseInt(yearTxt.getText())); }
                ResultSet result = filter.sqlQueryCars();

                while (result.next()) {
                    int carId = result.getInt("numri_serik");
                    String carName = result.getString("c_name");
                    String carModel = result.getString("car_model");
                    String carType = result.getString("car_type");
                    double carPrice = result.getDouble("price_c");
                    String carColor = result.getString("color");
                    double carMaxSpeed = result.getDouble("max_speed");
                    int carYear = result.getInt("year_c");
                    int quantity = result.getInt("quantity");
                    String carImage = result.getString("car_image");
                    Date carInsertedOn = result.getDate("inserted_on");
                    Date carUpdatedOn = result.getDate("updated_on");


                    Cars car = new Cars(carId, carName, carModel, carType, carPrice, carColor, carMaxSpeed, carYear,quantity, carImage, carInsertedOn, carUpdatedOn);
                    carsTable.add(car);
                    System.out.println(car);
                }
                carFilter.closeConnection();
                mainPage.setPageFactory(this::createPage);
            } catch (SQLException e) {
                Model.getInstance().getViewFactory().showAlert("Error",e.getMessage());
            }
        }
    }
}
