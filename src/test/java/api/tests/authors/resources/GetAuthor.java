package api.tests.authors.resources;

import org.json.JSONArray;

import static dtos.GetAuthorDto.constructGetAuthorDto;

public class GetAuthor {
    public static int authorId;
    public static JSONArray constructExpectedResponseInterface(){
        JSONArray expectedResponse = constructGetAuthorDto();
        return expectedResponse;
    }
}
