package steps;

import cucumber.api.java.en.Given;

import controllers.DataStore;
import controllers.DriverTripsController;
import controllers.ShareRideController;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Tr;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;
import org.junit.Assert;
import org.junit.Before;

import javax.xml.crypto.Data;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.mock;
/**
 * Created by samuelhooker on 23/04/17.
 */
public class DataStoreSteps {

    Car car;
    Route route;
    Trip trip;


    @Before
    public void clear(){
        DataStore.cars.clear();
        DataStore.trips.clear();
        DataStore.liveTrips.clear();
        DataStore.stops.clear();
        DataStore.routes.clear();
    }

    @Given("^a Car is created$")
    public void a_Car_is_created() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        car  = new Car("123","123","123","123",123,123, LocalDate.now(), LocalDate.now(), UUID.randomUUID(), 4);
        DataStore.cars.add(car);
    }

    @Given("^a Stop is created$")
    public void a_Stop_is_created() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Stop stop = new Stop("123 apple suburbia", 44.0, 44.0);
        DataStore.stops.add(stop);

    }

    @Given("^a Route is created$")
    public void a_Route_is_created() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        route = new Route(new ArrayList<Stop>(), "route", UUID.randomUUID());
        DataStore.routes.add(route);
    }

    @Given("^a Trip is created$")
    public void a_Trip_is_created() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        trip  = new Trip(car, route, new ArrayList<>(), LocalDate.now(), new ArrayList<>(), false, UUID.randomUUID());
        DataStore.trips.add(trip);

    }

    @Given("^a LiveTrip is Created$")
    public void a_LiveTrip_is_Created() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        LiveTrip lt = new LiveTrip(trip, LocalDate.now(), 1, UUID.randomUUID());
        DataStore.liveTrips.add(lt);
    }


    @When("^I clear data$")
    public void i_clear_data() throws Throwable {
        DataStore.saveData();
        clear();
    }

    @When("^when i load data$")
    public void when_i_load_data() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        DataStore.loadData();
    }

    @Then("^there should be a Car$")
    public void there_should_be_a_Car() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(1,DataStore.cars.size());
    }

    @Then("^there should be a Stop$")
    public void there_should_be_a_Stop() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(1,DataStore.stops.size());
    }

    @Then("^there should be a Route$")
    public void there_should_be_a_Route() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(1,DataStore.routes.size());
    }

    @Then("^there should be a Trip$")
    public void there_should_be_a_Trip() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(1,DataStore.trips.size());
    }

    @Then("^there should be a LiveTrip$")
    public void there_should_be_a_LiveTrip() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(1,DataStore.trips.size());
        clear();
        DataStore.saveData();
    }
}
