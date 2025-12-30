package com.hanapos.testcases.CashandCarry_Testcases;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T75_Payout_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;

    CustomSoftAssert softassert = new CustomSoftAssert();
    public static LoggerUtil logger_Util;

    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T75_Payout_Test() {
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
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");

            // Test Step - 5
            softassert.assertTrue(cashandcarry.is_Payout_Button_Disabled(), "Payout button is not disabled before selecting clerk name");
            delayWithGivenTime(2000);
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("cashandcarryclerkname"), "Test Step - 5 : Clerk name is not matched with selected clerk name");
            delayWithGivenTime(500);
            cashandcarry.ClickPayOutBtn();
            softassert.assertTrue(cashandcarry.VerifyPasswordPopupIsDisplayed(), "Verify password popup is not displayed");
            softassert.assertTrue(cashandcarry.VerifyPasswordPopup(), "Verify password header popup is not displayed");

            // Test Step - 6
            cashandcarry.EnterVerifyPassword("1234");
            softassert.assertEquals(cashandcarry.getEnteredVerifyPassword(), "1234", "Entered verify password data is not displayed");

            // Test Step - 7
            cashandcarry.ClickVerifyBtnOnPasswordPopup();
            delayWithGivenTime(1000);

            // Test Step - 8
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.VerifyCashDrawPopupDisplayed(), "Cash draw popup is not displayed");
            cashandcarry.SelectShopCashDrawPopup(prop.getProperty("bestshopname"));
            delayWithGivenTime(1000);
            cashandcarry.Select_CashRegisterId(prop.getProperty("payment_cash_registry"));
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getSelectedCashRegisterId(), prop.getProperty("payment_cash_registry"), "Test Step - 9: Selected cash register id is not displayed");

            // Test Step - 9
            cashandcarry.Select_Payroll_Expense_Type(prop.getProperty("payout_expense"));
            cashandcarry.Select_Employee_from_dropdown(prop.getProperty("salesperson"));
            delayWithGivenTime(1000);
            //softassert.assertEquals(cashandcarry.getEnteredNameonCashDrawPopup(), "Test Automation", "Entered name on cash draw popup is not displayed");

            // Test Step - 10
            cashandcarry.EnterReasononCashDrawPopup("This is a automation testing reason");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredReasononCashDrawPopup(), "This is a automation testing reason", "Entered reason on cash draw popup is not displayed");

            // Test Step - 11
            cashandcarry.EnterSubTotalonCashDrawPopup("abcdcef");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredSubTotalonCashDrawPopup(), "", "Entered alphabetic characters in sub total on cash draw popup is accepted");

            // Test Step - 12
            cashandcarry.EnterSubTotalonCashDrawPopup("@#$%^");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredSubTotalonCashDrawPopup(), "", "Entered special character in sub total on cash draw popup is accepted");

            // Test Step - 13
            cashandcarry.EnterSubTotalonCashDrawPopup("123456");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredSubTotalonCashDrawPopup(), "123456", "Entered numeric characters in sub total on cash draw popup is not accepted");

            // Test Step - 14
            cashandcarry.EnterTaxAmountonCashDrawPopup("abcdcef");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredTaxAmountonCashDrawPopup(), "", "Entered alphabetic characters in tax on cash draw popup is accepted");

            // Test Step - 15
            cashandcarry.EnterTaxAmountonCashDrawPopup("@#$%^");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredTaxAmountonCashDrawPopup(), "", "Entered special character in tax on cash draw popup is accepted");

            // Test Step - 16
            cashandcarry.EnterTaxAmountonCashDrawPopup("10");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredTaxAmountonCashDrawPopup(), "10", "Entered numeric characters in tax on cash draw popup is not accepted");

            // Test Step - 17
            softassert.assertFalse(cashandcarry.VerifyGrandTotalonCashDrawPopup(), "Grand total field is not disabled");

            // Test Step - 18
            cashandcarry.ClickCloseIcon();

            // Test Step - 19
//            cashandcarry.ClickOnReconcileBtn();
//            softassert.assertTrue(cashandcarry.VerifyReconcilePopupDisplayed(), "Test Step - 19: Reconcile popup is not displayed");
//            cashandcarry.SelectShopReconcilePopup(prop.getProperty("bestshopname"));
//            cashandcarry.Select_CashRegistryIdonReconcilePopup("Cash Register2");
//
//            //softassert.assertEquals(cashandcarry.getSelectedCashRegistryonReconcilePopup(),"SAM","Cash drawer popup selected cash registry is not displayed");
//            delayWithGivenTime(3000);
//            cashandcarry.getPayOut_Amount_OnReconcilePopup();

            cashandcarry.ClickPayOutBtn();
            logger.info("User clicked on pay out button..");
            softassert.assertTrue(cashandcarry.VerifyPasswordPopupIsDisplayed(), "Verify password popup is not displayed");
            softassert.assertTrue(cashandcarry.VerifyPasswordPopup(), "Verify password header popup is not displayed");
            logger.info("User navigated to hana dashboard page sucess..");

            cashandcarry.EnterVerifyPassword("1234");
            softassert.assertEquals(cashandcarry.getEnteredVerifyPassword(), "1234", "Entered verify password data is not displayed");

            cashandcarry.ClickVerifyBtnOnPasswordPopup();
            delayWithGivenTime(1000);

            // Test Step - 20
            softassert.assertTrue(cashandcarry.VerifyCashDrawPopupDisplayed(), "Cash draw popup is not displayed");
            cashandcarry.SelectShopCashDrawPopup(prop.getProperty("bestshopname"));
            cashandcarry.Select_CashRegisterId("Cash Register2");
            cashandcarry.Select_Payroll_Expense_Type(prop.getProperty("payout_expense"));
            cashandcarry.Select_Employee_from_dropdown(prop.getProperty("salesperson"));
            cashandcarry.EnterReasononCashDrawPopup("This is a automation testing reason");
            cashandcarry.EnterSubTotalonCashDrawPopup("100");
            cashandcarry.EnterTaxAmountonCashDrawPopup("10");
            cashandcarry.ClickSaveBtnOnCashDrawPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.VerifySuccessMessageIsDisplayed(), "Success message is not displayed");
            delayWithGivenTime(1000);


            //RobotDismissAlert();
            logger.info("User click the cancel button on webclientprint window popup");

            // Test Step - 21
            delayWithGivenTime(1000);
            cashandcarry.ClickOnReconcileBtn();
            softassert.assertTrue(cashandcarry.VerifyReconcilePopupDisplayed(), "Reconcile popup is not displayed");
            cashandcarry.SelectShopReconcilePopup(prop.getProperty("bestshopname"));
            cashandcarry.Select_CashRegistryIdonReconcilePopup("Cash Register2");

            //softassert.assertEquals(cashandcarry.getSelectedCashRegistryonReconcilePopup(),"SAM","Cash drawer popup selected cash registry is not displayed");
            delayWithGivenTime(3000);
            softassert.assertEquals(cashandcarry.getPayOut_Amount_OnReconcilePopup(), cashandcarry.ExpectedCalculatedPayoutAmount(), "Payout amount on reconcile popup is not displayed");

            //RobotDismissAlert();
            logger.info("User click the cancel button on webclientprint window popup");
            delayWithGivenTime(1000);
            logger.info("**** Finished Hana_T75_Payout_CashAndCarryTest  ****");

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
