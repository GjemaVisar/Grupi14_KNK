package Home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage ) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(LogIn.App.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,450);
        stage.setScene(scene);
        stage.setTitle("FiekMOTORS");
        stage.show();
    }

}
