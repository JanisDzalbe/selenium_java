package selenium.sample;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample1Task {

    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Test
    public void goToHomepage() throws Exception {

        System.setProperty("webdriver.chrome.driver",
                libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        WebDriver driver = new ChromeDriver();

        driver.get("https://janisdzalbe.github.io/example-site/index2.html");

        System.out.println("Title: " + driver.getTitle());

        System.out.println("URL: " + driver.getCurrentUrl());

        Thread.sleep(5000);

        driver.quit();
    }
}