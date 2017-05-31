package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import models.Stop;
import org.junit.Assert;
import utils.MapUtils;

/**
 * Created by sjh298 on 29/05/17.
 */
public class MapUtilsSteps {

double cost;
double efficiency;
double distance;
double lat;
double lng;

    @Given("^a trip is (\\d+)km and the fuel efficiency is (\\d+) liters per (\\d+)kms$")
    public void a_trip_is_km_and_the_fuel_efficiency_is_liters_per_kms(int distance, int efficiency, int arg3) throws Throwable {
        this.distance = distance;
        this.efficiency = efficiency;
    }

    @When("^The price is calculated$")
    public void the_price_is_calculated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.cost = MapUtils.calculatePrice(distance, efficiency);
    }


    @Then("^the price should be \\$\"([^\"]*)\"$")
    public void the_price_should_be_$(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        double expected = Double.parseDouble(arg1);
        Assert.assertEquals(expected, cost, 0.1);
    }

    @Given("^the latitude is \"([^\"]*)\" and the longitude is \"([^\"]*)\"$")
    public void the_latitude_is_and_the_longitude_is(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        lat = Double.parseDouble(arg1);
        lng = Double.parseDouble(arg2);

    }

    @When("^the distacne between uni is calculated$")
    public void the_distacne_between_uni_is_calculated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        distance = MapUtils.distanceToUni(lat, lng);
    }

    @Then("^the distance is \"([^\"]*)\"km$")
    public void the_distance_is_km(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        double expected = Double.parseDouble(arg1);

        Assert.assertEquals(expected, distance, 0.1);
    }

    String streetNo;
    String address;
    String suburb;
    Stop stop;

    @Given("^the street number is \"([^\"]*)\", the address is \"([^\"]*)\" and the suburb is \"([^\"]*)\"$")
    public void the_street_number_is_the_address_is_and_the_suburb_is(String arg1, String arg2, String arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        streetNo = arg1;
        address = arg2;
        suburb = arg3;

    }

    @Then("^the stop is not null$")
    public void the_stop_is_not_null() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertNotEquals(null, stop);
    }

    @Then("^the stop is null$")
    public void the_stop_is_null() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(null, stop);

    }

    @When("^a new Stop is created$")
    public void a_new_Stop_is_created() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stop = MapUtils.getStopFromAddress(streetNo+" "+address+ ""+suburb);
    }


}
