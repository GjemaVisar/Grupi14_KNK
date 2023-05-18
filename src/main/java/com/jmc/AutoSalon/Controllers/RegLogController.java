package com.jmc.AutoSalon.Controllers;

import com.jmc.AutoSalon.Models.Model;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.AnnotatedCheckAction;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
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
    public static boolean current_language;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField passTxt;

    @FXML
    private PasswordField cpassTxt;

    @FXML
    private Label register_lbl;

    @FXML
    private Button signupBtn;

    @FXML
    private Label have_acc;

    @FXML
    private Button login;

    @FXML
    private Pane fullStage;

    @FXML
    public void signup(ActionEvent e) throws IOException, SQLException {
        String username = this.usernameTxt.getText();
        String email = this.emailTxt.getText();
        String password = this.passTxt.getText();
        String cpassword = this.cpassTxt.getText();
        boolean status = UserAuthService.register(username, email, password, cpassword);
        if (status) {
            try {
                User user = this.userService.signup(username, password);
                if (user == null) {
                    return;
                }
                this.usernameTxt.setText("");
                this.emailTxt.setText("");
                this.passTxt.setText("");
                this.cpassTxt.setText("");
                System.out.println("User inserted successfully");
            } catch (SQLException se) {
                System.out.println("Could not create user: " + se.getMessage());
            }
        } else {
            Model.getInstance().getViewFactory().showAlert("Cannot create user!",UserAuthService.signup_error);
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
        this.translate();
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

         this.fullStage.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.L) {
                this.language_switch(event);
            }

        });
        return ;



    }


    private void translate(){
        Locale currentLocale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content",currentLocale);
        this.set_translations(translate);

    }

    @FXML
    public void language_switch(KeyEvent ke){
        ActionEvent e = new ActionEvent();
        if(RegLogController.current_language){
            this.handleShqipBtn(e);
        }else{
            this.handleEnglishBtn(e);
        }
    }

    private void set_translations(ResourceBundle translate){
        usernameTxt.setPromptText(translate.getString("username_lbl"));
        passTxt.setPromptText(translate.getString("password_lbl"));
        cpassTxt.setPromptText(translate.getString("confirm_password"));
        emailTxt.setPromptText(translate.getString("email_placeholder"));
        register_lbl.setText(translate.getString("register_lbl"));
        signupBtn.setText(translate.getString("register_lbl"));
        have_acc.setText(translate.getString("have_acc"));
        login.setText(translate.getString("login_btn"));
    }

    @FXML
    private void handleShqipBtn(ActionEvent event) {
        Locale.setDefault(new Locale("sq","AL"));
        RegLogController.current_language = false;
        this.translate();
    }
    @FXML
    private void handleEnglishBtn(ActionEvent event) {
        Locale.setDefault(new Locale("en"));
        RegLogController.current_language = true;
        this.translate();
    }
}
