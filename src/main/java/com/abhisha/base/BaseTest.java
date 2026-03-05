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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent = ExtentReportManager.getInstance();
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeMethod
    public void setup(java.lang.reflect.Method method) {
        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");

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
}