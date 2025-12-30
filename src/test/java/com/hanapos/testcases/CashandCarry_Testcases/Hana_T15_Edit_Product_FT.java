package com.hanapos.testcases.CashandCarry_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.*;
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

public class Hana_T15_Edit_Product_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private DashboardOrderPage dashboardorder;
    public static final String dataSheetName = "Hana_T15";
    public static LoggerUtil logger_Util;
    CustomSoftAssert softassert = new CustomSoftAssert();
    String invoicenumber;
    String custname;
    String custid;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    //retryAnalyzer= com.hanapos.utilities.RetryTest.class,
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Description("Hana_T15 :- Edit the Added Product functionality in Cash and Carry")
    @Epic("Cash and Carry Module")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T15_Edit_Product_Test(String searchandselectitemcode, String occasion, String qtyrow1, String customername) {

        // Test Step - 1
        logger.info("**** Starting Hana_T15_Edit_Product_CashAndCarryTest  ****");
        logger.debug("capturing application debug logs....");
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");
            logger.info("User on the hana pos login page");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            logger.info("Entering valid username and password..");
            lp.EnterUserName(prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("bestpass"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            delayWithGivenTime(2000);
            dashboard = new HanaDashBoardPage();

            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Hana dashboard Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            delayWithGivenTime(2000);
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            Assert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("bestshopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            //Test Step - 5
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("cashandcarryclerkname"), "Test Step - 5 : Clerk name is not matched with selected clerk name");

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6 : Employee name is not matched with selected employee name");

            // Test Step - 7
            softassert.assertTrue(cashandcarry.IsPayButtonDisabled(), "Test Step - 7 - Pay button is not disabled");

            // Test Step - 8
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));

            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 8 - Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 8 - Item quantity is not matched with search and selected item code");

            if (cashandcarry.ItemPriceValueIsExist() == "299") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 8 - Item price is not matched with search and selected item code");
            } else if (cashandcarry.ItemPriceValueIsExist() == "309") {
                softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "309", "Test Step - 8 - Item price is not matched with search and selected item code");
            }
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 8 - Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 8 - Item discount percentage is not matched with search and selected item code");

            // Test Step - 9
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem());
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Step - 9 - Added item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Step - 9 - Added item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 9 - Added item quantity is not matched with search and selected item code");

            if (cashandcarry.GetAddedItemExtPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Step - 9 - Added item extended price is not matched with search and selected item code");
            } else if (cashandcarry.GetAddedItemExtPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$309.00", "Test Step - 9 - Added item extended price is not matched with search and selected item code");
            }

            if (cashandcarry.GetAddedItemPrice() == "$299.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Step - 9 - Added item price is not matched with search and selected item code");
            } else if (cashandcarry.GetAddedItemPrice() == "$309.00") {
                softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$309.00", "Test Step - 9 - Added item price is not matched with search and selected item code");
            }

            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 9 - Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 9 - Added item discount percentage is not matched with search and selected item code");

            //Delete the added item on Cash and Carry page
            cashandcarry.ClickRow1DeleteIcon();

            // Test Step - 10
            delayWithGivenTime(2000);
            logger.info("User verify add the title product to the Cash and Carry page is displayed..");
            cashandcarry.ClickParticularProdTitle();
            softassert.assertEquals(cashandcarry.getAddedItemCode(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 10 - Added item code is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.getDisplayedItemDescription(), prop.getProperty("cashandcarry_product_description"), "Test Step - 10 - Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getDisplayedItemQty(), "1", "Test Step - 10 - Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getDisplayedItemPrice(), "$40.00", "Test Step - 10 - Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getDisplayedItemExtPrice(), "$40.00", "Test Step - 10 - Added item extended price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 10 - Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 10 - Added item discount percentage is not matched with search and selected item code");

            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), prop.getProperty("product_taxtype"), "Test Step - 10 - Tax type is not matched with selected tax type");

            cashandcarry.SelectOccasion(occasion);
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), occasion, "Test Step - 10 - Occasion is not matched with selected occasion");

            cashandcarry.Enter_Customer_Name_On_CashAndCarryPage(prop.getProperty("custfullname"));
            delayWithGivenTime(2000);
            custname = cashandcarry.getDisplayedCustomerNameOnCCPage();
            custid = cashandcarry.get_Displayed_CustomerId();

            // Test Step - 11
            cashandcarry.ClickPayButton();
            cashandcarrypayment = new CashAndCarryPaymentPage();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step:11 - Cash And Carry payment page is not displayed");
            logger.info("User is on Cash And Carry payment page");

            // Test Step - 12
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickBackButtonOnTopRightCorner();
            logger.info("User click on Back button on top right corner");
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step:12 - Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 13
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getAddedItemCode(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step: 13 - Item code is not matched");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), prop.getProperty("cashandcarry_product_description"), "Test Step: 13 - Item description is not matched");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step: 13 - Item quantity is not matched");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$40.00", "Test Step: 13 - Item extended price is not matched");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$40.00", "Test Step: 13 - Item price is not matched");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step: 13 - Item discount amount is not matched");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step: 13 - Item discount percentage is not matched");

            // Test Step - 14
            cashandcarry.ClickAddedRow1EditButton();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.IsSavedIconDisplayedOnAddedItemTableRow1(), "Test Step:14 - Saved Icon is not displayed");
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.IsCancelIconDisplayedOnAddedItemTableRow1(), "Test Step:14 - Cancel Icon is not displayed");

            //Test Step - 15
            cashandcarry.EditQuantityOnTableRow1(qtyrow1);
            softassert.assertEquals(cashandcarry.getQuantity_on_row1(), qtyrow1, "Test Step:15 - Edited Quantity is not matched");

            //Test Step - 16
            cashandcarry.ClickTheCancelIconOnAddedItemTableRow1();

            //Test Step - 17
            delayWithGivenTime(2000);
            cashandcarry.ClickAddedRow1EditButton();
            cashandcarry.EditQuantityOnTableRow1(qtyrow1);
            softassert.assertEquals(cashandcarry.getQuantity_on_row1(), qtyrow1, "Test Step:17 - Edited Quantity is not matched");
            softassert.assertTrue(cashandcarry.IsSavedIconDisplayedOnAddedItemTableRow1(), "Test Step:17 - Saved Icon is not displayed");
            softassert.assertTrue(cashandcarry.IsCancelIconDisplayedOnAddedItemTableRow1(), "Test Step:17 - Cancel Icon is not displayed");
            cashandcarry.ClickTheSavedIconOnAddedItemTableRow1();

            // Test Step - 18
            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            delayWithGivenTime(2000);

            if (cashandcarry.get_selected_tax_type().equals(prop.getProperty("product_taxtype"))) {
                delayWithGivenTime(2000);
                softassert.assertEquals(cashandcarry.ValidateGrandTotalDefaultValue(), "120.00", "Test Step:18 - Grand total to pay is not matched");
                // Test Step - 19
                softassert.assertEquals(cashandcarry.ValidatePayButtonDisplayedPrice(), "120.00", "Test Step:19 - Grand total to pay is not matched");
                // Test Step - 20
                cashandcarry.SelectOccasion(occasion);
                softassert.assertEquals(cashandcarry.get_selected_occasion_value(), occasion, "Test Step - 20 - Occasion is not matched with selected occasion");
                cashandcarry.ClickPayButton();

                logger.info("User fillout the customer,tax type & occasion details and click on Pay button");
                cashandcarrypayment = new CashAndCarryPaymentPage();
                softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step:20 - Cash And Carry payment page is not displayed");
                logger.info("User is on Cash And Carry payment page");

                // Test Step - 21
                cashandcarrypayment.ClickCashTab();
                logger.info("User select the payment type as cash tab");
                delayWithGivenTime(1000);
                softassert.assertEquals(cashandcarrypayment.getGrandTotaltoPay(), "120.00", "Test Step:21 - Grand total to pay is not matched");
            }

            // Test Step - 22
            cashandcarrypayment.EnterGivenAmount();
            logger.info("User enter the amount in given amount field");

            cashandcarrypayment.click_Cancel_Selected_CustomerDetails();
            delayWithGivenTime(1000);
            cashandcarrypayment.SearchAndSelectCustomer(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));
            logger.info("User search and select the customer ");
            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustomerNameOnCCPage(), "Abish David", "Test Step - 22 - : Customer name is not displayed on Cash And Carry payment page when search & select the customer on this field");
            softassert.assertEquals(cashandcarrypayment.getCustomerIdDisplayed(), custid, "Test Step - 22 - : Customer ID is not displayed on Cash And Carry payment page when search & select the customer on this field");

            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step:22 - Success Toast message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step:22 - Order confirmation message is not matched");

            delayWithGivenTime(1000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                invoicenumber = cashandcarrypayment.GetInvoiceNumber();
                cashandcarrypayment.GetTenderPrice();
            }


            // Test Step - 23
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();
            logger.info("User select the payment type as cash tab");

            // Test Step - 24
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();

            //Test Step - 25
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step:25 - Dashboard order page is not displayed");
            logger.info("User verify that the order page is navigated to dashboard order page");
            delayWithGivenTime(1000);
            dashboardorder.EnterGlobalSearch(invoicenumber);
            delayWithGivenTime(3000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoicenumber), "Test Step - 25 - Respective Invoice number is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoicenumber), "Delivered", "Test Step - 25 - Respective Invoice status is not displayed on all orders page");
            delayWithGivenTime(2000);


            // Test Step - 26
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.GetInvoiceAmount_Walkin_pickup_Cash_OnOrderPage("Walkin Sales", "Pick Up", "Cash"), "$120.00", "Test Step:26 - Invoice amount on order page is not matched");

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

