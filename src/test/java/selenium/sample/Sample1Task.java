package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static selenium.sample.Sample1.libWithDriversLocation;

public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {

        System.setProperty(
                "webdriver.edge.driver",
                libWithDriversLocation + "msedgedriver" + new selenium.ChangeToFileExtension().extension()
        );

        WebDriver driver = new EdgeDriver();

        driver.get("https://janisdzalbe.github.io/example-site/index2.html");

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(10000);
        driver.quit();
//        TODO:
//         define driver
//         go to https://janisdzalbe.github.io/example-site/index2.html
//         get title of page
//         get URL of current page
//         close browser
    }
}
