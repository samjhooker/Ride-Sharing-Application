package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import models.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by samuelhooker on 29/03/17.
 */
public class ShareRideController implements Initializable {

    @FXML
    private ComboBox tripComboBox;
    @FXML
    private TextField seatsTextField;
    @FXML
    private TableView tripsTableView;

    @FXML
    private Label confirmFeedbackLabel;
    @FXML
    private TableColumn stopsColumn;
    @FXML
    private TableColumn dateColumn;
    @FXML
    private TableColumn directionColumn;
    @FXML
    private TableColumn seatsColumn;
    @FXML
    private TableColumn routeColumn;
    @FXML
    private TableColumn statusColumn;


    @FXML
    private ListView selectedTripListView;

    private LiveTrip selectedLiveTrip;






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tripComboBox.setItems(DataStore.getTrips());
        seatsTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    seatsTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        stopsColumn.setCellValueFactory(new PropertyValueFactory<>("stops"));
        routeColumn.setCellValueFactory(new PropertyValueFactory<>("route"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        directionColumn.setCellValueFactory(new PropertyValueFactory<>("direction"));


        tripsTableView.setItems(DataStore.getLiveTrips());

        tripsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedLiveTrip = (LiveTrip) newSelection;
                updateSelectedTripListView();
            }
        });



    }
    private void updateSelectedTripListView(){
        ObservableList pickups = FXCollections.observableArrayList();
        pickups.addAll(selectedLiveTrip.getPickups());
        selectedTripListView.setItems(pickups);
    }

    private boolean validateInput(String text)
    {
        return text.matches("[0-9]*");
    }


    @FXML
    public void confirmButtonPressed(){

        if(tripComboBox.getValue() == null){
            confirmFeedbackLabel.setText("Select a Trip");
            return;
        }
        Trip trip = (Trip) tripComboBox.getValue();

        if(!validateInput(seatsTextField.getText()) && seatsTextField.getText().equals("")){
            confirmFeedbackLabel.setText("Enter Number of Seats");
            return;
        }

        int seats;
        try{
            seats = Integer.parseInt(seatsTextField.getText());
        }catch (Exception e){
            confirmFeedbackLabel.setText("Input Invalid");
            return;
        }
        if(seats > trip.getCar().getNumberOfSeats()){
            confirmFeedbackLabel.setText("Car does not have "+seats+" seats");
            return;
        }

        createLiveTrips(trip, seats);



    }

    public void createLiveTrips(Trip trip, int seats){
        for (LocalDate date = LocalDate.now(); date.isBefore(trip.getExpiryDate().plusDays(1)); date = date.plusDays(1)) {
            if((date==trip.getExpiryDate() && trip.getDays().size() ==0)||
                    (trip.getDays().contains(date.getDayOfWeek()))){
                LiveTrip liveTrip = new LiveTrip(trip, date, seats, DataStore.currentUser.getUserId());
                DataStore.liveTrips.add(liveTrip);
                try{
                    tripsTableView.setItems(DataStore.getLiveTrips());
                }catch(NullPointerException e){
                }

            }
        }
    }

    @FXML
    private void cancelTripButtonPressed() {

        if (selectedLiveTrip != null) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Trip Cancel");
            dialog.setHeaderText("Are you sure you want to cancel the trip for yourself and your passengers?\nThis could result in a poor driver review!");
            dialog.setContentText("Please provide reason:");


            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                cancelLiveTrip(selectedLiveTrip);
                tripsTableView.setItems(DataStore.getLiveTrips());


                for(Pickup pickup : selectedLiveTrip.getPickups()){
                    DataStore.notifications.add(new Notification("One of your drivers have cancelled your ride\nReason:" + result, pickup.getUserID()));
                }


            }

        }
    }

    public void cancelLiveTrip(LiveTrip liveTrip){
        liveTrip.setStatus(LiveTrip.TripStatus.CANCELLED);
        for(Pickup pickup: liveTrip.getPickups()){
            pickup.setRideStatus(Pickup.RideStatus.CANCELLED);
        }

    }
}



