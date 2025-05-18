package selenium.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BootcampUtils {
    public static WebDriver initializeChromeDriver() {
        System.setProperty("webdriver.chrome.driver", Constants.libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        return new ChromeDriver();
    }
}
