package selenium.pages;

import org.openqa.selenium.*;
import java.util.ArrayList;
import java.util.List;

public class ListPage {

    private final WebDriver driver;

    private final By addPersonButton =
            By.xpath("//button[normalize-space()='Add person']");
    private final By resetListButton =
            By.xpath("//button[normalize-space()='Reset List']");

    private final By listLocator = By.id("listOfPeople");
    private final By personItems = By.cssSelector("#listOfPeople li");

    public ListPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isTablePresent() {
        return !driver.findElements(personItems).isEmpty();
    }


    public List<Person> getPeople() {
        List<Person> people = new ArrayList<>();

        if (!isTablePresent()) {
            return people;
        }

        for (WebElement li : driver.findElements(personItems)) {
            String name = li.findElement(By.cssSelector(".name")).getText().trim();
            String job  = li.findElement(By.cssSelector(".job")).getText().trim();
            people.add(new Person(name, job));
        }
        return people;
    }

    public FormPage clickAddPerson() {
        driver.findElement(addPersonButton).click();
        return new FormPage(driver);
    }

    public void clickResetList() {
        driver.findElement(resetListButton).click();
    }

    public FormPage clickEditFor(Person person) {
        WebElement li = findItemByName(person.getName());
        li.findElement(By.xpath(".//span[contains(@onclick,'openModalForEditPersonWithJob')]")).click();
        return new FormPage(driver);
    }

    public void clickDeleteFor(Person person) {
        WebElement li = findItemByName(person.getName());
        li.findElement(By.xpath(".//span[contains(@onclick,'deletePerson')]")).click();
    }

    private WebElement findItemByName(String name) {
        for (WebElement li : driver.findElements(personItems)) {
            String liName = li.findElement(By.cssSelector(".name")).getText().trim();
            if (liName.equals(name)) {
                return li;
            }
        }
        throw new NoSuchElementException("No person with name: " + name);
    }
}
