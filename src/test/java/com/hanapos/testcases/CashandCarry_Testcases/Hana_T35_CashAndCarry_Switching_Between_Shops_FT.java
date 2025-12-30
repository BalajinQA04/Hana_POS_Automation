package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T35_CashAndCarry_Switching_Between_Shops_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    public static final String dataSheetName = "Hana_T35";
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data", parallel = true)
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    public static LoggerUtil logger_Util;

    //expectedExceptions = {NoSuchElementException.class},retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Description("Hana_T35 :- Switching between shops functionality")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T35_CashAndCarry_Switching_Between_Shops_Test(String searchandselectitemcode, String dashboardshopname, String shopname) throws IOException {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered password as " + prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            logger.info("Clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucessfully");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(dashboardshopname);
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), dashboardshopname, "Test Step - 3 - Selected Shop as All not is displayed on dashboard page");
            logger.info("User select the shop name as All in the dashboard page");

            // Test Step - 4
            delayWithGivenTime(2000);
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 4 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 5
            delayWithGivenTime(1000);
            // Known Issue: Shop name as ALL is not displaying
            //  softassert.assertEquals(cashandcarry.getShopNameSelectedOption(), "--Select Shop--", "Test Step - 5 - Selected Shop not is displayed on cash and carry page"); //"Hana POS (Canada)"
            // logger.info("User select the shop name as " + prop.getProperty("shopname"));

            // Test Step - 6
            delayWithGivenTime(1000);
            dashboard.ClickOnHomeIcon();
            logger.info("User clicks on Home icon..");
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 6 - Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucessfully");

            //Test Step - 7
            dashboard.SelectShopNameDropDown(shopname);
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), shopname, "Test Step - 7 - Selected Shop as " + shopname + " not is displayed on dashboard page");

            // Test Step - 8
            dashboard.CashAndCarryMenuClick();
            logger.info("User clicks on Cash and Carry menu..");
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 8 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            //Test Step - 9
            softassert.assertEquals(cashandcarry.getShopNameSelectedOption(), shopname, "Test Step - 9 - Selected Shopname " + shopname + " is not displayed");

            // Test Step - 10
            cashandcarry.SelectShopName(prop.getProperty("shopname"));
            logger.info("User selected shop name as " + prop.getProperty("shopname") + " from dropdown in the cash and carry page");
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getShopNameSelectedOption(), prop.getProperty("shopname"), "Test Step - 10 - Selected Shopname is not displayed");
            logger.info("User select the shop name as " + prop.getProperty("shopname") + " in the cash and carry page");

            // Test Step - 11
            delayWithGivenTime(1000);
            dashboard.ClickOnHomeIcon();
            logger.info("User clicks on Home icon..");
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 11 - Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucessfully");

            // Test Step - 12
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getShopNameSelectedOption(), prop.getProperty("shopname"), "Test Step - 12 - Selected Shopname is not displayed");
            logger.info("User verify the shop name displayed on cash and carry page and dashboard page shop name are matched");
        } catch (Exception e) {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
            softassert.fail(e.getMessage());
        } finally {
            try {
                softassert.assertAll();
            } catch (AssertionError ae) {
                logger_Util = new LoggerUtil();
                logger_Util.attachNetworkLogs(testCaseName);
                ConsoleLogUtils.CaptureConsoleLogs(testCaseName);
                throw ae;
            }
        }
    }
}
