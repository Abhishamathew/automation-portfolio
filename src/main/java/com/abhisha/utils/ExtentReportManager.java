package com.abhisha.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/TestReport.html");

            reporter.config().setReportName("Automation Portfolio Test Report");
            reporter.config().setDocumentTitle("Test Results");
            reporter.config().setTheme(Theme.STANDARD);
            reporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Project", "Automation Portfolio");
            extent.setSystemInfo("Tester", "Abhisha");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Site", "automationexercise.com");
        }
        return extent;
    }
}