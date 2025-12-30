package com.hanapos.utilities;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import io.qameta.allure.Allure;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PerformanceLogger extends TestBaseClass {
    private static Logger logger;
    private static FileHandler fileHandler;
    private static final String LOG_FILE_PATH = "performance_logs/performance.log";

    static {
        try {
            // Setup logger to write to a file
            File logDir = new File("performance_logs");
            if (!logDir.exists()) logDir.mkdirs();

            logger = Logger.getLogger("PerformanceLogger");
            fileHandler = new FileHandler(LOG_FILE_PATH, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PerformanceLogger() {
        PageFactory.initElements(getDriver(), this);
    }

    /**
     * Captures page load time and logs the information.
     *
     * @param stepDescription The step description.
     * @return Page load time in milliseconds.
     */
    public static long capturePageLoadTime(String stepDescription) {
        // Record the start time
        long startTime = System.currentTimeMillis();

        // Wait until the page has fully loaded
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "if (document.readyState === 'complete') {" +
                        "   callback();" +
                        "} else {" +
                        "   window.addEventListener('load', function() { callback(); });" +
                        "}");

        // Record the end time
        long endTime = System.currentTimeMillis();

        // Calculate page load time
        long pageLoadTime = endTime - startTime;

        // Format log message
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String logMessage = String.format("[%s] Step: %s | URL: %s | Page Load Time: %d ms",
                timestamp, stepDescription, getDriver().getCurrentUrl(), pageLoadTime);

        // Log to console and file
        // System.out.println(logMessage);
        logger.info(logMessage);
        return pageLoadTime;
    }

    /**
     * Attach the performance log file to Allure Report.
     */
    public static void attachPerformanceLogsToAllure() {
        try (FileInputStream fis = new FileInputStream(LOG_FILE_PATH)) {
            // Allure.addAttachment("Performance Logs", "text/plain", fis, "log");
            Allure.addAttachment("Performance Logs", new FileInputStream(LOG_FILE_PATH));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
