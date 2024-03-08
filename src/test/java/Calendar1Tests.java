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

public class Calendar1Tests {

    static WebDriver driver;

    @Test
    public void selectDateCalendar1() throws IOException, InterruptedException {

        //webdriver instatiate
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\smruti\\OneDrive\\Desktop\\AmazingFunctionality\\src\\main\\resources\\executables\\chromedriver.exe");
        driver= new ChromeDriver();


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

        //select date, month and year from calendar
        selectDateInCalendar("29", "November", "2025");


        //quit the webdriver
        driver.quit();

    }

    public static void selectDateInCalendar(String day, String month, String year) throws InterruptedException {

        if(Integer.parseInt(day) > 31 || Integer.parseInt(day) < 1){
            System.out.println("Invalid date : Day should be between 1 to 31");
            return;
        }
        if(leapYear(year)){
            if(month.equals("February")){
                if(Integer.parseInt(day) > 29){
                    System.out.println("Its a leap year and choose between 1 to 29 for FEB");
                    return;
                }
            }
        }else{
            if(month.equals("February")){
                if(Integer.parseInt(day) > 28){
                    System.out.println("Its not a leap year and choose between 1 to 28 for FEB");
                    return;
                }
            }
        }


        //Retrive current year and month that's shown in calendar
        String currentMonthYear = driver.findElement(By.className("ui-datepicker-title")).getText();
        String monthInCalendar = currentMonthYear.split(" ")[0];
        String yearInCalendar = currentMonthYear.split(" ")[1];

        //iterate over the calendar with next arrow until you land on desired date
        while(!(month.equals(monthInCalendar) && (year.equals(yearInCalendar)))) {

            driver.findElement(By.xpath("//a[@title='Next']")).click();
            currentMonthYear = driver.findElement(By.className("ui-datepicker-title")).getText();
            monthInCalendar = currentMonthYear.split(" ")[0];
            yearInCalendar = currentMonthYear.split(" ")[1];

        }

        String dayXpath = "//td[@data-handler='selectDay']/a[text()=\'"+day+"\']";
        driver.findElement(By.xpath(dayXpath)).click();
        Thread.sleep(5000);


    }

    public static boolean leapYear(String year){
        int yearInt = Integer.parseInt(year);
        if((yearInt % 400 == 0) || ((yearInt % 4 == 0) && (yearInt % 100 != 0))){
            return true;
        }
        return false;
    }

}
