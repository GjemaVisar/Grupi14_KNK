module com.example.ushtrime1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.ushtrime1 to javafx.fxml;
    exports com.example.ushtrime1;
    exports JAVA1 to javafx.graphics;
}