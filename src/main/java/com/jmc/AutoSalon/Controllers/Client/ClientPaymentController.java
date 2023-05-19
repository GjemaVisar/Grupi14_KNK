package com.jmc.AutoSalon.Controllers.Client;

import com.jmc.AutoSalon.Models.Model;
import com.jmc.AutoSalon.Models.User;
import com.jmc.AutoSalon.Services.Interfaces.UserServiceInterface;
import com.jmc.AutoSalon.Services.userService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.time.LocalDate;

public class ClientPaymentController {

    private boolean status;

    public boolean get_status(){
        return this.status;
    }
    @FXML
    private TextField acc_name;

    @FXML
    private TextField bank_number;

    @FXML
    private TextField cvv_text;

    @FXML
    private Button submit_btn;


    @FXML
    public boolean onSubmitPayment(ActionEvent event) {

        String account = acc_name.getText();
        String bank_nr = bank_number.getText();
        String cvv = cvv_text.getText();

        boolean paymentSuccessful = isValidAccountCredentials(account, bank_nr,cvv);
        if(paymentSuccessful){
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Purchase Successful");
            successAlert.setHeaderText(null);
            successAlert.setContentText("The item has been purchased successfully!");
            successAlert.showAndWait();

        }else{
            Model.getInstance().getViewFactory().showAlert("Payment Failure","Could not process your payment");
        }


        return paymentSuccessful;
    }

    private boolean isValidAccountCredentials(String account,String bank_number, String cvv) {
        String bankNumberRegex = "\\d{16}";
        String cvvRegex = "\\d{3}";
        String nameSurnameRegex = "[A-Za-z]+ [A-Za-z]+";

        if(account.matches(nameSurnameRegex) && bank_number.matches(bankNumberRegex) && cvv.matches(cvvRegex)){
            return true;
        }else if(account.isEmpty() || bank_number.isEmpty() || cvv.isEmpty()){
            return false;
        }else{
            return false;
        }
    }
}
