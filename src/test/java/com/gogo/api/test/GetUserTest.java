package com.gogo.api.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogo.api.exception.GoApiTestException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import static com.gogo.api.client.GoRestClientFactory.getClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GetUserTest {
    @DataProvider(name="GetUser")
    public Object[][] getUserDataProvider() {
        com.gogo.api.pojo.created.Result result = new com.gogo.api.pojo.created.Result();
        result.setEmail("egrady@example.net");
        result.setDob("1978-08-28");
        result.setId("1671");
        result.setGender("female");
        result.setFirstName("Corene");
        result.setLastName("Schinner");
        return new Object[][]
                {{result}};
    }

    @Test(dataProvider = "GetUser")
    public void testGetUser(com.gogo.api.pojo.created.Result expectedResult) throws GoApiTestException {
        final io.restassured.response.Response response = getClient().getUser(expectedResult.getId());
        assertThat(response.statusCode(), is(equalTo(200)));
        final String userDetailsJSONResponse = response.getBody().prettyPrint();

        try {
            final com.gogo.api.pojo.created.Response responseObj =
                    new ObjectMapper().readValue(userDetailsJSONResponse, com.gogo.api.pojo.created.Response.class);
            assertThat(expectedResult, is(equalTo(responseObj.getResult())));
        } catch (IOException e) {
            throw new GoApiTestException(e.getMessage());
        }
    }

    @Test
    public void testGetUserUnAuthorized() {
        assertThat(getClient(false).getUser("1671").statusCode(), is(equalTo(401)));
    }
}
