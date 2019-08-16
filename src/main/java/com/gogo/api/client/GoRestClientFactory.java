package com.gogo.api.client;

public class GoRestClientFactory {
    private static GoRestClient client;

    private GoRestClientFactory() {

    }

    public static GoRestClient getClient() {
        if(client == null) {
            client = new GoRestClient();
        }
        return client;
    }

    public static GoRestClient getClient(boolean authorization) {
        return new GoRestClient(authorization);
    }
}
