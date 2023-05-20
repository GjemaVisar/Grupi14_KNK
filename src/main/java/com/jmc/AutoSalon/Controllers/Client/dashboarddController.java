package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.userService;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class dashboarddController implements Initializable {
    public TitledPane tabelaBlerjet;
    public FlowPane mostSoldCarsFlowPane;
    public Button refresh_btn;
    private UserServiceInterface userService;
    @FXML
    public Label username_lbl;
    public TableView<Cars> tabela_view;
    public TableColumn<Cars, String> name_column;
    public TableColumn<Cars, String> model_column;
    public TableColumn<Cars, String> type_column;
    public TableColumn<Cars, Integer> year_column;
    public TableColumn<Cars, Double> price_column;
    public User user;
    public Cars cars;
    public dashboarddController(){
        this.userService = new userService();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            populateCarsTable();
            showMostSoldCars();
            bindData();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void bindData() throws SQLException {
        username_lbl.textProperty().bind(Bindings.concat("Hi, ").concat(this.userService.get_username()));
    }

    private void populateCarsTable() throws SQLException {
        List<Cars> carsList = this.userService.your_car();

        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        model_column.setCellValueFactory(new PropertyValueFactory<>("model"));
        type_column.setCellValueFactory(new PropertyValueFactory<>("type"));
        year_column.setCellValueFactory(new PropertyValueFactory<>("yearMade"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("price"));

        tabela_view.getItems().addAll(carsList);


    }

    public void showMostSoldCars(){
        mostSoldCarsFlowPane.getChildren().clear();

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3333/knk2023", "root", "root");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT car_image, c_name, car_model FROM cars ORDER BY quantity ASC LIMIT 3;")) {

            while(resultSet.next()){
                String car_image = resultSet.getString("car_image");
                String car_name = resultSet.getString("c_name");
                String car_model = resultSet.getString("car_model");

                try{

                Image image = new Image((getClass().getResource("/Images/" + car_model + "/" + car_image)).toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(250);
                imageView.setFitHeight(300);
                imageView.setPreserveRatio(true);
                Label nameLabel = new Label(car_name);

                VBox carBox = new VBox(imageView, nameLabel);
                carBox.setAlignment(Pos.CENTER);
                carBox.setSpacing(30);

                mostSoldCarsFlowPane.getChildren().add(carBox);
                }catch (IllegalArgumentException e){
                    System.err.println("Error loading image: " + car_image);
                    e.printStackTrace();
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void refreshBtn(ActionEvent event) {
        try {
            populateCarsTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
