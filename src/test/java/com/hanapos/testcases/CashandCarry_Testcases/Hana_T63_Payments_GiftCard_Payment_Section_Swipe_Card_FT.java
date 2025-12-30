package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.DashboardOrderPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T63_Payments_GiftCard_Payment_Section_Swipe_Card_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private DashboardOrderPage dashboardorder;
    String invoice;
    public static final String dataSheetName = "Hana_T14";

    // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    public static LoggerUtil logger_Util;

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T63_Payments_GiftCard_Payment_Section_Swipe_Card_Test(String searchandselectitemcode) throws InterruptedException, IOException {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("bestuname"));
            logger.info("User entered username as " + prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            logger.info("User entered username as " + prop.getProperty("bestpass"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(prop.getProperty("bestshopname"));
            logger.info("User selected the shop name as " + prop.getProperty("bestshopname") + "in dashboard page");
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));
            logger.info("User select the shop name as " + prop.getProperty("bestshopname") + "in cash and carry page");

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("clerkname"));
            logger.info("User select the clerk name as " + prop.getProperty("clerkname"));

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            logger.info("User select the employee name as " + prop.getProperty("employeename"));

            // Test Step - 7
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode);
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1");

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0");

            // Test Step - 8
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem());
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1");

            if (cashandcarry.GetAddedItemExtPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00");
            } else if (cashandcarry.GetAddedItemExtPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$309.00");
            }

            if (cashandcarry.GetAddedItemPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00");
            } else if (cashandcarry.GetAddedItemPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$309.00");
            }

            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00");

            // Test Step - 9
            delayWithGivenTime(2000);
            cashandcarry.ClickParticularProdTitle();
            logger.info("User click on the particular product tile");
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), "BalloonsYY", "Test Step - 9: Selected product tile on cash and carry page is not displayed");
            logger.info("User verify add the title product to the Cash and Carry page is displayed..");

            // Test Step - 10
            cashandcarry.EnterCustomerName(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            softassert.assertEquals(cashandcarry.getDisplayedCustomerName(), prop.getProperty("custfullname"), "Test Step - 9 - Customer name is not matched with entered customer name");

            cashandcarry.SelectTaxType("Tax Exemption");
            cashandcarry.SelectOccasion("Birthday");
            logger.info("User select the occasion as Birthday");
            delayWithGivenTime(1000);
            cashandcarry.ClickPayButton();
            logger.info("User click on Pay button");
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Cash And Carry payment page is not displayed");
            logger.info("User navigated to Cash And Carry payment page successfully");

            // Test Step - 11
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.VerifyCreditCardTabIsSelected(), "true", "By defaultCredit card tab section is not displayed");
            if (cashandcarrypayment.verify_Use_Store_Credit_Label_IsDisplayed()) {
                cashandcarrypayment.Click_StoreCredit_CheckBox();
            }

            // Test Step - 12
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOnGiftCardPaymentTab();
            softassert.assertTrue(cashandcarrypayment.VerifyIsSwipeGiftCardButtonDisplayed(), "click here swipe gift card button is not displayed");

            // Test Step - 13
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCashTab();
            softassert.assertTrue(cashandcarrypayment.VerifyGivenAmountOnCashTabTextboxIsDisplayed(), "Given amount on cash tab textbox is not displayed");

            // Test Step - 14
            delayWithGivenTime(1000);
            PressF8();
            softassert.assertTrue(cashandcarrypayment.VerifyIsSwipeGiftCardButtonDisplayed(), "click here swipe gift card button is not displayed");

            // Test Step - 15
            cashandcarrypayment.ClickSwipeGiftCardButton();
            // =================This step will be done by manually==============

            // Test Step - 16
            cashandcarrypayment.EnterGiftCardNumberOnGiftCardPaymentTab("889900889900");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.getEnteredGiftCardAmountOnGiftCardPaymentTab(), "889900889900");

            // Test Step - 16
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustNameOnGiftCardPaymentTab(), "Abish David");

            // Test Step - 17
            //   softassert.assertEquals(cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab().contains(cashandcarrypayment.getTableDisplayedBalanceAmt()), true);

            // Test Step - 18
            //  softassert.assertEquals(cashandcarrypayment.VerifyGiftCardCurrentBalance().contains("9"), true);

            // Test Step - 19
            logger_Util.payment_API_Logger("Gift Card Payment - Process Payment API LOG", () -> {
                cashandcarrypayment.ClickProcessPaymentBtn();
            });
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 19: Order payment done successfully toast message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 19: Order payment done successfully toast message is not displayed");

            delayWithGivenTime(1000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                invoice = cashandcarrypayment.GetInvoiceNumber();
                cashandcarrypayment.GetTenderPrice();
            }

            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();

            // Test Step - 20
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();

            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 20 - Dashboard order page is not displayed");
            logger.info("User verify that the order page is navigated to dashboard order page");
            delayWithGivenTime(1000);

            // Test Step - 21
            dashboardorder.EnterGlobalSearch(invoice);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Validate_GiftCard_MOP_DisplayedOnOrderPage(), "Test Step - 21 - Gift Card mode of pay is not displayed on orders page");
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
