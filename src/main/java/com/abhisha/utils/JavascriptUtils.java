package com.abhisha.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtils {

    WebDriver driver;
    JavascriptExecutor js;

    public JavascriptUtils(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void click(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void removePopups() {
        js.executeScript(
                "var iframes = document.querySelectorAll('iframe');" +
                        "iframes.forEach(function(iframe) { iframe.remove(); });"
        );
    }
}