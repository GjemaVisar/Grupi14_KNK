package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.PropertySheet;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private BorderPane admin_parent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldValue, newValue) ->{
            switch (newValue){
                case CLIENTS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getClientsView());
                case INSERT -> admin_parent.setCenter(Model.getInstance().getViewFactory().getInsertView());
                case CREATE_CLIENTS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateClientView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminDashboardView());
            }
        } );

    }
}
