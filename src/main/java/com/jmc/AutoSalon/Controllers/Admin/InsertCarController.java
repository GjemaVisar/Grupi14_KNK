package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Services.CarAuthService;
import com.jmc.AutoSalon.Services.Interfaces.CarServiceInterface;
import com.jmc.AutoSalon.Services.carService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.controlsfx.control.PropertySheet;

import java.net.URL;
import java.sql.SQLException;
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
                if (car == null) {
                    return;
                }
                this.typeCar.setText("");
                this.price.setText("");
                this.speedCar.setText("");
                this.yearCar.setText("");
                this.imageCar.setText("");
                this.colorCar.setValue(null);
                this.nameCar.setValue(null);
                this.modelCar.setValue(null);
                System.out.println("Car inserted successfuly");
            }
            catch(Exception ex){
                Model.getInstance().getViewFactory().showAlert("Mistake during Insert","Please make sure that your data is correct");
            }
        }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.nameCar.getItems().addAll("Audi", "BMW", "MercedesBenz");
        this.modelCar.getItems().addAll("SUV", "LUXURY", "SEDAN");
        this.colorCar.getItems().addAll("White", "Black", "Blue", "Grey");
        return;
    }


}
