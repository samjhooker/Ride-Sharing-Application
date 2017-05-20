package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import models.*;
import utils.MapUtils;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Created by samuelhooker on 26/04/17.
 */
public class PassengerRidesController implements Initializable {



    @FXML
    private Label timeLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label driverLabel;
    @FXML
    private Label carLabel;
    @FXML
    private Label costLabel;


    @FXML
    private ListView ridesListView;

    private ListViewObject selectedListViewObject;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ridesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ListViewObject>() {

            @Override
            public void changed(ObservableValue<? extends ListViewObject> observable, ListViewObject oldValue, ListViewObject newValue) {

                if(newValue != null){
                    selectedListViewObject = newValue;

                    timeLabel.setText(newValue.getPickup().getStopAndTime().getTime());
                    dateLabel.setText(newValue.getLiveTrip().getDate().toString());
                    Stop stop = newValue.getPickup().getStopAndTime().getStop();
                    Trip trip = newValue.getLiveTrip().getTrip();
                    addressLabel.setText(stop.toString());
                    statusLabel.setText(newValue.getPickup().getRideStatus().toString());
                    double distance = MapUtils.distanceToUni(stop.getLatitude(), stop.getLongitude());
                    double cost = MapUtils.calculatePrice(distance, trip.getCar().getLitresPer100km());
                    costLabel.setText(String.format("$%.2f", cost));

                    UUID creatorID = newValue.getLiveTrip().getUserId();
                    User creatorUser = null;
                    for (User user : DataStore.users){
                        if(user.getUserId().equals(creatorID)){
                            creatorUser = user;
                            break;
                        }
                    }

                    if(creatorUser != null){
                        driverLabel.setText(creatorUser.getName());
                        carLabel.setText(newValue.getLiveTrip().getTrip().getCar().toString());
                    }
                }







            }
        });

        populateList();
    }


    public void populateList(){
        ObservableList<ListViewObject> pickups = FXCollections.observableArrayList();

        for(LiveTrip liveTrip: DataStore.liveTrips){
            for(Pickup pickup: liveTrip.getPickups()){
                if(pickup.getUserID().equals(DataStore.currentUser.getUserId())){
                    pickups.add(new ListViewObject(pickup, liveTrip));
                }
            }
        }
        ridesListView.setItems(pickups);
    }

    class ListViewObject{

        public ListViewObject(Pickup pickup, LiveTrip liveTrip) {
            this.pickup = pickup;
            this.liveTrip = liveTrip;
        }

        public Pickup getPickup() {
            return pickup;
        }

        public LiveTrip getLiveTrip() {
            return liveTrip;
        }

        @Override
        public String toString() {
            return pickup.getRideStatus().toString() + " Trip "+ liveTrip.getDirection() +
                    ". "+pickup.getStopAndTime().getStop() + ". "+ liveTrip.getDate() +
                    ", "+ pickup.getStopAndTime().getTime();

        }

        private Pickup pickup;
        private LiveTrip liveTrip;

    }


    public void cancelPickup( Pickup pu, LiveTrip lt){
        pu.setRideStatus(Pickup.RideStatus.CANCELLED);
        lt.setSeats(lt.getSeats() +1 );
    }


    @FXML
    private void cancelRideButtonPressed(){
        if(selectedListViewObject != null){


            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Ride Cancel");
            dialog.setHeaderText("Are you sure you want to cancel the ride?\nThis could result in a poor passenger review!");
            dialog.setContentText("Please provide reason:");


            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                System.out.println("ride cancelled " + result.get());
                cancelPickup(selectedListViewObject.pickup, selectedListViewObject.liveTrip);
                populateList();

                DataStore.notifications.add(new Notification("One of your pickups have cancelled\nReason:" + result, selectedListViewObject.liveTrip.getUserId()));

            }

        }
    }




}
