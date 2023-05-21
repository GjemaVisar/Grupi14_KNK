package com.jmc.AutoSalon.Controllers;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Repository.Interfaces.UserRepositoryInterface;
import com.jmc.AutoSalon.Repository.RepositoryUser;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.UserAuthService;
import com.jmc.AutoSalon.Services.userService;
import com.jmc.AutoSalon.Views.AccountType;
import com.jmc.AutoSalon.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private UserServiceInterface userService;

    public static boolean current_language;

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
    private  Label trgBtn;

    @FXML
    private Label login_lbl;
    @FXML
    private Label newHere_lbl;
    @FXML
    private Button register_btn;
    @FXML
    private Label error_lbl;
    @FXML
    private AnchorPane fullStage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", locale);
        acc_select.setItems(FXCollections.observableArrayList(AccountType.CLIENT,AccountType.ADMIN));
        acc_select.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_select.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_select.getValue()));
        this.username_lbl.setOnKeyPressed(event->{
            if(event.getCode() == KeyCode.ENTER){
                ActionEvent ae = new ActionEvent();
                this.login(ae);
            }
        });

        this.password_lbl.setOnKeyPressed(event->{
                    if(event.getCode() == KeyCode.ENTER){
                        ActionEvent ae = new ActionEvent();
                        this.login(ae);
                    }

                }
        );

        this.fullStage.setOnKeyPressed(event->{
            if(event.isControlDown() && event.getCode()==KeyCode.L){
                this.language_switch(event);
            }
            else if(event.getCode() == KeyCode.ESCAPE){
                Model.getInstance().getViewFactory().terminate_app_esc();
            }
        });
    }
    public void switchToRegister(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/Register.fxml")));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void translate(){
        Locale currentLocale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content",currentLocale);
        username_lbl.setPromptText(translate.getString("username_lbl"));
        password_lbl.setPromptText(translate.getString("password_lbl"));
        login_btn.setText(translate.getString("login_btn"));
        login_lbl.setText(translate.getString("login_lbl"));
        newHere_lbl.setText(translate.getString("newHere_lbl"));
        register_btn.setText(translate.getString("register_btn"));
        trgBtn.setText(translate.getString("acc_select"));

    }

    @FXML
    public void login(ActionEvent e){
        String username = this.username_lbl.getText();
        String password = this.password_lbl.getText();
        try{
            User user =this.userService.login(username,password);
            if(user == null){
                Model.getInstance().getViewFactory().showAlert("Login mistake","Username, password, or role do not match");
                return;
            }
            this.onLogin();
        } catch (SQLException ex) {
            System.out.println("Mistake during logging in!");
        }


    }
    @FXML
    public void language_switch(KeyEvent event){
            ActionEvent e = new ActionEvent();
            if(LoginController.current_language){
                this.handleShqipBtn(e);
            }else{
                this.handleEnglishBtn(e);
            }
        }



    private void onLogin(){
        //per me e mbyll stage
        Model.getInstance().getViewFactory().closeWindow();
        // per me e hap pas client.fxml, pas login.fxml
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT){
            Model.getInstance().getViewFactory().showClientWindow();
        }else{
            Model.getInstance().getViewFactory().showAdminWindow();
        }
    }
    @FXML
    private void handleShqipBtn(ActionEvent event) {
        Locale.setDefault(new Locale("sq","AL"));
        LoginController.current_language = false;
        this.translate();
    }
    @FXML
    private void handleEnglishBtn(ActionEvent event) {
       Locale.setDefault(new Locale("en"));
       LoginController.current_language = true;
       this.translate();
    }

}

