package com.hanapos.testcases.CashandCarry_Testcases;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T04_WalkingSettings_UI_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    public static LoggerUtil logger_Util;

    // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
    CustomSoftAssert softassert = new CustomSoftAssert();

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Severity(SeverityLevel.MINOR)
    @Owner("Balaji N")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T04_WalkingSetting_UIElement_Test() {
        //Test Step - 1
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));

            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            logger.info("Clicked on Login button..");

            dashboard = new HanaDashBoardPage();

            //Test Step - 2
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), prop.getProperty("shopname"), "Test Step - 2: Selected shop name is not matching with expected shop name as " + prop.getProperty("shopname"));

            logger.info("User selected the shop name as " + prop.getProperty("shopname") + "in dashboard page");
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();

            //Test Step - 3
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");

            logger.info("User verify the Cash and Carry page is displayed..");

            //Test Step - 4
            softassert.assertEquals(cashandcarry.VerifyWalkingSettingsToolTip(), "WalkIns Settings", "***Walking Settings tooltip is not displayed***");

            //Test Step - 5
            cashandcarry.ClickWalkingSettingIcon();
            delayWithGivenTime(3000);

            //Test step - 6
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.verifyWalkingSettingPopupIsDisplayed(), "Walking Setting pop up is not displayed");
            delayWithGivenTime(3000);
            softassert.assertEquals(cashandcarry.VerifyDefaultShopNameonWalkingSettings(), prop.getProperty("shopname"), "***Default shop name is not displayed***");
            softassert.assertTrue(cashandcarry.VerifyAddNewButtonIsDisplayedonWalkinsSettings(), "Add new button is not displayed");
            softassert.assertTrue(cashandcarry.VerifyWalkingPopupCloseIcon(), "walking setting pop up close icon is not displayed");
            delayWithGivenTime(3000);
            softassert.assertEquals(cashandcarry.VerifyShopTableHeaderonWalkingSettingSno(), "S.No.", "***S.No. header is not displayed***");
            softassert.assertEquals(cashandcarry.VerifyShopTableHeaderonWalkingSettingClerkId(), "ClerkID", "***ClerkID header is not displayed***");
            softassert.assertEquals(cashandcarry.VerifyWalkingSettingShopPrinterName(), "PrinterName", "***Printer Name header is not displayed***");
            softassert.assertEquals(cashandcarry.VerifyWalkingSettingShopCashDrawcode(), "CashdrawCode", "***Cash draw Code header is not displayed***");
            softassert.assertEquals(cashandcarry.VerifyWalkingSettingPrinterCutCode(), "PrintCutCode", "***Print Cut Code header is not displayed***");
            softassert.assertEquals(cashandcarry.VerifyClerkDescWalkingSetting(), "ClerkDesc", "***Clerk description header is not displayed***");
            softassert.assertEquals(cashandcarry.VerifyWalkingSettingCustcopy(), "CustomerCopy", "***Customer Copy header is not displayed***");
            softassert.assertEquals(cashandcarry.VerifyWalkingSettingActionheader(), "Action", "***Action header is not displayed***");

            delayWithGivenTime(3000);

            softassert.assertTrue(cashandcarry.ValidateEditBtnPresenceOnWalkingSettingPopup(), "Edit button is not displayed");
            softassert.assertTrue(cashandcarry.ValidateDeleteIconPresenceOnWalkingSettingPopup(), "Delete button is not displayed");

            // Test Step - 7
            cashandcarry.ClickWalkingSettingPopupCloseIcon();
            logger.info("Ended the test case - Hana_T04_WalkingSettings_UI_CashAndCarryTest");
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
