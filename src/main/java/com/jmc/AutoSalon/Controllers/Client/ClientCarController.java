package com.jmc.AutoSalon.Controllers.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class ClientCarController {
    @FXML
    private FlowPane suvPaneM;
    @FXML
    private FlowPane luxPaneM;
    @FXML
    private FlowPane sedanPaneM;
    @FXML
    private FlowPane sedanPaneA;
    @FXML
    private FlowPane luxPaneA;
    @FXML
    private FlowPane suvPaneA;
    @FXML
    private FlowPane sedanPaneB;
    @FXML
    private FlowPane luxPaneB;
    @FXML
    private FlowPane suvPaneB;
    @FXML
    private Button home_btn;//na duhet per me dal ne home
    @FXML
    private Button cars_btn;

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

//
//    private Stage stage;
//    private Scene scene;
//    private Parent root;
//    public void switchToHome(ActionEvent e) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/FXML/home.fxml"));
//        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//
//    }
//
//    public void switchToLogin(ActionEvent e) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
//        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }


}
