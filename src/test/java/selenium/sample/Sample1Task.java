package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

/**
 * Basic Selenium test to open a webpage and fetch details
 */
public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Test
    public void goToHomepage() throws Exception {

        // Set system property - we will have a method to do this from now on
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        // initialize driver
        WebDriver driver = new ChromeDriver();

        // open the given URL
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");

        // get and print page title
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        // get and print current URL
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        // Sleep for 10 seconds
        Thread.sleep(10000);

        // close browser
        driver.quit();

    }
}
