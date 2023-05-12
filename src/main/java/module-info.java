module com.jmc.AutoSalon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

//    opens Cars to javafx.fxml;
//    opens com.jmc.AutoSalon.Home to javafx.fxml;
//    opens com.example.ushtrime1 to javafx.fxml;
      opens com.jmc.AutoSalon to javafx.fxml;
      opens Controllers to javafx.fxml;

    exports com.jmc.AutoSalon ;
    exports com.jmc.AutoSalon.Controllers ;
    exports com.jmc.AutoSalon.Controllers.Admin ;
    exports com.jmc.AutoSalon.Controllers.Client ;
    exports com.jmc.AutoSalon.Models ;
    exports com.jmc.AutoSalon.Views ;
    exports Controllers to javafx.graphics, javafx.fxml;



}