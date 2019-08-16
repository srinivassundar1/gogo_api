package com.gogo.api.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogo.api.client.UserFactory;
import com.gogo.api.exception.GoApiTestException;
import com.gogo.api.pojo.Meta;
import com.gogo.api.pojo.Response;
import com.gogo.api.pojo.Result;
import com.gogo.api.pojo.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static com.gogo.api.client.GoRestClientFactory.getClient;
import static com.gogo.api.constants.APIConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class CreateUserTest {

    private User getUserTestInput(final String fileName) {
        User user = null;
        try {
            user = UserFactory.getUser(
                    new File(TEST_DATA + File.separator + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }
    @DataProvider(name="EmailAlreadyTaken")
    public Object[][] getDataFromDataprovider(){
        return new Object[][] {{getUserTestInput(EMAIL_ALREADY_TAKEN)}};
    }

    @DataProvider(name="CreateUser")
    public Object[][] getCreateUserDataFromDataprovider(){
        return new Object[][] {{getUserTestInput(CREATE_USER)}};
    }

    @Test(dataProvider="CreateUser")
    public void testCreateUser(User user) throws GoApiTestException {
        user.setEmail("test_" + System.currentTimeMillis() + "@gmail.com");
        final String jsonResponse = getClient().createUser(user).getBody().prettyPrint();

        try {
            com.gogo.api.pojo.created.Response responseObj =
                    new ObjectMapper().readValue(jsonResponse, com.gogo.api.pojo.created.Response.class);
            final com.gogo.api.pojo.created.Result result = responseObj.getResult();

            // call get user API to assert the user details created
            final io.restassured.response.Response getResponse = getClient().getUser(result.getId());

            assertThat(getResponse.statusCode(), is(equalTo(200)));
            assertThat(user.getEmail(), is(equalTo(result.getEmail())));
            assertThat(user.getFirst_name(), is(equalTo(result.getFirstName())));
            assertThat(user.getLast_name(), is(equalTo(result.getLastName())));
            assertThat(user.getGender(), is(equalTo(result.getGender())));
        } catch (IOException e) {
            throw new GoApiTestException(e.getMessage());
        }
    }

    @Test(dataProvider="CreateUser", expectedExceptions = { GoApiTestException.class} )
    public void testCreateUserEmailAlreadyTaken(User user) throws GoApiTestException {
        final String jsonResponse = getClient().createUser(user).getBody().prettyPrint();

        try {
            new ObjectMapper().readValue(jsonResponse, com.gogo.api.pojo.created.Response.class);
        } catch (IOException e) {
            throw new GoApiTestException(e.getMessage());
        }
    }

    @Test(dataProvider="EmailAlreadyTaken")
    public void testEmailAlreadyTakenResponseStructure(User user) throws GoApiTestException {
        final String jsonResponse = getClient().createUser(user).getBody().prettyPrint();
        try {
            Response responseObj = new ObjectMapper().readValue(jsonResponse, Response.class);
            Meta meta = responseObj.getMeta();
            assertThat(responseObj.getMeta().getSuccess(), equalTo(false));
            assertThat(meta.getCode(), equalTo(422));
            List<Result> result = responseObj.getResult();
            assertThat(result, hasSize(1));
            assertThat(result.get(0).getField(), equalTo("email"));
        } catch (IOException e) {
            throw new GoApiTestException(e.getMessage());
        }
    }
}
