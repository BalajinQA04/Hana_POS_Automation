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

public class Hana_T14_Add_Product_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private DashboardOrderPage dashboardorder;
    public static final String dataSheetName = "Hana_T14";
    String custname;
    String custid;
    String invoicenumber;
    String balanceAmount;
    CustomSoftAssert softassert = new CustomSoftAssert();

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Epic("Cash and Carry Module")
    @Feature("Cash and Carry Page")
    @Story("Add Product Functionality")
    @Link(name = "Zephyr Scale Test Case", url = "https://hana-pos.atlassian.net/projects/HANA?selectedItem=com.atlassian.plugins.atlassian-connect-plugin:com.kanoah.test-manager__main-project-page#!/v2/testCase/HANA-T14")
    @Description("Hana_T14 :- Add Product on Cash And Carry Page Functionality with existing customer")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"}, dataProvider = "fetch_Excel_Data", threadPoolSize = 2)
    public void Validate_Hana_T14_Add_Product_Test(String searchandselectitemcode) {
        // Test Step - 1
        logger.info("**** Starting Hana_T14_Add_Product_CashAndCarryTest  ****");
        logger.debug("capturing application debug logs....");
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("bestpass"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            delayWithGivenTime(2000);

            dashboard = new HanaDashBoardPage();

            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page sucess..");

            // Test Step - 3
            delayWithGivenTime(2000);
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            logger.info("User hover the mouse on New order and click on Cash and Carry..");
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 : Cash And Carry page is not displayed");
            logger.info("User verify the Cash and Carry page is displayed..");

            // Test Step - 4
            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));
            logger.info("User select the shop name");
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("bestshopname"), "Test Step - 4 : Shop name is not matched with selected shop name");

            //Test Step - 5
            //  delayWithGivenTime(2000);
            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            logger.info("User select the clerk name");
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("cashandcarryclerkname"), "Test Step - 5 : Clerk name is not matched with selected clerk name");

            // Test Step - 6
            cashandcarry.SelectEmployeeName(prop.getProperty("employeename"));
            //  delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.get_selected_employeename(), prop.getProperty("employeename"), "Test Step - 6 : Employee name is not matched with selected employee name");

            // Test Step - 7
            softassert.assertTrue(cashandcarry.IsPayButtonDisabled(), "Test Step - 7 : Pay button is not disabled");

            // Test Step - 8
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, prop.getProperty("product_description1"));
            softassert.assertEquals(cashandcarry.ItemCodeValueIsExists(), searchandselectitemcode, "Test Step - 8 : Item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), "Red Rose Deluxe", "Test Step - 8 : Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 8 : Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 8 : Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 8 : Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 8 : Item discount percentage is not matched with search and selected item code");

            // Test Step - 9
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 9 : Added item is not displayed on grid table row 1");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Step - 9 : Added item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Step - 9 : Added item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 9 : Added item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Step - 9 : Added item extended price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Step - 9 : Added item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 9 : Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 9 : Added item discount percentage is not matched with search and selected item code");

            cashandcarry.ClickRow1DeleteIcon();

            //Test Step - 10
            // delayWithGivenTime(2000);
            logger.info("User verify add the title product to the Cash and Carry page is displayed..");
            softassert.assertEquals(cashandcarry.getDisplayedProductTitletooltip().contains("BalloonsYY-"), true, "Test Step - 10 : Mouse hover on Product title tooltip is not displayed");

            // Test Step - 11
            // delayWithGivenTime(2000);
            logger.info("User verify add the title product to the Cash and Carry page is displayed..");
            cashandcarry.ClickParticularProdTitle();
            softassert.assertEquals(cashandcarry.getAddedItemCode(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 11 - Added item code is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.getDisplayedItemDescription(), prop.getProperty("cashandcarry_product_description"), "Test Step - 11 - Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getDisplayedItemQty(), "1", "Test Step - 11 - Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getDisplayedItemPrice(), "$40.00", "Test Step - 11 - Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getDisplayedItemExtPrice(), "$40.00", "Test Step - 11 - Added item extended price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 11 - Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 11 - Added item discount percentage is not matched with search and selected item code");

            //  delayWithGivenTime(2000);
            cashandcarry.SelectTaxType(prop.getProperty("product_taxtype"));
            //  delayWithGivenTime(2000);
            cashandcarry.SelectOccasion(prop.getProperty("occasion"));

            //  delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), "Tax Exemption", "Test Step - 11 : Selected tax type is not displayed");
            //   delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 11 : Selected occasion is not displayed");
            //  delayWithGivenTime(2000);

            cashandcarry.Enter_Customer_Name_On_CashAndCarryPage(prop.getProperty("custfullname"));
            custname = cashandcarry.getDisplayedCustomerNameOnCCPage();
            custid = cashandcarry.get_Displayed_CustomerId();

            // Test Step - 12
            cashandcarry.ClickPayButton();
            // delayWithGivenTime(2000);

            logger.info("User fillout the customer,tax type & occasion details and click on Pay button");
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 12 : Cash And Carry payment page is not displayed");
            logger.info("User is on Cash And Carry payment page");

            // Test Step - 13
            cashandcarrypayment.ClickCashTab();
            logger.info("User select the payment type as cash tab");
            cashandcarrypayment.EnterGivenAmount();
            logger.info("User enter the amount in given amount field");

            softassert.assertEquals(cashandcarrypayment.getDisplayedCustomerNameOnCCPage(), custname, "Test Step - 13 - : Customer name is not displayed on Cash And Carry payment page when search & select the customer on this field");
            softassert.assertEquals(cashandcarrypayment.getCustomerIdDisplayed(), custid, "Test Step - 13 - : Customer ID is not displayed on Cash And Carry payment page when search & select the customer on this field");

            cashandcarrypayment.click_CrossIcon_On_AddCustomerSection_CashAndCarry_PaymentPage();

            cashandcarrypayment.SearchAndSelectCustomer(prop.getProperty("cust_firstName"), prop.getProperty("custfullname"));

            softassert.assertEquals(cashandcarrypayment.getDisplayedCustomerNameOnCCPage(), custname, "Test Step - 13 - : Search and Selected Customer name on Cash And Carry payment page is not displayed");
            softassert.assertEquals(cashandcarrypayment.getCustomerIdDisplayed(), custid, "Test Step - 13 - : Customer ID is not displayed on Cash And Carry payment page when search & select the customer on this field");

            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 13 : Success toast message is not displayed");
            logger.info("User verified the order payment done successfully");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 13 : Order confirmation message is not displayed");

            //   delayWithGivenTime(1000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                invoicenumber = cashandcarrypayment.GetInvoiceNumber();
                balanceAmount = cashandcarrypayment.GetTenderPrice();
            }
            invoicenumber = cashandcarrypayment.GetInvoiceNumber();

            logger.info("User click the cancel button on webclientprint window popup");
            // delayWithGivenTime(2000);

            // Test Step - 14
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();
            logger.info("User select the payment type as cash tab");

            // Test Step - 15
            // delayWithGivenTime(1000);
            dashboard.ClickOrder();
            //  delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            // ThreadWait(1000);

            //Test Step - 16
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 16 - Dashboard order page is not displayed");
            //  delayWithGivenTime(2000);

            dashboardorder.EnterGlobalSearch(invoicenumber);
            // delayWithGivenTime(3000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoicenumber), "Test Step - 15 - Respective Invoice number is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoicenumber), "Delivered", "Test Step - 15 - Respective Invoice status is not displayed on all orders page");
            // delayWithGivenTime(2000);


//            softassert.assertTrue(dashboardorder.ValidateInvoiceNumber("Walkin Sales", "Pick Up", "Cash"), "Test Step - 16 - Invoice number is not displayed on order page");
//            softassert.assertEquals(dashboardorder.Validate_OrderStatus_on_OrderPage("Walkin Sales", "Pick Up", "Cash"), "Delivered", "Test Step - 16 - Order status is not displayed as delivered for cash and carry order");
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