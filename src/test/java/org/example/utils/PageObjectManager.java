package org.example.utils;

import org.example.pageObjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectManager {

    private final WebDriver driver;
    private final WebDriverWait wait;

    PageObjectManager(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public HomePage getHomePage() {
        return new HomePage(driver, wait);
    }

    public WomenListsPage getListsPage() {
        return new WomenListsPage(driver, wait);
    }

    public ProductDetailPage getProductDetailPage() {
        return new ProductDetailPage(driver, wait);
    }

    public CartPage getCartPage() {
        return new CartPage(driver, wait);
    }
}
