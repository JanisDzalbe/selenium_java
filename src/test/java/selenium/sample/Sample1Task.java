package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {
        // Set system property - we will have a method to do this from now on
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // initialize driver
        WebDriver driver = new ChromeDriver();

        /* From now on, we will use this initialization utility method to not have repeated code:
        driver = BootcampUtils.initializeChromeDriver();
         */

        // open test homepage
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");
        System.out.println(driver.findElement(By.id("h1")).getText());
        // get title of page
        System.out.println(driver.getTitle());
        // get URL of current page
        System.out.println(driver.getCurrentUrl());
        // Sleep for 10 seconds
        Thread.sleep(10000);

        // Close browser
        driver.quit();
//        TODO:
//         define driver
//         go to https://janisdzalbe.github.io/example-site/index2.html
//         get title of page
//         get URL of current page
//         close browser
    }
}
