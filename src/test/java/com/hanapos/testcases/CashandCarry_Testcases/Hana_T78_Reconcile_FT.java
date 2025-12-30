package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T78_Reconcile_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    public static final String dataSheetName = "Hana_T78";
    public static LoggerUtil logger_Util;

    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T78_Reconcile_Test(String shopname,
                                                 String clerkname,
                                                 String employeename,
                                                 String itemcode,
                                                 String itemdesc,
                                                 String prodtile,
                                                 String customername,
                                                 String occasion,
                                                 String twentydollar,
                                                 String onedollar,
                                                 String twodollar,
                                                 String fivedollar,
                                                 String fiftydollar,
                                                 String hunderddollar,
                                                 String pennies) {
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
            logger.info("User entered username as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(shopname);
            logger.info("User selected the shop name as " + shopname + "in dashboard page");
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            cashandcarry.SelectShopName(shopname);
            logger.info("User select the shop name as " + shopname + "in cash and carry page");

            //Test Step - 5
            cashandcarry.SelectClerkName(clerkname);
            logger.info("User select the clerk name as " + clerkname);

            // Test Step - 6
            cashandcarry.SelectEmployeeName(employeename);
            logger.info("User select the employee name as " + employeename);

            // Test Step - 7
            softassert.assertTrue(cashandcarry.VerifyPayButtonIsEnabled(), "Test Step:7 - Pay button is not disabled");
            logger.info("User verify the Pay button is disabled");

            // Test Step - 8
            cashandcarry.SearchAndSelectTheItemCode(itemcode, itemdesc);
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Rose Flowers ", "Item Description is not matched with selected item");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Item quantity is not matched with selected item");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "80", "Test Step - 8 - Item price is not matched with selected item");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Discount amount is not matched with selected item");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Discount percentage is not matched with selected item");

            // Test Step - 9
            delayWithGivenTime(2000);
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem());
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "RSS", "Test Step - 9 - Item code is not matched");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Rose Flowers", "Test Step - 9 - Item description is not matched");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 9 - Item quantity is not matched");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$80.00", "Test Step - 9 - Item ext price is not matched");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$80.00", "Test Step - 9 - Item price is not matched");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 9 - Item discount amount is not matched");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 9 - Item discount percentage is not matched");

            // Test Step - 10

            // Test Step - 11
            delayWithGivenTime(2000);
            cashandcarry.Select_Displayed_ProdTitle();
            delayWithGivenTime(2000);

            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), "RSS", "Test Step - 11 - Tooltip message displayed is not matched");

            // Test Step - 12
            cashandcarry.Enter_CustomerName(customername);
            logger.info("User enter the customer name as " + customername);
            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            cashandcarry.SelectOccasion(occasion);
            logger.info("User select the occasion as " + occasion);

            delayWithGivenTime(2000);
            cashandcarry.ClickPayButton();
            logger.info("User click on Pay button");
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step:12 - Cash And Carry payment page is not displayed");
            logger.info("User navigated to Cash And Carry payment page successfully");

            // Test Step - 13
            cashandcarrypayment.ClickCashTab();
            logger.info("User select the payment type as cash tab");
            cashandcarrypayment.EnterGivenAmount();
            logger.info("User enter the amount in given amount field");
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step:13 - Success toast message is not displayed");
            logger.info("User verified the order payment done successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step:13 - Order payment done successfully toast message text is not displayed");

            delayWithGivenTime(1000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                cashandcarrypayment.GetInvoiceNumber();
                cashandcarrypayment.GetTenderPrice();
            }

            // Test Step - 14
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();
            delayWithGivenTime(1000);

            // Test Step - 15
            cashandcarry.ClickOnReconcileBtn();
            softassert.assertTrue(cashandcarry.VerifyReconcilePopupDisplayed(), "Test Step:15 - Reconcile popup is not displayed");

            // Test Step - 16
            delayWithGivenTime(1000);
            cashandcarry.SelectShopReconcilePopup(shopname);
            softassert.assertEquals(cashandcarry.getSelectedShopNameonReconcilePopup(), shopname, "Test Step:16 - Selected Shop name is not displayed");
            delayWithGivenTime(1000);
            cashandcarry.Select_CashRegistryIdonReconcilePopup(clerkname);
            softassert.assertEquals(cashandcarry.getSelectedCashRegistryonReconcilePopup(), clerkname, "Test Step:16 - Selected Cash Registry is not displayed");
            delayWithGivenTime(1000);

            //  softassert.assertTrue(cashandcarry.verify_Last_Reconciliation_Date_IsDisplayed(), "Test Step:16 - Last Reconciliation date is not displayed");
            softassert.assertTrue(cashandcarry.VerifyOpenBalanceOnReconcilePopup(), "Test Step:16 - Open Balance is not displayed");
            softassert.assertTrue(cashandcarry.VerifyExpectedBalance(), "Test Step:16 - Expected balance is not displayed");
            softassert.assertTrue(cashandcarry.VerifyActualBalance(), "Test Step:16 - Actual balance is not displayed");
            softassert.assertTrue(cashandcarry.VerifyCashRegisterSalesReview_dateTime(), "Test Step:16  Date time in cash register sales review is not displayed");
            softassert.assertTrue(cashandcarry.VerifyDiffernce(), "Test Step:16 Difference is not displayed");
            softassert.assertTrue(cashandcarry.VerifyCashRegisterSalesReview_Cash(), "Test Step:16 Cash is not displayed");
            softassert.assertTrue(cashandcarry.VerifyCashRegisterSalesReview_AfterTax(), "Test Step:16 After Tax is not displayed");
            softassert.assertTrue(cashandcarry.VerifyCashRegisterSalesReview_TotalSales(), "Test Step:16 Total sales is not displayed");
            softassert.assertTrue(cashandcarry.VerifyCashRegisterSalesReview_StartCash(), "Test Step:16 Start cash is not displayed");

            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getCashInDrawer(), cashandcarry.getCashInDrawerCalculation(), "Test Step:16 Cash in drawer is not matched");

            // Test Step - 17
            cashandcarry.EnterTwentyDollarDenomination(twentydollar);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getEnteredTwentyDollarDenomination(), twentydollar, "Entered 20 dollar denomination is not displayed");

            // Test Step - 18
            delayWithGivenTime(2000);
            cashandcarry.ClickReconcileBtnInPopup();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.is_Verify_Password_Popup_Displayed_For_Reconcile(), "Test Step:18 - Reconcile verify Password popup is not displayed");
            cashandcarry.enter_Password_For_Reconcile_Verify_Password_Popup("1234");
            delayWithGivenTime(1000);
            cashandcarry.click_Verify_Button_For_Reconcile_Verify_Password_Popup();

            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getActualBalance(), cashandcarry.Expected_Actual_Balance(), "Test Step:18 Actual balance is not matched");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getActualBalanceDifference(), cashandcarry.ExpectedBalanceDifference(), "Test Step:18 Difference value is not matched");

            // Test Step - 19
            delayWithGivenTime(2000);
            cashandcarry.EnterOneDollarDenomination(onedollar);
            cashandcarry.EnterTwoDollarDenomination(twodollar);
            delayWithGivenTime(2000);
            cashandcarry.EnterfiveDollarDenomination(fivedollar);
            cashandcarry.EnterFiftyDollarDenomination(fiftydollar);
            delayWithGivenTime(2000);
            cashandcarry.EnterOneHundredDollarDenomination(hunderddollar);
            cashandcarry.EnterPenniesDenomination(pennies);
            delayWithGivenTime(2000);
            cashandcarry.ClickReconcileBtnInPopup();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.is_Verify_Password_Popup_Displayed_For_Reconcile(), "Test Step:18 - Reconcile verify Password popup is not displayed");
            cashandcarry.enter_Password_For_Reconcile_Verify_Password_Popup("1234");
            delayWithGivenTime(1000);
            cashandcarry.click_Verify_Button_For_Reconcile_Verify_Password_Popup();

            softassert.assertEquals(cashandcarry.getActualBalance(), cashandcarry.Expected_Actual_Balance(), "Test Step:19 Actual balance is not matched");
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
