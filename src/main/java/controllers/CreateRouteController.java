package controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import models.Route;
import models.Stop;
import utils.MapUtils;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by samuelhooker on 20/03/17.
 */
public class CreateRouteController implements Initializable {

    @FXML
    private TextField numberTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField routeNameTextField;
    @FXML
    private ListView routeListView;
    @FXML
    private Label submitRouteFeedbackLabel;
    @FXML
    private TextField suburbTextField;

    private ObservableList<Stop> selectedStops = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.routeListView.getSelectionModel().clearSelection();

        routeListView.setCellFactory(CheckBoxListCell.forListView(new Callback<Stop, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Stop item) {
                BooleanProperty observable = new SimpleBooleanProperty();
                observable.addListener((obs, wasSelected, isNowSelected) ->
                        stopSelected(item)

                );
                return observable ;
            }
        }));
        routeListView.setItems(DataStore.getStops());



    }

    private void stopSelected(Stop stop){
        if(selectedStops.contains(stop)){
            selectedStops.remove(stop);
        }else{
            selectedStops.add(stop);
        }
    }

    @FXML
    private void stopPointSubmitPressed(){
        if(!numberTextField.getText().isEmpty()
                && !addressTextField.getText().isEmpty()
                && !suburbTextField.getText().isEmpty()){

            Stop stop = MapUtils.getStopFromAddress(numberTextField.getText() + " "+
                                                            addressTextField.getText() + ", "+
                                                            suburbTextField.getText());

            numberTextField.setText("");
            addressTextField.setText("");
            suburbTextField.setText("");
            if(stop != null){
                DataStore.stops.add(stop);
            }else{
                suburbTextField.setText("Invalid Address");
            }

            routeListView.setItems(DataStore.getStops());
        }
    }

    public Route createRoute(ObservableList selectedStops, String name){

        if(name != "" &&
                selectedStops.size() != 0){
            UUID userId;
            try{
                userId = DataStore.currentUser.getUserId();
            }catch (NullPointerException e){
                userId= UUID.randomUUID();
            }


            Route route = new Route(selectedStops, name, userId);
            return route;
        }
        return null;



    }

    @FXML
    private void submitRouteButtonPressed(){
        Route route = createRoute(selectedStops, routeNameTextField.getText());

        if(route != null){
            DataStore.routes.add(route);
            submitRouteFeedbackLabel.setText("Route Creation Successful");
            routeNameTextField.setText("");
        }
        else {
            submitRouteFeedbackLabel.setText("Route Name and Points Required");

        }

    }

}
