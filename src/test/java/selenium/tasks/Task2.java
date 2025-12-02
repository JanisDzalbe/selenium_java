package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
  WebDriver driver;
  private static WebDriverWait wait;

  @BeforeEach
  public void openPage() {
    String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
    System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
    driver = new ChromeDriver();
    driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
  }

  @AfterEach
  public void closeBrowser() {
    driver.quit();
  }

  @Test
  public void initialPeopleList() throws Exception {
//         TODO:
//          check that "Add person" and "Reset List" buttons are displayed and enabled
//          check list of people contains 10 entries with correct names and jobs
//        Mike, Web Designer
//        Jill, Support
//        Jane, Accountant
//        John, Software Engineer
//        Sarah, Product Manager
//        Carlos, Data Analyst
//        Emily, UX Designer
//        David, Project Manager
//        Maria, QA Engineer
//        Alex, DevOps Engineer
    WebElement AddPerson = driver.findElement(By.xpath("//button[text()=\"Add person\"]"));
    WebElement ResetList = driver.findElement(By.xpath("//button[text()=\"Reset List\"]"));
    assertAll(() -> assertTrue(AddPerson.isDisplayed()), () -> assertTrue(AddPerson.isEnabled()), () -> assertTrue(ResetList.isDisplayed()), () -> assertTrue(ResetList.isEnabled()));
    Map<String, String> Emplloyeemap = new HashMap<String, String>();
    Emplloyeemap.put("Mike", "Web Designer");
    Emplloyeemap.put("Jill", "Support");
    Emplloyeemap.put("Jane", "Accountant");
    Emplloyeemap.put("John", "Software Engineer");
    Emplloyeemap.put("Sarah", "Product Manager");
    Emplloyeemap.put("Emily", "UX Designer");
    Emplloyeemap.put("David", "Project Manager");
    Emplloyeemap.put("Maria", "QA Engineer");
    Emplloyeemap.put("Alex", "DevOps Engineer");
    Emplloyeemap.put("Carlos", "Data Analyst");

    int count = 0;
    List<WebElement> allElements = driver.findElements(By.xpath("//*[@id=\"listOfPeople\"]/div"));
    for (WebElement element : allElements) {
      count++;
    }
    assertEquals(10, count);

    for (WebElement element : allElements) {
      System.out.println(element.findElement(By.className("name")).getText() + " " + element.findElement(By.className("job")).getText());

      assertEquals(Emplloyeemap.get(element.findElement(By.className("name")).getText()), element.findElement(By.className("job")).getText());
    }

  }

  @Test
  public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job

    String PersonName = "Jane";
    String PersonJob = "JOOOB";

    wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
    WebElement AddPerson = driver.findElement(By.xpath("//button[text()=\"Add person\"]"));
    AddPerson.click();

    //add person
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()=\"Clear all fields\"]")));
    driver.findElement(By.id("name")).sendKeys(PersonName);
    driver.findElement(By.id("job")).sendKeys(PersonJob);
    driver.findElement(By.xpath("//button[text()=\"Add\"]")).click();
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()=\"Add person\"]")));

    // Find person in list
    List<WebElement> allElements = driver.findElements(By.xpath("//*[@id=\"listOfPeople\"]/div"));
    WebElement Person = null;
    for (WebElement element : allElements) {
      String inputVal = element.findElement(By.className("name")).getText();
      String Jobval = element.findElement(By.className("job")).getText();
      if (inputVal.equals(PersonName) && Jobval.equals(PersonJob)) {
        Person = element;
        break;
      }
    }
    assertEquals(PersonName, Person.findElement(By.className("name")).getText());
    assertEquals(PersonJob, Person.findElement(By.className("job")).getText());


  }

  @Test
  public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job

    //Change values here
    String PersonName = "Jane";
    String PersonJob = "JOOOB";

    wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);

    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"listOfPeople\"]/div")));
    List<WebElement> Personlist = driver.findElements(By.xpath("//*[@id=\"listOfPeople\"]/div"));

    WebElement Person = null;
    for (WebElement element : Personlist) {
      String inputVal = element.findElement(By.className("name")).getText();
      String Jobval = element.findElement(By.className("job")).getText();
      if (inputVal.equals(PersonName)) {
        Person = element;
        System.out.println("Original job: " + Jobval);
        break;
      }
    }
    WebElement Pencil = Person.findElement(By.xpath(".//i[@class='fa fa-pencil']"));
    Pencil.click();


    //edit person
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()=\"Edit\"]")));
    driver.findElement(By.id("job")).clear();
    driver.findElement(By.id("job")).sendKeys(PersonJob);
    driver.findElement(By.xpath("//button[text()=\"Edit\"]")).click();

    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()=\"Add person\"]")));

    // Find person in list
    List<WebElement> allElements = driver.findElements(By.xpath("//*[@id=\"listOfPeople\"]/div"));
    Person = null;
    for (WebElement element : allElements) {
      String inputVal = element.findElement(By.className("name")).getText();
      String Jobval = element.findElement(By.className("job")).getText();
      if (inputVal.equals(PersonName) && Jobval.equals(PersonJob)) {
        Person = element;
        break;
      }
    }
    System.out.println("Job now is : " + Person.findElement(By.className("job")).getText());
    assertEquals(PersonName, Person.findElement(By.className("name")).getText());
    assertEquals(PersonJob, Person.findElement(By.className("job")).getText());


  }

  @Test
  public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list


    //Change values here
    String PersonName = "Jane";


    wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);

    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"listOfPeople\"]/div")));
    List<WebElement> Personlist = driver.findElements(By.xpath("//*[@id=\"listOfPeople\"]/div"));

    WebElement Person = null;
    for (WebElement element : Personlist) {
      String inputVal = element.findElement(By.className("name")).getText();
      String Jobval = element.findElement(By.className("job")).getText();
      if (inputVal.equals(PersonName)) {
        Person = element;
        System.out.println("Person: " + PersonName + " Original job: " + Jobval);
        break;
      }
    }
    //Remove button
    WebElement removebtn = Person.findElement(By.xpath(".//*[contains(@class,'closebtn')]"));
    removebtn.click();

    // Find person in list
    List<WebElement> allElements = driver.findElements(By.xpath("//*[@id=\"listOfPeople\"]/div"));
    Person = null;
    for (WebElement element : allElements) {
      String inputVal = element.findElement(By.className("name")).getText();
      if (inputVal.equals(PersonName)) {
        Person = element;
        break;
      }
    }
    assertNull(Person);


  }

  @Test
  public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
    // Set up
    Map<String, String> Emplloyeemap = new HashMap<String, String>();
    Emplloyeemap.put("Mike", "Web Designer");
    Emplloyeemap.put("Jill", "Support");
    Emplloyeemap.put("Jane", "Accountant");
    Emplloyeemap.put("John", "Software Engineer");
    Emplloyeemap.put("Sarah", "Product Manager");
    Emplloyeemap.put("Emily", "UX Designer");
    Emplloyeemap.put("David", "Project Manager");
    Emplloyeemap.put("Maria", "QA Engineer");
    Emplloyeemap.put("Alex", "DevOps Engineer");
    Emplloyeemap.put("Carlos", "Data Analyst");

    //  do modification
    String PersonName = "Janey";
    String PersonJob = "JOOOB";

    wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
    WebElement AddPerson = driver.findElement(By.xpath("//button[text()=\"Add person\"]"));
    AddPerson.click();

    //add person
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()=\"Clear all fields\"]")));
    driver.findElement(By.id("name")).sendKeys(PersonName);
    driver.findElement(By.id("job")).sendKeys(PersonJob);
    driver.findElement(By.xpath("//button[text()=\"Add\"]")).click();
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()=\"Add person\"]")));

    // Find person in list
    List<WebElement> allElements = driver.findElements(By.xpath("//*[@id=\"listOfPeople\"]/div"));
    WebElement Person = null;
    for (WebElement element : allElements) {
      String inputVal = element.findElement(By.className("name")).getText();
      String Jobval = element.findElement(By.className("job")).getText();
      if (inputVal.equals(PersonName) && Jobval.equals(PersonJob)) {
        Person = element;
        System.out.println("Person found in list");
        break;
      }
    }
    //check for person
    assertEquals(PersonName, Person.findElement(By.className("name")).getText());
    assertEquals(PersonJob, Person.findElement(By.className("job")).getText());
    //Click reset list
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()=\"Reset List\"]")));
    WebElement ResetList = driver.findElement(By.xpath("//button[text()=\"Reset List\"]"));
    ResetList.click();
    // Check if back to original
    int count = 0;
    allElements = driver.findElements(By.xpath("//*[@id=\"listOfPeople\"]/div"));
    for (WebElement element : allElements) {
      count++;
      assertEquals(Emplloyeemap.get(element.findElement(By.className("name")).getText()), element.findElement(By.className("job")).getText());
    }
    assertEquals(10, count);
  }
}
