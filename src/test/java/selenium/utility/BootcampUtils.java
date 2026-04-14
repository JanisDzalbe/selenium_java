package selenium.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BootcampUtils {
    public static WebDriver initializeEdgeDriver() {
        System.setProperty("webdriver.edge.driver", Constants.libWithDriversLocation + "edgedriver" + new selenium.ChangeToFileExtension().extension());
        return new EdgeDriver();
    }
}
