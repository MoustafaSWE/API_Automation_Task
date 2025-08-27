package api.tests.authors.steps;

import api.headers.GenericHeaders;
import api.url.Endpoint;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import config.EnvironmentConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import network.ApiCall;
import org.json.JSONArray;
import org.json.JSONObject;
import utilities.ResponseUtilities;

import static api.tests.authors.resources.GetAuthor.authorId;
import static api.tests.authors.resources.GetAuthor.constructExpectedResponseInterface;
import static io.restassured.RestAssured.given;

public class GetAuthor {
    public void getAuthor(ExtentTest test) {

        //API Full URL
        String getAuthorUrl = EnvironmentConfig.testedEnvironmentBaseUrl.concat(Endpoint.authorEndpoint);

        //Configure CURL Generation
        RestAssuredConfig config = CurlLoggingRestAssuredConfigFactory.createConfig();

        //Initialize Request
        RequestSpecification request = given().config(config);

        //Set Request Headers
        GenericHeaders.setGenericHeaders(request);

        //API Call
        JSONObject networkObjects = ApiCall.execute(ApiCall.ApiVerb.GET, request, getAuthorUrl);

        //Get request and response
        String curl = (String) networkObjects.get("curl");
        Response apiResponse = (Response) networkObjects.get("response");

        //API Response Test Result
        ResponseUtilities.successfulApiResponseAssertionsArray(test, curl, apiResponse);

        //Get API Response JSON
        JSONArray actualResponse = null;
        try {

            String jsonResponse = apiResponse.asString();
            actualResponse = new JSONArray(jsonResponse);

        } catch (Exception exception) {

            test.log(Status.FAIL, "Actual Response JSON Parse Exception: " + exception);

        }

        //Assert Interface Parameters
        try {

            JSONArray expectedResponse = constructExpectedResponseInterface();
            JSONObject missingKeys = new JSONObject();
            JSONObject mismatchTypeKeys = new JSONObject();
            JSONObject interfaceAssertionResult = ResponseUtilities.responseInterfaceAssertionArray(actualResponse, expectedResponse, missingKeys, mismatchTypeKeys);
            ResponseUtilities.printResponseAssertionResultsArray(test, interfaceAssertionResult, mismatchTypeKeys);

        } catch (Exception exception) {

            test.log(Status.FAIL, "Response Interface Assertion Exception: " + exception);

        }

        // Save book id
        try {
            if (actualResponse != null) {
                JSONArray booksArray = new JSONArray(actualResponse.toString());
                if (!booksArray.isEmpty()) {
                    JSONObject firstBook = booksArray.getJSONObject(0);
                    authorId = firstBook.getInt("id");
                }
            }
        } catch (Exception exception) {
            test.log(Status.FAIL, "Actual Response JSON Parse Exception: " + exception);
        }
    }
}
