package api.tests.authors.resources;

import org.json.JSONObject;

import static dtos.DeleteAuthorDto.constructDeleteAuthorDto;

public class DeleteAuthor {

    public static JSONObject constructExpectedResponseInterface(){
        JSONObject expectedResponse = constructDeleteAuthorDto();
        return expectedResponse;
    }
}
