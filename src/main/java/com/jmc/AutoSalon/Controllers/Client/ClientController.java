package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    private BorderPane client_parent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldValue, newValue) -> {
            switch (newValue){
                case CARS -> client_parent.setCenter(Model.getInstance().getViewFactory().getCarsView());
                case CUSTOMIZE -> client_parent.setCenter(Model.getInstance().getViewFactory().getCustomizeView());
                default -> client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        } );
    }
}
