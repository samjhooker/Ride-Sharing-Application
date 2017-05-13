package models;

import java.util.UUID;

/**
 * Created by samuelhooker on 26/04/17.
 */
public class Pickup {

    public enum RideStatus {COMPLETED, BOOKED, CANCELLED}

    public Pickup(StopAndTime stopAndTime, UUID userID, RideStatus rideStatus) {
        this.stopAndTime = stopAndTime;
        this.userID = userID;
        this.rideStatus = rideStatus;
    }

    public StopAndTime getStopAndTime() {
        return stopAndTime;
    }

    public void setStopAndTime(StopAndTime stopAndTime) {
        this.stopAndTime = stopAndTime;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    @Override
    public String toString() {
        return rideStatus.toString() + " at " +stopAndTime.toString();
    }

    private StopAndTime stopAndTime;
    private UUID userID;
    private RideStatus rideStatus;

}
