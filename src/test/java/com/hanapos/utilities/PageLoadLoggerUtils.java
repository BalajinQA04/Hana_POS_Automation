package com.hanapos.utilities;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PageLoadLoggerUtils {

    private static final int MAX_LOGS_PER_TEST = 100;
    private static final ThreadLocal<LinkedList<String>> pageLoadLogs =
            ThreadLocal.withInitial(LinkedList::new);
    private static final String LOG_DIR = "reports/page-load-logs";

    public static void beforeEachTest(String testName) {
        pageLoadLogs.get().clear();
        pageLoadLogs.get().add("Test Case: " + testName + "\n----------------------------------------");
    }

    public static void logPageLoad(String stepName, Runnable navigation) {
        long start = System.currentTimeMillis();
        boolean success = false;
        String errorDetails = "";

        try {
            navigation.run();
            success = true;
        } catch (Exception e) {
            errorDetails = e.toString();
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            long loadTime = end - start;
            double loadTimeSec = loadTime / 1000.0;

            String status = success ? "✅ SUCCESS" : "❌ FAILED";
            String log = String.format(
                    "\nStep: %-30s\nStatus: %s\nThread: %d\nTime Taken: %d ms (%.2f s)\n%s\n",
                    stepName, status, Thread.currentThread().getId(), loadTime, loadTimeSec,
                    success ? "" : "Error: " + errorDetails
            );

            LinkedList<String> logs = pageLoadLogs.get();
            if (logs.size() >= MAX_LOGS_PER_TEST) {
                logs.removeFirst(); // Prevent memory overflow
            }
            logs.add(log);
        }
    }

    public static void attachLogsToAllure(String testName) {
        File dir = new File(LOG_DIR);
        if (!dir.exists()) dir.mkdirs();

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File logFile = new File(dir, testName + "_" + timestamp + "_PageLoadLog.txt");

        try (FileWriter fw = new FileWriter(logFile)) {
            for (String log : pageLoadLogs.get()) {
                fw.write(log);
            }
        } catch (IOException e) {
            Allure.addAttachment("Page Load Summary", "Unable to write logs: " + e.getMessage());
            return;
        }

        try (FileInputStream fis = new FileInputStream(logFile)) {
            Allure.addAttachment("📄 Page Load Summary - " + testName, fis);
        } catch (IOException e) {
            Allure.addAttachment("Page Load Summary", "Unable to attach logs: " + e.getMessage());
        }

        // 🔐 Clean up ThreadLocal to avoid memory leaks
        pageLoadLogs.remove();
    }


/*    private static final ThreadLocal<List<String>> pageLoadLogs = ThreadLocal.withInitial(ArrayList::new);
    private static final String LOG_DIR = "reports/page-load-logs";

    public static void beforeEachTest(String testName) {
        pageLoadLogs.get().clear();
        pageLoadLogs.get().add("Test Case: " + testName + "\n----------------------------------------");
    }

    public static void logPageLoad(String stepName, Runnable navigation) {
        long start = System.currentTimeMillis();
        boolean success = false;
        String errorDetails = "";

        try {
            navigation.run();
            success = true;
        } catch (Exception e) {
            errorDetails = e.toString();
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            long loadTime = end - start;
            double loadTimeSec = loadTime / 1000.0;

            String status = success ? "✅ SUCCESS" : "❌ FAILED";
            String log = String.format(
                    "\nStep: %-30s\nStatus: %s\nThread: %d\nTime Taken: %d ms (%.2f s)\n%s\n",
                    stepName, status, Thread.currentThread().getId(), loadTime, loadTimeSec,
                    success ? "" : "Error: " + errorDetails
            );

            pageLoadLogs.get().add(log);
        }
    }

    public static void attachLogsToAllure(String testName) {
        try {
            File dir = new File(LOG_DIR);
            if (!dir.exists()) dir.mkdirs();

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File logFile = new File(dir, testName + "_" + timestamp + "_PageLoadLog.txt");

            try (FileWriter fw = new FileWriter(logFile)) {
                for (String log : pageLoadLogs.get()) {
                    fw.write(log + "\n");
                }
            }

            try (FileInputStream fis = new FileInputStream(logFile)) {
                Allure.addAttachment("📄 Page Load Summary - " + testName, fis);
            }

        } catch (IOException e) {
            Allure.addAttachment("Page Load Summary", "Unable to attach logs: " + e.getMessage());
        }
    }*/
}
