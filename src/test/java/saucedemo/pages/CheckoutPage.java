package saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    WebElement firstNameInput;
    @FindBy( id = "last-name")
    WebElement lastNameInput;
    @FindBy(id = "postal-code")
    WebElement postalCodeInput;

    @FindBy(id = "continue")
    WebElement continueButton;
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage setFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public CheckoutPage setLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public CheckoutPage setPostalCode(String postalCode) {
        postalCodeInput.sendKeys(postalCode);
        return this;
    }

    public CheckoutPageTwo continueToCheckoutPageTwo() {
        continueButton.click();
        return new CheckoutPageTwo(driver);
    }


}
