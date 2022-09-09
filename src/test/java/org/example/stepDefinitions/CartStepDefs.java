package org.example.stepDefinitions;

import io.cucumber.java.Transpose;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageObjects.BasePage;
import org.example.pageObjects.CartPage;
import org.example.utils.CacheUtils;
import org.example.utils.CucumberContext;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.math.BigDecimal;
import java.security.Key;
import java.util.List;
import java.util.Map;

public class CartStepDefs {

    public Logger log = Logger.getLogger(BasePage.class);

    private final CucumberContext cucumberContext;
    private final CartPage cartPage;

    public CartStepDefs(CucumberContext cucumberContext) {
        this.cucumberContext = cucumberContext;
        cartPage = cucumberContext.pageObjectManager.getCartPage();
    }

    @Then("verify product details are same on the product detail and cart page")
    public void verifyProductDetailsAreSameOnTheProductDetailAndCartPage() {
        Map<String, String> cartProductDetail = cartPage.getCartProductTitlePriceQuantity();
        Assert.assertEquals(CacheUtils.getValue("productDetail"), cartProductDetail);
        log.info("cartProductDetail -----> " + cartProductDetail);
        log.info("productDetail ----> " + CacheUtils.getValue("productDetail"));
        // Calculate subtotal on the cart page
        String priceStr = cartProductDetail.get("price").replaceAll("\\D", "");
        String subtotalStr = cartPage.getCartProductSubtotal().replaceAll("\\D", "");
        BigDecimal price = new BigDecimal(priceStr);
        BigDecimal subtotal = new BigDecimal(subtotalStr);
        Assert.assertEquals(subtotal, price.multiply(new BigDecimal(cartProductDetail.get("quantity"))));
    }

    @When("user changes the address as following data")
    public void userChangesTheAddressAsFollowingData(@Transpose Map<String, String> address) {
        Assert.assertTrue(cartPage.clickChangeAddressLink());
        Assert.assertTrue(cartPage.clickCountryDropdown());
        Assert.assertTrue(cartPage.searchItemsInDropdown(address.get("country") + Keys.ARROW_DOWN + Keys.ENTER));
        Assert.assertTrue(cartPage.clickStateDropdown());
        Assert.assertTrue(cartPage.searchItemsInDropdown(address.get("state") + Keys.ENTER));
        Assert.assertTrue(cartPage.enterCity(address.get("city")));
        Assert.assertTrue(cartPage.enterPinCode(address.get("pinCode")));
        Assert.assertTrue(cartPage.clickUpdateShippingAddressButton());
    }

    @Then("user clicks the proceed to checkout button")
    public void userClicksTheProceedToCheckoutButton() {
        Assert.assertTrue(cartPage.isShippingAddressUpdateButtonInVisible());
        Assert.assertTrue(cartPage.clickProceedToCheckoutButton());
    }
}
