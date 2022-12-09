package sogeti.com;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;
import ui.pages.AutomationPage;
import ui.pages.HomePage;
import utils.TestSetup;

import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Navigate to Automation page")
@TmsLink("TC01")
@Severity(SeverityLevel.CRITICAL)
@Tag("E2ETest")
public class AutomationAndServiceTest extends TestSetup {

    private AutomationPage automationPage;

    private HomePage homePage;

    @Test
    @Description("Verify user can redirect to Automation page")
    void navigateToAutomationPage() {
        automationPage = new AutomationPage(driver);
        homePage = new HomePage(driver);

        step("User launched Sogeti home page and accept cookies", () -> {
            homePage.acceptCookie();
        });

        step("Hover Services Link and then Click Automation link.", () -> {
            homePage.clickAutomation();
        });

        step("Check that Automation Screen is displayed, and “Automation” text is visible in Page", () -> {
            assertThat(automationPage.getPageUrl())
                    .withFailMessage("Automation page is not displayed")
                    .isEqualTo(baseURL + "services/automation/");
            assertThat(automationPage.getPageHeading())
                    .withFailMessage("Automation page header is not displayed")
                    .isEqualTo("Automation");
            addAttachment("Automation page",automationPage.getScreenshot());
        });

        step("Verify that the Services and Automation are selected", () -> {
            assertThat(homePage.checkBothServicesAndAutomationSelected("#ff304c"))
                    .withFailMessage("Both Services and Automation were not selected")
                    .isTrue();
        });
    }
}
