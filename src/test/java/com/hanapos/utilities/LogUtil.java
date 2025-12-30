package com.hanapos.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import com.aventstack.extentreports.ExtentTest;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.WebDriver;

public class LogUtil extends TestBaseClass {
    private static ThreadLocal<StringBuilder> logThreadLocal = ThreadLocal.withInitial(StringBuilder::new);

    public static void log(String message) {
        logThreadLocal.get().append(message).append("\n");
    }

    public static String getLogs() {
        return logThreadLocal.get().toString();
    }

    public static void clearLogs() {
        logThreadLocal.remove();
    }


    public static void saveBrowserLogs(WebDriver driver, String filePath) {
        LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
        List<LogEntry> consoleLogs = logEntries.getAll();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (LogEntry consoleLog : consoleLogs) {
                writer.write(consoleLog.getMessage());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
