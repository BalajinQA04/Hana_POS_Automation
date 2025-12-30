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

public class Hana_T1276_Configuration_Settings_Order_Entry_Coupon_Details_Add_New_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Configuration_SettingsPage settingsPage;
    public static final String dataSheetName = "Hana_T1266";
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    String coupon_name;

    @Epic("Configuration Settings Module - Order Entry")
    @Test(groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1276_Configuration_Settings_Order_Entry_Coupon_Details_Add_New_Functionality_Test(
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
            settingsPage.click_CouponDetails_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(settingsPage.is_CouponDetails_Section_Displayed(), "Test Step - 4 - Coupon details page is not displayed");

            // Test Step - 5
            delayWithGivenTime(1000);
            settingsPage.select_ShopName_On_CouponDetails(shopname);
            delayWithGivenTime(1000);
            softassert.assertEquals(settingsPage.get_Selected_ShopName_On_CouponDetails(), shopname, "Test Step - 5: Selected shopname is not matching with expected shopname as " + shopname);

            // Test Step - 6
            settingsPage.click_AddNew_Coupon_Button();
            delayWithGivenTime(1000);
            softassert.assertTrue(settingsPage.is_AddNew_Record_Popup_Coupon_Details(), "Add new record popup is not displayed on coupon details page");
            coupon_name = settingsPage.generate_Coupon_Name();

            settingsPage.enter_CouponName_For_CouponDetails_Popup(coupon_name);
            softassert.assertEquals(settingsPage.get_CouponName_On_CouponDetails(), coupon_name, "Test Step - 6: Entered coupon name is not matching ");

            delayWithGivenTime(500);
            settingsPage.set_Coupon_Percent_On_CouponDetails_popup("10");
            softassert.assertEquals(settingsPage.get_CouponPercent_On_CouponDetailsPopup(), "10", "Test Step - 6: Entered coupon percent is not matched");
            delayWithGivenTime(2000);

            // Test Step - 7
            settingsPage.set_Coupon_StartDate_On_CouponDetails_popup(CurrentDate());
            delayWithGivenTime(2000);

            settingsPage.set_Coupon_EndDate_On_CouponDetails_popup(Next_20Days_Date());
            delayWithGivenTime(2000);

            // Test Step - 8
            settingsPage.enter_Coupon_Limit_On_CouponDetails_Popup("1000");
            delayWithGivenTime(1000);
            settingsPage.enter_Coupon_Minimum_On_CouponDetails_Popup("20.00");
            delayWithGivenTime(2000);

            // Test Step - 9
            settingsPage.Turn_On_Toogle_Use_Delivery_Dates_On_CouponDetails_Popup();
            delayWithGivenTime(1000);

            // Test Step - 10
            settingsPage.Turn_On_Toogle_Display_Coupon_On_Website_On_CouponDetails_Popup();
            delayWithGivenTime(1000);

            // Test Step - 11
            settingsPage.click_Submit_Button_On_Add_New_Coupon_Details_Popup();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();

            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Added New Common Address is not displayed");
            delayWithGivenTime(1000);

            softassert.assertTrue(settingsPage.is_Coupon_Name_Displayed_On_CouponDetails_TableGrid(coupon_name), "Test Step - 11: Coupon name is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_Percent_Displayed_On_CouponDetails_TableGrid(coupon_name), "Test Step - 11: Coupon percent is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_StartDate_Displayed_On_CouponDetails_TableGrid(coupon_name), "Test Step - 11: Coupon start date is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_EndDate_Displayed_On_CouponDetails_TableGrid(coupon_name), "Test Step - 11: Coupon end date is not displayed on coupon details table grid");


        } catch (Exception e) {
            softassert.fail(e.getMessage());
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            softassert.assertAll();
        }

    }

}
