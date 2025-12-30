package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
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

public class Hana_T69_Add_Product_Without_Customer_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private DashboardOrderPage dashboardorder;
    public static final String dataSheetName = "Hana_T69";
    public static LoggerUtil logger_Util;
    // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
    CustomSoftAssert softassert = new CustomSoftAssert();

    public static ExecutorService executorService;
    private static final int THREAD_POOL_SIZE = 2;
    String invoice;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }


    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T69_Add_Product_Without_Customer_Test(String shopname, String clerkname, String employeename, String itemcode, String itemdesc, String prodtile, String occasion) throws InterruptedException, IOException {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered username as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button..");
            delayWithGivenTime(2000);
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(prop.getProperty("cashandcarryshopname"));
            logger.info("User selected the shop name as " + prop.getProperty("shopname") + "in dashboard page");
            delayWithGivenTime(2000);
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("shopname"));
            logger.info("User select the shop name as " + prop.getProperty("shopname") + "in cash and carry page");

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("clerkname"));

            // Test Step - 6
            cashandcarry.SelectEmployeeName(employeename);
            logger.info("User select the employee name as " + employeename);

            // Test Step - 7
            softassert.assertTrue(cashandcarry.VerifyPayButtonIsEnabled(), "Test Step:7 - Pay button is not disabled");
            logger.info("User verify the Pay button is disabled");

            // Test Step - 8
            cashandcarry.SearchAndSelectTheItemCode(itemcode, itemdesc);
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Test Automation Item Deluxe", "Item Description is not matched with selected item");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Item quantity is not matched with selected item");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "85", "Test Step - 8 - Item price is not matched with selected item");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Discount amount is not matched with selected item");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Discount percentage is not matched with selected item");

            // Test Step - 9
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem());
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "TAI", "Test Step - 9 - Item code is not matched with selected item");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Test Automation Item Deluxe", "Test Step - 9 - Item description is not matched with selected item");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 9 - Item quantity is not matched with selected item");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$85.00", "Test Step - 9 - Item ext price is not matched with selected item");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$85.00", "Test Step - 9 - Item price is not matched with selected item");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 9 - Item discount amount is not matched with selected item");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 9 - Item discount percentage is not matched with selected item");

            // Test Step - 10
            //	softassert.assertEquals(cashandcarry.Verify_ToolTip_OnLeftSideTileProduct(), "Tile Product-Test Automation Tile Product Small-$220.00","Test Step - 10 - Tooltip is not matched");

            // Test Step - 11

            // Test Step - 12
            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");
            delayWithGivenTime(1000);

            cashandcarry.ClickPayButton();
            logger.info("User click on Pay button");
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Cash And Carry payment page is not displayed");
            logger.info("User navigated to Cash And Carry payment page successfully");

            // Test Step - 13
            cashandcarrypayment.ClickCashTab();
            logger.info("User select the payment type as cash tab");
            cashandcarrypayment.EnterGivenAmount();
            logger.info("User enter the amount in given amount field");
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg());
            logger.info("User verified the order payment done successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully");

            delayWithGivenTime(1000);
            if (cashandcarrypayment.getConfirmationPopup() == true) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                invoice = cashandcarrypayment.GetInvoiceNumber();
                System.out.println("Confirmation popup Order invoice number is :" + cashandcarrypayment.GetInvoiceNumber());
                cashandcarrypayment.GetTenderPrice();
                System.out.println("The remaining amount given to customer is :" + cashandcarrypayment.GetTenderPrice());
            }

            //	RobotDismissAlert();
            logger.info("User click the cancel button on webclientprint window popup");

            // Test Step - 14
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();

            // Test Step - 15
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"));
            logger.info("User verify that the order page is navigated to dashboard order page");

            // Test Step - 16
            delayWithGivenTime(1000);
            dashboardorder.EnterGlobalSearch(invoice);//dashboardorder.getInvoiceNumber_Walkin_pickup_Cash_OnOrderPage()

            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice), "Test Step - 16 - Invoice number is not displayed on hana dashboard order page");

            // Test Step - 17
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.GetSenderorCustomerOnOrderPage().contains("WALKIN | Cash And Carry Sale"), true, "Test Step - 17 - Sender or customer is not displayed on hana dashboard order page for respective invoice");
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
