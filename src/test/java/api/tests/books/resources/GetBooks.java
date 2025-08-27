package api.tests.books.resources;

import org.json.JSONArray;
import org.json.JSONObject;

import static dtos.GetBooksDto.constructGetBooksDto;

public class GetBooks {
    public static int bookId;
    public static JSONArray constructExpectedResponseInterface(){
        JSONArray expectedResponse = constructGetBooksDto();
        return expectedResponse;
    }
}
