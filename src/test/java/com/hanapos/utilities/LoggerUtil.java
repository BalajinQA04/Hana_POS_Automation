package com.hanapos.utilities;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v144.network.Network;
import org.openqa.selenium.devtools.v144.network.model.Request;
import org.openqa.selenium.devtools.v144.network.model.RequestId;
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

    /* ---------------- THREAD LOCALS ---------------- */

    private static final ThreadLocal<Logger> threadLogger = new ThreadLocal<>();
    private static final ThreadLocal<DevTools> threadDevTools = new ThreadLocal<>();

    private static final ThreadLocal<Map<RequestId, Long>> threadRequestStartTimes =
            ThreadLocal.withInitial(ConcurrentHashMap::new);

    private static final ThreadLocal<Map<RequestId, String>> threadRequestUrls =
            ThreadLocal.withInitial(ConcurrentHashMap::new);

    private static final ThreadLocal<Map<RequestId, String>> threadRequestPayloads =
            ThreadLocal.withInitial(ConcurrentHashMap::new);

    /* ---------------- LOGGER ---------------- */

    public static Logger getLogger(String testCaseName) {
        if (threadLogger.get() == null) {
            System.setProperty("logFileName", "logs/" + testCaseName + ".log");
            threadLogger.set(LogManager.getLogger(testCaseName));
        }
        return threadLogger.get();
    }

    /* ---------------- FILE LOGGING ---------------- */

    private void writeToLogFile(String testCaseName, String message) {
        File dir = new File("Network_Logs");
        if (!dir.exists()) dir.mkdirs();

        File file = new File(dir, testCaseName + "_network_logs.txt");

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(message + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* ---------------- BASIC NETWORK LOGGER ---------------- */

    public void startNetworkLogging(String testCaseName) {
        try {
            DevTools devTools = ((ChromeDriver) getDriver()).getDevTools();
            devTools.createSession();
            threadDevTools.set(devTools);

            devTools.send(Network.setCacheDisabled(true));
            devTools.send(Network.enable(
                    Optional.empty(),   // maxTotalBufferSize
                    Optional.empty(),   // maxResourceBufferSize
                    Optional.empty(),   // maxPostDataSize
                    Optional.of(false), // enableBinary
                    Optional.of(false)  // enableStreaming
            ));


            Map<RequestId, Long> requestStartTimes = new ConcurrentHashMap<>();

            devTools.addListener(Network.requestWillBeSent(), event ->
                    requestStartTimes.put(event.getRequestId(), System.currentTimeMillis())
            );

            devTools.addListener(Network.responseReceived(), response -> {
                RequestId reqId = response.getRequestId();
                int status = response.getResponse().getStatus();
                if (status < 400) return;

                Long start = requestStartTimes.get(reqId);
                if (start == null) return;

                long time = System.currentTimeMillis() - start;
                DecimalFormat df = new DecimalFormat("#.##");

                StringBuilder log = new StringBuilder()
                        .append("❌ Failed API\n")
                        .append("URL: ").append(response.getResponse().getUrl()).append("\n")
                        .append("Status: ").append(status).append("\n")
                        .append("Time: ").append(df.format(time)).append(" ms\n");

                try {
                    Network.GetResponseBodyResponse body =
                            devTools.send(Network.getResponseBody(reqId));
                    log.append("Body:\n").append(body.getBody());
                } catch (Exception e) {
                    log.append("Body: [Unavailable]");
                }

                writeToLogFile(testCaseName, log.toString());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ---------------- PAYMENT API LOGGER ---------------- */

    public void payment_API_Logger(String testCaseName, Runnable action) {
        try {
            DevTools devTools = ((ChromeDriver) getDriver()).getDevTools();
            devTools.createSession();
            threadDevTools.set(devTools);

            Set<String> dedupe = ConcurrentHashMap.newKeySet();
            Map<RequestId, Long> startTimes = new ConcurrentHashMap<>();
            Map<RequestId, String> urls = new ConcurrentHashMap<>();
            Map<RequestId, String> payloads = new ConcurrentHashMap<>();
            List<String> logs = Collections.synchronizedList(new ArrayList<>());

            String baseUrl = "https://hanafloralpos3.com";

            devTools.send(Network.setCacheDisabled(true));
            devTools.send(Network.enable(
                    Optional.empty(),   // maxTotalBufferSize
                    Optional.empty(),   // maxResourceBufferSize
                    Optional.empty(),   // maxPostDataSize
                    Optional.of(false), // enableBinary
                    Optional.of(false)  // enableStreaming
            ));


            devTools.addListener(Network.requestWillBeSent(), event -> {
                RequestId id = event.getRequestId();
                Request req = event.getRequest();
                String url = req.getUrl();

                if (!url.startsWith(baseUrl)) return;

                urls.put(id, url);
                payloads.put(id, req.getPostData().orElse(""));
                startTimes.put(id, System.currentTimeMillis());
            });

            devTools.addListener(Network.responseReceived(), response -> {
                RequestId id = response.getRequestId();
                String url = urls.get(id);
                if (url == null || !dedupe.add(url + id)) return;

                long duration = System.currentTimeMillis() -
                        startTimes.getOrDefault(id, System.currentTimeMillis());

                StringBuilder log = new StringBuilder()
                        .append("URL: ").append(url).append("\n")
                        .append("Status: ").append(response.getResponse().getStatus()).append("\n")
                        .append("Time: ").append(duration).append(" ms\n");

                String payload = payloads.get(id);
                if (payload != null && !payload.isEmpty()) {
                    log.append("Payload:\n").append(payload).append("\n");
                }

                try {
                    Network.GetResponseBodyResponse body =
                            devTools.send(Network.getResponseBody(id));
                    log.append("Response:\n").append(body.getBody());
                } catch (Exception e) {
                    log.append("Response: [Unavailable]");
                }

                logs.add(log.toString());
            });

            action.run();
            delayWithGivenTime(3000);

            StringBuilder finalLog = new StringBuilder();
            logs.forEach(l -> finalLog.append(l).append("\n-----------------\n"));

            Allure.addAttachment("Payment API Logs - " + testCaseName, finalLog.toString());

        } finally {
            cleanupThreadLocals();
        }
    }

    /* ---------------- GENERIC API LOGGER ---------------- */

    public void order_Confirmation_API_Logger(String testCaseName, Runnable action) {
        logAPI(testCaseName, action, "Order Confirmation");
    }

    public void create_Proposal_API_Logger(String testCaseName, Runnable action) {
        logAPI(testCaseName, action, "Create Proposal");
    }

    private void logAPI(String testCaseName, Runnable action, String context) {
        try {
            DevTools devTools = ((ChromeDriver) getDriver()).getDevTools();
            devTools.createSession();
            threadDevTools.set(devTools);

            ConcurrentMap<RequestId, Long> startTimes = new ConcurrentHashMap<>();
            ConcurrentMap<RequestId, String> urls = new ConcurrentHashMap<>();
            ConcurrentMap<RequestId, String> payloads = new ConcurrentHashMap<>();
            Set<RequestId> processed = ConcurrentHashMap.newKeySet();

            String baseUrl = "https://hanafloralpos3.com";

            devTools.send(Network.setCacheDisabled(true));
            devTools.send(Network.enable(
                    Optional.empty(),   // maxTotalBufferSize
                    Optional.empty(),   // maxResourceBufferSize
                    Optional.empty(),   // maxPostDataSize
                    Optional.of(false), // enableBinary
                    Optional.of(false)  // enableStreaming
            ));


            devTools.addListener(Network.requestWillBeSent(), event -> {
                String url = event.getRequest().getUrl();
                if (!url.startsWith(baseUrl)) return;

                RequestId id = event.getRequestId();
                urls.putIfAbsent(id, url);
                payloads.putIfAbsent(id, event.getRequest().getPostData().orElse(""));
                startTimes.putIfAbsent(id, System.currentTimeMillis());
            });

            devTools.addListener(Network.responseReceived(), response -> {
                RequestId id = response.getRequestId();
                if (!processed.add(id)) return;

                String url = urls.get(id);
                if (url == null) return;

                long time = System.currentTimeMillis() -
                        startTimes.getOrDefault(id, System.currentTimeMillis());

                StringBuilder log = new StringBuilder()
                        .append("URL: ").append(url).append("\n")
                        .append("Status: ").append(response.getResponse().getStatus()).append("\n")
                        .append("Time: ").append(time).append(" ms\n");

                String payload = payloads.get(id);
                if (payload != null && !payload.isEmpty()) {
                    log.append("Payload:\n").append(payload).append("\n");
                }

                try {
                    Network.GetResponseBodyResponse body =
                            devTools.send(Network.getResponseBody(id));
                    log.append("Response:\n")
                            .append(body.getBody().substring(0,
                                    Math.min(body.getBody().length(), 300)));
                } catch (Exception e) {
                    log.append("Response: [Unavailable]");
                }

                Allure.addAttachment(context + " API - " + testCaseName + " | " + id, log.toString());
            });

            action.run();
            delayWithGivenTime(1500);

        } finally {
            cleanupThreadLocals();
        }
    }

    /* ---------------- CLEANUP ---------------- */

    private void cleanupThreadLocals() {
        threadDevTools.remove();
        threadLogger.remove();
        threadRequestStartTimes.remove();
        threadRequestUrls.remove();
        threadRequestPayloads.remove();
    }
}
