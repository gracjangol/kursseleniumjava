package saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPageTwo extends BasePage {

    @FindBy(css = ".summary_subtotal_label")
    WebElement subtotalValue;

    @FindBy(css = ".summary_total_label")
    WebElement totalValue;

    @FindBy(id = "finish")
    WebElement finishButton;
    public CheckoutPageTwo(WebDriver driver) {
        super(driver);
    }

    public String getSubtotalValue() {
        String subtotalValueText = subtotalValue.getText();
        return subtotalValueText;
    }

    public String getTotalValue() {
        String totalValueText = totalValue.getText();
        return totalValueText;
    }

    public ThankYouPage goToThankYouPage() {
        finishButton.click();
        return new ThankYouPage(driver);
    }
}
