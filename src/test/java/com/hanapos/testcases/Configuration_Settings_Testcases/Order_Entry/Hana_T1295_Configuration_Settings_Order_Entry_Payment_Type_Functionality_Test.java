package com.hanapos.testcases.Configuration_Settings_Testcases.Order_Entry;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T1295_Configuration_Settings_Order_Entry_Payment_Type_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    public static final String dataSheetName = "Hana_T1432";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    private Configuration_SettingsPage settingsPage;


    @Epic("Configuration Settings Module - Order Entry")
    @Test(groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1295_Configuration_Settings_Order_Entry_Payment_Type_Functionality_Test(
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
            settingsPage.click_Payment_Types_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Payment_Types_Page_Header_Displayed(), "Test Step - 4 - Payment types configuration settings page is not displayed");

            // Test Step - 5
            settingsPage.click_Edit_Icon_On_Marigold_Payment_Type();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Update_Record_Payment_Type_Popup_Displayed(), "Test Step - 5 - Update record payment type popup is not displayed");

            // Test Step - 6
            String payment_option = settingsPage.get_Payment_Label();
            delayWithGivenTime(2000);
            settingsPage.click_TurnOff_Toogle_Button_On_Marigold_Payment_Type();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.verify_TurnOff_Toogle_Button_On_Marigold_Payment_Type(), "Test Step - 6 - Turn off toogle button on marigold payment type state is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 7
            settingsPage.click_Submit_Button_On_Update_Record_Payment_Type_Popup();
            delayWithGivenTime(2000);
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Test Step - 7 - Success toast message is not displayed");
            delayWithGivenTime(2000);

            softassert.assertTrue(settingsPage.is_Marigold_Payment_Type_Displayed_On_PaymentTypes_TableGrid(), "Test Step - 7 - Marigold payment type is not displayed on payment types table grid");
            getDriver().close();
            switchToWindowbyIndex(0);
            refreshPage();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 8 - Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 8 - Cash and carry option is not displayed");

            // Test Step - 8
            dashboard.ClickOrderEntry();

            // Test Step - 9
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            delayWithGivenTime(3000);

            softassert.assertTrue(phoneorder.is_Payment_Type_Section_On_PhoneOrderPage_Displayed(), "Test Step - 9 - Payment Section on phone order page is not displayed");
            delayWithGivenTime(3000);

            softassert.assertTrue(phoneorder.verify_Payment_Type_Option_NotPresent(payment_option), "Test Step - 9 - Payment type option is not displayed");

            // enable marigold payment type
            delayWithGivenTime(1000);
            dashboard.Click_settingsSubmenu();

            delayWithGivenTime(2000);
            settingsPage = new Configuration_SettingsPage();
            softassert.assertTrue(settingsPage.Verify_Configuration_SettingsPage(), "Test Step - 3 - Configuration Settings page is not displayed");

            settingsPage.Click_OrderEntryLeftMenu();
            settingsPage.click_Payment_Types_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Payment_Types_Page_Header_Displayed(), "Test Step - 4 - Payment types configuration settings page is not displayed");
            delayWithGivenTime(2000);
            settingsPage.click_Filter_Icon_On_PaymentTypes_TableGrid();

            delayWithGivenTime(2000);
            settingsPage.click_isFalse_Radio_Button_On_Filter_Popup();
            settingsPage.click_Filter_Button_On_Filter_Popup();

            delayWithGivenTime(2000);
            settingsPage.click_EditIcon_On_Marigold_Payment_Type();
            delayWithGivenTime(2000);

            settingsPage.Enabled_Marigold_Payment_Type();
            delayWithGivenTime(2000);
            settingsPage.click_Submit_Button_On_Update_Record_Payment_Type_Popup();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Test Step - 7 - Success toast message is not displayed");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }

    }

}
