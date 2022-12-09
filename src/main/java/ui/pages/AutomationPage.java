package ui.pages;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AutomationPage {

    private WebDriver driver;

    public AutomationPage(WebDriver driver) {
        this.driver = driver;
    }

    By pageHeading = By.cssSelector("div.page-heading"); //get current page url

    By firstName = By.xpath("//div[contains(@class,'row-0')]//input");

    By lastName = By.xpath("//div[contains(@class,'row-1')]//input");

    By email = By.xpath("//div[contains(@class,'row-2')]//input");

    By phoneNumber = By.xpath("//div[contains(@class,'row-3')]//input");

    By company = By.xpath("//div[contains(@class,'row-4')]//input");

    By countryDropdown = By.xpath("//div[contains(@class,'row-5')]//select");

    By message = By.xpath("//div[contains(@class,'row-6')]//textarea");

    By agreeCheckbox = By.xpath("//label[text()='I agree']");

    By captchaCheckbox = By.cssSelector("span#recaptcha-anchor");

    By submitButton = By.xpath("//button[@name='submit']");

    By captchaFrame = By.xpath("//iframe[@title='reCAPTCHA']");

    By successMessage = By.xpath("//div[contains(@class,'Success__Message')]//p");

    public void enterFirstName() {
        driver.findElement(firstName).sendKeys(RandomStringUtils.randomAlphabetic(6));
    }

    public void enterLastName() {
        driver.findElement(lastName).sendKeys(RandomStringUtils.randomAlphabetic(6));
    }

    public void enterEmail() {
        driver.findElement(email).sendKeys(RandomStringUtils.randomAlphabetic(6).concat("@automation.com"));
    }

    public void enterPhoneNumber() {
        driver.findElement(phoneNumber).sendKeys(RandomStringUtils.randomNumeric(10));
    }

    public void enterCompanyName() {
        driver.findElement(company).sendKeys(RandomStringUtils.randomAlphabetic(7));
    }

    public void selectCountry(String country) {
        Select select = new Select(driver.findElement(countryDropdown));
        select.selectByVisibleText(country);
    }

    public void enterMessage() {
        driver.findElement(message).sendKeys(RandomStringUtils.randomAlphabetic(7));
    }

    public void selectAgreeAndCaptchaCheckbox() {
        driver.findElement(agreeCheckbox).click();
        WebElement element = driver.findElement(captchaFrame);
        driver.switchTo().frame(element);
        driver.findElement(captchaCheckbox).click();
        driver.switchTo().defaultContent();
    }

    public void submitForm() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(submitButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText().trim();
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageHeading() {
        return driver.findElement(pageHeading).getText().trim();
    }

    public FileInputStream getScreenshot() throws IOException {
        File takesScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return FileUtils.openInputStream(takesScreenshot);
    }





}
