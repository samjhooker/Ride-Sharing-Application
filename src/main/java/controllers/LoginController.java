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
    @FXML
    private TextField studentIdTextField;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    MainController mainController;

    public User loginUser(String username, String password){
        if(username.equals("") || password.equals("")){
            return null;
        }
        for(User user: DataStore.users) {
            if (user.getUsername().equals(username)) {
                if (user.checkPasswordValid(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    @FXML
    private void loginButtonPressed(){
        User user = loginUser(loginEmailTextField.getText(), loginPasswordTextField.getText());
        if (user != null) {
            user.setAsCurrentUser();

            loginSuccess();
            mainController.passengerButtonPressed();
            mainController.showUserDetails();
        }else{
            loginErrorLabel.setText("Login Failed. Please Try again");
        }


    }

    public User createUser(String studentId, String registerName, String registerEmail, String  registerAddress,
                           String registerPhone, String registerPhotoUrl,
                           String password, String passwordConfirm){

        if(!User.checkEmailValid(registerEmail)){
            return null;
        }
        if(
                registerName != "" &&
                registerPhone != "" &&
                passwordConfirm.equals(password) &&
                password != "" &&
                studentId != "") {

            User user = new User(
                    studentId,
                    registerName,
                    registerEmail,
                    registerAddress,
                    registerPhone,
                    registerPhotoUrl,
                    password
            );
            return user;
        }else{
            return null;
        }

    }

    @FXML
    public void registerButtonPressed(){

        User user = createUser(
                studentIdTextField.getText(),
                registerNameTextField.getText(),
                registerEmailTextField.getText(),
                registerAddressTextField.getText(),
                registerPhoneTextField.getText(),
                registerPhotoUrlTextField.getText(),
                registerPasswordTextField.getText(),
                registerConfirmPasswordTextField.getText()
                );
        if(user != null) {
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

        }
        else
        {
            registerErrorLabel.setText("Invalid. Confirm valid UC email, matching passwords and all inputs entered");
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
            DataStore.notifications.remove(notification); //removes notification
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
