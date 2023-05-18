package com.jmc.AutoSalon.Views;

import com.jmc.AutoSalon.Controllers.Admin.AdminController;
import com.jmc.AutoSalon.Controllers.Admin.EditCarController;
import com.jmc.AutoSalon.Controllers.Admin.InsertCarController;
import com.jmc.AutoSalon.Controllers.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.Optional;


public class ViewFactory {
    private AccountType loginAccountType;
    private boolean edit_page;
    private Stage currentStage;

    private ArrayList<Stage> open_stages = new ArrayList<Stage>();

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
                dashboardView = new FXMLLoader(getClass().getResource("/FXML/Client/dashboardd.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getCarsView(){
        if(carsPaneView == null){
            try {
                carsPaneView = new FXMLLoader(getClass().getResource("/FXML/Client/TableViewCars.fxml")).load();
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

    public void showEditCarWindow(){
        this.edit_page = true;
        //llogarit indeksin aktual te listes
        //kur mbyllet stage i edit car remove aq anetare sa iu kane shtuar
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Admin/edit_car.fxml"));
        EditCarController controller = new EditCarController();
        fxmlLoader.setController(controller);
        createStage(fxmlLoader);
    }


    public void showAdminWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        fxmlLoader.setController(controller);
        createStage(fxmlLoader);
    }

    public void closeWindow(){
        /* sahere qe shtohet nje pane per editim, rritet edhe shuma. Shuma nis prej zeros
         kjo variabel na tregon sa stage shtese duhet me i hjek */
        int num_to_delete = InsertCarController.sum;

        //fillimi eshte gjithmone zero, pasi qe te mbyllet login, krijohet stage i ri me indeks zero ne listen open stages
        int beginning = 0 ;

        //caktojme limitin per loop, me dit sa anetare duhemi me i fshi, nese jon 4 stages ne varg dihet se 3 jon shtese, pra deri
        // te indeksi 3, size - 1
        int end = this.open_stages.size()-1;

        // nese ka pane shtese te shkaktuara si ne rastin e car edit, fshiji njehere ato deri sa te mbet me vec stage kryesore
            for(;end>beginning;end--){
                this.open_stages.remove(this.open_stages.get(end));
                }

            //stage kryesore fshihet.Shkojme ne login!
            Stage stage = this.open_stages.get(beginning);
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
        this.open_stages.add(stage);
    }

    public void terminate_app_esc(){
        this.showAlert("Terminate Program","Are you sure you want to exit?");
        this.closeWindow();
    }
    public void closeStage(Stage stage){
        stage.close();
        int last_index = this.open_stages.size() -1;
        this.open_stages.remove(this.open_stages.get(last_index));
    }






}
