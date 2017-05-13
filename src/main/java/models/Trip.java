package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by samuelhooker on 27/03/17.
 */
public class Trip {
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public ObservableList<StopAndTime>  getStopsAndTimes() {
        ObservableList<StopAndTime> st = FXCollections.observableArrayList();
        st.addAll(this.stopsAndTimes);
        return st;
    }

    public void setStopsAndTimes(List<StopAndTime>  stopsAndTimes) {
        this.stopsAndTimes.addAll(stopsAndTimes);
    }


    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public ObservableList<DayOfWeek> getDays() {
        ObservableList<DayOfWeek> st = FXCollections.observableArrayList();
        st.addAll(this.days);
        return st;
    }

    public void setDays(List<DayOfWeek> days) {
        this.days.addAll(days);
    }

    public Boolean getDirection() {
        return direction;
    }

    public void setDirection(Boolean direction) {
        this.direction = direction;
    }


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }


    public Trip(Car car, Route route, List<StopAndTime> stopsAndTimes, LocalDate expiryDate, List<DayOfWeek> days, Boolean direction, UUID userId) {
        this.car = car;
        this.route = route;
        this.stopsAndTimes.addAll(stopsAndTimes);
        this.expiryDate = expiryDate;
        this.days.addAll(days);
        this.direction = direction;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return route.getName() + " Trip";
    }

    private Car car;
    private Route route;
    private ArrayList<StopAndTime> stopsAndTimes = new ArrayList<>();
    private LocalDate expiryDate;
    private ArrayList<DayOfWeek> days = new ArrayList<>();
    private Boolean direction;
    private UUID userId;

}
