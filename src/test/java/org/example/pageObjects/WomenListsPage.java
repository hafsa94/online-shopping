package org.example.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class WomenListsPage extends BasePage {

    private final By ratingFilter = By.cssSelector(".wc-layered-nav-rating a");

    private final By womenProducts = By.className("woocommerce-LoopProduct-link");

    private final By womenProductsName = By.className("woocommerce-loop-product__title");

    private final By womenProductsPrice = By.className("woocommerce-Price-amount");

    public WomenListsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean clickOnRatingFilter() {
        return clickOnElement(ratingFilter);
    }

    public boolean selectWomenProductAtIndex(int index) {
        return clickOnElement(womenProducts, index);
    }

    public String getWomenProductNameAtIndex(int index) {
        return getTextOfElement(womenProductsName, index);
    }

    public String getWomenProductPriceAtIndex(int index) {
        return getTextOfElement(womenProductsPrice, index);
    }

    public Map<String, String> getListProductTitlePrice(int index) {
        return new HashMap<String, String>() {
            {
                put("title", getWomenProductNameAtIndex(index));
                put("price", getWomenProductPriceAtIndex(index));
            }
        };
    }
}
