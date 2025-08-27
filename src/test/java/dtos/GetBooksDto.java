package dtos;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetBooksDto {

    public static JSONArray constructGetBooksDto(){

        JSONArray array = new JSONArray();
        JSONObject getBooks = new JSONObject();
        getBooks.put("id", 0);
        getBooks.put("title", "");
        getBooks.put("description", "");
        getBooks.put("pageCount", 0);
        getBooks.put("excerpt", "");
        getBooks.put("publishDate", "");

        array.put(getBooks);

        return array;
    }
}
