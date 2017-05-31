package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import models.User;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by samuelhooker on 14/05/17.
 */
public class UpdateUserController implements Initializable {



    @FXML
    private Label usernameLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField photoUrlTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private ImageView profilePictureImageView;
    @FXML
    private Label feedbackLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       displayUserDetails();
    }

    private void displayUserDetails(){
        User currentUser = DataStore.currentUser;

        usernameLabel.setText(currentUser.getUsername()+"         "+currentUser.getStudentId());
        nameTextField.setText(currentUser.getName());
        emailTextField.setText(currentUser.getEmail());
        phoneTextField.setText(currentUser.getPhoneNumber());
        addressTextField.setText(currentUser.getAddress());
        photoUrlTextField.setText(currentUser.getPhotoUrl());

        if(currentUser.getPhotoUrl().isEmpty()){
            profilePictureImageView.setImage(new Image("http://s3.amazonaws.com/37assets/svn/765-default-avatar.png"));
        }else{
            try{
                profilePictureImageView.setImage(new Image(currentUser.getPhotoUrl()));
            }catch (Exception e){
                profilePictureImageView.setImage(new Image("http://s3.amazonaws.com/37assets/svn/765-default-avatar.png"));

            }

        }
    }

    public void updateUser(User currentUser, String name, String email, String phone,
                           String address, String photoUrl, String password){
        currentUser.setName(name);
        currentUser.setEmail(email);
        currentUser.setPhoneNumber(phone);
        currentUser.setAddress(address);
        currentUser.setPhotoUrl(photoUrl);
        if(!password.equals("")){
            currentUser.setPassword(password);
        }
    }

    @FXML
    private void saveButtonPressed(){
        User currentUser = DataStore.currentUser;
        updateUser(currentUser, nameTextField.getText(), emailTextField.getText(), phoneTextField.getText(),
                addressTextField.getText(), photoUrlTextField.getText(), passwordTextField.getText());

        displayUserDetails();
        feedbackLabel.setText("Update Successful");


    }

}
