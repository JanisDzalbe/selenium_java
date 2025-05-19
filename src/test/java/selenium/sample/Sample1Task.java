package selenium.sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.utility.BootcampUtils;
import org.junit.jupiter.api.Test;

public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {
        WebDriver driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/index2.html");
        System.out.println("Page Title: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());
        driver.quit();
    }
}
