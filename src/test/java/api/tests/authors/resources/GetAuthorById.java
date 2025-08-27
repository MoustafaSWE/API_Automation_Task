package api.tests.authors.resources;

import org.json.JSONObject;

import static dtos.GetAuthorByIdDto.constructGetAuthorByIdDto;

public class GetAuthorById {
    public static int bookId;
    public static JSONObject constructExpectedResponseInterface(){
        JSONObject expectedResponse = constructGetAuthorByIdDto();
        return expectedResponse;
    }
}
