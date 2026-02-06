package com.hanapos.testcases.Proposal_TestCases.Wedding;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.ManageProposalPage;
import com.hanapos.pageObjects.ProposalsPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T610_Add_Proposal_CreateProposalPage_Wedding_ExistingCustomer_AddNewProposal_CoupleInfo_CoupleDetails_AddressLookUp_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    String proposalId;

    @Epic("Proposal Module - Wedding")
    @Test(enabled = true, groups = {"Regression"})
    public void Hana_T610_Add_Proposal_CreateProposalPage_Wedding_ExistingCustomer_AddNewProposal_CoupleInfo_CoupleDetails_AddressLookUp_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana_T610_Add_Proposal_CreateProposalPage_Wedding_ExistingCustomer_AddNewProposal_CoupleInfo_CoupleDetails_AddressLookUp_Functionality_Testx ****");

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
            softassert.assertEquals(manageproposal.get_selected_proposal_type_dropdown_value(), "Wedding", "Test Step - 9: Proposal type as wedding is not selected on header tab of create proposal page");

            // Pre - requistes
            proposalId = manageproposal.get_ProposalId();

            // Test Step - 10
            manageproposal.Enter_Bride_Address(prop.getProperty("cust_address1"));
            delayWithGivenTime(2000);
            softassert.assertTrue(manageproposal.verify_address_field_autocomplete_options_IsDisplayed(), "Test Step - 10: For entered address autocomplete dropdown option is not displayed");
            manageproposal.Select_Address_from_autocomplete(prop.getProperty("cust_address1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Bride_City(), prop.getProperty("cust_city"), "Test Step - 10 -Entered bride city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_State(), prop.getProperty("cust_state"), "Test Step - 10 -Entered bride state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Address(), prop.getProperty("cust_address1"), "Test Step - 10 -Entered bride address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Zip(), prop.getProperty("cust_zipcode"), "Test Step - 10 -Entered bride zip code is not displayed");

            // Test Step - 11
            manageproposal.Enter_Bride_Address("8137 South Glen Lake Road");
            delayWithGivenTime(2000);
            softassert.assertTrue(manageproposal.verify_address_field_autocomplete_options_IsDisplayed(), "Test Step - 11: For entered address autocomplete dropdown option is not displayed");
            manageproposal.Select_Address_from_autocomplete("8137 South Glen Lake Road");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Bride_City(), prop.getProperty("liam_cust_city"), "Test Step - 11 - Entered bride city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_State(), prop.getProperty("liam_cust_state"), "Test Step - 11 - Entered bride state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Address(), prop.getProperty("liam_cust_address_1"), "Test Step - 11 - Entered bride address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Zip(), prop.getProperty("liam_cust_zipcode"), "Test Step - 11 - Entered bride zip code is not displayed");

            // Test Step - 12
            manageproposal.Enter_Groom_Address("Blossom Valley Rd, El Cajon");
            delayWithGivenTime(2000);
            softassert.assertTrue(manageproposal.verify_address2_field_autocomplete_options_IsDisplayed(), "Test Step - 12: For entered address autocomplete dropdown option is not displayed");
            manageproposal.Select_Address2_from_autocomplete("Blossom Valley Rd, El Cajon");
            delayWithGivenTime(2000);

            softassert.assertEquals(manageproposal.get_Entered_Groom_City(), "San Diego", "Test Step - 12 -Entered groom city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_State(), "CA", "Test Step - 12 -Entered groom state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Address(), "Blossom Valley Road", "Test Step - 12 -Entered groom address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Groom_Zip(), "", "Test Step - 12 -Entered groom zip code is not displayed");


        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }


}
