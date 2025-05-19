package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static selenium.sample.Sample1.libWithDriversLocation;

public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {
        //setting system property
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // defining driver
        WebDriver driver = new ChromeDriver();

        // going to https://acctabootcamp.github.io/site/index2.html
        driver.get("https://acctabootcamp.github.io/site/index2.html");

        // getting title of page
        System.out.println(driver.getTitle());

        // getting URL of current page
        System.out.println(driver.getCurrentUrl());

        // closing browser
        driver.quit();
    }
}
