package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.UserAuthService;
import com.jmc.AutoSalon.Services.userService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.application.Platform;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    private UserServiceInterface userService;
    @FXML
    private TextField username_fld;

    @FXML
    private TextField email_fld;
    @FXML
    private PasswordField password_fld;

    @FXML
    private PasswordField cfpassword_fld;

    @FXML
    private Button createClient_btn;
    @FXML
    private Label error_lbl;

    @FXML
    private TextField admin_username;
    @FXML
    private TextField admin_email;
    @FXML
    private PasswordField admin_pass;
    @FXML
    private PasswordField admin_cpass;

    @FXML
    private Button shqipBtn;
    @FXML
    private Button englishBtn;



    public CreateClientController(){
        this.userService = new userService();
    }



    public void initialize(URL url, ResourceBundle resourceBundle) {
        createClient_btn.setOnAction(event -> createClient(event)); // Pass the event parameter to the method
    }

    private void createClient(ActionEvent event) {
        try {
            String username = username_fld.getText();
            String email = email_fld.getText();
            String password = password_fld.getText();
            String cfpassword = cfpassword_fld.getText();
            boolean status = UserAuthService.createClient(username, email, password, cfpassword);
            if (status) {
                try {
                    User user = this.userService.createClient(username, password,false);
                    if (user == null) {
                        return;
                    }
                    username_fld.setText("");
                    email_fld.setText("");
                    password_fld.setText("");
                    cfpassword_fld.setText("");
                    System.out.println("User inserted successfully");
                } catch (SQLException se) {
                    System.out.println("Could not create user: " + se.getMessage());
                }
            } else {
                System.out.println("Cannot create user!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exceptions appropriately
        }
    }

    @FXML
    public void create_admin(){
        try {
            String username = admin_username.getText();
            String email = admin_email.getText();
            String password = admin_pass.getText();
            String cfpassword = admin_cpass.getText();
            boolean status = UserAuthService.createClient(username, email, password, cfpassword);
            if (status) {
                try {
                    User user = this.userService.createClient(username, password,true);
                    if (user == null) {
                        return;
                    }
                    admin_username.setText("");
                    admin_email.setText("");
                    admin_pass.setText("");
                    admin_cpass.setText("");
                    System.out.println("Admin inserted successfully");
                } catch (SQLException se) {
                    System.out.println("Could not create admin: " + se.getMessage());
                }
            } else {
                System.out.println("Cannot create admin!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleShqipBtn(){

    }

    @FXML
    public void handleEnglishBtn(){

    }
}
