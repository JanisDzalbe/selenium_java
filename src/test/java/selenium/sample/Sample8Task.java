package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample8Task {
  WebDriver driver;

  // method which is being run before each test
  @BeforeEach
  public void startingTests() throws Exception {
    // Initialize driver
    driver = BootcampUtils.initializeChromeDriver();

    //open page:
    driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_random");
  }

  // method which is being run after each test
  @AfterEach
  public void endingTests() throws Exception {
    driver.quit();
  }

  @Test
  public void findPersonByXPath() throws Exception {
//  TODO:
//   Click “Reset List”
//   Using xPath, find “John” and asset that he is “Software Engineer”
    WebElement Resetbtn = driver.findElement(By.id("resetListBtn"));
    Resetbtn.click();
    WebElement Personlist = driver.findElement(By.xpath("//*[@id=\"listOfPeople\"]"));
    WebElement Person = Personlist.findElement(By.xpath("//li[contains(@id,'person')  and  .//*[text()=\"John\"]]"));
    assertEquals("John", Person.findElement(By.className("name")).getText());
    assertEquals("Software Engineer", Person.findElement(By.xpath(".//*[@class='job']")).getText());

  }

  @Test
  public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”

    WebElement shuffleBtn = driver.findElement(By.id("shuffleBtn"));
    shuffleBtn.click();

    WebElement Personlist = driver.findElement(By.xpath("//*[@id=\"listOfPeople\"]"));
    WebElement Person = Personlist.findElement(By.xpath("//li[contains(@id,'person')  and  .//*[text()=\"John\"]]"));

    List<WebElement> allElements = driver.findElements(By.xpath("//*[@id=\"listOfPeople\"]/div"));
    ;
    WebElement Jane = null;
    for (WebElement element : allElements) {
      String inputVal = element.findElement(By.className("name")).getText();
      if (inputVal.equals("Jane")) {
        Jane = element;
        break;
      }
    }
    assertEquals("Jane", Jane.findElement(By.className("name")).getText());
    assertEquals("Accountant", Jane.findElement(By.className("job")).getText());
  }

  @Test
  public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees


    WebElement Resetbtn = driver.findElement(By.id("resetListBtn"));
    Resetbtn.click();
    int count = 0;
    List<WebElement> allElements = driver.findElements(By.xpath("//*[@id=\"listOfPeople\"]/div"));

    for (WebElement element : allElements) {
      if (element.findElement(By.cssSelector("input[value='employee']")).isSelected()) {
        count++;
      }
    }

    assertEquals(5, count);

    WebElement shuffleBtn = driver.findElement(By.id("shuffleBtn"));
    shuffleBtn.click();

    List<WebElement> employeeradio= driver.findElements(By.cssSelector("input[value='employee']"));
    count = 0;
    for (WebElement element : employeeradio) {
      if (element.isSelected()) {
        count++;
      }
    }
    assertEquals(5, count);
  }

}
