package com.hanapos.testcases.Proposal_TestCases.Event;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.BrokenLinksTest;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T283_Add_Proposal_CreateProposalPage_Event_NewCustomer_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    private BrokenLinksTest brokenLinksTest;
    private CustomerPage customerpage;
    private DashboardOrderPage dashboardorder;
    String proposalId;
    public static LoggerUtil logger_Util;

    @Epic("Proposal Module - Event")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"})
    public void Validate_Hana_T283_Add_Proposal_CreateProposalPage_Event_NewCustomer_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger.info("**** Starting  Validate_Hana_T283_Add_Proposal_CreateProposalPage_Event_NewCustomer_Functionality_Test ****");
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
            //   PerformanceLogger.capturePageLoadTime("Hana Dashboard Page");
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");

            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 4
            proposal = new ProposalsPage();
            proposal.ClickOnProposalsMenu();
            logger.info("User clicked on Proposal Menu");
            // PerformanceLogger.capturePageLoadTime("Proposal Page");

            // Test Step - 5
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            // PerformanceLogger.capturePageLoadTime("Add Proposal Popup");
            logger.info("User clicked on Proposal Button");
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 5 - Create a Proposal popup is not displayed");

            Faker usFaker = new Faker(new java.util.Locale("en-US"));
            Faker canadaFaker = new Faker(new java.util.Locale("en-CA"));
            // Generate random US customer details
            String usFirstName = usFaker.name().firstName();
            String usLastName = usFaker.name().lastName();
            String usFullName = usFirstName + " " + usLastName;

            String usPhoneNumber = GenerateUsPhoneNumber();

            String caPhoneNumber = canadaFaker.phoneNumber().cellPhone();
            String usStreetAddress = usFaker.address().streetAddress();
            String usCity = usFaker.address().city();
            String usState = usFaker.address().state();
            String usZipCode = usFaker.address().zipCode();
            String usFullAddress = usStreetAddress + ", " + usCity + ", " + usState + " " + usZipCode;

            proposal.Click_event_tab_on_Createproposal_popup();
            delayWithGivenTime(1000);

            // Test Step - 7
            proposal.Enter_PhoneNumber_on_CreateProposal_Popup(generaterandomeNumber(13));
            proposal.Enter_SecondaryPhoneNumber_on_CreateProposal_Popup(generaterandomeNumber(18));

            softassert.assertEquals(proposal.get_createproposalpopup_phonenumber_field(), "", "Test Step - 6: Phone number of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_altphonenumber_field(), "", "Test Step - 6: Alternative Phone number of customer is not displayed in create proposal popup");

            // Test Step - 8
            proposal.Enter_FirstName_on_CreateProposal_Popup(usFirstName);
            proposal.Enter_LastName_on_CreateProposal_Popup(usLastName);
            String companyname = Generate_CompanyName();
            String phonenumber = GenerateUsPhoneNumber();
            String secondaryphonenumber = GenerateCAPhoneNumber();
            String emailid = Generate_Random_EmailId();

            proposal.Enter_CompanyName_on_CreateProposal_Popup(companyname);
            proposal.Enter_City_on_CreateProposal_Popup(usCity);
            proposal.Enter_State_on_CreateProposal_Popup(usState);
            proposal.Enter_Address_on_CreateProposal_Popup(usStreetAddress);
            proposal.Enter_Zipcode_on_CreateProposal_Popup(usZipCode);
            proposal.Enter_PhoneNumber_on_CreateProposal_Popup(phonenumber);
            proposal.Enter_SecondaryPhoneNumber_on_CreateProposal_Popup(secondaryphonenumber);
            proposal.Enter_EmailId_on_CreateProposal_Popup(emailid);
            delayWithGivenTime(2000);
            proposal.Select_createproposalpopup_selectreport_dropdownfield("Conceptual Design1");
            //   proposal.Select_createproposalpopup_selectpackage_dropdownfield("Gold Event Package");
            proposal.Select_createproposalpopup_select_eventorwedding_dropdownfield("Abish");

            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_createproposalpopup_firstname_field(), usFirstName, "Test Step - 7: First name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_lasttname_field(), usLastName, "Test Step - 7: Last name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_companyname_field(), companyname, "Test Step - 7: COmpany Name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_email_field(), emailid, "Test Step - 7: Email Id of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_city_field(), usCity, "Test Step - 7: City of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_state_field(), usState, "Test Step - 7: State of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_address_field(), usStreetAddress, "Test Step - 7: Address of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_zipcode_field(), usZipCode, "Test Step - 7: Zipcode of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_phonenumber_field(), phonenumber, "Test Step - 7: Phone number of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_altphonenumber_field(), secondaryphonenumber, "Test Step - 7: Alternative Phone number of customer is not displayed in create proposal popup");
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_Select_createproposalpopup_selectreport_dropdownfield(), "Conceptual Design1", "Test Step - 7: Selected report is not displayed in create proposal popup");
            //   softassert.assertEquals(proposal.get_createproposalpopup_selectpackage_dropdownfield(), "Gold Event Package", "Test Step - 7: Selected package is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_select_eventorwedding_dropdownfield(), "Abish", "Test Step - 7: Selected event or wedding consultant is not displayed in create proposal popup");
            delayWithGivenTime(2000);

            proposal.Click_AddProposal_On_CreateProposal_Popup();
         //   softassert.assertEquals(proposal.verifySuccessToastMessageText(), "New Customer added successfully", "Test Step - 8: Success toaster message for created new customer is not displayed");

            // Test Step - 9
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 9 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 10
            proposalId = manageproposal.get_ProposalId();

            // Test Step - 10
            delayWithGivenTime(2000);
            customerpage = new CustomerPage();
            dashboardorder = new DashboardOrderPage();
            dashboardorder.ClickCustomerMenuOnDashboard();
            logger.info("User clicks on customer menu on dashboard");
            delayWithGivenTime(2000);
            softassert.assertTrue(customerpage.VerifyCustomerMenuPage(), "Test Step - 10 - customer menu page is not displayed");
            logger.info("User verify that customer menu page is displayed successfully");

            // Test Step - 11
            customerpage.Enter_CustomerName_searchbox_OnCustTable(usFullName);
            ThreadWait(1000);
            delayWithGivenTime(2000);
            softassert.assertEquals(customerpage.VerifyPhoneNumberOnCustTable(), phonenumber, "Test Step - 11 -Phone number on customer table is not matched");
            logger.info("User verify that phone number on customer table");

            //  softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), "2715 35th Ave Ct", "Test Step - 17 - Address on customer table is not matched");
            if (customerpage.VerifyAddressOnCustTable().contains(usStreetAddress)) {
                softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), usStreetAddress, "Test Step - 11 - address 1 is not displayed on phone order page");
            } else if (customerpage.VerifyAddressOnCustTable().contains(usFullAddress)) {
                softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), usFullAddress, "Test Step - 11 - address 1 is not displayed on phone order page");
            }

            logger.info("User verify that address on customer table");

            // Test Step - 12
            delayWithGivenTime(2000);
            customerpage.ClickCustomerTableRow1();
            logger.info("User clicks the displayed customer in the table ");
            delayWithGivenTime(3000);
            softassert.assertTrue(customerpage.VerifyCustomerDetailsPopup(), "Test Step - 12 - Customer details pop up is not displayed");
            logger.info("User verify that customer details popup is displayed");

            // Test Step - 13
            delayWithGivenTime(2000);
            softassert.assertEquals(customerpage.getCustDetailsFirstNameTextBox().contains(usFirstName), true, "Test Step - 13 - Added on first name field are not properly displayed");
            logger.info("User verify the first name field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsLastNameTextBox().contains(usLastName), true, "Test Step - 13 - Added on first name field are not properly displayed");
            logger.info("User verify the last name field entered data is displayed");
            softassert.assertEquals(customerpage.getCustDetailsPhoneNumberTextbox(), phonenumber, "Test Step - 13 - Added on phone number field are not properly displayed");
            logger.info("User verify the phone number field entered data is displayed");

            if (customerpage.VerifyAddressOnCustTable().contains(usFullAddress)) {
                softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), usFullAddress, "Test Step - 13 - address 1 is not displayed on phone order page");
            } else if (customerpage.VerifyAddressOnCustTable().contains(usStreetAddress)) {
                softassert.assertEquals(customerpage.VerifyAddressOnCustTable(), usStreetAddress, "Test Step - 13 - address 1 is not displayed on phone order page");
            }


        } catch (Exception e) {
            softassert.fail("Known Issue: " + e.getMessage());
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            softassert.assertAll();
        }
        logger.info("**** Completed Validate_Hana_T283_Add_Proposal_CreateProposalPage_Event_NewCustomer_Functionality_Test ****");
    }
}
