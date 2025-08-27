package api.tests.books.resources;

import org.json.JSONObject;

import static dtos.CreateBookDto.constructCreateBookDto;

public class UpdateBook {

    public static JSONObject constructExpectedResponseInterface(){
        JSONObject expectedResponse = constructCreateBookDto();
        return expectedResponse;
    }
}
