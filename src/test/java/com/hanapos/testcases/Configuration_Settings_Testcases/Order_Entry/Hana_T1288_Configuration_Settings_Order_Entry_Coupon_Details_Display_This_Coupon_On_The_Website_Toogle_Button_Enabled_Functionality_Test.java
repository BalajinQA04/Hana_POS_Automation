package com.hanapos.testcases.Configuration_Settings_Testcases.Order_Entry;

import com.hanapos.ecommerce_pageObjects.Ecommerce_HomePage;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T1288_Configuration_Settings_Order_Entry_Coupon_Details_Display_This_Coupon_On_The_Website_Toogle_Button_Enabled_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private Advance_DispatchPage advanceDispatchPage;
    private ConfirmationPage confirmationPage;
    private Ecommerce_HomePage ecommerce_homePage;
    String invoiceNumber;
    public static final String dataSheetName = "Hana_T1432";


    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    private Configuration_SettingsPage settingsPage;

    String coupon_name1;
    String coupon_name2;

    @Epic("Configuration Settings Module - Order Entry")
    @Test(enabled = true, priority = 1, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1288_Configuration_Settings_Order_Entry_Coupon_Details_Display_This_Coupon_On_The_Website_Toogle_Button_Enabled_Functionality_Test(
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
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 2: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 2: Entered password is not matching with expected password as " + prop.getProperty("password"));
            lp.ClickLoginButton();

            // Test Step - 3
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Hana dashboard page is not displayed");
            delayWithGivenTime(2000);
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), prop.getProperty("shopname"), "Test Step - 3: Selected shopname is not matching with expected shopname as " + dashboard.get_selected_shopname_from_hanadashboard());
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
            settingsPage.select_ShopName_On_CouponDetails(prop.getProperty("shopname"));
            delayWithGivenTime(1000);
            softassert.assertEquals(settingsPage.get_Selected_ShopName_On_CouponDetails(), prop.getProperty("shopname"), "Test Step - 5: Selected shopname is not matching with expected shopname as " + prop.getProperty("bestshopname"));

            // Test Step - 6
            settingsPage.click_AddNew_Coupon_Button();
            delayWithGivenTime(1000);
            softassert.assertTrue(settingsPage.is_AddNew_Record_Popup_Coupon_Details(), "Add new record popup is not displayed on coupon details page");
            coupon_name1 = settingsPage.generate_Coupon_Name();

            settingsPage.enter_CouponName_For_CouponDetails_Popup(coupon_name1);
            softassert.assertEquals(settingsPage.get_CouponName_On_CouponDetails(), coupon_name1, "Test Step - 6: Entered coupon name is not matching ");

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
            softassert.assertTrue(settingsPage.Verify_Toogle_TurnOn_State_Displayed_Coupon_On_Website_On_CouponDetails_Popup(), "Test Step - 10: Display this coupon on the Website toggle button enable Toogle turn on state is not displayed");

            // Test Step - 11
            settingsPage.click_Submit_Button_On_Add_New_Coupon_Details_Popup();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();

            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Added New Common Address is not displayed");
            delayWithGivenTime(3000);

            softassert.assertTrue(settingsPage.is_Coupon_Name_Displayed_On_CouponDetails_TableGrid(coupon_name1), "Test Step - 11: Coupon name is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_Percent_Displayed_On_CouponDetails_TableGrid(coupon_name1), "Test Step - 11: Coupon percent is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_StartDate_Displayed_On_CouponDetails_TableGrid(coupon_name1), "Test Step - 11: Coupon start date is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_EndDate_Displayed_On_CouponDetails_TableGrid(coupon_name1), "Test Step - 11: Coupon end date is not displayed on coupon details table grid");

            // Test Step - 12
            delayWithGivenTime(3000);
            dashboard.Click_ProfileIcon_On_HanaDashBoardPage_And_Clicks_User_Website();
            switchToWindowbyIndex(2);
            ecommerce_homePage = new Ecommerce_HomePage();
            delayWithGivenTime(3000);
            softassert.assertEquals(ecommerce_homePage.get_HomePage(), prop.getProperty("beautiful_shop"), "Test Step - 12: Ecommerce home page url is not matching with expected url");
            delayWithGivenTime(3000);

            softassert.assertEquals(ecommerce_homePage.get_coupon_information_on_home_page(), "Use coupon " + coupon_name1 + " for 10.00% discount on all orders over $20.00. Offers ends on " + next_20_Days_Date() + "", "Test Step - 12: Coupon information on home page is not displayed properly as expected");

            delayWithGivenTime(3000);
            switchToWindowbyIndex(1);
            settingsPage.click_Delete_Icon_On_CouponDetails_TableGrid(coupon_name1);
            delayWithGivenTime(2000);
            settingsPage.click_Yes_Button_On_Delete_Confirmation_Coupon_Popup();

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }

    }

    @Epic("Configuration Settings Module - Order Entry")
    @Test(priority = 2, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1291_Configuration_Settings_Order_Entry_Coupon_Details_Display_This_Coupon_On_The_Website_Toogle_Button_Disabled_Functionality_Test(
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
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 2: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 2: Entered password is not matching with expected password as " + prop.getProperty("password"));
            lp.ClickLoginButton();

            // Test Step - 3
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Hana dashboard page is not displayed");
            delayWithGivenTime(2000);
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), prop.getProperty("shopname"), "Test Step - 3: Selected shopname is not matching with expected shopname as " + dashboard.get_selected_shopname_from_hanadashboard());
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
            settingsPage.select_ShopName_On_CouponDetails(prop.getProperty("shopname"));
            delayWithGivenTime(1000);
            softassert.assertEquals(settingsPage.get_Selected_ShopName_On_CouponDetails(), prop.getProperty("shopname"), "Test Step - 5: Selected shopname is not matching with expected shopname as " + prop.getProperty("bestshopname"));

            // Test Step - 6
            settingsPage.click_AddNew_Coupon_Button();
            delayWithGivenTime(1000);
            softassert.assertTrue(settingsPage.is_AddNew_Record_Popup_Coupon_Details(), "Add new record popup is not displayed on coupon details page");
            coupon_name2 = settingsPage.generate_Coupon_Name();

            settingsPage.enter_CouponName_For_CouponDetails_Popup(coupon_name2);
            softassert.assertEquals(settingsPage.get_CouponName_On_CouponDetails(), coupon_name2, "Test Step - 6: Entered coupon name is not matching ");

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
            settingsPage.Turn_Off_Toogle_Display_Coupon_On_Website_On_CouponDetails_Popup();
            delayWithGivenTime(1000);
            softassert.assertTrue(settingsPage.Verify_Toogle_TurnOff_State_Displayed_Coupon_On_Website_On_CouponDetails_Popup(), "Test Step - 10: Display this coupon on the Website toggle button enable Toogle turn on state is not displayed");

            // Test Step - 11
            settingsPage.click_Submit_Button_On_Add_New_Coupon_Details_Popup();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();

            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Successfully updated settings", "Added New Common Address is not displayed");
            delayWithGivenTime(3000);

            softassert.assertTrue(settingsPage.is_Coupon_Name_Displayed_On_CouponDetails_TableGrid(coupon_name2), "Test Step - 11: Coupon name is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_Percent_Displayed_On_CouponDetails_TableGrid(coupon_name2), "Test Step - 11: Coupon percent is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_StartDate_Displayed_On_CouponDetails_TableGrid(coupon_name2), "Test Step - 11: Coupon start date is not displayed on coupon details table grid");
            softassert.assertTrue(settingsPage.is_Coupon_EndDate_Displayed_On_CouponDetails_TableGrid(coupon_name2), "Test Step - 11: Coupon end date is not displayed on coupon details table grid");

            // Test Step - 12
            delayWithGivenTime(3000);
            dashboard.Click_ProfileIcon_On_HanaDashBoardPage_And_Clicks_User_Website();
            switchToWindowbyIndex(2);
            ecommerce_homePage = new Ecommerce_HomePage();
            delayWithGivenTime(1000);
            softassert.assertEquals(ecommerce_homePage.get_HomePage(), prop.getProperty("beautiful_shop"), "Test Step - 12: Ecommerce home page url is not matching with expected url");
            delayWithGivenTime(3000);

            softassert.assertTrue(ecommerce_homePage.is_coupon_information_on_home_page_displayed(), "Test Step - 12: Coupon information on home page is displayed if we disabled the coupon on website toggle button");
            delayWithGivenTime(3000);
            switchToWindowbyIndex(1);
            settingsPage.click_Delete_Icon_On_CouponDetails_TableGrid(coupon_name2);
            delayWithGivenTime(2000);
            settingsPage.click_Yes_Button_On_Delete_Confirmation_Coupon_Popup();

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }

    }

}
