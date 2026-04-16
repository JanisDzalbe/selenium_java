package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.Color;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class extra2Task {
    WebDriver driver;
    String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    // Helper method to perform the actual check
    public void checkStyle() {
        driver.get("https://janisdzalbe.github.io/example-site/examples/po");

        // Find the h1 element
        String backgroundColorRaw = driver.findElement(By.tagName("h1")).getCssValue("background-color");

        // Normalize the color using Selenium's Color class
        // Based on the site CSS, the background is likely transparent (rgba(0, 0, 0, 0))
        // or a specific w3-color. Let's convert it to Hex for a reliable check.
        String hexColor = Color.fromString(backgroundColorRaw).asHex();

        // Print it out so you can see what the browser sees
        System.out.println("Background color in hex: " + hexColor);

        // Example assertion (adjust #000000 to the actual hex code you expect)
        // If it's transparent, it often shows as rgba(0, 0, 0, 0) -> #000000 with 0 alpha
        assertEquals("#000000", hexColor);
    }

    @AfterEach
    public void endingTests() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void runningOnFirefox() throws Exception {
        System.setProperty("webdriver.gecko.driver", libWithDriversLocation + "geckodriver.exe");
        driver = new FirefoxDriver();
        checkStyle();
    }

    @Test
    public void runningOnChrome() throws Exception {
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        checkStyle();
    }

    @Test
    public void runningOnIE() throws Exception {
        // Note: IE requires specific protected mode settings to work reliably
        System.setProperty("webdriver.ie.driver", libWithDriversLocation + "IEDriverServer.exe");
        driver = new InternetExplorerDriver();
        checkStyle();
    }
}