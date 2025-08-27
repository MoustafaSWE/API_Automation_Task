package api.tests.authors.steps;


import api.headers.GenericHeaders;
import api.url.Endpoint;
import com.aventstack.extentreports.ExtentTest;
import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import config.EnvironmentConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import network.ApiCall;
import org.json.JSONObject;
import utilities.ResponseUtilities;

import static api.tests.authors.resources.CreateAuthor.createdAuthorId;
import static io.restassured.RestAssured.given;

public class DeleteAuthor {

    public void deleteAuthor(ExtentTest test) {

        //API Full URL
        String deleteAuthorUrl = EnvironmentConfig.testedEnvironmentBaseUrl.concat(Endpoint.authorByIdEndpoint.replace("{authorId}", String.valueOf(createdAuthorId)));

        //Configure CURL Generation
        RestAssuredConfig config = CurlLoggingRestAssuredConfigFactory.createConfig();

        //Initialize Request
        RequestSpecification request = given().config(config);

        //Set Request Headers
        GenericHeaders.setGenericHeaders(request);

        //API Call
        JSONObject networkObjects = ApiCall.execute(ApiCall.ApiVerb.DELETE, request, deleteAuthorUrl);

        //Get request and response
        String curl = (String) networkObjects.get("curl");
        Response apiResponse = (Response) networkObjects.get("response");

        //API Response Test Result
        ResponseUtilities.successfulApiResponseAssertionsWithEmptyBody(test, curl, apiResponse);
    }
}