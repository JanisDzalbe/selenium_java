package selenium.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BootcampUtils {
    public static WebDriver initializeChromeDriver() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", Constants.libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        return new ChromeDriver();
    }
}
