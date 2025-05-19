package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Locale;

public class Task1 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    // There is quite a bit of identical or very similar text in my tests, I'm aware that it's probably bad practice
    // and probably should be avoided somehow, but I'm keeping them here currently as I don't want to "overengineer" these tasks :)
    // Also not sure if I should handle errors that IJ shows such as Method invocation 'contains' may produce 'NullPointerException'
    // obviously it would be just clicking alt-enter to fix as it wants us to :)

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen

        //Also not sure if I should move these two to @BeforeEach
        WebElement errorElement = driver.findElement(By.id("ch1_error"));
        WebElement numberInput = driver.findElement(By.id("numb"));

        Assertions.assertFalse(errorElement.isDisplayed());
        Assertions.assertEquals("", errorElement.getText());

        // not sure if I should also check css elements and the colors,but I do lol. if not, those lines could be commented out :)
        Assertions.assertTrue(numberInput.getDomAttribute("class").contains("w3-light-grey"));
        Assertions.assertFalse(numberInput.getDomAttribute("class").contains("w3-pale-red"));

        numberInput.sendKeys("abc");
        driver.findElement(By.className("w3-orange")).click(); // Submit button, not sure if selecting it another way would be better practice

        Assertions.assertTrue(errorElement.isDisplayed());
        Assertions.assertEquals("Please enter a number", errorElement.getText());
        Assertions.assertTrue(numberInput.getDomAttribute("class").contains("w3-pale-red"));
        Assertions.assertFalse(numberInput.getDomAttribute("class").contains("w3-light-grey"));
    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen
        WebElement errorElement = driver.findElement(By.id("ch1_error"));
        WebElement numberInput = driver.findElement(By.id("numb"));
        int number = 8;

        Assertions.assertFalse(errorElement.isDisplayed());
        Assertions.assertEquals("", errorElement.getText());
        Assertions.assertTrue(numberInput.getDomAttribute("class").contains("w3-light-grey"));
        Assertions.assertFalse(numberInput.getDomAttribute("class").contains("w3-pale-red"));

        numberInput.sendKeys(Integer.toString(number));
        driver.findElement(By.className("w3-orange")).click();

        Assertions.assertTrue(errorElement.isDisplayed());
        Assertions.assertEquals("Number is too small", errorElement.getText());
        Assertions.assertTrue(numberInput.getDomAttribute("class").contains("w3-pale-red"));
        Assertions.assertFalse(numberInput.getDomAttribute("class").contains("w3-light-grey"));

    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement errorElement = driver.findElement(By.id("ch1_error"));
        WebElement numberInput = driver.findElement(By.id("numb"));
        int number = 101;

        Assertions.assertFalse(errorElement.isDisplayed());
        Assertions.assertEquals("", errorElement.getText());
        Assertions.assertTrue(numberInput.getDomAttribute("class").contains("w3-light-grey"));
        Assertions.assertFalse(numberInput.getDomAttribute("class").contains("w3-pale-red"));

        numberInput.sendKeys(Integer.toString(number));
        driver.findElement(By.className("w3-orange")).click();

        Assertions.assertTrue(errorElement.isDisplayed());
        Assertions.assertEquals("Number is too big", errorElement.getText());
        Assertions.assertTrue(numberInput.getDomAttribute("class").contains("w3-pale-red"));
        Assertions.assertFalse(numberInput.getDomAttribute("class").contains("w3-light-grey"));

    }

    @Test
    public void correctSquareRoot() {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code
        WebElement errorElement = driver.findElement(By.id("ch1_error"));
        WebElement numberInput = driver.findElement(By.id("numb"));
        int number = 58;
        double correctSquareRoot = Math.sqrt(number);

        Assertions.assertFalse(errorElement.isDisplayed());
        Assertions.assertEquals("", errorElement.getText());
        Assertions.assertTrue(numberInput.getDomAttribute("class").contains("w3-light-grey"));
        Assertions.assertFalse(numberInput.getDomAttribute("class").contains("w3-pale-red"));

        numberInput.sendKeys(Integer.toString(number));
        driver.findElement(By.className("w3-orange")).click();

        // This is the method I found to round to 2 decimal places, so weird though. Does java not have a better method?
        // https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java?__cf_chl_tk=rC2E2NVm9rLu76CXGB_qSLJ4rjppG5se0NKMI9TbhYE-1747656339-1.0.1.1-_gAmnq7TPtLrl.iHiRpZ4QGnHmSLRWVywd8b3nge8sE
        // Also weird that comm a or dot to seperate decimal part depends on locale. - https://stackoverflow.com/questions/23270269/formatting-doubles-to-two-decimal-places-in-java-produces-a-comma-instead-of-a-d
        // Not sure if I should use string.format or decimalformat

        Assertions.assertTrue(driver.switchTo().alert().getText().contains(String.format(Locale.US,"%.2f", correctSquareRoot)));
        driver.switchTo().alert().accept();

        Assertions.assertFalse(errorElement.isDisplayed());
        Assertions.assertEquals("", errorElement.getText());
        Assertions.assertTrue(numberInput.getDomAttribute("class").contains("w3-light-grey"));
        Assertions.assertFalse(numberInput.getDomAttribute("class").contains("w3-pale-red"));
    }
}
