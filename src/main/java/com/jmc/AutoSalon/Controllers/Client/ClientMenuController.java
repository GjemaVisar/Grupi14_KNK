package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Views.AccountType;
import com.jmc.AutoSalon.Views.ClientMenuOptions;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {

    public Text fiekLbl;
    @FXML
    private Button dashboard_btn;
    @FXML
    private Button cars_btn;
    @FXML
    private Button costumize_btn;

    private final KeyCombination closeAccelerator = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);

    @FXML
    public void logout(ActionEvent e) {
        Model.getInstance().getViewFactory().closeWindow();
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener() {
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

    private void onCustomize() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.CUSTOMIZE);
    }

    @FXML
    private void closeWindow2() {
        Stage stage = (Stage) fiekLbl.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAccelerator(KeyEvent event) {
        if (closeAccelerator.match(event)) {
            Platform.exit();
        }
    }
    @FXML
    public void aboutUs(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/help.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
