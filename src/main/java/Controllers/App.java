package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage ) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TabPane-Cars.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),840,670);
        stage.setScene(scene);
        stage.setTitle("FiekMOTORS");
        stage.show();
//        stage.setFullScreen(true);
    }

    public static void main(String [] args){
        launch(args);
    }
}
