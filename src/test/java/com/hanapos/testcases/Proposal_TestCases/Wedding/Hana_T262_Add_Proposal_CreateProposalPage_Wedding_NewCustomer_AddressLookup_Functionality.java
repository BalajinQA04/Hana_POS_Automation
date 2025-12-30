package com.hanapos.testcases.Proposal_TestCases.Wedding;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.ManageProposalPage;
import com.hanapos.pageObjects.ProposalsPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.BrokenLinksTest;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T262_Add_Proposal_CreateProposalPage_Wedding_NewCustomer_AddressLookup_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    private BrokenLinksTest brokenLinksTest;
    String proposalId;

    @Epic("Proposal Module - Wedding")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T262_Add_Proposal_CreateProposalPage_Wedding_NewCustomer_AddressLookup_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger.info("**** Starting  Validate_Hana_T262_Add_Proposal_CreateProposalPage_Wedding_NewCustomer_AddressLookup_Functionality_Test ****");
        logger.debug("capturing application debug logs....");

        try {

            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");
            //  PerformanceLogger.capturePageLoadTime("Login Page");

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
            String usPhoneNumber = usFaker.phoneNumber().cellPhone();
            String caPhoneNumber = canadaFaker.phoneNumber().cellPhone();
            String usStreetAddress = usFaker.address().streetAddress();
            String usCity = usFaker.address().city();
            String usState = usFaker.address().state();
            String usZipCode = usFaker.address().zipCode();
            String usFullAddress = usStreetAddress + ", " + usCity + ", " + usState + " " + usZipCode;

            // Test Step - 6
            proposal.Enter_FirstName_on_CreateProposal_Popup(usFirstName);
            proposal.Enter_LastName_on_CreateProposal_Popup(usLastName);
            String companyname = Generate_CompanyName();
            String phonenumber = GenerateUsPhoneNumber();
            String secondaryphonenumber = GenerateCAPhoneNumber();
            String emailid = Generate_Random_EmailId();

            proposal.Enter_CompanyName_on_CreateProposal_Popup(companyname);
            proposal.Enter_PhoneNumber_on_CreateProposal_Popup(phonenumber);
            proposal.Enter_SecondaryPhoneNumber_on_CreateProposal_Popup(secondaryphonenumber);
            proposal.Enter_EmailId_on_CreateProposal_Popup(emailid);

            proposal.Enter_Address_on_CreateProposal_Popup("10 S Main St Driggs");
            softassert.assertTrue(proposal.verify_address_autosuggestion_on_createproposalPopup(), "Test Step - 6: Address Autosuggestion is not displayed on create proposal popup");
            delayWithGivenTime(2000);
            proposal.Search_And_Select_Address_On_CreateProposal_Popup("10 S Main St");
            proposal.Select_createproposalpopup_selectreport_dropdownfield("Conceptual Design1");
            proposal.Select_createproposalpopup_selectpackage_dropdownfield("Gold Event Package");
            proposal.Select_createproposalpopup_select_eventorwedding_dropdownfield("Abish");

            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_createproposalpopup_firstname_field(), usFirstName, "Test Step - 6: First name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_lasttname_field(), usLastName, "Test Step - 6: Last name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_companyname_field(), companyname, "Test Step - 6: COmpany Name of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_email_field(), emailid, "Test Step - 6: Email Id of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_city_field(), "Driggs", "Test Step - 6: City of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_state_field(), "ID", "Test Step - 6: State of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_address_field(), "10 S Main St", "Test Step - 6: Address of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_zipcode_field(), "83422", "Test Step - 6: Zipcode of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_phonenumber_field(), phonenumber, "Test Step - 6: Phone number of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_altphonenumber_field(), secondaryphonenumber, "Test Step - 6: Alternative Phone number of customer is not displayed in create proposal popup");
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_Select_createproposalpopup_selectreport_dropdownfield(), "Conceptual Design1", "Test Step - 6: Selected report is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_selectpackage_dropdownfield(), "Gold Event Package", "Test Step - 6: Selected package is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_select_eventorwedding_dropdownfield(), "Abish", "Test Step - 6: Selected event or wedding consultant is not displayed in create proposal popup");
            delayWithGivenTime(2000);

            // Test Step - 7
            proposal.Enter_Address_on_CreateProposal_Popup("16415 Village Plaza View Dr, Wildwood, MO");
            softassert.assertTrue(proposal.verify_address_autosuggestion_on_createproposalPopup(), "Test Step - 7: Again Address Autosuggestion is not displayed on create proposal popup");
            delayWithGivenTime(2000);
            //   proposal.searchAndSelect_Address_On_CreateProposal_Popup("827 North Rock Hill Road");
            manageproposal = new ManageProposalPage();
            manageproposal.searchAndSelect_Address2("16415 Village Plaza View Dr, Wildwood, MO");
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_createproposalpopup_city_field(), "Ballwin", "Test Step - 7: City of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_state_field(), "MO", "Test Step - 7: State of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_address_field(), "16415 Village Plaza View Dr", "Test Step - 7: Address of customer is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_zipcode_field(), "63011", "Test Step - 7: Zipcode of customer is not displayed in create proposal popup");

            // Test Step - 8
            delayWithGivenTime(2000);
            proposal.Click_AddProposal_On_CreateProposal_Popup();
            // softassert.assertEquals(proposal.verifySuccessToastMessageText(), "New Customer added successfully", "Test Step - 8: Success toaster message for created new customer is not displayed");

            // Test Step - 9
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 9 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 10
            proposalId = manageproposal.get_ProposalId();

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
        logger.info("**** Completed Validate_Hana_T262_Add_Proposal_CreateProposalPage_Wedding_NewCustomer_AddressLookup_Functionality_Test ****");
    }
}
