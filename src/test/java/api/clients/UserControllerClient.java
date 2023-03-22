package api.clients;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import step_definitions.Hooks;

import java.util.Map;

public class UserControllerClient {

    Gson gson = new Gson();
    Hooks hooks = new Hooks();

    public Response createUser(Map<String, String> preparedUser) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + hooks.getAuthToken())
                .body(gson.toJson(preparedUser))
                .when()
                .post("/user");
    }

    public Response getUserById(int userId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + hooks.getAuthToken())
                .when()
                .pathParam("userId", userId)
                .get("/user/{userId}");
    }

    public Response deleteUser(int userId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + hooks.getAuthToken())
                .when()
                .pathParam("userId", userId)
                .delete("/user/{userId}");
    }

    public Response getListOfUsers() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers("Authorization", "Bearer " + hooks.getAuthToken())
                .when()
                .get("/users");
    }
}
