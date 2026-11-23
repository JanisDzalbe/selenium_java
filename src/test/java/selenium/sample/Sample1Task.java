package selenium.sample;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

/**
 * Activity 1: Basic Selenium WebDriver setup, navigation,
 * page info retrieval, and browser teardown.
 */
public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    /**
     * Opens the example site v2 in Chrome, prints the page title and current URL,
     * and closes the browser.
     *
     * @throws Exception if driver initialization fails
     */
    @Test
    public void goToHomepage() throws Exception {
        // define driver
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        WebDriver driver = new ChromeDriver();

        // go to https://janisdzalbe.github.io/example-site/index2.html
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");

        // get title of page
        System.out.println(driver.getTitle());

        // get URL of current page
        System.out.println(driver.getCurrentUrl());

        // close browser
        driver.quit();
    }
}