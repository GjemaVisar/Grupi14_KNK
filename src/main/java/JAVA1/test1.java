package JAVA1;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class test1 extends Application {
    public void start (Stage stage){
        Button btn = new Button("OK");
        Pane pane = new Pane();
        pane.getChildren().add(btn);

        Scene sc = new Scene(pane,200,200);
        stage.setScene(sc);
        stage.show();
    }
}
