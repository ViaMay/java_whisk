package core.api.Responses;

import com.google.gson.JsonObject;

public class ListResponse
{
    private ListItem list;
    public ListItem getList() {
        return list;
    }

    private JsonObject content;
    public JsonObject getContent() {
        return content;
    }
}