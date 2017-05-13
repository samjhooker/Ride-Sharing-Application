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
 * Created by samuelhooker on 4/04/17.
 */
public class PassengerController implements Initializable {

    @FXML
    private ListView actionList;
    @FXML
    private AnchorPane passengerContentAnchorPane;

    private ObservableList<String> actions = FXCollections.observableArrayList(
            "Search Stop Points", "My Rides");



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.actionList.getSelectionModel().clearSelection();
        actionList.setItems(actions);



        actionList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                if (newValue != null) {
                    switch (newValue) {
                        case "Search Stop Points":
                            showPage("searchStopPoints.fxml");
                            break;
                        case "My Rides":
                            showPage("passengerRides.fxml");
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
            passengerContentAnchorPane.getChildren().setAll(node);
            passengerContentAnchorPane.setTopAnchor(node,0.0);
            passengerContentAnchorPane.setBottomAnchor(node,0.0);
            passengerContentAnchorPane.setLeftAnchor(node,0.0);
            passengerContentAnchorPane.setRightAnchor(node,0.0);

            //DriverController graphController = loader.getController();
            //graphController.sayHello();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
