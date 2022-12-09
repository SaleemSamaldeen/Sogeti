package sogeti.com;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;
import ui.pages.HomePage;
import utils.TestSetup;

import static helper.Countries.countryNames;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static ui.pages.HomePage.countryNames;

@Feature("Check all country links")
@TmsLink("TC03")
@Severity(SeverityLevel.CRITICAL)
@Tag("SmokeTest")
public class SogetiLinksTest extends TestSetup {

    private HomePage homePage;

    @Test
    @Description("Verify all country specific links are working")
    void checkSogetiCountryLinks() {

        homePage = new HomePage(driver);

        step("User launched Sogeti home page and accept cookies",() -> {
            homePage.acceptCookie();
        });

        step("Click on Worldwide Dropdown link in Page Header",() -> {
            homePage.clickWorldWide();
        });

        step("Check if all Sogeti country links displayed",() -> {
            assertThat(homePage.getAllCountryNames())
                    .withFailMessage("All Sogeti Country Names not displayed")
                    .containsExactlyElementsOf(countryNames());
        });

        step("Check if all Sogeti country links redirecting to specific page",() -> {
            assertThat(homePage.checkCountryLinks())
                    .withFailMessage("Country links are not working as expected ")
                    .isEqualTo(countryNames.size());
        });
    }
}
