package core.api.Request;

import com.google.gson.Gson;
import core.api.Controller;
import core.api.Responses.ListsResponse;

public class getShoppingListsRequest
{
    public static ListsResponse sendRequest() {
        Controller controller = new Controller();
        String responseEntity = controller.controllerGet("list/v2");
        Gson gson = new Gson();
        System.out.println(responseEntity);
        ListsResponse responseClass = gson.fromJson(responseEntity, ListsResponse.class);
        return responseClass;
    }
}