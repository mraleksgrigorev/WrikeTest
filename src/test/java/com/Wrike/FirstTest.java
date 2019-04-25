package com.Wrike;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;



public class FirstTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/mrgrigorev/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @Test
    public void mainTest() throws InterruptedException {

        //working with main page
        MainPage mainPage = new MainPage(driver);
        mainPage.getStarted();
        mainPage.setMailField();

        //checking that moved to resend email page
        ResendPage resendPage = new ResendPage(driver);
        Assert.assertTrue(resendPage.isPageOpened());

        //checking resend-email button
        resendPage.resendEmail();
        Assert.assertTrue(resendPage.isEmailSentAgain());

        //checking Q&A survey
        resendPage.submitSurvey();
        Assert.assertFalse(resendPage.isSurveySubmitted());

        //checking twitter-icon
        Assert.assertTrue(resendPage.isIconExists());
        Assert.assertTrue(resendPage.isIconCorrect());

        //checking twitter-link
        Assert.assertTrue(resendPage.openTwitter());
    }

    @After
    public void quit() {
        driver.quit();
    }
}
