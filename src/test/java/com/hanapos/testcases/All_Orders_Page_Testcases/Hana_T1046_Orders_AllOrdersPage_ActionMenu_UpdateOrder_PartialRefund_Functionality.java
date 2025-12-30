package com.hanapos.testcases.All_Orders_Page_Testcases;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T1046_Orders_AllOrdersPage_ActionMenu_UpdateOrder_PartialRefund_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    String invoice_number;
    String total_amount;
    String balance_amount;
    public static final String dataSheetName = "Hana_T1046";
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Owner("Balaji N")
    @Epic("All Orders Page Module")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1046_Orders_AllOrdersPage_ActionMenu_UpdateOrder_PartialRefund_Functionality_Test
            (String username,
             String password,
             String shopname,
             String salesperson,
             String customername,
             String recipientfname,
             String recipientlname,
             String recipientaddress1,
             String recipientfulladdress1,
             String recipientzipcode,
             String recipientcity,
             String recipientstate,
             String recipientcountry,
             String recipientphone,
             String recipientlocation,
             String deliveryontime,
             String occasion,
             String cardmessage,
             String itemcode,
             String itemdescription,
             String itemquantity,
             String itemprice,
             String paymenttype,
             String cash_registry,
             String ordertype,
             String deliverytype,
             String mop,
             String submenu,
             String order_update_reason) {

        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();

        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(username);
            lp.EnterPassword(password);
            softassert.assertEquals(lp.get_entered_username(), username, "Test Step - 2: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), password, "Test Step - 2: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2: Page does not navigated to hana dashboard page");

            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 2: Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 2: Cash and carry option is not displayed");

            dashboard.ClickOrderEntry();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(shopname);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), shopname, "Test Step - 2 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Test Step - 2: Delivery type as Delivery is not highlighted in blue color");

            // Test Step - 3
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), prop.getProperty("cust_firstName"), "Test Step - 3 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), prop.getProperty("cust_lastName"), "Test Step - 3 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), prop.getProperty("cust_companyName"), "Test Step - 3 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("cust_email"), "Test Step - 3 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), prop.getProperty("cust_address1"), "Test Step - 3 address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 3 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), prop.getProperty("cust_zipcode"), "Test Step - 3 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), prop.getProperty("cust_city"), "Test Step - 3 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_phoneNumber"), "Test Step - 3 phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 3 - Alt phone number is not displayed on phone order page");

            phoneorder.EnterReciFirstName(recipientfname);
            phoneorder.EnterReciLastName(recipientlname);
            softassert.assertEquals(phoneorder.getReciFirstName(), recipientfname, "Test Step - 3 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recipientlname, "Test Step - 3 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(recipientfulladdress1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), recipientaddress1, "Test Step - 3 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), recipientzipcode, "Test Step - 3 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), recipientcity, "Test Step - 3 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), recipientstate, "Test Step - 3 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(recipientcountry);
            phoneorder.EnterReciPhone(recipientphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recipientlocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown(deliveryontime);
            delayWithGivenTime(1000);

            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), cardmessage);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), occasion, "Test Step - 3 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(cardmessage), true, "Test Step - 3 - Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(itemcode, itemdescription);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), itemcode, "Test Step - 3 - Item code as " + itemcode + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), itemdescription, "Test Step - 3 - Item description " + prop.getProperty("product_description1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 3 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 3 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 4
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), paymenttype, "Test Step - 4 - Selected payment type " + paymenttype + " is not displayed on phone order page payment section");

            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getDisplayedPaymentTypeSelectedOption(), paymenttype, "Test Step - 5 - Selected Payment type is not displayed on phone order page payment section");
            delayWithGivenTime(1000);
            phoneorder.Enter_CashPaymentType_Amount();
            delayWithGivenTime(1000);
            phoneorder.SelectCashRegistry_On_CashPaymentType(cash_registry);

            // Test Step - 12
            total_amount = phoneorder.getGrandTotalAmount();

            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 12 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 5
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 13 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoice_number = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 14
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);

            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 14 - Dashboard order page is not displayed");

            // Test Step - 15
            dashboardorder.EnterGlobalSearch(invoice_number);
            delayWithGivenTime(1000);

            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice_number), "Test Step - 15 - Respective Invoice number is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice_number), "New", "Test Step - 15 - Respective Invoice status is not displayed on all orders page");
            delayWithGivenTime(2000);

//========================= Above are pre - requisite steps for this test case =============================================

            // Test Step - 3
            dashboardorder.Click_ActionMenu_For_Respective_Invoice(invoice_number);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_Action_submenu_is_displayed(), "Test Step - 3 - Action sub menus are not displayed when clicks the action menu in order page");
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Verify_Respective_Submenu_is_displayed(submenu), "Test Step - 3 - Update Order sub menu is not displayed when clicks the action menu in order page");

            // Test Step - 4
            dashboardorder.Click_UpdateOrder_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_UpdateOrder_Popup_IsDisplayed(), "Test Step - 4 - Update order popup is not displayed when clicks the cancel order action submenu row 1");

            // Test Step - 5
            String updatedprice = dashboardorder.getUpdatedPriceAfterSubtraction();
            dashboardorder.Enter_Updated_Order_Price_UpdateOrderPopup(updatedprice);
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_Entered_Updated_Order_Price_UpdateOrderPopup(), updatedprice, "Test Step - 5 - Updated order price is not displayed on update order popup");

            // Test Step - 6
            PressTabKey();
            delayWithGivenTime(3000);
            dashboardorder.Select_UpdateOrder_Reason_InUpdateOrderPopup(order_update_reason);
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.get_Selected_UpdateOrder_Reason_InUpdateOrderPopup(), order_update_reason, "Test Step - 6 - Update order reason is not displayed on update order popup");

            // Test Step - 8
            balance_amount = dashboardorder.Get_Refund_Balance_Amount();
            delayWithGivenTime(1000);
            dashboardorder.Click_UpdateOrder_Button_InUpdateOrderPopup();
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Verify_Update_Order_Popup_IsDisplayed(), "Test Step - 8 - Update order popup is not displayed when clicks the cancel order action submenu row 1");

            // Test Step - 9
            dashboardorder.Click_YesButton_CancelOrder_Popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Validate_VerifyPassword_Popup_IsDisplayed(), "Test Step - 9 - Verify password popup is not displayed when clicks the yes button on cancel order popup");

            // Test Step - 10
            dashboardorder.Enter_Manager_Password_On_VerifyPassword_Popup(TestData.Manager_Password.getValue());
            delayWithGivenTime(1000);
            dashboardorder.Click_OkayButton_VerifyPassword_Popup();
            softassert.assertEquals(dashboardorder.Verify_Success_Toaster_Message_Text(), "Order Updated Successfully", "Test Step - 10 - Success toaster message is not displayed when clicks the yes button on cancel order popup");

            // Test Step - 11
            getDriver().navigate().refresh();
            delayWithGivenTime(2000);
            dashboardorder.EnterGlobalSearch(invoice_number);
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice_number), "New", "Test Step - 11 - New as order status is not displayed on order page");

            // Test Step - 12
            dashboardorder.clickInvoiceNumber_On_TableGrid_AllOrdersPage(invoice_number);
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_OrderUpdated_ActiviesTab().contains("Refund Processed"), true, "Test Step - 12 - Refund Processed label message is not displayed on activities tab order page");
            softassert.assertEquals(dashboardorder.get_OrderUpdated_ActiviesTab().contains("Refund Amount: " + dashboardorder.get_refund_amount_on_activitiesTab()), true, "Test Step - 12 - Refund Amount is not displayed on activities tab order page");
            softassert.assertEquals(dashboardorder.get_OrderUpdated_ActiviesTab().contains("Payment Method: Cash"), true, "Test Step - 12 - Payment Method as Cash message is not displayed on activities tab order page");
            softassert.assertEquals(dashboardorder.get_OrderUpdated_ActiviesTab().contains("Reason: " + dashboardorder.get_reason_on_activitiesTab()), true, "Test Step - 12 - Reason message is not displayed on activities tab order page");

            // Test Step - 13
            delayWithGivenTime(2000);
            dashboardorder.Click_PaymentTab_On_InvoicePopup();
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_payment_refund_paymentTab(), balance_amount, "Test Step - 13 - Cash Amount of Refund Payment is not displayed on payment tab");


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