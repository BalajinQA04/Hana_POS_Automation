package com.hanapos.testcases.Proposal_TestCases.Wedding;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.CustomerPage;
import com.hanapos.pageObjects.DashboardOrderPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.ManageProposalPage;
import com.hanapos.pageObjects.ProposalsPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class Hana_T259_Add_Proposal_CreateProposalPage_Wedding_NewCustomer_Functionality
        extends TestBaseClass {

    // =========================
    // Page Objects
    // =========================
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    private CustomerPage customerpage;
    private DashboardOrderPage dashboardorder;

    // =========================
    // Utilities / Variables
    // =========================
    private String proposalId;
    private LoggerUtil loggerUtil;

    @Epic("Proposal Module")
    @Feature("Wedding Proposal")
    @Story("Create Proposal - New Customer")
    @Test(groups = {"Smoke", "Regression", "Sanity"})
    public void Validate_CreateProposal_Wedding_NewCustomer_Functionality() {

        CustomSoftAssert softAssert = new CustomSoftAssert();
        logger.info("===== STARTED : Create Proposal Wedding New Customer Test =====");

        String testCaseName = getCurrentTestName();
        loggerUtil = new LoggerUtil();
        loggerUtil.startNetworkLogging(testCaseName);

        // =====================================================
        // Step 1 : Login Page
        // =====================================================
        lp = new LoginPage();
        softAssert.assertTrue(
                lp.LoginPageIsDisplayed(),
                "Login page is not displayed"
        );

        lp.EnterUserName(prop.getProperty("username"));
        lp.EnterPassword(prop.getProperty("password"));
        lp.ClickLoginButton();
        logger.info("User logged in successfully");

        // =====================================================
        // Step 2 : Dashboard
        // =====================================================
        dashboard = new HanaDashBoardPage();
        softAssert.assertTrue(
                dashboard.VerifyHanaDashBoardPage(),
                "Dashboard page is not displayed"
        );

        dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
        logger.info("Shop selected on dashboard");

        // =====================================================
        // Step 3 : Proposal Page
        // =====================================================
        proposal = new ProposalsPage();
        proposal.ClickOnProposalsMenu();
        proposal.Click_AddProposalBtn();

        softAssert.assertTrue(
                proposal.Verify_CreateProposalHeader_Popup(),
                "Create Proposal popup is not displayed"
        );

        // =====================================================
        // Step 4 : Generate Test Data
        // =====================================================
        Faker usFaker = new Faker(new java.util.Locale("en-US"));
        Faker caFaker = new Faker(new java.util.Locale("en-CA"));

        String firstName = usFaker.name().firstName();
        String lastName = usFaker.name().lastName();
        String fullName = firstName + " " + lastName;

        String city = usFaker.address().city();
        String state = usFaker.address().state();
        String street = usFaker.address().streetAddress();
        String zipcode = usFaker.address().zipCode();
        String fullAddress = street + ", " + city + ", " + state + " " + zipcode;

        String company = Generate_CompanyName();
        String phone = GenerateUsPhoneNumber();
        String altPhone = GenerateCAPhoneNumber();
        String email = Generate_Random_EmailId();

        // =====================================================
        // Step 5 : Enter Customer Details
        // =====================================================
        proposal.Enter_FirstName_on_CreateProposal_Popup(firstName);
        proposal.Enter_LastName_on_CreateProposal_Popup(lastName);
        proposal.Enter_CompanyName_on_CreateProposal_Popup(company);
        proposal.Enter_City_on_CreateProposal_Popup(city);
        proposal.Enter_State_on_CreateProposal_Popup(state);
        proposal.Enter_Address_on_CreateProposal_Popup(street);
        proposal.clickOnZipcodeFieldOnCreateProposalPopup();
        proposal.Enter_Zipcode_on_CreateProposal_Popup(zipcode);
        proposal.Enter_PhoneNumber_on_CreateProposal_Popup(phone);
        proposal.Enter_SecondaryPhoneNumber_on_CreateProposal_Popup(altPhone);
        proposal.Enter_EmailId_on_CreateProposal_Popup(email);

        proposal.Select_createproposalpopup_selectreport_dropdownfield("Conceptual Design1");
        proposal.Select_createproposalpopup_selectpackage_dropdownfield("Gold Event Package");
        proposal.Select_createproposalpopup_select_eventorwedding_dropdownfield("Abish");

        // =====================================================
        // Step 6 : Verify Entered Details
        // =====================================================
        softAssert.assertEquals(proposal.get_createproposalpopup_firstname_field(), firstName, "First name mismatch");
        softAssert.assertEquals(proposal.get_createproposalpopup_lasttname_field(), lastName, "Last name mismatch");
        softAssert.assertEquals(proposal.get_createproposalpopup_companyname_field(), company, "Company mismatch");
        softAssert.assertEquals(proposal.get_createproposalpopup_email_field(), email, "Email mismatch");
        softAssert.assertEquals(proposal.get_createproposalpopup_city_field(), city, "City mismatch");
        softAssert.assertEquals(proposal.get_createproposalpopup_state_field(), state, "State mismatch");
        softAssert.assertEquals(proposal.get_createproposalpopup_address_field(), street, "Address mismatch");
        softAssert.assertEquals(proposal.get_createproposalpopup_zipcode_field(), zipcode, "Zipcode mismatch");
        softAssert.assertEquals(proposal.get_createproposalpopup_phonenumber_field(), phone, "Phone mismatch");
        softAssert.assertEquals(proposal.get_createproposalpopup_altphonenumber_field(), altPhone, "Alt phone mismatch");

        // =====================================================
        // Step 7 : Create Proposal
        // =====================================================
        proposal.Click_AddProposal_On_CreateProposal_Popup();

        manageproposal = new ManageProposalPage();
        proposalId = manageproposal.get_ProposalId();
        softAssert.assertEquals(
                manageproposal.get_ManageProposalPageTitle(),
                "Hana | ManageProposal",
                "Manage Proposal page not displayed"
        );
        logger.info("Proposal created successfully. Proposal ID : " + proposalId);

        // =====================================================
        // Step 8 : Verify Customer in Customer Page
        // =====================================================
        dashboardorder = new DashboardOrderPage();
        customerpage = new CustomerPage();

        dashboardorder.ClickCustomerMenuOnDashboard();
        softAssert.assertTrue(
                customerpage.VerifyCustomerMenuPage(),
                "Customer page is not displayed"
        );

        customerpage.Enter_CustomerName_searchbox_OnCustTable(fullName);

        softAssert.assertEquals(
                customerpage.VerifyPhoneNumberOnCustTable(),
                phone,
                "Phone number mismatch in customer table"
        );

        String addressFromTable = customerpage.VerifyAddressOnCustTable();
        softAssert.assertTrue(
                addressFromTable.contains(street) || addressFromTable.contains(fullAddress),
                "Address mismatch in customer table"
        );

        // =====================================================
        // Step 9 : Verify Customer Details Popup
        // =====================================================
        customerpage.ClickCustomerTableRow1();
        softAssert.assertTrue(
                customerpage.VerifyCustomerDetailsPopup(),
                "Customer details popup not displayed"
        );

        softAssert.assertTrue(
                customerpage.getCustDetailsFirstNameTextBox().contains(firstName),
                "First name mismatch in customer details popup"
        );
        softAssert.assertTrue(
                customerpage.getCustDetailsLastNameTextBox().contains(lastName),
                "Last name mismatch in customer details popup"
        );
        softAssert.assertEquals(
                customerpage.getCustDetailsPhoneNumberTextbox(),
                phone,
                "Phone number mismatch in customer details popup"
        );

        // =====================================================
        // Final Assertions + Network Logs
        // =====================================================
        loggerUtil.attachNetworkLogs(testCaseName);
        softAssert.assertAll();

        logger.info("===== COMPLETED : Create Proposal Wedding New Customer Test =====");
    }
}
