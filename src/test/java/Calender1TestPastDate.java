import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Calender1TestPastDate {

    static WebDriver driver;

    @Test
    public void selectDateCalendar1PastDate() throws IOException, InterruptedException {

        //webdriver instatiate
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\smruti\\OneDrive\\Desktop\\AmazingFunctionality\\src\\main\\resources\\executables\\chromedriver.exe");
        driver = new ChromeDriver();


        //read property file
        FileInputStream file = new FileInputStream("C:\\Users\\smruti\\OneDrive\\Desktop\\AmazingFunctionality\\src\\main\\resources\\config\\config.properties");
        Properties prop = new Properties();
        prop.load(file);

        //hit the URL & maximise the window
        driver.get(prop.getProperty("urlForCalendar1"));
        driver.manage().window().maximize();

        //click on the input box to view the calendar
        WebElement calendarField = driver.findElement(By.id("datepicker"));
        calendarField.click();

        //wait until the calendar appears, as its ajax (asynchronous), the calendar gets loaded without any modification of url
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));

    }
}