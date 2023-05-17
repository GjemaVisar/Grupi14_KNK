package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.userService;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class dashboarddController implements Initializable {
    public TitledPane tabelaBlerjet;
    private UserServiceInterface userService;
    @FXML
    public Label username_lbl;
    public TableView<Cars> tabela_view;
    public TableColumn<Cars, String> name_column;
    public TableColumn<Cars, String> model_column;
    public TableColumn<Cars, String> type_column;
    public TableColumn<Cars, Integer> year_column;
    public TableColumn<Cars, Double> price_column;
    public User user;
    public dashboarddController(){
        this.userService = new userService();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateCarsTable();
            bindData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void bindData() throws SQLException {
        username_lbl.textProperty().bind(Bindings.concat("Hi, ").concat(this.userService.get_username()));
    }

    private void populateCarsTable() throws SQLException {
        List<Cars> carsList = this.userService.your_car();

        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        model_column.setCellValueFactory(new PropertyValueFactory<>("model"));
        type_column.setCellValueFactory(new PropertyValueFactory<>("type"));
        year_column.setCellValueFactory(new PropertyValueFactory<>("yearMade"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("price"));

        tabela_view.getItems().addAll(carsList);
    }

}
