package com.hanapos.seleniumProjectBase;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.hanapos.utilities.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.ThreadContext;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;

import java.awt.event.InputEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.imageio.ImageIO;
import javax.mail.*;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.firefox.HasFullPageScreenshot;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.Optional;
import org.apache.commons.text.RandomStringGenerator;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;


/**
 * Test Base Class is the base class for all the test classes.
 * It contains the common methods and variables used in all the test classes.
 *
 * @Author: Balaji N
 */
public class TestBaseClass implements FrameworkDesign {
    private static JsonNode jsonData;
    private static final Object lock = new Object();
    public static Properties prop;
    public static Logger logger;
    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public static DesiredCapabilities capabilities = new DesiredCapabilities();
    public static LoggerUtil logger_Util;
    private static final ThreadLocal<ConcurrentLinkedQueue<String>> pageLoadLogs = ThreadLocal.withInitial(ConcurrentLinkedQueue::new);


    @BeforeSuite(groups = {"Smoke", "Sanity", "Regression"})
    @Override
    public void loadConfig() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //    @Parameters({"browser"})
//    @BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
//    public void setup(@Optional("chrome") String browser, Method method) {
//        try {
//            if (driver != null) {
//                launchApplication(browser);
//                String testCaseName = method.getName();
//                logger_Util = new LoggerUtil();
//                logger_Util.startNetworkLogging(testCaseName);
//                PageLoadLoggerUtils.beforeEachTest(method.getName());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, groups = {"Smoke", "Sanity", "Regression"})
    public void setup(@Optional("chrome") String browser, Method method) {
        try {
            launchApplication(browser);
            String testCaseName = method.getName();

            logger_Util = new LoggerUtil();
            logger_Util.startNetworkLogging(testCaseName); // Starts DevTools logging

            PageLoadLoggerUtils.beforeEachTest(testCaseName); // Starts page load timer/log
        } catch (Exception e) {
            Allure.addAttachment("🚨 Setup Failure", e.toString());
            throw new RuntimeException("❌ Setup failed for test: " + method.getName(), e);
        }
    }

//    @AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
//    public void tearDown(Method method) {
//        if (getDriver() != null) {
//            try {
//                PageLoadLoggerUtils.attachLogsToAllure(method.getName());
//                getDriver().quit();
//                ThreadContext.clearAll();
//            } catch (Exception e) {
//                Allure.addAttachment("Browser Not Closed Issue", "Failed to quit WebDriver: " + e.getMessage());
//            }
//        } // Quit Mobile Driver
//        if (getMobileDriver() != null) {
//            getMobileDriver().quit();
//            mobileDriver.remove();
//        }
//    }

    @AfterMethod(alwaysRun = true, groups = {"Smoke", "Sanity", "Regression"})
    public void tearDown(Method method) {
        try {
            // Attach page load timing logs
            PageLoadLoggerUtils.attachLogsToAllure(method.getName());
        } catch (Exception e) {
            Allure.addAttachment("⚠️ Page Load Logger Error", e.toString());
        }

        // Kill browser safely with timeout
        safeQuitDriver(TestBaseClass.getDriver(), 5, TimeUnit.SECONDS);

        // Clear ThreadLocal driver reference
        if (driver != null) {
            driver.remove();
        }
    }

    /**
     * Quits WebDriver in a separate thread with a timeout.
     * If timeout expires, kills Chrome process manually.
     */
    private void safeQuitDriver(WebDriver driver, long timeout, TimeUnit unit) {
        if (driver == null) return;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<?> future = executor.submit(driver::quit);
            future.get(timeout, unit);
        } catch (TimeoutException e) {
            Allure.addAttachment("🚨 Browser Quit Timeout", "Killing browser process after timeout");
            killChromeProcess();
        } catch (Exception e) {
            Allure.addAttachment("🚨 Browser Quit Failure", e.toString());
        } finally {
            executor.shutdownNow();
        }
    }

    /**
     * Force-kills Chrome processes on Windows/Mac/Linux.
     */
    private void killChromeProcess() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /T");
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
            } else {
                Runtime.getRuntime().exec("pkill -f chrome");
                Runtime.getRuntime().exec("pkill -f chromedriver");
            }
        } catch (Exception e) {
            Allure.addAttachment("🚨 Process Kill Failure", e.toString());
        }
    }


    @Override
    public void launchApplication(String browserName) {
        logger = LogManager.getLogger(this.getClass());
        String downloadPath = System.getProperty("user.dir");
        try {
            if (browserName.equalsIgnoreCase("Chrome")) {
                WebDriverManager.chromedriver().timeout(30).setup();
                ChromeOptions options = new ChromeOptions();

                options.addArguments("--disable-gpu");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-background-networking");
                options.addArguments("--disable-background-timer-throttling");
                options.addArguments("--disable-renderer-backgrounding");
                options.addArguments("--disable-features=PaintHolding,TranslateUI,VizDisplayCompositor");
               // options.addArguments("--incognito");
                options.addArguments("force-device-scale-factor=1.25"); // 125% zoom

                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.BROWSER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logPrefs);

                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadPath);
                options.setExperimentalOption("prefs", chromePrefs);

                // Fix here: pass List<String> not String[]
                options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                options.merge(capabilities);

                driver.set(new ChromeDriver(options));

            } else if (browserName.equalsIgnoreCase("FireFox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions opt = new FirefoxOptions();
                opt.merge(capabilities);
                capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                driver.set(new FirefoxDriver(opt));

            } else if (browserName.equalsIgnoreCase("EDGE")) {
                WebDriverManager.edgedriver().timeout(60).setup();
                EdgeOptions opt = new EdgeOptions();

                opt.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
                opt.merge(capabilities);
                opt.addArguments("--remote-allow-origins=*");
                capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                driver.set(new EdgeDriver(opt));
            }
            getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
            getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(120));
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            getDriver().manage().deleteAllCookies();
            getDriver().manage().window().setSize(new Dimension(1920, 1080));
            getDriver().manage().window().maximize();
            getDriver().navigate().to(getAppURL());
            // navigateAndLog("Hana POS Login Page", getAppURL());

        } catch (Exception e) {
            String errorMessage = "Unable to launch the browser: " + e.getMessage();
            // Add to Allure report
            Allure.addAttachment("Browser Launch Error", new ByteArrayInputStream(errorMessage.getBytes()));
        }
    }

//    public void navigateAndLog(String pageName, String url) {
//        logPageLoad(pageName, () ->);
//    }


 /*   public static void CaptureConsoleLogs(String testCaseName) {
        try {
            // Ensure the logs directory exists
            File logDir = new File("logs");*//**//*
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            // Create the complete path for the log file
            String filePath = "logs" + File.separator + testCaseName + ".txt";

            // Capture console logs
            LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
            List<LogEntry> logs = logEntries.getAll();

            // Write logs to a file
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                for (LogEntry logEntry : logs) {
                    fileWriter.write(
                            logEntry.getTimestamp() + " " +
                                    logEntry.getLevel() + " " +
                                    logEntry.getMessage() + "\n\n"
                    );
                }
                System.out.println("Browser console logs saved to " + filePath);

                // Attach logs to Allure
                try (FileInputStream fis = new FileInputStream(filePath)) {
                    Allure.addAttachment("Console Logs", fis);
                } catch (IOException e) {
                    e.printStackTrace();
                    Allure.addAttachment("Console Logs", "No console logs found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

    /**
     * This function capture the network request and response add to the listeners to logging the networks
     *
     * @Author Balaji N
     */
    public String get_env_from_xml_file(ITestContext context) {
        String env = context.getCurrentXmlTest().getParameter("env");
        System.out.println("Testing on environment: " + env);
        String appURL = getAppURL(env);
        return appURL;
    }

    /**
     * Returns the WebDriver instance.
     * This method provides access to the WebDriver object, which is used for browser automation.
     *
     * @return The WebDriver instance.
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Get the URL based on the environment
     *
     * @return It returns the URL based on the environment setup
     * @author Balaji N
     */
    public String getAppURL() {
        switch (prop.getProperty("env")) {
            case "dev":
                return "https://hanadevpos3-dev1.azurewebsites.net/Account/Login";
            case "qa-final":
                return "https://hanadevpos3-qa-final.azurewebsites.net/";
            case "staging":
                return "https://hanafloralpos3-pre-production.azurewebsites.net/";
            case "live":
                return "https://hanafloralpos3.com/Account/Login";
            default:
                throw new IllegalStateException("Unexpected value: " + prop.getProperty("appURL"));
        }
    }

    public String getAppURL(String env) {
        switch (env) {
            case "dev":
                return "https://hanadevpos3-dev1.azurewebsites.net/Account/Login";
            case "qa-final":
                return "https://hanadevpos3-qa-final.azurewebsites.net/";
            case "staging":
                return "https://hanafloralpos3-staging.azurewebsites.net/";
            case "live":
                return "https://hanafloralpos3.com/Account/Login";
            default:
                throw new IllegalStateException("Unexpected value: " + env);
        }
    }


    // Load JSON data before suite
/*    @BeforeSuite(groups = {"Smoke", "Sanity", "Regression"})
    public void loadJsonData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonData = mapper.readTree(new File(
                    System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\testData.json"
            ));
            System.out.println("JSON Test Data Loaded Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(" Failed to load test data from JSON file.");
        }
    }*/

    // Method to retrieve data
    public static String getTestData(String keyPath) {
        String[] keys = keyPath.split("\\.");
        JsonNode node = jsonData;
        for (String key : keys) {
            node = node.path(key);
        }
        return node.asText();
    }


    public String getCurrentTestName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public void setupLogFileName(Method method) {
        String testName = method.getName(); // Or customize with className-methodName
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String logFileName = testName + "_" + dateTime;
        ThreadContext.put("logFilename", logFileName);  // Sets the filename for current thread
    }

    /* @Parameters({"browser", "os"})
     @BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
     public void Setup(@Optional("chrome") String browser, @Optional("windows") String os) {
         try {
             // Ensure properties are loaded
             if (prop == null) {
                 throw new IllegalStateException("Properties file is not loaded.");
             }

             String executionEnv = prop.getProperty("execution_env", "local").toLowerCase();

             switch (executionEnv) {
                 case "remote":
                     setupRemoteEnvironment(browser, os);
                     break;
                 case "local":
                     setupLocalEnvironment(browser);
                     break;
                 default:
                     throw new IllegalArgumentException("Invalid execution environment: " + executionEnv);
             }
         } catch (Exception e) {
             e.printStackTrace();
             throw new RuntimeException("Setup failed: " + e.getMessage(), e);
         }
     }
 */
    private void setupRemoteEnvironment(String browser, String os) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set platform (OS)
        if (os.equalsIgnoreCase("windows")) {
            capabilities.setPlatform(Platform.WIN11);
        } else {
            throw new IllegalArgumentException("Unsupported OS: " + os);
        }

        // Set browser
        switch (browser.toLowerCase()) {
            case "chrome":
                capabilities.setBrowserName("chrome");
                break;
            case "edge":
                capabilities.setBrowserName("MicrosoftEdge");
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.set(new RemoteWebDriver(new URL("http://localhost:4444"), capabilities));
        initializeDriver();
    }

    private void setupLocalEnvironment(String browser) {
        if (driver.get() == null) {
            launchApplication(browser);
            // startNetworkLogging();
        } else {
            System.out.println("Driver already initialized.");
        }
    }

    private void initializeDriver() {
        if (driver.get() != null) {
            driver.get().manage().deleteAllCookies();
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

            String appUrl = prop.getProperty("appURL");
            if (appUrl == null || appUrl.isEmpty()) {
                throw new IllegalArgumentException("Application URL is not defined in properties file.");
            }
            driver.get().get(getAppURL());
            driver.get().manage().window().maximize();
        }
    }

    /**
     * Add environment details to the Allure report
     *
     * @Author Balaji N
     */
    public static void addEnvironmentToAllureReport() {
        Allure.addAttachment("Test Environment : ", prop.getProperty("env"));
        Allure.addAttachment("Test URL : ", prop.getProperty("appURL"));
        Allure.addAttachment("Test Browser : ", prop.getProperty("browser"));
    }

    public static void AddEnvironment_To_AllureReport() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties")) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load config.properties");
            return;
        }

        String envFilePath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "environment.properties";

        try (FileWriter writer = new FileWriter(envFilePath)) {
            Properties properties = new Properties();
            properties.setProperty("Test Environment", prop.getProperty("env", prop.getProperty("env")));
            properties.setProperty("Test URL", prop.getProperty("appURL", prop.getProperty("appURL")));
            properties.setProperty("Test Browser", prop.getProperty("browser", prop.getProperty("browser")));
            properties.store(writer, "Environment Properties for Allure Report");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Add Environment to allure report is not configured");
        }
    }


    /**
     * Generates and attaches network logs for each test case in a separate file.
     *
     * @param result The {@link ITestResult} object containing test case details.
     */
    public void attachNetworkLogs(String testCaseName) {
        //  String testCaseName = result.getMethod().getMethodName(); // Get the test case name
        String logFileName = "Network_Logs/" + testCaseName + "_network_logs.txt"; // Unique log file for each test
        File logFile = new File(logFileName);

        try {
            // Ensure the "logs" directory exists
            File logDir = new File("Network_Logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            // Simulate writing logs (replace with actual log collection logic)
            FileWriter writer = new FileWriter(logFile);
            writer.write("Network logs for test case: " + testCaseName + "\n");
            writer.write("\n"); // Replace with real logs
            writer.close();

            // Attach the log file to the Allure report
            if (logFile.exists()) {
                Allure.addAttachment("Network API Logs - " + testCaseName, new FileInputStream(logFile));
            } else {
                Allure.addAttachment("Network API Logs - " + testCaseName, "No network logs found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private WebElement refreshElement(WebElement ele) {
        String locator = ele.toString().split("->")[1].trim(); // Extract locator details
        String locatorType = locator.split(":")[0].trim(); // Example: "By.xpath"
        String locatorValue = locator.split(":")[1].trim(); // Example: "//div[@id='example']"

        switch (locatorType) {
            case "By.id":
                return getDriver().findElement(By.id(locatorValue));
            case "By.name":
                return getDriver().findElement(By.name(locatorValue));
            case "By.className":
                return getDriver().findElement(By.className(locatorValue));
            case "By.cssSelector":
                return getDriver().findElement(By.cssSelector(locatorValue));
            case "By.xpath":
                return getDriver().findElement(By.xpath(locatorValue));
            case "By.linkText":
                return getDriver().findElement(By.linkText(locatorValue));
            case "By.partialLinkText":
                return getDriver().findElement(By.partialLinkText(locatorValue));
            case "By.tagName":
                return getDriver().findElement(By.tagName(locatorValue));
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
        }
    }

    @Override
    /**
     * Highlights a WebElement by applying a red border around it.
     * Ensures the element is visible before applying the highlight effect.
     *
     * @param ele The WebElement to be highlighted.
     * @throws RuntimeException If an exception occurs while executing JavaScript.
     */
    public void HighlightElement(WebElement ele) {
        try {
            fluentWait(ele);
            JavascriptExecutor JS = (JavascriptExecutor) getDriver();
            JS.executeScript("arguments[0].style.border='3px solid red'", ele);
        } catch (Exception e) {
            String elementDescription = getElementXPath(ele);
            String userFriendlyMessage = "❌ Could not highlight: " + elementDescription + "\n" +
                    "Reason: " + e.getClass().getSimpleName() + "\n" +
                    suggestFixes(e);
            Allure.step(userFriendlyMessage);
        }
    }

    /**
     * Attaches detailed highlight failure information to Allure report.
     */
    private void attachHighlightErrorToAllure(String errorType, WebElement ele, Exception e, String possibleCauses) {
        String message = "\n"
                + "❌ [HIGHLIGHT ELEMENT FAILURE] " + errorType + "\n"
                + " - Element locator : " + ele.toString() + "\n"
                + " - Exception type  : " + e.getClass().getSimpleName() + "\n"
                + "\n"
                + "🔍 POSSIBLE CAUSES:\n" + possibleCauses + "\n"
                + "\n"
                + "📌 NOTE: This error occurred while attempting to visually highlight the element before interaction.\n";

        Allure.addAttachment("Highlight Element Error", message);
    }

    /**
     * Highlights a WebElement by applying a red border around it.
     * Ensures the element is visible before applying the highlight effect.
     *
     * @param ele       The WebElement to be highlighted.
     * @param fieldname The name of the field
     * @throws RuntimeException If an exception occurs while executing JavaScript.
     * @Author Balaji N
     */
    public void Highlight_Element(WebElement ele, String fieldname) {
        try {
            JavascriptExecutor JS = (JavascriptExecutor) getDriver();
            JS.executeScript("arguments[0].style.border='3px solid red'", ele);
        } catch (NoSuchElementException e) {
            printError(ele, fieldname, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldname, "StaleElementReferenceException", e);
        } catch (JavascriptException e) {
            printError(ele, fieldname, "JavascriptException", e);
        } catch (WebDriverException e) {
            printError(ele, fieldname, "WebDriverException", e);
        } catch (Exception e) {
            printError(ele, fieldname, "UnexpectedException", e);
        }
    }


    public String generaterandomeNumber(int i) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .build();
        String randomNumeric = generator.generate(i);
        return randomNumeric;
    }

    public String twodigitrandomeString() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .build();
        String generatedString = generator.generate(2);
        return generatedString;
    }

    public BigDecimal round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd;
    }

    @Override
    public void click(WebElement ele) {
        int attempts = 0;
        int maxAttempts = 3;
        boolean isClicked = false;
        while (attempts < maxAttempts) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(ExpectedConditions.elementToBeClickable(ele));
                JavascriptExecutor JS = (JavascriptExecutor) getDriver();
                JS.executeScript("arguments[0].style.border='3px solid red'", ele);
                ele.click();
                isClicked = true;
                break;
            } catch (NoSuchElementException e) {
                attempts++;
                logError("Attempt " + attempts + " - Element not found: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Element not found after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
            } catch (StaleElementReferenceException e) {
                // Handle case where element is stale (detached from DOM)
                attempts++;
                logError("Attempt " + attempts + " - Stale element: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Stale element after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
                ele = getDriver().findElement(By.xpath(ele.toString())); // Update this with appropriate locator
            } catch (ElementNotInteractableException e) {
                // Handle case where element is not interactable (e.g., hidden or disabled)
                logError("Element is not interactable: " + ele.toString(), e);
                throw new RuntimeException("Element is not interactable: " + ele.toString(), e);
            } catch (TimeoutException e) {
                // Handle case where the element is not clickable within the timeout
                logError("Timeout waiting for element to be clickable: " + ele.toString(), e);
                throw new RuntimeException("Timeout waiting for element to be clickable: " + ele.toString(), e);
            } catch (WebDriverException e) {
                // Handle general WebDriver-related exceptions (e.g., issues with JavaScript execution)
                logError("WebDriver exception while clicking element: " + ele.toString(), e);
                throw new RuntimeException("WebDriver exception while clicking element: " + ele.toString(), e);
            } catch (Exception e) {
                // Catch any other general exceptions
                logError("Unexpected exception while clicking element: " + ele.toString(), e);
                throw new RuntimeException("Unexpected exception while clicking element: " + ele.toString(), e);
            }
        }
    }

    /**
     * Clicks the element with highlighting and without scrolling to particular element
     *
     * @param ele
     * @param fieldname
     * @Author Balaji N
     */
    public void click(WebElement ele, String fieldname) {
        int attempts = 0;
        int maxAttempts = 3;
        boolean isClicked = false;

        while (attempts < maxAttempts) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(ExpectedConditions.elementToBeClickable(ele));

                JavascriptExecutor JS = (JavascriptExecutor) getDriver();
                JS.executeScript("arguments[0].style.border='3px solid red'", ele);

                // Allure.step("Clicking element '" + fieldname + "'");
                ele.click();

                String successMsg = "✅ Clicked on: " + fieldname + " successfully.";
                System.out.println(successMsg);
                Allure.step(successMsg);

                isClicked = true;
                break;

            } catch (ElementNotInteractableException e) {
                attempts++;
                String warnMsg = "⚠️ Attempt " + attempts + " - ElementNotInteractableException for: " + fieldname;
                System.err.println(warnMsg);
                e.printStackTrace();
                Allure.step(warnMsg);
                printError(ele, fieldname, "ElementNotInteractableException on attempt " + attempts, e);

                if (attempts >= maxAttempts) {
                    try {
                        Allure.step("Attempting fallback JavaScript click for '" + fieldname + "'");
                        JavascriptExecutor js = (JavascriptExecutor) getDriver();
                        js.executeScript("arguments[0].click();", ele);

                        String fallbackMsg = "✅ Clicked using JavaScript fallback for: " + fieldname;
                        System.err.println(fallbackMsg);
                        Allure.step(fallbackMsg);

                        isClicked = true;
                        break;

                    } catch (Exception jsEx) {
                        String failMsg = "❌ JS click also failed for: " + fieldname;
                        System.err.println(failMsg);
                        jsEx.printStackTrace();
                        Allure.step(failMsg);
                        printError(ele, fieldname, "JavaScript fallback click failed", jsEx);
                        throw new RuntimeException(failMsg, jsEx);
                    }
                }

            } catch (StaleElementReferenceException e) {
                attempts++;
                String staleMsg = "⚠️ Attempt " + attempts + " - StaleElementReferenceException for: " + fieldname;
                System.err.println(staleMsg);
                e.printStackTrace();
                System.err.println("⚠️ Cannot re-fetch element as only WebElement reference is available.");
                Allure.step(staleMsg);
                printError(ele, fieldname, "StaleElementReferenceException on attempt " + attempts, e);

            } catch (NoSuchElementException e) {
                attempts++;
                String noSuchMsg = "⚠️ Attempt " + attempts + " - NoSuchElementException for: " + fieldname;
                System.err.println(noSuchMsg);
                e.printStackTrace();
                Allure.step(noSuchMsg);
                printError(ele, fieldname, "NoSuchElementException on attempt " + attempts, e);

            } catch (TimeoutException e) {
                String timeoutMsg = "❌ TimeoutException while waiting for: " + fieldname;
                System.err.println(timeoutMsg);
                e.printStackTrace();
                Allure.step(timeoutMsg);
                printError(ele, fieldname, "TimeoutException while waiting for element", e);
                break;

            } catch (WebDriverException e) {
                String webdriverMsg = "❌ WebDriverException for: " + fieldname;
                System.err.println(webdriverMsg);
                e.printStackTrace();
                Allure.step(webdriverMsg);
                printError(ele, fieldname, "WebDriverException", e);
                break;

            } catch (Exception e) {
                String unexpectedMsg = "❌ Unexpected exception during click on: " + fieldname;
                System.err.println(unexpectedMsg);
                e.printStackTrace();
                Allure.step(unexpectedMsg);
                printError(ele, fieldname, "Unexpected exception", e);
                break;
            }
        }

        if (!isClicked) {
            String finalFailMsg = "❌ Failed to click " + fieldname + " after " + maxAttempts + " attempts.";
            System.err.println(finalFailMsg);
            Allure.step(finalFailMsg);
            throw new RuntimeException("Failed to click element: " + fieldname);
        }
    }


    public void Click(WebElement ele, String fieldname) {
        int attempts = 0;
        int maxAttempts = 3;
        boolean isClicked = false;
        while (attempts < maxAttempts) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(ExpectedConditions.elementToBeClickable(ele));
                HighlightElement(ele);
                ele.click();
                String successMsg = "✅ Clicked on: " + fieldname + " successfully.";
                System.out.println(successMsg);
                Allure.step(successMsg);
                isClicked = true;
                break;
            } catch (NoSuchElementException e) {
                attempts++;
                logError("Attempt " + attempts + " - Element not found: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Element not found after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
                printError(ele, fieldname, "NoSuchElementException", e);
            } catch (StaleElementReferenceException e) {
                // Handle case where element is stale (detached from DOM)
                attempts++;
                logError("Attempt " + attempts + " - Stale element: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Stale element after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
                ele = getDriver().findElement(By.xpath(ele.toString())); // Update this with appropriate locator
                printError(ele, fieldname, "Stale Element Reference - Exception", e);
            } catch (ElementNotInteractableException e) {
                printError(ele, fieldname, "ElementNotInteractable - Exception", e);
            } catch (TimeoutException e) {
                printError(ele, fieldname, "Timeout - Exception", e);
            } catch (WebDriverException e) {
                printError(ele, fieldname, "WebDriverException - Exception", e);
            } catch (Exception e) {
                printError(ele, fieldname, "Unexpected exception while clicking element:", e);
            }
        }
    }


    /**
     * Verifies whether the specified element is enabled on the web page with highlighting the web element.
     *
     * @param element   The WebElement to verify.
     * @param fieldName The name of the field for logging purposes.
     * @return {@code true} if the element is enabled, otherwise {@code false}.
     * @Author Balaji N
     */
    public boolean is_Element_Enabled(WebElement element, String fieldName) {
        try {
            HighlightElement(element);
            boolean isEnabled = element.isEnabled();
            return isEnabled;
        } catch (NoSuchElementException e) {
            printError(element, fieldName, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(element, fieldName, "StaleElementReferenceException", e);
        } catch (ElementNotInteractableException e) {
            printError(element, fieldName, "ElementNotInteractableException", e);
        } catch (TimeoutException e) {
            printError(element, fieldName, "TimeoutException", e);
        } catch (WebDriverException e) {
            printError(element, fieldName, "WebDriverException", e);
        } catch (Exception e) {
            printError(element, fieldName, "UnexpectedException", e);
        }
        return false;
    }


    /**
     * Verify whether the element is displayed or not in web page
     *
     * @param element - The element to be verify
     * @return If element is displayed it returns true or else returns false
     * @Author Balaji N
     */
    public boolean isElementDisplayed(WebElement element) {
        try {
            HighlightElement(element);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            System.err.println("Element not found while validating element is displayed: " + element.toString());
            return false;
        }
    }

    /**
     * Verifies whether the specified element is displayed on the web page with highlighting the web element.
     *
     * @param element   The WebElement to verify.
     * @param fieldName The name of the field for logging purposes.
     * @return {@code true} if the element is displayed, otherwise {@code false}.
     * @Author Balaji N
     */
    public boolean is_Element_Displayed(WebElement element, String fieldName) {
        try {
            HighlightElement(element);
            boolean isDisplayed = element.isDisplayed();
            if (isDisplayed) {
                Allure.step("✅ '" + fieldName + "' is visible on the screen.");
                return true;
            } else {
                Allure.step("❌ '" + fieldName + "' is present in DOM but not visible on the screen.");
                return false;
            }
        } catch (NoSuchElementException e) {
            String locator = element.toString();
            String msg = String.format(
                    "❌ '%s' was not found on the page.%n" +
                            "Reason: The element does not exist in the DOM.%n" +
                            "Possible causes:%n" +
                            "  • The page section did not load as expected%n" +
                            "  • The locator is incorrect or outdated%n" +
                            "  • The element is loaded dynamically and needs extra wait%n" +
                            "Locator: %s",
                    fieldName, locator
            );
            Allure.step(msg);
            printError(element, fieldName, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            String locator = element.toString();
            String msg = String.format(
                    "⚠️ '%s' became stale (no longer attached to DOM).%n" +
                            "Reason: The page refreshed or element was re-rendered.%n" +
                            "Fix: Re-locate the element before interacting.%n" +
                            "Locator: %s",
                    fieldName, locator
            );
            Allure.step(msg);
            printError(element, fieldName, "StaleElementReferenceException", e);
        } catch (ElementNotInteractableException e) {
            String locator = element.toString();
            String msg = String.format(
                    "⚠️ '%s' is present but not interactable.%n" +
                            "Reason: It might be hidden, disabled, or covered by another element.%n" +
                            "Fix: Scroll into view or wait for it to become clickable.%n" +
                            "Locator: %s",
                    fieldName, locator
            );
            Allure.step(msg);
            printError(element, fieldName, "ElementNotInteractableException", e);
        } catch (TimeoutException e) {
            String locator = element.toString();
            String msg = String.format(
                    "⏳ '%s' did not appear within the expected time.%n" +
                            "Reason: The element took too long to load.%n" +
                            "Possible causes:%n" +
                            "  • Slow network or backend processing%n" +
                            "  • Locator is correct but timing is wrong%n" +
                            "Locator: %s",
                    fieldName, locator
            );
            Allure.step(msg);
            printError(element, fieldName, "TimeoutException", e);
        } catch (WebDriverException e) {
            String locator = element.toString();
            String msg = String.format(
                    "🚫 WebDriver error occurred while checking '%s'.%n" +
                            "Reason: %s%n" +
                            "Locator: %s",
                    fieldName, e.getMessage(), locator
            );
            Allure.step(msg);
            printError(element, fieldName, "WebDriverException", e);
        } catch (Exception e) {
            String locator = element.toString();
            String msg = String.format(
                    "⚠️ Unexpected error occurred while checking '%s'.%n" +
                            "Reason: %s%n" +
                            "Locator: %s",
                    fieldName, e.getMessage(), locator
            );
            Allure.step(msg);
            printError(element, fieldName, "UnexpectedException", e);
        }
        return false;
    }

    public boolean isElementDisplayed(WebElement element, String fieldName) {
        try {
            JavascriptExecutor JS = (JavascriptExecutor) getDriver();
            JS.executeScript("arguments[0].style.border='3px solid red'", element);

            if (element.isDisplayed()) {
                Allure.step("✅ '" + fieldName + "' is visible on the screen.");
                return true;
            } else {
                Allure.step("❌ '" + fieldName + "' is present in DOM but not visible on the screen.");
                return false;
            }

        } catch (NoSuchElementException e) {
            String msg = "❌ '" + fieldName + "' was not found on the page.";
            Allure.step(msg);
            printError(element, fieldName, "NoSuchElementException", e);

        } catch (StaleElementReferenceException e) {
            String msg = "❌ '" + fieldName + "' disappeared or reloaded before it could be checked.";
            Allure.step(msg);
            printError(element, fieldName, "StaleElementReferenceException", e);

        } catch (ElementNotInteractableException e) {
            String msg = "❌ '" + fieldName + "' is present but cannot be interacted with.";
            Allure.step(msg);
            printError(element, fieldName, "ElementNotInteractableException", e);

        } catch (TimeoutException e) {
            String msg = "❌ Timed out while waiting for '" + fieldName + "' to appear.";
            Allure.step(msg);
            printError(element, fieldName, "TimeoutException", e);

        } catch (WebDriverException e) {
            String msg = "❌ Could not verify '" + fieldName + "' due to browser/driver issue.";
            Allure.step(msg);
            printError(element, fieldName, "WebDriverException", e);

        } catch (Exception e) {
            String msg = "❌ An unexpected error occurred while checking '" + fieldName + "'.";
            Allure.step(msg);
            printError(element, fieldName, "UnexpectedException", e);
        }
        return false;
    }


//    public boolean isElementDisplayed(WebElement element, String fieldName) {
//        try {
//            JavascriptExecutor JS = (JavascriptExecutor) getDriver();
//            JS.executeScript("arguments[0].style.border='3px solid red'", element);
//            boolean isDisplayed = element.isDisplayed();
//            return isDisplayed;
//        } catch (NoSuchElementException e) {
//            String msg = "NoSuchElementException caught for element '" + fieldName + "'";
//            Allure.step(msg);
//            printError(element, fieldName, "NoSuchElementException", e);
//        } catch (StaleElementReferenceException e) {
//            String msg = "StaleElementReferenceException caught for element '" + fieldName + "'";
//            Allure.step(msg);
//            printError(element, fieldName, "StaleElementReferenceException", e);
//        } catch (ElementNotInteractableException e) {
//            String msg = "ElementNotInteractableException caught for element '" + fieldName + "'";
//            Allure.step(msg);
//            printError(element, fieldName, "ElementNotInteractableException", e);
//        } catch (TimeoutException e) {
//            String msg = "TimeoutException caught for element '" + fieldName + "'";
//            Allure.step(msg);
//            printError(element, fieldName, "TimeoutException", e);
//        } catch (WebDriverException e) {
//            String msg = "WebDriverException caught for element '" + fieldName + "'";
//            Allure.step(msg);
//            printError(element, fieldName, "WebDriverException", e);
//        } catch (Exception e) {
//            String msg = "UnexpectedException caught for element '" + fieldName + "'";
//            Allure.step(msg);
//            printError(element, fieldName, "UnexpectedException", e);
//        }
//        return false;
//    }


    /**
     * Checks if the given WebElement is enabled on the web page.
     *
     * @param ele       The WebElement to check.
     * @param fieldName The name of the field for logging purposes.
     * @return {@code true} if the element is enabled, {@code false} otherwise.
     * Returns {@code false} if the element is not found, stale, or an exception occurs.
     * @Author Balaji N
     */
    public boolean isElementEnabled(WebElement ele, String fieldName) {
        try {
            JavascriptExecutor JS = (JavascriptExecutor) getDriver();
            JS.executeScript("arguments[0].style.border='3px solid red'", ele);
            return ele.isEnabled();
        } catch (TimeoutException e) {
            printError(ele, fieldName, "TimeoutException", e);
        } catch (NoSuchElementException e) {
            printError(ele, fieldName, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldName, "StaleElementReferenceException", e);
        } catch (Exception e) {
            printError(ele, fieldName, "UnexpectedException", e);
        }
        return false;
    }

    /**
     * Checks if the given WebElement is disabled on the web page.
     *
     * @param ele
     * @param fieldName
     * @return If the element is disabled it returns true or else returns false
     * @Author Balaji N
     */
    public boolean js_Verify_Element_Is_Disabled(WebElement ele, String fieldName) {
        try {
            JavascriptExecutor JS = (JavascriptExecutor) getDriver();
            JS.executeScript("arguments[0].style.border='3px solid red'", ele);
            boolean isDisabled = (Boolean) JS.executeScript("return arguments[0].disabled;", ele);
            return isDisabled;
        } catch (TimeoutException e) {
            printError(ele, fieldName, "TimeoutException", e);
        } catch (NoSuchElementException e) {
            printError(ele, fieldName, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldName, "StaleElementReferenceException", e);
        } catch (Exception e) {
            printError(ele, fieldName, "UnexpectedException", e);
        }
        return false;
    }

    /**
     * Checks if the given WebElement is disabled on the web page.
     *
     * @param ele
     * @param fieldName
     * @return If the element is disabled it returns true or else returns false
     * @Author Balaji N
     */
    public boolean js_Verify_Element_Is_Enabled(WebElement ele, String fieldName) {
        try {
            JavascriptExecutor JS = (JavascriptExecutor) getDriver();
            JS.executeScript("arguments[0].style.border='3px solid red'", ele);
            boolean isEnabled = !(Boolean) JS.executeScript("return arguments[0].hasAttribute('disabled');", ele);
            return isEnabled;
        } catch (TimeoutException e) {
            printError(ele, fieldName, "TimeoutException", e);
        } catch (NoSuchElementException e) {
            printError(ele, fieldName, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldName, "StaleElementReferenceException", e);
        } catch (Exception e) {
            printError(ele, fieldName, "UnexpectedException", e);
        }
        return false;
    }


    @Override
    public void jsScrollClick(WebElement ele) {
        int attempts = 0;
        int maxAttempts = 3;
        while (attempts < maxAttempts) {
            try {
                // Wait for the element using FluentWait
                fluentWait(ele);

                // Highlight the element
                HighlightElement(ele);

                // Scroll and click using JavaScript Executor
                JavascriptExecutor executor = (JavascriptExecutor) getDriver();
                executor.executeScript("arguments[0].click();", ele);

                // Exit the loop if click is successful
                return;
            } catch (NoSuchElementException e) {
                attempts++;
                logError("Attempt " + attempts + " - Element not found: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Element not found after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
            } catch (StaleElementReferenceException e) {
                // Handle case where element is stale (detached from DOM)
                attempts++;
                logError("Attempt " + attempts + " - Stale element: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Stale element after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
                ele = getDriver().findElement(By.xpath(ele.toString())); // Update this with appropriate locator
            } catch (ElementNotInteractableException e) {
                // Handle case where element is not interactable (e.g., hidden or disabled)
                logError("Element is not interactable: " + ele.toString(), e);
                throw new RuntimeException("Element is not interactable: " + ele.toString(), e);
            } catch (TimeoutException e) {
                // Handle case where the element is not clickable within the timeout
                logError("Timeout waiting for element to be clickable: " + ele.toString(), e);
                throw new RuntimeException("Timeout waiting for element to be clickable: " + ele.toString(), e);
            } catch (WebDriverException e) {
                // Handle general WebDriver-related exceptions (e.g., issues with JavaScript execution)
                logError("WebDriver exception while clicking element: " + ele.toString(), e);
                throw new RuntimeException("WebDriver exception while clicking element: " + ele.toString(), e);
            } catch (Exception e) {
                // Catch any other general exceptions
                logError("Unexpected exception while clicking element: " + ele.toString(), e);
                throw new RuntimeException("Unexpected exception while clicking element: " + ele.toString(), e);
            }
        }
    }

    public void jsScrollClick(WebElement ele, String fieldName) {
        int attempts = 0;
        int maxAttempts = 3;
        while (attempts < maxAttempts) {
            try {
                // Wait for the element using FluentWait
                fluentWait(ele);

                // Highlight the element
                HighlightElement(ele);

                // Scroll and click using JavaScript Executor
                JavascriptExecutor executor = (JavascriptExecutor) getDriver();
                executor.executeScript("arguments[0].scrollIntoView(true);", ele);
                executor.executeScript("arguments[0].click();", ele);

                // Exit the loop if click is successful
                return;
            } catch (NoSuchElementException e) {
                attempts++;
                printError(ele, fieldName, "NoSuchElementException", e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Element not found after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
            } catch (StaleElementReferenceException e) {
                // Handle case where element is stale (detached from DOM)
                attempts++;
                printError(ele, fieldName, "StaleElementReferenceException", e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Stale element after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
                ele = getDriver().findElement(By.xpath(ele.toString())); // Update this with appropriate locator
            } catch (ElementNotInteractableException e) {
                // Handle case where element is not interactable (e.g., hidden or disabled)
                printError(ele, fieldName, "ElementNotInteractableException", e);
                throw new RuntimeException("Element is not interactable: " + ele.toString(), e);
            } catch (TimeoutException e) {
                // Handle case where the element is not clickable within the timeout
                printError(ele, fieldName, "TimeoutException", e);
                throw new RuntimeException("Timeout waiting for element to be clickable: " + ele.toString(), e);
            } catch (WebDriverException e) {
                // Handle general WebDriver-related exceptions (e.g., issues with JavaScript execution)
                printError(ele, fieldName, "WebDriverException", e);
                throw new RuntimeException("WebDriver exception while clicking element: " + ele.toString(), e);
            } catch (Exception e) {
                // Catch any other general exceptions
                printError(ele, fieldName, "UnexpectedException", e);
                throw new RuntimeException("Unexpected exception while clicking element: " + ele.toString(), e);
            }
        }
    }

    /**
     * Clicks the given WebElement using Actions class to click with highlight.
     *
     * @param ele       the WebElement to click
     * @param fieldName the name of the field
     * @Author Balaji N
     */
    public void actionClick(WebElement ele, String fieldName) {
        int attempts = 0;
        int maxAttempts = 3;
        while (attempts < maxAttempts) {
            try {
                HighlightElement(ele);
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(ExpectedConditions.elementToBeClickable(ele));
                Actions action = new Actions(getDriver());
                action.moveToElement(ele).click().build().perform();
            } catch (NoSuchElementException e) {
                attempts++;
                printError(ele, fieldName, "NoSuchElementException", e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Element not found after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
            } catch (StaleElementReferenceException e) {
                // Handle case where element is stale (detached from DOM)
                attempts++;
                logError("Attempt " + attempts + " - Stale element: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Stale element after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
                ele = getDriver().findElement(By.xpath(ele.toString())); // Update this with appropriate locator
            } catch (ElementNotInteractableException e) {
                // Handle case where element is not interactable (e.g., hidden or disabled)
                logError("Element is not interactable: " + ele.toString(), e);
                throw new RuntimeException("Element is not interactable: " + ele.toString(), e);
            } catch (TimeoutException e) {
                // Handle case where the element is not clickable within the timeout
                logError("Timeout waiting for element to be clickable: " + ele.toString(), e);
                throw new RuntimeException("Timeout waiting for element to be clickable: " + ele.toString(), e);
            } catch (WebDriverException e) {
                // Handle general WebDriver-related exceptions (e.g., issues with JavaScript execution)
                logError("WebDriver exception while clicking element: " + ele.toString(), e);
                throw new RuntimeException("WebDriver exception while clicking element: " + ele.toString(), e);
            } catch (Exception e) {
                // Catch any other general exceptions
                logError("Unexpected exception while clicking element: " + ele.toString(), e);
                throw new RuntimeException("Unexpected exception while clicking element: " + ele.toString(), e);
            }
        }
    }


    @Override
    public void actionClick(WebElement ele) {
        int attempts = 0;
        int maxAttempts = 3;
        while (attempts < maxAttempts) {
            try {
                HighlightElement(ele);
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                wait.until(ExpectedConditions.elementToBeClickable(ele));
                Actions action = new Actions(getDriver());
                action.moveToElement(ele).click().build().perform();
            } catch (NoSuchElementException e) {
                attempts++;
                logError("Attempt " + attempts + " - Element not found: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Element not found after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
            } catch (StaleElementReferenceException e) {
                // Handle case where element is stale (detached from DOM)
                attempts++;
                logError("Attempt " + attempts + " - Stale element: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Stale element after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
                ele = getDriver().findElement(By.xpath(ele.toString())); // Update this with appropriate locator
            } catch (ElementNotInteractableException e) {
                // Handle case where element is not interactable (e.g., hidden or disabled)
                logError("Element is not interactable: " + ele.toString(), e);
                throw new RuntimeException("Element is not interactable: " + ele.toString(), e);
            } catch (TimeoutException e) {
                // Handle case where the element is not clickable within the timeout
                logError("Timeout waiting for element to be clickable: " + ele.toString(), e);
                throw new RuntimeException("Timeout waiting for element to be clickable: " + ele.toString(), e);
            } catch (WebDriverException e) {
                // Handle general WebDriver-related exceptions (e.g., issues with JavaScript execution)
                logError("WebDriver exception while clicking element: " + ele.toString(), e);
                throw new RuntimeException("WebDriver exception while clicking element: " + ele.toString(), e);
            } catch (Exception e) {
                // Catch any other general exceptions
                logError("Unexpected exception while clicking element: " + ele.toString(), e);
                throw new RuntimeException("Unexpected exception while clicking element: " + ele.toString(), e);
            }
        }
    }


    /**
     * Extracts the XPath or another identifiable locator from the WebElement.
     *
     * @param ele The WebElement whose XPath needs to be retrieved.
     * @return The XPath string of the given WebElement.
     */
    protected String getElementXPath(WebElement ele) {
        String elementDescription = ele.toString();
        int start = elementDescription.indexOf("->") + 2;
        return (start > 2) ? elementDescription.substring(start).trim() : elementDescription;
    }

    @Override
    public void actionScrollClick(WebElement ele) {
        int attempts = 0;
        int maxAttempts = 3;
        while (attempts < maxAttempts) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOf(ele));
                Actions action = new Actions(getDriver());
                WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(ele);
                action.scrollFromOrigin(scrollOrigin, 0, 100) // Scroll directly to bring element into view
                        .build()
                        .perform();
                highlightElement(ele);
                ele.click();
            } catch (NoSuchElementException e) {
                attempts++;
                logError("Attempt " + attempts + " - Element not found: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Element not found after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
            } catch (StaleElementReferenceException e) {
                attempts++;
                logError("Attempt " + attempts + " - Stale element: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Stale element after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
                ele = getDriver().findElement(By.xpath(ele.toString())); // Update this with appropriate locator
            } catch (ElementNotInteractableException e) {
                logError("Element is not interactable: " + ele.toString(), e);
                throw new RuntimeException("Element is not interactable: " + ele.toString(), e);
            } catch (TimeoutException e) {
                logError("Timeout waiting for element to be clickable: " + ele.toString(), e);
                throw new RuntimeException("Timeout waiting for element to be clickable: " + ele.toString(), e);
            } catch (WebDriverException e) {
                logError("WebDriver exception while clicking element: " + ele.toString(), e);
                throw new RuntimeException("WebDriver exception while clicking element: " + ele.toString(), e);
            } catch (Exception e) {
                logError("Unexpected exception while clicking element: " + ele.toString(), e);
                throw new RuntimeException("Unexpected exception while clicking element: " + ele.toString(), e);
            }
        }
    }

    /**
     * This method highlights the element in the webpage
     *
     * @param element The element to be highlighted
     * @Author Balaji N
     */
    private void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }


    @Override
    public void jsClick(WebElement ele) {
        int attempts = 0;
        int maxAttempts = 3;

        while (attempts < maxAttempts) {
            try {
                HighlightElement(ele);
                fluentWait(ele);
                JavascriptExecutor executor = (JavascriptExecutor) getDriver();
                executor.executeScript("arguments[0].click();", ele);
                break;
            } catch (NoSuchElementException e) {
                attempts++;
                logError("Attempt " + attempts + " - Element not found: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Element not found after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
            } catch (StaleElementReferenceException e) {
                // Handle case where element is stale (detached from DOM)
                attempts++;
                logError("Attempt " + attempts + " - Stale element: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Stale element after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
                ele = getDriver().findElement(By.xpath(ele.toString())); // Update this with appropriate locator
            } catch (ElementNotInteractableException e) {
                // Handle case where element is not interactable (e.g., hidden or disabled)
                logError("Element is not interactable: " + ele.toString(), e);
                throw new RuntimeException("Element is not interactable: " + ele.toString(), e);
            } catch (TimeoutException e) {
                // Handle case where the element is not clickable within the timeout
                logError("Timeout waiting for element to be clickable: " + ele.toString(), e);
                throw new RuntimeException("Timeout waiting for element to be clickable: " + ele.toString(), e);
            } catch (WebDriverException e) {
                // Handle general WebDriver-related exceptions (e.g., issues with JavaScript execution)
                logError("WebDriver exception while clicking element: " + ele.toString(), e);
                throw new RuntimeException("WebDriver exception while clicking element: " + ele.toString(), e);
            } catch (Exception e) {
                // Catch any other general exceptions
                logError("Unexpected exception while clicking element: " + ele.toString(), e);
                throw new RuntimeException("Unexpected exception while clicking element: " + ele.toString(), e);
            }
        }
    }

    /**
     * This method is used to click the element using javascript executor
     *
     * @param ele
     * @param fieldname
     * @Author Balaji N
     */
    public void jsClick(WebElement ele, String fieldname) {
        int attempts = 0;
        int maxAttempts = 3;
        while (attempts < maxAttempts) {
            try {
                fluentWait(ele);
                JavascriptExecutor JS = (JavascriptExecutor) getDriver();
                JS.executeScript("arguments[0].style.border='3px solid red'", ele);
                JS.executeScript("arguments[0].click();", ele);
                break;
            } catch (NoSuchElementException e) {
                attempts++;
                logError("Attempt " + attempts + " - Element not found: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Element not found after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
                printError(ele, fieldname, "NoSuchElementException", e);
            } catch (StaleElementReferenceException e) {
                attempts++;
                logError("Attempt " + attempts + " - Stale element: " + ele.toString(), e);
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Stale element after " + maxAttempts + " attempts: " + ele.toString(), e);
                }
                ele = getDriver().findElement(By.xpath(ele.toString())); // Update this with appropriate locator
                printError(ele, fieldname, "Stale Element Reference - Exception", e);
            } catch (ElementNotInteractableException e) {
                printError(ele, fieldname, "ElementNotInteractable - Exception", e);
            } catch (TimeoutException e) {
                printError(ele, fieldname, "Timeout - Exception", e);
            } catch (WebDriverException e) {
                printError(ele, fieldname, "WebDriverException - Exception", e);
            } catch (Exception e) {
                printError(ele, fieldname, "Unexpected exception while clicking element:", e);
            }
        }
    }

    /**
     * This method is used to click the element using javascript executor
     *
     * @param ele
     * @param fieldname
     * @Author Balaji N
     */
 /*   public void js_Click(WebElement ele, String fieldname) {
        int attempts = 0;
        int maxAttempts = 3;
        boolean isClicked = false;

        while (attempts < maxAttempts) {
            try {
                HighlightElement(ele);
                fluentWait(ele);
                JavascriptExecutor executor = (JavascriptExecutor) getDriver();
                executor.executeScript("arguments[0].click();", ele);

                System.out.println("✅ JavaScript click successful for: " + fieldname);
                isClicked = true;
                break;

            } catch (NoSuchElementException e) {
                attempts++;
                System.err.println("❌ Attempt " + attempts + " - Element not found for JS click: " + fieldname);
                e.printStackTrace();
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Element not found after " + maxAttempts + " attempts for JS click: " + fieldname, e);
                }

            } catch (StaleElementReferenceException e) {
                attempts++;
                System.err.println("⚠️ Attempt " + attempts + " - StaleElementReferenceException for JS click: " + fieldname);
                e.printStackTrace();
                // Since we use WebElement, we cannot re-locate. Log it.
                System.err.println("⚠️ Cannot re-fetch element. WebElement reference only.");

            } catch (ElementNotInteractableException e) {
                System.err.println("⚠️ ElementNotInteractableException during JS click for: " + fieldname);
                e.printStackTrace();

            } catch (TimeoutException e) {
                System.err.println("❌ TimeoutException during JS click for: " + fieldname);
                e.printStackTrace();
                break;

            } catch (WebDriverException e) {
                System.err.println("❌ WebDriverException during JS click for: " + fieldname);
                e.printStackTrace();
                break;

            } catch (Exception e) {
                System.err.println("❌ Unexpected exception during JS click for: " + fieldname);
                e.printStackTrace();
                break;
            }
        }

        if (!isClicked) {
            System.err.println("❌ Failed to click using JavaScript on: " + fieldname + " after " + maxAttempts + " attempts.");
        }
    }
*/
/*    public void js_Click(WebElement ele, String fieldname) {
        int attempts = 0;
        int maxAttempts = 3;
        boolean isClicked = false;

        while (attempts < maxAttempts) {
            try {
                HighlightElement(ele);
                fluentWait(ele);
                JavascriptExecutor executor = (JavascriptExecutor) getDriver();
                executor.executeScript("arguments[0].click();", ele);

                String successMsg = "✅ JavaScript click successful for: " + fieldname;
                // Allure.step(successMsg);

                isClicked = true;
                break;

            } catch (NoSuchElementException e) {
                attempts++;
                String errorMsg = "❌ Attempt " + attempts + " - Element not found for JS click: " + fieldname;
                System.err.println(errorMsg);
                e.printStackTrace();
                Allure.step(errorMsg);
                if (attempts >= maxAttempts) {
                    String failMsg = "Element not found after " + maxAttempts + " attempts for JS click: " + fieldname;
                    Allure.step(failMsg);
                    throw new RuntimeException(failMsg, e);
                }
                printError(ele, fieldname, "NoSuchElementException", e);

            } catch (StaleElementReferenceException e) {
                attempts++;
                String staleMsg = "⚠️ Attempt " + attempts + " - StaleElementReferenceException for JS click: " + fieldname;
                System.err.println(staleMsg);
                e.printStackTrace();
                Allure.step(staleMsg);
                System.err.println("⚠️ Cannot re-fetch element. WebElement reference only.");

            } catch (ElementNotInteractableException e) {
                String eniMsg = "⚠️ ElementNotInteractableException during JS click for: " + fieldname;
                System.err.println(eniMsg);
                e.printStackTrace();
                Allure.step(eniMsg);
                printError(ele, fieldname, "Element Not Interactable - Exception", e);
            } catch (TimeoutException e) {
                String timeoutMsg = "❌ TimeoutException during JS click for: " + fieldname;
                System.err.println(timeoutMsg);
                e.printStackTrace();
                Allure.step(timeoutMsg);
                printError(ele, fieldname, "Timeout - Exception", e);
                break;

            } catch (WebDriverException e) {
                String webdriverMsg = "❌ WebDriverException during JS click for: " + fieldname;
                System.err.println(webdriverMsg);
                e.printStackTrace();
                Allure.step(webdriverMsg);
                break;

            } catch (Exception e) {
                String unexpectedMsg = "❌ Unexpected exception during JS click for: " + fieldname;
                System.err.println(unexpectedMsg);
                e.printStackTrace();
                Allure.step(unexpectedMsg);
                break;
            }
        }

        if (!isClicked) {
            String failClickMsg = "❌ Failed to click using JavaScript on: " + fieldname + " after " + maxAttempts + " attempts.";
            System.err.println(failClickMsg);
            Allure.step(failClickMsg);
        }
    }*/
//    public void js_Click(WebElement ele, String fieldname) {
//        int attempts = 0;
//        boolean clicked = false;
//
//        while (attempts < 3) {
//            try {
//                attempts++;
//                HighlightElement(ele);
//                fluentWait(ele);
//                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", ele);
//                String msg = "✅ Step Passed: Clicked element using JavaScript - " + fieldname;
//                clicked = true;
//                break;
//
//            } catch (Exception e) {
//                String reason = e.getClass().getSimpleName();
//                String msg = String.format("❌ Attempt %d: Failed to click '%s' due to [%s] - %s",
//                        attempts, fieldname, reason, e.getMessage());
//
//                System.err.println(msg);
//                Allure.step(msg);
//
//                if (attempts == 3 || e instanceof TimeoutException || e instanceof WebDriverException) {
//                    String finalFail = "❌ Step Failed: Could not click '" + fieldname + "' after 3 attempts.";
//                    Allure.step(finalFail);
//                    throw new RuntimeException(finalFail, e);
//                }
//            }
//        }
//
//        if (!clicked) {
//            Allure.step("❌ Click action on '" + fieldname + "' was unsuccessful.");
//        }
//    }
    public void js_Click(WebElement ele, String fieldName) {
        int attempts = 0;
        boolean clicked = false;

        while (attempts < 3) {
            try {
                attempts++;
                HighlightElement(ele);
                fluentWait(ele);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", ele);

                String msg = "✅ Clicked on '" + fieldName + "' successfully (using JavaScript).";
                Allure.step(msg);
                clicked = true;
                break;

            } catch (TimeoutException e) {
                String msg = "❌ Attempt " + attempts + ": Timed out while waiting to click on '" + fieldName + "'.";
                Allure.step(msg);
                printError(ele, fieldName, "TimeoutException", e);

            } catch (StaleElementReferenceException e) {
                String msg = "❌ Attempt " + attempts + ": '" + fieldName + "' was not stable on the page (reloaded or changed).";
                Allure.step(msg);
                printError(ele, fieldName, "StaleElementReferenceException", e);

            } catch (ElementNotInteractableException e) {
                String msg = "❌ Attempt " + attempts + ": '" + fieldName + "' is present but not interactable.";
                Allure.step(msg);
                printError(ele, fieldName, "ElementNotInteractableException", e);

            } catch (WebDriverException e) {
                String msg = "❌ Attempt " + attempts + ": Browser/driver issue prevented clicking on '" + fieldName + "'.";
                Allure.step(msg);
                printError(ele, fieldName, "WebDriverException", e);

            } catch (Exception e) {
                String msg = "❌ Attempt " + attempts + ": Unexpected error while clicking on '" + fieldName + "'.";
                Allure.step(msg);
                printError(ele, fieldName, "UnexpectedException", e);
            }
        }

        if (!clicked) {
            String failMsg = "❌ Step Failed: Could not click '" + fieldName + "' even after 3 attempts.";
            Allure.step(failMsg);
            throw new RuntimeException(failMsg); // keep for framework failure tracking
        }
    }

    public void js_Scroll_And_Click(WebElement ele, String fieldName) {
        int attempts = 0;
        boolean clicked = false;

        while (attempts < 3) {
            try {
                attempts++;
                HighlightElement(ele);
                fluentWait(ele);

                JavascriptExecutor js = (JavascriptExecutor) getDriver();
                // Scroll element into view
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", ele);
                // Click element using JS
                js.executeScript("arguments[0].click();", ele);

                String msg = "✅ Scrolled and clicked on '" + fieldName + "' successfully (using JavaScript).";
                Allure.step(msg);
                clicked = true;
                break;

            } catch (TimeoutException e) {
                String msg = "❌ Attempt " + attempts + ": Timed out while waiting to scroll and click on '" + fieldName + "'.";
                Allure.step(msg);
                printError(ele, fieldName, "TimeoutException", e);

            } catch (StaleElementReferenceException e) {
                String msg = "❌ Attempt " + attempts + ": '" + fieldName + "' was not stable on the page (reloaded or changed) during scroll/click.";
                Allure.step(msg);
                printError(ele, fieldName, "StaleElementReferenceException", e);

            } catch (ElementNotInteractableException e) {
                String msg = "❌ Attempt " + attempts + ": '" + fieldName + "' is present but not interactable during scroll/click.";
                Allure.step(msg);
                printError(ele, fieldName, "ElementNotInteractableException", e);

            } catch (WebDriverException e) {
                String msg = "❌ Attempt " + attempts + ": Browser/driver issue prevented scrolling/clicking on '" + fieldName + "'.";
                Allure.step(msg);
                printError(ele, fieldName, "WebDriverException", e);

            } catch (Exception e) {
                String msg = "❌ Attempt " + attempts + ": Unexpected error while scrolling and clicking on '" + fieldName + "'.";
                Allure.step(msg);
                printError(ele, fieldName, "UnexpectedException", e);
            }
        }

        if (!clicked) {
            String failMsg = "❌ Step Failed: Could not scroll and click '" + fieldName + "' even after 3 attempts.";
            Allure.step(failMsg);
            throw new RuntimeException(failMsg);
        }
    }

    public void js_Scroll_And_NormalClick(WebElement ele, String fieldName) {
        int attempts = 0;
        boolean clicked = false;

        while (attempts < 3) {
            try {
                attempts++;
                HighlightElement(ele);
                fluentWait(ele);

                JavascriptExecutor js = (JavascriptExecutor) getDriver();
                // Scroll element into view
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", ele);
                ele.click();

                String msg = "✅ Scrolled and clicked on '" + fieldName + "' successfully (using JavaScript).";
                Allure.step(msg);
                clicked = true;
                break;

            } catch (TimeoutException e) {
                String msg = "❌ Attempt " + attempts + ": Timed out while waiting to scroll and click on '" + fieldName + "'.";
                Allure.step(msg);
                printError(ele, fieldName, "TimeoutException", e);

            } catch (StaleElementReferenceException e) {
                String msg = "❌ Attempt " + attempts + ": '" + fieldName + "' was not stable on the page (reloaded or changed) during scroll/click.";
                Allure.step(msg);
                printError(ele, fieldName, "StaleElementReferenceException", e);

            } catch (ElementNotInteractableException e) {
                String msg = "❌ Attempt " + attempts + ": '" + fieldName + "' is present but not interactable during scroll/click.";
                Allure.step(msg);
                printError(ele, fieldName, "ElementNotInteractableException", e);

            } catch (WebDriverException e) {
                String msg = "❌ Attempt " + attempts + ": Browser/driver issue prevented scrolling/clicking on '" + fieldName + "'.";
                Allure.step(msg);
                printError(ele, fieldName, "WebDriverException", e);

            } catch (Exception e) {
                String msg = "❌ Attempt " + attempts + ": Unexpected error while scrolling and clicking on '" + fieldName + "'.";
                Allure.step(msg);
                printError(ele, fieldName, "UnexpectedException", e);
            }
        }

        if (!clicked) {
            String failMsg = "❌ Step Failed: Could not scroll and click '" + fieldName + "' even after 3 attempts.";
            Allure.step(failMsg);
            throw new RuntimeException(failMsg);
        }
    }


    public void js_Click(By locator, String fieldname) {
        int attempts = 0;
        boolean clicked = false;

        while (attempts < 3) {
            try {
                attempts++;
                WebElement ele = getDriver().findElement(locator); // Relocate element on each attempt
                fluentWait(ele);
                HighlightElement(ele);

                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", ele);
                String msg = "✅ Step Passed: Clicked element using JavaScript - " + fieldname;
                clicked = true;
                break;

            } catch (StaleElementReferenceException | TimeoutException | ElementNotInteractableException e) {

                String reason = e.getClass().getSimpleName();
                String msg = String.format("❌ Attempt %d: Failed to click '%s' due to [%s] - %s",
                        attempts, fieldname, reason, e.getMessage());

                System.err.println(msg);
                Allure.step(msg);

                // Optional small wait before retry
                delayWithGivenTime(500); // Add your own sleep method or Thread.sleep
            } catch (Exception e) {
                String msg = "❌ Unexpected failure while clicking '" + fieldname + "': " + e.getMessage();
                System.err.println(msg);
                Allure.step(msg);
                throw new RuntimeException(msg, e);
            }
        }

        if (!clicked) {
            String finalFail = "❌ Step Failed: Could not click '" + fieldname + "' after 3 attempts.";
            System.err.println(finalFail);
            Allure.step(finalFail);
            throw new RuntimeException(finalFail);
        }
    }


    protected void logError(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
    }

    @Override
    public void append(WebElement ele, String data) {
        try {
            HighlightElement(ele);
            ele.sendKeys(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionClear(WebElement ele) {
        HighlightElement(ele);
        try {
            Actions action = new Actions(getDriver());
            action.click();
            action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE);
            action.perform();
            String currentValue = ele.getAttribute("value");
            System.out.println("Verify Current value after clearing: " + currentValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DoubleClick(WebElement ele) {
        try {
            Actions actions = new Actions(getDriver());
            fluentWait(ele);
            actions.doubleClick(ele).build().perform();
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while performing Double Click And Type: " + e.getMessage());
        }
    }

    /**
     * This method is used to perform double click action
     *
     * @param ele
     * @param fieldname
     * @Author: Sakrateesh R
     */
    public void Double_Click(WebElement ele, String fieldname) {
        try {
            if (ele == null) {
                throw new IllegalArgumentException("WebElement is null for field: " + fieldname);
            }
            JavascriptExecutor JS = (JavascriptExecutor) getDriver();
            JS.executeScript("arguments[0].style.border='3px solid red'", ele);

           /* Actions actions = new Actions(getDriver());
            fluentWait(ele);
            actions.doubleClick(ele).build().perform();*/
            ele.click();
            delayWithGivenTime(200);
            ele.click();
        } catch (TimeoutException e) {
            printError(ele, fieldname, "Timeout occurred while waiting for element: " + e.getMessage(), e);
        } catch (ElementNotInteractableException e) {
            printError(ele, fieldname, "Element not interactable: " + e.getMessage(), e);
        } catch (NoSuchElementException e) {
            printError(ele, fieldname, "Element not found: " + e.getMessage(), e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldname, "Stale element reference: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            printError(null, fieldname, "Invalid argument: " + e.getMessage(), e);
        } catch (Exception e) {
            printError(ele, fieldname, "Unexpected error while performing Double Click And Type: " + e.getMessage(), e);
        }

    }

    /**
     * Performs a double click on the given WebElement using JavaScriptExecutor.
     *
     * @param fieldname The fieldname of element.
     * @param element   The WebElement to double-click.
     */
    public void doubleClickJS(WebElement ele, String fieldname) {
        try {
            if (ele == null) {
                throw new IllegalArgumentException("WebElement is null for field: " + fieldname);
            }
            JavascriptExecutor js = (JavascriptExecutor) getDriver();

            String javaScript = "var evt = new MouseEvent('dblclick', { " +
                    "bubbles: true, cancelable: true, view: window });" +
                    "arguments[0].dispatchEvent(evt);";
            js.executeScript("arguments[0].style.border='3px solid red'", ele);

            js.executeScript(javaScript, ele);
        } catch (TimeoutException e) {
            printError(ele, fieldname, "Timeout occurred while waiting for element: " + e.getMessage(), e);
        } catch (ElementNotInteractableException e) {
            printError(ele, fieldname, "Element not interactable: " + e.getMessage(), e);
        } catch (NoSuchElementException e) {
            printError(ele, fieldname, "Element not found: " + e.getMessage(), e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldname, "Stale element reference: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            printError(null, fieldname, "Invalid argument: " + e.getMessage(), e);
        } catch (Exception e) {
            printError(ele, fieldname, "Unexpected error while performing Double Click And Type: " + e.getMessage(), e);
        }
    }


    public void DoubleClickAndType(WebElement ele, String data) {
        try {
            ele.clear();
            HighlightElement(ele);
            Actions actions = new Actions(getDriver());
            explicitWait(ele);
            actions.doubleClick(ele).build().perform();
            ele.sendKeys(data);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while Double Click And Type: " + e.getMessage());
        }
    }

    /**
     * Clicks on an element using JavaScript and types the specified data into it.
     * This method also scrolls the element into view and highlights it with a red border.
     *
     * @param ele       The WebElement to interact with.
     * @param data      The text to be entered into the element.
     * @param fieldname The name of the field (used for logging and error messages).
     * @throws TimeoutException                If the element takes too long to load.
     * @throws ElementNotInteractableException If the element is not interactable.
     * @throws NoSuchElementException          If the element cannot be found.
     * @throws StaleElementReferenceException  If the element reference is stale.
     * @throws IllegalArgumentException        If the provided WebElement is null.
     * @throws Exception                       If an unexpected error occurs.
     */
    public void js_ClickAndType(WebElement ele, String data, String fieldname) {
        try {
            if (ele == null) {
                String nullMsg = "WebElement is null for field: " + fieldname;
                //Allure.step(nullMsg);
                throw new IllegalArgumentException(nullMsg);
            }

            explicitWait(ele, 10);
            JavascriptExecutor js = (JavascriptExecutor) getDriver();

            js.executeScript("arguments[0].style.border='3px solid red'", ele);

            // Clear existing value and set new value using JS
            js.executeScript("arguments[0].value='';", ele);
            js.executeScript("arguments[0].value=arguments[1];", ele, data);

            Allure.step("Successfully Entered the data in field '" + fieldname + "' : " + data);

        } catch (TimeoutException e) {
            String msg = "Timeout occurred while waiting for element '" + fieldname + "': " + e.getMessage();
            Allure.step(msg);
            printError(ele, fieldname, msg, e);
        } catch (ElementNotInteractableException e) {
            String msg = "Element not interactable for field '" + fieldname + "': " + e.getMessage();
            Allure.step(msg);
            printError(ele, fieldname, msg, e);
        } catch (NoSuchElementException e) {
            String msg = "Element not found for field '" + fieldname + "': " + e.getMessage();
            Allure.step(msg);
            printError(ele, fieldname, msg, e);
        } catch (StaleElementReferenceException e) {
            String msg = "Stale element reference for field '" + fieldname + "': " + e.getMessage();
            Allure.step(msg);
            printError(ele, fieldname, msg, e);
        } catch (IllegalArgumentException e) {
            String msg = "Invalid argument for field '" + fieldname + "': " + e.getMessage();
            Allure.step(msg);
            printError(null, fieldname, msg, e);
        } catch (Exception e) {
            String msg = "Unexpected error while performing JS Click And Type on field '" + fieldname + "': " + e.getMessage();
            Allure.step(msg);
            printError(ele, fieldname, msg, e);
        }
    }


    /**
     * Performs a double-click action on the specified WebElement, clears any existing text,
     * highlights the element, and then types the given data into it.
     *
     * @param ele       The WebElement to be interacted with.
     * @param data      The text to be entered after the double-click.
     * @param fieldname The name of the field for better error tracking.
     */

    public void Double_Click_And_Type(WebElement ele, String data, String fieldname) {
        int retryCount = 3;
        int attempts = 0;
        boolean success = false;

        if (ele == null) {
            String nullMsg = "WebElement is null for field: " + fieldname;
            //Allure.step(nullMsg);
            throw new IllegalArgumentException(nullMsg);
        }

        while (attempts < retryCount) {
            attempts++;
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(ele));

                ele.clear();

                HighlightElement(ele);

                delayWithGivenTime(300);  // Consider replacing with explicit wait if possible

                //Allure.step("Performing double-click action on '" + fieldname + "'");
                Actions actions = new Actions(getDriver());
                actions.doubleClick(ele).perform();

                delayWithGivenTime(300);  // Again, explicit wait preferable over fixed delay

                ele.sendKeys(Keys.BACK_SPACE);

                delayWithGivenTime(200);

                ele.sendKeys(data);

                success = true;
                Allure.step("Successfully Entered the data in field '" + fieldname + "' : " + data);
                break; // success, exit retry loop
            } catch (StaleElementReferenceException e) {
                String msg = "StaleElementReferenceException on attempt " + attempts + " for field '" + fieldname + "'";
                Allure.step(msg);
                printError(ele, fieldname, msg, e);
                // Refetch element if possible before retrying (not shown here)
            } catch (TimeoutException e) {
                String msg = "TimeoutException on attempt " + attempts + " for field '" + fieldname + "'";
                Allure.step(msg);
                printError(ele, fieldname, msg, e);
            } catch (ElementNotInteractableException e) {
                String msg = "ElementNotInteractableException on attempt " + attempts + " for field '" + fieldname + "'";
                Allure.step(msg);
                printError(ele, fieldname, msg, e);
                break; // usually not retryable
            } catch (NoSuchElementException e) {
                String msg = "NoSuchElementException on attempt " + attempts + " for field '" + fieldname + "'";
                Allure.step(msg);
                printError(ele, fieldname, msg, e);
                break; // usually not retryable
            } catch (IllegalArgumentException e) {
                String msg = "IllegalArgumentException for field '" + fieldname + "': " + e.getMessage();
                Allure.step(msg);
                printError(null, fieldname, msg, e);
                break;
            } catch (Exception e) {
                String msg = "Unexpected exception on attempt " + attempts + " for field '" + fieldname + "'";
                Allure.step(msg);
                printError(ele, fieldname, msg, e);
                break;
            }
        }

        if (!success) {
            String failMsg = "Failed to perform double click and type on '" + fieldname + "' after " + retryCount + " attempts";
            Allure.step(failMsg);
            throw new RuntimeException(failMsg);
        }
    }

    @Override
    /**
     * Clicks on the given element, clears it, highlights it, and types the provided data.
     * Retries in case of a StaleElementReferenceException.
     *
     * @param ele  The WebElement to interact with.
     * @param data The data to be entered into the element.
     * @throws RuntimeException if the action fails after retries.
     * @Author: Balaji N
     */
    public void clickAndType(WebElement ele, String data) {
        int retryCount = 3; // Number of retries in case of StaleElementReferenceException

        for (int attempt = 1; attempt <= retryCount; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(ele));

                ele.clear();
                HighlightElement(ele);
                ele.click();
                ele.sendKeys(data);

                // Allure.step("Successfully Entered the data in field '" + fieldname + "' : " + data);
                return; // Successfully executed, exit the loop

            } catch (StaleElementReferenceException e) {
                String msg = "Attempt " + attempt + " - StaleElementReferenceException for element: " + ele.toString();
                Allure.step(msg);
                logError(msg, e);
                if (attempt == retryCount) {
                    String failMsg = "StaleElementReferenceException after " + retryCount + " retries: " + e.getMessage();
                    Allure.step(failMsg);
                    throw new RuntimeException(failMsg, e);
                }
            } catch (NoSuchElementException e) {
                String msg = "Element not found: " + ele.toString();
                Allure.step(msg);
                logError(msg, e);
                throw new RuntimeException(msg, e);

            } catch (ElementNotInteractableException e) {
                String msg = "Element is not interactable: " + ele.toString();
                Allure.step(msg);
                logError(msg, e);
                throw new RuntimeException(msg, e);

            } catch (TimeoutException e) {
                String msg = "Timeout waiting for element to be clickable: " + ele.toString();
                Allure.step(msg);
                logError(msg, e);
                throw new RuntimeException(msg, e);

            } catch (WebDriverException e) {
                String msg = "WebDriver exception while performing click and type: " + ele.toString();
                Allure.step(msg);
                logError(msg, e);
                throw new RuntimeException(msg, e);

            } catch (Exception e) {
                String msg = "Unexpected error while performing click and type: " + ele.toString();
                Allure.step(msg);
                logError(msg, e);
                throw new RuntimeException(msg, e);
            }
        }
    }

    /**
     * Clicks on the given element, clears it, and types the provided data.
     * Retries in case of a StaleElementReferenceException.
     *
     * @param ele       The WebElement to interact with.
     * @param data      The data to be entered into the element.
     * @param fieldname The name of the field for logging purposes.
     */
    public void ClickAndType(WebElement ele, String data, String fieldname) {
        int retryCount = 3;
        for (int attempt = 1; attempt <= retryCount; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(ele));
                ele.click();
                ele.clear();
                ele.sendKeys(data);
                Allure.step("Successfully Entered the data in field '" + fieldname + "' : " + data);
                return;

            } catch (StaleElementReferenceException e) {
                String msg = "Attempt " + attempt + " - StaleElementReferenceException for field: " + fieldname;
                Allure.step(msg);
                logError(msg, e);
                if (attempt == retryCount) {
                    printError(ele, fieldname, "StaleElementReference exception error while performing click and type: " + e.getMessage(), e);
                }
            } catch (NoSuchElementException e) {
                String msg = "NoSuchElementException while performing click and type on field '" + fieldname + "'";
                Allure.step(msg);
                printError(ele, fieldname, msg, e);
            } catch (ElementNotInteractableException e) {
                String msg = "ElementNotInteractable exception while performing click and type on field '" + fieldname + "'";
                Allure.step(msg);
                printError(ele, fieldname, msg, e);
                throw new RuntimeException(msg, e);
            } catch (TimeoutException e) {
                String msg = "TimeoutException while performing click and type on field '" + fieldname + "'";
                Allure.step(msg);
                printError(ele, fieldname, msg, e);
            } catch (WebDriverException e) {
                String msg = "WebDriverException while performing click and type on field '" + fieldname + "'";
                Allure.step(msg);
                printError(ele, fieldname, msg, e);
            } catch (Exception e) {
                String msg = "Unexpected error while performing click and type on field '" + fieldname + "'";
                Allure.step(msg);
                printError(ele, fieldname, msg, e);
            }
        }
    }


    /**
     * Logs and throws a runtime exception when an error occurs.
     *
     * @param locator       The WebElement where the error occurred.
     * @param fieldName     The name of the field related to the error.
     * @param exceptionType The type of exception encountered.
     * @param e             The original exception that was caught.
     * @return
     * @throws RuntimeException with detailed error information.
     */
    public void printError(WebElement ele, String fieldName, String exceptionType, Exception e) {
        String elementLocator = ele.toString();
        String errorMsg = String.format(
                "\n\n" + "Field Name: %s\n\n" + "" +
                        "DOM - Element: %s\n\n" + "" +
                        "Exception Type: %s\n\n" + "" +
                        "Error log Message: %s\n\n" + "",
                fieldName, elementLocator, exceptionType, e.getMessage());
        System.err.println(errorMsg);
        throw new RuntimeException(errorMsg);
    }

    /**
     * Highlights the given WebElement, and get the style attribute of the element.
     *
     * @param ele
     * @param fieldName
     * @return
     * @Author Balaji N
     */
    public String getStyleElement(WebElement ele, String fieldName) {
        String style = null + fieldName + " : Unable to fetch style of element: " + ele.toString();
        try {
            HighlightElement(ele);
            style = ele.getAttribute("style");
        } catch (NoSuchElementException e) {
            printError(ele, fieldName, "NoSuchElementException while fetching style", e);
        } catch (Exception e) {
            printError(ele, fieldName, "Unexpected error while fetching style", e);
        }
        return style;
    }

    /**
     * Dynamically fetches locator type and value for the given WebElement.
     */
    private String getElementDetails(WebElement element) {
        String elementDetails = "Tag: " + element.getTagName();
        try {
            String[] elementParts = element.toString().split("->");
            if (elementParts.length > 1) {
                String locatorPart = elementParts[1].trim();
                elementDetails += " | Locator: " + locatorPart;
            }
        } catch (Exception e) {
            elementDetails += " | Locator: Unable to retrieve locator details.";
        }
        return elementDetails;
    }

    public void PrintError(WebElement element, String fieldName, String exceptionType, Exception e) {
        String elementDetails = getElementDetails(element);
        String errorMessage = String.format("Assertion Error: Expected [%s], but Actual Found [%s]%nField Name: %s%nElement: %s%nException Type: %s",
                "Expected Condition", "Actual Condition", fieldName, elementDetails, exceptionType);

        // Extent Report Logging with Screenshot
        String screenshotPath = TestBaseClass.captureScreenshotBase64();
        //ExtentReportManager.getTest().fail(errorMessage).addScreenCaptureFromBase64String(screenshotPath, "Test Step Failed");

        // Allure Report Logging with Screenshot
        byte[] screenshot = ((TakesScreenshot) TestBaseClass.getDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.getLifecycle().addAttachment("Screenshot on Failure", "image/png", "png", screenshot);

        // Soft Assertion to Fail Test with Detailed Message
        CustomSoftAssert softAssert = new CustomSoftAssert();
        softAssert.fail(errorMessage);
    }

    public void print_Error(WebElement element, String fieldName, String exceptionType, Exception e) {
        System.err.println("Error while interacting with element: " + element);
        System.err.println("Field Name: " + fieldName);
        System.err.println("Exception Type: " + exceptionType);
        System.err.println("Exception Message: " + e.getMessage());
        e.printStackTrace();
    }

    public String getElementLocator(WebElement element) {
        String elementDescription = element.toString();
        // Example: '[[ChromeDriver: chrome on WINDOWS (abcd1234)] -> xpath: //select[@id='paymentOptions']]'

        if (elementDescription.contains("->")) {
            return elementDescription.substring(elementDescription.indexOf("->") + 3, elementDescription.length() - 1);
        } else {
            return "Locator not found";
        }
    }


    /**
     * It retrieves the text of the specified WebElement and returns it as a String with highlighting.
     *
     * @param ele       Element to get text
     * @param fieldName Name of the field
     * @return It returns the text of the WebElement if displayed and enabled, otherwise it returns "Error: Unable to retrieve text from " + fieldName
     * @Author Balaji N
     */
//    public String getElementText(WebElement ele, String fieldName) {
//        try {
//            HighlightElement(ele);
//            String text = ele.getText();
//            return text;
//        } catch (TimeoutException e) {
//            String msg = "TimeoutException while retrieving text from '" + fieldName + "'";
//            Allure.step(msg);
//            printError(ele, fieldName, msg, e);
//        } catch (NoSuchElementException e) {
//            String msg = "NoSuchElementException while retrieving text from '" + fieldName + "'";
//            Allure.step(msg);
//            printError(ele, fieldName, msg, e);
//        } catch (StaleElementReferenceException e) {
//            String msg = "StaleElementReferenceException while retrieving text from '" + fieldName + "'";
//            Allure.step(msg);
//            printError(ele, fieldName, msg, e);
//
//        } catch (Exception e) {
//            String msg = "UnexpectedException while retrieving text from '" + fieldName + "'";
//            Allure.step(msg);
//            printError(ele, fieldName, msg, e);
//        }
//
//        return "Error: Unable to retrieve text from\n Field Name : " + fieldName + " \n| Element : " + ele;
//    }
    public String getElementText(WebElement ele, String fieldName) {
        int retryCount = 0;
        while (retryCount < 2) {
            try {
                HighlightElement(ele);
                Allure.step("✅ Retrieving the element for the field: **" + fieldName + "** - text successfully.");
                return ele.getText();

            } catch (StaleElementReferenceException e) {
                retryCount++;
                if (retryCount == 1) {
                    Allure.step("⚠ The element for **" + fieldName + "** refreshed/changed in the page. Retrying once...");
                    ele = reLocateElement(ele);
                } else {
                    attachElementErrorToAllure(fieldName, ele, e,
                            "❌ Could not read text for **" + fieldName + "** because the element disappeared from the page.\n"
                                    + "ℹ️ Possible reasons: The content (like a toaster message) vanished before it could be read.");
                    throw new RuntimeException("Unable to retrieve text for field: " + fieldName, e);
                }

            } catch (TimeoutException e) {
                attachElementErrorToAllure(fieldName, ele, e,
                        "❌ The system waited too long for the field **" + fieldName + "** to appear.\n"
                                + "ℹ️ Possible reasons: Page took longer than expected, or the element is hidden.");
                throw new RuntimeException("Timeout retrieving text for field: " + fieldName, e);

            } catch (NoSuchElementException e) {
                attachElementErrorToAllure(fieldName, ele, e,
                        "❌ Could not find the field **" + fieldName + "** on the page.\n"
                                + "ℹ️ Possible reasons: Locator mismatch, or the element is not visible anymore.");
                throw new RuntimeException("Element not found for field: " + fieldName, e);

            } catch (Exception e) {
                attachElementErrorToAllure(fieldName, ele, e,
                        "❌ Unexpected error while retrieving text for **" + fieldName + "**.\n"
                                + "ℹ️ Check logs or screenshot for details.");
                throw new RuntimeException("Unexpected exception retrieving text for field: " + fieldName, e);
            }
        }
        return null;
    }


    /**
     * Re-locates a WebElement from its original locator to handle stale references.
     */
    private WebElement reLocateElement(WebElement ele) {
        if (ele instanceof RemoteWebElement) {
            String locatorString = ele.toString();
            String xpath = locatorString.substring(locatorString.indexOf("xpath: ") + 7, locatorString.length() - 1);
            return getDriver().findElement(By.xpath(xpath));
        }
        return ele; // If not possible to re-locate, return original
    }

    /**
     * Attaches detailed element retrieval failure info to Allure.
     */
    private void attachElementErrorToAllure(String fieldName, WebElement ele, Exception e, String possibleCauses) {
        String message = "\n"
                + "❌ [GET ELEMENT TEXT FAILURE]\n"
                + "Field Name: " + fieldName + "\n"
                + "Element   : " + ele.toString() + "\n"
                + "Exception : " + e.getClass().getSimpleName() + "\n"
                + "\n"
                + "🔍 POSSIBLE CAUSES:\n" + possibleCauses + "\n";

        Allure.addAttachment("Element Text Retrieval Error - " + fieldName, message);
    }


    /**
     * It retrieves the text of the specified WebElement and returns it as a String without scrolling and highlighting the element.
     *
     * @param ele       Element to get text
     * @param fieldName Name of the field
     * @return It returns the text of the WebElement if displayed and enabled, otherwise it returns "Error: Unable to retrieve text from " + fieldName
     * @Author Balaji N
     */
    public String get_Element_Text(WebElement ele, String fieldName) {
        try {
            JavascriptExecutor JS = (JavascriptExecutor) getDriver();
            JS.executeScript("arguments[0].style.border='3px solid red'", ele);
            return ele.getText();
        } catch (TimeoutException e) {
            printError(ele, fieldName, "TimeoutException", e);
        } catch (NoSuchElementException e) {
            printError(ele, fieldName, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldName, "StaleElementReferenceException", e);
        } catch (Exception e) {
            printError(ele, fieldName, "UnexpectedException", e);
        }
        return "Error: Unable to retrieve text from " + fieldName;
    }


    @Override
    public void actionType(WebElement ele, String data) {
        try {
            ele.clear();
            HighlightElement(ele);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            Actions action = new Actions(getDriver());
            action.moveToElement(ele).sendKeys(data).build().perform();
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void clearAndType(WebElement ele, String data) {
        try {
            ele.clear();
            HighlightElement(ele);
            ele.sendKeys(data);
        } catch (ElementNotInteractableException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }

    /**
     * Clears the value of the specified WebElement using JavaScript.
     * If an exception occurs, it is handled and logged.
     *
     * @param ele The WebElement whose value needs to be cleared.
     */
    public void jsClear(WebElement ele) {
        try {
            HighlightElement(ele);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
            jsExecutor.executeScript("arguments[0].value='';", ele);
        } catch (Exception e) {
            printError(ele, "jsClear", "JavaScriptExecutionException", e);
        }
    }

    /**
     * Clears the value of the specified WebElement using JavaScript.
     * If an exception occurs, it is handled and logged.
     *
     * @param ele The WebElement whose value needs to be cleared.
     */
    public void js_Clear(WebElement ele, String fieldName) {
        try {
            HighlightElement(ele);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
            jsExecutor.executeScript("arguments[0].value='';", ele);
        } catch (Exception e) {
            printError(ele, fieldName, "JavaScriptExecutionException while performing js_Clear", e);
        }
    }

    @Override
    /**
     * Clears the value of the specified WebElement and types the given data using JavaScript.
     * If an exception occurs, it is handled and logged.
     *
     * @param ele  The WebElement where text needs to be entered.
     * @param data The text to be typed into the WebElement.
     */
    public void jsClearAndType(WebElement ele, String data) {
        try {
            HighlightElement(ele);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
            jsExecutor.executeScript("arguments[0].value='';", ele);
            jsExecutor.executeScript("arguments[0].value=arguments[1];", ele, data);
        } catch (Exception e) {
            printError(ele, "jsClearAndType", "JavaScriptExecutionException", e);
        }
    }

    /**
     * Clears the value of the specified WebElement and types the given data using JavaScript.
     * If an exception occurs, it is handled and logged.
     *
     * @param ele  The WebElement where text needs to be entered.
     * @param data The text to be typed into the WebElement.
     */
    public void js_Clear_And_Type(WebElement ele, String data, String fieldname) {
        try {
            HighlightElement(ele);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
            jsExecutor.executeScript("arguments[0].value='';", ele);
            jsExecutor.executeScript("arguments[0].value=arguments[1];", ele, data);
        } catch (Exception e) {
            printError(ele, fieldname, "JavaScript Execution Exception while performing js_Clear_And_Type", e);
        }
    }

    @Override
    public void jsTypeAndEnter(WebElement ele, String data) {
        try {
            HighlightElement(ele);
            ele.clear();
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].value='" + data + "'", ele);
            ele.sendKeys(Keys.ENTER);
        } catch (ElementNotInteractableException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * Checks if the given WebElement is disabled.
     *
     * @param ele
     * @return If the WebElement is disabled, returns true; otherwise, returns false.
     * @Author Balaji N
     */
    public boolean IsDisabled(WebElement ele) {
        HighlightElement(ele);
        String disabledAttribute = ele.getAttribute("disabled");
        boolean isDisabled = disabledAttribute != null && disabledAttribute.equals("true");
        return isDisabled;
    }

    @Override
    public void jsScrollClickAndEnter(WebElement ele, String data) {
        try {
            HighlightElement(ele);
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].scrollIntoView();", ele);
            ele.clear();
            clickAndType(ele, data);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void ThreadWait(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    /**
     * Introduces a delay for the given time in milliseconds.
     * <p>
     * This method uses a combination of `Thread.sleep` and Robot's wait mechanism
     * to ensure precision and reliability. Handles any interruptions gracefully.
     * </p>
     *
     * @param timeInMillis the delay duration in milliseconds
     * @throws IllegalArgumentException if the given time is less than or equal to zero
     * @throws RuntimeException if the delay execution fails
     * @Author Balaji N
     */
    public void delayWithGivenTime(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid delay duration: " + e.getMessage());
            throw e; // Re-throwing to notify invalid usage
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted during delay: " + e.getMessage());
            throw new RuntimeException("Thread interruption occurred during delay.", e);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during delay: " + e.getMessage());
            throw new RuntimeException("Error occurred during delay.", e);
        }
    }


    /**
     * Fluent wait on specific element appears with given time
     *
     * @param ele - Specific element to be interacted
     * @param i   - Provided Timeout seconds to be entered
     * @param j   - Provided pooling every given timeout to be entered
     * @Description: This function uses the fluent wait selenium function for particular specific element will appears to interact with given time
     * @Author Balaji N
     */
    public void delayWithGivenTime(WebElement ele, int i, int j) {
        try {
            fluentWait(ele, i, j);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    /**
     * Press the enter key using actions class
     *
     * @Author Balaji N
     */
    public void RobotPressEnter() {
        try {
            Actions action = new Actions(getDriver());
            action.sendKeys(Keys.ENTER).build().perform();
        } catch (Exception e) {
            printError(null, "RobotPressEnter", "Error occurred while pressing the Enter key: " + e.getMessage(), e);
        }
    }

    /**
     * Simulates pressing the Enter key using Actions class in a thread-safe manner.
     */
    public void ActionPressEnter() {
        try {
           /* JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("document.activeElement.dispatchEvent(new KeyboardEvent('keydown', {key: 'Enter'}));");
*/
            Actions actions = new Actions(getDriver());
            actions.sendKeys(Keys.ENTER).build().perform();
        } catch (Exception e) {
            printError(null, "Action Press Enter", "Error occurred while pressing the Enter key: " + e.getMessage(), e);
        }
    }


    /**
     * Press the tab key using actions class
     *
     * @Author Balaji N
     */
    public void PressTabKey() {
        try {
            Actions action = new Actions(getDriver());
            action.sendKeys(Keys.TAB).build().perform();
        } catch (Exception e) {
            printError(null, "PressTabKey", "Error occurred while pressing the Tab key: " + e.getMessage(), e);
        }
    }

    /**
     * Press the escape key using actions class
     *
     * @Author Balaji N
     */
    public void PressEscapeKey() {
        try {
            Actions actions = new Actions(getDriver());
            actions.sendKeys(Keys.ESCAPE).perform();
        } catch (Exception e) {
            printError(null, "PressEscapeKey", "Error occurred while pressing the Escape key: " + e.getMessage(), e);
        }
    }

    // Singleton Robot instance protected with synchronization
    private static Robot robot;

    // Lock object to ensure thread safety
    private static final Object objlocker = new Object();

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("Failed to initialize Robot", e);
        }
    }

    // Method to simulate Escape key press in a thread-safe way
    public static void pressEscapeRobot() {
        synchronized (objlocker) {
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
        }
    }

    // Method to simulate Enter key press in a thread-safe way
    public static void pressEnterRobot() {
        synchronized (objlocker) {
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
    }

    public void ActionArrowDown() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: actionsEscapeKey - Press the escape key
     * @Description: This method is used to press the escape key using action class
     * @Author Balaji N
     */
    public void actionsEscapeKey() {
        try {
            delayWithGivenTime(1000);
            Actions actions = new Actions(getDriver());
            actions.sendKeys(Keys.ESCAPE).perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * Selects an option from a dropdown with retry in case of interaction failure.
     *
     * @param ele         The dropdown WebElement.
     * @param value       The value to select.
     * @param usingMethod The method to select by: "index", "value", or "VisibleText".
     */
    public void dropDown(WebElement ele, String value, String usingMethod) {
        if (ele == null) {
            throw new IllegalArgumentException("Dropdown WebElement cannot be null");
        }
        if (value == null || usingMethod == null) {
            throw new IllegalArgumentException("Value and usingMethod cannot be null");
        }

        int retryCount = 3; // Number of retries
        int retryInterval = 1000; // Time (in milliseconds) between retries

        for (int attempt = 1; attempt <= retryCount; attempt++) {
            try {
                Select select = new Select(ele);

                switch (usingMethod.toLowerCase()) { // Case-insensitive method matching
                    case "index":
                        try {
                            int index = Integer.parseInt(value);
                            select.selectByIndex(index);
                        } catch (NumberFormatException nfe) {
                            throw new IllegalArgumentException("Invalid index value: " + value, nfe);
                        }
                        break;

                    case "value":
                        select.selectByValue(value);
                        break;

                    case "visibletext":
                        jsClick(ele);
                        select.selectByVisibleText(value);
                        break;

                    default:
                        throw new UnsupportedOperationException(
                                "Unsupported selection method: " + usingMethod + ". Use 'index', 'value', or 'VisibleText'."
                        );
                }

                // Exit the retry loop if successful
                return;

            } catch (NoSuchElementException nse) {
                if (attempt < retryCount) {
                    System.err.println("Retrying interaction with dropdown (Attempt " + attempt + " of " + retryCount + ")");
                    try {
                        Thread.sleep(retryInterval); // Wait before retrying
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    System.err.println("Failed after " + retryCount + " attempts. No such element: " + ele);
                    throw nse;
                }
            } catch (Exception e) {
                System.out.println("DropDown Value : " + value + " not able to select");
                System.err.println("An error occurred during dropdown interaction: " + e.getMessage());
                throw e; // Re-throw unexpected exceptions
            }
        }
    }

    /**
     * Selects an option from a dropdown with retry in case of interaction failure.
     *
     * @param ele         The dropdown WebElement.
     * @param value       The value to select.
     * @param usingMethod The method to select by: "index", "value", or "VisibleText".
     */
 /*   public void drop_Down(WebElement ele, String value, String usingMethod, String fieldname) {
        if (ele == null) {
            throw new IllegalArgumentException("Dropdown WebElement cannot be null");
        }
        if (value == null || usingMethod == null) {
            throw new IllegalArgumentException("Value and usingMethod cannot be null");
        }

        int retryCount = 3;
        int retryInterval = 1000;

        for (int attempt = 1; attempt <= retryCount; attempt++) {
            try {
                Select select = new Select(ele);

                switch (usingMethod.toLowerCase()) {
                    case "index":
                        try {
                            int index = Integer.parseInt(value);
                            select.selectByIndex(index);
                        } catch (NumberFormatException nfe) {
                            throw new IllegalArgumentException("Invalid index value: " + value, nfe);
                        }
                        break;

                    case "value":
                        select.selectByValue(value);
                        break;

                    case "visibletext":
                        js_Click(ele, fieldname);
                        select.selectByVisibleText(value);
                        break;

                    default:
                        throw new UnsupportedOperationException(
                                "Unsupported selection method: " + usingMethod + ". Use 'index', 'value', or 'VisibleText'."
                        );
                }
                return;

            } catch (NoSuchElementException nse) {
                if (attempt < retryCount) {
                    System.err.println("Retrying interaction with dropdown (Attempt " + attempt + " of " + retryCount + ")");
                    try {
                        Thread.sleep(retryInterval); // Wait before retrying
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        printError(ele, fieldname, "InterruptedException while waiting for retry", ie);
                    }
                } else {
                    System.err.println("Failed after " + retryCount + " attempts. No such element: " + ele);
                    //  throw nse;
                    printError(ele, fieldname, "Failed after " + retryCount + " attempts. No such element: " + ele, nse);
                }
            } catch (Exception e) {
                printError(ele, fieldname, "Failed after " + retryCount + " attempts. No such element: " + ele, e);
            }
        }
    }
*/
    public void Drop_Down(WebElement ele, String value, String usingMethod, String fieldname) {
        if (ele == null) {
            throw new IllegalArgumentException("Dropdown WebElement cannot be null");
        }
        if (value == null || usingMethod == null) {
            throw new IllegalArgumentException("Value and usingMethod cannot be null");
        }

        int retryCount = 3;
        int retryInterval = 1000;

        for (int attempt = 1; attempt <= retryCount; attempt++) {
            try {
                Select select = new Select(ele);
                boolean selected = false;

                switch (usingMethod.toLowerCase()) {
                    case "index":
                        try {
                            int index = Integer.parseInt(value);
                            select.selectByIndex(index);
                            selected = true;
                            // Allure.step("Selected dropdown '" + fieldname + "' by index: " + index);
                        } catch (NumberFormatException nfe) {
                            String msg = "Invalid index value: " + value;
                            Allure.step(msg);
                            throw new IllegalArgumentException(msg, nfe);
                        }
                        break;

                    case "value":
                        select.selectByValue(value);
                        selected = true;
                        // Allure.step("Selected dropdown '" + fieldname + "' by value: " + value);
                        break;

                    case "visibletext":
                        click(ele, fieldname);
                        select.selectByVisibleText(value);
                        selected = true;
                        Allure.step("Selected dropdown '" + fieldname + "' by visible text: " + value);
                        break;

                    default:
                        String msg = "Unsupported selection method: " + usingMethod + ". Use 'index', 'value', or 'VisibleText'.";
                        Allure.step(msg);
                        throw new UnsupportedOperationException(msg);
                }

                if (selected) {
                    // Allure.step("Dropdown option successfully selected for '" + fieldname + "'");
                } else {
                    Allure.step("Dropdown option not selected for '" + fieldname + "'");
                }
                return;

            } catch (NoSuchElementException nse) {
                Allure.step("NoSuchElementException on attempt " + attempt + " for dropdown '" + fieldname + "': " + nse.getMessage());
                if (attempt < retryCount) {
                    System.err.println("Retrying interaction with dropdown (Attempt " + attempt + " of " + retryCount + ")");
                    try {
                        Thread.sleep(retryInterval); // Wait before retrying
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        printError(ele, fieldname, "InterruptedException while waiting for retry", ie);
                        Allure.step("InterruptedException while waiting for retry: " + ie.getMessage());
                    }
                } else {
                    System.err.println("Failed after " + retryCount + " attempts. No such element: " + ele);
                    printError(ele, fieldname, "Failed after " + retryCount + " attempts. No such element: " + ele, nse);
                    Allure.step("Failed after " + retryCount + " attempts. No such element: " + ele);
                }
            } catch (Exception e) {
                Allure.step("Exception occurred: " + e.getMessage());
                printError(ele, fieldname, "Failed after " + retryCount + " attempts. Exception occurred", e);
            }
        }
    }

    /**
     * Retrieves the currently displayed (selected) value from a dropdown.
     * This method highlights the dropdown element for visibility and safely handles exceptions
     * to ensure that the automation flow is not interrupted if an issue occurs.
     *
     * @param ele The dropdown WebElement from which the selected value needs to be retrieved.
     * @return The text of the currently selected option in the dropdown, or an empty string if no option is selected.
     * @throws IllegalArgumentException If the provided WebElement is null.
     * @Author Balaji N
     */
    /**
     * Selects an option from a dropdown WebElement using the specified method ("index", "value", "visibletext").
     * Retries up to 3 times in case of interaction failure. Logs errors and exceptions with detailed messages.
     *
     * @param ele         The dropdown WebElement.
     * @param value       The value to select (index as String, value, or visible text).
     * @param usingMethod The method to select by: "index", "value", or "visibletext".
     * @param fieldname   The name of the field for logging purposes.
     * @throws IllegalArgumentException      If the element, value, or usingMethod is null, or index is invalid.
     * @throws UnsupportedOperationException If the selection method is not supported.
     * @author Balaji N
     */
    public void drop_Down(WebElement ele, String value, String usingMethod, String fieldname) {
        if (ele == null) {
            String msg = "Dropdown WebElement cannot be null for field: " + fieldname;
            System.err.println(msg);
            throw new IllegalArgumentException(msg);
        }
        if (value == null || usingMethod == null) {
            String msg = "Value and usingMethod cannot be null for field: " + fieldname;
            System.err.println(msg);
            throw new IllegalArgumentException(msg);
        }

        int retryCount = 3;
        int retryInterval = 1000;

        for (int attempt = 1; attempt <= retryCount; attempt++) {
            try {
                Select select = new Select(ele);
                boolean selected = false;

                switch (usingMethod.toLowerCase()) {
                    case "index":
                        try {
                            int index = Integer.parseInt(value);
                            select.selectByIndex(index);
                            selected = true;
                        } catch (NumberFormatException nfe) {
                            String msg = "Invalid index value '" + value + "' for dropdown '" + fieldname + "'";
                            System.err.println(msg);
                            Allure.step(msg);
                            throw new IllegalArgumentException(msg, nfe);
                        }
                        break;

                    case "value":
                        select.selectByValue(value);
                        selected = true;
                        break;

                    case "visibletext":
                        js_Click(ele, fieldname);
                        select.selectByVisibleText(value);
                        Allure.step("Selected dropdown '" + fieldname + "' by visible text: " + value);
                        selected = true;
                        break;

                    default:
                        String msg = "Unsupported selection method '" + usingMethod + "' for dropdown '" + fieldname + "'. Use 'index', 'value', or 'visibletext'.";
                        System.err.println(msg);
                        Allure.step(msg);
                        throw new UnsupportedOperationException(msg);
                }

                if (selected) {
                    String successMsg = "Dropdown option successfully selected for '" + fieldname + "' using method '" + usingMethod + "'";
                    //  System.out.println(successMsg);
                    //  Allure.step(successMsg);
                } else {
                    String failMsg = "Dropdown option : " + value + " not selected for '" + fieldname + "'";
                    System.err.println(failMsg);
                    Allure.step(failMsg);
                }
                return;

            } catch (NoSuchElementException nse) {
                String msg = "NoSuchElementException on attempt " + attempt + " for dropdown '" + fieldname + "': " + nse.getMessage();
                System.err.println(msg);
                Allure.step(msg);
                if (attempt < retryCount) {
                    System.err.println("Retrying interaction with dropdown (Attempt " + attempt + " of " + retryCount + ")");
                    try {
                        Thread.sleep(retryInterval);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        String interruptMsg = "InterruptedException while waiting for retry on dropdown '" + fieldname + "': " + ie.getMessage();
                        System.err.println(interruptMsg);
                        printError(ele, fieldname, interruptMsg, ie);
                        Allure.step(interruptMsg);
                    }
                } else {
                    String finalFailMsg = "Failed after " + retryCount + " attempts. No such element: " + ele + " for dropdown '" + fieldname + "'";
                    System.err.println(finalFailMsg);
                    printError(ele, fieldname, finalFailMsg, nse);
                    Allure.step(finalFailMsg);
                }
            } catch (Exception e) {
                String msg = "Exception occurred on attempt " + attempt + " for dropdown '" + fieldname + "': " + e.getMessage();
                System.err.println(msg);
                Allure.step(msg);
                printError(ele, fieldname, msg, e);
            }
        }
    }


    public String Get_Displayed_DropDown_Value(WebElement ele) {
        if (ele == null) {
            throw new IllegalArgumentException("Dropdown WebElement cannot be null");
        }

        try {
            Select select = new Select(ele);
            HighlightElement(ele); // Assuming HighlightElement is defined elsewhere
            return select.getFirstSelectedOption().getText();
        } catch (NoSuchElementException e) {
            System.err.println("No option is currently selected in the dropdown: " + e.getMessage());
            return ""; // Return an empty string if no option is selected
        } catch (Exception e) {
            System.err.println("An error occurred while retrieving the selected dropdown value: " + e.getMessage());
            return ""; // Return an empty string to avoid breaking the flow
        }
    }


    public void SelectDropDownVisibleText(WebElement ele, String value) {
        Select select = new Select(ele);
        select.selectByVisibleText(value);
    }

    @Override
    public void switchToAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            getDriver().switchTo().alert();
            getAlertText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void acceptAlert() {
        String text = "";
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            getDriver().switchTo().activeElement();
            Alert alert = getDriver().switchTo().alert();
            text = alert.getText();
            alert.accept();
        } catch (NoAlertPresentException e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("window.confirm = function(msg) { return true; }");
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
        }
        System.out.println("Displayed alert text is : " + text);
    }

    public void PressF8() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("var event = new KeyboardEvent('keydown', {keyCode: 119, which: 119}); document.dispatchEvent(event);");
    }

    public void action_PressF8() {
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.F8).perform();
    }

    public void Select_Text() {
        Actions actions = new Actions(getDriver());
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
    }

    /**
     * Accept the alert popup using javascript executor
     *
     * @Author Balaji N
     */
    public void JsAcceptAlert() {
        getDriver().switchTo().activeElement();
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.confirm = function(msg) { return true; }");
    }

    public void JsDismissAlert() {
        getDriver().switchTo().activeElement();
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.confirm = function(msg) { return false; }");
    }

    @Override
    public void dismissAlert() {
        String text = "";
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            getDriver().switchTo().activeElement();
            Alert alert = getDriver().switchTo().alert();
            text = alert.getText();
            alert.dismiss();

        } catch (NoAlertPresentException e) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("window.confirm = function(msg) { return false; }");
            //	RobotDismissAlert();
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("WebDriverException : " + e.getMessage());
        }
        System.out.println("Displayed alert text is : " + text);
    }

    @Override
    public String getAlertText() {
        String text = "";
        try {
            getDriver().switchTo().activeElement();
            Alert alert = getDriver().switchTo().alert();
            text = alert.getText();
        } catch (NoAlertPresentException e) {
            System.out.println("There is no alert present.");
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
        }
        return text;
    }

    public void RobotDismissAlert() {
        try {
            Actions actions = new Actions(getDriver());
            delayWithGivenTime(1000);
            actions.sendKeys(Keys.ESCAPE).perform();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public void ActionDismissAlert() {
        delayWithGivenTime(1000);
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    public void ActionAcceptAlert() {
        delayWithGivenTime(1000);
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.ARROW_LEFT).perform();
        delayWithGivenTime(1000);
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void ConsoleLog(String message) {
        System.out.println(message);
    }

    @Override
    public void explicitWait(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void explicitWait_for_ClickAction(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void explicitWait(WebElement ele, int i) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(i));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    @Override
    public void fluentWait(WebElement ele) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(40))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotInteractableException.class);

            wait.until(ExpectedConditions.visibilityOf(ele));
        } catch (TimeoutException e) {
            System.err.println("Timeout waiting for element to be visible: " + ele);
            e.printStackTrace();
            throw new RuntimeException("Timeout waiting for element to be visible", e);
        } catch (Exception e) {
            System.err.println("Unexpected error during fluentWait: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Unexpected error during fluentWait", e);
        }
    }

    private String suggestFixes(Exception e) {
        if (e instanceof NoSuchElementException) {
            return " - Element not found on the page\n - Locator may be outdated\n - Element may not be in the DOM yet";
        } else if (e instanceof StaleElementReferenceException) {
            return " - DOM changed after locating the element\n - Try waiting for page to stabilize";
        } else if (e instanceof ElementNotInteractableException) {
            return " - Element exists but is hidden or disabled\n - Check CSS/visibility";
        } else if (e instanceof TimeoutException) {
            return " - Element took too long to appear\n - Increase wait time or verify locator";
        } else {
            return " - Unknown issue, check application logs and network requests";
        }
    }


    /**
     * Waits for the specified WebElement to become visible using Fluent Wait.
     * <p>
     * This method allows for configurable timeout and polling intervals.
     * It ignores common exceptions that may occur during element retrieval.
     *
     * <p><b>Parameters:</b>
     * <ul>
     *   <li>{@code ele} - The WebElement to wait for.</li>
     *   <li>{@code timeoutInSeconds} - The maximum time (in seconds) to wait for the element to appear.</li>
     *   <li>{@code pollingInSeconds} - The interval (in seconds) at which to check for element visibility.</li>
     * </ul>
     *
     * <p><b>Possible Exceptions Handled:</b>
     * <ul>
     *   <li>{@link org.openqa.selenium.NoSuchElementException} - If the element is not found initially.</li>
     *   <li>{@link org.openqa.selenium.StaleElementReferenceException} - If the element becomes stale during wait.</li>
     *   <li>{@link org.openqa.selenium.TimeoutException} - If the element does not become visible within the timeout period.</li>
     *   <li>{@link org.openqa.selenium.WebDriverException} - If the WebDriver encounters any issue.</li>
     * </ul>
     *
     * @param ele              The WebElement to wait for
     * @param timeoutInSeconds Maximum time in seconds to wait
     * @param pollingInSeconds Polling interval in seconds
     * @throws TimeoutException If the element does not become visible within the specified time
     */
    public void fluentWait(WebElement ele, int timeoutInSeconds, int pollingInSeconds) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                    .pollingEvery(Duration.ofSeconds(pollingInSeconds))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(WebDriverException.class);
            wait.until(ExpectedConditions.visibilityOf(ele));
        } catch (TimeoutException e) {
            System.err.println("TimeoutException: Element not visible after " + timeoutInSeconds + " seconds.");
            e.printStackTrace();
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: Issue encountered with WebDriver while waiting for element.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Waits for the specified WebElement to become visible using Fluent Wait.
     * <p>
     * This method waits for the element within the given timeout and checks its visibility.
     * It ignores common exceptions like {@link NoSuchElementException} and {@link StaleElementReferenceException}.
     *
     * <p><b>Parameters:</b>
     * <ul>
     *   <li>{@code ele} - The WebElement to wait for.</li>
     *   <li>{@code timeoutInSeconds} - The maximum time (in seconds) to wait for the element to appear.</li>
     *   <li>{@code pollingInSeconds} - The interval (in seconds) at which to check for element visibility.</li>
     * </ul>
     *
     * <p><b>Returns:</b>
     * <ul>
     *   <li>{@code true} if the element becomes visible within the timeout.</li>
     *   <li>{@code false} if the element is not found or remains invisible after the timeout.</li>
     * </ul>
     *
     * <p><b>Possible Exceptions Handled:</b>
     * <ul>
     *   <li>{@link org.openqa.selenium.NoSuchElementException} - If the element is not found initially.</li>
     *   <li>{@link org.openqa.selenium.StaleElementReferenceException} - If the element becomes stale during wait.</li>
     *   <li>{@link org.openqa.selenium.TimeoutException} - If the element does not become visible within the timeout period.</li>
     *   <li>{@link org.openqa.selenium.WebDriverException} - If the WebDriver encounters any issue.</li>
     * </ul>
     *
     * @param ele              The WebElement to wait for.
     * @param timeoutInSeconds Maximum time in seconds to wait.
     * @param pollingInSeconds Polling interval in seconds.
     * @return {@code true} if the element is found and visible, otherwise {@code false}.
     */
    public void fluent_Wait_for_Visibility(WebElement ele, int timeoutInSeconds, int pollingInSeconds) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                    .pollingEvery(Duration.ofSeconds(pollingInSeconds))
                    .ignoring(NoSuchElementException.class)
                  /*  .ignoring(StaleElementReferenceException.class)
                    .ignoring(WebDriverException.class)*/;
            wait.until(ExpectedConditions.visibilityOf(ele));
        } catch (TimeoutException e) {
            System.err.println("TimeoutException: Element not visible after " + timeoutInSeconds + " seconds.");
            e.printStackTrace();
        } catch (WebDriverException e) {
            System.err.println("WebDriverException: Issue encountered with WebDriver while waiting for element.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void switchToFrame(int index) {
        try {
            getDriver().switchTo().frame(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void switchToFrame(WebElement ele) {
        try {
            getDriver().switchTo().frame(ele);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to switch to the frame based on Id or name
     *
     * @param ele
     * @param fieldname
     * @Author: Balaji N
     * @Last-Modified:Sakrateesh R
     */
    public void SwitchToFrame(String ele, String fieldname) {
        WebElement errorele = getDriver().findElement(By.id(ele));
        try {
            getDriver().switchTo().frame(ele);
        } catch (NoSuchFrameException e) {
            printError(errorele, fieldname, "No Such Frame Exception", e);
        } catch (StaleElementReferenceException e) {
            printError(errorele, fieldname, "Stale Element Reference Exception", e);
        }
    }

    @Override
    public void switchToFrame(String idOrName) {
        try {
            getDriver().switchTo().frame(idOrName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void defaultContent() {
        try {
            getDriver().switchTo().defaultContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifyUrl(String url) {
        if (getDriver().getCurrentUrl().equals(url)) {
            System.out.println("The url: " + url + " matched successfully");
            return true;
        } else {
            System.out.println("The url: " + url + " not matched");
        }
        return false;
    }

    /**
     * This method will return the Page Title
     *
     * @param PageName - The Page which we are trying to get the title
     * @return : Return the Page Title
     * @author Balaji N
     * @Last-Modified-By : Sakrateesh R
     */
    @Override
    public String getTitle(String PageName) {
        String ele;
        String fieldName;
        try {
            ele = getDriver().getTitle();
            return ele;
        } catch (TimeoutException e) {

            throw new RuntimeException(e.getMessage() + "Title Not Found");
        } catch (NoSuchElementException e) {

            throw new RuntimeException(e.getMessage() + "Title Not Found");
        } catch (StaleElementReferenceException e) {

            throw new RuntimeException(e.getMessage() + "Title Not Found");
        } catch (Exception e) {

            throw new RuntimeException(e.getMessage() + "Title Not Found");
        }


    }

    @Override
    public String getElementText(WebElement ele) {
        String text = "";
        try {
            HighlightElement(ele);
            text = ele.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * Retrieves the value of the specified WebElement's "value" attribute.
     * Highlights the element before retrieving the attribute for better visibility during debugging.
     *
     * @param ele the WebElement from which to retrieve the "value" attribute.
     * @return the value of the WebElement's "value" attribute, or an empty string if an error occurs.
     * @throws RuntimeException if the WebElement is not found or accessible.
     */
    public String get_Element_Attribute(WebElement ele) {
        String text = "";
        try {
            HighlightElement(ele);
            text = ele.getAttribute("value");
            return text;
        } catch (Exception e) {
            String errorMessage = "Failed to retrieve the value attribute from the specified WebElement.";
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    /**
     * Retrieves the value of the specified WebElement's "value" attribute with highlighting.
     *
     * @param ele
     * @param fieldName
     * @return If element is displayed then returns the value of the WebElement's "value" attribute, otherwise returns "Error: Unable to retrieve text from " + fieldName
     * @Author Balaji N
     */
    public String getElementAttribute(WebElement ele, String fieldName) {
        String text = "";
        try {
            HighlightElement(ele);

            text = ele.getAttribute("value");
            // Allure.step("Successfully retrieved 'value' attribute from '" + fieldName + "': \"" + text + "\"");

        } catch (TimeoutException e) {
            String msg = "TimeoutException while retrieving attribute 'value' from '" + fieldName + "'";
            Allure.step(msg);
            printError(ele, fieldName, msg, e);

        } catch (NoSuchElementException e) {
            String msg = "NoSuchElementException while retrieving attribute 'value' from '" + fieldName + "'";
            Allure.step(msg);
            printError(ele, fieldName, msg, e);

        } catch (StaleElementReferenceException e) {
            String msg = "StaleElementReferenceException while retrieving attribute 'value' from '" + fieldName + "'";
            Allure.step(msg);
            printError(ele, fieldName, msg, e);

        } catch (Exception e) {
            String msg = "UnexpectedException while retrieving attribute 'value' from '" + fieldName + "'";
            Allure.step(msg);
            printError(ele, fieldName, msg, e);
        }
        return text;
    }

    /**
     * It retrieves the value of the specified WebElement's "placeholder" attribute with highlighting.
     *
     * @param ele
     * @param fieldName
     * @return If the element is displayed then returns the value of the WebElement's "placeholder" attribute, otherwise returns "Error: Unable to retrieve text from " + fieldName
     * @Author Balaji N
     */
    public String get_Placeholder_Element_Attribute(WebElement ele, String fieldName) {
        String text = "";
        try {
            HighlightElement(ele);
            text = ele.getAttribute("placeholder");
        } catch (TimeoutException e) {
            printError(ele, fieldName, "TimeoutException", e);
        } catch (NoSuchElementException e) {
            printError(ele, fieldName, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldName, "StaleElementReferenceException", e);
        } catch (Exception e) {
            printError(ele, fieldName, "UnexpectedException", e);
        }
        return text;
    }

    /**
     * Retrieves the value of the specified WebElement's "value" attribute without highlighting the element.
     *
     * @param ele       - The WebElement from which to retrieve the "value" attribute.
     * @param fieldName - The name of the field for logging purposes.
     * @return The value of the WebElement's "value" attribute, or an empty string if an error occurs.
     * @throws RuntimeException if the WebElement is not found or accessible.
     * @Author: Balaji N
     */
    public String get_element_attribute(WebElement ele, String fieldName) {
        String text = "";
        try {
            text = ele.getAttribute("value");
            return text;
        } catch (TimeoutException e) {
            printError(ele, fieldName, "TimeoutException", e);
        } catch (NoSuchElementException e) {
            printError(ele, fieldName, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldName, "StaleElementReferenceException", e);
        } catch (Exception e) {
            printError(ele, fieldName, "UnexpectedException", e);
        }
        return "Error: Unable to retrieve text from " + fieldName + " : field value : " + text;
    }


    /**
     * Retrieves the value of the specified WebElement's "value" attribute with triming the whitespace.
     *
     * @param ele       - The WebElement from which to retrieve the "value" attribute.
     * @param fieldName - The name of the field for logging purposes.
     * @return The value of the WebElement's "value" attribute, or an empty string if an error occurs.
     * @throws RuntimeException if the WebElement is not found or accessible.
     * @Author: Balaji N
     */
    public String get_element_attribute_with_trim(WebElement ele, String fieldName) {
        String text = "";
        try {
            HighlightElement(ele);
            text = ele.getAttribute("value").trim();
            return text;
        } catch (TimeoutException e) {
            printError(ele, fieldName, "TimeoutException", e);
        } catch (NoSuchElementException e) {
            printError(ele, fieldName, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldName, "StaleElementReferenceException", e);
        } catch (Exception e) {
            printError(ele, fieldName, "UnexpectedException", e);
        }
        System.err.println("Element not found while validating element is displayed: " + ele.toString() + " - Field Name: " + fieldName);
        return "Error: Unable to retrieve text from " + fieldName + " : field value : " + text;
    }


    /**
     * Retrieves the selected option text from a dropdown.
     *
     * @param ele       The dropdown WebElement.
     * @param fieldName The name of the field for logging purposes.
     * @return The text of the selected option, or an empty string if an exception occurs.
     * @Author Balaji N
     */
    public String get_Selected_Option(WebElement ele, String fieldName) {
        String text = "";
        try {
            HighlightElement(ele);
            Select select = new Select(ele);
            text = select.getFirstSelectedOption().getText();
        } catch (NoSuchElementException e) {
            printError(ele, fieldName, "NoSuchElementException - Element not found in the DOM", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldName, "StaleElementReferenceException - Element is stale", e);
        } catch (ElementNotInteractableException e) {
            printError(ele, fieldName, "ElementNotInteractableException - Element is not interactable", e);
        } catch (UnexpectedTagNameException e) {
            printError(ele, fieldName, "UnexpectedTagNameException - Element is not a <select> tag", e);
        } catch (TimeoutException e) {
            printError(ele, fieldName, "TimeoutException - Timeout while waiting for element", e);
        } catch (WebDriverException e) {
            printError(ele, fieldName, "WebDriverException - WebDriver-related issue", e);
        } catch (Exception e) {
            printError(ele, fieldName, "UnexpectedException - General exception occurred", e);
        }
        return text;
    }

    /**
     * It retrieves the selected option text from a dropdown without highlighting the element.
     *
     * @param ele       Element to be selected
     * @param fieldName Name of the field
     * @return It returns the selected option text
     * @Author: Balaji N
     */
    public String get_selected_option(WebElement ele, String fieldName) {
        String text = "";
        try {
            Select select = new Select(ele);
            text = select.getFirstSelectedOption().getText();
            // Allure.step("Selected option text for '" + fieldName + "' is: \"" + text + "\"");

        } catch (NoSuchElementException e) {
            String msg = "NoSuchElementException - Element not found in the DOM for '" + fieldName + "'";
            Allure.step(msg);
            printError(ele, fieldName, msg, e);

        } catch (StaleElementReferenceException e) {
            String msg = "StaleElementReferenceException - Element is stale for '" + fieldName + "'";
            Allure.step(msg);
            printError(ele, fieldName, msg, e);

        } catch (ElementNotInteractableException e) {
            String msg = "ElementNotInteractableException - Element is not interactable for '" + fieldName + "'";
            Allure.step(msg);
            printError(ele, fieldName, msg, e);

        } catch (UnexpectedTagNameException e) {
            String msg = "UnexpectedTagNameException - Element is not a <select> tag for '" + fieldName + "'";
            Allure.step(msg);
            printError(ele, fieldName, msg, e);

        } catch (TimeoutException e) {
            String msg = "TimeoutException - Timeout while waiting for element '" + fieldName + "'";
            Allure.step(msg);
            printError(ele, fieldName, msg, e);

        } catch (WebDriverException e) {
            String msg = "WebDriverException - WebDriver-related issue for '" + fieldName + "'";
            Allure.step(msg);
            printError(ele, fieldName, msg, e);

        } catch (Exception e) {
            String msg = "UnexpectedException - General exception occurred for '" + fieldName + "'";
            Allure.step(msg);
            printError(ele, fieldName, msg, e);
        }
        return text;
    }

    @Override
    public String getTypedText(WebElement ele) {
        String attributeValue = ele.getAttribute("value");
        return attributeValue;
    }

    @Override
    public void searchAndSelect(List<WebElement> ele, String data) {
        try {
            List<WebElement> l1 = ele;
            for (WebElement val : l1) {
                if (val.getText().equals(data)) {
                    actionClick(val);
                    break;
                }
            }

        } catch (ElementNotInteractableException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void drawSignature(WebElement ele) {
        try {
            Actions actionBuilder = new Actions(getDriver());
            Action drawOnCanvas = actionBuilder
                    .moveToElement(ele, 8, 8)
                    .clickAndHold(ele)
                    .moveByOffset(120, 120)
                    .moveByOffset(60, 70)
                    .moveByOffset(-140, -140)
                    .release(ele)
                    .build();
            drawOnCanvas.perform();
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public String Eastern_TimeZone() {
        try {
            LocalDateTime systemDateTime = LocalDateTime.now();

            ZoneId systemZone = ZoneId.systemDefault();
            ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

            // Define the Eastern Time (US & Canada) zone
            ZoneId easternTimeZone = ZoneId.of("America/New_York");  // Eastern Time Zone

            ZonedDateTime easternZonedDateTime = systemZonedDateTime.withZoneSameInstant(easternTimeZone);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
            String formattedEasternTime = easternZonedDateTime.format(formatter).toUpperCase();
            ;

            return formattedEasternTime;
        } catch (Exception e) {
            throw new RuntimeException("Error on Eastern Time zone" + e.getMessage());
        }
    }

    /**
     * Get current time and date in Atlantic Time zone in format "MMM d yyyy h:mma" (Example: "JUL 21 2025  2:25AM")
     *
     * @return
     * @Author Balaji N
     */
    public String Get_Atlantic_TimeZone() {
        try {
            // Get current time in Atlantic Time zone directly
            ZonedDateTime atlanticZonedDateTime = ZonedDateTime.now(ZoneId.of("America/Halifax"));

            // Format the Atlantic Time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
            String formattedAtlanticTime = atlanticZonedDateTime.format(formatter).toUpperCase();
            System.out.println("Current AST Time and Date: " + formattedAtlanticTime);
            return formattedAtlanticTime;
        } catch (Exception e) {
            throw new RuntimeException("Error getting Atlantic timezone date and time: " + e.getMessage());
        }
    }

    public String Get_Atlantic_TimeZone1() {
        try {
            // Get current time in Atlantic Time zone directly
            ZonedDateTime atlanticZonedDateTime = ZonedDateTime.now(ZoneId.of("America/Halifax"));

            // Format to "AUG 06 2025 4:18PM"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma", Locale.ENGLISH);
            String formattedAtlanticTime = atlanticZonedDateTime.format(formatter).toUpperCase();

            System.out.println("Current AST Time and Date: " + formattedAtlanticTime);
            return formattedAtlanticTime;
        } catch (Exception e) {
            throw new RuntimeException("Error getting Atlantic timezone date and time: " + e.getMessage());
        }
    }


    /**
     * Get current time and date in Atlantic Time zone in format "MMM d yyyy h:mma" (Example: "Jul 21 2025  2:25AM")
     *
     * @return
     * @Author Balaji N
     */
    public String get_AST_DateAndTime() {
        try {
            // Get current time in Atlantic Time zone directly
            ZonedDateTime atlanticZonedDateTime = ZonedDateTime.now(ZoneId.of("America/Halifax"));

            // Format the Atlantic Time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH);
            String formattedAtlanticTime = atlanticZonedDateTime.format(formatter);
            System.out.println("Current AST Time and Date: " + formattedAtlanticTime);
            return formattedAtlanticTime;
        } catch (Exception e) {
            throw new RuntimeException("Error getting Atlantic timezone date and time: " + e.getMessage());
        }
    }

    public String Atlantic_TimeZone() {
        try {
            // Get the current system time
            LocalDateTime systemDateTime = LocalDateTime.now();

            // Get the system's default zone
            ZoneId systemZone = ZoneId.systemDefault();
            ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

            // Define the Atlantic Standard Time zone (UTC-04:00)
            ZoneId atlanticTimeZone = ZoneId.of("America/Halifax");

            // Convert to Atlantic Standard Time
            ZonedDateTime atlanticZonedDateTime = systemZonedDateTime.withZoneSameInstant(atlanticTimeZone);

            // Format the Atlantic Time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
            String formattedAtlanticTime = atlanticZonedDateTime.format(formatter).toUpperCase();

            // Replace "SEPT" with "SEP" if necessary
            if (formattedAtlanticTime.startsWith("JAN")) {
                formattedAtlanticTime = formattedAtlanticTime.replaceFirst("JAN", "JAN");
            }
            return formattedAtlanticTime; // Return the formatted Atlantic Time
        } catch (Exception e) {
            throw new RuntimeException("Error Unable to get Atlantic timezone date and time " + e.getMessage());
        }
    }

    /**
     * AST Timezone in the format of MM/DD/YYYY h:mma
     *
     * @return
     */
    public String Atlantic_TimeZone_NumberDateFormat() {
        try {
            LocalDateTime systemDateTime = LocalDateTime.now();

            ZoneId systemZone = ZoneId.systemDefault();
            ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

            // Define the Atlantic Standard Time zone (UTC-04:00)
            ZoneId atlanticTimeZone = ZoneId.of("America/Halifax"); // Use "America/Halifax" for AST

            ZonedDateTime atlanticZonedDateTime = systemZonedDateTime.withZoneSameInstant(atlanticTimeZone);

            // Remove the space at mm a to get hh:mma = 05:30AM || h:mma = 5:30AM
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mma");
            String formattedAtlanticTime = atlanticZonedDateTime.format(formatter).toUpperCase();

            return formattedAtlanticTime;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * AST Timezone in the format of MM/DD/YYYY h:mma
     *
     * @return
     */
    public String Atlantic_TimeZone_In_MM_dd_yyyy_hhmm() {
        try {
            LocalDateTime systemDateTime = LocalDateTime.now();
            ZoneId systemZone = ZoneId.systemDefault();
            ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

            // Define the Atlantic Standard Time zone (UTC-04:00)
            ZoneId atlanticTimeZone = ZoneId.of("America/Halifax"); // Use "America/Halifax" for AST
            ZonedDateTime atlanticZonedDateTime = systemZonedDateTime.withZoneSameInstant(atlanticTimeZone);

            // Remove the space at mm a to get hh:mma = 05:30AM || h:mma = 5:30AM
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
            String formattedAtlanticTime = atlanticZonedDateTime.format(formatter).toUpperCase();
            return formattedAtlanticTime;
        } catch (Exception e) {
            throw new RuntimeException("AST Time zone in the format of MM/DD/YYYY hh:mma " + e.getMessage());
        }
    }

    public String Central_TimeZone() {
        LocalDateTime systemDateTime = LocalDateTime.now();

        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

        // Define the Central Time (US & Canada) zone
        ZoneId centralTimeZone = ZoneId.of("America/Chicago");  // Central Time Zone (UTC-06:00)

        // Convert system date-time to Central Time
        ZonedDateTime centralZonedDateTime = systemZonedDateTime.withZoneSameInstant(centralTimeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        String formattedCentralTime = centralZonedDateTime.format(formatter).toUpperCase();
        ;

        return formattedCentralTime;
    }

    public String Mountain_TimeZone() {
        LocalDateTime systemDateTime = LocalDateTime.now();

        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

        // Define the Mountain Time (US & Canada) zone
        ZoneId mountainTimeZone = ZoneId.of("America/Denver");  // Mountain Time Zone (UTC-07:00)

        ZonedDateTime mountainZonedDateTime = systemZonedDateTime.withZoneSameInstant(mountainTimeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        String formattedMountainTime = mountainZonedDateTime.format(formatter).toUpperCase();
        ;

        return formattedMountainTime;
    }

    public String Pacific_TimeZone() {
        LocalDateTime systemDateTime = LocalDateTime.now();

        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

        // Define the Pacific Time (US & Canada) zone (UTC-08:00)
        ZoneId pacificTimeZone = ZoneId.of("America/Los_Angeles");

        ZonedDateTime pacificZonedDateTime = systemZonedDateTime.withZoneSameInstant(pacificTimeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        String formattedPacificTime = pacificZonedDateTime.format(formatter).toUpperCase();
        ;

        return formattedPacificTime;
    }

    public String Alaska_TimeZone() {
        LocalDateTime systemDateTime = LocalDateTime.now();
        // Remove the space at mm a to get h:mma = 5:30AM
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime systemZonedDateTime = systemDateTime.atZone(systemZone);

        // Define the Alaska Time zone (America/Anchorage)
        ZoneId alaskaTimeZone = ZoneId.of("America/Anchorage");  // Alaska Time (UTC-09:00)

        ZonedDateTime alaskaZonedDateTime = systemZonedDateTime.withZoneSameInstant(alaskaTimeZone);

        String formattedAlaskaTime = alaskaZonedDateTime.format(inputFormatter).toUpperCase();
        ;

        return formattedAlaskaTime;
    }

    public String GetCurrentMonth() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate previousMonthDate = currentDate.minusMonths(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM"); // Short month format
            return previousMonthDate.format(formatter);
        } catch (Exception e) {
            throw new RuntimeException("Error on get current month reusable function " + e);
        }
    }


    public String GetPreviousMonth() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate previousMonthDate = currentDate.minusMonths(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM"); // Short month format
            return previousMonthDate.format(formatter);
        } catch (Exception e) {
            throw new RuntimeException("Error on get previous month " + e);
        }
    }

    public String GetNextMonth() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate previousMonthDate = currentDate.plusMonths(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM"); // Short month format
            return previousMonthDate.format(formatter);
        } catch (Exception e) {
            throw new RuntimeException("Error on get next month function " + e);
        }
    }

    /**
     * It gets the current date of the system
     *
     * @return It returns the current date as MM/dd/yyyy
     */
    public String CurrentDate() {
        try {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedCurrentDate = currentDate.format(formatter);
            return (formattedCurrentDate);
        } catch (Exception e) {
            throw new RuntimeException("Error on Get Current Date function" + e);
        }
    }


    public String getCurrentAtlanticDateTime() {
        try {
            // Atlantic Standard Time (UTC -04:00)
            ZoneId atlanticZone = ZoneId.of("America/Halifax");

            ZonedDateTime atlanticDateTime = ZonedDateTime.now(atlanticZone);

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

            return atlanticDateTime.format(formatter);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error while getting current Atlantic Standard Time date & time", e
            );
        }
    }

    /**
     * Returns the current or future date in AST (Atlantic Standard Time) zone.
     *
     * @param daysToAdd Number of days to add to current date (0 for today, negative for past).
     * @return Date string in MM/dd/yyyy format.
     */
    public static String get_AST_Date(int daysToAdd) {
        try {
            ZoneId astZone = ZoneId.of("America/Halifax"); // AST zone with daylight saving
            LocalDate date = LocalDate.now(astZone).plusDays(daysToAdd);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            return date.format(formatter);
        } catch (Exception e) {
            throw new RuntimeException("Error getting AST date: " + e.getMessage(), e);
        }
    }

    /**
     * Returns the current or future time in AST (Atlantic Standard Time) zone.
     *
     * @param hoursToAdd   Number of hours to add (0 for current time, can be negative).
     * @param minutesToAdd Number of minutes to add (0 for current time, can be negative).
     * @return Time string in HH:mm:ss a format.
     */
    public static String getASTTime(int hoursToAdd, int minutesToAdd) {
        try {
            ZoneId astZone = ZoneId.of("America/Halifax"); // AST with daylight saving
            LocalTime time = LocalTime.now(astZone)
                    .plusHours(hoursToAdd)
                    .plusMinutes(minutesToAdd);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a"); // 12-hour format with AM/PM
            return time.format(formatter);
        } catch (Exception e) {
            throw new RuntimeException("Error getting AST time: " + e.getMessage(), e);
        }
    }

    public String FutureDateEST(int daysToAdd) {
        try {
            ZoneId estZone = ZoneId.of("America/New_York");
            LocalDate futureDate = LocalDate.now(estZone).plusDays(daysToAdd);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            return futureDate.format(formatter);
        } catch (Exception e) {
            throw new RuntimeException("Error on Get Future EST Date function: " + e);
        }
    }

    public String getCurrentDate() {
        try {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            return currentDate.format(formatter);
        } catch (Exception e) {
            throw new RuntimeException("Error in getCurrentDate function: " + e.getMessage(), e);
        }
    }


    public String getCurrentTime() {
        try {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            return currentTime.format(formatter);
        } catch (Exception e) {
            throw new RuntimeException("Error on Get Current Time function: " + e);
        }
    }


    public String currentDate() {
        try {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            String formattedCurrentDate = currentDate.format(formatter);
            return (formattedCurrentDate);
        } catch (Exception e) {
            throw new RuntimeException("Error on Get Current Date function" + e);
        }
    }

    /**
     * It gets the previous date of the system
     *
     * @return
     */
    public String PreviousDate() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate previousDay = currentDate.plusDays(-1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedPreviousDay = previousDay.format(formatter);
            return formattedPreviousDay;
        } catch (Exception e) {
            throw new RuntimeException("Error on previous date function " + e);
        }
    }

    public String PreviousDate_AST() {
        try {
            // Define the AST timezone
            ZoneId astZone = ZoneId.of("America/Halifax"); // Halifax is in AST (standard time) or ADT (daylight time)

            // Get current date-time in AST timezone
            ZonedDateTime nowInAst = ZonedDateTime.now(astZone);

            // Get the previous day in AST timezone
            ZonedDateTime previousDayInAst = nowInAst.minusDays(1);

            // Extract the date part only
            LocalDate previousDate = previousDayInAst.toLocalDate();

            // Format the date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            return previousDate.format(formatter);
        } catch (Exception e) {
            throw new RuntimeException("Error on previous date function " + e);
        }
    }

    public String past_Date() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate previousDay = currentDate.plusDays(-1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            String formattedPreviousDay = previousDay.format(formatter);
            return formattedPreviousDay;
        } catch (Exception e) {
            throw new RuntimeException("Error on previous date function " + e);
        }
    }

    /**
     * Calculates the next day's date from the current date and returns it in the format "MM/dd/yyyy".
     *
     * <p>Example:
     * If today's date is 01/22/2025, the method will return "01/23/2025".</p>
     *
     * @return A string representing the next day's date in the format "MM/dd/yyyy".
     * @throws RuntimeException if an error occurs during date calculation or formatting.
     * @author Balaji N
     */
    public String NextDate() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate previousDay = currentDate.plusDays(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedPreviousDay = previousDay.format(formatter);
            return formattedPreviousDay;
        } catch (Exception e) {
            throw new RuntimeException("Error on next date function " + e);
        }
    }

    /**
     * Calculates the next day's date from the current date and returns it in the format "MM/dd/yyyy".
     *
     * <p>Example:
     * If today's date is 01/22/2025, the method will return "01/23/2025".</p>
     *
     * @return A string representing the next day's date in the format "MM/dd/yyyy".
     * @throws RuntimeException if an error occurs during date calculation or formatting.
     * @author Balaji N
     */
    public String NextDate(int i) {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate previousDay = currentDate.plusDays(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedPreviousDay = previousDay.format(formatter);
            return formattedPreviousDay;
        } catch (Exception e) {
            throw new RuntimeException("Error on next date function " + e);
        }
    }

    /**
     * Get the next date based on Alaska timezone in the format "yyyy/MM/dd". for example 2025/06/20
     *
     * @return A string representing the next date in Alaska timezone in the format "yyyy/MM/dd".
     * @Balaji N
     */
    public static String next_Date() {
        // Alaska timezone (UTC-09:00)
        ZoneId alaskaZoneId = ZoneId.of("America/Anchorage");

        // Current date-time in Alaska
        ZonedDateTime alaskaDateTime = ZonedDateTime.now(alaskaZoneId);

        // Get the next date in Alaska
        LocalDate nextDate = alaskaDateTime.plusDays(1).toLocalDate();

        // Format: yyyy/MM/dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return nextDate.format(formatter);
    }


    /**
     * Get the next date based on Alaska timezone in the format "/MM/dd/yyyy". for example 06/20/2025
     *
     * @return A string representing the next date in Alaska timezone in the format "/MM/dd/yyyy".
     * @Balaji N
     */
    public static String nextDate() {
        // Alaska timezone (UTC-09:00)
        ZoneId alaskaZoneId = ZoneId.of("America/Anchorage");

        // Current date-time in Alaska
        ZonedDateTime alaskaDateTime = ZonedDateTime.now(alaskaZoneId);

        // Get the next date in Alaska
        LocalDate nextDate = alaskaDateTime.plusDays(1).toLocalDate();

        // Format: yyyy/MM/dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        return nextDate.format(formatter);
    }


    /**
     * Retrieves the date of the next day from the current date in the format "dd-MMM-yyyy".
     *
     * <p>Example:
     * If today's date is 22-Jan-2025, the method will return "23-Jan-2025".</p>
     *
     * @return A string representing the next day's date in the format "dd-MMM-yyyy".
     * @throws RuntimeException if an error occurs while calculating or formatting the next day's date.
     * @Author Balaji N
     */
    public String Next_Date() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate nextDay = currentDate.plusDays(1); // Add 1 day to the current date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
            String formattedNextDay = nextDay.format(formatter);
            return formattedNextDay;
        } catch (Exception e) {
            throw new RuntimeException("Error in next date function: " + e);
        }
    }

    public String Next_10Days_Date() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate previousDay = currentDate.plusDays(10);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedPreviousDay = previousDay.format(formatter);
            return formattedPreviousDay;
        } catch (Exception e) {
            throw new RuntimeException("Error on next 10 days date function " + e);
        }
    }

    /**
     * It returns the date of the next 20 days from the current date in the format "MM/dd/yyyy".
     *
     * @return The date of the next 20 days from the current date in the format "MM/dd/yyyy".
     * @Author Balaji N
     */
    public String Next_20Days_Date() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate previousDay = currentDate.plusDays(20);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedPreviousDay = previousDay.format(formatter);
            return formattedPreviousDay;
        } catch (Exception e) {
            throw new RuntimeException("Error on next 20 days date function " + e);
        }
    }

    /**
     * It returns the date of the next 20 days from the current date in the format "MMM dd, yyyy".
     *
     * @return The date of the next 20 days from the current date in the format "MMM dd, yyyy".
     * @Description: It return date in the format of Jun 18, 2025
     * @Author Balaji N
     */
    public String next_20_Days_Date() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate futureDate = currentDate.plusDays(20);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
            String formattedDate = futureDate.format(formatter);
            return formattedDate;
        } catch (Exception e) {
            throw new RuntimeException("Error on next 20 days date function " + e);
        }
    }

    /**
     * It returns the date of the next 20 days from the current date in the format "MM/dd/yyyy".
     *
     * @return The date of the next 20 days from the current date in the format "MM/dd/yyyy".
     * @Author Balaji N
     */
    public String select_Next_Dates_Dynamically(int plusdays) {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate previousDay = currentDate.plusDays(plusdays);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedPreviousDay = previousDay.format(formatter);
            return formattedPreviousDay;
        } catch (Exception e) {
            throw new RuntimeException("Error on next 20 days date function " + e);
        }
    }


    /**
     * Set the value of next two years date from current date dyanmically
     *
     * @return
     * @Author Balaji N
     */
    public String Next_TwoYears_Date() {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate previousDay = currentDate.plusDays(730);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String formattedPreviousDay = previousDay.format(formatter);
            return formattedPreviousDay;
        } catch (Exception e) {
            throw new RuntimeException("Error on next two years date function " + e);
        }
    }

    @Override
    public void switchToWindowbyIndex(int i) {
        try {
            Set<String> windowIds = getDriver().getWindowHandles();
            List<String> windowIdsList = new ArrayList<>(windowIds);

            if (i < 0 || i >= windowIdsList.size()) {
                throw new IndexOutOfBoundsException("Invalid window index: " + i + ". Available windows: " + windowIdsList.size());
            }

            String childWindowId = windowIdsList.get(i);
            getDriver().switchTo().window(childWindowId);

        } catch (NoSuchWindowException e) {
            throw new RuntimeException("No such window found with index: " + i, e);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Window index out of bounds: " + i + ". Total windows available: " + getDriver().getWindowHandles().size(), e);
        } catch (WebDriverException e) {
            throw new RuntimeException("WebDriver encountered an error while switching windows", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while switching to window by index: " + i, e);
        }
    }

    public void switchToTabByIndex(int tabIndex) {
        try {
            // Get all the open tabs (which the browser sees as "window handles")
            ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());

            // Check if the tab index is valid
            if (tabIndex >= tabs.size() || tabIndex < 0) {
                throw new IndexOutOfBoundsException("Tab index out of bounds: " + tabIndex);
            }

            // Switch to the tab at the given index
            getDriver().switchTo().window(tabs.get(tabIndex));

        } catch (IndexOutOfBoundsException e) {
            // Handle the case where the tab index is invalid
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            // Handle other unexpected errors
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    @Override
    public String getBackgroundColor(WebElement ele) {
        try {
            String cssValue = ele.getCssValue("color");
            return cssValue;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void GetBackgroundColor(WebElement ele) {
        try {
            String colorele = ele.getCssValue("color");
            String hex_code = Color.fromString(colorele).asHex();
            System.out.println("Displayed heading color is : " + colorele);
            System.out.println("Displayed heading hex color code is : " + hex_code);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void jsDatePicker(WebElement ele, String dateval) {
        try {
            HighlightElement(ele);
            JavascriptExecutor JS = (JavascriptExecutor) getDriver();
            JS.executeScript("arguments[0].setAttribute('value','" + dateval + "');", ele);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void datePicker(WebElement ele, WebElement ActualMonthYear, WebElement NextArrow, List<WebElement> alldates, String date) {
        try {
            String day = date.substring(4, 6);
            String monthyear = date.substring(0, 3) + " " + date.substring(7, 11);
            HighlightElement(ele);
            click(ele);
            explicitWait(ele);

            while (true) {
                if (ActualMonthYear.equals(monthyear)) {
                    break;
                }
                click(NextArrow);
            }

            for (WebElement element : alldates) {
                String days = element.getText();
                if (days.equals(day)) {
                    click(element);
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void MouseHover(WebElement ele) {
        try {
            HighlightElement(ele);
            Actions action = new Actions(getDriver());
            action.moveToElement(ele).build().perform();
            delayWithGivenTime(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Perform a mouse hover action on a given element with enhanced exception handling.
     *
     * @param ele The WebElement to hover over.
     * @throws IllegalArgumentException        If the provided element is null.
     * @throws ElementNotInteractableException If the element is not interactable.
     * @throws NoSuchElementException          If the element is not found in the DOM.
     * @Author Balaji N
     */
    public void js_Click(WebElement ele) {
        try {
            // Validate the WebElement is not null
            if (ele == null) {
                throw new IllegalArgumentException("The provided WebElement is null.");
            }

            // Validate the WebElement is attached to the DOM and visible
            if (!ele.isDisplayed()) {
                throw new ElementNotInteractableException("The provided WebElement is not visible on the DOM.");
            }

            // Synchronize with the WebElement to avoid stale or unready element issues
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(ele));

            // Highlight the element for debugging purposes
            HighlightElement(ele);

            // Perform the mouse hover action
            Actions action = new Actions(getDriver());
            action.moveToElement(ele).perform();

            // Optional delay (can be removed for performance optimization)
            delayWithGivenTime(500);

            logger.info("Mouse hover action successfully performed on the element: " + ele.toString());

        } catch (IllegalArgumentException e) {
            logger.error("Invalid argument: " + e.getMessage());
            throw e; // Re-throw the exception
        } catch (StaleElementReferenceException e) {
            logger.error("Stale element reference: " + e.getMessage());
            throw e; // Re-throw the exception
        } catch (ElementNotInteractableException e) {
            logger.error("Element not interactable: " + e.getMessage());
            throw e; // Re-throw the exception
        } catch (NoSuchElementException e) {
            logger.error("No such element found: " + e.getMessage());
            throw e; // Re-throw the exception
        } catch (TimeoutException e) {
            logger.error("Timeout while waiting for the element to be visible: " + e.getMessage());
            throw e; // Re-throw the exception
        } catch (Exception e) {
            logger.error("An unexpected error occurred during mouse hover: " + e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred during mouse hover.", e); // Wrap and re-throw
        }
    }

    /**
     * Perform a mouse hover action using javascript executor on a given element with enhanced exception handling.
     *
     * @param ele       The WebElement to hover over.
     * @param fieldname The field name associated with the element.
     * @throws IllegalArgumentException        If the provided element is null.
     * @throws ElementNotInteractableException If the element is not interactable.
     * @throws NoSuchElementException          If the element is not found in the DOM.
     * @Author Balaji N
     */
//    public void Mouse_Hover(WebElement ele, String fieldname) {
//        try {
//            if (ele == null) {
//                throw new IllegalArgumentException("The provided WebElement is null.");
//            }
//
//            if (!ele.isDisplayed()) {
//                throw new ElementNotInteractableException("The provided WebElement is not visible on the DOM.");
//            }
//
//            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
//            wait.until(ExpectedConditions.visibilityOf(ele));
//
//            HighlightElement(ele);
//
//            Actions action = new Actions(getDriver());
//            action.moveToElement(ele).build().perform();
//            delayWithGivenTime(2000);
//
//        } catch (IllegalArgumentException e) {
//            printError(ele, fieldname, "IllegalArgumentException", e);
//        } catch (StaleElementReferenceException e) {
//            printError(ele, fieldname, "StaleElementReferenceException", e);
//        } catch (ElementNotInteractableException e) {
//            printError(ele, fieldname, "ElementNotInteractableException", e);
//        } catch (NoSuchElementException e) {
//            printError(ele, fieldname, "NoSuchElementException", e);
//        } catch (TimeoutException e) {
//            printError(ele, fieldname, "TimeoutException", e);
//        } catch (Exception e) {
//            printError(ele, fieldname, "An unexpected error occurred during mouse hover: ", e);
//        }
//    }

    // --- Build a By from an @FindBy field via reflection ---
    protected By getByFromWebElement(Object pageInstance, WebElement element) {
        try {
            for (Field field : pageInstance.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object val = field.get(pageInstance);
                if (val == element && field.isAnnotationPresent(FindBy.class)) {
                    FindBy fb = field.getAnnotation(FindBy.class);
                    if (!fb.xpath().isEmpty()) return By.xpath(fb.xpath());
                    if (!fb.css().isEmpty()) return By.cssSelector(fb.css());
                    if (!fb.id().isEmpty()) return By.id(fb.id());
                    if (!fb.name().isEmpty()) return By.name(fb.name());
                    if (!fb.className().isEmpty()) return By.className(fb.className());
                    if (!fb.tagName().isEmpty()) return By.tagName(fb.tagName());
                    if (!fb.linkText().isEmpty()) return By.linkText(fb.linkText());
                    if (!fb.partialLinkText().isEmpty()) return By.partialLinkText(fb.partialLinkText());
                }
            }
        } catch (IllegalAccessException ignored) {
        }
        return null; // not found
    }

    // --- Safely re-find a fresh element for parallel/stale resistance ---
    protected WebElement getFreshElement(Object pageInstance, WebElement ele) {
        By by = getByFromWebElement(pageInstance, ele);
        if (by != null) {
            return getDriver().findElement(by);
        }
        // Fallback: last resort, use the same reference
        return ele;
    }

    // --- Human-readable failure block for Allure + console ---
    protected void logUserFriendlyFailureDetails(
            String fieldName,
            String locatorString,
            List<String> attemptLogs,
            String failureTitle,
            String... possibleCauses
    ) {
        StringBuilder sb = new StringBuilder(512);
        sb.append("\n")
                .append("❌ [").append(failureTitle).append("]\n")
                .append("Field Name: ").append(fieldName).append("\n")
                .append("Locator   : ").append(locatorString).append("\n\n")
                .append("🧪 Attempt Summary:\n");
        for (String line : attemptLogs) sb.append(line).append("\n");
        sb.append("\n🔍 POSSIBLE CAUSES:\n");
        for (String cause : possibleCauses) sb.append(" - ").append(cause).append("\n");

        String debugInfo = sb.toString();
        Allure.addAttachment(failureTitle + " Details", debugInfo);
        System.err.println(debugInfo);
        throw new RuntimeException(debugInfo); // fail the step/test
    }

    // --- Small sleep with proper interrupt handling ---
    protected void delayWithGivenTime(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Retryable, parallel-safe mouse hover with human-readable failure logging.
     * Pass the page instance (`this`) so we can reflect the @FindBy to build a By.
     */
    public void Mouse_Hover(Object pageInstance, WebElement ele, String fieldName) {
        int maxRetries = 3;
        int delayBetweenRetriesMs = 300;
        List<String> attemptLogs = new ArrayList<>();
        boolean success = false;

        By by = getByFromWebElement(pageInstance, ele); // may be null (fallback handled below)
        String locatorForLog = (by != null) ? by.toString() : "[Locator not resolved via @FindBy reflection]";

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                if (ele == null) {
                    throw new IllegalArgumentException("No element found for '" + fieldName + "'.");
                }

                // Always re-fetch to avoid stale references
                WebElement freshEle = (by != null)
                        ? new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(by))
                        : new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOf(ele));

                HighlightElement(freshEle);
                new Actions(getDriver()).moveToElement(freshEle).perform();
                delayWithGivenTime(200);
                success = true;
                break;

            } catch (TimeoutException e) {
                attemptLogs.add("Attempt " + attempt + ": TimeoutException → Element not visible within wait time.");
            } catch (StaleElementReferenceException e) {
                attemptLogs.add("Attempt " + attempt + ": StaleElementReferenceException → Element became detached from DOM.");
            } catch (NoSuchElementException e) {
                attemptLogs.add("Attempt " + attempt + ": NoSuchElementException → Element not found in DOM.");
            } catch (MoveTargetOutOfBoundsException e) {
                attemptLogs.add("Attempt " + attempt + ": MoveTargetOutOfBoundsException → Element position invalid for hover.");
            } catch (Exception e) {
                attemptLogs.add("Attempt " + attempt + ": UnexpectedException → " + e.getMessage());
                break; // don't spin on unknown fatal errors
            }

            delayWithGivenTime(delayBetweenRetriesMs);
        }

        if (!success) {
            logUserFriendlyFailureDetails(
                    fieldName,
                    locatorForLog,
                    attemptLogs,
                    "MOUSE HOVER FAILURE",
                    "Element is not visible within expected time",
                    "Element locator is incorrect or changed",
                    "Element was re-rendered or removed from DOM",
                    "UI transitions/animations prevented hover action"
            );
        }
    }


    //
//    public static String getLocatorFromWebElement(Object pageInstance, WebElement element) {
//        try {
//            for (Field field : pageInstance.getClass().getDeclaredFields()) {
//                field.setAccessible(true);
//                if (field.get(pageInstance) == element && field.isAnnotationPresent(FindBy.class)) {
//                    FindBy findBy = field.getAnnotation(FindBy.class);
//                    if (!findBy.xpath().isEmpty()) return "By.xpath: " + findBy.xpath();
//                    if (!findBy.id().isEmpty()) return "By.id: " + findBy.id();
//                    if (!findBy.css().isEmpty()) return "By.cssSelector: " + findBy.css();
//                    if (!findBy.name().isEmpty()) return "By.name: " + findBy.name();
//                    if (!findBy.linkText().isEmpty()) return "By.linkText: " + findBy.linkText();
//                    if (!findBy.partialLinkText().isEmpty()) return "By.partialLinkText: " + findBy.partialLinkText();
//                    if (!findBy.tagName().isEmpty()) return "By.tagName: " + findBy.tagName();
//                    if (!findBy.className().isEmpty()) return "By.className: " + findBy.className();
//                }
//            }
//        } catch (IllegalAccessException ignored) {}
//        return "[Locator not found via reflection]";
//    }
//
//
//    /**
//     * Extract locator from @FindBy proxy and re-find element to avoid stale references.
//     */
//    private WebElement getFreshElement(WebElement ele) {
//        String eleDescription = ele.toString();
//        if (!eleDescription.contains("->")) return ele; // Already resolved
//
//        try {
//            String locatorPart = eleDescription.split("->")[1].trim();
//            String type = locatorPart.split(":")[0].trim();
//            String value = locatorPart.split(":")[1].trim();
//
//            By by;
//            switch (type) {
//                case "xpath":
//                    by = By.xpath(value);
//                    break;
//                case "css selector":
//                    by = By.cssSelector(value);
//                    break;
//                case "id":
//                    by = By.id(value);
//                    break;
//                case "name":
//                    by = By.name(value);
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unsupported locator: " + type);
//            }
//            return getDriver().findElement(by);
//
//        } catch (Exception ex) {
//            return ele; // fallback to original
//        }
//    }
//
//    /**
//     * User-friendly error logging for Allure and console.
//     */
//    private void logUserFriendlyError(String fieldName, String userMessage, Exception e) {
//        String logMsg = String.format(
//                "❌ Action Failed: %s%n📌 Reason: %s%n🔍 Technical Details: %s",
//                fieldName, userMessage, e.getMessage()
//        );
//        Allure.step(logMsg); // visible in Allure report
//        System.err.println(logMsg); // visible in Jenkins console
//        throw new RuntimeException(logMsg, e);
//    }
//
//    /**
//     * Logs a detailed, human-readable error message for multiple retry failures.
//     */
//    private void logUserFriendlyFailureDetails(
//            String fieldName,
//            By locator,
//            List<String> attemptLogs,
//            String failureTitle,
//            String... possibleCauses
//    ) {
//        String debugInfo = "\n"
//                + "❌ [" + failureTitle + "]\n"
//                + "Field Name: " + fieldName + "\n"
//                + "Locator   : " + locator.toString() + "\n\n"
//                + "🧪 Attempt Summary:\n"
//                + String.join("\n", attemptLogs) + "\n\n"
//                + "🔍 POSSIBLE CAUSES:\n"
//                + String.join("\n - ", possibleCauses);
//
//        // Add to Allure report
//        Allure.addAttachment(failureTitle + " Details", debugInfo);
//
//        // Also print to console for Jenkins visibility
//        System.err.println(debugInfo);
//    }


    /**
     * Perform a mouse hover action on a given element with enhanced exception handling.
     *
     * @param ele
     * @param fieldname
     * @return It return the title of the element
     */
    public String HandleTooltip(WebElement ele, String fieldname) {
        try {
            HighlightElement(ele);
            Actions action = new Actions(getDriver());
            explicitWait(ele);
            action.moveToElement(ele).build().perform();
            delayWithGivenTime(1500);
            String title = ele.getAttribute("title");
            return title;
        } catch (IllegalArgumentException e) {
            printError(ele, fieldname, "IllegalArgumentException", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldname, "StaleElementReferenceException", e);
        } catch (ElementNotInteractableException e) {
            printError(ele, fieldname, "ElementNotInteractableException", e);
        } catch (NoSuchElementException e) {
            printError(ele, fieldname, "NoSuchElementException", e);
        } catch (TimeoutException e) {
            printError(ele, fieldname, "TimeoutException", e);
        } catch (Exception e) {
            printError(ele, fieldname, "An unexpected error occurred during mouse hover: ", e);
        }
        return null;
    }

    @Override
    public boolean mouseHoverByJavaScript(WebElement ele) {
        try {
            String javaScript = "var evObj = document.createEvent('MouseEvents');"
                    + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                    + "arguments[0].dispatchEvent(evObj);";
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript(javaScript, ele);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void MouseHoverAndClick(WebElement hoverele, WebElement clickeele) {
        try {
            HighlightElement(hoverele);
            fluentWait(clickeele);

            Actions action = new Actions(getDriver());
            action.moveToElement(hoverele).build().perform();

            delayWithGivenTime(2000);

            HighlightElement(clickeele);
            action.moveToElement(clickeele).click().build().perform();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String captureScreen(String tname) {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        //System.getProperty("user.dir") +
        String targetDir = ".\\screenshots\\";
        String targetFilePath = targetDir + tname + "_" + timeStamp + ".png";

        try {
            Files.createDirectories(Paths.get(targetDir));
            Files.copy(sourceFile.toPath(), Paths.get(targetFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return targetFilePath;
    }

    public String captureScreenshot(String screenshotName) {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String dest = System.getProperty("user.dir") + "/reports/screenshots/" + screenshotName + "_" + timeStamp + ".png";
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            File screenshotFile = new File(dest);
            ImageIO.write(screenFullImage, "png", screenshotFile);
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
        return dest;
    }


    public static String captureScreenshotBase64() {
        String screenshotBase64 = "";
        try {
            // Capture the screenshot using Robot class
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            // Convert the captured image to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screenFullImage, "png", baos);
            byte[] screenshotBytes = baos.toByteArray();

            // Encode the byte array to Base64
            screenshotBase64 = Base64.getEncoder().encodeToString(screenshotBytes);
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }

        return screenshotBase64; // Return Base64 string of the screenshot
    }

    public static String capture_Screenshot_Base64() {
        String screenshotBase64 = "";
        try {
            // Capture screenshot as Base64
            screenshotBase64 = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenshotBase64;
    }

    public static void capture_FullPage_ScreenshotForAllure() {
        WebDriver driver = TestBaseClass.getDriver();

        if (driver instanceof HasFullPageScreenshot) {
            // Capture full-page screenshot using Selenium's built-in capability
            byte[] screenshotBytes = ((HasFullPageScreenshot) driver).getFullPageScreenshotAs(OutputType.BYTES);

            // Attach the screenshot to Allure report
            Allure.getLifecycle().addAttachment(
                    "Full Page Screenshot with URL",
                    "image/png",
                    "png",
                    screenshotBytes
            );
        } else {
            System.err.println("Full page screenshot is only supported in ChromeDriver.");
        }
    }


    // Define a thread-local Robot instance
    private static final ThreadLocal<Robot> robotThreadLocal = ThreadLocal.withInitial(() -> {
        try {
            return new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("Failed to create Robot instance", e);
        }
    });

    private static final ReentrantLock locker = new ReentrantLock(true); // Fair lock ensures order

    /**
     * Uploads a file by simulating keyboard events.
     * This method ensures thread safety by using a thread-local Robot instance.
     *
     * @param ele      The WebElement (file input field) where the file needs to be uploaded.
     * @param filepath The absolute path of the file to be uploaded.
     * @throws IllegalArgumentException If the provided WebElement or file path is null/empty.
     */
    public void uploadFile(WebElement ele, String filepath) {
        if (ele == null || filepath == null || filepath.trim().isEmpty()) {
            throw new IllegalArgumentException("WebElement and filepath must not be null or empty.");
        }
        try {
            HighlightElement(ele);
            ele.click();
            delayWithGivenTime(2000);

            Robot rb = new Robot();

            StringSelection selection = new StringSelection(filepath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

            // Paste the file path using CTRL + V and press ENTER
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_V);

            rb.keyRelease(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);

            System.out.println("File uploaded successfully: " + filepath);

        } catch (HeadlessException e) {
            throw new RuntimeException("File upload failed: System does not support GUI operations", e);
        } catch (AWTException e) {
            throw new RuntimeException("Failed to initialize Robot instance", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during file upload", e);
        }
    }

    /**
     * Sets the clipboard text in a thread-safe manner.
     *
     * @param text The text to be copied to clipboard.
     */
    private static synchronized void setClipboard(String text) {
        StringSelection str = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
    }

    public void sendImageFile(String querySelector, String imageName) throws InterruptedException {
        try {
            JavascriptExecutor jse = (JavascriptExecutor) getDriver();
            WebElement chooseImage = (WebElement) jse.executeScript(" return " + querySelector + "");
            File file = new File("./testFiles/" + imageName + ".png");
            chooseImage.sendKeys(file.getAbsolutePath());
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    @Override
    public void VerifyFileDownLoad(WebElement ele, String pathfile, String filename) {
        try {
            click(ele);
            File filelocation = new File(pathfile);
            File[] totalfiles = filelocation.listFiles();

            for (File file : totalfiles) {
                if (file.getName().equals(filename)) {
                    System.out.println("File is download successfully");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles drag-and-drop using the Robot class.
     *
     * @param sourceElement The WebElement to drag.
     * @param targetElement The WebElement to drop onto.
     */
    public void dragAndDropUsingRobot(WebElement sourceElement, WebElement targetElement) {
        try {
            Point sourcePoint = sourceElement.getLocation();
            Point targetPoint = targetElement.getLocation();

            Robot robot = new Robot();
            robot.mouseMove(sourcePoint.getX(), sourcePoint.getY());
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(500); // Allow time for drag effect
            robot.mouseMove(targetPoint.getX(), targetPoint.getY());
            Thread.sleep(500); // Allow time for drop effect
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            System.out.println("Successfully performed drag-and-drop using Robot class.");
        } catch (Exception e) {
            System.err.println("Failed to perform drag-and-drop using Robot: " + e.getMessage());
        }
    }


    /**
     * Handles drag-and-drop using the Robot class, with the source as a file path.
     *
     * @param filePath      The full path of the source file to drag.
     * @param targetElement The WebElement representing the drop zone on the web page.
     */
    public void dragAndDropFileUsingRobot(String filePath, WebElement targetElement) {
        try {
            // Convert the file path to a format the system clipboard can use
            StringSelection stringSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            // Get the target element's location
            Point targetPoint = targetElement.getLocation();

            // Initialize Robot
            Robot robot = new Robot();

            // Simulate CTRL+V to paste the file path
            robot.delay(500); // Add delay to ensure focus
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            // Simulate Enter key to confirm the file selection
            robot.delay(500); // Allow time for the paste operation
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            // Move mouse to the drop zone and perform the drop action
            robot.mouseMove(targetPoint.getX(), targetPoint.getY());
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(500); // Allow time for the drag effect
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            System.out.println("Successfully performed drag-and-drop for file: " + filePath);
        } catch (Exception e) {
            System.err.println("Failed to perform drag-and-drop using Robot: " + e.getMessage());
        }
    }


    @Override
    public void HandleAnalogClock(String TimeValue, WebElement ele) {
        String DeliveryTime = TimeValue; //5:30 PM
        click(ele);
        delayWithGivenTime(2000);
        getDriver().switchTo().activeElement();

        String[] dt = DeliveryTime.split(":");
        System.out.println("Hour is :" + dt[0]);
        System.out.println("Minutes is :" + dt[1]);

        WebElement SelectHour = getDriver().findElement(By.xpath("//div[normalize-space()= " + dt[0] + ")]"));
        delayWithGivenTime(2000);
        click(SelectHour);

        WebElement SelectMinute = getDriver().findElement(By.xpath("//div[normalize-space()=" + dt[1].substring(0, 1) + ")]"));
        click(SelectMinute);

        WebElement PMbutton = getDriver().findElement(By.xpath("(//a[normalize-space()='PM'])[1]"));
        jsScrollClick(PMbutton);

    }

    @Override
    public String GetCurrentTime() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            return dtf.format(now);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean verifyIsDisplayed(WebElement ele) {
        try {
            HighlightElement(ele);
            if (ele.isDisplayed()) {
                return true;
            }
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean verifyIsEnabled(WebElement ele) {
        try {
            HighlightElement(ele);
            if (ele.isEnabled()) {
                return true;
            }
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
        }
        return false;

    }

    @Override
    public boolean verifyIsSelected(WebElement ele) {

        try {
            HighlightElement(ele);
            return ele.isSelected();
        } catch (WebDriverException e) {
            System.out.println("WebDriverException : " + e.getMessage());
            return false;
        }
    }

    /**
     * This method is used to verify whether the respective method is selected or not.
     *
     * @param ele
     * @param fieldname
     * @return: true if the respective Element is selected else false.
     * @Author: Sakrateesh R
     */
    public boolean verify_Is_Selected(WebElement ele, String fieldname) {
        try {
            HighlightElement(ele);
            if (isElementDisplayed(ele)) {
                return ele.isSelected();
            }
        } catch (NoSuchElementException e) {
            printError(ele, fieldname, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldname, "StaleElementReferenceException", e);
        } catch (JavascriptException e) {
            printError(ele, fieldname, "JavascriptException", e);
        } catch (WebDriverException e) {
            printError(ele, fieldname, "WebDriverException", e);
        } catch (Exception e) {
            printError(ele, fieldname, "UnexpectedException", e);
        }
        return false;
    }

    @Override
    public void rightClickAction() {
        try {
            Actions action = new Actions(getDriver());
            action.contextClick().build().perform();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void dragAndDrop(WebElement source, WebElement target) {
        try {
            Actions action = new Actions(getDriver());
            action.dragAndDrop(source, target).build().perform();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Either the source or target element was not found for drag-and-drop.", e);
        } catch (StaleElementReferenceException e) {
            throw new StaleElementReferenceException("One of the elements became stale before the drag-and-drop action.", e);
        } catch (MoveTargetOutOfBoundsException e) {
            throw new MoveTargetOutOfBoundsException("The target element is outside the visible viewport.", e);
        } catch (WebDriverException e) {
            throw new WebDriverException("WebDriver encountered an issue while performing drag-and-drop.", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected exception occurred during drag-and-drop.", e);
        }
    }

    @Override
    public int getColumncount(WebElement ele) {
        List<WebElement> columns = ele.findElements(By.tagName("td"));
        int a = columns.size();
        System.out.println(columns.size());
        for (WebElement column : columns) {
            System.out.print(column.getText());
            System.out.print("|");
        }
        return a;
    }

    @Override
    public int getRowCount(WebElement ele) {
        List<WebElement> rows = ele.findElements(By.tagName("tr"));
        int a = rows.size() - 1;
        return a;
    }

    @Override
    public void scrollAction(WebElement ele) {
        try {
            if (ele == null) {
                throw new IllegalArgumentException("Scroll Action at WebElement cannot be null");
            }
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].scrollIntoView(true);", ele);
        } catch (StaleElementReferenceException e) {
            System.err.println("Element became stale. Retrying scroll...");
            scrollAction(ele); // Retry once if element is stale
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Element not found on the page: " + ele, e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to scroll to element: " + ele, e);
        }
    }

    @Override
    public void HandleAutocomplete(WebElement ele, String data) {
        try {
            HighlightElement(ele);
            ele.sendKeys(data);
            delayWithGivenTime(2000);
            fluentWait(ele);
            Actions actions = new Actions(getDriver());
            //	actions.moveToElement(hoverElement).moveToElement(clickElement).click().build().perform();
            actions.sendKeys(ele, Keys.DOWN).sendKeys(Keys.ENTER).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String rgbaToHex(String color) {
        String[] numbers = color.replace("rgba(", "").replace("rgb(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());
        return String.format("#%02x%02x%02x", r, g, b);
    }

    /**
     * Press the Tab key
     *
     * @Description: This static method is used to press the Tab key
     * @Author Balaji N
     */
    public static void press_Tab_Key() {
        try {
            Actions actions = new Actions(getDriver());
            actions.sendKeys(Keys.TAB).build().perform();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void clear_And_Type(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public List<WebElement> waitForElementsVisibility(List<WebElement> elements, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement waitForElementToBeClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void Handle_DatePicker_To_Click_CurrentDate() {
        try {
            // Get today's date in the format mm/dd/yyyy
            // Get today's date
            LocalDate currentDate = LocalDate.now();
            String day = currentDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            String month = currentDate.getMonth().name(); // Use this if month dropdown is present
            int year = currentDate.getYear();

            // Select the year, month, and day dynamically if needed
            // Example to locate and click current day
            WebElement today = getDriver().findElement(By.xpath("//td[not(contains(@class, 'disabled'))]/button/span[text()='" + day + "']"));
            today.click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


       /* LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println("Current Date: " + formattedDate);
*/

    }


    public void date_Picker(WebElement actualMonthElement, WebElement actualYearElement, WebElement nextMonthArrowElement, List<WebElement> allDatesElements, String targetDate) {
        try {
            String targetDay = targetDate.substring(3, 5); // Extract the day
            String targetMonth = targetDate.substring(0, 2); // Extract the month as MM
            String targetYear = targetDate.substring(6, 10); // Extract the year as YYYY

            // Navigate to the target year
            while (true) {
                Select s = new Select(actualYearElement);
                String currentYear = s.getFirstSelectedOption().getText();
                if (currentYear.equals(targetYear)) {
                    break; // Exit the loop if the year matches
                }
            }

            // Navigate to the target month
            while (true) {
                Select s = new Select(actualMonthElement);
                String currentMonth = s.getFirstSelectedOption().getText();
                System.out.println("Actual Month : " + currentMonth);

                if (currentMonth.equals(getMonthName(targetMonth))) { // Convert MM to the month name
                    break; // Exit the loop if the month matches
                }
                click(nextMonthArrowElement); // Click the arrow to go to the next month
            }

            // Select the target day
            for (WebElement dateElement : allDatesElements) {
                String dayText = dateElement.getText();
                // HighlightElement(dateElement);
                if (dayText.equals(targetDay)) {
                    click(dateElement, "Days in Date Picker");
                    //dateElement.click();
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getMonthName(String monthNumber) {
        try {
            // Convert month number (MM) to month name (e.g., 01 -> January)
            switch (monthNumber) {
                case "01":
                    return "Jan";
                case "02":
                    return "Feb";
                case "03":
                    return "Mar";
                case "04":
                    return "Apr";
                case "05":
                    return "May";
                case "06":
                    return "Jun";
                case "07":
                    return "Jul";
                case "08":
                    return "Aug";
                case "09":
                    return "Sep";
                case "10":
                    return "Oct";
                case "11":
                    return "Nov";
                case "12":
                    return "Dec";
                default:
                    throw new IllegalArgumentException("Invalid month: " + monthNumber);
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Perform Click Action and Monitor Page Load Time")
    public void Perform_Click_ActionWithMonitoring(WebElement element, String stepDescription) {
        // Perform the action
        HighlightElement(element);
        element.click();
        // Log the performance for this action
        PerformanceLogger.capturePageLoadTime(stepDescription);
    }

    public String GenerateCAPhoneNumber() {
        // Create Faker instances for US and Canada
        Faker caFaker = new Faker(new java.util.Locale("en-CA"));
        // Generate random Canada phone number
        // String caPhoneNumber = caFaker.phoneNumber().phoneNumber(); // Format: (XXX) XXX-XXXX

        String caPhoneNumber = null;
        for (int i = 1; i <= 5; i++) {
            // Generate random US phone number in the format XXX-XXX-XXXX
            caPhoneNumber = caFaker.phoneNumber().cellPhone().replaceAll("[^\\d]", "").substring(0, 10);
            caPhoneNumber = caPhoneNumber.substring(0, 3) + "-" + caPhoneNumber.substring(3, 6) + "-" + caPhoneNumber.substring(6);
            return caPhoneNumber;
        }
        return caPhoneNumber;
    }

    public String GenerateUsPhoneNumber() {
        // Create Faker instances for US and Canada
        Faker usFaker = new Faker(new java.util.Locale("en-US"));
        // Generate random US phone number
        // String usPhoneNumber = usFaker.phoneNumber().phoneNumber(); // Format: (XXX) XXX-XXXX
        String usPhoneNumber = null;
        for (int i = 1; i <= 5; i++) {
            // Generate random US phone number in the format XXX-XXX-XXXX
            usPhoneNumber = usFaker.phoneNumber().cellPhone().replaceAll("[^\\d]", "").substring(0, 10);
            usPhoneNumber = usPhoneNumber.substring(0, 3) + "-" + usPhoneNumber.substring(3, 6) + "-" + usPhoneNumber.substring(6);
            return usPhoneNumber;
        }

        return usPhoneNumber;
    }

    public String Generate_Random_EmailId() {
        Faker faker = new Faker();
        // Generate random Gmail addresses
        String firstName = faker.name().firstName().toLowerCase();
        String lastName = faker.name().lastName().toLowerCase();
        int randomNumber = faker.number().numberBetween(100, 999); // Ensures uniqueness

        // Combine parts to create Gmail address
        String randomGmail = firstName + "." + lastName + randomNumber + "@gmail.com";
        return randomGmail;
    }

    public String Generate_CompanyName() {
        Faker usFaker = new Faker(new java.util.Locale("en-US"));
        Faker caFaker = new Faker(new java.util.Locale("en-CA"));

        // Randomly decide whether to generate a US or CA company name
        Faker selectedFaker = Math.random() > 0.5 ? usFaker : caFaker;

        // Generate a random company name
        String companyName = selectedFaker.company().name();

        // Convert to Title Case directly in this function
        StringBuilder titleCase = new StringBuilder();
        for (String word : companyName.split("\\s+")) {
            if (!word.isEmpty()) {
                titleCase.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    titleCase.append(word.substring(1).toLowerCase());
                }
                titleCase.append(" ");
            }
        }

        return titleCase.toString().trim();
    }


    /**
     * This method is used to read the pdf and return the pdf content
     *
     * @param PDFFile
     * @return : PDF Content as String data
     * @throws IOException
     * @Description : This method will strip the text in the pdf and return the pdf content as Dtring
     * @author : Sakrateesh R
     */
    public String PDF_Reader(String PDFFile) throws IOException {
        String PDFFilePath = PDFFile;
        PDDocument document = PDDocument.load(new File(PDFFilePath));
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String pdfText = pdfStripper.getText(document);
        return pdfText;
    }

    /**
     * Provides a mechanism to retry a specific action multiple times if it fails due to a {@link NoSuchElementException}.
     * <p>
     * This method is useful for handling intermittent issues such as delayed element loading in Selenium tests.
     * It retries the given action up to a specified maximum number of attempts, introducing a delay between retries.
     * If the maximum retry limit is reached and the action still fails, the exception is re-thrown.
     * </p>
     *
     * <pre>
     * Example Usage:
     * {@code
     * retryAction(() -> someMethodThatMightFail(), 3);
     * }
     * </pre>
     *
     * @param action     A {@link Runnable} representing the action to be performed.
     *                   This is typically a lambda or method reference containing the code prone to failure.
     * @param maxRetries The maximum number of retry attempts allowed.
     *                   The method will stop retrying after this limit is reached and propagate the exception.
     * @throws NoSuchElementException if the action fails in all retry attempts.
     * @author Balaji N
     */
    public void retryAction(Runnable action, int maxRetries) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                action.run();
                return;
            } catch (TimeoutException e) {
                attempt++;
                if (attempt >= maxRetries) {
                    throw e;
                }
                getDriver().navigate().refresh();
                delayWithGivenTime(2000);
            } catch (StaleElementReferenceException e) {
                attempt++;
                if (attempt >= maxRetries) {
                    throw e;
                }
                getDriver().navigate().refresh();
                delayWithGivenTime(2000);
            }
        }
    }

    public static void initializeLogDirectory() {
        File logDir = new File("logs");
        if (!logDir.exists()) {
            if (logDir.mkdir()) {
                System.out.println("Log directory created: " + logDir.getAbsolutePath());
            } else {
                System.out.println("Failed to create log directory!");
            }
        }
    }

    public static Logger getLogger(String testCaseName) {
        String logFilePath = "logs/" + testCaseName + ".log";
        System.setProperty("logFileName", logFilePath);
        logger = LogManager.getLogger(testCaseName);
        System.out.println("Logger initialized for test case: " + testCaseName + ", log file: " + logFilePath);
        return logger;
    }

    public void Verify_PDF_Content() {
        // 1. Specify the download directory path
        String downloadDir = System.getProperty("user.home") + "/Downloads";

        // 2. Get the latest downloaded file (PDF) from the directory
        File latestFile = getLatestFileFromDir(downloadDir, ".pdf");
        if (latestFile != null) {
            System.out.println("Latest downloaded PDF file: " + latestFile.getName());

            // 3. Open the PDF and read its content
            String pdfContent = null;
            try {
                pdfContent = extractPDFContent(latestFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // 4. Check if "Invoice Number" is present
            if (pdfContent.contains("Invoice Number")) {
                System.out.println("✅ Invoice Number is present in the PDF.");

                // 5. Count the number of invoices
                int invoiceCount = countOccurrences(pdfContent, "Invoice Number");
                System.out.println("Number of invoices in the PDF: " + invoiceCount);
            } else {
                System.out.println("❌ Invoice Number is not present in the PDF.");
            }
        } else {
            System.out.println("No PDF files found in the download directory.");
        }
    }

    // Method to get the latest downloaded file with the specified extension
    public static File getLatestFileFromDir(String dirPath, String extension) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(extension));

        if (files != null && files.length > 0) {
            File latestFile = files[0];
            for (File file : files) {
                if (file.lastModified() > latestFile.lastModified()) {
                    latestFile = file;
                }
            }
            return latestFile;
        }
        return null;
    }

    // Method to extract text content from a PDF file
    public static String extractPDFContent(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             PDDocument document = PDDocument.load(fis)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        }
    }

    // Method to count occurrences of a substring in a string
    public static int countOccurrences(String str, String substring) {
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }
        return count;
    }

    /**
     * This method returns the previous month in the format "MMMM yyyy".
     *
     * @return
     */
    public static String getPreviousMonth() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();

        // Subtract one month
        calendar.add(Calendar.MONTH, -1);

        // Format the date as "Month Year"
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
        return dateFormat.format(calendar.getTime());
    }

    /**
     * This method will return the random item code dynamically
     *
     * @return
     * @Author Balaji N
     */
    public static String getRandomFlowerCode() {
        List<String> colors = Arrays.asList(
                "Red", "White", "Pink", "Yellow", "Purple", "Orange",
                "Blue", "Green", "Violet", "Indigo", "Brown", "Black",
                "Grey", "Turquoise", "Magenta", "Cyan", "Maroon", "Beige",
                "Lavender", "Olive", "Navy", "Peach", "Coral", "Teal",
                "Mint", "Charcoal", "Salmon", "Amber", "Bronze", "Gold",
                "Silver", "Ivory", "Crimson", "Fuchsia", "Mustard", "Lilac",
                "Tan", "Aqua", "Emerald", "Sky Blue", "Rose Gold", "Rust",
                "Copper", "Mauve", "Plum", "Burgundy", "Slate", "Sand",
                "Denim", "Sea Green", "Forest Green", "Canary Yellow", "Midnight Blue"
        );

        List<String> flowers = Arrays.asList(
                "Rose", "Lily", "Tulip", "Orchid", "Daisy", "Sunflower",
                "Marigold", "Jasmine", "Carnation", "Lavender",
                "Peony", "Chrysanthemum", "Begonia", "Hibiscus", "Zinnia",
                "Aster", "Daffodil", "Camellia", "Petunia", "Freesia",
                "Geranium", "Gladiolus", "Snapdragon", "Pansy", "Iris",
                "Anemone", "Azalea", "Calla Lily", "Cosmos", "Foxglove",
                "Gardenia", "Hydrangea", "Lilac", "Magnolia", "Narcissus",
                "Oleander", "Passionflower", "Phlox", "Ranunculus", "Rhododendron",
                "Salvia", "Statice", "Sweet Pea", "Tuberose", "Verbena",
                "Viola", "Yarrow", "Bluebell", "Calendula", "Cornflower",
                "Heliconia", "Morning Glory", "Primrose", "Scabiosa", "Protea"
        );

        Random random = new Random();

        String color = colors.get(random.nextInt(colors.size()));
        String flower = flowers.get(random.nextInt(flowers.size()));

        // Remove spaces and take first 3 characters in uppercase
        String colorCode = color.replaceAll("\\s+", "").substring(0, 3).toUpperCase();
        String flowerCode = flower.replaceAll("\\s+", "").substring(0, 3).toUpperCase();

        int uniqueNumber = 100 + random.nextInt(900); // 3-digit random number

        return flowerCode + colorCode + "-" + uniqueNumber;
    }


    // Method to generate random flower product item name
    public static String getRandomFlowerName() {
        List<String> colors = Arrays.asList(
                "Red", "White", "Pink", "Yellow", "Purple", "Orange",
                "Blue", "Green", "Violet", "Indigo", "Brown", "Black",
                "Grey", "Turquoise", "Magenta", "Cyan", "Maroon", "Beige",
                "Lavender", "Olive", "Navy", "Peach", "Coral", "Teal",
                "Mint", "Charcoal", "Salmon", "Amber", "Bronze", "Gold",
                "Silver", "Ivory", "Crimson", "Fuchsia", "Mustard", "Lilac",
                "Tan", "Aqua", "Emerald", "Sky Blue", "Rose Gold", "Rust",
                "Copper", "Mauve", "Plum", "Burgundy", "Slate", "Sand",
                "Denim", "Sea Green", "Forest Green", "Canary Yellow", "Midnight Blue"
        );

        List<String> flowers = Arrays.asList(
                "Rose", "Lily", "Tulip", "Orchid", "Daisy", "Sunflower",
                "Marigold", "Jasmine", "Carnation", "Lavender",
                "Peony", "Chrysanthemum", "Begonia", "Hibiscus", "Zinnia",
                "Aster", "Daffodil", "Camellia", "Petunia", "Freesia",
                "Geranium", "Gladiolus", "Snapdragon", "Pansy", "Iris",
                "Anemone", "Azalea", "Calla Lily", "Cosmos", "Foxglove",
                "Gardenia", "Hydrangea", "Lilac", "Magnolia", "Narcissus",
                "Oleander", "Passionflower", "Phlox", "Ranunculus", "Rhododendron",
                "Salvia", "Statice", "Sweet Pea", "Tuberose", "Verbena",
                "Viola", "Yarrow", "Bluebell", "Calendula", "Cornflower",
                "Heliconia", "Morning Glory", "Primrose", "Scabiosa", "Protea"
        );

        Random random = new Random();

        String color = colors.get(random.nextInt(colors.size()));
        String flower = flowers.get(random.nextInt(flowers.size()));
        return flower + " " + color;
    }

    public static String generate_Price() {
        Random random = new Random();
        BigDecimal rounded;
        while (true) {
            double price = 10 + (90 * random.nextDouble());
            rounded = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);

            String decimalPart = rounded.toString().split("\\.")[1];

            // Avoid if decimal ends with '0' (e.g., 56.80 or 45.00)
            if (!decimalPart.endsWith("0")) {
                break;
            }
        }

        return rounded.toString();
    }


    public int generate_random_three_digits() {
        int randomNum = ThreadLocalRandom.current().nextInt(100, 999);
        return randomNum;
    }

    public static WebElement explicitWait(By locator, String conditionType, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        WebElement element = null;

        switch (conditionType.toLowerCase()) {
            case "visible":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;

            case "clickable":
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                break;

            case "presence":
                element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                break;

            case "invisible":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
                break;

          /*  case "selected":
                element = wait.until(ExpectedConditions.elementToBeSelected(locator));
                break;
*/
            case "frame":
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
                break;

          /*  case "text_present":
                element = wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, "Expected Text"));
                break;
*/
            default:
                throw new IllegalArgumentException("Invalid condition type: " + conditionType);
        }

        return element;
    }

    public WebElement locateElement(String locatorType, String value) {
        try {
            switch (locatorType.toLowerCase()) {
                case "id":
                    return getDriver().findElement(By.id(value));
                case "name":
                    return getDriver().findElement(By.name(value));
                case "class":
                    return getDriver().findElement(By.className(value));
                case "link":
                    return getDriver().findElement(By.linkText(value));
                case "xpath":
                    return getDriver().findElement(By.xpath(value));
                case "css":
                    return getDriver().findElement(By.cssSelector(value));
            }
        } catch (NoSuchElementException e) {
            throw new RuntimeException();
        } catch (Exception e) {
        }
        return null;
    }

    public List<WebElement> locateElements(String type, String value) {
        try {
            switch (type.toLowerCase()) {
                case "id":
                    return getDriver().findElements(By.id(value));
                case "name":
                    return getDriver().findElements(By.name(value));
                case "class":
                    return getDriver().findElements(By.className(value));
                case "link":
                    return getDriver().findElements(By.linkText(value));
                case "xpath":
                    return getDriver().findElements(By.xpath(value));
                case "css":
                    return getDriver().findElements(By.cssSelector(value));
            }
        } catch (NoSuchElementException e) {
            System.err.println("The Element with locator:" + type + " Not Found with value: " + value);
            throw new RuntimeException();
        }
        return null;
    }

    public static String captureFullPageScreenshotBase64ForAllure() {
        WebDriver driver = TestBaseClass.getDriver();

        if (driver instanceof HasFullPageScreenshot) {
            // Capture full-page screenshot as Base64
            String screenshotBase64 = ((HasFullPageScreenshot) driver).getFullPageScreenshotAs(OutputType.BASE64);

            // Attach the screenshot to Allure report
            Allure.getLifecycle().addAttachment(
                    "Full Page Screenshot on Failure (Base64)",
                    "image/png",
                    "png",
                    java.util.Base64.getDecoder().decode(screenshotBase64)
            );

            return screenshotBase64;
        } else {
            System.err.println("Full page screenshot is only supported in ChromeDriver.");
            return "";
        }
    }

    public void navigatePage(String url) {
        getDriver().navigate().to(url);
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
        wait_For_Page_To_Be_Stable(getDriver());
    }

    public void backToPreviousPage() {
        getDriver().navigate().back();
    }

    /**
     * Perform double-click using JavaScript (thread-safe alternative)
     */
    public void doubleClickUsingJS(WebElement ele, String fieldname) {
        try {
            if (ele == null) {
                throw new IllegalArgumentException("WebElement is null for field: " + fieldname);
            }

            fluentWait(ele);
            ((JavascriptExecutor) getDriver()).executeScript(
                    "var event = new MouseEvent('dblclick', {" +
                            "bubbles: true, cancelable: true, view: window});" +
                            "arguments[0].dispatchEvent(event);", ele);

            System.out.println("Double click performed via JS on: " + fieldname);

        } catch (Exception e) {
            printError(ele, fieldname, "Error in JS double click: " + e.getMessage(), e);
        }
    }

    /**
     * This method will be wait for page to stable with timeout of 120 seconds
     *
     * @param driver
     */
    public static void wait_For_Page_To_Be_Stable(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.pollingEvery(Duration.ofMillis(1000));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
            //  wait.until(webDriver -> js.executeScript("return (typeof jQuery !== 'undefined') ? jQuery.active == 0 : true").equals(true));
            wait.until(webDriver -> (Boolean) js.executeScript(
                    "return Array.from(document.images).every(img => img.complete) && " +
                            "(document.fonts ? document.fonts.status === 'loaded' : true);"
            ));
            Thread.sleep(1000);
        } catch (TimeoutException | InterruptedException e) {
            String readyState = (String) js.executeScript("return document.readyState");
            // Object jQueryActive = js.executeScript("return (typeof jQuery !== 'undefined') ? jQuery.active : 'jQuery not present'");
            Object imageStatus = js.executeScript("return Array.from(document.images).map(img => img.complete)");
            Object fontStatus = js.executeScript("return (document.fonts ? document.fonts.status : 'not supported')");

            Map<String, Object> perfTiming = (Map<String, Object>) js.executeScript("return window.performance.timing.toJSON();");
            long pageLoadTime = perfTiming.get("loadEventEnd") != null
                    ? ((Number) perfTiming.get("loadEventEnd")).longValue() - ((Number) perfTiming.get("navigationStart")).longValue()
                    : -1;

            String debugInfo = "\n"
                    + "❌ [PAGE LOAD TIMEOUT] Page did not stabilize within 120 seconds.\n"
                    + "\n"
                    + "🧾 DETAILS:\n"
                    + " - Page readyState        : " + readyState + "\n"
                    // + " - jQuery active requests : " + jQueryActive + "\n"
                    + " - Images loaded          : " + imageStatus + "\n"
                    + " - Fonts loaded           : " + fontStatus + "\n"
                    + " - Approx. page load time : " + (pageLoadTime >= 0 ? pageLoadTime + " ms" : "Unavailable") + "\n"
                    + "\n"
                    + "🔍 POSSIBLE CAUSES:\n"
                    //  + " - API calls are taking too long to complete (jQuery.active > 0)\n"
                    + " - Some images or fonts may be slow to load\n"
                    + " - JavaScript still running in the background\n"
                    + "\n"
                    + "📌 NOTE: This may not indicate a functional bug, but it slows automation stability.\n";

            Allure.addAttachment("Page Stability Timeout Info", debugInfo);

            // Throw exception with readable log
            throw new TimeoutException(debugInfo, e);
        }
    }


    public String Past_30Days_Date() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDay = currentDate.plusDays(-30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedPreviousDay = previousDay.format(formatter);
        return formattedPreviousDay;
    }

    public static String removeTrailingZeros(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value); // If whole number
        } else {
            return String.valueOf(value); // If decimal, automatically removes unnecessary zeros
        }
    }

    public static String get_Random_US_ZipCode() {
        List<String> realZipCodes = Arrays.asList(
                "10001", "90001", "60601", "77001", "85001",
                "92101", "75201", "33101", "98101", "30301",
                "63084", "63332", "63342", "63357", "63089",
                "63039", "63055"
        );
        Random random = new Random();
        return realZipCodes.get(random.nextInt(realZipCodes.size()));
    }

    static List<String> colors = Arrays.asList(
            "Red", "White", "Pink", "Yellow", "Purple", "Orange",
            "Blue", "Green", "Violet", "Indigo", "Brown", "Black",
            "Grey", "Turquoise", "Magenta", "Cyan", "Maroon", "Beige",
            "Lavender", "Olive", "Navy", "Peach", "Coral", "Teal",
            "Mint", "Charcoal", "Salmon", "Amber", "Bronze", "Gold",
            "Silver", "Ivory", "Crimson", "Fuchsia", "Mustard", "Lilac",
            "Tan", "Aqua", "Emerald", "Sky Blue", "Rose Gold", "Rust",
            "Copper", "Mauve", "Plum", "Burgundy", "Slate", "Sand",
            "Denim", "Sea Green", "Forest Green", "Canary Yellow", "Midnight Blue"
    );

    static List<String> flowers = Arrays.asList(
            "Rose", "Lily", "Tulip", "Orchid", "Daisy", "Sunflower",
            "Marigold", "Jasmine", "Carnation", "Lavender",
            "Peony", "Chrysanthemum", "Begonia", "Hibiscus", "Zinnia",
            "Aster", "Daffodil", "Camellia", "Petunia", "Freesia",
            "Geranium", "Gladiolus", "Snapdragon", "Pansy", "Iris",
            "Anemone", "Azalea", "Calla Lily", "Cosmos", "Foxglove",
            "Gardenia", "Hydrangea", "Lilac", "Magnolia", "Narcissus",
            "Oleander", "Passionflower", "Phlox", "Ranunculus", "Rhododendron",
            "Salvia", "Statice", "Sweet Pea", "Tuberose", "Verbena",
            "Viola", "Yarrow", "Bluebell", "Calendula", "Cornflower",
            "Heliconia", "Morning Glory", "Primrose", "Scabiosa", "Protea"
    );

    public static String generate_Product_Variant() {
        Faker faker = new Faker();
        Random random = new Random();
        String color = colors.get(random.nextInt(colors.size()));
        String flower = flowers.get(random.nextInt(flowers.size()));
        return color + " " + flower;
    }

    public static String generate_SKU_Code() {
        Faker faker = new Faker();
        Random random = new Random();
        List<String> colors = Arrays.asList("Red", "White", "Pink", "Yellow", "Blue");
        List<String> flowers = Arrays.asList("Rose", "Tulip", "Lily", "Peony", "Daisy");
        String color = colors.get(random.nextInt(colors.size()));
        String flower = flowers.get(random.nextInt(flowers.size()));

        // SKU Format: FLR-RED-ROS-5839
        String colorCode = color.substring(0, 3).toUpperCase();
        String flowerCode = flower.substring(0, 3).toUpperCase();
        int uniqueNumber = 1000 + random.nextInt(9000);

        return "FLR-" + colorCode + "-" + flowerCode + "-" + uniqueNumber;
    }


    //===============================Mobile Automation Setup==========================================================
    private static ThreadLocal<AppiumDriver> mobileDriver = new ThreadLocal<>();

    public AppiumDriver getMobileDriver() {
        return mobileDriver.get();
    }

    public void launchMobileApplication() {
        String serverUrl = prop.getProperty("mobile.appiumServerUrl");
        String platformName = prop.getProperty("mobile.platformName");
        String deviceName = prop.getProperty("mobile.deviceName");
        String appPath = prop.getProperty("mobile.appPath");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        caps.setCapability(MobileCapabilityType.APP, new File(appPath).getAbsolutePath());
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");

        try {
            AppiumDriver driver = new AppiumDriver(new URL(serverUrl), caps);
            mobileDriver.set(driver);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        } catch (Exception e) {
            Assert.fail("Failed to launch mobile app: " + e.getMessage());
        }
    }

   /*    @AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
 public void tearDown() {
        // Quit Web Driver
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // Clear ThreadLocal
        }
        // Quit Mobile Driver
        if (getMobileDriver() != null) {
            getMobileDriver().quit();
            mobileDriver.remove(); // Clear ThreadLocal
        }
    }*/

    /**
     * Drag and drop image to upload
     *
     * @param dropTarget
     * @param filePath
     * @Author: Balaji N
     */
    public static void simulateDragAndDropImageUpload(WebElement dropTarget, String filePath) {
        try {
            File file = new File(filePath);
            String base64 = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
            String fileName = file.getName();

            String script =
                    "var dropTarget = arguments[0];" +
                            "var base64 = arguments[1];" +
                            "var fileName = arguments[2];" +
                            "function base64ToBlob(base64, mime) {" +
                            "   mime = mime || '';" +
                            "   var byteChars = atob(base64);" +
                            "   var byteNumbers = new Array(byteChars.length);" +
                            "   for (var i = 0; i < byteChars.length; i++) {" +
                            "       byteNumbers[i] = byteChars.charCodeAt(i);" +
                            "   }" +
                            "   var byteArray = new Uint8Array(byteNumbers);" +
                            "   return new Blob([byteArray], {type: mime});" +
                            "}" +
                            "var blob = base64ToBlob(base64, 'image/png');" +
                            "var file = new File([blob], fileName, {type: 'image/png'});" +
                            "var dataTransfer = new DataTransfer();" +
                            "dataTransfer.items.add(file);" +
                            "var event = new DragEvent('drop', {" +
                            "   dataTransfer: dataTransfer," +
                            "   bubbles: true," +
                            "   cancelable: true" +
                            "});" +
                            "dropTarget.dispatchEvent(event);";

            ((JavascriptExecutor) getDriver()).executeScript(script, dropTarget, base64, fileName);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Drag and drop image upload failed.", e);
        }
    }

    public static void simulateNativeDragAndDrop(String filePath) throws Exception {
        Robot robot = new Robot();
        robot.delay(2000);

        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(1000);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static String generateWebCategoryName() {
        Faker faker = new Faker();
        Set<String> usedCategories = new HashSet<>();

        String category;
        do {
            String color = capitalize(faker.color().name());                  // e.g., "Lavender"
            String noun = capitalize(faker.commerce().productName().split(" ")[0]); // e.g., "Basket"
            category = color + " " + noun;                                    // e.g., "Lavender Basket"
        } while (usedCategories.contains(category));
        usedCategories.add(category);
        return category;
    }

    private static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

    public static String generateCategoryMetaTitle() {
        Faker faker = new Faker();
        String flower = faker.commerce().productName().split(" ")[0]; // e.g., "Bouquet"
        String adjective = faker.commerce().material();               // e.g., "Soft"
        String occasion = faker.options().option("Birthday", "Anniversary", "Wedding", "Valentine", "Funeral", "Spring");

        return "Buy " + adjective + " " + flower + " for " + occasion;
    }

    public static String getData(String key, String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(new File(System.getProperty("user.dir") +
                    "/src/test/resources/testData/" + fileName));

            String[] keys = key.split("\\.");
            for (String k : keys) {
                rootNode = rootNode.path(k);
            }

            return rootNode.asText();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON data from " + fileName);
        }
    }

    public String get_Border_Color_Of_Element(WebElement element, String fieldname) {
        HighlightElement(element);
        return element.getCssValue("border-color");
    }

    public String get_Background_Color_Of_Element(WebElement element, String fieldname) {
        HighlightElement(element);
        return element.getCssValue("background-color");
    }

    public String get_Style_Attribute_Of_Element(WebElement element, String fieldname) {
        HighlightElement(element);
        return element.getAttribute("style");
    }

    /**
     * Utility to check if a toggle button is ON based on background-color and border-color.
     *
     * @param toggleElement The <span> WebElement representing the toggle container.
     * @return true if toggle is ON (green), false if OFF (red/gray)
     */
    public boolean is_Toggle_On_By_Style(WebElement toggleElement, String fieldname) {
        try {
            String style = toggleElement.getAttribute("style").toLowerCase();
            System.out.println("Toggle style: " + style);

            // ON state: green toggle (rgb(197, 231, 244) or similar)
            boolean isGreen = style.contains("rgb(197, 231, 244)");

            // OFF state: white toggle with gray border (rgb(255, 255, 255) or rgb(223, 223, 223))
            // Just returning based on ON state match
            return isGreen;
        } catch (Exception e) {
            System.out.println("Error while checking toggle state: " + e.getMessage());
            return false;
        }
    }

    public static String getCurrentDateInAST() {
        // Define AST timezone
        ZoneId astZone = ZoneId.of("America/Puerto_Rico"); // AST zone

        // Get current date-time in AST
        ZonedDateTime astDateTime = ZonedDateTime.now(astZone);

        // Format date to MM/dd/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        return astDateTime.format(formatter);
    }

    public static String get_Current_EST_Time() {
        // Get current IST time (system default) and convert to EST
        ZonedDateTime istNow = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime estTime = istNow.withZoneSameInstant(ZoneId.of("America/New_York"));

        // Format to match UI output like: Jul 7 2025 5:18AM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH);
        return estTime.format(formatter);
    }

    public static String get_Current_AST_Time() {
        // Get current IST time and convert to AST (Atlantic Standard Time)
        ZonedDateTime istNow = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime astTime = istNow.withZoneSameInstant(ZoneId.of("Canada/Atlantic"));

        // Format to match UI output like: Jul 7 2025 5:18AM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH);
        return astTime.format(formatter);
    }

    public static String get_Current_AST_Date() {
        // Get current IST time
        ZonedDateTime istNow = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));

        // Convert IST to AST
        ZonedDateTime astDateTime = istNow.withZoneSameInstant(ZoneId.of("Canada/Atlantic"));

        // Format just the date like: Jul 7 2025
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
        return astDateTime.format(dateFormatter);
    }

    public boolean isTimestampWithinOneMinute(String actual, String expected) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH);
        LocalDateTime actualTime = LocalDateTime.parse(actual, formatter);
        LocalDateTime expectedTime = LocalDateTime.parse(expected, formatter);
        long minutesDiff = Math.abs(Duration.between(actualTime, expectedTime).toMinutes());
        return minutesDiff <= 1;
    }

    // Formatter: JUL 7 2025 7:27AM
    private final DateTimeFormatter wordDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH);

    // Formatter: 07/07/2025 7:27AM
    private final DateTimeFormatter numberDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mma", Locale.ENGLISH);

    /**
     * Returns current Atlantic time formatted as JUL 7 2025 7:27AM
     */
    public String getAtlanticTime_WordDateFormat() {
        ZonedDateTime astNow = ZonedDateTime.now(ZoneId.of("America/Halifax"));
        return astNow.format(wordDateFormat).toUpperCase(); // Match "JUL" style
    }

    /**
     * Returns current Atlantic time formatted as 07/07/2025 7:27 AM
     *
     * @Author: Balaji N
     */
    public String getAtlanticTime_NumberDateFormat() {
        DateTimeFormatter numberDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
        ZonedDateTime astNow = ZonedDateTime.now(ZoneId.of("America/Halifax"));
        return astNow.format(numberDateFormat);
    }

    /**
     * Compare two datetime strings with ±1 minute tolerance
     */
    public boolean isTimeWithinOneMinute(String actual, String expected, DateTimeFormatter formatter) {
        LocalDateTime actualTime = LocalDateTime.parse(actual, formatter);
        LocalDateTime expectedTime = LocalDateTime.parse(expected, formatter);
        long minutes = Math.abs(Duration.between(actualTime, expectedTime).toMinutes());
        return minutes <= 1;
    }


    // Overloaded convenience methods
    public boolean isWordDateFormatWithinOneMinute(String actual) {
        return isTimeWithinOneMinute(actual, getAtlanticTime_WordDateFormat(), wordDateFormat);
    }

    public boolean isNumberDateFormatWithinOneMinute(String actual) {
        return isTimeWithinOneMinute(actual, getAtlanticTime_NumberDateFormat(), numberDateFormat);
    }

    public String convertToExpectedFormat(String dateStr) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mma");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");

        LocalDateTime dateTime = LocalDateTime.parse(dateStr, inputFormatter);
        return dateTime.format(outputFormatter).toUpperCase();
    }

    public static String getEasternDate(String format) {
        try {
            ZoneId easternZone = ZoneId.of("America/New_York");
            ZonedDateTime easternTime = ZonedDateTime.now(easternZone);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return easternTime.format(formatter);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to get Eastern Time date in format [" + format + "]: " + e.getMessage(), e);
        }
    }

    public void logPass(String message) {
        Allure.step(message);  // or add a green icon log if using custom
        System.out.println("PASS: " + message);
    }

    public void logFail(String message) {
        Allure.step("❌ " + message);
        System.err.println("FAIL: " + message);
    }

    /**
     * Logs the page load status for a given page name and navigation action.<br>
     * If the navigation action throws an exception, the status will be logged as "FAILED".
     *
     * @param pageName
     * @param navigation
     * @Author: Balaji N
     */
    public void logPageLoad(String pageName, Runnable navigation) {
        long start = System.currentTimeMillis();
        boolean success = false;
        try {
            navigation.run();
            success = true;
        } catch (Exception e) {
            String failLog = String.format(
                    "\n" +
                            " 🚫 %-25s \n" +
                            " Thread: %-6d\n" +
                            " Page:   %-20s \n" +
                            " FAILED: %-18s \n" +
                            "\n",
                    "PAGE LOAD FAILURE", Thread.currentThread().getId(), pageName, e.toString()
            );
            pageLoadLogs.get().add(failLog);
            throw e;
        } finally {
            long end = System.currentTimeMillis();
            long loadTimeMs = end - start;
            double loadTimeSec = loadTimeMs / 1000.0;
            String statusEmoji = success ? "✅" : "❌";
            String statusTxt = success ? "SUCCESS" : "FAILED";

            String logEntry = String.format(
                    " %s Thread: %-5d │ Page : %-22s \n" +
                            " Page Load Time         : %6d ms (%6.2f s)\n" +
                            " Page Navigation Status : %-10s\n",
                    statusEmoji, Thread.currentThread().getId(), pageName,
                    loadTimeMs, loadTimeSec, statusTxt
            );

            pageLoadLogs.get().add(logEntry);
        }
    }

    public void attachLogsToAllure() {
        StringBuilder builder = new StringBuilder();
        for (String log : pageLoadLogs.get()) {
            builder.append(log).append("\n");
        }
        Allure.addAttachment("Page Load Summary", "text/plain", builder.toString());
    }

    public static String generate_Ceremony_Location() {
        Faker faker = new Faker();
        String venueName = faker.company().name() + " Banquet Center";
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String zip = faker.address().zipCode();

        String fullAddress = String.format("%s, %s, %s, %s %s", venueName, address, city, state, zip);
        return fullAddress;
    }

    public boolean isOrderConfirmationEmailReceived(String expectedOrderNumber) throws Exception {
        String host = "imap.gmail.com";
        String username = "hanaposqateam@gmail.com";
        String password = "bcfburrmktksjckr";

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");

        int maxAttempts = 3;
        Exception lastException = null;
        String subjectKeyword = "Order Confirmation For ";

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.println("Attempt " + attempt + " to check order confirmation email...");
            try {
                Session session = Session.getInstance(props);
                Store store = session.getStore();
                store.connect(host, username, password);

                Folder inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_ONLY);

                Message[] messages = inbox.getMessages();

                // Search latest to oldest for efficiency
                for (int i = messages.length - 1; i >= 0; i--) {
                    Message message = messages[i];
                    String subject = message.getSubject();

                    if (subject != null) {
                        // Build the expected subject string
                        String expectedSubject = subjectKeyword + expectedOrderNumber;

                        if (subject.contains(expectedSubject)) {
                            System.out.println("Order confirmation email received with subject: " + subject);

                            inbox.close(false);
                            store.close();
                            return true;
                        }
                    }
                }

                inbox.close(false);
                store.close();

                // Wait before next retry
                if (attempt < maxAttempts) {
                    Thread.sleep(10000);
                }

            } catch (Exception e) {
                lastException = e;
                System.out.println("Error in attempt " + attempt + ": " + e.getMessage());
                if (attempt < maxAttempts) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ie) {
                    }
                }
            }
        }

        if (lastException != null) {
            throw new Exception("Failed to verify order confirmation email for order: " + expectedOrderNumber, lastException);
        }

        System.out.println("Order confirmation email not found for order: " + expectedOrderNumber);
        return false;
    }


    public String extractEmailBodyContent(Message message) throws Exception {
        Object contentObj = message.getContent();
        StringBuilder contentBuilder = new StringBuilder();

        if (contentObj instanceof String) {
            // Simple text content
            contentBuilder.append((String) contentObj);
        } else if (contentObj instanceof Multipart) {
            Multipart multipart = (Multipart) contentObj;

            // Iterate through each part
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart part = multipart.getBodyPart(i);
                String contentType = part.getContentType().toLowerCase();

                if (contentType.contains("text/plain")) {
                    // Plain text part
                    contentBuilder.append(part.getContent().toString()).append("\n");
                } else if (contentType.contains("text/html")) {
                    // HTML part - optionally convert HTML to plain text, or append raw HTML
                    String html = part.getContent().toString();
                    String plainText = org.jsoup.Jsoup.parse(html).text();  // Requires jsoup library
                    contentBuilder.append(plainText).append("\n");
                }
                // You can also handle other content types (e.g., inline images or attachments) if needed
            }
        }
        return contentBuilder.toString().trim();
    }

    /**
     * Waits for jQuery to finish and logs any pending requests if timeout occurs.
     */
    public static void waitForJQueryToFinish(int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds));


        try {
            wait.until(webDriver -> {
                try {
                    Long active = (Long) ((JavascriptExecutor) webDriver)
                            .executeScript("return window.jQuery ? jQuery.active : 0;");
                    return active == 0;
                } catch (Exception e) {
                    return true; // assume no AJAX
                }
            });
        } catch (TimeoutException e) {
            // Timeout => AJAX still pending, try to extract details
            try {
                List<Map<String, Object>> pendingRequests =
                        (List<Map<String, Object>>) ((JavascriptExecutor) getDriver())
                                .executeScript(
                                        "if (window.pendingAjaxRequests) {" +
                                                "   return window.pendingAjaxRequests" +
                                                "     .filter(r => r.status === 'pending')" +
                                                "     .map(r => ({url: r.url, method: r.method, timestamp: r.timestamp, status: r.status}));" +
                                                "} else {" +
                                                "   return [];" +
                                                "}"
                                );

                if (pendingRequests != null && !pendingRequests.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("🚨 Pending AJAX Requests Detected:\n\n");
                    for (Map<String, Object> req : pendingRequests) {
                        sb.append(" - URL: ").append(req.get("url"))
                                .append("\n   Method: ").append(req.get("method"))
                                .append("\n   Started At: ").append(req.get("timestamp"))
                                .append("\n   Status: ").append(req.get("status"))
                                .append("\n\n");
                    }

                    String pendingInfo = sb.toString();

                    // Print to console
                    System.err.println("[AJAX PENDING DETAILS]\n" + pendingInfo);

                    // Attach nicely in Allure
                    Allure.addAttachment("Pending jQuery Requests", pendingInfo);
                } else {
                    System.err.println("[AJAX PENDING] Timeout occurred, but no pending requests were captured.");
                    Allure.addAttachment("Pending jQuery Requests", "Timeout occurred, but no pending requests were found.");
                }

            } catch (Exception jsEx) {
                System.err.println("[AJAX PENDING] Unable to extract pending requests: " + jsEx.getMessage());
            }

            // Rethrow original timeout so test still fails
            throw e;
        }
    }

    public static void enableAjaxAndFetchTracking() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String script =
                "if (!window.pendingAjaxRequests) {" +
                        "   window.pendingAjaxRequests = [];" +

                        // Hook XMLHttpRequest
                        "   (function(open, send) {" +
                        "       XMLHttpRequest.prototype.open = function(method, url) {" +
                        "           this._url = url;" +
                        "           this._method = method;" +
                        "           open.apply(this, arguments);" +
                        "       };" +
                        "       XMLHttpRequest.prototype.send = function() {" +
                        "           var xhr = this;" +
                        "           var req = { url: xhr._url, method: xhr._method, status: 'pending', timestamp: new Date().toISOString() };" +
                        "           window.pendingAjaxRequests.push(req);" +
                        "           var removeReq = function() { req.status = 'done'; };" +
                        "           xhr.addEventListener('loadend', removeReq);" +
                        "           send.apply(this, arguments);" +
                        "       };" +
                        "   })(XMLHttpRequest.prototype.open, XMLHttpRequest.prototype.send);" +

                        // Hook fetch
                        "   (function(fetch) {" +
                        "       window.fetch = function() {" +
                        "           var url = arguments[0];" +
                        "           var req = { url: url, method: (arguments[1] && arguments[1].method) || 'GET', status: 'pending', timestamp: new Date().toISOString() };" +
                        "           window.pendingAjaxRequests.push(req);" +
                        "           return fetch.apply(this, arguments).then(function(response) {" +
                        "               req.status = 'done';" +
                        "               return response;" +
                        "           }).catch(function(error) {" +
                        "               req.status = 'error';" +
                        "               throw error;" +
                        "           });" +
                        "       };" +
                        "   })(window.fetch);" +
                        "}";
        js.executeScript(script);
    }

    public static void injectAjaxTracker(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript(
                "if (!window.pendingAjaxRequests) {" +
                        "   window.pendingAjaxRequests = [];" +
                        "   (function(open, send) {" +
                        "       XMLHttpRequest.prototype.open = function(method, url) {" +
                        "           this._url = url;" +
                        "           this._method = method;" +
                        "           open.apply(this, arguments);" +
                        "       };" +
                        "       XMLHttpRequest.prototype.send = function(body) {" +
                        "           var xhr = this;" +
                        "           var req = { url: xhr._url, method: xhr._method, status: 'pending', timestamp: new Date().toISOString() };" +
                        "           window.pendingAjaxRequests.push(req);" +
                        "           var interval = setInterval(function() {" +
                        "               if (xhr.readyState === 4) {" +
                        "                   req.status = 'completed';" +
                        "                   clearInterval(interval);" +
                        "               }" +
                        "           }, 100);" +
                        "           send.apply(this, arguments);" +
                        "       };" +
                        "   })(XMLHttpRequest.prototype.open, XMLHttpRequest.prototype.send);" +
                        "}"
        );
    }

    public static String fetchPendingAjaxRequests(WebDriver driver) {
        try {
            List<Map<String, Object>> pendingRequests =
                    (List<Map<String, Object>>) ((JavascriptExecutor) driver).executeScript(
                            "if (window.pendingAjaxRequests) {" +
                                    "   return window.pendingAjaxRequests" +
                                    "     .filter(r => r.status === 'pending')" +
                                    "     .map(r => ({" +
                                    "         url: r.url," +
                                    "         method: r.method," +
                                    "         timestamp: r.timestamp," +
                                    "         status: r.status" +
                                    "     }));" +
                                    "} else { return []; }"
                    );

            if (pendingRequests != null && !pendingRequests.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append("🚨 Pending AJAX Requests Detected:\n\n");
                for (Map<String, Object> req : pendingRequests) {
                    sb.append(" - URL: ").append(req.get("url"))
                            .append("\n   Method: ").append(req.get("method"))
                            .append("\n   Started At: ").append(req.get("timestamp"))
                            .append("\n   Status: ").append(req.get("status"))
                            .append("\n\n");
                }
                return sb.toString();
            }
        } catch (Exception e) {
            return "[AJAX PENDING] Unable to fetch pending requests: " + e.getMessage();
        }
        return "";
    }
    public static String getDateTimeInSystemZone() {
        String datetimeStr = "Sep 1 2025 7:25AM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma", Locale.ENGLISH);

        // Parse the hardcoded datetime string without timezone info
        LocalDateTime localDateTime = LocalDateTime.parse(datetimeStr, formatter);

        // Define AST timezone (Atlantic Standard Time)
        ZoneId astZone = ZoneId.of("America/Halifax");

        // Localize datetime to AST timezone
        ZonedDateTime astZonedDateTime = localDateTime.atZone(astZone);

        // Convert to system default timezone
        ZoneId systemZone = ZoneId.systemDefault();
        ZonedDateTime systemZonedDateTime = astZonedDateTime.withZoneSameInstant(systemZone);

        // Format datetime in desired string format
        return systemZonedDateTime.format(formatter);
    }

}
