package com.hanapos.utilities;

import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * AllureListener class implements TestNG listeners to integrate with Allure reporting.
 * It captures screenshots, browser console logs, and manages Allure report generation.
 *
 * @Author Balaji N
 */
public class AllureListener extends TestBaseClass implements ITestListener, IExecutionListener, IInvokedMethodListener, ISuiteListener {
    private File historyBackup;
    public static LoggerUtil loggerUtil = new LoggerUtil();

    /**
     * Retrieves the test method name from ITestResult.
     *
     * @param iTestResult TestNG result object
     * @return Name of the test method
     * @Author Balaji N
     */
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    /**
     * Captures a screenshot using WebDriver and attaches it to the Allure report.
     *
     * @return Byte array of the screenshot image
     */
    @Attachment(value = "WebDriver Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        WebDriver driver = getDriver();
        if (driver == null) {
            System.err.println("WebDriver is null. Screenshot capture failed.");
            return null;
        }
        try {
            return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Captures a full-page screenshot using AWT Robot and attaches it to the Allure report.
     *
     * @return Byte array of the screenshot image
     */
    @Attachment(value = "Full Page Screenshot", type = "image/png")
    public byte[] saveFullScreenshot() {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenCapture = robot.createScreenCapture(screenRect);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screenCapture, "png", baos);
            return baos.toByteArray();
        } catch (AWTException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Captures browser console logs and attaches them to the Allure report.
     *
     * @return String representation of the browser console logs
     * @Author Balaji N
     */
    @Attachment(value = "Browser Console Logs", type = "text/plain")
    public String attachBrowserLogs() {
        try {
            // Fetch browser logs
            LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);

            if (logEntries == null || logEntries.getAll().isEmpty()) {
                return "No browser console logs available.";
            }

            // Convert log entries to a single string
            StringBuilder logs = new StringBuilder();
            for (LogEntry logEntry : logEntries) {
                System.out.println(logEntry);
                logs.append("[").append(logEntry.getLevel()).append("] ")
                        .append(logEntry.getMessage()).append("\n");
            }

            return logs.toString();
        } catch (Exception e) {
            return "Error fetching browser console logs: " + e.getMessage();
        }
    }

    @Override
    public void onStart(ISuite suite) {
        // Optional: Log heap at start
    }

    @Override
    public void onFinish(ISuite suite) {
        logHeapUsage("🔚 Test Suite Completed");
    }

    private void logHeapUsage(String context) {
        long used = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);
        long max = Runtime.getRuntime().maxMemory() / (1024 * 1024);

        System.out.println("[" + context + "] ✅ Heap Used: " + used + " MB");
        System.out.println("[" + context + "] 📦 Heap Max : " + max + " MB");

        // Optional: Add to Allure
        io.qameta.allure.Allure.addAttachment(
                "Heap Memory Report - " + context,
                "Used: " + used + " MB\nMax: " + max + " MB"
        );
    }

    @Override
    public void onTestStart(ITestResult result) {
        Allure.step("****** Test started ******\n" + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        backupAllureHistory();
        clearAllureResultsAndReports();
        //clearAllureResultsAndReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        restoreAllureHistory();
        // generateAndOpenAllureReportWithTimestamp();
        generateAndOpenAllureReport();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.step("Test passed: " + getTestMethodName(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.step("Test failed: " + getTestMethodName(result));
        Allure.addAttachment("Failure Stacktrace", getStackTrace(result.getThrowable()));
    }

//    @Override
//    public void onTestFailure(ITestResult result) {
//        Allure.step("❌ Test failed: " + getTestMethodName(result));
//        Allure.addAttachment("Failure Stacktrace", getStackTrace(result.getThrowable()));
//
//        try {
//            if (getDriver() != null) {
//                byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
//                Allure.getLifecycle().addAttachment(
//                        "Failure Screenshot", "image/png", "png", screenshot
//                );
//            } else {
//                attachFallbackScreenshot();
//            }
//        } catch (Exception e) {
//            attachFallbackScreenshot();
//        }
//    }

    private void attachFallbackScreenshot() {
        try {
            byte[] screenshot = saveFullScreenshot(); // your AWT Robot method
            Allure.getLifecycle().addAttachment(
                    "Full Screen Capture", "image/png", "png", screenshot
            );
        } catch (Exception ex) {
            System.err.println("Fallback screenshot failed: " + ex.getMessage());
        }
    }

    // Screenshot attachment method with Allure annotation
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.step("Test skipped: " + getTestMethodName(result));
    }


    /**
     * Attaches a full-page screenshot to the Allure report in case of test failure.
     */
    private void attachScreenshotOnFailure() {
        try {
            Allure.addAttachment("Full Page Screenshot", new ByteArrayInputStream(takeScreenshot()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears previous Allure results and reports directories.
     */
//    public void clearAllureResultsAndReports() {
//        String projectDir = System.getProperty("user.dir") + "\\reports\\";
//        String allureResultsDir = projectDir + "allure-results";
//        String allureReportDir = projectDir + "allure-report";
//
//        deleteDirectory(new File(allureResultsDir));
//        deleteDirectory(new File(allureReportDir));
//        new File(allureResultsDir).mkdirs();
//    }

    /**
     * Recursively deletes a directory and its contents.
     *
     * @param directoryToBeDeleted Directory to be deleted
     */
    public void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    /**
     * Generates the Allure report from results and opens it.
     */
    public void generateAndOpenAllureReport() {
        String projectDir = System.getProperty("user.dir") + "\\reports\\";
        String allureResultsDir = projectDir + "allure-results";
        String allureReportDir = projectDir + "allure-report";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c",
                    "allure", "generate", allureResultsDir, "-o", allureReportDir);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Back up the history folder if it exists
    private void backupAllureHistory() {
        String projectDir = System.getProperty("user.dir") + "\\reports\\";
        File historyDir = new File(projectDir + "allure-report\\history");
        if (historyDir.exists()) {
            historyBackup = new File(projectDir + "allure-history-backup");
            deleteDirectory(historyBackup);
            copyDirectory(historyDir, historyBackup);
        }
    }

    // Restore the history folder before reporting
    private void restoreAllureHistory() {
        String allureResultsDir = System.getProperty("user.dir") + "\\reports\\allure-results\\";
        if (historyBackup != null && historyBackup.exists()) {
            File historyDest = new File(allureResultsDir + "history");
            deleteDirectory(historyDest);
            copyDirectory(historyBackup, historyDest);
        }
    }

    // Create a timestamped allure-report directory
    private void generateAndOpenAllureReportWithTimestamp() {
        String projectDir = System.getProperty("user.dir") + "\\reports\\";
        String allureResultsDir = projectDir + "allure-results";
        String timeStamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
        String allureReportDir = projectDir + "allure-report-" + timeStamp;

        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c",
                    "allure", "generate", allureResultsDir, "-o", allureReportDir);
            pb.inheritIO();
            Process p = pb.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Utility methods: Directory copy & delete
    private void copyDirectory(File sourceDirectory, File destinationDirectory) {
        try {
            org.apache.commons.io.FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // deleteDirectory is your existing recursive delete.

    // Replace your clearAllureResultsAndReports() to NOT delete historyBackup!
    public void clearAllureResultsAndReports() {
        String projectDir = System.getProperty("user.dir") + "\\reports\\";
        deleteDirectory(new File(projectDir + "allure-results"));
        deleteDirectory(new File(projectDir + "allure-report"));
        new File(projectDir + "allure-results").mkdirs();
    }

    private String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

}

