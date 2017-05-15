package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import models.Car;
import models.DriverUser;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by samuelhooker on 25/04/17.
 */
public class NotificationSteps {

    Car car;
    Boolean result;
    DriverUser driverUser;



    @Given("^that the expiry date of WOF is in (\\d+) days$")
    public void that_the_expiry_date_of_WOF_is_in_days(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        car = new Car("a","a", "a", "a", 1990, 5, LocalDate.now().plusDays(arg1), LocalDate.now(), UUID.randomUUID(), 4);
    }

    @When("^I check whether to show a notification for WOF$")
    public void i_check_whether_to_show_a_notification_for_WOF() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        result = car.showWofNotification();
    }

    @Then("^a notification should be shown$")
    public void a_notification_should_be_shown() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(result);
    }

    @Then("^a notification should not be shown$")
    public void a_notification_should_not_be_shown() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertFalse(result);
    }

    @Given("^that the expiry date of Rego is in (\\d+) days$")
    public void that_the_expiry_date_of_Rego_is_in_days(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        car = new Car("a","a", "a", "a", 1990, 5, LocalDate.now(), LocalDate.now().plusDays(arg1), UUID.randomUUID(), 4);

    }

    @When("^I check whether to show a notification for rego$")
    public void i_check_whether_to_show_a_notification_for_rego() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        result = car.showRegistrationNotification();
    }

    @When("^I check whether to show a notification for rego twice$")
    public void i_check_whether_to_show_a_notification_for_rego_twice() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        result = car.showRegistrationNotification();
        result = car.showRegistrationNotification();
    }


    @Given("^that the expiry date of Licence is in (\\d+) days$")
    public void that_the_expiry_date_of_Licence_is_in_days(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driverUser = new DriverUser(LocalDate.now().plusDays(arg1), LocalDate.now(), "nu;;", "null");
    }

    @When("^I check whether to show a notification for license$")
    public void i_check_whether_to_show_a_notification_for_license() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        result = driverUser.showExpiryNotification();
    }
}
