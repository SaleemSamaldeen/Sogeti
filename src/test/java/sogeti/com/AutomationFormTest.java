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

@Feature("Submit Automation Application Form")
@TmsLink("TC02")
@Severity(SeverityLevel.CRITICAL)
@Tag("E2ETest")
public class AutomationFormTest extends TestSetup {

    private AutomationPage automationPage;

    private HomePage homePage;

    @Test
    @Description("Verify if user submit Automation form successfully")
    void submitAutomationForm() {

        automationPage = new AutomationPage(driver);
        homePage = new HomePage(driver);

        step("User launched Sogeti home page and accept cookies", () -> {
            homePage.acceptCookie();
        });

        step("Hover Services Link and then Click Automation link.", () -> {
            homePage.clickAutomation();
        });

        step("Fill the Automation form", () -> {
            automationPage.enterFirstName();
            automationPage.enterLastName();
            automationPage.enterEmail();
            automationPage.enterPhoneNumber();
            automationPage.enterCompanyName();
            automationPage.selectCountry("Germany");
            automationPage.enterMessage();
        });

        step("Select I agree and not a robot checkbox", () -> {
            automationPage.selectAgreeAndCaptchaCheckbox();
            addAttachment("Automation page",automationPage.getScreenshot());
        });

        step("User submit the Application Form", () -> {
            automationPage.submitForm();
        });

        step("Check success message displayed after form submission", () -> {
            assertThat(automationPage.getSuccessMessage())
                    .withFailMessage("Success message is not displayed")
                    .isEqualToIgnoringCase("Thank you for contacting us.");
        });
    }
}
