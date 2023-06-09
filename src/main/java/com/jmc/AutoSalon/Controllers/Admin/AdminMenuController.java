package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Views.AdminMenuOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.PropertySheet;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;

public class AdminMenuController implements Initializable {

    public static Locale language;
    public static boolean language_change;

    @FXML
    private Button create_client_btn;

    @FXML
    private Button clients_btn;

    @FXML
    private Button insert_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private Button logout_btn;



    @FXML
    private Button dashboard;



    private final KeyCombination closeAccelerator = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);

    @FXML
    public void logout(ActionEvent e){
        Model.getInstance().getViewFactory().closeWindow();
        Model.getInstance().getViewFactory().showLoginWindow();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }


    private void addListeners(){
        create_client_btn.setOnAction(event -> this.onCreateClient());
        clients_btn.setOnAction(event -> this.onClients());
        insert_btn.setOnAction(event -> this.onInsert());
        dashboard.setOnAction(event -> this.onDashboard());

    }


    private void onDashboard(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.DASHBOARD);
    }

    private void onInsert(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.INSERT);
    }
    private void onCreateClient(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_CLIENTS);
    }

    private void onClients(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CLIENTS);
    }
    @FXML
    private void closeWindow() {
        Platform.exit();
    }
    @FXML
    private void handleAccelerator(KeyEvent event) {
        if (closeAccelerator.match(event)) {
            Platform.exit();
        }
    }
}

