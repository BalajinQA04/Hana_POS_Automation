package com.hanapos.pageObjects;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Dispatch Page Class is a class that contains all the web elements and methods of Quick Dispatch Page
 *
 * @Author Balaji N
 */
public class DispatchPage extends TestBaseClass {

    public DispatchPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //================================= Dispatch Page Web Elements =============================================
    @FindBy(xpath = "//div[@id='dispatch-info-full-view']//div[@id='dispatch-view-modal-body']")
    private WebElement quick_dispatch_popup;

    @FindBy(xpath = "//h4[text()='Dispatch']")
    private WebElement dispatchPage;

    @FindBy(xpath = "//input[@id='undispatch-invoicescan']")
    private WebElement scanOrder_Textbox;

    @FindBy(xpath = "//select[@id='ddlDispatchDriver']")
    private WebElement selectDriver_Dropdown;

    @FindBy(xpath = "//select[@id='dispatch-order-shop-selector']")
    private WebElement SelectShop_Dropdown;

    @FindBy(xpath = "//table[@id='dispatchCurrentDispatch']")
    private WebElement trip_table_grid;

    @FindBy(xpath = "//table[@id='dispatchCurrentDispatch']//tbody//tr[1]//td[1]")
    private WebElement InvoiceNumberOnDispatchTable_Row1_TripSection;

    @FindBy(xpath = "//table[@id='dispatchCurrentDispatch']/tbody/tr/td[1]")
    private List<WebElement> InvoiceNumberOnDispatchTable_Row_TripSection;

    @FindBy(xpath = "//table[@id='dispatchCurrentDispatch']//tbody//tr[1]//td[2]")
    private WebElement Name_Address_OnDispatchTable_Row1_TripSection;

    @FindBy(xpath = "//table[@id='dispatchCurrentDispatch']//tbody//tr[1]//td[3]")
    private WebElement City_OnDispatchTable_Row1_TripSection;

    @FindBy(xpath = "//table[@id='dispatchCurrentDispatch']//tbody//tr[1]//td[4]")
    private WebElement State_OnDispatchTable_Row1_TripSection;

    @FindBy(xpath = "//table[@id='dispatchCurrentDispatch']//tbody//tr[1]//td[5]")
    private WebElement Zipcode_OnDispatchTable_Row1_TripSection;

    @FindBy(id = "btnDispatchSave")
    private WebElement DispatchSave_Button;

    @FindBy(id = "btnDispatchCreateTrip")
    private WebElement DispatchNewTrip_Button;

    @FindBy(id = "ancDispatchRoutePlanner")
    private WebElement DispatchRoutePlanner_Button;

    @FindBy(id = "btnDispatchRemotePrint")
    private WebElement DispatchRemotePrint_Button;

    @FindBy(id = "btnDispatchManualPrint")
    private WebElement DispatchManualPrint_Button;

    @FindBy(xpath = "//button[@id='btnDispatchAddPayrate']")
    private WebElement DispatchAddPayrate_Button;

    @FindBy(xpath = "(//table[@role='grid']//tbody)[6]//tr//td[2]")
    private List<WebElement> SavedTrips_driverInitial;

    @FindBy(xpath = "(//table[@role='grid']//tbody)[6]//tr//td[3]")
    private List<WebElement> SavedTrips_driverName;

    @FindBy(xpath = "(//table[@role='grid']//tbody)[6]//tr//td[4]")
    private List<WebElement> SavedTrips_driverPhonenumber;

    @FindBy(xpath = "//div[@id='kendoDispatchSavedTrips']/div[2]/table/tbody/tr/td[5]")
    private List<WebElement> SavedTrips_routeNumber;

    @FindBy(xpath = "//div[@class='col-md-1 dfvc p-r-0']//a//i")
    private WebElement closeIcon_dispatchPopup;

    @FindBy(xpath = "//div[@id='kendoDispatchSavedTrips']/div/div/table/thead/tr/th[1]")
    private WebElement Saved_Trip_SNo_Column_header;

    @FindBy(xpath = "//div[@id='kendoDispatchSavedTrips']/div/div/table/thead/tr/th[2]")
    private WebElement Saved_Trip_DriverInitial_Column_header;

    @FindBy(xpath = "//div[@id='kendoDispatchSavedTrips']/div/div/table/thead/tr/th[3]")
    private WebElement Saved_Trip_DriverName_Column_header;

    @FindBy(xpath = "//div[@id='kendoDispatchSavedTrips']/div/div/table/thead/tr/th[4]")
    private WebElement Saved_Trip_PhoneNo_Column_header;

    @FindBy(xpath = "//div[@id='kendoDispatchSavedTrips']/div/div/table/thead/tr/th[5]")
    private WebElement Saved_Trip_RouteNo_Column_header;

    @FindBy(xpath = "//div[@id='kendoDispatchSavedTrips']/div/div/table/thead/tr/th[6]")
    private WebElement Saved_Trip_Created_Date_Column_header;

    @FindBy(xpath = "//div[@id='kendoDispatchSavedTrips']/div/div/table/thead/tr/th[7]/a")
    private WebElement Saved_Trip_Delete_Column_header;

    @FindBy(xpath = "(//div[@id='dispatch-view-modal-body']/following::div//button[@class='btn btn-white' and contains(text(),'Close')])[1]")
    private WebElement closeButton_on_quickdispatch_popup_atBottom;

    @FindBy(xpath = "//div[@id='kendoDispatchSavedTrips']/div[2]/table/tbody/tr/td[7]")
    private WebElement DeleteSavedTrip;

    @FindBy(xpath = "//input[@id='dispatch-dlv-date']")
    private WebElement deliverydate_on_quickdispatch_popup;

    @FindBy(css = "div[class='datepicker-days'] th[class='datepicker-switch']")
    private WebElement deliverydate_monthyear_on_advancedispatchpage;

    @FindBy(css = "div[class='datepicker-days'] th[class='next']")
    private WebElement deliverydate_nextbutton_on_advancedispatchpage;

    @FindBy(css = "div[class='datepicker-days'] th[class='prev']")
    private WebElement deliverydate_previousbutton_on_advancedispatchpage;

    @FindBy(xpath = "//div[@class='datepicker-days']//table//tbody//tr//td[@class='day' or @class='active day' or @class='today day']")
    private List<WebElement> listofdays_on_deliverydate_datepicker;

    @FindBy(xpath = "//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']")
    private WebElement datepicker_on_deliverydate_advancedispatch;

    // ================ * Begining of Pending Deliveries Section * ========================

    //	Pending Deliveries Section Element
    @FindBy(xpath = "(//div[@class='col-md-6 col-sm-12 row model-grid-block']/div)[1]")
    private WebElement pending_deliveries_section;

//	Undispatch Order Invoice List Element

    @FindBy(xpath = "//div[@id='kendoUndispatchOrders']//span[contains(@class, 'hana-grid-row-undispatchorder')] ")
    private List<WebElement> undispatch_order_invoices_list;

    @FindBy(xpath = "//table[@id='dispatchCurrentDispatch']/tbody/tr/td[16]/a")
    private List<WebElement> TripSection_delete_list;

    //	Delivery Date field Element
    @FindBy(xpath = "//input[@id='dispatch-dlv-date']")
    private WebElement delivery_date_field;

    //    Undispatched info message element
    @FindBy(xpath = "(//div[@class='k-grid-norecords'])[1]")
    private WebElement undispatch_info_message_label;

    //    All Shops toggle button Element
    @FindBy(xpath = "//span[@class='switchery switchery-default']/small")
    private WebElement undispatch_all_shops_toggle_button;

    //    All shop toggle element to verify enabled / disabled functionality
    @FindBy(xpath = "(//div[@class='smal-switch-cms']/span)[1]")
    private WebElement all_shop_toggle_to_verify_enable_or_disable;

    // ================ * End of Pending Deliveries Section * ========================

    //	Dispatch Button Element in Dashboard Page
    @FindBy(xpath = "//div[@id='dispatchMenu']/div/button[@id='dropdownMenu1']")
    private WebElement dispatch_button;

    //    Current Date Element in the Calendar
    @FindBy(xpath = "//td[@class='active day']")
    private WebElement current_date_in_calendar;

    @FindBy(xpath = "//div[@id='kendoUndispatchOrders']/div/div/table/thead/tr/th[1]")
    private WebElement pending_deliveries_table_column_header_srno;

    @FindBy(xpath = "//div[@id='kendoUndispatchOrders']/div/div/table/thead/tr/th[2]")
    private WebElement getPending_deliveries_table_column_header_invoice;

    @FindBy(xpath = "//div[@id='kendoUndispatchOrders']/div/div/table/thead/tr/th[3]")
    private WebElement getPending_deliveries_table_column_header_name;

    @FindBy(xpath = "//div[@id='kendoUndispatchOrdersSummary']/div/div/table/thead/tr/th[1]")
    private WebElement summary_table_column_header_srno;

    @FindBy(xpath = "//div[@id='kendoUndispatchOrdersSummary']/div/div/table/thead/tr/th[2]")
    private WebElement summary_table_column_header_summarized_by;

    @FindBy(xpath = "//div[@id='kendoUndispatchOrdersSummary']/div/div/table/thead/tr/th[3]")
    private WebElement summary_table_column_header_count;

    @FindBy(xpath = "//div[@class='row dispatch-view-row']/div/div[2]")
    private WebElement summary_section_element;

    @FindBy(xpath = "//div[@class='mediumaquamarine-bg dispatchHeading']/h4[contains(text(),'Summary')]")
    private WebElement summary_section_header_label_element;

    @FindBy(xpath = "//table/tbody/tr/td[2][contains(text(),'San Diego')]")
    private WebElement summary_record_sandiego_element;

    @FindBy(xpath = "//table/tbody/tr/td[2][contains(text(),'San Diego')]/following::td[1]")
    private WebElement summary_record_sandiego_count_element;

    @FindBy(xpath = "//div[@class='tripinformationtab']/div[5]/div[2]/h3/label")
    private WebElement trip_section_total_deliveries_count_element;

    @FindBy(xpath = "//label[@id='lbltripno']")
    private WebElement trip_no_generating_element;

    @FindBy(xpath = "//label[@id='lblDispatchRoute']")
    private WebElement trip_route_number_element;

    @FindBy(xpath = "//label[@id='lblDispatchDateTime']")
    private WebElement trip_section_created_date_time_element;

    @FindBy(xpath = "//table[@id='dispatchCurrentDispatch']/tbody/tr/td[1]")
    private List<WebElement> trip_section_table_invoices_list;

//    @FindBy(xpath = "//div[@class='sweet-alert visible showSweetAlert']")
//    private WebElement quick_dispatch_confirmation_popup;

    @FindBy(xpath = "(//table[@role='grid'])[7]")
    private WebElement SavedTripSection;

    @FindBy(xpath = "//h2[normalize-space()='Confirmation']")
    private WebElement quick_dispatch_confirmatio_popup_header;

    @FindBy(xpath = "//h2[normalize-space()='Confirmation']/following::p[1]")
    private WebElement quick_dispatch_confirmation_popup_warning_text;

    @FindBy(xpath = "//button[normalize-space()='No']")
    private WebElement quick_dispatch_confirmation_popup_no_button;

    @FindBy(xpath = "//button[normalize-space()='Yes']")
    private WebElement quick_dispatch_confirmation_popup_yes_button;

    @FindBy(xpath = "//div[@id='ModalDispatchSrc']/div/div")
    private WebElement quick_dispatch_manual_print_popup;

    @FindBy(xpath = "//iframe[@id='ifrmManualPrint']")
    private WebElement dispatch_print_content_iframe;

    @FindBy(xpath = "//viewer-toolbar//viewer-download-controls//cr-icon-button[@id='download']")
    private WebElement dispatch_print_popup_download_button;

    @FindBy(id = "spnIframeText")
    private WebElement add_payrate_label;

    @FindBy(css = "#gvPayrateDetails tbody tr")
    private List<WebElement> payrate_invoice_row_lists;

    @FindBy(xpath = "//table[@id='gvPayrateDetails']//td[2]")
    private WebElement row1_payrate_popup_invoice_number;

    @FindBy(xpath = "//table[@id='gvPayrateDetails']//td[9]//input")
    private WebElement row1_non_payable_reason_textbox;

    @FindBy(xpath = "//table[@id='gvPayrateDetails']//td[10]//input")
    private WebElement row1_driver_pay_rate_textbox;

    @FindBy(id = "gvPayrateDetails_txtPayrate_0")
    private WebElement drivers_payrate_textbox;

    @FindBy(id = "gvPayrateDetails_txtnonpayable_0")
    private WebElement non_payable_reason_textbox;

    @FindBy(xpath = "(//span[text()='Add Pay Rate']/following::button[text()='Update'])[1]")
    private WebElement Add_payrate_update_button;

    @FindBy(xpath = "//div[@id='ModalDispatchSrc']//div//button[text()='Close']")
    private WebElement Add_payrate_close_button;

    @FindBy(xpath = "//div//span[contains(text(),'Successfully')]")
    private WebElement Add_payrate_success_message;

    @FindBy(xpath = "//a[@class='close']")
    private WebElement Add_payrate_close_icon_button;

    @FindBy(xpath = "//div[@id='dispatch-view-modal-body']/div/div[2]")
    private WebElement Trip_Section_Element;

    @FindBy(xpath = "//div[@class='sweet-alert showSweetAlert visible']")
    private WebElement Confirmation_popup;

    @FindBy(xpath = "//div[@class='sa-button-container']/button[2]")
    private WebElement Confirmation_Yes_button;

    @FindBy(xpath = "//div[@id='dashboardDispatch_datepicker']/div/h4")
    private WebElement Delivery_Date_Label;


    //========================== Dispatch Page Functions =================================================

    public boolean is_Invoice_Number_Displayed_In_Dispatch_Table_Grid_Row1_After_Confirm_NewTrip() {
        try {
            return isElementDisplayed(InvoiceNumberOnDispatchTable_Row1_TripSection, "Invoice Number in the Trip Section");
        } catch (NoSuchElementException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String get_InvoiceNumberOnDispatchTable() {
        HighlightElement(InvoiceNumberOnDispatchTable_Row1_TripSection);
        return InvoiceNumberOnDispatchTable_Row1_TripSection.getText();
    }

    /**
     * This method will verify whether the Dispatch pop-up is displayed or not
     *
     * @return: true if the Dispatch pop-up is displayed else false
     * @Author: Sakrateesh R
     */
    public boolean Verify_DispatchPopup_IsDisplayed() {
        wait_For_Page_To_Be_Stable(getDriver());
        boolean dispatchpopup = false;
        if (isElementDisplayed(quick_dispatch_popup, "Quick Dispatch Popup")) {
            dispatchpopup = isElementDisplayed(dispatchPage, "Quick Dispatch Popup");
        }
        return dispatchpopup;
    }

    public void Enter_InvoiceNumber_on_ScanOrderTextbox(String order) {
        try {
            scanOrder_Textbox.sendKeys(order);
            delayWithGivenTime(1000);
            scanOrder_Textbox.sendKeys(Keys.ENTER);
        } catch (NoSuchElementException e) {
            printError(scanOrder_Textbox, "Scan Order Textbox in Dispatch popup", "No Such Element Exception", e);
        } catch (Exception e) {
            printError(scanOrder_Textbox, "Scan Order Textbox in Dispatch popup", "unexpected Exception", e);
        }
    }

    /**
     * This method is used to select a driver in the Quick Dispatch pop-up
     *
     * @param drivername
     * @Author: Sakrateesh R
     */
    public void Select_Driver(String drivername) {
        drop_Down(selectDriver_Dropdown, drivername, "VisibleText", "Select Driver dropdown in Quick Dispatch");
    }

    /**
     * This method return the selected driver in the Driver dropdown field in Quick Dispatch pop-up
     *
     * @return : Selected Option/Driver
     * @Author: Sakrateesh R
     */
    public String get_Selected_Driver() {
        return get_selected_option(selectDriver_Dropdown, "Select Driver dropdown in Quick Dispatch");
    }

    /**
     * This method will return the invoicenumber displayed in the row 1 in trip section
     *
     * @return: Invoice Number in the Trip Section
     * @Author:Sakrateesh R
     */
    public String get_Displayed_InvoiceNumber_Row1() {
        return get_Element_Text(InvoiceNumberOnDispatchTable_Row1_TripSection, "Invoice Number in the Trip Section");
    }

    /**
     * This method is used to click the Dispatch Save button
     *
     * @Author: Sakrateesh R
     */
    public void Click_DispatchSave_Button() {
        click(DispatchSave_Button, "Dispatch Save button in Quick Dispatch");
    }

    /**
     * This method will verify whether the NewTrip button is displayed or not
     *
     * @return: true if the NewTrip button is displayed else false
     * @Author: Sakrateesh R
     */
    public boolean Verify_NewTripBtn_IsDisplayed() {
        return isElementDisplayed(DispatchNewTrip_Button, "New Trip Button in Dispatch Pop-up");
    }

    /**
     * This method will verify whether the Route Planner button is displayed
     *
     * @return: true if the Route Planner button is displayed else false
     * @Author: Sakrateesh R
     */
    public boolean Verify_RoutePlannerBtn_IsDisplayed() {
        return isElementDisplayed(DispatchRoutePlanner_Button, "Route Planner Button in Dispatch Pop-up");
    }

    /**
     * This method will verify whether the Remote Print Button is Displayed
     *
     * @return: true if the Remote Print Button is Displayed else false
     * @Author: Sakrateesh R
     */
    public boolean Verify_RemotePrintBtn_IsDisplayed() {
        return isElementDisplayed(DispatchRemotePrint_Button, "Remote Print Button in Dispatch Pop-up");
    }

    /**
     * This method will verify whether the Manual Print button is displayed
     *
     * @return: true if the Manual Print button is displayed else false
     * @Author: Sakrateesh R
     */
    public boolean Verify_ManualPrintBtn_IsDisplayed() {
        return isElementDisplayed(DispatchManualPrint_Button, "Manual Print Button in Dispatch Pop-up");
    }

    /**
     * This method verify whether the Add Payrate button is displayed
     *
     * @return: true if the Add Payrate button is displayed else false
     * @Author: Sakrateesh R
     */
    public boolean Verify_AddPayrateBtn_IsDisplayed() {
        explicitWait(DispatchAddPayrate_Button, 10);
        return isElementDisplayed(DispatchAddPayrate_Button, "Add Payrate Button in Dispatch Pop-up");
    }


    public String get_InvoiceNumber_On_TripSection() {
        return get_Element_Text(InvoiceNumberOnDispatchTable_Row1_TripSection, "Invoice Number in the Trip Section").trim();
    }

    /**
     * This method is used to verify whether the invoice number is display in the Trip Section
     *
     * @param Invoice
     * @return
     */
    public boolean verify_invoice_isDisplayed_in_trip_section(String Invoice) {
        for (WebElement invoice : InvoiceNumberOnDispatchTable_Row_TripSection) {
            if (get_Element_Text(invoice, "Invoice number in Trip Section").equals(Invoice)) {
                return true;
            }
        }
        return false;
    }

    public boolean is_Invoice_Number_Displayed_After_Delete(String invoiceNumber) {
        By invoiceLocator = By.xpath("//table[@id='dispatchCurrentDispatch']/tbody/tr/td[text()='" + invoiceNumber + "']");
        int maxRetries = 5;
        int waitBetweenRetriesMillis = 1000;

        try {
            for (int attempt = 1; attempt <= maxRetries; attempt++) {
                List<WebElement> elements = getDriver().findElements(invoiceLocator);

                if (elements.isEmpty()) {
                    System.out.println("✅ Invoice number '" + invoiceNumber + "' is NOT displayed after deletion (confirmed on attempt " + attempt + ").");
                    return true; // Invoice is not displayed => successfully deleted
                } else {
                    System.out.println("ℹ️ Attempt " + attempt + ": Invoice number '" + invoiceNumber + "' is still displayed.");
                    Thread.sleep(waitBetweenRetriesMillis); // Wait and retry
                }
            }

            System.out.println("❌ Invoice number '" + invoiceNumber + "' is STILL displayed after maximum retries.");
            return false;

        } catch (Exception e) {
            System.err.println("⚠️ Error while checking invoice display status: " + e.getMessage());
            e.printStackTrace();
            return false; // Safe fallback (treat as still visible in case of exception)
        }
    }


    public String get_NameAndAddress_On_TripSection() {
        return get_Element_Text(Name_Address_OnDispatchTable_Row1_TripSection, "Name and Address in the Trip Section").trim();
    }

    public String get_City_On_TripSection() {
        return get_Element_Text(City_OnDispatchTable_Row1_TripSection, "City in the Trip Section").trim();
    }

    public String get_State_On_TripSection() {
        return get_Element_Text(State_OnDispatchTable_Row1_TripSection, "State in the Trip Section").trim();
    }

    public String get_Zipcode_On_TripSection() {
        return get_Element_Text(Zipcode_OnDispatchTable_Row1_TripSection, "Zipcode in the Trip Section").trim();
    }

    /**
     * This method will return the driver Initial
     *
     * @param driverInitial
     * @return: The matched driver Initial
     * @Author: Sakrateesh R
     */
    public String get_SavedTrips_driverInitial(String driverInitial) {
        String driverinitial = null;
        for (WebElement ele : SavedTrips_driverInitial) {
            if (ele.getText().contains(driverInitial)) {
                driverinitial = (ele.getText());
                return driverinitial;
            }
        }
        return driverinitial;
    }

    /**
     * This method will return the DriverName in the Saved Trip Section
     *
     * @param drivername
     * @return: Driver Name in the Saved Trip Section
     * @Author: Sakrateesh R
     */
    public String get_SavedTrips_driverName(String drivername) {
        String driverinitial = null;
        for (WebElement ele : SavedTrips_driverName) {
            if (ele.getText().contains(drivername)) {
                driverinitial = (ele.getText());
                return driverinitial;
            }
        }
        return driverinitial;
    }

    /**
     * This method will return the Driver phone number in the Saved Trip Section
     *
     * @param driverphonenumber
     * @return: Driver phone number in the Saved Trip Section
     * @Author: Sakrateesh R
     */
    public String get_SavedTrips_driverPhoneNumber(String driverphonenumber) {
        String driverinitial = null;
        for (WebElement ele : SavedTrips_driverPhonenumber) {
            if (ele.getText().contains(driverphonenumber)) {
                driverinitial = (ele.getText());
                return driverinitial;
            }
        }
        return driverinitial;
    }

    /**
     * This method is used to click the saved trip in the Saved trip section
     *
     * @param RouteNumber
     * @param RouteNumber
     * @Description : This method is used to click the trip from the saved trip section based on the matching Route number
     * @Author : Sakrateesh R
     */
    public void Click_SavedTrips_based_on_route_number(String RouteNumber) {
        for (WebElement ele : SavedTrips_routeNumber) {
            if (getElementText(ele, " Saved Trip Route number").contains(RouteNumber)) {
                js_Click(ele, "Saved Trip based on Route Number");
                break;
            }
        }
    }

    public void click_SavedTrip_Route_Number(String RouteNumber) {
        WebElement routeNumber = getDriver().findElement(By.xpath("//td[text()='" + RouteNumber + "']"));
        explicitWait_for_ClickAction(routeNumber);
        jsClick(routeNumber, "Route Number: " + RouteNumber);
    }


    /**
     * This method is used to verify the saved trip in the Saved trip section
     *
     * @param RouteNumber
     * @Description : This method is used to verify the trip from the saved trip section based on the matching Route number
     * @Author : Sakrateesh R
     */

    public boolean Verify_SavedTrip_based_on_route_number(String RouteNumber) {
        for (WebElement ele : SavedTrips_routeNumber) {
            HighlightElement(ele);
            boolean value = ele.getText().contains(RouteNumber);
            if (ele.getText().contains(RouteNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method will click the close icon on the dispatch pop-up
     *
     * @Author: Sakrateesh R
     */
    public void Click_CloseIcon_dispatchPopup() {
        if (isElementDisplayed(closeIcon_dispatchPopup, "Close (X) icon in the Dispatch pop-up")) {
            jsClick(closeIcon_dispatchPopup, "Close (X) icon in the Dispatch pop-up");
        }
    }

//	=================== * Beginning of Pending Deliveries Functions * =======================

    public void click_Pending_Deliveries_Section_Invoice_Number(String invoice_no) {
        WebElement pending_deliveries_invoice = getDriver().findElement(By.xpath("//span[@class='hana-grid-row-undispatchorder'][contains(text(),'" + invoice_no + "')]/following::td[1]"));
        jsClick(pending_deliveries_invoice, "Invoice number in the Pending Deliveries section");
    }

    /**
     * This method will verify whether the Pending Delivery Section is displayed
     *
     * @return: true if the Pending Delivery Section is displayed else false
     * @Author: Sakrateesh R
     */
    public boolean Verify_Pending_Deliveries_Section_IsDisplayed() {
        return isElementDisplayed(pending_deliveries_section, "Pending Delivery Section in Dispatch Pop-up");
    }

    /**
     * This method will verify whether the Invoice is displayed in the Pending Deliveries section
     *
     * @param invoice_no
     * @return: true if invoice is displayed in the Pending Delivery section else false
     * @Author: Balaji N
     */
    public boolean Verify_The_Invoice_From_Pending_Deliveries(String invoice_no) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        int maxRetries = 3;
        int retryCount = 0;
        boolean isFound = false;

        By invoiceLocator = By.xpath("//div[@id='kendoUndispatchOrders']//td[2]//span[text()='" + invoice_no + "']");

        while (retryCount < maxRetries) {
            try {
                wait_For_Page_To_Be_Stable(getDriver());
                WebElement pendingInvoice = wait.until(ExpectedConditions.presenceOfElementLocated(invoiceLocator));
                wait.until(ExpectedConditions.visibilityOf(pendingInvoice));
                isFound = isElementDisplayed(pendingInvoice, "Invoice number label in Pending Deliveries section");

                if (isFound) {
                    Allure.step("✅ Invoice number **" + invoice_no + "** is displayed in the Pending Deliveries section.");
                    return true;
                } else {
                    Allure.step("❌ Invoice number **" + invoice_no + "** is not visible in the Pending Deliveries section.");
                    return false;
                }

            } catch (StaleElementReferenceException e) {
                retryCount++;
                Allure.step("⚠️ StaleElementReferenceException encountered. Retrying... Attempt: " + retryCount);
                delayWithGivenTime(1000);
            } catch (NoSuchElementException | TimeoutException e) {
                retryCount++;
                Allure.step("⚠️ Invoice number **" + invoice_no + "** not found on attempt " + retryCount + ". Retrying...");
                delayWithGivenTime(1000);
            } catch (Exception e) {
                Allure.step("❌ Unexpected error while verifying invoice number **" + invoice_no + "**: " + e.getMessage());
                return false;
            }
        }

        Allure.step("❌ Invoice number :" + invoice_no + " is not found in the Pending Deliveries section after " + maxRetries + " attempts.");
        return false;
    }


    /**
     * This method will verify whether the Invoice is displayed in the Pending Deliveries section
     *
     * @param invoice_no
     * @return: true if invoice is displayed in the Pending Delivery section else false
     * @Author: Sakrateesh R
     */
    public String get_The_Invoice_From_Pending_Deliveries(String invoice_no) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(90));
        WebElement pendingInvoices = getDriver().findElement(By.xpath("//div[@id='kendoUndispatchOrders']//td[2]//span[text()='" + invoice_no + "']"));
        wait.until(ExpectedConditions.visibilityOf(pendingInvoices));
        return get_Element_Text(pendingInvoices, "Invoice number label in Pending Deliveries section");
    }

    /**
     * This method will verify whether the Invoice is displayed in the Pending Deliveries section
     *
     * @param invoice_no
     * @return: true if invoice is displayed in the Pending Delivery section else false
     * @Author: Sakrateesh R
     */
    public boolean Verify_The_Invoice_Not_On_Pending_Deliveries(String invoice_no) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            WebElement pendingInvoices = getDriver().findElement(By.xpath("//div[@id='kendoUndispatchOrders']//td[2]//span[text()='" + invoice_no + "']"));
            wait.until(ExpectedConditions.visibilityOf(pendingInvoices));
            return isElementDisplayed(pendingInvoices, "Invoice number label in Pending Deliveries section");
        } catch (NoSuchElementException e) {
            return true;
        }

    }

    public boolean Verify_The_Invoice_Number_Displayed_On_Pending_Deliveries(String invoice_no) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            WebElement pendingInvoices = getDriver().findElement(By.xpath("//div[@id='kendoUndispatchOrders']//td[2]//span[text()='" + invoice_no + "']"));
            wait.until(ExpectedConditions.visibilityOf(pendingInvoices));
            return isElementDisplayed(pendingInvoices, "Invoice number label in Pending Deliveries section");
        } catch (Exception e) {
            throw new RuntimeException("pending deliveries section invoice number is " + invoice_no + " not found" + e);
        }

    }

    /**
     * This method is used to click the invoice from the Pending Deliveries section
     *
     * @param invoice_no
     * @Author: Sakrateesh R
     */
    public void Click_The_Invoice_In_The_Pending_Deliveries_Section(String invoice_no) {
        for (WebElement PendingDeliveries : undispatch_order_invoices_list) {
            if (PendingDeliveries.getText().equals(invoice_no)) {
                click(PendingDeliveries, "Invoice in the Pending Deliveries Section");
                break;
            }
        }
    }

    /**
     * Clicks the Invoice number on the Pending Deliveries section
     *
     * @param invoice_no
     * @Author Balaji N
     */
    public void click_The_Invoice_Number_On_The_Pending_Deliveries_Section(String invoice_no) {
        try {
            WebElement pending_deliveries_invoice = getDriver().findElement(By.xpath("//div[@id='kendoUndispatchOrders']//td//span[text()='" + invoice_no + "']"));
            click(pending_deliveries_invoice, "Invoice number in the Pending Deliveries section");
        } catch (Exception e) {
            throw new RuntimeException("Unable to click the invoice number in the Pending Deliveries section for the pending invoice of the invoice number :" + invoice_no + " " + e.getMessage());
        }
    }

    /**
     * This method will click on the Invoice i icon in the Pending Deliveries section
     *
     * @param invoiceNumber
     * @Author: Sakrateesh R
     */
    public void Click_The_I_Icon_For_The_Invoice_Displaying_In_Pending_Deliveries(String invoiceNumber) {
        try {
            WebElement Invoice_I_Icon_In_Pending_Deliveries_Section = getDriver().findElement(By.xpath("//span[@class='hana-grid-row-undispatchorder'][contains(text(),'" + invoiceNumber + "')]/following::i[1]"));
            click(Invoice_I_Icon_In_Pending_Deliveries_Section, "Invoice I icon in the Pending Deliveries section");
        } catch (Exception e) {
            throw new RuntimeException("Unable to clicks the I icon in the Pending Deliveries section for the invoice of the invoice number :" + invoiceNumber + " " + e.getMessage());
        }
    }

    /**
     * This method is to verify whether the timed icon is displaying or not
     *
     * @param : invoice_no
     * @return : boolean based on the condition
     * @Description : We have given the invoice number as params to validate, if the respective invoice number has been a timed order or not, based on the condition if it is a timed order it will return true else it will return false.
     * @Author : Sakrateesh R
     */
    public boolean verify_clock_icon_is_displaying_or_not(String invoiceNumber) {
        try {
            WebElement Invoice_Clock_Icon_In_Pending_Deliveries_Section = getDriver().findElement(By.xpath("//span[@class='hana-grid-row-undispatchorder'][contains(text(),'" + invoiceNumber + "')]/following::i[2]"));
            if (Invoice_Clock_Icon_In_Pending_Deliveries_Section.isDisplayed()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Clock icon is not displaying for the invoice number :" + invoiceNumber + " " + e);
        }
    }

    /**
     * This method is to verify whether the timed icon is displaying or not
     *
     * @param : invoice_no
     * @return : boolean if the designed completed icon is displayed it will return true else it will return false.
     * @Description : We have given the invoice number as params to validate, if the designe completed icon is displaying for the respective invoice or not, based on the condition if it is a timed order it will return true else it will return false.
     * @Author : Sakrateesh R
     */
    public boolean verify_designed_icon_is_displaying_or_not(String invoiceNumber) {
        try {
            WebElement Invoice_Designed_Icon_In_Pending_Deliveries_Section = getDriver().findElement(By.xpath("//span[@class='hana-grid-row-undispatchorder'][contains(text(),'" + invoiceNumber + "')]/following::td/i[@class='fa fa-check-circle']"));
            return is_Element_Displayed(Invoice_Designed_Icon_In_Pending_Deliveries_Section, "Design Icon in the Invoice under Pending Delivery Section in Quick Dispatch");
        } catch (Exception e) {
            throw new RuntimeException("Unable to verify the designed icon for the invoice number :" + invoiceNumber + " " + e);
        }
    }


//	Clicking on the Order
//	=================== * End of Pending Deliveries Functions * =======================

    public void Set_Delivery_Date(String date) {
        delivery_date_field.clear();
        delivery_date_field.sendKeys(date);
        current_date_in_calendar.clear();
    }

    //    Set current date as delivery date funciton
    public void Set_Current_Delivery_Date(String date) {
        delivery_date_field.sendKeys(date);
        current_date_in_calendar.click();
    }


    public String Get_delivery_date() {
        return delivery_date_field.getAttribute("value");
    }

//	Verifying the delivery date field is displaying or not.

    public boolean verify_delivery_date_field_is_displayed() {
        return verifyIsDisplayed(delivery_date_field);
    }

    //	Selecting Future Date
    public String Future_30Days_Date() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDay = currentDate.plusDays(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedPreviousDay = previousDay.format(formatter);
        return formattedPreviousDay;
    }

    public String Past_30Days_Date() {
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDay = currentDate.plusDays(-30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedPreviousDay = previousDay.format(formatter);
        return formattedPreviousDay;
    }

//    Clicking on the Future date in Calendar

    public void Click_future_date_in_calendar() {
        delivery_date_field.clear();
        delivery_date_field.sendKeys(Next_20Days_Date());
        current_date_in_calendar.click();
    }

//    Clicking on the Future date in Calendar

    public void Click_Past_date_in_calendar() {
        delivery_date_field.clear();
        delivery_date_field.sendKeys(Past_30Days_Date());
        current_date_in_calendar.click();

    }

// Validating the logic for the List of undispatched or message is displaying or not.

    /**
     * This method is used to verify whether the lis is displaying or not
     *
     * @return : If the invoice is present it will return true else it will return false
     * @Description : This method verify whether the invoice list is displaying or not in the Quick Dispatch section
     * @Author : Sakrateesh R
     * @Last-Modified-By : Sakrateesh R
     */
    public boolean validate_invoice_list_is_displaying_or_not() {
        try {
            return !undispatch_order_invoices_list.isEmpty();
        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

//    Get Text from the Undispatch Order when there is not order displayed

    public String Get_Text_from_Undispatch_Orders_when_there_is_no_order_displayed() {
        return undispatch_info_message_label.getText();
    }


    /**
     * This method Verif's the Invoice is displaying in the Trip Section or not
     *
     * @return : if the invoice list is displayed then it will return true else false
     * @Author : Sakrateesh R
     */
    public boolean validate_invoice_list_is_displaying_or_not_in_Trip_section() {
        try {
//            return trip_table_grid.isDisplayed();
            return is_Element_Displayed(trip_table_grid, "Invoice List in the Trip Section");
        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method is used to click the All Shop Toggle button
     *
     * @Author: Sakrateesh R
     */
    public void Click_All_shops_toggle_functionality() {
        js_Click(undispatch_all_shops_toggle_button, "All shop toggle button in the Quick Dispatch pop-up");
        wait_For_Page_To_Be_Stable(getDriver());
    }


//    Verifying the All shop toggle button is displaying or not

    /**
     * Verifying the All shop toggle button is displaying or not
     *
     * @return : if the element is displayed it will return true else it will return false.
     * @Description: This function highlights the All shop toggle button and verify's whether the respective toggle button is displaying or not
     * @Author : Sakrateesh R
     */

    public boolean verify_all_shop_toggle_button_is_displayed_or_not() {
//        HighlightElement(undispatch_all_shops_toggle_button);
//        return undispatch_all_shops_toggle_button.isDisplayed();
        return is_Element_Displayed(undispatch_all_shops_toggle_button, "All shop toggle button in Quick Dispatch popup");
    }

    /**
     * Verifying All shop toggle button is disabled or not.
     *
     * @return : if the toggle button is disabled then it will return true else it will return false
     * @Description : This function higlights the All shop toggle button and verify's whether the Allshop toggle button is disabled or not.
     * @Author : Sakrateesh R
     */
    public boolean verify_all_shop_toggle_button_is_disabled_or_not() {
        String disable_toggle_style = all_shop_toggle_to_verify_enable_or_disable.getAttribute("style");
        try {
            HighlightElement(all_shop_toggle_to_verify_enable_or_disable);

            return disable_toggle_style.contains("background-color: rgb(255, 255, 255)");
        } catch (NoSuchElementException e) {
            printError(all_shop_toggle_to_verify_enable_or_disable, "All shop Toggle button in Quick Dispatch", "No Such Element Exception", e);
        } catch (StaleElementReferenceException e) {
            getDriver().navigate().refresh();
            if (disable_toggle_style.contains("background-color: rgb(255, 255, 255)")) {
                return disable_toggle_style.contains("background-color: rgb(255, 255, 255)");
            } else {
                printError(all_shop_toggle_to_verify_enable_or_disable, "All shop Toggle button in Quick Dispatch", "Stale Element Reference Exception", e);
            }
        }
        return false;
    }

    /**
     * This method perform drag drop action between S.R.No column and Invoice column
     *
     * @return : If the column has been swapped it will return the column header as String.
     * @Description : In this method we are using the drag and drop method to swap or perform the reorder functionality,In the dragAndDrop method we need pass the two xpath which we need to reorder. once the respective column is reordered, we will take the 1st element text using getText() method to verify whether the Invoice is displaying in the first column.
     * @Author : Sakrateesh R
     */

    public String verify_column_reordered_or_not_for_column_sno() {
        dragAndDrop(pending_deliveries_table_column_header_srno, getPending_deliveries_table_column_header_invoice);
        delayWithGivenTime(2000);
//        return pending_deliveries_table_column_header_srno.getText();
        return getElementText(pending_deliveries_table_column_header_srno, "Pending Deliveries table invoice column");
    }

    /**
     * This method used to revert the changes made by "verify_column_reordered_or_not_for_column_sno" this funciton.
     *
     * @Description : Reverting the method "verify_column_reordered_or_not_for_column_sno" to maintian the table column order to the original state.
     * @Author : Sakrateesh R
     */
    public void revert_verify_column_reordered_or_not_for_column_sno_function() {
        dragAndDrop(getPending_deliveries_table_column_header_invoice, pending_deliveries_table_column_header_srno);
    }

    /**
     * This method perform drag drop action between S.R.No column and Invoice column
     *
     * @return : If the column has been swapped it will return the column header as String.
     * @Description : In this method we are using the drag and drop method to swap or perform the reorder functionality,In the dragAndDrop method we need pass the two xpath which we need to reorder. once the respective column is reordered, we will take the 1st element text using getText() method to verify whether the Invoice is displaying in the first column.
     * @Author : Sakrateesh R
     */

    public String verify_column_reordered_or_not_for_column_invoice() {
        dragAndDrop(getPending_deliveries_table_column_header_invoice, pending_deliveries_table_column_header_srno);
        delayWithGivenTime(1500);
//        return pending_deliveries_table_column_header_srno.getText();
        return getElementText(pending_deliveries_table_column_header_srno, "Pending Deliveries table invoice column");
    }

    /**
     * This method used to revert the changes made by "verify_column_reordered_or_not_for_column_invoice" this funciton.
     *
     * @Description : Reverting the method "verify_column_reordered_or_not_for_column_invoice" to maintian the table column order to the original state.
     * @Author : Sakrateesh R
     */
    public void revert_verify_column_reordered_or_not_for_column_invoice_function() {
        dragAndDrop(getPending_deliveries_table_column_header_invoice, pending_deliveries_table_column_header_srno);
    }

    /**
     * This method perform drag drop action between Invoice column and Name column
     *
     * @return : If the column has been swapped it will return the column header as String.
     * @Description : In this method we are using the drag and drop method to swap or perform the reorder functionality,In the dragAndDrop method we need pass the two xpath which we need to reorder. once the respective column is reordered, we will take the 1st element text using getText() method to verify whether the Invoice is displaying in the first column.
     * @Author : Sakrateesh R
     */
    public String verify_column_reordered_or_not_for_column_name() {
        dragAndDrop(getPending_deliveries_table_column_header_name, pending_deliveries_table_column_header_srno);
        delayWithGivenTime(1500);
//        return pending_deliveries_table_column_header_srno.getText();
        return getElementText(pending_deliveries_table_column_header_srno, "Pending Deliveries Column Name Header");
    }

    /**
     * This method used to revert the changes made by "verify_column_reordered_or_not_for_column_name" this funciton.
     *
     * @Description : Reverting the method "verify_column_reordered_or_not_for_column_name" to maintian the table column order to the original state.
     * @Author : Sakrateesh R
     */
    public void revert_verify_column_reordered_or_not_for_column_name_function() {
        dragAndDrop(getPending_deliveries_table_column_header_name, pending_deliveries_table_column_header_srno);
    }

    /**
     * This method is used to reorder the column between S.R.No and Summarized By column
     *
     * @return : If the column has been swapped it will return the column header as String.
     * @Description : In this method we are using the drag and drop method to swap or perform the reorder functionality,In the dragAndDrop method we need pass the two xpath which we need to reorder. once the respective column is reordered, we will take the 1st element text using getText() method to verify whether the Summarized By is displaying in the first column.
     * @Author : Sakrateesh R
     */
    public String verfiy_summary_table_column_srno_reorder_or_not() {
        dragAndDrop(summary_table_column_header_srno, summary_table_column_header_summarized_by);
//        return summary_table_column_header_srno.getText();
        return getElementText(summary_table_column_header_srno, "Summary Table Column Summarized by Header");
    }

    /**
     * This method used to revert the changes made by "verfiy_summary_table_column_srno_reorder_or_not" this funciton.
     *
     * @Description : Reverting the method "verfiy_summary_table_column_srno_reorder_or_not" to maintian the table column order to the original state.
     * @Author : Sakrateesh R
     */
    public void revert_verfiy_summary_table_column_srno_reorder_or_not() {
        dragAndDrop(summary_table_column_header_summarized_by, summary_table_column_header_srno);
    }

    /**
     * This method is used to reorder the column between Summarized By and S.R.No column
     *
     * @return : If the column has been swapped it will return the column header as String.
     * @Description : In this method we are using the drag and drop method to swap or perform the reorder functionality,In the dragAndDrop method we need pass the two xpath which we need to reorder. once the respective column is reordered, we will take the 1st element text using getText() method to verify whether the Summarized By is displaying in the first column.
     * @Author : Sakrateesh R
     */
    public String verfiy_summary_table_column_summarized_by_reorder_or_not() {
        dragAndDrop(summary_table_column_header_srno, summary_table_column_header_summarized_by);
//        return summary_table_column_header_srno.getText();
        return getElementText(summary_table_column_header_srno, "Summary Table Column Summarized by Header");
    }

    /**
     * This method used to revert the changes made by "verfiy_summary_table_column_summarized_by_reorder_or_not" this funciton.
     *
     * @Description : Reverting the method "verfiy_summary_table_column_summarized_by_reorder_or_not" to maintian the table column order to the original state.
     * @Author : Sakrateesh R
     */
    public void revert_verfiy_summary_table_column_summarized_by_reorder_or_not() {
        dragAndDrop(summary_table_column_header_summarized_by, summary_table_column_header_srno);
    }

    /**
     * This method is used to reorder the column between Summarized By and S.R.No column
     *
     * @return : If the column has been swapped it will return the column header as String.
     * @Description : In this method we are using the drag and drop method to swap or perform the reorder functionality,In the dragAndDrop method we need pass the two xpath which we need to reorder. once the respective column is reordered, we will take the 1st element text using getText() method to verify whether the Summarized By is displaying in the first column.
     * @Author : Sakrateesh R
     */
    public String verfiy_summary_table_column_count_reorder_or_not() {
        dragAndDrop(summary_table_column_header_count, summary_table_column_header_summarized_by);
//        return summary_table_column_header_count.getText();
        return getElementText(summary_table_column_header_count, "Summary Table Column Count Header");
    }

    /**
     * This method used to revert the changes made by "verfiy_summary_table_column_count_reorder_or_not" this funciton.
     *
     * @Description : Reverting the method "verfiy_summary_table_column_count_reorder_or_not" to maintian the table column order to the original state.
     * @Author : Sakrateesh R
     */
    public void revert_verfiy_summary_table_column_count_reorder_or_not() {
        dragAndDrop(summary_table_column_header_summarized_by, summary_table_column_header_count);
    }

    /**
     * Verifying whether the Summary section is displaying or not.
     *
     * @return : True if the Summary Section is displayed else return false
     * @Description : In this method we are validating whether the Summary section is displaying in the quick dispatch pop-up or not
     * @Author : Sakrateesh R
     */

    public boolean Verify_summary_section_is_displayed() {
        //return summary_section_element.isDisplayed() && Get_Summary_section_label().equals("Summary");
        return isElementDisplayed(summary_section_element, "Summary Section in the Quick Dispatch") && Get_Summary_section_label().equals("Summary");
    }

    /**
     * Extracting the Summary Section Header
     *
     * @return : Respective Element Header text as String
     * @Description : In this method we are using getText() method to extract the text from the Summary section
     * @Author : Sakrateesh R
     */

    public String Get_Summary_section_label() {
        return getElementText(summary_section_header_label_element, "Summary Label in the Quick Dispatch");
    }

    /**
     * Click the record in the Summary table
     *
     * @Description : In this method it will click the record which contains the city as San Diego in the summary table
     * @Author : Sakrateesh R
     */
    public void Click_San_diego_record_in_summary_table() {
        if (isElementDisplayed(summary_record_sandiego_element, "Sandiego City records")) {
//            summary_record_sandiego_element.click();
            js_Click(summary_record_sandiego_element, "Cliking the Record based on the Sandiego City  in Summary table");
        }
    }

    /**
     * This method will return the order count for the city sandiego
     *
     * @return : It will return the count as string
     * @Description : This method will return the count of the summarized orders for the city sandiego
     * @Author : Sakrateesh R
     */
    public String Get_count_from_summarized_order_for_city_sandiego() {
//        return summary_record_sandiego_count_element.getText();
        return getElementText(summary_record_sandiego_count_element, "Count of Summarized Orders for City San Diego");
    }

    /**
     * This method will return the total deliveries count
     *
     * @return : It will return the total deliveries count as string
     * @Description : This method will return the count of total deliveries in the trip section
     * @Author : Sakrateesh R
     */
    public String Get_total_deliveries_count_from_trip_section() {
        return getElementText(trip_section_total_deliveries_count_element, "Total Deliveries Count from Trip Section");
    }

    /**
     * This method verifies wheher the save button is displaying or not.
     *
     * @return : if the save button is displayed it will return true else it will return false
     * @Description : In this method we are verifying whether the save button is displaying or not.
     * @Author : Sakrateesh R
     */
    public boolean trip_section_save_button_is_displayed() {
        return isElementDisplayed(DispatchSave_Button, "Dispatch Save Button");
    }

    /**
     * This method verify whether the trip number is generated or not.
     *
     * @return : It will return true when the trip number is generated else it will return false
     * @Description: This method verify whether the trip number is generated or not
     * @Author : Sakrateesh R
     */
    public boolean verify_trip_no_is_generated_or_not() {
        return !getElementText(trip_no_generating_element, "Trip No in the Quick Dispatch").equals("");

    }

    /**
     * This method verify whether the route number is generated or not.
     *
     * @return : It will return true if the route number is displayed else it will return false
     * @Description : This method verify whether the route number is generated or not
     * @Author : Sakrateesh R
     */
    public boolean verify_route_number_is_generated_or_not() {
        return !getElementText(trip_route_number_element, "Route Number in the Quick Dispatch").equals("");
    }

    /**
     * This method will return the Generated the Route Number
     *
     * @return : It will return the Route Number
     * @Description: This method will return the Generated the Route Number
     * @Author: Sakrateesh R
     */
    public String Get_Route_number() {
        System.out.println("Route Number" + trip_route_number_element.getText());
        return getElementText(trip_route_number_element, "Trip Route Number in Trip Section");
    }

    /**
     * This method verify whether the date is displaying in the current format or not
     *
     * @return : If the date time format is displaying properly then it will return true else it will return false
     * @Description : This method verify whether the date time format is displaying or not
     * @Author : Sakrateesh R
     */
    public boolean verify_trip_section_created_date_time_format_displaying_properly_or_not() {
        String date = getElementText(trip_section_created_date_time_element, "Trip Section Created Date and Time").trim(); // Trim whitespace
        //System.out.println("Actual Date String: " + date);  // For debugging

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.ENGLISH);

        try {
            LocalDateTime.parse(date, formatter);
            return true;  // Parsing was successful
        } catch (DateTimeParseException e) {
            System.out.println("Parsing failed: " + e.getMessage());  // Print the exception message
            return false;  // Parsing failed
        }
    }

    /**
     * This method verify whether the saved trip is displaying or not.
     *
     * @return : If the route no is displayed in the saved trip then it will return true else it will return false
     * @Description : This method is verify whether the route number is displaying in the saved trip section
     * @Author : Sakrateesh R
     */
    public boolean verify_whether_the_route_no_is_displaying_in_saved_trip_section() {
        String route_no = trip_route_number_element.getText();
        WebElement RouteXpath = getDriver().findElement(By.xpath("//div[@id='kendoDispatchSavedTrips']/div[2]/table/tbody/tr/td[contains(text(),'" + route_no + "')]"));
        return isElementDisplayed(RouteXpath, "Route Number in the Saved Trip Section");

    }

    /**
     * This method verify whether the invoice is displaying in the trip section or not
     * Description : This method verifies whether the the respective invoice is displaying in the trip table or not under the trip section
     *
     * @param invoice
     * @return : If the invoice is present in the trip table then it will return true else it will return false
     * @Author : Sakrateesh R
     */
    public boolean verify_invoice_is_displaying_in_trip_section(String invoice) {
        try {
            for (WebElement invoice_in_trip : trip_section_table_invoices_list) {
                return isElementDisplayed(invoice_in_trip, "Invoice number label on trip section");
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return false;
    }

    /**
     * This method will click the New trip button
     * Description : This method will click the New trip button in the Trip section
     *
     * @Author : Sakrateesh R
     */
    public void Click_New_Trip_button() {
        click(DispatchNewTrip_Button, "New Trip Button");
    }

    /**
     * This method verify whether the confirmation pop-up is displaying or not.
     *
     * @return : If the confirmation pop-up is displayed it will return true else it will return false
     * @Description : This method will verify whether the confirmation pop-up is displayed or not
     * @Author : Sakrateesh R
     */
    public boolean verify_confirmation_popup_is_displaying_or_not() {
        //  fluent_Wait_for_Visibility(Confirmation_popup, 30, 2);
        try {
            return Confirmation_popup.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * This method is used to verify whether the Saved Trip Section is Displayed or not.
     *
     * @return : Return true if the Saved Trip section is displayed else return false
     * @Description : This method is used to verify whether the Saved Trip Section is Displayed or not.
     * @Author : Sakrateesh R
     */
    public boolean Verify_Saved_Trip_Section_IsDisplayed() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", SavedTripSection);

        // Verify if the section is displayed
        if (is_Element_Displayed(SavedTripSection, "Saved Trip Section")) {
            return is_Element_Displayed(SavedTripSection, "Saved Trip Section");
        } else {
            System.out.println("The section is NOT displayed.");
        }
        return false;

    }

    /**
     * This method is used to delete the SavedTrips.
     *
     * @param RouteNumber
     * @Description : This method is used to delete the SavedTrips based on the RouteNumber
     * @Author : Sakrateesh R
     */
    public void Delete_SavedTrip_based_on_route_number(String RouteNumber) {
        for (int i = 0; i < SavedTrips_routeNumber.size(); i++) {

            System.out.println(RouteNumber);
            // Check if the route number matches
            if (SavedTrips_routeNumber.get(i).getText().contains(RouteNumber)) {
                // Dynamically construct the XPath
                String deleteXpath = "//div[@id='kendoDispatchSavedTrips']/div[2]/table/tbody/tr[" + (i + 1) + "]/td[7]/a";
                System.out.println("Generated XPath: " + deleteXpath);

                WebElement DeleteXpath = getDriver().findElement(By.xpath(deleteXpath));
                explicitWait_for_ClickAction(DeleteXpath);
                HighlightElement(DeleteXpath);
                DeleteXpath.click();
                break;
            }
        }
    }

    /**
     * This method return the confirmation popup header
     *
     * @return : It will return the text of the confirmation header
     * @Description : This method will return the text of the confirmation header
     * @Author : Sakrateesh R
     */
    public String Get_Confirmation_popup_header_text() {

        return getElementText(quick_dispatch_confirmatio_popup_header, "Confirmation pop-up header text");
    }

    /**
     * This method return the confirmation popup warning message
     *
     * @return : It will return the text of the confirmation warning text message
     * @Description : This method will return the text of the confirmation warning message
     * @Author : Sakrateesh R
     */
    public String Get_confirmation_popup_warning_text() {

        return getElementText(quick_dispatch_confirmation_popup_warning_text, "Confirmation popup warning message");
    }

    /**
     * This method verify whether the button is displaying or not
     *
     * @return : If the no button is displayed it will return true else it wil return false
     * @Description : This method will verify whether the no button is displaying in the confirmation pop-up or not.
     * @Author : Sakrateesh R
     */
    public boolean verify_no_button_is_displaying_or_not_in_confirmation_popup() {

        return is_Element_Displayed(quick_dispatch_confirmation_popup_no_button, "No Button in the Confirmation Pop-up");
    }

    /**
     * This method verify whether the button is displaying or not
     *
     * @return : If the yes button is displayed it will return true else it wil return false
     * @Description : This method will verify whether the yes button is displaying in the confirmation pop-up or not.
     * @Author : Sakrateesh R
     */
    public boolean verify_yes_button_is_displaying_or_not_in_confirmation_popup() {

        return is_Element_Displayed(quick_dispatch_confirmation_popup_yes_button, "Yes Button in Confirmation pop-up");
    }

    /**
     * This method used to click the button
     * Description : This method is used to click the no button in the confirmation pop-up
     *
     * @Author : Sakrateesh R
     */
    public void Click_confirmation_popup_no_button() {
        explicitWait_for_ClickAction(quick_dispatch_confirmation_popup_no_button);
        Click(quick_dispatch_confirmation_popup_no_button, "No button in Confirmation pop-up");
    }

    /**
     * This method used to click the button
     * Description : This method is used to click the yes button in the confirmation pop-up
     *
     * @Author : Sakrateesh R
     */
    public void Click_confirmation_popup_yes_button() {
        explicitWait_for_ClickAction(quick_dispatch_confirmation_popup_yes_button);
        Click(quick_dispatch_confirmation_popup_yes_button, "Yes button in the Confirmation pop-up");
    }

    /**
     * This method used to click the button
     * Description : This method is used to click the route planner button in the Trip section in Quick Dispatch
     *
     * @Author : Sakrateesh R
     */
    public void Click_route_planner_button() {
//        DispatchRoutePlanner_Button.click();
        Click(DispatchRoutePlanner_Button, "Route Planner button");
    }

    /**
     * This method used to click the button
     * Description : This method is used to click the remote print button in the Trip section in Quick Dispatch
     *
     * @Author : Sakrateesh R
     */
    public void Click_remote_print_button() {
        Click(DispatchRemotePrint_Button, "Remote print button");
    }

    /**
     * This method used to click the button
     * Description : This method is used to click the manual print button in the Trip section in Quick Dispatch
     *
     * @Author : Sakrateesh R
     */
    public void Click_manual_print_button() {
        Click(DispatchManualPrint_Button, "Manual print button");
    }

    /**
     * This method used to verify whether the Manual Print pop-up is displaying or not.
     *
     * @return : if the pop-up is displayed it will return true else it will return false.
     * @Description : This method is used to verify whether the Manual Print pop-up is displaying or not.
     * @Author : Sakrateesh R
     */
    public boolean verify_manual_print_is_displayed() {
        return is_Element_Displayed(quick_dispatch_manual_print_popup, "Manual Print pop-up");
    }

    /**
     * This method used to Handle the iframes
     *
     * @Description : This method is used to Handle the I frames, it is still under construction
     * @Author : Sakrateesh R
     */
    public void Handle_I_frame_pop_up() {
        WebElement FrameElement = getDriver().findElement(By.cssSelector("#ifrmManualPrint"));
        try {
            // Switch to the iframe containing the pdf-viewer
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameElement));
            System.out.println("Switched to iframe successfully.");

            // Wait for the pdf-viewer to become visible
            WebElement pdfViewer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("pdf-viewer")));
            if (pdfViewer != null) {
                // Scroll to the pdf viewer
                JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
                jsExecutor.executeScript("arguments[0].scrollIntoView();", pdfViewer);
                System.out.println("Scrolled to pdf-viewer.");
            } else {
                System.out.println("pdf-viewer not found.");
            }

            // Continue with your Robot actions...

        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for iframe or element to load: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
        } catch (NoSuchFrameException e) {
            printError(FrameElement, "Pdf Viewer Iframe", "No Such Frame Exception", e);
        } finally {
            // Switch back to the main content
            getDriver().switchTo().defaultContent();
            System.out.println("Switched back to the main content.");
        }
    }

    /**
     * This method selects the date in the delivery date field in the Quick Dispatch pop-up
     *
     * @param deliveryDate
     * @Author: Balaji N
     */
    public void Select_Delivery_Date_on_Quick_Dispatch_Page(String deliveryDate) {
        logPageLoad("Quick Dispatch → Select Delivery Date Picker Field", () -> {
            wait_For_Page_To_Be_Stable(getDriver());
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate targetDate = LocalDate.parse(deliveryDate, formatter);

                int targetDay = targetDate.getDayOfMonth();
                String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();

                click(deliverydate_on_quickdispatch_popup, "delivery Date in the quick dispatch pop-up");

                while (true) {
                    String displayedMonthYear = getElementText(deliverydate_monthyear_on_advancedispatchpage, "Delivery Date Month year on the Quick dispatch").trim();
                    if (displayedMonthYear.equalsIgnoreCase(targetMonthYear)) {
                        break;
                    }
                    click(deliverydate_nextbutton_on_advancedispatchpage, "Delivery Date Picker Next Button on the Dispatch pop-up");
                }

                for (WebElement dayElement : listofdays_on_deliverydate_datepicker) {
                    if (dayElement.isEnabled() &&
                            dayElement.getText().equalsIgnoreCase(String.valueOf(targetDay))) {
                        click(dayElement, "Date in the Calendar Date Pickup in Dispatch pop-up");
                        break;
                    }
                }
            } catch (NoSuchElementException e) {
                printError(deliverydate_on_quickdispatch_popup, "Delivery Date Field on Quick Dispatch popup", "No Such Element Exception", e);
            }
        });
    }

    /**
     * This method is used to verify whether the Add Payrate pop-up is displaying or not
     *
     * @Description: This method verify whether the Add Payrate pop-up is displaying or not
     * @return: If the Add Payrate popup is displayed it will return true else it will return false
     * @Author: Sakrateesh R
     */
    public boolean verify_add_payrate_popup_IsDisplayed() {
        return isElementDisplayed(add_payrate_label, "Add Payrate Pop-up");
    }

    public String get_Add_Payrate_Invoice_Number_On_Add_Payrate_Popup() {
        Switch_TO_Add_Payrate_Iframe();
        return get_Element_Text(row1_payrate_popup_invoice_number, "Invoice Number in the Add Payrate Popup").trim();
    }

    public void Switch_TO_Add_Payrate_Iframe() {
        getDriver().switchTo().frame("ifrmAddPayrate");
    }

    public String get_Add_Payrate_Invoice_Number_On_Add_Payrate_Popup(String Invoice) {
        Switch_TO_Add_Payrate_Iframe();
        WebElement invoiceNumber = getDriver().findElement(By.xpath("//table[@id='gvPayrateDetails']//td[text()='" + Invoice + "']"));
        return get_Element_Text(invoiceNumber, "Invoice Number in the Add Payrate Popup").trim();
    }

    /**
     * This method is used to verify whether the invoice is displaying or not
     *
     * @param Invoice
     * @Description: This method is used to verify whether the invoice in the trip section is displaying in the add payrate table or not.
     * @return: If the inovoice is displayed in the add payrate table it will return true else it will return false
     * @Author: Sakrateesh R
     */
    public boolean verify_invoice_is_displaying_in_the_AddPayrate_popup(String Invoice) {

        for (int i = 1; i < payrate_invoice_row_lists.size(); i++) {
            WebElement row = payrate_invoice_row_lists.get(i);
            List<WebElement> cells = row.findElements(By.xpath(".//td"));

            //System.out.println("Row " + i + " has " + cells.size() + " cells.");
            if (cells.size() >= 2) {

                WebElement invoiceNumberCell = cells.get(1);  // 0-indexed, so cells.get(1) is the second td
                String cellText = invoiceNumberCell.getText().trim();

                try {
                    if (cellText.equals(Invoice)) {
                        Highlight_Element(invoiceNumberCell, "Invoice in Add Pay Rate popup"); // Assuming HighlightElement highlights the matched cell
                        return true;
                    }
                } catch (NoSuchElementException e) {
                    printError(invoiceNumberCell, "Invoice in the Add Payrate popup", "No Such Element Exception", e);
                }
            } else {
                System.out.println("Row " + i + " does not have enough columns.");
            }
        }

        System.out.println("Invoice not found.");
        return false;
    }

    /**
     * This method is used to enter the value in the Payrate textbox
     *
     * @param Payrate
     * @Description: This method is used to enter the value in the Drivers Pay rate textbox
     * @Author: Sakrateesh R
     */
    public void Enter_value_in_Drivers_Payrate_textbox(String Payrate, String Invoice) {
        if (payrate_invoice_row_lists.size() > 0) {
            for (int i = 1; i < payrate_invoice_row_lists.size(); i++) {
                WebElement row = payrate_invoice_row_lists.get(i);
                List<WebElement> cells = row.findElements(By.xpath(".//td"));

                if (cells.size() >= 10) {
                    WebElement PayrateNumberCell = cells.get(9);  // Target the 10th td cell
                    WebElement cellText = cells.get(1);


                    if (cellText.getText().trim().equalsIgnoreCase(Invoice.trim())) {

                        Highlight_Element(PayrateNumberCell, "Drivers Payrate Number Field");

                        try {

                            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", PayrateNumberCell);


                            WebElement inputField = PayrateNumberCell.findElement(By.xpath(".//input[contains(@name, 'txtPayrate')]"));

                            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                            wait.until(ExpectedConditions.elementToBeClickable(inputField));

                            Click(inputField, "Drivers Payrate field");  // Ensure focus

                            inputField.clear();
                            for (char c : Payrate.toCharArray()) {
                                inputField.sendKeys(String.valueOf(c));
                                Thread.sleep(100);  // Small delay between key presses
                            }

                        } catch (NoSuchElementException | InterruptedException e) {
                            printError(PayrateNumberCell, "Drivers Payrate Number Cell", "No Such Element Exception", e);

                        }
                    }
                } else {
                    System.out.println("Row " + i + " does not have enough columns.");
                }
            }
        } else {
            System.out.println("No rows found in payrate_invoice_row_lists.");
        }
    }

    public void enter_Non_Payable_Reason(String nonpayable_reason) {
        click(row1_non_payable_reason_textbox, "Row 1 Non Payable Reason Textbox");
        row1_non_payable_reason_textbox.clear();
        ClickAndType(row1_non_payable_reason_textbox, nonpayable_reason, "Row 1 Non Payable Reason Textbox");
    }

    public void enter_Driver_Payrate(String Payrate) {
        click(row1_driver_pay_rate_textbox, "Row 1 Driver Payrate Textbox");
        row1_driver_pay_rate_textbox.clear();
        clickAndType(row1_driver_pay_rate_textbox, Payrate);
    }


    /**
     * This method is used to click the Add Payrate Update button
     *
     * @Description: This method is used to click the Add Payrate Update button
     * @Author: Sakrateesh R
     */
    public void Click_add_payrate_update_button() {
        jsClick(Add_payrate_update_button, "Add Payrate Update button");
    }

    /**
     * This method is verify whehter success toaster message is displaying or not
     *
     * @Description: This method is verify whehter success toaster message is displaying or not
     * @return: if the success message is displayed it will return true else it will return false
     */
    public boolean verify_add_payrate_success_message_IsDisplayed() {
        // HighlightElement(Add_payrate_success_message);
        return is_Element_Displayed(Add_payrate_success_message, "Add Payrate Success message");
    }

    /**
     * This method is used to get the success message
     *
     * @Description: This method will get the text from the success message label
     * @return: it will return the text of success message
     */
    public String Get_success_message_add_payrate() {
        return Add_payrate_success_message.getText();
    }

    /**
     * This method is used to click the close icon in the Add Payrate popup
     *
     * @Description: This method is used to click the close icon in the Add Payrate popup
     * @Author: Sakrateesh R
     */
    public void Click_close_icon_add_payrate_pop_up() {
        Add_payrate_close_icon_button.click();
    }

    /**
     * This method is used to click the close button in the Add Payrate popup
     *
     * @Description: This method is used to click the close button in the Add Payrate popup
     * @Author: Sakrateesh R
     */
    public void Click_Close_button_add_payrate_popup() {
        fluentWait(Add_payrate_close_button);
        Double_Click(Add_payrate_close_button, "Close button in Add Payrate popup");
    }

    /**
     * This method is used to get the value in the Payrate textbox
     *
     * @Description: This method is used to get the value in the Drivers Pay rate textbox
     * @Author: Sakrateesh R
     */
    public String Get_value_in_Drivers_Payrate_textbox() {
        return getElementAttribute(drivers_payrate_textbox, "drivers payrate textbox");
//        return drivers_payrate_textbox.getAttribute("value");
    }

    /**
     * This method is used Click the AddPayrate Button
     *
     * @Description: This method is used to Click the AddPayrate Button
     * @Author: Sakrateesh R
     */
    public void Click_AddPayrate_Button() {
        if (isElementDisplayed(DispatchAddPayrate_Button, "Add Payrate button in quick dispatch popup")) {
            js_Click(DispatchAddPayrate_Button, "Add Payrate button");
        }
    }


    /**
     * This method is used to enter the value in the Non Payable Reason textbox
     *
     * @param NonPayableReason
     * @Description: This method is used to enter the value in the Non Payable Reason textbox
     * @Author: Sakrateesh R
     */
    public void Enter_value_in_Non_Payable_Reason_textbox(String NonPayableReason, String Invoice) {
        if (payrate_invoice_row_lists.size() > 0) {
            for (int i = 1; i < payrate_invoice_row_lists.size(); i++) {
                WebElement row = payrate_invoice_row_lists.get(i);
                List<WebElement> cells = row.findElements(By.xpath(".//td"));

                System.out.println("Row " + i + " has " + cells.size() + " cells.");
                if (cells.size() >= 10) {
                    WebElement NoPayableReasonCell = cells.get(8);  // Target the 8th td cell
                    WebElement cellText = cells.get(1);

                    // Confirm Invoice matches
                    System.out.println("Checking invoice for row " + i + ": " + cellText.getText().trim());
                    if (cellText.getText().trim().equalsIgnoreCase(Invoice.trim())) {
                        //System.out.println("Invoice matches for row " + i);
                        Highlight_Element(NoPayableReasonCell, "Non Payable Reason Input Field");

                        try {
                            // Step 1: Scroll to the PayrateNumberCell to make sure it’s visible
                            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", NoPayableReasonCell);

                            // Step 2: Locate the input field with a robust locator
                            WebElement inputField = NoPayableReasonCell.findElement(By.xpath(".//input[contains(@name, 'gvPayrateDetails$ctl02$txtnonpayable')]"));
                            System.out.println("Located input field: " + inputField.getAttribute("outerHTML"));

                            // Step 3: Wait for the input field to be clickable and focus on it
                            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                            wait.until(ExpectedConditions.elementToBeClickable(inputField));

                            Click(inputField, "Non Payable Input Field");  // Ensure focus

                            // Step 4: Clear and type the value character-by-character
                            js_Clear(inputField, "Non Payable Input Field");
                            js_Clear_And_Type(inputField, NonPayableReason, "Non Payable Input Field");

                        } catch (Exception e) {
                            System.out.println("Error entering pay rate for row " + i + ": " + e.getMessage());
                        }
                    }
                } else {
                    System.out.println("Row " + i + " does not have enough columns.");
                }
            }
        } else {
            System.out.println("No rows found in payrate_invoice_row_lists.");
        }
    }

    /**
     * This method is used to get the value in the Non Payable Reason textbox
     *
     * @Description: This method is used to get the value in the Non Payable Reason textbox
     * @Author: Sakrateesh R
     */
    public String Get_value_in_Non_Payable_Reason_textbox() {
        return non_payable_reason_textbox.getAttribute("Value");
    }

    /**
     * This method is used to Check the Payable Checkbox
     *
     * @param Invoice
     * @Description: This method is used to Check the Payable Checkbox
     * @Author: Sakrateesh R
     */
    public void Payable_checkbox_checked(String Invoice) {
        if (payrate_invoice_row_lists.size() > 0) {
            for (int i = 1; i < payrate_invoice_row_lists.size(); i++) {
                WebElement row = payrate_invoice_row_lists.get(i);
                List<WebElement> cells = row.findElements(By.xpath(".//td"));

                //System.out.println("Row " + i + " has " + cells.size() + " cells.");
                if (cells.size() >= 10) {
                    WebElement Payable_checkbox = cells.get(7); // Target the 8th td cell
                    WebElement cellText = cells.get(1); // Target the 2nd td cell for Invoice

                    if (cellText.getText().trim().equalsIgnoreCase(Invoice.trim())) {

                        Highlight_Element(Payable_checkbox, "Payable Checkbox in Add Payrate popup");

                        // Locate the checkbox inside the Payable_checkbox cell
                        WebElement checkbox = Payable_checkbox.findElement(By.xpath(".//input[@type='checkbox']"));

                        // Check if the checkbox is selected
                        if (!checkbox.isSelected()) {
                            Click(checkbox, "Payable Checkbox in the Add Payrate pop-up");
                            //checkbox.click();
                        }
                    }
                }
            }
        } else {
            System.out.println("No rows found in payrate_invoice_row_lists.");
        }
    }

    /**
     * This method is used to unCheck the Payable Checkbox
     *
     * @param Invoice
     * @Description: This method is used to unCheck the Payable Checkbox
     * @Author: Sakrateesh R
     */
    public void Payable_checkbox_unchecked(String Invoice) {
        if (payrate_invoice_row_lists.size() > 0) {
            for (int i = 1; i < payrate_invoice_row_lists.size(); i++) {
                WebElement row = payrate_invoice_row_lists.get(i);
                List<WebElement> cells = row.findElements(By.xpath(".//td"));

                //System.out.println("Row " + i + " has " + cells.size() + " cells.");
                if (cells.size() >= 10) {
                    WebElement Payable_checkbox = cells.get(7); // Target the 8th td cell
                    WebElement cellText = cells.get(1); // Target the 2nd td cell for Invoice

                    if (cellText.getText().trim().equalsIgnoreCase(Invoice.trim())) {

                        Highlight_Element(Payable_checkbox, "Payable Checkbox in Add Payrate popup");

                        WebElement checkbox = Payable_checkbox.findElement(By.xpath(".//input[@type='checkbox']"));

                        if (checkbox.isSelected()) {
                            Click(checkbox, "Payable Checkbox");
                            //checkbox.click();
                        }
                    }
                }
            }
        } else {
            System.out.println("No rows found in payrate_invoice_row_lists.");
        }
    }

    /**
     * This method is used to unCheck the Payable Checkbox
     *
     * @param Invoice
     * @Description: This method is used to unCheck the Payable Checkbox
     * @Author: Sakrateesh R
     */
    public boolean Verify_Payable_Checkbox_IsChecked(String Invoice) {
        if (payrate_invoice_row_lists.size() > 0) {
            for (int i = 1; i < payrate_invoice_row_lists.size(); i++) {
                WebElement row = payrate_invoice_row_lists.get(i);
                List<WebElement> cells = row.findElements(By.xpath(".//td"));

                if (cells.size() >= 10) {
                    WebElement Payable_checkbox = cells.get(7); // Target the 8th td cell
                    WebElement cellText = cells.get(1); // Target the 2nd td cell for Invoice

                    if (cellText.getText().trim().equalsIgnoreCase(Invoice.trim())) {
                        try {
                            Highlight_Element(Payable_checkbox, "Payable Checkbox in Add Payrate popup");

                            // Locate the checkbox inside the Payable_checkbox cell
                            WebElement checkbox = Payable_checkbox.findElement(By.xpath(".//input[@type='checkbox']"));
                            return checkbox.isSelected();
                        } catch (NoSuchElementException e) {
                            printError(cellText, "Payable Checkbox", "No Such Element Exception", e);
                        }
                    }
                }
            }
        } else {
            System.out.println("No rows found in payrate_invoice_row_lists.");
        }
        return false;
    }

    /**
     * This method verify whether the Trip section is displaying or not.
     *
     * @Description: This method verify whether the Trip section is displaying or not.
     * @Author: Sakrateesh R
     * @return: If the Trip section is displayed it will return true else it will return false
     */
    public boolean Verify_Trip_Section_IsDisplayed() {
        return Trip_Section_Element.isDisplayed();
    }

    /**
     * This method verify whether the Confirmation pop-up is displaying or not.
     *
     * @Description: This method verify whether the Confirmation pop-up  is displaying or not.
     * @Author: Sakrateesh R
     * @return: If the Confirmation pop-up is displayed it will return true else it will return false
     */
    public boolean Verify_Confirmation_popup_IsDisplayed() {
        try {
            // Define a WebDriverWait with a timeout of 10 seconds
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            By confmpopup = By.xpath("//div[@class='sweet-alert showSweetAlert visible']");

            // Wait until the popup becomes visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(confmpopup));

            // Locate the popup element
            WebElement Confirmation_popup = getDriver().findElement(confmpopup);

            // Highlight the popup for debugging purposes
            HighlightElement(Confirmation_popup);

            // Return true as the popup is displayed
            return Confirmation_popup.isDisplayed();
        } catch (TimeoutException e) {
            System.err.println("Timeout: Confirmation popup was not displayed within the specified time.");
        } catch (NoSuchElementException e) {
            System.err.println("NoSuchElementException: Confirmation popup element was not found.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while verifying the confirmation popup: " + e.getMessage());
        }

        return false;
    }

    /**
     * This method is used to click the Confirmation popup yes button
     *
     * @Description: This method is used to click the Confirmation popup yes button
     * @Author: Sakrateesh R
     */
    public void Click_Confirmation_yes_button() {
        js_Click(Confirmation_Yes_button, "Confirmation popup yes button");
    }

    /**
     * This method is used to remove the invoice from the trip
     *
     * @param Invoice
     * @Description: This methos is used to remove the invoice from the current trip
     * @Author : Sakrateesh R
     */

    public void Remove_Invoice_from_the_Trip_section(String Invoice) {
        WebElement invoice = null;
        try {
            for (int i = 0; i < InvoiceNumberOnDispatchTable_Row_TripSection.size(); i++) {
                for (int j = 0; j < TripSection_delete_list.size(); j++) {
                    if (InvoiceNumberOnDispatchTable_Row_TripSection.get(i).getText().equals(Invoice)) {
                        TripSection_delete_list.get(i).click();
                        invoice = TripSection_delete_list.get(i);
                    }
                }
            }
        } catch (NoSuchElementException e) {
            printError(invoice, "Invoice Number", "No Such Element Exception", e);
        }
    }

    /**
     * This method is used to delete the invoice from the trip
     *
     * @param Invoice
     * @Author: Balaji N
     */
    public void Delete_Invoice_from_the_Trip_section(String Invoice) {
        By deletelink = By.xpath("//td[text()='" + Invoice + "']/following-sibling::td//a");

        int retries = 3;
        boolean isClicked = false;

        while (retries > 0 && !isClicked) {
            try {
                wait_For_Page_To_Be_Stable(getDriver());

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
                WebElement deleteLink = wait.until(ExpectedConditions.elementToBeClickable(deletelink));

                // HighlightElement(deleteLink);
                click(deleteLink, "Delete link for Invoice '" + Invoice + "' in the Trip Section");

                String success = "✅ Successfully clicked Delete link for Invoice: " + Invoice;
                System.out.println(success);
                Allure.step(success);

                isClicked = true;
            } catch (StaleElementReferenceException | TimeoutException e) {
                retries--;
                String retryMsg = String.format("⚠️ Retry %d: Failed to click delete link for Invoice '%s' due to [%s]. Retrying...",
                        (3 - retries + 1), Invoice, e.getClass().getSimpleName());
                System.err.println(retryMsg);
                Allure.step(retryMsg);
                delayWithGivenTime(1000);
            } catch (NoSuchElementException e) {
                String notFound = "❌ Invoice '" + Invoice + "' not found in the Trip Section.";
                System.err.println(notFound);
                Allure.step(notFound);
                throw new RuntimeException(notFound, e);
            } catch (Exception e) {
                String fatal = "❌ Unexpected error while deleting Invoice '" + Invoice + "': " + e.getMessage();
                System.err.println(fatal);
                Allure.step(fatal);
                throw new RuntimeException(fatal, e);
            }
        }

        if (!isClicked) {
            String fail = "❌ Failed to click Delete link for Invoice on Trip Section '" + Invoice + "' after multiple retries.";
            System.err.println(fail);
            Allure.step(fail);
            throw new RuntimeException(fail);
        }
    }


    /**
     * This method is used to select a shop
     *
     * @param Shop
     * @Description : This method is used to select a shop from the select shop dropdown
     * @Author : Sakrateesh R
     */
    public void Select_Shops_From_Dropdown(String Shop) {
        dropDown(SelectShop_Dropdown, Shop, "VisibleText");
    }

    /**
     * This method is used to verify whether the selected shop is displaing or not
     *
     * @param shop
     * @return : If the shop is displayed then it will return true else it will return false
     * @Description: This method is used to verify whether the selected shop is displaing or not
     * @author : Sakrateesh R
     */

    public boolean Shop_is_Displayed_or_not(String shop) {
        Select select = new Select(SelectShop_Dropdown);
        return select.getFirstSelectedOption().getText().equals(shop);
    }

    /**
     * This method is used to select the current date in the dispatch date field.
     *
     * @Description: This method will compare the local time and the application time based on that it will select the current date in the Quick dispatch delivery date field
     * @author : Sakrateesh R
     */
    public void Select_Dispatch_Date() {
        String App_Date = deliverydate_on_quickdispatch_popup.getText();
        System.out.println(App_Date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // Adjust format if needed
        String formattedAppDate = App_Date.format(String.valueOf(formatter));
        String LocalCurrentDate = LocalDate.now().format(formatter);
        System.out.println(LocalCurrentDate);
        System.out.println(formattedAppDate);
        if (LocalCurrentDate.equals(formattedAppDate)) {
            System.out.println("Current date is displaying properly");
        } else {
            deliverydate_on_quickdispatch_popup.clear();
            Set_Current_Delivery_Date(CurrentDate());
        }

    }

    public String reorder_sno_column_and_verify() {
        try {
            HighlightElement(Saved_Trip_SNo_Column_header);
            HighlightElement(Saved_Trip_DriverInitial_Column_header);
            dragAndDrop(Saved_Trip_SNo_Column_header, Saved_Trip_DriverInitial_Column_header);
            return Saved_Trip_DriverInitial_Column_header.getText();
        } catch (Exception e) {
            printError(Saved_Trip_DriverInitial_Column_header, "Saved Trip Driver Initial Column Header", "NoSuchElementException", e);
            return null;
        }
    }

    public String reorder_driverInitial_column_and_verify() {
        try {
            dragAndDrop(Saved_Trip_DriverInitial_Column_header, Saved_Trip_SNo_Column_header);
            return Saved_Trip_DriverInitial_Column_header.getText();
        } catch (Exception e) {
            printError(Saved_Trip_DriverInitial_Column_header, "Saved Trip Driver Initial Column Header", "NoSuchElementException", e);
            return null;
        }
    }

    public String reorder_driverName_column_and_verify() {
        try {
            dragAndDrop(Saved_Trip_DriverName_Column_header, Saved_Trip_PhoneNo_Column_header);
            return Saved_Trip_PhoneNo_Column_header.getText();
        } catch (Exception e) {
            printError(Saved_Trip_PhoneNo_Column_header, "Saved Trip Phone Number Column Header", "NoSuchElementException", e);
            return null;
        }

    }

    public String reorder_PhoneNo_column_and_verify() {
        try {
            dragAndDrop(Saved_Trip_PhoneNo_Column_header, Saved_Trip_DriverName_Column_header);
            return Saved_Trip_PhoneNo_Column_header.getText();
        } catch (Exception e) {
            printError(Saved_Trip_Created_Date_Column_header, "Saved Trip Created Date Column Header", "NoSuchElementException", e);
            return null;
        }

    }

    public String reorder_RouteNo_column_and_verify() {
        try {
            dragAndDrop(Saved_Trip_RouteNo_Column_header, Saved_Trip_Created_Date_Column_header);
            return Saved_Trip_Created_Date_Column_header.getText();
        } catch (Exception e) {
            printError(Saved_Trip_Created_Date_Column_header, "Saved Trip Created Date Column Header", "NoSuchElementException", e);
            return null;
        }
        //will return Route No
    }

    public String reorder_CreatedDate_column_and_verify() {
        try {
            dragAndDrop(Saved_Trip_RouteNo_Column_header, Saved_Trip_Created_Date_Column_header);
            return Saved_Trip_Created_Date_Column_header.getText();
        } catch (Exception e) {
            printError(Saved_Trip_Created_Date_Column_header, "Saved Trip Created Date Column Header", "NoSuchElementException", e);
            return null;
        }
        //will return Created Date
    }

    public String reorder_Delete_column_and_verify() {
        try {
            dragAndDrop(Saved_Trip_Delete_Column_header, Saved_Trip_Created_Date_Column_header);
            return Saved_Trip_Created_Date_Column_header.getText();
        } catch (NoSuchElementException e) {
            printError(Saved_Trip_Delete_Column_header, "Delete Column header", "NoSuchElementException", e);
            return null;
        }
        //will return Created Date
    }

    public boolean is_Invoice_Number_Displayed_On_TripSection(String invoicenumber) {
        return isElementDisplayed(getDriver().findElement(By.xpath("//table[@id='dispatchCurrentDispatch']//td[text()='" + invoicenumber + "']")), "Invoice Number on Trip Section");
    }

    public void click_Delete_Link_in_Saved_Trip_Section(String RouteNumber) {
        WebElement deleteLink = getDriver().findElement(By.xpath("//td[text()='" + RouteNumber + "']/following-sibling::td[2]//a"));
        js_Click(deleteLink, "Delete Link in Saved Trip Section for respective route number");
    }

    private YearMonth parseMonthYear(String monthYearText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
        return YearMonth.parse(monthYearText, formatter);
    }

    public void select_Delivery_Date_on_Quick_Dispatch_Page(String deliveryDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate targetDate = LocalDate.parse(deliveryDate, formatter);
            int targetDay = targetDate.getDayOfMonth();
            String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();

            click(deliverydate_on_quickdispatch_popup, "delivery Date in the quick dispatch pop-up");

            // Loop until the displayed month and year match the target
            while (true) {
                String displayedMonthYear = getElementText(deliverydate_monthyear_on_advancedispatchpage, "Delivery Date Month year on the Quick dispatch").trim();
                YearMonth displayedYM = parseMonthYear(displayedMonthYear);
                YearMonth targetYM = YearMonth.from(targetDate);

                if (displayedYM.equals(targetYM)) {
                    break;
                } else if (displayedYM.isBefore(targetYM)) {
                    click(deliverydate_nextbutton_on_advancedispatchpage, "Next Button on Calendar");
                } else {
                    click(deliverydate_previousbutton_on_advancedispatchpage, "Previous Button on Calendar");
                }
            }

            // Select the day
            for (WebElement dayElement : listofdays_on_deliverydate_datepicker) {
                if (dayElement.isEnabled() && dayElement.getText().equals(String.valueOf(targetDay))) {
                    click(dayElement, "Selected Day in Date Picker");
                    break;
                }
            }

        } catch (NoSuchElementException e) {
            printError(deliverydate_on_quickdispatch_popup, "Delivery Date Field on Quick Dispatch popup", "No Such Element Exception", e);
        }
    }

    public void Set_Delivery_Date_Based_On_Order_Delivery_Date(String DeliveryDate) {
        String QuickDispatch_DeliveryDate = delivery_date_field.getAttribute("value");
        System.out.println(QuickDispatch_DeliveryDate + "Quick Dispatch Delivery Date");
        System.out.println(DeliveryDate + "Delivery Date from Phone order page");
        if (QuickDispatch_DeliveryDate != DeliveryDate) {
            js_Clear_And_Type(delivery_date_field, DeliveryDate, "Quick Dispatch Delivery Date field");
            delivery_date_field.click();
            current_date_in_calendar.click();
            System.out.println(QuickDispatch_DeliveryDate + "Quick Dispatch Delivery Date");
        }

    }

//    public void Select_Delivery_Date_on_Quick_Dispatch_Page(String deliveryDate) {
//        try {
//            // Format target delivery date
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//            LocalDate targetDate = LocalDate.parse(deliveryDate, formatter);
//
//            // Read already selected date on quick dispatch, if available
//            String selectedDateText = getElementText(deliverydate_on_quickdispatch_popup, "Currently selected delivery date on Quick Dispatch");
//            LocalDate selectedDate = null;
//
//            try {
//                selectedDate = LocalDate.parse(selectedDateText, formatter);
//            } catch (Exception e) {
//                // Handle cases where there's no date selected yet or invalid format
//            }
//
//            // Skip re-selecting if already correct date is selected
//            if (selectedDate != null && selectedDate.equals(targetDate)) {
//                log("Delivery date already selected correctly: " + selectedDateText);
//                return;
//            }
//
//            // Open calendar widget
//            click(deliverydate_on_quickdispatch_popup, "delivery Date in the quick dispatch pop-up");
//
//            // Navigate through months until the correct month/year is visible
//            String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();
//            int maxTries = 12; // Prevent infinite loop
//            while (maxTries-- > 0) {
//                String displayedMonthYear = getElementText(deliverydate_monthyear_on_advancedispatchpage, "Calendar Month-Year").trim();
//                if (displayedMonthYear.equalsIgnoreCase(targetMonthYear)) {
//                    break;
//                }
//                click(deliverydate_nextbutton_on_advancedispatchpage, "Calendar Next Button");
//            }
//
//            // Select the target day
//            String targetDayStr = String.valueOf(targetDate.getDayOfMonth());
//            boolean dateSelected = false;
//
//            for (WebElement dayElement : listofdays_on_deliverydate_datepicker) {
//                if (dayElement.isEnabled() && dayElement.getText().equalsIgnoreCase(targetDayStr)) {
//                    click(dayElement, "Selecting date: " + targetDayStr);
//                    dateSelected = true;
//                    break;
//                }
//            }
//
//            if (!dateSelected) {
//                throw new NoSuchElementException("Date not found or disabled in calendar: " + deliveryDate);
//            }
//
//        } catch (NoSuchElementException e) {
//            printError(deliverydate_on_quickdispatch_popup, "Delivery Date Field on Quick Dispatch popup", "No Such Element Exception", e);
//        } catch (Exception e) {
//            printError(deliverydate_on_quickdispatch_popup, "Delivery Date Field on Quick Dispatch popup", "General Exception", e);
//        }
//    }


}

