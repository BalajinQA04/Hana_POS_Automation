package com.hanapos.testcases.Configuration_Settings_Testcases.Order_Entry;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T1320_Configuration_Settings_Order_Entry_Product_Categories_Add_New_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private ProposalsPage proposal;
    private ManageProposalPage manageproposal;
    String invoiceNumber;
    public static final String dataSheetName = "Hana_T1432";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    private Configuration_SettingsPage settingsPage;

    String coupon_name;
    Faker faker = new Faker();
    String category;
    String code;
    String description;

    @Epic("Configuration Settings Module - Order Entry")
    @Test(groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1320_Configuration_Settings_Order_Entry_Product_Categories_Add_New_Functionality_Test(
            String username,
            String password,
            String shopname,
            String salesperson,
            String customername,
            String recipientfname,
            String recipientlname,
            String recipientaddress1,
            String recipientfulladdress1,
            String recipientzipcode,
            String recipientcity,
            String recipientcountry,
            String recipientphone,
            String recipientlocation,
            String deliveryontime,
            String occasion,
            String cardmessage,
            String itemcode,
            String itemdescription,
            String paymenttype,
            String ordertype,
            String deliverytype,
            String mop
    ) {
        CustomSoftAssert softassert = new CustomSoftAssert();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");

            // Test Step - 2
            lp.EnterUserName(username);
            lp.EnterPassword(password);
            softassert.assertEquals(lp.get_entered_username(), username, "Test Step - 2: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), password, "Test Step - 2: Entered password is not matching with expected password as " + prop.getProperty("password"));
            lp.ClickLoginButton();

            // Test Step - 3
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Hana dashboard page is not displayed");
            delayWithGivenTime(2000);
            dashboard.SelectShopNameDropDown(shopname);
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), shopname, "Test Step - 3: Selected shopname is not matching with expected shopname as " + dashboard.get_selected_shopname_from_hanadashboard());
            delayWithGivenTime(1000);
            dashboard.Click_settingsSubmenu();

            delayWithGivenTime(2000);
            settingsPage = new Configuration_SettingsPage();
            softassert.assertTrue(settingsPage.Verify_Configuration_SettingsPage(), "Test Step - 3 - Configuration Settings page is not displayed");

            // Test Step - 4
            settingsPage.Click_OrderEntryLeftMenu();
            settingsPage.click_Product_Categories_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Product_Categories_Page_Header_Displayed(), "Test Step - 4 - Product Categories configuration settings page is not displayed");

            // Test Step - 5
            settingsPage.click_AddNew_Button_On_ProductCategories_TableGrid();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Add_New_Record_ProductCategories_Popup_Displayed(), "Test Step - 5 - Add New Record product categories popup is not displayed");

            // Test Step - 6
            category = faker.commerce().department();
            code = faker.code().ean8();
            description = faker.lorem().sentence();

            settingsPage.enter_ProductCategories_Name_On_AddNewRecord_Popup(category);
            delayWithGivenTime(2000);
            softassert.assertEquals(settingsPage.get_ProductCategories_Name_On_AddNewRecord_Popup(), category, "Test Step - 6: Entered Product categories name is not displayed on add new record popup");

            settingsPage.enter_ProductCategories_Code_On_AddNewRecord_Popup(code);
            delayWithGivenTime(2000);
            softassert.assertEquals(settingsPage.get_ProductCategories_Code_On_AddNewRecord_Popup(), code, "Test Step - 7: Entered Product categories code is not displayed on add new record popup as " + settingsPage.get_ProductCategories_Code_On_AddNewRecord_Popup());

            settingsPage.enter_ProductCategories_Description_On_AddNewRecord_Popup(description);
            delayWithGivenTime(2000);
            softassert.assertEquals(settingsPage.get_ProductCategories_Description_On_AddNewRecord_Popup(), description, "Test Step - 8: Entered Product categories description is not displayed on add new record popup");

            settingsPage.turnOn_IsEvent_Toogle_Button_On_AddNewRecord_Popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.verify_IsEvent_Toogle_Button_On_TurnOn_State_AddNewRecord_Popup(), "Test Step - 9: IsEvent toggle button is not displayed on add new record popup");

            settingsPage.turnOn_IsWedding_Toogle_Button_On_AddNewRecord_Popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.verify_IsWedding_Toogle_Button_On_TurnOn_State_AddNewRecord_Popup(), "Test Step - 9: IsWedding toggle button is not displayed on add new record popup");

            settingsPage.turnOn_IsEnabled_Toogle_Button_On_AddNewRecord_Popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.verify_IsEnabled_Toogle_Button_On_TurnOn_State_AddNewRecord_Popup(), "Test Step - 9: IsEnabled toggle button is not displayed on add new record popup");

            delayWithGivenTime(2000);
            settingsPage.click_Submit_Button_On_AddNewRecord_Product_Categories_Popup();

            delayWithGivenTime(2000);


//==========================Wedding Proposal=============================================================

            proposal = new ProposalsPage();
            proposal.ClickOnProposalsMenu();

            // Test Step - 5
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 21 - Create a Proposal popup is not displayed");

            // Test Step - 6
            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            proposal.Click_CancelButton_On_createproposal_popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.isCreateProposalPopupClosed(), "Test Step - 6: Create proposal popup is not closed on view proposal page");

            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 7 - Create a Proposal popup is not displayed on view proposal page");

            // Test Step - 7
            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            softassert.assertEquals(proposal.Verify_CustomerNameIsDisplayed_On_SearchTextBox(), prop.getProperty("custfullname"), "Test Step - 7: Searched customer full name " + prop.getProperty("custfullname") + " is not displayed on create proposal popup");
            delayWithGivenTime(2000);
            proposal.Click_AddProposal_On_CreateProposal_Popup();

            // Test Step - 8
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 8 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);

            manageproposal.Click_ItemsAndProducts_Tab();

            delayWithGivenTime(2000);
            manageproposal.Select_CategoryDropdown_On_ItemsAndProducts(category);
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Selected_Category_On_ItemsAndProducts(), category, "Test Step - 9: Selected category is not displayed on manage proposal page");
            delayWithGivenTime(2000);

//============================== Event ==================================================

            proposal = new ProposalsPage();
            proposal.ClickOnProposalsMenu();

            // Test Step - 5
            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 21 - Create a Proposal popup is not displayed");

            // Test Step - 6
            proposal.Click_event_tab_on_Createproposal_popup();
            delayWithGivenTime(1000);
            proposal.SearchandSelect_Customer_OnProposal(prop.getProperty("cust_firstName"));
            proposal.Click_CancelButton_On_createproposal_popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.isCreateProposalPopupClosed(), "Test Step - 6: Create proposal popup is not closed on view proposal page");

            delayWithGivenTime(3000);
            proposal.Click_AddProposalBtn();
            delayWithGivenTime(2000);
            softassert.assertTrue(proposal.Verify_CreateProposalHeader_Popup(), "Test Step - 7 - Create a Proposal popup is not displayed on view proposal page");

            // Test Step - 7
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
            proposal.Click_AddProposal_On_CreateProposal_Popup();

            // Test Step - 8
            delayWithGivenTime(2000);
            manageproposal = new ManageProposalPage();
            softassert.assertEquals(manageproposal.get_ManageProposalPageTitle(), "Hana | ManageProposal", "Test Step - 8 - Manage Proposal page is not displayed");
            delayWithGivenTime(2000);

            manageproposal.Click_ItemsAndProducts_Tab();
            delayWithGivenTime(2000);
            manageproposal.Select_CategoryDropdown_On_ItemsAndProducts(category);
            delayWithGivenTime(2000);
            softassert.assertEquals(manageproposal.get_Selected_Category_On_ItemsAndProducts(), category, "Test Step - 9: Selected category is not displayed on manage proposal page");
            delayWithGivenTime(2000);


        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }

    }

}
