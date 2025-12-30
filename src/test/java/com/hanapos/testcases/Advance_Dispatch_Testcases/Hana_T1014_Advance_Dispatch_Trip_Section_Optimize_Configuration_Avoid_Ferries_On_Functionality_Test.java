package com.hanapos.testcases.Advance_Dispatch_Testcases;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
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

public class Hana_T1014_Advance_Dispatch_Trip_Section_Optimize_Configuration_Avoid_Ferries_On_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private Advance_DispatchPage advanceDispatchPage;
    String invoice_number, invoice_number2;
    String routenumber;
    String automationshop_deliverydate;

    public static final String dataSheetName = "Hana_T1005";
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Epic("Advance Dispatch Module")
    @Test(groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T1014_Advance_Dispatch_Trip_Section_Optimize_Configuration_Avoid_Ferries_On_Functionality_Test(
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
            String recipientstate,
            String recipientcountry,
            String recipientfname2,
            String recipientlname2,
            String recipientaddress2,
            String recipientfulladdress2,
            String recipientzipcode2,
            String recipientcity2,
            String recipientstate2,
            String recipientcountry2,
            String recipientphone,
            String recipientlocation,
            String deliveryontime,
            String occasion,
            String short_card_message,
            String cardmessage,
            String itemcode,
            String itemdescription,
            String itemquantity,
            String itemprice,
            String paymenttype,
            String ordertype,
            String deliverytype,
            String mop) {
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
            automationshop_deliverydate = phoneorder.getDeliveryDateOnReciSection();

            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(short_card_message, cardmessage);
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

            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 4 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 5
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 5 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoice_number = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            //===================== Second Invoice Order ===================================
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

            phoneorder.EnterReciFirstName(recipientfname2);
            phoneorder.EnterReciLastName(recipientlname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recipientfname2, "Test Step - 3 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recipientlname2, "Test Step - 3 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(recipientfulladdress2);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), recipientaddress2, "Test Step - 3 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), recipientzipcode2, "Test Step - 3 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), recipientcity2, "Test Step - 3 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), recipientstate2, "Test Step - 3 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(recipientcountry2);
            phoneorder.EnterReciPhone(recipientphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recipientlocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown(deliveryontime);
            delayWithGivenTime(1000);

            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(occasion);
            phoneorder.EnterViewShortCode(short_card_message, cardmessage);
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

            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 4 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 5
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 5 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoice_number2 = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();


            // Test Step - 6
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);

            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 6 - Dashboard order page is not displayed");
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice_number), "Test Step - 6 - Respective Invoice number is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice_number), "New", "Test Step - 6 - Respective Invoice status is not displayed on all orders page");
            delayWithGivenTime(2000);

            advanceDispatchPage = new Advance_DispatchPage();
            dashboard.Hover_Dispatch_And_Click_AdvanceDispatch();
            delayWithGivenTime(2000);
            // Skip the invalid address pop-up
            if (advanceDispatchPage.verifying_invalid_address_pop_up_is_displaying_or_not()) {
                //  PressEscapeKey();
                advanceDispatchPage.click_proceed_without_address_button();
            }

            delayWithGivenTime(3000);
            advanceDispatchPage.Set_Delivery_Date_on_Advance_Dispatch(automationshop_deliverydate);
            delayWithGivenTime(3000);

            // Skip the invalid address pop-up
            if (advanceDispatchPage.verifying_invalid_address_pop_up_is_displaying_or_not()) {
                advanceDispatchPage.click_proceed_without_address_button();
            }

            delayWithGivenTime(2000);
            delayWithGivenTime(2000);
            softassert.assertTrue(advanceDispatchPage.is_Invoice_Number_Displayed_In_Pending_Deliveries_Section(invoice_number), "Test Step - 14 - Invoice number " + invoice_number + " is not displaying in the pending Deliveries section");

            // Test Step - 15
            delayWithGivenTime(2000);
            advanceDispatchPage.Click_pending_deliveries_invoice(invoice_number);
            delayWithGivenTime(2000);

            // skip the multiple order with same address pop-up
            if (advanceDispatchPage.multiple_order_with_same_address_popup_is_displayed()) {
                advanceDispatchPage.uncheck_the_ivoice_remaining_the_invoice_required_in_multiple_invoices_with_same_address(invoice_number);
                advanceDispatchPage.Click_confirm_button_in_multiple_order_with_same_address_popup();
            }

            delayWithGivenTime(3000);
            softassert.assertTrue(advanceDispatchPage.validate_the_invoice_is_displaying_in_the_trip_section(invoice_number), "Test Step - 15 - Invoice is not displaying in the Trip Section");

            // Test Step - 16
            advanceDispatchPage.Select_Driver(prop.getProperty("Assigned_DriverName"));
            softassert.assertEquals(advanceDispatchPage.get_selected_drivername(), prop.getProperty("Assigned_DriverName"), "Test Step - 16 - Selected Driver Is not displaying");

            //Test Step - 17
            advanceDispatchPage.Click_DispatchSave_Button();

            softassert.assertTrue(phoneorder.verifySuccessToastMessageAppears(), "Test Step - 17 - Success Toaster Message is not displaying");
            delayWithGivenTime(2000);
            // Skip the invalid address pop-up
            if (advanceDispatchPage.verifying_invalid_address_pop_up_is_displaying_or_not()) {
                // PressEscapeKey();
                advanceDispatchPage.click_proceed_without_address_button();
            }
            delayWithGivenTime(2000);
            softassert.assertTrue(advanceDispatchPage.verify_pending_deliveries_invoice_isDisplayed(invoice_number2), "Test Step - 14 - Invoice is not displaying in the pending Deliveries section");

            // Test Step - 15
            advanceDispatchPage.Click_pending_deliveries_invoice(invoice_number2);
            delayWithGivenTime(2000);

            // skip the multiple order with same address pop-up
            if (advanceDispatchPage.multiple_order_with_same_address_popup_is_displayed()) {
                advanceDispatchPage.uncheck_the_ivoice_remaining_the_invoice_required_in_multiple_invoices_with_same_address(invoice_number2);
                advanceDispatchPage.Click_confirm_button_in_multiple_order_with_same_address_popup();
            }

            delayWithGivenTime(3000);
            softassert.assertTrue(advanceDispatchPage.validate_the_invoice_is_displaying_in_the_trip_section(invoice_number2), "Test Step - 15 - Invoice is not displaying in the Trip Section");
            advanceDispatchPage.Click_DispatchSave_Button();

            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            delayWithGivenTime(1500);
            softassert.assertTrue(phoneorder.verifySuccessToastMessageAppears(), "Test Step - 17 - Success Toaster Message is not displaying");
            routenumber = advanceDispatchPage.get_Actual_Route_Number_Displayed_In_Trip_Section();
            delayWithGivenTime(2000);

            softassert.assertTrue(advanceDispatchPage.verify_Dispatch_Icon_On_Trip_Section(), "Test Step - 16: Dispatch yellow icon is not displayed on trip section");
            softassert.assertTrue(advanceDispatchPage.verify_Route_Number_IsDisplayed_In_Trip_Section(), "Test Step - 16: Route number along with driver name is not displayed on trip section");
            softassert.assertTrue(advanceDispatchPage.is_TimeStamp_Displayed_On_TripSection(), "Test Step - 16: Time Stamp on trip section - Advance dispatch page");
            // softassert.assertEquals(advanceDispatchPage.get_Actual_TimeStamp_Displayed_On_Trip_Section(), Atlantic_TimeZone_In_MM_dd_yyyy_hhmm(), "Test Step - 16: Time Stamp on trip section - Advance dispatch page");
            softassert.assertTrue(advanceDispatchPage.is_TripNumber_Displayed_On_Trip_Section(), "Test Step - 16: Trip number is not displayed on trip section");

            // Test Step - 18
            advanceDispatchPage.click_Route_Dropdown_In_Trip_Section();
            softassert.assertTrue(advanceDispatchPage.is_Existing_Trip_Displayed(routenumber), "Test Step - 17: Saved trip invoice number : " + invoice_number + " is not displayed in saved trip section");
            softassert.assertTrue(advanceDispatchPage.is_NewRoute_Option_Displayed_On_SavedTrip_Dropdown(), "Test Step - 17: New route option is not displayed on saved trip dropdown");

            delayWithGivenTime(2000);
            advanceDispatchPage.select_Existing_Trip(routenumber);
            delayWithGivenTime(2000);

            softassert.assertTrue(advanceDispatchPage.verify_Dispatch_Icon_On_Trip_Section(), "Test Step - 16: Dispatch yellow icon is not displayed on trip section");
            softassert.assertTrue(advanceDispatchPage.verify_Route_Number_IsDisplayed_In_Trip_Section(), "Test Step - 16: Route number along with driver name is not displayed on trip section");
            softassert.assertTrue(advanceDispatchPage.is_TimeStamp_Displayed_On_TripSection(), "Test Step - 16: Time Stamp on trip section - Advance dispatch page");
            //   softassert.assertEquals(advanceDispatchPage.get_Actual_TimeStamp_Displayed_On_Trip_Section(), Atlantic_TimeZone_In_MM_dd_yyyy_hhmm(), "Test Step - 16: Time Stamp on trip section - Advance dispatch page");
            softassert.assertTrue(advanceDispatchPage.is_TripNumber_Displayed_On_Trip_Section(), "Test Step - 16: Trip number is not displayed on trip section");
            delayWithGivenTime(2000);

            // Test Step - 19
            softassert.assertTrue(advanceDispatchPage.is_Optimize_Configuration_Settings_Icon_Displayed_On_TripSection(), "Test Step - 19: Optimize route settings icon is not displayed on trip section");
            delayWithGivenTime(2000);
            advanceDispatchPage.click_Optimize_Configuration_Settings_Icon_On_TripSection();

            // Test Step - 20
            delayWithGivenTime(2000);
            softassert.assertTrue(advanceDispatchPage.is_Optimize_Route_Settings_Popup_Displayed(), "Test Step - 20: Optimize Configuration popup header is not displayed on trip section");

            // Test Step - 21
            advanceDispatchPage.turn_ON_Avoid_Ferries_Toogle_On_Optimize_Configuration_Popup();

            // Test Step - 22
            advanceDispatchPage.click_Close_Button_On_Optimize_Configuration_Popup();

            // Need to verify by manually..........


        } catch (Exception e) {
            softassert.fail(e.getMessage());
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            softassert.assertAll();
        }
    }


}
