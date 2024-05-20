package saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage{


    @FindBy(css = "button[id*='add-to-cart']")
    List<WebElement> addToCartButtons;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement addToCartButton;

    @FindBy(css = "a.shopping_cart_link")
    WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    public InventoryPage addOneItemToCart() {
        addToCartButton.click();
        return this;
    }

    public InventoryPage addItemByIndex(int index) {
        addToCartButtons.get(index).click();
        return this;
    }

    public InventoryPage addAllItemsToCart() {
        for (WebElement addToCartButton : addToCartButtons) {
            addToCartButton.click();
        }
        return this;
    }

    public CartPage goToCart() {
        cartLink.click();
        return new CartPage(driver);
    }
}
