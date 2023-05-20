//package com.jmc.AutoSalon.Controllers.Client;
//
//import com.jmc.AutoSalon.Models.Cars;
//import com.jmc.AutoSalon.Models.Model;
//import com.jmc.AutoSalon.Models.User;
//import com.jmc.AutoSalon.Repository.RepositorySales;
//import com.jmc.AutoSalon.Repository.RepositoryUser;
//import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
//import com.jmc.AutoSalon.Services.userService;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import org.w3c.dom.Text;
//
//import java.io.IOException;
//import java.net.URL;
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Locale;
//import java.util.Optional;
//import java.util.ResourceBundle;
//
//public class HelpController implements Initializable {
//    private UserServiceInterface userService;
//    public HelpController(){
//        this.userService = new userService();
//    }
//
//    @FXML
//    private ScrollPane scrollpane;
//    @FXML
//    private AnchorPane help;
//
//    @FXML
//    private Button shqipBtn;
//
//    @FXML
//    private Button englishBtn;
//
//
//
//    @FXML
//    private Label tekstihyrje;
//
//    @FXML
//    private Text tekstidyte;
//
//    @FXML
//    private Text tekstitrete;
//
//    @FXML
//    private TextField dashboard;
//
//    @FXML
//    private Text tekstidashboard;
//
//    @FXML
//    private Text teksticostumize;
//
//    @FXML
//    private ImageView costumize_helpic;
//
//    @FXML
//    private ImageView foto_dashboard;
//
//    @FXML
//    private TextField costumize_help;
//
//
//
//    @FXML
//    private TextField cars_help;
//
//    @FXML
//    private Text teksticars;
//
//    @FXML
//    private ImageView carsfotohelp;
//
//    @FXML
//    private TextField payment_help;
//
//    @FXML
//    private Text tekstipaymentform;
//
//    @FXML
//    private ImageView payment_foto;
//
//    @FXML
//    private Text tekstikontakt;
//
//    @FXML
//    private ImageView email;
//
//    @FXML
//    private ImageView call;
//
//    @FXML
//    private Text tekstipaymentform11;
//
//    @FXML
//    private Text number;
//    @FXML
//    private void handleShqipBtn(){
//        Locale.setDefault(new Locale("sq","AL"));
//        this.translate();
//    }
//    @FXML
//    private void handleEnglishBtn(){
//        Locale.setDefault(new Locale("en"));
//        this.translate();
//    }
//
//    private void translate(){
//        Locale currentLocale = Locale.getDefault();
//        ResourceBundle translate = ResourceBundle.getBundle("Translations.content",currentLocale);
//        this.setTranslations(translate);
//    }
//
//    private void setTranslations(ResourceBundle translate){
//        tekstidashboard.setText(translate.getString("tekstidashboard"));
//        teksticars.setText(translate.getString("teksticars"));
//        teksticostumize.setText(translate.getString("teksticostumize"));
//        tekstipaymentform.setText(translate.getString("tekstipaymentform"));
//        tekstihyrje.setText(translate.getString("tekstihyrje"));
//        tekstidyte.setText(translate.getString("tekstidyte"));
//        tekstitrete.setText(translate.getString("tekstitrete"));
//        tekstikontakt.setText(translate.getString("tekstikontakt"));
//
//    }
//
//
//}
//
