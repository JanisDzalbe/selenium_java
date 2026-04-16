package selenium.tasks.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.tasks.Person;
import java.util.List;
import java.util.stream.Collectors;

public class ListPage {
    private final WebDriver driver;

    public ListPage(WebDriver driver) { this.driver = driver; }

    public void clickAddPerson() {
        driver.findElement(By.xpath("//button[text()='Add person']")).click();
    }

    public void clickEditForPerson(int index) {
        driver.findElements(By.className("editbtn")).get(index).click();
    }

    public void clickDeleteForPerson(int index) {
        driver.findElements(By.className("closebtn")).get(index).click();
    }

    public List<Person> getPeopleList() {
        return driver.findElements(By.cssSelector("#listOfPeople li")).stream()
                .map(li -> new Person(
                        li.findElement(By.className("name")).getText(),
                        li.findElement(By.className("job")).getText()
                )).collect(Collectors.toList());
    }
}
