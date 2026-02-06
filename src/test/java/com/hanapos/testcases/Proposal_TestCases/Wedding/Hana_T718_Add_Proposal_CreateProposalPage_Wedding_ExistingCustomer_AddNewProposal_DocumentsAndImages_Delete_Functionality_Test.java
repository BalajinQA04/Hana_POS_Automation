package com.hanapos.testcases.Proposal_TestCases.Wedding;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.ManageProposalPage;
import com.hanapos.pageObjects.ProposalsPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

/**
 * Test Case ID: Hana_T718
 * Test Case Name: Add Proposal - Wedding - Existing Customer - Add New Proposal - Documents & Images - Delete Functionality
 * Module: Proposal - Wedding
 * Objective: Verify that for an existing wedding proposal, documents and images can be uploaded and deleted successfully.
 * Author: Balaji N
 * Created On: 08-Aug-2025
 */
public class Hana_T718_Add_Proposal_CreateProposalPage_Wedding_ExistingCustomer_AddNewProposal_DocumentsAndImages_Delete_Functionality_Test extends TestBaseClass {

    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    String proposalId;

    @Epic("Proposal Module - Wedding")
    @Test(enabled = true, groups = {"Regression"})
    public void Hana_T718_Add_Proposal_CreateProposalPage_Wedding_ExistingCustomer_AddNewProposal_DocumentsAndImages_Delete_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();
        try {
            // Step 1: Verify login page is displayed
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1: Login page is not displayed");
            logger.info("User is on the Hana POS login page");

            // Step 2: Enter credentials and login
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("Entered username: " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("Entered password: " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("Clicked Login button");

            // Step 3: Verify navigation to dashboard & select shop
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 3: Dashboard page not displayed");
            logger.info("Navigated to dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("canada_shopname"));
            logger.info("Selected shop: " + prop.getProperty("canada_shopname"));

            // Step 4: Navigate to Proposals menu
            proposal = new ProposalsPage();
            proposal.ClickOnProposalsMenu();

            // Step 5: Click 'Add Proposal' button & verify popup
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(),
                    "Test Step - 5: 'Create a Proposal' popup is not displayed");

            // Step 6: Search & select existing customer, verify all customer details
            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            softassert.assertEquals(proposal.Verify_CustomerNameIsDisplayed_On_SearchTextBox(),
                    prop.getProperty("custfullname"),
                    "Test Step - 6: Customer full name mismatch");
            delayWithGivenTime(2000);
            softassert.assertEquals(proposal.get_createproposalpopup_firstname_field(),
                    prop.getProperty("cust_firstName"), "Test Step - 6: First name mismatch");
            softassert.assertEquals(proposal.get_createproposalpopup_lasttname_field(),
                    prop.getProperty("cust_lastName"), "Test Step - 6: Last name mismatch");
            softassert.assertEquals(proposal.get_createproposalpopup_companyname_field(),
                    prop.getProperty("cust_companyName"), "Test Step - 6: Company name mismatch");
            softassert.assertEquals(proposal.get_createproposalpopup_email_field(),
                    prop.getProperty("cust_email"), "Test Step - 6: Email mismatch");
            softassert.assertEquals(proposal.get_createproposalpopup_city_field(),
                    prop.getProperty("cust_city"), "Test Step - 6: City mismatch");
            softassert.assertEquals(proposal.get_createproposalpopup_state_field(),
                    prop.getProperty("cust_state"), "Test Step - 6: State mismatch");
            softassert.assertEquals(proposal.get_createproposalpopup_address_field(),
                    prop.getProperty("cust_address1"), "Test Step - 6: Address mismatch");
            softassert.assertEquals(proposal.get_createproposalpopup_zipcode_field(),
                    prop.getProperty("cust_zipcode"), "Test Step - 6: Zipcode mismatch");
            softassert.assertEquals(proposal.get_createproposalpopup_phonenumber_field(),
                    prop.getProperty("cust_phoneNumber"), "Test Step - 6: Phone number mismatch");
            softassert.assertEquals(proposal.get_createproposalpopup_altphonenumber_field(),
                    prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 6: Alternate phone mismatch");
            delayWithGivenTime(2000);

            // Step 7: Click 'Add Proposal' on popup
            proposal.Click_AddProposal_On_CreateProposal_Popup();

            // Step 8: Verify navigation to Manage Proposal page
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(),
                    "Hana | ManageProposal",
                    "Test Step - 8: Manage Proposal page title mismatch");
            delayWithGivenTime(2000);

            // Step 9: Verify proposal type is 'Wedding'
            softassert.assertEquals(manageproposal.get_selected_proposal_type_dropdown_value(),
                    "Wedding",
                    "Test Step - 9: Proposal type mismatch");

            // Pre-requisite: Store Proposal ID
            proposalId = manageproposal.get_ProposalId();

            // Step 10: Upload documents and images
            manageproposal.Click_DocumentsAndImages_Tab();
            delayWithGivenTime(2000);
            manageproposal.UploadFiles("Gift card.pdf");
            delayWithGivenTime(2000);
            //manageproposal.UploadImageFile1("samplepdf.pdf");
            softassert.assertTrue(manageproposal.verify_uploaded_firstdocument_displayed_on_grid(),
                    "Test Step - 10: First uploaded document not displayed");
            //   softassert.assertTrue(manageproposal.verify_uploaded_seconddocument_displayed_on_grid(),
            //           "Test Step - 10: Second uploaded document not displayed");

            // Step 11: Delete uploaded documents
            delayWithGivenTime(2000);
            manageproposal.Click_DeleteIcon1_On_UploadedDocument();
            delayWithGivenTime(1000);
          //  manageproposal.Click_DeleteIcon2_On_UploadedDocument();

            // Step 12: Verify deletion success
            softassert.assertTrue(manageproposal.isBrowseFileButtonDisplayed(),
                    "Test Step - 12: Uploaded files not deleted");

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception: " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}
