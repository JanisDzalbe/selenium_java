package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.ls.LSOutput;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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

        List<String> namesList = new ArrayList<>();
        namesList.add("Mike");
        namesList.add("Jill");
        namesList.add("Jane");
        namesList.add("John");
        namesList.add("Sarah");
        namesList.add("Carlos");
        namesList.add("Emily");
        namesList.add("David");
        namesList.add("Maria");
                                                                                namesList.add("Alex");
        List<String> jobsList = new ArrayList<>();
        jobsList.add("Web Designer");
        jobsList.add("Support");
        jobsList.add("Accountant");
        jobsList.add("Software Engineer");
        jobsList.add("Product Manager");
        jobsList.add("Data Analyst");
        jobsList.add("UX Designer");
        jobsList.add("Project Manager");
        jobsList.add("QA Engineer");
        jobsList.add("DevOps Engineer");

        WebElement buttonAdd = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(@class,'w3-btn') and contains(text(),'Add person')]"));
        assertTrue(buttonAdd.isDisplayed());
        assertTrue(buttonAdd.isEnabled());

        WebElement buttonResetList = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(@class,'w3-btn') and contains(text(),'Reset List')]"));
        assertTrue(buttonResetList.isDisplayed());
        assertTrue(buttonResetList.isEnabled());

        String url = driver.getCurrentUrl();
        assertEquals(url, "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");

     //   List<WebElement> people = driver.findElements(By.xpath("//*[@id='listOfPeople']"));
        List<WebElement> allPeople = driver.findElements(By.className("w3-padding-16"));

        String xName = "";
        String xJob = "";
        for (int i = 0; i < allPeople.size(); i++) {
            WebElement person = allPeople.get(i);
              xName = person.findElement(By.className("w3-xlarge")).getText();
              xJob = person.findElement(By.className("job")).getText();

              assertEquals(xName, namesList.get(i));
              assertEquals(xJob, jobsList.get(i));

//            Just for visual troubleshooting and debugging;
//            assertEquals(xJob, jobsList.get(i+1));
//            System.out.println("name: " + i + " -" + xName + "-");
//            System.out.println("name: " + i + " -" + namesList.get(i) + "-");
//            System.out.println("job: " + i + " -" + xJob + "-");
//            System.out.println("job: " + i + " -" + jobsList.get(i) + "-");
        }
    }



    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job

        List<String> namesList = new ArrayList<>();
        namesList.add("Mike");
        namesList.add("Jill");
        namesList.add("Jane");
        namesList.add("John");
        namesList.add("Sarah");
        namesList.add("Carlos");
        namesList.add("Emily");
        namesList.add("David");
        namesList.add("Maria");
        namesList.add("Alex");

        List<String> jobsList = new ArrayList<>();
        jobsList.add("Web Designer");
        jobsList.add("Support");
        jobsList.add("Accountant");
        jobsList.add("Software Engineer");
        jobsList.add("Product Manager");
        jobsList.add("Data Analyst");
        jobsList.add("UX Designer");
        jobsList.add("Project Manager");
        jobsList.add("QA Engineer");
        jobsList.add("DevOps Engineer");

        WebElement buttonAdd = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(@class,'w3-btn') and contains(text(),'Add person')]"));
        assertTrue(buttonAdd.isDisplayed());
        assertTrue(buttonAdd.isEnabled());

        WebElement buttonResetList = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(@class,'w3-btn') and contains(text(),'Reset List')]"));
        assertTrue(buttonResetList.isDisplayed());
        assertTrue(buttonResetList.isEnabled());

        String url = driver.getCurrentUrl();
        assertEquals(url, "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
        //    System.out.println(driver.getCurrentUrl());

        buttonAdd.click();

        url = driver.getCurrentUrl();
        //      System.out.println(driver.getCurrentUrl());
        assertEquals(url,"https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html");

       //  driver.findElement(By.xpath("//*[@id=\"addPersonBtn\"]")).click();

        WebElement fieldName =  driver.findElement(By.xpath("//*[@id='name']"));
        WebElement fieldJob = driver.findElement(By.xpath("//*[@id='job']"));

        assertEquals("", fieldName.getText());
        assertEquals("", fieldJob.getText());

        String nameKey = "Boris";
        String jobKey = "Team lead";

        namesList.add(nameKey);
        jobsList.add(jobKey);

        fieldName.sendKeys(nameKey);
        fieldJob.sendKeys(jobKey);

        assertEquals("Boris", fieldName.getAttribute("value"));
        assertEquals("Team lead",  fieldJob.getAttribute("value"));

        driver.findElement(By.xpath("//*[@id='modal_button' and contains(text(),'Add')]")).click();

        url = driver.getCurrentUrl();

        assertEquals(url,"https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");

        List<WebElement> allPeople = driver.findElements(By.className("w3-padding-16"));

        String xName = "";
        String xJob = "";
        for (int i = 0; i < allPeople.size(); i++) {
            WebElement person = allPeople.get(i);
            xName = person.findElement(By.className("w3-xlarge")).getText();
            xJob = person.findElement(By.className("job")).getText();

            assertEquals(xName, namesList.get(i));
            assertEquals(xJob, jobsList.get(i));
        }

        assertEquals("Boris", namesList.get(allPeople.size() - 1));
        assertEquals("Team lead",jobsList.get(allPeople.size() - 1 ));
    }


    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        List<WebElement> allPeople = driver.findElements(By.className("w3-padding-16"));

        //Change 3rd person value
        allPeople.get(2).findElement(By.cssSelector(".fa.fa-pencil")).click();

        String url = "https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html?id=2";
        String currentUrl = driver.getCurrentUrl();
        assertEquals(url, currentUrl);

        WebElement fieldName =  driver.findElement(By.xpath("//*[@id='name']"));
        WebElement fieldJob = driver.findElement(By.xpath("//*[@id='job']"));

        assertEquals("Jane", fieldName.getAttribute("value"));
        assertEquals("Accountant",  fieldJob.getAttribute("value"));


        fieldName.clear();
        fieldJob.clear();
        assertEquals("", fieldName.getAttribute("value"));
        assertEquals("",  fieldJob.getAttribute("value"));

        String  nameKey = "Jack";
        String jobKey = "HR";
        fieldName.sendKeys(nameKey);
        fieldJob.sendKeys(jobKey);
        assertEquals("Jack", fieldName.getAttribute("value"));
        assertEquals("HR",  fieldJob.getAttribute("value"));


        WebElement buttonEdit = driver.findElement(By.xpath("//*[@id='modal_button'  and contains(text(),'Edit')]"));
        assertTrue(buttonEdit.isDisplayed());
        assertTrue(buttonEdit.isEnabled());

        buttonEdit.click();

        List<WebElement> updatedPeople = driver.findElements(By.className("w3-padding-16"));

        url = "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html";
        currentUrl = driver.getCurrentUrl();
        assertEquals(url, currentUrl);

        WebElement updatedPerson = updatedPeople.get(2);
        String xName = updatedPerson.findElement(By.xpath("//*[contains(@class,'w3-xlarge') and contains(text(),'Jack')]")).getText();
        String xJob = updatedPerson.findElement(By.xpath("//*[contains(@class,'job') and contains(text(),'HR')]")).getText();

        assertEquals("Jack", xName);
        assertEquals("HR", xJob);

    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list

        List<String> namesList = new ArrayList<>();
        namesList.add("Mike");
        namesList.add("Jill");
        namesList.add("Jane");
        namesList.add("John");
        namesList.add("Sarah");
        namesList.add("Carlos");
        namesList.add("Emily");
        namesList.add("David");
        namesList.add("Maria");
        namesList.add("Alex");

        List<String> jobsList = new ArrayList<>();
        jobsList.add("Web Designer");
        jobsList.add("Support");
        jobsList.add("Accountant");
        jobsList.add("Software Engineer");
        jobsList.add("Product Manager");
        jobsList.add("Data Analyst");
        jobsList.add("UX Designer");
        jobsList.add("Project Manager");
        jobsList.add("QA Engineer");
        jobsList.add("DevOps Engineer");

        List<WebElement> allPeople = driver.findElements(By.className("w3-padding-16"));

        //Remove 2nd person //w3-closebtn × "Jill" "Support"
        String deleteName = "Jill";
        String deleteJob = "Support";

        allPeople.get(1).findElement(By.xpath(".//*[contains(@class,'w3-closebtn') and contains(text(),'×')]")).click();
        jobsList.remove(1);

        String url = "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs";
        String currentUrl = driver.getCurrentUrl();
        assertEquals(url, currentUrl);

        String xName = "";
        String xJob = "";

        List<WebElement> updatedAllPeople = driver.findElements(By.className("w3-padding-16"));

        //List debug
//        for (int i = 0; i < updatedAllPeople.size(); i++) {
//            System.out.println(updatedAllPeople.get(i).getText());
//        }

        for (int i = 0; i < updatedAllPeople.size(); i++) {
            WebElement person = updatedAllPeople.get(i);
            xName = person.findElement(By.className("w3-xlarge")).getText();
            xJob = person.findElement(By.className("job")).getText();

                assertNotEquals(xName, deleteName);
                assertNotEquals(xJob, deleteJob);
        }


    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries

        List<String> namesList = new ArrayList<>();
        namesList.add("Mike");
        namesList.add("Jill");
        namesList.add("Jane");
        namesList.add("John");
        namesList.add("Sarah");
        namesList.add("Carlos");
        namesList.add("Emily");
        namesList.add("David");
        namesList.add("Maria");
        namesList.add("Alex");

        List<String> jobsList = new ArrayList<>();
        jobsList.add("Web Designer");
        jobsList.add("Support");
        jobsList.add("Accountant");
        jobsList.add("Software Engineer");
        jobsList.add("Product Manager");
        jobsList.add("Data Analyst");
        jobsList.add("UX Designer");
        jobsList.add("Project Manager");
        jobsList.add("QA Engineer");
        jobsList.add("DevOps Engineer");

        WebElement buttonAdd = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(@class,'w3-btn') and contains(text(),'Add person')]"));
        assertTrue(buttonAdd.isDisplayed());
        assertTrue(buttonAdd.isEnabled());

        WebElement buttonResetList = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(@class,'w3-btn') and contains(text(),'Reset List')]"));
        assertTrue(buttonResetList.isDisplayed());
        assertTrue(buttonResetList.isEnabled());

        String url = driver.getCurrentUrl();
        assertEquals(url, "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
        //    System.out.println(driver.getCurrentUrl());

        buttonAdd.click();

        url = driver.getCurrentUrl();
        //      System.out.println(driver.getCurrentUrl());
        assertEquals(url,"https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html");

        //  driver.findElement(By.xpath("//*[@id=\"addPersonBtn\"]")).click();

        WebElement fieldName =  driver.findElement(By.xpath("//*[@id='name']"));
        WebElement fieldJob = driver.findElement(By.xpath("//*[@id='job']"));

        assertEquals("", fieldName.getText());
        assertEquals("", fieldJob.getText());

        String nameKey = "Boris";
        String jobKey = "Team lead";

        namesList.add(nameKey);
        jobsList.add(jobKey);

        fieldName.sendKeys(nameKey);
        fieldJob.sendKeys(jobKey);

        assertEquals("Boris", fieldName.getAttribute("value"));
        assertEquals("Team lead",  fieldJob.getAttribute("value"));

        driver.findElement(By.xpath("//*[@id='modal_button' and contains(text(),'Add')]")).click();

        url = driver.getCurrentUrl();

        assertEquals(url,"https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");

        List<WebElement> allPeople = driver.findElements(By.className("w3-padding-16"));

        String xName = "";
        String xJob = "";
        for (int i = 0; i < allPeople.size(); i++) {
            WebElement person = allPeople.get(i);
            xName = person.findElement(By.className("w3-xlarge")).getText();
            xJob = person.findElement(By.className("job")).getText();

            assertEquals(xName, namesList.get(i));
            assertEquals(xJob, jobsList.get(i));
        }

        assertEquals("Boris", namesList.get(allPeople.size() - 1));
        assertEquals("Team lead",jobsList.get(allPeople.size() - 1 ));

        allPeople = driver.findElements(By.className("w3-padding-16"));
        //Remove 11th person // "Boris" "Team lead"
        String deleteName = "Boris";
        String deleteJob = "Team lead";
        allPeople.get(10).findElement(By.xpath(".//*[contains(@class,'w3-closebtn') and contains(text(),'×')]")).click();
        jobsList.remove(10);

        url = "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html";
        String currentUrl = driver.getCurrentUrl();
        assertEquals(url, currentUrl);

         xName = "";
         xJob = "";

        List<WebElement> updatedAllPeople1 = driver.findElements(By.className("w3-padding-16"));

        for (int i = 0; i < updatedAllPeople1.size(); i++) {
            WebElement person = updatedAllPeople1.get(i);
            xName = person.findElement(By.className("w3-xlarge")).getText();
            xJob = person.findElement(By.className("job")).getText();

            assertNotEquals(xName, deleteName);
            assertNotEquals(xJob, deleteJob);
        }

        //Double check
//        for (int i = 0; i < updatedAllPeople1.size(); i++) {
//            System.out.println(updatedAllPeople1.get(i).getText());
//        }

        // click on Button Reset List
        buttonResetList = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(@class,'w3-btn') and contains(text(),'Reset List')]"));
        assertTrue(buttonResetList.isDisplayed());
        assertTrue(buttonResetList.isEnabled());
        buttonResetList.click();


        List<WebElement> updatedAllPeople2 = driver.findElements(By.className("w3-padding-16"));

        for (int i = 0; i < updatedAllPeople2.size(); i++) {
            WebElement person = updatedAllPeople2.get(i);
            xName = person.findElement(By.className("w3-xlarge")).getText();
            xJob = person.findElement(By.className("job")).getText();

            assertEquals(xName, namesList.get(i));
            assertEquals(xJob, jobsList.get(i));

        }
    }




}

