package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.StageStyle;
import models.Car;
import models.Notification;
import models.User;
import org.controlsfx.control.action.Action;

/**
 * Created by samuelhooker on 24/04/17.
 */
public class LoginController {


    @FXML
    private TextField loginEmailTextField;
    @FXML
    private PasswordField loginPasswordTextField;
    @FXML
    private Label loginErrorLabel;

    @FXML
    private TextField registerNameTextField;
    @FXML
    private TextField registerEmailTextField;
    @FXML
    private TextField registerAddressTextField;
    @FXML
    private TextField registerPhoneTextField;
    @FXML
    private TextField registerPhotoUrlTextField;
    @FXML
    private PasswordField registerPasswordTextField;
    @FXML
    private PasswordField registerConfirmPasswordTextField;
    @FXML
    private Label registerErrorLabel;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    MainController mainController;


    @FXML
    public void loginButtonPressed(){
        if(loginEmailTextField.getText().isEmpty() || loginPasswordTextField.getText().isEmpty()){
            loginErrorLabel.setText("Enter Email and Password");
            return;
        }
        for(User user: DataStore.users){
            if(user.getUsername().equals(loginEmailTextField.getText())){
                if(user.checkPasswordValid(loginPasswordTextField.getText())){
                    //log in valid
                    user.setAsCurrentUser();
                    loginSuccess();
                    mainController.passengerButtonPressed();
                    mainController.showUserDetails();
                    return;
                }
                loginErrorLabel.setText("Password is Invalid");
                return;
            }

        }
        loginErrorLabel.setText("Email Not Found. Try register");
        return;

    }

    @FXML
    public void registerButtonPressed(){

        if(registerNameTextField.getText().isEmpty() || registerPhoneTextField.getText().isEmpty()){
            registerErrorLabel.setText("Name and Phone Number required");
            return;
        }
        if(!User.checkEmailValid(registerEmailTextField.getText())){
            registerErrorLabel.setText("Use a valid UC email address");
            return;
        }
        if(registerPasswordTextField.getText().equals(registerConfirmPasswordTextField.getText()) &&
                !registerPasswordTextField.getText().isEmpty()){
            //password valid

            User user = new User(
                    registerNameTextField.getText(),
                    registerEmailTextField.getText(),
                    registerAddressTextField.getText(),
                    registerPhoneTextField.getText(),
                    registerPhotoUrlTextField.getText(),
                    registerPasswordTextField.getText()
            );

            DataStore.users.add(user);
            user.setAsCurrentUser();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Welcome to UC SHARE");
            alert.setHeaderText(null);
            alert.setContentText("You have just successfully created an account");

            alert.showAndWait();
            loginSuccess();
            mainController.passengerButtonPressed();
            mainController.showUserDetails();
        }else{
            registerErrorLabel.setText("Passwords Must Match");
            return;
        }

    }

    public void loginSuccess(){
        if(DataStore.currentUser.getDriverUser() != null && DataStore.currentUser.getDriverUser().showExpiryNotification()){
            showWarningDialog("Licence Expiry", "Your licence is about to expire. renew it soon");
        }


        //manage notifications that need to be shown
        for(Car car : DataStore.getCars()){
                if(car.showRegistrationNotification()){
                    showWarningDialog("Registration Expiry", "Your Registration on one of your cars is about to expire. renew it soon");

                }
                if (car.showWofNotification()){
                    showWarningDialog("WOF Expiry", "Your WOF is about to expire on one of your cars. renew it soon");

                }

        }
        System.out.println("looking for notifications");
        for (Notification notification: DataStore.getNotifications()){
            System.out.println("got one");

            showWarningDialog("New Notification", notification.getMessage());
            //DataStore.notifications.remove(notification); //removes notification
        }

    }

    private void showWarningDialog(String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

}
