package com.gogo.api.client;

import com.gogo.api.pojo.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import static com.gogo.api.constants.APIConstants.*;


/***
 * @author Srinivas
 * Web client class with get user & create user request
 */
public class GoRestClient {
    private static final Logger LOGGER = Logger.getLogger(GoRestClient.class);
    private RequestSpecification request;

    GoRestClient() {
        RestAssured.baseURI = REQUEST_URL;
        request = RestAssured.given();
        request.header("Content-Type", APPLICATION_TYPE_JSON);
        setAuthorization(true);
    }

    GoRestClient(boolean authorization) {
        RestAssured.baseURI = REQUEST_URL;
        request = RestAssured.given();
        request.header("Content-Type", APPLICATION_TYPE_JSON);
        setAuthorization(authorization);
    }

    /***
     * get user details response for the user id
     * @param userId user id
     * @return RestAPI response
     */
    public Response getUser(final String userId) {
        LOGGER.info("Getting user for id " + userId);
        return request.get(String.format("/users/%s", userId));
    }

    /***
     * create user with the specified input structure
     * @param user user json input structure
     * @return RestAPI response
     */
    public Response createUser(User user) {
        LOGGER.info("Creating user with the input request " + user);
        request.body(user);
        return request.post("/users");
    }

    private void setAuthorization(boolean authorization) {
        if(authorization)
            request.header("Authorization", AUTHORIZATION);
        else
            request.header("Authorization", "Bearer ");
    }
}
