package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

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

public class Hana_T79_Add_Product_With_Existing_Customer_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private DashboardOrderPage dashboardorder;
    public static final String dataSheetName = "Hana_T79";
    public static ExecutorService executorService;
    private static final int THREAD_POOL_SIZE = 2;
    public static LoggerUtil logger_Util;

    // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
    CustomSoftAssert softassert = new CustomSoftAssert();

    String invoice;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }


    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T79_Add_Product_With_Existing_Customer_Test(String searchandselectitemcode, String customershortname, String taxtype, String occasion) throws InterruptedException, IOException {
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            logger.info("Entering valid username and password..");
            lp.EnterUserName(prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            lp.ClickLoginButton();
            logger.info("Clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.SelectShopNameDropDown(prop.getProperty("bestshopname"));
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            Assert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));
            logger.info("User select the shop name");

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            logger.info("User select the clerk name");

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            logger.info("User select the employee name");

            // Test Step - 7
            softassert.assertTrue(cashandcarry.IsPayButtonDisabled(), "Pay button is not disabled");

            // Test Step - 8
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));

            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1");

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0");

            // Test Step - 9
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

            //Delete the added item on Cash and Carry page
            cashandcarry.ClickRow1DeleteIcon();

            //Test Step - 10
            delayWithGivenTime(2000);
            logger.info("User verify add the title product to the Cash and Carry page is displayed..");
            softassert.assertEquals(cashandcarry.getDisplayedProductTitletooltip().contains(prop.getProperty("cashandcarry_product_itemcode")), true, "Test Step - 10 product tile is not displayed on product table grid");

            // Test Step - 11
            delayWithGivenTime(2000);
            logger.info("User verify add the title product to the Cash and Carry page is displayed..");
            cashandcarry.ClickParticularProdTitle();
            softassert.assertEquals(cashandcarry.getAddedItemCode(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 11: Added item code is not displayed");
            cashandcarry.EnterCustomerName(customershortname, prop.getProperty("custfullname"));
            logger.info("User enter the customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");
            delayWithGivenTime(1000);

            // Test Step - 12
            cashandcarry.ClickPayButton();
            delayWithGivenTime(2000);
            logger.info("User fillout the customer,tax type & occasion details and click on Pay button");
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Cash And Carry payment page is not displayed");
            logger.info("User is on Cash And Carry payment page");

            // Test Step - 13
            cashandcarrypayment.ClickCashTab();
            logger.info("User select the payment type as cash tab");
            cashandcarrypayment.EnterGivenAmount();
            logger.info("User enter the amount in given amount field");

            logger.info("User search and select the customer ");
            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 13 - success toast message is not displayed");
            logger.info("User verified the order payment done successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 13: Order payment done successfully toast message is not displayed");
            delayWithGivenTime(1000);

            if (cashandcarrypayment.getConfirmationPopup()) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                invoice = cashandcarrypayment.GetInvoiceNumber();
                System.out.println("Order invoice number is :" + cashandcarrypayment.GetInvoiceNumber());
                cashandcarrypayment.GetTenderPrice();
                System.out.println("The remaining amount given to customer is :" + cashandcarrypayment.GetTenderPrice());
            }

            logger.info("User click the cancel button on webclientprint window popup");
            delayWithGivenTime(1000);

            // Test Step - 14
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();
            logger.info("User select the payment type as cash tab");


            // Test Step - 15
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();

            //Test Step - 16
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 16 - Dashboard order page is not displayed");
            logger.info("User verify that the order page is navigated to dashboard order page");

            delayWithGivenTime(1000);
            dashboardorder.EnterGlobalSearch(invoice);

            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice), "Test Step - 16 - Invoice number is not displayed on hana dashboard order page");

            // Test Step - 17
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.GetSenderorCustomerOnOrderPage().contains("Hana_Sisterchicks | Abish David"), true, "Test Step - 17 Sender or customer name is not displayed");
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
