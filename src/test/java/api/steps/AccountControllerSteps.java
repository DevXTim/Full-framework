package api.steps;

import api.services.AccountControllerService;
import api.services.ResponseContext;
import api.services.ScenarioContext;
import io.cucumber.java.en.Given;

import java.util.List;
import java.util.Map;

public class AccountControllerSteps {

    public AccountControllerService accountControllerService = new AccountControllerService();
    private ScenarioContext scenarioContext;
    private ResponseContext responseContext;

    public AccountControllerSteps(ScenarioContext scenarioContext, ResponseContext responseContext) {
        this.scenarioContext = ScenarioContext.getInstance();
        this.responseContext = ResponseContext.getInstance();
    }

    @Given("creates new checking account with following details:")
    public void creates_new_checking_account_with_following_details(List<Map<String, String>> checkingValues) {
        Map<String, String> preparedCheckingAcc = checkingValues.get(0);
        scenarioContext.setContext("checkingValues", preparedCheckingAcc);
        accountControllerService.createAccountViaApi(preparedCheckingAcc);
    }
}
