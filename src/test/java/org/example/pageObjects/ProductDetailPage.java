package org.example.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailPage extends BasePage {

    private final By productTitle = By.cssSelector("h1.product_title");

    private final By productPrice = By.cssSelector(".summary .woocommerce-Price-amount");

    private final By quantityInput = By.cssSelector(".quantity input");

    private final By addToCartButton = By.className("single_add_to_cart_button");

    private final By viewCartButton = By.cssSelector(".woocommerce-message a");

    public ProductDetailPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean enterItemQuantity(String quantity) {
        return enterFieldInput(quantityInput, quantity);
    }

    public boolean clickAddToCartButton() {
        return clickOnElement(addToCartButton);
    }

    public boolean clickViewCartButton() {
        return clickOnElement(viewCartButton);
    }

    public Map<String, String> getProductTitlePrice() {
        return new HashMap<String, String>() {
            {
                put("title", getTextOfElement(productTitle));
                put("price", getTextOfElement(productPrice));
            }
        };
    }
}
