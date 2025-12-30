package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.VerificationResult;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Advance Dispatch Page in Hana POS Application
 *
 * @Author Balaji N
 */
public class Advance_DispatchPage extends TestBaseClass {

    public Advance_DispatchPage() {
        PageFactory.initElements(getDriver(), this);
    }

    // ============ Advance Dispatch Page Elements ========================
    //    Advance Dispatch Sub Menu in the Dispatch Menu button
    @FindBy(xpath = "//a[@class='li_AdvanceDispatch']")
    private WebElement Advance_Dispatch_Sub_Menu;

    // Advance Dispatch Page Map View dropdown Web Element
    @FindBy(xpath = "//select[@id='mapDataRoute']")
    private WebElement Advance_Dispatch_map_view_dropdown;

    //  Advance Dispatch List of Map View Delivery_Icon Element
    @FindBy(xpath = "//div[@role='button']")
    private List<WebElement> List_of_Map_View_Delivery_Icons;

    @FindBy(xpath = "//div[@id='kendoAdvanceUndispatchOrders']/div[2]/table/tbody/tr/td[2]")
    private List<WebElement> pending_deliveries_invoice_list;

 /*   @FindBy(xpath = "//div[@id='kendoAdvanceUndispatchOrders']/div[2]/table/tbody/tr/td//span//a[contains(text(),'" + invoicenumber + "')]/following::td[1]")
    private WebElement pending_deliveries_address;*/

    @FindBy(xpath = "//div[@class='gm-style']//div[3]//div[3]//img[1]")
    private WebElement not_delivered_map_icon;

    @FindBy(xpath = "//div[@class='gm-style-iw-d']//h3")
    private WebElement hover_map_icon_on_invoicenumber;

    @FindBy(xpath = "//div[@id='kendoAdvanceUndispatchOrders']//table/tbody/tr/td//a")
    private List<WebElement> pending_deliveries_invoices_list;

    @FindBy(xpath = "//h5[text()='Summary']/following::div[1]")
    private WebElement summary_section_advance_dispatch_page;

    //    Invalid Address Pop-up Element
    @FindBy(xpath = "//div[@id='ModalVerifyAddress']//div[@class='modal-content']")
    private WebElement invalid_address_pop_up;

    //    Proceed without Address Verification button Element
    @FindBy(xpath = "//input[@id='btnWithoutAddressVerify' or normalize-space()='Proceed without Address Validation']")
    private WebElement proceed_without_address_verification_button;

    // Mouse hover on delivered icon and extract the tool tip recipient name and invoice element
    @FindBy(xpath = "//div[@class='col-md-12']//h3")
    private WebElement tooltip_recipientandInvoiceNumber_On_MapView;

    @FindBy(xpath = "//select[@id='SortUnDeliveredOrders']")
    private WebElement sort_by_pending_deliveries_dropdown;

    @FindBy(xpath = "//a[@id='AdvanceDispatchSortOrder']")
    private WebElement sort_icon_on_pending_deliveries_section;

    //    Delivery Date field Element
    @FindBy(xpath = "//input[@id='advance-dispatch-dlv-date']")
    private WebElement deliverydate_on_Advancedispatch_page;

    @FindBy(xpath = "//div[@class='datepicker-days']")
    private WebElement deliverydate_datepicker_on_advancedispatch_page;

    @FindBy(css = "div[class='datepicker-days'] th[class='datepicker-switch']")
    private WebElement deliverydate_monthyear_on_advancedispatchpage;

    @FindBy(css = "div[class='datepicker-days'] th[class='next']")
    private WebElement deliverydate_nextbutton_on_advancedispatchpage;

    @FindBy(css = "div[class='datepicker-days'] th[class='prev']")
    private WebElement deliverydate_previousbutton_on_advancedispatchpage;

    @FindBy(xpath = "//div[@class='datepicker-days']//table//tbody//tr//td[@class='day' or @class='active day' or @class='today day' or @class='today active day']")
    private List<WebElement> listofdays_on_deliverydate_datepicker;

    @FindBy(xpath = "//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']")
    private WebElement datepicker_on_deliverydate_advancedispatch;

    //    Scan Order Textbox Element
    @FindBy(xpath = "//input[@id='advance-undispatch-invoicescan']")
    private WebElement scanOrder_Textbox;

    @FindBy(xpath = "//table[@id='advanceDispatchCurrentDispatch']")
    private WebElement trip_section_row1_added_order;

    //    Invoice Element in the Trip Section
    @FindBy(xpath = "(//span[@class='icon_div']/a)[1]")
    private WebElement invoice_in_trip_section;

    //    Name and Address Element in the Trip Section
    @FindBy(xpath = "//td[@class='tdCustomDesign']/span[1]")
    private WebElement Name_Address_OnDispatchTable_Row1_TripSection;

    //    Driver Dropdown Element in the Trip Section
    @FindBy(xpath = "//select[@id='ddlAdvanceDispatchDriver']")
    private WebElement selectDriver_Dropdown;

    // Advance Dispatch Save button in the Trip Section
    @FindBy(xpath = "//button[@id='btnAdvanceDispatchSave']")
    private WebElement DispatchSave_Button;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement toaster_message;

    @FindBy(xpath = "//div[@id='ModalSameAddressInvoice']//div[@class='modal-content']")
    private WebElement multiple_address_with_same_address_popup;

    //    Multiple Address with the Same Address pop-up title Element
    @FindBy(xpath = "//div[@id='ModalSameAddressInvoice']//div[@class='modal-content']//h2")
    private WebElement multiple_address_with_same_address_popup_header;

    //    Multiple order with same address List
    @FindBy(xpath = "//table/tbody/tr[@class='rowSameAddress']")
    private List<WebElement> multiple_order_with_same_address_list;

    //    Multiple order with same address List invoice data column Element
    @FindBy(xpath = "//table/tbody/tr[@class='rowSameAddress']/td//a")
    private List<WebElement> multiple_order_with_same_address_list_of_invoice;

    //  //div[@id='dvSameAddessInvoices']/table/tbody/tr/td[1]

    //    Multiple order with same address List invoice data column Element
    @FindBy(xpath = "//table/tbody/tr[@class='rowSameAddress']/td[3]/input")
    private List<WebElement> multiple_order_with_same_address_invoice_checkbox;

    @FindBy(xpath = "//input[@value='Verify Address']")
    private WebElement verify_button_on_invalid_address_popup;


//    Multiple Order with Same address confirm button Element

    @FindBy(xpath = "//input[@id='btnConfirmInvoices']")
    private WebElement multiple_order_with_same_address_confirm_button;

    @FindBy(xpath = "//table[@id='advanceDispatchCurrentDispatch']//td//span//a[contains(@title,'2061')]")
    private List<WebElement> invoice_number_on_tripsection;

    //    Active date click method in Quick Dispatch:
    @FindBy(xpath = "//td[@class='active day']")
    private WebElement current_date_in_calendar;

    @FindBy(xpath = "//tr[@class='list-element']/td/span/a[1]")
    private List<WebElement> advance_dispatch_invoice_no_lists;

    @FindBy(id = "divAdvanceDispatch")
    private WebElement Advance_Dispatch_Page_Element;

    @FindBy(xpath = "//tr[@class='rowCorrectedAddress']/td")
    private List<WebElement> invalid_address_invoice_td_element;

    @FindBy(xpath = "//tr[@class='rowCorrectedAddress']/td[1]")
    private List<WebElement> invalid_address_invoice_list;

    @FindBy(xpath = "//div[contains(@class,'pac-item')][1]")
    private WebElement google_map_suggestion_dropdown_first_option;

    @FindBy(xpath = "//div[@class='pac-container pac-logo hdpi']//div")
    private List<WebElement> google_map_suggestion_dropdown_list;

    @FindBy(xpath = "(//div[@class='pac-item'][1])")
    private WebElement google_suggestion_dropdown_value_one;

    @FindBy(xpath = "//div[@class='pac-item'][1]/span[@class='pac-item-query']")
    private WebElement google_suggestion_address_1;

    @FindBy(xpath = "//div[@class='pac-item'][1]/span[3]")
    private WebElement getGoogle_suggestion_address_2;

    @FindBy(xpath = "//input[@value='Verify Address']")
    private WebElement invalid_address_popup_verify_address_button;

    @FindBy(xpath = "//label[@id='divMessageModalFooter']")
    private WebElement invalid_address_label_on_popup;

    @FindBy(xpath = "//button[@id='btnAdvanceDispatchVerifyAddress']")
    private WebElement verify_address_button;

    @FindBy(xpath = "//span[text()='All Shop']/following::span[1]")
    private WebElement all_shops_toggle_button;

    @FindBy(xpath = "//button[@id='btnAdvanceDispatchAddPayrate']")
    private WebElement Add_Payrate_Advance_Dispatch_button;

    @FindBy(xpath = "//div[@id='ModalAdvanceDispatchSrc']/div/div/div/h4/span")
    private WebElement add_payrate_label;

    @FindBy(xpath = "(//div[@class='modal-footer']/button[2])[1]")
    private WebElement Add_payrate_close_button;

    @FindBy(xpath = "//div[@class='gm-style-iw-d']//h3")
    private WebElement map_icon_tooltip_on_map_view;
    //div[@class='gm-style-iw gm-style-iw-c']//h3
    @FindBy(xpath = "//select[@id='advance-dispatch-order-shop-selector']")
    private WebElement select_shop_dropdown_on_advance_dispatch_page;

    @FindBy(xpath = "//div[@role='button' and contains(@style, 'width: 30px; height: 50px;')]//img")
    private WebElement dispatched_icon_on_mapview;

    @FindBy(xpath = "//div[@class='SidebarWrap']/child::i")
    private WebElement left_arrow_on_pending_deliveries_section;

    @FindBy(xpath = "//div[contains(@class,'advanceDispatchSavedTripWrap')]/child::i")
    private WebElement right_arrow_on_trip_section;

    @FindBy(xpath = "//div[@id='kendoAdvanceUndispatchOrders']//thead//th//a[contains(text(),'Invoice')]")
    private WebElement invoice_column_label_header_pending_deliveries_section;

    @FindBy(xpath = "//div[@id='kendoAdvanceUndispatchOrders']//thead//th//a[contains(text(),'Invoice')]//span[contains(@class,'asc')]")
    private WebElement ascending_order_icon_on_invoice_label_pending_deliveries_section;

    @FindBy(xpath = "//div[@id='kendoAdvanceUndispatchOrders']//thead//th//a[contains(text(),'Invoice')]//span[contains(@class,'desc')]")
    private WebElement descending_order_icon_on_invoice_label_pending_deliveries_section;

    @FindBy(xpath = "//table[@id='advanceDispatchCurrentDispatch']//td[@class='tdCustomDesign']//span[@class='imgTooltip']//img")
    private WebElement dispatch_icon_on_trip_section;

    @FindBy(xpath = "//span[@class='ActiveRouteDetail']")
    private WebElement active_route_detail_on_trip_section;

    @FindBy(xpath = "//span[@id='lblAdvanceDispatchDateTime']")
    private WebElement timestamp_on_tripsection;

    @FindBy(xpath = "//button[@id='dropdownMenuRoutes']")
    private WebElement route_dropdown_on_tripsection;

    @FindBy(xpath = "//ul[@id='RouteDataWrap']//li//a[@id='newRoute']")
    private WebElement new_route_button_on_saved_trip_dropdown_on_tripsection;

    @FindBy(xpath = "//span[@id='lblAdvanceDispatchTripNo']")
    private WebElement trip_no_on_tripsection;

    @FindBy(xpath = "//button[@id='btnAdvanceDispatchCreateTrip']")
    private WebElement new_trip_button_below_tripsection;

    @FindBy(xpath = "//div[@class='sweet-alert showSweetAlert visible']")
    private WebElement confirmation_popup_on_add_new_tripsection;

    @FindBy(xpath = "//button[@class='cancel']")
    private WebElement no_button_on_confirmation_popup_on_add_new_tripsection;

    @FindBy(xpath = "//button[@class='confirm']")
    private WebElement yes_button_on_confirmation_popup_on_add_new_tripsection;

    @FindBy(xpath = "//button[@id='btnAdvanceDispatchRemotePrint']")
    private WebElement remote_print_button_on_tripsection;

    @FindBy(xpath = "//button[@id='btnAdvanceDispatchManualPrint']")
    private WebElement manual_print_button_on_tripsection;

    @FindBy(xpath = "//span[contains(text(),'Manual Print')]")
    private WebElement manual_print_popup_on_tripsection;

    @FindBy(xpath = "//button[@id='btnAdvanceDispatchAddPayrate']")
    private WebElement add_payrate_button_on_tripsection;

    @FindBy(xpath = "//div[@id='ModalAdvanceDispatchSrc']//div[@class='modal-content']")
    private WebElement add_payrate_popup_on_tripsection;

    @FindBy(xpath = "//span[contains(text(),'Add Pay Rate')]")
    private WebElement add_payrate_popup_on_tripsection_header;

    @FindBy(id = "ifrmAddPayrateAdvanceDispatch")
    private WebElement addpayrate_table_grid;

    @FindBy(xpath = "//table[@id='gvPayrateDetails']//td[2]")
    private WebElement payrate_invoice_number;

    @FindBy(xpath = "//table[@id='gvPayrateDetails']//td//span//input")
    private WebElement payable_checkbox_add_payrate_popup;

    @FindBy(xpath = "//table[@id='gvPayrateDetails']//td//input[@id='gvPayrateDetails_txtPayrate_0']")
    private WebElement driver_payrate_textbox_add_payrate_popup;

    @FindBy(xpath = "//table[@id='gvPayrateDetails']//td//input[@id='gvPayrateDetails_txtnonpayable_0']")
    private WebElement non_payable_textbox_add_payrate_popup;

    @FindBy(xpath = "//span[text()='Add Pay Rate']/ancestor::div[contains(@class,'modal-header')]//button[@class='close']/span[1]")
    private WebElement close_icon_on_add_payrate_popup;

    @FindBy(xpath = "(//button[text()='Update'])[1]")
    private WebElement update_button_on_add_payrate_popup;

    @FindBy(xpath = "//span[@id='lblMsg']")
    private WebElement success_message_on_add_payrate_popup;

    @FindBy(xpath = "//button[@id='ancPrintMap']")
    private WebElement print_map_button_on_tripsection;

    @FindBy(id = "btnAdvanceDispatchOptimize")
    private WebElement optimize_button_on_trip_section;

    @FindBy(id = "btnReverseCurrentRoute")
    private WebElement reverse_trip_button_on_trip_section;

    @FindBy(xpath = "//span[text()='Print Your Route']")
    private WebElement print_your_route_newtab_on_page;

    @FindBy(xpath = "//button[text()='Satellite']")
    private WebElement satellite_button_on_print_mapsection;

    @FindBy(xpath = "//label[text()='Labels']/preceding-sibling::span")
    private WebElement satellite_label_checkbox_on_print_mapsection;

    @FindBy(xpath = "//button[text()='Map']")
    private WebElement map_view_button_on_print_mapsection;

    @FindBy(xpath = "//label[text()='Terrain']/preceding-sibling::span")
    private WebElement terrain_checkbox_on_print_mapsection;

    @FindBy(xpath = "//div[@class='gm-style']")
    private WebElement map_element_on_print_mapsection;

    @FindBy(xpath = "//button[@class='gm-svpc']")
    private WebElement pegman_icon_on_print_mapsection;

    @FindBy(xpath = "(//button[@class='gm-control-active gm-fullscreen-control']/child::img)[1]")
    private WebElement fullscreen_icon_on_print_mapsection;

    @FindBy(xpath = "//label[text()='Print Driver Sheet: ']//following-sibling::button")
    private WebElement print_driver_sheet_print_button_on_print_mapsection;

    @FindBy(xpath = "//div[@id='advanceDispatchRoutesettings']//child::div//i")
    private WebElement optimize_configuration_settings_icon_on_tripsection;

    @FindBy(xpath = "//h4[text()='Optimize Configration']")
    private WebElement optimize_configuration_popup_header_on_tripsection;

    @FindBy(xpath = "//h5[text()='Avoid Toll Roads']//child::span")
    private WebElement avoid_toll_road_toogle_on_optimize_configuration_popup;

    @FindBy(xpath = "//h5[text()='Avoid Highways']//child::span")
    private WebElement avoid_highway_toogle_on_optimize_configuration_popup;

    @FindBy(xpath = "//h5[text()='Avoid Ferries']//child::span")
    private WebElement avoid_ferries_toogle_on_optimize_configuration_popup;

    @FindBy(xpath = "//h5[normalize-space()='Unit: Miles']//child::span")
    private WebElement unit_miles_toogle_on_optimize_configuration_popup;

    @FindBy(xpath = "//div[@id='OptimizerConfig']//button[text()='Close']")
    private WebElement close_button_on_optimize_configuration_popup;

    @FindBy(xpath = "//span[@id='lblTime']//b[2]")
    private WebElement total_distance_print_map_section;

    @FindBy(xpath = "//a[text()='Summarized By']")
    private WebElement summarized_by_label_column;

    @FindBy(xpath = "//div[@id='kendoAdvanceUndispatchOrdersSummary']//table//td[2]")
    private List<WebElement> list_of_summarizedby_city;

    @FindBy(xpath = "//div[@id='kendoAdvanceUndispatchOrdersSummary']//span[contains(@class,'asc')]")
    private WebElement ascending_icon_summary_section;

    @FindBy(xpath = "//div[@id='kendoAdvanceUndispatchOrdersSummary']//span[contains(@class,'desc')]")
    private WebElement descending_icon_summary_section;

    @FindBy(xpath = "//span[text()='Optimize Route']/following-sibling::span")
    private WebElement optimize_route_toogle_button_on_tripsection;

    @FindBy(xpath = "//span[text()='Round Trip']/following-sibling::span")
    private WebElement round_trip_toogle_button_on_tripsection;


    //    ******************  Advance Dispatch Page Functions  **************************** //

    //    Clicking on the Advance dispatch submenu from the Dispatch Button
    public void Click_on_Advance_Dispatch_sub_menu_in_Dashboard_Page() {
        HighlightElement(Advance_Dispatch_Sub_Menu);
        jsClick(Advance_Dispatch_Sub_Menu);
    }

    /**
     * Select the Map View on Advance Dispatch Page
     *
     * @param View If the view is selected it will return true else it will return false
     */
    public void Select_the_Map_View_on_Advance_Dispatch_Page(String View) {
        drop_Down(Advance_Dispatch_map_view_dropdown, View, "VisibleText", "Map view dropdown field on advance dispatch page");
    }

    /**
     * It retrieves the selected Map View on Advance Dispatch Page
     *
     * @return If the Map View is selected it will return value of the Map View else it will return null
     * @Author Balaji N
     */
    public String get_Selected_Map_View_on_Advance_Dispatch_Page() {
        return get_Selected_Option(Advance_Dispatch_map_view_dropdown, "Map view dropdown field value on advance dispatch page");
    }

    //  Returning Number of Pending Deliveries Count
    public int Return_Number_of_Pending_Deliveries_Count_Dispatch_Page() {
        return List_of_Map_View_Delivery_Icons.size();
    }

//    Verifying the Map View in the Advance Dispatch Map

    public String verify_the_map_view(String view, int pending_deliveries_count) {

        switch (view) {
            case "All Deliveries":
                if (List_of_Map_View_Delivery_Icons.size() >= pending_deliveries_count) {
                    System.out.println("Case 1 : All Deliveries");
                    return "All Deliveries";
                }
                break;
            case "Only Pending Deliveries":
                if (List_of_Map_View_Delivery_Icons.size() == pending_deliveries_count) {
                    System.out.println("Case 2 : Pending Deliveries");
                    return "Only Pending Deliveries";
                }
                break;
            default:
                System.out.println("Unknown view selected.");
                break;
        }
        System.out.println(view);
        return view;
    }

    /**
     * Verifying the invalid address pop-up is displaying or not
     *
     * @return If the invalid address pop-up is displayed, it returns true; otherwise, it returns false
     * @Author Sakrateesh R
     * Last Updated By: Balaji N - March 14,2025
     */
    public boolean verifying_invalid_address_pop_up_is_displaying_or_not() {
        delayWithGivenTime(2000);
        return isElementDisplayed(invalid_address_pop_up, "Invalid Address pop-up on Advance Dispatch Page");
    }

    //    Click method for the proceed without address verification button

    /**
     * Clicks the proceed without address verification button on invalid address pop-up on Advance Dispatch Page
     *
     * @Author Balaji N
     */
    public void click_proceed_without_address_button() {
        // fluentWait(proceed_without_address_verification_button, 30, 2);
        wait_For_Page_To_Be_Stable(getDriver());
        js_Click(proceed_without_address_verification_button, "proceed_without_address_validation_button");//proceed_without_address_verification_button.click();
        wait_For_Page_To_Be_Stable(getDriver());
    }

    //    ******************  End of the Functions  **************************** //

    public String get_tooltip_recipientandInvoiceNumber_On_MapView() {
        MouseHover(tooltip_recipientandInvoiceNumber_On_MapView);
        delayWithGivenTime(2000);
        HighlightElement(tooltip_recipientandInvoiceNumber_On_MapView);
        return tooltip_recipientandInvoiceNumber_On_MapView.getText();
    }

    public String Verify_Advance_DispatchPopup_IsDisplayed() {
        wait_For_Page_To_Be_Stable(getDriver());
        String PageName = getDriver().getTitle();
        wait_For_Page_To_Be_Stable(getDriver());
        return PageName;
    }

    /**
     * Select the delivery date on advance dispatch page
     *
     * @param dateToSelect
     */
    public void SetDeliveryDate(String dateToSelect) {
        // Parse the target date
        // Define formatter for input date format
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        LocalDate targetDate = LocalDate.parse(dateToSelect, inputFormatter); // Format: yyyy-MM-dd
        int targetDay = targetDate.getDayOfMonth();
        String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();

        // Open the datepicker
        deliverydate_on_Advancedispatch_page.click();

        // Navigate to the correct month and year
        while (true) {
            String displayedMonthYear = deliverydate_monthyear_on_advancedispatchpage.getText().trim();
            if (displayedMonthYear.equalsIgnoreCase(targetMonthYear)) {
                break;
            }
            deliverydate_nextbutton_on_advancedispatchpage.click();
        }

        for (WebElement dayElement : listofdays_on_deliverydate_datepicker) {
            if (dayElement.isEnabled() && dayElement.getText().equalsIgnoreCase(String.valueOf(targetDay))) {
                dayElement.click();
                break;
            }
        }
    }

    /**
     * It retrieves the displayed delivery date on advance dispatch
     *
     * @return If the delivery date is displayed it will return the delivery date else it will return null
     * @Author Balaji N
     */
    public String get_Displayed_Delivery_Date_On_Advance_Dispatch() {
        return getElementAttribute(deliverydate_on_Advancedispatch_page, "Delivery date picker on advance dispatch");
    }

    private YearMonth parseMonthYear(String monthYearText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
        return YearMonth.parse(monthYearText, formatter);
    }


    /**
     * Select the delivery date on advance dispatch page
     *
     * @param dateToSelect
     * @Author Balaji N
     */
//    public void Set_Delivery_Date_on_Advance_Dispatch(String dateToSelect) {
//        logPageLoad("Advance Dispatch Page → Delivery Date Picker field", () -> {
//            try {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//                LocalDate targetDate = LocalDate.parse(dateToSelect, formatter);
//
//                int targetDay = targetDate.getDayOfMonth();
//                String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();
//
//                click(deliverydate_on_Advancedispatch_page, "Delivery date picker on advance dispatch");
//
//                if (!deliverydate_datepicker_on_advancedispatch_page.isDisplayed()) {
//                    click(deliverydate_on_Advancedispatch_page, "Delivery date picker on advance dispatch");
//                }
//
//                while (true) {
//                    String displayedMonthYear = deliverydate_monthyear_on_advancedispatchpage.getText().trim();
//                    YearMonth displayedYM = parseMonthYear(displayedMonthYear);
//                    YearMonth targetYM = YearMonth.from(targetDate);
//
//                    if (displayedYM.equals(targetYM)) {
//                        break;
//                    } else if (displayedYM.isBefore(targetYM)) {
//                        js_Click(deliverydate_nextbutton_on_advancedispatchpage, "Next Button on Calendar");
//                    } else {
//                        js_Click(deliverydate_previousbutton_on_advancedispatchpage, "Previous Button on Calendar");
//                    }
//                }
//
//                for (WebElement dayElement : listofdays_on_deliverydate_datepicker) {
//                    if (dayElement.isEnabled() && dayElement.getText().equals(String.valueOf(targetDay))) {
//                        js_Click(dayElement, "Select the delivery date on the Advance Dispatch page");
//                        break;
//                    }
//                }
//                wait_For_Page_To_Be_Stable(getDriver());
//            } catch (Exception e) {
//                throw new RuntimeException("Unable to set the delivery date on advance dispatch page: " + e.getMessage());
//            }
//        });
//    }
//    public void Set_Delivery_Date_on_Advance_Dispatch(String dateToSelect) {
//        int attempts = 3;
//        boolean success = false;
//
//        while (attempts-- > 0 && !success) {
//            try {
//                logPageLoad("Advance Dispatch Page → Delivery Date Picker field", () -> {
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//                    LocalDate targetDate = LocalDate.parse(dateToSelect, formatter);
//
//                    int targetDay = targetDate.getDayOfMonth();
//                    String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();
//                    YearMonth targetYM = YearMonth.from(targetDate);
//
//                    click(deliverydate_on_Advancedispatch_page, "Click delivery date picker");
//                    if (!deliverydate_datepicker_on_advancedispatch_page.isDisplayed()) {
//                        click(deliverydate_on_Advancedispatch_page, "Retry click delivery date picker");
//                        delayWithGivenTime(500);
//                    }
//
//                    isElementDisplayed(deliverydate_datepicker_on_advancedispatch_page, "Delivery date picker on advance dispatch");
//                    while (true) {
//                        String displayedMonthYear = deliverydate_monthyear_on_advancedispatchpage.getText().trim();
//                        YearMonth displayedYM = parseMonthYear(displayedMonthYear);
//
//                        if (displayedYM.equals(targetYM)) break;
//
//                        if (displayedYM.isBefore(targetYM)) {
//                            js_Click(deliverydate_nextbutton_on_advancedispatchpage, "Next");
//                        } else {
//                            js_Click(deliverydate_previousbutton_on_advancedispatchpage, "Previous");
//                        }
//                    }
//
//                    for (WebElement dayElement : listofdays_on_deliverydate_datepicker) {
//                        if (dayElement.isEnabled() && dayElement.getText().equals(String.valueOf(targetDay))) {
//                            js_Click(dayElement, "Pick the delivery date");
//                            wait_For_Page_To_Be_Stable(getDriver());
//                            break;
//                        }
//                    }
//                });
//
//                success = true;
//
//            } catch (Exception e) {
//                if (attempts == 0) {
//                    String errorMessage = "❌ Failed to set delivery date [" + dateToSelect + "] after multiple attempts. Reason: " + e.getMessage();
//                    System.out.println(errorMessage);
//                    Allure.step(errorMessage);
//                    throw new RuntimeException(errorMessage, e);
//                } else {
//                    System.out.println("⚠️ Retry setting delivery date [" + dateToSelect + "], remaining attempts: " + attempts);
//                }
//            }
//        }
//    }

    private final By datePickerBy = By.xpath("//div[@class='datepicker-days']");

    public void Set_Delivery_Date_on_Advance_Dispatch(String dateToSelect) {
        int maxAttempts = 3;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                // Parse the target date
                LocalDate targetDate = LocalDate.parse(dateToSelect, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                int targetDay = targetDate.getDayOfMonth();
                String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();
                YearMonth targetYM = YearMonth.from(targetDate);

                // Step 1: Open date picker
                click(deliverydate_on_Advancedispatch_page, "Click delivery date picker");
                wait_For_Page_To_Be_Stable(getDriver());
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerBy));


                // Step 2: Navigate to correct month/year
                while (true) {
                    String displayedMonthYear = deliverydate_monthyear_on_advancedispatchpage.getText().trim();
                    YearMonth displayedYM = parseMonthYear(displayedMonthYear);

                    if (displayedYM.equals(targetYM)) {
                        break;
                    }

                    if (displayedYM.isBefore(targetYM)) {
                        js_Click(deliverydate_nextbutton_on_advancedispatchpage, "Next month");
                    } else {
                        js_Click(deliverydate_previousbutton_on_advancedispatchpage, "Previous month");
                    }
                }

                // Step 3: Select day
                boolean dayFound = false;
                for (WebElement dayElement : listofdays_on_deliverydate_datepicker) {
                    if (dayElement.isEnabled() && dayElement.getText().equals(String.valueOf(targetDay))) {
                        js_Click(dayElement, "Select day " + targetDay);
                        wait_For_Page_To_Be_Stable(getDriver());
                        dayFound = true;
                        break;
                    }
                }

                if (!dayFound) {
                    throw new NoSuchElementException("Day " + targetDay + " not found in calendar for " + targetMonthYear);
                }

                return; // ✅ SUCCESS — exit method

            } catch (Exception e) {
                if (attempt == maxAttempts) {
                    String userFriendlyError = String.format(
                            "❌ Could not set delivery date [%s] after %d attempts. Possible causes:\n" +
                                    " - Date picker did not open in time\n" +
                                    " - Locator changed or element missing\n" +
                                    " - Page refresh or animation delay\n" +
                                    " - Selected day not available in calendar",
                            dateToSelect, maxAttempts
                    );

                    Allure.step(userFriendlyError);
                    Allure.addAttachment("Technical Details", e.toString());
                    throw new RuntimeException(userFriendlyError, e);
                } else {
                    Allure.step("⚠️ Retry " + (attempt + 1) + " → Date picker not ready or failed to select date");
                    delayWithGivenTime(500);
                }
            }
        }
    }

//    public boolean is_Verify_Address_Button_Displayed_On_Advance_Dispatch() {
//        return isElementDisplayed(verify_address_button, "Verify Address button in Advance Dispatch");
//    }

    public boolean is_Verify_Address_Button_Displayed_On_Advance_Dispatch() {
        int retries = 2; // retry once more if stale
        while (retries >= 0) {
            try {
                boolean displayed = isElementDisplayed(
                        verify_address_button,
                        "Verify Address button in Advance Dispatch"
                );

                if (displayed) {
                    // Allure.step("✅ 'Verify Address' button is visible on Advance Dispatch page.");
                } else {
                    Allure.step("⚠️ 'Verify Address' button is NOT visible on Advance Dispatch page.");
                }
                return displayed;

            } catch (StaleElementReferenceException sere) {
                // handle stale and retry
                if (retries > 0) {
                    System.out.println("♻️ Retrying after stale element reference...");
                    retries--;
                } else {
                    String errorMessage = "❌ Failed to verify the 'Verify Address' button on Advance Dispatch.\n\n"
                            + "🔍 Possible reasons:\n"
                            + "  • The page refreshed or reloaded during check.\n"
                            + "  • The 'Verify Address' button was re-rendered (dynamic DOM).\n"
                            + "  • Locator became invalid after page update.\n\n"
                            + "👉 Please recheck the page flow or locator stability.";

                    Allure.step(errorMessage);
                    System.err.println(errorMessage);
                    return false;
                }
            } catch (Exception e) {
                String errorMessage = "❌ Unexpected error while checking 'Verify Address' button on Advance Dispatch.\n\n"
                        + "👉 Possible issue: " + e.getMessage();
                Allure.step(errorMessage);
                System.err.println(errorMessage);
                return false;
            }
        }
        return false; // fallback
    }


    public void Click_On_Advance_Dispatch_Verify_Button() {
        js_Click(verify_address_button, "Verify Address button in Advance Dispatch");
    }

    /**
     * Enter the invoice number on the scan order textbox field at trip section
     *
     * @param invoice_Number
     * @Author Balaji N
     */
    public void enter_InvoiceNumber_On_ScanOrder_Trip_Section(String invoice_Number) {
        clickAndType(scanOrder_Textbox, invoice_Number);
        delayWithGivenTime(2000);
        scanOrder_Textbox.sendKeys(Keys.ENTER);
    }


    public boolean Verify_Invoice_isAdded_To_TripSection() {
        HighlightElement(trip_section_row1_added_order);
        explicitWait(trip_section_row1_added_order);
        return trip_section_row1_added_order.isDisplayed();
    }

    //    Get invoice number from the Trip section
    public String get_invoice_no_from_the_trip_section() {
        HighlightElement(invoice_in_trip_section);
        return invoice_in_trip_section.getText();
    }

    //    verify the address and name in the trip section for the invoice
    public String get_NameAndAddress_On_TripSection() {
        HighlightElement(Name_Address_OnDispatchTable_Row1_TripSection);
        return Name_Address_OnDispatchTable_Row1_TripSection.getText().trim();
    }

    //    Selecting the driver from the driver dropdown field function
    public void Select_Driver(String drivername) {
        drop_Down(selectDriver_Dropdown, drivername, "VisibleText", "Select driver dropdown field on advance dispatch page");
    }

    /**
     * It retrieves the selected driver name on advance dispatch
     *
     * @return
     * @Author Balaji N
     */
    public String get_selected_drivername() {
        return get_Selected_Option(selectDriver_Dropdown, "Select the driver dropdown field on advance dispatch page");
    }

    //    Clickin on the Save button in the trip section
    public void Click_DispatchSave_Button() {
        js_Click(DispatchSave_Button, "Save button on trip assigned section");
    }


    /**
     * Verify whether the success toaster message displayed on advance dispatch page
     *
     * @return
     * @Author Balaji N
     */
    public String Validate_Success_Toaster_message_Appears() {
        return getElementText(toaster_message, "Success Toaster Message on advance dispatch page");
    }

// Checking the Multiple Order with the same address pop-up is displaying or not.

    /**
     * Verify whether multiple order with same address popup is displayed or not
     *
     * @return If the multiple order with same address popup is displayed return true else false
     * @Author Balaji N
     */
    public boolean multiple_order_with_same_address_popup_is_displayed() {
        wait_For_Page_To_Be_Stable(getDriver());
        isElementDisplayed(multiple_address_with_same_address_popup, "Multiple Address with same address popup");
        return isElementDisplayed(multiple_address_with_same_address_popup_header, "Multiple Address with same address popup header");
    }

    public void uncheck_the_ivoice_remaining_the_invoice_required_in_multiple_invoices_with_same_address(String invoiceNumber) {
        for (int i = 0; i < multiple_order_with_same_address_list_of_invoice.size(); i++) {
            if (!multiple_order_with_same_address_list_of_invoice.get(i).getText().equals(invoiceNumber)) {
                multiple_order_with_same_address_invoice_checkbox.get(i).click();
            }
        }
    }

    /**
     * Clicks the Confirm button on Multiple Order with Same Address Found Popup
     *
     * @Author: Balaji N
     */
    public void Click_confirm_button_in_multiple_order_with_same_address_popup() {
        if (isElementDisplayed(multiple_order_with_same_address_confirm_button, "Confirm button on Multiple Order with Same Address Found Popup")) {
            js_Click(multiple_order_with_same_address_confirm_button, "Confirm button on Multiple Order with Same Address Found Popup");
        }
    }

    /**
     * Verify whether the invoice number is displaying on the trip section
     *
     * @param invoice
     * @return
     */
   /* public boolean verify_invoice_is_displaying_in_the_trip_section(String invoice) {
        try {
            explicitWait(invoice_number_on_tripsection);
            String actual_invoicenumber = getElementText(invoice_number_on_tripsection, "Invoice Number on trip section");
            if (actual_invoicenumber.equalsIgnoreCase(invoice)) {
                return true;
            }
        } catch (Exception e) {
            printError(invoice_number_on_tripsection, "Invoice Number on trip section", "No Such Exception", e);
        }
        return false;
    }*/

    /**
     * Validate whether the invoice is displaying in the trip section or not
     *
     * @param invoice
     * @return If the invoice is displayed then it will return true else it will return false
     * @Author Balaji N
     */
    public boolean validate_the_invoice_is_displaying_in_the_trip_section(String invoiceNumber) {
        // wait_For_Page_To_Be_Stable(getDriver());
        int maxRetries = 3;
        int attempts = 0;

        while (attempts < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
                By invoiceLocator = By.xpath("//table[@id='advanceDispatchCurrentDispatch']//td//span//a[text()='" + invoiceNumber + "']");
                WebElement invoiceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(invoiceLocator));

                return isElementDisplayed(invoiceElement, "Invoice Number - placed order on trip section");

            } catch (StaleElementReferenceException e) {
                attempts++;
                System.out.println("StaleElementReferenceException caught. Retrying... Attempt " + attempts);
                // Small wait before retrying
                delayWithGivenTime(1000);
            } catch (TimeoutException e) {
                throw new RuntimeException("Timeout: Invoice number '" + invoiceNumber + "' not found in the trip section.", e);
            } catch (Exception e) {
                throw new RuntimeException("Error validating invoice in trip section: " + invoiceNumber + ". " + e.getMessage(), e);
            }
        }

        throw new RuntimeException("StaleElementReferenceException: Failed to validate invoice after retries for invoice number '" + invoiceNumber + "'.");

    }

    /**
     * This method verifies whether the invoice is displaying or not
     *
     * @param invoice
     * @return : If the invoice is displayed then it will return true else it will return false
     * @Description : This method verify whether the invoice is displaying in the trip section or not in advance dispatch page
     * @Author : Sakrateesh R
     */
    public boolean verify_whether_invoice_is_displaying_in_the_advance_dispatch_trip_section(String invoice) {

        for (WebElement trip_invoice : advance_dispatch_invoice_no_lists) {
            try {
                if (trip_invoice.getText().equals(invoice)) {
                    return true;
                }
            } catch (NoSuchElementException e) {
                printError(trip_invoice, "Invoice List in Advance Dispatch", "No Such Exception", e);
            }
        }

        return false;
    }

    /**
     * This method verify whether the Advance Dispatch page is displaying or not
     *
     * @return : if the advance dispatch page is displayed it will return true else false
     * @Description: This method verify whether the Advance Dspatch page is displaying or not
     * @Author: Sakrateesh R
     */
    public boolean verify_Advance_Dispatch_Page_IsDisplayed() {
        return is_Element_Displayed(Advance_Dispatch_Page_Element, "Advance Dispatch Page");
    }

    public void click_Verify_Address_Button_On_Invalid_Address_Popup() {
        Click(verify_button_on_invalid_address_popup, "Verify button");
    }

    /**
     * Clears the invalid address and enters the valid address in the Invalid Address pop-up
     *
     * @Author Balaji N
     */
    public void clear_Invalid_Address_On_Invalid_Address_popup() {
        List<WebElement> addressRows = getDriver().findElements(By.xpath("//tr[@class='rowCorrectedAddress']/td[2]//input"));
        for (WebElement inputField : addressRows) {
            inputField.click();
            // Clear existing text
            delayWithGivenTime(500);
            inputField.clear();
            delayWithGivenTime(500);
            inputField.sendKeys("107 Walker Rd, Farmington, MO 63640");
            delayWithGivenTime(500);

            List<WebElement> suggestions = getDriver().findElements(By.cssSelector(".pac-container .pac-item"));
            if (!suggestions.isEmpty()) {
                HighlightElement(suggestions.get(0));
                suggestions.get(0).click(); // Clicks the first suggestion
            }
        }

        delayWithGivenTime(2000);
        click_Verify_Address_Button_On_Invalid_Address_Popup();
    }


    /**
     * This method verify whether the google suggestion is displaying or not
     *
     * @param invoice
     * @param textToEnter
     * @return : If google suggestion pop-up is displayed it will return true else it will return false
     * @Description : This method verify whether the google suggestion is displaying or not in the Invalid Address pop-up
     * @Author: Sakrateesh R
     */
    public boolean Verify_Google_Suggestion_Dropdown_IsDisplayed(String invoice, String textToEnter) {
        int maxRetries = 3;
        int attempt = 0;
        By addressInputLocator = By.xpath("//div[@id='dvAddressVerificationNew']//table//tr//td[text()='" + invoice + "']/following-sibling::td//input");

        while (attempt < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                WebElement inputField = getDriver().findElement(addressInputLocator);
                js_Click(inputField, "Invalid Address pop-up Full Address textbox field");

                inputField.clear();
                inputField.sendKeys(textToEnter);
                delayWithGivenTime(2000);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", google_map_suggestion_dropdown_first_option);
                return isElementDisplayed(google_map_suggestion_dropdown_first_option, "Google Suggestion Dropdown on invalid address popup");
            } catch (StaleElementReferenceException | TimeoutException | ElementNotInteractableException e) {
                System.err.println("[Retry " + (attempt + 1) + "/" + maxRetries + "] Retrying due to: " + e.getClass().getSimpleName());
            } catch (Exception e) {
                throw new RuntimeException("❌ Error verifying Google suggestion dropdown for invoice [" + invoice + "]: " + e.getMessage(), e);
            }

            delayWithGivenTime(1000); // Small wait before retry
            attempt++;
        }

        return false;
    }


    /**
     * This method is used to click the Google suggestion dropdown value [1]
     *
     * @param invoice
     * @param textToEnter
     * @Author: Sakrateesh R Last updated by: Balaji N - 24/07/2025
     */
    public void Click_Address_On_Google_Sugggestion(String invoice, String textToEnter) {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                // Locate the invoice-specific address input each time to avoid stale reference
                By inputLocator = By.xpath("//div[@id='dvAddressVerificationNew']//table//tr//td[text()='" + invoice + "']/following-sibling::td//input");
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));

                click(inputField, "Address input field on invalid address popup");
                delayWithGivenTime(500);

                js_Clear(inputField, "Clearing address field");
                inputField.clear();
                inputField.sendKeys(textToEnter);
                delayWithGivenTime(2000);

                // Wait for and click first suggestion
                By suggestionItemLocator = By.xpath("//div[@class='pac-container pac-logo hdpi']//div[@class='pac-item'][1]");
                WebElement suggestionOption = wait.until(ExpectedConditions.elementToBeClickable(suggestionItemLocator));

                //  new Actions(getDriver()).moveToElement(suggestionOption).perform();
                // Highlight_Element(suggestionOption, "First suggestion in Google dropdown");
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", suggestionOption);

                click(suggestionOption, "First suggestion in Google dropdown on invalid address popup");

                System.out.println("✅ Clicked suggestion successfully for invoice: " + invoice);
                return; // Success, exit the loop

            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                System.err.println("⚠️ [Retry " + (attempt + 1) + "/" + maxRetries + "] Retrying due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                throw new RuntimeException("❌ Unexpected failure on attempt " + (attempt + 1) + " for invoice [" + invoice + "]: " + e.getMessage(), e);
            }

            attempt++;
        }

        throw new RuntimeException("❌ Failed to click Google suggestion for invoice [" + invoice + "] after " + maxRetries + " attempts.");
    }


    public void verify_Other_Addresses(String currentInvoice) {
        try {
            List<WebElement> allAddressInputs = getDriver().findElements(
                    By.xpath("//div[@id='dvAddressVerificationNew']//table//tr//td/following-sibling::td//input")
            );

            WebElement currentInvoiceAddress = getDriver().findElement(
                    By.xpath("//div[@id='dvAddressVerificationNew']//table//tr//td[text()='" + currentInvoice + "']/following-sibling::td//input")
            );

            for (int i = 0; i < allAddressInputs.size(); i++) {
                int attempts = 0;
                int maxAttempts = 3;
                boolean isSuccessful = false;

                while (attempts < maxAttempts) {
                    try {
                        WebElement addressInput = allAddressInputs.get(i);

                        if (addressInput.equals(currentInvoiceAddress)) {
                            break;
                        }

                        delayWithGivenTime(500);
                        click(addressInput, "Other invoice address field");

                        delayWithGivenTime(500);
                        js_Clear(addressInput, "Other invoice address field");

                        addressInput.clear();
                        addressInput.sendKeys("14");
                        delayWithGivenTime(800);

                        By firstSuggestionLocator = By.xpath("//div[@class='pac-container pac-logo hdpi']//div[@class='pac-item'][1]");
                        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

                        WebElement firstSuggestion = wait.until(ExpectedConditions.elementToBeClickable(firstSuggestionLocator));
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", firstSuggestion);
                        click(firstSuggestion, "First autosuggest item");
                        delayWithGivenTime(800);

                        isSuccessful = true;
                        break; // Exit retry loop on success

                    } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                        attempts++;
                        System.err.println("⚠️ Attempt " + attempts + " failed on index [" + i + "] due to: " + e.getClass().getSimpleName());
                        delayWithGivenTime(1000);
                        if (attempts == maxAttempts) {
                            System.err.println("❌ Max retry reached for address input at index [" + i + "]");
                        }
                    } catch (Exception e) {
                        System.err.println("❌ Unexpected error on address input at index [" + i + "]: " + e.getMessage());
                        break; // Stop retries on unexpected error
                    }
                }

                if (!isSuccessful) {
                    System.err.println("⚠️ Skipped address input at index [" + i + "] after retries.");
                }
            }

            System.out.println("✅ All other address fields handled successfully.");

        } catch (Exception e) {
            throw new RuntimeException("❌ Error during address verification: " + e.getMessage(), e);
        }
    }


    public boolean are_Other_Addresses_Displayed(String currentInvoice) {
        try {
            List<WebElement> allAddressInputs = getDriver().findElements(
                    By.xpath("//div[@id='dvAddressVerificationNew']//table//tr//td/following-sibling::td//input")
            );

            WebElement currentInvoiceAddress = getDriver().findElement(
                    By.xpath("//div[@id='dvAddressVerificationNew']//table//tr//td[text()='" + currentInvoice + "']/following-sibling::td//input")
            );

            for (WebElement addressInput : allAddressInputs) {
                if (!addressInput.equals(currentInvoiceAddress)) {
                    if (addressInput.isDisplayed() && addressInput.isEnabled()) {
                        System.out.println("✅ Other address field is displayed");
                        return true; // At least one other address field is visible
                    }
                }
            }

            return false; // No other address fields are displayed

        } catch (Exception e) {
            System.err.println("❌ Error checking other address fields: " + e.getMessage());
            return false;
        }
    }


    public boolean is_Invalid_Address_Displayed_For_Invoice(String invoice) {
        wait_For_Page_To_Be_Stable(getDriver());
        try {
            By invoicenumber = By.xpath("//div[@id='dvAddressVerificationNew']//table//tr//td[text()='" + invoice + "']");
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(invoicenumber));
            WebElement invoiceele = getDriver().findElement(invoicenumber);
            return false;
        } catch (TimeoutException | NoSuchElementException e) {
            return true;
        }
    }


    /**
     * This method is used to get the text of the Google suggestion dropdown.
     *
     * @param invoice
     * @param textToEnter
     * @return : Return the first value in the dropdown list
     * @Author : Sakrateesh R
     */
    public String Get_Google_Suggestion_Dropdown_Value_One(String invoice, String textToEnter) {
        By inputLocator = By.xpath("//div[@id='dvAddressVerificationNew']//table//tr//td[text()='" + invoice + "']/following-sibling::td//input");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement inputField = getDriver().findElement(inputLocator);

        click(inputField, "Invalid Address pop-up Full Address textbox field");
        inputField.clear();
        inputField.sendKeys(textToEnter);

        delayWithGivenTime(2000);
        // Wait for suggestions to load
        By suggestionItemLocator = By.xpath("//div[@class='pac-container pac-logo hdpi']//div[@class='pac-item']");
        List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(suggestionItemLocator));

        if (!suggestions.isEmpty()) {
            //  Highlight_Element(suggestions.get(0), "First suggestion in Google dropdown on invalid address popup");
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", suggestions.get(0));

            click(suggestions.get(0), "First suggestion in Google dropdown on invalid address popup");
        } else {
            System.err.println("No suggestions found for address: " + textToEnter);
        }

        return getElementAttribute(suggestions.get(0), "First suggestion in Google dropdown on invalid address popup");
    }

    public String get_Actual_Selected_Address(String invoice) {
        By inputLocator = By.xpath("//div[@id='dvAddressVerificationNew']//table//tr//td[text()='" + invoice + "']/following-sibling::td//input");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement inputField = getDriver().findElement(inputLocator);
        return getElementAttribute(inputField, "Invalid Address for the invoice: " + invoice);
    }

    public String Get_Invalid_Address_From_Input_Field(String invoice) {
        try {
            for (WebElement invoiceElement : invalid_address_invoice_td_element) {
//                System.out.println("Inside the For Loop");
                if (invoiceElement.getText().contains(invoice)) {
//                    System.out.println("inside the If condition");
                    // Locate the next sibling <td> and find the input field inside it
                    WebElement nextTd = invoiceElement.findElement(By.xpath("./following-sibling::td[1]"));

                    // Locate the <input> inside the next <td>
                    WebElement inputField = nextTd.findElement(By.tagName("input"));

                    // Clear and enter text
                    return inputField.getAttribute("value");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Address not found in the input";
    }

    /**
     * This method is used to click on the Verify Address button in the Invalid address popup
     *
     * @Author: Sakrateesh R
     */
    public void Click_Verify_Address_Button_In_Invalid_Address_Popup() {
        wait_For_Page_To_Be_Stable(getDriver());
        click(invalid_address_popup_verify_address_button, "Verify Address button in the Invalid Address pop-up");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    /**
     * Verifying whether the Verify Address button is displayed in the Invalid Address pop-up
     *
     * @return If the Verify Address button is displayed, it returns true; otherwise, it returns false
     * @Author: Balaji N
     */
    public boolean is_Verify_Address_Button_Displayed_In_Invalid_Address_Popup() {
        return isElementDisplayed(invalid_address_popup_verify_address_button, "Verify Address button in the Invalid Address pop-up");
    }

    public String get_Invalid_Address_Popup_Label() {
        return getElementText(invalid_address_label_on_popup, "Invalid Address pop-up label");
    }

    /**
     * Enters the address in the Full Address textbox field in the Invalid Address pop-up
     *
     * @param invoice
     * @param fulladdress
     * @Author Balaji N
     */
    public void Enter_Full_Address_In_Invalid_Address_Popup(String invoice, String fulladdress) {
        WebElement address = getDriver().findElement(By.xpath("//tr[@class='rowCorrectedAddress']/child::td[contains(text(),'" + invoice + "')]/following::td/child::input[contains(@class,'txtGoogleOriginalAddress')]"));
        address.clear();
        ClickAndType(address, fulladdress, "Invalid Address pop-up Full Address textbox");
    }

    /**
     * This method will verify whether the invoice is displaying in the Invalid Address pop-up
     *
     * @param invoice
     * @return : if the invoice is displayed then it will return true else it will return false
     * @Author: Sakrateesh R
     */
    public boolean Verify_Invoice_Is_Displayed_In_Invalid_Address_Popup(String invoice) {

        try {
            for (WebElement invoiceElement : invalid_address_invoice_td_element) {

                if (invoiceElement.getText().contains(invoice)) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method is used to verify whether only one invoice is displaying or not, if more invoices displayed it will remove the invoices untill it become one invoice.
     *
     * @param invoice
     * @param text
     * @return : return true if only one invoice is displayed
     * @Author : Sakrateesh R
     */
    public boolean Verify_Only_One_Invoice_Is_Displayed_In_Invalid_Address_Popup(String invoice, String text) {
        try {
            for (WebElement invoiceElement : invalid_address_invoice_list) {
                System.out.println("Checking invoice: " + invoice);

                if (!invoiceElement.getText().equals(invoice) && invalid_address_invoice_list.size() > 1) {
                    HighlightElement(invoiceElement);
                    WebElement inputField = invoiceElement.findElement(By.xpath("./following-sibling::td[1]//input"));

                    inputField.clear();
                    inputField.click();
                    inputField.sendKeys(text);

                    delayWithGivenTime(1500);
                    google_suggestion_dropdown_value_one.click();
                    delayWithGivenTime(1500);
                    Click_Verify_Address_Button_In_Invalid_Address_Popup();
                    delayWithGivenTime(1500);
                } else {
                    return true;
                }
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Stale element encountered, retrying...");
            return Verify_Only_One_Invoice_Is_Displayed_In_Invalid_Address_Popup(invoice, text); // Recursion to retry
        }
        return false;
    }

    public String Verify_The_Address_In_Pending_Deliveries_Section(String invoice) {
        try {

            for (WebElement invoiceElement : pending_deliveries_invoice_list) {
                WebElement invoiceno = invoiceElement.findElement(By.xpath("./span/a"));
                if (invoiceno.getText().contains(invoice)) {

                    // Locate the next sibling <td> and find the input field inside it
                    WebElement nextTd = invoiceElement.findElement(By.xpath("./following-sibling::td[1]"));
                    String fullText = nextTd.getText();  // Get the full text
                    Pattern pattern = Pattern.compile("\\d+\\s+.*"); // Regex to match starting with a number
                    Matcher matcher = pattern.matcher(fullText);
                    if (matcher.find()) {
                        String address = matcher.group();
//                        System.out.println(address);
                        return address;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Address not displayed";
    }

    /**
     * Verify whether the invoice is displayed in the pending deliveries section - Advance dispatch page
     *
     * @param invoicenumber
     * @return If the pending deliveries invoice is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean verify_pending_deliveries_invoice_isDisplayed(String invoicenumber) {
        wait_For_Page_To_Be_Stable(getDriver());
        By invoiceLocator = By.xpath("//div[@id='kendoAdvanceUndispatchOrders']//table/tbody/tr/td//a[text()='" + invoicenumber + "']");

        try {
            WebElement invoiceElement = getDriver().findElement(invoiceLocator);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
            return isElementDisplayed(invoiceElement, "Pending Deliveries Invoice: " + invoicenumber);

        } catch (StaleElementReferenceException | TimeoutException ex) {
            System.err.println("⚠️ Element became stale or timed out for invoice [" + invoicenumber + "]. Refetching and revalidating...");

            try {
                WebElement invoiceElementRetry = getDriver().findElement(invoiceLocator);
                explicitWait(invoiceElementRetry);
                return isElementDisplayed(invoiceElementRetry, "Pending Deliveries Invoice (retry): " + invoicenumber);

            } catch (Exception retryEx) {
                System.err.println("❌ Invoice [" + invoicenumber + "] not displayed even after refetch due to: " + retryEx.getMessage());
                return false;
            }

        } catch (Exception e) {
            System.err.println("❌ Unexpected error while verifying invoice [" + invoicenumber + "]: " + e.getMessage());
            return false;
        }
    }
/*    public VerificationResult verify_pending_deliveries_invoice_isDisplayed(String invoicenumber) {
        wait_For_Page_To_Be_Stable(getDriver());
        By invoiceLocator = By.xpath("//div[@id='kendoAdvanceUndispatchOrders']//table/tbody/tr/td//a[text()='" + invoicenumber + "']");

        try {
            WebElement invoiceElement = getDriver().findElement(invoiceLocator);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
            if (isElementDisplayed(invoiceElement, "Pending Deliveries Invoice: " + invoicenumber)) {
                return new VerificationResult(true, "✅ Invoice " + invoicenumber + " is displayed.");
            } else {
                return new VerificationResult(false, "❌ Invoice " + invoicenumber + " was not visible even though present in DOM.");
            }

        } catch (StaleElementReferenceException | TimeoutException ex) {
            String retryMessage = "⚠️ Element became stale or timed out for invoice [" + invoicenumber + "]. Refetching and revalidating...";
            System.err.println(retryMessage);

            try {
                WebElement invoiceElementRetry = getDriver().findElement(invoiceLocator);
                explicitWait(invoiceElementRetry);
                if (isElementDisplayed(invoiceElementRetry, "Pending Deliveries Invoice (retry): " + invoicenumber)) {
                    return new VerificationResult(true, "✅ Invoice " + invoicenumber + " is displayed after retry.");
                } else {
                    return new VerificationResult(false, "❌ Invoice " + invoicenumber + " not visible after retry.");
                }

            } catch (Exception retryEx) {
                String msg = "❌ Invoice [" + invoicenumber + "] not displayed even after retry due to: " + retryEx.getMessage();
                System.err.println(msg);
                return new VerificationResult(false, msg);
            }

        } catch (Exception e) {
            String msg = "❌ Unexpected error while verifying invoice [" + invoicenumber + "]: " + e.getMessage();
            System.err.println(msg);
            return new VerificationResult(false, msg);
        }
    }*/


    public boolean verify_pending_deliveries_invoice_isNotDisplayed(String invoicenumber) {
        wait_For_Page_To_Be_Stable(getDriver());

        try {
            if (pending_deliveries_invoices_list == null || pending_deliveries_invoices_list.isEmpty()) {
                System.out.println("✅ Invoice list is empty or not initialized. So invoice is NOT displayed.");
                return true;
            }

            for (WebElement invoiceElement : pending_deliveries_invoices_list) {
                fluentWait(invoiceElement, 60, 3);
                String text = invoiceElement.getText().trim();
                System.out.println("🔍 Checking invoice: " + text);

                if (text.equalsIgnoreCase(invoicenumber)) {
                    System.err.println("❌ Invoice [" + invoicenumber + "] is unexpectedly displayed in Pending Deliveries.");
                    return false;
                }
            }

            System.out.println("✅ Invoice [" + invoicenumber + "] is NOT displayed in Pending Deliveries.");
            return true;

        } catch (Exception e) {
            System.err.println("❌ Exception while verifying invoice NOT displayed: " + e.getMessage());
            throw new RuntimeException("Exception while verifying invoice not displayed: " + e.getMessage());
        }
    }


    public void click_pending_deliveries_invoice_isDisplayed(String invoicenumber) {
        logPageLoad("Advance Dispatch Page → Clicks Invoice on pending deliveries section", () -> {
            wait_For_Page_To_Be_Stable(getDriver());
            try {
                for (WebElement invoiceElement : pending_deliveries_invoices_list) {
                    fluentWait(invoiceElement, 60, 3);
                    if (invoiceElement.getText().equalsIgnoreCase(invoicenumber)) {
                        click(invoiceElement, "Invoice number on pending deliveries - Advance dispatch page");
                    }
                }
            } catch (Exception e) {
                System.err.println("❌ Exception while clicking invoice on pending deliveries section on Advance dispatch page: " + e.getMessage());
            }
        });
    }

    /**
     * Verify whether the summary section is displayed - Advance dispatch page
     *
     * @return : If the summary section is displayed then it will return true else it will return false
     * @Author Balaji N
     */
    public boolean is_summary_section_advance_dispatch_page_Displayed() {
        return is_Element_Displayed(summary_section_advance_dispatch_page, "Summary section - Advance dispatch page");
    }

    /**
     * Clicks the recipient city on the summary section - Advance dispatch page
     *
     * @param recipientcity
     * @Author Balaji N
     */
    public void click_Summarized_Recipient_City_Advance_Dispatch(String recipientcity) {
        By recipient_city = By.xpath("//div[@id='kendoAdvanceUndispatchOrdersSummary']//div[2]//table//td[text()='" + recipientcity + "']");
        WebElement city = getDriver().findElement(recipient_city);
        fluentWait(city, 60, 3);
        click(city, "Summarized Recipient City - Summary Section in Advance dispatch page");
    }


    /**
     * Verify whether the invoice is displayed in the pending deliveries section - Advance dispatch page
     *
     * @param invoicenumber
     * @return If the invoice is displayed, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean is_Invoice_Number_Displayed_In_Pending_Deliveries_Section(String invoicenumber) {
        By invoiceLocator = By.xpath("//div[@id='kendoAdvanceUndispatchOrders']//div[2]//td//span//a[contains(text(),'" + invoicenumber + "')]");

        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(120));
            wait.pollingEvery(Duration.ofSeconds(1));
            WebElement invoiceElement = getDriver().findElement(invoiceLocator);
            wait.until(ExpectedConditions.visibilityOf(invoiceElement));
            return isElementDisplayed(invoiceElement, "Invoice number on pending deliveries - Advance dispatch page");

        } catch (StaleElementReferenceException | TimeoutException ex) {
            System.err.println("⚠️ Invoice [" + invoicenumber + "] element became stale or timed out. Retrying by refetching...");

            try {
                WebDriverWait waitRetry = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                waitRetry.pollingEvery(Duration.ofSeconds(1));
                WebElement invoiceElementRetry = getDriver().findElement(invoiceLocator);
                waitRetry.until(ExpectedConditions.visibilityOf(invoiceElementRetry));
                return isElementDisplayed(invoiceElementRetry, "Invoice number on pending deliveries - Advance dispatch page (retry)");
            } catch (Exception retryEx) {
                System.err.println("❌ Retry failed: Invoice [" + invoicenumber + "] not visible even after refetch. Reason: " + retryEx.getMessage());
                return false;
            }

        } catch (Exception e) {
            System.err.println("❌ Unexpected error while checking invoice [" + invoicenumber + "]: " + e.getMessage());
            return false;
        }
    }


    /**
     * Clicks the invoice number hyperlink on the pending deliveries section - Advance dispatch page
     *
     * @param invoicenumber
     * @Author Balaji N
     */
    public void click_Invoice_Number_Hyperlink_On_Pending_DeliveriesSection(String invoicenumber) {
        try {
            WebElement invoice = getDriver().findElement(By.xpath("//div[@id='kendoAdvanceUndispatchOrders']//div[2]//td//span//a[contains(text(),'" + invoicenumber + "')]"));
            js_Click(invoice, "Invoice number on pending deliveries - Advance dispatch page");
        } catch (Exception e) {
            throw new RuntimeException("Unable to click on the invoice number: " + invoicenumber + " hyperlink on pending deliveries section. An error occurred: " + e.getMessage());
        }
    }


    /**
     * Clicks the invoice number on the pending deliveries section - Advance dispatch page
     *
     * @param invoicenumber
     * @Author Balaji N
     */
    public void Click_pending_deliveries_invoice(String invoicenumber) {
        int maxRetries = 3;
        int attempt = 0;
        boolean isClicked = false;

        By addressCellLocator = By.xpath("//div[@id='kendoAdvanceUndispatchOrders']//td//a[text()='" + invoicenumber + "']/following::td[1]");

        while (attempt < maxRetries && !isClicked) {
            try {
                delayWithGivenTime(1000);
                wait_For_Page_To_Be_Stable(getDriver());

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                WebElement addressCell = wait.until(ExpectedConditions.elementToBeClickable(addressCellLocator));

                HighlightElement(addressCell);
                addressCell.click();

                //Allure.step("✅ Clicked on address cell of invoice: " + invoicenumber + " in Advance Dispatch - Pending Deliveries section.");
                wait_For_Page_To_Be_Stable(getDriver());
                isClicked = true;

            } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException e) {
                attempt++;
                Allure.step("⚠️ Attempt " + attempt + ": Failed to click on invoice [" + invoicenumber + "] due to: " + e.getClass().getSimpleName());
                System.err.println("Retry " + attempt + " - Click failed due to " + e.getClass().getSimpleName() + ": " + e.getMessage());

            } catch (Exception e) {
                Allure.step("❌ Unexpected error while clicking invoice [" + invoicenumber + "]: " + e.getMessage());
                throw new RuntimeException("Failed to click invoice '" + invoicenumber + "' in Pending Deliveries section: " + e.getMessage(), e);
            }
        }

        if (!isClicked) {
            String msg = "❌ Failed to click on address cell for invoice '" + invoicenumber + "' in Pending Deliveries section after " + maxRetries + " attempts.";
            Allure.step(msg);
            throw new RuntimeException(msg);
        }
    }


    /**
     * Verify whether the pending delivery address for respective invoice order
     *
     * @param invoicenumber
     * @Author Balaji N
     */
    public void verify_Pending_Delivery_Address_For_Respective_order(String invoicenumber) {
        WebElement address = getDriver().findElement(By.xpath("//div[@id='kendoAdvanceUndispatchOrders']//td//span//a[contains(text(),'" + invoicenumber + "')]/following::td[1]//span"));
        getElementText(address, "Address label on pending delivery section");
    }

    /**
     * Clicks the invoice number hyperlink on the pending deliveries section - Advance dispatch page
     *
     * @param invoicenumber
     * @Author Balaji N
     */
    public void click_Invoice_Number_Hyperlink_On_Pending_Deliveries_Section(String invoicenumber) {
        WebElement invoice = getDriver().findElement(By.xpath("//div[@id='kendoAdvanceUndispatchOrders']//td//span//a[contains(text(),'" + invoicenumber + "')]"));
        click(invoice, "Invoice number on pending deliveries - Advance dispatch page");
        delayWithGivenTime(2000);
        switchToWindowbyIndex(1);
    }

    /**
     * Clicks the invoice number on the pending deliveries section - Advance dispatch page
     *
     * @param invoicenumber
     * @return
     * @Author Balaji N
     */
    public String get_pending_deliveries_invoice(String invoicenumber) {
        WebElement address = getDriver().findElement(By.xpath("//div[@id='kendoAdvanceUndispatchOrders']/div[2]/table/tbody/tr/td//span//a[contains(text(),'" + invoicenumber + "')]/following::td[1]"));
        return getElementText(address, "Invoice number on pending deliveries address cell - Advance dispatch page");
    }

    /**
     * This method will verify whether the Invalid address pop-up contans multiple invoices
     *
     * @return : If multiple invoices is present it will return true, otherwise false.
     * @Author: Sakrateesh R
     */
    public boolean Verify_Multiple_Invoices_Is_Displaying_Or_Not() {
        try {
            return invalid_address_invoice_list.size() > 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean is_All_Shop_Toogle_Button_In_Off_State() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(optimize_route_toogle_button_on_tripsection));

        Highlight_Element(optimize_route_toogle_button_on_tripsection, "Optimize Route toggle button on trip section");

        String backgroundColor = optimize_route_toogle_button_on_tripsection.getCssValue("background-color");
        System.out.println("Background color of Optimize Route toggle button: " + backgroundColor);

        // Checking if background color indicates OFF state (white)
        if (backgroundColor.equals("rgba(255, 255, 255, 1)") || backgroundColor.equals("rgb(255, 255, 255)")) {
            System.out.println("Toggle button is in the 'OFF' state.");
            return true;
        } else {
            System.out.println("Toggle button is in the 'ON' state.");
            return false;
        }
    }


    /**
     * This method will verify whether the All Shop Toggle button is displaying or not
     *
     * @return: If the toggle button is displayed it will return true else false
     * @Author: Sakrateesh R
     */
    public boolean Verify_All_Shops_Toggle_Button_Is_Displayed() {
        return isElementDisplayed(all_shops_toggle_button, "All Shops Toggle Button");
    }


    /**
     * This method will click on the All Shops Toggle button
     *
     * @Author: Sakrateesh R
     */
    public void Click_All_Shops_Toggle_Button() {
        js_Click(all_shops_toggle_button, "All Shops Toggle Button");
    }

    /**
     * This method verifies whether the All Shops Toggle Button is enabled
     *
     * @return : if All Shops Toggle Button is enabled it will return true else it will return false
     * @Author: Sakrateesh R
     */
    public boolean Verify_Whether_All_Shops_Toggle_Button_IsEnabled() {
        String RGBValue = all_shops_toggle_button.getAttribute("style");
        System.out.println(RGBValue);
        if (RGBValue.contains("rgb(197, 231, 244)")) {
            System.out.println("All Shops Toggle Button is enabled");
            return true;
        } else {
            System.out.println("All Shops Toggle Button is disabled");
        }
        return false;
    }

    /**
     * This method will verify whether the Invoice is displayed in the Pending Deliveries section
     *
     * @param invoice
     * @return : if invoice is displayed then it will return true else it will return false
     * @Author: Sakrateesh R
     */
    public boolean Verify_Invoice_IsDisplayed_In_Pending_Deliveries(String invoice) {
        try {
            for (WebElement invoiceElement : pending_deliveries_invoice_list) {
                WebElement invoiceno = invoiceElement.findElement(By.xpath("./span/a"));
                if (invoiceno.getText().contains(invoice)) {
                    return true;
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * This method will click on the Invoice number from the Pending Deliveries section
     *
     * @param invoice
     * @Author: Sakrateesh R
     */
    public void Click_Invoice_From_Pending_Deliveres_Section(String invoice) {
        try {
            for (WebElement invoiceno : pending_deliveries_invoice_list) {
                if (invoiceno.getText().contains(invoice)) {
                    WebElement anchortag = invoiceno.findElement(By.xpath(".//span/a"));
                    Click(anchortag, "Invoice number in the pending deliveries section");
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Unable to click on the invoice number: " + invoice + " on pending deliveries section. An error occurred: " + e);
        }
    }

    /**
     * This method will verify whether the Add Payrate button is displayed in the Advance Dispatch
     *
     * @return : if Add Payrate button is displayed then it will return true else it will return false
     * @Author: Sakrateesh R
     */
    public boolean Verify_Add_Payrate_Button_Is_Displayed() {
        return is_Element_Displayed(Add_Payrate_Advance_Dispatch_button, "Add Payrate button in Advance Dispatch");
    }

    /**
     * This method is used to click the Add Payrate button in Advance Dispatch
     *
     * @Author: Sakrateesh R
     */
    public void Click_Add_Payrate_Button_In_Advance_Dispatch() {
        fluentWait(Add_Payrate_Advance_Dispatch_button, 30, 2);
        Click(Add_Payrate_Advance_Dispatch_button, "Add Payrate button in the Advance Dispatch");
    }

    public void Switch_to_Default() {
        getDriver().switchTo().defaultContent();
    }

    /**
     * This method is used to verify whether the Add Payrate pop-up is displaying or not
     *
     * @Description: This method verify whether the Add Payrate pop-up is displaying or not
     * @return: If the Add Payrate popup is displayed it will return true else it will return false
     * @Author: Sakrateesh R
     */
    public boolean verify_add_payrate_popup_IsDisplayed() {

        return is_Element_Displayed(add_payrate_label, "Add Payrate Pop-up");
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
     * Verify whether the I icon is displayed for the invoice
     *
     * @param invoice
     * @return If the I icon is displayed for the invoice it will return true else it will return false
     * @Author Balaji N
     */
    public boolean verify_I_Icon_For_Invoice_Is_Displayed(String invoice) {
        WebElement invoiceNo = getDriver().findElement(By.xpath("//div[@id='kendoAdvanceUndispatchOrders']/div[2]/table/tbody/tr/td//span//a[contains(text(),'" + invoice + "')]/following::span[1]"));
        return isElementDisplayed(invoiceNo, "I icon for invoice");
    }

    /**
     * Clicks the I icon for the invoice order
     *
     * @param invoice
     * @Author Balaji N
     */
    public void click_I_Icon_For_Invoice_Is_Displayed(String invoice) {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                // Define the locator each time to avoid referencing stale elements
                By iconLocator = By.xpath("//div[@id='kendoAdvanceUndispatchOrders']/div[2]/table/tbody/tr/td//span//a[contains(text(),'" + invoice + "')]/following::span[1]");

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                WebElement invoiceIcon = wait.until(ExpectedConditions.elementToBeClickable(iconLocator)); // Re-located every retry

                Highlight_Element(invoiceIcon, "I icon for invoice [" + invoice + "]");
                js_Click(invoiceIcon, "I icon for invoice [" + invoice + "]");
                System.out.println("✅ Clicked I icon successfully for invoice [" + invoice + "]");
                return; // Exit on success

            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                attempt++;
                System.err.println("⚠️ [Retry " + attempt + "/" + maxRetries + "] due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000); // Give DOM time to recover
            } catch (Exception e) {
                throw new RuntimeException("❌ Unexpected error while clicking I icon for invoice [" + invoice + "]: " + e.getMessage(), e);
            }
        }

        throw new RuntimeException("❌ Failed to click I icon for invoice [" + invoice + "] after " + maxRetries + " attempts.");
    }


    /**
     * Verify whether the Highlighted tooltip on map view is displayed or not
     *
     * @return If the Highlighted tooltip on map view is displayed it will return true else it will return false
     * @Author Balaji N
     */
    public boolean verify_Highlighted_ToolTip_On_Map_View_IsDisplayed() {
        fluentWait(map_icon_tooltip_on_map_view);
        return isElementDisplayed(map_icon_tooltip_on_map_view, "Highlighted tooltip on map view");
    }

    /**
     * Verify whether the Highlighted tooltip on map view is not displayed
     *
     * @return If the Highlighted tooltip on map view is not displayed it will return true else it will return false
     * @Author Balaji N
     */
    public boolean verify_Highlighted_ToolTip_On_Map_View_IsNotDisplayed() {
        try {
            return isElementDisplayed(map_icon_tooltip_on_map_view, "Highlighted tooltip on map view");
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    /**
     * Verify whether the Timed Delivery icon is displayed for the invoice
     *
     * @param invoice
     * @return If the Timed Delivery icon is displayed for the invoice it will return true else it will return false
     * @Author Balaji N
     */
    public boolean verify_Timed_Delivery_Icon_Is_Displayed(String invoice) {
        By timedDeliveryIconLocator = By.xpath("//div[@id='kendoAdvanceUndispatchOrders']/div[2]/table/tbody/tr/td//span//a[contains(text(),'" + invoice + "')]/following::span[1]//following-sibling::div");

        try {
            WebElement timedDeliveryIcon = getDriver().findElement(timedDeliveryIconLocator);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfElementLocated(timedDeliveryIconLocator));
            boolean isDisplayed = isElementDisplayed(timedDeliveryIcon, "Timed Delivery Icon for invoice: " + invoice);
            System.out.println("✅ Timed Delivery Icon displayed status for invoice [" + invoice + "]: " + isDisplayed);
            return isDisplayed;

        } catch (StaleElementReferenceException | TimeoutException e1) {
            System.err.println("⚠️ Element stale or timeout on first attempt for invoice [" + invoice + "], retrying by relocating...");

            try {
                WebElement timedDeliveryIconRetry = getDriver().findElement(timedDeliveryIconLocator);
                explicitWait(timedDeliveryIconRetry); // re-wait on relocated element
                boolean isDisplayed = isElementDisplayed(timedDeliveryIconRetry, "Timed Delivery Icon after retry for invoice: " + invoice);
                System.out.println("✅ Retry successful - Timed Delivery Icon displayed: " + isDisplayed);
                return isDisplayed;

            } catch (Exception finalFailure) {
                System.err.println("❌ Failed after retrying to locate Timed Delivery Icon for invoice [" + invoice + "]: " + finalFailure.getMessage());
            }

        } catch (NoSuchElementException e2) {
            System.err.println("❌ Timed Delivery Icon not found for invoice [" + invoice + "]");
        } catch (Exception e3) {
            System.err.println("❌ Unexpected error while verifying Timed Delivery Icon for invoice [" + invoice + "]: " + e3.getMessage());
        }

        return false;
    }


    /**
     * Verify whether the Timed Delivery icon is displayed for the invoice
     *
     * @param invoice
     * @return If the Timed Delivery icon is displayed for the invoice it will return true else it will return false
     * @Author Balaji N
     */
    public boolean verify_Timed_Delivery_Icon_Is_Not_Displayed(String invoice) {
        try {
            WebElement timedDeliveryIcon = getDriver().findElement(By.xpath("//div[@id='kendoAdvanceUndispatchOrders']/div[2]/table/tbody/tr/td//span//a[contains(text(),'" + invoice + "')]/following::span[1]//following-sibling::div"));
            return isElementDisplayed(timedDeliveryIcon, "Timed Delivery Icon");
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    /**
     * Validate whether the designed icon is displayed
     *
     * @param invoiceNumber
     * @return
     */
    public boolean verify_Designed_Icon_IsDisplayed_On_Pending_Deliveries(String invoiceNumber) {
        By designedIconLocator = By.xpath("//div[@id='kendoAdvanceUndispatchOrders']/div[2]/table/tbody/tr/td//span//a[contains(text(),'" + invoiceNumber + "')]/following::img[@title='Designed'][1]");

        try {
            WebElement designedIcon = getDriver().findElement(designedIconLocator);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfElementLocated(designedIconLocator));

            boolean isDisplayed = isElementDisplayed(designedIcon, "Designed icon for invoice: " + invoiceNumber);
            System.out.println("✅ Designed icon displayed status for invoice [" + invoiceNumber + "]: " + isDisplayed);
            return isDisplayed;

        } catch (StaleElementReferenceException | TimeoutException e1) {
            System.err.println("⚠️ Stale or Timeout exception on first attempt for Designed icon [" + invoiceNumber + "], retrying...");

            try {
                WebElement designedIconRetry = getDriver().findElement(designedIconLocator);
                explicitWait(designedIconRetry);
                boolean isDisplayed = isElementDisplayed(designedIconRetry, "Designed icon after retry for invoice: " + invoiceNumber);
                System.out.println("✅ Retry successful - Designed icon displayed for invoice [" + invoiceNumber + "]: " + isDisplayed);
                return isDisplayed;

            } catch (Exception finalFailure) {
                System.err.println("❌ Retry failed: Could not verify Designed icon for invoice [" + invoiceNumber + "]: " + finalFailure.getMessage());
            }

        } catch (NoSuchElementException e2) {
            System.err.println("❌ Designed icon not found for invoice [" + invoiceNumber + "]");
        } catch (Exception e3) {
            System.err.println("❌ Unexpected error while verifying Designed icon for invoice [" + invoiceNumber + "]: " + e3.getMessage());
        }

        return false;
    }


    /**
     * Validate whether the designed icon is not displayed
     *
     * @param invoiceNumber
     * @return
     */
    public boolean verify_Designed_Icon_Is_NotDisplayed_On_Pending_Deliveries(String invoiceNumber) {
        try {
            WebElement designed_icon = getDriver().findElement(By.xpath("//div[@id='kendoAdvanceUndispatchOrders']/div[2]/table/tbody/tr/td//span//a[contains(text(),'" + invoiceNumber + "')]/following::span[1]/following::div[1]//img[contains(@src,'design')]"));
            return isElementDisplayed(designed_icon, "Designed icon on invoice order");
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    /**
     * Validate whether the priority flag is displayed or not on pending delivery section
     *
     * @param invoicenumber
     * @return If the priority flag is displayed it will return true else it will return false
     * @Author Balaji N
     */
    public boolean verify_Priority_Order_RedFlag_IsDisplayed(String invoicenumber) {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                By priorityFlagLocator = By.xpath("//div[@id='kendoAdvanceUndispatchOrders']/div[2]/table/tbody/tr/td//span//a[contains(text(),'" + invoicenumber + "')]/following::span[1]/following-sibling::span");

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                WebElement priorityFlag = wait.until(ExpectedConditions.presenceOfElementLocated(priorityFlagLocator));

                boolean isDisplayed = isElementDisplayed(priorityFlag, "Priority flag on pending delivery section");
                System.out.println("✅ Priority flag visibility for invoice [" + invoicenumber + "]: " + isDisplayed);
                return isDisplayed;

            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                System.err.println("⚠️ [Retry " + (attempt + 1) + "/" + maxRetries + "] Retrying due to: " + e.getClass().getSimpleName());
                delayWithGivenTime(1000);
            } catch (Exception e) {
                System.err.println("❌ Unexpected error while checking priority flag: " + e.getMessage());
                break; // stop retrying for unknown issues
            }

            attempt++;
        }

        throw new RuntimeException("❌ Failed to verify red flag for invoice [" + invoicenumber + "] after " + maxRetries + " attempts.");
    }


    /**
     * Validate whether the priority flag is displayed or not on pending delivery section
     *
     * @param invoicenumber
     * @return If the priority flag is displayed it will return true else it will return false
     * @Author Balaji N
     */
    public boolean verify_Priority_Order_RedFlag_Is_NotDisplayed(String invoicenumber) {
        try {
            WebElement priority_flag = getDriver().findElement(By.xpath("//div[@id='kendoAdvanceUndispatchOrders']/div[2]/table/tbody/tr/td//span//a[contains(text(),'" + invoicenumber + "')]/following::span[1]/following-sibling::span"));
            return isElementDisplayed(priority_flag, "Priority flag on pending delivery section");
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    /**
     * Clicks the not delivered map icon
     *
     * @Author Balaji N
     */
    public void click_not_delivered_map_icon() {
        Mouse_Hover(this, not_delivered_map_icon, "Not delivered map icon");
    }

    /**
     * It returns the invoice number on non delivery order
     *
     * @return
     */
    public String get_invoice_number_on_non_delivery_order() {
        return get_Element_Text(hover_map_icon_on_invoicenumber, "Invoice number on non delivery order");
    }

    public boolean non_delivery_Order_IsDisplayed_On_Pending_Deliveries() {
        return is_Element_Displayed(hover_map_icon_on_invoicenumber, "Map view on non delivery order");
    }

    /**
     * Selects the shop dropdown field on advance dispatch page
     *
     * @param shopname
     * @Author Balaji N
     */
    public void Select_Shop_Dropdown_On_Advance_Dispatch_Page(String shopname) {
        drop_Down(select_shop_dropdown_on_advance_dispatch_page, shopname, "VisibleText", "Select shop dropdown field on advance dispatch page");
    }

    /**
     * It retrieves the selected shop dropdown value on advance dispatch page
     *
     * @return
     */
    public String get_Selected_Shop_Dropdown_Value_Dispatch_Page() {
        return get_selected_option(select_shop_dropdown_on_advance_dispatch_page, "Select dropdown field value on advance dispatch page");
    }

    /**
     * Verify whether the dispatched icon is displayed on mapview
     *
     * @return If the dispatched icon is displayed it returns true else false
     * @Author Balaji N
     */
    public boolean verify_Dispatched_Icon_IsDisplayed_On_MapView() {
        return is_Element_Displayed(dispatched_icon_on_mapview, "Dispatched icon on map view on advance dispatch page");
    }

    /**
     * Zoom out on google map on advance dispatch page
     */
    public void zoom_out_google_map() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("document.querySelector('div.gm-style').style.zoom='0.40';");
        } catch (Exception e) {
            System.err.println("Unable to zoom out on map view " + e.getMessage());
        }

    }

    /**
     * clicks the left arrow on below pending deliveries section
     */
    public void click_Left_Arrow_On_Pending_Deliveries_Section() {
        click(left_arrow_on_pending_deliveries_section, "Left Arrow on below pending deliveries section");
    }

    /**
     * clicks the left arrow on below pending deliveries section
     */
    public void click_Right_Arrow_On_Trip_Section() {
        click(right_arrow_on_trip_section, "Right Arrow on below Trip section");
    }

    /**
     * Clicks the invoice number hyperlink on multiple orders with same address popup
     *
     * @param invoicenumber
     * @Author Balaji N
     */
    public void click_Invoice_Number_HyperLink_On_MultipleOrder_WithSame_Address(String invoicenumber) {
        WebElement invoice = getDriver().findElement(By.xpath("//div[@id='dvSameAddessInvoices']//td//a[text()='" + invoicenumber + "']"));
        click(invoice, "Invoice number hyperlink on multiple order with same address popup ");
        switchToWindowbyIndex(1);
    }

    /**
     * Clicks the invoice label header on pending deliveries section
     *
     * @Author Balaji N
     */
    public void click_Invoice_Label_Header_On_Pending_Deliveries_Section() {
        click(invoice_column_label_header_pending_deliveries_section, "Invoice Column label header in pending deliveries section");
    }

    public boolean verify_ascending_order_icon_on_invoice_label_pending_deliveries_section() {
        return isElementDisplayed(ascending_order_icon_on_invoice_label_pending_deliveries_section, "Ascending upward arrow icon Invoice label on pending deliveries");
    }

    public boolean verify_descending_order_icon_on_invoice_label_pending_deliveries_section() {
        return isElementDisplayed(descending_order_icon_on_invoice_label_pending_deliveries_section, "Ascending upward arrow icon Invoice label on pending deliveries");
    }

    /**
     * Verify whether the ascending order icon on invoice label pending deliveries section is Not Displayed
     *
     * @return
     */
    public boolean verify_ascending_order_icon_on_invoice_label_pending_deliveries_section_is_NotDisplayed() {
        try {
            return (ascending_order_icon_on_invoice_label_pending_deliveries_section).isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public boolean is_Invoices_Sorted_As_AscendingOrder_In_Pending_Deliveries_Section() {
        // 1. Get default list from the page
        List<WebElement> elements = getDriver().findElements(By.xpath("//div[@id='kendoAdvanceUndispatchOrders']//td//a"));

        List<String> defaultOrder = new ArrayList<>();
        for (WebElement element : elements) {
            defaultOrder.add(element.getText().trim());
        }

// 2. Sort it ascending to form expected list
        List<String> expectedSortedOrder = new ArrayList<>(defaultOrder);
        Collections.sort(expectedSortedOrder);

        click_Invoice_Label_Header_On_Pending_Deliveries_Section();

// Add wait for sorted results to load (use explicit wait if needed)
        delayWithGivenTime(2000); // replace with WebDriverWait for stability

// 4. Get the sorted list again from UI
        List<WebElement> sortedElements = getDriver().findElements(By.xpath("//div[@id='kendoAdvanceUndispatchOrders']//td//a"));

        List<String> actualSortedOrder = new ArrayList<>();
        for (WebElement element : sortedElements) {
            HighlightElement(element);
            actualSortedOrder.add(element.getText().trim());
        }

// 5. Compare expected vs actual
        if (expectedSortedOrder.equals(actualSortedOrder)) {
            System.out.println("PASS: The list is sorted in ascending order correctly.");
            return true;
        } else {
            System.out.println("FAIL: The list is not sorted correctly.");
            System.out.println("Expected: " + expectedSortedOrder);
            System.out.println("Actual: " + actualSortedOrder);
            return false;
        }

    }

    /**
     * Verify Invoices are sorted in descending order in pending deliveries section
     *
     * @return If the Invoices are sorted in descending order, returns true; otherwise, returns false
     * @Author Balaji N
     */
    public boolean is_DescendingOrder_Invoices_In_Pending_Deliveries_Section() {
        // 1. Get the default list from UI
        List<WebElement> elements = getDriver().findElements(By.xpath("//div[@id='kendoAdvanceUndispatchOrders']//td//a"));

        List<String> defaultOrder = new ArrayList<>();
        for (WebElement element : elements) {
            defaultOrder.add(element.getText().trim());
        }

// 2. Sort in descending order (expected)
        List<String> expectedDescendingOrder = new ArrayList<>(defaultOrder);
        expectedDescendingOrder.sort(Comparator.reverseOrder());

// 3. Click on sort descending icon
        click_Invoice_Label_Header_On_Pending_Deliveries_Section();

// Add wait for DOM to refresh
        delayWithGivenTime(2000); // Replace with WebDriverWait for better reliability

// 4. Fetch the list again after sorting
        List<WebElement> sortedElements = getDriver().findElements(By.xpath("//div[@id='kendoAdvanceUndispatchOrders']//td//a"));

        List<String> actualDescendingOrder = new ArrayList<>();
        for (WebElement element : sortedElements) {
            HighlightElement(element);
            actualDescendingOrder.add(element.getText().trim());
        }

// 5. Compare expected vs actual
        if (expectedDescendingOrder.equals(actualDescendingOrder)) {
            System.out.println("PASS: The list is sorted in descending order correctly.");
            return true;
        } else {
            System.out.println("FAIL: The list is not sorted correctly in descending order.");
            System.out.println("Expected: " + expectedDescendingOrder);
            System.out.println("Actual: " + actualDescendingOrder);
            return false;
        }

    }

    /**
     * Verify whether the dispatch icon on trip section is displayed or not
     *
     * @return If the dispatch icon is displayed it returns true else false
     * @Author Balaji N
     */
    public boolean verify_Dispatch_Icon_On_Trip_Section() {
        WebElement yellowIcon = dispatch_icon_on_trip_section;
        String iconSrc = yellowIcon.getAttribute("src");
        String expectedSrc = "https://hanafloralpos3.com/images/MapIcons/yellow_t1.png";

        if (iconSrc.contains(expectedSrc)) {
            return isElementDisplayed(yellowIcon, "Dispatch icon on trip section - Advance dispatch page");
        }
        return false;
    }

    /**
     * Verify whether the route number is displayed on trip section
     *
     * @return If the route number is displayed it returns true else false
     * @Author Balaji N
     */
    public boolean verify_Route_Number_IsDisplayed_In_Trip_Section() {
        return isElementDisplayed(active_route_detail_on_trip_section, "Route number on trip section - Advance dispatch page");
    }

    /**
     * It retrieves the route number displayed on trip section
     *
     * @return If the route number is displayed it returns value of route number else returns the null value
     * @Author Balaji N
     */
    public String get_Actual_Route_Number_Displayed_In_Trip_Section() {
        String routeNumber_with_assigned_driver = get_Element_Text(active_route_detail_on_trip_section, "Route number on trip section - Advance dispatch page");
        String routeNumber = routeNumber_with_assigned_driver.split("-")[0].trim();
        return routeNumber;
    }

    public boolean is_TimeStamp_Displayed_On_TripSection() {
        return isElementDisplayed(timestamp_on_tripsection, "Time Stamp label on trip section - Advance dispatch page");
    }

    public String get_Actual_TimeStamp_Displayed_On_Trip_Section() {
        return getElementText(timestamp_on_tripsection, "Time Stamp label on trip section - Advance dispatch page");
    }

    /**
     * Validate whether the invoice is displaying in the trip section or not
     *
     * @param invoice
     * @return If the invoice is displayed then it will return true else it will return false
     * @Author Balaji N
     */
    public void click_Delete_Icon_for_Respective_Invoice_In_Trip_Section(String invoiceNumber) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            By invoiceLocator = By.xpath("//table[@id='advanceDispatchCurrentDispatch']//td//span//a[text()='" + invoiceNumber + "']/following-sibling::a[@class='SavedTripEditDelete']");
            WebElement invoiceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(invoiceLocator));
            click(invoiceElement, "Delete Icon for respective invoice in the trip section - Advance Dispatch Page");
            wait_For_Page_To_Be_Stable(getDriver());
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout: Invoice number '" + invoiceNumber + "' not found in the trip section.", e);
        } catch (Exception e) {
            throw new RuntimeException("Error validating invoice in trip section: " + invoiceNumber + ". " + e.getMessage(), e);
        }
    }

    /**
     * Verify whether the respective invoice number is not displayed in trip section - Advance dispatch
     *
     * @param invoice_number
     * @return If the invoice number is displayed it return false else return true
     */
    public boolean verify_Invoice_Is_Not_Displayed_On_Trip_Section(String invoice_number) {
        try {
            //  WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
            By invoiceLocator = By.xpath("//table[@id='advanceDispatchCurrentDispatch']//td//span//a[text()='" + invoice_number + "']/following-sibling::a[@class='SavedTripEditDelete']");
            WebElement invoiceElement = getDriver().findElement(invoiceLocator);
            return !isElementDisplayed(invoiceElement, "Delete Icon for respective invoice in the trip section - Advance Dispatch Page");
        } catch (NoSuchElementException e) {
            return true;
        }

    }

    /**
     * Clicks the route dropdown in trip section
     *
     * @Author Balaji N
     */
    public void click_Route_Dropdown_In_Trip_Section() {
        js_Click(route_dropdown_on_tripsection, "Route dropdown in trip section - Advance dispatch page");
    }

    /**
     * Select the existing trip from the route dropdown dynamically
     *
     * @param routenumber
     * @Author Balaji N
     */
    public void select_Existing_Trip(String routenumber) {
        WebElement existingTrip = getDriver().findElement(By.xpath("//ul[@id='RouteDataWrap']//li//a[@id='" + routenumber + "']"));
        js_Click(existingTrip, "Existing trip in trip section - Advance dispatch page");
    }

    /**
     * Verify whether the existing trip from the route dropdown dynamically
     *
     * @param routenumber
     * @return
     * @Author Balaji N
     */
    public boolean is_Deleted_Trip_Displayed(String routenumber) {
        try {
            WebElement existingTrip = getDriver().findElement(By.xpath("//ul[@id='RouteDataWrap']//li//a[@id='" + routenumber + "']"));
            return !isElementDisplayed(existingTrip, "Existing trip in trip section - Advance dispatch page");
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    /**
     * Verify whether the existing trip is displayed based on route number
     *
     * @param routenumber
     * @return If the existing trip is displayed based on route number it returns true else false
     * @Author Balaji N
     */
    public boolean is_Existing_Trip_Displayed(String routenumber) {
        WebElement existingTrip = getDriver().findElement(By.xpath("//ul[@id='RouteDataWrap']//li//a[@id='" + routenumber + "']"));
        return isElementDisplayed(existingTrip, "Existing trip in trip section - Advance dispatch page");
    }

    /**
     * Verify whether the new route option is displayed on saved trip dropdown
     *
     * @return If the new route option is displayed on saved trip dropdown field it returns true
     * @Author Balaji N
     */
    public boolean is_NewRoute_Option_Displayed_On_SavedTrip_Dropdown() {
        return isElementDisplayed(new_route_button_on_saved_trip_dropdown_on_tripsection, "New route option in saved trip dropdown - Advance dispatch page");
    }

    /**
     * Verify whether the trip number is displayed or not in trip section
     *
     * @return If the trip number is displayed it returns true else returns false
     * @Author Balaji N
     */
    public boolean is_TripNumber_Displayed_On_Trip_Section() {
        return isElementDisplayed(trip_no_on_tripsection, "Trip number on trip section - Advance dispatch page");
    }

    /**
     * Verify whether the new trip button is displayed below trip section
     *
     * @return If the new trip button is displayed it returns true else returns false
     * @Author Balaji N
     */
    public boolean is_New_Trip_Button_Displayed_Tripsection() {
        return isElementDisplayed(new_trip_button_below_tripsection, "New trip button below trip section - Advance dispatch page");
    }

    /**
     * Clicks the new trip button below trip section
     *
     * @Author Balaji N
     */
    public void click_New_Trip_Button_Tripsection() {
        click(new_trip_button_below_tripsection, "New trip button below trip section - Advance dispatch page");
    }

    /**
     * Verify whether the confirmation popup is displayed after clicking new trip button
     *
     * @return If the confirmation popup is displayed it returns true else returns false
     * @Author Balaji N
     */
    public boolean is_Confirmation_Popup_Displayed_After_Clicking_New_Trip_Button() {
        return isElementDisplayed(confirmation_popup_on_add_new_tripsection, "Confirmation popup after clicking new trip button - Advance dispatch page");
    }

    /**
     * Verify whether the confirmation popup is displayed after clicking optimize button
     *
     * @return If the confirmation popup is displayed it returns true else returns false
     * @Author Balaji N
     */
    public boolean is_Confirmation_Popup_Displayed_After_Clicking_Optimize_Button() {
        return isElementDisplayed(confirmation_popup_on_add_new_tripsection, "Confirmation popup after clicking optimize button - Advance dispatch page");
    }


    /**
     * Verify whether the confirmation popup is displayed after clicking new trip button
     *
     * @return If the confirmation popup is displayed it returns true else returns false
     * @Author Balaji N
     */
    public boolean is_Confirmation_Popup_Closed_After_Clicking_No_Yes_Button() {
        try {
            return !(confirmation_popup_on_add_new_tripsection).isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Confirmation popup after clicking New trip button - Advance dispatch page" + e);
        }
    }

    public void click_No_Button_In_Confirmation_Popup() {
        click(no_button_on_confirmation_popup_on_add_new_tripsection, "No button in confirmation popup - Advance dispatch page");
    }

    public void click_Yes_Button_In_Confirmation_Popup() {
        click(yes_button_on_confirmation_popup_on_add_new_tripsection, "Yes button in confirmation popup - Advance dispatch page");
    }

    /**
     * Verify whether the remote print button is displayed in trip section
     *
     * @return If the remote print button is displayed it returns true else returns false
     * @Author Balaji N
     */
    public boolean is_Remote_Print_Button_Displayed() {
        return isElementDisplayed(remote_print_button_on_tripsection, "Remote print button in trip section - Advance dispatch page");
    }

    /**
     * Clicks the remote print button in trip section
     *
     * @Author Balaji N
     */
    public void click_Remote_Print_Button_Tripsection() {
        click(remote_print_button_on_tripsection, "Remote print button in trip section - Advance dispatch page");
    }


    /**
     * Verify whether the remote print button is displayed in trip section
     *
     * @return If the remote print button is displayed it returns true else returns false
     * @Author Balaji N
     */
    public boolean is_Manual_Print_Button_Displayed() {
        return isElementDisplayed(manual_print_button_on_tripsection, "Remote print button in trip section - Advance dispatch page");
    }

    /**
     * Clicks the remote print button in trip section
     *
     * @Author Balaji N
     */
    public void click_Manual_Print_Button_Tripsection() {
        click(manual_print_button_on_tripsection, "Remote print button in trip section - Advance dispatch page");
    }

    /**
     * Verify whether the manual print popup is displayed or not
     *
     * @return If the manual print popup is displayed it returns true else returns false
     * @Author Balaji N
     */
    public boolean is_Manual_Print_Popup_Header_Displayed() {
        return isElementDisplayed(manual_print_popup_on_tripsection, "Manual print popup header - Advance dispatch page");
    }

    /**
     * Verify whether the Add Payrate button is displayed in trip section
     *
     * @return If the Add Payrate button is displayed it returns true else returns false
     * @Author Balaji N
     */
    public boolean is_Add_Payrate_Button_Displayed() {
        return isElementDisplayed(add_payrate_button_on_tripsection, "Add Payrate button in trip section - Advance dispatch page");
    }

    /**
     * Clicks the Add Payrate button in trip section
     *
     * @Author Balaji N
     */
    public void click_Add_Payrate_Button_Tripsection() {
        click(add_payrate_button_on_tripsection, "Add Payrate button in trip section - Advance dispatch page");
    }

    /**
     * Verify whether the Add Payrate popup is displayed or not
     *
     * @return If the Add Payrate popup is displayed it returns true else returns false
     * @Author Balaji N
     */
    public boolean is_Add_Payrate_Popup_Displayed() {
        return isElementDisplayed(add_payrate_popup_on_tripsection, "Add Payrate popup on trip section - Advance dispatch page");
    }

    public void switchTo_Add_Payrate_TableGrid() {
        try {
            switchToFrame(addpayrate_table_grid);
        } catch (Exception e) {
            throw new RuntimeException("Error switching to Add Payrate table grid: " + e.getMessage());
        }
    }

    public String get_Displayed_InvoiceNumber_On_Add_Payrate_TableGrid() {
        return get_Element_Text(payrate_invoice_number, "Invoice number on Add Payrate table grid - Advance dispatch page");
    }

    public void unchecked_The_Payable_Checkbox_Add_Payrate_Popup() {
        click(payable_checkbox_add_payrate_popup, "Payable checkbox on Add Payrate popup - Advance dispatch page");
    }

    public void enter_Driver_Payrate_On_Add_Payrate_Popup_TableGrid(String driverpayrate) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(driver_payrate_textbox_add_payrate_popup)).click();
        js_ClickAndType(driver_payrate_textbox_add_payrate_popup, driverpayrate, "Driver payrate on Add Payrate popup table grid - Advance dispatch page");
    }

    public String get_Displayed_Driver_Payrate_On_Add_Payrate_TableGrid() {
        return getElementAttribute(driver_payrate_textbox_add_payrate_popup, "Driver payrate on Add Payrate table grid - Advance dispatch page");
    }

    public void click_Close_Icon_On_Add_Payrate_Popup() {
        click(close_icon_on_add_payrate_popup, "Close icon on Add Payrate popup - Advance dispatch page");
    }


    public void enter_Non_Payable_Reason_On_Add_Payrate_Popup_TableGrid(String non_payable_reason) {
        ClickAndType(non_payable_textbox_add_payrate_popup, non_payable_reason, "Non Payable Reason on Add Payrate popup table grid - Advance dispatch page");
    }

    public String get_Displayed_Non_Payable_Reason_On_Add_Payrate_TableGrid() {
        return getElementAttribute(non_payable_textbox_add_payrate_popup, "Non Payable Reason on Add Payrate table grid - Advance dispatch page");
    }

    public void click_Update_Button_On_Add_Payrate_Popup() {
        Click(update_button_on_add_payrate_popup, "Update button on Add Payrate popup - Advance dispatch page");
    }

    public String is_Payrate_Updated_Successfully_Label_Displayed() {
        return get_Element_Text(success_message_on_add_payrate_popup, "Pay Rate Updated Successfully - Success message on Add Payrate popup - Advance dispatch page");
    }

    /**
     * Verify whether the Print Map button is displayed or not
     *
     * @return If the Print Map button is displayed it returns true else returns false
     * @Author Balaji N
     */
    public boolean is_Print_Map_Button_Displayed() {
        return isElementDisplayed(print_map_button_on_tripsection, "Print Map button - Advance dispatch page");
    }

    /**
     * Clicks the Print Map button in trip section
     *
     * @Author Balaji N
     */
    public void click_Print_Map_Button() {
        click(print_map_button_on_tripsection, "Print Map button - Advance dispatch page");
        switchToWindowbyIndex(1);
    }

    public boolean is_Optimize_Button_Displayed_On_TripSection() {
        return isElementDisplayed(optimize_button_on_trip_section, "Optimize button on trip section");
    }

    public void click_Optimize_Button_On_TripSection() {
        click(optimize_button_on_trip_section, "Optimize button on trip section");
    }

    public boolean is_Reverse_Trip_Button_Displayed_On_TripSection() {
        return isElementDisplayed(reverse_trip_button_on_trip_section, "Reverse trip button on trip section");
    }

    public void click_Reverse_Trip_Button_On_TripSection() {
        click(reverse_trip_button_on_trip_section, "Reverse Trip button on trip section");
    }

    public boolean is_Print_Map_Window_Displayed() {
        return isElementDisplayed(print_your_route_newtab_on_page, "Print Map window - Advance dispatch page");
    }

    public void click_Satellite_Button_On_Print_Map_Popup() {
        click(satellite_button_on_print_mapsection, "Satellite button on Print Map popup - Advance dispatch page");
    }

    public void uncheck_Label_On_Satellite_View_Print_Map_Section() {
        Mouse_Hover(this, satellite_button_on_print_mapsection, "Uncheck label on satellite view print map section - Advance dispatch page");
        delayWithGivenTime(2000);
        js_Click(satellite_label_checkbox_on_print_mapsection, "Uncheck label on satellite view print map section - Advance dispatch page");
    }

    public void click_Map_View_Button_On_Print_Map_Popup() {
        click(map_view_button_on_print_mapsection, "Map view button on Print Map popup - Advance dispatch page");
    }

    public void check_Terrain_On_Map_View_Print_Map_Section() {
        Mouse_Hover(this, map_view_button_on_print_mapsection, "Check terrain on map view print map section - Advance dispatch page");
        delayWithGivenTime(2000);
        click(terrain_checkbox_on_print_mapsection, "Check terrain on map view print map section - Advance dispatch page");
    }

    // Synchronized method to ensure thread safety
    public synchronized void ctrlScroll(WebElement element, boolean scrollUp) {
        try {
            Actions actions = new Actions(getDriver());
            actions.keyDown(Keys.CONTROL).perform();

            // Simulate scroll using sendKeys with Arrow Up/Down if applicable
            if (scrollUp) {
                actions.moveToElement(element).sendKeys(Keys.ADD).perform();  // Simulated zoom-in
            } else {
                actions.moveToElement(element).sendKeys(Keys.SUBTRACT).perform();  // Simulated zoom-out
            }

            actions.keyUp(Keys.CONTROL).perform();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verify_Zoom_In_Functionality_On_Map_View_Print_Map_Section() {
        ctrlScroll(map_element_on_print_mapsection, true);
    }

    public void verify_Zoom_Out_Functionality_On_Map_View_Print_Map_Section() {
        ctrlScroll(map_element_on_print_mapsection, false);
    }

    public void dragAndDrop_Pegman_Icon_On_Map_View_Print_Map_Section() {
        dragAndDrop(pegman_icon_on_print_mapsection, map_element_on_print_mapsection);
    }

    public void click_fullscreen_icon_on_print_mapsection() {
        js_Click(fullscreen_icon_on_print_mapsection, "Fullscreen icon on Print Map popup - Advance dispatch page");
    }

    public boolean is_Print_Button_Displayed_On_Print_Map_Page() {
        return isElementDisplayed(print_driver_sheet_print_button_on_print_mapsection, "Print button on Print Map page - Advance dispatch page");
    }

    public void click_Print_Button_On_Print_Map_Page() {
        click(print_driver_sheet_print_button_on_print_mapsection, "Print button on Print Map page - Advance dispatch page");
    }

    /**
     * Verify Invoices number are in reverse order on trip section after clicking reverse trip button
     *
     * @return If the Invoices number are in reverse order it returns true else returns false
     * @Author Balaji N
     */
    public boolean verify_Invoices_Are_In_Reverse_Order() {
        List<String> originalInvoiceOrder = new ArrayList<>();
        for (WebElement invoice : invoice_number_on_tripsection) {
            originalInvoiceOrder.add(invoice.getText().trim());
        }
        //   System.out.println("Original Invoice Order: " + originalInvoiceOrder);

        click_Reverse_Trip_Button_On_TripSection();
        delayWithGivenTime(3000
        );

        //  System.out.println("=========== Reverse Trip button clicked ======================");
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(invoice_number_on_tripsection));

        List<String> reversedInvoiceOrder = new ArrayList<>();
        for (WebElement invoice : invoice_number_on_tripsection) {
            reversedInvoiceOrder.add(invoice.getText().trim());
        }
        //  System.out.println("Reversed Invoice Order: " + reversedInvoiceOrder);

        List<String> expectedReversedOrder = new ArrayList<>(originalInvoiceOrder);
        Collections.reverse(expectedReversedOrder);

        //  System.out.println("Converted Original Invoice Order: " + expectedReversedOrder);
        //   System.out.println("Reversed Invoice Order: " + reversedInvoiceOrder);
        return expectedReversedOrder.equals(reversedInvoiceOrder);

    }

    public boolean is_Optimize_Configuration_Settings_Icon_Displayed_On_TripSection() {
        return isElementDisplayed(optimize_configuration_settings_icon_on_tripsection, "Optimize route settings icon on trip section");
    }

    public void click_Optimize_Configuration_Settings_Icon_On_TripSection() {
        click(optimize_configuration_settings_icon_on_tripsection, "Optimize route settings icon on trip section");
    }

    public boolean is_Optimize_Route_Settings_Popup_Displayed() {
        return isElementDisplayed(optimize_configuration_popup_header_on_tripsection, "Optimize route settings popup");
    }

    public void turn_ON_Avoid_Tolls_Road_Toogle_On_Optimize_Configuration_Popup() {
        jsClick(avoid_toll_road_toogle_on_optimize_configuration_popup, "Avoid toll road toogle on optimize configuration popup");
    }

    public void turn_ON_Avoid_Highway_Toogle_On_Optimize_Configuration_Popup() {
        jsClick(avoid_highway_toogle_on_optimize_configuration_popup, "Avoid Highways toogle on optimize configuration popup");
    }

    public void turn_ON_Avoid_Ferries_Toogle_On_Optimize_Configuration_Popup() {
        jsClick(avoid_ferries_toogle_on_optimize_configuration_popup, "Avoid Ferries toogle on optimize configuration popup");
    }

    public void turn_ON_Unit_Miles_Toogle_On_Optimize_Configuration_Popup() {
        jsClick(unit_miles_toogle_on_optimize_configuration_popup, "Unit Miles toogle on optimize configuration popup");
    }

    public void click_Close_Button_On_Optimize_Configuration_Popup() {
        click(close_button_on_optimize_configuration_popup, "Close button on optimize configuration popup");
    }

    public void select_SortBy_Option_On_Pending_Deliveries_Section(String option) {
        drop_Down(sort_by_pending_deliveries_dropdown, option, "VisibleText", "Sort by dropdown field on pending deliveries section");
    }

    public String get_Selected_SortBy_Option_On_Pending_Deliveries_Section() {
        return get_selected_option(sort_by_pending_deliveries_dropdown, "Sort By drop down pending deliveries section");
    }

    public void click_Sort_Icon_On_Pending_Deliveries_Section() {
        jsClick(sort_icon_on_pending_deliveries_section, "Sort Icon on pending deliveries section");
    }

    public void click_Summarized_By_Label_Summary_Section() {
        click(summarized_by_label_column, "Summarized by label column");
    }

    public boolean is_Summarized_By_Rows_Are_In_AlphabeticOrder() {
        // 1. Get default list from the page
        List<String> defaultOrder = new ArrayList<>();
        for (WebElement element : list_of_summarizedby_city) {
            defaultOrder.add(element.getText().trim());
        }

        // 2. Sort it ascending to form expected list
        List<String> expectedSortedOrder = new ArrayList<>(defaultOrder);
        Collections.sort(expectedSortedOrder);

        click_Summarized_By_Label_Summary_Section();

// Add wait for sorted results to load (use explicit wait if needed)
        delayWithGivenTime(2000); // replace with WebDriverWait for stability

// 4. Get the sorted list again from UI
        List<WebElement> sortedElements = list_of_summarizedby_city;

        List<String> actualSortedOrder = new ArrayList<>();
        for (WebElement element : sortedElements) {
            HighlightElement(element);
            actualSortedOrder.add(element.getText().trim());
        }

// 5. Compare expected vs actual
        if (expectedSortedOrder.equals(actualSortedOrder)) {
            System.out.println("PASS: The list is sorted in ascending order correctly.");
            return true;
        } else {
            System.out.println("FAIL: The list is not sorted correctly.");
            System.out.println("Expected: " + expectedSortedOrder);
            System.out.println("Actual: " + actualSortedOrder);
            return false;
        }
    }

    public boolean is_Summarized_By_Rows_In_Alphabetic_Reverse_Order() {
        // 1. Get default list from the page
        List<String> defaultOrder = new ArrayList<>();
        for (WebElement element : list_of_summarizedby_city) {
            defaultOrder.add(element.getText().trim());
        }

        // 2. Sort it in descending (Z to A) order
        List<String> expectedReverseSortedOrder = new ArrayList<>(defaultOrder);
        expectedReverseSortedOrder.sort(Comparator.reverseOrder());

        // 3. Click to sort in reverse (Z-A) on UI
        click_Summarized_By_Label_Summary_Section();

        // 4. Wait for sorted results to load
        delayWithGivenTime(2000); // Replace with WebDriverWait for reliability

        // 5. Get the sorted list again from UI
        List<WebElement> sortedElements = list_of_summarizedby_city;

        List<String> actualSortedOrder = new ArrayList<>();
        for (WebElement element : sortedElements) {
            HighlightElement(element);
            actualSortedOrder.add(element.getText().trim());
        }

        // 6. Compare expected vs actual
        if (expectedReverseSortedOrder.equals(actualSortedOrder)) {
            System.out.println("✅ PASS: The list is sorted in reverse alphabetical order (Z to A).");
            return true;
        } else {
            System.out.println("❌ FAIL: The list is not sorted in reverse alphabetical order.");
            System.out.println("Expected: " + expectedReverseSortedOrder);
            System.out.println("Actual: " + actualSortedOrder);
            return false;
        }
    }

    public boolean is_Ascending_Upward_Arrow_Displayed() {
        return isElementDisplayed(ascending_icon_summary_section, "upward icon on summary section");
    }

    public boolean is_Descending_downward_Arrow_Displayed() {
        return isElementDisplayed(descending_icon_summary_section, "downward icon on summary section");
    }

    public boolean is_Optimize_Route_Toogle_Button_Displayed() {
        return isElementDisplayed(optimize_route_toogle_button_on_tripsection, "Optimize route toogle button on trip section");
    }

    /**
     * Verify whether the optimize route toogle button is in the off state on the create recipe popup
     *
     * @return If the optimize route toogle button is in the off state it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean is_Optimize_Route_Toogle_Button_In_On_State() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(optimize_route_toogle_button_on_tripsection));

        Highlight_Element(optimize_route_toogle_button_on_tripsection, "Optimize route toogle button on trip section");
        String backgroundColor = optimize_route_toogle_button_on_tripsection.getCssValue("background-color");
        System.out.println("state of optimize route toogle button color : " + backgroundColor);

        if (backgroundColor.equals("rgba(26, 179, 148, 1)")) {  // Color for off state: rgb(237, 85, 101) corresponds to #ed5565
            return true;
        } else {
            System.out.println("Toggle button is in the 'off' state...");
            return false;
        }
    }

    public boolean is_Optimize_Route_Toogle_Button_In_Off_State() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(optimize_route_toogle_button_on_tripsection));

        Highlight_Element(optimize_route_toogle_button_on_tripsection, "Route trip toggle button on trip section");

        String backgroundColor = optimize_route_toogle_button_on_tripsection.getCssValue("background-color");
        System.out.println("Background color of Round trip toggle button: " + backgroundColor);

        // Checking if background color indicates OFF state (white)
        if (backgroundColor.equals("rgba(237, 85, 101, 1)") || backgroundColor.equals("rgb(255, 255, 255)")) {
            System.out.println("Toggle button is in the 'OFF' state.");
            return true;
        } else {
            System.out.println("Toggle button is in the 'ON' state.");
            return false;
        }
    }

    /**
     * Verify whether the round trip toogle button is in the off state on the create recipe popup
     *
     * @return If the round trip toogle button is in the off state it returns true, otherwise it returns false
     * @Author Balaji N
     */
    public boolean is_Round_Trip_Toogle_Button_In_On_State() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(round_trip_toogle_button_on_tripsection));

        Highlight_Element(round_trip_toogle_button_on_tripsection, "Round trip toogle button on trip section");
        String backgroundColor = round_trip_toogle_button_on_tripsection.getCssValue("background-color");
        System.out.println("state of round trip toogle button color : " + backgroundColor);

        if (backgroundColor.equals("rgba(26, 179, 148, 1)")) {  // Color for off state: rgb(237, 85, 101) corresponds to #ed5565
            return true;
        } else {
            System.out.println("Round trip Toggle button is in the 'off' state...");
            return false;
        }
    }

    public boolean is_Round_Trip_Toogle_Button_In_Off_State() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(round_trip_toogle_button_on_tripsection));

        Highlight_Element(round_trip_toogle_button_on_tripsection, "Optimize Route toggle button on trip section");

        String backgroundColor = round_trip_toogle_button_on_tripsection.getCssValue("background-color");
        System.out.println("Background color of Round trip toggle button: " + backgroundColor);

        // Checking if background color indicates OFF state (white)
        if (backgroundColor.equals("rgba(237, 85, 101, 1)") || backgroundColor.equals("rgb(255, 255, 255)")) {
            System.out.println("Toggle button is in the 'OFF' state.");
            return true;
        } else {
            System.out.println("Toggle button is in the 'ON' state.");
            return false;
        }
    }

    public boolean is_Round_Trip_Toogle_Button_Displayed() {
        return isElementDisplayed(round_trip_toogle_button_on_tripsection, "Round trip toogle button on trip section");
    }

    public void click_Optimize_Route_Toogle_On_TripSection() {
        click(optimize_route_toogle_button_on_tripsection, "Optimize route toggle");
    }

    public void click_Round_Trip_Toogle_Off_TripSection() {
        click(round_trip_toogle_button_on_tripsection, "Round trip toggle");
    }

    public String get_Total_Distance_Displayed_On_Print_Map_Page() {
        return get_Element_Text(total_distance_print_map_section, "Total distance on print map page");
    }

}








