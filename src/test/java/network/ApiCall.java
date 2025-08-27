package network;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ApiCall {

    public enum ApiVerb {
        GET,
        POST,
        PUT,
        PATCH,
        DELETE
    }

    public static JSONObject execute (ApiVerb verb, RequestSpecification request , String apiFullUrl) {

        //Get Original System Out
        PrintStream standardOut = System.out;

        //Set Console Log (CURL) to Bytes
        ByteArrayOutputStream consoleLogBytes = new ByteArrayOutputStream();
        PrintStream consoleLog = new PrintStream(consoleLogBytes);
        System.setOut(consoleLog);

        //API Call
        Response apiResponse = null;
        switch (verb) {

            case GET:
                apiResponse = request.get(apiFullUrl);
                break;

            case POST:
                apiResponse = request.post(apiFullUrl);
                break;

            case PUT:
                apiResponse = request.put(apiFullUrl);
                break;

            case PATCH:
                apiResponse = request.patch(apiFullUrl);
                break;

            case DELETE:
                apiResponse = request.delete(apiFullUrl);
                break;

        }

        //Get Request CURL
        String curl = consoleLogBytes.toString();

        //Set Console Log to Original State
        System.setOut(standardOut);

        JSONObject networkObjects = new JSONObject();
        networkObjects.put("curl", curl);
        networkObjects.put("response", apiResponse);

        return networkObjects;
    }
}
