package models;

import java.util.UUID;

/**
 * Created by samuelhooker on 20/03/17.
 */
public class Stop {

    public Stop(String streetNumber, String address, String suburb, UUID userId) {
        this.streetNumber = streetNumber;
        this.address = address;
        this.suburb = suburb;
        this.userId = userId;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    private String suburb;

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
        return streetNumber + " " + address+ ", " + suburb;
    }




    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    private UUID userId;
    private String streetNumber;
    private String address;




}
