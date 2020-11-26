package core.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Controller {
    private RestTemplate restTemplate;
    private final String BASE_URL = "https://api.whisk-dev.com/";
    private String authorization = "Bearer NtNx6DeeWgzXOWhgIW89bPbXV1WRx7852IQJtRNVcvmH5xuOrHrT6qFkg1wbwIru";

    public Controller() {
        restTemplate = new RestTemplate();
    }

    public String controllerGet(String url) {
        return sendRequest(url, HttpMethod.GET, null);
    }

    public String controllerDelete(String url) {
        return sendRequest(url, HttpMethod.DELETE, null);
    }

    public String controllerPost(String url, Map<String, Object> values) {
        return sendRequest(url, HttpMethod.POST, values);
    }

    private String sendRequest (String url, HttpMethod httpMethod, Map<String, Object> values) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            headers.add("Authorization", authorization);
            HttpEntity entityValue;
            if (values != null) {
                entityValue = new HttpEntity<>(values, headers);
            } else
                entityValue = new HttpEntity(headers);

            ResponseEntity<String> entity = restTemplate.exchange(
                    BASE_URL + url, httpMethod, entityValue, String.class);
            assertEquals(entity.getStatusCode().toString(), "200");
            return entity.getBody();
        } catch (HttpClientErrorException errorException) {
            String responseBody = errorException.getResponseBodyAsString();
            assertEquals(errorException.getStatusCode().value(), 400);
            System.out.println(responseBody);
            return responseBody;
        }
    }
}
