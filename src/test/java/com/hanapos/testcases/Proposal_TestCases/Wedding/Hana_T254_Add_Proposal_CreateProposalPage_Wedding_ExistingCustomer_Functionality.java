package com.hanapos.testcases.Proposal_TestCases.Wedding;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.ProposalsPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class Hana_T254_Add_Proposal_CreateProposalPage_Wedding_ExistingCustomer_Functionality
        extends TestBaseClass {

    // =========================
    // Class Level Page Objects
    // =========================
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;

    // =========================
    // Test Case
    // =========================
    @Epic("Proposal Module")
    @Feature("Wedding Proposal")
    @Story("Create Proposal - Existing Customer")
    @Test(groups = {"Regression"})
    public void Validate_CreateProposal_Wedding_ExistingCustomer_Functionality() {

        CustomSoftAssert softAssert = new CustomSoftAssert();
        logger.info("===== STARTED : Create Proposal Wedding Existing Customer Test =====");

        // -------------------------
        // Step 1 : Login Page
        // -------------------------
        lp = new LoginPage();
        softAssert.assertTrue(
                lp.LoginPageIsDisplayed(),
                "Login page is not displayed"
        );

        lp.EnterUserName(prop.getProperty("username"));
        lp.EnterPassword(prop.getProperty("password"));
        lp.ClickLoginButton();
        logger.info("User logged in successfully");

        // -------------------------
        // Step 2 : Dashboard Page
        // -------------------------
        dashboard = new HanaDashBoardPage();
        softAssert.assertTrue(
                dashboard.VerifyHanaDashBoardPage(),
                "Hana dashboard page not displayed"
        );

        dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
        logger.info("Shop selected from dashboard");

        // -------------------------
        // Step 3 : Proposal Page
        // -------------------------
        proposal = new ProposalsPage();
        proposal.ClickOnProposalsMenu();
        proposal.Click_AddProposalBtn();

        softAssert.assertTrue(
                proposal.Verify_CreateProposalHeader_Popup(),
                "Create Proposal popup is not displayed"
        );

        // =====================================================
        // Search Existing Customer - By First Name
        // =====================================================
        proposal.SearchandSelect_Customer_OnProposal(
                prop.getProperty("cust_firstName")
        );

        softAssert.assertEquals(
                proposal.Verify_CustomerNameIsDisplayed_On_SearchTextBox(),
                prop.getProperty("custfullname"),
                "Customer full name not displayed after search"
        );

        proposal.verifyCustomerDetails(
                softAssert,
                prop.getProperty("cust_firstName"),
                prop.getProperty("cust_lastName"),
                prop.getProperty("cust_companyName"),
                prop.getProperty("cust_email"),
                prop.getProperty("cust_city"),
                prop.getProperty("cust_state"),
                prop.getProperty("cust_address1"),
                prop.getProperty("cust_zipcode"),
                prop.getProperty("cust_phoneNumber"),
                prop.getProperty("cust_Alt_phoneNumber")
        );

        // =====================================================
        // Search Existing Customer - By Phone Number
        // =====================================================
        proposal.SearchandSelect_Customer_OnProposal(
                prop.getProperty("cust_phoneNumber")
        );

        proposal.verifyCustomerDetails(
                softAssert,
                prop.getProperty("cust_firstName"),
                prop.getProperty("cust_lastName"),
                prop.getProperty("cust_companyName"),
                prop.getProperty("cust_email"),
                prop.getProperty("cust_city"),
                prop.getProperty("cust_state"),
                prop.getProperty("cust_address1"),
                prop.getProperty("cust_zipcode"),
                prop.getProperty("cust_phoneNumber"),
                prop.getProperty("cust_Alt_phoneNumber")
        );

        // =====================================================
        // Search Existing Customer - By Customer ID
        // =====================================================
        proposal.SearchandSelect_Customer_OnProposal(
                prop.getProperty("cust_id")
        );

        softAssert.assertEquals(
                proposal.get_customerid_createproposalpopup(),
                prop.getProperty("cust_id"),
                "Customer ID mismatch in create proposal popup"
        );

        // =====================================================
        // Clear Customer Details
        // =====================================================
        proposal.Clear_Customer_On_createproposalpopup();

        proposal.verifyCustomerDetails(
                softAssert,
                "", "", "", "", "", "", "", "", "", ""
        );

        // -------------------------
        // Assert All
        // -------------------------
        softAssert.assertAll();
        logger.info("===== COMPLETED : Create Proposal Wedding Existing Customer Test =====");
    }
}
