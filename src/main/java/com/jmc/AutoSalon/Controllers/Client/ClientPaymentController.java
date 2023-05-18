package com.jmc.AutoSalon.Controllers.Client;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientPaymentController {
    public TextField bankAcc_lbl;
    public PasswordField bankPass_lbl;
    public Button submit_btn;

    public boolean processPayment() {

        String accountNumber = bankAcc_lbl.getText();
        String password = bankPass_lbl.getText();

        boolean paymentSuccessful = isValidAccountCredentials(accountNumber, password);

        if (paymentSuccessful) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Payment Successful");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Payment was successful!");
            successAlert.showAndWait();

            Stage paymentStage = (Stage) bankAcc_lbl.getScene().getWindow();
            paymentStage.close();

        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Payment Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Payment failed. Please check your account credentials.");
            errorAlert.showAndWait();
        }

        return paymentSuccessful;
    }

    private boolean isValidAccountCredentials(String accountNumber, String password) {

        return !accountNumber.isEmpty() && !password.isEmpty();
    }
    public void onSubmitPayment(ActionEvent event) {
        boolean paymentSuccessful = processPayment();
    }
}
