package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.userService;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class dashboarddController implements Initializable {
    private UserServiceInterface userService;

    @FXML
    public Label username_lbl;
    public TitledPane tabelaBlerjet;
    public TableColumn name_column;
    public TableColumn model_column;
    public TableColumn year_column;
    public TableColumn price_column;
    public User user;
    public dashboarddController(){
        this.userService = new userService();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            bindData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void bindData() throws SQLException {
        username_lbl.textProperty().bind(Bindings.concat("Hi, ").concat(this.userService.get_username()));
    }
}
