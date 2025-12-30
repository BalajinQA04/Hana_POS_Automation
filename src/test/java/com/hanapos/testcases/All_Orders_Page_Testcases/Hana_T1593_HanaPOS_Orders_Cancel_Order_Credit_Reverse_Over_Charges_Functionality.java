package com.hanapos.testcases.All_Orders_Page_Testcases;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.*;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T1593_HanaPOS_Orders_Cancel_Order_Credit_Reverse_Over_Charges_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private DashboardOrderPage dashboardorder;

    String custname;
    String custid;
    String invoicenumber;
    String balanceAmount;
    String total_amount;
    CustomSoftAssert softassert = new CustomSoftAssert();
    public static LoggerUtil logger_Util;
    public static final String dataSheetName = "Hana_T1431";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Epic("All Orders Page Module")
    @Description("Hana T1593 :- Hana_T1593_HanaPOS_Orders_Cancel_Order_Credit_Reverse_Over_Charges_Functionality")
    @Test(enabled = true, groups = {"Smoke", "Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1593_HanaPOS_Orders_Cancel_Order_Credit_Reverse_Over_Charges_Functionality(String username,
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
                                                                                                          String deliverytype, String mop) {
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
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("bestuname"));
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

            cashandcarry.SelectClerkName(prop.getProperty("cashandcarryclerkname"));
            softassert.assertEquals(cashandcarry.get_selected_clerkname(), prop.getProperty("cashandcarryclerkname"), "Test Step - 3 : Clerk name is not matched with selected clerk name");

            cashandcarry.SelectEmployeeName(employeename);
            softassert.assertEquals(cashandcarry.get_selected_employeename(), employeename, "Test Step - 3 : Employee name is not matched with selected employee name");

            // Test Step - 4
            delayWithGivenTime(2000);
            cashandcarry.SearchAndSelectTheItemCode(searchandselectitemcode, itemdescription);
            delayWithGivenTime(2000);
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

            // Adding one more product
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
            total_amount = cashandcarrypayment.getGrandTotaltoPay();
            cashandcarrypayment.EnterGivenAmount();
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustomerNameOnCCPage(), custname, "Test Step - 7 - : Customer name is not displayed on Cash And Carry payment page when search & select the customer on this field");
            softassert.assertEquals(cashandcarrypayment.getCustomerIdDisplayed(), custid, "Test Step - 7 - : Customer ID is not displayed on Cash And Carry payment page when search & select the customer on this field");

            cashandcarrypayment.click_CrossIcon_On_AddCustomerSection_CashAndCarry_PaymentPage();
            delayWithGivenTime(1000);
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
            }

            // Test Step - 8
            cashandcarrypayment.ClickOrderConfirmationPopupCloseBtn();

            // Test Step - 9
            dashboard.ClickOrder();
            dashboardorder = new DashboardOrderPage();
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 9 - All order page is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 10
            dashboardorder.EnterGlobalSearch(invoicenumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoicenumber), "Test Step - 10 - Respective Invoice number : " + invoicenumber + " is not displayed on all orders page");

            // Test Step - 11
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoicenumber), "Delivered", "Test Step - 11 - Order status is not displayed as delivered for cash and carry order");

            // Test Step - 12
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoicenumber), ordertype, "Test Step - 12: Order Type is not properly displayed for cash and carry order");
            softassert.assertEquals(dashboardorder.validate_Recipient_Info_On_AllOrdersPage(invoicenumber), "", "Test Step - 12: Recipient information is not displayed properly for cash and carry order");

            // Test Step - 13
            dashboardorder.Click_ActionMenu_Row1();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_Action_submenu_is_displayed(), "Test Step - 13 - Action sub menus are not displayed when clicks the action menu in order page");
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Verify_Respective_Submenu_is_displayed("Cancel Order"), "Test Step - 13 - Cancel Order sub menu is not displayed when clicks the action menu in order page");

            // Test Step - 14
            dashboardorder.Click_CancelOrder_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_CancelOrder_Popup_IsDisplayed(), "Test Step - 14 - Cancel order popup is not displayed when clicks the cancel order action submenu row 1");

            // Test Step - 15
            softassert.assertTrue(dashboardorder.is_Cancel_Order_Confirmation_Message_Displayed(), "Test Step - 15 - Are you sure you want to cancel the order? confirmation message is not displayed when clicks the cancel order action submenu row 1");
            delayWithGivenTime(1000);
            dashboardorder.Select_CancelOrder_Reason("Credit: Reverse Over Charges");
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_cancelOrder_Reason(), "Credit: Reverse Over Charges", "Test Step - 15 - Cancel order reason is not displayed on cancel order popup");

            // Test Step - 16
            dashboardorder.Click_NoButton_CancelOrder_Popup();
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoicenumber), "Delivered", "Test Step - 16 - order status is not displayed properlyon order page after clicking the No button on cancel order popup");

            // Test Step - 17
            dashboardorder.Click_ActionMenu_Row1();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_Action_submenu_is_displayed(), "Test Step - 17 - Action sub menus are not displayed when clicks the action menu in order page");
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Verify_Respective_Submenu_is_displayed("Cancel Order"), "Test Step - 17 - Cancel Order sub menu is not displayed when clicks the action menu in order page");

            dashboardorder.Click_CancelOrder_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_CancelOrder_Popup_IsDisplayed(), "Test Step - 17 - Cancel order popup is not displayed when clicks the cancel order action submenu row 1");

            softassert.assertTrue(dashboardorder.is_Cancel_Order_Confirmation_Message_Displayed(), "Test Step - 17 - Are you sure you want to cancel the order? confirmation message is not displayed when clicks the cancel order action submenu row 1");
            delayWithGivenTime(1000);
            dashboardorder.Select_CancelOrder_Reason("Credit: Reverse Over Charges");
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_cancelOrder_Reason(), "Credit: Reverse Over Charges", "Test Step - 17 - Cancel order reason is not displayed on cancel order popup");

            dashboardorder.Click_YesButton_CancelOrder_Popup();
            delayWithGivenTime(2000);

            if (dashboardorder.Validate_VerifyPassword_Popup_IsDisplayed()) {
                softassert.assertTrue(dashboardorder.Validate_VerifyPassword_Popup_IsDisplayed(), "Test Step - 17 - Verify password popup is not displayed when clicks the yes button on cancel order popup");
                dashboardorder.Enter_Manager_Password_On_VerifyPassword_Popup(TestData.Manager_Password.getValue());
                delayWithGivenTime(1000);
                dashboardorder.Click_OkayButton_VerifyPassword_Popup();
                softassert.assertEquals(dashboardorder.Verify_Success_Toaster_Message_Text(), "Order Cancelled Successfully", "Test Step - 17 - Success toaster message is not displayed when clicks the yes button on cancel order popup");
            }


            // Test Step - 18
            delayWithGivenTime(2000);
            dashboardorder.EnterGlobalSearch(invoicenumber);
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoicenumber), "Cancelled", "Test Step - 17 - Cancelled order status is not displayed on order page after clicking the yes button on cancel order popup");

            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoicenumber), "Test Step - 18 - Respective Invoice number is not displayed on all orders page");
            delayWithGivenTime(2000);
            dashboardorder.click_Status_Cell_On_AllOrdersPage(invoicenumber);
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_OrderUpdate_CancelledMessageTextActivityTab(), "ORDER CANCELLED", "Test Step - 18 - Cancelled order message is not displayed on invoice activities tab order page");

            // Test Step - 19
            softassert.assertEquals(dashboardorder.Verify_Top_Of_Invoice_OrderStatus(), "Cancelled", "Test Step - 19 - Cancelled order status is not displayed on top of invoice on order page");

            // Test Step - 20
            delayWithGivenTime(2000);
            dashboardorder.Click_PaymentTab_On_InvoicePopup();
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_CancelledOrderStatus_payment_refund_amount_row1(), "$-" + total_amount, "Test Step - 10 - Invoice/House Account Refund Payment description is not displayed on payment tab");

            // Test Step - 11
            delayWithGivenTime(2000);
            dashboardorder.Click_StatusTab_onInvPopup();
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_statustab_description_row2(), "Cancelled", "Test Step - 11 - Cancelled order status is not displayed on Status tab");

            // Test Step - 22
            String expectedDateTime = get_Current_EST_Time();
            String expectedTime = get_Current_EST_Time();

            String actualRefundMessage = dashboardorder.get_Activities_Tab_Refund_Message();
            String actualOrderUpdateMessage = dashboardorder.get_Activities_Tab_Order_Updated_Message();

            String actualRefundTime = actualRefundMessage.split("\n")[1].trim();
            String actualOrderUpdateTime = actualOrderUpdateMessage.split("\n")[1].trim();

            softassert.assertTrue(
                    isTimestampWithinOneMinute(actualRefundTime, expectedTime),
                    "Refund timestamp not within ±1 minute: expected [" + expectedTime + "] but got [" + actualRefundTime + "]"
            );
            softassert.assertTrue(
                    isTimestampWithinOneMinute(actualOrderUpdateTime, expectedTime),
                    "Order update timestamp not within ±1 minute: expected [" + expectedTime + "] but got [" + actualOrderUpdateTime + "]"
            );

            softassert.assertTrue(
                    actualRefundMessage.contains("Refund Processed\nRefund Amount: $-" + total_amount + "\nRefund Method: Cash\nReason: Reverse Over Charges"),
                    "Refund message content mismatch for the Reason: Reverse Over Charges"
            );

            softassert.assertTrue(
                    actualOrderUpdateMessage.contains("Order Update : ORDER CANCELLED " + actualOrderUpdateTime),
                    "Order update content mismatch"
            );

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


