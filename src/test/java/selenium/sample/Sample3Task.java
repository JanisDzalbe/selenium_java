package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sample3Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
//         TODO:
            List < WebElement> elemList = driver.findElement(By.className("test"));
            assertEquals(5, elemList.size());
            assertEquals("This is also a button", driver.findElements(By.id("buttonId")).getFirst().getDomAttribute("value"));
//          check how many element with class "test" there are on page (5)
//          check that value of second button is "This is also a button"
    }

    @Test
    public void assertTrueTask() throws Exception {
        assertTrue(driver.findElement(By.id("button_id")).getAttribute("value").equalsIgnoreCase("this is also a button"));
//         TODO:
//          check that it is True that value of second button is
//          "this is Also a Button" if you ignore Caps Locks
//          fail with custom error message:
    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//          check that it is False that value of second button is "This is a button"
    }

    @Test
    public void failTask() throws Exception {
        List < WebElement> elemList = driver.findElement(By.className("test"));
        for (WebElement : elemList){
            if (elem.getText().contains(190))
        }
//        TODO:
//         check that none of items with class "test"
//         contain number 190
    }
}
