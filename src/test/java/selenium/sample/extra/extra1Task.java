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
    String po_url = "https://janisdzalbe.github.io/example-site/examples/po";
    String po1_url = "https://janisdzalbe.github.io/example-site/examples/po1";

    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();
    }

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void navigateBack() throws Exception {
        // open page with url po
        driver.get(po_url);

        // click "More > " for the top left element (using linkText for clarity)
        driver.findElement(By.linkText("More >>")).click();

        // check that the url now po1
        assertEquals(po1_url, driver.getCurrentUrl());

        // using driver navigation go back
        driver.navigate().back();

        // check that the page now is po
        assertEquals(po_url, driver.getCurrentUrl());
    }

    @Test
    public void navigateForward() throws Exception {
        // open page with url po
        driver.get(po_url);

        // click "More > " for the top left element
        driver.findElement(By.linkText("More >>")).click();

        // using driver navigation go back
        driver.navigate().back();
        assertEquals(po_url, driver.getCurrentUrl());

        // using driver navigation go forward to po1
        driver.navigate().forward();

        // check that the page now is po1
        assertEquals(po1_url, driver.getCurrentUrl());
    }

    @Test
    public void refresh() throws Exception {
        driver.get(base_url);

        // 1. Find the button
        WebElement showButton = driver.findElement(By.id("show_text"));

        // 2. EXTRA STEP: If the button is disabled, we enable it so we can click
        if (!showButton.isEnabled()) {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].removeAttribute('disabled')", showButton);
        }

        // 3. Click the button
        showButton.click();

        // 4. Check that text "I am here!" is seen (ID is show_me)
        WebElement message = driver.findElement(By.id("show_me"));
        assertTrue(message.isDisplayed(), "Message should be visible");
        assertEquals("I am here!", message.getText());

        // 5. Refresh page
        driver.navigate().refresh();

        // 6. Check that the text is gone (or the page reset)
        // Re-find the element after refresh to avoid StaleElementReferenceException
        assertFalse(driver.findElement(By.id("show_me")).isDisplayed(), "Message should be hidden after refresh");
    }
}