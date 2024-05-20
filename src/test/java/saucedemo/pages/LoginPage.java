package saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(id = "user-name")
    WebElement userInput;
    @FindBy( id = "password")
    WebElement passwordInput;
    @FindBy(id = "login-button")
    WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage setUser(String username) {
        userInput.sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.sendKeys("secret_sauce");
        return this;
    }

    public InventoryPage submit() {
        loginButton.click();
        return new InventoryPage(driver);
    }

    public InventoryPage login(String username, String password) {
        setUser(username);
        setPassword(password);
        return submit();
    }
}
