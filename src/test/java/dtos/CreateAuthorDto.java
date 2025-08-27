package dtos;

import org.json.JSONObject;

public class CreateAuthorDto {

    public static JSONObject constructCreateAuthorDto(){

        JSONObject author = new JSONObject();
        author.put("id", 0);
        author.put("idBook", 0);
        author.put("firstName", "");
        author.put("lastName", "");

        return author;
    }
}
