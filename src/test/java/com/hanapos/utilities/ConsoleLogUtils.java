package com.hanapos.utilities;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import io.qameta.allure.Allure;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.util.List;
import java.util.logging.Level;

public class ConsoleLogUtils extends TestBaseClass {

    public static void CaptureConsoleLogs(String testCaseName) {
        try {
            // Capture console logs
            LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
            List<LogEntry> logs = logEntries.getAll();

            // Create a directory for storing console logs
            File logDir = new File("reports/console-logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            // Create a log file for the current test case
            File logFile = new File(logDir, testCaseName + "_consoleLogs.txt");

            // Write only SEVERE logs to the file
            try (FileWriter fileWriter = new FileWriter(logFile)) {
                boolean hasSevereLogs = false;

                for (LogEntry logEntry : logs) {
                    Level level = logEntry.getLevel();
                    if (level == Level.SEVERE) {
                        fileWriter.write(logEntry.getTimestamp() + " " + level + " " + logEntry.getMessage() + "\n\n");
                        hasSevereLogs = true;
                    }
                }

                if (hasSevereLogs) {
                    try (FileInputStream fis = new FileInputStream(logFile)) {
                        Allure.addAttachment("Console Logs - " + testCaseName, fis);
                    }
                } else {
                    Allure.addAttachment("Console Logs for " + testCaseName, "No SEVERE level console logs found.");
                }
            }
        } catch (Exception e) {
            Allure.addAttachment("Console Logs for " + testCaseName, "Error capturing console logs: " + e.getMessage());
        }
    }

    public static void captureConsoleLogsInline(String testCaseName) {
        try {
            LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
            List<LogEntry> logs = logEntries.getAll();
            StringBuilder logBuilder = new StringBuilder();
            boolean hasSevereLogs = false;

            for (LogEntry logEntry : logs) {
                Level level = logEntry.getLevel();
                if (level == Level.SEVERE) {
                    logBuilder.append(logEntry.getTimestamp())
                            .append(" ")
                            .append(level)
                            .append(" ")
                            .append(logEntry.getMessage())
                            .append("\n\n");
                    hasSevereLogs = true;
                }
            }

            if (hasSevereLogs) {
                Allure.addAttachment("Console Logs (SEVERE) for " + testCaseName, logBuilder.toString());
            } else {
                Allure.addAttachment("Console Logs for " + testCaseName, "No SEVERE level console logs found.");
            }
        } catch (Exception e) {
            Allure.addAttachment("Console Logs for " + testCaseName, "Error capturing console logs: " + e.getMessage());
        }
    }
}
