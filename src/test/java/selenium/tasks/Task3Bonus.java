package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import java.io.File;



public class Task3Bonus {
    WebDriver driver;
    PeopleWithJobsPage page;
//	ListPage listPage = PageFactory.initElements(driver, ListPage.class);
//     should contain what you see when you just open the page (the table with names/jobs)
//	FormPage formPage = PageFactory.initElements(driver, FormPage.class);
//     should be what you see if you click "Add" or "Edit" (2 input field and a button (Add/Edit) and (Cancel)

//    Bonus:
//    try storing people via an Object/separate class


    @BeforeEach
    public void openPage() {
            driver = BootcampUtils.initializeDriver();


            driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
            page = new PeopleWithJobsPage(driver);

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
        List<String> namesBefore = page.getPersonNames();
        List<String> jobsBefore  = page.getPersonJobs();
        int countBefore = page.getPeopleCount();

        String newName = "Zoe";
        String newJob  = "Tester";
        page.addPerson(newName, newJob);
        List<String> namesAfter = page.getPersonNames();
        List<String> jobsAfter  = page.getPersonJobs();

        assertEquals(countBefore + 1, namesAfter.size());
        assertEquals(countBefore + 1, jobsAfter.size());
        for (int i = 0; i < countBefore; i++) {
            assertEquals(namesBefore.get(i), namesAfter.get(i),   "Name in string " + i + " changed");
            assertEquals(jobsBefore.get(i),  jobsAfter.get(i),    "Occupation in string " + i + " changed");
        }
        assertEquals(newName, namesAfter.get(namesAfter.size() - 1));
        assertEquals(newJob,  jobsAfter.get(jobsAfter.size() - 1));
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
        List<String> namesBefore = page.getPersonNames();
        List<String> jobsBefore  = page.getPersonJobs();
        assertTrue(namesBefore.size() >= 3, "For test min 3 people");

        int indexToEdit = 1;
        String originalName = namesBefore.get(indexToEdit);
        String originalJob  = jobsBefore.get(indexToEdit);
        page.openEditModalByIndex(indexToEdit);
        assertEquals(originalName, page.getNameFromEditModal());
        assertEquals(originalJob,  page.getJobFromEditModal());
        String newJob = originalJob + " (edited)";
        page.changeJobInEditModal(newJob);
        page.submitEdit();
        List<String> namesAfter = page.getPersonNames();
        List<String> jobsAfter  = page.getPersonJobs();
        assertEquals(namesBefore.size(), namesAfter.size());
        assertEquals(jobsBefore.size(),  jobsAfter.size());
        assertEquals(namesBefore.get(0), namesAfter.get(0));
        assertEquals(jobsBefore.get(0),  jobsAfter.get(0));
        assertEquals(originalName, namesAfter.get(indexToEdit));
        assertEquals(newJob,       jobsAfter.get(indexToEdit));
        int next = indexToEdit + 1;
        assertEquals(namesBefore.get(next), namesAfter.get(next));
        assertEquals(jobsBefore.get(next),  jobsAfter.get(next));
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
        List<String> namesBefore = page.getPersonNames();
        List<String> jobsBefore  = page.getPersonJobs();
        assertFalse(namesBefore.isEmpty());

        int indexToEdit = 0;
        page.openEditModalByIndex(indexToEdit);
        page.changeJobInEditModal("Should not be saved");
        page.cancelEdit();
        List<String> namesAfter = page.getPersonNames();
        List<String> jobsAfter  = page.getPersonJobs();
        assertEquals(namesBefore.size(), namesAfter.size());
        assertEquals(jobsBefore.size(),  jobsAfter.size());

        for (int i = 0; i < namesBefore.size(); i++) {
            assertEquals(namesBefore.get(i), namesAfter.get(i), "Name in string " + i + " changed");
            assertEquals(jobsBefore.get(i),  jobsAfter.get(i),  "Occupation in string " + i + " changed");
        }
    }


    @Test
    public void deletePerson() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete 1 person see that there are now 2 people in the table with correct data
         */
        List<String> namesBefore = page.getPersonNames();
        List<String> jobsBefore  = page.getPersonJobs();
        assertTrue(namesBefore.size() >= 3, "Нужно минимум 3 записи");

        int indexToDelete = 1;
        String deletedName = namesBefore.get(indexToDelete);
        String deletedJob  = jobsBefore.get(indexToDelete);
        page.deletePersonByIndex(indexToDelete);
        List<String> namesAfter = page.getPersonNames();
        List<String> jobsAfter  = page.getPersonJobs();

        assertEquals(namesBefore.size() - 1, namesAfter.size());
        assertEquals(jobsBefore.size()  - 1, jobsAfter.size());
        for (int i = 0, j = 0; i < namesBefore.size(); i++) {
            if (i == indexToDelete) continue;
            assertEquals(namesBefore.get(i), namesAfter.get(j));
            assertEquals(jobsBefore.get(i),  jobsAfter.get(j));
            j++;
        }

        assertFalse(namesAfter.contains(deletedName));
        assertFalse(jobsAfter.contains(deletedJob));
    }


    @Test
    public void deletePersonAll() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete all people and check that there is no no table on page, but the button Add is still present and working
         */ // store the list of people and jobs currently on page
        int countBefore = page.getPeopleCount();
        assertTrue(countBefore > 0, "List cant be empty");

        while (page.getPeopleCount() > 0) {
            page.deletePersonByIndex(0);
        }
        assertFalse(page.isTablePresent(), "Table must dissapear");
        assertTrue(page.isAddPersonButtonVisible(), "Button 'Add person' must stay");

        page.addPerson("Single Person", "Only Job");
        assertEquals(1, page.getPeopleCount());
        assertEquals("Single Person", page.getPersonNames().get(0));
        assertEquals("Only Job",      page.getPersonJobs().get(0));

    }
}
