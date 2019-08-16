package com.gogo.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogo.api.pojo.User;

import java.io.File;
import java.io.IOException;

public class UserFactory {

    public static User getUser(final File testInputFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(testInputFile, User.class);
    }
}
