package com.hanapos.testcases.All_Orders_Page_Testcases;

import com.github.javafaker.Faker;
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
import java.util.Locale;

public class Hana_T1502_AllOrders_Verify_Status_is_ChangedTo_RedeliverFlowersBackInStore_When_Florist_Redelivered_The_Delivered_Order_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private Advance_DispatchPage advanceDispatchPage;
    private ConfirmationPage confirmationPage;
    String invoiceNumber;
    public static final String dataSheetName = "Hana_T1502";
    public static LoggerUtil logger_Util;
    String automationshop_deliverydate;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_full_address1;

    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Epic("All Orders Page Module")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1502_AllOrders_Verify_Status_is_ChangedTo_RedeliverFlowersBackInStore_When_Florist_Redelivered_The_Delivered_Order_Functionality_Test(
            String username,
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
            String recipientcountry,
            String recipientphone,
            String recipientlocation,
            String deliveryontime,
            String occasion,
            String cardmessage,
            String itemcode,
            String itemdescription,
            String paymenttype,
            String ordertype,
            String deliverytype,
            String mop,
            String drivername,
            String deliverycode) {
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

            // Test Step - 4
            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            reci_full_address1 = "11854 Bluff Rd, Dutzow, MO 63342, United States";
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "11854 Bluff Rd", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63342", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Dutzow", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(recipientcountry);
            phoneorder.EnterReciPhone(recipientphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recipientlocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown(deliveryontime);
            delayWithGivenTime(1000);
            automationshop_deliverydate = phoneorder.getDeliveryDateOnReciSection();

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
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "New", "Test Step - 10 - Order status is not displayed as New for order");

            // Test Step - 13
            advanceDispatchPage = new Advance_DispatchPage();
            dashboard.Hover_Dispatch_And_Click_AdvanceDispatch();
            softassert.assertEquals(advanceDispatchPage.Verify_Advance_DispatchPopup_IsDisplayed(), "Hana | Advance Dispatch", "Test Step-13: Advance Dispatch Page is not displaying");
            delayWithGivenTime(3000);

            // Test Step - 14
            // Skip the invalid address pop-up
            if (advanceDispatchPage.verifying_invalid_address_pop_up_is_displaying_or_not()) {
                advanceDispatchPage.click_proceed_without_address_button();
            }
            delayWithGivenTime(3000);
            advanceDispatchPage.Set_Delivery_Date_on_Advance_Dispatch(automationshop_deliverydate);
            delayWithGivenTime(3000);

            if (advanceDispatchPage.verifying_invalid_address_pop_up_is_displaying_or_not()) {
                advanceDispatchPage.click_proceed_without_address_button();
            }

            delayWithGivenTime(2000);
            softassert.assertTrue(advanceDispatchPage.verify_pending_deliveries_invoice_isDisplayed(invoiceNumber), "Test Step - 14 - Pending Deliveries Invoice: " + invoiceNumber + " is not displayed in advance dispatch page");

            // Test Step - 15
            advanceDispatchPage.Click_pending_deliveries_invoice(invoiceNumber);
            delayWithGivenTime(3000);

            // skip the multiple order with same address pop-up
            if (advanceDispatchPage.multiple_order_with_same_address_popup_is_displayed()) {
                advanceDispatchPage.uncheck_the_ivoice_remaining_the_invoice_required_in_multiple_invoices_with_same_address(invoiceNumber);
                advanceDispatchPage.Click_confirm_button_in_multiple_order_with_same_address_popup();
            }

            delayWithGivenTime(2000);
            advanceDispatchPage.validate_the_invoice_is_displaying_in_the_trip_section(invoiceNumber);

            // Test Step - 16
            advanceDispatchPage.Select_Driver(drivername);
            softassert.assertEquals(advanceDispatchPage.get_selected_drivername(), drivername, "Test Step - 16: Selected driver name is not displayed properly on advance dispatch page");

            // Test Step - 17
            advanceDispatchPage.Click_DispatchSave_Button();
            softassert.assertEquals(advanceDispatchPage.Validate_Success_Toaster_message_Appears(), "Dispatch Saved Successfully", "Test Step - 17: Dispatch Saved Successfully toaster message is not displayed");

            // Test Step - 18
            dashboard.ClickOrder();
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 18 - All orders page is not displayed");

            // Test Step - 19
            if (!dashboardorder.get_Selected_View_On_AllOrdersPage().equalsIgnoreCase("Last 30 days")) {
                dashboardorder.Select_views_dropdown_on_all_ordersPage("Last 30 days");
            }
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 19 - Respective Invoice number is not displayed on all orders page");
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "Dispatched\nDriver: Liam Jack Benjamin", "Test Step - 19 - Order status is not displayed as dispatched for order");

            // Test Step - 20
            dashboard.MouseAndClick_Confirmation_sub_menu();
            confirmationPage = new ConfirmationPage();
            delayWithGivenTime(2000);
            softassert.assertTrue(confirmationPage.Verify_Delivery_Confirmation_Header(), "Test Step - 20 - Confirmation delivery date popup is not displayed");
            delayWithGivenTime(2000);

            confirmationPage.Select_Date_On_DeliveryConfirmationDetails(CurrentDate());
            delayWithGivenTime(2000);
            confirmationPage.Select_ToDate_On_DeliveryConfirmationDetails(automationshop_deliverydate);
            delayWithGivenTime(2000);
            confirmationPage.click_Search_Button_On_Delivery_Confirmation_Details();
            delayWithGivenTime(2000);

            if (confirmationPage.Confirmation_Popup_Verification()) {
                confirmationPage.Confirming_the_confirmation_pop_up();
            }

            // Test Step - 21
            confirmationPage.Enter_RecipientInvoice_ConfirmationPage(invoiceNumber);
            delayWithGivenTime(2000);
            softassert.assertEquals(confirmationPage.get_entered_RecipientInvoice_ConfirmationPage(), invoiceNumber, "Test Step - 21 - Recipient Invoice is not matched with entered invoice number on confirmation page");
            delayWithGivenTime(2000);

            // Test Step - 22, 23, 24
            confirmationPage.select_delivery_code_Confirmation_Page(deliverycode);
            softassert.assertEquals(confirmationPage.get_selected_delivery_code_Confirmation_Page(), deliverycode, "Test Step - 22 - Selected delivery code is not displayed properly on confirmation page");

            confirmationPage.confirm_selected_deliveries_button_click_Confirmation_page();
            softassert.assertTrue(phoneorder.verifySuccessToastMessageAppears(), "Test Step - 22 - Delivery Confirmation Toaster message is not displayed");
            confirmationPage.confirm_delivery_page_close_icon_click();

            // Test Step - 25
            dashboard.ClickOrder();
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 25 - Respective Invoice number is not displayed on all orders page");
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "Delivered\nDriver: Liam Jack Benjamin", "Test Step - 25 - Order status is not displayed as delivered for order");

            // ================== Above are the pre-requistes for the test case ================== //

            // Test Step  - 2
            dashboard.ClickOrder();
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 2 - Respective Invoice number is not displayed on all orders page");
            dashboardorder.Click_ActionMenu_For_Respective_Invoice(invoiceNumber);

            // Test Step - 3
            softassert.assertTrue(dashboardorder.verify_Redelivery_Submenu_IsDisplayed(), "Test Step - 3 - Redelivery submenu is not displayed");

            // Test Step - 5
            dashboardorder.click_redelivery_Submenu();

            // Test Step - 6
            softassert.assertEquals(dashboardorder.extract_InvoiceNumber_On_Redelivery_Popup(), invoiceNumber, "Test Step - 6 - Invoice number is not displayed on redelivery popup");

            // Test Step - 7
            softassert.assertTrue(dashboardorder.is_Redelivery_Order_Confirmation_Message_Displayed(), "Test Step - 7 - Redelivery order confirmation message is not displayed");
            delayWithGivenTime(2000);
            dashboardorder.set_New_Delivery_Date_On_Redelivery_Popup(Next_10Days_Date());
            delayWithGivenTime(1000);
            dashboardorder.click_Submit_Button_On_Redelivery_Popup();
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.Verify_Success_Toaster_Message_Text(), "Order Updated Successfully to Redeliver", "Test Step - 9 - Order Updated Successfully to Redeliver toaster message is not displayed");
            wait_For_Page_To_Be_Stable(getDriver());

            // Test Step - 10
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "Redeliver Flowers Back In Store\nDriver: Liam Jack Benjamin", "Test Step - 10 - Order status is not displayed as Redeliver Flowers Back In Store for order");
            delayWithGivenTime(2000);
            dashboardorder.click_Status_Cell_On_AllOrdersPage(invoiceNumber);

            delayWithGivenTime(2000);

            // Test Step - 11
            softassert.assertEquals(dashboardorder.get_OrderStatus_DeliveryPopup(), "Redeliver Flowers Back In Store", "Test Step - 11 - Order Invoice Popup is not displayed as Redeliver Flowers Back In Store at center");
            delayWithGivenTime(2000);
            dashboardorder.Click_StatusTab_onInvPopup();
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.get_Status_Description_On_Redelivery_Popup(), "Redeliver Flowers Back In Store", "Test Step - 11 - Status description is not displayed as Redeliver Flowers Back In Store for order");


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
