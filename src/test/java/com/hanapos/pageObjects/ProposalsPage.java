package com.hanapos.pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProposalsPage extends TestBaseClass {

    public ProposalsPage() {
        PageFactory.initElements(getDriver(), this);
    }

    //=============================== Proposal Page Elements ==========================================
    @FindBy(xpath = "//a[@class='nav-link li_Proposals']")
    private WebElement ProposalsMenu;

    @FindBy(xpath = "//a[@id='btnAddProposalRV3']")
    private WebElement AddProposalBtn;

    @FindBy(xpath = "//div[@id='proposalCustomerLookUp']")
    private WebElement CreateProposalPopup;

    @FindBy(xpath = "//h4[contains(text(),'Create Proposal')]")
    private WebElement CreateProposalHeader_Popup;

    @FindBy(xpath = "//input[@id='searchCustomer']")
    private WebElement SearchCustomer_SearchboxField;

    @FindBy(xpath = "(//button[@class='btn hana-btn-cancel btn-block'])[1]")
    private WebElement cancelButton_on_createproposal_popup;

    @FindBy(xpath = "//div[@id='proposalCustomerLookUp']//div[@class='modal-dialog']")
    private WebElement create_proposal_popup;

    @FindBy(xpath = "//ul[@id='ui-id-2']")
    private WebElement list_of_customer_options_on_proposalview_page;

    // Last Updated on Jan 13 2025, previous xpath will be //ul[@id='ui-id-1']//li
    @FindBy(xpath = "//ul[@id='ui-id-2']//li")
    private List<WebElement> CustomerList;

    @FindBy(xpath = "//button[@id='btnContinueToProposal']")
    private WebElement AddProposalBtn_On_CreateProposalPopup;

    @FindBy(xpath = "//a[@id='proposaldsort']")
    private WebElement proposal_Id_label;

    @FindBy(xpath = "//span[@class='k-icon k-i-sort-asc-sm']")
    private WebElement proposal_Id_Sort_asc_Icon;

    @FindBy(xpath = "//span[@class='k-icon k-i-sort-desc-sm']")
    private WebElement proposal_Id_Sort_desc_Icon;

    @FindBy(xpath = "(//select[@id='ddlStatusType'])[1]")
    private WebElement proposal_StatusType_DropDown;

    @FindBy(xpath = "//input[@id='txtProposalGridSearch']")
    private WebElement proposal_SearchBox;

    @FindBy(xpath = "//span[text()='Invoice:']/following-sibling::b[1]")
    private WebElement proposal_InvoiceNumber;

    @FindBy(xpath = "//span[text()='Total:']/following-sibling::b[1]")
    private WebElement proposal_total_amount;

    @FindBy(xpath = "//select[@id='ddlStatusType']/following-sibling::button[1]")
    private WebElement accept_cancel_tick_icon_on_proposal_grid;

    @FindBy(xpath = "(//td[contains(@class,'hana-action-column proposal-grid-action')]//span)[1]")
    private WebElement proposal_Action_Button;

    @FindBy(xpath = "(//div[@class='col-sm-12 no-padding action-menu-inner'])[1]")
    private WebElement ActionMenu_Popup_Options;

    @FindBy(xpath = "//div[@class='open']//a[@class='CustomerProposalView'][normalize-space()='Customer Proposal View']")
    private WebElement proposal_CustProposalView_Button;

    @FindBy(xpath = "//p[text()='Red Rose Small']")
    private WebElement proposal_CustProposalView_itemName;

    @FindBy(xpath = "(//table[@role='grid'])[2]//tbody//tr//td[3]")
    private List<WebElement> listofproposal_id_view_proposal;

    @FindBy(xpath = "//span[@class='k-widget k-dropdown k-header']")
    private WebElement pagination_dropdown_arrow;

    @FindBy(xpath = "//ul[@class='k-list k-reset']//li[normalize-space()='100']")
    private WebElement dropdownvalues_of_pageNumber;

    @FindBy(xpath = "(//table[@role='grid'])[2]//tbody//tr//td[4]//span")
    private List<WebElement> listofproposal_type;

    @FindBy(xpath = "//a[text()='Proposal type']")
    private WebElement proposal_type_label_tablegridheader;

    @FindBy(xpath = "(//a[text()='Invoice'])[1]")
    private WebElement Invoice_label_headertablegrid_view_proposal;

    @FindBy(xpath = "(//table[@role='grid'])[2]//tbody//tr//td[6]//b[1]")
    private List<WebElement> listofinvoices_on_tablegrid_invoicecolumn;

    @FindBy(xpath = "//span[@title='Ceremony or Event Date']")
    private WebElement date_label_viewproposal;

    @FindBy(xpath = "(//table[@role='grid'])[2]//tbody//tr//td[8]")
    private List<WebElement> listofdates_viewproposal;

    @FindBy(xpath = "//tr[@class='k-filter-row']//th[8]")
    private WebElement datepicker_inputbox;

    @FindBy(xpath = "//div[@class='daterangepicker dropdown-menu show-calendar opensright']")
    private WebElement datepicker_popup;

    @FindBy(xpath = "//div[@class='daterangepicker_start_input']")
    private WebElement calender_section_1_fromdate;

    @FindBy(xpath = "(//input[@class='input-mini'])[1]")
    private WebElement calender_section_1_fromdate_mini_inputbox;

    @FindBy(xpath = "//div[@class='daterangepicker_end_input']")
    private WebElement calendar_section_1_Todate;

    @FindBy(xpath = "//div[@class='daterangepicker_end_input']//input")
    private WebElement calender_section_1_Todate_mini_inputbox;

    @FindBy(xpath = "//button[contains(@class,'applyBtn')]")
    private WebElement calendar_section_1_ApplyButton;

    @FindBy(xpath = "//button[contains(@class,'cancelBtn')]")
    private WebElement calendar_section_1_ClearButton;

    @FindBy(xpath = "//div[contains(@class,'calendar second left')]//div[contains(@class,'calendar-date')]")
    private WebElement left_calendar_section;

    @FindBy(xpath = "//th[contains(@class,'prev available')]")
    private WebElement leftcalendar_leftarrow;

    @FindBy(xpath = "//div[@class='calendar second left']//select[contains(@class,'monthselect')]")
    private WebElement leftcalendar_selectmonth_dropdown;

    @FindBy(xpath = "//div[@class='calendar second left']//select[contains(@class,'yearselect')]")
    private WebElement leftcalendar_selectyear_dropdown;

    @FindBy(xpath = "//div[@class='calendar second left']//th[contains(@class,'next available')]")
    private WebElement leftcalendar_rightArrow_button;

    @FindBy(xpath = "//div[@class='calendar first right']//th[@class='prev available']")
    private WebElement rightcalendar_leftArrow_button;

    @FindBy(xpath = "//div[@class='calendar first right']//select[contains(@class,'monthselect')]")
    private WebElement rightcalendar_selectmonth_dropdown;

    @FindBy(xpath = "//div[@class='calendar first right']//select[contains(@class,'yearselect')]")
    private WebElement rightcalendar_selectyear_dropdown;

    @FindBy(xpath = "//div[@class='calendar first right']//th[contains(@class,'next available')]")
    private WebElement rightcalendar_rightArrow;

    @FindBy(xpath = "//div[contains(@class,'calendar first right')]//div[contains(@class,'calendar-date')]")
    private WebElement rightcalendar_datepicker;

    @FindBy(xpath = "(//table[@class='table-condensed'])[2]//tr//td[not(contains(@class,'available off'))]")
    private List<WebElement> listofleftcalendar_day;

    @FindBy(xpath = "(//table[@class='table-condensed'])[1]//tr//td[contains(@class, 'available') and not(contains(@class, 'off')) and not(contains(@class, 'disabled'))]")
    private List<WebElement> listofrighttcalendar_day;

    @FindBy(xpath = "//input[@id='custFirstName']")
    private WebElement createproposalpopup_firstname_field;

    @FindBy(xpath = "//input[@id='custLastName']")
    private WebElement createproposalpopup_lasttname_field;

    @FindBy(xpath = "//input[@id='custCompanyName']")
    private WebElement createproposalpopup_companyname_field;

    @FindBy(xpath = "//input[@id='custEmail']")
    private WebElement createproposalpopup_email_field;

    @FindBy(xpath = "//input[@id='custCity']")
    private WebElement createproposalpopup_city_field;

    @FindBy(xpath = "//input[@id='custState']")
    private WebElement createproposalpopup_state_field;

    @FindBy(xpath = "(//input[@id='custAddress1'])[1]")
    private WebElement createproposalpopup_address_field;

    @FindBy(xpath = "//input[@id='custZipCode']")
    private WebElement createproposalpopup_zipcode_field;

    @FindBy(xpath = "//input[@id='custPhone']")
    private WebElement createproposalpopup_phonenumber_field;

    @FindBy(xpath = "//input[@id='custAltPhone']")
    private WebElement createproposalpopup_altphonenumber_field;

    @FindBy(xpath = "//span[contains(text(),'Select report')]/following::select[@id='proposalCustReportId']")
    private WebElement createproposalpopup_selectreport_dropdownfield;

    @FindBy(xpath = "//span[contains(text(),'Event / Wedding Consultant')]/following::select[@id='proposalWeddingConsultantId']//option")
    private List<WebElement> list0freports_dropdownoptions;

    @FindBy(xpath = "//span[contains(text(),'Select package')]/following::select[@id='ProposalPackageId']")
    private WebElement createproposalpopup_selectpackage_dropdownfield;

    @FindBy(xpath = "//span[contains(text(),'Event / Wedding Consultant')]/following::select[@id='proposalWeddingConsultantId']")
    private WebElement createproposalpopup_select_eventorwedding_dropdownfield;

    @FindBy(xpath = "//h4[@id='proposalCustomerId']")
    private WebElement createproposalpopup_customerid;

    @FindBy(xpath = "//a[@id='canceldata']//span")
    private WebElement createproposalpopup_customer_clear_button;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement SuccessToastMsg;
    By sucessToastMsg = By.xpath("//div[@class='toast-message']");

    @FindBy(xpath = "//div[@class='toast-title']")
    private WebElement toaster_message_title;

    @FindBy(xpath = "//p[normalize-space()='Event']")
    private WebElement event_tab_on_Createproposal_popup;

    @FindBy(xpath = "//div[@class='pac-container pac-logo hdpi']")
    private WebElement address_autosuggestion_create_proposal_popup;

    @FindBy(xpath = "//div[@class='pac-container pac-logo hdpi']//div//span[2]")
    private List<WebElement> address_autosuggestion_list_on_create_proposal_popup;

    @FindBy(xpath = "//div[@class='pac-container pac-logo hdpi']//div//span[3]")
    private List<WebElement> address_citystatecountry_autosuggestion_list_on_create_proposal_popup;

    @FindBy(xpath = "//div[@class='swal2-popup swal2-modal swal2-show']")
    private WebElement cancel_confirmation_popup;

    @FindBy(xpath = "//div[@class='swal2-popup swal2-modal swal2-show']//p[2]")
    private WebElement extract_confirmation_code;

    @FindBy(xpath = "//div[@class='swal2-popup swal2-modal swal2-show']//input[@id='txtConfirmNumber']")
    private WebElement confirmation_code_textbox;

    @FindBy(xpath = "//*[text()='OK']")
    private WebElement confirmation_ok_button;

    @FindBy(xpath = "//a[contains(@class,'proposalsetting')]")
    private WebElement proposalsetting_icon;

    //======================================== Proposal Page Functions =======================================

    public String get_ProposalsViewPageTitle() {
        wait_For_Page_To_Be_Stable(getDriver());
        return getDriver().getTitle();
    }

    /**
     * Clicks the proposal menu
     *
     * @Author Balaji N
     */
    public void ClickOnProposalsMenu() {
        logPageLoad("Proposal View Page", () -> {
            int maxRetries = 3;
            int attempt = 0;
            boolean success = false;

            while (attempt < maxRetries && !success) {
                try {
                    js_Click(ProposalsMenu, "Proposal Menu");
                    wait_For_Page_To_Be_Stable(getDriver());
                    success = true;
                } catch (StaleElementReferenceException | ElementNotInteractableException e) {
                    attempt++;

                    Allure.step("Retry " + attempt + ": Click on 'Proposal Menu' failed due to: " + e.getClass().getSimpleName());

                    // Try refreshing the WebElement reference
                    try {
                        PageFactory.initElements(getDriver(), this);
                    } catch (Exception refreshEx) {
                        Allure.step("Failed to reinitialize elements: " + refreshEx.getMessage());
                    }

                    if (attempt == maxRetries) {
                        Allure.step("Click on 'Proposal Menu' failed after " + maxRetries + " attempts.");
                        throw e;
                    }
                } catch (Exception ex) {
                    Allure.step("Unexpected error while clicking 'Proposal Menu': " + ex.getMessage());
                    throw ex;
                }
            }
        });
    }


    /**
     * Clicks the add proposal button on view proposal page
     *
     * @Author Balaji N
     */
    public void Click_AddProposalBtn() {
        logPageLoad("Proposal Page → Add Proposal Button", () -> {
            js_Click(AddProposalBtn, "Add Proposal Button");
        });
    }

    /**
     * Verifies the create proposal header popup on view proposal page is displayed
     *
     * @return If the create proposal header popup is displayed it returns true; otherwise, it returns false
     * @Author Balaji N
     */
    public boolean Verify_CreateProposalHeader_Popup() {
        isElementDisplayed(CreateProposalPopup, "Create Proposal Popup");
        return is_Element_Displayed(CreateProposalHeader_Popup, "Create Proposal Popup Header");
    }

    // Last updated on Jan 13 2025 by Balaji N

    /**
     * Searches and selects the customer on proposal view page
     *
     * @param customerName the name of the customer
     * @Author Balaji N
     */

    public void SearchandSelect_Customer_OnProposal(String customerName) {
        int maxRetries = 3;
        int retryCount = 0;
        boolean success = false;

        while (retryCount < maxRetries && !success) {
            try {
                fluentWait(SearchCustomer_SearchboxField, 15, 2000);
                ClickAndType(SearchCustomer_SearchboxField, customerName, "Search Customer Name textbox field on create proposal popup");
                delayWithGivenTime(2000);

                if (isElementDisplayed(list_of_customer_options_on_proposalview_page, "List of customer autosuggestion options in the customer search textbox field")) {
                    By customer = By.xpath("//ul[@id='ui-id-2']//li//div//div//span[contains(text(),'" + customerName + "')]");
                    WebElement selectcustomer = getDriver().findElement(customer);
                    click(selectcustomer, "Select the customer from the autosuggestion list in the create proposal popup");
                    success = true; // successfully selected customer, exit loop
                } else {
                    // Autosuggestion list not displayed - log and throw to retry or fail fast
                    String errorMessage = "Autosuggestion list is NOT displayed for customer: '" + customerName + "'";
                    System.out.println(errorMessage);
                    throw new NoSuchElementException(errorMessage);
                }

            } catch (ElementNotInteractableException | StaleElementReferenceException | TimeoutException |
                     NoSuchElementException e) {
                retryCount++;
                System.out.println("Retry " + retryCount + "/" + maxRetries + ": " + e.getClass().getSimpleName() + " encountered. Retrying...");
                delayWithGivenTime(2000);
            } catch (Exception e) {
                // For unexpected exceptions, log and break
                System.out.println("Unexpected error: " + e.getMessage());
                break;
            }
        }

        if (!success) {
            throw new RuntimeException("Failed to search and select customer in create proposal popup after " + maxRetries + " attempts.");
        }
    }

   /* public void SearchandSelect_Customer_OnProposal(String customerName) {
        fluentWait(SearchCustomer_SearchboxField, 15, 2000);
        ClickAndType(SearchCustomer_SearchboxField, customerName, "Search Customer Name textbox on create proposal popup");
        delayWithGivenTime(2000);

        if (is_Element_Displayed(list_of_customer_options_on_proposalview_page, "List of customer autosuggestion options in the customer search textbox field") == true) {
            By customer = By.xpath("//ul[@id='ui-id-2']//li//div//div//span[contains(text(),'" + customerName + "')]");
            WebElement selectcustomer = getDriver().findElement(customer);
            js_Click(selectcustomer, "Select the customer from the autosugesstion list in the create proposal popup");
        }
    }*/

    public String Verify_CustomerNameIsDisplayed_On_SearchTextBox() {
        return getElementAttribute(SearchCustomer_SearchboxField, "Customer Name on Search Textbox field");
    }

    /**
     * Clicks the add proposal button on create proposal popup
     *
     * @Author Balaji N
     */
    public void Click_AddProposal_On_CreateProposal_Popup() {
        logPageLoad("Proposal Page → Add Proposal Button on Create Proposal Popup", () -> {
            js_Click(AddProposalBtn_On_CreateProposalPopup, "Add Proposal Button on Create Proposal Popup");
        });
    }

    /**
     * Clicks the proposal id label on view proposal page
     *
     * @Author Balaji N
     */
    public void Click_Proposal_Id_Label() {
        js_Click(proposal_Id_label, "Proposal Id Label");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    public boolean Verify_Proposal_Id_Sort_desc_Icon() {
        HighlightElement(proposal_Id_Sort_desc_Icon);
        return proposal_Id_Sort_desc_Icon.isDisplayed();
    }

    public boolean Verify_Proposal_Id_Sort_asc_Icon() {
        HighlightElement(proposal_Id_Sort_asc_Icon);
        return proposal_Id_Sort_asc_Icon.isDisplayed();
    }


    public void Select_Proposal_StatusType_DropDown(String statusType) {
        drop_Down(proposal_StatusType_DropDown, statusType, "VisibleText", "Proposal Status Type Dropdown");
    }

    public String get_Selected_Proposal_StatusType() {
        HighlightElement(proposal_StatusType_DropDown);
        Select s = new Select(proposal_StatusType_DropDown);
        return s.getFirstSelectedOption().getText();
    }

    /**
     * Clicks the proposal action menu button on table grid
     *
     * @Author Balaji N
     */
    public void Click_Proposal_Action_Button() {
        Click(proposal_Action_Button, "Proposal Action Menu Button");
    }

    public boolean Verify_ViewProposal_ActionMenu_Popup_IsDisplayed() {
        return is_Element_Displayed(ActionMenu_Popup_Options, "Action Menu view proposal page");
    }

    /**
     * Clicks the customer proposal view button on view proposal page
     *
     * @Author Balaji N
     */
    public void Click_Proposal_CustProposalView_Button() {
        //  Mouse_Hover(proposal_CustProposalView_Button, "Action Menu View Proposal - CustomerProposal View Button");
        explicitWait(proposal_CustProposalView_Button, 15);
        delayWithGivenTime(1000);
        js_Click(proposal_CustProposalView_Button, "Action Menu View Proposal - CustomerProposal View Button");
        delayWithGivenTime(2000);
        switchToWindowbyIndex(1);
    }

    public boolean Verify_ItemNameIsDisplayed_on_CustProposalView() {
        return is_Element_Displayed(proposal_CustProposalView_itemName, "Item Name on Customer Proposal View");
    }

    public void Enter_ProposalId_In_Proposal_GlobalSearchBox(String proposalId) {
        fluentWait(proposal_SearchBox);
        proposal_SearchBox.clear();
        delayWithGivenTime(1000);
        proposal_SearchBox.sendKeys(proposalId);
        delayWithGivenTime(1000);
        proposal_SearchBox.sendKeys(Keys.ENTER);
    }

    public List<String> proposal_id_list_originalOrder() {
        List<WebElement> cells;
        List<String> originalValues = new ArrayList<>();

        try {
            // Attempt to get the list of elements
            cells = listofproposal_id_view_proposal;
            for (WebElement cell : cells) {
                HighlightElement(cell);
                originalValues.add(cell.getText().trim());
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("StaleElementReferenceException occurred. Re-locating elements...");
            // Re-locate the elements and try again
            cells = getDriver().findElements(By.xpath("(//table[@role='grid'])[2]//tbody//tr//td[3]"));
            for (WebElement cell : cells) {
                HighlightElement(cell);
                originalValues.add(cell.getText().trim());
            }
        }

        System.out.println("Original Proposal IDs: " + originalValues);
        return originalValues;
    }


    /**
     * Validate whether the list of proposal id are in ascending order or not
     *
     * @return If proposal id s are in ascending order it returns true or else false
     * @Author Balaji N
     */

    public boolean validate_ProposalId_Arranged_As_Ascending() {
        try {
            // pre - requiste
            Set_Page_number_As_50();


            // Step 1: Fetch and store the original state of proposal IDs
            List<WebElement> proposalIdElements = listofproposal_id_view_proposal;
            List<String> originalValues = new ArrayList<>();
            for (WebElement element : proposalIdElements) {
                HighlightElement(element);
                String text = element.getText().trim();
                if (!text.isEmpty()) {
                    originalValues.add(text);
                }
            }
            System.out.println("Original Proposal IDs: " + originalValues);

            // Step 2: Click on the Proposal ID label to sort in ascending order
            Click_Proposal_Id_Label();

            // Step 3: Verify that the upward arrow icon is displayed
            boolean isUpArrowDisplayed = Verify_Proposal_Id_Sort_asc_Icon();
            if (!isUpArrowDisplayed) {
                System.out.println("Upward arrow icon is not displayed.");
                return false;
            }
            System.out.println("Upward arrow icon is displayed.");

            // Step 4: Sort the original list programmatically to get the expected ascending order
            List<String> expectedSortedValues = new ArrayList<>(originalValues);
            Collections.sort(expectedSortedValues);
            System.out.println("Expected Sorted Proposal IDs: " + expectedSortedValues);

            // Step 5: Fetch the current list of proposal IDs from the UI
            List<String> actualSortedValues = new ArrayList<>();
            for (WebElement element : proposalIdElements) {
                HighlightElement(element);
                String text = element.getText().trim();
                if (!text.isEmpty()) {
                    actualSortedValues.add(text);
                }
            }
            System.out.println("Actual Sorted Proposal IDs: " + actualSortedValues);

            // Step 6: Validate if the current UI list matches the expected sorted list
            if (actualSortedValues.equals(expectedSortedValues)) {
                System.out.println("Proposal IDs are sorted correctly in ascending order.");
                return true;
            } else {
                System.out.println("Proposal IDs are not sorted correctly.");
                System.out.println("Mismatch:\nExpected: " + expectedSortedValues + "\nActual: " + actualSortedValues);
                return false;
            }
        } catch (Exception e) {
            // Log any exceptions encountered during validation
            System.err.println("Error validating Proposal ID sorting: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean validate_ProposalId_Sorting_As_Ascending() {
        // Pre-requisite: Set page number as 50
        Set_Page_number_As_50();

        // Initialize a list to store invoice numbers
        List<String> originalInvoiceNumbers = new ArrayList<>();

        try {
            // Store the proposal IDs in the list
            for (WebElement proposalIdElement : listofproposal_id_view_proposal) {
                HighlightElement(proposalIdElement);
                originalInvoiceNumbers.add(proposalIdElement.getText());
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("StaleElementReferenceException occurred. Re-locating elements...");
            listofproposal_id_view_proposal = getDriver().findElements(By.xpath("(//table[@role='grid'])[2]//tbody//tr//td[3]"));
            for (WebElement proposalIdElement : listofproposal_id_view_proposal) {
                HighlightElement(proposalIdElement);
                originalInvoiceNumbers.add(proposalIdElement.getText());
            }
        }

        delayWithGivenTime(2000);

        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
        Collections.sort(sortedInvoiceNumbers);

        // Compare the original list with the sorted list
        if (originalInvoiceNumbers.equals(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original proposal IDs: " + originalInvoiceNumbers);
            System.out.println("Sorted proposal IDs: " + sortedInvoiceNumbers);
            System.out.println("Proposal IDs are not sorted in ascending order.");
            return false;
        }
    }


    public boolean Verify_Proposal_Id_Sorted_As_Descending() {
        // Initialize a list to store proposal IDs
        List<String> originalProposalIds = new ArrayList<>();

        try {
            // Store the proposal IDs in the list
            for (WebElement invoiceElement : listofproposal_id_view_proposal) {
                originalProposalIds.add(invoiceElement.getText());
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("StaleElementReferenceException occurred. Re-locating elements...");
            listofproposal_id_view_proposal = getDriver().findElements(By.xpath("(//table[@role='grid'])[2]//tbody//tr//td[3]")); // Replace with your actual locator
            for (WebElement invoiceElement : listofproposal_id_view_proposal) {
                originalProposalIds.add(invoiceElement.getText());
            }
        }

        delayWithGivenTime(2000);

        // Create a copy of the list and sort it in reverse (descending) order
        List<String> sortedProposalIds = new ArrayList<>(originalProposalIds);
        Collections.sort(sortedProposalIds, Collections.reverseOrder());

        // Compare the original list with the sorted list
        if (originalProposalIds.equals(sortedProposalIds)) {
            return true;
        } else {
            System.out.println("Original proposal IDs: " + originalProposalIds);
            System.out.println("Sorted proposal IDs (descending): " + sortedProposalIds);
            System.out.println("Proposal IDs are not sorted in descending order.");
            return false;
        }
    }

    public void Click_proposal_type_label_tablegridheader() {
        js_Click(proposal_type_label_tablegridheader, "proposal type label");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    public boolean validate_ProposalItype_Sorting_As_Ascending() {
        Set_Page_number_As_50();
        // Store the invoice numbers in a list
        List<String> originalInvoiceNumbers = new ArrayList<>();
        for (WebElement proposaltypeElement : listofproposal_type) {
            HighlightElement(proposaltypeElement);
            originalInvoiceNumbers.add(proposaltypeElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
        Collections.sort(sortedInvoiceNumbers);

        // Compare the original list with the sorted list
        if (originalInvoiceNumbers.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original proposal types: " + originalInvoiceNumbers);
            System.out.println("Sorted proposal types: " + sortedInvoiceNumbers);
            System.out.println("proposal types are not sorted in ascending order.");
            return false;
        }
    }

    public boolean Verify_Proposal_type_Sorted_As_Descending() {
        // Store the invoice numbers in a list
        List<String> originalproposalIds = new ArrayList<>();
        for (WebElement invoiceElement : listofproposal_type) {
            originalproposalIds.add(invoiceElement.getText());
        }
        delayWithGivenTime(2000);

        List<String> sorted_proposal_id = new ArrayList<>(originalproposalIds);
        Collections.sort(sorted_proposal_id, Collections.reverseOrder());

        if (originalproposalIds.containsAll(sorted_proposal_id)) {
            return true;
        } else {
            System.out.println("proposal types are not sorted in descending order.");
            return false;
        }
    }

    public List<String> proposal_type_list_originalOrder() {
        List<String> originalValues = new ArrayList<>();
        int retryCount = 3; // Number of retries for handling stale elements
        for (int attempt = 0; attempt < retryCount; attempt++) {
            try {
                // Re-locate the elements in each attempt to handle StaleElementReferenceException
                List<WebElement> cells = listofproposal_type;
                originalValues.clear(); // Clear values before retrying

                for (WebElement cell : cells) {
                    HighlightElement(cell); // Highlighting element (assume implemented elsewhere)
                    originalValues.add(cell.getText().trim());
                }

                System.out.println("Original Proposal types: " + originalValues);
                return originalValues; // Exit the method if successful
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException occurred, retrying... Attempt: " + (attempt + 1));
                // Optionally include a small wait here to allow DOM stabilization
            }
        }
        // If all attempts fail, throw an exception or handle it as per your requirement
        throw new RuntimeException("Unable to retrieve proposal types due to repeated StaleElementReferenceException");
    }


    public void Click_Invoice_label_headertablegrid_view_proposal() {
        js_Click(Invoice_label_headertablegrid_view_proposal, "Invoice label");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    public void Set_Page_number_As_50() {
        fluentWait(pagination_dropdown_arrow);
        explicitWait(pagination_dropdown_arrow);
        delayWithGivenTime(4000);
        jsClick(pagination_dropdown_arrow);//pagination_dropdown_arrow.click();

        fluentWait(dropdownvalues_of_pageNumber);
        explicitWait(dropdownvalues_of_pageNumber);
        jsClick(dropdownvalues_of_pageNumber);
    }

    public boolean validate_invoice_Sorting_As_Ascending() {
        // pre - requiste
        Set_Page_number_As_50();

        // Store the invoice numbers in a list
        List<String> originalInvoiceNumbers = new ArrayList<>();
        for (WebElement proposaltypeElement : listofinvoices_on_tablegrid_invoicecolumn) {
            HighlightElement(proposaltypeElement);
            originalInvoiceNumbers.add(proposaltypeElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
        Collections.sort(sortedInvoiceNumbers);

        // Compare the original list with the sorted list
        if (originalInvoiceNumbers.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original proposal types: " + originalInvoiceNumbers);
            System.out.println("Sorted proposal types: " + sortedInvoiceNumbers);
            System.out.println("proposal types are not sorted in ascending order.");
            return false;
        }
    }

    public boolean Verify_invoice_Sorted_As_Descending() {
        // Store the invoice numbers in a list
        List<String> originalproposalIds = new ArrayList<>();
        for (WebElement invoiceElement : listofinvoices_on_tablegrid_invoicecolumn) {
            originalproposalIds.add(invoiceElement.getText());
        }
        delayWithGivenTime(2000);

        List<String> sorted_invoices = new ArrayList<>(originalproposalIds);
        Collections.sort(sorted_invoices, Collections.reverseOrder());

        if (originalproposalIds.containsAll(sorted_invoices)) {
            return true;
        } else {
            System.out.println("proposal types are not sorted in descending order.");
            return false;
        }
    }

    public List<String> invoice_list_originalOrder() {
        List<WebElement> cells = listofinvoices_on_tablegrid_invoicecolumn;
        // Extract text values from each cell and store them in a list
        List<String> originalValues = new ArrayList<>();
        for (WebElement cell : cells) {
            HighlightElement(cell);
            originalValues.add(cell.getText().trim());
        }
        System.out.println("Original Invoices: " + originalValues);
        return originalValues;
    }

    public void Click_Date_Label_on_ViewProposal() {
        js_Click(date_label_viewproposal, "Date label");
        wait_For_Page_To_Be_Stable(getDriver());
    }

    public List<String> date_list_originalOrder() {
        List<WebElement> cells = listofdates_viewproposal;
        // Extract text values from each cell and store them in a list
        List<String> originalValues = new ArrayList<>();
        for (WebElement cell : cells) {
            HighlightElement(cell);
            originalValues.add(cell.getText().trim());
        }
        System.out.println("Original dates: " + originalValues);
        return originalValues;
    }

    public boolean validate_date_Sorting_As_Ascending() {
        // pre - requiste
        Set_Page_number_As_50();
        wait_For_Page_To_Be_Stable(getDriver());

        // Store the invoice numbers in a list
        List<String> originalInvoiceNumbers = new ArrayList<>();
        for (WebElement proposaltypeElement : listofdates_viewproposal) {
            HighlightElement(proposaltypeElement);
            originalInvoiceNumbers.add(proposaltypeElement.getText());
        }
        delayWithGivenTime(2000);
        // Create a copy of the list and sort it
        List<String> sortedInvoiceNumbers = new ArrayList<>(originalInvoiceNumbers);
        Collections.sort(sortedInvoiceNumbers);

        // Compare the original list with the sorted list
        if (originalInvoiceNumbers.containsAll(sortedInvoiceNumbers)) {
            return true;
        } else {
            System.out.println("Original proposal types: " + originalInvoiceNumbers);
            System.out.println("Sorted proposal types: " + sortedInvoiceNumbers);
            System.out.println("proposal types are not sorted in ascending order.");
            return false;
        }
    }

    public boolean Verify_date_Sorted_As_Descending() {
        // Store the invoice numbers in a list
        List<String> originalproposalIds = new ArrayList<>();
        for (WebElement invoiceElement : listofdates_viewproposal) {
            originalproposalIds.add(invoiceElement.getText());
        }
        delayWithGivenTime(2000);

        List<String> sorted_invoices = new ArrayList<>(originalproposalIds);
        Collections.sort(sorted_invoices, Collections.reverseOrder());

        if (originalproposalIds.containsAll(sorted_invoices)) {
            return true;
        } else {
            System.out.println("proposal types are not sorted in descending order.");
            return false;
        }
    }

    public void Click_DatePicker_InputBox() {
        wait_For_Page_To_Be_Stable(getDriver());
        datepicker_inputbox.click();
        wait_For_Page_To_Be_Stable(getDriver());
    }

    public String get_displayed_filtered_dates_datepicker_inputtextbox() {
        return getElementAttribute(datepicker_inputbox, "Datepicker inputbox");
    }

    public boolean Verify_DatePicker_Popup_IsDisplayed() {
        return isElementDisplayed(datepicker_popup, "Datepicker popup");
    }

    public boolean Verify_fromDate_Mini_input_textbox_IsDisplayed() {
        return isElementDisplayed(calender_section_1_fromdate_mini_inputbox, "From date mini input textbox");
    }

    public String get_fromdate_mini_input_textbox() {
        HighlightElement(calender_section_1_fromdate_mini_inputbox);
        return calender_section_1_fromdate_mini_inputbox.getAttribute("value");
    }

    public boolean Verify_ToDate_Mini_input_textbox_IsDisplayed() {
        return isElementDisplayed(calender_section_1_Todate_mini_inputbox, "To date mini input textbox");
    }

    public String get_todate_mini_input_textbox() {
        return getElementAttribute(calender_section_1_Todate_mini_inputbox, "To date mini input textbox");
    }

    public boolean Verify_Apply_Button_On_Calendar_Section1_IsDisplayed() {
        return isElementDisplayed(calendar_section_1_ApplyButton, "Apply Button");
    }

    public void Click_ApplyButton_On_CalendarSection() {
        js_Click(calendar_section_1_ApplyButton, "Apply Button");
        wait_For_Page_To_Be_Stable(getDriver());
    }


    public boolean Verify_ClearButton_IsDisplayed_On_Calendar_Section1() {
        return isElementDisplayed(calendar_section_1_ClearButton, "Clear Button");
    }

    public void Click_ClearButton_on_Calendar_Section() {
        js_Click(calendar_section_1_ClearButton, "Clear Button");
    }

    public boolean Verify_LeftCalendar_section_isDisplayed() {
        return isElementDisplayed(left_calendar_section, "Left Calendar Section");
    }

    public boolean Verify_leftArrow_isDisplayed_On_leftcalendar() {
        return isElementDisplayed(leftcalendar_leftarrow, "Left Arrow");
    }

    public void Click_LeftArrow_FromDate_Calendar() {
        js_Click(leftcalendar_leftarrow, "Left Arrow");
    }


    public boolean Verify_selectmonth_dropdown_isDisplayedOn_leftcalendar() {
        return isElementDisplayed(leftcalendar_selectmonth_dropdown, "Select month dropdown");
    }

    public boolean verify_previous_Month_IsDisplayed() {
        Select select = new Select(leftcalendar_selectmonth_dropdown);
        String month = select.getFirstSelectedOption().getText();
        System.out.println("Actual Previous Month : " + month);
        System.out.println("Expected Previous Month : " + GetPreviousMonth());
        if (month.equals(GetPreviousMonth())) {
            return true;
        }
        return false;
    }

    public boolean Verify_selectyear_dropdown_isDisplayedOn_leftcalendar() {
        return isElementDisplayed(leftcalendar_selectyear_dropdown, "Select year dropdown");
    }

    public boolean Verify_rightArow_isDisplayed_On_leftcalendar() {
        return isElementDisplayed(leftcalendar_rightArrow_button, "Right Arrow");
    }

    public boolean verify_next_Month_IsDisplayed() {
        Select select = new Select(leftcalendar_selectmonth_dropdown);
        String month = select.getFirstSelectedOption().getText();
        System.out.println("Actual Next displayed month " + month);
        System.out.println("Expected Next displayed month " + GetCurrentMonth());
        if (month.equals(GetCurrentMonth())) {
            return true;
        }
        return false;
    }

    public void Click_RightArrow_FromDate_Calendar() {
        js_Click(leftcalendar_rightArrow_button, "Right Arrow");
    }


    public boolean Verify_selectmonth_dropdown_isDisplayed_on_rightcalendar() {
        return isElementDisplayed(rightcalendar_selectmonth_dropdown, "Select month dropdown");
    }

    public boolean verify_next_Month_IsDisplayed_on_ToDate() {
        Select select = new Select(rightcalendar_selectmonth_dropdown);
        String month = select.getFirstSelectedOption().getText();
        System.out.println("Actual next displayed month on  To Date Calendar" + month);
        System.out.println("Expected next displayed month on To Date Calendar" + GetNextMonth());
        if (month.equals(GetNextMonth())) {
            return true;
        }
        return false;
    }

    public boolean Verify_selectyear_dropdown_isDisplayed_on_rightcalendar() {
        return isElementDisplayed(rightcalendar_selectyear_dropdown, "Select year dropdown");
    }

    public boolean Verify_rightArrow_IsDisplayed_On_RightCalendar() {
        return isElementDisplayed(rightcalendar_rightArrow, "Right Arrow");
    }

    public void Click_RightArrow_FromDate_RightCalendar() {
        js_Click(rightcalendar_rightArrow, "Right Arrow");
    }


    public boolean Verify_rightcalendar_IsDisplayed() {
        return isElementDisplayed(rightcalendar_datepicker, "Right Calendar");
    }

    public void Click_FromDate_on_LeftCalendar(String date) {
        date_Picker(leftcalendar_selectmonth_dropdown, leftcalendar_selectyear_dropdown, leftcalendar_rightArrow_button, listofleftcalendar_day, date);
    }

    public void Click_ToDate_on_RightCalendar(String date) {
        date_Picker(rightcalendar_selectmonth_dropdown, rightcalendar_selectyear_dropdown, rightcalendar_rightArrow, listofrighttcalendar_day, date);
    }

    public void Click_LeftArrow_ToDate_on_RightCalendar() {
        js_Click(rightcalendar_leftArrow_button, "Left Arrow");
    }

    public boolean verify_previous_Month_IsDisplayed_on_ToDate() {
        Select select = new Select(rightcalendar_selectmonth_dropdown);
        String month = select.getFirstSelectedOption().getText();
        System.out.println("Actual previous displayed month on To Date Calendar" + month);
        System.out.println("Expected previous displayed month on To Date Calendar" + GetCurrentMonth());
        if (month.equals(GetCurrentMonth())) {
            return true;
        }
        return false;
    }

    public void Click_CancelButton_On_createproposal_popup() {
        js_Click(cancelButton_on_createproposal_popup, "Cancel Button");
    }

    /**
     * Checks if the "Create Proposal" popup is closed.
     *
     * @return true if the popup is closed, false if it is still open.
     * @Author Balaji N
     */
    public boolean isCreateProposalPopupClosed() {
        try {
            // Check if the popup is displayed
            if (create_proposal_popup.isDisplayed()) {
                System.out.println("Create Proposal popup is still open.");
                return false; // Popup is still open
            }
        } catch (NoSuchElementException e) {
            System.out.println("Create Proposal popup is not found in the DOM, it is closed.");
            return true; // Popup is closed
        } catch (StaleElementReferenceException e) {
            System.out.println("Create Proposal popup is no longer attached to the DOM, it is closed.");
            return true; // Popup is closed
        }
        return true; // Assuming popup is closed if no exceptions and not displayed
    }

    public void Enter_FirstName_on_CreateProposal_Popup(String firstname) {
        ClickAndType(createproposalpopup_firstname_field, firstname, "First Name textbox field on create proposal popup at proposals page");
    }

    public String get_createproposalpopup_firstname_field() {
        HighlightElement(createproposalpopup_firstname_field);
        return createproposalpopup_firstname_field.getAttribute("value");
    }

    public void Enter_LastName_on_CreateProposal_Popup(String lastname) {
        clickAndType(createproposalpopup_lasttname_field, lastname);
    }

    public String get_createproposalpopup_lasttname_field() {
        HighlightElement(createproposalpopup_lasttname_field);
        return createproposalpopup_lasttname_field.getAttribute("value");
    }

    public void Enter_CompanyName_on_CreateProposal_Popup(String companyname) {
        clickAndType(createproposalpopup_companyname_field, companyname);
    }

    public String get_createproposalpopup_companyname_field() {
        HighlightElement(createproposalpopup_companyname_field);
        return createproposalpopup_companyname_field.getAttribute("value");
    }

    public void Enter_EmailId_on_CreateProposal_Popup(String emailid) {
        clickAndType(createproposalpopup_email_field, emailid);
    }

    public String get_createproposalpopup_email_field() {
        HighlightElement(createproposalpopup_email_field);
        return createproposalpopup_email_field.getAttribute("value");
    }

    public void Enter_City_on_CreateProposal_Popup(String city) {
        clickAndType(createproposalpopup_city_field, city);
    }

    public void clear_City_Value() {
        createproposalpopup_city_field.clear();
    }

    public String get_createproposalpopup_city_field() {
        HighlightElement(createproposalpopup_city_field);
        return createproposalpopup_city_field.getAttribute("value");
    }

    public void Enter_State_on_CreateProposal_Popup(String state) {
        clickAndType(createproposalpopup_state_field, state);
    }

    public String get_createproposalpopup_state_field() {
        HighlightElement(createproposalpopup_state_field);
        return createproposalpopup_state_field.getAttribute("value");
    }

    public void Enter_Address_on_CreateProposal_Popup(String address) {
        fluentWait(createproposalpopup_address_field);
        createproposalpopup_address_field.clear();
        clickAndType(createproposalpopup_address_field, address);
        createproposalpopup_address_field.click();
    }

    public String get_createproposalpopup_address_field() {
        HighlightElement(createproposalpopup_address_field);
        return createproposalpopup_address_field.getAttribute("value");
    }

    public void Enter_Zipcode_on_CreateProposal_Popup(String zipcode) {
        clickAndType(createproposalpopup_zipcode_field, zipcode);
    }

    public String get_createproposalpopup_zipcode_field() {
        HighlightElement(createproposalpopup_zipcode_field);
        return createproposalpopup_zipcode_field.getAttribute("value");
    }

    public void Enter_PhoneNumber_on_CreateProposal_Popup(String phonenumber) {
        clickAndType(createproposalpopup_phonenumber_field, phonenumber);
    }

    public String get_createproposalpopup_phonenumber_field() {
        HighlightElement(createproposalpopup_phonenumber_field);
        return createproposalpopup_phonenumber_field.getAttribute("value");
    }

    public void Enter_SecondaryPhoneNumber_on_CreateProposal_Popup(String secondaryphonenumber) {
        clickAndType(createproposalpopup_altphonenumber_field, secondaryphonenumber);
    }

    public String get_createproposalpopup_altphonenumber_field() {
        HighlightElement(createproposalpopup_altphonenumber_field);
        return createproposalpopup_altphonenumber_field.getAttribute("value");
    }

    public void Select_createproposalpopup_selectreport_dropdownfield(String report) {

        if (isElementDisplayed(createproposalpopup_selectreport_dropdownfield) == true) {
            //  createproposalpopup_selectreport_dropdownfield.click();
            //  delayWithGivenTime(1000);
            dropDown(createproposalpopup_selectreport_dropdownfield, report, "VisibleText");
        } else {
            for (int i = 0; i < list0freports_dropdownoptions.size(); i++) {
                if (list0freports_dropdownoptions.get(i).getText().equalsIgnoreCase(report)) {
                    explicitWait_for_ClickAction(list0freports_dropdownoptions.get(i));
                    list0freports_dropdownoptions.get(i).click();
                    break;
                }
            }
        }
    }

    public String get_Select_createproposalpopup_selectreport_dropdownfield() {
        HighlightElement(createproposalpopup_selectreport_dropdownfield);
        Select select = new Select(createproposalpopup_selectreport_dropdownfield);
        return select.getFirstSelectedOption().getText();
    }


    public void Select_createproposalpopup_selectpackage_dropdownfield(String Package) {
        drop_Down(createproposalpopup_selectpackage_dropdownfield, Package, "VisibleText", "Select Package dropdown on Create Proposal popup");
    }

    public String get_createproposalpopup_selectpackage_dropdownfield() {
        Select select = new Select(createproposalpopup_selectpackage_dropdownfield);
        return select.getFirstSelectedOption().getText();
    }

    public void Select_createproposalpopup_select_eventorwedding_dropdownfield(String event_wedding) {
        if (isElementDisplayed(createproposalpopup_select_eventorwedding_dropdownfield) == true) {
            dropDown(createproposalpopup_select_eventorwedding_dropdownfield, event_wedding, "VisibleText");
        }
    }

    public String get_createproposalpopup_select_eventorwedding_dropdownfield() {
        HighlightElement(createproposalpopup_select_eventorwedding_dropdownfield);
        Select select = new Select(createproposalpopup_select_eventorwedding_dropdownfield);
        return select.getFirstSelectedOption().getText();
    }

    public String get_customerid_createproposalpopup() {
        // Get the text content of the element
        String customerText = createproposalpopup_customerid.getText();
        // Extract the numerical part
        String customerId = customerText.split(":")[1].trim();
        return customerId;
    }

    public void Clear_Customer_On_createproposalpopup() {
        jsClick(createproposalpopup_customer_clear_button);
    }


    public boolean verifySuccessToastMessageAppears() {
        return isElementDisplayed(SuccessToastMsg, "Success Toast Message");
    }

    public String verifySuccessToastMessageText() {
        try {
            WebElement successToastMsg = getDriver().findElement(sucessToastMsg);
            String toastText = getElementText(successToastMsg, "Success Toast Message Text on Proposals Page");
            return toastText;
        } catch (TimeoutException e) {
            Allure.step("Toast message on Proposals Page not visible within expected time.");
            return "Toast message not visible";
        } catch (StaleElementReferenceException | NoSuchElementException ex) {
            Allure.step("Toast message on Proposals Page disappeared before capture. Exception: " + ex.getClass().getSimpleName());
            return "Toast message on Proposals Page disappeared too quickly";
        }
    }

    public void Click_event_tab_on_Createproposal_popup() {
        click(event_tab_on_Createproposal_popup, "Event tab on Createproposal popup");
    }

    public void Search_And_Select_Address_On_CreateProposal_Popup(String expectedAddress) {
        // Wait for the autosuggestions to be visible
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(address_autosuggestion_list_on_create_proposal_popup));

        // Stream to find the expected address
        Optional<WebElement> matchingAddress = address_autosuggestion_list_on_create_proposal_popup.stream()
                .filter(option -> option.getText().equalsIgnoreCase(expectedAddress))
                .findFirst();

        // Handle the presence or absence of the address
        if (matchingAddress.isPresent()) {
            matchingAddress.get().click(); // Click the found address
        } else {
            throw new RuntimeException("Expected address not found: " + expectedAddress);
        }
    }

    public void selectGoogleAddressSuggestion(String partialAddressText) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        By googleaddresssuggestions = By.xpath("//div[@class='pac-item']");
        try {
            // Wait until at least one suggestion appears
            wait.until(ExpectedConditions.presenceOfElementLocated(googleaddresssuggestions));

            // Build dynamic XPath based on input
            String dynamicXpath = String.format("//div[@class='pac-item' and contains(normalize-space(.), \"%s\")]", partialAddressText);

            WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));

            try {
                suggestion.click();
            } catch (Exception e) {
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", suggestion);
            }

            Allure.step("Selected Google suggestion: " + partialAddressText);

        } catch (TimeoutException e) {
            Allure.step("Timeout waiting for address suggestions to appear for: " + partialAddressText);
            throw new RuntimeException("No suggestions found matching: " + partialAddressText, e);
        } catch (Exception e) {
            Allure.step("Failed to select Google address suggestion: " + e.getMessage());
            throw e;
        }
    }

    public void searchAndSelect_Address_On_CreateProposal_Popup(String expectedAddress) {
        int maxRetries = 3;
        int retryCount = 0;
        boolean success = false;

        By addressfield = By.xpath("//div[@class='pac-item' and contains(normalize-space(),'" + expectedAddress + "')]");

        while (retryCount < maxRetries && !success) {
            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.presenceOfElementLocated(addressfield));
                WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(addressfield));

                try {
                    suggestion.click();
                } catch (Exception clickException) {
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", suggestion);
                }

                success = true;

            } catch (StaleElementReferenceException | TimeoutException e) {
                retryCount++;
                System.out.println("Retry " + retryCount + "/" + maxRetries + ": " + e.getClass().getSimpleName() + " encountered while selecting address suggestion '" + expectedAddress + "'. Retrying...");
                delayWithGivenTime(2000);
            } catch (Exception e) {
                String errorMsg = "Unexpected error while selecting address suggestion '" + expectedAddress + "': " + e.getMessage();
                System.out.println(errorMsg);
                Allure.step(errorMsg);
                throw e;
            }
        }

        if (!success) {
            String failMessage = "Failed to select address suggestion '" + expectedAddress + "' after " + maxRetries + " retries.";
            System.out.println(failMessage);
            Allure.step(failMessage);
            throw new RuntimeException(failMessage);
        }
    }


    public boolean verify_address_autosuggestion_on_createproposalPopup() {
        HighlightElement(address_autosuggestion_create_proposal_popup);
        return address_autosuggestion_create_proposal_popup.isDisplayed();
    }

    public String get_Invoice_Number_on_Created_Proposal() {
        return get_Element_Text(proposal_InvoiceNumber, "Invoice number on created proposal page");
    }

    public String get_Total_Amount_on_Created_Proposal() {
        return get_Element_Text(proposal_total_amount, "Get total amount on created proposal page");
    }

    public void Click_on_Invoice_Number_on_Created_Proposal() {
        logPageLoad("Invoice Number Hyperlink on View proposal page → Recent Order Page", () -> {
            click(proposal_InvoiceNumber, "Invoice number on created proposal page");
        });
    }

    public void click_on_Tick_Icon_for_Cancelled_Proposal_On_Created_Proposal() {
        jsClick(accept_cancel_tick_icon_on_proposal_grid, "Tick icon on created proposal page");
    }

    public boolean is_Cancelled_Proposal_Popup_On_Proposal_Grid() {
        return isElementDisplayed(cancel_confirmation_popup, "Cancelled confirmationproposal popup on proposal grid");
    }

    public String extract_Confirmation_Code_From_Cancelled_Proposal_Popup() {
        // Extract the number using regex
        String number = extract_confirmation_code.getText().trim().replaceAll("\\D+", "");

        return number;
    }

    public void enter_Confirmation_Code_On_Cancelled_Proposal_Popup(String confirmationCode) {
        ClickAndType(confirmation_code_textbox, confirmationCode, "Confirmation code on cancelled proposal popup");
    }

    public void click_OK_Button_On_Confirm_Button_On_Cancelled_Proposal_Popup() {
        jsClick(confirmation_ok_button, "OK button on cancelled proposal popup");
    }

    public void click_Proposal_Settings_Icon() {
        click(proposalsetting_icon, "Proposal settings icon on view proposal page");
    }

}