package models;

import java.time.LocalDate;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;


/**
 * Created by samuelhooker on 20/03/17.
 */
public class Car {
    private String make;


    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return make + " "+ model;
    }

    public LocalDate getWofExpiryDate() {
        return wofExpiryDate;
    }

    public void setWofExpiryDate(LocalDate wofExpiryDate) {
        this.wofExpiryDate = wofExpiryDate;
    }

    public LocalDate getRegistrationExpiryDate() {
        return registrationExpiryDate;
    }

    public void setRegistrationExpiryDate(LocalDate registrationExpiryDate) {
        this.registrationExpiryDate = registrationExpiryDate;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }


    public boolean showWofNotification(){
        //notification shown 30, 16 and 9 days before
        LocalDate now = LocalDate.now();
        int daysBetween = (int)DAYS.between(now, wofExpiryDate);

        if(daysBetween == 30 && !showWof30dayNotification){
            showWof30dayNotification = true;
            return true;
        }
        if(daysBetween == 16 && !showWof16dayNotification){
            showWof16dayNotification = true;
            return true;
        }
        if(daysBetween == 9 && !showWof9dayNotification){
            showWof16dayNotification = true;
            return true;
        }
        return false;

    }

    public boolean showRegistrationNotification(){
        //notification shown 30, 16 and 9 days before
        //notification shown 30, 16 and 9 days before
        LocalDate now = LocalDate.now();
        int daysBetween = (int)DAYS.between(now, registrationExpiryDate);

        if(daysBetween == 30 && !showRegistration30dayNotification){
            showRegistration30dayNotification = true;
            return true;
        }
        if(daysBetween == 16 && !showRegistration16dayNotification){
            showRegistration16dayNotification = true;
            return true;
        }
        if(daysBetween == 9 && !showRegistration9dayNotification){
            showRegistration9dayNotification = true;
            return true;
        }
        return false;


    }


    public Car(String make, String model, String color, String licence, int year, int numberOfSeats, LocalDate wofExpiryDate, LocalDate registrationExpiryDate, UUID userID) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.licence = licence;
        this.year = year;
        this.numberOfSeats = numberOfSeats;
        this.wofExpiryDate = wofExpiryDate;
        this.registrationExpiryDate = registrationExpiryDate;
        this.userID = userID;
    }

    private String model;
    private String color;
    private String licence;
    private int year;
    private int numberOfSeats;
    private LocalDate wofExpiryDate;
    private LocalDate registrationExpiryDate;
    private UUID userID;

    private Boolean showWof30dayNotification= false;
    private Boolean showWof16dayNotification= false;
    private Boolean showWof9dayNotification= false;

    private Boolean showRegistration30dayNotification = false;
    private Boolean showRegistration16dayNotification = false;
    private Boolean showRegistration9dayNotification = false;


}
