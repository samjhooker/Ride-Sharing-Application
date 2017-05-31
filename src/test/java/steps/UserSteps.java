package steps;

import controllers.DataStore;
import controllers.LoginController;
import controllers.UpdateUserController;
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


    String studentId;
    String name;
    String address;
    String phoneNumber;
    String photoUrl;
    String password2;
    LoginController loginController = new LoginController();




    @Given("^A correct set of registration paramaters$")
    public void a_correct_set_of_registration_paramaters() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        studentId = "hello";
        name = "hello";
        email = "hello@uclive.ac.nz";
        address = "hello";
        phoneNumber = "hello";
        photoUrl = "hello";
        password = "hello";
        password2 = "hello";

    }

    @When("^a user is created$")
    public void a_user_is_created() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        user = loginController.createUser(studentId, name, email, address, phoneNumber, photoUrl, password, password2);
    }

    @Then("^the user should not be null$")
    public void the_user_should_not_be_null() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertNotEquals(null, user);
    }

    @Given("^An incomplete set of registration paramaters$")
    public void an_incomplete_set_of_registration_paramaters() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        a_correct_set_of_registration_paramaters();
        address = "";
        phoneNumber = "";
    }

    @Then("^the user is null$")
    public void the_user_is_null() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(null, user);
    }

    @Given("^A correct set of registration paramaters with non-matching passwords$")
    public void a_correct_set_of_registration_paramaters_with_non_matching_passwords() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        a_correct_set_of_registration_paramaters();
        password2 = "hello2";

    }


    @Given("^An account exists with password \"([^\"]*)\" and username \"([^\"]*)\"$")
    public void an_account_exists_with_password_and_username(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        a_correct_set_of_registration_paramaters();
        user = loginController.createUser(studentId, name, arg1+"@uclive.ac.nz", address, phoneNumber, photoUrl, arg2, arg2);
        DataStore.users.add(user);


    }

    @When("^Login function is called with password \"([^\"]*)\" and username \"([^\"]*)\"$")
    public void login_function_is_called_with_password_and_username(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        user = loginController.loginUser(arg1, arg2);
    }


    UpdateUserController updateUserController = new UpdateUserController();


    @When("^the user is updated and email is changed to \"([^\"]*)\"$")
    public void the_user_is_updated_and_email_is_changed_to(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        updateUserController.updateUser(user, "1", arg1, "1", "1","1","1");

    }

    @Then("^the new email is \"([^\"]*)\"$")
    public void the_new_email_is(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(arg1, user.getEmail());
    }

    @When("^the user is updated and password is changed to \"([^\"]*)\"$")
    public void the_user_is_updated_and_password_is_changed_to(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        updateUserController.updateUser(user, "1", "1", "1", "1","1",arg1);

    }

    @Then("^the new password is \"([^\"]*)\"$")
    public void the_new_password_is(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //user = loginController.loginUser(user.getUsername(), arg1);
        Assert.assertEquals(true, user.checkPasswordValid(arg1));
    }



}
