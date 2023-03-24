package api.clients;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import step_definitions.Hooks;

import java.util.Map;

public class AccountControllerClient {

    Gson gson = new Gson();
    Hooks hooks = new Hooks();

    public Response createAccount(Map<String, String> preparedUser, String userId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + hooks.getAuthToken())
                .body(gson.toJson(preparedUser))
                .when()
                .pathParam("userId", userId)
                .post("/user/{userId}/account");
    }
}
