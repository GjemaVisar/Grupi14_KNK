package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Views.AdminMenuOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.controlsfx.control.PropertySheet;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button create_client_btn;
    public Button clients_btn;
    public Button insert_btn;
    public Button profile_btn;


    public Button logout_btn;

    @FXML
    public void logout(ActionEvent e){
        Model.getInstance().getViewFactory().closeAdminWindow();
        Model.getInstance().getViewFactory().showLoginWindow();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
    private void addListeners(){
        create_client_btn.setOnAction(event -> onCreateClient());
        clients_btn.setOnAction(event -> onClients());
    }

    private void onCreateClient(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_CLIENTS);
    }

    private void onClients(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CLIENTS);
    }
}
