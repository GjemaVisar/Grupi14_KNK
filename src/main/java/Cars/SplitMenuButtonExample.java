package Cars;

        import javafx.application.Application;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.fxml.FXML;
        import javafx.scene.Scene;
        import javafx.scene.control.SplitMenuButton;
        import javafx.scene.control.MenuItem;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.Pane;
        import javafx.scene.layout.StackPane;
        import javafx.stage.Stage;

public class SplitMenuButtonExample extends Application {

    @FXML
    public void start(Stage primaryStage) {
        // Create a SplitMenuButton
        SplitMenuButton splitMenuButton = new SplitMenuButton();
        splitMenuButton.setText("Zgjedhni modelin");

        // Create some menu items
        MenuItem option1 = new MenuItem("SUV");
        MenuItem option2 = new MenuItem("LUXURY");
        MenuItem option3 = new MenuItem("SEDAN");
        MenuItem option4 = new MenuItem("COUPE");
        // Add the menu items to the SplitMenuButton
        splitMenuButton.getItems().addAll(option1, option2, option3, option4);

        // Set an action for each menu item
        option1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Ju zgjodhet SUV");
            }
        });
        option2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Ju zgjodhet LUXURY");
            }
        });
        option3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Ju zgjodhet SEDAN");
            }
        });
        // Create a StackPane with the SplitMenuButton and set it as the root of the scene
        StackPane root = new StackPane();
        root.getChildren().add(splitMenuButton);
        Scene scene = new Scene(root, 300, 250);

        // Set the scene and show the stage
        primaryStage.setTitle("SplitMenuButton Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
