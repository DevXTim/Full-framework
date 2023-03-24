package step_definitions;

import api.services.AuthenticationControllerService;
import api.services.UserControllerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.DatabaseUtils;
import utils.DriverUtils;

public class Hooks {

    private static String authorizationToken = "";
    AuthenticationControllerService authenticationControllerService = new AuthenticationControllerService();

    public String getAuthToken() {
        return authorizationToken;
    }

    @Before("@newUser")
    public void setupBaseUri() {
        ConfigReader.initializeProperties();
        String authorizationUri = ConfigReader.getProperty("authorization.uri");
        RestAssured.baseURI = ConfigReader.getProperty("base.url");

        Response authResponse = authenticationControllerService.authorizeAdmin(authorizationUri);

        authorizationToken = authResponse.jsonPath().getString("authToken");
        System.out.println(authorizationToken);
    }

    @After("@newUser")
    public void cleanUsers() throws JsonProcessingException {
        UserControllerService userControllerService = new UserControllerService();
        userControllerService.cleanUpAllAutoUsers();
    }

    @Before("@UI")
    public void setUp() {
        ConfigReader.initializeProperties();
        DriverUtils.createDriver();
    }

    @After("@UI")
    public void cleanUp(Scenario scenario) throws InterruptedException {
        if (scenario.isFailed()) {
            scenario.attach(CommonMethods.takeScreenshot(), "image/png", scenario.getName());
        }
        CommonMethods.takeScreenshot(scenario);
        Thread.sleep(3000);
        DriverUtils.getDriver().quit();
    }

    @Before("@DB")
    public void setUpDB() {
        DatabaseUtils.initializeDBProperties();
    }

    @After("@DB")
    public void cleanUpDB() {
        DatabaseUtils.closeDataBaseConnection();
    }
}
