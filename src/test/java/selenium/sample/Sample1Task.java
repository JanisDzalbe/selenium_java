package selenium.sample;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir")
            + File.separator + "lib" + File.separator;
    @Test
    public void goToHomepage() throws Exception {
        // define driver
        System.setProperty("webdriver.chrome.driver",
                libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        // start browser
        WebDriver driver = new ChromeDriver();

        // go to test page
        driver.get("https://www.google.com");

        // print title
        System.out.println(driver.getTitle());

        // print URL
        System.out.println(driver.getCurrentUrl());

        // close browser
        driver.quit();
    }
}
