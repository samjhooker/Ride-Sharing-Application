package models;

import controllers.DataStore;

import java.util.UUID;

/**
 * Created by samuelhooker on 23/04/17.
 */
public class User {

//    University ID and email, name, address, home number and/or mobile number;
//1. The email address must use @uclive.ac.nz or @canterbury.ac.nz from the
//    University of Canterbury (you do not need to check the email address exists,
//                              only that it uses the correct format and uses one of the domains specified);
//    iii. In the case of drivers:
//            1. Driver's licence data: type (restricted, full, full for 2 years, etc.), number, issue
//    date, expired date, etc;
//    iv. A photo;
//    v. A password - must be entered twice to ensure the user has entered it correctly.

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public boolean checkPasswordValid(String password){
        if (password.equals(this.password)){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean checkEmailValid(String e){
        if(e.toLowerCase().endsWith("uclive.ac.nz") || e.toLowerCase().endsWith("canterbury.ac.nz")){
            return true;
        }else{
            return false;
        }
    }

    public User(String studentId, String name, String email, String address, String phoneNumber, String photoUrl, String password) {
        String[] parts = email.split("@");
        this.studentId = studentId;
        this.username = parts[0];
        this.userId = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    private String username;
    private UUID userId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String photoUrl;
    private String studentId;

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public DriverUser getDriverUser() {
        return driverUser;
    }

    public void setDriverUser(DriverUser driverUser) {
        this.driverUser = driverUser;
    }

    private DriverUser driverUser;


    public void setAsCurrentUser(){
        DataStore.currentUser = this;
    }

    public String getStudentId() {
        return studentId;
    }
}
