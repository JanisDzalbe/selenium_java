package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.io.File;

public class Task2 {
    WebDriver driver;

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

        WebElement buttonAdd = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(@class,'w3-btn') and contains(text(),'Add person')]"));
        assertTrue(buttonAdd.isDisplayed());
        assertTrue(buttonAdd.isEnabled());

        WebElement buttonResetList = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(@class,'w3-btn') and contains(text(),'Reset List')]"));
        assertTrue(buttonResetList.isDisplayed());
        assertTrue(buttonResetList.isEnabled());

        String url = driver.getCurrentUrl();





    //      List<WebElement> people = driver.findElements(By.xpath("//*[@id='listOfPeople']"));

    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        //    System.out.println(driver.getCurrentUrl());
        WebElement buttonAdd = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(@class,'w3-btn') and contains(text(),'Add person')]"));
        buttonAdd.click();
        String url = driver.getCurrentUrl();

        //      System.out.println(driver.getCurrentUrl());
        assertEquals(url,"https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html");

        driver.findElement(By.xpath("//*[@id=\"addPersonBtn\"]")).click();

        WebElement fieldName =  driver.findElement(By.xpath("//*[@id='name']"));
        WebElement fieldJob = driver.findElement(By.xpath("//*[@id='job']"));

        assertEquals("", fieldName.getText());
        assertEquals("", fieldJob.getText());

        String nameKey = "Boris";
        String jobKey = "Team lead";

        fieldName.sendKeys(nameKey);
        fieldJob.sendKeys(jobKey);

        assertEquals("Boris", fieldName.getAttribute("value"));
        assertEquals("Team lead",  fieldJob.getAttribute("value"));

        driver.findElement(By.xpath("//*[@id='modal_button' and contains(text(),'Add')]")).click();

        url = driver.getCurrentUrl();

        assertEquals(url,"https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");

















    }


    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
    }
}
