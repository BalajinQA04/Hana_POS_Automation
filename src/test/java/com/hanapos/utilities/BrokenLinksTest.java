package com.hanapos.utilities;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.io.ByteArrayInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class BrokenLinksTest extends TestBaseClass {
    // Store info for post-processing or reporting
    private static final List<String> brokenLinksInfo = new ArrayList<>();

    public BrokenLinksTest() {
        PageFactory.initElements(getDriver(), this);
    }

    //  @Step("Check and log broken links on the page")
    public static void checkBrokenLinksOnPage() {
        List<WebElement> links = getDriver().findElements(By.tagName("a"));
        brokenLinksInfo.clear();
        int brokenCount = 0;

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                boolean isBroken = checkLink(url, brokenCount + 1);
                if (isBroken) brokenCount++;
            }
        }

        // Attach the summary of broken links to Allure report
        if (!brokenLinksInfo.isEmpty()) {
            Allure.addAttachment("Broken Links Summary",
                    String.join(System.lineSeparator(), brokenLinksInfo));
        } else {
            Allure.addAttachment("Broken Links Summary", "No broken links found on this page.");
        }
    }

    /**
     * Returns true if broken, false if OK
     */
    //   @Step("Check link: {0}")
    public static boolean checkLink(String url, int brokenIndex) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode >= 400) {
                logBrokenLink(url, responseCode, brokenIndex);
                return true;
            } else {
                logWorkingLink(url, responseCode);
                return false;
            }
        } catch (Exception e) {
            // If error occurs, consider it broken
            logBrokenLink(url, 500, brokenIndex);
            return true;
        }
    }

    // This will attach screenshot to Allure for each broken link
    @Step("Log broken link: {0} with response code: {1}")
    public static void logBrokenLink(String url, int responseCode, int brokenIndex) {
        String msg = "Broken link: " + url + " with response code: " + responseCode;
        Allure.step(msg);
        brokenLinksInfo.add(msg);

        // Attach screenshot to Allure for each broken link
        try {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Broken Link Screenshot #" + brokenIndex, "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception ex) {
            Allure.step("Failed to capture screenshot for broken link: " + ex);
        }
    }

    @Step("Log working link: {0} with response code: {1}")
    public static void logWorkingLink(String url, int responseCode) {
        Allure.step("Working link: " + url + " with response code: " + responseCode);
    }
}
