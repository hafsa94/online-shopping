package org.example.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.pageObjects.HomePage;
import org.example.pageObjects.ProductDetailPage;
import org.example.utils.CucumberContext;
import org.testng.Assert;

public class HomePageStepDefs {

    private final CucumberContext cucumberContext;
    private final HomePage homePage;
    private final ProductDetailPage productDetailPage;

    public HomePageStepDefs(CucumberContext cucumberContext) {
        this.cucumberContext = cucumberContext;
        homePage = cucumberContext.pageObjectManager.getHomePage();
        productDetailPage = cucumberContext.pageObjectManager.getProductDetailPage();
    }

    @Given("user goes to the home page")
    public void userGoesToTheHomePage() {
        homePage.webPageNavigation("to", "https://demo.competethemes.com/");
    }

    @Given("user goes to the home page of title {string}")
    public void userGoesToTheHomePageOfTitle(String title) {
        Assert.assertEquals(homePage.getWebPageTitle(), title);
        Assert.assertTrue(homePage.switchToMainIframe());
    }

    @And("user goes to the women page")
    public void userGoesToTheWomenPage() {
        Assert.assertTrue(homePage.clickWomenCategoryLink());
    }
}
