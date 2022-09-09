package org.example.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class CartPage extends BasePage {

    private final By cartProductTitle = By.cssSelector(".cart_item .product-name");

    private final By cartProductPrice = By.cssSelector(".cart_item .product-price");

    private final By cartProductQuantity = By.cssSelector(".quantity input");

    private final By cartProductSubtotal = By.cssSelector(".cart_item .product-subtotal");

    private final By changeAddressLink = By.className("shipping-calculator-button");

    private final By countryDropdown = By.id("select2-calc_shipping_country-container");

    private final By stateDropdown = By.id("select2-calc_shipping_state-container");

    private final By dropdownSearchInput = By.className("select2-search__field");

    private final By cityInput = By.cssSelector("input#calc_shipping_city");

    private final By pinCodeInput = By.cssSelector("input#calc_shipping_postcode");

    private final By shippingAddressUpdateButton = By.cssSelector("[name='calc_shipping']");

    private final By proceedToCheckoutButton = By.className("checkout-button");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public Map<String, String> getCartProductTitlePriceQuantity() {
        return new HashMap<String, String>() {
            {
                put("title", getTextOfElement(cartProductTitle));
                put("price", getTextOfElement(cartProductPrice));
                put("quantity", getElementAttribute(cartProductQuantity, "value"));
            }
        };
    }

    public String getCartProductSubtotal() {
        return getTextOfElement(cartProductSubtotal);
    }

    public boolean clickChangeAddressLink() {
        return clickOnElement(changeAddressLink);
    }

    public boolean clickCountryDropdown() {
        return clickOnElement(countryDropdown);
    }

    public boolean clickStateDropdown() {
        return clickOnElement(stateDropdown);
    }

    public boolean searchItemsInDropdown(String searchKey) {
        return enterFieldInput(dropdownSearchInput, searchKey);
    }

    public boolean enterCity(String city) {
        return enterFieldInput(cityInput, city);
    }

    public boolean enterPinCode(String pinCode) {
        return enterFieldInput(pinCodeInput, pinCode);
    }

    public boolean clickUpdateShippingAddressButton() {
        return clickOnElement(shippingAddressUpdateButton);
    }

    public boolean clickProceedToCheckoutButton() {
        return clickOnElement(proceedToCheckoutButton);
    }

    public boolean isShippingAddressUpdateButtonInVisible() {
        return checkElementIsInVisible(shippingAddressUpdateButton);
    }
}
