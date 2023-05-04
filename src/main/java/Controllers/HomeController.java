package Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToCars(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TabPane-Cars.fxml"));
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToLogin(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
