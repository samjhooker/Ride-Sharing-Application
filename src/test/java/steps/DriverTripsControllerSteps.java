package steps;

import controllers.DataStore;
import controllers.DriverTripsController;
import controllers.ShareRideController;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Car;
import models.Route;
import models.Trip;
import models.User;
import org.junit.Assert;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.mock;

/**
 * Created by samuelhooker on 5/04/17.
 */
public class DriverTripsControllerSteps {

    ShareRideController shareRideController = new ShareRideController();
    LocalDate ld;
    Trip trip;
    Car mockCar = mock(Car.class);
    Route mockRoute = mock(Route.class);
    User user = new User("123456", "abc123", "fake@uclive.ac.nz", "123 Fake rd", "0220108042", "no", "Hello");


    @Given("^the Date is today$")
    public void the_Date_is_today() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ld=ld.now();
    }


    @Given("^a trip repeats everyday$")
    public void a_trip_repeats_everyday() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ObservableList<DayOfWeek> days = FXCollections.observableArrayList();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.TUESDAY);
        days.add(DayOfWeek.WEDNESDAY);
        days.add(DayOfWeek.THURSDAY);
        days.add(DayOfWeek.FRIDAY);
        days.add(DayOfWeek.SATURDAY);
        days.add(DayOfWeek.SUNDAY);



        trip = new Trip(mockCar, mockRoute, FXCollections.observableArrayList(), ld, days, false, UUID.randomUUID());
    }

    @Given("^a trip repeats on (\\d+) day$")
    public void a_trip_repeats_on_day(int daysOfRepeat) throws Throwable {


        ObservableList<DayOfWeek> days = FXCollections.observableArrayList();
        for(int i=0; i < daysOfRepeat; i++){
            days.add(DayOfWeek.MONDAY);
        }

        trip = new Trip(mockCar, mockRoute, FXCollections.observableArrayList(), ld, days, false, UUID.randomUUID());
    }

    @Given("^the expiry date is in (\\d+) days$")
    public void the_expiry_date_is_in_days(int days) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        trip.setExpiryDate(trip.getExpiryDate().plusDays(days));
    }

    @When("^I create a repeated ride$")
    public void i_create_a_repeated_ride() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        DataStore.liveTrips.clear();
        user.setAsCurrentUser();
        shareRideController.createLiveTrips(trip, 3);
    }

    @Then("^there should only be (\\d+) LiveTrips created$")
    public void there_should_only_be_LiveTrips_created(int numOfLiveTrips) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(numOfLiveTrips, DataStore.liveTrips.size());
    }



}
