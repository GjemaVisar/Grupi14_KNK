module com.example.ushtrime1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens Cars to javafx.fxml;
    opens Home to javafx.fxml;
    opens com.example.ushtrime1 to javafx.fxml;
    exports com.example.ushtrime1;


    opens Controllers to javafx.fxml;
    exports Controllers to javafx.fxml, javafx.graphics;

}