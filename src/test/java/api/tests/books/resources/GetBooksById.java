package api.tests.books.resources;

import org.json.JSONArray;
import org.json.JSONObject;

import static dtos.GetBooksByIdDto.constructGetBooksByIdDto;

public class GetBooksById {
    public static int bookId;
    public static JSONObject constructExpectedResponseInterface(){
        JSONObject expectedResponse = constructGetBooksByIdDto();
        return expectedResponse;
    }
}
