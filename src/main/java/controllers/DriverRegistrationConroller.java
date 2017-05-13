package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.DriverUser;

import java.time.LocalDate;

/**
 * Created by samuelhooker on 25/04/17.
 */
public class DriverRegistrationConroller {

    @FXML
    private TextField numberTextField;
    @FXML
    private TextField classTextField;
    @FXML
    private DatePicker issueDatePicker;
    @FXML
    private DatePicker expiryDatePicker;
    @FXML
    private Label errorLabel;

    MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void registerButtonPressed(){
        LocalDate issueDate = issueDatePicker.getValue();
        LocalDate expiryDate = expiryDatePicker.getValue();
        String licenceClass = classTextField.getText();
        String licenceNumber = numberTextField.getText();

        if (DataStore.currentUser == null){return;}
        if (issueDate == null || expiryDate == null ||
                licenceClass.equals("") || licenceNumber.equals("")){
            errorLabel.setText("Please Enter All Values");
            return;
        }
        if(expiryDate.isBefore(LocalDate.now())){
            errorLabel.setText("License is expired");
            return;
        }

        DriverUser du = new DriverUser(expiryDate, issueDate, licenceNumber, licenceClass);
        DataStore.currentUser.setDriverUser(du);

        mainController.driverButtonPressed();



    }

}
