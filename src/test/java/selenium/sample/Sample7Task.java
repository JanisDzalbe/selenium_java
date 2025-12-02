package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
//         TODO:
//          check that none of the checkboxes are ticked
//          tick  "Option 2"
//          check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//          tick  "Option 3"
//          click result
//          check that text 'You selected value(s): Option 2, Option 3' is being displayed
    List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

    for (WebElement checkBox : checkBoxes) {
      assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
    }
    WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
    option2.click();
    assertTrue(option2.isSelected());
    WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
    assertFalse(option3.isSelected());
    WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
    assertFalse(option1.isSelected());
    option3.click();
    driver.findElement(By.id("result_button_checkbox")).click();
    WebElement resultcheckbox = driver.findElement(By.id("result_checkbox"));
    assertEquals("You selected value(s): Option 2, Option 3", resultcheckbox.getText());

  }


  @Test
  public void selectRadioButton() throws Exception {
//         TODO:
//          check that none of the radio are selected
//          select  "Option 3"
//          check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
//          select  "Option 1"
//          check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
//          click result
//          check that 'You selected option: Option 1' text is being displayed

    List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));

    for (WebElement radioButton : radioButtons) {
      assertFalse(radioButton.isSelected()); // radio are NOT selected
    }
    WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio']"));
    WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio']"));
    WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio']"));
    option3.click();
    assertTrue(option3.isSelected());
    assertFalse(option1.isSelected());
    assertFalse(option2.isSelected());
    option1.click();
    assertTrue(option1.isSelected());
    assertFalse(option3.isSelected());
    assertFalse(option2.isSelected());

    driver.findElement(By.id("result_button_ratio")).click();
    WebElement result_radio = driver.findElement(By.id("result_radio"));
    assertEquals("You selected option: Option 1", result_radio.getText());
  }

  @Test
  public void selectOption() throws Exception {
//         TODO:
//          select "Option 3" in Select
//          check that selected option is "Option 3"
//          select "Option 2" in Select
//          check that selected option is "Option 2"
//          click result
//          check that 'You selected option: Option 2' text is being displayed

    Select dropdown = new Select(driver.findElement(By.className("w3-select")));

    dropdown.selectByVisibleText("Option 3");
    assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
    dropdown.selectByVisibleText("Option 2");
    assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
    driver.findElement(By.id("result_button_select")).click();
    WebElement result_select = driver.findElement(By.id("result_select"));
    assertEquals("You selected option: Option 2", result_select.getText());

  }

  // ** Bonus tasks **
  @Test
  public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//          enter date '4 of July 2007' using calendar widget
//          check that correct date is added

    // get today date
    Calendar cal = Calendar.getInstance();
    cal.set(2007, Calendar.JULY, 4);
    String result = new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
    System.out.println(result);
    WebElement dateBox = driver.findElement(By.id("vfb-8"));
    assertEquals("", dateBox.getDomProperty("value"));

    dateBox.click();


    WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
    while (!dateWidget.findElement(By.className("ui-datepicker-year")).getText().equals("2007")) {
      dateWidget.findElement(By.className("ui-datepicker-prev")).click();
    }
    while (!dateWidget.findElement(By.className("ui-datepicker-month")).getText().equals("July")) {
      dateWidget.findElement(By.className("ui-datepicker-prev")).click();
      // Just in case it ever goes to 2006 it should never run
      if (dateWidget.findElement(By.className("ui-datepicker-year")).getText().equals("2006")){
        System.out.println("How did it run to 2006??????????????????");
          break;
      }
    }
    // select date 15
    dateWidget.findElement(By.xpath("//a[text()='4']")).click();

    assertEquals(result, dateBox.getDomProperty("value"));
    dateBox.clear();


  }

  @Test
  public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//          enter date '2 of May 1959' using text
//          check that correct date is added

    // get today date
    Calendar cal = Calendar.getInstance();
    cal.set(1959, Calendar.MAY, 2);
    String result = new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());


    WebElement dateBox = driver.findElement(By.id("vfb-8"));
    assertEquals("", dateBox.getDomProperty("value"));

    dateBox.clear();
    dateBox.sendKeys(result);
    assertEquals(result, dateBox.getDomProperty("value"));


  }
}
