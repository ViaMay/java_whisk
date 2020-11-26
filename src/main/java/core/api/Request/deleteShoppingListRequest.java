package core.api.Request;

import core.api.Controller;
import org.springframework.http.ResponseEntity;

import static org.testng.Assert.assertEquals;

public class deleteShoppingListRequest
{
    public static void sendRequest(String id) {
        Controller controller = new Controller();
        String responseDeleteEntity = controller.controllerDelete("list/v2/" + id);
        assertEquals(responseDeleteEntity, "{}");
    }
}