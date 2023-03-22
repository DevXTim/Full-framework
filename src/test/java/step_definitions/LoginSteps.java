package step_definitions;

import api.services.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverUtils;

import java.util.Map;


public class LoginSteps {
    //17.create objects of classes
    //we're creating objects rather than extending because a class cannot extend multiple classes
    //in our methods, we will call our objects and use methods that exist in the pages (LoginPage and HomePage)

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    Hooks hooks = new Hooks();
    ScenarioContext scenarioContext;
    public LoginSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }


    //10b. paste our steps and import annotations
    @Given("user enters valid username and password")
    public void user_enters_valid_username_and_password() throws InterruptedException {
        loginPage.enterValidUserNamePassword();
        Thread.sleep(1500);
    }

    @When("user clicks on Sign In button")
    public void user_clicks_on_sign_in_button() {
        loginPage.clickSignInButton();
    }

    @Then("verify user is successfully logged in to their account")
    public void verify_user_is_successfully_logged_in_to_their_account() throws InterruptedException {
        homePage.verifyHomePage();
        Thread.sleep(1500);
    }

    @Given("user enters valid {string} and {string}")
    public void user_enters_valid_username_and_password(String str, String str1) {
        loginPage.enterValidLoginInfo(str, str1);
    }

    @Then("verify user is successfully logged in to the account")
    public void verify_user_is_successfully_logged_in_to_the_account() {
        homePage.verifyPage();
    }

    @When("starts browser and authorizes")
    public void user_starts_browser_and_authorizes() {
        DriverUtils.createDriver();
        Map<String, String> expectedValues = (Map<String, String>) scenarioContext.getContext("registerValues");
        System.out.println("****************");
        System.out.println(expectedValues);
        user_enters_valid_username_and_password(expectedValues.get("emailAddress"), expectedValues.get("password"));
    }
}