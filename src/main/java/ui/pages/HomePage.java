package ui.pages;

import helper.Countries;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By cookie = By.cssSelector("button.acceptCookie");

    By services = By.xpath("//li[@data-levelname='level2']//span");

    By automation = By.xpath("//a[text()='Automation']");

    By worldWide = By.xpath("//div[contains(@class,'global-arrowdown')]");

    By countryList = By.cssSelector("div#country-list-id ul li a");

    private static List<WebElement> countries;

    public int count = 0;

    private boolean country = false;

    public static List<String> countryNames;

    public void clickWorldWide() {
        driver.findElement(worldWide).click();
    }

    public void acceptCookie() {
        driver.findElement(cookie).click();
    }

    public void clickAutomation() {
        mouseHoverServices();
        driver.findElement(automation).click();
    }

    public void mouseHoverServices() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(services)).perform();
    }

    public List<String> getAllCountryNames() {
        countryNames = new ArrayList<>();
        countries = driver.findElements(countryList);
        countries.forEach(element -> countryNames.add(element.getText().trim()));
        return countryNames;
    }

    public int checkCountryLinks() {
        String currentWindow = driver.getWindowHandle();
        for (String countryName : countryNames) {
            String locatorName = countryName.length() <= 3 ? countryName : countryName.substring(0, 1).toUpperCase() + countryName.substring(1).toLowerCase();
            driver.findElement(By.xpath("//a[text()='" + locatorName + "']")).click();
            if (validateNewWindow(countryName, currentWindow)) {
                count++;
            }
        }
        return count;
    }

    private boolean validateNewWindow(String countryName, String currentWindow) {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!currentWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                country = driver.getCurrentUrl().equals(Countries.valueOf(countryName).getPageUrl());
                driver.close();
            }
        }
        driver.switchTo().window(currentWindow);
        return country;
    }

    public boolean checkBothServicesAndAutomationSelected(String expectedColor) {
        mouseHoverServices();
        return Color.fromString(driver.findElement(services).getCssValue("color")).asHex().equals(expectedColor)
                && Color.fromString(driver.findElement(automation).getCssValue("color")).asHex().equals(expectedColor);
    }

}
