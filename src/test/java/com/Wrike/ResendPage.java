package com.Wrike;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;



public class ResendPage {

    private WebDriver webDriver;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[1]/p[3]/button")
    private
    WebElement resendButton;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form")
    private
    WebElement survey;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/button")
    WebElement survey_button;

    public ResendPage (WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageOpened() {
        return resendButton.isDisplayed();
    }

    public void resendEmail() {
        resendButton.click();
    }

    public boolean isEmailSentAgain() {
        int time = 20;
        WebDriverWait driverWait = new WebDriverWait(webDriver,time);
        driverWait.until(ExpectedConditions.invisibilityOf(resendButton));
        int opacity = Integer.parseInt(resendButton.getCssValue("opacity"));
        return opacity == 0;
    }

    public void submitSurvey() {

        WebElement interest = webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[1]/label[1]/button"));
        interest.click();
        WebElement team = webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[2]/label[1]/button"));
        team.click();
        WebElement business = webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[3]/label[1]/button"));
        business.click();
        survey_button.click();
    }

    public boolean isSurveySubmitted() {
        int time = 20;
        WebDriverWait driverWait = new WebDriverWait(webDriver,time);
        driverWait.until(ExpectedConditions.invisibilityOf(survey));
        return survey_button.isDisplayed();
    }

    public boolean openTwitter() throws InterruptedException {
        WebElement twitterButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]"));
        twitterButton.click();
        //there was a problem with switching tabs - thread.sleep the only decision i've found
        Thread.sleep(5000);
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
        return webDriver.getCurrentUrl().equals("https://twitter.com/wrike");
    }

    public boolean isIconExists() {
        String href = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]/a")).getAttribute("href");
        return href.contains("twitter");
    }

    public boolean isIconCorrect() {
        String imageHref = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[1]/a/*[name()='svg']/*[name()='use']")).getAttribute("xlink:href");
        return imageHref.contains("twitter");
    }


}