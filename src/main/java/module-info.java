module com.example.ushtrime1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens LogIn to javafx.fxml;
    opens com.example.ushtrime1 to javafx.fxml;
    exports com.example.ushtrime1;
    exports JAVA1 to javafx.graphics;
    exports LogIn to javafx.graphics;
}