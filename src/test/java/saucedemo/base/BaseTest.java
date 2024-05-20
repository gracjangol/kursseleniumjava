package saucedemo.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import saucedemo.screenshot.ScreenshotUtil;


import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected static final Logger LOG =
            LogManager.getLogger(BaseTest.class);
    long startTime;
    @BeforeClass
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestResult myTest) {
        LOG.info("Test " + myTest.getMethod().getMethodName() +
                " started");
        startTime = System.currentTimeMillis();
    }

    @AfterMethod
    public void methodTearDown(ITestResult result) throws IOException {
        long endTime = System.currentTimeMillis();
        LOG.info("Upłyneło czasu {}ms ", endTime - startTime);


        if(result.getStatus() != ITestResult.SUCCESS){
            String screenshotName = "screenshots/" + result.getName() + result.getEndMillis() + ".png";
            ScreenshotUtil.takeScreenshot(driver, screenshotName);
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }


}
