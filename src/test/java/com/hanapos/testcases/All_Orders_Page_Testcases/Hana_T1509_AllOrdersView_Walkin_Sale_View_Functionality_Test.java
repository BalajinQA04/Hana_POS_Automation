package com.hanapos.testcases.All_Orders_Page_Testcases;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T1509_AllOrdersView_Walkin_Sale_View_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private DashboardOrderPage dashboardorder;

    String custname;
    String custid;
    String invoicenumber;
    String balanceAmount;
    CustomSoftAssert softassert = new CustomSoftAssert();
    public static LoggerUtil logger_Util;
    public static final String dataSheetName = "Hana_T1509";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Epic("All Orders Page Module")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1509_AllOrdersView_Walkin_Sale_View_Functionality_Test(
            String username,
            String password,
            String shopname,
            String clerkname,
            String employeename,
            String searchandselectitemcode,
            String itemdescription,
            String taxexemption,
            String occasion,
            String customername,
            String customerfname,
            String ordertype,
            String deliverytype,
            String mop,
            String selectview) {

        // Test Step - 1
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 1: Entered username is not matching with expected username as " +prop.getProperty("bestuname"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("bestpass"), "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("bestpass"));

            lp.ClickLoginButton();
            delayWithGivenTime(2000);
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard Page does not navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            dashboard.CashAndCarryMenuClick();
            cashandcarry = new CashAndCarryPage();
            softassert.assertTrue(cashandcarry.VerifyCashAndCarryPage(), "Test Step - 3 : Cash And Carry page is not displayed");

            cashandcarry.SelectShopName(prop.getProperty("bestshopname"));
            softassert.assertEquals(cashandcarry.get_selected_shopname(), prop.getProperty("bestshopname"), "Test Step - 3 : Shop name is not matched with selected shop name");

            cashandcarry.SelectClerkName(prop.getProperty("clerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("clerkname"), "Test Step - 3 : Clerk name is not matched with selected clerk name");

            cashandcarry.SelectEmployeeName(employeename);
            softassert.assertEquals(cashandcarry.get_selected_employeename(), employeename, "Test Step - 3 : Employee name is not matched with selected employee name");

            // Test Step - 4
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, itemdescription);
            softassert.assertEquals(cashandcarry.ItemCodeValueIsExists(), searchandselectitemcode, "Test Step - 4 : Item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDescriptionValueIsExist(), itemdescription, "Test Step - 4 : Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemQtyValueIsExist(), "1", "Test Step - 4 : Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemPriceValueIsExist(), "299", "Test Step - 4 : Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountAmountIsExist(), "0", "Test Step - 4 : Item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.ItemDiscountPercentageValueIsExist(), "0", "Test Step - 4 : Item discount percentage is not matched with search and selected item code");

            // Test Step - 5
            cashandcarry.ClickAddItem();
            softassert.assertTrue(cashandcarry.VerifyAddedItem(), "Test Step - 5 : Added item is not displayed on grid table row 1");
            softassert.assertEquals(cashandcarry.getAddedItemCode(), "rrd", "Test Step - 5 : Added item code is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDescription(), "Red Rose Deluxe", "Test Step - 5 : Added item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemQty(), "1", "Test Step - 5 : Added item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemExtPrice(), "$299.00", "Test Step - 5 : Added item extended price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemPrice(), "$299.00", "Test Step - 5 : Added item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountAmount(), "$ 0.00", "Test Step - 5 : Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.GetAddedItemDiscountPercentage(), "0.00", "Test Step - 5 : Added item discount percentage is not matched with search and selected item code");

            cashandcarry.ClickParticularProdTitle();
            softassert.assertEquals(cashandcarry.getAddedItemCodeRow2(), prop.getProperty("cashandcarry_product_itemcode"), "Test Step - 5 - Added item code is not displayed on cash and carry page");
            softassert.assertEquals(cashandcarry.getAddedItemDescriptionRow2(), prop.getProperty("cashandcarry_product_description"), "Test Step - 5 - Item description is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getAddedItemQuantityRow2(), "1", "Test Step - 5 - Item quantity is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getAddedItemPriceRow2(), "$40.00", "Test Step - 5 - Item price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getAddedItemExtentPriceRow2(), "$40.00", "Test Step - 5 - Added item extended price is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountAmountRow2(), "$ 0.00", "Test Step - 5 - Added item discount amount is not matched with search and selected item code");
            softassert.assertEquals(cashandcarry.getAddedItemDiscountPercentageRow2(), "0.00", "Test Step - 5 - Added item discount percentage is not matched with search and selected item code");

            // Test Step - 6
            cashandcarry.SelectTaxType(taxexemption);
            softassert.assertEquals(cashandcarry.get_selected_tax_type(), taxexemption, "Test Step - 6 : Selected tax type is not displayed");
            cashandcarry.SelectOccasion(occasion);
            softassert.assertEquals(cashandcarry.get_selected_occasion_value(), "Birthday", "Test Step - 6 : Selected occasion is not displayed");

            cashandcarry.Enter_Customer_Name_On_CashAndCarryPage(customername);
            custname = cashandcarry.getDisplayedCustomerNameOnCCPage();
            custid = cashandcarry.get_Displayed_CustomerId();

            cashandcarry.ClickPayButton();
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 6 : Cash And Carry payment page is not displayed");

            // Test Step - 7
            cashandcarrypayment.ClickCashTab();
            cashandcarrypayment.EnterGivenAmount();
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustomerNameOnCCPage(), custname, "Test Step - 7 - : Customer name is not displayed on Cash And Carry payment page when search & select the customer on this field");
            softassert.assertEquals(cashandcarrypayment.getCustomerIdDisplayed(), custid, "Test Step - 7 - : Customer ID is not displayed on Cash And Carry payment page when search & select the customer on this field");

            cashandcarrypayment.click_CrossIcon_On_AddCustomerSection_CashAndCarry_PaymentPage();
            cashandcarrypayment.SearchAndSelectCustomer(customerfname, customername);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustomerNameOnCCPage(), custname, "Test Step - 7 - : Search and Selected Customer name on Cash And Carry payment page is not displayed");
            softassert.assertEquals(cashandcarrypayment.getCustomerIdDisplayed(), custid, "Test Step - 7 - : Customer ID is not displayed on Cash And Carry payment page when search & select the customer on this field");

            cashandcarrypayment.ClickProcessPaymentBtn();
            softassert.assertTrue(cashandcarrypayment.SuccessToastMsg(), "Test Step - 7 : Success toast message is not displayed");
            softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), "Order payment done successfully", "Test Step - 7 : Order confirmation message is not displayed");

            delayWithGivenTime(1000);
            if (cashandcarrypayment.getConfirmationPopup()) {
                cashandcarrypayment.VerifyOrderConfirmationPopup();
                cashandcarrypayment.GetOrderConfirmationMsgAndInvoiceNo();
                invoicenumber = cashandcarrypayment.GetInvoiceNumber();
                balanceAmount = cashandcarrypayment.GetTenderPrice();
                cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();
            }

            // Test Step - 9
            dashboard.ClickOrder();
            dashboardorder = new DashboardOrderPage();
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 9 - All order page is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 10
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoicenumber), "Test Step - 10 - Respective Invoice number : " + invoicenumber + " is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoicenumber), "Delivered", "Test Step - 10 - Order status is not displayed as delivered for cash and carry order");
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoicenumber), ordertype, "Test Step - 10: Order Type is not properly displayed for cash and carry order");
            softassert.assertEquals(dashboardorder.validate_DeliveryType_On_AllOrdersPage(invoicenumber), "Pick Up", "Test Step - 10 : Delivery Type as Pick Up is not displayed");
            softassert.assertEquals(dashboardorder.validate_Recipient_Info_On_AllOrdersPage(invoicenumber), "", "Test Step - 10: Recipient information is not displayed properly for cash and carry order");

            dashboardorder.Select_views_dropdown_on_all_ordersPage(selectview);
            delayWithGivenTime(3000);
            softassert.assertEquals(dashboardorder.get_Selected_View_On_AllOrdersPage(), selectview, "Test Steps - 10: Selected view is not displayed on all orders page");
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoicenumber), "Test Step - 10 - Respective Invoice number : " + invoicenumber + " is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoicenumber), mop, "Test Step - 10 - Respective MOP is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoicenumber), "Delivered", "Test Step - 10 - Order status is not displayed as delivered for cash and carry order");


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
