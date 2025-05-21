package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no ticks are clicked
        assertEquals("", driver.findElement(By.name("name")).getAttribute("value"));
        assertEquals("", driver.findElement(By.name("age")).getAttribute("value"));

        assertFalse(driver.findElement(By.cssSelector("input[name='language'][value='English']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[name='language'][value='French']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[name='language'][value='Spanish']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[name='language'][value='Chinese']")).isSelected());
//        "Don't know" is selected in "Genre"
        WebElement dontKnowRadio = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[3]"));
        assertTrue(dontKnowRadio.isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[value='male']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[value='female']")).isSelected());
//         "Choose your option" in "How do you like us?"
        WebElement selectedOption = driver.findElement(By.cssSelector("select[id='like_us'] > option[selected]"));
        assertEquals("Choose your option", selectedOption.getText());
//         check that the button send is blue with white letters
        WebElement sendButton = driver.findElement(By.cssSelector("button[type='submit']"));
        String backgroundColor = sendButton.getCssValue("background-color");
        String textColor = sendButton.getCssValue("color");
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)

        WebElement sendButton = driver.findElement(By.cssSelector("button[type='submit']"));
        sendButton.click();

        WebElement confirmBlock = driver.findElement(By.id("fb_thx"));
        assertTrue(confirmBlock.isDisplayed());

        String nameText = driver.findElement(By.xpath("//*[contains(text(), 'Your name:')]")).getText();
        String ageText = driver.findElement(By.xpath("//*[contains(text(), 'Your age:')]")).getText();
        String languageText = driver.findElement(By.xpath("//*[contains(text(), 'Your language:')]")).getText();
        String genreText = driver.findElement(By.xpath("//*[contains(text(), 'Your genre:')]")).getText();
        String optionText = driver.findElement(By.xpath("//*[contains(text(), 'Your option of us:')]")).getText();
        String commentText = driver.findElement(By.xpath("//*[contains(text(), 'Your comment:')]")).getText();


        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        WebElement noButton = driver.findElement(By.xpath("//button[text()='No']"));


        String yesBgColor = yesButton.getCssValue("background-color");
        String yesTextColor = yesButton.getCssValue("color");

        String noBgColor = noButton.getCssValue("background-color");
        String noTextColor = noButton.getCssValue("color");

        assertEquals("rgba(76, 175, 80, 1)", yesBgColor);
        assertEquals("rgba(255, 255, 255, 1)", yesTextColor);

        assertEquals("rgba(244, 67, 54, 1)", noBgColor);
        assertEquals("rgba(255, 255, 255, 1)", noTextColor);


    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)


        driver.findElement(By.id("fb_name")).sendKeys("Krisjanis");
        driver.findElement(By.id("fb_age")).sendKeys("40");
        driver.findElement(By.cssSelector("input[name='language'][value='English']")).click();
        driver.findElement(By.cssSelector("input[name='language'][value='Spanish']")).click();
        driver.findElement(By.cssSelector("input[name='gender'][value='female']")).click();
        WebElement selectElement = driver.findElement(By.xpath("//*[@id=\"like_us\"]"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("Good");
        driver.findElement(By.name("comment")).sendKeys("Awesome site!");

        WebElement nameField = driver.findElement(By.id("fb_name"));
        assertEquals("Krisjanis", nameField.getAttribute("value"));

        WebElement ageField = driver.findElement(By.id("fb_age"));
        assertEquals("40", ageField.getAttribute("value"));

        WebElement englishCheckbox = driver.findElement(By.cssSelector("input[name='language'][value='English']"));
        WebElement spanishCheckbox = driver.findElement(By.cssSelector("input[name='language'][value='Spanish']"));
        assertTrue(englishCheckbox.isSelected());
        assertTrue(spanishCheckbox.isSelected());

        WebElement femaleRadio = driver.findElement(By.cssSelector("input[name='gender'][value='female']"));
        assertTrue(femaleRadio.isSelected());

        WebElement selectedOption = new Select(driver.findElement(By.id("like_us"))).getFirstSelectedOption();
        assertEquals("Good", selectedOption.getText());

        WebElement commentField = driver.findElement(By.name("comment"));
        assertEquals("Awesome site!", commentField.getAttribute("value"));

        WebElement sendButton = driver.findElement(By.cssSelector("button[type='submit']"));
        sendButton.click();

        Thread.sleep(1000);

        WebElement yesBtn = driver.findElement(By.xpath("//button[text()='Yes']"));
        WebElement noBtn = driver.findElement(By.xpath("//button[text()='No']"));

        String yesBackColor = yesBtn.getCssValue("background-color");
        String yesTextColor = yesBtn.getCssValue("color");

        String noBackColor = noBtn.getCssValue("background-color");
        String noTextColor = noBtn.getCssValue("color");


        assertEquals("rgba(76, 175, 80, 1)", yesBackColor);
        assertEquals("rgba(255, 255, 255, 1)", yesTextColor);
        assertEquals("rgba(244, 67, 54, 1)", noBackColor);
        assertEquals("rgba(255, 255, 255, 1)", noTextColor);
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background


        String testName = "Krisjanis";
        driver.findElement(By.id("fb_name")).sendKeys(testName);
        WebElement sendButton = driver.findElement(By.cssSelector("button[type='submit']"));
        sendButton.click();

        Thread.sleep(1000);

        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        yesButton.click();
        Thread.sleep(1000);
        WebElement message = driver.findElement(By.id("message"));

        String expected_string = "Thank you, " + testName + ", for your feedback!";
        assertEquals(expected_string, message.getText());

        WebElement messageContainer = driver.findElement(By.cssSelector(".w3-panel.w3-green"));
        WebElement messageText = driver.findElement(By.id("message"));

        String classAttr = messageContainer.getAttribute("class");
        assertTrue(classAttr.contains("w3-panel"));
        assertTrue(classAttr.contains("w3-green"));

        String bgColor = messageContainer.getCssValue("background-color");
        String textColor = messageText.getCssValue("color");

        assertEquals("rgba(76, 175, 80, 1)", bgColor);
        assertEquals("rgba(255, 255, 255, 1)", textColor);
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background

        WebElement sendButton = driver.findElement(By.cssSelector("button[type='submit']"));
        sendButton.click();
        Thread.sleep(1000);

        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        yesButton.click();
        Thread.sleep(1000);

        WebElement message = driver.findElement(By.id("message"));
        String actualText = message.getText().trim();
        assertEquals("Thank you for your feedback!", actualText);


        WebElement parentPanel = driver.findElement(By.className("w3-panel"));
        String bgColor = parentPanel.getCssValue("background-color");
        String textColor = message.getCssValue("color");

        System.out.println("Background color: " + bgColor);
        System.out.println("Text color: " + textColor);

        assertEquals("rgba(76, 175, 80, 1)", bgColor);
        assertEquals("rgba(255, 255, 255, 1)", textColor);

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly

        driver.findElement(By.id("fb_name")).sendKeys("Andrejs");
        driver.findElement(By.id("fb_age")).sendKeys("23");

        driver.findElement(By.cssSelector("input[name='language'][value='English']")).click();
        driver.findElement(By.cssSelector("input[name='language'][value='French']")).click();
        driver.findElement(By.cssSelector("input[name='language'][value='Spanish']")).click();
        driver.findElement(By.cssSelector("input[name='language'][value='Chinese']")).click();

        driver.findElement(By.cssSelector("input[name='gender'][value='female']")).click();
        WebElement selectElement = driver.findElement(By.cssSelector("select[id='like_us']"));
        new Select(selectElement).selectByVisibleText("Ok, i guess");
        driver.findElement(By.name("comment")).sendKeys("Greetings!");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[text()='No']")).click();
        WebElement nameField = driver.findElement(By.id("fb_name"));
        System.out.println("Name field text: " + nameField.getAttribute("value"));
        assertEquals("Andrejs", nameField.getAttribute("value"));

        WebElement ageField = driver.findElement(By.id("fb_age"));
        assertEquals("23", ageField.getAttribute("value"));

        WebElement englishCheckbox = driver.findElement(By.cssSelector("input[name='language'][value='English']"));
        WebElement spanishCheckbox = driver.findElement(By.cssSelector("input[name='language'][value='Spanish']"));
        WebElement frenchCheckbox = driver.findElement(By.cssSelector("input[name='language'][value='French']"));
        WebElement chineseCheckbox = driver.findElement(By.cssSelector("input[name='language'][value='Chinese']"));
        assertTrue(englishCheckbox.isSelected());
        assertTrue(spanishCheckbox.isSelected());
        assertTrue(frenchCheckbox.isSelected());
        assertTrue(chineseCheckbox.isSelected());

        WebElement femaleRadio = driver.findElement(By.cssSelector("input[name='gender'][value='female']"));
        assertTrue(femaleRadio.isSelected());
        WebElement dropdown = driver.findElement(By.id("like_us"));
        Select select = new Select(dropdown);
        assertEquals("Ok, i guess", select.getFirstSelectedOption().getText());
        WebElement commentField = driver.findElement(By.name("comment"));
        assertEquals("Greetings!", commentField.getAttribute("value"));

    }
}
