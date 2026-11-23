package selenium.sample.extra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;
import static org.junit.jupiter.api.Assertions.*;

public class extra1Task {
    WebDriver driver;
    String base_url = "https://janisdzalbe.github.io/example-site/examples/act";
    String new_url = "https://janisdzalbe.github.io/example-site/examples/link1";

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void navigateBack() throws Exception {
//        TODO
//         open page with url "https://janisdzalbe.github.io/example-site/examples/po"
            driver.get("https://janisdzalbe.github.io/example-site/examples/po");
//         click "More > " for the top left element
            driver.findElement(By.cssSelector("a[href=\"po1\"]")).click();
//         check that the url now "https://janisdzalbe.github.io/example-site/examples/po1"
            assertEquals("https://janisdzalbe.github.io/example-site/examples/po1", driver.getCurrentUrl());
//         using driver navigation go back to "https://janisdzalbe.github.io/example-site/examples/po"
            driver.navigate().back();
//         check that the page now is "https://janisdzalbe.github.io/example-site/examples/po"
            assertEquals("https://janisdzalbe.github.io/example-site/examples/po", driver.getCurrentUrl());
    }

    @Test
    public void navigateForward() throws Exception {
//        TODO
//         open page with url "https://janisdzalbe.github.io/example-site/examples/po"
        driver.get("https://janisdzalbe.github.io/example-site/examples/po");
//         click "More > " for the top left element
        driver.findElement(By.cssSelector("a[href=\"po1\"]")).click();
//         using driver navigation go back to "https://janisdzalbe.github.io/example-site/examples/po"
        driver.navigate().back();
//         using driver navigation go forward to "https://janisdzalbe.github.io/example-site/examples/po1"
        driver.navigate().forward();
//         check that the page now is "https://janisdzalbe.github.io/example-site/examples/po1"
        assertEquals("https://janisdzalbe.github.io/example-site/examples/po1", driver.getCurrentUrl());
    }

    @Test
    public void refresh() throws Exception {
//        TODO
//         open page "https://janisdzalbe.github.io/example-site/examples/act"
        driver.get(base_url);
//         click on "Show" button in 'Button' section
        driver.findElement(By.cssSelector("[onclick='showTextByClickingButton(true)']")).click();
//         check that text "I am here!" is seen
        WebElement message = driver.findElement(By.id("show_me"));
        assertTrue(message.isDisplayed());
//         refresh page
        driver.navigate().refresh();

        assertFalse(driver.findElement(By.id("show_me")).isDisplayed());
//         check that text "I am here!" is not seen

    }
}
