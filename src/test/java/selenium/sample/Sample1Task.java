package selenium.sample;

import org.junit.jupiter.api.Test;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample1Task {


    @Test
    public void goToHomepage() throws Exception {

        // define driver
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "lib" + File.separator + "chromedriver");
        WebDriver driver = new ChromeDriver();

        // go to URL
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");

        // get title of page
        System.out.println(driver.getTitle());

        // get URL of current page
        System.out.println(driver.getCurrentUrl());

        // close browser
        driver.quit();
    }
}
