package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
        System.setProperty("webdriver.chrome.driver", lib.chromedriver.exe + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        WebDriver driver = new ChromeDriver();

//         go to https://janisdzalbe.github.io/example-site/index2.html
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");

//         get title of page
        System.out.println(driver.getTitle());

//         get URL of current page
        System.out.println(driver.findElement(By.id("h1")).getText());

//         close browser
        driver.quit();

    }
}
