package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T09_WalkinsSetting_CashRegistery_Merchant_Copy_with_Merchant_Copy_Notes_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    public static final String dataSheetName = "Hana_T09";
    CustomSoftAssert softassert = new CustomSoftAssert();
    String invoicenumber;
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T09_WalkinsSetting_CashRegistery_Merchant_Copy_with_Merchant_Copy_Notes_FT(String searchandselectitemcode, String customershortname, String occasion) {
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
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard Page does not navigated to hana dashboard page");
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
            logger.info("User selected the shop name");
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("shopname"), "Test Step - 4: Selected shopname is not matching with expected shopname as " + prop.getProperty("shopname"));

            // Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("clerkname"));
            logger.info("User selected the clerk");
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("clerkname"), "Test Step - 5: Selected clerkname is not matching with expected clerkname as " + prop.getProperty("clerkname"));

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            logger.info("User selected the employee");
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6: Selected employeename is not matching with expected employeename as " + prop.getProperty("employeename"));

            // Test Step - 7
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            logger.info("User search and selected the item code");
            softassert.assertEquals(cashandcarry.ItemCodeValueIsExists(), prop.getProperty("product_itemcode1"), "Test Step - 7 - Item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 7 - Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 7 - Item quantity is not matched with search and selected item code");

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 7 - Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Test Step - 7 - Item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 7 - Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 7 - Item discount percentage is not matched with search and selected item code");

            // Test Step - 8
            cashandcarry.ClickAddItem();

            // Test Step - 9
            cashandcarry.EnterCustomerName(customershortname, prop.getProperty("custfullname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getDisplayedCustomerNameOnCCPage(), "Abish David", "Test Step - 9 - Customer name is not matched with entered customer name");
            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(occasion);
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), occasion, "Test Step - 10 : Selected occasion is not displayed");

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
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 11 : order payment done successfully toast message is not displayed");
            logger.info("User verified the order payment done successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 11 : order payment done successfully toast message is not displayed");

            if (cashandcarrypayment.getConfirmationPopup() ) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                invoicenumber = cashandcarrypayment.GetInvoiceNumber();
                System.out.println("Order invoice number is :" + cashandcarrypayment.GetInvoiceNumber());
                cashandcarrypayment.GetTenderPrice();
                System.out.println("The remaining amount given to customer is :" + cashandcarrypayment.GetTenderPrice());
            }
            delayWithGivenTime(1000);

            // Test Step - 12

            // Test Step - 13
            delayWithGivenTime(2000);
            RobotDismissAlert();

            // Test Step - 14
            delayWithGivenTime(1000);
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 14: Selected employeename is not matching with expected employeename as " + prop.getProperty("employeename"));
            logger.info("User selected the employee");

            delayWithGivenTime(1000);
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            delayWithGivenTime(1000);
            logger.info("User search and selected the item code");
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 14 - Item description is not matched with search and selected item code");
            ;
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 14 - Item quantity is not matched with search and selected item code");

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 14 - Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Test Step - 14 - Item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 14 - Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 14 - Item discount percentage is not matched with search and selected item code");
            delayWithGivenTime(1000);

            cashandcarry.ClickAddItem();

            cashandcarry.EnterCustomerName(customershortname, prop.getProperty("custfullname"));
            softassert.assertEquals(cashandcarry.getDisplayedCustomerNameOnCCPage(), "Abish David", "Test Step - 9 - Customer name is not matched with entered customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(occasion);
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");

            delayWithGivenTime(1000);
            cashandcarry.ClickPayButton();
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            Assert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 14 : Cash And Carry payment page is not displayed");
            logger.info("User is on Cash And Carry payment page");

            softassert.assertTrue(cashandcarrypayment.verifyPaymentDescriptionTableIsDisplayed(), "Test Step - 14 : Payment description table is not displayed");
            cashandcarrypayment.ClickCashTab();
            logger.info("User select the payment type as cash tab");

            cashandcarrypayment.EnterGivenAmount();
            cashandcarrypayment.ClickProcessPaymentBtn();
            logger.info("User click the process payment button");
            Assert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 14 : Payment done successfully toast message is not displayed");
            logger.info("User verified the order payment done successfully");
            Assert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 14 : Order payment done successfully toast message text is not displayed");
            delayWithGivenTime(1000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                cashandcarrypayment.GetInvoiceNumber();
                logger.info("Order invoice number is :" + cashandcarrypayment.GetInvoiceNumber());
                cashandcarrypayment.GetTenderPrice();
                logger.info("The remaining amount given to customer is :" + cashandcarrypayment.GetTenderPrice());
            }
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();
            logger.info("User repeat the test step from 6 to 12 and click the open webclientprint button");
            logger.info("**** Ending Hana_T09_WalkinsSetting_CashRegistery_Merchant_Copy_with_Merchant_Copy_Notes_FunctionalityTest  ****");
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
