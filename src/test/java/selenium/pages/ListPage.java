package selenium.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ListPage {
    //there are two buttons, fetching them both in order to test functionality of both :)
    @FindBy(xpath = "//button[@id='addPersonBtn' and text() = 'Add person']")
    private List<WebElement> addPersonButtons;
    @FindBy(xpath = "//button[@id='addPersonBtn' and text() = 'Reset List']")
    private List<WebElement> resetListButtons;

    @FindBy(css = "ul#listOfPeople li")
    private List<WebElement> peopleList;
    public List<WebElement> getPeopleList(){
        return peopleList;
    }

    public void clickAddPersonTopButton() {
        addPersonButtons.getFirst().click();
    }

    public String getPersonName(int index){
        return peopleList.get(index).findElement(By.cssSelector("span.name")).getText();
    }

    public Map<String, String> getPeople(){
        Map<String, String> peopleAndJobs = new HashMap<>();
        for(WebElement person : peopleList){
            peopleAndJobs.put(person.findElement(By.cssSelector("span.name")).getText(), person.findElement(By.cssSelector("span.job")).getText());

        }
        return peopleAndJobs;
    }

    public void assertPeopleInList(Map<String, String> peopleAndJobs){
        for(WebElement person : peopleList){
            String name = person.findElement(By.cssSelector("span.name")).getText();
            String job = person.findElement(By.cssSelector("span.job")).getText();

            assertTrue(peopleAndJobs.containsKey(name) && peopleAndJobs.get(name).equals(job));
        }
    }

    public void clickEditPerson(int index){
        peopleList.get(index).findElement(By.cssSelector("span.editbtn")).click();
    }

    public void clickDeletePerson(int index){
        peopleList.get(index).findElement(By.cssSelector("span.closebtn")).click();
    }




}
