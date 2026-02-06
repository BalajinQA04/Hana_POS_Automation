package com.hanapos.testcases.Proposal_TestCases.Event;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.ManageProposalPage;
import com.hanapos.pageObjects.ProposalsPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T702_Add_Proposal_CreateProposalPage_Event_ExistingCustomer_AddNewProposal_DocumentsAndImages_UploadImages_Upload_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    String proposalId;

    @Epic("Proposal Module - Event")
    @Test(enabled = true, groups = {"Regression"})
    public void Hana_T702_Add_Proposal_CreateProposalPage_Event_ExistingCustomer_AddNewProposal_DocumentsAndImages_UploadImages_Upload_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();
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
            delayWithGivenTime(2000);
            manageproposal.Click_DocumentsAndImages_Tab();
            manageproposal.Click_Upload_Images_Tab();
            delayWithGivenTime(2000);

            // Test Step - 12
            manageproposal.Upload_Image1_under_UploadImagesTab("Bride.jpg");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.VerifySucessToasterMessageText(), "Image saved successfully..", "Test Step - 12: Uploaded Image1 is not displayed");

            // Test Step - 13
            manageproposal.Upload_Image2_under_UploadImagesTab("Groom.jpg");
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.VerifySucessToasterMessageText(), "Image saved successfully..");

            // Test Step - 14
            manageproposal.Upload_Image3_under_UploadImagesTab("Wedding Stage.jpg");
            delayWithGivenTime(1000);
            softassert.assertEquals(manageproposal.VerifySucessToasterMessageText(), "Image saved successfully..");

            // Test Step - 15
            delayWithGivenTime(2000);
            manageproposal.Click_DocumentsAndImages_Tab();
            delayWithGivenTime(2000);
            manageproposal.UploadFiles("samplepdf.pdf");

            // Test Step - 16
            manageproposal.UploadImageFile1("samplepdf.pdf");
            softassert.assertTrue(manageproposal.verify_uploaded_firstdocument_displayed_on_grid(), "Test Step - 11: Uploaded first document is not displayed on documents and images tab");
            softassert.assertTrue(manageproposal.verify_uploaded_seconddocument_displayed_on_grid(), "Test Step - 11: Uploaded first document is not displayed on documents and images tab");


        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }


}
