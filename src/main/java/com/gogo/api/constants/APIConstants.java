package com.gogo.api.constants;

public class APIConstants {
    public static String REQUEST_URL = "https://gorest.co.in/public-api";
    public static String AUTHORIZATION = "Bearer L41uyhzxoig9roHjTyRHFvBT48K2GjCMjbVC";
    public static String EMAIL_ALREADY_TAKEN = "email_already_taken.json";
    public static String CREATE_USER = "create_user.json";
    public static String TEST_DATA = "testdata";
    public static String APPLICATION_TYPE_JSON = "application/json";


    public enum ContentType {
        JSON("application/json");
        private String contentType;
        ContentType(String contentType) {
            this.contentType = contentType;
        }
        public String getContentType() {
            return contentType;
        }
    }
}
