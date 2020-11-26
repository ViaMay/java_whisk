package tests;

import core.api.Request.deleteShoppingListRequest;
import core.api.Request.getShoppingListRequest;
import core.api.Request.getShoppingListsRequest;
import core.api.Request.postShoppingListRequest;
import core.api.Responses.ErrorResponse;
import core.api.Responses.ListResponse;
import core.api.Responses.ListsResponse;
import core.pages.BasePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WhiskApiTests extends BasePage {

    @Test(description = "add shopping List tests")
    public void addShoppingListTest() {
        String shoppingListName = "api_test_shopping_list";
//        take All shopping lists
        ListsResponse listsResponse = getShoppingListsRequest.sendRequest();
//        delete All shoppingList by name =  "api_test_shopping_list"
        for (int i = 0; i < listsResponse.getList().size(); i = i + 1 ){
            if (listsResponse.getList().get(i).getName().equals(shoppingListName)){
                deleteShoppingListRequest.sendRequest(listsResponse.getList().get(i).getId());
            }
        }

//      1. Create Shoping list POST: /list/v2
        ListResponse listAddResponse = postShoppingListRequest.sendRequest(shoppingListName);
//      2. Get Shopping List by id: GET /list/v2/{id}
        ListResponse listGetResponse = getShoppingListRequest.sendRequest(listAddResponse.getList().getId());
//      3.  Verify that response contains the necessary id
        assertEquals(listGetResponse.getList().getId(), listAddResponse.getList().getId());
//      4.  Verify that Shopping list is empty (content object is empty)
        assertEquals(listGetResponse.getContent().toString(), "{}");
    }


    @Test(description = "delete shopping List tests")
    public void deleteShoppingListTest() {
        String shoppingListName = "api_test_shopping_list_delete";
//        take All shopping lists
        ListsResponse listsResponse = getShoppingListsRequest.sendRequest();
//        delete All shoppingList by name =  "api_test_shopping_list_delete"
        for (int i = 0; i < listsResponse.getList().size(); i = i + 1 ){
            if (listsResponse.getList().get(i).getName().equals(shoppingListName)){
                deleteShoppingListRequest.sendRequest(listsResponse.getList().get(i).getId());
            }
        }

//       1. Create Shopping list POST: /list/v2
        ListResponse listAddResponse = postShoppingListRequest.sendRequest(shoppingListName);
//       2. Delete Shopping list by id DELETE: /list/v2/{id}
        deleteShoppingListRequest.sendRequest(listAddResponse.getList().getId());
//       3. Get Shopping List by id: GET /list/v2/{id}
        ErrorResponse errorResponse = getShoppingListRequest.sendRequestError(listAddResponse.getList().getId());
//       4. Verify that code response = 200 => todo don't work!
        assertEquals(errorResponse.getCode(), "shoppingList.notFound");
        assertEquals(errorResponse.getErrorCode(), "LIST_ERROR_NOT_FOUND");
//       5. Verify that the response message is 'shoppingList.notFound'
        assertEquals(errorResponse.getMessage(), "Shopping list not found");
    }
}