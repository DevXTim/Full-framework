package api.services;

import io.restassured.response.Response;

public class ResponseContext {
    private Response response;

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void printResponseBodyAndStatusCode(Response response) {
        System.out.println("\nStatus code is: \n" + response.getStatusCode() + "\n\nResponse body is: \n" + response.getBody().asString());
    }

    public void printLastResponseBodyAndStatusCode() {
        Response response = this.getResponse();
        System.out.println("\nStatus code is: \n" + response.getStatusCode() + "\n\nResponse body is: \n" + response.getBody().asString());
    }
}

