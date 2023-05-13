package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Views.AccountType;
import com.jmc.AutoSalon.Views.ClientMenuOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {


    @FXML
    private Button dashboard_btn;
    @FXML
    private Button cars_btn;

    @FXML
    private Button costumize_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private Button logout_btn;
    @FXML
    private Button report_btn;

    @FXML
    public void logout(ActionEvent e){
        Model.getInstance().getViewFactory().closeClientWindow();
        Model.getInstance().getViewFactory().showLoginWindow();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        addListener();
    }
    private void addListener(){
        dashboard_btn.setOnAction(event -> onDashboard());
        cars_btn.setOnAction(event -> onCars());
        costumize_btn.setOnAction(event -> onCustomize());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    private void onCars() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.CARS);
    }
    private void onCustomize(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.CUSTOMIZE);
    }
}
