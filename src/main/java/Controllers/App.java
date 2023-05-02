package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class App extends Application {
    @Override
    public void start(Stage stage ) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,450);
        stage.setScene(scene);
        stage.setTitle("FiekMOTORS");
        stage.show();
    }

    public static void main(String [] args){
        launch(args);
    }
}
