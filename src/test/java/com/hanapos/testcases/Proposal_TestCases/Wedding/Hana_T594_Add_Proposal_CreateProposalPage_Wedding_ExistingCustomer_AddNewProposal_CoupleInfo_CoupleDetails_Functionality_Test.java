package com.hanapos.testcases.Proposal_TestCases.Wedding;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.ManageProposalPage;
import com.hanapos.pageObjects.ProposalsPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T594_Add_Proposal_CreateProposalPage_Wedding_ExistingCustomer_AddNewProposal_CoupleInfo_CoupleDetails_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    String proposalId;

    @Epic("Proposal Module - Wedding")
    @Test(enabled = true, groups = {"Regression"})
    public void Hana_T594_Add_Proposal_CreateProposalPage_Wedding_ExistingCustomer_AddNewProposal_CoupleInfo_CoupleDetails_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana_T594_Add_Proposal_CreateProposalPage_Wedding_ExistingCustomer_AddNewProposal_CoupleInfo_CoupleDetails_Functionality_Test ****");

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
            manageproposal.Enter_LineOneTitle_Under_CoupleInfo("Proposal - Wedding 2025 & Estimation");
            softassert.assertEquals(manageproposal.get_entered_lineonetitle_under_coupleinfo(), "Proposal - Wedding 2025 & Estimation", "Test Step - 10 - Under couple info line one title is not displayed");

            // Test Step - 11
            manageproposal.Enter_LineTwoTitle_Under_CoupleInfo("Two Hearts, One Love Story - Wedding 2025");
            softassert.assertEquals(manageproposal.get_entered_linetwotitle_under_coupleinfo(), "Two Hearts, One Love Story - Wedding 2025", "Test Step - 11: Under couple info line two title is not displayed");

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
            delayWithGivenTime(1000);
            manageproposal.Select_weddingorEvent_consultant_dropdown(prop.getProperty("cust_firstName"));
            delayWithGivenTime(1000);
            softassert.assertEquals(manageproposal.get_selected_weddingorevent_consultant_dropdown(), prop.getProperty("cust_firstName"), "Test Step -14: Selected Wedding/Event consultant is not displayed");

            // Test Step - 15
            manageproposal.Click_CoupleDetailsTab();
            manageproposal.Select_Bride_Groom_DropDown1("Groom");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_selected_bride_grrom_dropdown1(), "Groom", "Test Step - 15 - Selected bride or groom option is not displayed");

            // Test Step - 16
            manageproposal.Enter_Bride_FirstName(prop.getProperty("cust_firstName"));
            manageproposal.Enter_Bride_LastName(prop.getProperty("cust_lastName"));
            manageproposal.Enter_Bride_City(prop.getProperty("cust_city"));
            manageproposal.Enter_Bride_State(prop.getProperty("cust_state"));
            manageproposal.Enter_Bride_Email(prop.getProperty("cust_email"));
            manageproposal.Enter_Bride_Address(prop.getProperty("cust_address1"));
            manageproposal.Enter_Bride_Zip(prop.getProperty("cust_zipcode"));
            manageproposal.Enter_Bride_PhoneNumber(prop.getProperty("cust_phoneNumber"));

            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Entered_Bride_FirstName(), prop.getProperty("cust_firstName"), "Test Step - 9 - Entered bride first name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_LastName(), prop.getProperty("cust_lastName"), "Test Step - 9 - Entered bride last name is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_PhoneNumber(), prop.getProperty("cust_phoneNumber"), "Test Step - 9 -Entered bride phone number is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Email(), prop.getProperty("cust_email"), "Test Step - 9 -Entered bride email is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_City(), prop.getProperty("cust_city"), "Test Step - 9 -Entered bride city is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_State(), prop.getProperty("cust_state"), "Test Step - 9 -Entered bride state is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Address(), prop.getProperty("cust_address1"), "Test Step - 9 -Entered bride address is not displayed");
            softassert.assertEquals(manageproposal.get_Entered_Bride_Zip(), prop.getProperty("cust_zipcode"), "Test Step - 9 -Entered bride zip code is not displayed");


        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }


}
