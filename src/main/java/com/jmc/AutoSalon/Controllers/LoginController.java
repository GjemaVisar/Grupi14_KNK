package com.jmc.AutoSalon.Controllers;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public ChoiceBox<AccountType> acc_select;
    public TextField username_lbl;
    public PasswordField password_lbl;
    public Button login_btn;
    public Label login_lbl;
    public Label newHere_lbl;
    public Button register_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_select.setItems(FXCollections.observableArrayList(AccountType.CLIENT,AccountType.ADMIN));
        acc_select.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_select.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_select.getValue()));
        login_btn.setOnAction(event -> onLogin());
    }
    private void onLogin(){
        //per me e mbyll stage
        Stage stage = (Stage) newHere_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        // per me e hap pas client.fxml, pas login.fxml
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT){
            Model.getInstance().getViewFactory().showClientWindow();
        }else{
            Model.getInstance().getViewFactory().showAdminWindow();
        }
    }


}

