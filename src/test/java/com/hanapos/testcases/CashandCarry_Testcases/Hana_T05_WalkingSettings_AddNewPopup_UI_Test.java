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

public class Hana_T05_WalkingSettings_AddNewPopup_UI_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    CustomSoftAssert softassert = new CustomSoftAssert();
    public static LoggerUtil logger_Util;

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Severity(SeverityLevel.MINOR)
    @Owner("Balaji N")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T05_WalkingSetting_AddNewBtn_UIElement_Test() {
        //Test Step - 1
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));

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

            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();

            //Test Step - 3
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");

            logger.info("User verify the Cash and Carry page is displayed..");

            //Test Step - 4
            cashandcarry.ClickWalkingSettingIcon();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.verifyWalkingSettingPopupIsDisplayed(), "Test Step - 4: Walkins Setting pop up is not displayed");
            delayWithGivenTime(2000);

            //Test Step - 5
            cashandcarry.ClickAddNewButtonOnWalkingSettingPopup();

            //Test Step - 6
            softassert.assertTrue(cashandcarry.VerifyAddNewWalkingSettingPopupIsDisplayed(), "Test Step - 6: Add new walking setting popup is not displayed");

            softassert.assertTrue(cashandcarry.clerkIdLabelonAddnewPopupIsExists(), "Test Step - 6: Clerk Id label is not displayed");
            softassert.assertTrue(cashandcarry.clerkIdTextboxonAddnewPopupIsDisplayed(), "Test Step - 6: Clerk Id textbox is not displayed");
            softassert.assertTrue(cashandcarry.clerkDescriptionLabelonAddnewPopupIsDisplayed(), "Test Step - 6: Clerk description label is not displayed");
            softassert.assertTrue(cashandcarry.clerkDescriptionTextboxonAddnewPopupIsDisplayed(), "Test Step - 6: Clerk description textbox is not displayed");
            softassert.assertTrue(cashandcarry.printerNameonAddnewPopupIsDisplayed(), "Test Step - 6: printer name label is not displayed");
            softassert.assertTrue(cashandcarry.printerNameTextboxonAddnewPopupIsDisplayed(), "Test Step - 6: printer name textbox is not displayed");
            softassert.assertTrue(cashandcarry.manufacturerLabelonAddnewPopupIsDisplayed(), "manufacturer label is not displayed");
            softassert.assertTrue(cashandcarry.manufacturerDropdownonAddnewPopupIsDisplayed(), "manufacturer dropdown is not displayed");
            softassert.assertTrue(cashandcarry.modelLabelonAddnewPopupIsDisplayed(), "model label is not displayed");
            softassert.assertTrue(cashandcarry.modelDropdownonAddnewPopupIsDisplayed(), "model dropdown is not displayed");
            softassert.assertTrue(cashandcarry.cashdrawcodeLabelonAddnewPopupIsDisplayed(), "cashdraw label is not displayed");
            softassert.assertTrue(cashandcarry.cashdrawcodeTextboxonAddnewPopupIsDisplayed(), "cashdraw textbox is not displayed");
            softassert.assertTrue(cashandcarry.printercutcodeLabelonAddnewPopupIsDisplayed(), "printercut label is not displayed");
            softassert.assertTrue(cashandcarry.printercutcodeTextboxonAddnewPopupIsDisplayed(), "printercut textbox is not displayed");
            softassert.assertTrue(cashandcarry.clearantApiKeyLabelonAddnewPopupIsDisplayed(), "clearant label is not displayed");
            softassert.assertTrue(cashandcarry.clearantApiKeyTextboxonAddnewPopupIsDisplayed(), "clearant API Key textbox is not displayed");
            softassert.assertTrue(cashandcarry.cloverDeviceIdLabelonAddnewPopupIsDisplayed(), "clover label is not displayed");
            softassert.assertTrue(cashandcarry.cloverDeviceIdTextboxonAddnewPopupIsDisplayed(), "clover device id textbox is not displayed");
            softassert.assertTrue(cashandcarry.clovercashdrawerIdLabelonAddnewPopupIsDisplayed(), "clovercashdrawer label is not displayed");
            softassert.assertTrue(cashandcarry.clovercashdrawerIdsearchicononAddnewPopupIsDisplayed(), "clovercashdrawer search icon is not displayed");
            softassert.assertTrue(cashandcarry.enableCloverPrinteronAddnewPopupIsDisplayed(), "enable clover label is not displayed");
            softassert.assertTrue(cashandcarry.enableCloverPrinterCheckboxonAddnewPopupIsDisplayed(), "enable clover label is not displayed");

           /* Removed from UI
           if (cashandcarry.openEdgeWebIDonAddnewPopupIsDisplayed() == true) {
                softassert.assertTrue(cashandcarry.openEdgeWebIDonAddnewPopupIsDisplayed(), "open edge web id label is not displayed");
            } else {
                softassert.fail("open edge web id label is not displayed");
            }*/

            /*if (cashandcarry.openEdgeWebIDTextboxonAddnewPopupIsDisplayed() == true) {
                softassert.assertTrue(cashandcarry.openEdgeWebIDTextboxonAddnewPopupIsDisplayed(), "open edge web id textbox is not displayed");
            } else {
                softassert.fail("open edge web id textbox is not displayed");
            }

            if (cashandcarry.openEdgeTerminalIDLabelonAddnewPopupIsDisplayed() == true) {
                softassert.assertTrue(cashandcarry.openEdgeTerminalIDLabelonAddnewPopupIsDisplayed(), "open edge terminal id label is not displayed");
            } else {
                softassert.fail("open edge terminal id label is not displayed");
            }

            if (cashandcarry.openEdgeTerminalIDTextboxonAddnewPopupIsDisplayed() == true) {
                softassert.assertTrue(cashandcarry.openEdgeTerminalIDTextboxonAddnewPopupIsDisplayed(), "open edge terminal id label is not displayed");
            } else {
                softassert.fail("open edge terminal id label is not displayed");
            }
            if (cashandcarry.openEdgeAuthKeyLabelonAddnewPopupIsDisplayed() == true) {
                softassert.assertTrue(cashandcarry.openEdgeAuthKeyLabelonAddnewPopupIsDisplayed(), "open edge terminal id label is not displayed");
            } else {
                softassert.fail("open edge terminal id label is not displayed");
            }

            if (cashandcarry.openEdgeAuthKeyTextboxonAddnewPopupIsDisplayed() == true) {
                softassert.assertTrue(cashandcarry.openEdgeAuthKeyTextboxonAddnewPopupIsDisplayed(), "open edge terminal id label is not displayed");
            } else {
                softassert.fail("open edge terminal id label is not displayed");
            }*/
            softassert.assertTrue(cashandcarry.OpenEdgeCustomerCopyLabelonAddnewPopupIsDisplayed(), "open edge web id label is not displayed");
            softassert.assertTrue(cashandcarry.openEdgeCustomerCopyCheckboxonAddnewPopupIsDisplayed(), "open edge web id checkbox is not displayed");
            softassert.assertTrue(cashandcarry.OpenEdgeMerchantCopyLabelonAddnewPopupIsDisplayed(), "merchant copy label is not displayed");
            softassert.assertTrue(cashandcarry.openEdgeMerchantCopyCheckboxonAddnewPopupIsDisplayed(), "merchant copy checkbox is not displayed");
            softassert.assertTrue(cashandcarry.custCopyNoteLabelonAddnewPopupIsDisplayed(), "customer copy note label is not displayed");
            softassert.assertTrue(cashandcarry.custCopyNoteTextboxonAddnewPopupIsDisplayed(), "customer copy note textbox is not displayed");
            softassert.assertTrue(cashandcarry.merchantCopyNoteLabelonAddnewPopupIsDisplayed(), "merchant copy note label is not displayed");
            softassert.assertTrue(cashandcarry.merchantCopyNoteTextboxonAddnewPopupIsDisplayed(), "merchant copy note textbox is not displayed");

            softassert.assertTrue(cashandcarry.saveBtnonAddnewPopupIsDisplayed(), "save button is not displayed");
            softassert.assertTrue(cashandcarry.cancelBtnonAddnewPopupIsDisplayed(), "cancel button is not displayed");
            softassert.assertTrue(cashandcarry.closeIconOnAddnewPopupIsDisplayed(), "close icon is not displayed");
            logger.info("***** Ended test cases Hana_T05_WalkingSettings_AddNewPopup_UI_CashAndCarryTest *****");
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
