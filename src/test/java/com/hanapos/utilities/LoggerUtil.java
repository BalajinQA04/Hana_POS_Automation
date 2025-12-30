package com.hanapos.utilities;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.network.Network;
import org.openqa.selenium.devtools.v128.network.model.Request;
import org.openqa.selenium.devtools.v128.network.model.RequestId;
import org.openqa.selenium.support.PageFactory;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LoggerUtil extends TestBaseClass {
    public LoggerUtil() {
        PageFactory.initElements(getDriver(), this);
    }

    public static final ThreadLocal<Logger> threadLogger = new ThreadLocal<>();
    public static final ThreadLocal<DevTools> threadDevTools = new ThreadLocal<>();
    private static final int MAX_CAPTURED_LOGS = 100;

    private static final ThreadLocal<Map<RequestId, Long>> threadRequestStartTimes = ThreadLocal.withInitial(HashMap::new);
    private static final ThreadLocal<Map<RequestId, String>> threadRequestUrls = ThreadLocal.withInitial(HashMap::new);
    private static final ThreadLocal<Map<RequestId, String>> threadRequestPayloads = ThreadLocal.withInitial(HashMap::new);
    private static final ThreadLocal<List<String>> threadCapturedLogs = ThreadLocal.withInitial(ArrayList::new);

    public static Logger getLogger(String testCaseName) {
        if (threadLogger.get() == null) {
            String logFilePath = "logs/" + testCaseName + ".log";
            System.setProperty("logFileName", logFilePath);
            threadLogger.set(LogManager.getLogger(testCaseName));
        }
        return threadLogger.get();
    }

    private void writeToLogFile(String testCaseName, String message) {
        String logDirName = "Network_Logs";
        File logDir = new File(logDirName);
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        String logFileName = logDirName + "/" + testCaseName + "_network_logs.txt";
        File logFile = new File(logFileName);

        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startNetworkLogging(String testCaseName) {
        try {
            DevTools devTools = ((ChromeDriver) getDriver()).getDevTools();
            devTools.createSession();
            threadDevTools.set(devTools);

            devTools.send(Network.setCacheDisabled(true));
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

            Map<String, Long> requestStartTimes = new HashMap<>();

            devTools.addListener(Network.requestWillBeSent(), request -> {
                String url = request.getRequest().getUrl();
                requestStartTimes.put(url, System.currentTimeMillis());
            });

            devTools.addListener(Network.responseReceived(), response -> {
                String url = response.getResponse().getUrl();
                int statusCode = response.getResponse().getStatus();
                String resourceType = response.getType().toString();
                RequestId requestId = response.getRequestId();

                Long requestStartTime = requestStartTimes.get(url);
                if (requestStartTime != null && (resourceType.equalsIgnoreCase("XHR") || resourceType.equalsIgnoreCase("Fetch"))) {
                    if (statusCode >= 400) {
                        long responseTime = System.currentTimeMillis() - requestStartTime;
                        DecimalFormat df = new DecimalFormat("#.##");

                        StringBuilder message = new StringBuilder();
                        message.append("🔍 Failed API Call Detected:")
                                .append("\nURL: ").append(url)
                                .append("\nStatus: ").append(statusCode)
                                .append("\nResponse Time: ").append(df.format(responseTime)).append(" ms");

                        try {
                            Network.GetResponseBodyResponse body = devTools.send(Network.getResponseBody(requestId));
                            message.append("\nResponse Body: ").append(body.getBody());
                        } catch (Exception e) {
                            message.append("\nResponse Body: [Unable to fetch: ").append(e.getMessage()).append("]");
                        }

                        writeToLogFile(testCaseName, message.toString());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void cleanupThreadLocals() {
        threadDevTools.remove();
        threadLogger.remove();
        threadRequestStartTimes.remove();
        threadRequestUrls.remove();
        threadRequestPayloads.remove();
        threadCapturedLogs.remove();
    }

    public void attachNetworkLogs(String testCaseName) {
        String logFileName = "Network_Logs/" + testCaseName + "_network_logs.txt";
        File logFile = new File(logFileName);

        try {
            if (logFile.exists()) {
                Allure.addAttachment("Network API Logs - " + testCaseName, new FileInputStream(logFile));
            } else {
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void payment_API_Logger(String testCaseName, Runnable action) {
        try {
            DevTools devTools = ((ChromeDriver) getDriver()).getDevTools();
            devTools.createSession();
            threadDevTools.set(devTools);

            // Thread-safe sets to store unique request URLs
            Set<String> loggedRequestUrls = ConcurrentHashMap.newKeySet();
            Map<RequestId, Long> requestStartTimes = new ConcurrentHashMap<>();
            Map<RequestId, String> requestUrls = new ConcurrentHashMap<>();
            Map<RequestId, String> requestPayloads = new ConcurrentHashMap<>();
            List<String> capturedLogs = Collections.synchronizedList(new ArrayList<>());

            String appBaseUrl = "https://hanafloralpos3.com";

            devTools.send(Network.setCacheDisabled(true));
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

            // Capture filtered requests
            devTools.addListener(Network.requestWillBeSent(), request -> {
                RequestId reqId = request.getRequestId();  // Ensure you’re storing by actual RequestId
                Request req = request.getRequest();
                String url = req.getUrl();

                // Skip irrelevant resources (images, scripts, etc.)
                if (!url.startsWith(appBaseUrl) ||
                        url.matches(".*\\.(js|css|png|jpg|jpeg|gif|svg|woff2?|ttf|ico|map)(\\?.*)?$")) {
                    return;
                }

                // Get POST body safely (non-deprecated)
                String postData = req.getPostData().orElse("");

                // Save necessary details
                requestUrls.put(reqId, url);
                requestStartTimes.put(reqId, System.currentTimeMillis());
                requestPayloads.put(reqId, postData);
            });


            // Capture filtered responses
            devTools.addListener(Network.responseReceived(), response -> {
                RequestId reqId = response.getRequestId();
                String url = requestUrls.getOrDefault(reqId, response.getResponse().getUrl());

                if (!url.startsWith(appBaseUrl)) return;

                // Skip duplicates based on final URL
                if (!loggedRequestUrls.add(url + reqId.toString())) return;

                String payload = requestPayloads.getOrDefault(reqId, "");
                int status = response.getResponse().getStatus();
                long responseSize = response.getResponse().getEncodedDataLength() != null
                        ? (long) response.getResponse().getEncodedDataLength()
                        : 0;
                long startTime = requestStartTimes.getOrDefault(reqId, System.currentTimeMillis());
                long duration = System.currentTimeMillis() - startTime;

                StringBuilder logEntry = new StringBuilder();
                logEntry.append("Request URL: ").append(url).append("\n")
                        .append("Status Code: ").append(status).append("\n")
                        .append("Response Time: ").append(duration).append(" ms\n")
                        .append("Response Size: ").append(responseSize).append(" bytes\n");

                if (!payload.isEmpty()) {
                    logEntry.append("📤 Payload:\n").append(payload).append("\n");
                }

                try {
                    Network.GetResponseBodyResponse body = devTools.send(Network.getResponseBody(reqId));
                    logEntry.append("Response Body:\n").append(body.getBody()).append("\n");
                } catch (Exception e) {
                    logEntry.append("Response Body:\n[Unable to fetch body - ").append(e.getMessage()).append("]\n");
                }

                capturedLogs.add(logEntry.toString());
            });

            // Execute user action
            action.run();

            // Wait extra time to ensure all responses are received
            delayWithGivenTime(3000);

            // Attach logs to Allure
            StringBuilder finalLog = new StringBuilder();
            for (String log : capturedLogs) {
                finalLog.append(log).append("\n----------------------------\n");
            }

            Allure.addAttachment("Payment API Logs - " + testCaseName, finalLog.toString());

        } catch (Exception e) {
            Allure.addAttachment("DevTools Error", "Exception while logging API calls: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanupThreadLocals();
        }
    }

    public void order_Confirmation_API_Logger(String testCaseName, Runnable action) {
        logAPI(testCaseName, action, "Order Confirmation");
    }

    public void create_Proposal_API_Logger(String testCaseName, Runnable action) {
        logAPI(testCaseName, action, "Create Proposal");
    }

    private void logAPI(String testCaseName, Runnable action, String context) {
        DevTools devTools = null;
        try {
            devTools = ((ChromeDriver) getDriver()).getDevTools();
            devTools.createSession();
            threadDevTools.set(devTools);

            ConcurrentMap<RequestId, Long> requestStartTimes = new ConcurrentHashMap<>();
            ConcurrentMap<RequestId, String> requestUrls = new ConcurrentHashMap<>();
            ConcurrentMap<RequestId, String> requestPayloads = new ConcurrentHashMap<>();
            Set<RequestId> processedResponses = Collections.newSetFromMap(new ConcurrentHashMap<>());

            String appBaseUrl = "https://hanafloralpos3.com";

            devTools.send(Network.setCacheDisabled(true));
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

            devTools.addListener(Network.requestWillBeSent(), request -> {
                String url = request.getRequest().getUrl();

                if (!url.startsWith(appBaseUrl) || url.matches(".*\\.(js|css|png|jpg|jpeg|gif|svg|woff2?|ttf|ico|map)(\\?.*)?$")) {
                    return;
                }

                RequestId reqId = request.getRequestId();
                requestUrls.putIfAbsent(reqId, url);
                requestStartTimes.putIfAbsent(reqId, System.currentTimeMillis());
                requestPayloads.putIfAbsent(reqId, request.getRequest().getPostData().orElse(""));
            });

            DevTools finalDevTools = devTools;
            devTools.addListener(Network.responseReceived(), response -> {
                RequestId reqId = response.getRequestId();

                if (processedResponses.contains(reqId)) return; // prevent duplicates
                processedResponses.add(reqId);

                String url = requestUrls.getOrDefault(reqId, response.getResponse().getUrl());
                if (!url.startsWith(appBaseUrl)) return;

                String payload = requestPayloads.getOrDefault(reqId, "");
                int status = response.getResponse().getStatus();
                long responseSize = (long) Optional.ofNullable(response.getResponse().getEncodedDataLength()).orElse(0);
                long startTime = requestStartTimes.getOrDefault(reqId, System.currentTimeMillis());
                long duration = System.currentTimeMillis() - startTime;

                StringBuilder logEntry = new StringBuilder();
                logEntry.append("🔗 Request URL: ").append(url).append("\n")
                        .append("📶 Status Code: ").append(status).append("\n")
                        .append("⏱️ Response Time: ").append(duration).append(" ms\n")
                        .append("📦 Response Size: ").append(responseSize).append(" bytes\n");

                if (!payload.isEmpty()) {
                    logEntry.append("📤 Payload:\n").append(payload).append("\n");
                }

                try {
                    Network.GetResponseBodyResponse body = finalDevTools.send(Network.getResponseBody(reqId));
                    if (body.getBody() != null && !body.getBody().isEmpty()) {
                        logEntry.append("📥 Response Body:\n").append(body.getBody().substring(0, Math.min(body.getBody().length(), 300))).append("\n");
                    }
                } catch (Exception ignored) {
                    logEntry.append("📥 Response Body: [Unable to fetch body]\n");
                }

                logEntry.append("\n----------------------------\n");

                // Stream logs to Allure directly instead of keeping them all in memory
                Allure.addAttachment(context + " API - " + testCaseName + " | " + reqId.toString(), logEntry.toString());
            });

            // Execute test action
            action.run();
            delayWithGivenTime(1500); // wait briefly for final responses

        } catch (Exception e) {
            Allure.addAttachment("❌ DevTools Error", "Exception in " + context + ": " + e.getMessage());
        } finally {
            cleanupThreadLocals();
        }
    }


}
