package org.example.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    public Logger log = Logger.getLogger(BasePage.class);

    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * This method simulates typing into an element, which may set its value.
     *
     * @param locator   used to find the element
     * @param inputText string to send to the element
     * @param index     of the element to be typed. Default index is 0
     * @return true when successfully send input to the element otherwise false
     */
    public boolean enterFieldInput(By locator, String inputText, Integer... index) {
        boolean result = false;
        try {
            int elementIndex = 0;
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            if (index.length > 0) {
                elementIndex = index[0];
            }
            elements.get(elementIndex).clear();
            elements.get(elementIndex).sendKeys(inputText);
            result = true;
        } catch (Exception e) {
            log.error("Failed to send input to the element", e);
        }
        return result;
    }

    /**
     * This method clicks the element on the given index from the list of all the elements with the
     * same locator
     *
     * @param locator used to find the element
     * @param index   of the element to be clicked. Default index is 0
     * @return true when successfully click the element otherwise false
     */
    public boolean clickOnElement(By locator, Integer... index) {
        boolean result = false;
        try {
            int elementIndex = 0;
            List<WebElement> element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            if (index.length > 0) {
                if (index[0] >= 0) {
                    elementIndex = index[0];
                } else {
                    log.error("Index should be equal and greater than 0");
                }
            }
            element.get(elementIndex).click();
            result = true;
        } catch (Exception e) {
            log.error("Failed to click on the element", e);
        }
        return result;
    }

    /**
     * This method returns the visible (i.e. not hidden by CSS) text of this element, including sub-elements
     *
     * @param locator used to find the element
     * @param index   of the element. Default index is 0
     * @return the visible text of this element otherwise empty string
     */
    public String getTextOfElement(By locator, Integer... index) {
        String elementText = "";
        try {
            int elementIndex = 0;
            List<WebElement> element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            if (index.length > 0) {
                elementIndex = index[0];
            }
            elementText = element.get(elementIndex).getText();
        } catch (Exception e) {
            log.error("Failed to get the text of the element", e);
        }
        return elementText;
    }

    /**
     * This method checks whether an element is invisible on the DOM of a page or not
     *
     * @param locator used to find the element
     * @return true when the element is invisible otherwise false
     */
    public boolean checkElementIsInVisible(By locator) {
        boolean result = false;
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            result = true;
        } catch (Exception e) {
            log.error("Element is not visible on the page", e);
        }
        return result;
    }

    /**
     * This method checks whether the element is present and then move to that element
     *
     * @param type used to define type fo navigation to be performed
     * @param url  is an optional parameter required to navigate to an url
     * @return true when navigation is completed
     */
    public boolean webPageNavigation(String type, String... url) {
        boolean result = false;
        try {
            switch (type) {
                case "back":
                    driver.navigate().back();
                    break;
                case "forward":
                    driver.navigate().forward();
                    break;
                case "refresh":
                    driver.navigate().refresh();
                    break;
                case "to":
                    driver.navigate().to(url[0]);
            }
            result = true;
        } catch (Exception e) {
            log.error("Failed to navigate the web page", e);
        }
        return result;
    }

    /**
     * This method returns the title of the current web page
     *
     * @return the title of the current page otherwise empty string
     */
    public String getWebPageTitle() {
        String title = "";
        try {
            title = driver.getTitle();
        } catch (Exception e) {
            log.error("Failed to get the web page title", e);
        }
        return title;
    }

    /**
     * This method returns the url of the current web page
     *
     * @return the url of the current page otherwise empty string
     */
    public String getWebPageCurrentUrl() {
        String url = "";
        try {
            url = driver.getCurrentUrl();
        } catch (Exception e) {
            log.error("Failed to get the web page url", e);
        }
        return url;
    }

    /**
     * This method returns the value of the given attribute of the element
     *
     * @param locator used to find the element
     * @return The attribute/property's current value or null if the value is not set
     */
    public String getElementAttribute(By locator, String attribute) {
        String attributeValue = "";
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            attributeValue = element.getAttribute(attribute);
        } catch (Exception e) {
            log.error("Failed to find the element on the page", e);
        }
        return attributeValue;
    }

    /**
     * This method switches a specific frame
     *
     * @return true when successfully snew tab is opened otherwise false
     */
    public boolean switchToFrame(By locator) {
        boolean switched = false;
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
            switched = true;
        } catch (Exception e) {
            log.error("failed To switch to frame", e);
        }
        return switched;
    }
}




