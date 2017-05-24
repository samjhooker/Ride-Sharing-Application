package steps;

import controllers.DataStore;
import controllers.RegisterCarController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;

/**
 * Created by samuelhooker on 24/05/17.
 */
public class UpdateCarSteps {

    Car car;
    Trip trip;
    LiveTrip liveTrip;
    Pickup pickup;
    Route mockRoute = mock(Route.class);
    StopAndTime stopAndTime = mock(StopAndTime.class);
    RegisterCarController registerCarController = new RegisterCarController();

    int notificationCount = 0;


    @Given("^a livetrip exists with one booked pickup$")
    public void a_livetrip_exists_with_one_booked_pickup() throws Throwable {

        car = new Car("Holden", "Commedore", "Blue", "ABC123", 2009, 5, LocalDate.now(), LocalDate.now(), UUID.randomUUID(),5);
        trip= new Trip(car, mockRoute,new ArrayList<>(), LocalDate.now(), new ArrayList<>(), false, UUID.randomUUID());
        liveTrip = new LiveTrip(trip, LocalDate.now(), 5,UUID.randomUUID());
        pickup = new Pickup(stopAndTime, UUID.randomUUID(), Pickup.RideStatus.BOOKED);
        List<Pickup> pickups = FXCollections.observableArrayList();
        pickups.add(pickup);
        liveTrip.setPickups(pickups);
    }

    @Given("^the current notifications are stored$")
    public void the_current_notifications_are_stored() throws Throwable {
        notificationCount = DataStore.notifications.size();
    }

    @When("^the update function is called and the LitersPer(\\d+)km are changed$")
    public void the_update_function_is_called_and_the_LitersPer_km_are_changed(int arg1) throws Throwable {
        registerCarController.updateCar(car, LocalDate.now(), LocalDate.now(), "4", "6.0");

    }

    @Then("^there should be one more notifications$")
    public void there_should_be_one_more_notifications() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(notificationCount +1, DataStore.notifications.size());
    }

    @Then("^cars paramaters are updated$")
    public void cars_paramaters_are_updated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(6.0, car.getLitresPer100km(), 0.1);
        Assert.assertEquals(4, car.getNumberOfSeats(), 0.1);
    }

}
