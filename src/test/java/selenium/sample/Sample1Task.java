
package selenium.sample;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;

public class Sample1Task {

    @Test
    public void goToHomepage() {

        String driverPath = System.getProperty("user.dir")
                + File.separator + "lib" + File.separator;

        System.setProperty("webdriver.chrome.driver",
                driverPath + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        WebDriver driver = new ChromeDriver();

        driver.get("https://janisdzalbe.github.io/example-site/index2.html");

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        driver.quit();
    }
}
