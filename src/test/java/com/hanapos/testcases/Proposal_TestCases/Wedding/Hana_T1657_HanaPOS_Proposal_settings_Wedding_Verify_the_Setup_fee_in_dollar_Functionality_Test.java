package com.hanapos.testcases.Proposal_TestCases.Wedding;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T1657_HanaPOS_Proposal_settings_Wedding_Verify_the_Setup_fee_in_dollar_Functionality_Test extends TestBaseClass {
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

    @Epic("Proposal Module - Wedding")
    @Test(priority = 1, enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T1657_HanaPOS_Proposal_settings_Wedding_Verify_the_Setup_fee_in_dollar_Functionality() {
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            lp.ClickLoginButton();

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));

            // Test Step - 3
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

            proposal_settings.select_Wedding_Setup_Fee_Type("$");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Wedding_Setup_Fee_Type(), "$", "Wedding proposal settings setup fee type label is not displayed");

            proposal_settings.select_Wedding_Delivery_Fee_Type("$");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Wedding_Delivery_Fee_Type(), "$", "Wedding proposal settings setup fee type label is not displayed");

            proposal_settings.enter_Wedding_Setup_Fee("74.99");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Wedding_Setup_Fee(), "74.99", "Wedding proposal settings setup fee label is not displayed");

            proposal_settings.enter_Wedding_Delivery_Fee("19.99");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Wedding_Delivery_Fee(), "19.99", "Wedding proposal settings delivery fee label is not displayed");

            proposal_settings.click_WeddingProposalSettings_save_settings_button();

            // Test Step - 6
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 21 - Create a Proposal popup is not displayed");

            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            softassert.assertEquals(proposal.Verify_CustomerNameIsDisplayed_On_SearchTextBox(), prop.getProperty("custfullname"), "Test Step - 7: Searched customer full name " + prop.getProperty("custfullname") + " is not displayed on create proposal popup");
            delayWithGivenTime(2000);
            proposal.Click_AddProposal_On_CreateProposal_Popup();

            // Test Step - 8
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 8 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 9
            proposalId = manageproposal.get_ProposalId();
            manageproposal.Click_CoupleDetailsTab();
            manageproposal.Select_Bride_Groom_DropDown1("Groom");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Bride_FirstName(), "Abish", "Test Step - 9 - Entered bride first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_LastName(), "David", "Test Step - 9 - Entered bride last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_PhoneNumber(), "9566550756", "Test Step - 9 -Entered bride phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Email(), "hanaposqateam@gmail.com", "Test Step - 9 -Entered bride email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_City(), "San Diego", "Test Step - 9 -Entered bride city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_State(), "CA", "Test Step - 9 -Entered bride state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Address(), "3402 Park Blvd", "Test Step - 9 -Entered bride address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Zip(), "92103", "Test Step - 9 -Entered bride zip code is not displayed");

            // Test Step - 10
            delayWithGivenTime(2000);
            manageproposal.Select_Bride_Groom_DropDown2("Bride");
            delayWithGivenTime(2000);
            manageproposal.Enter_Groom_FirstName("O'Brien");
            manageproposal.Enter_Groom_LastName("John");
            manageproposal.Enter_Groom_PhoneNumber("925-456-2305");
            manageproposal.Enter_Groom_Email("hanaposqateam@gmail.com");
            manageproposal.Enter_Groom_City("El Cajon");
            manageproposal.Enter_Groom_State("CA");
            manageproposal.Enter_Groom_Address("Blossom Valley");
            manageproposal.Enter_Groom_Zip("92021");

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Groom_FirstName(), "O'Brien", "Test Step - 10 -Entered groom first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_LastName(), "John", "Test Step - 10 -Entered groom last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_PhoneNumber(), "925-456-2305", "Test Step - 10 -Entered groom phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Email(), "hanaposqateam@gmail.com", "Test Step - 10 -Entered groom email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_City(), "El Cajon", "Test Step - 10 -Entered groom city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_State(), "CA", "Test Step - 10 -Entered groom state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Address(), "Blossom Valley", "Test Step - 10 -Entered groom address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Zip(), "92021", "Test Step - 10 -Entered groom zip code is not displayed");

            // Test Step - 11
            delayWithGivenTime(2000);
            manageproposal.Click_Weddings_eventDetails_Tab();
            manageproposal.Enter_CeremonyLocation("The Ritz-Carlton, Lake Tahoe");
            manageproposal.Enter_CeremonyDate_WithInNext30Days();
            manageproposal.Enter_CeremonyTime();
            manageproposal.Enter_Ceremony_Address("13031 Ritz Carlton Highlands Court");
            manageproposal.Enter_Ceremony_City("Truckee");
            manageproposal.Enter_Ceremony_State("CA");
            manageproposal.Enter_Ceremony_Zip("96161");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_CeremonyLocation(), "The Ritz-Carlton, Lake Tahoe", "Test Step - 24 -Entered ceremony location is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyDate(), "Wed, 01 Oct 2022","Test Step - 24 -Entered ceremony date is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyTime(), "11:00 AM","Test Step - 24 -Entered ceremony time is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_Address(), "13031 Ritz Carlton Highlands Court", "Test Step - 24 -Entered ceremony address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_City(), "Truckee", "Test Step - 24 -Entered ceremony city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_State(), "CA", "Test Step - 24 -Entered ceremony state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_Zip(), "96161", "Test Step - 24 -Entered ceremony zip code is not displayed");

            // Test Step - 12
            delayWithGivenTime(2000);
            manageproposal.Enter_ReceptionLocation("Rutherford Wedding Venue");
            manageproposal.Enter_ReceptionDate_WithInNext30Days();
            manageproposal.Enter_ReceptionTime();
            manageproposal.Enter_Reception_Address("180 Rutherford Hill Rd, Rutherford");
            manageproposal.Enter_Reception_City("Rutherford");
            manageproposal.Enter_Reception_State("CA");
            manageproposal.Enter_Reception_Zip("94573");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_ReceptionLocation(), "Rutherford Wedding Venue", "Test Step - 24 -Entered reception location is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyDate(), "Wed, 01 Oct 2022","Test Step - 24 -Entered ceremony date is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyTime(), "11:00 AM","Test Step - 24 -Entered ceremony time is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Reception_Address(), "180 Rutherford Hill Rd, Rutherford", "Test Step - 24 -Entered reception address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Reception_City(), "Rutherford", "Test Step - 24 -Entered reception city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_reception_State(), "CA", "Test Step - 24 -Entered reception state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Reception_Zip(), "94573", "Test Step - 24 -Entered reception zip code is not displayed");

            // Test Step - 13
            delayWithGivenTime(2000);
            manageproposal.Enter_WeddingCoordinator_FirstName("Daniel");
            manageproposal.Enter_WeddingCoordinator_LastName("Wilton");
            manageproposal.Enter_WeddingCoordinator_PhoneNumber("604-685-5761");
            manageproposal.Enter_WeddingCoordinator_Email("hanaposqateam@gmail.com");
            manageproposal.Enter_WeddingCoordinator_City("North Vancouver");
            manageproposal.Enter_WeddingCoordinator_State("BC");
            manageproposal.Enter_WeddingCoordinator_Address("1433 Pemberton Ave");
            manageproposal.Enter_WeddingCoordinator_Zip("V7P 2R8");

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_FirstName(), "Daniel", "Test Step - 13 -Entered Wedding Coordinator first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_LastName(), "Wilton", "Test Step - 13 -Entered Wedding Coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_PhoneNumber(), "604-685-5761", "Test Step - 13 -Entered Wedding Coordinator phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_Email(), "hanaposqateam@gmail.com", "Test Step - 13 -Entered Wedding Coordinator email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_City(), "North Vancouver", "Test Step - 13 -Entered Wedding Coordinator city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_State(), "BC", "Test Step - 13 -Entered Wedding Coordinator state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_Address(), "1433 Pemberton Ave", "Test Step - 13 -Entered Wedding Coordinator address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_Zip(), "V7P 2R8", "Test Step - 13 -Entered Wedding Coordinator zip code is not displayed");

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

            softassert.assertEquals(manageproposal.get_Setup_Fee_Displayed_On_ItemsAndProducts_Tab(), "74.99", "Test Step - 16 - Setup fee is not displayed on row 1 product table grid");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Actual_GrandTotal(), manageproposal.get_Setupfee_Dollar_Deliveryfee_Percentage_Based_Calculated_Expected_GrandTotal(), "Test Step - 16 - Grand total is not displayed properly on product table grid");

            // Test Step - 17
            delayWithGivenTime(2000);
            manageproposal.Click_Publish_Pending_Changes();
            softassert.assertTrue(manageproposal.Verify_Proposal_Pending_Changes_AlertIsDisplayed(), "Test Step - 17 - Publish pending changes alert popup is not displayed");

            // Test Step - 18
            delayWithGivenTime(2000);
            manageproposal.Click_PublishBtn_On_AlertPopup();
            proposal = new ProposalsPage();
            delayWithGivenTime(3000);

            // Test Step - 19
            dashboardorder = new DashboardOrderPage();
            delayWithGivenTime(2000);
            proposal.Enter_ProposalId_In_Proposal_GlobalSearchBox(proposalId);
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_Selected_Proposal_StatusType(), "Accepted", "Test Step - 19 - Selected proposal status type is not displayed");

            delayWithGivenTime(3000);
            invoiceNumber = proposal.get_Invoice_Number_on_Created_Proposal();
            proposal.Click_on_Invoice_Number_on_Created_Proposal();

            delayWithGivenTime(3000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 18 - In orders summary page proposal invoice number is not displayed for placed order");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoiceNumber), "Wedding proposal", "Test Step - 18 - Order type is not displayed as Wedding proposal");
            softassert.assertEquals(dashboardorder.validate_DeliveryType_On_AllOrdersPage(invoiceNumber), "Delivery", "Test Step - 18 - Delivery type is not displayed for placed order");
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoiceNumber), prop.getProperty("payment_type_invoice"), "Test Step - 19 - Mode of payment as " + prop.getProperty("payment_type_invoice") + " is not displayed");

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            softassert.assertAll();
        }

    }


    @Epic("Proposal Module - Wedding")
    @Test(priority = 2, enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T1659_HanaPOS_Proposal_settings_Wedding_Verify_the_Setup_fee_in_percentage_Functionality() {
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            lp.ClickLoginButton();

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));

            // Test Step - 3
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

            proposal_settings.select_Wedding_Setup_Fee_Type("%");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Wedding_Setup_Fee_Type(), "%", "Wedding proposal settings setup fee type label is not displayed");

            proposal_settings.select_Wedding_Delivery_Fee_Type("$");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Wedding_Delivery_Fee_Type(), "$", "Wedding proposal settings delivery fee type label is not displayed");

            proposal_settings.enter_Wedding_Setup_Fee("10");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Wedding_Setup_Fee(), "10", "Wedding proposal settings setup fee label is not displayed");

            proposal_settings.enter_Wedding_Delivery_Fee("19.99");
            delayWithGivenTime(1000);
            softassert.assertEquals(proposal_settings.get_Wedding_Delivery_Fee(), "19.99", "Wedding proposal settings delivery fee label is not displayed");

            proposal_settings.click_WeddingProposalSettings_save_settings_button();


            // Test Step - 6
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 21 - Create a Proposal popup is not displayed");

            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            softassert.assertEquals(proposal.Verify_CustomerNameIsDisplayed_On_SearchTextBox(), prop.getProperty("custfullname"), "Test Step - 7: Searched customer full name " + prop.getProperty("custfullname") + " is not displayed on create proposal popup");
            delayWithGivenTime(2000);
            proposal.Click_AddProposal_On_CreateProposal_Popup();

            // Test Step - 8
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 8 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 9
            proposalId = manageproposal.get_ProposalId();
            manageproposal.Click_CoupleDetailsTab();
            manageproposal.Select_Bride_Groom_DropDown1("Groom");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Bride_FirstName(), "Abish", "Test Step - 9 - Entered bride first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_LastName(), "David", "Test Step - 9 - Entered bride last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_PhoneNumber(), "9566550756", "Test Step - 9 -Entered bride phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Email(), "hanaposqateam@gmail.com", "Test Step - 9 -Entered bride email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_City(), "San Diego", "Test Step - 9 -Entered bride city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_State(), "CA", "Test Step - 9 -Entered bride state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Address(), "3402 Park Blvd", "Test Step - 9 -Entered bride address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Zip(), "92103", "Test Step - 9 -Entered bride zip code is not displayed");

            // Test Step - 10
            delayWithGivenTime(2000);
            manageproposal.Select_Bride_Groom_DropDown2("Bride");
            delayWithGivenTime(2000);
            manageproposal.Enter_Groom_FirstName("O'Brien");
            manageproposal.Enter_Groom_LastName("John");
            manageproposal.Enter_Groom_PhoneNumber("925-456-2305");
            manageproposal.Enter_Groom_Email("hanaposqateam@gmail.com");
            manageproposal.Enter_Groom_City("El Cajon");
            manageproposal.Enter_Groom_State("CA");
            manageproposal.Enter_Groom_Address("Blossom Valley");
            manageproposal.Enter_Groom_Zip("92021");

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Groom_FirstName(), "O'Brien", "Test Step - 10 -Entered groom first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_LastName(), "John", "Test Step - 10 -Entered groom last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_PhoneNumber(), "925-456-2305", "Test Step - 10 -Entered groom phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Email(), "hanaposqateam@gmail.com", "Test Step - 10 -Entered groom email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_City(), "El Cajon", "Test Step - 10 -Entered groom city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_State(), "CA", "Test Step - 10 -Entered groom state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Address(), "Blossom Valley", "Test Step - 10 -Entered groom address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Zip(), "92021", "Test Step - 10 -Entered groom zip code is not displayed");

            // Test Step - 11
            delayWithGivenTime(2000);
            manageproposal.Click_Weddings_eventDetails_Tab();
            manageproposal.Enter_CeremonyLocation("The Ritz-Carlton, Lake Tahoe");
            manageproposal.Enter_CeremonyDate_WithInNext30Days();
            manageproposal.Enter_CeremonyTime();
            manageproposal.Enter_Ceremony_Address("13031 Ritz Carlton Highlands Court");
            manageproposal.Enter_Ceremony_City("Truckee");
            manageproposal.Enter_Ceremony_State("CA");
            manageproposal.Enter_Ceremony_Zip("96161");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_CeremonyLocation(), "The Ritz-Carlton, Lake Tahoe", "Test Step - 24 -Entered ceremony location is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyDate(), "Wed, 01 Oct 2022","Test Step - 24 -Entered ceremony date is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyTime(), "11:00 AM","Test Step - 24 -Entered ceremony time is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_Address(), "13031 Ritz Carlton Highlands Court", "Test Step - 24 -Entered ceremony address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_City(), "Truckee", "Test Step - 24 -Entered ceremony city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_State(), "CA", "Test Step - 24 -Entered ceremony state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Ceremony_Zip(), "96161", "Test Step - 24 -Entered ceremony zip code is not displayed");

            // Test Step - 12
            delayWithGivenTime(2000);
            manageproposal.Enter_ReceptionLocation("Rutherford Wedding Venue");
            manageproposal.Enter_ReceptionDate_WithInNext30Days();
            manageproposal.Enter_ReceptionTime();
            manageproposal.Enter_Reception_Address("180 Rutherford Hill Rd, Rutherford");
            manageproposal.Enter_Reception_City("Rutherford");
            manageproposal.Enter_Reception_State("CA");
            manageproposal.Enter_Reception_Zip("94573");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_ReceptionLocation(), "Rutherford Wedding Venue", "Test Step - 24 -Entered reception location is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyDate(), "Wed, 01 Oct 2022","Test Step - 24 -Entered ceremony date is not displayed");
            //	softassert.assertEquals(manageproposal.get_Entered_CeremonyTime(), "11:00 AM","Test Step - 24 -Entered ceremony time is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Reception_Address(), "180 Rutherford Hill Rd, Rutherford", "Test Step - 24 -Entered reception address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Reception_City(), "Rutherford", "Test Step - 24 -Entered reception city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_reception_State(), "CA", "Test Step - 24 -Entered reception state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Reception_Zip(), "94573", "Test Step - 24 -Entered reception zip code is not displayed");

            // Test Step - 13
            delayWithGivenTime(2000);
            manageproposal.Enter_WeddingCoordinator_FirstName("Daniel");
            manageproposal.Enter_WeddingCoordinator_LastName("Wilton");
            manageproposal.Enter_WeddingCoordinator_PhoneNumber("604-685-5761");
            manageproposal.Enter_WeddingCoordinator_Email("hanaposqateam@gmail.com");
            manageproposal.Enter_WeddingCoordinator_City("North Vancouver");
            manageproposal.Enter_WeddingCoordinator_State("BC");
            manageproposal.Enter_WeddingCoordinator_Address("1433 Pemberton Ave");
            manageproposal.Enter_WeddingCoordinator_Zip("V7P 2R8");

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_FirstName(), "Daniel", "Test Step - 13 -Entered Wedding Coordinator first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_LastName(), "Wilton", "Test Step - 13 -Entered Wedding Coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_PhoneNumber(), "604-685-5761", "Test Step - 13 -Entered Wedding Coordinator phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_Email(), "hanaposqateam@gmail.com", "Test Step - 13 -Entered Wedding Coordinator email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_City(), "North Vancouver", "Test Step - 13 -Entered Wedding Coordinator city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_State(), "BC", "Test Step - 13 -Entered Wedding Coordinator state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_Address(), "1433 Pemberton Ave", "Test Step - 13 -Entered Wedding Coordinator address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_WeddingCoordinator_Zip(), "V7P 2R8", "Test Step - 13 -Entered Wedding Coordinator zip code is not displayed");

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

            delayWithGivenTime(1000);
            softassert.assertEquals(manageproposal.get_Setup_Fee_Displayed_On_ItemsAndProducts_Tab(), "10.00", "Test Step - 16 - Setup fee is not displayed on row 1 product table grid");

            softassert.assertEquals(manageproposal.get_Final_Setup_Fee_Displayed_On_ItemsAndProducts_Tab(), manageproposal.get_Setup_Fee_On_Percentage_Expected_Calculation(), "Test Step - 16 - Setup fee is not displayed on row 1 product table grid");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Actual_GrandTotal(), manageproposal.get_Setupfee_Percentage_Deliveryfee_Dollar_Based_Calculated_Expected_GrandTotal(), "Test Step - 16 - Grand total is not displayed properly on product table grid");

            // Test Step - 17
            delayWithGivenTime(2000);
            manageproposal.Click_Publish_Pending_Changes();
            softassert.assertTrue(manageproposal.Verify_Proposal_Pending_Changes_AlertIsDisplayed(), "Test Step - 17 - Publish pending changes alert popup is not displayed");

            // Test Step - 18
            delayWithGivenTime(2000);
            manageproposal.Click_PublishBtn_On_AlertPopup();
            proposal = new ProposalsPage();
            delayWithGivenTime(3000);

            // Test Step - 19
            dashboardorder = new DashboardOrderPage();
            delayWithGivenTime(2000);
            proposal.Enter_ProposalId_In_Proposal_GlobalSearchBox(proposalId);
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_Selected_Proposal_StatusType(), "Accepted", "Test Step - 19 - Selected proposal status type is not displayed");

            delayWithGivenTime(3000);
            invoiceNumber = proposal.get_Invoice_Number_on_Created_Proposal();
            proposal.Click_on_Invoice_Number_on_Created_Proposal();

            delayWithGivenTime(3000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 18 - In orders summary page proposal invoice number is not displayed for placed order");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoiceNumber), "Wedding proposal", "Test Step - 18 - Order type is not displayed as Wedding proposal");
            softassert.assertEquals(dashboardorder.validate_DeliveryType_On_AllOrdersPage(invoiceNumber), "Delivery", "Test Step - 18 - Delivery type is not displayed for placed order");
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoiceNumber), prop.getProperty("payment_type_invoice"), "Test Step - 19 - Mode of payment as " + prop.getProperty("payment_type_invoice") + " is not displayed");

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            softassert.assertAll();
        }

    }

}
