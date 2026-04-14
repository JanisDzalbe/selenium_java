package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

public class Sample1Task {



    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
//         go to https://janisdzalbe.github.io/example-site/index2.html
//         get title of page
//         get URL of current page
//         close browser

        WebDriver driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/");
        String title= driver.getTitle();
        String url = driver.getCurrentUrl();

        System.out.println(title);
        System.out.println(url);

        Thread.sleep(2000);

        // Close browser
        driver.quit();
    }
}
