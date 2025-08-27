package utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.gson.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ResponseUtilities {

    public static void successfulApiResponseAssertions(ExtentTest test, String curl, io.restassured.response.Response apiResponse) {

        //Remove Timestamp from curl
        String finalCurl = curl.substring(curl.indexOf("[main] DEBUG curl - ") + 20);
        //Log Request curl
        test.createNode("Request CURL: ").info("<pre>" + finalCurl + "</pre>");
        //Get API response Status Code
        int statusCode = apiResponse.getStatusCode();
        //Assert API response
        try {
            Assert.assertEquals(statusCode, 200);
            test.log(Status.PASS, "API Status Code: 200");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "API Status Code: ".concat(String.valueOf(statusCode)));
        }

        //Get Response Time
        long responseTime = apiResponse.getTime();
        //Report API Performance
        if (responseTime <= ResponseTimeThresholds.apiResponseTimeThreshold) {
            test.log(Status.PASS, "Response Time = " + (responseTime) + " ms");
        } else {
            test.log(Status.WARNING, "Response Time = " + (responseTime) + " ms");
        }
        try {
            //Parse Actual Response to JSON Object
            String jsonResponseAsString = apiResponse.asString();
            JsonObject jsonResponseAsJSON = JsonParser.parseString(jsonResponseAsString).getAsJsonObject();
            //Beautify JSON Object
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            String beautifiedResponseAsJSON = gson.toJson(jsonResponseAsJSON);
            //View API response
            test.createNode("Api Response: ").info("<pre>" + beautifiedResponseAsJSON + "</pre>");
        } catch (Exception exception) {
            test.log(Status.FAIL, "Actual Response JSON Parse & Beautify Exception: " + exception);
        }
    }

    public static void successfulApiResponseAssertionsWithEmptyBody(ExtentTest test, String curl, io.restassured.response.Response apiResponse) {

        //Remove Timestamp from curl
        String finalCurl = curl.substring(curl.indexOf("[main] DEBUG curl - ") + 20);
        //Log Request curl
        test.createNode("Request CURL: ").info("<pre>" + finalCurl + "</pre>");
        //Get API response Status Code
        int statusCode = apiResponse.getStatusCode();
        //Assert API response
        try {
            Assert.assertEquals(statusCode, 200);
            test.log(Status.PASS, "API Status Code: 200");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "API Status Code: ".concat(String.valueOf(statusCode)));
        }

        //Get Response Time
        long responseTime = apiResponse.getTime();
        //Report API Performance
        if (responseTime <= ResponseTimeThresholds.apiResponseTimeThreshold) {
            test.log(Status.PASS, "Response Time = " + (responseTime) + " ms");
        } else {
            test.log(Status.WARNING, "Response Time = " + (responseTime) + " ms");
        }
    }

    public static void successfulApiResponseAssertionsArray(ExtentTest test, String curl, io.restassured.response.Response apiResponse) {

        //Remove Timestamp from curl
        String finalCurl = curl.substring(curl.indexOf("[main] DEBUG curl - ") + 20);
        //Log Request curl
        test.createNode("Request CURL: ").info("<pre>" + finalCurl + "</pre>");
        //Get API response Status Code
        int statusCode = apiResponse.getStatusCode();
        //Assert API response
        try {
            Assert.assertEquals(statusCode, 200);
            test.log(Status.PASS, "API Status Code: 200");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "API Status Code: ".concat(String.valueOf(statusCode)));
        }

        //Get Response Time
        long responseTime = apiResponse.getTime();
        //Report API Performance
        if (responseTime <= ResponseTimeThresholds.apiResponseTimeThreshold) {
            test.log(Status.PASS, "Response Time = " + (responseTime) + " ms");
        } else {
            test.log(Status.WARNING, "Response Time = " + (responseTime) + " ms");
        }
        try {
            //Parse Actual Response to JSON Array
            String jsonResponseAsString = apiResponse.asString();
            JsonArray jsonResponseAsJSON = JsonParser.parseString(jsonResponseAsString).getAsJsonArray();
            //Beautify JSON Object
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            String beautifiedResponseAsJSON = gson.toJson(jsonResponseAsJSON);
            //View API response
            test.createNode("Api Response: ").info("<pre>" + beautifiedResponseAsJSON + "</pre>");
        } catch (Exception exception) {
            test.log(Status.FAIL, "Actual Response JSON Parse & Beautify Exception: " + exception);
        }
    }

    public static org.json.JSONObject responseInterfaceAssertion(org.json.JSONObject actualResponse, org.json.JSONObject expectedResponse, org.json.JSONObject missingKeys, org.json.JSONObject mismatchTypeKeys) {

        for (final Iterator<String> iterator = expectedResponse.keys(); iterator.hasNext(); ) {

            final String key = iterator.next();

            if (!actualResponse.has(key)) {
                missingKeys.put(InterfaceAssertionFailedKeys.failedAssertionKeyFullPath.concat(key), "Not Found");
            } else {
                String expectedType;
                if (expectedResponse.get(key).equals("Null")) {
                    expectedType = "Null";
                } else {
                    expectedType = expectedResponse.get(key).getClass().getSimpleName();
                }
                String actualType = actualResponse.get(key).getClass().getSimpleName();

                if (!expectedType.equals(actualType)) {
                    mismatchTypeKeys.put(InterfaceAssertionFailedKeys.failedAssertionKeyFullPath.concat(key), "Expected [".concat(expectedType).concat("] but found [").concat(actualType).concat("]"));
                }

                Object obj = expectedResponse.get(key);

                boolean actualNull = !expectedType.equals(actualType) && !expectedType.equals("Null");

                if (obj instanceof org.json.JSONObject && !expectedResponse.getJSONObject(key).isEmpty() && !actualNull) {
                    InterfaceAssertionFailedKeys.failedAssertionKeyFullPath = InterfaceAssertionFailedKeys.failedAssertionKeyFullPath.concat(key).concat("_");
                    if (!actualResponse.getJSONObject(key).isEmpty()) {
                        responseInterfaceAssertion(actualResponse.getJSONObject(key), expectedResponse.getJSONObject(key), missingKeys, mismatchTypeKeys);
                    } else {
                        for (final Iterator<String> missingParametersIterator = expectedResponse.keys(); missingParametersIterator.hasNext(); ) {
                            final String missingParameter = missingParametersIterator.next();
                            missingKeys.put(InterfaceAssertionFailedKeys.failedAssertionKeyFullPath.concat(missingParameter), "Not Found");
                        }
                    }

                    InterfaceAssertionFailedKeys.failedAssertionKeyFullPath = InterfaceAssertionFailedKeys.failedAssertionKeyFullPath.replace(key.concat("_"), "");

                } else if (obj instanceof org.json.JSONArray && expectedResponse.getJSONArray(key).length() != 0 && !actualNull) {

                    InterfaceAssertionFailedKeys.failedAssertionKeyFullPath = InterfaceAssertionFailedKeys.failedAssertionKeyFullPath.concat(key).concat("_");

                    if (actualResponse.getJSONArray(key).length() != 0) {

                        responseInterfaceAssertion(actualResponse.getJSONArray(key).getJSONObject(0), expectedResponse.getJSONArray(key).getJSONObject(0), missingKeys, mismatchTypeKeys);

                    } else {

                        for (final Iterator<String> missingParametersIterator = expectedResponse.getJSONArray(key).getJSONObject(0).keys(); missingParametersIterator.hasNext(); ) {

                            final String missingParameter = missingParametersIterator.next();
                            missingKeys.put(InterfaceAssertionFailedKeys.failedAssertionKeyFullPath.concat(missingParameter), "Not Found");

                        }
                    }

                    InterfaceAssertionFailedKeys.failedAssertionKeyFullPath = InterfaceAssertionFailedKeys.failedAssertionKeyFullPath.replace(key.concat("_"), "");

                }
            }
        }
        return missingKeys;
    }

    public static void printResponseAssertionResults(ExtentTest test, org.json.JSONObject interfaceAssertionResult, org.json.JSONObject mismatchTypeKeys) throws IOException {

        if (interfaceAssertionResult.length() == 0 && mismatchTypeKeys.length() == 0) {

            test.log(Status.PASS, "Interface Assertion Status: Succeeded!");

        } else {

            test.log(Status.FAIL, "Interface Assertion Status: Failed!");

            if (interfaceAssertionResult.length() != 0) {

                //Order Missing Parameters Alphabetically
                String missingKeysAsString = interfaceAssertionResult.toString();
                ObjectMapper om = new ObjectMapper();
                om.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
                Map<String, Object> map = om.readValue(missingKeysAsString, HashMap.class);
                String missingKeysOrdered = om.writeValueAsString(map);

                //Beautify Missing Keys JSON
                JsonObject missingKeysAsJson = JsonParser.parseString(missingKeysOrdered).getAsJsonObject();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String missingKeysBeautified = gson.toJson(missingKeysAsJson);

                //Print Missing Keys From Interface
                test.createNode("Response's Missing Parameters: ").fail("<pre>" + missingKeysBeautified + "</pre>");

            }

            if (mismatchTypeKeys.length() != 0) {

                //Order Mismatch Type Keys Alphabetically
                String mismatchTypeKeysAsString = mismatchTypeKeys.toString();
                ObjectMapper om = new ObjectMapper();
                om.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
                Map<String, Object> map = om.readValue(mismatchTypeKeysAsString, HashMap.class);
                String mismatchTypeKeysOrdered = om.writeValueAsString(map);

                //Beautify Mismatch Type Keys JSON
                JsonObject mismatchTypeKeysAsJson = JsonParser.parseString(mismatchTypeKeysOrdered).getAsJsonObject();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String mismatchTypeKeysBeautified = gson.toJson(mismatchTypeKeysAsJson);

                //Print Missing Keys From Interface
                test.createNode("Response's Keys Type Mismatch: ").fail("<pre>" + mismatchTypeKeysBeautified + "</pre>");

            }
        }
    }

    public static org.json.JSONObject responseInterfaceAssertionArray(org.json.JSONArray actualResponse, org.json.JSONArray expectedResponse, org.json.JSONObject missingKeys, org.json.JSONObject mismatchTypeKeys) {

        for (int i = 0; i < expectedResponse.length(); i++) {
            if (actualResponse.length() <= i) {
                missingKeys.put(InterfaceAssertionFailedKeys.failedAssertionKeyFullPath.concat("Index ").concat(Integer.toString(i)), "Not Found");
                continue;
            }

            Object expectedObj = expectedResponse.get(i);
            Object actualObj = actualResponse.get(i);

            if (!expectedObj.getClass().equals(actualObj.getClass())) {
                mismatchTypeKeys.put(InterfaceAssertionFailedKeys.failedAssertionKeyFullPath.concat("Index ").concat(Integer.toString(i)), "Expected [" + expectedObj.getClass().getSimpleName() + "] but found [" + actualObj.getClass().getSimpleName() + "]");
                continue;
            }

            if (expectedObj instanceof org.json.JSONObject && actualObj instanceof org.json.JSONObject) {
                responseInterfaceAssertion((org.json.JSONObject) actualObj, (org.json.JSONObject) expectedObj, missingKeys, mismatchTypeKeys);
            } else if (expectedObj instanceof org.json.JSONArray && actualObj instanceof org.json.JSONArray) {
                responseInterfaceAssertionArray((org.json.JSONArray) actualObj, (org.json.JSONArray) expectedObj, missingKeys, mismatchTypeKeys);
            }
        }
        return missingKeys;
    }

    public static void printResponseAssertionResultsArray(ExtentTest test, org.json.JSONObject interfaceAssertionResult, org.json.JSONObject mismatchTypeKeys) throws IOException {

        if (interfaceAssertionResult.length() == 0 && mismatchTypeKeys.length() == 0) {
            test.log(Status.PASS, "Interface Assertion Status: Succeeded!");
        } else {
            test.log(Status.FAIL, "Interface Assertion Status: Failed!");

            if (interfaceAssertionResult.length() != 0) {
                for (String key : interfaceAssertionResult.keySet()) {

                    //Order Missing Parameters Alphabetically
                    String missingKeysAsString = interfaceAssertionResult.get(key).toString();
                    ObjectMapper om = new ObjectMapper();
                    om.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
                    Map<String, Object> map = om.readValue(missingKeysAsString, HashMap.class);
                    String missingKeysOrdered = om.writeValueAsString(map);

                    //Beautify Missing Keys JSON
                    JsonObject missingKeysAsJson = JsonParser.parseString(missingKeysOrdered).getAsJsonObject();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String missingKeysBeautified = gson.toJson(missingKeysAsJson);

                    //Print Missing Keys From Interface
                    test.createNode("Response's Missing Parameters: " + key).fail("<pre>" + missingKeysAsString + "</pre>");
                }
            }

            if (mismatchTypeKeys.length() != 0) {
                for (String key : mismatchTypeKeys.keySet()) {

                    //Order Mismatch Type Keys Alphabetically
                    String mismatchTypeKeysAsString = mismatchTypeKeys.get(key).toString();
                    ObjectMapper om = new ObjectMapper();
                    om.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
                    Map<String, Object> map = om.readValue(mismatchTypeKeysAsString, HashMap.class);
                    String mismatchTypeKeysOrdered = om.writeValueAsString(map);

                    //Beautify Mismatch Type Keys JSON
                    JsonObject mismatchTypeKeysAsJson = JsonParser.parseString(mismatchTypeKeysOrdered).getAsJsonObject();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String mismatchTypeKeysBeautified = gson.toJson(mismatchTypeKeysAsJson);

                    //Print Missing Keys From Interface
                    test.createNode("Response's Keys Type Mismatch: " + key).fail("<pre>" + mismatchTypeKeysAsString + "</pre>");
                }
            }
        }
    }
}