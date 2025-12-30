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
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T11_WalkinsSetting_CashRegistery_Customer_Copy_With_Customer_Copy_Notes_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    CustomSoftAssert softassert = new CustomSoftAssert();
    public static LoggerUtil logger_Util;

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T11_WalkinsSetting_CashRegistery_Customer_Copy_with_Customer_Copy_Notes_FT() {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            logger.info("Entering valid username and password..");
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            logger.info("Clicked on Login button..");
            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), prop.getProperty("shopname"), "Test Step - 2: Selected shop name is not matching with expected shop name as " + prop.getProperty("shopname"));

            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            Assert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 : Cash And Carry page is not displayed");

            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("shopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("shopname"), "Test Step - 4: Selected shopname is not matching with expected shopname as " + prop.getProperty("shopname"));


            // Pre-requisite - Go to walkins setting --> edit the cash registry (Automation_Don't Delete) -->
            // set customer copy as enabled & customer copy notes as "Customer Copy Notes - Automation"

            cashandcarry.ClickWalkingSettingIcon();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.verifyWalkingSettingPopupIsDisplayed(), "Test Step - 4 : Walking Setting pop up is not displayed");

            cashandcarry.ClickAutomationDontDeleteEditBtnOnWalkInSetting();
            softassert.assertTrue(cashandcarry.VerifyAddNewWalkingSettingPopupIsDisplayed(), "Test Step - 4 : Add new walking setting pop up is not displayed");
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.custCopyCheckBoxonAddNewpopUPIsSelected(), "Test Step - 4 : Customer copy checkbox is not selected");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredCustCopyNoteonAddnewPopup(), "Customer Copy Notes - Automation", "Test Step - 4 : Customer copy notes is not displayed");
            delayWithGivenTime(1000);
            cashandcarry.ClickWalkingPopupCloseIcon();
            delayWithGivenTime(3000);
            logger.info("User selected the shop name");

            // Test Step - 5
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 5 : Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");
            cashandcarry.SelectClerkName("Automation_Don't Delete");
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), "Automation_Don't Delete", "Test Step - 5: Selected clerkname is not matching with expected clerkname");

            logger.info("User selected the clerk as Automation_Don't Delete");

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6: Selected employeename is not matching with expected employeename as " + prop.getProperty("employeename"));

            // Test Step - 7
            cashandcarry.SearchAndSelectTheItemCode(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));

            // If it fails possible to check default values of qty is changed

            logger.info("User search and selected the item code");
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 7 : Item description is not matched with search and selected item code");
            ;
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 7 : Item qty is not matched with search and selected item code");
            ;

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 7 : Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Test Step - 7 : Item price is not matched with search and selected item code");
            }
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 7 : Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 7 : Item discount percentage is not matched with search and selected item code");

            // Test Step - 8
            cashandcarry.ClickAddItem();

            // Test Step - 9
            cashandcarry.EnterCustomerName(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            softassert.assertEquals(cashandcarry.getDisplayedCustomerNameOnCCPage(), "Abish David", "Test Step - 9 - Customer name is not matched with entered customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), prop.getProperty("product_taxtype"), "Test Step - 9 - Tax type is not matched with entered tax type");
            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), prop.getProperty("occasion"), "Test Step - 9 - Occasion is not matched with entered occasion");

            // Test Step - 10
            cashandcarry.ClickPayButton();

            // Test Step - 11
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 11 : Cash And Carry payment page is not displayed");
            logger.info("User is on Cash And Carry payment page");

            cashandcarrypayment.ClickCashTab();
            logger.info("User select the payment type as cash tab");

            cashandcarrypayment.EnterGivenAmount();
            cashandcarrypayment.ClickProcessPaymentBtn();
            logger.info("User click the process payment button");
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 11 : Success toast message is not displayed");
            logger.info("User verified the order payment done successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 11 : Order confirmation toast message is not displayed");
            delayWithGivenTime(1000);

            if (cashandcarrypayment.getConfirmationPopup() == true) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                cashandcarrypayment.GetInvoiceNumber();
            }
            delayWithGivenTime(1000);

            // Test Step - 12
            logger.info("User verified the order payment done successfully");

            // Test Step - 13
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 13 : Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 14
            delayWithGivenTime(1000);
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            logger.info("User selected the employee");
            cashandcarry.SearchAndSelectTheItemCode(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            logger.info("User search and selected the item code");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 14 : Item description is not matched with search and selected item code");
            ;
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 14 : Item qty is not matched with search and selected item code");
            ;

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 14 : Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Test Step - 14 : Item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 14 : Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 14 : Item discount percentage is not matched with search and selected item code");
            cashandcarry.ClickAddItem();
            cashandcarry.EnterCustomerName(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            softassert.assertEquals(cashandcarry.getDisplayedCustomerNameOnCCPage(), "Abish David", "Test Step - 9 - Customer name is not matched with entered customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            cashandcarry.ClickPayButton();
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 14 : Cash And Carry payment page is not displayed");
            logger.info("User is on Cash And Carry payment page");

            softassert.assertTrue(cashandcarrypayment.verifyPaymentDescriptionTableIsDisplayed());

            delayWithGivenTime(1000);

            cashandcarrypayment.ClickCashTab();

            logger.info("User select the payment type as cash tab");
            cashandcarrypayment.EnterGivenAmount();
            cashandcarrypayment.GetCustomerId();
            logger.info("Selected customer id is:" + cashandcarrypayment.GetCustomerId());
            cashandcarrypayment.GetCustomerName();
            logger.info("Selected customer name is:" + cashandcarrypayment.GetCustomerName());
            cashandcarrypayment.ClickProcessPaymentBtn();
            logger.info("User click the process payment button");
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 14 : Success toast message is not displayed");
            logger.info("User verified the order payment done successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 14 : Order payment done successfully text is not displayed");
            delayWithGivenTime(1000);

            if (cashandcarrypayment.getConfirmationPopup() == true) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                cashandcarrypayment.GetInvoiceNumber();
                cashandcarrypayment.GetTenderPrice();
            }

            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();
            logger.info("User repeat the test step from 6 to 12 and click the open webclientprint button");
            logger.info("Finished Hana_T11_WalkinsSetting_CashRegistery_Customer_Copy_With_Customer_Copy_Notes_FT execution completed successfully");
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
