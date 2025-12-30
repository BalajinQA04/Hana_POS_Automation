package com.hanapos.testcases.Configuration_Settings_Testcases.Order_Entry;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T1316_Configuration_Settings_Order_Entry_WireOut_Methods_Functionality_Test extends TestBaseClass {
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
    public void Validate_Hana_T1316_Configuration_Settings_Order_Entry_WireOut_Methods_Functionality_Test(
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
            settingsPage.click_WireOut_Methods_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_WireOut_Methods_Page_Header_Displayed(), "Test Step - 4 - Wire Out Methods - configuration settings page is not displayed");

            // Test Step - 5
            settingsPage.click_CFS_Edit_Icon_On_WireOutMethods_Table();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Update_Record_WireOutMethods_Popup_Displayed(), "Test Step - 5 - Update record wire out methods popup is not displayed");

            // Test Step - 6
            settingsPage.turnOff_IsEnabled_Toogle_Button_On_WireOutMethods_Popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.verify_IsEnabled_Toogle_Button_On_WireOutMethods_PopupTurnOff_State(), "Test Step - 6 - IsEnabled toogle button on wire out methods popup state is not turn off state");
            delayWithGivenTime(1000);
            settingsPage.click_Save_Button_On_WireOutMethods_Popup();
            delayWithGivenTime(1000);
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Test Step - 6 - Saved Successfully toast success text message is not displayed after updating wire out methods");
            delayWithGivenTime(1000);

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

            phoneorder.Click_WireOut_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_On_WireOut_PhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Wire out Delivery type is not highlighted in blue color");
            delayWithGivenTime(3000);

            softassert.assertTrue(phoneorder.is_WireOut_Option_PaymentMethod_NotDisplayed_OnPhoneOrderPage("CFS"), "Test Step - 5 - CFS - Wire out option is displayed after disabled the wireout methods in configuration settings");


            dashboard.Click_settingsSubmenu();

            delayWithGivenTime(2000);
            settingsPage = new Configuration_SettingsPage();
            softassert.assertTrue(settingsPage.Verify_Configuration_SettingsPage(), "Test Step - 3 - Configuration Settings page is not displayed");

            delayWithGivenTime(2000);
            settingsPage.Click_OrderEntryLeftMenu();
            settingsPage.click_WireOut_Methods_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_WireOut_Methods_Page_Header_Displayed(), "Test Step - 4 - Wire Out Methods - configuration settings page is not displayed");

            settingsPage.click_CFS_Edit_Icon_On_WireOutMethods_Table();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_Update_Record_WireOutMethods_Popup_Displayed(), "Test Step - 5 - Update record wire out methods popup is not displayed");

            settingsPage.turnOn_IsEnabled_Toogle_Button_On_WireOutMethods_Popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.verify_IsEnabled_Toogle_Button_On_WireOutMethods_PopupTurnOn_State(), "Test Step - 6 - IsEnabled toogle button on wire out methods popup state is not turn on state");
            delayWithGivenTime(1000);
            settingsPage.click_Save_Button_On_WireOutMethods_Popup();
            delayWithGivenTime(1000);
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Test Step - 6 - Saved Successfully toast success text message is not displayed after updating wire out methods");
            delayWithGivenTime(3000);

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

            phoneorder.Click_WireOut_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_On_WireOut_PhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Wire out Delivery type is not highlighted in blue color");
            delayWithGivenTime(3000);
            softassert.assertTrue(phoneorder.is_WireOut_Option_PaymentMethod_Displayed_OnPhoneOrderPage("CFS"), "Test Step - 5 - CFS - Wire out option is not displayed after enabed the wireout methods in configuration settings");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }

    }

}
