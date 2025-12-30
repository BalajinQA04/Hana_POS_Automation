package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.pageObjects.*;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class Hana_T53_Payments_Check_Payment_Section_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    public static final String dataSheetName = "Hana_T53";
    public static LoggerUtil logger_Util;
    String invoice;
    private DashboardOrderPage dashboardorder;

    CustomSoftAssert softassert = new CustomSoftAssert();

    String invoiceNo;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T53_Payments_Check_Payment_Section_Test(String searchandselectitemcode, String customername, String occasion, String checksplnum, String checknum, String bankname, String nameofcheck) throws InterruptedException, IOException {
        // Test Step - 1
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            Allure.step("User on the hana pos login page");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Ste 2
            lp.EnterUserName(prop.getProperty("bestuname"));

            lp.EnterPassword(prop.getProperty("bestpass"));

            lp.ClickLoginButton();

            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard Page does not navigated to hana dashboard page");

            // Test Step - 3
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();

            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("bestshopname")); //"Hana POS (Canada)"

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname")); //"Automation clerk desc"

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename")); //"QA Team Automation"

            // Test Step - 7
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 7 : Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 7 : Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 7 : Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 7 : Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 7 : Item discount percentage is not matched with search and selected item code");

            // Test Step - 8
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 8 : Added item is not displayed");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Step - 8 : Added item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Step - 8 : Added item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 8 : Added item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Step - 8 : Added item extended price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Step - 8 : Added item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 8 : Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 8 : Added item discount percentage is not matched with search and selected item code");


            // Test Step - 9
            delayWithGivenTime(2000);
            cashandcarry.ClickParticularProdTitle();
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 10 - Added product tile is not displayed on row 2 table grid");
            softassert.assertEquals(cashandcarry.getAddedItemDescriptionRow2(), prop.getProperty("cashandcarry_product_description"), "Test Step - 10 - Added item description in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemQuantityRow2(), "1", "Test Step - 10 - Added item quantity in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemPriceRow2(), "$40.00", "Test Step - 10 - Added item extended price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemExtentPriceRow2(), "$40.00", "Test Step - 10 - Added item price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountAmountRow2(), "$ 0.00", "Test Step - 10 - Added item discount amount in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountPercentageRow2(), "0.00", "Test Step - 10 - Added item discount percentage in row 2 is not displayed on the page");

            // Test Step - 10
            cashandcarry.EnterCustomerName(customername, prop.getProperty("custfullname"));
            cashandcarry.SelectTaxType("Tax Exemption");
            cashandcarry.SelectOccasion(occasion);

            cashandcarry.ClickPayButton();
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 10 : Cash And Carry payment page is not displayed");

            // Test Step - 11
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.VerifyCreditCardTabIsSelected(), "true", "Test Step - 11 : By defaultCredit card tab section is not displayed");

            // Test Step - 12
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCheckTab();
            softassert.assertTrue(cashandcarrypayment.VerifyCheckTabDisplayed(), "Test Step - 12 : Check tab is not displayed");

            // Test Step - 13
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckNumber(), "1844020000000010", "Test Step - 13 : Check number is not displayed");
            softassert.assertEquals(cashandcarrypayment.getDisplayedBankName(), "Bank Of America", "Test Step - 13 : Check bank name is not displayed");
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckName(), "Hana POS", "Test Step - 13 : Check name is not displayed");

            // Test Step - 14
            cashandcarrypayment.ClickProcessPaymentBtn();

            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 14 : Order payment done successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 14 : Order payment done successfully message is not displayed");

            delayWithGivenTime(1000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                logger.info("User verify the order confirmation popup is displayed");
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                logger.info("User verify the order confirmation message and invoice number is displayed");
                invoiceNo = cashandcarrypayment.GetInvoiceNumber();
                cashandcarrypayment.GetTenderPrice();
                logger.info(("The remaining amount given to customer is :" + cashandcarrypayment.GetTenderPrice()));
            }


            // Test Step - 15
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();

            delayWithGivenTime(4000);
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 15 : Cash And Carry page is not displayed");

            delayWithGivenTime(2000);
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 16 : Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 16 : Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 16 : Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 16 : Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 16 : Item discount percentage is not matched with search and selected item code");

            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem());
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Step - 16 : Added item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Step - 16 : Added item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 16 : Added item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Step - 16 : Added item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Step - 16 : Added item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 16 : Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 16 : Added item discount percentage is not matched with search and selected item code");
            Allure.step("User verified the added item details are displayed on table grid row 1");

            delayWithGivenTime(2000);
            cashandcarry.ClickParticularProdTitle();
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 9 - Added item code in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDescriptionRow2(), prop.getProperty("cashandcarry_product_description"), "Test Step - 9 - Added item description in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemQuantityRow2(), "1", "Test Step - 9 - Added item quantity in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemPriceRow2(), "$40.00", "Test Step - 9 - Added item extended price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemExtentPriceRow2(), "$40.00", "Test Step - 9 - Added item price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountAmountRow2(), "$ 0.00", "Test Step - 9 - Added item discount amount in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountPercentageRow2(), "0.00", "Test Step - 9 - Added item discount percentage in row 2 is not displayed on the page");

            cashandcarry.EnterCustomerName(customername, prop.getProperty("custfullname"));

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");
            delayWithGivenTime(1000);

            cashandcarry.ClickPayButton();
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Known Issue Test Step - 16 : Cash And Carry payment page is not displayed");

            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.VerifyCreditCardTabIsSelected(), "true", "Known Issue Test Step - 16 : By defaultCredit card tab section is not displayed");

            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCheckTab();

            softassert.assertTrue(cashandcarrypayment.VerifyCheckTabDisplayed(), "Test Step - 16 : Check tab is not displayed");

            // Test Step - 16
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckNumber(), "1844020000000010", "Test Step - 16 : Check number is not displayed");

            softassert.assertEquals(cashandcarrypayment.getDisplayedBankName(), "Bank Of America", "Test Step - 16 : Bank name is not displayed");

            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckName(), "Hana POS", "Test Step - 16 : Name on check is not displayed");

            // Test Step - 17
            cashandcarrypayment.EnterCheckNumber("");
            cashandcarrypayment.EnterBankName("");
            cashandcarrypayment.EnterNameOnCheck("");

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckNumber(), "", "Test Step - 17 : Check number is not displayed as blank");
            softassert.assertEquals(cashandcarrypayment.getDisplayedBankName(), "", "Test Step - 17 : Bank name is not displayed as blank");
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckName(), "", "Test Step - 17 : Name on check is not displayed as blank");

            // Test Step - 18

            // Test Step - 19
            cashandcarrypayment.EnterCheckNumber(checksplnum);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckNumber(), "", "Test Step - 19 : Special Check number is displayed");

            // Test Step - 20
            cashandcarrypayment.EnterCheckNumber(checknum);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckNumber(), "1844020000000050", "Test Step - 20 : Check number is not displayed");

            // Test Step - 21
            cashandcarrypayment.EnterBankName(bankname);
            softassert.assertEquals(cashandcarrypayment.getDisplayedBankName(), "Fedral Bank", "Test Step - 21 : Bank name is not displayed");

            // Test Step - 22
            cashandcarrypayment.EnterNameOnCheck(nameofcheck);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCheckName(), "Hana Test Automation for pos", "Test Step - 22 : Name on check is not displayed");

            // Test Step - 23
            logger_Util.payment_API_Logger("Check Payment - Process Payment API LOG", () -> {
                cashandcarrypayment.ClickProcessPaymentBtn();
            });
          //  cashandcarrypayment.ClickProcessPaymentBtn();

            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 23 : Success toast message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 23 : Order confirmation toast message is not displayed");

            delayWithGivenTime(1000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                softassert.assertTrue(cashandcarrypayment.VerifyOrderConfirmationPopup(), "Order confirmation popup is not displayed");
                softassert.assertTrue(cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo(), "Order confirmation message along with invoice number is not displayed");
                invoice = cashandcarrypayment.GetInvoiceNumber();
                cashandcarrypayment.GetTenderPrice();
            }
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();

            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 20: User is not navigated to All orders page");
            delayWithGivenTime(1000);

            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice), "Test Step - 20 - Respective Invoice number : " + invoice + " is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice), "Delivered", "Test Step - 20 - Order status is not displayed as delivered for cash and carry order");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoice), "Walkin Sales", "Test Step - 20: Order Type as Walkin Sales is not properly displayed for cash and carry order");

            dashboardorder.EnterGlobalSearch(invoice);
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice), "Check", "Test STep - 20: Cash mode of pay is not displayed on orders page");

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
