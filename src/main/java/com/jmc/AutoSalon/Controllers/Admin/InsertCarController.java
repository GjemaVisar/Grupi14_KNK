package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Services.CarAuthService;
import com.jmc.AutoSalon.Services.Interfaces.CarServiceInterface;
import com.jmc.AutoSalon.Services.carService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.PropertySheet;

import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class InsertCarController implements Initializable {

    @FXML
    private Button refreshbtn_id;
    private CarServiceInterface carService;

    public InsertCarController() {
        this.carService = new carService();

    }

    public static int sum = 0;

    public static int id_to_edit;
    public static String type_to_edit;
    public static String price_to_edit;
    public  static String speed_to_edit;
    public static String quantity_to_edit;
    public static String image_to_edit;
    public static String image_to_pass;


    @FXML
    private ComboBox<String> nameCar;

    @FXML
    private ComboBox<String> modelCar;

    @FXML
    private TextField typeCar;

    @FXML
    private Button editCarsBtn;

    @FXML
    private TextField price;

    @FXML
    private ComboBox<String> colorCar;

    @FXML
    private TextField speedCar;

    @FXML
    private TextField yearCar;

    @FXML
    private TextField imageCar;

    @FXML
    private TextField quantity;
    @FXML
    private BorderPane admin_parent;

    @FXML
    private Button addCarBtn;

    @FXML
    private Text insert_car_lbl;

    @FXML
    private Text name_car;

    @FXML
    private Text model_car;

    @FXML
    private Text car_type;

    @FXML
    private Text price_car;

    @FXML
    private Text car_color;

    @FXML
    private Text max_speed;

    @FXML
    private Text year_car;

    @FXML
    private Text image_car;

    @FXML
    private Text car_quantity;

    @FXML
    private AnchorPane fullStage;

    @FXML
    private TableView<Cars> car_table;

    @FXML
    private TableColumn<Cars,String> name_col;

    @FXML
    private TableColumn<Cars,String> model_col;

    @FXML
    private TableColumn<Cars,String> type_col;

    @FXML
    private TableColumn<Cars,Double> price_col;

    @FXML
    private TableColumn<Cars,Double> speed_col;

    @FXML
    private TableColumn<Cars,Integer> year_col;

    @FXML
    private TableColumn<Cars,Integer> quantity_col;

    @FXML
    private TableColumn<Cars,String> image_col;



    @FXML
    public void addCar(ActionEvent e) throws SQLException {
        try {
            String name = this.nameCar.getValue().toString();
            String model = this.modelCar.getValue().toString();
            String type = this.typeCar.getText();
            double price = Double.valueOf(this.price.getText());
            String color = this.colorCar.getValue().toString();
            double speed = Double.valueOf(this.speedCar.getText());
            int year = Integer.valueOf(this.yearCar.getText());
            int quantity = Integer.valueOf(this.quantity.getText());
            String image = this.imageCar.getText();
            Cars car = this.carService.insert_car(name, model, type, price, color, speed, year,quantity, image);
            if (car != null) {
                this.typeCar.setText("");
                this.price.setText("");
                this.speedCar.setText("");
                this.yearCar.setText("");
                this.quantity.setText("");
                this.imageCar.setText("");
                this.colorCar.setValue(null);
                this.nameCar.setValue(null);
                this.modelCar.setValue(null);
                Model.getInstance().getViewFactory().showConfirm("Successful insert","Car inserted successfuly");
            } else {
                Model.getInstance().getViewFactory().showAlert("Error on insertion", "Car exists");
            }

        } catch (Exception ex) {
            //comment
            Model.getInstance().getViewFactory().showAlert("Error on insertion", "Please fill out the fields correctly");
        }
    }

    @FXML
    public void getIdPressed(MouseEvent e){
        if(e.isPrimaryButtonDown() && e.getClickCount()==2){
            InsertCarController.sum += 1;
            Model.getInstance().getViewFactory().showEditCarWindow();
        }
        if(EditCarController.refresh_status) {
            this.car_table.refresh();
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.name_col.setCellValueFactory(new PropertyValueFactory<Cars,String>("name"));
        this.model_col.setCellValueFactory(new PropertyValueFactory<Cars,String>("model"));
        this.type_col.setCellValueFactory(new PropertyValueFactory<Cars,String>("type"));
        this.price_col.setCellValueFactory(new PropertyValueFactory<Cars, Double>("price"));
        this.speed_col.setCellValueFactory(new PropertyValueFactory<Cars, Double>("maxSpeed"));
        this.year_col.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("yearMade"));
        this.quantity_col.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("quantity"));
        this.image_col.setCellValueFactory(new PropertyValueFactory<Cars, String>("carImage"));
        try {
            this.carService.fillCarTable(this.car_table);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", locale);



        this.nameCar.getItems().addAll("Audi", "BMW", "MercedesBenz");
        this.modelCar.getItems().addAll("SUV", "LUXURY", "SEDAN");
        this.colorCar.getItems().addAll("White", "Black", "Blue", "Grey");

        this.imageCar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                ActionEvent ae = new ActionEvent();
                try {
                    this.addCar(ae);
                } catch (Exception e) {
                    Model.getInstance().getViewFactory().showAlert("Error on insertion", "Please fill out fields correctly");
                }
            }
        });

        car_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelect, newSelect) -> {
            if (newSelect != null) {
                InsertCarController.id_to_edit = newSelect.getSerial();
                InsertCarController.type_to_edit = newSelect.getType();
                InsertCarController.price_to_edit = String.valueOf(newSelect.getPrice());
                InsertCarController.speed_to_edit = String.valueOf(newSelect.getMaxSpeed());
                InsertCarController.quantity_to_edit = String.valueOf(newSelect.getQuantity());
                InsertCarController.image_to_edit = newSelect.getCarImage();

                String model = newSelect.getModel();

                if (Objects.equals(model, "SUV")) {
                    model = "suv";
                } else if (Objects.equals(model, "SEDAN")) {
                    model = "Sedan";
                } else {
                    model = "LUXURY";
                }

                InsertCarController.image_to_pass = "/Images/" + model + "/" + newSelect.getCarImage();
            }
        });



        return;
    }

    private void translate() {
        Locale currentLocale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", currentLocale);
        this.set_translations(translate);

    }


    private void set_translations(ResourceBundle translate) {
        insert_car_lbl.setText(translate.getString("insert_car_lbl"));
        name_car.setText(translate.getString("name_car"));
        model_car.setText(translate.getString("model_car"));
        car_type.setText(translate.getString("car_type"));
        price_car.setText(translate.getString("price_car"));
        car_color.setText(translate.getString("car_color"));
        max_speed.setText(translate.getString("max_speed"));
        year_car.setText(translate.getString("year_car"));
        image_car.setText(translate.getString("image_car"));
        addCarBtn.setText(translate.getString("add_car_text"));
        car_quantity.setText(translate.getString("car_quantity"));
    }
    @FXML
    private void handleShqipBtn(ActionEvent event) {
        Locale.setDefault(new Locale("sq","AL"));
        this.translate();
    }
    @FXML
    private void handleEnglishBtn(ActionEvent event) {
        Locale.setDefault(new Locale("en"));
        this.translate();
    }

    @FXML
    public void refreshBtn(ActionEvent event) throws SQLException {
        car_table.getItems().clear();
        this.carService.fillCarTable(this.car_table);
    }
}
