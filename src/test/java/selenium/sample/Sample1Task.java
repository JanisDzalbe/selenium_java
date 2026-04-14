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
        // set path to chromedriver
        System.setProperty("webdriver.chrome.driver",
                libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        // define driver
        WebDriver driver = new ChromeDriver();

        // go to page
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");

        // get title of page
        System.out.println(driver.getTitle());

        // get URL of current page
        System.out.println(driver.getCurrentUrl());

        // optional wait (just like in sample)
        Thread.sleep(5000);

        // close browser
        driver.quit();
    }
}