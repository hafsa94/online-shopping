package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class CucumberContext {

    public WebDriver driver;
    public WebDriverWait wait;
    public TestBase baseTest;
    public PageObjectManager pageObjectManager;

    public CucumberContext() throws IOException {
        baseTest = new TestBase();
        driver = baseTest.getWebDriver();
        wait = baseTest.getTimeout();
        pageObjectManager = new PageObjectManager(driver, wait);
    }
}
