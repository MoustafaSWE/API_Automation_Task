package api.tests.books.steps;


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

import static api.tests.books.resources.DeleteBook.constructExpectedResponseInterface;
import static api.tests.books.resources.CreateBook.createdBookId;
import static io.restassured.RestAssured.given;

public class DeleteBook {

    public void deleteBook(ExtentTest test) {

        //API Full URL
        String deleteBookUrl = EnvironmentConfig.testedEnvironmentBaseUrl.concat(Endpoint.booksByIdEndpoint.replace("{bookId}", String.valueOf(createdBookId)));

        //Configure CURL Generation
        RestAssuredConfig config = CurlLoggingRestAssuredConfigFactory.createConfig();

        //Initialize Request
        RequestSpecification request = given().config(config);

        //Set Request Headers
        GenericHeaders.setGenericHeaders(request);

        //API Call
        JSONObject networkObjects = ApiCall.execute(ApiCall.ApiVerb.DELETE, request, deleteBookUrl);

        //Get request and response
        String curl = (String) networkObjects.get("curl");
        Response apiResponse = (Response) networkObjects.get("response");

        //API Response Test Result
        ResponseUtilities.successfulApiResponseAssertionsWithEmptyBody(test, curl, apiResponse);
    }
}