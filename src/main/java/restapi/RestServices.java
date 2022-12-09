package restapi;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class RestServices {

    private String baseurl;
    private RequestSpecification requestSpec;
    protected Response response;

    public RestServices(String endpoint) {
        this.baseurl = endpoint;
    }

    public Response assertPOSTRequest() {
        response = getRequestSpec().request(Method.POST, baseurl);
        return assertResponse(response, "POST");
    }

    public Response assertGETRequest() {
        response = getRequestSpec().request(Method.GET, baseurl);
        return assertResponse(response, "GET");
    }

    private Response assertResponse(Response response, String method) {
        assertThat(response.getStatusCode())
                .withFailMessage(method + ": " + baseurl + " - is not success")
                .isEqualTo(200);
        assertThat(response.getTimeIn(TimeUnit.SECONDS))
                .withFailMessage(method + " - " + baseurl + " - response time is not less than 1 second")
                .isLessThan(1);
        assertThat(response.getContentType())
                .withFailMessage(method + " - " + baseurl + " - response body content is not JSON")
                .isEqualTo("application/json");
        return response;
    }

    private RequestSpecification getRequestSpec() {
        RestAssured.baseURI = baseurl;
        requestSpec = RestAssured.given();
        return requestSpec;
    }

    public boolean assertJSONByPostCode(Response response, String postCode, String placeName){
        boolean result = false;
        List<HashMap<String,String>> responseList = response.getBody().jsonPath().get("places");
        Iterator<HashMap<String,String>> iterator = responseList.iterator();
        while(iterator.hasNext()) {
            HashMap<String,String> locationInfo = iterator.next();
            if(locationInfo.get("post code").equals(postCode)){
                result = locationInfo.get("place name").equals(placeName);
                break;
            }
        }
        return result;
    }

    public boolean assertJSONByPlaceName(Response response, String placeName){
        List<HashMap<String,String>> responseList = response.getBody().jsonPath().get("places");
        return responseList.get(0).get("place name").equals(placeName);
    }

    public String assertJSONField(Response response, String jsonPath){
        return response.getBody().jsonPath().get(jsonPath).toString();
    }
}
