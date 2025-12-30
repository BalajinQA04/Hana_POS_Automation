package com.hanapos.testcases.Configuration_Settings_Testcases.Order_Entry;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.Configuration_SettingsPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T1271_Common_Address_Management_Delete_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Configuration_SettingsPage settingsPage;
    public static final String dataSheetName = "Hana_T1266";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Configuration Settings Module - Order Entry")
    @Test(groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1271_Common_Address_Management_Delete_Functionality_Test(
            String username,
            String password,
            String shopname,
            String fullname,
            String fulladdress1,
            String fulladdress2,
            String recizip,
            String recicity,
            String recistate,
            String recicountry,
            String updated_name
    ) {
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");
            logger_Util.startNetworkLogging(testCaseName);

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
            settingsPage.Click_CommonAddressManagement();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_CommonAddressManagement_Page_Displayed(), "Test Step - 4 - Common Address Management page is not displayed");

            // Test Step - 5
            delayWithGivenTime(1000);
            settingsPage.click_CommonAddressManagement_AddButton();

            // Test Step - 6
            delayWithGivenTime(1000);
            softassert.assertTrue(settingsPage.is_AddNewRecord_Popup_Displayed_On_CommonAddressManagement(), "Add new record popup is not displayed on common address management page");
            settingsPage.set_Name_On_Common_Address_Management_Page(fullname);
            softassert.assertEquals(settingsPage.get_Name_On_Common_Address_Management_Page(), fullname, "Test Step - 6: Entered name is not matching with expected name as Abish David");

            delayWithGivenTime(500);
            settingsPage.set_Address1_On_Common_Address_Management_Page(fulladdress1);
            softassert.assertEquals(settingsPage.get_Address1_On_Common_Address_Management_Page(), fulladdress1, "Test Step - 6: Entered address1 is not matching with expected address as " + fulladdress1);
            delayWithGivenTime(2000);

            // Test Step - 7
            settingsPage.set_Address2_On_Common_Address_Management_Page(fulladdress2);
            delayWithGivenTime(2000);
            softassert.assertEquals(settingsPage.get_Address2_On_Common_Address_Management_Page(), fulladdress2, "Test Step - 7: Entered address2 is not matching with expected address as " + fulladdress2);
            settingsPage.set_Zipcode_On_Common_Address_Management_Page(recizip);
            delayWithGivenTime(2000);
            softassert.assertEquals(settingsPage.get_Zipcode_On_Common_Address_Management_Page(), recizip, "Test Step - 7: Entered zipcode is not matching with expected zipcode as " + recizip);
            softassert.assertEquals(settingsPage.get_City_On_Common_Address_Management_Page(), recicity, "Test Step - 7: Entered city is not matching with expected city as " + recicity);
            softassert.assertEquals(settingsPage.get_State_On_Common_Address_Management_Page(), recistate, "Test Step - 7: Entered state is not matching with expected state as " + recistate);
            softassert.assertEquals(settingsPage.get_Country_On_Common_Address_Management_Page(), recicountry, "Test Step - 7: Entered country is not matching with expected country as " + recicountry);

            Faker faker = new Faker(new java.util.Locale("en-US"));
            // Custom format to enforce '989-###-####'
            String phoneNumber1 = "919-" + faker.numerify("###-####");
            String phoneNumber2 = "901-" + faker.numerify("###-####");

            settingsPage.set_PhoneNumber1_On_Common_Address_Management_Page(phoneNumber1);
            softassert.assertEquals(settingsPage.get_PhoneNumber1_On_Common_Address_Management_Page(), phoneNumber1, "Test Step - 7: Entered phone number1 is not matching with expected phone number as " + phoneNumber1);

            settingsPage.set_PhoneNumber2_On_Common_Address_Management_Page(phoneNumber2);
            softassert.assertEquals(settingsPage.get_PhoneNumber2_On_Common_Address_Management_Page(), phoneNumber2, "Test Step - 7: Entered phone number2 is not matching with expected phone number as " + phoneNumber2);

            // Test Step - 8
            settingsPage.click_Submit_Button_On_Common_Address_Management_Page();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();

            // Test Step - 9
            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Added New Common Address is not displayed");
            delayWithGivenTime(3000);
            softassert.assertEquals(settingsPage.get_Displayed_Address1_table_grid_common_address_management_page(), fulladdress1, "Test Step 9 - Common address management table grid - Address 1 field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_Address2_table_grid_common_address_management_page(), fulladdress2, "Test Step 9 - Common address management table grid - Address 2 field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_zipcode_table_grid_common_address_management_page(), recizip, "Test Step 9 - Common address management table grid - Country field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_country_table_grid_common_address_management_page(), recicountry, "Test Step 9 - Common address management table grid - Zipcode field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_city_table_grid_common_address_management_page(), recicity, "Test Step 9 - Common address management table grid - City field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_state_table_grid_common_address_management_page(), recistate, "Test Step 9 - Common address management table grid - State field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_phonenumber1_table_grid_common_address_management_page(), phoneNumber1, "Test Step 9 - Common address management table grid - Phone number 1 field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_phonenumber2_table_grid_common_address_management_page(), phoneNumber2, "Test Step 9 - Common address management table grid - Phone number 2 field is not displayed properly");

            // Test Step - 10
            settingsPage.click_Delete_Icon_On_Common_Address_Management_Page();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Delete_Confirmation_Message_Popup_Displayed(), "Delete confirmation header popup is not displayed on click of delete icon - for Common Address page ");

            // Test Step - 11
            settingsPage.click_No_Button_On_Delete_Confirmation_Message_Popup();
            delayWithGivenTime(2000);
            softassert.assertFalse(settingsPage.is_Delete_Confirmation_Message_Popup_Displayed(), "Delete confirmation header popup is displayed on click of no button - for Common Address page ");

            // Test Step - 12
            settingsPage.click_Delete_Icon_On_Common_Address_Management_Page();
            delayWithGivenTime(2000);
            settingsPage.click_Yes_Button_On_Delete_Confirmation_Message_Popup();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Row deleted successfully", "Added New Common Address is not displayed");


        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }

    }

}
