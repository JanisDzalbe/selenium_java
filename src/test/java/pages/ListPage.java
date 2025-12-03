package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ListPage {

    WebDriver driver;

    @FindBy(css = "#addPersonBtn")
    WebElement addPersonBtn;

    @FindBy(css = "#listOfPeople li[id^='person']")
    List<WebElement> personRows;

    public ListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // return all people as objects
    public List<Person> getAllPeople() {
        List<Person> list = new ArrayList<>();

        for (WebElement row : personRows) {
            String name = row.findElement(By.cssSelector(".name")).getText();
            String job  = row.findElement(By.cssSelector(".job")).getText();
            list.add(new Person(name, job));
        }
        return list;
    }

    public void clickAddPerson() {
        addPersonBtn.click();
    }

    public void clickEditPerson(int index) {
        personRows.get(index)
                .findElement(By.cssSelector("span[onclick*='openModalForEditPersonWithJob']"))
                .click();
    }

    public void deletePerson(int index) {
        personRows.get(index)
                .findElement(By.cssSelector("span[onclick^='deletePerson']"))
                .click();
    }

    public boolean isListEmpty() {
        return driver.findElements(By.cssSelector("#listOfPeople li")).isEmpty();
    }
}
