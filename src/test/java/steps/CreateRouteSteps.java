package steps;

import controllers.CreateRouteController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Route;
import models.Stop;
import org.junit.Assert;

/**
 * Created by samuelhooker on 31/05/17.
 */
public class CreateRouteSteps {

    ObservableList<Stop> selectedStops;
    String name;
    Route route;
    CreateRouteController createRouteController = new CreateRouteController();

    @Given("^selectedStops is not empty and a route name is given$")
    public void selectedstops_is_not_empty_and_a_route_name_is_given() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        selectedStops = FXCollections.observableArrayList();
        selectedStops.add(new Stop("123, address", 1.0, 1.0));
        name = "name is here";
    }

    @When("^a new Route is created$")
    public void a_new_Route_is_created() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        route = createRouteController.createRoute(selectedStops, name);
    }

    @Then("^a new route should be not null$")
    public void a_new_route_should_be_not_null() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertNotEquals(null, route);
    }

    @Given("^selectedStops is empty and a route name is given$")
    public void selectedstops_is_empty_and_a_route_name_is_given() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        selectedStops = FXCollections.observableArrayList();
        name = "name is here";
    }

    @Then("^a new route should be null$")
    public void a_new_route_should_be_null() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(null, route);
    }

    @Given("^selectedStops is not empty and a route name is not given$")
    public void selectedstops_is_not_empty_and_a_route_name_is_not_given() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        selectedStops = FXCollections.observableArrayList();
        selectedStops.add(new Stop("123, address", 1.0, 1.0));
        name = "";
    }

}
