package com.jmc.AutoSalon.Controllers;

import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.PasswordHasher;
import com.jmc.AutoSalon.Services.UserAuthService;
import com.jmc.AutoSalon.Services.userService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegLogController implements Initializable {

    private UserServiceInterface userService;

    public RegLogController(){
        this.userService = new userService();
    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField passTxt;

    @FXML
    private PasswordField cpassTxt;

    @FXML
    public void signup(ActionEvent e) throws IOException, SQLException {
        String username = this.usernameTxt.getText();
        String email = this.emailTxt.getText();
        String password = this.passTxt.getText();
        String cpassword = this.cpassTxt.getText();
        boolean status = UserAuthService.register(username,email,password,cpassword);
        if(status) {
            try {
                User user = this.userService.signup(username, password);
                if (user == null) {
                    return;
                }
                this.usernameTxt.setText("");
                this.emailTxt.setText("");
                this.passTxt.setText("");
                this.cpassTxt.setText("");
                System.out.println("User inserted successfuly");
            } catch (SQLException se) {
                System.out.println("Could not create user: " + se.getMessage());
            }
        }
        else{
            System.out.println("Cannot create user!");
        }
    }

    public void switchToLogin(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/Login.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        this.cpassTxt.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                ActionEvent ae = new ActionEvent();
                try {
                    this.signup(ae);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return;
    }
}
