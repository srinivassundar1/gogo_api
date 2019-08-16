package com.gogo.api.client;

/***
 * @author Srinivas
 * Factory class that returns singleton rest client
 */
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

    /***
     * get client instance with authorization header flag
     * @param authorization authorization true if header to be passed
     * @return {@link GoRestClient}
     */
    public static GoRestClient getClient(boolean authorization) {
        return new GoRestClient(authorization);
    }
}
