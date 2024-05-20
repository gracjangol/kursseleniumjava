package saucedemo.linear;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import saucedemo.base.BaseTest;

public class testRelativeLocators extends BaseTest {
    @Test
    public void testExcOne() {
        driver.get("https://testerautomatyczny2023.github.io/CookieTesting/relativeform.html");
        String emailInputText = driver.findElement(By.id("email")).getAttribute("value");

        Assert.assertEquals(emailInputText, "jan.kowalski@gmail.com");

        WebElement passwordInput = driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.id("email")));
        String passwordInputText = passwordInput.getAttribute("value");

        Assert.assertEquals(passwordInputText, "Tajne123");
    }

    @Test
    public void testExcTwo() {
        driver.get("https://testerautomatyczny2023.github.io/CookieTesting/relativeform.html");
        String passwordInputText = driver.findElement(By.id("password")).getAttribute("value");

        Assert.assertEquals(passwordInputText, "Tajne123");

        WebElement emailInput = driver.findElement(RelativeLocator.with(By.tagName("input")).above(By.id("password")));
        String emailInputText = emailInput.getAttribute("value");

        Assert.assertEquals(emailInputText, "jan.kowalski@gmail.com");
    }

    @Test
    public void testExcThree() {
        driver.get("https://testerautomatyczny2023.github.io/CookieTesting/relativeform.html");
        String loginButtonText = driver.findElement(By.cssSelector(".login-button")).getText();

        Assert.assertEquals(loginButtonText, "Login");

        WebElement cancelButton = driver.findElement(RelativeLocator.with(By.tagName("button")).toRightOf(By.cssSelector(".login-button")));
        String cancelInputText = cancelButton.getText();

        Assert.assertEquals(cancelInputText, "Cancel");
    }

    @Test
    public void testExcFour() {
        driver.get("https://testerautomatyczny2023.github.io/CookieTesting/relativeform.html");
        String cancelButtonText = driver.findElement(By.cssSelector(".cancel-button")).getText();

        Assert.assertEquals(cancelButtonText, "Cancel");
        WebElement loginButton = driver.findElement(RelativeLocator.with(By.tagName("button")).toLeftOf(By.cssSelector(".cancel-button")));
        String loginButtonText = loginButton.getText();

        Assert.assertEquals(loginButtonText, "Login");
    }

    @Test
    public void testExcFive() {
        driver.get("https://testerautomatyczny2023.github.io/CookieTesting/relativebuttons.html");
        WebElement middleButton = driver.findElement(By.id("middle"));

        WebElement upperMiddle = driver.findElement(RelativeLocator.with(By.tagName("button")).above(middleButton));
        upperMiddle.click();
        Assert.assertEquals(upperMiddle.getText(),"2");

        WebElement belowMiddle = driver.findElement(RelativeLocator.with(By.tagName("button")).below(middleButton));
        belowMiddle.click();
        Assert.assertEquals(belowMiddle.getText(),"8");

        WebElement leftMiddle = driver.findElement(RelativeLocator.with(By.tagName("button")).toLeftOf(middleButton));
        leftMiddle.click();
        Assert.assertEquals(leftMiddle.getText(),"4");

        WebElement rightMiddle = driver.findElement(RelativeLocator.with(By.tagName("button")).toRightOf(middleButton));
        rightMiddle.click();
        Assert.assertEquals(rightMiddle.getText(),"6");

        WebElement upperRight = driver.findElement(RelativeLocator.with(By.tagName("button")).above(middleButton).toRightOf(middleButton));
        upperRight.click();
        Assert.assertEquals(upperRight.getText(),"3");

        WebElement upperLeft = driver.findElement(RelativeLocator.with(By.tagName("button")).above(middleButton).toLeftOf(middleButton));
        upperLeft.click();
        Assert.assertEquals(upperLeft.getText(),"1");

        WebElement belowRight = driver.findElement(RelativeLocator.with(By.tagName("button")).below(middleButton).toRightOf(middleButton));
        belowRight.click();
        Assert.assertEquals(belowRight.getText(),"9");

        WebElement belowLeft = driver.findElement(RelativeLocator.with(By.tagName("button")).below(middleButton).toLeftOf(middleButton));
        belowLeft.click();
        Assert.assertEquals(belowLeft.getText(),"7");
    }

    @Test
    public void testExcSeven() {
        driver.get("https://testerautomatyczny2023.github.io/CookieTesting/relativeform.html");
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement cancelButton = driver.findElement(By.cssSelector(".cancel-button"));

        Assert.assertEquals(emailInput.getAttribute("value"),"jan.kowalski@gmail.com");
        WebElement loginButton = driver.findElement(RelativeLocator.with(By.cssSelector(".login-button")).below(emailInput).below(passwordInput).toLeftOf(cancelButton));

        Assert.assertEquals(loginButton.getText(),"Login");
    }

    @Test
    public void testExcEightRelativeLocator() {
        driver.get("https://testerautomatyczny2023.github.io/CookieTesting/relativeone.html");
        WebElement firstButton = driver.findElement(By.id("button1"));
        WebElement breakElement = driver.findElement(By.tagName("br"));
        firstButton.click();

        WebElement firstButtonText = driver.findElement(RelativeLocator.with(By.tagName("div")).near(firstButton).below(breakElement));
        Assert.assertEquals(firstButtonText.getText(),"Tekst dla przycisku 1");
    }

    @Test (dataProvider = "test")
    public void testExcEightDataProvider(String buttonID,String expectedText) {
        driver.get("https://testerautomatyczny2023.github.io/CookieTesting/relativeone.html");
        WebElement button = driver.findElement(By.id(buttonID));
        WebElement breakElement = driver.findElement(By.tagName("br"));

        button.click();

        WebElement buttonText = driver.findElement(RelativeLocator.with(By.tagName("div")).below(breakElement).near(button));
        Assert.assertEquals(buttonText.getText(),expectedText);

    }

    @DataProvider (name = "test")
    public Object[][] stringData(){
        return new Object[][] {
                {"button1", "Tekst dla przycisku 1"},
                {"button2", "Tekst dla przycisku 2"},
                {"button3", "Tekst dla przycisku 3"}
        };
    }
}