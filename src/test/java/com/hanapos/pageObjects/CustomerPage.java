package com.hanapos.pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * CustomerPage class is a page object class for Customer page
 * It contains all the web elements of Customer page and thier interactions functions
 *
 * @Author Balaji N
 */
public class CustomerPage extends TestBaseClass {
    public CustomerPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //================================================ Customer Page WebElements ============================
    @FindBy(xpath = "(//div[@id='CustomerDetailModal']//div[@class='modal-content ModalBodyHeight'])[1]")
    private WebElement customer_details_popup;

    @FindBy(xpath = "//h2[@class='set-text-heading']")
    private WebElement CustomerMenuHeading;

    @FindBy(xpath = "//button[contains(text(),'New Customer')]")
    private WebElement new_customer_button;

    @FindBy(xpath = "//h4[@id='AddNewCustomerModalTitle' or normalize-space(.)='Add New Customer']")
    private WebElement add_new_customer_popup_header;

    @FindBy(xpath = "//th[@data-field='CustomerId']")
    private WebElement CustomerIdColumn_header;

    @FindBy(xpath = "//span[@class='k-icon k-i-sort-desc-sm']")
    private WebElement descendingIconOnCustomerIdColumn;

    @FindBy(xpath = "//span[@class='k-icon k-i-sort-asc-sm']")
    private WebElement ascendingIconOnCustomerIdColumn;

    @FindBy(xpath = "//input[@data-text-field='CustomerName']")
    private WebElement CustomerNameSearchBox;

    @FindBy(xpath = "//td[4]")
    private List<WebElement> CustomerId_List_OnCustTable;

    @FindBy(xpath = "//input[@data-text-field='CustomerId']")
    private WebElement CustomerIdSearchBox;

    @FindBy(xpath = "(//input[@data-text-field='CityStateZip'])[1]")
    private WebElement CustomerCityStateZipSearchBox;

    @FindBy(xpath = "(//input[@data-text-field='Cust_phone'])")
    private WebElement CustomerPhoneSearchBox;

    @FindBy(xpath = "(//input[@data-text-field='Cust_address1'])")
    private WebElement CustomerAddressSearchBox;

    @FindBy(xpath = "(//ul[@class='k-list k-reset'])//li")
    private List<WebElement> CustomerList;

    @FindBy(xpath = "(//tr[@role='row'])[2]//td[6]")
    private WebElement CompanyNameOnCustTable;

    @FindBy(xpath = "(//tr[@role='row'])[2]//td[7]")
    private WebElement PhoneNumberOnCustTable;

    @FindBy(xpath = "(//tr[@role='row'])[2]//td[8]")
    private WebElement AltPhoneNumberOnCustTable;

    @FindBy(xpath = "(//tr[@role='row'])[2]//td[9]")
    private WebElement AddressOnCustTable;

    @FindBy(xpath = "(//tr[@role='row'])[2]//td[10]")
    private WebElement CityStateZipCodeOnCustTable;

    @FindBy(xpath = "(//div[@id='gvCustomers']//table)[2]//td[5]")
    private WebElement CustomerTableRow1;

    @FindBy(xpath = "//td[@class='rowEvent hana-invoice-column set-customer-font']")
    private List<WebElement> ListOfCustomerID_OnCustomerTableRow;

    @FindBy(xpath = "//select[@data-role='dropdownlist']")
    private WebElement CustomerDetails_PaginationDropDown;

    @FindBy(xpath = "//div[@id='page-wrapper']//h4[@id='Title']")
    private WebElement CustomerDetailsPopupHeading;

    @FindBy(xpath = "(//div[@Class='modal-header customer-modal-header']//a//i)[1]")
    private WebElement CustomerDetailsPopupCloseIcon;

    @FindBy(xpath = "(//input[@class='i-checks checkbox-all-commongrid'])[1]")
    private WebElement CustomerTableAllCheckBox;

    @FindBy(xpath = "(//span[@class='fa fa-bars fa-1x fa-font-style'])[1]")
    private WebElement CustomerActionbutton;

    @FindBy(xpath = "//a[normalize-space()='Delete']")
    private WebElement CustomerDeleteButton;

    @FindBy(xpath = "//div[@id='page-wrapper']//input[@id='txtCompanyName']")
    private WebElement CustDetailsCompanyNameTextBox;

    @FindBy(xpath = "(//input[@id='txtFirstName'])[1]")
    private WebElement CustDetailsFirstNameTextBox;

    @FindBy(xpath = "(//input[@id='txtLastName'])[1]")
    private WebElement CustDetailsLastNameTextBox;

    @FindBy(xpath = "(//input[@id='txtAddress1'])[1]")
    private WebElement CustDetailsAddress1TextBox;

    @FindBy(xpath = "(//input[@id='txtAddress2'])[1]")
    private WebElement CustDetailsAddress2TextBox;

    @FindBy(xpath = "(//input[@id='txtCity'])[1]")
    private WebElement CustDetailsCityTextBox;

    @FindBy(xpath = "(//input[@id='txtState'])[1]")
    private WebElement CustDetailsStateTextBox;

    @FindBy(xpath = "(//input[@id='txtZip'])[1]")
    private WebElement CustDetailsZipCodeTextBox;

    @FindBy(xpath = "(//input[@id='txtCountry'])[1]")
    private WebElement CustDetailsCountryTextBox;

    @FindBy(xpath = "//select[@id='ddlAddCustomerType']")
    private WebElement CustDetailsCustomerTypeDropdown;

    @FindBy(xpath = "(//input[@id='txtPhoneNumber'])[1]")
    private WebElement CustDetailsPhoneNumberTextBox;

    @FindBy(xpath = "(//input[@id='txtAltPhone'])[1]")
    private WebElement CustDetailsAltPhoneNumberTextBox;

    @FindBy(xpath = "(//input[@id='txtEmail'])[1]")
    private WebElement CustDetailsEmailTextBox;

    @FindBy(xpath = "(//input[@id='txtStoreCredit'])[1]")
    private WebElement CustDetailsStoreCreditTextBox;

    @FindBy(xpath = "(//select[@id='ddlCustomerType'])[1]")
    private WebElement updatecustdetails_customerType_dropdown;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[1]")
    private WebElement CustDetailsCreditApprovedToogle;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[2]")
    private WebElement CustDetailsEmailSettingToogle;

    @FindBy(xpath = "(//span[@class='switchery switchery-default'])[3]")
    private WebElement CustDetailsLateFeeSettingToogle;

    @FindBy(xpath = "(//span[contains(@class,'switchery switchery-default')])[5]")
    private WebElement CustDetailsPaperlessStatementToogle;

    @FindBy(xpath = "(//span[contains(@class,'switchery switchery-default')])[6]")
    private WebElement CustDetailsEnableLoyaltyToogle;

    @FindBy(xpath = "(//span[contains(@class,'switchery switchery-default')])[7]")
    private WebElement CustDetailsAllowFeedbackEmailToogle;

    @FindBy(xpath = "//div[@id='page-wrapper']//select[@id='ddlCustomerType']")
    private WebElement CustDetailsCustomerTypeDropDown;

    @FindBy(xpath = "(//select[@id='CustomerSrcCode'])[1]")
    private WebElement CustDetailsCustomerSourceDropDown;

    @FindBy(xpath = "(//textarea[@id='txtCustNotes'])[1]")
    private WebElement update_custdetails_customer_notes;

    @FindBy(xpath = "(//a[@id='btnUpdateCustomerDetails'])[1]")
    private WebElement CustDetailsUpdateButton;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr[last()]//td[4]")
    private WebElement CustTable_CustomerID_LastRow;

    @FindBy(xpath = "//tbody[@role='rowgroup']//tr[1]//td[4]")
    private WebElement CustTable_CustomerID_Row1;

    @FindBy(xpath = "(//div[@class='card-no-text'])")
    private List<WebElement> List_OfCustDetailsPopup_CreditCardNo;

    @FindBy(xpath = "(//div[@class='card-no-text'])[1]")
    private WebElement CustDetailsPopup_CreditCardNo;

    @FindBy(xpath = "(//div[@class='card-date-text'])[1]")
    private WebElement CustDetailsPopup_ExpiryDate;

    @FindBy(xpath = "//div[@id='bkCrditcardDetail']//div[@class='inner-trash']//i")
    private WebElement CustDetailsPopup_CreditCard_DeleteIcon;

    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]//button[contains(text(),'Ok')]")
    private WebElement CustDetailsPopup_CreditcardTab_DeleteIconAlert_OkButton;

    @FindBy(xpath = "//button[@class='btn btn-primary' and contains(text(),'New Customer')]")
    private WebElement new_customer_button_on_customerpage;

    @FindBy(xpath = "(//div[@id='CustomerDetailModal']//i)[1]")
    private WebElement customer_details_popup_close_icon;

    @FindBy(xpath = "//li[@id='CustomersMenu']")
    private WebElement customer_menu;

    @FindBy(xpath = "//select[@id='ddlShop']")
    private WebElement shop_dropdown;

    @FindBy(xpath = "//select[@id='ddlCustomershop']")
    private WebElement select_shop_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddCompanyName']")
    private WebElement company_name_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddFirstName']")
    private WebElement first_name_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddLastName']")
    private WebElement last_name_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddAddress1']")
    private WebElement address1_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddAddress2']")
    private WebElement address2_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddZip']")
    private WebElement zip_code_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddCity']")
    private WebElement city_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddState']")
    private WebElement state_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddCountry']")
    private WebElement country_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddPhoneNumber']")
    private WebElement phone_number_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddPhoneNumberExt']")
    private WebElement phone_extension_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddAltPhone']")
    private WebElement alt_phone_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddAltPhoneExt']")
    private WebElement alt_phone_extension_on_addnewcustomer_popup;

    @FindBy(xpath = "//input[@id='txtAddEmail']")
    private WebElement email_textbox_on_addnewcustomer_popup;

    @FindBy(xpath = "//a[@id='btnAddNewCustomer']")
    private WebElement add_new_customer_button;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement toast_message;

    @FindBy(xpath = "//button[@class='toast-close-button']")
    private WebElement toast_close_button;

    @FindBy(xpath = "//span[@data-field='CustomerName']//input")
    private WebElement customer_search_textbox_on_customer_page;

    @FindBy(xpath = "(//input[@id='txtStoretxtAddStoreCreditCredit'])")
    private WebElement store_credit_textbox_add_new_customer_popup;

    @FindBy(xpath = "(//input[@id='txtCutomerId'])[1]")
    private WebElement customer_id_textbox_field_add_new_customer_popup;

    @FindBy(xpath = "(//div[@class='input-group marginleft5'])[1]//span")
    private WebElement actionmenu_on_customerdetails_tablegrid_row1;

    @FindBy(xpath = "//a[@class='DeleteCustomer']")
    private WebElement delete_customer_button_on_actionmenu_tablegrid_row1;

    @FindBy(xpath = "//button[@class='confirm']")
    private WebElement delete_confirmation_yes_button;

    @FindBy(xpath = "//button[@class='cancel']")
    private WebElement delete_confirmation_no_button;

    //============================ customer details header elements =============================================
    @FindBy(xpath = "(//span[@id='subTitCustContent'])[1]//span[1]")
    private WebElement customer_full_name_on_customer_details_page_header;

    @FindBy(xpath = "(//span[@id='subTitCustContent'])[1]//span[2]")
    private WebElement customer_id_on_customer_details_page_header;

    @FindBy(xpath = "(//span[@id='subTitCustContent'])[1]//span[3]")
    private WebElement customer_phonenumber_on_customer_details_page_header;

    @FindBy(xpath = "(//span[@id='subTitCustContent'])[1]//span[4]")
    private WebElement customer_companyname_on_customer_details_page_header;

    //===================== confirmation popup element ============================
    @FindBy(xpath = "//div[contains(@class,'showSweetAlert')]")
    private WebElement confirmation_alert_popup;

    @FindBy(xpath = "//div[contains(@class,'showSweetAlert')]//h2")
    private WebElement confirmation_popup_header;

    @FindBy(xpath = "(//div[contains(@class,'showSweetAlert')]//p)[1]")
    private WebElement confirmation_popup_message;

    @FindBy(xpath = "//input[@placeholder='Confirmation code']")
    private WebElement confirmation_code_textbox;

    @FindBy(xpath = "//input[@placeholder='Confirmation code']/following::button[text()='OK']")
    private WebElement confirmation_popup_ok_button;


    //======================================= Customer Page Functions ===================================

    /**
     * Verify the Customer Page header is displayed or not
     *
     * @return It returns true if the Customer Page header is displayed or false otherwise
     * @Author Balaji N
     */
    public boolean VerifyCustomerMenuPage() {
        isElementDisplayed(customer_details_popup, "Customer Details Popup on customer module page");
        return isElementDisplayed(CustomerMenuHeading, "Customer Page Heading");
    }

    /**
     * Clicks the New customer button on customer page
     *
     * @Author Balaji N
     */
    public void click_New_Customer_Button() {
        click(new_customer_button, "New Customer Button on customer page");
    }

    /**
     * Verify whether the Add New Customer Popup is displayed or not
     *
     * @return If the Add New Customer Popup is displayed it will return true else it will return false
     * @Author Balaji N
     */
    public boolean verify_Add_New_Customer_Popup_IsDisplayed() {
        return isElementDisplayed(add_new_customer_popup_header, "Add New Customer Popup Header");
    }

    public void SearchAndSelectCustomerName(String customerName) {
        clickAndType(CustomerNameSearchBox, customerName);
        delayWithGivenTime(3000);

        for (WebElement customer : CustomerList) {
            if (customer.getText().equals("Test Automation")) {
                click(customer);
                break;
            }
        }
    }

    public void SearchAndSelectCustomerCityStateZip(String citystatezip) {
        clickAndType(CustomerCityStateZipSearchBox, citystatezip);
        delayWithGivenTime(3000);

        for (WebElement customer : CustomerList) {
            if (customer.getText().equals("Coimbatore")) {
                click(customer);
                break;
            }
        }
    }

    public void SearchAndSelectCustomerPhone(String phone) {
        clickAndType(CustomerPhoneSearchBox, phone);
    }

    public void SearchAndSelectCustomerAddress(String address) {
        clickAndType(CustomerAddressSearchBox, address);
    }

    public void ClickCustomerId_LastRow_OnCustTable() {
        fluentWait(CustTable_CustomerID_LastRow);
        click(CustTable_CustomerID_LastRow, "Customer Id Row Last on Customer Table grid");
    }

    public void Click_CustomerId_Row1_OnCustTable() {
        fluentWait(CustTable_CustomerID_Row1);
        jsClick(CustTable_CustomerID_Row1);
    }

    public void Search_and_SelectCustomerName(String customerName, String expectedcustomername) {
        CustomerNameSearchBox.click();
        delayWithGivenTime(1000);
        DoubleClickAndType(CustomerNameSearchBox, customerName);
        delayWithGivenTime(1000);
        for (WebElement customer : CustomerList) {
            if (customer.getText().contains(expectedcustomername)) {
                jsClick(customer);
                break;
            }
        }
    }


    /**
     * Enters the customer name into the search box on the customer table.
     * <p>
     * Description: Clears the input field, types the provided customer name, adds a short delay,
     * and then simulates pressing the ENTER key.
     *
     * @param customername The name of the customer to be searched.
     * @Author Balaji N
     */
    public void Enter_CustomerName_searchbox_OnCustTable(String customername) {
        try {
            if (CustomerNameSearchBox.isDisplayed() && CustomerNameSearchBox != null) {
                CustomerNameSearchBox.clear();
                CustomerNameSearchBox.sendKeys(customername);
                delayWithGivenTime(1000);
                CustomerNameSearchBox.sendKeys(Keys.ENTER);
                System.out.println("Successfully entered customer name: " + customername);
            } else {
                throw new ElementNotInteractableException("Customer Name SearchBox is not interactable");
            }
        } catch (NoSuchElementException e) {
            printError(CustomerNameSearchBox, "Customer Name Search Box in Customer Module Page", "NoSuchElementException", e);
        } catch (ElementNotInteractableException e) {
            printError(CustomerNameSearchBox, "Customer Name Search Box in Customer Module Page", "ElementNotInteractableException", e);
        } catch (TimeoutException e) {
            printError(CustomerNameSearchBox, "Customer Name Search Box in Customer Module Page", "TimeoutException", e);
        } catch (WebDriverException e) {
            printError(CustomerNameSearchBox, "Customer Name Search Box in Customer Module Page", "WebDriverException", e);
        } catch (Exception e) {
            printError(CustomerNameSearchBox, "Customer Name Search Box in Customer Module Page", "UnexpectedException", e);
        }
    }

    /**
     * Verify whether the company name on the customer table is displayed or not
     *
     * @return If the company name is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String VerifyCompanyNameOnCustTable() {
        return get_Element_Text(CompanyNameOnCustTable, "Customer Company Name Row 1 on Customer Table grid");
    }

    /**
     * Verify whether the phone number on the customer table is displayed or not
     *
     * @return If the phone number is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String VerifyPhoneNumberOnCustTable() {
        return get_Element_Text(PhoneNumberOnCustTable, "Customer Phone Number Row 1 on Customer Table grid");
    }

    /**
     * Verify whether the address on the customer table is displayed or not
     *
     * @return If the address is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String VerifyAddressOnCustTable() {
        return get_Element_Text(AddressOnCustTable, "Customer Address Row 1 on Customer Table grid");
    }

    /**
     * Verify whether the city state zip code on the customer table is displayed or not
     *
     * @return If the city state zip code is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String VerifyCityStateZipCodeOnCustTable() {
        return get_Element_Text(CityStateZipCodeOnCustTable, "Customer City State Zip Row 1 on Customer Table grid");
    }

    /**
     * Click the Customer table grid row 1
     *
     * @Author Balaji N
     */
    /**
     * Clicks on the first row (5th column cell) of the customer table grid on the Customer section.
     * Retries on common runtime issues like stale reference or DOM delay.
     *
     * @Author Balaji N
     */
    public void ClickCustomerTableRow1() {
        int maxRetries = 3;
        int attempt = 0;

        By locator = By.xpath("(//div[@id='gvCustomers']//table)[2]//td[5]");

        while (attempt < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

                // Wait until the page is fully loaded
                wait.until(driver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
                wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return !!window.jQuery && jQuery.active === 0"));

                // Wait for the element to be visible and clickable
                WebElement customerRow = wait.until(ExpectedConditions.elementToBeClickable(locator));

                // Scroll into view for safety
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block:'center'});", customerRow);
                delayWithGivenTime(300);

                // JavaScript click for reliability
                js_Click(customerRow, "Customer Table Row 1 on Customer Table grid");
                return;

            } catch (StaleElementReferenceException | ElementNotInteractableException | TimeoutException e) {
                System.err.println("[Retry " + (attempt + 1) + "/" + maxRetries + "] Retry due to: " + e.getClass().getSimpleName());
            } catch (Exception e) {
                printError(CustomerTableRow1, "ClickCustomerTableRow1", "Unexpected error while clicking row: " + e.getMessage(), e);
                break;
            }

            delayWithGivenTime(1000);
            attempt++;
        }

        throw new RuntimeException("Failed to click Customer Table Row 1 after " + maxRetries + " attempts.");
    }


    /**
     * Verify whether the customer details popup is displayed or not
     *
     * @return It returns true if the customer details popup is displayed or false otherwise
     * @Author Balaji N
     */
    public boolean VerifyCustomerDetailsPopup() {
        fluentWait(CustomerDetailsPopupHeading,30,2);
        return isElementDisplayed(CustomerDetailsPopupHeading, "Customer Details Popup Heading");
    }

    /**
     * Clicks the close icon on the customer details popup in the customer page
     *
     * @Author Balaji N
     */
    public void ClickCustomerDetailsPopupCloseIcon() {
        jsScrollClick(CustomerDetailsPopupCloseIcon, "Customer Details Popup Close Icon in the customer page");
    }

    public void ClickOnCustomerTableAllCheckBox() {
        jsClick(CustomerTableAllCheckBox);
    }

    public void ClickOnActionButton() {
        jsClick(CustomerActionbutton);
    }

    public void ClickOnCustomerDeleteButton() {
        jsClick(CustomerDeleteButton);
    }

    /**
     * Retrieves the company name of the customer details popup in the customer page
     *
     * @return If the company name is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustDetailsCompanyNameTextBox() {
        return get_element_attribute(CustDetailsCompanyNameTextBox, "Customer Company Name on Customer Details Popup");
    }

    public void enter_CompanyName_On_Update_Customer_Details_Popup(String companyname) {
        ClickAndType(CustDetailsCompanyNameTextBox, companyname, "Customer Company Name on Customer Details Popup");
    }

    /**
     * Get the first name of the customer details popup
     *
     * @return if the first name is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustDetailsFirstNameTextBox() {
        return get_element_attribute(CustDetailsFirstNameTextBox, "Customer First Name on Customer Details Popup");
    }

    public void enter_FirstName_On_Update_Customer_Details_Popup(String firstname) {
        ClickAndType(CustDetailsFirstNameTextBox, firstname, "Customer First Name on Customer Details Popup");
    }

    /**
     * Get the last name of the customer details popup
     *
     * @return if the last name is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustDetailsLastNameTextBox() {
        return get_element_attribute(CustDetailsLastNameTextBox, "Customer Last Name on Customer Details Popup");
    }

    public void enter_LastName_On_Update_Customer_Details_Popup(String lastname) {
        ClickAndType(CustDetailsLastNameTextBox, lastname, "Customer Last Name on Customer Details Popup");
    }

    /**
     * Get the address 1 of the customer details popup
     *
     * @return If the address 1 is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustDetailsAddress1TextBox() {
        return getElementAttribute(CustDetailsAddress1TextBox, "Customer Address 1 on Customer Details Popup");
    }

    public void enter_Address1_On_Update_Customer_Details_Popup(String address1) {
        ClickAndType(CustDetailsAddress1TextBox, address1, "Customer Address 1 on Customer Details Popup");
    }

    public String getCustDetailsAddress2TextBox() {
        return getElementAttribute(CustDetailsAddress2TextBox, "Customer Address 2 on Customer Details Popup");
    }

    public void enter_Address2_On_Update_Customer_Details_Popup(String address2) {
        ClickAndType(CustDetailsAddress2TextBox, address2, "Customer Address 2 on Customer Details Popup");
    }

    public String getCustDetailsCityTextBox() {
        return getElementAttribute(CustDetailsCityTextBox, "City textbox field value on customer details popup at customer page").trim();
    }

    /**
     * It retrieves the state of the customer details popup in the customer page
     *
     * @return If the state is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustDetailsStateTextBox() {
        return getElementAttribute(CustDetailsStateTextBox, "Customer State on Customer Details Popup");
    }

    /**
     * It retrieves the zip code of the customer details popup in the customer page
     *
     * @return If the zip code is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustDetailsZipCodeTextBox() {
        return getElementAttribute(CustDetailsZipCodeTextBox, "Customer Zip Code on Customer Details Popup");
    }

    public void enter_ZipCode_On_Update_Customer_Details_Popup(String zipcode) {
        ClickAndType(CustDetailsZipCodeTextBox, zipcode, "Customer Zip Code on Customer Details Popup");
        delayWithGivenTime(1000);
        CustDetailsZipCodeTextBox.sendKeys(Keys.TAB);
    }

    public String getCustDetailsCountryTextBox() {
        return getElementAttribute(CustDetailsCountryTextBox, "Customer Country textboxfiled value on Customer Details Popup");
    }

    /**
     * It retrieves the alternate phone number of the customer details popup
     *
     * @return if the alternate phone number is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustDetailsAltPhoneNumberTextBox() {
        return get_element_attribute(CustDetailsAltPhoneNumberTextBox, "Customer Alternate Phone Number on Customer Details Popup");
    }

    public void enter_AltPhoneNumber_On_Update_Customer_Details_Popup(String altphonenumber) {
        ClickAndType(CustDetailsAltPhoneNumberTextBox, altphonenumber, "Customer Alternate Phone Number on Customer Details Popup");
    }

    /**
     * It retrieves the email of the customer details popup in the customer page
     *
     * @return If the email is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustDetailsEmailTextBox() {
        return getElementAttribute(CustDetailsEmailTextBox, "Customer Email on Customer Details Popup");
    }

    public void enter_Email_On_Update_Customer_Details_Popup(String email) {
        ClickAndType(CustDetailsEmailTextBox, email, "Customer Email on Customer Details Popup");
    }

    /**
     * It retrieves the store credit of the customer details popup in the customer page
     *
     * @return If the store credit is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustDetailsStoreCreditTextBox() {
        return getElementAttribute(CustDetailsStoreCreditTextBox, "Customer Store Credit on Customer Details Popup");
    }

    public void enter_StoreCredit_On_Update_Customer_Details_Popup(String storecredit) {
        ClickAndType(CustDetailsStoreCreditTextBox, storecredit, "Customer Store Credit on Customer Details Popup");
    }

    public void select_Customer_Type_Dropdown_On_Update_Customer_Details_Popup(String customertype) {
        drop_Down(CustDetailsCustomerTypeDropDown, customertype, "VisibleText", "Customer Type dropdown field value on customer details popup at customer page");
    }

    public String get_Customer_Type_Dropdown_On_Update_Customer_Details_Popup() {
        return get_selected_option(CustDetailsCustomerTypeDropDown, "Customer Type dropdown field value on customer details popup at customer page");
    }

    public void select_Source_Code_Dropdown_On_Update_Customer_Details_Popup(String sourcecode) {
        drop_Down(CustDetailsCustomerSourceDropDown, sourcecode, "VisibleText", "Source Code dropdown field value on customer details popup at customer page");
    }

    public String get_Source_Code_Dropdown_On_Update_Customer_Details_Popup() {
        return get_selected_option(CustDetailsCustomerSourceDropDown, "Source Code dropdown field value on customer details popup at customer page");
    }

    public void Enter_Customer_Notes_On_Update_Customer_Details_Popup(String notes) {
        ClickAndType(update_custdetails_customer_notes, notes, "Customer Notes on Customer Details Popup");
    }

    public String get_Customer_Notes_On_Update_Customer_Details_Popup() {
        return getElementAttribute(update_custdetails_customer_notes, "Customer Notes on Customer Details Popup");
    }

    public void click_Update_Details_Button_On_Update_Customer_Details_Popup() {
        js_Click(CustDetailsUpdateButton, "Update Details Button on Customer Details Popup");
    }

    /**
     * Get the phone number of the customer details popup
     *
     * @return If the phone number is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String getCustDetailsPhoneNumberTextbox() {
        return get_element_attribute(CustDetailsPhoneNumberTextBox, "Customer Phone Number on Customer Details Popup");
    }

    public void enter_PhoneNumber_On_Update_Customer_Details_Popup(String phonenumber) {
        ClickAndType(CustDetailsPhoneNumberTextBox, phonenumber, "Customer Phone Number on Customer Details Popup");
    }

    public String getDisplayedCustDetailsCustomerType() {
        return get_selected_option(CustDetailsCustomerTypeDropDown, "Customer Type dropdown field value on customer details popup at customer page");
    }

    public boolean validateCustDetailsCreditApprovedToogle() {
        HighlightElement(CustDetailsCreditApprovedToogle);
        return CustDetailsCreditApprovedToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public boolean ValidateCustDetailsPaperlessStatementToogle() {
        HighlightElement(CustDetailsPaperlessStatementToogle);
        return CustDetailsPaperlessStatementToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public boolean ValidateCustDetailsEmailStatementToogle() {
        HighlightElement(CustDetailsEmailSettingToogle);
        return CustDetailsEmailSettingToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    public boolean ValidateCustDetailsEnableLoyaltyToogle() {
        HighlightElement(CustDetailsEnableLoyaltyToogle);
        return CustDetailsEnableLoyaltyToogle.getAttribute("style").contains("rgb(223, 223, 223)");
    }

    /**
     * Verifies whether the late fee setting toggle is enabled or not.
     *
     * <p>This method highlights the late fee setting toggle and checks its style attribute
     * to determine if it is enabled. It ensures robust exception handling to avoid test failures.</p>
     *
     * @return {@code true} if the late fee setting toggle is enabled, otherwise {@code false}.
     * @throws NoSuchElementException         if the toggle element is not found in the DOM.
     * @throws StaleElementReferenceException if the toggle element is no longer attached to the DOM.
     * @author Balaji N
     */
    public boolean ValidateCustDetailsLateFeeSettingToogle() {
        try {
            HighlightElement(CustDetailsLateFeeSettingToogle);
            String styleAttribute = CustDetailsLateFeeSettingToogle.getAttribute("style");
            return styleAttribute != null && styleAttribute.contains("rgb(223, 223, 223)");
        } catch (NoSuchElementException e) {
            printError(CustDetailsLateFeeSettingToogle, "Late Fee Setting Toggle", "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(CustDetailsLateFeeSettingToogle, "Late Fee Setting Toggle", "StaleElementReferenceException", e);
        } catch (Exception e) {
            printError(CustDetailsLateFeeSettingToogle, "Late Fee Setting Toggle", "UnexpectedException", e);
        }
        return false;
    }

    public boolean ValidateCustDetailsAllowFeedbackEmailTToogle() {
        HighlightElement(CustDetailsAllowFeedbackEmailToogle); // green background color
        return CustDetailsAllowFeedbackEmailToogle.getAttribute("style").contains("rgb(197, 231, 244)");
    }

    public void SelectPagination_OnCustomerTable(String pagination) {
        fluentWait(CustomerDetails_PaginationDropDown);
        dropDown(CustomerDetails_PaginationDropDown, pagination, "VisibleText");
    }

    /**
     * Verifies the credit card number is displayed on the customer details popup.
     *
     * @return If the credit card number is displayed on the customer details popup returns the credit card number, otherwise returns an empty string.
     * @Author Balaji N
     */
    public String Verify_CreditCardNumber_OnCustomerDetailsPopup() {
        return getElementText(CustDetailsPopup_CreditCardNo, "Customer Credit Card Number on Customer Details Popup - Credit Card Tab");
    }

    /**
     * Verifies the credit card expiration date is displayed on the customer details popup.
     *
     * @return If the credit card expiration date is displayed on the customer details popup returns the expiration date, otherwise returns an empty string.
     * @Author Balaji N
     */
    public String Verify_CreditCard_ExpiredDate_OnCustomerDetailsPopup() {
        return getElementText(CustDetailsPopup_ExpiryDate, "Customer Credit Card Expiry Date on Customer Details Popup - Credit Card Tab");
    }

    /**
     * Clicks the credit card delete icon on the customer details popup.
     * <p>
     * This method ensures reliability by:
     * <ul>
     *     <li>Waiting for the delete icon to be clickable.</li>
     *     <li>Handling common exceptions that might occur during interaction.</li>
     *     <li>Logging meaningful error messages for debugging.</li>
     * </ul>
     * <p>
     * Exceptions handled:
     * <ul>
     *     <li>{@link NoSuchElementException} - If the delete icon is not found in the DOM.</li>
     *     <li>{@link ElementNotInteractableException} - If the delete icon is hidden or disabled.</li>
     *     <li>{@link StaleElementReferenceException} - If the delete icon reference becomes stale.</li>
     *     <li>{@link TimeoutException} - If the delete icon is not clickable within the specified time.</li>
     *     <li>{@link WebDriverException} - If a general WebDriver-related issue occurs.</li>
     *     <li>{@link RuntimeException} - If all retries fail.</li>
     * </ul>
     *
     * @throws RuntimeException If the element remains unclickable after retries.
     * @Author Balaji N
     */
    public void Click_OnCreditCard_DeleteIcon_OnCustomerDetailsPopup() {
        int attempts = 0;
        int maxAttempts = 3;

        while (attempts < maxAttempts) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(CustDetailsPopup_CreditCard_DeleteIcon));

                HighlightElement(CustDetailsPopup_CreditCard_DeleteIcon);
                CustDetailsPopup_CreditCard_DeleteIcon.click();

                return; // Exit if successful

            } catch (NoSuchElementException e) {
                attempts++;
                printError(CustDetailsPopup_CreditCard_DeleteIcon, "Click_OnCreditCard_DeleteIcon", "NoSuchElementException", e);

            } catch (ElementNotInteractableException e) {
                printError(CustDetailsPopup_CreditCard_DeleteIcon, "Click_OnCreditCard_DeleteIcon", "ElementNotInteractableException", e);

            } catch (StaleElementReferenceException e) {
                attempts++;
                printError(CustDetailsPopup_CreditCard_DeleteIcon, "Click_OnCreditCard_DeleteIcon", "StaleElementReferenceException", e);
                if (attempts < maxAttempts) {
                    CustDetailsPopup_CreditCard_DeleteIcon = getDriver().findElement(By.xpath(getElementXPath(CustDetailsPopup_CreditCard_DeleteIcon)));
                }

            } catch (TimeoutException e) {
                printError(CustDetailsPopup_CreditCard_DeleteIcon, "Click_OnCreditCard_DeleteIcon", "TimeoutException", e);

            } catch (WebDriverException e) {
                printError(CustDetailsPopup_CreditCard_DeleteIcon, "Click_OnCreditCard_DeleteIcon", "WebDriverException", e);

            } catch (Exception e) {
                printError(CustDetailsPopup_CreditCard_DeleteIcon, "Click_OnCreditCard_DeleteIcon", "UnexpectedException", e);
            }
        }
    }

    /**
     * Clicks the credit card delete icon confirmation popup ok button on customer details popup in the phone order page
     *
     * @Author Balaji N
     */
    public void Click_OnCreditCard_DeleteIcon_DeleteConfirmation() {
        js_Click(CustDetailsPopup_CreditcardTab_DeleteIconAlert_OkButton, "Credit Card Delete Icon Delete Confirmation");
    }

    /**
     * Clicks the customer id header on customer table
     *
     * @Author Balaji N
     */
    public void click_CustomerId_Header_OnCustTable() {
        js_Click(CustomerIdColumn_header, "Customer Id Column Header");
    }

    /**
     * Verify the Descending icon on customer id column is displayed or not
     *
     * @return If the descending icon is displayed then it returns true otherwise false
     * @Author Balaji N
     */
    public boolean verify_custId_DescendingIcon() {
        return is_Element_Displayed(descendingIconOnCustomerIdColumn, "Descending Icon on Customer Id Column");
        //  return descendingIconOnCustomerIdColumn.isDisplayed();
    }

    /**
     * Verify the ascending icon on customer id column is displayed or not
     *
     * @return If the ascending icon is displayed then it returns true otherwise false
     * @Author Balaji N
     */
    public boolean verify_custId_AscendingIcon() {
        return is_Element_Displayed(ascendingIconOnCustomerIdColumn, "Ascending Icon on Customer Id Column");
        // return ascendingIconOnCustomerIdColumn.isDisplayed();
    }


    public String get_CustomerId_OnCustTable() {
        return CustomerId_List_OnCustTable.get(0).getText();
    }

    /**
     * Enters the customer id on customer table
     *
     * @Author Balaji N
     */
    public void Enter_CustomerId_SearchTextBox_OnCustomerTable(String custId) {
        if (is_Element_Displayed(CustomerIdSearchBox, "Customer Id Search Box on Customer Table")) {
            CustomerIdSearchBox.clear();
            CustomerIdSearchBox.sendKeys(custId);
            delayWithGivenTime(1000);
            CustomerIdSearchBox.sendKeys(Keys.ENTER);
        }
    }

    /**
     * Verifies the customer details popup is displayed or not
     *
     * @return If the customer details popup is displayed then it returns true otherwise false
     */
    public boolean Verify_DontSaveCreditCard_OnCustomerDetailsPopup() {
        int count = List_OfCustDetailsPopup_CreditCardNo.size();
        if (count < 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Clicks the New customer button
     *
     * @Author Balaji N
     */
    public void Click_New_Customer_on_CustomerPage() {
        Click(new_customer_button_on_customerpage, "New Customer button on customer Module Page");
    }

    public void select_ShopName_On_AddNewCustomer_Popup(String shopName) {
        Drop_Down(select_shop_on_addnewcustomer_popup, shopName, "VisibleText", "Shop Name dropdown field value on add new customer popup at customer page");
    }

    /**
     * Select the shopname on customer page
     *
     * @param shopName
     * @Author Balaji N
     */
    public void Select_ShopName_On_AddNewCustomer_Popup(String shopName) {
        drop_Down(shop_dropdown, shopName, "VisibleText", "Shop Name dropdown field value on customer page");
    }

    /**
     * It returns the selected shop name on customer page
     *
     * @return If the shop name is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_Selected_ShopName_On_Customer_Page() {
        return get_selected_option(shop_dropdown, "Shop Name dropdown field value on customer page");
    }

    /**
     * It returns the selected shop name on add new customer popup
     *
     * @return If the shop name is displayed it returns the value, otherwise it returns empty string
     * @Author Balaji N
     */
    public String get_Selected_ShopName_On_AddNewCustomer_Popup() {
        return get_selected_option(select_shop_on_addnewcustomer_popup, "Shop Name dropdown field value on add new customer popup at customer page");
    }

    /**
     * Enters the company name textfield on add new customer popup
     *
     * @param companyname
     * @Author Balaji N
     */
    public void enter_CompanyName_On_AddNewCustomer_Popup(String companyname) {
        ClickAndType(company_name_on_addnewcustomer_popup, companyname, "Company Name field on add new customer popup at customer page");
    }

    /**
     * It will return the entered company name on add new customer popup
     *
     * @return If the company name is displayed it returns the value, otherwise it returns empty string
     * @author Balaji N
     */
    public String get_entered_CompanyName_On_AddNewCustomer_Popup() {
        return getElementAttribute(company_name_on_addnewcustomer_popup, "Company Name field on add new customer popup at customer page");
    }

    public void enter_FirstName_On_AddNewCustomer_Popup(String firstname) {
        ClickAndType(first_name_on_addnewcustomer_popup, firstname, "First Name textbox field on add new customer popup at customer page");
    }

    public String get_entered_FirstName_On_AddNewCustomer_Popup() {
        return getElementAttribute(first_name_on_addnewcustomer_popup, "First Name textbox field value on add new customer at customer page");
    }

    /**
     * Enters the last name in the Last Name textbox field on the Add New Customer popup.
     *
     * @param lastName The last name to be entered.
     */
    public void enter_LastName_On_AddNewCustomer_Popup(String lastName) {
        ClickAndType(last_name_on_addnewcustomer_popup, lastName, "Last Name textbox field on add new customer popup at customer page");
    }

    /**
     * Retrieves the entered last name from the Last Name textbox field on the Add New Customer popup.
     *
     * @return The last name entered in the textbox.
     */
    public String get_entered_LastName_On_AddNewCustomer_Popup() {
        return getElementAttribute(last_name_on_addnewcustomer_popup, "Last Name textbox field value on add new customer at customer page");
    }

    /**
     * Enters the address in the Address 1 textbox field on the Add New Customer popup.
     *
     * @param address1 The first address line to be entered.
     */
    public void enter_Address1_On_AddNewCustomer_Popup(String address1) {
        ClickAndType(address1_on_addnewcustomer_popup, address1, "Address 1 textbox field on add new customer popup at customer page");
    }

    /**
     * Retrieves the entered address from the Address 1 textbox field on the Add New Customer popup.
     *
     * @return The address entered in the textbox.
     */
    public String get_entered_Address1_On_AddNewCustomer_Popup() {
        return getElementAttribute(address1_on_addnewcustomer_popup, "Address 1 textbox field value on add new customer at customer page");
    }

    /**
     * Enters the address in the Address 2 textbox field on the Add New Customer popup.
     *
     * @param address2 The second address line to be entered.
     */
    public void enter_Address2_On_AddNewCustomer_Popup(String address2) {
        ClickAndType(address2_on_addnewcustomer_popup, address2, "Address 2 textbox field on add new customer popup at customer page");
    }

    /**
     * Retrieves the entered address from the Address 2 textbox field on the Add New Customer popup.
     *
     * @return The address entered in the textbox.
     */
    public String get_entered_Address2_On_AddNewCustomer_Popup() {
        return getElementAttribute(address2_on_addnewcustomer_popup, "Address 2 textbox field value on add new customer at customer page");
    }

    /**
     * Enters the ZIP code in the ZIP Code textbox field on the Add New Customer popup.
     *
     * @param zipCode The ZIP code to be entered.
     */
    public void enter_ZipCode_On_AddNewCustomer_Popup(String zipCode) {
        ClickAndType(zip_code_on_addnewcustomer_popup, zipCode, "ZIP Code textbox field on add new customer popup at customer page");
    }

    /**
     * Retrieves the entered ZIP code from the ZIP Code textbox field on the Add New Customer popup.
     *
     * @return The ZIP code entered in the textbox.
     */
    public String get_entered_ZipCode_On_AddNewCustomer_Popup() {
        return getElementAttribute(zip_code_on_addnewcustomer_popup, "ZIP Code textbox field value on add new customer at customer page");
    }

    /**
     * Enters the city in the City textbox field on the Add New Customer popup.
     *
     * @param city The city to be entered.
     */
    public void enter_City_On_AddNewCustomer_Popup(String city) {
        ClickAndType(city_on_addnewcustomer_popup, city, "City textbox field on add new customer popup at customer page");
    }

    /**
     * Retrieves the entered city from the City textbox field on the Add New Customer popup.
     *
     * @return The city entered in the textbox.
     */
    public String get_entered_City_On_AddNewCustomer_Popup() {
        return getElementAttribute(city_on_addnewcustomer_popup, "City textbox field value on add new customer at customer page");
    }

    /**
     * Enters the state in the State textbox field on the Add New Customer popup.
     *
     * @param state The state to be entered.
     */
    public void enter_State_On_AddNewCustomer_Popup(String state) {
        ClickAndType(state_on_addnewcustomer_popup, state, "State textbox field on add new customer popup at customer page");
    }

    /**
     * Retrieves the entered state from the State textbox field on the Add New Customer popup.
     *
     * @return The state entered in the textbox.
     */
    public String get_entered_State_On_AddNewCustomer_Popup() {
        return getElementAttribute(state_on_addnewcustomer_popup, "State textbox field value on add new customer at customer page");
    }

    /**
     * Enters the country in the Country textbox field on the Add New Customer popup.
     *
     * @param country The country to be entered.
     */
    public void enter_Country_On_AddNewCustomer_Popup(String country) {
        ClickAndType(country_on_addnewcustomer_popup, country, "Country textbox field on add new customer popup at customer page");
    }

    /**
     * Retrieves the entered country from the Country textbox field on the Add New Customer popup.
     *
     * @return The country entered in the textbox.
     */
    public String get_entered_Country_On_AddNewCustomer_Popup() {
        return getElementAttribute(country_on_addnewcustomer_popup, "Country textbox field value on add new customer at customer page");
    }

    /**
     * Enters the phone number in the Phone Number textbox field on the Add New Customer popup.
     *
     * @param phoneNumber The phone number to be entered.
     */
    public void enter_PhoneNumber_On_AddNewCustomer_Popup(String phoneNumber) {
        ClickAndType(phone_number_on_addnewcustomer_popup, phoneNumber, "Phone Number textbox field on add new customer popup at customer page");
    }

    /**
     * Enters the alternate phone number in the customer page on the Add New Customer popup.
     *
     * @param altPhoneNumber
     * @Author Balaji N
     */
    public void enter_AltPhoneNumber_On_AddNewCustomer_Popup(String altPhoneNumber) {
        ClickAndType(alt_phone_on_addnewcustomer_popup, altPhoneNumber, "Alt phone number textbox field on add new customer popup at customer page");
    }

    /**
     * Retrieves the entered alternate phone number textbox field on the Add New Customer popup.
     *
     * @return The phone number entered in the textbox.
     */
    public String get_entered_AltPhoneNumber_On_AddNewCustomer_Popup() {
        return getElementAttribute(alt_phone_on_addnewcustomer_popup, "Alt Phone Number textbox field value on add new customer at customer page");
    }

    /**
     * Enters the email id text box field on add new customer popup
     *
     * @param email
     * @Author Balaji N
     */
    public void enter_Email_Id_On_AddNewCustomer_Popup(String email) {
        ClickAndType(email_textbox_on_addnewcustomer_popup, email, "Email ID textbox field on add new customer popup at customer page");
    }

    public String get_Entered_EmailID_textbox_on_addnewcustomer_popup() {
        return getElementAttribute(email_textbox_on_addnewcustomer_popup, "Email ID textbox field value on add new customer at customer page");
    }


    /**
     * Retrieves the entered phone number from the Phone Number textbox field on the Add New Customer popup.
     *
     * @return The phone number entered in the textbox.
     */
    public String get_entered_PhoneNumber_On_AddNewCustomer_Popup() {
        return getElementAttribute(phone_number_on_addnewcustomer_popup, "Phone Number textbox field value on add new customer at customer page");
    }

    /**
     * Clicks the Add New Customer button on the popup.
     */
    public void click_AddNewCustomer_Button() {
        click(add_new_customer_button, "Add New Customer button on add new customer popup at customer page");
    }

    /**
     * Validate whether the toaster message text is displayed or not
     *
     * @return If the toaster message text is displayed it returns the toaster value else returns null
     * @Author Balaji N
     */
    public String verify_Toaster_Message_Text() {
        return getElementText(toast_message, "Toaster Message Text");
    }

    public void click_Close_Icon_On_Success_Toaster_Message() {
        click(toast_close_button, "Close icon on success toaster message at customer page");
    }

    /**
     * Search the customer on the customer page on the customer search text box table grid
     *
     * @param customername
     * @Author Balaji N
     */
    public void search_Customer_On_Customer_Search_TextBox(String customername) {
        ClickAndType(customer_search_textbox_on_customer_page, customername, "Customer search textbox field on customer page");
        delayWithGivenTime(2000);
        customer_search_textbox_on_customer_page.sendKeys(Keys.ENTER);
    }

    public void select_CustomerType_AddNewCustomer_Popup(String customertype) {
        drop_Down(CustDetailsCustomerTypeDropdown, customertype, "VisibleText", "Customer Type - dropdown field on add new customer popup");
    }

    public String get_Selected_CustomerType_On_AddNewCustomer_Popup() {
        return get_selected_option(CustDetailsCustomerTypeDropdown, "Customer Type - dropdown field on add new customer popup");
    }

    public void enter_Store_Credit_On_Add_New_Customer_Popup(String storecredit) {
        ClickAndType(store_credit_textbox_add_new_customer_popup, storecredit, "Store credit textbox field on add new customer details popup");
    }

    public String get_Entered_Store_credit_On_Add_New_Customer_Popup() {
        return getElementAttribute(store_credit_textbox_add_new_customer_popup, "Store credit textbox field on add new customer details popup");
    }

    public String get_Customer_ID_On_Add_New_Customer_Details_Popup() {
        return getElementAttribute(customer_id_textbox_field_add_new_customer_popup, "Customer ID disabled textbox on add new customer details popup");
    }

    public void click_Customer_Close_Icon() {
        click(customer_details_popup_close_icon, "Customer Details Popup Close Icon in the customer page");
    }

    /**
     * Customer Full Name on customer details popup header
     *
     * @return If the customer full name is displayed it returns the customer full name else returns null
     * @Author Balaji N
     */
    public String get_Displayed_Customer_Name_On_Customer_Details_Popup_Header() {
        return getElementText(customer_full_name_on_customer_details_page_header, "Customer Full Name on customer details popup header");
    }

    /**
     * Customer ID on customer details popup header
     *
     * @return If the customer id is displayed it returns the customer id else returns null
     * @Author Balaji N
     */
    public String get_Displayed_Customer_ID_On_Customer_Details_Popup_Header() {
        return getElementText(customer_id_on_customer_details_page_header, "Customer ID on customer details popup header");
    }

    /**
     * Customer phone number on customer details popup header
     *
     * @return If the customer phone number is displayed it returns the customer phone number else returns null
     * @Author Balaji N
     */
    public String get_Displayed_Customer_Phonenumber_On_Customer_Details_Popup_Header() {
        return getElementText(customer_phonenumber_on_customer_details_page_header, "Customer phone number on customer details popup header");
    }

    /**
     * Customer company name on customer details popup header
     *
     * @return If the customer company name is displayed it returns the customer company name else returns null
     * @Author Balaji N
     */
    public String get_Displayed_Customer_CompanyName_On_Customer_Details_Popup_Header() {
        return getElementText(customer_companyname_on_customer_details_page_header, "Customer company name on customer details popup header");
    }

    public boolean is_CustomerDetails_Confirmation_Popup_Displayed() {
        return isElementDisplayed(confirmation_alert_popup, "Save to funeral log confirmation popup");
    }

    public String get_Confirmation_Code_On_CustomerDetails_Confirmation_Popup() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(get_Element_Text(confirmation_popup_message, "Confirmation code on customer details confirmation popup"));

        String code = "";
        if (matcher.find()) {
            code = matcher.group();
        }
        return code;
    }

    public void Enter_ConfirmationCode_On_CustomerDetails_Confirmation_Popup(String code) {
        ClickAndType(confirmation_code_textbox, code, "Confirmation code textbox field on customer details confirmation popup");
    }

    public void click_OK_Button_On_CustomerDetails_Confirmation_Popup() {
        js_Click(confirmation_popup_ok_button, "OK button on customer details confirmation popup");
    }

    public void click_ActionMenu_Icon_Row1_On_CustomerDetails_Tablegrid() {
        click(actionmenu_on_customerdetails_tablegrid_row1, "Action menu icon on customer details table grid row 1");
    }

    public void click_Delete_Button_On_ActionMenu() {
        click(delete_customer_button_on_actionmenu_tablegrid_row1, "Delete button on action menu popup");
    }

    public void click_Yes_Delete_Button_On_DeleteConfirmationPopup() {
        click(delete_confirmation_yes_button, "Yes button on delete confirmation popup");
    }

    public void click_No_Delete_Button_On_DeleteConfirmationPopup() {
        click(delete_confirmation_no_button, "No button on delete confirmation popup");
    }

}

