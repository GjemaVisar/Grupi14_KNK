package com.jmc.AutoSalon.Controllers;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Repository.Interfaces.UserRepositoryInterface;
import com.jmc.AutoSalon.Repository.RepositoryUser;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.userService;
import com.jmc.AutoSalon.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private UserServiceInterface userService;

    public LoginController(){
        this.userService = new userService();
    }

    @FXML
    private ChoiceBox<AccountType> acc_select;

    @FXML
    private TextField username_lbl;

    @FXML
    private PasswordField password_lbl;
    @FXML
    private Button login_btn;

    @FXML
    private Label login_lbl;

    @FXML
    private Label newHere_lbl;

    @FXML
    private Button register_btn;

    @FXML
    private Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_select.setItems(FXCollections.observableArrayList(AccountType.CLIENT,AccountType.ADMIN));
        acc_select.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_select.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_select.getValue()));
       //login_btn.setOnAction(event -> onLogin());
    }
    public void switchToRegister(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/Register.fxml")));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void login(ActionEvent e){
        String username = this.username_lbl.getText();
        String password = this.password_lbl.getText();
        try{
            User user =this.userService.login(username,password);
            if(user == null){
                System.out.println("Username or password not correct");
                return;
            }
            this.onLogin();
        } catch (SQLException ex) {
            System.out.println("Mistake during logging in!");
        }


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

