package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Models.dto.carFilter;
import com.jmc.AutoSalon.Services.Interfaces.TestDriveServiceInterface;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.TestDriveService;
import com.jmc.AutoSalon.Services.userService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class tableCarsController implements Initializable {



    @FXML
    public Pagination mainPage;
    private final int rowsPerPage = 2;
    public AnchorPane mainPane;
    public Button audiBtn;
    public Button bmwBtn;
    public Button mercBtn;
    public Button suvBtn;
    public Button luxBtn;
    public Button sdnBtn;
    public FlowPane flowPane;
    public ButtonBar btnBar;
    ObservableList<Cars>carsTable = FXCollections.observableArrayList();
    String name = "audi";
    String model = "suv";
    private Node createPage(int pageIndex) {
        if(carsTable.size() % rowsPerPage == 0) {
            mainPage.setPageCount(carsTable.size() / rowsPerPage);
        } else {
            mainPage.setPageCount(carsTable.size() / rowsPerPage + 1);
        }
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, carsTable.size());
        ObservableList<Cars>tempTable = FXCollections.observableArrayList();
        if(toIndex < fromIndex) {
            tempTable = FXCollections.observableArrayList(carsTable.subList(toIndex, fromIndex));
        } else {
            tempTable = FXCollections.observableArrayList(carsTable.subList(fromIndex, toIndex));
        }

        flowPane.getChildren().clear();
        for (Cars car : tempTable) {
            CustomPane newPane = new CustomPane(500, 240, car);
            flowPane.getChildren().add(newPane);
        }
        return flowPane;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getCars();
        mainPage.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> createPage((int) newIndex));
    }
    public void getCars() {
        try {
            if(!carsTable.isEmpty()) { carsTable.clear(); }
            carFilter filter = new carFilter();
            filter.setName(name);
            filter.setModel(model);
            ResultSet result = filter.sqlQueryCars();

            while (result.next()) {
                int carId = result.getInt("numri_serik");
                String carName = result.getString("c_name");
                String carModel = result.getString("car_model");
                String carType = result.getString("car_type");
                double carPrice = result.getDouble("price_c");
                String carColor = result.getString("color");
                double carMaxSpeed = result.getDouble("max_speed");
                int carYear = result.getInt("year_c");
                int quantity = result.getInt("quantity");
                String carImage = result.getString("car_image");
                Date carInsertedOn = result.getDate("inserted_on");
                Date carUpdatedOn = result.getDate("updated_on");


                Cars car = new Cars(carId, carName, carModel, carType, carPrice, carColor, carMaxSpeed, carYear,quantity, carImage, carInsertedOn, carUpdatedOn);
                carsTable.add(car);
                System.out.println(car);
            }
            carFilter.closeConnection();
            mainPage.setPageFactory(pageIndex -> createPage(pageIndex));
        } catch (SQLException e) {
            Model.getInstance().getViewFactory().showAlert("Error",e.getMessage());
        }
    }
    public void setAudi(ActionEvent actionEvent) {
        this.name = "audi";
        getCars();
    }
    public void setBMW(ActionEvent actionEvent) {
        this.name = "bmw";
        getCars();
    }
    public void setMerc(ActionEvent actionEvent) {
        this.name = "MercedesBenz";
        getCars();
    }
    public void setSUV(ActionEvent actionEvent) {
        this.model = "suv";
        getCars();
    }
    public void setL(ActionEvent actionEvent) {
        this.model = "luxury";
        getCars();
    }
    public void setSdn(ActionEvent actionEvent) {
        this.model = "sedan";
        getCars();
    }

}

class CustomPane extends Pane {
    private TestDriveServiceInterface testDriveService;
    private UserServiceInterface userService;
    public CustomPane(double width, double height ,Cars car) {
        super();
        this.testDriveService = new TestDriveService();
        this.userService = new userService();
        super.setWidth(width);
        super.setHeight(height);
        this.draw(car);
    }
    public void draw(Cars car) {


        Image carImg = new Image((getClass().getResource("/Images/" + car.getModel() + "/" + car.getCarImage())).toString(), 300, 225, false, false);
        ImageView imgV = new ImageView(carImg);
        Label label = new Label(car.toString());
        Button btn = new Button("TEST DRIVE");
        btn.setLayoutX(800);
        btn.setLayoutY(100);
        btn.setStyle("-fx-background-color: \n" +
                "        #000000,\n" +
                "        linear-gradient(#7ebcea, #2f4b8f),\n" +
                "        linear-gradient(#426ab7, #263e75),\n" +
                "        linear-gradient(#395cab, #223768);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;");


        btn.setOnAction(event -> {
            // Krijimi i dialogut per kalendar
            Dialog<LocalDate> dialog = new Dialog<>();
            dialog.setTitle("Choose Test Drive Date");

            // Shto DatePicler ne kalendar
            DatePicker datePicker = new DatePicker();
            dialog.getDialogPane().setContent(datePicker);

            // Me shtu submit buttonin
            ButtonType submitButtonType = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, submitButtonType);
            Button submitButton = (Button) dialog.getDialogPane().lookupButton(submitButtonType);
            submitButton.disableProperty().bind(datePicker.valueProperty().isNull());

            // Convert the result to a LocalDate object me morr vleren
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == submitButtonType) {
                    return datePicker.getValue();
                }
                return null;
            });

            // Show the dialog and handle the result
            // qitu nashta duhet me ndrru me this ne repository
            Optional<LocalDate> result = dialog.showAndWait();
            result.ifPresent(date -> {
                // Handle the selected date
                //System.out.println("Selected date: " + date);
                this.testDriveService.add_test_drive(this.userService.get_user_id(),car.getSerial(),Date.valueOf(date));
                // Additional code...

            });
        });


        label.setLayoutX(450);
        label.setLayoutY(112.5);

        // Create a new Pane to hold the button

        super.getChildren().addAll(imgV, label, btn);
    }

}