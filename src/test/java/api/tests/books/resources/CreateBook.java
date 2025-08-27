package api.tests.books.resources;

import org.json.JSONObject;

import static dtos.CreateBookDto.constructCreateBookDto;

public class CreateBook {
    public static int createdBookId =  115351;
    public static JSONObject constructExpectedResponseInterface(){
        JSONObject expectedResponse = constructCreateBookDto();
        return expectedResponse;
    }
}
