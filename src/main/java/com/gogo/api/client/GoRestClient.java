package com.gogo.api.client;

import com.gogo.api.pojo.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.gogo.api.constants.APIConstants.*;


public class GoRestClient {
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

    public Response getUser(final String userId) {
        return request.get(String.format("/users/%s", userId));
    }

    public Response createUser(User user) {
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
