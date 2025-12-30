package com.hanapos.pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import io.qameta.allure.Allure;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ConfirmationPage class is a page object class for Confirmation page
 * It contains all the web elements of Confirmation page and thier interactions functions
 *
 * @Author Balaji N
 */
public class ConfirmationPage extends TestBaseClass {

    public ConfirmationPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //============================== Confirmation Page WebElements ================================

    //    Confirmation Contains Text Box Elementff
    @FindBy(xpath = "//input[@id='txtContains']")
    private WebElement Confirmation_Contains_text_box;

    //    Current Date Element in the Calendar
    @FindBy(xpath = "//td[@class='active day']")
    private WebElement current_date_in_calendar;

    // Recipient column on Confirmation Page search box element
    @FindBy(xpath = "//input[@id='txtGridRecipient']")
    private WebElement searchbox_Recipient_column;

    //    Confimation Page Search button Element
    @FindBy(xpath = "//button[@id='btnSearchNew']")
    private WebElement Confirmation_Search_Button;

    @FindBy(id = "txtDelConfDateNew")
    private WebElement Confirmation_Date_field;

    @FindBy(xpath = "//div[@class='datepicker-days']")
    private WebElement deliveryconfirmation_date_picker;

    @FindBy(xpath = "//input[@id='txtDelConfDateNew']")
    private WebElement date_datepickerfield;

    @FindBy(xpath = "//input[@id='txtDelConftoDateNew']")
    private WebElement to_date_picker_field;

    @FindBy(xpath = "//div[@class='datepicker-days']//th[@class='datepicker-switch']")
    private WebElement monthandyear_dateselection;

    @FindBy(xpath = "//div[@class='datepicker-days']//th[@class='next']")
    private WebElement nextarrow_dateselection;

    @FindBy(xpath = "//div[@class='datepicker-days']//tbody//tr//td[(@class='day') or @class='active day' or @class='today day']")
    private List<WebElement> listofdays_dateselection;

    @FindBy(xpath = "//div[@class='input-group date']/span")
    private WebElement Confirmation_Date_Picker_icon;

    //    Confirmation Page Confirmation Pop-up Element
    @FindBy(xpath = "//div[@class='sweet-alert show-input showSweetAlert visible']")
    WebElement Confirmation_Pop_Up;

    //    Confirmation Page Confirmation Pop-up Message Text Element
    @FindBy(xpath = "//div[@class='sweet-alert show-input showSweetAlert visible']/p")
    WebElement Confirmation_Pop_Up_Message;

    //    Confirmation Page Confirmation Pop-up Header Element
    @FindBy(xpath = "//div[@class='sweet-alert show-input showSweetAlert visible']/h2")
    WebElement Confirmation_Pop_Up_Title;

    //    Confirmation Pop-up Text Input Field Element
    @FindBy(xpath = "//input[@placeholder='Confirmation code']")
    WebElement Confirmation_Pop_up_Text_Input_Field;

    //    Confirmation pop-up Confirm Button / Yes Button Element
    @FindBy(xpath = "//button[@class='confirm']")
    WebElement Confiramtion_pop_up_confirm_button;

    //    Delivery Confirmation Table element for single invoice
    @FindBy(xpath = "(//table[@role='grid']/tbody/tr/td[2])[13]")
    WebElement delivery_confirmation_single_invoice_recipient_column_element;

    @FindBy(xpath = "//div[@class='k-grid-content k-auto-scrollable']//td[2]")
    private List<WebElement> list_of_recipient_delivery_confirmationpopup;

    //    Delivery Code Dropdown field Web Element
    @FindBy(xpath = "//select[@class='form-control ddlCustom cst_dropdown ddlDeliveryCodeNew']")   ////select[@class='form-control ddlCustom cst_dropdown ddlDeliveryCodeNew']"
            WebElement delivery_code_dropdown_field_confirmation_page;

    @FindBy(xpath = "//select[contains(@class,'ddlDriverCodeNew')]")
    private WebElement driver_dropdown_field_delivery_confirmation_details_popup;

    //    Confirm Selected Deliveries button Element
    @FindBy(xpath = "//button[@id='btnSaveNew']")
    WebElement confirm_selected_delivereies_button;

    @FindBy(css = "input.txtDeliveryNotesNew")
    private WebElement delivery_notes_field_deliveryconfirmation_page;

    @FindBy(css = "span.txtDeliverDateTimeNew .k-link-time")
    private WebElement delivery_time_field_deliveryconfirmation_page;

    @FindBy(css = "ul[id*='timeview'] li")
    private List<WebElement> list_of_time_on_timepicker;

    //    Confimration Page pop-up close X button Element
    @FindBy(xpath = "(//div[@class = 'modal-header customer-modal-header']/i)[1]")
    WebElement confirm_delivery_page_close_icon;

    @FindBy(xpath = "//div[@id='DeliveryConfirmationModalNew']//div[@class='modal-content']")
    private WebElement delivery_confirmation_popup;

    // Confirmation delivery popup header
    @FindBy(xpath = "//div[@id='DeliveryConfirmationModalNew']//div[@class='modal-content']//h4")
    private WebElement confirmation_delivery_popup_header;

    @FindBy(xpath = "//div[@id='gvDeliveryDataNew']/div/table[@role='grid']/tbody/tr/td[2]")
    private List<WebElement> invoice_lists_in_confirmation_popup;

    @FindBy(xpath = "//div[@class='datepicker-days']//table//tr//td[@class='day' or @class='active day' or @class='today day']")
    private List<WebElement> listofdays_on_deliverydate_datepicker;

    @FindBy(css = "div[class='datepicker-days'] th[class='datepicker-switch']")
    private WebElement deliverydate_monthyear_on_deliveryconfirmationpage;

    @FindBy(css = "div[class='datepicker-days'] th[class='next']")
    private WebElement deliverydate_nextbutton_on_deliveryconfirmationpage;

//===================================== Confirmation Page Functions===================================
//    Confirmation Page Functions

    // Entering the Invoice Number and Click on Search button in Confirmation Pop-up
    public void Contains_Text_Input_Method_Delivery_Confirmation_Page(String Invoice) {
        HighlightElement(Confirmation_Contains_text_box);
        clickAndType(Confirmation_Contains_text_box, Invoice);
        click(Confirmation_Search_Button);
    }

    /**
     * Clicks the search button on delivery confirmation details popup
     *
     * @Author Balaji N
     */
    public void click_Search_Button_On_Delivery_Confirmation_Details() {
        click(Confirmation_Search_Button, "Search button on the delivery confirmation details popup");
    }

    public boolean Confirmation_Popup_Verification() {
        try {
            if (!isElementDisplayed(Confirmation_Pop_Up, "Confirmation pop-up on delivery confirmation popup")) {
                Allure.step("❌ Confirmation pop-up was not displayed on the screen.");
                return false;
            }
            if (!isElementDisplayed(Confirmation_Pop_Up_Title, "Confirmation pop-up title")) {
                Allure.step("❌ Confirmation pop-up appeared, but the title was missing.");
                return false;
            }

            return true;

        } catch (Exception e) {
            Allure.step(
                    "❌ Unable to verify Confirmation pop-up. " +
                            "It did not appear within the expected time or could not be located on the screen."
            );
            return false;
        }
    }


//    Extracting the number in the Confirmation pop-up

    public String Confirmation_pop_up_number_extract_method_confirmation_page() {
        String Paragraph = Confirmation_Pop_Up_Message.getText();
        Pattern pattern = Pattern.compile("\\d+"); // Matches one or more digits
        Matcher matcher = pattern.matcher(Paragraph);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }


    /**
     * Enter the confirmation code on the confirmation alert popup
     */
    public void Confirming_the_confirmation_pop_up() {
        ClickAndType(Confirmation_Pop_up_Text_Input_Field, Confirmation_pop_up_number_extract_method_confirmation_page(), "Confirmation code alert in delivery confirmation details popup");
        click(Confiramtion_pop_up_confirm_button, "Confirmation code textbox field in delivery confirmation details popup");
    }

    //    Verfying the invoice is displaying in the Delivery Confirmation Table
    public boolean verfiy_invoice_is_displaying_in_the_delivery_confirmation_table_confirmation_page(String invoice) {
        String Recipient_data = delivery_confirmation_single_invoice_recipient_column_element.getText();
        Pattern pattern = Pattern.compile("\\d+"); // Matches one or more digits
        Matcher matcher = pattern.matcher(Recipient_data);
        System.out.println(Recipient_data);
        if (matcher.find()) {
            String extractedInvoice = matcher.group(); // Extract the matched number
            System.out.println("Extracted Invoice: " + extractedInvoice);

            // Compare the extracted invoice with the provided invoice
            return extractedInvoice.equals(invoice);
        }

        // No number found, return false
        return false;
    }

    public List<String> extract_invoice_numbers_from_recipient_list() {
        List<String> invoiceNumbers = new ArrayList<>();

        for (WebElement recipient : list_of_recipient_delivery_confirmationpopup) {
            String text = recipient.getText(); // Get the recipient text
            String invoiceNumber = text.replaceAll("\\D+", ""); // Extract only numbers

            if (!invoiceNumber.isEmpty()) {
                invoiceNumbers.add(invoiceNumber); // Add the extracted invoice number
            }
        }
        return invoiceNumbers;
    }

    /**
     * Verify whether the respective invoice number is displayed
     *
     * @param invNum
     * @return
     */
    public boolean verify_InvoiceNumber_IsPresent_On_RecipientColumn(String invNum) {
        return extract_invoice_numbers_from_recipient_list().equals(invNum);
    }

    //    Delivery dropdown value select method in Confirmation Page

    /**
     * Select Delivery Code in Confirmation Popup
     *
     * @param DeliveryCode
     * @Author Balaji N
     * @Last-Modified-By : Sakrateesh R
     */
    public void select_delivery_code_Confirmation_Page(String DeliveryCode) {
        explicitWait(delivery_code_dropdown_field_confirmation_page);
        if (is_Element_Displayed(delivery_code_dropdown_field_confirmation_page, "Delivery Code Dropdown field")) {
            drop_Down(delivery_code_dropdown_field_confirmation_page, DeliveryCode, "VisibleText", "delivery code from delivery dropdown");
        }
    }

    /**
     * It retrieves the selected delivery code in confirmation popup
     *
     * @return If the delivery code is displayed returns value of displayed delivery code otherwise returns null
     * @Author Balaji N
     */
    public String get_selected_delivery_code_Confirmation_Page() {
        return get_Selected_Option(delivery_code_dropdown_field_confirmation_page, "delivery code dropdown field on delivery dropdown");
    }

    /**
     * Selects the driver code in delivery confirmation details popup
     *
     * @param DriverCode
     * @Author Balaji N
     */
    public void select_DriverCode_Delievry_Confirmation_Details_Popup(String DriverCode) {
        drop_Down(driver_dropdown_field_delivery_confirmation_details_popup, DriverCode, "VisibleText", "Driver code dropdown field on delivery confirmation details popup");
    }

    /**
     * It retrieves the selected driver code in delivery confirmation details popup
     *
     * @return If the driver code is displayed returns value of displayed driver code otherwise returns null
     * @Author Balaji N
     */
    public String get_Selected_DriverCode_Delievry_Confirmation_Details_Popup() {
        return get_Selected_Option(driver_dropdown_field_delivery_confirmation_details_popup, "Driver code dropdown field on delivery confirmation details popup");
    }

    /**
     * Clicks the Confirm Selected Deliveries button in Confirmation Popup
     *
     * @Author Balaji N
     */
    public void confirm_selected_deliveries_button_click_Confirmation_page() {
        Click(confirm_selected_delivereies_button, "Confirm Selected Deliveries button");
    }

//    Closing the Confirm Delivery Pop-up

    /**
     * Close icon on the confirm delivery pop-up
     *
     * @Author: Sakratesh R
     */
    public void confirm_delivery_page_close_icon_click() {
        js_Click(confirm_delivery_page_close_icon, "Close (x) icon");
    }

    /**
     * Enter the recipient invoice number in delivery confirmation popup
     *
     * @param RecipientInvoice
     * @Author Balaji N
     */
    public void Enter_RecipientInvoice_ConfirmationPage(String RecipientInvoice) {
        try {
            searchbox_Recipient_column.sendKeys(RecipientInvoice);
            searchbox_Recipient_column.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            printError(searchbox_Recipient_column, "Search Recipient Invoice textbox field on delivery confirmation popup", "Generic Error", e);
        }
    }

    /**
     * It retrieves the entered recipient invoice number in delivery confirmation popup
     *
     * @return If the invoice number is displayed returns value of displayed invoice number otherwise returns null
     * @Author Balaji N
     */
    public String get_entered_RecipientInvoice_ConfirmationPage() {
        String invoice;
        Highlight_Element(searchbox_Recipient_column, "Recipient Search Column");
        invoice = get_element_attribute(searchbox_Recipient_column, "Search Invoice field");
        return invoice;
    }

    /**
     * Verify Delivery Confirmation Header in Confirmation Page is displayed or not
     *
     * @return If th header is displayed returns true otherwise returns false
     * @Author Balaji N
     */
    public boolean Verify_Delivery_Confirmation_Header() {
        isElementDisplayed(delivery_confirmation_popup, "Delivery Confirmation popup");
        return is_Element_Displayed(confirmation_delivery_popup_header, "Delivery Confirmation popup Header");
    }

    public void Set_Current_Delivery_Date(String date) {
        Confirmation_Date_field.clear();
        Confirmation_Date_field.sendKeys(date);
        current_date_in_calendar.click();
    }

    /**
     * Select the date on the delivery confirmation details popup
     *
     * @param date
     * @Author Balaji N
     */
    public void Select_Date_On_DeliveryConfirmationDetails(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate targetDate = LocalDate.parse(date, formatter);

            int targetDay = targetDate.getDayOfMonth();
            String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();

            click(date_datepickerfield, "Date picker on delivery confirmation details popup");
            if (!deliveryconfirmation_date_picker.isDisplayed()) {
                click(date_datepickerfield, "Date picker on delivery confirmation details popup");
            }
            while (true) {
                String displayedMonthYear = getElementText(monthandyear_dateselection, "Date Month year on the delivery confirmation details datepicker field").trim();
                if (displayedMonthYear.equalsIgnoreCase(targetMonthYear)) {
                    break;
                }
                click(nextarrow_dateselection, "Date Picker Next Arrow or Button on the delivery confirmation details");
            }

            for (WebElement dayElement : listofdays_dateselection) {
                if (dayElement.isEnabled() && dayElement.getText().equalsIgnoreCase(String.valueOf(targetDay))) {
                    click(dayElement, "Date in the Calendar delivery confirmation details datepicker field");
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            printError(date_datepickerfield, "Date - date picker field on delivery confirmation details", "No Such Element Exception", e);
        }

    }


    /**
     * Select the To date on the delivery confirmation details popup
     *
     * @param date
     * @Author Balaji N
     */
    public void Select_ToDate_On_DeliveryConfirmationDetails(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate targetDate = LocalDate.parse(date, formatter);

            int targetDay = targetDate.getDayOfMonth();
            String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();

            click(to_date_picker_field, "Date picker on delivery confirmation details popup");
            if (!deliveryconfirmation_date_picker.isDisplayed()) {
                click(date_datepickerfield, "Date picker on delivery confirmation details popup");
            }
            while (true) {
                String displayedMonthYear = getElementText(monthandyear_dateselection, "Date Month year on the delivery confirmation details datepicker field").trim();
                if (displayedMonthYear.equalsIgnoreCase(targetMonthYear)) {
                    break;
                }
                click(nextarrow_dateselection, "Date Picker Next Arrow or Button on the delivery confirmation details");
            }

            for (WebElement dayElement : listofdays_dateselection) {
                if (dayElement.isEnabled() && dayElement.getText().equalsIgnoreCase(String.valueOf(targetDay))) {
                    click(dayElement, "Date in the Calendar delivery confirmation details datepicker field");
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            printError(date_datepickerfield, "To Date - date picker field on delivery confirmation details", "No Such Element Exception", e);
        }

    }

    public void Select_Confirmation_Date() {
        String App_Date = Confirmation_Date_field.getText();
        System.out.println(App_Date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // Adjust format if needed
        String formattedAppDate = App_Date.format(String.valueOf(formatter));
        String LocalCurrentDate = LocalDate.now().format(formatter);
        System.out.println(LocalCurrentDate);
        System.out.println(formattedAppDate);
        if (LocalCurrentDate.equals(formattedAppDate)) {
            System.out.println("Current date is displaying properly");
        } else {
            Confirmation_Date_Picker_icon.click();
            Set_Current_Delivery_Date(CurrentDate());
        }

    }

    /**
     * This method will verify whether the Invoice is displaying in the Confirmation pop-up or not.
     *
     * @param invoice
     * @return: true if Invoice is displayed else false
     * @Author: Sakrateesh R
     */
    public boolean Verify_Invoice_Is_Displayed_In_Confirmation_popup(String invoice) {

        for (WebElement Invoice : invoice_lists_in_confirmation_popup) {
            if (get_Element_Text(Invoice, "Confirmation Pop-up Invoice Row").contains(invoice)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method selects the date in the delivery date field in the Quick Dispatch pop-up
     *
     * @param deliveryDate
     * @Author: Balaji N
     */
    public void Select_Delivery_Date_on_Delivery_Confirmation_Page(String deliveryDate) {
        wait_For_Page_To_Be_Stable(getDriver());
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate targetDate = LocalDate.parse(deliveryDate, formatter);

            int targetDay = targetDate.getDayOfMonth();
            String targetMonthYear = targetDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + targetDate.getYear();

            click(to_date_picker_field, "delivery Date in the quick dispatch pop-up");

            while (true) {
                String displayedMonthYear = getElementText(deliverydate_monthyear_on_deliveryconfirmationpage, "Delivery Date Month year on the Quick dispatch").trim();
                if (displayedMonthYear.equalsIgnoreCase(targetMonthYear)) {
                    break;
                }
                click(deliverydate_nextbutton_on_deliveryconfirmationpage, "Delivery Date Picker Next Button on the Dispatch pop-up");
            }

            for (WebElement dayElement : listofdays_on_deliverydate_datepicker) {
                if (dayElement.isEnabled() &&
                        dayElement.getText().equalsIgnoreCase(String.valueOf(targetDay))) {
                    click(dayElement, "Date in the Calendar Date Pickup in Dispatch pop-up");
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            printError(to_date_picker_field, "Delivery Date Field on Quick Dispatch popup", "No Such Element Exception", e);
        }

    }

    public void set_Delivery_Notes(String delivery_notes) {
        ClickAndType(delivery_notes_field_deliveryconfirmation_page, delivery_notes, "Delivery Notes field on delivery confirmation page");
    }

    public String get_Delivery_Notes() {
        return get_element_attribute(delivery_notes_field_deliveryconfirmation_page, "Delivery Notes field on delivery confirmation page");
    }

    public void click_Delivery_Time_Field() {
        click(delivery_time_field_deliveryconfirmation_page, "Delivery Time field on delivery confirmation page");
    }

    public void select_Delivery_Time(String time) {
        for (WebElement timeOption : list_of_time_on_timepicker) {
            if (timeOption.getText().trim().equals(time)) {
                click(timeOption, "Delivery Time option: " + time);
                break;
            }
        }
    }




}
