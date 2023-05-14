package com.jmc.AutoSalon.Views;

import com.jmc.AutoSalon.Controllers.Admin.AdminController;
import com.jmc.AutoSalon.Controllers.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;


public class ViewFactory {
    private AccountType loginAccountType;

    private Stage currentStage;

    //Client
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane dashboardView;//AnchorPane as the Parent
    private AnchorPane carsPaneView;
    private AnchorPane customizeView;

    //Admin
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane createClientView;
    private AnchorPane clientsView;

    private AnchorPane insertCarView;


    public ViewFactory(){
        this.loginAccountType = AccountType.CLIENT;//e paraqesim si first ne choicebox, CLIENT-in
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public void showAlert(String title,String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }
    public AccountType getLoginAccountType(){
        return this.loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /*
            Client Views
        */
    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return this.clientSelectedMenuItem;
    }

    public AnchorPane getDashboardView(){
        if(dashboardView == null){ //null, sepse sa here qe useri kalon prej nje butoni ne tjetrin , ne nuk dojme qe me e bo load perseri
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/FXML/Client/Dashboard.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getCarsView(){
        if(carsPaneView == null){
            try {
                carsPaneView = new FXMLLoader(getClass().getResource("/FXML/Client/TabPane-Cars.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return carsPaneView;
    }
    public AnchorPane getCustomizeView(){
        if(customizeView == null){
            try {
                customizeView = new FXMLLoader(getClass().getResource("/FXML/Client/Customize.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return customizeView;
    }

    public void showClientWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);

    }


    /*
        Admin Views
    */
    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem(){
        return adminSelectedMenuItem;
    }
    public AnchorPane getCreateClientView(){
        if(createClientView == null){
            try {
                createClientView = new FXMLLoader(getClass().getResource("/FXML/Admin/CreateClient.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return createClientView;
    }
    public AnchorPane getClientsView(){
        if(this.clientsView == null){
            try {
                this.clientsView = new FXMLLoader(getClass().getResource("/FXML/Admin/Clients.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return clientsView;
    }

    public AnchorPane getInsertView(){
        if(this.insertCarView == null){
            try{
                this.insertCarView = new FXMLLoader(getClass().getResource("/FXML/Admin/InsertCars.fxml")).load();
            }catch(Exception e ){
                e.printStackTrace();
            }
        }
        return insertCarView;
    }



    public void showAdminWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        fxmlLoader.setController(controller);
        createStage(fxmlLoader);
    }

    public void closeWindow(){
        Stage stage = this.currentStage;
        this.closeStage(stage);
    }

//Perdoren edhe nga Client edhe nga Admins
    // si fillim na duhet me startu login.fxml
    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        createStage(loader);

    }
    //kjo sherben per me e mbyll login stage
    private void createStage(FXMLLoader loader){
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("FIEK Motors");
        stage.show();
        this.currentStage = stage;
    }
    //kjo sherben per me e mbyll login stage
    public void closeStage(Stage stage){
        stage.close();
        this.currentStage = null;
    }






}
