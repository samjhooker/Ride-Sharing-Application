package steps;

import controllers.RegisterCarController;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import models.Car;
import org.junit.Assert;

import java.time.LocalDate;

/**
 * Created by samuelhooker on 31/05/17.
 */
public class CreateCarSteps {

    Car car;
    String make;
    String model;
    String year;
    String seats;
    String litresPer100Kms;
    String licence;
    String color;
    RegisterCarController registerCarController = new RegisterCarController();

    @Given("^correct car paramaters are defined$")
    public void correct_car_paramaters_are_defined() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        make = "Holden";
        model = "Commedore";
        year = "1992";
        seats = "5";
        litresPer100Kms = "5";
        licence = "abc123";
        color = "green";

    }

    @When("^a car is created using the parameters$")
    public void a_car_is_created_using_the_parameters() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        car = registerCarController.createCar(make, model, color, licence, year, seats, LocalDate.now(), LocalDate.now(), litresPer100Kms);
    }

    @Then("^the car should exist$")
    public void the_car_should_exist() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertNotEquals(null, car);
    }

    @Given("^incorrect seats car paramaters are defined$")
    public void incorrect_seats_car_paramaters_are_defined() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        correct_car_paramaters_are_defined();
        seats = "five"; //incorrect
    }

    @Then("^the car should be null$")
    public void the_car_should_be_null() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(null, car);
    }

    @Given("^incorrect efficiency car paramaters are defined$")
    public void incorrect_efficiency_car_paramaters_are_defined() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        correct_car_paramaters_are_defined();
        litresPer100Kms = "five"; //incorrect
    }

    @Given("^incorrect year car paramaters are defined$")
    public void incorrect_year_car_paramaters_are_defined() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        correct_car_paramaters_are_defined();
        year = "five"; //incorrect
    }

}
