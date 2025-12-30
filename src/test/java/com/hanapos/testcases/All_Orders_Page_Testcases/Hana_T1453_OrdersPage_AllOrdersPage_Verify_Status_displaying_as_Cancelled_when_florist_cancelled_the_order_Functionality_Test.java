package com.hanapos.testcases.All_Orders_Page_Testcases;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;

public class Hana_T1453_OrdersPage_AllOrdersPage_Verify_Status_displaying_as_Cancelled_when_florist_cancelled_the_order_Functionality_Test extends TestBaseClass {

    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    String invoiceNumber;
    String total_amount;
    public static final String dataSheetName = "Hana_T1453";
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Epic("All Orders Page Module")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1453_OrdersPage_AllOrdersPage_Verify_Status_displaying_as_Cancelled_when_florist_cancelled_the_order_Functionality_Test(
            String username,
            String password,
            String shopname,
            String salesperson,
            String customername,
            String recipientfname,
            String recipientlname,
            String recipientaddress1,
            String recipientfulladdress1,
            String recipientaddress2,
            String recipientzipcode,
            String recipientcity,
            String recipientcountry,
            String recipientphone,
            String recipientlocation,
            String deliverytime_in_hour,
            String deliverytime_in_minutes,
            String expected_deliverytime,
            String deliveryontime,
            String occasion,
            String cardmessage,
            String itemcode,
            String itemdescription,
            String paymenttype,
            String ordertype,
            String deliverytype,
            String mop,
            String submenu,
            String order_cancel_reason,
            String manager_password,
            String orderstatus) {
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger_Util.startNetworkLogging(testCaseName);

            lp.EnterUserName(username);
            lp.EnterPassword(password);
            softassert.assertEquals(lp.get_entered_username(), username, "Test Step - 1: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), password, "Test Step - 1: Entered password is not matching with expected password as " + prop.getProperty("password"));
            lp.ClickLoginButton();

            // Test Step - 2
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step 1:Page does not navigated to hana dashboard page");
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 2: Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 2: Cash and carry option is not displayed");

            // Test Step - 3
            dashboard.ClickOrderEntry();

            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(shopname);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), shopname, "Test Step - 3 - Selected the shop name as " + shopname + "on phoneorder page is not displayed properly as expected");

            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Test Step - 3: Delivery type type is not highlighted in blue color");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), prop.getProperty("cust_firstName"), "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), prop.getProperty("cust_lastName"), "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), prop.getProperty("cust_companyName"), "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), prop.getProperty("cust_email"), "Test Step - 6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), prop.getProperty("cust_address1"), "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), prop.getProperty("cust_zipcode"), "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), prop.getProperty("cust_city"), "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_phoneNumber"), "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), prop.getProperty("cust_Alt_phoneNumber"), "Test Step - 6 - Alt phone number is not displayed on phone order page");

            // Test Step - 4
            Faker faker = new Faker(new Locale("en-US"));
            String recifname1;
            String recilname2;
            String reci_phone_number1;
            String reci_phone_number2;
            String floor_number;
            String reci_full_address1;

            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(recipientfulladdress1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "1160 W 5th St", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(recipientcountry);
            phoneorder.EnterReciPhone(recipientphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recipientlocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(Integer.parseInt(deliverytime_in_hour), Integer.parseInt(deliverytime_in_minutes));
            phoneorder.Select_DeliveryOnTime_Dropdown(deliveryontime);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), recipientcountry, "Test Step - 4 - Recipient country is not matched with customer country on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), recipientphone, "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), recipientlocation, "Test Step - 4 - Selected Location is not properly displayed on recipient section");
            softassert.assertEquals(phoneorder.get_deliveryTime_on_recipientSection(), expected_deliverytime, "Test Step - 4 - Selected Delivery time is not properly displayed on recipient section");
            softassert.assertEquals(phoneorder.getSelected_DeliveryOnTimeOptions_OnDropdown_RecipientSection(), deliveryontime, "Test Step - 4 - Selected Delivery on Time option is not properly displayed on recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 4 - Selected Delivery date is not properly displayed on recipient section");

            //Test Step - 5
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), cardmessage);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), occasion, "Test Step - 5 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(cardmessage), true, "Test Step - 5 - Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 6
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(itemcode, itemdescription);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), itemcode, "Test Step - 6 - Item code as " + itemcode + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), itemdescription, "Test Step - 6 - Item description " + prop.getProperty("product_description1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 6 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 6 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 7
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), paymenttype, "Test Step - 7 - Selected payment type " + paymenttype + " is not displayed on phone order page payment section");
            total_amount = phoneorder.getGrandTotalAmount();
            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 7 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 8
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 8 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 9
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 9 - Dashboard order page is not displayed");

            // Test Step - 10
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 10 - Respective Invoice number is not displayed on all orders page");
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);

            // Test Step - 11
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "New", "Test Step - 12 - Order status is not displayed as delivered for cash and carry order");

            // Test Step - 12
            softassert.assertEquals(dashboardorder.validate_OrderType_On_AllOrdersPage(invoiceNumber), ordertype, "Test Step - 12: Order Type is not properly displayed for cash and carry order");
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoiceNumber), mop, "Test Step - 12 - In orders summary page - " + mop + " payment type invoice number is not displayed for placed order");

            // Test Step - 13
            dashboardorder.Click_ActionMenu_For_Respective_Invoice(invoiceNumber);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_Action_submenu_is_displayed(), "Test Step - 13 - Action sub menus are not displayed when clicks the action menu in order page");

            // Test Step - 14
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Verify_Respective_Submenu_is_displayed(submenu), "Test Step - 14 - Cancel Order sub menu is not displayed when clicks the action menu in order page");

            // Test Step - 15
            dashboardorder.Click_CancelOrder_Submenu();
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.Verify_CancelOrder_Popup_IsDisplayed(), "Test Step - 15 - Cancel order popup is not displayed when clicks the cancel order action submenu row 1");

            // Test Step - 16
            dashboardorder.Select_CancelOrder_Reason(order_cancel_reason);
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_cancelOrder_Reason(), order_cancel_reason, "Test Step - 16 - Cancel order reason is not displayed on cancel order popup");

            dashboardorder.Click_YesButton_CancelOrder_Popup();
            delayWithGivenTime(2000);

            // Test Step - 17
            if (dashboardorder.Validate_VerifyPassword_Popup_IsDisplayed()) {
                softassert.assertTrue(dashboardorder.Validate_VerifyPassword_Popup_IsDisplayed(), "Test Step - 16 - Verify password popup is not displayed when clicks the yes button on cancel order popup");
                dashboardorder.Enter_Manager_Password_On_VerifyPassword_Popup(manager_password);
                delayWithGivenTime(1000);
                dashboardorder.Click_OkayButton_VerifyPassword_Popup();
                softassert.assertEquals(dashboardorder.Verify_Success_Toaster_Message_Text(), "Order Cancelled Successfully", "Test Step - 16 - Success toaster message is not displayed when clicks the yes button on cancel order popup");
            }

            // Test Step - 18
            delayWithGivenTime(2000);
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 18 - Respective Invoice number is not displayed on order page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), orderstatus, "Test Step - 18 - Cancelled order status is not displayed on order page after clicking the yes button on cancel order popup");

            delayWithGivenTime(2000);
            dashboardorder.click_Status_Cell_On_AllOrdersPage(invoiceNumber);
            delayWithGivenTime(3000);
            softassert.assertEquals(dashboardorder.Verify_Top_Of_Invoice_OrderStatus(), "Cancelled", "Test Step - 18 - Cancelled order status is not displayed on top of invoice on order page");
            softassert.assertEquals(dashboardorder.get_OrderUpdate_CancelledMessageTextActivityTab(), "ORDER CANCELLED", "Test Step - 18 - Cancelled order message is not displayed on invoice activities tab order page");

            delayWithGivenTime(2000);
            dashboardorder.Click_PaymentTab_On_InvoicePopup();
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_CancelledOrderStatus_payment_refund_amount_row1(), "$-" + total_amount, "Test Step - 18 - Invoice/House Account Refund Payment description is not displayed on payment tab");

            delayWithGivenTime(2000);
            dashboardorder.Click_StatusTab_onInvPopup();
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_statustab_description_row2(), "Cancelled", "Test Step - 18 - Cancelled order status is not displayed on Status tab");

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