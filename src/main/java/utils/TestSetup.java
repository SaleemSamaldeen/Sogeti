package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestSetup extends ConfigHelper {

    protected WebDriver driver;

    @BeforeMethod
    public void launchPage(){
        if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else driver = new FirefoxDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
