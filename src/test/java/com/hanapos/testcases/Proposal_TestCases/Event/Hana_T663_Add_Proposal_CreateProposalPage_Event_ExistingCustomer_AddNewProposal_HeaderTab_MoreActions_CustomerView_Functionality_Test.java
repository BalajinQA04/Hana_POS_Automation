package com.hanapos.testcases.Proposal_TestCases.Event;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.ManageProposalPage;
import com.hanapos.pageObjects.ProposalsPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T663_Add_Proposal_CreateProposalPage_Event_ExistingCustomer_AddNewProposal_HeaderTab_MoreActions_CustomerView_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    String proposalId;

    @Epic("Proposal Module - Event")
    @Test(enabled = true, groups = {"Regression"})
    public void Hana_T663_Add_Proposal_CreateProposalPage_Event_ExistingCustomer_AddNewProposal_HeaderTab_MoreActions_CustomerView_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana_T663_Add_Proposal_CreateProposalPage_Event_ExistingCustomer_AddNewProposal_HeaderTab_MoreActions_CustomerView_Functionality_Test ****");

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
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

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
            manageproposal.Click_more_actions_on_headerTab();

            // Test Step - 11
            manageproposal.Click_add_followup_on_headerTab();
            softassert.assertTrue(manageproposal.Verify_add_follow_up_popup_on_moreActions_IsDisplayed(), "Test Step - 11: Add follow up popup is not displayed");

            // Test Step - 12
            manageproposal.Click_closeIcon_addFollowUp_popup_on_moreActions();

            // Test Step - 13
            manageproposal.Click_more_actions_on_headerTab();
            manageproposal.Click_customerview_on_moreactions_at_headerTab();
            softassert.assertTrue(manageproposal.Validate_EventInfo_Header_on_floristView(), "Test Step - 13 - Florist View - Event Info Header is not displayed");

            // Test Step - 14
            getDriver().close();
            switchToWindowbyIndex(0);
            softassert.assertTrue(manageproposal.verify_edit_proposal_label_header_isDisplayed(), "Test Step - 14: Edit Proposal Label Header is not displayed");


        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }


}
