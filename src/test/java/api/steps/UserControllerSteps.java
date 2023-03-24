package api.steps;

import java.util.List;
import java.util.Map;

import api.services.ResponseContext;
import api.services.ScenarioContext;
import api.services.UserControllerService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class UserControllerSteps {

    public UserControllerService userControllerService = new UserControllerService();
    private ScenarioContext scenarioContext;
    private ResponseContext responseContext;

    public UserControllerSteps(ScenarioContext scenarioContext, ResponseContext responseContext) {
        this.scenarioContext = ScenarioContext.getInstance();
        this.responseContext = ResponseContext.getInstance();
    }

    @When("sends request to create user with following fields:")
    public void createsUserViaApiWithFollowingFields(List<Map<String, String>> registerValues) {
        Map<String, String> preparedUser = userControllerService.prepareUserFromMap(registerValues.get(0));
        scenarioContext.setContext("registerValues", preparedUser);
        userControllerService.createUserViaApi(preparedUser);
    }

    @Then("validate user is created")
    public void validate_user_is_created() {
        Map<String, String> expectedValues = (Map<String, String>) scenarioContext.getContext("registerValues");
        System.out.println(expectedValues);
        userControllerService.validateUserExists(expectedValues);
    }
}
