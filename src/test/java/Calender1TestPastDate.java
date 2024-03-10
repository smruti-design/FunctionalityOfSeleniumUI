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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Properties;

public class Calender1TestPastDate {

    static WebDriver driver;

    @Test
    public void selectDateCalendar1PastDate() throws IOException, InterruptedException {


        //date
        String date = "16-05-2025";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ld = LocalDate.parse(date,df);
        int d = ld.getDayOfMonth();
        int m = ld.getMonthValue();
        int y = ld.getYear();


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


        //click on the input box to view the calendar
        WebElement calendarField = driver.findElement(By.id("datepicker"));
        calendarField.click();


        //wait until the calendar appears, as its ajax (asynchronous), the calendar gets loaded without any modification of url
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));


        //get the current month and year from the calendar shown
        String monthText = driver.findElement(By.className("ui-datepicker-month")).getText();
        int monthInt = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(monthText).get(ChronoField.MONTH_OF_YEAR);
        String yearText = driver.findElement(By.className("ui-datepicker-year")).getText();
        int yearInt = Integer.parseInt(yearText);

        //if the dates are past ones
        while(monthInt > m || yearInt > y){
            driver.findElement(By.xpath("//a[@title='Prev']")).click();
            monthText = driver.findElement(By.className("ui-datepicker-month")).getText();
            monthInt = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(monthText).get(ChronoField.MONTH_OF_YEAR);
            yearText = driver.findElement(By.className("ui-datepicker-year")).getText();
            yearInt = Integer.parseInt(yearText);

        }

        //if the dates are future ones
        while(monthInt < m || yearInt < y){
            driver.findElement(By.xpath("//a[@title='Next']")).click();
            monthText = driver.findElement(By.className("ui-datepicker-month")).getText();
            monthInt = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(monthText).get(ChronoField.MONTH_OF_YEAR);
            yearText = driver.findElement(By.className("ui-datepicker-year")).getText();
            yearInt = Integer.parseInt(yearText);

        }

        //select the day
        String dayXpath = "//td[@data-handler='selectDay']/a[text()=\'"+d+"\']";
        driver.findElement(By.xpath(dayXpath)).click();

        Thread.sleep(5000);
        //quit the webdriver
        driver.quit();
    }
}