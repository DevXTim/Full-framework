package api.services;

import api.clients.UserControllerClient;
import api.pojos.User;
import api.pojos.UserProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assumptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.*;
import java.util.stream.Collectors;

public class UserControllerService {

    private static final String BASE_EMAIL = "@gmailyahoo.com";
    private static final String BASE_HOMEPHONE = "555";
    private static final int SSN_LENGTH = 9;
    private static final Random RANDOM = new Random();

    ResponseContext responseContext = ResponseContext.getInstance();
    Gson gson = new Gson();
    UserControllerClient userControllerClient = new UserControllerClient();


    public Map<String, String> prepareUserFromMap(Map<String, String> user) {
        Map<String, String> userDetails = new HashMap<>(user);

        String firstName = user.get("firstName") + "_" + getRandomString();
        userDetails.put("firstName", firstName);

        // Generate unique email address based on the user's first name
        String email = userDetails.get("firstName").toLowerCase() + "." + getRandomString() + BASE_EMAIL;
        userDetails.put("emailAddress", email);

        // Generate unique 9-digit SSN number
        String ssn = getRandomSSN();
        userDetails.put("ssn", ssn);

        // Generate unique 10-digit home phone number with the prefix "555"
        String homePhone = BASE_HOMEPHONE + getRandomHomePhone();
        userDetails.put("homePhone", homePhone);

        // Add default values for remaining fields
        userDetails.put("address", "123 Main St.");
        userDetails.put("country", "US");
        userDetails.put("locality", "Anytown");
        userDetails.put("mobilePhone", "555-555-5555");
        userDetails.put("password", "MatchingPassword123!");
        userDetails.put("postalCode", "12345");
        userDetails.put("region", "Any State");
        userDetails.put("title", user.get("title"));
        userDetails.put("workPhone", "555-555-1234");
        return userDetails;
    }

    private String getRandomString() {
        // Generate a random string of 4 characters
        return Integer.toString(Math.abs(RANDOM.nextInt()), 16).substring(0, 4);
    }

    private String getRandomHomePhone() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }

    private String getRandomSSN() {
        // Generate a random 9-digit SSN number
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SSN_LENGTH; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.substring(0, 3) + "-" + sb.substring(3, 5) + "-" + sb.substring(5);
    }

    public void createUserViaApi(Map<String, String> preparedUser) {
        Response createUserResponse = userControllerClient.createUser(preparedUser);
        responseContext.setResponse("createUser", createUserResponse);

        responseContext.printLastResponseBodyAndStatusCode();
    }

    public void validateUserExists(Map<String, String> expectedValues) {
        Response getUserResponse = userControllerClient.getUserById(responseContext.getLastResponse().jsonPath().get("id"));
        responseContext.setResponse("getUser", getUserResponse);
        responseContext.printLastResponseBodyAndStatusCode();
        JsonObject jsonObject = gson.fromJson(getUserResponse.asString(), JsonObject.class);
        JsonObject userProfileJson = jsonObject.getAsJsonObject("userProfile");
        UserProfile userProfileFromGet = gson.fromJson(userProfileJson, UserProfile.class);
        assertAll(
                () -> assertEquals(expectedValues.get("lastName"), userProfileFromGet.getLastName()),
                () -> assertEquals(expectedValues.get("country"), userProfileFromGet.getCountry()),
                () -> assertEquals(expectedValues.get("address"), userProfileFromGet.getAddress()),
                () -> assertEquals(expectedValues.get("gender"), userProfileFromGet.getGender()),
                () -> assertEquals(expectedValues.get("homePhone"), userProfileFromGet.getHomePhone()),
                () -> assertEquals(expectedValues.get("postalCode"), userProfileFromGet.getPostalCode()),
                () -> assertEquals(expectedValues.get("locality"), userProfileFromGet.getLocality()),
                () -> assertEquals(expectedValues.get("title"), userProfileFromGet.getTitle()),
                () -> assertEquals(expectedValues.get("ssn"), userProfileFromGet.getSsn()),
                () -> assertEquals(expectedValues.get("firstName"), userProfileFromGet.getFirstName()),
                () -> assertEquals(expectedValues.get("emailAddress"), userProfileFromGet.getEmailAddress()),
                () -> assertEquals(expectedValues.get("mobilePhone"), userProfileFromGet.getMobilePhone()),
                () -> assertEquals(expectedValues.get("dob"), userProfileFromGet.getDob()),
                () -> assertEquals(expectedValues.get("workPhone"), userProfileFromGet.getWorkPhone()),
                () -> assertEquals(expectedValues.get("region"), userProfileFromGet.getRegion())
        );
    }

    public void cleanUpAllAutoUsers() throws JsonProcessingException {
        Response getListOfUsersResponse = userControllerClient.getListOfUsers();
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<User> userList = objectMapper.readValue(getListOfUsersResponse.asString(), new TypeReference<ArrayList<User>>() {});
        List<Integer> userIds = userList.stream()
                .filter(user -> user.getUserProfile().getEmailAddress().contains("@gmailyahoo.com"))
                .map(User::getId)
                .collect(Collectors.toList());
        System.out.println(userIds);

        for (Integer id : userIds) {
            Response deleteUser = userControllerClient.deleteUser(id);
            Assumptions.assumeTrue(204 == deleteUser.getStatusCode());
        }
    }
}
