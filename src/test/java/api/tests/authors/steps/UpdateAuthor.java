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
import org.json.JSONObject;
import utilities.ResponseUtilities;

import static api.tests.authors.resources.CreateAuthor.constructExpectedResponseInterface;
import static api.tests.authors.resources.CreateAuthor.createdAuthorId;
import static io.restassured.RestAssured.given;

public class UpdateAuthor {

    public void updateAuthor(ExtentTest test) {

        //API Full URL
        String updateAuthorUrl = EnvironmentConfig.testedEnvironmentBaseUrl.concat(Endpoint.authorByIdEndpoint.replace("{authorId}", String.valueOf(createdAuthorId)));

        //Configure CURL Generation
        RestAssuredConfig config = CurlLoggingRestAssuredConfigFactory.createConfig();

        //Initialize Request
        RequestSpecification request = given().config(config);

        //Set Request Headers
        GenericHeaders.setGenericHeaders(request);

        //Initialize Request Body JSON Object
        JSONObject requestBody  = new JSONObject();

        //Set Request Body JSON Object Parameters
        requestBody.put("id", createdAuthorId);
        requestBody.put("title", "Demo");
        requestBody.put("firstName", "Moustafa");
        requestBody.put("lastName", "Mohamed");

        //Attach Request Body JSON Object to generated Request
        request.body(requestBody.toString());

        //API Call
        JSONObject networkObjects = ApiCall.execute(ApiCall.ApiVerb.PUT, request, updateAuthorUrl);

        //Get request and response
        String curl = (String) networkObjects.get("curl");
        Response apiResponse = (Response) networkObjects.get("response");

        //API Response Test Result
        ResponseUtilities.successfulApiResponseAssertions(test, curl, apiResponse);

        //Get API Response JSON
        JSONObject actualResponse = null;
        try {

            String jsonResponse = apiResponse.asString();
            actualResponse = new JSONObject(jsonResponse);

        } catch (Exception exception) {

            test.log(Status.FAIL, "Actual Response JSON Parse Exception: " + exception);

        }

        //Assert Interface Parameters
        try {

            JSONObject expectedResponse = constructExpectedResponseInterface();
            JSONObject missingKeys = new JSONObject();
            JSONObject mismatchTypeKeys = new JSONObject();
            JSONObject interfaceAssertionResult = ResponseUtilities.responseInterfaceAssertion(actualResponse, expectedResponse, missingKeys, mismatchTypeKeys);
            ResponseUtilities.printResponseAssertionResults(test, interfaceAssertionResult, mismatchTypeKeys);

        } catch (Exception exception) {

            test.log(Status.FAIL, "Response Interface Assertion Exception: " + exception);

        }
    }
}