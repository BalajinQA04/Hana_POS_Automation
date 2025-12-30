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
import java.nio.file.Paths;

public class Hana_T1272_Common_Address_Management_Export_To_Excel_Functionality_Test extends TestBaseClass {
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
    public void Validate_Hana_T1272_Common_Address_Management_Export_To_Excel_Functionality_Test(
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
            settingsPage.click_ExportToExcel_Button_On_CommonAddressManagement();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();

            // Test Step - 6
            String downloadPath = Paths.get(System.getProperty("user.home"), "Downloads").toString();
            delayWithGivenTime(2000);
            phoneorder.VerifyFileIsDownloaded(downloadPath, "Config.xlsx");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }

    }

}
