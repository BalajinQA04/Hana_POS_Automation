package com.hanapos.testcases.All_Orders_Page_Testcases;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T1434_Order_Page_All_Orders_Verify_the_Status_is_change_to_Dispatch_when_order_is_dispatch_from_Advance_Dispatch_Functionality extends TestBaseClass {
    public DashboardOrderPage dashboardOrderPage;
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private Advance_DispatchPage advanceDispatch;
    String invoiceNumber;
    String total_amount;
    public static final String dataSheetName = "Hana_T1433";
    private String routeNumber;
    String automationshop_deliverydate;
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Owner("Balaji N")
    @Epic("All Orders Page Module")
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data", priority = 1)
    public void Validate_Hana_T1434_Order_Page_All_Orders_Verify_the_Status_is_change_to_Dispatch_when_order_is_dispatch_from_Advance_Dispatch_Functionality(String shopname, String salesperson, String customername, String recifname, String recilname, String fulladdress, String reciaddress1, String reciaddress2, String recizip, String recicity,
                                                                                                                                                             String recicountry, String reciphone, String recilocation, String occasion, String itemcode, String itemdescription, String paymenttype, String cash_registry
    ) {
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            lp.EnterUserName(prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            lp.ClickLoginButton();

            // Test Step - 1
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard Page does not navigated to hana dashboard page");

            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step 1 - Order Entry Option is not displaying");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step 1 - Cash and carry Option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(shopname);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), shopname, "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Delivery type is not highlighted in blue color");

            // Test Step - 6
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

            // Test Step - 7
            phoneorder.EnterReciFirstName(recifname);
            phoneorder.EnterReciLastName(recilname);
            phoneorder.EnterReciAddress1(reciaddress1);
            phoneorder.EnterReciAddress2(reciaddress2);
            phoneorder.EnterReciZipcode(recizip);
            delayWithGivenTime(1000);

            phoneorder.SelectReciCountry(recicountry);
            phoneorder.EnterReciPhone(reciphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname, "Test Step - 7 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname, "Test Step - 7 - Entered last name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), reciaddress1, "Test Step - 7 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), reciaddress2, "Test Step - 7 - Entered address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), recizip, "Test Step - 7 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), recicity, "Test Step - 7 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), recilocation, "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");
            automationshop_deliverydate = phoneorder.getDeliveryDateOnReciSection();

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(itemcode, itemdescription);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 10
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getDisplayedPaymentTypeSelectedOption(), paymenttype, "Test Step - 10 - Selected Payment type is not displayed on phone order page payment section");
            delayWithGivenTime(1000);
            phoneorder.EnterCashAmount();
            delayWithGivenTime(1000);
            phoneorder.SelectCashRegistry_On_CashPaymentType(cash_registry);

            // Test Step - 12
            total_amount = phoneorder.getGrandTotalAmount();
            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 12 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 13
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 13 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 9
            delayWithGivenTime(1000);
            dashboard = new HanaDashBoardPage();
            dashboardOrderPage = new DashboardOrderPage();
            dashboard.ClickOrder();
            delayWithGivenTime(5000);
            softassert.assertEquals(dashboardOrderPage.Get_Text_From_Filter_View(), "Last 7 days", "Test Step 9 - All Order Page is not displaying");

            // Test Step - 10
            softassert.assertTrue(dashboardOrderPage.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step 10 - Invoice is not displaying in the Order Page");

            // Test Step - 11
            softassert.assertEquals(dashboardOrderPage.validate_Status_On_AllOrdersPage(invoiceNumber), "New", "Test Step 11 - Invoice Status is not displaying as New");

            // Test Step - 12
            softassert.assertEquals(dashboardOrderPage.validate_OrderType_On_AllOrdersPage(invoiceNumber), "Phone Order", "Test Step 12 - Order Type is not displaying as Phone Order");

            // Test Step - 13
            dashboard.Hover_Dispatch_And_Click_AdvanceDispatch();
            advanceDispatch = new Advance_DispatchPage();
            delayWithGivenTime(3000);

            softassert.assertTrue(advanceDispatch.verify_Advance_Dispatch_Page_IsDisplayed(), "Test Step - 13 - Advance Dispatch page is not displayed");

            // Test Step - 14
            // Skip the invalid address pop-up
            if (advanceDispatch.verifying_invalid_address_pop_up_is_displaying_or_not()) {
                advanceDispatch.click_proceed_without_address_button();
            }
            delayWithGivenTime(3000);
            advanceDispatch.Set_Delivery_Date_on_Advance_Dispatch(automationshop_deliverydate);
            delayWithGivenTime(3000);

            // Skip the invalid address pop-up
            if (advanceDispatch.verifying_invalid_address_pop_up_is_displaying_or_not()) {
                advanceDispatch.click_proceed_without_address_button();
            }

            delayWithGivenTime(2000);
            softassert.assertTrue(advanceDispatch.verify_pending_deliveries_invoice_isDisplayed(invoiceNumber), "Test Step - 14 - Invoice is not displaying in the pending Deliveries section");

            // Test Step - 15
            advanceDispatch.Click_pending_deliveries_invoice(invoiceNumber);
            delayWithGivenTime(2000);

            // skip the multiple order with same address pop-up
            if (advanceDispatch.multiple_order_with_same_address_popup_is_displayed()) {
                advanceDispatch.uncheck_the_ivoice_remaining_the_invoice_required_in_multiple_invoices_with_same_address(invoiceNumber);
                advanceDispatch.Click_confirm_button_in_multiple_order_with_same_address_popup();
            }

            delayWithGivenTime(3000);
            softassert.assertTrue(advanceDispatch.validate_the_invoice_is_displaying_in_the_trip_section(invoiceNumber), "Test Step - 15 - Invoice is not displaying in the Trip Section");


            // Test Step - 16
            advanceDispatch.Select_Driver(prop.getProperty("Assigned_DriverName"));
            softassert.assertEquals(advanceDispatch.get_selected_drivername(), prop.getProperty("Assigned_DriverName"), "Test Step - 16 - Selected Driver Is not displaying");

            //Test Step - 17
            advanceDispatch.Click_DispatchSave_Button();
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            delayWithGivenTime(1500);
            softassert.assertTrue(phoneorder.verifySuccessToastMessageAppears(), "Test Step - 17 - Success Toaster Message is not displaying");

            // Test Step - 18
            dashboard = new HanaDashBoardPage();
            dashboard.ClickOrder();
            dashboardOrderPage = new DashboardOrderPage();
            delayWithGivenTime(5000);

            if (!dashboardOrderPage.get_Selected_View_On_AllOrdersPage().equalsIgnoreCase("Last 30 days")) {
                dashboardOrderPage.Select_views_dropdown_on_all_ordersPage("Last 30 days");
            }
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardOrderPage.Get_Text_From_Filter_View(), "Last 30 days", "Test Step 18 - ALl Order Page is not displaying");

            // Test Step - 19
            softassert.assertTrue(dashboardOrderPage.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step 19 - Invoice is not displaying in the Order Page");
            softassert.assertEquals(dashboardOrderPage.validate_Status_On_AllOrdersPage(invoiceNumber), "Dispatched\nDriver: Liam Jack Benjamin", "Test Step 19 - Invoice Status is not displaying as New");

            // Test Step - 20
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardOrderPage.verify_Dispatched_Status_Table_Grid_IsDisplayed("Dispatched\nDriver: Liam Jack Benjamin"), "Test Step - 20 - Dispatched Status Table Grid is not displaying");


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
