package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by samuelhooker on 20/03/17.
 */
public class DriverController  implements Initializable {

    @FXML
    private ListView actionList;
    @FXML
    private AnchorPane driverContentAnchorPane;

    private ObservableList<String> actions = FXCollections.observableArrayList(
            "Register A Car", "Create a Route", "My Trips", "Share A Ride");



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.actionList.getSelectionModel().clearSelection();
        actionList.setItems(actions);



        actionList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                if (newValue != null) {
                    switch (newValue) {
                        case "Register A Car":
                            showPage("registerCar.fxml");
                            break;
                        case "Create a Route":
                            showPage("createRoute.fxml");
                            break;
                        case "My Trips":
                            showPage("myTrips.fxml");
                            break;
                        case "Share A Ride":
                            showPage("shareRide.fxml");
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }





    private void showPage(String fxml){

        Node node;
        try {

            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = getClass().getClassLoader().getResource(fxml);
            node = loader.load(fxmlLocation.openStream());
            node.setUserData(fxmlLocation);
            loader.setLocation(fxmlLocation);
            driverContentAnchorPane.getChildren().setAll(node);
            driverContentAnchorPane.setTopAnchor(node,0.0);
            driverContentAnchorPane.setBottomAnchor(node,0.0);
            driverContentAnchorPane.setLeftAnchor(node,0.0);
            driverContentAnchorPane.setRightAnchor(node,0.0);

            //DriverController graphController = loader.getController();
            //graphController.sayHello();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
