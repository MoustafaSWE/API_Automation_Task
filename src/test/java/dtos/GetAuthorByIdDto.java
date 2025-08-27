package dtos;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetAuthorByIdDto {

    public static JSONObject constructGetAuthorByIdDto(){

        JSONObject author = new JSONObject();
        author.put("id", 0);
        author.put("idBook", 0);
        author.put("firstName", "");
        author.put("lastName", "");

        return author;
    }
}
