package com.hanapos.testcases.Quick_Dispatch_Testcases;

import java.io.IOException;

import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.DashboardOrderPage;
import com.hanapos.pageObjects.DispatchPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.pageObjects.Order_Confirmation_Page;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;


public class Hana_T711_Quick_Dispatch_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private DispatchPage dispatch;
    String invoice;
    public static final String dataSheetName = "Hana_T711";
    public static LoggerUtil logger_Util;
    public String Delivery_Date;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Quick Dispatch Module")
    //,invocationCount=5 - to run the test multiple times
    @Test(enabled = true, groups = {"Regression", "Smoke", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T711_Quick_Dispatch_Functionality_with_Existing_customer(
            String salesperson, String customername, String recifname, String recilname,
            String reci_full_address1, String reciaddress1, String reciaddress2, String recizip, String recicity,
            String recistate, String recicountry, String reciphone, String recilocation, String occasion, String searchandselectitemcode, String paymenttype) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));

            lp.EnterPassword(prop.getProperty("password"));

            lp.ClickLoginButton();


            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickDeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnDeliveryTypeOnPhoneOrderPage(), "#676a6c", "Pickup type is not highlighted in blue color");

            // Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(salesperson);
            phoneorder.SearchAndSelectCustomerOnCust_Section(customername);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Abish", "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "David", "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Hana_Sisterchicks", "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "hanaposqateam@gmail.com", "Test Step - 6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "3402 Park Blvd", "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "92103", "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Diego", "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 6 - Alt phone number is not displayed on phone order page");

            // Test Step - 7
            phoneorder.EnterReciFirstName(recifname);
            phoneorder.EnterReciLastName(recilname);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), reciaddress1, "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), recizip, "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), recicity, "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), recistate, "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.EnterReciAddress2(reciaddress2);
            phoneorder.SelectReciCountry(recicountry);
            phoneorder.EnterReciPhone(reciphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            Delivery_Date = phoneorder.getDeliveryDateOnReciSection();

            delayWithGivenTime(2000);

            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), reciphone, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), recilocation, "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown("Exactly At");
            delayWithGivenTime(1000);

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(searchandselectitemcode, prop.getProperty("product_description1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            }
            delayWithGivenTime(2000);

            // Test Step - 10
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(paymenttype);
            delayWithGivenTime(1000);
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 10 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 11
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 13 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoice = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);

            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 14 - Dashboard order page is not displayed");
            delayWithGivenTime(2000);

            dashboardorder.EnterGlobalSearch(invoice);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice), "Test Step - 10 - Respective Invoice number : " + invoice + " is not displayed on all orders page");
            delayWithGivenTime(1000);

            //=======================Above are the pre requiste


            // Test Step - 3
            dashboard.Hover_Dispatch_And_Click_QuickDispatch();
            dispatch = new DispatchPage();
            softassert.assertTrue(dispatch.Verify_DispatchPopup_IsDisplayed(), "Test Step - 3 - Dispatch popup is not displayed");
            delayWithGivenTime(1000);

            dispatch.Select_Delivery_Date_on_Quick_Dispatch_Page(Delivery_Date);

            // Test Step - 4
            delayWithGivenTime(3000);
            dispatch.Enter_InvoiceNumber_on_ScanOrderTextbox(invoice);
            delayWithGivenTime(3000);
            softassert.assertEquals(dispatch.get_InvoiceNumber_On_TripSection(), dashboardorder.getInvoiceNumber_On_PhoneOrder_DeliveryInvoiceInHousePayment(), "Test Step - 4 - Entered Invoice number is not matched on trip section");
            softassert.assertEquals(dispatch.get_NameAndAddress_On_TripSection(), recifname + " " + recilname + " " + reciaddress1 + " " + reciaddress2, "Test Step - 4 - Displayed Name and address are not matched");
            softassert.assertEquals(dispatch.get_City_On_TripSection(), recicity, "Test Step - 4 - Displayed city is not matched");
            softassert.assertEquals(dispatch.get_State_On_TripSection(), recistate, "Test Step - 4 - Displayed state is not matched");
            softassert.assertEquals(dispatch.get_Zipcode_On_TripSection(), recizip, "Test Step - 4 - Displayed zipcode is not matched");

            // Test Step - 5
            dispatch.Select_Driver(prop.getProperty("Assigned_DriverName"));
            delayWithGivenTime(2000);
            softassert.assertEquals(dispatch.get_Selected_Driver(), prop.getProperty("Assigned_DriverName"), "Test Step - 5 - Selected Driver is not displayed on the dropdown");

            // Test Step - 6
            dispatch.Click_DispatchSave_Button();
            String dispatched_datetime = Get_Atlantic_TimeZone1();
            String Dispatch_Time = Get_Atlantic_TimeZone();

            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Dispatch Saved Successfully", "Test Step - 6 - Dispatch Saved Successfully toast success text message is not displayed");
            softassert.assertTrue(dispatch.Verify_NewTripBtn_IsDisplayed(), "Test Step - 6 - New trip button is not displayed");
            softassert.assertTrue(dispatch.Verify_RoutePlannerBtn_IsDisplayed(), "Test Step - 6 - Route planner button is not displayed");
            softassert.assertTrue(dispatch.Verify_RemotePrintBtn_IsDisplayed(), "Test Step - 6 - Remote print button is not displayed");
            softassert.assertTrue(dispatch.Verify_ManualPrintBtn_IsDisplayed(), "Test Step - 6 - Manual Print button is not displayed");
            softassert.assertTrue(dispatch.Verify_AddPayrateBtn_IsDisplayed(), "Test Step - 6 - Add Payrate button is not displayed");

            // Test Step - 7
            delayWithGivenTime(2000);
            softassert.assertEquals(dispatch.get_SavedTrips_driverInitial("LJB"), "LJB", "Test Step - 7 - Driver Initial is not matched with expected on saved trips section");
            softassert.assertEquals(dispatch.get_SavedTrips_driverName("Liam Jack Benjamin"), "Liam Jack Benjamin", "Test Step - 7 - Driver Name is not matched with expected on saved trips section");
            softassert.assertEquals(dispatch.get_SavedTrips_driverPhoneNumber("837-837-8990"), "837-837-8990", "Test Step - 7 - Driver phonenumber is not matched with expected on saved trips section");

            // Test Step - 8
            dispatch.Click_CloseIcon_dispatchPopup();

            // Test Step - 9
            //======There will be possible to failed during parallel execution
            // So that commented below steps

            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(2000);

            // Test Step - 11
            dashboardorder.EnterGlobalSearch(invoice);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice), "Test Step - 10 - Respective Invoice number : " + invoice + " is not displayed on all orders page");
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice), "Dispatched\nDriver: Liam Jack Benjamin", "Test Step - 12 - Order status is not displayed as delivered for order");
            //  softassert.assertEquals(dashboardorder.get_status_on_orderpage(), "Dispatched", "Test Step - 11 - Order status displayed on dashboard order page is not matched");

            // Test Step - 12
            //  softassert.assertTrue(dashboardorder.Validate_PhoneOrder_DeliveryInvoiceInHousePayment(), "Test Step - 12 - Phone order invoice in house payment is not displayed");
            delayWithGivenTime(2000);

            // Test Step - 13
            dashboardorder.click_Status_Cell_On_AllOrdersPage(invoice);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.verify_Order_Info_Popup_IsDisplayed(), "Test Step - 13 - Order info popup is not displayed");
            delayWithGivenTime(2000);

            softassert.assertEquals(dashboardorder.get_OrderStatus_DeliveryPopup(), "Dispatched", "Test Step - 13 - Order status is not updated as expected in invoice popup");


            // Test Step - 14
            softassert.assertEquals(dashboardorder.get_drivername_onInvoicePopup(), "LJB - Liam Jack Benjamin", "Test Step - 14 - Driver name is not autopopulated on invoice popup");

            // Test Step - 15
            dashboardorder.Click_DispatchTab_onInvPopup();

            // Test Step - 16
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.get_dispatchConfirmation_Date_and_Time(), Dispatch_Time, "Test Step - 16 - Dispatch confirmation date and time is not matched as expected atlantic time zone on invoice popup");
            //  softassert.assertTrue(dashboardorder.Validate_dispatchConfirmation_Date_and_Time_With_AtlanticTimeZone(),"Test Step - 16 - Dispatch confirmation date and time is not matched as expected atlantic time zone on invoice popup");

            // System.out.println(dashboardorder.get_dispatchConfirmation_Date_and_Time() + "Dispatch Confirmation time");
            //System.out.println(Dispatch_Time + "Store Date value");

            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.get_dispatchConfirmation_DriverName(), "LJB (Liam Jack Benjamin)", "Test Step - 16 - Dispatch confirmation driver name is not matched as expected on invoice popup");

            // Test Step - 17
            delayWithGivenTime(1000);
            dashboardorder.Click_StatusTab_onInvPopup();

            // Test Step - 18
            delayWithGivenTime(2000);
            String actualFormattedDate = convertToExpectedFormat(dashboardorder.get_StatusTab_date_and_time());
            softassert.assertEquals(actualFormattedDate, Dispatch_Time, "Test Step - 18 - Status tab confirmation dispatch date and time is not matched as expected on invoice popup");

            // Test Step - 19
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.Verify_DeliveryOnTruck_at_ActivitySection(), "Delivery On Truck", "Test Step - 19 - Delivery on truck message text is not matched as expected on activity section");

            delayWithGivenTime(2000);
            // softassert.assertEquals(dashboardorder.Validate_DeliveryOnTruck_DateandTime_at_ActivitySection(), true, "Test Step - 19 - Delivery on truck date and time is not matched as expected on activity section");
            softassert.assertEquals(dashboardorder.get_DeliveryOnTruck_DateandTime_at_ActivitySection(), Dispatch_Time, "Test Step - 19 - Delivery on truck date and time is not matched as expected on activity section");

            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.get_Confirmation_DriverName_RouteNumber_on_ActivitySection(), "LJB", "Test Step - 19 - Delivery on truck driver name is not matched as expected on activity section");

            delayWithGivenTime(2000);
            // softassert.assertEquals(dashboardorder.Validate_ConfirmationDate_and_Time_RouteNumber_on_ActivitySection(), true, "Test Step - 19 - Delivery on truck driver name is not matched as expected on activity section");
            softassert.assertEquals(dashboardorder.get_ConfirmationDate_and_Time_RouteNumber_on_ActivitySection(), Dispatch_Time, "Test Step - 19 - Delivery on truck driver name is not matched as expected on activity section");
            delayWithGivenTime(1000);

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
