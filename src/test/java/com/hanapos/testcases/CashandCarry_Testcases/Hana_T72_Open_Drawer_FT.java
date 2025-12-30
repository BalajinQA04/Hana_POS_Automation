package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T72_Open_Drawer_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    CustomSoftAssert softassert = new CustomSoftAssert();
    public static LoggerUtil logger_Util;

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T72_Open_Drawer_Test() throws InterruptedException, IOException {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            lp.ClickLoginButton();

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(prop.getProperty("bestshopname"));

            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");

            // Test Step - 5
            softassert.assertTrue(cashandcarry.is_Open_Drawer_Button_Disabled(), "Open drawer button is not disabled before selecting clerk name");
            delayWithGivenTime(2000);
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("cashandcarryclerkname"), "Test Step - 5 : Clerk name is not matched with selected clerk name");
            delayWithGivenTime(500);

            cashandcarry.ClickOpenDrawerBtn();

            softassert.assertTrue(cashandcarry.VerifyPasswordPopupIsDisplayed(), "Verify password popup is not displayed");
            softassert.assertTrue(cashandcarry.VerifyPasswordPopup(), "Verify password header popup is not displayed");

            // Test Step - 6
            cashandcarry.EnterVerifyPassword("1234");
            softassert.assertEquals(cashandcarry.getEnteredVerifyPassword(), "1234", "Entered verify password data is not displayed");

            // Test Step - 7
            cashandcarry.ClickVerifyBtnOnPasswordPopup();
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
