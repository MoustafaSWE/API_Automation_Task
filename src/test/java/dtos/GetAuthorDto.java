package dtos;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetAuthorDto {

    public static JSONArray constructGetAuthorDto(){

        JSONArray array = new JSONArray();
        JSONObject author = new JSONObject();
        author.put("id", 0);
        author.put("idBook", 0);
        author.put("firstName", "");
        author.put("lastName", "");

        array.put(author);

        return array;
    }
}
