package selenium.sample;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir")
            + File.separator + "lib" + File.separator; 
    @Test
    public void goToHomepage() throws Exception {
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver"  + new selenium.ChangeToFileExtension().extension());
        //        TODO:
//         define driver
        WebDriver driver = new ChromeDriver();
//         go to https://janisdzalbe.github.io/example-site/index2.html
            driver.get("https://janisdzalbe.github.io/example-site/index2.html");
//         get title of page
            System.out.println(driver.getTitle());
//         get URL of current page
            System.out.println(driver.getCurrentUrl());

             Thread.sleep(10000);
//         close browser
            driver.quit();
    }
}
