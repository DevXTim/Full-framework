package step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;

public class ProfileSteps {
    HomePage homePage = new HomePage();
    @When("user clicks on account button")
    public void userClicksOnAccountButton() {
        homePage.clickOnAccountBtn();

    }


    @Then("user can see {string}")
    public void userCanSee(String btn) {


        if (btn.equals("My Profile")){
            Assertions.assertTrue(homePage.profile.isDisplayed());
            Assertions.assertTrue(homePage.profile.getText().equals(btn));
        } else if (btn.equals("Change Password")) {
            Assertions.assertTrue(homePage.changePassword.isDisplayed());
            Assertions.assertTrue(homePage.changePassword.getText().equals(btn));
        } else if (btn.equals("Logout")) {
            Assertions.assertTrue(homePage.logOut.isDisplayed());
            Assertions.assertTrue(homePage.logOut.getText().equals(btn));
        }

    }
}
