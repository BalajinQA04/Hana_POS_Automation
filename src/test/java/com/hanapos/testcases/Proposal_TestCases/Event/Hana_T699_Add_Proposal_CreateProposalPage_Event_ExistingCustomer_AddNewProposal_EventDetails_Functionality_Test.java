package com.hanapos.testcases.Proposal_TestCases.Event;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.ManageProposalPage;
import com.hanapos.pageObjects.ProposalsPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T699_Add_Proposal_CreateProposalPage_Event_ExistingCustomer_AddNewProposal_EventDetails_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    String proposalId;

    @Epic("Proposal Module - Event")
    @Test(enabled = true, groups = {"Regression"})
    public void Hana_T699_Add_Proposal_CreateProposalPage_Event_ExistingCustomer_AddNewProposal_EventDetails_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana_T699_Add_Proposal_CreateProposalPage_Event_ExistingCustomer_AddNewProposal_EventDetails_Functionality_Test ****");

        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

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
            dashboard.SelectShopNameDropDown(prop.getProperty("canada_shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("canada_shopname"));

            // Test Step - 4
            proposal = new ProposalsPage();
            proposal.ClickOnProposalsMenu();

            // Test Step - 5
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 5 - Create a Proposal popup is not displayed");

            // Test Step - 6
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

            // Test Step - 7
            proposal.Click_AddProposal_On_CreateProposal_Popup();

            // Test Step - 8
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 8 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 9
            softassert.assertEquals(manageproposal.get_selected_proposal_type_dropdown_value(), "Event", "Test Step - 9: Proposal type as wedding is not selected on header tab of create proposal page");

            // Pre - requistes
            proposalId = manageproposal.get_ProposalId();

            // Test Step - 10
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_FirstName(), prop.getProperty("cust_firstName"), "Test Step - 9 - Customer first name is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_LastName(), prop.getProperty("cust_lastName"), "Test Step - 9 - Customer last name is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_PhoneNumber(), "9566550756", "Test Step - 9 - Customer phonenumber is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Email(), prop.getProperty("cust_email"), "Test Step - 9 - Customer Email Id is not autopopulated in customer details section ");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_City(), prop.getProperty("cust_city"), "Test Step - 9 - Customer City is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_State(), prop.getProperty("cust_state"), "Test Step - 9 - Customer State is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Address(), prop.getProperty("cust_address1"), "Test Step - 9 - Customer Address is not autopopulated in customer details section");
            softassert.assertEquals(manageproposal.get_Entered_Event_CustSection_Zip(), prop.getProperty("cust_zipcode"), "Test Step - 9 - Customer Zipcode is not autopopulated in customer details section ");
            delayWithGivenTime(2000);

            // Test Step - 11
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

            // Test Step - 12
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


        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }


}
