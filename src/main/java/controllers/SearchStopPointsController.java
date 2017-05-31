package controllers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import controllers.DataStore;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.*;
import utils.MapUtils;

import javax.xml.crypto.Data;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Created by samuelhooker on 4/04/17.
 */
public class SearchStopPointsController implements Initializable, MapComponentInitializedListener{

    private static final double UC_LATITUDE = -43.5235;
    private static final double UC_LONGITUDE = 172.5839;


    @FXML
    private ListView stopsListView;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button findDatesAndTimesButton;

    @FXML
    private TableView tripsTableView;
    @FXML
    private TableColumn timeColumn;
    @FXML
    private TableColumn dateColumn;
    @FXML
    private TableColumn directionColumn;

    @FXML
    private Label makeLabel;
    @FXML
    private Label seatsLabel;
    @FXML
    private Label colorLabel;
    @FXML
    private Label seatRemainingLabel;
    @FXML
    private Label costLabel;

    @FXML
    private AnchorPane tripsAnchorPane;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;



    private ObservableList<Stop> stops = FXCollections.observableArrayList();
    Stop selectedStop;
    public LiveTrip selectedLiveTrip;
    private StopAndTime selectedStopAndTime;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        findDatesAndTimesButton.disableProperty().setValue(true);

        for (LiveTrip liveTrip: DataStore.liveTrips){
            ObservableList<Stop> liveTripStops = liveTrip.getRoute().getStops();
            for(Stop stop : liveTripStops){
                if(!stops.contains(stop)){
                    stops.add(stop);
                }
            }
        }

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshStopsListView();
        });

        stopsListView.setItems(stops.sorted());



        stopsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stop>() {
            @Override
            public void changed(ObservableValue<? extends Stop> observable, Stop oldValue, Stop newValue) {
                findDatesAndTimesButton.disableProperty().setValue(false);
                selectedStop = newValue;

            }
        });


        tripsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                TableObject option = (TableObject) newSelection;
                Trip trip = option.getLiveTrip().getTrip();

                makeLabel.setText(trip.getCar().toString());
                colorLabel.setText(trip.getCar().getColor());
                seatsLabel.setText(Integer.toString(trip.getCar().getNumberOfSeats()));
                seatRemainingLabel.setText(Integer.toString(option.getLiveTrip().getSeats()));
                Stop stop = option.getStopAndTime().getStop();
                double distance = MapUtils.distanceToUni(stop.getLatitude(), stop.getLongitude());
                double cost = MapUtils.calculatePrice(distance, trip.getCar().getLitresPer100km());
                costLabel.setText(String.format("$%.2f", cost));

                selectedLiveTrip = option.getLiveTrip();
                selectedStopAndTime = option.getStopAndTime();
            }
        });

        mapView.addMapInializedListener(this);


    }


    @Override
    public void mapInitialized() {



        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(UC_LATITUDE, UC_LONGITUDE))
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);
        loadPointsOnMap();


    }

    private void loadPointsOnMap(){
        System.out.println("reloading");
        map.clearMarkers();
        ObservableList<Stop> stops = stopsListView.getItems();
        for(Stop stop : stops){
            LatLong latLong = new LatLong(stop.getLatitude(), stop.getLongitude());

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLong);

            Marker marker = new Marker(markerOptions);
            map.addMarker( marker );


        }

    }



    public class TableObject{
        public LocalDate getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getDirection() {
            return Direction;
        }

        public LiveTrip getLiveTrip() {
            return trip;
        }

        public TableObject(LocalDate date, String time, String direction, LiveTrip trip, StopAndTime stopAndTime) {
            this.date = date;
            this.time = time;
            Direction = direction;
            this.trip = trip;
            this.stopAndTime = stopAndTime;
        }

        private LocalDate date;
        private String time;
        private String Direction;
        private LiveTrip trip;

        public StopAndTime getStopAndTime() {
            return stopAndTime;
        }

        private StopAndTime stopAndTime;



    }

    private void refreshStopsListView(){
        ObservableList<Stop> filteredStops = FXCollections.observableArrayList();
        filteredStops.clear();
        String searchString = searchTextField.getText();
        if(searchString.equals("")){
            stopsListView.setItems(stops.sorted());
            return;
        }

        for(Stop stop : stops.sorted()){
            if (stop.toString().indexOf(searchString) != -1) {
                filteredStops.add(stop);
            }
        }
        stopsListView.setItems(filteredStops);
        loadPointsOnMap();
    }

    @FXML
    public void findDatesAndTimesButtonPressed(){
        if(findDatesAndTimesButton.getText().equals("Back")){
            tripsAnchorPane.setVisible(false);
            findDatesAndTimesButton.setText("Find Dates and Times");
        }else{
            tripsAnchorPane.setVisible(true);
            findDatesAndTimesButton.setText("Back");

            if(selectedStop != null){
                timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
                dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
                directionColumn.setCellValueFactory(new PropertyValueFactory<>("direction"));

                ObservableList<TableObject> tableData = FXCollections.observableArrayList();
                tableData.clear();
                for(LiveTrip lt: DataStore.liveTrips){
                    for(StopAndTime st :lt.getTrip().getStopsAndTimes()){
                        if (st.getStop().equals(selectedStop) &&
                                lt.getSeats() >0 &&
                                (lt.getStatus() == null || lt.getStatus().equals(LiveTrip.TripStatus.ACTIVE))){
                            tableData.add(new TableObject(lt.getDate(), st.getTime(), lt.getDirection(), lt, st));
                        }
                    }

                }
                tripsTableView.setItems(tableData);
            }


        }


    }

    public void bookRide(LiveTrip liveTrip){

        Pickup pickup;
        try{
            pickup = new Pickup(selectedStopAndTime, DataStore.currentUser.getUserId(), Pickup.RideStatus.BOOKED);
        }catch (NullPointerException e){
            //test case
            pickup = new Pickup(selectedStopAndTime, UUID.randomUUID(), Pickup.RideStatus.BOOKED);
        }
        List<Pickup> pickups = liveTrip.getPickups();
        pickups.add(pickup);
        liveTrip.setPickups(pickups);
        liveTrip.setSeats(liveTrip.getSeats()-1);
    }

    @FXML
    private void bookRideButtonPressed(){
        if(selectedLiveTrip != null){
            bookRide(selectedLiveTrip);
            findDatesAndTimesButtonPressed();
        }
    }
}
