package org.example.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private final By iframe = By.id("iframe");

    private final By womenCategoryLink = By.cssSelector("#menu-primary-items a[href*='product-category/women']");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean switchToMainIframe() {
        return switchToFrame(iframe);
    }

    public boolean clickWomenCategoryLink() {
        return clickOnElement(womenCategoryLink);
    }
}
