package saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ThankYouPage extends BasePage {

    @FindBy(css = ".complete-header")
    WebElement thankYouMessage;
    public ThankYouPage(WebDriver driver) {
        super(driver);
    }

    public boolean thankYouMessageVisibility() {
        if (thankYouMessage.isDisplayed()) {
            return true;
        } else if(!thankYouMessage.isDisplayed())  {
            return false;
        } else {
            return false;
        }
    }
}
