package com.hanapos.testcases.Advance_Dispatch_Testcases;

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

public class Hana_T900_Advance_Dispatch_VerifyAddress_With_Invalid_Address_Functionality_Test extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private Advance_DispatchPage advanceDispatchPage;
    String invoice_number;
    String automationshop_deliverydate;

    public static final String dataSheetName = "Hana_T900";
    public static LoggerUtil logger_Util;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Epic("Advance Dispatch Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T900_Advance_Dispatch_VerifyAddress_With_Invalid_Address_Functionality_Test(
            String username,
            String password,
            String shopname,
            String salesperson,
            String customername,
            String recipientfname,
            String recipientlname,
            String recifname,
            String recilname,
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
            String itemquantity,
            String itemprice,
            String paymenttype,
            String ordertype,
            String deliverytype,
            String mop,
            String mapview,
            String current_trip_fulladdress,
            String current_trip_address1,
            String current_trip_zipcode,
            String current_trip_city,
            String current_trip_state) {
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

            Faker faker = new Faker(new Locale("en-US"));
            String recifname1;
            String recilname2;
            String reci_phone_number1;
            String reci_phone_number2;
            String floor_number;

            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.EnterReciAddress1(recipientfulladdress1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), recipientfulladdress1, "Test Step - 3 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), recipientzipcode, "Test Step - 3 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), recipientcity, "Test Step - 3 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "FL", "Test Step - 3 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(recipientcountry);
            phoneorder.EnterReciPhone(recipientphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recipientlocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(Next_10Days_Date());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(18, 30);
            phoneorder.Select_DeliveryOnTime_Dropdown(deliveryontime);
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), Next_10Days_Date(), "Test Step - 3: Selected delivery date as " + CurrentDate() + " is not displayed");
            automationshop_deliverydate = phoneorder.getDeliveryDateOnReciSection();

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

            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 4 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 5
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 5 - Order confirmation page is not displayed");
            delayWithGivenTime(500);
            invoice_number = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 6
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 6 - Dashboard order page is not displayed");
            delayWithGivenTime(2000);
            dashboardorder.select_Date_On_DateSelection_DatePicker(automationshop_deliverydate);
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice_number), "Test Step - 6 - Respective Invoice number is not displayed on all orders page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoice_number), "New", "Test Step - 6 - Respective Invoice status is not displayed on all orders page");

            // Test Step - 7
            delayWithGivenTime(2000);
            advanceDispatchPage = new Advance_DispatchPage();
            dashboard.Hover_Dispatch_And_Click_AdvanceDispatch();

            if (advanceDispatchPage.verifying_invalid_address_pop_up_is_displaying_or_not()) {
                advanceDispatchPage.click_proceed_without_address_button();
            }
            delayWithGivenTime(2000);
            advanceDispatchPage.Set_Delivery_Date_on_Advance_Dispatch(automationshop_deliverydate);

            delayWithGivenTime(3000);
            softassert.assertEquals(advanceDispatchPage.get_Displayed_Delivery_Date_On_Advance_Dispatch(), automationshop_deliverydate, "Test Step - 8: Selected delivery date is not displayed on advance dispatch page");

            delayWithGivenTime(2000);
            softassert.assertTrue(advanceDispatchPage.verifying_invalid_address_pop_up_is_displaying_or_not(), "Test Step - 7 - Invalid Address pop-up is not displayed on advance dispatch page");
            softassert.assertTrue(advanceDispatchPage.is_Verify_Address_Button_Displayed_In_Invalid_Address_Popup(), "Test Step - 7 - Verify Address button is not displayed in the Invalid Address pop-up");

            advanceDispatchPage.Click_Verify_Address_Button_In_Invalid_Address_Popup();
            softassert.assertEquals(advanceDispatchPage.get_Invalid_Address_Popup_Label(), "Google Maps not able to verify some addresses above. Please correct the addresses before proceeding.", "Test Step - 7 - Verify Address button is not displayed in the Invalid Address pop-up");

            advanceDispatchPage.Enter_Full_Address_In_Invalid_Address_Popup(invoice_number, current_trip_fulladdress);
            advanceDispatchPage.Click_Verify_Address_Button_In_Invalid_Address_Popup();

            delayWithGivenTime(2000);
            softassert.assertTrue(advanceDispatchPage.is_Invoice_Number_Displayed_In_Pending_Deliveries_Section(invoice_number), "Test Step - 8 - Future date Invoice is not displaying in the pending Deliveries section");
            softassert.assertEquals(advanceDispatchPage.get_pending_deliveries_invoice(invoice_number), "" + recifname1 + " " + recilname2 + "\n" +
                    "481 Lakeshore Pkwy Rock Hill", "Test Step - 8 - Updated & verified address is not displaying in the pending Deliveries section for the respective invoice");


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
