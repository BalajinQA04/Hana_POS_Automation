//package com.hanapos.utilities;
//
//import com.hanapos.seleniumProjectBase.TestBaseClass;
//import io.qameta.allure.Allure;
//import org.openqa.selenium.*;
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.devtools.DevTools;
//import org.openqa.selenium.devtools.HasDevTools;
//import org.openqa.selenium.devtools.v126.network.Network;
//import org.openqa.selenium.devtools.v126.network.model.RequestId;
//import org.openqa.selenium.devtools.v126.network.model.Response;
//import org.testng.asserts.IAssert;
//import org.testng.asserts.SoftAssert;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import java.util.concurrent.*;
//
//public class CustomSoftAssert extends SoftAssert {
//
//    private static final ThreadLocal<List<String>> NETWORK_LOGS =
//            ThreadLocal.withInitial(CopyOnWriteArrayList::new);
//
//    private static final int MAX_NETWORK_LOGS = 50; // Prevent memory overload
//
//    public static void initNetworkLogging() {
//        WebDriver driver = TestBaseClass.getDriver();
//        if (driver instanceof HasDevTools) {
//            DevTools devTools = ((HasDevTools) driver).getDevTools();
//            devTools.createSession();
//            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//
//            List<String> logs = NETWORK_LOGS.get();
//            logs.clear();
//
//            devTools.addListener(Network.responseReceived(), responseReceived -> {
//                try {
//                    Response res = responseReceived.getResponse();
//                    RequestId requestId = responseReceived.getRequestId();
//
//                    if (logs.size() >= MAX_NETWORK_LOGS) return;
//
//                    StringBuilder entry = new StringBuilder();
//                    entry.append("URL: ").append(res.getUrl()).append("\n");
//                    entry.append("Status: ").append(res.getStatus());
//
//                    if (res.getStatus() >= 400) {
//                        try {
//                            String responseBody = devTools.send(Network.getResponseBody(requestId)).getBody();
//                            if (responseBody.length() > 1000) {
//                                responseBody = responseBody.substring(0, 1000) + "... [truncated]";
//                            }
//                            entry.append("\nResponse: ").append(responseBody);
//                        } catch (Exception e) {
//                            entry.append("\nResponse: [Failed to fetch body: ").append(e.getMessage()).append("]");
//                        }
//                    }
//
//                    logs.add(entry.toString());
//                } catch (Exception ignored) {
//                }
//            });
//        }
//    }
//
//    private static String collectAndClearNetworkLogs() {
//        List<String> logs = NETWORK_LOGS.get();
//        if (logs == null || logs.isEmpty()) return "No network logs captured.";
//        StringBuilder builder = new StringBuilder("📡 Network Logs on Failure:\n");
//        for (String log : logs) {
//            builder.append(log).append("\n----------------------\n");
//        }
//        logs.clear();
//        NETWORK_LOGS.remove();
//        return builder.toString();
//    }
//
//    @Override
//    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
//        try {
//            WebDriver driver = TestBaseClass.getDriver();
//            String currentUrl = driver != null ? driver.getCurrentUrl() : "UNKNOWN";
//            String timestamp = LocalDateTime.now()
//                    .format(DateTimeFormatter.ofPattern("MMMM dd yyyy hh:mm:ss a"));
//
//            String failureMsg = "❌ Assertion failed at URL: " + currentUrl +
//                    "\n\n🕒 Time: " + timestamp +
//                    "\n\n📄 Message: " + assertCommand.getMessage();
//
//            Allure.addAttachment("Assertion Failure Message", failureMsg);
//
//            // Capture screenshot (fast timeout + fallback HTML)
//            String screenshotBase64 = captureScreenshotWithTimeout(5, TimeUnit.SECONDS);
//            if (!screenshotBase64.isEmpty()) {
//                byte[] screenshotBytes = Base64.getDecoder().decode(screenshotBase64);
//                Allure.getLifecycle().addAttachment(
//                        "🖼 Screenshot on Failure",
//                        "image/png",
//                        "png",
//                        screenshotBytes
//                );
//            } else {
//                attachPageSourceFallback();
//            }
//
//            // Network logs
//            String networkLogs = collectAndClearNetworkLogs();
//            Allure.addAttachment("Network Logs", networkLogs);
//
//        } catch (Exception e) {
//            Allure.addAttachment("Assertion Logger Exception", e.toString());
//        }
//
//        super.onAssertFailure(assertCommand, ex);
//    }
//
//    /**
//     * Captures screenshot with a custom timeout.
//     */
//    private String captureScreenshotWithTimeout(long timeout, TimeUnit unit) {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        try {
//            Future<String> future = executor.submit(() -> {
//                try {
//                    WebDriver driver = TestBaseClass.getDriver();
//                    if (driver == null) return "";
//                    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//                } catch (Exception e) {
//                    Allure.addAttachment("Screenshot Capture Failure", e.toString());
//                    return "";
//                }
//            });
//            return future.get(timeout, unit);
//        } catch (TimeoutException te) {
//            Allure.addAttachment("Screenshot Capture Failure", "Timed out after " + timeout + " " + unit);
//            return "";
//        } catch (Exception e) {
//            Allure.addAttachment("Screenshot Capture Failure", e.toString());
//            return "";
//        } finally {
//            executor.shutdownNow();
//        }
//    }
//
//    /**
//     * Attaches page source as a fallback when screenshot fails.
//     */
//    private void attachPageSourceFallback() {
//        try {
//            WebDriver driver = TestBaseClass.getDriver();
//            if (driver != null) {
//                String html = driver.getPageSource();
//                Allure.addAttachment("📄 Page Source (Fallback)", "text/html", html, ".html");
//            }
//        } catch (Exception e) {
//            Allure.addAttachment("Page Source Capture Failure", e.toString());
//        }
//    }
//}


package com.hanapos.utilities;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.RequestId;
import org.openqa.selenium.devtools.v126.network.model.Response;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CustomSoftAssert extends SoftAssert {

    private static final ThreadLocal<List<String>> NETWORK_LOGS =
            ThreadLocal.withInitial(CopyOnWriteArrayList::new);

    private static final int MAX_NETWORK_LOGS = 50; // Prevent memory overload

    /** Enable network logging */
    public static void initNetworkLogging() {
        WebDriver driver = TestBaseClass.getDriver();
        if (driver instanceof HasDevTools) {
            DevTools devTools = ((HasDevTools) driver).getDevTools();
            devTools.createSession();
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

            List<String> logs = NETWORK_LOGS.get();
            logs.clear();

            devTools.addListener(Network.responseReceived(), responseReceived -> {
                try {
                    Response res = responseReceived.getResponse();
                    RequestId requestId = responseReceived.getRequestId();

                    if (logs.size() >= MAX_NETWORK_LOGS) return;

                    StringBuilder entry = new StringBuilder();
                    entry.append("URL: ").append(res.getUrl()).append("\n");
                    entry.append("Status: ").append(res.getStatus());

                    if (res.getStatus() >= 400) {
                        try {
                            String responseBody = devTools.send(Network.getResponseBody(requestId)).getBody();
                            if (responseBody.length() > 1000) {
                                responseBody = responseBody.substring(0, 1000) + "... [truncated]";
                            }
                            entry.append("\nResponse: ").append(responseBody);
                        } catch (Exception e) {
                            entry.append("\nResponse: [Failed to fetch body: ").append(e.getMessage()).append("]");
                        }
                    }

                    logs.add(entry.toString());
                } catch (Exception ignored) {
                }
            });
        }
    }

    private static String collectAndClearNetworkLogs() {
        List<String> logs = NETWORK_LOGS.get();
        if (logs == null || logs.isEmpty()) return "No network logs captured.";
        StringBuilder builder = new StringBuilder("📡 Network Logs on Failure:\n");
        for (String log : logs) {
            builder.append(log).append("\n----------------------\n");
        }
        logs.clear();
        NETWORK_LOGS.remove();
        return builder.toString();
    }

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        try {
            WebDriver driver = TestBaseClass.getDriver();
            String currentUrl = driver != null ? driver.getCurrentUrl() : "UNKNOWN";
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("MMMM dd yyyy hh:mm:ss a"));

            // Failure message
            String failureMsg = "❌ Assertion failed at URL: " + currentUrl +
                    "\n\n🕒 Time: " + timestamp +
                    "\n\n📄 Message: " + assertCommand.getMessage();
            Allure.addAttachment("Assertion Failure Message", failureMsg);

            // Screenshot only when assertion fails
            if (driver != null) {
                try {
                    byte[] screenshotPng = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                    // Compress PNG → JPG to reduce size
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(screenshotPng));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(image, "jpg", baos);

                    Allure.getLifecycle().addAttachment(
                            "🖼 Screenshot on Failure",
                            "image/jpeg",
                            "jpg",
                            baos.toByteArray()
                    );
                } catch (Exception e) {
                    Allure.addAttachment("Screenshot Capture Failure", e.toString());
                }
            }

            // Attach network logs
          //  String networkLogs = collectAndClearNetworkLogs();
          //  Allure.addAttachment("Network Logs", networkLogs);

        } catch (Exception e) {
            Allure.addAttachment("Assertion Logger Exception", e.toString());
        }

        super.onAssertFailure(assertCommand, ex);
    }
}
