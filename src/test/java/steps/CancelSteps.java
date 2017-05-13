package steps;

import controllers.PassengerRidesController;
import controllers.ShareRideController;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import models.*;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;

/**
 * Created by samuelhooker on 8/05/17.
 */
public class CancelSteps {

    Pickup pickup;
    LiveTrip liveTrip;

    StopAndTime mockStopAndTime = mock(StopAndTime.class);
    Trip mockTrip = mock(Trip.class);
    PassengerRidesController passengerRidesController= new PassengerRidesController();
    ShareRideController shareRideController= new ShareRideController();


    @Given("^I have booked a ride with (\\d+) seats$")
    public void i_have_booked_a_ride_with_seats(int seats) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        pickup = new Pickup(mockStopAndTime, UUID.randomUUID(), Pickup.RideStatus.BOOKED);
        liveTrip = new LiveTrip(mockTrip, LocalDate.now(), seats, UUID.randomUUID());
        List<Pickup> pickups = new ArrayList<>();
        pickups.add(pickup);
        liveTrip.setPickups(pickups);
    }

    @When("^I cancel the ride$")
    public void i_cancel_the_ride() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        passengerRidesController.cancelPickup(pickup,liveTrip);
    }

    @Then("^there should be (\\d+) available seats$")
    public void there_should_be_available_seats(int seats) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(seats, liveTrip.getSeats());
    }

    @Then("^pickup status is Cancelled$")
    public void pickup_status_is_Cancelled() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(Pickup.RideStatus.CANCELLED, pickup.getRideStatus());
    }




    @Given("^I have an active LiveTrip$")
    public void i_have_an_active_LiveTrip() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        liveTrip = new LiveTrip(mockTrip, LocalDate.now(), 1, UUID.randomUUID());
    }

    @When("^I cancel the trip$")
    public void i_cancel_the_trip() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        shareRideController.cancelLiveTrip(liveTrip);
    }

    @Then("^the LiveTrip status should be cancelled\\.$")
    public void the_LiveTrip_status_should_be_cancelled() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(LiveTrip.TripStatus.CANCELLED, liveTrip.getStatus());
    }

}
