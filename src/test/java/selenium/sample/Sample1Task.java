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
//        TODO: define driver
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        WebDriver driver = new ChromeDriver();
//         TODO: go to https://janisdzalbe.github.io/example-site/index2.html
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");
//         TODO: get title of page
        System.out.println("Title of page: " + driver.getTitle());
//         TODO: get URL of current page
        System.out.println("URL of current page: " + driver.getCurrentUrl());
//         TODO: close browser
        driver.quit();
    }
}
