package rest.services;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import restapi.RestServices;
import utils.DataInputProvider;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Place name for different post code and country")
@TmsLink("RestAPITC02")
@Severity(SeverityLevel.CRITICAL)
@Tag("E2ETest")
public class RestTDDTest {

    private Response response;

    @Test(dataProvider = "GenericDataProvider", dataProviderClass = DataInputProvider.class, testName = "ValidatePlaceName")
    @Description("Verify place name for different post code and country")
    void checkPlaceName(String country, String postCode, String placeName) {

        String baseURI = String.format("http://api.zippopotam.us/%s/%s", country, postCode);

        RestServices restServices = new RestServices(baseURI);

        step("Assert the status code, response time and content type for " + baseURI, () -> {
            response = restServices.assertGETRequest();
        });

        step("Check place name for country" + country, () -> {
            assertThat(restServices.assertJSONByPlaceName(response, placeName))
                    .withFailMessage("Postal Code: " + postCode + " has incorrect place name - " + placeName)
                    .isTrue();
        });


    }
}
