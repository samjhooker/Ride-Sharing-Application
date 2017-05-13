package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by samuelhooker on 21/03/17.
 */
public class Route {

    public Route(ArrayList<Stop> stops, String name, UUID userId) {
        this.stops = stops;
        this.name = name;
        this.userId = userId;
    }

    public Route(ObservableList<Stop> stops, String name, UUID userId) {
        this.stops = new ArrayList<Stop>();
        this.stops.addAll(stops);
        this.name = name;
        this.userId = userId;
    }

    public ObservableList<Stop> getStops() {
        ObservableList<Stop> obsStops = FXCollections.observableArrayList();
        obsStops.addAll(stops);
        return obsStops;
    }

    public void setStops(ArrayList<Stop> stops) {
        this.stops = stops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name + " ("+ stops.size() + " stops)";
    }

    private String name;
    private ArrayList<Stop> stops;

    public UUID getUserId() {
        return userId;
    }

    private UUID userId;
}
