package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by samuelhooker on 1/04/17.
 */
public class LiveTrip {

    public enum TripStatus{ACTIVE, COMPLETED, CANCELLED}

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setStops(ArrayList<StopAndTime> stops) {
        this.stops = stops;
    }

    public List<Pickup> getPickups() {
        return pickups;
    }

    public void setPickups(List<Pickup> pickups) {
        this.pickups = pickups;
    }

    public void setStops(List<StopAndTime> stops) {
        this.stops.addAll(stops);
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }
    public ObservableList<StopAndTime> getStops() {
        ObservableList<StopAndTime> st = FXCollections.observableArrayList();
        st.addAll(this.stops);
        return st;
    }


    public LiveTrip(Trip trip, LocalDate date, int seats, UUID userId) {

        this.trip = trip;
        this.date = date;
        this.seats = seats;
        ArrayList stopsArrayList = new ArrayList<>();
        try{
            stopsArrayList.addAll(trip.getStopsAndTimes());
        }catch(NullPointerException e){

        }
        this.stops = stopsArrayList;
        this.route = trip.getRoute();
        if(trip.getDirection()){
            this.direction = "To University";
        }else{
            this.direction = "From University";
        }
        this.userId = userId;

        this.status = TripStatus.ACTIVE;


    }

    private Trip trip;
    private LocalDate date;
    private int seats;




    private List<StopAndTime> stops = new ArrayList<>();
    private Route route;
    private String direction;
    private List<Pickup> pickups = new ArrayList<>();
    private UUID userId;


    private TripStatus status;

}
