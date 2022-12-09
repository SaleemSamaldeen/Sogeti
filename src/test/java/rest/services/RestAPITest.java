package rest.services;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import restapi.RestServices;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Stuttgart location post code and place name")
@TmsLink("RestAPITC01")
@Severity(SeverityLevel.CRITICAL)
@Tag("E2ETest")
public class RestAPITest {

    private Response response;

    @Test
    @Description("Verify stuttgart location post code and place name")
    void checkStuttgartArea() {

        String baseURI = "http://api.zippopotam.us/de/bw/stuttgart";
        String postCode = "70597";
        String placeName = "Stuttgart Degerloch";

        RestServices restServices = new RestServices(baseURI);

        step("Assert the status code, response time and content type for " + baseURI, () -> {
            response = restServices.assertGETRequest();
        });

        step("Check country and state field", () -> {
            assertThat(restServices.assertJSONField(response, "country")).isEqualToIgnoringCase("Germany");
            assertThat(restServices.assertJSONField(response, "state")).isEqualToIgnoringCase("Baden-Württemberg");
        });

        step("Check postal code and place name field", () -> {
            assertThat(restServices.assertJSONByPostCode(response, postCode, placeName))
                    .withFailMessage("Postal Code: " + postCode + " has incorrect place name - " + placeName)
                    .isTrue();
        });

    }


}
