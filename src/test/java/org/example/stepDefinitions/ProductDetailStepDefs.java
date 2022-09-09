package org.example.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageObjects.ProductDetailPage;
import org.example.utils.CacheUtils;
import org.example.utils.CucumberContext;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.Map;

public class ProductDetailStepDefs {

    Logger log = Logger.getLogger(ProductDetailStepDefs.class);

    private final CucumberContext cucumberContext;
    private final ProductDetailPage productDetailPage;
    private Map<String, String> productDetail;

    public ProductDetailStepDefs(CucumberContext cucumberContext) {
        this.cucumberContext = cucumberContext;
        productDetailPage = cucumberContext.pageObjectManager.getProductDetailPage();
    }

    @When("user adds item to the cart of quantity {string}")
    public void userAddsItemToTheCartOfQuantity(String quantity) {
        Assert.assertTrue(productDetailPage.enterItemQuantity(quantity));
        Assert.assertTrue(productDetailPage.clickAddToCartButton());
        productDetail.put("quantity", quantity);
        CacheUtils.put("productDetail", productDetail);
    }

    @Then("verify product details are same on the product list and detail page")
    public void verifyProductDetailsAreSameOnTheProductListAndDetailPage() {
        productDetail = productDetailPage.getProductTitlePrice();
        Assert.assertEquals(CacheUtils.getValue("listProductDetail"), productDetail);
    }

    @And("user clicks the view cart button")
    public void userClicksTheViewCartButton() {
        Assert.assertTrue(productDetailPage.clickViewCartButton());
    }
}
