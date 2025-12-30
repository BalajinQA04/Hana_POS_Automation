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

public class Hana_T1467_OrderPage_AllOrdersView_PendingDeliveriesView_Delivery_TypeAs_Delivered_And_StatusAs_New_Redeliver_Because_Of_Complaint_And_Redeliver_Flowers_Back_In_Store_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private Advance_DispatchPage advanceDispatchPage;
    private ConfirmationPage confirmationPage;
    String invoiceNumber;
    public static final String dataSheetName = "Hana_T1467";
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Owner("Balaji N")
    @Epic("All Orders Page Module")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1467_OrderPage_AllOrdersView_PendingDeliveriesView_Delivery_TypeAs_Delivered_And_StatusAs_New_Redeliver_Because_Of_Complaint_And_Redeliver_Flowers_Back_In_Store_Functionality_Test(
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
            String selectview,
            String drivername,
            String deliverycode,
            String orderfinalstatus,
            String rescheduleorderstatus) {
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
            phoneorder.EnterReciFirstName(recipientfname);
            phoneorder.EnterReciLastName(recipientlname);
            softassert.assertEquals(phoneorder.getReciFirstName(), recipientfname, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recipientlname, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

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
            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown(deliveryontime);
            delayWithGivenTime(1000);

            //Test Step - 5
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), cardmessage);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), occasion, "Test Step - 5 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(cardmessage), true, "Test Step - 5 - Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(itemcode, itemdescription);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), itemcode, "Test Step - 6 - Item code as " + itemcode + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), itemdescription, "Test Step - 6 - Item description " + prop.getProperty("product_description1") + " is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 6 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 6 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), paymenttype, "Test Step - 7 - Selected payment type " + paymenttype + " is not displayed on phone order page payment section");

            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 7 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 8 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 9 - Dashboard order page is not displayed");

            // Test Step - 6
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 10 - Respective Invoice number is not displayed on all orders page");

            dashboardorder.Select_views_dropdown_on_all_ordersPage(selectview);
            delayWithGivenTime(3000);
            softassert.assertEquals(dashboardorder.get_Selected_View_On_AllOrdersPage(), selectview, "Test Step - 6: Selected view is not displayed on all orders page");

            dashboardorder.click_Filters_Icon_On_All_Orders_Page();
            delayWithGivenTime(3000);
            softassert.assertEquals(dashboardorder.get_Displayed_Status_Option1_On_Filters_In_AllOrdersPage(), "New", "Test Step - 7: Status option is not displayed on filters in all orders page");
            softassert.assertEquals(dashboardorder.get_Displayed_Status_Option2_On_Filters_In_AllOrdersPage(), "Redeliver Because Of Complaint", "Test Step - 7: Status option is not displayed on filters in all orders page");
            softassert.assertEquals(dashboardorder.get_Displayed_Status_Option3_On_Filters_In_AllOrdersPage(), "Redeliver Flowers Back In Store", "Test Step - 7: Status option is not displayed on filters in all orders page");

            softassert.assertEquals(dashboardorder.get_Displayed_Delivery_Option1_On_Filters_In_AllOrdersPage(), "Delivery", "Test Step - 7: Delivery option is not displayed on filters in all orders page");
            softassert.assertEquals(dashboardorder.get_Displayed_Delivery_Option2_On_Filters_In_AllOrdersPage(), "Wire In", "Test Step - 7: Delivery option is not displayed on filters in all orders page");
            softassert.assertEquals(dashboardorder.get_Displayed_Delivery_Option3_On_Filters_In_AllOrdersPage(), "Wire Out", "Test Step - 7: Delivery option is not displayed on filters in all orders page");

            dashboardorder.click_Cancel_Button_On_Filters_Popup_All_Orders_Page();

            // Test Step - 8
            dashboardorder.Select_views_dropdown_on_all_ordersPage(selectview);
            delayWithGivenTime(3000);
            softassert.assertEquals(dashboardorder.get_Selected_View_On_AllOrdersPage(), selectview, "Test Step - 6: Selected view is not displayed on all orders page");

            delayWithGivenTime(1000);
            dashboardorder.Click_Checkbox_on_AllOrdersPage_for_Respective_Invoices(invoiceNumber);
            delayWithGivenTime(1000);
            dashboardorder.click_ConfirmDeliveries_Button_On_PendingDeliveries();

            // Test Step - 9
            confirmationPage = new ConfirmationPage();
            delayWithGivenTime(2000);
            softassert.assertTrue(confirmationPage.Verify_Delivery_Confirmation_Header(), "Test Step - 9 - Confirmation delivery date popup is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 10
            confirmationPage.select_delivery_code_Confirmation_Page(deliverycode);
            softassert.assertEquals(confirmationPage.get_selected_delivery_code_Confirmation_Page(), deliverycode, "Test Step - 10 - Selected delivery code is not displayed properly on confirmation page");

            confirmationPage.select_DriverCode_Delievry_Confirmation_Details_Popup(drivername);
            softassert.assertEquals(confirmationPage.get_Selected_DriverCode_Delievry_Confirmation_Details_Popup(), drivername, "Test Step - 10 - Selected driver code is not displayed properly on confirmation page");

            confirmationPage.confirm_selected_deliveries_button_click_Confirmation_page();
            softassert.assertTrue(phoneorder.verifySuccessToastMessageAppears(), "Test Step - 10 - Delivery Confirmation Toaster message is not displayed");
            confirmationPage.confirm_delivery_page_close_icon_click();

            // Test Step - 11
            delayWithGivenTime(3000);
            dashboardorder.click_Non_Deliveries_Tab();
            delayWithGivenTime(3000);
            softassert.assertEquals(dashboardorder.get_Selected_View_On_AllOrdersPage(), "Today Non Deliveries", "Test Step - 11 - Today Non Deliveries option is not selected");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "Not Delivered Delivery Attempted\nDriver: Liam Jack Benjamin", "Test Step - 11 - Order status is not displayed as delivered for order");
            softassert.assertTrue(dashboardorder.verify_Reschedule_Button_IsDisplayed(), "Test Step - 11 - Reschedule button is not displayed");

            // Test Step - 12
            dashboardorder.Click_Checkbox_on_AllOrdersPage_for_Respective_Invoices(invoiceNumber);

            // Test Step - 13
            dashboardorder.click_Reschedule_Button_On_Non_Deliveries_Tab();
            softassert.assertTrue(dashboardorder.verify_Reschedule_Order_Popup_IsDisplayed(), "Test Step - 13 - Reschedule order popup is not displayed");

            dashboardorder.Enter_Choose_New_Delivery_Date(Next_20Days_Date());
            softassert.assertEquals(dashboardorder.get_Entered_Choose_New_Delivery_Date(), Next_20Days_Date(), "Test Step - 13 - Selected new delivery date is not displayed");

            dashboardorder.click_Reschedule_Button_On_Reschedule_Order_Popup();
            softassert.assertEquals(dashboardorder.Verify_Success_Toaster_Message_Text(), "Orders Rescheduled Successfully", "Test Step - 13 - Orders Rescheduled Successfully toaster message is not displayed");

            // Test Step - 14
            dashboard.ClickOrder();
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 14 - Respective Invoice number is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), rescheduleorderstatus + "\nDriver: Liam Jack Benjamin", "Test Step - 14 - Order status is not displayed as delivered for order");

            // Test Step - 15
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), rescheduleorderstatus + "\nDriver: Liam Jack Benjamin", "Test Step - 15 - Order status " + rescheduleorderstatus + " is not displayed as delivered for order");

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
