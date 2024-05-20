package saucedemo.linear;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.base.BaseTest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class JavascriptExecutorTest extends BaseTest {
    @Test
    public void testEnableButton() {
        driver.get("https://testerautomatyczny2023.github.io/CookieTesting/disabledbutton.html");

        WebElement button = driver.findElement(By.id("myButton"));
        Assert.assertFalse(button.isEnabled());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('disabled')",button);

        Assert.assertTrue(button.isEnabled());
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(),"Kliknięto przycisk!");
        alert.accept();

    }

    @Test
    public void sumCount() {
        driver.get("https://testerautomatyczny2023.github.io/CookieTesting/nobutton.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("calculateTotal()");

        WebElement sum = driver.findElement(By.id("totalAmount"));
        Assert.assertTrue(sum.isDisplayed());

    }

    @Test
    public void testCalendar() {
        driver.get("https://testerautomatyczny2023.github.io/CookieTesting/calendar.html");

        WebElement arrivalDateInput = driver.findElement(By.name("arrival"));
        WebElement leaveDateInput = driver.findElement(By.name("leave"));
        WebElement submitButton = driver.findElement(By.cssSelector("input[type=submit]"));

        // Pobranie aktualnej daty
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String currentDateText = String.format("%d-%02d-%02d", year, month, dayOfMonth);

        // Pobranie daty za pięć dni
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        int futureYear = calendar.get(Calendar.YEAR);
        int futureMonth = calendar.get(Calendar.MONTH) + 1;
        int futureDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String futureDateText = String.format("%d-%02d-%02d", futureYear, futureMonth, futureDayOfMonth);

        // Wprowadzenie daty do pierwszego pola
        arrivalDateInput.sendKeys(currentDateText);

        // Wprowadzenie daty do drugiego pola
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", leaveDateInput, futureDateText);

        submitButton.click();

        String arrivalDate = driver.findElement(By.id("arrival")).getText().toString();
        String leaveDate = driver.findElement(By.id("leave")).getText().toString();

        String expectedArrivalDate = String.format("%d-%02d-%02d", year, month, dayOfMonth);

        Assert.assertEquals(arrivalDate,expectedArrivalDate);
        Assert.assertEquals(leaveDate,futureDateText);
    }

    @Test
    public void testJQueryCalendar() {
        driver.get("https://testerautomatyczny2023.github.io/CookieTesting/jquerycalendar.html");
        WebElement dateInput = driver.findElement(By.id("datepicker"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].value = '2024/12/12'", dateInput);

        String dateInputValue = dateInput.getAttribute("value");

        Assert.assertEquals(dateInputValue,"2024/12/12");
    }

    @Test
    public void testBasicAuth() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        String authMessage = driver.findElement(By.cssSelector(".example p")).getText().toString();
        Assert.assertEquals(authMessage,"Congratulations! You must have the proper credentials.");
    }

}
