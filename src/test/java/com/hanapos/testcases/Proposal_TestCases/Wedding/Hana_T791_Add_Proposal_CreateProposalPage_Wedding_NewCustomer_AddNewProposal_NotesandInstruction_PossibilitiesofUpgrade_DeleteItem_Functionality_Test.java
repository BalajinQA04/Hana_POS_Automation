package com.hanapos.testcases.Proposal_TestCases.Wedding;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.ManageProposalPage;
import com.hanapos.pageObjects.ProposalsPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T791_Add_Proposal_CreateProposalPage_Wedding_NewCustomer_AddNewProposal_NotesandInstruction_PossibilitiesofUpgrade_DeleteItem_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    String proposalId;

    @Epic("Proposal Module - Wedding")
    @Test(enabled = true, groups = {"Regression"})
    public void Hana_T791_Add_Proposal_CreateProposalPage_Wedding_NewCustomer_AddNewProposal_NotesandInstruction_PossibilitiesofUpgrade_DeleteItem_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana_T791_Add_Proposal_CreateProposalPage_Wedding_NewCustomer_AddNewProposal_NotesandInstruction_PossibilitiesofUpgrade_DeleteItem_Functionality_Test ****");

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
            //  softassert.assertEquals(proposal.get_createproposalpopup_selectpackage_dropdownfield(), "Gold Event Package", "Test Step - 7: Selected package is not displayed in create proposal popup");
            softassert.assertEquals(proposal.get_createproposalpopup_select_eventorwedding_dropdownfield(), "Abish", "Test Step - 7: Selected event or wedding consultant is not displayed in create proposal popup");

            // Test Step - 7
            proposal.Click_AddProposal_On_CreateProposal_Popup();
           // softassert.assertEquals(proposal.verifySuccessToastMessageText(), "New Customer added successfully", "Test Step - 7: Success toaster message for created new customer is not displayed");

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
            manageproposal.Click_AddNotes_And_Instructions_Tab();
            softassert.assertTrue(manageproposal.isPossibilities_of_upgrade_header_displayed(), "Test Step - 10 - Possibilities of upgrade header label is not displayed");

            // Test Step - 11
            manageproposal.Enter_Description_On_Possibilities_Of_Upgrade("Test - Possibilities of upgrade # Chocolates - 1/2kg");
            softassert.assertEquals(manageproposal.get_entered_description_on_possibilitiesOfUpgrade_Section(), "Test - Possibilities of upgrade # Chocolates - 1/2kg", "Test Step - 11 : Entered description on possibilities of upgrade section on notes and instruction tab is not displayed");

            // Test Step - 12
            manageproposal.Enter_Quantity_On_PossibilitiesOfUpgrade_Section("12");
            softassert.assertEquals(manageproposal.get_entered_quantity_on_possibilitiesOfUpgrade_Section(), "12", "Test Step - 12 : Entered quantity on possibilities of upgrade section on notes and instruction tab is not displayed");

            // Test Step - 13
            manageproposal.Enter_Price_On_PossibilitiesOfUpgrade_Section("1200");
            softassert.assertEquals(manageproposal.get_entered_price_on_possibilitiesOfUpgrade_Section(), "1200", "Test Step - 13 : Entered price on possibilities of upgrade section on notes and instruction tab is not displayed");

            // Test Step - 14
            manageproposal.Click_AddItem_Button_on_PossibilitiesOfUpgrade_Section();
            delayWithGivenTime(2000);

            // Test Step - 15
            softassert.assertEquals(manageproposal.VerifySucessToasterMessageText(), "Possibilities Added successfully", "Test Step - 15 : Success toaster message is not displayed on notes and instruction tab");
            softassert.assertEquals(manageproposal.get_description_on_possibilitiesOfUpgrade_tablegrid(), "Test - Possibilities of upgrade # Chocolates - 1/2kg", "Test Step - 15 : Entered description on Possibilities of upgrade description on table grid is not displayed on notes and instruction tab");
            softassert.assertEquals(manageproposal.get_quantity_on_possibilitiesOfUpgrade_tablegrid(), "12", "Test Step - 15 : Entered quantity on Possibilities of upgrade quantity on table grid is not displayed on notes and instruction tab");
            softassert.assertEquals(manageproposal.get_price_on_possibilitiesOfUpgrade_tablegrid(), "$1,200.00", "Test Step - 15 : Entered price on Possibilities of upgrade price on table grid is not displayed on notes and instruction tab");
            softassert.assertEquals(manageproposal.get_total_on_possibilitiesOfUpgrade_tablegrid(), manageproposal.Expected_total_on_possibilitiesOfUpgrade_tablegrid(), "Test Step - 15 : Total on Possibilities of upgrade table grid is not displayed on notes and instruction tab");

            // Test Step - 16
            delayWithGivenTime(3000);
            manageproposal.Click_DeleteIcon_on_possibilitiesofupgrade_notesandinstructions_tab();
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.VerifySucessToasterMessageText(), "Possibilities Deleted successfully", "Test Step - 16 : Deleted Success toaster message is not displayed on notes and instruction tab");

            softassert.assertEquals(manageproposal.get_entered_description_on_possibilitiesOfUpgrade_Section(), "", "Test Step - 16 : Deleted description on possibilities of upgrade section on notes and instruction tab is displayed");
            softassert.assertEquals(manageproposal.get_entered_quantity_on_possibilitiesOfUpgrade_Section(), "", "Test Step - 16 : Deleted quantity on possibilities of upgrade section on notes and instruction tab is displayed");
            softassert.assertEquals(manageproposal.get_entered_price_on_possibilitiesOfUpgrade_Section(), "", "Test Step - 16 : Deleted price on possibilities of upgrade section on notes and instruction tab is displayed");


        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }


    }


}
