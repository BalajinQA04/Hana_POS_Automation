package com.hanapos.testcases.Proposal_TestCases.Event;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.ManageProposalPage;
import com.hanapos.pageObjects.ProposalsPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T689_Add_Proposal_CreateProposalPage_Event_NeworExisting_Customer_AddProposal_GeneralInfo_CustomerDetails_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    String proposalId;
    public static LoggerUtil logger_Util;

    //,dataProvider="fetch_Excel_Data"
    @Epic("Proposal Module - Event")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T689_Add_Proposal_CreateProposalPage_Event_NeworExisting_Customer_AddProposal_GeneralInfo_CustomerDetails_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger.info("**** Starting  Validate_Hana_T689_Add_Proposal_CreateProposalPage_Event_NeworExisting_Customer_AddProposal_GeneralInfo_CustomerDetails_Functionality_Test ****");
        logger.debug("capturing application debug logs....");
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
            proposal = new ProposalsPage();
            proposal.ClickOnProposalsMenu();

            // Test Step - 5
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 21 - Create a Proposal popup is not displayed");

            // Test Step - 6
            proposal.Click_event_tab_on_Createproposal_popup();
            delayWithGivenTime(1000);
            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            proposal.Click_CancelButton_On_createproposal_popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.isCreateProposalPopupClosed(), "Test Step - 6: Create proposal popup is not closed on view proposal page");

            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 7 - Create a Proposal popup is not displayed on view proposal page");

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
            //    proposal.Select_createproposalpopup_selectreport_dropdownfield("Conceptual Design1");
            //    proposal.Select_createproposalpopup_selectpackage_dropdownfield("Gold Event Package");
            //    proposal.Select_createproposalpopup_select_eventorwedding_dropdownfield("Abish");
            delayWithGivenTime(2000);
            // softassert.assertEquals(proposal.get_Select_createproposalpopup_selectreport_dropdownfield(), "Conceptual Design1", "Test Step - 7: Selected report is not displayed in create proposal popup");
            // softassert.assertEquals(proposal.get_createproposalpopup_selectpackage_dropdownfield(), "Gold Event Package", "Test Step - 7: Selected package is not displayed in create proposal popup");
            // softassert.assertEquals(proposal.get_createproposalpopup_select_eventorwedding_dropdownfield(), "Abish", "Test Step - 7: Selected event or wedding consultant is not displayed in create proposal popup");

            delayWithGivenTime(2000);
            proposal.Click_AddProposal_On_CreateProposal_Popup();

            // Test Step - 8
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            proposalId = manageproposal.get_ProposalId();

            // Test Step - 9
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 8 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 10
            manageproposal.Enter_LineOneTitle_Under_CoupleInfo("Event - Social 2025 & Estimation");
            softassert.assertEquals(manageproposal.get_entered_lineonetitle_under_coupleinfo(), "Event - Social 2025 & Estimation", "Test Step - 10 - Under couple info line one title is not displayed");

            // Test Step - 11
            manageproposal.Enter_LineTwoTitle_Under_CoupleInfo("Two Hearts, One Love Story - House Warming 2025");
            softassert.assertEquals(manageproposal.get_entered_linetwotitle_under_coupleinfo(), "Two Hearts, One Love Story - House Warming 2025", "Test Step - 11: Under couple info line two title is not displayed");

            // Test Step - 12
            softassert.assertTrue(manageproposal.Validate_theme_dropdown_options_IsDisplayed(), "Test Step - 12: Select theme dropdown options are not displayed");

            // Test Step - 13
            manageproposal.Select_Theme_under_coupleInfo("Portrait");
            delayWithGivenTime(1000);
            manageproposal.Accept_Selected_Theme_On_ConfirmationPopup();
            delayWithGivenTime(1000);
            //  softassert.assertEquals(proposal.verifySuccessToastMessageText(), "Theme removed successfully", "Test Step - 13: Success toaster message is not displayed while changed the theme");
            softassert.assertEquals(manageproposal.get_selected_theme_displayed(), "Portrait", "Test Step - 13: Selected theme is not displayed");

            // Test Step - 14
            manageproposal.clickGeneralInfoTab();
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_FirstName(), prop.getProperty("cust_firstName"), "Test Step - 14 - Customer first name is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_LastName(), prop.getProperty("cust_lastName"), "Test Step - 14 - Customer last name is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_PhoneNumber(), "9566550756", "Test Step - 14 - Customer phonenumber is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Email(), prop.getProperty("cust_email"), "Test Step - 14 - Customer Email Id is not autopopulated in customer details section ");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_City(), prop.getProperty("cust_city"), "Test Step - 14 - Customer City is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_State(), prop.getProperty("cust_state"), "Test Step - 14 - Customer State is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Address(), prop.getProperty("cust_address1"), "Test Step - 14 - Customer Address is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Zip(), prop.getProperty("cust_zipcode"), "Test Step - 14 - Customer Zipcode is not autopopulated in customer details section ");
            delayWithGivenTime(2000);

            // Test Step - 15
            manageproposal.Enter_EventCustSection_FirstName("O'Brien");
            manageproposal.Enter_Event_CustSection_LastName("John");
            manageproposal.Enter_Event_CustSection_PhoneNumber("925-456-2305");
            manageproposal.Enter_Event_CustSection_Email("hanaposqateam@gmail.com");
            manageproposal.Enter_Event_CustSection_City("El Cajon");
            manageproposal.Enter_Event_CustSection_State("CA");
            manageproposal.Enter_Event_CustSection_Address("Blossom Valley");
            manageproposal.Enter_Event_CustSection_Zip("92021");

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_FirstName(), "O'Brien", "Test Step - 15 - Entered customer first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_LastName(), "John", "Test Step - 15 - Entered customer last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_PhoneNumber(), "925-456-2305", "Test Step - 15 - Entered customer phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Email(), "hanaposqateam@gmail.com", "Test Step - 15 - Entered customer email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_City(), "El Cajon", "Test Step - 15 - Entered customer city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_State(), "CA", "Test Step - 15 - Entered customer state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Address(), "Blossom Valley", "Test Step - 15 - Entered customer address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Zip(), "92021", "Test Step - 15 - Entered customer zip code is not displayed");

            // Test Step - 16
            manageproposal.enter_AltContact_FirstName(prop.getProperty("AltContact_FirstName_1"));
            manageproposal.enter_AltContact_LastName(prop.getProperty("AltContact_LastName_1"));
            manageproposal.enter_AltContact_PhoneNumber(prop.getProperty("AltContact_PhoneNumber_1"));
            manageproposal.enter_AltContact_EmailID(prop.getProperty("AltContact_EmailID_1"));
            manageproposal.enter_AltContact_Address(prop.getProperty("AltContact_Address_1"));
            manageproposal.enter_AltContact_City(prop.getProperty("AltContact_City_1"));
            manageproposal.enter_AltContact_State(prop.getProperty("AltContact_State_1"));
            manageproposal.enter_AltContact_Zipcode(prop.getProperty("AltContact_Zipcode_1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_AltContact_FirstName(), prop.getProperty("AltContact_FirstName_1"), "Test Step - 16 - Alternate Entered first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_LastName(), prop.getProperty("AltContact_LastName_1"), "Test Step - 16 - Alternate Entered last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_PhoneNumber(), prop.getProperty("AltContact_PhoneNumber_1"), "Test Step - 16 - Alternate Entered phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_EmailID(), prop.getProperty("AltContact_EmailID_1"), "Test Step - 16 - Alternate Entered email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_City(), prop.getProperty("AltContact_City_1"), "Test Step - 16 - Alternate Entered city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_State(), prop.getProperty("AltContact_State_1"), "Test Step - 16 - Alternate Entered state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_Address(), prop.getProperty("AltContact_Address_1"), "Test Step - 16 - Alternate Entered address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_AltContact_Zipcode(), prop.getProperty("AltContact_Zipcode_1"), "Test Step - 16 - Alternate Entered zip code is not displayed");

            // Test Step - 17
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
            softassert.assertEquals(manageproposal.get_Entered_Event_Name(), prop.getProperty("Event_Name"), "Test Step - 17 - Entered Event Name is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Location(), prop.getProperty("Event_Location"), "Test Step - 17 - Entered Event Location is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Date(), Next_20Days_Date(), "Test Step - 17 - Entered Event Date is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Time(), prop.getProperty("Event_Time"), "Test Step - 17 - Entered Event Time is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_Address(), prop.getProperty("Event_Address"), "Test Step - 17 - Entered Event Address is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_ZipCode(), prop.getProperty("Event_Zipcode"), "Test Step - 17 - Entered Event Zipcode is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_City(), prop.getProperty("Event_City"), "Test Step - 17 - Entered Event City is not displayed correctly");
            softassert.assertEquals(manageproposal.get_Entered_Event_State(), prop.getProperty("Event_State"), "Test Step - 17 - Entered Event State is not displayed correctly");

            // Test Step - 18
            manageproposal.Enter_FirstName_Event_Coordinator(prop.getProperty("Event_Coordinator_Fname"));
            manageproposal.Enter_LastName_Event_Coordinator(prop.getProperty("Event_Coordinator_Lname"));
            manageproposal.Enter_Phonenumber_Event_Coordinator(prop.getProperty("Event_Coordinator_Phonenumber"));
            manageproposal.Enter_EmailId_Event_Coordinator(prop.getProperty("Event_Coordinator_EmailId"));
            manageproposal.Enter_Address_Event_Coordinator(prop.getProperty("Event_Coordinator_Address"));
            manageproposal.Enter_Zipcode_Event_Coordinator(prop.getProperty("Event_Coordinator_Zipcode"));
            // manageproposal.Enter_City_Event_Coordinator(prop.getProperty("Event_Coordinator_City"));
            // manageproposal.Enter_State_Event_Coordinator(prop.getProperty("Event_Coordinator_State"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_entered_firstname_event_coordinator(), prop.getProperty("Event_Coordinator_Fname"), "Test Step - 18: Entered event coordinator first name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_lastname_event_coordinator(), prop.getProperty("Event_Coordinator_Lname"), "Test Step - 18: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_phonenumber_event_coordinator(), prop.getProperty("Event_Coordinator_Phonenumber"), "Test Step - 18: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_emailid_event_coordinator(), prop.getProperty("Event_Coordinator_EmailId"), "Test Step - 18: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_address_event_coordinator(), prop.getProperty("Event_Coordinator_Address"), "Test Step - 18: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_zipcode_event_coordinator(), prop.getProperty("Event_Coordinator_Zipcode"), "Test Step - 18: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_city_event_coordinator(), prop.getProperty("Event_Coordinator_City"), "Test Step - 18: Entered event coordinator last name is not displayed");
            softassert.assertEquals(manageproposal.get_entered_state_event_coordinator(), prop.getProperty("Event_Coordinator_State"), "Test Step - 18: Entered event coordinator last name is not displayed");

        } catch (Exception e) {
            e.printStackTrace();
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
