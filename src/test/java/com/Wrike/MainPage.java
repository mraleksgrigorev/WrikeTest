package com.Wrike;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {

    private WebDriver webDriver;

    private static final String PAGE_URL = "https://www.wrike.com";

    @FindBy(xpath = "/html/body/div[1]/header/div[3]/div[2]/div/div/div[2]/div/form/button")
    private WebElement getStartedButton;

    @FindBy(xpath="//*[@id=\"modal-pro\"]/form/label[1]/input")
    private WebElement mailField;


    public MainPage (WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(PAGE_URL);
        PageFactory.initElements(webDriver, this);
    }

    public void getStarted() {
        getStartedButton.click();
    }

    public void setMailField() {
        String email = "wrikeTest+wpt@wriketask.qaa";
        mailField.sendKeys(email);
        mailField.submit();
    }

}
