package com.abhisha.base;

import com.abhisha.utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait; // ← added
    protected static ExtentReports extent = ExtentReportManager.getInstance();
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();



    @BeforeMethod
    public void setUp(java.lang.reflect.Method method) {
        ExtentTest extentTest = ExtentReportManager.getInstance()
                .createTest(method.getName());
        test.set(extentTest);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        if (System.getenv("HEADLESS") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--remote-debugging-port=9222");
            System.out.println("Running in HEADLESS mode");
        }

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.ads", 2);
        prefs.put("profile.default_content_setting_values.popups", 2);
        options.setExperimentalOption("prefs", prefs);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // ← added

        driver.get("https://automationexercise.com");
        removeAds();

        test.get().info("Browser opened and navigated to automationexercise.com");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = takeScreenshot(result.getName());
            test.get().fail("Test Failed: " + result.getThrowable().getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(
                            "../" + screenshotPath
                    ).build()
            );
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.get().pass("Test Passed Successfully");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.get().skip("Test Skipped");
        }
        driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
        System.out.println("Extent Report saved to: reports/TestReport.html");
    }

    private String takeScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String path = "screenshots/" + testName + "_" + timestamp + ".png";
            FileUtils.copyFile(src, new File(path));
            System.out.println("Screenshot saved: " + path);
            return path;
        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
            return "";
        }
    }

    public void removeAds() {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "var iframes = document.querySelectorAll('iframe');" +
                            "iframes.forEach(function(el) { el.remove(); });" +
                            "var ads = document.querySelectorAll('ins, .adsbygoogle, [id*=\"google_ads\"], [id*=\"aswift\"], .google-auto-placed');" +
                            "ads.forEach(function(el) { el.remove(); });"
            );
        } catch (Exception e) {
            System.out.println("Ad removal skipped");
        }
    }
}