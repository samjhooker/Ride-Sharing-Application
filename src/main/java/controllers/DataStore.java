package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import models.*;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by samuelhooker on 20/03/17.
 */
public class DataStore{


    public static User currentUser;
    public static ObservableList<Car> cars = FXCollections.observableArrayList();
    public static ObservableList<Stop> stops = FXCollections.observableArrayList();
    public static ObservableList<Route> routes = FXCollections.observableArrayList();
    public static ObservableList<Trip> trips = FXCollections.observableArrayList();
    public static ObservableList<LiveTrip> liveTrips = FXCollections.observableArrayList();
    public static ObservableList<User> users = FXCollections.observableArrayList();
    public static ObservableList<Pickup> pickups = FXCollections.observableArrayList();
    public static ObservableList<Notification> notifications = FXCollections.observableArrayList();


    public static ObservableList<Car> getCars() {
        List<Car> filtered = cars.stream()
                .filter(car -> car.getUserID().equals(currentUser.getUserId()))
                .collect(Collectors.toList());
        ObservableList<Car> returnable = FXCollections.observableArrayList();
        returnable.addAll(filtered);
        return returnable;
    }

    public static ObservableList<Stop> getStops() {
        List<Stop> filtered = stops.stream()
                .filter(car -> car.getUserId().equals(currentUser.getUserId()))
                .collect(Collectors.toList());
        ObservableList<Stop> returnable = FXCollections.observableArrayList();
        returnable.addAll(filtered);
        return returnable;
    }

    public static ObservableList<Route> getRoutes() {
        List<Route> filtered = routes.stream()
                .filter(car -> car.getUserId().equals(currentUser.getUserId()))
                .collect(Collectors.toList());
        ObservableList<Route> returnable = FXCollections.observableArrayList();
        returnable.addAll(filtered);
        return returnable;    }

    public static ObservableList<Trip> getTrips() {
        List<Trip> filtered = trips.stream()
                .filter(car -> car.getUserId().equals(currentUser.getUserId()))
                .collect(Collectors.toList());
        ObservableList<Trip> returnable = FXCollections.observableArrayList();
        returnable.addAll(filtered);
        return returnable;    }

    public static ObservableList<LiveTrip> getLiveTrips() {
        List<LiveTrip> filtered = liveTrips.stream()
                .filter(car -> car.getUserId().equals(currentUser.getUserId()))
                .collect(Collectors.toList());
        ObservableList<LiveTrip> returnable = FXCollections.observableArrayList();
        returnable.addAll(filtered);
        return returnable;     }

    public static ObservableList<Notification> getNotifications() {
        List<Notification> filtered = notifications.stream()
                .filter(car -> car.getUserId().equals(currentUser.getUserId()))
                .collect(Collectors.toList());
        ObservableList<Notification> returnable = FXCollections.observableArrayList();
        returnable.addAll(filtered);
        return returnable;     }
//
//    public static ObservableList<User> getUsers() {
//        //return users.removeIf(p -> !p.getUserID().equals(currentUser.getUserId());
//    }

    static List<List> ALL_LISTS = new ArrayList(Arrays.asList(cars, stops, routes, trips, liveTrips, users, pickups, notifications));
    static List<String> ALL_LIST_NAMES = new ArrayList(Arrays.asList("cars", "stops", "routes", "trips", "liveTrips", "users", "pickups", "notifications"));
    static List<String> IMPORT_BUFFERS = new ArrayList(Arrays.asList("", "", "", "", "", "", "", ""));



    public static void loadData(){


        for(int i = 0; i< ALL_LISTS.size(); i++){
            try {

                ClassLoader classLoader = DataStore.class.getClassLoader();
                File myFile = new File(classLoader.getResource("data/"+ ALL_LIST_NAMES.get(i) +".txt").getFile());
                FileInputStream fIn = new FileInputStream(myFile);
                BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                String aDataRow = "";
                while ((aDataRow = myReader.readLine()) != null) {
                    String buffer = IMPORT_BUFFERS.get(i);
                    IMPORT_BUFFERS.set(i, (buffer += aDataRow));
                }
                myReader.close();




            }catch (IOException e){
                System.out.println("Error with retrieving Data");
                System.out.println(e);
            }
        }

        List<Car> importedCars  = new Gson().fromJson(IMPORT_BUFFERS.get(0), new TypeToken<ObservableList<Car>>(){}.getType());
        if(importedCars != null){
            cars.addAll(importedCars);
        }

        List<Stop> importedStops  = new Gson().fromJson(IMPORT_BUFFERS.get(1), new TypeToken<ObservableList<Stop>>(){}.getType());
        if(importedStops != null){
            stops.addAll(importedStops);
        }

        List<Route> importedRoutes  = new Gson().fromJson(IMPORT_BUFFERS.get(2), new TypeToken<ObservableList<Route>>(){}.getType());
        System.out.println(importedRoutes);
        if(importedRoutes != null){
            routes.addAll(importedRoutes);
        }

        List<Trip> importedTrips  = new Gson().fromJson(IMPORT_BUFFERS.get(3), new TypeToken<ObservableList<Trip>>(){}.getType());
        if(importedTrips != null){
            trips.addAll(importedTrips);
        }

        List<LiveTrip> importedLiveTrips  = new Gson().fromJson(IMPORT_BUFFERS.get(4), new TypeToken<ObservableList<LiveTrip>>(){}.getType());
        if(importedLiveTrips != null){
            liveTrips.addAll(importedLiveTrips);
        }

        List<User> importedUsers  = new Gson().fromJson(IMPORT_BUFFERS.get(5), new TypeToken<ObservableList<User>>(){}.getType());
        if(importedUsers != null){
            users.addAll(importedUsers);
        }

        List<Pickup> importedPickups  = new Gson().fromJson(IMPORT_BUFFERS.get(6), new TypeToken<ObservableList<Pickup>>(){}.getType());
        if(importedPickups != null){
            pickups.addAll(importedPickups);
        }

        List<Notification> importedNotifications  = new Gson().fromJson(IMPORT_BUFFERS.get(7), new TypeToken<ObservableList<Notification>>(){}.getType());
        if(importedPickups != null){
            notifications.addAll(importedNotifications);
        }



    }

    public static void saveData() {

        for (int i = 0; i < ALL_LISTS.size(); i++) {
            try {

                String json = new Gson().toJson(ALL_LISTS.get(i));
                ClassLoader classLoader = DataStore.class.getClassLoader();
                File myFile = new File(classLoader.getResource("data/" + ALL_LIST_NAMES.get(i) + ".txt").getFile());
                myFile.createNewFile();
                FileOutputStream fOut = new FileOutputStream(myFile);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                myOutWriter.append(json);
                myOutWriter.close();
                fOut.close();

            } catch (IOException e) {
                System.out.println("Error with Saving Data");
                System.out.println(e);
            }


        }
    }


}
