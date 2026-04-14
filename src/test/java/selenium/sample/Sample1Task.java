package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;

public class Sample1Task {

    static String libWithDriversLocation = System.getProperty("user.dir")
            + File.separator + "lib" + File.separator;

    @Test
    public void goToHomepage() throws Exception {

        //driver defines here
        WebDriver driver = new ChromeDriver();

        //getting page
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");

        //getting title of the page
        System.out.println("Page Title: " + driver.getTitle());

        // getting URL of current page
        System.out.println("Page URL: " + driver.getCurrentUrl());

        //pause
        Thread.sleep(3000);

        //quiting browser
        driver.quit();
    }
}

