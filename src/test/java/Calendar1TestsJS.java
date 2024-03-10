import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Calendar1TestsJS {

    static WebDriver driver;

    @Test
    public void selectDateCalendar1UsingJS() throws IOException, InterruptedException {

        //webdriver instantiate
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\smruti\\OneDrive\\Desktop\\AmazingFunctionality\\src\\main\\resources\\executables\\chromedriver.exe");
        driver = new ChromeDriver();


        //read property file
        FileInputStream file = new FileInputStream("C:\\Users\\smruti\\OneDrive\\Desktop\\AmazingFunctionality\\src\\main\\resources\\config\\config.properties");
        Properties prop = new Properties();
        prop.load(file);

        //hit the URL & maximise the window
        driver.get(prop.getProperty("urlForCalendar1"));
        driver.manage().window().maximize();

        //enter the date details directly on input box
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementById('datepicker').value='12/05/2026'");
        Thread.sleep(5000);

    }
}