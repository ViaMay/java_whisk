package core.api.Request;

import com.google.gson.Gson;
import core.api.Controller;
import core.api.Responses.ErrorResponse;
import core.api.Responses.ListResponse;

import static org.testng.Assert.assertEquals;

public class getShoppingListRequest
{
    public static ListResponse sendRequest(String id) {
        Controller controller = new Controller();
        String responseEntity = controller.controllerGet("list/v2/" + id);
        Gson gson = new Gson();
        System.out.println(responseEntity);
        ListResponse responseClass = gson.fromJson(responseEntity, ListResponse.class);
        assertEquals(responseClass.getList().getName().isEmpty(), false);
        assertEquals(responseClass.getList().getPrimary(), false);
        assertEquals(responseClass.getList().getId(), id);
        return responseClass;
    }

    public static ErrorResponse sendRequestError(String id) {
        Controller controller = new Controller();
        String responseEntity = controller.controllerGet("list/v2/" + id);
        Gson gson = new Gson();
        System.out.println(responseEntity);
        ErrorResponse responseClass = gson.fromJson(responseEntity, ErrorResponse.class);
        return responseClass;
    }
}