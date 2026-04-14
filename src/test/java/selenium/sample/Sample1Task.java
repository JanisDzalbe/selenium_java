package selenium.sample;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {

        //get the driver
        WebDriver driver = new EdgeDriver();
        System.setProperty("webdriver.edge.driver", "lib/msedgedriver.exe");

        //go to https://janisdzalbe.github.io/example-site/index2.html
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");

        //get title of page
        System.out.println(driver.getTitle());

        //get URL of current page
        System.out.println(driver.getCurrentUrl());

        //close browser
        driver.quit();
    }
}
