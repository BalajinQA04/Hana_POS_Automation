package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CashAndCarryPage;
import com.hanapos.pageObjects.CashAndCarryPaymentPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T16_Delete_Product_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    public static final String dataSheetName = "Hana_T16";
    public static LoggerUtil logger_Util;
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Description("Hana_T16 :- Delete the Added Product functionality in Cash and Carry")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"}, dataProvider = "fetch_Excel_Data", threadPoolSize = 2)
    public void Validate_Hana_T16_Delete_Product_Test(String searchandselectitemcode, String occasion) {
        // Test Step - 1
        logger.info("**** Starting Hana_T16_Delete_Product_CashAndCarryTest  ****");
        logger.debug("capturing application debug logs....");
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1: Login page is not displayed");
            logger.info("User on the hana pos login page");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            logger.info("Entering valid username and password..");
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            logger.info("Clicked on Login button..");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2: Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3: Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("cashandcarryshopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("cashandcarryshopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("cashandcarryclerkname"), "Test Step - 5 : Clerk name is not matched with selected clerk name");

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6 : Employee name is not matched with selected employee name");

            // Test Step - 7
            softassert.assertTrue(cashandcarry.IsPayButtonDisabled(), "Test Step - 7 - Pay button is not disabled on cash and carry page");

            // Test Step - 8
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 8 - Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 8 - Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 8 - Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 8 - Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 8 - Item discount percentage is not matched with search and selected item code");

            // Test Step - 9
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem());
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Step - 9 - Added item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Step - 9 - Added item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 9 - Added item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Step - 9 - Added item extended price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Step - 9 - Added item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 9 - Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 9 - Added item discount percentage is not matched with search and selected item code");

            delayWithGivenTime(2000);
            cashandcarry.ClickRow1DeleteIcon();

            // Test Step - 10
            delayWithGivenTime(2000);
            logger.info("User verify add the title product to the Cash and Carry page is displayed..");
            cashandcarry.ClickParticularProdTitle();
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getAddedItemCode(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 10 - Added item code is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.getDisplayedItemDescription(), prop.getProperty("cashandcarry_product_description"), "Test Step - 10 - Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getDisplayedItemQty(), "1", "Test Step - 10 - Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getDisplayedItemPrice(), "$40.00", "Test Step - 10 - Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getDisplayedItemExtPrice(), "$40.00", "Test Step - 10 - Added item extended price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 10 - Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 10 - Added item discount percentage is not matched with search and selected item code");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 10 : Selected tax type is not displayed");

            cashandcarry.SelectOccasion(occasion);
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 10 : Selected occasion is not displayed");

            // Test Step - 11
            cashandcarry.ClickPayButton();

            logger.info("User fillout the customer,tax type & occasion details and click on Pay button");
            cashandcarrypayment = new CashAndCarryPaymentPage();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 11 : Cash And Carry payment page is not displayed");
            logger.info("User is on Cash And Carry payment page");

            // Test Step - 12
            cashandcarrypayment.ClickBackButtonOnTopRightCorner();
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 12 - Cash And Carry page is not displayed");

            logger.info("User verify the Cash and Carry page is displayed..");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 12 - Added item code is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), prop.getProperty("cashandcarry_product_description"), "Test Step - 12 - Added item description is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 12 - Added item quantity is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$40.00", "Test Step - 12 - Added item extended price is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$40.00", "Test Step - 12 - Added item price is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 12 - Added item discount amount is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 12 - Added item discount percentage is not displayed on cash and carry page");

            // Test Step - 13
            // Add one more product - Red Rose Deluxe-309
            delayWithGivenTime(1000);
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            cashandcarry.ClickAddItem();
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), "rrd", "Test Step - 12 - Added item code is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.getAddedItemDescriptionRow2(), "Red Rose Deluxe", "Test Step - 12 - Added item description is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.getAddedItemQuantityRow2(), "1", "Test Step - 12 - Added item quantity is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.getAddedItemExtentPriceRow2(), "$299.00", "Test Step - 12 - Added item extended price is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.getAddedItemPriceRow2(), "$299.00", "Test Step - 12 - Added item price is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountAmountRow2(), "$ 0.00", "Test Step - 12 - Added item discount amount is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountPercentageRow2(), "0.00", "Test Step - 12 - Added item discount percentage is not displayed on cash and carry page");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 13 : Selected tax type is not displayed");

            // Before delete the added item on Cash and Carry page

            if (cashandcarry.ValidatePayButtonDisplayedPrice() == "339.00") {
                softassert.assertEquals(cashandcarry.ValidatePayButtonDisplayedPrice(), "339.00", "Test Step - 13 - Pay button displayed price is not correct as expected");

            } else if (cashandcarry.ValidatePayButtonDisplayedPrice() == "349.00") {
                softassert.assertEquals(cashandcarry.ValidatePayButtonDisplayedPrice(), "349.00", "Test Step - 13 - Pay button displayed price is not correct as expected");
            }

            if (cashandcarry.ValidateGrandTotalDefaultValue() == "339.00") {
                softassert.assertEquals(cashandcarry.ValidateGrandTotalDefaultValue(), "339.00", "Test Step - 13 - Grand total value is not correct as expected");

            } else if (cashandcarry.ValidateGrandTotalDefaultValue() == "349.00") {
                softassert.assertEquals(cashandcarry.ValidateGrandTotalDefaultValue(), "349.00", "Test Step - 13 - Grand total value is not correct as expected");

            }

            delayWithGivenTime(1000);
            cashandcarry.ClickOnDeleteIconAtRow2();
            delayWithGivenTime(1000);

            // Test Step - 14
            // After delete the added item on pay button
            softassert.assertEquals(cashandcarry.ValidatePayButtonDisplayedPrice(), "40.00", "Test Step - 14 - Pay button displayed price is not correct as expected");

            // Test Step - 15
            // After delete the added item on grand total
            softassert.assertEquals(cashandcarry.ValidateGrandTotalDefaultValue(), "40.00", "Test Step - 15 - Grand total value is not correct as expected");

            // Test Step - 16
            cashandcarry.SelectOccasion(occasion);
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 16 : Selected occasion is not displayed");

            cashandcarry.ClickPayButton();
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 16 - Cash And Carry payment page is not displayed");

            //Test Step - 17
            cashandcarrypayment.ClickBackButtonOnTopRightCorner();
            logger.info("User click on Back button on top right corner");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 17 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            //Test Step - 18
            softassert.assertTrue(cashandcarry.IsRow2TableAddedItemDisplayed(), "Test Step - 18 - Added Red Rose Deluxe Item in row 2 table data is not deleted");

            // Test Step - 19
            cashandcarry.ClickOnDeleteIconAtRow1();

            // Test Step - 20
            softassert.assertTrue(cashandcarry.IsRow1TableAddedItemDisplayed(), "Test Step - 20 - Added Item row 1 table data is not deleted");
            delayWithGivenTime(1000);

            softassert.assertTrue(cashandcarry.IsPayButtonDisabled(), "Test Step - 20 - pay button is not disabled");
            softassert.assertEquals(cashandcarry.ValidatePayButtonDisplayedPrice(), "", "Test Step - 20 - Pay button displayed price is not correct as expected");
            softassert.assertEquals(cashandcarry.ValidateGrandTotalDefaultValue(), "0.00", "Test Step - 20 - Grand total value is not correct as expected");
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
