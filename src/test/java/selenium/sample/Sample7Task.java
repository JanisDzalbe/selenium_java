package selenium.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import selenium.utility.BootcampUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;



public class Sample7Task {
    WebDriver driver;
    String base_url = "https://janisdzalbe.github.io/example-site/examples/actions";

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
    public void selectCheckBox() throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");

        WebElement option1 = driver.findElement(By.id("vfb-6-0"));
        WebElement option2 = driver.findElement(By.id("vfb-6-1"));
        WebElement option3 = driver.findElement(By.id("vfb-6-2"));

        Assertions.assertFalse(option1.isSelected());
        Assertions.assertFalse(option2.isSelected());
        Assertions.assertFalse(option3.isSelected());

        option2.click();

        Assertions.assertFalse(option1.isSelected());
        Assertions.assertTrue(option2.isSelected());
        Assertions.assertFalse(option3.isSelected());

        option3.click();

        driver.findElement(By.id("result_button_checkbox")).click();

        WebElement result = driver.findElement(By.id("result_checkbox"));
        Assertions.assertTrue(result.isDisplayed());
        Assertions.assertEquals("You selected value(s): Option 2, Option 3", result.getText());

        driver.quit();
    }



    @Test
    public void selectRadioButton() throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");

        WebElement option1 = driver.findElement(By.id("vfb-7-1"));
        WebElement option2 = driver.findElement(By.id("vfb-7-2"));
        WebElement option3 = driver.findElement(By.id("vfb-7-3"));

        Assertions.assertFalse(option1.isSelected());
        Assertions.assertFalse(option2.isSelected());
        Assertions.assertFalse(option3.isSelected());

        option3.click();

        Assertions.assertFalse(option1.isSelected());
        Assertions.assertFalse(option2.isSelected());
        Assertions.assertTrue(option3.isSelected());

        option1.click();

        Assertions.assertTrue(option1.isSelected());
        Assertions.assertFalse(option2.isSelected());
        Assertions.assertFalse(option3.isSelected());

        driver.findElement(By.id("result_button_ratio")).click();

        WebElement result = driver.findElement(By.id("result_radio"));
        Assertions.assertTrue(result.isDisplayed());
        Assertions.assertEquals("You selected option: Option 1", result.getText());

        driver.quit();
    }


    @Test
    public void selectOption() throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/examples/actions");

        Select select = new Select(driver.findElement(By.id("vfb-12")));

        select.selectByVisibleText("Option 3");
        Assertions.assertEquals("Option 3", select.getFirstSelectedOption().getText());

        select.selectByVisibleText("Option 2");
        Assertions.assertEquals("Option 2", select.getFirstSelectedOption().getText());

        driver.findElement(By.id("result_button_select")).click();

        WebElement result = driver.findElement(By.id("result_select"));
        Assertions.assertTrue(result.isDisplayed());
        Assertions.assertEquals("You selected option: Option 2", result.getText());

        driver.quit();
    }


// ** Bonus tasks **
@Test
public void chooseDateViaCalendarBonus() {

    driver.findElement(By.id("vfb-8")).click();

    String targetMonth = "July";
    String targetYear = "2007";
    String targetDay = "4";

    while (true) {
        WebElement title = driver.findElement(By.cssSelector(".ui-datepicker-title"));
        String[] parts = title.getText().split(" ");
        String month = parts[0].trim();
        String year = parts[1].trim();

        if (month.equals(targetMonth) && year.equals(targetYear)) {
            break;
        }

        driver.findElement(By.cssSelector(".ui-datepicker-prev")).click();
    }

    driver.findElement(By.xpath("//a[text()='" + targetDay + "']")).click();

    String value = driver.findElement(By.id("vfb-8")).getAttribute("value");
    Assertions.assertEquals("07/04/2007", value);
}



    @Test
    public void chooseDateViaTextBoxBonus() {

        WebElement dateInput = driver.findElement(By.id("vfb-8"));

        dateInput.clear();
        dateInput.sendKeys("05/02/1959");

        String value = dateInput.getAttribute("value");
        Assertions.assertEquals("05/02/1959", value);
    }

}
