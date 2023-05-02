package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.layout.FlowPane;

public class carController {

    public FlowPane suvPaneM;
    public FlowPane luxPaneM;
    public FlowPane sedanPaneM;

    public void suvSwitchM(ActionEvent actionEvent) {
        luxPaneM.setOpacity(0);
        sedanPaneM.setOpacity(0);
        suvPaneM.setOpacity(1);
    }

    public void luxSwitchM(ActionEvent actionEvent) {
        luxPaneM.setOpacity(1);
        sedanPaneM.setOpacity(0);
        suvPaneM.setOpacity(0);
    }

    public void sedanSwitchM(ActionEvent actionEvent) {
        System.out.println("test");
        luxPaneM.setOpacity(0);
        sedanPaneM.setOpacity(1);
        suvPaneM.setOpacity(0);
    }
}
