package models;

import controllers.DataStore;

import java.util.UUID;

/**
 * Created by samuelhooker on 20/03/17.
 */
public class Stop {

    public Stop(String address, Double latitude, Double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        try{
            this.userId = DataStore.currentUser.getUserId();
        }catch(NullPointerException e){}

    }

    @Override
    public boolean equals(Object obj) {
        try {
            Stop stop = (Stop)obj;
            if (this.toString().equals(stop.toString())) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){return false;}

    }

    @Override
    public String toString() {
        return address;
    }

    public UUID getUserId() {
        return userId;
    }

    private UUID userId;
    private String address;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    private Double latitude;
    private Double longitude;





}
