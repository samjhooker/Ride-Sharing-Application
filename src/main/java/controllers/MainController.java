package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.User;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by samschofield on 15/03/17.
 */
public class MainController implements Initializable {


    @FXML
    private javafx.scene.image.ImageView driverButton;
    @FXML
    private javafx.scene.image.ImageView  passengerButton;
    @FXML
    private AnchorPane contentAnchorPane;


    @FXML
    private Circle profilePictureCircle;
    @FXML
    private Label nameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(DataStore.currentUser == null) {showLogin();}
        else{ showUserDetails();}
    }

    @FXML
    public void driverButtonPressed(){

        if(DataStore.currentUser == null) {showLogin(); return;}
        if(DataStore.currentUser.getDriverUser() == null) {showDriverRegistration(); return;}

        Node node;
        try {

            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getClassLoader().getResource("driver.fxml");
            node = loader.load(fxmlLocation.openStream());
            node.setUserData(fxmlLocation);
            loader.setLocation(fxmlLocation);
            contentAnchorPane.getChildren().setAll(node);
            contentAnchorPane.setTopAnchor(node,0.0);
            contentAnchorPane.setBottomAnchor(node,0.0);
            contentAnchorPane.setLeftAnchor(node,0.0);
            contentAnchorPane.setRightAnchor(node,0.0);

            DriverController graphController = loader.getController();
            //graphController.sayHello();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    public void passengerButtonPressed(){

        if(DataStore.currentUser == null) {showLogin(); return;}

        Node node;
        try {

            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getClassLoader().getResource("passenger.fxml");
            node = loader.load(fxmlLocation.openStream());
            node.setUserData(fxmlLocation);
            loader.setLocation(fxmlLocation);
            contentAnchorPane.getChildren().setAll(node);
            contentAnchorPane.setTopAnchor(node,0.0);
            contentAnchorPane.setBottomAnchor(node,0.0);
            contentAnchorPane.setLeftAnchor(node,0.0);
            contentAnchorPane.setRightAnchor(node,0.0);

            PassengerController graphController = loader.getController();
            //graphController.sayHello();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void showLogin(){
        Node node;
        try {

            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getClassLoader().getResource("login.fxml");
            node = loader.load(fxmlLocation.openStream());
            node.setUserData(fxmlLocation);
            loader.setLocation(fxmlLocation);
            contentAnchorPane.getChildren().setAll(node);
            contentAnchorPane.setTopAnchor(node,0.0);
            contentAnchorPane.setBottomAnchor(node,0.0);
            contentAnchorPane.setLeftAnchor(node,0.0);
            contentAnchorPane.setRightAnchor(node,0.0);

            LoginController graphController = loader.getController();
            graphController.setMainController(this);



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void showDriverRegistration(){
        Node node;
        try {

            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getClassLoader().getResource("driverRegister.fxml");
            node = loader.load(fxmlLocation.openStream());
            node.setUserData(fxmlLocation);
            loader.setLocation(fxmlLocation);
            contentAnchorPane.getChildren().setAll(node);
            contentAnchorPane.setTopAnchor(node,0.0);
            contentAnchorPane.setBottomAnchor(node,0.0);
            contentAnchorPane.setLeftAnchor(node,0.0);
            contentAnchorPane.setRightAnchor(node,0.0);

            DriverRegistrationConroller graphController = loader.getController();
            graphController.setMainController(this);



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void showUserDetails(){
        User user = DataStore.currentUser;
        if(user == null){return;}
        if(user.getPhotoUrl().isEmpty()){
            profilePictureCircle.setFill(new ImagePattern(new Image("http://s3.amazonaws.com/37assets/svn/765-default-avatar.png")));
        }else{
            try{
                profilePictureCircle.setFill(new ImagePattern(new Image(user.getPhotoUrl())));
            }catch (Exception e){
                profilePictureCircle.setFill(new ImagePattern(new Image("http://s3.amazonaws.com/37assets/svn/765-default-avatar.png")));

            }

        }

        String name;
        if(user.getName().contains(" ")){
            name = user.getName().substring(0, user.getName().indexOf(" "));
        }
        else{
            name = user.getName();
        }

        nameLabel.setText(name);
    }


    @FXML
    private void profilePictureCirclePressed(){

        if(DataStore.currentUser == null) {showLogin(); return;}

        Node node;
        try {

            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getClassLoader().getResource("updateUser.fxml");
            node = loader.load(fxmlLocation.openStream());
            node.setUserData(fxmlLocation);
            loader.setLocation(fxmlLocation);
            contentAnchorPane.getChildren().setAll(node);
            contentAnchorPane.setTopAnchor(node,0.0);
            contentAnchorPane.setBottomAnchor(node,0.0);
            contentAnchorPane.setLeftAnchor(node,0.0);
            contentAnchorPane.setRightAnchor(node,0.0);

            //DriverRegistrationConroller graphController = loader.getController();
            //graphController.setMainController(this);



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
