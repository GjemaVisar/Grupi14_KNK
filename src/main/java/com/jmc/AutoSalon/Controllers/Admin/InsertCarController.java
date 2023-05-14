package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.Model;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.PropertySheet;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class InsertCarController implements Initializable {

    private CarServiceInterface carService;

    public InsertCarController(){
        this.carService = new carService();

    }

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
    private BorderPane admin_parent;

    @FXML
    private Button addCarBtn;

    @FXML
    public void addCar(ActionEvent e) throws SQLException {
            try{
                String name = this.nameCar.getValue().toString();
                String model = this.modelCar.getValue().toString();
                String type = this.typeCar.getText();
                double price = Double.valueOf(this.price.getText());
                String color = this.colorCar.getValue().toString();
                double speed = Double.valueOf(this.speedCar.getText());
                int year = Integer.valueOf(this.yearCar.getText());
                String image = this.imageCar.getText();
                Cars car = this.carService.insert_car(name,model,type,price,color,speed,year,image);
                if (car != null) {
                    this.typeCar.setText("");
                    this.price.setText("");
                    this.speedCar.setText("");
                    this.yearCar.setText("");
                    this.imageCar.setText("");
                    this.colorCar.setValue(null);
                    this.nameCar.setValue(null);
                    this.modelCar.setValue(null);
                    System.out.println("Car inserted successfuly");
                }else{
                    Model.getInstance().getViewFactory().showAlert("Error on insertion","Car exists");
                }

            }
            catch(Exception ex){
                //comment
               Model.getInstance().getViewFactory().showAlert("Error on insertion","Please fill out the fields correctly");
            }
        }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.nameCar.getItems().addAll("Audi", "BMW", "MercedesBenz");
        this.modelCar.getItems().addAll("SUV", "LUXURY", "SEDAN");
        this.colorCar.getItems().addAll("White", "Black", "Blue", "Grey");

        this.imageCar.setOnKeyPressed(event->{
            if(event.getCode() == KeyCode.ENTER){
                ActionEvent ae = new ActionEvent();
                try{
                    this.addCar(ae);}
                catch(Exception e){
                    Model.getInstance().getViewFactory().showAlert("Error on insertion","Please fill out fields correctly");
                }
            }
        });

        return;
    }


}
