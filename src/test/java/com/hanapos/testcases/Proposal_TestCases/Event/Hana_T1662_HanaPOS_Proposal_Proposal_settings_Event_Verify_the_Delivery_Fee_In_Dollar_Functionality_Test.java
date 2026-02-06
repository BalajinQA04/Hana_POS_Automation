package com.hanapos.testcases.Proposal_TestCases.Event;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T1662_HanaPOS_Proposal_Proposal_settings_Event_Verify_the_Delivery_Fee_In_Dollar_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private Proposal_Settings_Page proposal_settings;
    private ManageProposalPage manageproposal;
    private DashboardOrderPage dashboardorder;
    private Configuration_SettingsPage settingsPage;
    private TaxSettings_Configuration_Setting_Page taxsettings_settingsPage;
    String proposalId;
    String invoiceNumber;
    public static LoggerUtil logger_Util;

    @Epic("Proposal Module - Event")
    @Test(priority = 1, enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T1662_HanaPOS_Proposal_Proposal_settings_Event_Verify_the_Delivery_Fee_In_Dollar_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 4
            delayWithGivenTime(1000);
            proposal = new ProposalsPage();
            proposal.ClickOnProposalsMenu();
            delayWithGivenTime(1000);
            proposal.click_Proposal_Settings_Icon();

            // Test Step - 5
            delayWithGivenTime(3000);
            proposal_settings = new Proposal_Settings_Page();
            softassert.assertTrue(proposal_settings.is_Proposal_Settings_Page_Displayed(), "Proposal settings page is not displayed");
            softassert.assertTrue(proposal_settings.is_Event_IncludeDeliveryFeeinTaxCalculation_Displayed(), "Event proposal settings Include delivery fee in tax calculation label is not displayed");
            softassert.assertTrue(proposal_settings.is_Weddding_IncludeDeliveryFeeinTaxCalculation_Displayed(), "Wedding proposal settings Include delivery fee in tax calculation label is not displayed");

            proposal_settings.select_Event_Delivery_Fee_Type("$");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Event_Delivery_Fee_Type(), "$", "Event proposal settings delivery fee type label is not displayed");

            proposal_settings.select_Event_Setup_Fee_Type("$");
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal_settings.get_Event_Setup_Fee_Type(), "$", "Event proposal settings setup fee type label is not displayed");

            proposal_settings.enter_Event_Setup_Fee("54.99");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Event_Setup_Fee(), "54.99", "Event proposal settings setup fee label is not displayed");

            proposal_settings.enter_Event_Delivery_Fee("11.99");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Event_Delivery_Fee(), "11.99", "Event proposal settings delivery fee label is not displayed");

            proposal_settings.click_EventProposalSettings_save_settings_button();

            // Test Step - 5
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 5 - Create a Proposal popup is not displayed");

            // Test Step - 7
            proposal.Click_event_tab_on_Createproposal_popup();
            delayWithGivenTime(1000);
            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            softassert.assertEquals(proposal.Verify_CustomerNameIsDisplayed_On_SearchTextBox(), prop.getProperty("custfullname"), "Test Step - 6: Searched customer full name is not displayed on create proposal popup");
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_createproposalpopup_firstname_field(), prop.getProperty("cust_firstName"), "Test Step - 6: First name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_lasttname_field(), prop.getProperty("cust_lastName"), "Test Step - 6: Last name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_companyname_field(), prop.getProperty("cust_companyName"), "Test Step - 6: COmpany Name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_email_field(), prop.getProperty("cust_email"), "Test Step - 6: Email Id of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_city_field(), prop.getProperty("cust_city"), "Test Step - 6: City of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_state_field(), prop.getProperty("cust_state"), "Test Step - 6: State of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_address_field(), prop.getProperty("cust_address1"), "Test Step - 6: Address of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_zipcode_field(), prop.getProperty("cust_zipcode"), "Test Step - 6: Zipcode of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_phonenumber_field(), prop.getProperty("cust_phoneNumber"), "Test Step - 6: Phone number of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_altphonenumber_field(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 6: Alternative Phone number of customer is not displayed in create proposal popup");
            delayWithGivenTime(2000);

            delayWithGivenTime(2000);
            proposal.Click_AddProposal_On_CreateProposal_Popup();

            // Test Step - 8
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            proposalId = manageproposal.get_ProposalId();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 8 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 9
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_FirstName(), prop.getProperty("cust_firstName"), "Test Step - 9 - Customer first name is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_LastName(), prop.getProperty("cust_lastName"), "Test Step - 9 - Customer last name is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_PhoneNumber(), "9566550756", "Test Step - 9 - Customer phonenumber is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Email(), prop.getProperty("cust_email"), "Test Step - 9 - Customer Email Id is not autopopulated in customer details section ");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_City(), prop.getProperty("cust_city"), "Test Step - 9 - Customer City is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_State(), prop.getProperty("cust_state"), "Test Step - 9 - Customer State is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Address(), prop.getProperty("cust_address1"), "Test Step - 9 - Customer Address is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Zip(), prop.getProperty("cust_zipcode"), "Test Step - 9 - Customer Zipcode is not autopopulated in customer details section ");
            delayWithGivenTime(2000);

            // Test Step - 10
            manageproposal.Enter_EventCustSection_FirstName("O'Brien");
            manageproposal.Enter_Event_CustSection_LastName("John");
            manageproposal.Enter_Event_CustSection_PhoneNumber("925-456-2305");
            manageproposal.Enter_Event_CustSection_Email("hanaposqateam@gmail.com");
            manageproposal.Enter_Event_CustSection_City("El Cajon");
            manageproposal.Enter_Event_CustSection_State("CA");
            manageproposal.Enter_Event_CustSection_Address("Blossom Valley");
            manageproposal.Enter_Event_CustSection_Zip("92021");

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_FirstName(), "O'Brien", "Test Step - 10 - Entered customer first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_LastName(), "John", "Test Step - 10 - Entered customer last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_PhoneNumber(), "925-456-2305", "Test Step - 10 - Entered customer phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Email(), "hanaposqateam@gmail.com", "Test Step - 10 - Entered customer email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_City(), "El Cajon", "Test Step - 10 - Entered customer city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_State(), "CA", "Test Step - 10 - Entered customer state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Address(), "Blossom Valley", "Test Step - 10 - Entered customer address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Zip(), "92021", "Test Step - 10 - Entered customer zip code is not displayed");

            // Test Step - 11
            manageproposal.enter_AltContact_FirstName(prop.getProperty("AltContact_FirstName_1"));
            manageproposal.enter_AltContact_LastName(prop.getProperty("AltContact_LastName_1"));
            manageproposal.enter_AltContact_PhoneNumber(prop.getProperty("AltContact_PhoneNumber_1"));
            manageproposal.enter_AltContact_EmailID(prop.getProperty("AltContact_EmailID_1"));
            manageproposal.enter_AltContact_Address(prop.getProperty("AltContact_Address_1"));
            manageproposal.enter_AltContact_City(prop.getProperty("AltContact_City_1"));
            manageproposal.enter_AltContact_State(prop.getProperty("AltContact_State_1"));
            manageproposal.enter_AltContact_Zipcode(prop.getProperty("AltContact_Zipcode_1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_AltContact_FirstName(), prop.getProperty("AltContact_FirstName_1"), "Test Step - 11 - Alternate Entered first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_LastName(), prop.getProperty("AltContact_LastName_1"), "Test Step - 11 - Alternate Entered last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_PhoneNumber(), prop.getProperty("AltContact_PhoneNumber_1"), "Test Step - 11 - Alternate Entered phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_EmailID(), prop.getProperty("AltContact_EmailID_1"), "Test Step - 11 - Alternate Entered email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_City(), prop.getProperty("AltContact_City_1"), "Test Step - 11 - Alternate Entered city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_State(), prop.getProperty("AltContact_State_1"), "Test Step - 11 - Alternate Entered state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_Address(), prop.getProperty("AltContact_Address_1"), "Test Step - 11 - Alternate Entered address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_Zipcode(), prop.getProperty("AltContact_Zipcode_1"), "Test Step - 11 - Alternate Entered zip code is not displayed");

            // Test Step - 12
            manageproposal.Click_On_eventdetailstab();
            manageproposal.enter_Event_Name(prop.getProperty("Event_Name"));
            manageproposal.enter_Event_Location(prop.getProperty("Event_Location"));
            manageproposal.enter_Event_Date();
            manageproposal.enter_Event_Time();
            manageproposal.enter_Event_Address(prop.getProperty("Event_Address"));
            manageproposal.enter_Event_ZipCode(prop.getProperty("Event_Zipcode"));
            //  manageproposal.enter_Event_City(prop.getProperty("Event_City"));
            //  manageproposal.enter_Event_State(prop.getProperty("Event_State"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Event_Name(), prop.getProperty("Event_Name"), "Test Step - 12 - Entered Event Name is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Location(), prop.getProperty("Event_Location"), "Test Step - 12 - Entered Event Location is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Date(), Next_20Days_Date(), "Test Step - 12 - Entered Event Date is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Time(), prop.getProperty("Event_Time"), "Test Step - 12 - Entered Event Time is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Address(), prop.getProperty("Event_Address"), "Test Step - 12 - Entered Event Address is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_ZipCode(), prop.getProperty("Event_Zipcode"), "Test Step - 12 - Entered Event Zipcode is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_City(), prop.getProperty("Event_City"), "Test Step - 12 - Entered Event City is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_State(), prop.getProperty("Event_State"), "Test Step - 12 - Entered Event State is not displayed correctly");

            // Test Step - 13
            manageproposal.Enter_FirstName_Event_Coordinator(prop.getProperty("Event_Coordinator_Fname"));
            manageproposal.Enter_LastName_Event_Coordinator(prop.getProperty("Event_Coordinator_Lname"));
            manageproposal.Enter_Phonenumber_Event_Coordinator(prop.getProperty("Event_Coordinator_Phonenumber"));
            manageproposal.Enter_EmailId_Event_Coordinator(prop.getProperty("Event_Coordinator_EmailId"));
            manageproposal.Enter_Address_Event_Coordinator(prop.getProperty("Event_Coordinator_Address"));
            manageproposal.Enter_Zipcode_Event_Coordinator(prop.getProperty("Event_Coordinator_Zipcode"));
            // manageproposal.Enter_City_Event_Coordinator(prop.getProperty("Event_Coordinator_City"));
            // manageproposal.Enter_State_Event_Coordinator(prop.getProperty("Event_Coordinator_State"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_entered_firstname_event_coordinator(), prop.getProperty("Event_Coordinator_Fname"), "Test Step - 13: Entered event coordinator first name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_lastname_event_coordinator(), prop.getProperty("Event_Coordinator_Lname"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_phonenumber_event_coordinator(), prop.getProperty("Event_Coordinator_Phonenumber"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_emailid_event_coordinator(), prop.getProperty("Event_Coordinator_EmailId"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_address_event_coordinator(), prop.getProperty("Event_Coordinator_Address"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_zipcode_event_coordinator(), prop.getProperty("Event_Coordinator_Zipcode"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_city_event_coordinator(), prop.getProperty("Event_Coordinator_City"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_state_event_coordinator(), prop.getProperty("Event_Coordinator_State"), "Test Step - 13: Entered event coordinator last name is not displayed");

            // Test Step - 14
            delayWithGivenTime(2000);
            manageproposal.Click_DocumentsAndImages_Tab();
            delayWithGivenTime(2000);
            manageproposal.UploadFiles("James heman proposal report.pdf");

            delayWithGivenTime(2000);
            softassert.assertTrue(manageproposal.verify_uploaded_firstdocument_displayed_on_grid(), "Test Step - 10: Uploaded first document is not displayed on documents and images tab");

            // Test Step - 15
            manageproposal.Click_ItemsAndProducts_Tab();

            // Test Step - 16
            delayWithGivenTime(2000);
            manageproposal.select_TaxType("QA Tax");
            delayWithGivenTime(1000);
            softassert.assertEquals(manageproposal.get_TaxType_Displayed(), "QA Tax", "Test Step - 16 - Tax type is not displayed on product table grid");

            delayWithGivenTime(2000);
            manageproposal.Select_CategoryDropdown_On_ItemsAndProducts("Mother's Day");
            manageproposal.Enter_ItemName_On_ItemsAndProducts("rrd", "rrd Small");
            manageproposal.Click_AddBtn_On_ItemsAndProducts();
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Row1_ItemCategory(), "Mother's Day", "Test Step - 16 - Selected category is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_itemCode(), "rrd", "Test Step - 16 - Item code is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_itemDescription(), "Red Rose Small", "Test Step - 16 - Item description is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_itemQuantity(), "1", "Test Step - 16 - Item quantity is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_item_TotalPrice(), "$199.00", "Test Step - 16 - Item total price is not displayed on row 1 product table grid");

            softassert.assertEquals(manageproposal.get_DeliveryFee_Symbol_Displayed_On_ItemsAndProducts_Tab(), "$", "Test Step - 16 - Delivery fee symbol is not displayed properly");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Delivery_Fee_Displayed(), "11.99", "Test Step - 16 - ConfiguredDelivery fee is not displayed on row 1 product table grid");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Actual_GrandTotal(), manageproposal.get_Dollar_Based_Calculated_Expected_GrandTotal(), "Test Step - 16 - Grand total is not displayed properly on Event Proposal - Item & Products tab");

            // Test Step - 17
            manageproposal.Click_Publish_Pending_Changes();
            softassert.assertTrue(manageproposal.Verify_Proposal_Pending_Changes_AlertIsDisplayed(), "Test Step - 17 - Publish pending changes alert popup is not displayed");

            // Test Step - 18
            delayWithGivenTime(2000);
            manageproposal.Click_PublishBtn_On_AlertPopup();
            proposal = new ProposalsPage();
            delayWithGivenTime(3000);

            // Test Step - 19
            delayWithGivenTime(2000);
            proposal.Enter_ProposalId_In_Proposal_GlobalSearchBox(proposalId);
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_Selected_Proposal_StatusType(), "Accepted", "Test Step - 19 - Selected proposal status type is not displayed");
            delayWithGivenTime(3000);
            dashboardorder = new DashboardOrderPage();
            delayWithGivenTime(2000);

            delayWithGivenTime(3000);
            invoiceNumber = proposal.get_Invoice_Number_on_Created_Proposal();
            proposal.Click_on_Invoice_Number_on_Created_Proposal();

            delayWithGivenTime(3000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 18 - In orders summary page proposal invoice number is not displayed for placed order");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoiceNumber), "Event proposal", "Test Step - 18 - Order type is not displayed as Wedding proposal");
            softassert.assertEquals(dashboardorder.validate_DeliveryType_On_AllOrdersPage(invoiceNumber), "Delivery", "Test Step - 18 - Delivery type is not displayed for placed order");
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoiceNumber), prop.getProperty("payment_type_invoice"), "Test Step - 19 - Mode of payment as " + prop.getProperty("payment_type_invoice") + " is not displayed");


        } catch (Exception e) {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.assertAll();
        }
    }

    @Epic("Proposal Module - Event")
    @Test(priority = 2, enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T1664_HanaPOS_Proposal_Proposal_settings_Event_Verify_the_Delivery_Fee_In_Percentage_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 4
            delayWithGivenTime(1000);
            proposal = new ProposalsPage();
            proposal.ClickOnProposalsMenu();
            delayWithGivenTime(1000);
            proposal.click_Proposal_Settings_Icon();

            // Test Step - 5
            delayWithGivenTime(3000);
            proposal_settings = new Proposal_Settings_Page();
            softassert.assertTrue(proposal_settings.is_Proposal_Settings_Page_Displayed(), "Proposal settings page is not displayed");
            softassert.assertTrue(proposal_settings.is_Event_IncludeDeliveryFeeinTaxCalculation_Displayed(), "Event proposal settings Include delivery fee in tax calculation label is not displayed");
            softassert.assertTrue(proposal_settings.is_Weddding_IncludeDeliveryFeeinTaxCalculation_Displayed(), "Wedding proposal settings Include delivery fee in tax calculation label is not displayed");

            proposal_settings.select_Event_Setup_Fee_Type("%");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Event_Setup_Fee_Type(), "%", "Event proposal settings setup fee type label is not displayed");

            proposal_settings.select_Event_Delivery_Fee_Type("%");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Event_Delivery_Fee_Type(), "%", "Event proposal settings delivery fee type label is not displayed");

            proposal_settings.enter_Event_Setup_Fee("10");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Event_Setup_Fee(), "10", "Event proposal settings setup fee label is not displayed");

            proposal_settings.enter_Event_Delivery_Fee("10.00");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Event_Delivery_Fee(), "10.00", "Event proposal settings delivery fee label is not displayed");

            proposal_settings.click_EventProposalSettings_save_settings_button();

            // Test Step - 5
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 21 - Create a Proposal popup is not displayed");

            // Test Step - 7
            proposal.Click_event_tab_on_Createproposal_popup();
            delayWithGivenTime(1000);
            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            softassert.assertEquals(proposal.Verify_CustomerNameIsDisplayed_On_SearchTextBox(), prop.getProperty("custfullname"), "Test Step - 6: Searched customer full name is not displayed on create proposal popup");
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_createproposalpopup_firstname_field(), prop.getProperty("cust_firstName"), "Test Step - 6: First name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_lasttname_field(), prop.getProperty("cust_lastName"), "Test Step - 6: Last name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_companyname_field(), prop.getProperty("cust_companyName"), "Test Step - 6: COmpany Name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_email_field(), prop.getProperty("cust_email"), "Test Step - 6: Email Id of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_city_field(), prop.getProperty("cust_city"), "Test Step - 6: City of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_state_field(), prop.getProperty("cust_state"), "Test Step - 6: State of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_address_field(), prop.getProperty("cust_address1"), "Test Step - 6: Address of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_zipcode_field(), prop.getProperty("cust_zipcode"), "Test Step - 6: Zipcode of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_phonenumber_field(), prop.getProperty("cust_phoneNumber"), "Test Step - 6: Phone number of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_altphonenumber_field(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 6: Alternative Phone number of customer is not displayed in create proposal popup");
            delayWithGivenTime(2000);

            delayWithGivenTime(2000);
            proposal.Click_AddProposal_On_CreateProposal_Popup();

            // Test Step - 8
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            proposalId = manageproposal.get_ProposalId();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 8 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 9
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_FirstName(), prop.getProperty("cust_firstName"), "Test Step - 9 - Customer first name is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_LastName(), prop.getProperty("cust_lastName"), "Test Step - 9 - Customer last name is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_PhoneNumber(), "9566550756", "Test Step - 9 - Customer phonenumber is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Email(), prop.getProperty("cust_email"), "Test Step - 9 - Customer Email Id is not autopopulated in customer details section ");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_City(), prop.getProperty("cust_city"), "Test Step - 9 - Customer City is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_State(), prop.getProperty("cust_state"), "Test Step - 9 - Customer State is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Address(), prop.getProperty("cust_address1"), "Test Step - 9 - Customer Address is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Zip(), prop.getProperty("cust_zipcode"), "Test Step - 9 - Customer Zipcode is not autopopulated in customer details section ");
            delayWithGivenTime(2000);

            // Test Step - 10
            manageproposal.Enter_EventCustSection_FirstName("O'Brien");
            manageproposal.Enter_Event_CustSection_LastName("John");
            manageproposal.Enter_Event_CustSection_PhoneNumber("925-456-2305");
            manageproposal.Enter_Event_CustSection_Email("hanaposqateam@gmail.com");
            manageproposal.Enter_Event_CustSection_City("El Cajon");
            manageproposal.Enter_Event_CustSection_State("CA");
            manageproposal.Enter_Event_CustSection_Address("Blossom Valley");
            manageproposal.Enter_Event_CustSection_Zip("92021");

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_FirstName(), "O'Brien", "Test Step - 10 - Entered customer first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_LastName(), "John", "Test Step - 10 - Entered customer last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_PhoneNumber(), "925-456-2305", "Test Step - 10 - Entered customer phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Email(), "hanaposqateam@gmail.com", "Test Step - 10 - Entered customer email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_City(), "El Cajon", "Test Step - 10 - Entered customer city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_State(), "CA", "Test Step - 10 - Entered customer state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Address(), "Blossom Valley", "Test Step - 10 - Entered customer address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Zip(), "92021", "Test Step - 10 - Entered customer zip code is not displayed");

            // Test Step - 11
            manageproposal.enter_AltContact_FirstName(prop.getProperty("AltContact_FirstName_1"));
            manageproposal.enter_AltContact_LastName(prop.getProperty("AltContact_LastName_1"));
            manageproposal.enter_AltContact_PhoneNumber(prop.getProperty("AltContact_PhoneNumber_1"));
            manageproposal.enter_AltContact_EmailID(prop.getProperty("AltContact_EmailID_1"));
            manageproposal.enter_AltContact_Address(prop.getProperty("AltContact_Address_1"));
            manageproposal.enter_AltContact_City(prop.getProperty("AltContact_City_1"));
            manageproposal.enter_AltContact_State(prop.getProperty("AltContact_State_1"));
            manageproposal.enter_AltContact_Zipcode(prop.getProperty("AltContact_Zipcode_1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_AltContact_FirstName(), prop.getProperty("AltContact_FirstName_1"), "Test Step - 11 - Alternate Entered first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_LastName(), prop.getProperty("AltContact_LastName_1"), "Test Step - 11 - Alternate Entered last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_PhoneNumber(), prop.getProperty("AltContact_PhoneNumber_1"), "Test Step - 11 - Alternate Entered phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_EmailID(), prop.getProperty("AltContact_EmailID_1"), "Test Step - 11 - Alternate Entered email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_City(), prop.getProperty("AltContact_City_1"), "Test Step - 11 - Alternate Entered city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_State(), prop.getProperty("AltContact_State_1"), "Test Step - 11 - Alternate Entered state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_Address(), prop.getProperty("AltContact_Address_1"), "Test Step - 11 - Alternate Entered address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_Zipcode(), prop.getProperty("AltContact_Zipcode_1"), "Test Step - 11 - Alternate Entered zip code is not displayed");

            // Test Step - 12
            manageproposal.Click_On_eventdetailstab();
            manageproposal.enter_Event_Name(prop.getProperty("Event_Name"));
            manageproposal.enter_Event_Location(prop.getProperty("Event_Location"));
            manageproposal.enter_Event_Date();
            manageproposal.enter_Event_Time();
            manageproposal.enter_Event_Address(prop.getProperty("Event_Address"));
            manageproposal.enter_Event_ZipCode(prop.getProperty("Event_Zipcode"));
            //  manageproposal.enter_Event_City(prop.getProperty("Event_City"));
            //  manageproposal.enter_Event_State(prop.getProperty("Event_State"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Event_Name(), prop.getProperty("Event_Name"), "Test Step - 12 - Entered Event Name is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Location(), prop.getProperty("Event_Location"), "Test Step - 12 - Entered Event Location is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Date(), Next_20Days_Date(), "Test Step - 12 - Entered Event Date is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Time(), prop.getProperty("Event_Time"), "Test Step - 12 - Entered Event Time is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Address(), prop.getProperty("Event_Address"), "Test Step - 12 - Entered Event Address is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_ZipCode(), prop.getProperty("Event_Zipcode"), "Test Step - 12 - Entered Event Zipcode is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_City(), prop.getProperty("Event_City"), "Test Step - 12 - Entered Event City is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_State(), prop.getProperty("Event_State"), "Test Step - 12 - Entered Event State is not displayed correctly");

            // Test Step - 13
            manageproposal.Enter_FirstName_Event_Coordinator(prop.getProperty("Event_Coordinator_Fname"));
            manageproposal.Enter_LastName_Event_Coordinator(prop.getProperty("Event_Coordinator_Lname"));
            manageproposal.Enter_Phonenumber_Event_Coordinator(prop.getProperty("Event_Coordinator_Phonenumber"));
            manageproposal.Enter_EmailId_Event_Coordinator(prop.getProperty("Event_Coordinator_EmailId"));
            manageproposal.Enter_Address_Event_Coordinator(prop.getProperty("Event_Coordinator_Address"));
            manageproposal.Enter_Zipcode_Event_Coordinator(prop.getProperty("Event_Coordinator_Zipcode"));
            // manageproposal.Enter_City_Event_Coordinator(prop.getProperty("Event_Coordinator_City"));
            // manageproposal.Enter_State_Event_Coordinator(prop.getProperty("Event_Coordinator_State"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_entered_firstname_event_coordinator(), prop.getProperty("Event_Coordinator_Fname"), "Test Step - 13: Entered event coordinator first name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_lastname_event_coordinator(), prop.getProperty("Event_Coordinator_Lname"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_phonenumber_event_coordinator(), prop.getProperty("Event_Coordinator_Phonenumber"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_emailid_event_coordinator(), prop.getProperty("Event_Coordinator_EmailId"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_address_event_coordinator(), prop.getProperty("Event_Coordinator_Address"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_zipcode_event_coordinator(), prop.getProperty("Event_Coordinator_Zipcode"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_city_event_coordinator(), prop.getProperty("Event_Coordinator_City"), "Test Step - 13: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_state_event_coordinator(), prop.getProperty("Event_Coordinator_State"), "Test Step - 13: Entered event coordinator last name is not displayed");

            // Test Step - 14
            delayWithGivenTime(2000);
            manageproposal.Click_DocumentsAndImages_Tab();
            delayWithGivenTime(2000);
            manageproposal.UploadFiles("James heman proposal report.pdf");

            delayWithGivenTime(2000);
            softassert.assertTrue(manageproposal.verify_uploaded_firstdocument_displayed_on_grid(), "Test Step - 10: Uploaded first document is not displayed on documents and images tab");

            // Test Step - 15
            manageproposal.Click_ItemsAndProducts_Tab();

            // Test Step - 16
            delayWithGivenTime(2000);
            manageproposal.select_TaxType("QA Tax");
            delayWithGivenTime(1000);
            softassert.assertEquals(manageproposal.get_TaxType_Displayed(), "QA Tax", "Test Step - 16 - Tax type is not displayed on product table grid");

            delayWithGivenTime(2000);
            manageproposal.Select_CategoryDropdown_On_ItemsAndProducts("Mother's Day");
            manageproposal.Enter_ItemName_On_ItemsAndProducts("rrd", "rrd Small");
            manageproposal.Click_AddBtn_On_ItemsAndProducts();
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Row1_ItemCategory(), "Mother's Day", "Test Step - 16 - Selected category is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_itemCode(), "rrd", "Test Step - 16 - Item code is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_itemDescription(), "Red Rose Small", "Test Step - 16 - Item description is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_itemQuantity(), "1", "Test Step - 16 - Item quantity is not displayed on row 1 product table grid");
            softassert.assertEquals(manageproposal.get_row1_item_TotalPrice(), "$199.00", "Test Step - 16 - Item total price is not displayed on row 1 product table grid");

            softassert.assertEquals(manageproposal.get_DeliveryFee_Symbol_Displayed_On_ItemsAndProducts_Tab(), "%", "Test Step - 16 - Delivery fee symbol is not displayed properly");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Final_Delivery_Fee_Displayed_On_ItemsAndProducts_Tab(), manageproposal.get_Delivery_Fee_On_Percentage_Expected_Calculation(), "Test Step - 16 - Delivery fee is not displayed on product table grid");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Delivery_Fee_Displayed(), "10.00", "Test Step - 16 - Configured Delivery fee is not displayed properly on row 1 product table grid");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Actual_GrandTotal(), manageproposal.get_Percentage_Based_Calculated_Expected_GrandTotal(), "Test Step - 16 - Grand total is not displayed properly on product table grid");

            // Test Step - 17
            manageproposal.Click_Publish_Pending_Changes();
            softassert.assertTrue(manageproposal.Verify_Proposal_Pending_Changes_AlertIsDisplayed(), "Test Step - 17 - Publish pending changes alert popup is not displayed");

            // Test Step - 18
            delayWithGivenTime(2000);
            manageproposal.Click_PublishBtn_On_AlertPopup();
            proposal = new ProposalsPage();
            delayWithGivenTime(3000);
            softassert.assertEquals(proposal.get_ProposalsViewPageTitle(), "Hana | View Proposal", "Test Step - 18 - View proposal page is not displayed");

            // Test Step - 19
            delayWithGivenTime(2000);
            proposal.Enter_ProposalId_In_Proposal_GlobalSearchBox(proposalId);
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_Selected_Proposal_StatusType(), "Accepted", "Test Step - 19 - Selected proposal status type is not displayed");
            delayWithGivenTime(3000);
            dashboardorder = new DashboardOrderPage();
            delayWithGivenTime(2000);

            delayWithGivenTime(3000);
            invoiceNumber = proposal.get_Invoice_Number_on_Created_Proposal();
            proposal.Click_on_Invoice_Number_on_Created_Proposal();

            delayWithGivenTime(3000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 18 - In orders summary page proposal invoice number is not displayed for placed order");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoiceNumber), "Event proposal", "Test Step - 18 - Order type is not displayed as Wedding proposal");
            softassert.assertEquals(dashboardorder.validate_DeliveryType_On_AllOrdersPage(invoiceNumber), "Delivery", "Test Step - 18 - Delivery type is not displayed for placed order");
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoiceNumber), prop.getProperty("payment_type_invoice"), "Test Step - 19 - Mode of payment as " + prop.getProperty("payment_type_invoice") + " is not displayed");


        } catch (Exception e) {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.assertAll();
        }
    }

}
