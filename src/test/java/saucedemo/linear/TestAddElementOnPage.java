package saucedemo.linear;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import saucedemo.base.BaseTest;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class TestAddElementOnPage extends BaseTest {
    @CacheLookup
    @FindBy(css = "button[onclick='addElement()']")
    WebElement button;


    @FindBy(css = "button.added-manually")
    List<WebElement> buttonsAddedManually;
    @Test
    public void testAddElementOnPage() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        PageFactory.initElements(driver, this);

        assertEquals(buttonsAddedManually.size(), 0);

        button.click();

        assertEquals(buttonsAddedManually.size(), 1);

        for(int i = 1; i <= 9; i++) {
            button.click();
        }

        assertEquals(buttonsAddedManually.size(), 10);

        buttonsAddedManually.get(0).click();

        assertEquals(buttonsAddedManually.size(),9);

    }
}
