package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

public class carController {
    @FXML
    public FlowPane suvPaneM;
    @FXML
    public FlowPane luxPaneM;
    @FXML
    public FlowPane sedanPaneM;
    public FlowPane sedanPaneA;
    public FlowPane luxPaneA;
    public FlowPane suvPaneA;
    public FlowPane sedanPaneB;
    public FlowPane luxPaneB;
    public FlowPane suvPaneB;

    public void suvSwitchM(ActionEvent actionEvent) {
        switchPanes("M", 1);
    }

    public void luxSwitchM(ActionEvent actionEvent) {
        switchPanes("M", 2);
    }

    public void sedanSwitchM(ActionEvent actionEvent) {
        switchPanes("M", 3);
    }
    public void suvSwitchA(ActionEvent actionEvent) {
        switchPanes("A", 1);
    }

    public void luxSwitchA(ActionEvent actionEvent) {
        switchPanes("A", 2);
    }

    public void sedanSwitchA(ActionEvent actionEvent) {
        switchPanes("A", 3);
    }
    public void suvSwitchB(ActionEvent actionEvent) {
        switchPanes("B", 1);
    }

    public void luxSwitchB(ActionEvent actionEvent) {
        switchPanes("B", 2);
    }

    public void sedanSwitchB(ActionEvent actionEvent) {
        switchPanes("B", 3);
    }
    public void switchPanes(String a, int b) {
        if(a == "M") { //mercedes
            switch(b) {
                case 1:
                    suvPaneM.setOpacity(1);
                    luxPaneM.setOpacity(0);
                    sedanPaneM.setOpacity(0);
                    break;
                case 2:
                    suvPaneM.setOpacity(0);
                    luxPaneM.setOpacity(1);
                    sedanPaneM.setOpacity(0);
                    break;
                default:
                    suvPaneM.setOpacity(0);
                    luxPaneM.setOpacity(0);
                    sedanPaneM.setOpacity(1);
                    break;
            }
        } else if (a == "B") { // bmw
            switch(b) {
                case 1:
                    suvPaneB.setOpacity(1);
                    luxPaneB.setOpacity(0);
                    sedanPaneB.setOpacity(0);
                    break;
                case 2:
                    suvPaneB.setOpacity(0);
                    luxPaneB.setOpacity(1);
                    sedanPaneB.setOpacity(0);
                    break;
                default:
                    suvPaneB.setOpacity(0);
                    luxPaneB.setOpacity(0);
                    sedanPaneB.setOpacity(1);
                    break;
            }
        } else { //audi
            switch(b) {
                case 1:
                    suvPaneA.setOpacity(1);
                    luxPaneA.setOpacity(0);
                    sedanPaneA.setOpacity(0);
                    break;
                case 2:
                    suvPaneA.setOpacity(0);
                    luxPaneA.setOpacity(1);
                    sedanPaneA.setOpacity(0);
                    break;
                default:
                    suvPaneA.setOpacity(0);
                    luxPaneA.setOpacity(0);
                    sedanPaneA.setOpacity(1);
                    break;
            }
        }
    }
}
