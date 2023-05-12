package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button cars_btn;
    public Button costumize_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;


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
