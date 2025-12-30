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
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T46_Payments_CreditCard_SwipeCard_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    public static LoggerUtil logger_Util;

    public static final String dataSheetName = "Hana_T46";
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T46_Payments_CreditCard_SwipeCard_Test(String searchandselectitemcode, String customername, String occasion) throws InterruptedException, IOException {
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
            Assert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Cash And Carry page is not displayed");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("shopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("shopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("clerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("clerkname"), "Test Step - 5 : Clerk name is not matched with selected clerk name");

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6 : Employee name is not matched with selected employee name");

            // Test Step - 7
            softassert.assertTrue(cashandcarry.IsPayButtonDisabled(), "Pay button is not disabled");

            // Test Step - 8
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 8: Item description is not displayed");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Steps - 7 - Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Steps - 7 - Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Steps - 7 - Item discount percentage is not matched with search and selected item code");

            // Test Step - 9
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 9: Added Item code is not displayed on product table grid");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Steps - 8 - Added item code is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Steps - 8 - Added item description is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Steps - 8 - Added item quantity is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Steps - 8 - Added item extended price is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Steps - 8 - Added item price is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Steps - 8 - Added item discount amount is not matched displayed on table grid row1");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Steps - 8 - Added item discount percentage is not matched displayed on table grid row1");

            // Test Step - 09
            delayWithGivenTime(2000);
            cashandcarry.ClickParticularProdTitle();
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), "BalloonsYY", "Test Step - 10 - Added product tile is not displayed on row 2 table grid");
            softassert.assertEquals(cashandcarry.getAddedItemDescriptionRow2(), "Balloons Small", "Test Step - 10 - Added item description in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemQuantityRow2(), "1", "Test Step - 10 - Added item quantity in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemPriceRow2(), "$40.00", "Test Step - 10 - Added item extended price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemExtentPriceRow2(), "$40.00", "Test Step - 10 - Added item price in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountAmountRow2(), "$ 0.00", "Test Step - 10 - Added item discount amount in row 2 is not displayed on the page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountPercentageRow2(), "0.00", "Test Step - 10 - Added item discount percentage in row 2 is not displayed on the page");

            // Test Step - 11
            cashandcarry.EnterCustomerName(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            softassert.assertEquals(cashandcarry.getDisplayedCustomerName(), prop.getProperty("custfullname"), "Known Issue Test Step - 9 - Customer name is not matched with entered customer name");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(prop.getProperty("occasion"));
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");
            delayWithGivenTime(1000);

            cashandcarry.ClickPayButton();
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Cash And Carry payment page is not displayed");

            // Test Step - 11
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.VerifyCreditCardTabIsSelected(), "true", "By defaultCredit card tab section is not displayed");

            // Test Step - 12
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCreditCardTab();
            cashandcarrypayment.ClickProcessPaymentBtn();

            // Test Step - 13 - need to verify by manually

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
