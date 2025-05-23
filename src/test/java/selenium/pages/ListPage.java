package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ListPage {
    WebDriver driver;

    public ListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddButton() {
        driver.findElement(By.id("addPersonBtn")).click();
    }

    public List<String[]> getPeopleList() {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        return people.stream().map(person -> {
            String name = person.findElement(By.cssSelector("span.w3-xlarge")).getText().trim();
            String job = person.findElement(By.cssSelector("span.job")).getText().trim();
            return new String[]{name, job};
        }).collect(Collectors.toList());
    }

    public void clickEditButton(int indx) {
        String selector = String.format("span[onclick='openModalForEditPersonWithJob(%d)']", indx);
        WebElement editButton = driver.findElement(By.cssSelector(selector));
        editButton.click();
    }

    public void clickDeleteButton(int indx) {
        String script = String.format("deletePerson(%d)", indx);
       // String xpath = String.format("//span[contains(@onclick, '%s')]", script);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(@onclick, 'deletePerson')]")
        ));
        deleteBtn.click();

    }

    public void waitUntilListSizeChanges(int expectedSize) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> getPeopleList().size() == expectedSize);
    }

    public boolean isPeopleListPresent() {
        List<WebElement> peopleItems = driver.findElements(By.cssSelector("#listOfPeople > li"));
        return !peopleItems.isEmpty();
    }

    public boolean isAddButtonPresent() {
        return driver.findElements(By.id("addPersonBtn")).size() > 0;
    }


}
