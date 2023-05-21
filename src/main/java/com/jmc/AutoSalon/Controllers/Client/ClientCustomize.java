package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.Cars;
import com.jmc.AutoSalon.Repository.RepositorySales;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.userService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClientCustomize implements Initializable {

    private UserServiceInterface userService;

    public ClientCustomize(){
        this.userService = new userService();
    }

    @FXML
    private ImageView carImg;
    @FXML
    private TextArea descBox;
    @FXML
    private AnchorPane costumizePane;
    @FXML
    private ComboBox<String> llojiMakines;
    @FXML
    private ComboBox<String> modeliMakines;
    @FXML
    private ComboBox<String> ngjyrat;
    @FXML
    private CheckBox viti20;
    @FXML
    private CheckBox viti21;
    @FXML
    private CheckBox viti22;
    @FXML
    private CheckBox viti23;
    @FXML
    private TableView<Cars> tabelaStock;

    @FXML
    private TableColumn<Cars, Integer> numriSerikColumn;

    @FXML
    private TableColumn<Cars, String> emriColumn;

    @FXML
    private TableColumn<Cars, String> modeliColumn;

    @FXML
    private TableColumn<Cars, String> ngjyraColumn;

    @FXML
    private TableColumn<Cars, Integer> vitiColumn;

    @FXML
    private TableColumn<Cars, Double> cmimiColumn;

    @FXML
    private TableColumn<Cars, Double> maksimumiColumn;

    @FXML
    private TableColumn<Cars, Integer> tipiColumn;

    @FXML
    private TableColumn<Cars, Date> shtuarColumn;

    @FXML
    private TableColumn<Cars, Date> perditesuarColumn;

    @FXML
    private Button shqipBtn;

    @FXML
    private Button englishBtn;

    @FXML
    private Label cakto_makinen;

    @FXML
    private Label cakto_modelin;

    @FXML
    private Label cakto_label;


    @FXML
    private Label cakto_ngjyren;

    @FXML
    private Label cakto_vitin;

    @FXML
    private Button kerko;

    @FXML
    private Button buy;

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
        cakto_makinen.setText(translate.getString("cakto_makinen"));
        cakto_modelin.setText(translate.getString("cakto_modelin"));
        cakto_label.setText(translate.getString("cakto_label"));
        cakto_ngjyren.setText(translate.getString("cakto_ngjyren"));
        cakto_vitin.setText(translate.getString("cakto_vitin"));
        kerko.setText(translate.getString("kerko"));
        buy.setText(translate.getString("buy"));

    }

    public ImageView getCarImg() {
        return carImg;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", locale);

        llojiMakines.getItems().addAll("Audi", "BMW", "MercedesBenz");
        modeliMakines.getItems().addAll("SUV", "LUXURY", "SEDAN");
        ngjyrat.getItems().addAll("White", "Black", "Blue", "Grey");
        numriSerikColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Integer>("serial"));
        emriColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, String>("name"));
        modeliColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, String>("model"));
        ngjyraColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, String>("color"));
        vitiColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Integer>("yearMade"));
        cmimiColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Double>("price"));
        maksimumiColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Double>("maxSpeed"));
        tipiColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Integer>("type"));
        shtuarColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Date>("insertedOn"));
        perditesuarColumn.setCellValueFactory(
                new PropertyValueFactory<Cars, Date>("updatedOn"));
    }

    @FXML
    private void handleDergoButton(ActionEvent event) {
        tabelaStock.getItems().clear();
        String selectedCarName = llojiMakines.getValue();
        String selectedModel = modeliMakines.getValue();
        String selectedColor = ngjyrat.getValue();
        int selectedYear = 0;
        if (viti20.isSelected()) selectedYear = 2020;
        else if (viti21.isSelected()) selectedYear = 2021;
        else if (viti22.isSelected()) selectedYear = 2022;
        else if (viti23.isSelected()) selectedYear = 2023;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3333/knk2023", "root", "root");
            String sql = "SELECT * FROM cars WHERE c_name=? AND car_model=? AND color=? AND year_c=? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, selectedCarName);
            statement.setString(2, selectedModel);
            statement.setString(3, selectedColor);
            statement.setInt(4, selectedYear);
            ResultSet result = statement.executeQuery();

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

                if(quantity == 0){
                    carName = carName + " (Out of Stock)";
                    this.tabelaStock.refresh();
                }

                Cars car = new Cars(carId, carName, carModel, carType, carPrice, carColor, carMaxSpeed, carYear,quantity, carImage, carInsertedOn, carUpdatedOn);
                tabelaStock.getItems().add(car);
                System.out.println(car);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setOnMousePressed(MouseEvent event) throws IOException {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
            Node node = ((Node) event.getTarget()).getParent();
            TableRow<Cars> row;
            if (node instanceof TableRow) {
                row = (TableRow) node;
            } else {
                row = (TableRow) node.getParent();
            }

            Cars car = row.getItem();
            if(car != null){
                String model = car.getModel();
                String name = car.getName();
                String type = car.getType();
                if(model == "SEDAN") {
                    model = "Sedan";
                } else if (model == "SUV") {
                    model = "suv";
                }
                System.out.println(car.toString() + " " + car.getCarImage());
                carImg.setImage(new Image((getClass().getResource("/Images/" + model + "/" + car.getCarImage())).toString()));
                descBox.setText(car.toString());
            }
        }
    }

    @FXML
    public void buyButton(ActionEvent event) {
            Cars selectedItem = tabelaStock.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                int carId = selectedItem.getSerial();
                    try{
                        RepositorySales repositorySales = new RepositorySales();
                        if(repositorySales.isQuantityZero(carId)){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(null);
                            alert.setContentText("We are out of stock");
                            alert.showAndWait();
                            return;
                        }

                        Stage paymentStage = new Stage();
                        paymentStage.setTitle("Payment Form");

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Client/Payment.fxml"));
                        Parent paymentFormRoot = loader.load();
                        paymentStage.setScene(new Scene(paymentFormRoot));

                        ClientPaymentController clientPaymentController = loader.getController();

                        paymentStage.initModality(Modality.APPLICATION_MODAL);
                        paymentStage.initOwner(((Node) event.getSource()).getScene().getWindow());
                        paymentStage.showAndWait();

                    boolean paymentSuccessful = clientPaymentController.get_status();
                        if (paymentSuccessful) {
                            paymentStage.close();
                            int userId = this.userService.get_user_id();
                            Date purchaseDate = Date.valueOf(LocalDate.now());
                            double price = selectedItem.getPrice();

                            repositorySales.insertSale(userId, carId, purchaseDate, price);
                            repositorySales.decrement_quantity(carId);
                        }
                    } catch (IOException | SQLException e) {
                        System.out.println();
                    }
            }
    }

}
