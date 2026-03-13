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
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "var iframes = document.querySelectorAll('iframe');" +
                            "iframes.forEach(function(el) { el.remove(); });" +
                            "var ads = document.querySelectorAll('ins, .adsbygoogle, [id*=\"google_ads\"], [id*=\"aswift\"], .google-auto-placed, [id*=\"ad_\"]');" +
                            "ads.forEach(function(el) { el.remove(); });"
            );
            System.out.println("Ads and popups removed");
        } catch (Exception e) {
            System.out.println("No ads to remove");
        }
    }


}