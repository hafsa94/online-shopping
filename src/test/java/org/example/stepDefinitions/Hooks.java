package org.example.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.utils.CucumberContext;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.example.utils.ScreenRecorderUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    Logger log = Logger.getLogger(Hooks.class);

    public CucumberContext cucumberContext;

    public Hooks(CucumberContext cucumberContext) {
        this.cucumberContext = cucumberContext;
    }

    @Before
    public void testRecorder(Scenario scenario) throws Exception {
        ScreenRecorderUtil.startRecording(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        WebDriver driver = cucumberContext.baseTest.getWebDriver();
        if (scenario.isFailed()) {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
            String timestamp = sdf.format(now);
            String fileName = String.format("%1s%2s%3s-%4s.png",
                    System.getProperty("user.dir"),
                    "/src/test/resources/screenshots/",
                    scenario.getName().replaceAll(" ", "-"),
                    timestamp);
            log.info("Screenshot File Name: " + fileName);
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(fileName));
        }
        ScreenRecorderUtil.stopRecording();
        driver.quit();
        log.info("Driver has been closed successfully");
    }
}
