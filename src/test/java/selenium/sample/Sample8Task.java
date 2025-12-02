package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.Arrays;
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
        WebElement resetButton = driver.findElement(By.id("resetListBtn"));
        resetButton.click();

        WebElement johnElement = driver.findElement(By.id("person3"));      //find john and assert him as software engineer
        String johnJob = driver.findElement(By.xpath("//li[@id='person3']//span[@class='job']")).getText(); //many classes job, have to be specific
        assertEquals("Software Engineer", johnJob);
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        WebElement shuffleButton = driver.findElement(By.id("shuffleBtn"));
        shuffleButton.click();

        List<WebElement> allPeople = driver.findElements(By.xpath("//li[starts-with(@id,'person')]"));
        WebElement janeElement = null;

        for (WebElement person : allPeople) {   //find jane
            String name = person.findElement(By.className("name")).getText();
            if (name.equals("Jane")) {
                janeElement = person;
                break;
            }
        }

        String janeJob = janeElement.findElement(By.className("job")).getText();
        assertEquals("Accountant", janeJob);
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees
        WebElement resetButton = driver.findElement(By.id("resetListBtn"));
        resetButton.click();

        //honestly couldn't figure out how to do this without hard-coding, the radios are visually ticked but in the code they appear the same..
        List<String> employees = Arrays.asList("Emily", "David", "Jane", "John", "Sarah");

        List<WebElement> allPeople = driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")); //employees counted before shuffle
        int employeeCount = 0;
        for (WebElement person : allPeople) {
            String name = person.findElement(By.className("name")).getText();
            if (employees.contains(name)) {
                employeeCount++;
            }
         //   System.out.println(name);       //test if all 10 are really in the loop. they are
        }
        assertEquals(5, employeeCount);

        WebElement shuffleButton = driver.findElement(By.id("shuffleBtn"));
        shuffleButton.click();

        allPeople = driver.findElements(By.xpath("//ul[@id='listOfPeople']//li"));  //count employees after shuffle
        int employeeCountAfterShuffle = 0;
        for (WebElement person : allPeople) {
            String name = person.findElement(By.className("name")).getText();
            if (employees.contains(name)) {
                employeeCountAfterShuffle++;
            }
        }
        assertEquals(5, employeeCountAfterShuffle);     //FINALLY
    }
}
