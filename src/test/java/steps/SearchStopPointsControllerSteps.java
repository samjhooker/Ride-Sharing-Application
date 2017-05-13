package steps;

import controllers.SearchStopPointsController;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import models.LiveTrip;
import models.Trip;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.mock;


/**
 * Created by sam on 11/03/17.
 */
public class SearchStopPointsControllerSteps {
    Trip mockTrip = mock(Trip.class);
    LocalDate mockDate = LocalDate.now();
    LiveTrip liveTrip;
    SearchStopPointsController searchStopPointsController = new SearchStopPointsController();


    @Given("^that there are (\\d+) seats on a LiveTrip$")
    public void that_there_are_seats_on_a_LiveTrip(int seats) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        liveTrip = new LiveTrip(mockTrip, mockDate, seats, UUID.randomUUID());

    }

    @When("^I book a LiveTrip$")
    public void i_book_a_LiveTrip() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        searchStopPointsController.selectedLiveTrip = liveTrip;
        searchStopPointsController.bookRide(liveTrip);

    }

    @Then("^there should only be (\\d+) seats on the LiveTrip$")
    public void there_should_only_be_seats_on_the_LiveTrip(int seats) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(seats, liveTrip.getSeats());
    }

}
