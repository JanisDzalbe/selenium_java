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
//         go to https://acctabootcamp.github.io/site/index2.html
//         get title of page
//         get URL of current page
//         close browser
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        WebDriver driver = new ChromeDriver();

        driver.get("https://acctabootcamp.github.io/site/");
        System.out.println(driver.findElement(By.id("h1")).getText());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(5000);
        driver.quit();

    }
}
