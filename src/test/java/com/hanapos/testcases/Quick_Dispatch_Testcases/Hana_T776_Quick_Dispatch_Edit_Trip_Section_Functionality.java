package com.hanapos.testcases.Quick_Dispatch_Testcases;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T776_Quick_Dispatch_Edit_Trip_Section_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private DispatchPage dispatch;
    private Advance_DispatchPage advanceDispatchPage;
    String invoice1;
    String invoice2;
    String Delivery_Date;
    public static LoggerUtil logger_Util;
    public static final String dataSheetName = "Hana_T776";

    String updateDriverName = "LJB Liam Jack Benjamin";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Quick Dispatch Module")
    @Test(enabled = true, priority = 1, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T776_Quick_Dispatch_Edit_Trip_Section_Functionality(
            String salesperson, String customername, String recifname, String recilname,
            String reci_full_address1, String reciaddress1, String reciaddress2, String recizip, String recicity,
            String recistate, String recicountry, String reciphone, String recilocation, String occasion,
            String searchandselectitemcode, String paymenttype) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4 //
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
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

            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), reciphone, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), recilocation, "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(11, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown("Exactly At");
            delayWithGivenTime(1000);
            Delivery_Date = phoneorder.getDeliveryDateOnReciSection();

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase("Happy Birthday! Hope you have an amazing day!"), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(searchandselectitemcode, "rrd-Red Rose Deluxe");
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
            getDriver().switchTo().activeElement();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 10 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 11
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 13 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoice1 = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // ======================== Second Order ======================== //

            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
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

            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), reciphone, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), recilocation, "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(11, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown("Exactly At");
            delayWithGivenTime(1000);

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase("Happy Birthday! Hope you have an amazing day!"), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(searchandselectitemcode, "rrd-Red Rose Deluxe");
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
            getDriver().switchTo().activeElement();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 10 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 11
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 11 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoice2 = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();                          //https://hanafloralpos3.com/Dashboard/Order
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 14 - Dashboard order page is not displayed");
            delayWithGivenTime(2000);

            dashboardorder.EnterGlobalSearch(invoice1);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice1), "Test Step - 10 - Respective Invoice number : " + invoice1 + " is not displayed on all orders page");

            dashboardorder.EnterGlobalSearch(invoice2);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice2), "Test Step - 10 - Respective Invoice number : " + invoice2 + " is not displayed on all orders page");

            // Test Step - 2
            dashboard.Hover_Dispatch_And_Click_QuickDispatch();
            dispatch = new DispatchPage();
            softassert.assertTrue(dispatch.Verify_DispatchPopup_IsDisplayed(), "Test Step - 3 - Dispatch popup is not displayed");

            // Test Step - 3
            dispatch.Select_Delivery_Date_on_Quick_Dispatch_Page(Delivery_Date);
            delayWithGivenTime(3000);
            int inital_delivery_count = Integer.parseInt(dispatch.Get_total_deliveries_count_from_trip_section());
            dispatch.Click_The_Invoice_In_The_Pending_Deliveries_Section(invoice1);
            softassert.assertEquals(dispatch.get_InvoiceNumber_On_TripSection(), invoice1, "Test Step 4 - Invoice number is not displaying in the Trip section");
            softassert.assertEquals(Integer.parseInt(dispatch.Get_total_deliveries_count_from_trip_section()), inital_delivery_count + 1, "Test Step 4 - Save button is not displaying in the Trip Section");
            delayWithGivenTime(3000);

            dispatch.Select_Driver("LJB Liam Jack Benjamin");
            softassert.assertEquals(dispatch.get_Selected_Driver(), "LJB Liam Jack Benjamin", "Test Step - 4 - Selected Driver is not displayed on the dropdown");

            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            dispatch.Click_DispatchSave_Button();
            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Dispatch Saved Successfully", "Test Step - 4 - Dispatch Saved Successfully toast success text message is not displayed");

            softassert.assertTrue(dispatch.verify_trip_no_is_generated_or_not(), "Test step 4 - Trip number is not generated");
            softassert.assertTrue(dispatch.verify_route_number_is_generated_or_not(), "Test step 4 - Route number is not displaying");
            softassert.assertTrue(dispatch.verify_trip_section_created_date_time_format_displaying_properly_or_not(), "Test Step 4 - Created Date Time format is not displaying properly");

            //Test Step 5
            softassert.assertTrue(dispatch.Verify_Trip_Section_IsDisplayed(), "Test Step 5 - Trip section is not displayed");
            String Route_Number = dispatch.Get_Route_number();
            dispatch.Click_SavedTrips_based_on_route_number(Route_Number);

            //dispatch.Click_confirmation_popup_yes_button();
            delayWithGivenTime(2000);

            softassert.assertEquals(dispatch.Get_Route_number(), Route_Number, "Test Step 5 - Trip section is displayed properly with respective route");

            // Test Step 6
            dispatch.Select_Driver(updateDriverName);
            softassert.assertEquals(dispatch.get_Selected_Driver(), updateDriverName, "Test Step - 6 Updated Driver is displaying properly");

            //Test Step 7
            dispatch.Click_The_Invoice_In_The_Pending_Deliveries_Section(invoice2);
            softassert.assertTrue(dispatch.verify_invoice_isDisplayed_in_trip_section(invoice2), "Test Step - 7 Added invoice is not displaying ");

            // Test Step 8
            dispatch.Delete_Invoice_from_the_Trip_section(invoice2);
            delayWithGivenTime(2000);
            softassert.assertTrue(dispatch.is_Invoice_Number_Displayed_After_Delete(invoice2), "Test Step - 8 Invoice is displaying ");

            //Test Step 9
            dispatch.Click_DispatchSave_Button();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Dispatch Saved Successfully", "Test Step - 9 - Dispatch Saved Successfully toast success text message is not displayed");
            delayWithGivenTime(2000);

            //Test Step 10
            dispatch.Click_SavedTrips_based_on_route_number(Route_Number);
            delayWithGivenTime(3000);
            //Test STep 11
            softassert.assertEquals(dispatch.get_Selected_Driver(), updateDriverName, "Test Step 11 - Updated driver is not displaying");


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

 /*   @Epic("Quick Dispatch Module")
    //,invocationCount=5 - to run the test multiple times
    @Test(enabled = false, priority = 2, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T776_Quick_Dispatch_Edit_Trip_Section_Functionality(
            String salesperson, String customername, String recifname, String recilname, String reciaddress1, String reciaddress2, String recizip, String recicity,
            String recicountry, String reciphone, String recilocation, String occasion, String searchandselectitemcode, String paymenttype) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana-Validate_Hana_T776_Quick_Dispatch_Edit_Trip_Section_Functionality  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.Verify_AdvanceDispatch_OptionIsDisplayed(), "Test Step - 2 - Advance dispatch option is not displayed");
            softassert.assertTrue(dashboard.Verify_QuickDispatch_OptionIsDisplayed(), "Test Step - 2 - Quick dispatch option is not displayed");

            // Test Step - 2
            dashboard.Hover_Dispatch_And_Click_QuickDispatch();
            dispatch = new DispatchPage();
            softassert.assertTrue(dispatch.Verify_DispatchPopup_IsDisplayed(), "Test Step - 3 - Dispatch popup is not displayed");

            // Test Step - 3
            delayWithGivenTime(3000);
            int inital_delivery_count = Integer.parseInt(dispatch.Get_total_deliveries_count_from_trip_section());
            dispatch.Click_The_Invoice_In_The_Pending_Deliveries_Section(invoice1);
            softassert.assertEquals(dispatch.get_InvoiceNumber_On_TripSection(), invoice1, "Test Step 4 - Invoice number is not displaying in the Trip section");
            softassert.assertEquals(Integer.parseInt(dispatch.Get_total_deliveries_count_from_trip_section()), inital_delivery_count + 1, "Test Step 4 - Save button is not displaying in the Trip Section");
            dispatch.Select_Driver("LJB Liam Jack Benjamin");
            softassert.assertEquals(dispatch.get_Selected_Driver(), "LJB Liam Jack Benjamin", "Test Step - 4 - Selected Driver is not displayed on the dropdown");
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            dispatch.Click_DispatchSave_Button();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Dispatch Saved Successfully", "Test Step - 4 - Dispatch Saved Successfully toast success text message is not displayed");
            softassert.assertTrue(dispatch.verify_trip_no_is_generated_or_not(), "Test step 4 - Trip number is not generated");
            softassert.assertTrue(dispatch.verify_route_number_is_generated_or_not(), "Test step 4 - Route number is not displaying");
            softassert.assertTrue(dispatch.verify_trip_section_created_date_time_format_displaying_properly_or_not(), "Test Step 4 - Created Date Time format is not displaying properly");

            //Test Step 5
            softassert.assertTrue(dispatch.Verify_Trip_Section_IsDisplayed(), "Test Step 5 - Trip section is not displayed");
            String Route_Number = dispatch.Get_Route_number();
            dispatch.Click_SavedTrips_based_on_route_number(Route_Number);

            dispatch.Click_confirmation_popup_yes_button();

            softassert.assertEquals(dispatch.Get_Route_number(), Route_Number, "Test Step 5 - Trip section is displayed properly with respective route");

            // Test Step 6
            dispatch.Select_Driver(updateDriverName);
            softassert.assertEquals(dispatch.get_Selected_Driver(), updateDriverName, "Test Step - 6 Updated Driver is displaying properly");

            //Test Step 7

            dispatch.Click_The_Invoice_In_The_Pending_Deliveries_Section(invoice2);
            softassert.assertTrue(dispatch.verify_invoice_isDisplayed_in_trip_section(invoice2), "Test Step - 7 Added invoice is not displaying ");

            // Test Step 8
            dispatch.Remove_Invoice_from_the_Trip_section(invoice2);
            delayWithGivenTime(2000);
            softassert.assertFalse(dispatch.verify_invoice_isDisplayed_in_trip_section(invoice2), "Test Step - 8 Invoice is displaying ");

            //Test Step 9
            dispatch.Click_DispatchSave_Button();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Dispatch Saved Successfully", "Test Step - 9 - Dispatch Saved Successfully toast success text message is not displayed");
            delayWithGivenTime(2000);
            //Test Step 10
            dispatch.Click_SavedTrips_based_on_route_number(Route_Number);

            //Test STep 11
            softassert.assertEquals(dispatch.get_Selected_Driver(), updateDriverName, "Test Step 11 - Updated driver is not displaying");


        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }*/
}
