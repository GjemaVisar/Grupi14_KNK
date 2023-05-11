package com.jmc.AutoSalon.Views;

import com.jmc.AutoSalon.Controllers.Client.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.security.spec.ECField;
import java.util.Locale;

public class ViewFactory {
    //Client
    private AnchorPane dashboardView;

    public ViewFactory(){

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
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        createStage(loader);

    }

    public void showClientWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);

    }

    public void closeStage(Stage stage){
        stage.close();
    }






}
