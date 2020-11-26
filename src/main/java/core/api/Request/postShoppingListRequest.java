package core.api.Request;

import com.google.gson.Gson;
import core.api.Controller;
import core.api.Responses.ListResponse;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class postShoppingListRequest
{
    public static ListResponse sendRequest(String name) {
        Map<String, Object> values = new HashMap<>();
        values.put("name", name);
        values.put("primary", false);

        Controller controller = new Controller();
        String responseAddEntity = controller.controllerPost("list/v2", values);
        Gson gson = new Gson();
        System.out.println(responseAddEntity);
        ListResponse responseClass = gson.fromJson(responseAddEntity, ListResponse.class);
        assertEquals(responseClass.getList().getName().toString(), name);
        assertEquals(responseClass.getList().getPrimary(), false);
        assertEquals(responseClass.getList().getId().isEmpty(), false);
        return responseClass;
    }
}