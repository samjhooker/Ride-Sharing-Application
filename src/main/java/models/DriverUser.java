package models;

import javax.management.Notification;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.YEARS;

/**
 * Created by samuelhooker on 24/04/17.
 */
public class DriverUser{

    public enum LicenceType{
        FULL, FULLFOR2YEARS
    }

    public DriverUser(LocalDate licenceExpiryDate, LocalDate licenceIssueDate, String licenceNumber, String licenceClass) {
        this.licenceExpiryDate = licenceExpiryDate;
        this.licenceIssueDate = licenceIssueDate;
        this.licenceNumber = licenceNumber;
        this.licenceClass = licenceClass;

        if (YEARS.between(licenceIssueDate, LocalDate.now()) >=2){
           this.setLicenceType(LicenceType.FULLFOR2YEARS);
        }else{
            this.setLicenceType(LicenceType.FULL);
        }


    }

    public boolean showExpiryNotification(){
        //notification shown 30, 16 and 9 days before
        //notification shown 30, 16 and 9 days before
        LocalDate now = LocalDate.now();
        int daysBetween = (int)DAYS.between(now, licenceExpiryDate);

        if(daysBetween == 30 && !showExpiry30dayNotification){
            showExpiry30dayNotification = true;
            return true;
        }
        if(daysBetween == 16 && !showExpiry16dayNotification){
            showExpiry16dayNotification = true;
            return true;
        }
        if(daysBetween == 9 && !showExpiry9dayNotification){
            showExpiry9dayNotification = true;
            return true;
        }
        return false;


    }


    public LicenceType getLicenceType() {
        return licenceType;
    }

    public void setLicenceType(LicenceType licenceType) {
        this.licenceType = licenceType;
    }

    public LocalDate getLicenceExpiryDate() {
        return licenceExpiryDate;
    }

    public void setLicenceExpiryDate(LocalDate licenceExpiryDate) {
        this.licenceExpiryDate = licenceExpiryDate;
    }

    public LocalDate getLicenceIssueDate() {
        return licenceIssueDate;
    }

    public void setLicenceIssueDate(LocalDate licenceIssueDate) {
        this.licenceIssueDate = licenceIssueDate;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getLicenceClass() {
        return licenceClass;
    }

    public void setLicenceClass(String licenceClass) {
        this.licenceClass = licenceClass;
    }

    private LicenceType licenceType;
    private LocalDate licenceExpiryDate;
    private LocalDate licenceIssueDate;
    private String licenceNumber;
    private String licenceClass;

    private Boolean showExpiry30dayNotification = false;
    private Boolean showExpiry16dayNotification = false;
    private Boolean showExpiry9dayNotification = false;





}
