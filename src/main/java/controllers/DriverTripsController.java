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
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.*;

import java.beans.EventHandler;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by samuelhooker on 21/03/17.
 */
public class DriverTripsController implements Initializable {

    @FXML
    private Label routeLabel;
    @FXML
    private Label directionLabel;
    @FXML
    private Label reoccuringLabel;
    @FXML
    private Label expiryLabel;
    @FXML
    private Label carLabel;
    @FXML
    private Label submitButtonFeedbackLabel;


    @FXML
    private ComboBox routeComboBox;
    @FXML
    private ComboBox carComboBox;
    @FXML
    private ComboBox directionComboBox;


    @FXML
    private CheckBox repeatCheckBox;
    @FXML
    private CheckBox monCheckBox;
    @FXML
    private CheckBox tueCheckBox;
    @FXML
    private CheckBox wedCheckBox;
    @FXML
    private CheckBox thuCheckBox;
    @FXML
    private CheckBox friCheckBox;
    @FXML
    private CheckBox satCheckBox;
    @FXML
    private CheckBox sunCheckBox;

    private ArrayList<CheckBox> dayCheckBoxes = new ArrayList<>();


    @FXML
    private DatePicker expiryDatePicker;

    @FXML
    private ListView myTripsListView;
    @FXML
    private TableView createTripTableView;
    @FXML
    private TableColumn<StopAndTime, String> stopTableColumn;
    @FXML
    private TableColumn<StopAndTime, String> timeTableColumn;


    @FXML
    private AnchorPane repeatAnchorPane;


    ObservableList<StopAndTime> stopAndTimes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //this.myTripsListView.getSelectionModel().clearSelection();
        //myTripsListView.setItems(DataStore.trips);

        myTripsListView.setItems(DataStore.getTrips());

        myTripsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Trip trip = (Trip) newValue;
                routeLabel.setText(trip.getRoute().toString());
                String dir = "To University";
                if (!trip.getDirection()){
                    dir = "From University";
                }
                directionLabel.setText(dir);
                reoccuringLabel.setText(trip.getDays().toString());
                expiryLabel.setText(trip.getExpiryDate().getDayOfMonth() +"/"+ trip.getExpiryDate().getMonthValue()+"/"+ trip.getExpiryDate().getYear());
                carLabel.setText(trip.getCar().toString());
            }
        });


        dayCheckBoxes.add(monCheckBox);
        dayCheckBoxes.add(tueCheckBox);
        dayCheckBoxes.add(wedCheckBox);
        dayCheckBoxes.add(thuCheckBox);
        dayCheckBoxes.add(friCheckBox);
        dayCheckBoxes.add(satCheckBox);
        dayCheckBoxes.add(sunCheckBox);


        routeComboBox.setItems(DataStore.getRoutes());
        carComboBox.setItems(DataStore.getCars());
        ObservableList<String> directions = FXCollections.observableArrayList();
        directions.add(0, "To University");
        directions.add(1, "From University");
        directionComboBox.setItems(directions);


        repeatCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(repeatAnchorPane.isDisabled()){
                    repeatAnchorPane.setDisable(false);
                }else{
                    repeatAnchorPane.setDisable(true);
                }
            }
        });



        //table set up
        routeComboBox.valueProperty().addListener(new ChangeListener<Route>() {
            @Override public void changed(ObservableValue ov, Route oldValue, Route newValue) {


                stopAndTimes = FXCollections.observableArrayList();
                stopAndTimes.clear();
                for(Stop stop: newValue.getStops()){
                    stopAndTimes.add(new StopAndTime(stop));
                }

                stopTableColumn.setCellValueFactory(new PropertyValueFactory<>("stop"));
                timeTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
                timeTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                timeTableColumn.setOnEditCommit(event -> {
                    StopAndTime st  = event.getTableView().getItems().get(event.getTablePosition().getRow());
                    try{
                        LocalTime localTime = LocalTime.parse(event.getNewValue());
                        st.setTime(localTime.toString());
                    }catch (Exception e){
                        st.setTime("Invalid");
                    }
                    //.setTime(event.getNewValue());
                });


                createTripTableView.setItems(stopAndTimes);


            }
        });




    }



    @FXML
    private void submitButtonPressed(){

        Route route = null;
        if(!routeComboBox.getSelectionModel().isEmpty()){
            route = (Route) routeComboBox.getSelectionModel().getSelectedItem();
        }else{
            submitButtonFeedbackLabel.setText("Please Select Route");
            return;
        }

        Car car = null;
        if(!carComboBox.getSelectionModel().isEmpty()){
            car = (Car) carComboBox.getSelectionModel().getSelectedItem();
        }else{
            submitButtonFeedbackLabel.setText("Please Select Car");
            return;
        }

        Boolean direction = null;
        if(!directionComboBox.getSelectionModel().isEmpty()){
            int index = directionComboBox.getSelectionModel().getSelectedIndex();
            if(index == 0){
                //to uni
                direction = true;
            }
            else{
                direction =false;
            }
        }else{
            submitButtonFeedbackLabel.setText("Please Select Direction");
            return;
        }

        for(StopAndTime stop : stopAndTimes){
            if(stop.getTime().equals("") || stop.getTime().equals("Invalid")){
                createTripTableView.setItems(null);
                createTripTableView.setItems(stopAndTimes);
                submitButtonFeedbackLabel.setText("Please Select A Valid HH:MM Time for Each Stop");
                return;
            }
        }

        ObservableList<DayOfWeek> days = FXCollections.observableArrayList();
        LocalDate date = LocalDate.now();
        if(repeatCheckBox.isSelected()){
            for (CheckBox checkBox: dayCheckBoxes){
                if(checkBox.isSelected()){
                    switch (checkBox.getText()){
                        case "Mon":
                            days.add(DayOfWeek.MONDAY);break;
                        case "Tue":
                            days.add(DayOfWeek.TUESDAY);break;
                        case "Wed":
                            days.add(DayOfWeek.WEDNESDAY);break;
                        case "Thu":
                            days.add(DayOfWeek.THURSDAY);break;
                        case "Fri":
                            days.add(DayOfWeek.FRIDAY);break;
                        case "Sat":
                            days.add(DayOfWeek.SATURDAY);break;
                        case "Sun":
                            days.add(DayOfWeek.SUNDAY);break;
                        default:
                            days.add(DayOfWeek.MONDAY);break;
                    }
                }
            }

            if(expiryDatePicker.getValue() != null){
                date = expiryDatePicker.getValue();
            }
        }
        if(days.isEmpty()){
            days.add(date.getDayOfWeek());
        }

        Trip trip = new Trip(car, route, stopAndTimes, date, days, direction, DataStore.currentUser.getUserId());
        DataStore.trips.add(trip);
        myTripsListView.setItems(DataStore.getTrips());
        submitButtonFeedbackLabel.setText("Submitted");



    }






}
