package Cars;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Veturat extends Application {
    @Override
    public void start (Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Veturat.class.getResource("TabPane-Cars.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 900,600);
        stage.setTitle("App");
        stage.setScene(scene);
        stage.show();
    }
}