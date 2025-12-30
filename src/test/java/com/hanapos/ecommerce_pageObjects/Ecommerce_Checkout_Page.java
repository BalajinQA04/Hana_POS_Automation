package com.hanapos.ecommerce_pageObjects;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import io.qameta.allure.Allure;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * Ecommerce Checkout page class in the bestflorist pos application - bestflorist ecommerce website
 *
 * @Author Balaji N
 */
public class Ecommerce_Checkout_Page extends TestBaseClass {
    public Ecommerce_Checkout_Page() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "(//h4[@class='info-text'])[1]")
    private WebElement checkout_page_heading;

    @FindBy(id = "ddlOccasion")
    private WebElement cart_tab_Occasion_dropdown;

    @FindBy(id = "txtEmail")
    private WebElement cart_tab_email_textbox;

    @FindBy(xpath = "//textarea[@id='txtCardMessage']")
    private WebElement cart_tab_message_textarea;

    @FindBy(xpath = "//div[@class='DivCardMessage']")
    private List<WebElement> list_of_card_message_textarea;

    @FindBy(xpath = "//button[@id='btnCustomNext']")
    private WebElement cart_tab_Continue_toDelivery_PickUp_button;


    //===================== Recipient Information =====================

    @FindBy(id = "spnDelConfim")
    private WebElement recipient_information_subtitle;

    @FindBy(xpath = "//input[@id='txtFirstName']")
    private WebElement recipient_first_name_textbox;

    @FindBy(xpath = "//input[@id='txtLastName']")
    private WebElement recipient_last_name_textbox;

    @FindBy(xpath = "//input[@id='txtPhoneNumber']")
    private WebElement recipient_phone_number_textbox;

    @FindBy(xpath = "//input[@id='txtCompany']")
    private WebElement business_name_textbox;

    @FindBy(xpath = "(//div[@class='pac-container pac-logo hdpi'])[2]//div//span[2]")
    private List<WebElement> list_of_businessName_googleAutoComplete;

    @FindBy(xpath = "(//div[@class='pac-container pac-logo hdpi'])[2]//div//span[3]")
    private List<WebElement> list_of_business_address_street_Country_googleAutoComplete;

    @FindBy(xpath = "//input[@id='txtStreetAddress']")
    private WebElement recipient_street_address_textbox;

    @FindBy(xpath = "//select[@id='ddlDeliveryCountry']")
    private WebElement recipient_country_dropdown;

    @FindBy(xpath = "//select[@id='ddlState']")
    private WebElement recipient_state_dropdown;

    @FindBy(xpath = "//input[@id='txtCity']")
    private WebElement recipient_city_textbox;

    @FindBy(xpath = "//input[@id='txtZipCode']")
    private WebElement recipient_zip_code_textbox;

    @FindBy(xpath = "//input[@id='txtDeliveryDate']")
    private WebElement recipient_delivery_date_textbox;

    @FindBy(xpath = "//button[@id='btnCustomNextbottom']")
    private WebElement continue_to_payment_button;

    @FindBy(xpath = "//p[@id='DisplayMessage']")
    private WebElement display_message_popup;

    @FindBy(xpath = "//div[@class='user signinBx']")
    private WebElement modal_popup;

    @FindBy(xpath = "//button[@class='close-btn']//span[normalize-space()='×']")
    private WebElement modal_popup_close_icon;

    @FindBy(xpath = "(//div[@class='modal-content'])[1]")
    private WebElement display_message_popup_content;

    @FindBy(xpath = "(//button[@class='close'])[1]")
    private WebElement display_message_popup_close_button;

    @FindBy(xpath = "//h4[contains(text(),'Payment Information ')]")
    private WebElement payment_information_subtitle;

    @FindBy(xpath = "//select[@id='ddlDeliveryLocation']")
    private WebElement select_delivery_location_dropdown;

    @FindBy(xpath = "//div[@id='dropdownValueMain']")
    private WebElement expedited_delivery_fee_dropdown;

    @FindBy(xpath = "//div[@id='dropdownValueMain']//ul//li[2]")
    private WebElement expedited_delivery_fee_dropdown_option;

    @FindBy(xpath = "//textarea[@id='txtFloristInstruction']")
    private WebElement florist_instruction_textarea;

    @FindBy(xpath = "//span[text()='Product Total']/following::div[1]")
    private WebElement product_total_price;

    @FindBy(xpath = "//span[text()='Delivery Cost']/following::div[1]")
    private WebElement delivery_cost_price;

    @FindBy(xpath = "//span[text()='Expedited Delivery Fees']/following::div[1]")
    private WebElement expedited_delivery_fee_price;

    @FindBy(xpath = "//div[@id='taxDivTemp']//span[text()='Total Tax']/following::div[1]")
    private WebElement total_tax;

    @FindBy(xpath = "//span[text()='Grand Total']/following::div[1]")
    private WebElement grand_total_price;
    //==================== Payments Info elements ==================

    @FindBy(id = "ddlPaymentMethod")
    private WebElement payment_method_dropdown;

    @FindBy(xpath = "//input[@id='txtNameOnCard']")
    private WebElement name_on_card_textbox;

    @FindBy(xpath = "//input[@id='txtCardNumber']")
    private WebElement card_number_textbox;

    @FindBy(xpath = "//input[@id='ddlExpiryDate']")
    private WebElement expiry_date_textbox;

    @FindBy(xpath = "//input[@id='txtCVC']")
    private WebElement ccvc_textbox;

    //============ Billing Info elements ==================

    @FindBy(xpath = "//h4[contains(text(),'Your Billing Information ')]")
    private WebElement billing_information_subtitle;

    @FindBy(xpath = "//input[@id='txtBillingFirstName']")
    private WebElement billing_first_name_textbox;

    @FindBy(xpath = "//input[@id='txtBillingLastName']")
    private WebElement billing_last_name_textbox;

    @FindBy(xpath = "//input[@id='txtBillingPhoneNumber']")
    private WebElement billing_phone_number_textbox;

    @FindBy(xpath = "//input[@id='txtBillingStreetAddress']")
    private WebElement billing_street_address_textbox;

    @FindBy(xpath = "//select[@id='ddlBillingCountry']")
    private WebElement billing_country_dropdown;

    @FindBy(xpath = "//select[@id='ddlBillingState']")
    private WebElement billing_state_dropdown;

    @FindBy(xpath = "//input[@id='txtBillingCity']")
    private WebElement billing_city_textbox;

    @FindBy(xpath = "//input[@id='txtBillingZipCode']")
    private WebElement billing_zip_code_textbox;

    @FindBy(xpath = "//span[text()='Product Total']/preceding::button[text()='Continue to Payment']")
    private WebElement continue_to_payment_button_on_payment_tab_section;

    @FindBy(xpath = "//iframe[@name='submit']")
    private WebElement continue_to_review_button;

    @FindBy(css="#btnCustomNext")
    private WebElement continue_to_review_button_invoicehouseaccount;

    @FindBy(xpath = "//h4[contains(text(),'Review')]")
    private WebElement review_information_subtitle;

    @FindBy(xpath = "//input[@id='chkSubstitution']")
    private WebElement substitution_policy_checkbox;

    @FindBy(xpath = "//span[text()='Product Total']/preceding::button[text()='Place your Order'][1]")
    private WebElement place_order_button;

    @FindBy(css = ".toast-message")
    private WebElement toast_message;

    @FindBy(xpath = "//h1[contains(text(),'Order Confirmation')]")
    private WebElement order_confirmation_heading;

    @FindBy(css = "#NotePreview")
    private WebElement cardmessage_Displayed_on_review_page;

    @FindBy(css = "#divEmail")
    private WebElement emailid_Displayed_on_review_page;

    @FindBy(css = "div#divRecipientInfo p")
    private WebElement recipientinfo_Displayed_on_review_page;

    @FindBy(css = "div#DivBillingInfo p")
    private WebElement customerinfo_Displayed_on_review_page;

    @FindBy(css = "#DivPayInfo p")
    private WebElement creditcardinfo_Displayed_on_review_page;

    @FindBy(css = "#divDeliveryDateReview b")
    private WebElement deliverydate_Displayed_on_review_page;

    @FindBy(css = "#SpecialInstructionPreview")
    private WebElement florist_or_delivery_instruction_Displayed_on_review_page;


    /**
     * Validate whether the checkout page is displayed or not
     *
     * @return Identify whether the checkout page is displayed or not
     * @Author: Balaji N
     */
    public boolean Verify_CheckoutPage() {
        wait_For_Page_To_Be_Stable(getDriver());
        return isElementDisplayed(checkout_page_heading, "Checkout page heading");
    }

    /**
     * Select the occasion on cart tab in checkout page
     *
     * @param occasion given occasion to be selected
     * @Author: Balaji N
     */
    public void Select_Occasion(String occasion) {
        drop_Down(cart_tab_Occasion_dropdown, occasion, "VisibleText", "Occasion dropdown field on checkout page - cart tab");
    }

    /**
     * It retrieves the selected occasion on cart tab in checkout page
     *
     * @return If  occasion selected value is displayed on checkout page it returns the value of occasion selected value, otherwise it returns null string
     * @Author: Balaji N
     */
    public String get_selected_occasion_on_cartTab_checkoutPage() {
        return get_selected_option(cart_tab_Occasion_dropdown, "Occasion dropdown field value on checkout page - cart tab");
    }

    /**
     * Enters the email on cart tab in checkout page
     *
     * @param email The given email to be entered
     * @Author: Balaji N
     */
    public void Enter_Email_on_cartTab_checkoutPage(String email) {
        ClickAndType(cart_tab_email_textbox, email, "Email textbox field on checkout page - cart tab");
    }

    /**
     * It retrieves the entered email on cart tab in checkout page
     *
     * @return If email entered value is displayed on checkout page it returns the value of email entered value, otherwise it returns null string
     * @Author: Balaji N
     */
    public String get_entered_email_on_cartTab_checkoutPage() {
       /* HighlightElement(cart_tab_email_textbox);
        return cart_tab_email_textbox.getAttribute("value");*/
        return get_element_attribute(cart_tab_email_textbox, "Email textbox field value on checkout page - cart tab");
    }

    /**
     * Enters the card message on cart tab in checkout page
     *
     * @param message The given message to be entered
     * @Author: Balaji N
     */
    public void Enter_card_message_on_cartTab_checkoutPage(String message) {
        ClickAndType(cart_tab_message_textarea, message, "Card message textbox field on checkout page - cart tab");
    }

    /**
     * It retrieves the entered card message on cart tab in checkout page
     *
     * @return If card message entered value is displayed on checkout page it returns the value of card message entered value, otherwise it returns null string
     * @Author: Balaji N
     */
    public String get_entered_card_message_on_cartTab_checkoutPage() {
       /* HighlightElement(cart_tab_message_textarea);
        return cart_tab_message_textarea.getAttribute("value").trim();*/
        return get_element_attribute_with_trim(cart_tab_message_textarea, "Card message textbox field value on checkout page - cart tab");
    }

    /**
     * Clicks the card message from suggestion on cart tab in checkout page
     *
     * @param suggestion_card_message The given suggestion card message to be clicked
     * @Author: Balaji N
     */
    public void Click_card_message_from_Suggestion(String suggestion_card_message) {
        for (int i = 0; i < list_of_card_message_textarea.size(); i++) {
            if (list_of_card_message_textarea.get(i).getText().equalsIgnoreCase(suggestion_card_message)) {
                js_Click(list_of_card_message_textarea.get(i), "Card message from suggestion on cart tab in checkout page");
                break;
            }
        }
    }

    /**
     * Clicks the continue to delivery pick up button on cart tab in checkout page
     * <p>
     *
     * @Author: Balaji N
     */
    public void Click_cart_tab_Continue_toDelivery_PickUp_button() {
        jsClick(cart_tab_Continue_toDelivery_PickUp_button, "Clicking on continue to payment button");
    }

    /**
     * Validates whether the recipient information subtitle is displayed or not
     *
     * @return If recipient information subtitle is displayed then true else false
     * @Author: Balaji N
     */
    public boolean verify_recipient_information_subtitle() {
        wait_For_Page_To_Be_Stable(getDriver());
        return isElementDisplayed(recipient_information_subtitle, "Recipient information subtitle");
    }

    /**
     * Enters the recipient first name on recipient information section in checkout page
     *
     * @param first_name The given first name to be entered
     * @Author: Balaji N
     */
    public void Enter_recipient_first_name(String first_name) {
        clickAndType(recipient_first_name_textbox, first_name);
    }

    /**
     * It retrieves the entered recipient first name on recipient information section in checkout page
     *
     * @return If recipient first name entered value is displayed on checkout page it returns the value of recipient first name entered value, otherwise it returns null string
     * @Author Balaji N
     */
    public @Nullable String get_entered_firstname_on_recipient_information_section() {
        HighlightElement(recipient_first_name_textbox);
        return recipient_first_name_textbox.getAttribute("value");
    }

    /**
     * Enters the recipient last name on recipient information section in checkout page
     *
     * @param last_name The given last name to be entered
     * @Author: Balaji N
     */
    public void Enter_recipient_last_name(String last_name) {
        clickAndType(recipient_last_name_textbox, last_name);
    }

    /**
     * It retrieves the entered recipient last name on recipient information section in checkout page
     *
     * @return If recipient last name entered value is displayed on checkout page it returns the value of recipient first name entered value, otherwise it returns null string
     * @Author Balaji N
     */
    public @Nullable String get_entered_lastname_on_recipient_information_section() {
        HighlightElement(recipient_last_name_textbox);
        return recipient_last_name_textbox.getAttribute("value");
    }

    /**
     * Enters the recipient phone number on recipient information section in checkout page
     *
     * @param phone_number The given phone number to be entered
     * @Author: Balaji N
     */
    public void Enter_recipient_phone_number(String phone_number) {
        clickAndType(recipient_phone_number_textbox, phone_number);
    }

    /**
     * It retrieves the entered recipient phone number on recipient information section in checkout page
     *
     * @return If recipient phone number entered value is displayed on checkout page it returns the value of recipient phone number entered value, otherwise it returns null string
     * @Author Balaji N
     */
    public @Nullable String get_entered_phone_number_on_recipient_information_section() {
        HighlightElement(recipient_phone_number_textbox);
        return recipient_phone_number_textbox.getAttribute("value");
    }

    public void Search_and_select_with_BusinessName(String businessName) {
        ClickAndType(business_name_textbox, businessName, "Business name textbox field on checkout page");
        delayWithGivenTime(3000);
        By addressfirstsuggestion = By.xpath("(//div[@class='pac-item' and contains(normalize-space(),'" + businessName + "')])[1]");
        WebElement addressfirstsuggestion1 = getDriver().findElement(addressfirstsuggestion);
        click(addressfirstsuggestion1, "Business name suggestion on checkout page");
    }


    /**
     * Validates whether the message popup is displayed or not
     *
     * @return If message popup is displayed then true else false
     * @Author: Balaji N
     */
    public boolean Verify_Message_Popup_isDisplay() {
        return isElementDisplayed(modal_popup, "Message Popup");
    }

    public void close_Message_Popup_isDisplay() {
        if (isElementDisplayed(modal_popup, "Message Popup")) {
            js_Click(modal_popup_close_icon, "Close message popup");
        }
    }

    /**
     * Clicks the close icon on displayed message popup
     *
     * @Author: Balaji N
     */
    public void Click_on_outside_from_messagePopup() {
        jsClick(display_message_popup_close_button);
    }

    /**
     * It retrieves the displayed street address on recipient information section in checkout page
     *
     * @return If street address is displayed on checkout page it returns the displayed street address value, otherwise it returns null string
     * @Author: Balaji N
     */
    public @Nullable String get_displayed_street_address() {
        HighlightElement(recipient_street_address_textbox);
        return recipient_street_address_textbox.getAttribute("value");
    }

    public String get_selected_country() {
        HighlightElement(recipient_country_dropdown);
        Select select = new Select(recipient_country_dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String get_selected_state() {
        HighlightElement(recipient_state_dropdown);
        Select select = new Select(recipient_state_dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public @Nullable String get_displayed_city() {
        HighlightElement(recipient_city_textbox);
        return recipient_city_textbox.getAttribute("value");
    }

    public @Nullable String get_displayed_zip_code() {
        HighlightElement(recipient_zip_code_textbox);
        return recipient_zip_code_textbox.getAttribute("value");
    }

    public void Enter_delivery_date(String delivery_date) {
        jsDatePicker(recipient_delivery_date_textbox, delivery_date);
        delayWithGivenTime(500);
        recipient_delivery_date_textbox.sendKeys(Keys.ENTER);
    }

    public boolean Verify_Payment_Information_Tab_isDisplayed() {
       /* HighlightElement(payment_information_subtitle);
        return payment_information_subtitle.isDisplayed();*/
        return isElementDisplayed(payment_information_subtitle, "Payment information subtitle");
    }

    public void Select_Payment_Method(String payment_method) {
        dropDown(payment_method_dropdown, payment_method, "VisibleText");
    }

    public String get_selected_payment_method() {
        HighlightElement(payment_method_dropdown);
        Select select = new Select(payment_method_dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public void Enter_Name_of_Card_on_Payment_Info(String name_of_card) {
        clickAndType(name_on_card_textbox, name_of_card);
    }

    public @Nullable String get_entered_name_of_card_on_Payment_Info() {
        HighlightElement(name_on_card_textbox);
        return name_on_card_textbox.getAttribute("value");
    }

    public void Enter_Card_Number_on_Payment_Info(String card_number) {
        fluentWait(card_number_textbox);
        clickAndType(card_number_textbox, card_number);
    }

    public @Nullable String get_entered_card_number_on_Payment_Info() {
        HighlightElement(card_number_textbox);
        return card_number_textbox.getAttribute("value");
    }

    public void Enter_expiry_date_on_Payment_Info(String expiry_date) {
        clickAndType(expiry_date_textbox, expiry_date);
    }

    public @Nullable String get_entered_expiry_date_on_Payment_Info() {
        HighlightElement(expiry_date_textbox);
        return expiry_date_textbox.getAttribute("value");
    }

    public void Enter_CVV_on_Payment_Info(String cvv) {
        clickAndType(ccvc_textbox, cvv);
    }

    public @Nullable String get_entered_CVV_on_Payment_Info() {
        HighlightElement(ccvc_textbox);
        return ccvc_textbox.getAttribute("value");
    }

    public boolean verify_billing_information_subtitle_isDisplayed() {
        HighlightElement(billing_information_subtitle);
        return billing_information_subtitle.isDisplayed();
    }

    public void enterBillingFirstName(String firstName) {
        clickAndType(billing_first_name_textbox, firstName);
    }

    public void enterBillingLastName(String lastName) {
        clickAndType(billing_last_name_textbox, lastName);
    }

    public void enterBillingPhoneNumber(String phoneNumber) {
        clickAndType(billing_phone_number_textbox, phoneNumber);
    }

    public void enterBillingStreetAddress(String streetAddress) {
        billing_street_address_textbox.click();
        delayWithGivenTime(1000);
        billing_street_address_textbox.clear();
        delayWithGivenTime(2000);
        // billing_street_address_textbox.sendKeys(streetAddress);
        clickAndType(billing_street_address_textbox, streetAddress);
    }

    public void selectBillingCountry(String country) {
        dropDown(billing_country_dropdown, country, "VisibleText");
    }

    public void selectBillingState(String state) {
        dropDown(billing_state_dropdown, state, "VisibleText");
    }

    public void enterBillingCity(String city) {
        clickAndType(billing_city_textbox, city);
    }

    public void enterBillingZipCode(String zipCode) {
        clickAndType(billing_zip_code_textbox, zipCode);
    }

    public String getBillingFirstName() {
        return billing_first_name_textbox.getAttribute("value");
    }

    public String getBillingLastName() {
        return billing_last_name_textbox.getAttribute("value");
    }

    public String getBillingPhoneNumber() {
        return billing_phone_number_textbox.getAttribute("value");
    }

    public String getBillingStreetAddress() {
        return billing_street_address_textbox.getAttribute("value");
    }

    public String getBillingCountry() {
        HighlightElement(billing_country_dropdown);
        Select select = new Select(billing_country_dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String getBillingState() {
        HighlightElement(billing_state_dropdown);
        Select select = new Select(billing_state_dropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String getBillingCity() {
        return billing_city_textbox.getAttribute("value");
    }

    public String getBillingZipCode() {
        return billing_zip_code_textbox.getAttribute("value");
    }

    public void Click_continue_to_review_button() {
        click(continue_to_payment_button_on_payment_tab_section, "Continue to Payment button on payment section");
    }

    public void click_ContinueToReview_Button(){
        js_Scroll_And_NormalClick(continue_to_review_button_invoicehouseaccount, "Continue to Review button on payment section");
    }


    public void click_Continue_To_Review_Button_On_Payment_Section() {
        try {
            // getDriver().switchTo().frame(continue_to_review_button);
            js_Scroll_And_NormalClick(continue_to_review_button, "Continue to Review button on payment section");
            //getDriver().switchTo().defaultContent();
        } catch (NoSuchFrameException ex) {
            String message = "Payment review step failed: Unable to switch to payment iframe. Please check if the frame index is correct and the frame is present on the page.";
            Allure.step(message);
        } catch (NoSuchElementException ex) {
            String message = "Payment review step failed: Continue to Review button not found in the payment section. Please verify the selector and page state.";
            Allure.step(message);
        } catch (ElementClickInterceptedException ex) {
            String message = "Payment review step failed: Could not click on the Continue to Review button. The button may be hidden, disabled, or covered by another element.";
            Allure.step(message);
        } catch (Exception ex) {
            String message = "Payment review step failed: Unexpected error while clicking Continue to Review button on payment section.\nDetails: " + ex.getMessage();
            Allure.step(message);
        }
    }


    public boolean Verify_Review_Information_Tab_isDisplayed() {
        wait_For_Page_To_Be_Stable(getDriver());
        return isElementDisplayed(review_information_subtitle, "Review Information subtitle");
    }

    public void Click_Agree_Substitution_Checkbox() {
        js_Click(substitution_policy_checkbox, "Agree substitution policy checkbox");
    }

    public void Click_Place_Your_Order_Button() {
        js_Click(place_order_button, "Place your order button");
    }

    public boolean Verify_Order_Confirmation_Message() {
        wait_For_Page_To_Be_Stable(getDriver());
        return isElementDisplayed(order_confirmation_heading, "Order confirmation heading");
    }

    public void select_Delivery_Location_Dropdown(String location) {
        drop_Down(select_delivery_location_dropdown, location, "VisibleText", "Delivery Location dropdown field on checkout page");
    }

    public boolean is_Expedited_Delivery_Fee_Dropdown_Displayed() {
        return isElementDisplayed(expedited_delivery_fee_dropdown, "Expedited Delivery Fee dropdown field on checkout page");
    }

    public void select_Expedited_Delivery_Fee() {
        click(expedited_delivery_fee_dropdown, "Expedited Delivery Fee dropdown field on checkout page");
        delayWithGivenTime(1000);
        click(expedited_delivery_fee_dropdown_option, "Expedited Delivery Fee option on checkout page");
    }

    public void enter_Florist_Instructions(String floristInstructions) {
        if (isElementDisplayed(florist_instruction_textarea, "Florist Instructions field on checkout page")) {
            {
                click(florist_instruction_textarea, "Florist Instructions field on checkout page");
            }
            delayWithGivenTime(1000);
            ClickAndType(florist_instruction_textarea, floristInstructions, "Florist Instructions field on checkout page");

        }
    }

    public String get_Displayed_Product_Total() {
        is_Element_Displayed(product_total_price, "Product Total on checkout page");
        return getElementText(product_total_price, "Product Total on checkout page");
    }

    public String get_Displayed_Delivery_Cost() {
        return getElementText(delivery_cost_price, "Delivery Cost on checkout page");
    }

    public String get_Displayed_Expedited_Delivery_Cost() {
        return getElementText(expedited_delivery_fee_price, "Expedited Delivery Cost on checkout page");
    }

    public String expected_Grand_Total() {
        double productTotal = Double.parseDouble(get_Displayed_Product_Total());
        double deliveryCost = Double.parseDouble(get_Displayed_Delivery_Cost());
        double expeditedDeliveryCost = Double.parseDouble(get_Displayed_Expedited_Delivery_Cost());
        //  double totalTax = Double.parseDouble(get_Displayed_Total_Tax());

        double grandTotal = productTotal + deliveryCost + expeditedDeliveryCost; //+ totalTax
        System.out.println("Calculated Grand Total: " + grandTotal);
        return String.valueOf(grandTotal);
    }


    public String get_Displayed_Total_Tax() {
        return getElementText(total_tax, "Total Tax on checkout page");
    }

    public String get_Displayed_Grand_Total() {
        return getElementText(grand_total_price, "Grand Total on checkout page");
    }

    public String get_Displayed_CardMessage_on_ReviewPage() {
        return getElementText(cardmessage_Displayed_on_review_page, "Card message displayed on review page");
    }

    public String get_Displayed_EmailID_on_ReviewPage() {
        return getElementText(emailid_Displayed_on_review_page, "Email ID displayed on review page");
    }

    public String get_Displayed_RecipientInfo_on_ReviewPage() {
        return getElementText(recipientinfo_Displayed_on_review_page, "Recipient info displayed on review page");
    }

    public String get_Displayed_Customer_Information_on_ReviewPage() {
        return getElementText(customerinfo_Displayed_on_review_page, "Customer information displayed on review page");
    }

    public String get_Displayed_CreditCardInfo_on_ReviewPage() {
        return getElementText(creditcardinfo_Displayed_on_review_page, "Credit card information displayed on review page");
    }

    public static String convertDisplayedDateTo_MMddyyyy(String displayedDateWithWeekday, int year) {
        // Remove weekday and newline - extract actual date part ("Sept 2" or "Sep 2")
        // Assuming the format always starts with weekday and newline, then date part
        // Example input: "Tuesday\nSept 2 2025"

        // Extract the date portion after newline
        String[] parts = displayedDateWithWeekday.split("\n");
        String datePart = parts.length > 1 ? parts[1].trim() : displayedDateWithWeekday.trim();

        // Normalize "Sept" to "Sep" for parsing compatibility
        datePart = datePart.replace("Sept", "Sep");

        // Now append the year
        String fullDate = datePart + " " + year; // e.g. "Sep 2 2025"

        // Define input formatter for 3-letter month and day with year
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);

        LocalDate date = LocalDate.parse(fullDate, inputFormat);

        // Define output format MM/dd/yyyy
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        return date.format(outputFormat);
    }


    public String get_Displayed_DeliveryDate_on_ReviewPage() {
        return getElementText(deliverydate_Displayed_on_review_page, "Delivery date displayed on review page");
    }

    public String get_Displayed_FloristOrDeliveryInstruction_on_ReviewPage() {
        return getElementText(florist_or_delivery_instruction_Displayed_on_review_page, "Florist or delivery instruction displayed on review page");
    }

    public String get_toast_message_text() {
        try {
            return getElementText(toast_message, "Toast message");
        } catch (Exception e) {
            String errorMsg = "⚠️ Could not retrieve the toast message text. The toast message may not be present or accessible at this time.";
            Allure.step("Toast Message Retrieval Error: "+ errorMsg + "\nDetails: " + e.getMessage());
            return "";  // or return null depending on your preference
        }
    }



}