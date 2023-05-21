package com.jmc.AutoSalon.Controllers;

import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.userService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelpController implements Initializable {
    private UserServiceInterface userService;
    public HelpController(){
        this.userService = new userService();
    }

    @FXML
    private ScrollPane scrollpane;
    @FXML
    private AnchorPane help;
    @FXML
    private Button shqipBtn;
    @FXML
    private Button englishBtn;
    @FXML
    private Label tekstihyrje;
    @FXML
    private Text tekstidyte;
    @FXML
    private Text tekstitrete;
    @FXML
    private TextField dashboard;
    @FXML
    private Text tekstidashboard;
    @FXML
    private Text teksticostumize;
    @FXML
    private ImageView costumize_helpic;
    @FXML
    private ImageView foto_dashboard;
    @FXML
    private TextField costumize_help;
    @FXML
    private TextField cars_help;
    @FXML
    private Text teksticars;
    @FXML
    private ImageView carsfotohelp;
    @FXML
    private TextField payment_help;
    @FXML
    private Text tekstipaymentform;
    @FXML
    private ImageView payment_foto;
    @FXML
    private Text tekstikontakt;
    @FXML
    private ImageView email;
    @FXML
    private ImageView call;
    @FXML
    private Text tekstipaymentform11;
    @FXML
    private Text number;
    @FXML
    private void handleShqipBtn(){
        Locale.setDefault(new Locale("sq","AL"));
        this.translate();
    }
    @FXML
    private void handleEnglishBtn(){
        Locale.setDefault(new Locale("en"));
        this.translate();
    }

    private void translate(){
        Locale currentLocale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content",currentLocale);
        this.setTranslations(translate);
    }

    private void setTranslations(ResourceBundle translate){
        tekstidashboard.setText(translate.getString("tekstidashboard"));
        teksticars.setText(translate.getString("teksticars"));
        teksticostumize.setText(translate.getString("teksticostumize"));
        tekstipaymentform.setText(translate.getString("tekstipaymentform"));
        tekstihyrje.setText(translate.getString("tekstihyrje"));
        tekstidyte.setText(translate.getString("tekstidyte"));
        tekstitrete.setText(translate.getString("tekstitrete"));
        tekstikontakt.setText(translate.getString("tekstikontakt"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", locale);
    }
}

