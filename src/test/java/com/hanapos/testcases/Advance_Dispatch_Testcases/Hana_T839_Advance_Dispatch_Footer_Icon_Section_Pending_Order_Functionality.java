package com.hanapos.testcases.Advance_Dispatch_Testcases;

import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T839_Advance_Dispatch_Footer_Icon_Section_Pending_Order_Functionality extends TestBaseClass {
    public DashboardOrderPage dashboardOrderPage;
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private Advance_DispatchPage advanceDispatch;
    private ConfirmationPage confirmationPage;
    String invoiceNumber;
    String second_invoiceNumber;
    String automationshop_deliverydate;
    public static final String dataSheetName = "Hana_T839";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Severity(SeverityLevel.NORMAL)
    @Owner("Balaji N")
    @Epic("Advance Dispatch Module")
    @Test(enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T839_Advance_Dispatch_Footer_Icon_Section_Pending_Order_Functionality(
            String username,
            String password,
            String shopname,
            String salesperson,
            String customername,
            String recifname,
            String recilname,
            String reciaddress1,
            String reciaddress2,
            String recizip,
            String recicity,
            String recicountry,
            String reciphone,
            String recilocation,
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
            String recipientfirstname,
            String recipientlastname,
            String designer,
            String design_recipe
    ) {
        CustomSoftAssert softassert = new CustomSoftAssert();

        try {
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");

            // Test Step - 2
            lp.EnterUserName(username);
            lp.EnterPassword(password);
            softassert.assertEquals(lp.get_entered_username(), username, "Test Step - 2: Entered username is not matching with expected username as " + prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_password(), password, "Test Step - 2: Entered password is not matching with expected password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2: Page does not navigated to hana dashboard page");

            //================= Non Pending - delivery order ======================
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
            phoneorder.EnterReciFirstName(recifname);
            phoneorder.EnterReciLastName(recilname);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reciaddress1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "1160 W 5th St", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(recicountry);
            phoneorder.EnterReciPhone(reciphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
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
            phoneorder.Click_PriorityCheckBox_OnOrderDetails();
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

            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 9 - Dashboard order page is not displayed");

            // Test Step - 10
            delayWithGivenTime(1000);
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 10 - Respective Invoice number " + invoiceNumber + " is not displayed on all orders page");

            advanceDispatch = new Advance_DispatchPage();
            dashboard.Hover_Dispatch_And_Click_AdvanceDispatch();
            softassert.assertEquals(advanceDispatch.Verify_Advance_DispatchPopup_IsDisplayed(), "Hana | Advance Dispatch", "Test Step-13: Advance Dispatch Page is not displaying");
            delayWithGivenTime(3000);

            if (advanceDispatch.verifying_invalid_address_pop_up_is_displaying_or_not()) {
                advanceDispatch.click_proceed_without_address_button();
            }

            delayWithGivenTime(3000);
            advanceDispatch.Set_Delivery_Date_on_Advance_Dispatch(automationshop_deliverydate);
            delayWithGivenTime(3000);

            if (advanceDispatch.verifying_invalid_address_pop_up_is_displaying_or_not()) {
                advanceDispatch.click_proceed_without_address_button();
            }

            // Test Step - 14
            softassert.assertTrue(advanceDispatch.verify_pending_deliveries_invoice_isDisplayed(invoiceNumber), "Test Step - 14 - Pending Deliveries Invoice is not displayed for respective invoice");

            if (advanceDispatch.verifying_invalid_address_pop_up_is_displaying_or_not()) {
                advanceDispatch.click_proceed_without_address_button();
            }

            delayWithGivenTime(2000);
            softassert.assertTrue(advanceDispatch.verify_pending_deliveries_invoice_isDisplayed(invoiceNumber), "Test Step - 14 - Invoice : " + invoiceNumber + " is not displaying in the pending Deliveries section");

            // Test Step - 15
            advanceDispatch.Click_pending_deliveries_invoice(invoiceNumber);

            delayWithGivenTime(3000);
            if (advanceDispatch.multiple_order_with_same_address_popup_is_displayed()) {
                advanceDispatch.uncheck_the_ivoice_remaining_the_invoice_required_in_multiple_invoices_with_same_address(invoiceNumber);
                delayWithGivenTime(2000);
                advanceDispatch.Click_confirm_button_in_multiple_order_with_same_address_popup();
                delayWithGivenTime(2000);
            }

            softassert.assertTrue(advanceDispatch.validate_the_invoice_is_displaying_in_the_trip_section(invoiceNumber), "Invoice Order in trip section");

            // Test Step - 16
            advanceDispatch.Select_Driver(drivername);
            softassert.assertEquals(advanceDispatch.get_selected_drivername(), drivername, "Test Step - 16: Selected driver name is not displayed properly on advance dispatch page");

            // Test Step - 17
            advanceDispatch.Click_DispatchSave_Button();
            softassert.assertEquals(advanceDispatch.Validate_Success_Toaster_message_Appears(), "Dispatch Saved Successfully", "Test Step - 17: Dispatch Saved Successfully toaster message is not displayed");

            // Test Step - 18
            dashboard.ClickOrder();
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 18 - All orders page is not displayed");

            // Test Step - 19
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 10 - Respective Invoice number " + invoiceNumber + " is not displayed on all orders page");

            delayWithGivenTime(3000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 10 - Respective Invoice number: " + invoiceNumber + " is not displayed on all orders page");
            delayWithGivenTime(2000);
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "Dispatched\nDriver: Liam Jack Benjamin", "Test Step - 10 - Order status is not displayed as Dispacted for order");

            dashboardorder.Select_views_dropdown_on_all_ordersPage("Pending Confirmations");
            delayWithGivenTime(3000);
            softassert.assertEquals(dashboardorder.get_Selected_View_On_AllOrdersPage(), "Pending Confirmations", "Test Steps - 11: Selected view is not displayed on all orders page");

            delayWithGivenTime(3000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 11 - Respective Invoice number is not displayed on order page");
            softassert.assertEquals(dashboardorder.validate_Status_On_AllOrdersPage(invoiceNumber), "Dispatched\nDriver: Liam Jack Benjamin", "Test Step - 11 - Order status is not displayed as delivered for cash and carry order");

            dashboardorder.Click_Checkbox_on_AllOrdersPage_for_Respective_Invoices(invoiceNumber);
            dashboardorder.click_Confirm_Delivery_Button_On_AllOrderPages();

            // Test Step - 12
            confirmationPage = new ConfirmationPage();
            delayWithGivenTime(2000);
            softassert.assertTrue(confirmationPage.Verify_Delivery_Confirmation_Header(), "Test Step - 11 - Confirmation delivery date popup is not displayed");
            delayWithGivenTime(2000);

            confirmationPage.Select_Date_On_DeliveryConfirmationDetails(automationshop_deliverydate);
            delayWithGivenTime(2000);
            confirmationPage.Select_ToDate_On_DeliveryConfirmationDetails(automationshop_deliverydate);
            delayWithGivenTime(2000);
            confirmationPage.click_Search_Button_On_Delivery_Confirmation_Details();
            delayWithGivenTime(2000);

            if (confirmationPage.Confirmation_Popup_Verification()) {
                confirmationPage.Confirming_the_confirmation_pop_up();
            }
            confirmationPage.Enter_RecipientInvoice_ConfirmationPage(invoiceNumber);
            delayWithGivenTime(2000);
            softassert.assertEquals(confirmationPage.get_entered_RecipientInvoice_ConfirmationPage(), invoiceNumber, "Test Step - 13 - Recipient Invoice is not matched with entered invoice number on confirmation page");

            // Test Step - 13
            confirmationPage.select_delivery_code_Confirmation_Page("Not Delivered");
            softassert.assertEquals(confirmationPage.get_selected_delivery_code_Confirmation_Page(), "Not Delivered", "Test Step - 13 - Selected delivery code is not displayed properly on confirmation page");

            confirmationPage.confirm_selected_deliveries_button_click_Confirmation_page();
            softassert.assertTrue(phoneorder.verifySuccessToastMessageAppears(), "Test Step - 13 - Delivery Confirmation Toaster message is not displayed");
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Delivery Confirmation done successfully", "Test Step - 13 - Delivery Confirmation Toaster message is not displayed");
            confirmationPage.confirm_delivery_page_close_icon_click();

            // Test Step - 14
            dashboard.ClickOrder();
            delayWithGivenTime(2000);
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 14 - Respective Invoice number is not displayed on all orders page");


            //=========== Pending Delivered Order ===========//
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
            phoneorder.EnterReciFirstName(recipientfirstname);
            phoneorder.EnterReciLastName(recipientlastname);
            softassert.assertEquals(phoneorder.getReciFirstName(), recipientfirstname, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recipientlastname, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reciaddress1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "1160 W 5th St", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.SelectReciCountry(recicountry);
            phoneorder.EnterReciPhone(reciphone);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(1000);

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
            second_invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 9
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 9 - Dashboard order page is not displayed");

            // Test Step - 10
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 10 - Respective Invoice number is not displayed on all orders page");
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(second_invoiceNumber), "Test Step - 10 - Respective Invoice number is not displayed on all orders page");


            //=========== Above are the pre-requiste for the test case ===========//
            // Test Step - 13
            dashboard.Hover_Dispatch_And_Click_AdvanceDispatch();
            delayWithGivenTime(3000);
            softassert.assertTrue(advanceDispatch.verify_Advance_Dispatch_Page_IsDisplayed(), "Test Step - 13 - Advance Dispatch page is not displayed");

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

            // Test Step - 14
            softassert.assertTrue(advanceDispatch.is_Invoice_Number_Displayed_In_Pending_Deliveries_Section(second_invoiceNumber), "Test Step - 14 - Invoice is not displaying in the pending Deliveries section");

            // Test Step - 15
            delayWithGivenTime(2000);
            softassert.assertTrue(advanceDispatch.verify_I_Icon_For_Invoice_Is_Displayed(second_invoiceNumber), "Test Step - 15 - I icon is not displayed for invoice number: " + invoiceNumber);
            delayWithGivenTime(2000);
            advanceDispatch.click_I_Icon_For_Invoice_Is_Displayed(second_invoiceNumber);
            delayWithGivenTime(2000);
            // softassert.assertTrue(advanceDispatch.verify_Highlighted_ToolTip_On_Map_View_IsDisplayed(), "Test Step - 15 - Highlighted tooltip is not displayed on map view");

          /*

           Due to common element for all map icon it is failing we are not able to automate properly,
           // Test Step - 16
            delayWithGivenTime(2000);
            softassert.assertTrue(advanceDispatch.verify_I_Icon_For_Invoice_Is_Displayed(invoiceNumber), "Test Step - 16 - I icon is not displayed for invoice number: " + second_invoiceNumber);
            delayWithGivenTime(2000);
            advanceDispatch.click_I_Icon_For_Invoice_Is_Displayed(invoiceNumber);
            softassert.assertFalse(advanceDispatch.verify_Highlighted_ToolTip_On_Map_View_IsNotDisplayed(), "Test Step - 16 - Highlighted tooltip is not displayed on map view");
*/
        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();

        }
    }
}
