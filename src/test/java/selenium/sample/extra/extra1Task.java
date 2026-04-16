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
        driver.get(po_url);
        driver.findElement(By.linkText("More >>")).click();

        assertEquals(po1_url, driver.getCurrentUrl());

        driver.navigate().back();

        assertEquals(po_url, driver.getCurrentUrl());
    }

    @Test
    public void navigateForward() throws Exception {
        // open page with url po
        driver.get(po_url);

        driver.findElement(By.linkText("More >>")).click();
        driver.navigate().back();
        assertEquals(po_url, driver.getCurrentUrl());

        driver.navigate().forward();

        assertEquals(po1_url, driver.getCurrentUrl());
    }

    @Test
    public void refresh() throws Exception {
        driver.get(base_url);

        WebElement showButton = driver.findElement(By.id("show_text"));

        if (!showButton.isEnabled()) {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].removeAttribute('disabled')", showButton);
        }
        showButton.click();
        WebElement message = driver.findElement(By.id("show_me"));
        assertTrue(message.isDisplayed(), "Message should be visible");
        assertEquals("I am here!", message.getText());

        driver.navigate().refresh();
        assertFalse(driver.findElement(By.id("show_me")).isDisplayed(), "Message should be hidden after refresh");
    }
}