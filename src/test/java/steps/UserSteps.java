package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import models.User;
import org.junit.Assert;
import org.junit.Before;

/**
 * Created by samuelhooker on 23/04/17.
 */
public class UserSteps {
    User user = new User("63266757","Joe", "email", "address", "phone", "pic", "passwor  ");;

    String password;
    boolean passwordVerified;
    String email;
    boolean emailVerified;

    @Before
    public void createUser(){
        user= new User("63266757","Joe", "email", "address", "phone", "pic", "passwor  ");
    }


    @Given("^the password is \"([^\"]*)\"$")
    public void the_password_is(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        user = new User("63266757","Joe", "email", "address", "phone", "pic", arg1);
    }

    @When("^the password \"([^\"]*)\" is verified$")
    public void the_password_is_verified(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        password = arg1;
        emailVerified = user.checkPasswordValid(arg1);
    }

    @Then("^the password should be verified$")
    public void the_password_should_be_verified() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(emailVerified);
    }

    @Then("^the password should not be verified$")
    public void the_password_should_not_be_verified() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertFalse(emailVerified);
    }

    @Given("^a user object exists$")
    public void a_user_object_exists() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //done
    }

    @When("^the email \"([^\"]*)\" is verified$")
    public void the_email_is_verified(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        email = arg1;
        emailVerified = user.checkEmailValid(arg1);
    }

    @Then("^the email should be verified$")
    public void the_email_should_be_verified() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(emailVerified);
    }

    @Then("^the email should not be verified$")
    public void the_email_should_not_be_verified() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertFalse(emailVerified);
    }


}
