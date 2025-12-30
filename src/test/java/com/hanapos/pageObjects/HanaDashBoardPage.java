package com.hanapos.pageObjects;


import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.PageLoadLoggerUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

/**
 * Hana Dashboard page class in the pos application
 *
 * @Author Balaji N
 */
public class HanaDashBoardPage extends TestBaseClass {
    public HanaDashBoardPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //========================================= Hana Dashboard page web elements =========================
    @FindBy(xpath = "//a[@class='li_Hana navbar-brand ' or span[contains(text(),'hana')]]")
    private WebElement HanaLogo;

    @FindBy(xpath = "//a[@id='clsUnRead']/following::button[3]")
    private WebElement NewOrder_Menu_Btn;

    @FindBy(xpath = "//a[@id='clsUnRead']/following::button[5]")
    private WebElement Dispatch_Menu_Btn;

    @FindBy(xpath = "(//button[@class='btn btn-default dropdown-toggle'][normalize-space()='New Order'])")
    private WebElement NewOrderMenuBtn;

    @FindBy(xpath = "(//button[@class='btn btn-default dropdown-toggle'][normalize-space()='Dispatch'])")
    private WebElement DispatchMenuBtn;

    @FindBy(xpath = "//a[@class='li_Dispatch']")
    private WebElement quickDispatch;

    @FindBy(xpath = "//a[@class='li_AdvanceDispatch']")
    private WebElement advanceDispatch;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[@class='li_NewOrder'  or normalize-space()='Order Entry']")
    private WebElement OrderEntry;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[@class='li_CashAndCarry' or normalize-space()='Cash & Carry']")
    private WebElement CashAndCarry;

    @FindBy(xpath = "//span[normalize-space()='Orders']")
    private WebElement OrdersMenu;

    @FindBy(xpath = "//a[normalize-space()='All Orders']")
    private WebElement AllOrdersSubMenu;

    @FindBy(xpath = "//select[@id='ddlShop']")
    private WebElement ShopNameDropDown;

    @FindBy(xpath = "//div[@id='dashboard_datepicker']//div[@class='dashborad-tabs']")
    private WebElement DateSelection;

    @FindBy(xpath = "//span[contains(text(),' Wait Queue ')]")
    private WebElement WaitQueueTab;

    @FindBy(xpath = "//span[@id='lblWaitQueue']")
    private WebElement WaitQueueCount;

    @FindBy(xpath = "//span[contains(text(),' Pending Pickups ')]")
    private WebElement PendingPickupsTab;

    @FindBy(xpath = "//span[@id='lblPickupOrders']")
    private WebElement PendingPickupsCount;

    @FindBy(xpath = "//span[contains(text(),' Pending Deliveries ')]")
    private WebElement PendingDeliveriesTab;

    @FindBy(xpath = "//span[@id='lblAwaitingDispatch']")
    private WebElement PendingDeliveriesCount;

    @FindBy(xpath = "//span[contains(text(),' Pending Confirmations ')]")
    private WebElement PendingConfirmationsTab;

    @FindBy(xpath = "//span[@id='lblUnconfirm']")
    private WebElement PendingConfirmationsCount;

    @FindBy(xpath = "//label[@id='lblWaitcnt']")
    private WebElement WaitQueueIconCount;

    @FindBy(xpath = "//a[@id='idWaitQueue']")
    private WebElement WaitQueueIcon;

    @FindBy(xpath = "(//i[@class='fa fa-2x fa-shopping-basket'])[1]")
    private WebElement PendingPickupIcon;

    @FindBy(xpath = "//span[@id='spnPickupCnt']")
    private WebElement PendingPickupIconCount;

    @FindBy(xpath = "(//i[@class='fa fa-2x fa-clipboard'])[1]")
    private WebElement ViewDraftIcon;

    @FindBy(xpath = "//label[@id='lblDraftcnt']")
    private WebElement ViewDraftIconCount;

    @FindBy(xpath = "(//i[@class='fa fa-2x fa-comment'])[1]")
    private WebElement NewMessageIcon;

    @FindBy(xpath = "//label[@id='lblUnRead']")
    private WebElement NewMessageIconCount;

    @FindBy(xpath = "(//button[normalize-space()='Dispatch'])[1]")
    private WebElement DispatchMenu;

    @FindBy(xpath = "//a[normalize-space()='Advanced Dispatch']")
    private WebElement AdvancedDispatch;

    @FindBy(xpath = "//a[normalize-space()='Quick Dispatch']")
    private WebElement QuickDispatch;

    @FindBy(xpath = "(//i[@data-toggle='tooltip'])[1]")
    private WebElement NewTabPlusIcon;

    @FindBy(xpath = "(//input[@id='top-search1'])[1]")
    private WebElement DashboardSearchBox;

    @FindBy(xpath = "//a[@id='idEmail']")
    private WebElement EmailIcon;

    @FindBy(xpath = "//label[@id='lblEmail']")
    private WebElement EmailIconCount;

    @FindBy(xpath = "//a[@id='CalenderList']")
    private WebElement IntegrateCalenderIcon;

    @FindBy(xpath = "//a[@id='btnRemoteConnect']")
    private WebElement RemoteConnectIcon;

    @FindBy(xpath = "//a[@class='open-small-chat']")
    private WebElement HelpIcon;

    @FindBy(xpath = "//a[@id='hana-profile-menu-link']")
    private WebElement ProfileIcon;

    @FindBy(xpath = "//a[@class='li_LogoutNew common-dynamic-font-size']")
    private WebElement LogoutIcon;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//li//a")
    private List<WebElement> NewOrderList;

    @FindBy(id = "CustomersMenu")
    private WebElement CustomersMenu;

    @FindBy(xpath = "//li[@id='CustomersMenu']//a")
    private WebElement customer_menu;

    @FindBy(xpath = "//li[@id='ConfigurationMenu']")
    private WebElement ConfigurationMenu;

    @FindBy(xpath = "//a[@class='li_Configuration']")
    private WebElement settingsSubmenu;

    @FindBy(xpath = "//li[normalize-space()='Products Management']")
    private WebElement products_management_submenu;

    @FindBy(xpath = "//a[@class='li_Websiteadmin dropdown-item' and contains(text(), 'Web Admin')]")
    private WebElement WebAdmin_SubMenu;

    @FindBy(xpath = "(//span[normalize-space()='Marketing'])[1]")
    private WebElement MarketingMenu;

    @FindBy(xpath = "//a[@class='li_EmailMarketing dropdown-item' and contains(text(),'Email / SMS Marketing')]")
    private WebElement email_marketing_submenu;

    @FindBy(xpath = "//a[@class='dropdown-item li_Reminder']")
    private WebElement Reminder_FromMarkingMenu;

    //	Orders Menu Element from the Left menu
    @FindBy(xpath = "//li[@id='OrderMenu']")
    private WebElement OrderMenu;

    //	Confirmation sub menu Element from the Orders Menu in the Left Menu
    @FindBy(xpath = "//a[@id='btnOpenDeliveryConfModalNew']")
    private WebElement ConfirmationSubMenu;

    @FindBy(xpath = "//div[@id='clsUnconfirm']//span[normalize-space()='Pending Confirmations']")
    private WebElement pending_confirmation_tab;

    @FindBy(xpath = "//a[@id='userWebsiteDisplayName']")
    private WebElement userWebsiteDisplayName;

    @FindBy(xpath = "//li[@id='ReportsMenu']/a")
    private WebElement Reports_Menu;

    @FindBy(xpath = "//a[@data-action='DeliveryReview']")
    private WebElement Payroll_Report_Sub_Menu;

    @FindBy(xpath = "//li[@id='CustomersMenu']")
    private WebElement customer_menu_on_hana_dashboard_page;

    //======================================= Hana Dashboard page Web Elements =========================
    public String VerifyPageTitleonDashboard() {
        return getDriver().getTitle();
    }


    /**
     * Selects the shop name from the dropdown field on dashboard page
     *
     * @param shopname Provided Shop name to be selected
     * @Description: This function select the visible text of provided shop name from the dropdown field on the hana dashboard page
     * @Author: Balaji N
     */
    public HanaDashBoardPage SelectShopNameDropDown(String shopname) {
        try {
            fluentWait(ShopNameDropDown, 45, 4);
            Select select = new Select(ShopNameDropDown);
            select.selectByVisibleText(shopname);
            wait_For_Page_To_Be_Stable(getDriver());
        } catch (NoSuchElementException e) {
            printError(ShopNameDropDown, "Shop Name dropdown field on hana dashboard page", "No such element found", e);
        } catch (Exception e) {
            printError(ShopNameDropDown, "Shop Name dropdown field on hana dashboard page", "Generic Exception", e);
        }
        return this;
    }


    /**
     * It gets the selected shop name from the dropdown field on dashboard page
     *
     * @return If the shop name is selected it returns the selected shop name; otherwise it returns null
     * @Description: This function select the visible text of provided shop name from the dropdown field on the hana dashboard page
     * @Author: Balaji N
     */
    public String get_selected_shopname_from_hanadashboard() {
        Select select = new Select(ShopNameDropDown);
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Verifies if the Hana Dashboard Page is loaded by checking the visibility of the Home logo.
     * Uses fluent wait to handle dynamic loading issues.
     *
     * @return {@code true} if the Hana logo is displayed on the Dashboard page, otherwise {@code false}.
     * @throws RuntimeException if an unexpected exception occurs during execution.
     * @Author Balaji N
     */
    public boolean VerifyHanaDashBoardPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));

        try {
            // Step 1: Wait for the DOM to be ready
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));

            // Step 2: Wait for jQuery AJAX to finish (if jQuery is used)
            //enableAjaxAndFetchTracking();
           // injectAjaxTracker(getDriver());
          //  waitForJQueryToFinish(90);

           // wait_For_Page_To_Be_Stable(getDriver());
            // Step 3: Verify logo is visible
            wait.until(ExpectedConditions.visibilityOf(HanaLogo));
            return isElementDisplayed(HanaLogo, "Hana Dashboard Page Home Icon");

        } catch (TimeoutException e) {
            String pending = fetchPendingAjaxRequests(getDriver());
            if (pending != null && !pending.isEmpty()) {
                Allure.addAttachment("Pending AJAX Requests at Timeout", pending);
            }
            attachReadableError("Hana Dashboard did not load within 120 seconds. " +
                    "The Hana Home Logo was not visible. Please check if the application is slow or unresponsive.", e);
        } catch (NoSuchElementException e) {
            attachReadableError("The Hana Home Logo was not found on the Dashboard. " +
                    "This may indicate a UI change or incorrect locator.", e);
        } catch (StaleElementReferenceException e) {
            attachReadableError("The Hana Home Logo was found but became stale before interaction. " +
                    "This usually happens when the page refreshed or re-rendered unexpectedly.", e);
        } catch (WebDriverException e) {
            attachReadableError("A browser/driver issue occurred while loading the Dashboard. " +
                    "This might be due to browser crash, driver disconnection, or server issue.", e);
        } catch (NullPointerException e) {
            attachReadableError("The Hana Home Logo element is not initialized in the Page Object. " +
                    "Please verify PageFactory or WebElement declaration.", e);
        } catch (Exception e) {
            attachReadableError("An unexpected error occurred while verifying the Hana Dashboard Page.", e);
        }
        return false;
    }

    /**
     * Attach user-friendly + technical error log.
     */
    private void attachReadableError(String userMessage, Exception e) {
        // Console log (developer friendly)
        System.err.println("[ERROR] " + userMessage + " | Root cause: " + e.getMessage());

        // Allure attachment (stakeholder friendly)
        Allure.addAttachment("User Friendly Error Log", userMessage);

        // Allure attachment (technical details for debugging)
        Allure.addAttachment("Technical Stack Trace", e.toString());
    }


    /**
     * Clicks on the Hana home icon using javascript click.
     *
     * @Description: This function clicks on the Hana home icon using javascript click.
     *
     * <p>
     * Expected functionality: If the Hana home icon is clicked on hana dashboard page, It should be refreshed or reloaded
     * </p>
     * @Author: Balaji N
     */
    public void ClickOnHomeIcon() {
        js_Click(HanaLogo, "Hana Home Icon");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    public OrderEntry_Alais_PhoneOrderPage NewOrderMenuClick() {
        HighlightElement(NewOrderMenuBtn);
        MouseHover(NewOrderMenuBtn);
        HighlightElement(OrderEntry);
        click(OrderEntry);
        return new OrderEntry_Alais_PhoneOrderPage();
    }

    /**
     * Clicks on the Cash and Carry menu option.
     *
     * @Description: This function clicks on the Cash and Carry menu option.
     * @Author: Balaji N
     */
  /*  public CashAndCarryPage CashAndCarryMenuClick() {
        int retryCount = 3;
        boolean isClicked = false;

        for (int attempt = 0; attempt < retryCount; attempt++) {
            try {
                delayWithGivenTime(2000);
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(40));
                WebElement newOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='btn btn-default dropdown-toggle'][normalize-space()='New Order'])")));
                fluentWait(NewOrderMenuBtn);
                Actions action = new Actions(getDriver());
                action.moveToElement(NewOrderMenuBtn).build().perform();
                delayWithGivenTime(2000);
                fluentWait(CashAndCarry);
                HighlightElement(CashAndCarry);
                action.moveToElement(CashAndCarry).click().perform();
                isClicked = true;
                break;
            } catch (TimeoutException e) {
                System.out.println("TimeoutException on attempt " + (attempt + 1) + ": " + e.getMessage());
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException on attempt " + (attempt + 1) + ": " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("NoSuchElementException on attempt " + (attempt + 1) + ": " + e.getMessage());
            } catch (ElementNotInteractableException e) {
                System.out.println("ElementNotInteractableException on attempt " + (attempt + 1) + ": " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected exception on attempt " + (attempt + 1) + ": " + e.getMessage());
            }

            // Retry mechanism with a short delay before retrying
            if (!isClicked && attempt < retryCount - 1) {
                logger.info("Retrying to click on Cash and Carry menu (Attempt " + (attempt + 2) + ")");
                delayWithGivenTime(2000);
            }
        }

        // Final validation after all retries
        if (!isClicked) {
            throw new RuntimeException("Failed to click on the Cash and Carry menu after " + retryCount + " attempts.");
        }

        return new CashAndCarryPage();
    }
*/

    /**
     * Clicks on the Cash and Carry menu item under the New Order button with retry mechanism.
     *
     * @return CashAndCarryPage instance if successful
     * @throws RuntimeException if the menu click fails after retries
     * @Author Balaji N
     */
    public CashAndCarryPage CashAndCarryMenuClick() {
        final CashAndCarryPage[] page = new CashAndCarryPage[1];

        logPageLoad("Cash and Carry Page", () -> {
            int maxRetries = 3;
            int attempt = 0;

            while (attempt < maxRetries) {
                try {
                    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                    // Wait for page and jQuery/AJAX readiness
                    wait.until(driver -> ((JavascriptExecutor) driver)
                            .executeScript("return document.readyState").equals("complete"));
                    wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                            .executeScript("return !!window.jQuery && jQuery.active === 0"));

                    // Hover over New Order menu
                    fluentWait(NewOrderMenuBtn, 20, 2);
                    ((JavascriptExecutor) getDriver()).executeScript(
                            "var evObj = document.createEvent('MouseEvents');" +
                                    "evObj.initEvent('mouseover', true, true);" +
                                    "arguments[0].dispatchEvent(evObj);", NewOrderMenuBtn);

                    delayWithGivenTime(1500); // Allow dropdown to appear

                    // Click on Cash and Carry
                    fluentWait(CashAndCarry, 20, 2);
                    HighlightElement(CashAndCarry);
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", CashAndCarry);

                    page[0] = new CashAndCarryPage(); // Assign to outer variable
                    return;

                } catch (TimeoutException | StaleElementReferenceException |
                         ElementNotInteractableException | NoSuchElementException e) {
                    System.err.println("[Retry " + (attempt + 1) + "/" + maxRetries + "] Exception: " + e.getClass().getSimpleName());
                } catch (Exception e) {
                    System.err.println("[Retry " + (attempt + 1) + "/" + maxRetries + "] Unexpected error: " + e.getMessage());
                    e.printStackTrace();
                }

                attempt++;
                delayWithGivenTime(2000);
            }

            throw new RuntimeException("Failed to click on the Cash and Carry menu after " + maxRetries + " attempts.");
        });

        return page[0]; // Return captured object
    }


    /**
     * Hovers the mouse over the New Order menu and clicks the Order Entry option on the Hana Dashboard.
     * Robust retry mechanism is applied as this is a critical entry point into the Cash and Carry flow.
     *
     * @Author: Balaji N
     */
    public void ClickOrderEntry() {
        PageLoadLoggerUtils.logPageLoad("Hana Dashboard Page → Phone Order Page", () -> {
            int maxRetries = 3;

            int attempt = 0;
            while (attempt < maxRetries) {
                try {
                    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                    // Ensure the page is fully loaded and AJAX calls are completed
                    wait.until(driver -> ((JavascriptExecutor) driver)
                            .executeScript("return document.readyState").equals("complete"));

//                    wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
//                            .executeScript("return !!window.jQuery && jQuery.active === 0"));

                    // Wait and hover over the New Order menu
                    fluentWait(NewOrderMenuBtn, 30, 2);
                    if (isElementDisplayed(NewOrderMenuBtn, "New Order Menu Button on Hana Dashboard Page")) {
                        ((JavascriptExecutor) getDriver()).executeScript(
                                "var evObj = document.createEvent('MouseEvents');" +
                                        "evObj.initEvent('mouseover', true, true);" +
                                        "arguments[0].dispatchEvent(evObj);", NewOrderMenuBtn);
                        delayWithGivenTime(1500); // Allow submenu to appear
                    } else {
                        throw new NoSuchElementException("New Order Menu Button is not visible.");
                    }

                    // Wait for Order Entry option
                    fluentWait(OrderEntry, 20, 2);
                    Highlight_Element(OrderEntry, "Order Entry Option on Hana Dashboard Page - New Order button");

                    if (isElementDisplayed(OrderEntry, "Order Entry Option on Hana Dashboard Page - New Order button")) {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", OrderEntry);
                        return;
                    } else {
                        throw new NoSuchElementException("Order Entry Option is not visible.");
                    }

                } catch (StaleElementReferenceException | TimeoutException | ElementNotInteractableException e) {
                    System.err.println("[Retry " + (attempt + 1) + "/" + maxRetries + "] Retrying due to: " + e.getClass().getSimpleName());
                } catch (Exception e) {
                    printError(OrderEntry,
                            "Order Entry Option on Hana Dashboard Page - New Order button",
                            "UnexpectedException: " + e.getMessage(), e);
                    break;
                }

                delayWithGivenTime(1000);
                attempt++;
            }


            throw new RuntimeException("Failed to click Order Entry after " + maxRetries + " attempts.");

        });
    }

    /**
     * This method will click on the Quick Dispatch button
     *
     * @Author: Balaji N
     * @Last-Modified-By: Sakrateesh R
     */
    public void Hover_Dispatch_And_Click_QuickDispatch() {
        logPageLoad("Quick Dispatch Popup", () -> {
            int maxRetries = 3;
            int attempt = 0;

            while (attempt < maxRetries) {
                try {
                    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                    wait.until(driver -> ((JavascriptExecutor) driver)
                            .executeScript("return document.readyState").equals("complete"));
                    wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                            .executeScript("return !!window.jQuery && jQuery.active === 0"));

                    fluentWait(DispatchMenuBtn, 20, 2);
                    if (isElementDisplayed(DispatchMenuBtn, "Dispatch Menu Button")) {
                        Actions action = new Actions(getDriver());
                        action.moveToElement(DispatchMenuBtn).build().perform();
                        delayWithGivenTime(1500);
                    } else {
                        throw new NoSuchElementException("Dispatch Menu Button is not visible.");
                    }

                    fluentWait(quickDispatch, 20, 2);
                    Highlight_Element(quickDispatch, "Quick Dispatch Option in Hana Dashboard");
                    if (isElementDisplayed(quickDispatch, "Quick Dispatch Option")) {
                        new Actions(getDriver()).moveToElement(quickDispatch).click().build().perform();
                        wait_For_Page_To_Be_Stable(getDriver());
                        return;
                    } else {
                        throw new NoSuchElementException("Quick Dispatch Option is not visible.");
                    }

                } catch (StaleElementReferenceException | TimeoutException | ElementNotInteractableException e) {
                    System.err.println("[Retry " + (attempt + 1) + "/" + maxRetries + "] Retrying due to: " + e.getClass().getSimpleName());
                } catch (Exception e) {
                    printError(quickDispatch, "Quick Dispatch Option", "Unexpected Exception: " + e.getMessage(), e);
                    break;
                }

                delayWithGivenTime(1000);
                attempt++;
            }

            throw new RuntimeException("Failed to click Quick Dispatch after " + maxRetries + " attempts.");
        });
    }


    public void Hover_Dispatch_And_Click_AdvanceDispatch() {
        logPageLoad("Advance Dispatch Page", () -> {
            int maxRetries = 3;
            int attempt = 0;

            while (attempt < maxRetries) {
                try {
                    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                    wait.until(driver -> ((JavascriptExecutor) driver)
                            .executeScript("return document.readyState").equals("complete"));
                    wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                            .executeScript("return !!window.jQuery && jQuery.active === 0"));

                    fluentWait(DispatchMenuBtn, 20, 2);
                    if (isElementDisplayed(DispatchMenuBtn, "Dispatch Menu Button")) {
                        new Actions(getDriver()).moveToElement(DispatchMenuBtn).build().perform();
                        delayWithGivenTime(1500);
                    } else {
                        throw new NoSuchElementException("Dispatch Menu Button is not visible.");
                    }

                    fluentWait(advanceDispatch, 20, 2);
                    Highlight_Element(advanceDispatch, "Advance Dispatch Option in Hana Dashboard");
                    if (isElementDisplayed(advanceDispatch, "Advance Dispatch Option")) {
                        new Actions(getDriver()).moveToElement(advanceDispatch).click().build().perform();
                        wait_For_Page_To_Be_Stable(getDriver());
                        return;
                    } else {
                        throw new NoSuchElementException("Advance Dispatch Option is not visible.");
                    }

                } catch (StaleElementReferenceException | TimeoutException | ElementNotInteractableException e) {
                    System.err.println("[Retry " + (attempt + 1) + "/" + maxRetries + "] Retrying due to: " + e.getClass().getSimpleName());
                } catch (Exception e) {
                    printError(advanceDispatch, "Advance Dispatch Option", "Unexpected Exception: " + e.getMessage(), e);
                    break;
                }

                delayWithGivenTime(1000);
                attempt++;
            }

            throw new RuntimeException("Failed to click Advance Dispatch after " + maxRetries + " attempts.");
        });
    }

    /**
     * Clicks the Orders menu from the left menu
     *
     * @Description: This function will wait for the Orders menu element using fluent wait on Orders menu and clicks it using jsclick function.
     * @Author: Balaji N
     */
    public void ClickOrder() {
        PageLoadLoggerUtils.logPageLoad("Phone Order Page → All Orders / Recent Orders Page", () -> {
            js_Click(OrdersMenu, "Orders - Menu on Hana Dashboard Page");
           // wait_For_Page_To_Be_Stable(getDriver());
        });
    }


    /**
     * Waits until the page is fully loaded (readyState == complete).
     */
    public static void waitForPageToLoadCompletely() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(60)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
    }


    /**
     * Validates whether the Order Entry option is displayed on the Hana dashboard page.
     *
     * @return {@code true} if the Order Entry option is displayed on the page; otherwise, {@code false}.
     * @throws TimeoutException               if waiting for elements or page load exceeds the defined time.
     * @throws NoSuchElementException         if the elements required for validation are not found.
     * @throws StaleElementReferenceException if elements become stale before interaction.
     * @throws WebDriverException             for any other WebDriver-related errors.
     * @description This function hovers over the "New Order Menu" button and checks if the Order Entry option is visible on the Hana dashboard page.
     * @author Balaji N
     */
   /* public boolean VerifyOrderEntryOptionIsDisplayed() {
        Actions actions = new Actions(getDriver());
        try {
            fluentWait(NewOrderMenuBtn, 90, 4);
            actions.moveToElement(NewOrderMenuBtn).build().perform();
            delayWithGivenTime(1500);
            fluentWait(OrderEntry, 90, 4);
            return isElementDisplayed(OrderEntry, "Order Entry Option on New Order Menu");
        } catch (TimeoutException e) {
            printError(OrderEntry, "Order Entry Option on New Order Menu", "TimeoutException", e);
        } catch (NoSuchElementException e) {
            printError(OrderEntry, "Order Entry Option on New Order Menu", "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(OrderEntry, "Order Entry Option on New Order Menu", "StaleElementReferenceException", e);
        } catch (WebDriverException e) {
            printError(OrderEntry, "Order Entry Option on New Order Menu", "WebDriverException", e);
        }
        return false;
    }*/
//    public boolean VerifyOrderEntryOptionIsDisplayed() {
//        try {
//            fluentWait(NewOrderMenuBtn, 90, 4);
//
//            String mouseOverScript = "var evObj = document.createEvent('MouseEvents');" +
//                    "evObj.initEvent('mouseover', true, true);" +
//                    "arguments[0].dispatchEvent(evObj);";
//            ((JavascriptExecutor) getDriver()).executeScript(mouseOverScript, NewOrderMenuBtn);
//
//            fluentWait(OrderEntry, 90, 4);
//
//            return isElementDisplayed(OrderEntry, "Order Entry Option on New Order Menu");
//        } catch (TimeoutException e) {
//            printError(OrderEntry, "Order Entry Option on New Order Menu", "TimeoutException", e);
//        } catch (NoSuchElementException e) {
//            printError(OrderEntry, "Order Entry Option on New Order Menu", "NoSuchElementException", e);
//        } catch (StaleElementReferenceException e) {
//            printError(OrderEntry, "Order Entry Option on New Order Menu", "StaleElementReferenceException", e);
//        } catch (WebDriverException e) {
//            printError(OrderEntry, "Order Entry Option on New Order Menu", "WebDriverException", e);
//        }
//        return false;
//    }
    public boolean VerifyOrderEntryOptionIsDisplayed() {
        final int maxRetries = 2;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                fluentWait(NewOrderMenuBtn, 90, 4);
                String mouseOverScript = "var evObj = document.createEvent('MouseEvents');" +
                        "evObj.initEvent('mouseover', true, true);" +
                        "arguments[0].dispatchEvent(evObj);";
                ((JavascriptExecutor) getDriver()).executeScript(mouseOverScript, NewOrderMenuBtn);


                fluentWait(OrderEntry, 90, 4);
                return isElementDisplayed(OrderEntry, "Order Entry Option on New Order Menu");

            } catch (NoSuchElementException | StaleElementReferenceException e) {
                retryCount++;
                printError(OrderEntry, "Order Entry Option on New Order Menu", e.getClass().getSimpleName() + " - Retry " + retryCount, e);

                if (retryCount >= maxRetries) {
                    return false;
                }
                delayWithGivenTime(1000); // Wait before retry
            } catch (TimeoutException e) {
                printError(OrderEntry, "Order Entry Option on New Order Menu", "TimeoutException", e);
                return false;
            } catch (WebDriverException e) {
                printError(OrderEntry, "Order Entry Option on New Order Menu", "WebDriverException", e);
                return false;
            }
        }

        return false;
    }


    /**
     * Validates whether the Cash and Carry option is displayed on the Hana dashboard page.
     *
     * @return {@code true} if the Cash and Carry option is displayed on the page; otherwise, {@code false}.
     * @throws TimeoutException               if waiting for elements or page load exceeds the defined time.
     * @throws NoSuchElementException         if the elements required for validation are not found.
     * @throws StaleElementReferenceException if elements become stale before interaction.
     * @throws WebDriverException             for any other WebDriver-related errors.
     * @description This function hovers over the "New Order Menu" button and checks if the Cash and Carry option is visible on the Hana dashboard page.
     * @author Balaji N
     */
    /**
     * Verifies whether the "Cash and Carry" option is displayed under the New Order Menu.
     * This method is critical and uses robust retry logic to avoid flaky failures.
     *
     * @return true if displayed, false otherwise
     * @Author Balaji N
     */
    public boolean Verify_Cashandcarry_OptionIsDisplayed() {
        int maxRetries = 2;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Wait for DOM and jQuery to load
                wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
//                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
//                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                // Hover over the New Order menu using JS
                fluentWait(NewOrderMenuBtn, 30, 2);
                ((JavascriptExecutor) getDriver()).executeScript(
                        "var evObj = document.createEvent('MouseEvents');" +
                                "evObj.initEvent('mouseover', true, true);" +
                                "arguments[0].dispatchEvent(evObj);", NewOrderMenuBtn);

                delayWithGivenTime(1500);

                // Wait for Cash & Carry option to appear
                fluentWait(CashAndCarry, 30, 2);

                if (isElementDisplayed(CashAndCarry, "Cash and Carry Option on New Order Menu")) {
                    return true;
                }

            } catch (StaleElementReferenceException | TimeoutException | ElementNotInteractableException e) {
                System.err.println("[Retry " + (attempt + 1) + "/" + maxRetries + "] Retry due to: " + e.getClass().getSimpleName());
            } catch (Exception e) {
                printError(CashAndCarry, "Cash and Carry Option on New Order Menu",
                        "Unexpected error while verifying Cash and Carry Option: " + e.getMessage(), e);
                break;
            }

            attempt++;
            delayWithGivenTime(1000);
        }

        return false;
    }

    /**
     * Retries the execution of a critical step once if it fails, before marking it as failed.
     *
     * @param action      The action to perform.
     * @param element     The WebElement involved in the action.
     * @param description Description of the action for reporting.
     * @return true if the action is successful, false otherwise.
     */
    private boolean retryIfFailed(Supplier<Boolean> action, WebElement element, String description) {
        try {
            return action.get();
        } catch (
                WebDriverException e) {
            printError(element, description, e.getClass().getSimpleName(), e);
            System.out.println("Retrying critical step: " + description);
            try {
                return action.get(); // Retry once
            } catch (Exception retryException) {
                printError(element, description, retryException.getClass().getSimpleName(), retryException);
            }
        }
        return false;
    }


    /**
     * verify whether the Quick Dispatch option is displayed on the Hana dashboard page
     *
     * @return If the Quick Dispatch option is displayed, returns true; otherwise, returns false
     * @author Balaji N
     */
    public boolean Verify_QuickDispatch_OptionIsDisplayed() {
        Mouse_Hover(this, DispatchMenuBtn, "Dispatch Menu Button on Hana Dashboard Page");
        return isElementDisplayed(quickDispatch, "Quick Dispatch Option on Dispatch Menu");
    }

    /**
     * Verify the Advance Dispatch option is displayed on the Hana dashboard page
     *
     * @return If the Advance Dispatch option is displayed, returns true; otherwise, returns false
     * @author Balaji N
     */
    public boolean Verify_AdvanceDispatch_OptionIsDisplayed() {
        Mouse_Hover(this, DispatchMenuBtn, "Dispatch Menu Button on Hana Dashboard Page");
        return isElementDisplayed(advanceDispatch, "Advance Dispatch Option on Dispatch Menu");
    }


    /**
     * Clicks on the Customers menu in the application.
     * This method first waits for the element using a fluent wait, then performs a click action.
     *
     * @throws NoSuchElementException           if the Customers menu is not found
     * @throws ElementClickInterceptedException if the click action is intercepted by another element
     * @throws ElementNotInteractableException  if the element is present but not interactable
     * @throws TimeoutException                 if the element does not appear within the expected time
     * @throws StaleElementReferenceException   if the element becomes detached from the DOM before interaction
     * @throws WebDriverException               for any other WebDriver-related exceptions
     * @author Balaji N
     */
    public void ClickCustomersMenu() {
        try {
            ThreadWait(2000);
            fluentWait(CustomersMenu);
            CustomersMenu.click();
        } catch (NoSuchElementException e) {
            printError(CustomersMenu, "Customers Menu", "NoSuchElementException", e);
            throw e;
        } catch (ElementClickInterceptedException e) {
            printError(CustomersMenu, "Customers Menu", "ElementClickInterceptedException", e);
            throw e;
        } catch (ElementNotInteractableException e) {
            printError(CustomersMenu, "Customers Menu", "ElementNotInteractableException", e);
            throw e;
        } catch (TimeoutException e) {
            printError(CustomersMenu, "Customers Menu", "TimeoutException", e);
            throw e;
        } catch (StaleElementReferenceException e) {
            printError(CustomersMenu, "Customers Menu", "StaleElementReferenceException", e);
            throw e;
        } catch (WebDriverException e) {
            printError(CustomersMenu, "Customers Menu", "WebDriverException", e);
            throw e;
        }
    }

    /**
     * Clicks the customer menu on the Hana dashboard page.
     *
     * @Author Balaji N
     */
    public void click_Customer_Menu() {
        js_Click(customer_menu, "Customers Menu on Hana Dashboard Page");
    }

    /**
     * Clicks on the Setting submenu - Configuration menu in the hana dashboard application.
     *
     * @Author Balaji N
     */
    public void Click_settingsSubmenu() {
        waitForPageToLoadCompletely();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        delayWithGivenTime(1000);
        Mouse_Hover(this, ConfigurationMenu, "Configuration Menu on Hana Dashboard Page");
        delayWithGivenTime(1000);
        Actions action = new Actions(getDriver());
        action.moveToElement(settingsSubmenu).build().perform();
        click(settingsSubmenu, "Settings Sub menu on Configuration Menu");
        switchToWindowbyIndex(1);
    }

    /**
     * Clicks on the Web Admin submenu - Configuration menu in the hana dashboard application.
     *
     * @Author Balaji N
     */
    public void Click_WebAdmin_Submenu() {
        delayWithGivenTime(2000);
        Mouse_Hover(this, ConfigurationMenu, "Configuration Menu on Hana Dashboard Page");
        delayWithGivenTime(2000);
        js_Click(WebAdmin_SubMenu, "Web Admin SubMenu on Configuration Menu");
    }

    /**
     * Clicks on the Marketing submenu - Marketing menu in the hana dashboard application.
     *
     * @Author Balaji N
     */
    public void Click_Marketing_RemainderMenu() {
        Mouse_Hover(this, MarketingMenu, "Marketing Menu on Hana Dashboard Page");
        delayWithGivenTime(1000);
        js_Click(Reminder_FromMarkingMenu, "Reminder Sub menu on Marketing Menu");
    }

    /**
     * Cicks on the Email Marketing submenu - Marketing menu in the hana dashboard application.
     *
     * @Author Balaji N
     */
    public void Click_EmailMarketing_SubMenu() {
        Mouse_Hover(this, MarketingMenu, "Marketing Menu on Hana Dashboard Page");
        delayWithGivenTime(1000);
        js_Click(email_marketing_submenu, "Email Marketing Sub menu on Marketing Menu");
    }

    /**
     * Clicks on the Confirmation submenu - Order menu in the hana dashboard application.
     *
     * @Author Balaji N
     */
    public void MouseAndClick_Confirmation_sub_menu() {
        logPageLoad("DeliveryConfirmation Popup", () -> {
            Mouse_Hover(this, OrderMenu, "Order Menu on Hana Dashboard Page");
            delayWithGivenTime(1000);
            Click(ConfirmationSubMenu, "Confirmation Sub menu on Order Menu");
        });
    }

    /**
     * Clicks the Profile icon on the Hana dashboard page.
     *
     * @Description: This function will click the Profile icon on the Hana dashboard page.
     * @Author: Balaji N
     */
    public void Click_ProfileIcon_On_HanaDashBoardPage_And_Clicks_User_Website() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(ProfileIcon));
        Actions action = new Actions(getDriver());
        action.moveToElement(ProfileIcon).build().perform();
        delayWithGivenTime(2000);
        if (isElementDisplayed(userWebsiteDisplayName, "User Website Display Name")) {
            click(userWebsiteDisplayName, "User Website Display Name");
            delayWithGivenTime(2000);
        }

    }

    /**
     * Clicks on the Payroll Reports submenu - Reports menu in the hana dashboard application.
     *
     * @Author Balaji N
     */
    public void MouseAndClick_Payroll_Reports_sub_menu() {
        scrollAction(Reports_Menu);
        Mouse_Hover(this, Reports_Menu, "Reports Menu on Hana Dashboard Page");
        delayWithGivenTime(1000);
        js_Click(Payroll_Report_Sub_Menu, "Payroll Report Sub menu on Reports Menu");
    }

    /**
     * Clicks the customer menu on hana dashboard page
     *
     * @Author Balaji N
     */
    public void Click_CustomerMenu_On_Hana_Dashboard_Page() {
        Click(customer_menu_on_hana_dashboard_page, "Customer Menu on Hana dashboard page");
    }

    public void Click_Product_Management_Submenu() {
        waitForPageToLoadCompletely();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        delayWithGivenTime(1000);
        Mouse_Hover(this, ConfigurationMenu, "Configuration Menu on Hana Dashboard Page");
        delayWithGivenTime(1000);
        Actions action = new Actions(getDriver());
        action.moveToElement(products_management_submenu).build().perform();
        Click(products_management_submenu, "Product Management Sub menu on Configuration Menu");
    }

    /**
     * Clicks the Pending Confirmation Tab on dashboard order page
     *
     * @Author Balaji N
     */
    public void click_Pending_Confirmation_Tab() {
        click(pending_confirmation_tab, "Pending Confirmation Tab");
    }


}
