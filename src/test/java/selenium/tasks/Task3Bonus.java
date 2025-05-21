package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import selenium.pages.FormPage;
import selenium.pages.ListPage;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Bonus {
    WebDriver driver;
	ListPage listPage = PageFactory.initElements(driver, ListPage.class);
//     should contain what you see when you just open the page (the table with names/jobs)
	FormPage formPage = PageFactory.initElements(driver, FormPage.class);
//     should be what you see if you click "Add" or "Edit" (2 input field and a button (Add/Edit) and (Cancel)

//    Bonus:
//    try storing people via an Object/separate class
    // I feel like doing it as a map here is just more efficient than creating a seperate class for it which would probably work with a map under the hood anyways

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeChromeDriver();
        listPage = PageFactory.initElements(driver, ListPage.class);
        formPage = PageFactory.initElements(driver, FormPage.class);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void addPerson() {
        /* TODO:
         * implement adding new person using page object
         *
         * in order: store the list of people and jobs currently on page
         * add a person via "Add person button"
         * check the list again, that non of the people where changes, but an additional one with correct name/job was added
         */
        String name = "TestName";
        String job = "TestJob";

        Map<String, String> peopleAndJobs = listPage.getPeople();
        listPage.clickAddPersonTopButton();

        formPage.enterName(name);
        formPage.enterJob(job);
        formPage.clickAdd();

        peopleAndJobs.put(name, job);
        listPage.assertPeopleInList(peopleAndJobs);

    }

    @Test
    public void editPerson() {
        /* TODO:
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link
         * check the list again and that 2 people stayed the same and the one used was changed
         */

        String name = "TestName";
        String job = "TestJob";
        int index = 0;

        Map<String, String> peopleAndJobs = listPage.getPeople();
        String nameBefore = listPage.getPersonName(index);
        listPage.clickEditPerson(index);
        peopleAndJobs.remove(nameBefore);

        formPage.enterName(name);
        formPage.enterJob(job);
        formPage.clickEdit();

        peopleAndJobs.put(name, job);
        listPage.assertPeopleInList(peopleAndJobs);
    }

    @Test
    public void editPersonCancel() {
        /* TODO:
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link then click "Cancel" on edit form instead of "Edit"
         * check the list again and that no changes where made
         */
        String name = "TestName";
        String job = "TestJob";
        int index = 0;

        Map<String, String> peopleAndJobs = listPage.getPeople();
        String nameBefore = listPage.getPersonName(index);
        listPage.clickEditPerson(index);

        formPage.enterName(name);
        formPage.enterJob(job);
        formPage.clickCancel();

        listPage.assertPeopleInList(peopleAndJobs);
    }


    @Test
    public void deletePerson() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete 1 person see that there are now 2 people in the table with correct data
         */

        int index = 0;
        Map<String, String> peopleAndJobs = listPage.getPeople();
        String nameBefore = listPage.getPersonName(index);
        peopleAndJobs.remove(nameBefore);
        listPage.clickDeletePerson(index);

        listPage.assertPeopleInList(peopleAndJobs);
    }


    @Test
    public void deletePersonAll() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete all people and check that there is no no table on page, but the button Add is still present and working
         */
        String name = "TestName";
        String job = "TestJob";
        Map<String, String> peopleAndJobs = listPage.getPeople();
        while(!peopleAndJobs.isEmpty()) {
            String nameBefore = listPage.getPersonName(0);
            peopleAndJobs.remove(nameBefore);
            listPage.clickDeletePerson(0);
        }
        listPage.assertPeopleInList(peopleAndJobs);


        listPage.clickAddPersonTopButton();

        formPage.enterName(name);
        formPage.enterJob(job);
        formPage.clickAdd();

        peopleAndJobs.put(name, job);
        listPage.assertPeopleInList(peopleAndJobs);
    }
}
