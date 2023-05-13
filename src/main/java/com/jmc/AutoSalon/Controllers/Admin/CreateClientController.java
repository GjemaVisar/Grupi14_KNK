package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.UserAuthService;
import com.jmc.AutoSalon.Services.userService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    private UserServiceInterface userService;

    public TextField username_fld;
    public TextField email_fld;
    public PasswordField password_fld;
    public PasswordField cfpassword_fld;
    public Button createClient_btn;
    public Label error_lbl;
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
                    User user = this.userService.createClient(username, password);
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

//    private void emptyFields(){
//        username_fld.setText("");
//        email_fld.setText("");
//        password_fld.setText("");
//        cfpassword_fld.setText("");
//
//    }
}
