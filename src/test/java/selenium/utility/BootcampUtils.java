package selenium.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;

public class BootcampUtils {
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
    public static WebDriver initializeDriver() {
        System.setProperty(
                "webdriver.edge.driver",
                libWithDriversLocation + "msedgedriver" + new selenium.ChangeToFileExtension().extension()
        );

        return new EdgeDriver();
    }


}