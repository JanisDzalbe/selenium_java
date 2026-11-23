
package selenium.sample;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample1Task {

    static String libWithDriversLocation =
            System.getProperty("user.dir")
                    + File.separator + "lib" + File.separator;

    @Test
    public void goToHomepage() throws Exception {

        // Set system property
        System.setProperty(
                "webdriver.chrome.driver",
                libWithDriversLocation
                        + "chromedriver"
                        + new selenium.ChangeToFileExtension().extension()
        );

        // Initialize driver
        WebDriver driver = new ChromeDriver();

        // Open test homepage
        driver.get("https://janisdzalbe.github.io/example-site/");

        // Get title of page
        System.out.println(driver.getTitle());

        // Get URL of current page
        System.out.println(driver.getCurrentUrl());

        // Sleep for 10 seconds
        Thread.sleep(10000);

        // Close browser
        driver.quit();
    }
}
