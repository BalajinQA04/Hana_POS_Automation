package com.hanapos.testcases.Configuration_Settings_Testcases.Order_Entry;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.Configuration_SettingsPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T1264_Common_Address_Management_Add_New_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Configuration_SettingsPage settingsPage;

    @Epic("Configuration Settings Module - Order Entry")
    @Test(groups = {"Regression"})
    public void Validate_Hana_T1264_Common_Address_Management_Add_New_Functionality() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 2: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 2: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();

            // Test Step - 3
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Hana dashboard page is not displayed");

            delayWithGivenTime(2000);
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
            settingsPage.set_Name_On_Common_Address_Management_Page("Abish David");
            softassert.assertEquals(settingsPage.get_Name_On_Common_Address_Management_Page(), "Abish David", "Test Step - 6: Entered name is not matching with expected name as Abish David");

            delayWithGivenTime(500);
            settingsPage.set_Address1_On_Common_Address_Management_Page("107 Diversified Dr, Villa Ridge, MO 63089");
            softassert.assertEquals(settingsPage.get_Address1_On_Common_Address_Management_Page(), "107 Diversified Dr, Villa Ridge, MO 63089", "Test Step - 6: Entered address1 is not matching with expected address as 107 Diversified Dr, Villa Ridge, MO 63089");
            delayWithGivenTime(2000);

            // Test Step - 7
            settingsPage.set_Address2_On_Common_Address_Management_Page("1419 Highway AM, Villa Ridge, MO 63089");
            delayWithGivenTime(2000);
            softassert.assertEquals(settingsPage.get_Address2_On_Common_Address_Management_Page(), "1419 Highway AM, Villa Ridge, MO 63089", "Test Step - 7: Entered address2 is not matching with expected address as 1419 Highway AM, Villa Ridge, MO 63089");
            settingsPage.set_Zipcode_On_Common_Address_Management_Page("63089");
            delayWithGivenTime(2000);
            softassert.assertEquals(settingsPage.get_Zipcode_On_Common_Address_Management_Page(), "63089", "Test Step - 7: Entered zipcode is not matching with expected zipcode as 63089");
            softassert.assertEquals(settingsPage.get_City_On_Common_Address_Management_Page(), "Villa Ridge", "Test Step - 7: Entered city is not matching with expected city as Villa Ridge");
            softassert.assertEquals(settingsPage.get_State_On_Common_Address_Management_Page(), "MO", "Test Step - 7: Entered state is not matching with expected state as MO");
            softassert.assertEquals(settingsPage.get_Country_On_Common_Address_Management_Page(), "US", "Test Step - 7: Entered country is not matching with expected country as US");

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
            softassert.assertEquals(settingsPage.get_Displayed_Address1_table_grid_common_address_management_page(), "107 Diversified Dr, Villa Ridge, MO 63089", "Test Step 9 - Common address management table grid - Address 1 field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_Address2_table_grid_common_address_management_page(), "1419 Highway AM, Villa Ridge, MO 63089", "Test Step 9 - Common address management table grid - Address 2 field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_zipcode_table_grid_common_address_management_page(), "63089", "Test Step 9 - Common address management table grid - Country field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_country_table_grid_common_address_management_page(), "US", "Test Step 9 - Common address management table grid - Zipcode field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_city_table_grid_common_address_management_page(), "Villa Ridge", "Test Step 9 - Common address management table grid - City field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_state_table_grid_common_address_management_page(), "MO", "Test Step 9 - Common address management table grid - State field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_phonenumber1_table_grid_common_address_management_page(), phoneNumber1, "Test Step 9 - Common address management table grid - Phone number 1 field is not displayed properly");
            softassert.assertEquals(settingsPage.get_Displayed_phonenumber2_table_grid_common_address_management_page(), phoneNumber2, "Test Step 9 - Common address management table grid - Phone number 2 field is not displayed properly");


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            softassert.assertAll();
        }

    }

}
