package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
// Set system property - we will have a method to do this from now on
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // initialize driver
        WebDriver driver = new ChromeDriver();

//         go to https://janisdzalbe.github.io/example-site/index2.html
        driver.get("https://janisdzalbe.github.io/example-site/");
//         get title of page
        System.out.println(driver.findElement(By.id("h1")).getText());
//         get URL of current page
        System.out.println(driver.getCurrentUrl());

        Thread.sleep(10000);
//         close browser
        driver.quit();
    }
}
