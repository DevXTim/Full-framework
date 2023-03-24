package api.services;

import io.restassured.response.Response;

import java.util.LinkedHashMap;


public class ResponseContext {
    private static ResponseContext instance;
    private LinkedHashMap<String, Response> responseMap;

    public ResponseContext() {
        responseMap = new LinkedHashMap<>();
    }

    public static synchronized ResponseContext getInstance() {
        if (instance == null) {
            instance = new ResponseContext();
        }
        return instance;
    }

    public void setResponse(String key, Response response) {
        this.responseMap.put(key, response);
    }

    public Response getResponse(String key) {
        return responseMap.get(key);
    }

    public Response getLastResponse() {
        if (!responseMap.isEmpty()) {
            return responseMap.get(responseMap.keySet().toArray()[responseMap.size() - 1]);
        } else {
            return null;
        }
    }

    public void printResponseBodyAndStatusCode(Response response) {
        System.out.println("\nStatus code is: \n" + response.getStatusCode() + "\n\nResponse body is: \n" + response.getBody().asString());
    }

    public void printLastResponseBodyAndStatusCode() {
        Response response = getLastResponse();
        if (response != null) {
            System.out.println("\nStatus code is: \n" + response.getStatusCode() + "\n\nResponse body is: \n" + response.getBody().asString());
        } else {
            System.out.println("No responses have been saved.");
        }
    }
}

