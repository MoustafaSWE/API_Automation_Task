package api.tests.books.resources;

import org.json.JSONObject;

import static dtos.CreateBookDto.constructCreateBookDto;
import static dtos.DeleteBookDto.constructDeleteBookDto;

public class DeleteBook {

    public static JSONObject constructExpectedResponseInterface(){
        JSONObject expectedResponse = constructDeleteBookDto();
        return expectedResponse;
    }
}
