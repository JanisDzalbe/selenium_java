package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.Color;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class extra2Task {
    WebDriver driver;
    String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }
// Not able to run on my Mac
    @Test
    public void runningOnFirefox() throws Exception {
        System.setProperty("webdriver.gecko.driver", libWithDriversLocation + "geckodriver.exe");
        driver = new FirefoxDriver();
//        TODO
//        go to page https://janisdzalbe.github.io/example-site/examples/po
//        check the background color of h1 element
    }

    @Test
    public void runningOnChrome() throws Exception {
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
//        TODO
//        go to page https://janisdzalbe.github.io/example-site/examples/po
//        check the background color of h1 element
        driver.get("https://janisdzalbe.github.io/example-site/examples/po");
        WebElement h1 = driver.findElement(By.className("w3-jumbo"));
        assertEquals(Color.fromString("rgba(241, 241, 241, 1)"), Color.fromString(getEffectiveBackgroundColor(h1)));
    }
    // Not able to run on my Mac
    @Test
    public void runningOnIE() throws Exception {
        System.setProperty("webdriver.ie.driver", libWithDriversLocation + "IEDriverServer.exe");
        driver = new InternetExplorerDriver();
//        TODO
//        go to page https://janisdzalbe.github.io/example-site/examples/po
//        check the background color of h1 element
    }


    // This thing forced me to check some websites and use AI to understand this concept better.
    public String getEffectiveBackgroundColor(WebElement element) {
        String color = element.getCssValue("background-color");

        // Check if the color is transparent (rgba(0, 0, 0, 0) or "transparent")
        if (color.equals("rgba(0, 0, 0, 0)") || color.equals("transparent")) {
            WebElement parent = element.findElement(By.xpath(".."));
            // If we reached the top of the document, stop
            if (parent.getTagName().equals("html")) {
                return color;
            }
            return getEffectiveBackgroundColor(parent);
        }
        return color;
    }
}
