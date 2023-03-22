package api.services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthenticationControllerService {

    public Response authorizeAdmin(String authorizationUri) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .baseUri(authorizationUri)
                .queryParam("username", "admin@demo.io")
                .queryParam("password", "Demo123!")
                .when()
                .request("POST");
    }
}
