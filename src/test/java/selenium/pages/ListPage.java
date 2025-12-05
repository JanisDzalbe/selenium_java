package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import selenium.customDataTable.PersonData;

import java.util.ArrayList;
import java.util.List;

public class ListPage {
    @FindBy(how = How.CSS, using = "[onclick = \"openModalForAddPersonWithJob()\"]")
    private WebElement addPerson;
    @FindBy(how = How.CSS, using = "[onclick = \"resetListOfPeople()\"]")
    private WebElement resetList;
    @FindBy(how = How.CLASS_NAME, using = "w3-padding-16")
    private List<WebElement> people;

    public String getPageUrl() {
        return "https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs";
    }

    public void clickAddPerson() {
        addPerson.click();
    }
    public void clickResetList() {
        resetList.click();
    }

    public List<PersonData> getPeople() {
        List<PersonData> listOfPeople = new ArrayList<>();

        for (WebElement person : people) {
            listOfPeople.add(new PersonData(
                    person.findElement(By.className("name")).getText(),
                    person.findElement(By.className("job")).getText())
            );
        }
        return listOfPeople;
    }
    private WebElement getPersonWebElement(String name) {
        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals(name)) {
                return person;
            }
        }
        return null;
    }
    public PersonData getPerson(String name) {
        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals(name)) {
                return new PersonData(
                        person.findElement(By.className("name")).getText(),
                        person.findElement(By.className("job")).getText()
                );
            }
        }
        return null;
    }
    public void clickEditPerson(String name) {
        WebElement person = getPersonWebElement(name);
        person.findElement(By.className("editbtn")).click();
    }
    public void clickRemovePerson(String name) {
        WebElement person = getPersonWebElement(name);
        person.findElement(By.className("closebtn")).click();
    }
}
