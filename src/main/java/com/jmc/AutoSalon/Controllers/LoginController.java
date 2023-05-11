package com.jmc.AutoSalon.Controllers;

import com.jmc.AutoSalon.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public TextField username_lbl;
    public PasswordField password_lbl;
    public Button login_btn;
    public Label login_lbl;
    public Label newHere_lbl;
    public Button register_btn;
    public Label error_lbl;

    private void onLogin(){
        Stage stage = (Stage) newHere_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_btn.setOnAction(event -> onLogin());
    }


}

