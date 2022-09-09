package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;
    public Properties properties;

    public Properties getAllProperties() throws IOException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/application.properties");
        Properties prop = new Properties();
        prop.load(file);
        return prop;
    }

    public WebDriver getWebDriver() throws IOException {
        properties = getAllProperties();
        String baseUrl = properties.getProperty("baseUrl");
        String browserProperty = properties.getProperty("browser");
        String browserSystem = System.getProperty("browser");

        String browser = browserSystem != null ? browserSystem : browserProperty;

        if (driver == null) {
            DriverManager dm = new DriverManager();
            driver = dm.getDriver(browser);
            driver.manage().window().fullscreen();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get(baseUrl);
        }
        return driver;
    }

    public WebDriverWait getTimeout() {
        int timeout = Integer.parseInt(properties.getProperty("timeout"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait;
    }
}
