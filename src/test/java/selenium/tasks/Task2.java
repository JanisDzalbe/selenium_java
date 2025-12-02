package selenium.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;


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

        WebElement addBtn = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetBtn = driver.findElement(By.xpath("//button[text()='Reset List']"));

        Assertions.assertTrue(addBtn.isDisplayed() && addBtn.isEnabled(), "'Add person' button must be visible & enabled");
        Assertions.assertTrue(resetBtn.isDisplayed() && resetBtn.isEnabled(), "'Reset List' button must be visible & enabled");

        String[][] expected = {
                {"Mike", "Web Designer"},
                {"Jill", "Support"},
                {"Jane", "Accountant"},
                {"John", "Software Engineer"},
                {"Sarah", "Product Manager"},
                {"Carlos", "Data Analyst"},
                {"Emily", "UX Designer"},
                {"David", "Project Manager"},
                {"Maria", "QA Engineer"},
                {"Alex", "DevOps Engineer"}
        };

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople > div"));
        Assertions.assertEquals(10, people.size(), "List must contain exactly 10 people");

        for (int i = 0; i < people.size(); i++) {
            WebElement person = people.get(i);

            String actualName = person.findElement(By.className("name")).getText().trim();
            String actualJob = person.findElement(By.className("job")).getText().trim();

            Assertions.assertEquals(expected[i][0], actualName, "Name mismatch at index " + i);
            Assertions.assertEquals(expected[i][1], actualJob, "Job mismatch at index " + i);
        }

    }


    @Test
    public void addNewPerson() throws Exception {

        WebElement addPersonBtn = driver.findElement(By.id("addPersonBtn"));
        addPersonBtn.click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));

        String newName = "TestPerson";
        String newJob = "Robot Engineer";

        nameInput.sendKeys(newName);
        jobInput.sendKeys(newJob);

        WebElement addButton = driver.findElements(By.id("modal_button")).get(0);
        addButton.click();

        Thread.sleep(500);

        List<WebElement> people = driver.findElements(
                By.cssSelector("#listOfPeople > div")
        );

        WebElement lastPerson = people.get(people.size() - 1);

        String actualName = lastPerson.findElement(By.cssSelector("span.name")).getText().trim();
        String actualJob = lastPerson.findElement(By.cssSelector("span.job")).getText().trim();

        Assertions.assertEquals(newName, actualName, "New person's name mismatch");
        Assertions.assertEquals(newJob, actualJob, "New person's job mismatch");
    }

    @Test
    public void editExistingPerson() throws Exception {

        driver.findElement(By.xpath("(//span[contains(@onclick,'openModalForEditPersonWithJob')])[1]")).click();

        String nameInModal = driver.findElement(By.id("name")).getAttribute("value").trim();
        String jobInModal = driver.findElement(By.id("job")).getAttribute("value").trim();

        Assertions.assertEquals("Mike", nameInModal, "Expected first person's name to be Mike in modal");
        Assertions.assertEquals("Web Designer", jobInModal, "Expected first person's job to be Web Designer in modal");

        WebElement jobInput = driver.findElement(By.id("job"));
        jobInput.clear();
        jobInput.sendKeys("Senior Web Designer");

        driver.findElement(By.xpath("//button[text()='Edit']")).click();

        WebElement firstPerson = driver.findElement(By.xpath("(//ul[@id='listOfPeople']//li)[1]"));
        String updatedJob = firstPerson.findElement(By.xpath(".//span[contains(@class,'job')]")).getText().trim();

        Assertions.assertEquals("Senior Web Designer", updatedJob, "The job in the list should be updated");

    }


    @Test
    public void removeExistingPerson() {

        WebElement firstPerson = driver.findElement(By.cssSelector("#listOfPeople li:first-child .name"));
        String removedName = firstPerson.getText().trim();

        WebElement deleteBtn = driver.findElement(By.cssSelector("#listOfPeople li:first-child span.w3-closebtn"));
        deleteBtn.click();

        List<WebElement> remaining = driver.findElements(By.xpath("//span[@class='name' and normalize-space()='" + removedName + "']"));

        Assertions.assertEquals(0, remaining.size(), "Person was not removed!");
    }


    @Test
    public void resetList() throws Exception {

        String[] expected = {"Mike", "Jill", "Jane", "John", "Sarah", "Carlos", "Emily", "David", "Maria", "Alex"};

        WebElement firstNameElem = driver.findElement(By.cssSelector("#listOfPeople li:first-child .name"));
        String removedName = firstNameElem.getText().trim();

        WebElement deleteBtn = driver.findElement(By.cssSelector("#listOfPeople li:first-child span.w3-closebtn"));
        deleteBtn.click();

        List<WebElement> foundAfterDelete = driver.findElements(By.xpath("//span[contains(@class,'name') and normalize-space()='" + removedName + "']"));
        Assertions.assertTrue(foundAfterDelete.isEmpty(), "Person was not removed — modification failed");

        List<WebElement> resetButtons = driver.findElements(By.xpath("//button[contains(@onclick,'resetListOfPeople')]"));
        if (!resetButtons.isEmpty()) {
            resetButtons.get(0).click();
        } else {
            driver.findElement(By.xpath("//button[normalize-space()='Reset List']")).click();
        }

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        int tries = 0;
        while (people.size() != 10 && tries < 25) {
            people = driver.findElements(By.cssSelector("#listOfPeople li"));
            tries++;
        }

        Assertions.assertEquals(10, people.size(), "Reset List did NOT restore 10 entries (tried " + tries + " times)");

        for (int i = 0; i < expected.length; i++) {
            WebElement person = people.get(i);
            String actualName = person.findElement(By.cssSelector(".name")).getText().trim();
            Assertions.assertEquals(expected[i], actualName, "Name mismatch at index " + i);
        }
    }
}



