package rest.services;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleAutomation {

    public static void main (String[] params) {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://login.salesforce.com/");
        driver.findElement(By.name("username")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
    }
}
