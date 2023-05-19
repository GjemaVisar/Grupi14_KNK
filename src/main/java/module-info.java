module com.jmc.AutoSalon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.commons;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires jbcrypt;

//    opens Cars to javafx.fxml;
//    opens com.jmc.AutoSalon.Home to javafx.fxml;
//    opens com.example.ushtrime1 to javafx.fxml;
    opens com.jmc.AutoSalon.Controllers.Admin to javafx.fxml;
      opens com.jmc.AutoSalon to javafx.fxml;
      opens com.jmc.AutoSalon.Controllers to javafx.fxml;
    opens com.jmc.AutoSalon.Controllers.Client;

    exports com.jmc.AutoSalon ;
    exports com.jmc.AutoSalon.Controllers ;
    exports com.jmc.AutoSalon.Controllers.Admin ;
    exports com.jmc.AutoSalon.Controllers.Client ;
    exports com.jmc.AutoSalon.Models ;
    exports com.jmc.AutoSalon.Views ;


}