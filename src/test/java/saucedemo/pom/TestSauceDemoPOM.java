package saucedemo.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.base.BaseTest;
import saucedemo.pages.*;
import saucedemo.tables.CartItem;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class TestSauceDemoPOM extends BaseTest {
    @Test
    public void testAddOneProductToCart() {
        driver.get("https://www.saucedemo.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .login("standard_user", "secret_sauce")
                .addOneItemToCart()
                .addItemByIndex(1)
                .addItemByIndex(2)
                .goToCart();

        CartPage cartPage = new CartPage(driver);
        List<WebElement> items = cartPage.getItems();

        assertEquals(items.size(), 3);

        List<CartItem> cartItems = cartPage.getCartItems();
        cartItems.get(1).clickRemoveButton();
        cartItems = cartPage.getCartItems();
        double sum = cartItems.stream().mapToDouble(CartItem::getPrice).sum();
        cartItems.stream().forEach( item ->
                LOG.info("{} cena produktu {}", item.getName(), item.getPrice())
        );
        LOG.info("suma cen produktów w koszyku:{}", sum);
        cartItems.get(0).clickInventoryItemLink();
    }

    @Test
    public void testAddAllProductToCartAndCountSum() {
        driver.get("https://www.saucedemo.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce")
                .addAllItemsToCart()
                .goToCart();
        CartPage cartPage = new CartPage(driver);
        List<WebElement> items = cartPage.getItems();

        assertEquals(items.size(), 6);

        List<CartItem> cartItems = cartPage.getCartItems();
        double sum = cartItems.stream().mapToDouble(CartItem::getPrice).sum();
        cartItems.stream().forEach( item ->
                LOG.info("{} cena produktu {}", item.getName(), item.getPrice())
        );
        LOG.info("suma cen produktów w koszyku:{}", sum);
        assertEquals(sum, 129.94);

    }

    @Test
    public void testPurchaseFlowWithOrderPlacing() {
        driver.get("https://www.saucedemo.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce")
                .addAllItemsToCart()
                .goToCart();
        CartPage cartPage = new CartPage(driver);
        List<WebElement> items = cartPage.getItems();

        assertEquals(items.size(), 6);

        List<CartItem> cartItems = cartPage.getCartItems();
        double sum = cartItems.stream().mapToDouble(CartItem::getPrice).sum();
        cartItems.stream().forEach( item ->
                LOG.info("{} cena produktu {}", item.getName(), item.getPrice())
        );
        LOG.info("suma cen produktów w koszyku:{}", sum);
        assertEquals(sum, 129.94);

        cartPage.goCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.setFirstName("Gracjan");
        checkoutPage.setLastName("Gołębiewski");
        checkoutPage.setPostalCode("84-200");

        checkoutPage.continueToCheckoutPageTwo();

        CheckoutPageTwo checkoutPageTwo = new CheckoutPageTwo(driver);

        Assert.assertEquals(checkoutPageTwo.getSubtotalValue(),"Item total: $129.94");
        Assert.assertEquals(checkoutPageTwo.getTotalValue(),"Total: $140.34");

        checkoutPageTwo.goToThankYouPage();

        ThankYouPage thankYouPage = new ThankYouPage(driver);
        Assert.assertTrue(thankYouPage.thankYouMessageVisibility());
    }
}
