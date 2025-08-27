package api.headers;

import io.restassured.specification.RequestSpecification;

public class GenericHeaders {

    public static void setGenericHeaders(RequestSpecification request) {

        request.header("Content-Type", "application/json");

    }

}
