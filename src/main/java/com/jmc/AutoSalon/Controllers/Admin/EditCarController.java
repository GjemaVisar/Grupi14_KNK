package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Services.Interfaces.CarServiceInterface;
import com.jmc.AutoSalon.Services.carService;
import com.jmc.AutoSalon.Views.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditCarController implements Initializable {

    private CarServiceInterface carService;

    @FXML
    private TextField edit_type;

    @FXML
    private TextField edit_price;

    @FXML
    private TextField edit_speed;

    @FXML
    private TextField edit_quantity;

    @FXML
    private TextField edit_image;

    @FXML
    private ImageView image;

    private int id_passed;
    private String type;
    private String price;
    private String speed;
    private String quantity;
    private String image_edit;

    @FXML
    private Button editBtn;

    @FXML
    public void edit_car() throws SQLException {
        String new_type = this.edit_type.getText();
        String new_price = this.edit_price.getText();
        String new_speed = this.edit_speed.getText();
        String new_quantity = this.edit_quantity.getText();
        String new_image = this.edit_image.getText();


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.setTitle("Confirm Editing Car");
        alert.setHeaderText("Are you sure you want to edit this car");
        alert.setContentText("You can reedit it later if you made a mistake!");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.getButtonTypes().addAll(yes,no);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == yes){
            this.carService.update_car(this.id_passed,new_type,new_price,new_speed,new_quantity,new_image);
            alert.close();
        }else{
            return;
        }
    }

    @FXML
    private Button deleteBtn;

    @FXML
    public void delete_car() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.setTitle("Confirm Deleting Car");
        alert.setHeaderText("Are you sure you want to delete this car");
        alert.setContentText("This action cannot be undone!");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.getButtonTypes().addAll(yes,no);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == yes){
            this.carService.deleteCar(this.id_passed);
            Model.getInstance().getViewFactory().closeWindow();
        }else{
            return;
        }
    }



    public EditCarController(){
        this.carService = new carService();


        this.id_passed = InsertCarController.id_to_edit;
        this.type = InsertCarController.type_to_edit;
        this.price = InsertCarController.price_to_edit;
        this.speed = InsertCarController.speed_to_edit;
        this.quantity = InsertCarController.quantity_to_edit;
        this.image_edit = InsertCarController.image_to_edit;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.edit_type.setText(this.type);
        this.edit_price.setText(this.price);
        this.edit_speed.setText(this.speed);
        this.edit_quantity.setText(this.quantity);
        this.edit_image.setText(this.image_edit);

    }
}
