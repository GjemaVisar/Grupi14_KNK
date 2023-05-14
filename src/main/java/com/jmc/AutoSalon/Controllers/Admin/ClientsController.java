package com.jmc.AutoSalon.Controllers.Admin;

import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Repository.RepositoryUser;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.userService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {

    private UserServiceInterface userService;

    public ClientsController(){
        this.userService = new userService();
    }

    private int id_to_delete;

    private ObservableList<User> user_list;

    @FXML
    private TableView<User> user_table;

    @FXML
    private TableColumn<User,Integer> user_id;

    @FXML
    private TableColumn<User,String> username;

    @FXML
    private TableColumn<User,String> password;

    @FXML
    private TableColumn<User, Date> date_registered;

    @FXML
    public void getIdPressed(MouseEvent e){
        if(e.isPrimaryButtonDown() && e.getClickCount()==1){
            this.user_table.getSelectionModel().selectedItemProperty().addListener((obs,oldSelect,newSelect)->{
                if(newSelect != null){
                    this.id_to_delete = newSelect.getId();
                }
            });
        }
        System.out.println(this.id_to_delete);
    }

    @FXML
    public void deleteUser(ActionEvent e) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to delete user");
        alert.setContentText("This action cannot be undone.");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.getButtonTypes().addAll(yes,no);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == yes){
            this.userService.deleteUser(this.id_to_delete);
            User selectedUser = this.user_table.getSelectionModel().getSelectedItem();

            this.user_list.remove(selectedUser);

            this.user_table.refresh();
        }else{
            return;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.user_id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        this.username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        this.password.setCellValueFactory(new PropertyValueFactory<User,String>("saltedHash"));
        this.date_registered.setCellValueFactory(new PropertyValueFactory<User,Date>("date_registered"));
        try {
            this.userService.fillUserTable(this.user_table,false);
            this.user_list = this.user_table.getItems();

            for(User u :user_list){
                System.out.println(u.getUsername()+" - "+ u.getSaltedHash());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




}
