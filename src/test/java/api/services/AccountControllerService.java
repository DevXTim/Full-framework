package api.services;

import api.clients.AccountControllerClient;
import api.pojos.User;
import com.google.gson.Gson;
import io.restassured.response.Response;

import java.util.Map;

public class AccountControllerService {
    ResponseContext responseContext = ResponseContext.getInstance();
    Gson gson = new Gson();
    AccountControllerClient accountControllerClient = new AccountControllerClient();


    public void createAccountViaApi(Map<String, String> preparedCheckingAcc) {
        String userId = gson.fromJson(responseContext.getResponse("createUser").asString(), User.class).getId() + "";
        Response createAccResponse = accountControllerClient.createAccount(preparedCheckingAcc, userId);
        responseContext.setResponse("createAcc", createAccResponse);

        responseContext.printLastResponseBodyAndStatusCode();
    }
}
