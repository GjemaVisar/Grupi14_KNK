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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

    @FXML
    private Text create_new_client;

    @FXML
    private Text create_username;

    @FXML
    private Text create_email;

    @FXML
    private Text create_password;

    @FXML
    private Text create_cpass;


    @FXML
    private Text create_new_admin;

    @FXML
    private Text admin_emails;

    @FXML
    private Text create_username2;

    @FXML
    private Text create_password2;

    @FXML
    private Text create_cpass2;

    @FXML
    private Button create_btn;

    private final KeyCombination closeAccelerator = new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN);

    public CreateClientController(){
        this.userService = new userService();
    }



    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", locale);
        createClient_btn.setOnAction(event -> createClient(event)); // Pass the event parameter to the method

        this.cfpassword_fld.setOnKeyPressed(event->{
            ActionEvent e = new ActionEvent();
            if(event.getCode() == KeyCode.ENTER){
                this.createClient(e);
            }
        });
        this.admin_cpass.setOnKeyPressed(event->{
            ActionEvent e = new ActionEvent();
            if(event.getCode() == KeyCode.ENTER){
                this.create_admin(e);
            }
        });


    }
    @FXML
    public void createClient(ActionEvent event) {
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
                Model.getInstance().getViewFactory().showAlert("Cannot create Client!",UserAuthService.signup_error);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exceptions appropriately
        }
    }

    @FXML
    public void create_admin(ActionEvent e){
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
                Model.getInstance().getViewFactory().showAlert("Cannot create admin!",UserAuthService.signup_error);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private void translate(){
        Locale currentLocale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content",currentLocale);
        this.setTranslations(translate);
    }
    private void setTranslations(ResourceBundle translate){
        create_new_client.setText(translate.getString("create_new_client"));
        create_new_admin.setText(translate.getString("create_new_admin"));
        create_username.setText(translate.getString("create_username"));
        create_email.setText(translate.getString("create_email"));
        admin_emails.setText(translate.getString("admin_emails"));
        create_password.setText(translate.getString("create_password"));
        create_cpass2.setText(translate.getString("create_cpass"));
        create_username2.setText(translate.getString("create_username"));
        create_password2.setText(translate.getString("create_password"));
        create_cpass.setText(translate.getString("create_cpass"));
        create_btn.setText(translate.getString("create_btn"));
        createClient_btn.setText(translate.getString("createClient_btn"));

    }

    @FXML
    public void handleShqipBtn(){
        Locale.setDefault(new Locale("sq","AL"));
        this.translate();
    }

    @FXML
    public void handleEnglishBtn(){
        Locale.setDefault(new Locale("en"));
        this.translate();
    }
}
