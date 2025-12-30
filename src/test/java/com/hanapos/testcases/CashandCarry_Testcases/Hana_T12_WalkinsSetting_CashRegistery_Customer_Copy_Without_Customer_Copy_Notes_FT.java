package com.hanapos.testcases.CashandCarry_Testcases;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T12_WalkinsSetting_CashRegistery_Customer_Copy_Without_Customer_Copy_Notes_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    CustomSoftAssert softassert = new CustomSoftAssert();

    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T12_WalkinsSetting_CashRegistery_Customer_Copy_without_Customer_Copy_Notes_FT() {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
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
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            softassert.assertEquals(dashboard.get_selected_shopname_from_hanadashboard(), prop.getProperty("shopname"), "Test Step - 2: Selected shop name is not matching with expected shop name as " + prop.getProperty("shopname"));

            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();

            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 : Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("shopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("shopname"), "Test Step - 4: Selected shopname is not matching with expected shopname as " + prop.getProperty("shopname"));

            // Pre-requisite - Go to walkins setting --> edit the cash registry (Automation clerk desc) -->
            // set customer copy as enabled & customer copy notes as ""

            cashandcarry.ClickWalkingSettingIcon();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.verifyWalkingSettingPopupIsDisplayed(), "Test Step - 4 : Walking Setting pop up is not displayed");

            cashandcarry.ClickAutomationClerkDescEditBtnOnWalkInSetting();
            softassert.assertTrue(cashandcarry.VerifyAddNewWalkingSettingPopupIsDisplayed(), "Test Step - 4 : Add New Walking Setting pop up is not displayed");
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.custCopyCheckBoxonAddNewpopUPIsSelected(), "Test Step - 4 : Customer Copy checkbox is not selected");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredCustCopyNoteonAddnewPopup(), "", "Test Step - 4 : Customer Copy Notes is not empty");
            delayWithGivenTime(1000);
            cashandcarry.ClickWalkingPopupCloseIcon();
            delayWithGivenTime(3000);
            logger.info("User selected the shop name");

            // Test Step - 5
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 5 : Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");
            cashandcarry.SelectClerkName("Automation Clerk Desc");
            logger.info("User selected the clerk as Automation Clerk Desc");

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6: Selected employeename is not matching with expected employeename as " + prop.getProperty("employeename"));

            // Test Step - 7
            cashandcarry.SearchAndSelectTheItemCode(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            delayWithGivenTime(500);
            // If it fails possible to check default values of qty is changed

            logger.info("User search and selected the item code");
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 7 : Item description is not matched with search and selected item code");
            ;
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 7 : Item quantity is not matched with search and selected item code");
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
            cashandcarry.SelectOccasion(prop.getProperty("occasion"));

            // Test Step - 10
            cashandcarry.ClickPayButton();

            // Test Step - 11
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 11 : Cash And Carry payment page is not displayed");
            logger.info("User is on Cash And Carry payment page");
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickCashTab();
            logger.info("User select the payment type as cash tab");
            cashandcarrypayment.EnterGivenAmount();
            cashandcarrypayment.GetCustomerId();
            cashandcarrypayment.GetCustomerName();
            logger_Util.payment_API_Logger("Cash Payment - Process Payment API LOG", () -> {
                cashandcarrypayment.ClickProcessPaymentBtn();
            });
            logger.info("User click the process payment button");
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 11 : Success toast message is not displayed");
            logger.info("User verified the order payment done successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 11 : Order confirmation toast message is not displayed");
            delayWithGivenTime(1000);

            if (cashandcarrypayment.getConfirmationPopup() == true) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                cashandcarrypayment.GetInvoiceNumber();
                System.out.println("Order invoice number is :" + cashandcarrypayment.GetInvoiceNumber());
                cashandcarrypayment.GetTenderPrice();
                System.out.println("The remaining amount given to customer is :" + cashandcarrypayment.GetTenderPrice());
            }
            delayWithGivenTime(2000);

            // Test Step - 12
            logger.info("User verified the order payment done successfully");

            // Test Step - 13
            //	RobotDismissAlert();
            logger.info("User click the cancel button on webclientprint window popup");
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();

            // Test Step - 14
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 14 : Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            logger.info("User selected the employee");
            cashandcarry.SearchAndSelectTheItemCode(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            logger.info("User search and selected the item code");
            delayWithGivenTime(500);
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 14 : Item description is not matched with search and selected item code");
            ;
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 14 : Item quantity is not matched with search and selected item code");
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
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 11 : Cash And Carry payment page is not displayed");
            logger.info("User is on Cash And Carry payment page");
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarrypayment.verifyPaymentDescriptionTableIsDisplayed());

            cashandcarrypayment.ClickCashTab();
            logger.info("User select the payment type as cash tab");

            cashandcarrypayment.EnterGivenAmount();
            cashandcarrypayment.GetCustomerId();
            logger.info("Selected customer id is:" + cashandcarrypayment.GetCustomerId());
            cashandcarrypayment.GetCustomerName();
            logger.info("Selected customer name is:" + cashandcarrypayment.GetCustomerName());

            cashandcarrypayment.ClickProcessPaymentBtn();
            logger.info("User click the process payment button");

            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 15 : Success toast message is not displayed");
            logger.info("User verified the order payment done successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 16 : Order confirmation toast message is not displayed");
            delayWithGivenTime(1000);

            if (cashandcarrypayment.getConfirmationPopup()) {
                softassert.assertTrue(cashandcarrypayment.VerifyOrderConfirmationPopup(), "Test Step - 17 : Order confirmation popup is not displayed");
                softassert.assertTrue(cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo(), "Test Step - 18 : Message and Invoice No in Order Confirmation Popup is not displayed");
                cashandcarrypayment.GetInvoiceNumber();
                cashandcarrypayment.GetTenderPrice();
            }
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();

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
