package org.example.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageObjects.WomenListsPage;
import org.example.utils.CacheUtils;
import org.example.utils.CucumberContext;
import org.testng.Assert;

public class WomenListsStepDefs {

    private final CucumberContext cucumberContext;
    private final WomenListsPage listsPage;

    public WomenListsStepDefs(CucumberContext cucumberContext) {
        this.cucumberContext = cucumberContext;
        listsPage = cucumberContext.pageObjectManager.getListsPage();
    }

    @When("user selects the filter by rating option")
    public void userSelectsTheFilterByRatingOption() {
        Assert.assertTrue(listsPage.clickOnRatingFilter());
    }

    @Then("user saves the first product name and price of the list page")
    public void userSavesTheProductNamePriceDetailTheListPage() {
        CacheUtils.put("listProductDetail", listsPage.getListProductTitlePrice(0));
    }

    @Then("user click the first product of the list page")
    public void userClicksTheFirstProductOfTheListPage() {
        Assert.assertTrue(listsPage.selectWomenProductAtIndex(0));
    }
}

