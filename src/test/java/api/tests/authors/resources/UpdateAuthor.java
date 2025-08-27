package api.tests.authors.resources;

import org.json.JSONObject;

import static dtos.CreateAuthorDto.constructCreateAuthorDto;

public class UpdateAuthor {

    public static JSONObject constructExpectedResponseInterface(){
        JSONObject expectedResponse = constructCreateAuthorDto();
        return expectedResponse;
    }
}
