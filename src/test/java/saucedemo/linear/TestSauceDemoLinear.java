package saucedemo.linear;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import saucedemo.base.BaseTest;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class TestSauceDemoLinear extends BaseTest {
    @Test
    public void testAddOneProductToCart() {
        driver.get("https://www.saucedemo.com");
        //go to login page
        //set username
        WebElement userInput = driver.findElement(By.id("user-name"));
        userInput.sendKeys("standard_user");
        //set password
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        //submit login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        //go to inventory page
        //add first item to cart
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        //go to cart
        WebElement cartLink = driver.findElement(By.cssSelector("a.shopping_cart_link"));
        cartLink.click();
        //check one element is in cart
        List<WebElement> items = driver.findElements(By.cssSelector("div.cart_item"));
        assertEquals(items.size(), 1);
    }

    @Test
    public void testAddOneProductFromProductDetailsToCart(){

    }
}
