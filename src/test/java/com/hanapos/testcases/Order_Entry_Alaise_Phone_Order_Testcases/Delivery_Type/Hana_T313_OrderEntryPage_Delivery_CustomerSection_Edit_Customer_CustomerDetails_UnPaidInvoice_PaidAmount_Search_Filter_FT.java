package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import java.io.IOException;

import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.pageObjects.Order_Confirmation_Page;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T313_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_PaidAmount_Search_Filter_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;

    String invoiceNumber;

    public static final String dataSheetName = "Hana_T106";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T313_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_PaidAmount_Search_Filter_Test(String valid_paid_amount, String twodigit_paid_amount, String invalid_paid_amount) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana_T313_OrderEntryPage_Delivery_CustomerSection_Edit_Customer_CustomerDetails_UnPaidInvoice_PaidAmount_Search_Filter_FT  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();

            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana dashboard page is not displayed");
            logger.info("User navigated to hana dashboard page");

            // Pre- requiste
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c", "Test Step - 5 - Delivery type is not highlighted in blue color");

            // Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
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
            phoneorder.EnterReciFirstName(prop.getProperty("cust_firstName"));
            phoneorder.EnterReciLastName(prop.getProperty("cust_lastName"));
            phoneorder.EnterReciAddress1(prop.getProperty("cust_address1"));
            phoneorder.EnterReciAddress2(prop.getProperty("recipient_address2"));
            phoneorder.EnterReciZipcode(prop.getProperty("cust_zipcode"));
            delayWithGivenTime(1000);
            //phoneorder.EnterReciCity(recicity);
            phoneorder.SelectReciCountry(prop.getProperty("recipient_country1"));
            phoneorder.EnterReciPhone(prop.getProperty("cust_phoneNumber"));
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(prop.getProperty("recipient_location1"));
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), prop.getProperty("cust_firstName"), "Test Step - 7 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), prop.getProperty("cust_lastName"), "Test Step - 7 - Entered last name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), prop.getProperty("cust_address1"), "Test Step - 7 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), prop.getProperty("recipient_address2"), "Test Step - 7 - Entered address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), prop.getProperty("cust_zipcode"), "Test Step - 7 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), prop.getProperty("cust_city"), "Test Step - 7 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), prop.getProperty("recipient_country1"), "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), prop.getProperty("cust_phoneNumber"), "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), prop.getProperty("recipient_location1"), "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_description1"), "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            } else if (phoneorder.getUnitPriceOnProdDetails() == "309.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "309.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            }

            delayWithGivenTime(2000);
            // Test Step - 10
            // Invoice/House Account - payment method
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type_invoice"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("payment_type_invoice"), "Test Step - 10 - Selected payment type is not displayed");

            // Test Step - 11
            softassert.assertTrue(phoneorder.Verify_PO_NumberField_IsDisplayed_On_InvoiceHouseAccount_PaymentSection(), "Test Step - 11 - Po Number field is not displayed on invoice house account payment section");
            softassert.assertTrue(phoneorder.Verify_Payment_TermsField_IsDisplayed_On_InvoiceHouseAccount_PaymentSection(), "Test Step - 11 - payment terms field is not displayed on invoice house account payment section");

            // Test Step - 12
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

            dashboard.ClickOnHomeIcon();

            //=======================================================================
            delayWithGivenTime(3000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 : Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 : Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c", "Test Step - 5 - Delivery type is not highlighted in blue color");

            //Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            delayWithGivenTime(2000);
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Abish", "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "David", "Test Step - 6 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Hana_Sisterchicks", "Test Step - 6 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "hanaposqateam@gmail.com", "Test Step -6 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "3402 Park Blvd", "Test Step - 6 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 6 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "92103", "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Diego", "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 6 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 6 - Alt phone number is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 7
            softassert.assertTrue(phoneorder.Verify_CustEditIcon_Appears(), "Test Step - 7 - Customer clear button is not displayed on phone order page");

            // Test Step  - 8
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 8 - Customer details popup on is not displayed on phone order page");

            // Test Step - 9
            delayWithGivenTime(1000);
            phoneorder.Click_UnpaidInvoiceTab_OnCustDetailsPopup();
            delayWithGivenTime(1000);

            phoneorder.Enter_InvoiceSearchext_UnpaidInvoiceTab(invoiceNumber);
            delayWithGivenTime(1000);
            phoneorder.PressEnterKey();
            delayWithGivenTime(1000);
            phoneorder.Click_LastRowCheckbox_OnUnpaidInvoiceTable_UnpaidInvoiceTab();
            delayWithGivenTime(1000);
            phoneorder.Click_MakePaymentBtn_InvoicePopup_OnUnpaidInvoiceTab();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_InvoicePaymentDetails_UnpaidInvoiceTab_OnCustDetailsPopup(), "Test Step - 14 - Invoice payment details section is not displayed on phone order page");
            delayWithGivenTime(2000);
            phoneorder.Select_PaymentMethod_OnUnpaidInvoiceTab(prop.getProperty("MOP_As_Cash"));
            phoneorder.Select_CashRegistry_OnUnpaidInvoiceTab(prop.getProperty("payment_cash_registry"));

            phoneorder.Enter_PaymentAmount_InvoiceDetails_UnpaidInvoiceTab(prop.getProperty("unpaid_Invoice_payment_amount"));
            phoneorder.Click_SubmitButton_InvoiceDetails_UnpaidInvoiceTab();
            delayWithGivenTime(1000);
            phoneorder.ClickBackButton_ConfirmationSection_UnpaidInvoiceTab();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_UnpaidInvoiceTable_UnpaidInvoiceTab(), "Test Step - 9 - Unpaid invoice table is not displayed on unpaid invoice tab in customer details popup on phone order page");
            delayWithGivenTime(1000);

            // Test Step - 10
            phoneorder.Enter_PaidAmount_OnInvoiceSearchBox_UnpaidInvoiceTab(valid_paid_amount);

            // Test Step - 11
            RobotPressEnter();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_PaidAmount_UnpaidInvoiceTable_OnUnpaidInvoiceTab(), "100",
                    "Test Step - 11 Displayed paid amount is did not matched with previously searched invoice number on unpaid invoice grid table");

            // Test Step - 12
            phoneorder.Enter_PaidAmount_OnInvoiceSearchBox_UnpaidInvoiceTab(twodigit_paid_amount);

            // Test Step 13
            RobotPressEnter();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_PaidAmount_UnpaidInvoiceTable_OnUnpaidInvoiceTab(), "50",
                    "Test Step - 13 Searched and Entered 2 digits paid amount invoice is did not displayed on the unpaid invoice grid table");

            // Test Step - 14
            phoneorder.Enter_PaidAmount_OnInvoiceSearchBox_UnpaidInvoiceTab(invalid_paid_amount);
            RobotPressEnter();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_PaidAmount_UnpaidInvoiceTable_OnUnpaidInvoiceTab(), "12345",
                    "Test Step - 14 Displayed paid amount is did not matched with previously searched invoice number on unpaid invoice grid table");
            softassert.assertTrue(phoneorder.Verify_NoUnpaidInvoiceFound(), "Test step - 14 - No unpaid invoice found message is not displayed in unpaid invoice grid table");

            // Test Step - 15
            softassert.assertTrue(phoneorder.Verify_ClearButton_AppearsOnPaidAmtSearchtextbox_UnpaidInvoiceTab(), "Test Step - 15 - Clear button is not displayed on invoice search textbox at unpaid invoice tab in customer details popup");

            // Test Step - 16
            delayWithGivenTime(1000);
            phoneorder.Click_Clearbutton_PaidAmtsearchtextbox_UnpaidInvoiceTab();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_PaidAmount_UnpaidInvoiceTable_OnUnpaidInvoiceTab(), "", "Test Step - 16 - Entered invoice number on search text box is not getting cleared ");
            softassert.assertNotEquals(phoneorder.Verify_ListofInvoiceNumbers_Appears_InUnpaidInvoiceTable_OnUnpaidInvoiceTab(), 0, "Test Step - 16 - Unpaid Invoices are not displayed in unpaid invoice table on unpaid invoice tab customer details popup");
            /*
             *
             * // Pre - requisite for Test Step - 10 delayWithGivenTime(1000);
             * phoneorder.Click_Customer_DetailsPopupCloseBtn(); delayWithGivenTime(2000);
             *
             * phoneorder.SearchAndSelectCustomerOnCust_Section("Abish");
             * delayWithGivenTime(2000);
             *
             * phoneorder.EnterReciFirstName("Abish");
             * phoneorder.EnterReciLastName("David");
             * phoneorder.EnterDeliveryDateOnReciSection(); delayWithGivenTime(2000);
             * phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage("Birthday");
             * phoneorder.EnterViewShortCode(); delayWithGivenTime(2000);
             * phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(
             * "rrd","rrd-Red Rose Deluxe"); delayWithGivenTime(2000); phoneorder.
             * SelectPaymentTypeOnPhoneOrderPage_PaymentSection("Invoice/House Account");
             * delayWithGivenTime(1000);
             *
             * phoneorder.ClickPlaceOrderButton(); delayWithGivenTime(1000);
             * getDriver().switchTo().activeElement(); delayWithGivenTime(2000);
             * phoneorder.ClickSubmitButton_On_ConfirmationPopup();
             * delayWithGivenTime(2000); orderconfirmationpage = new
             * Order_Confirmation_Page();
             * softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(),
             * "Test Step - 13 - Order confirmation page is not displayed");
             * delayWithGivenTime(2000);
             *
             * dashboard.ClickOnHomeIcon(); delayWithGivenTime(2000);
             * dashboard.ClickOrderEntry(); delayWithGivenTime(2000); phoneorder = new
             * OrderEntry_Alais_PhoneOrderPage();
             * phoneorder.ClickPickupTypeOnPhoneOrderPage(); delayWithGivenTime(2000);
             * softassert.assertEquals(phoneorder.
             * getHighlightedColorOnPickupTypeOnPhoneOrderPage(),"#2f9bc8",
             * "Test Step - 5 - Pickup type is not highlighted in blue color");
             *
             * delayWithGivenTime(2000);
             * phoneorder.SearchAndSelectCustomerOnCust_Section("Abish");
             * delayWithGivenTime(2000); phoneorder.Click_CustEditIcon();
             *
             * delayWithGivenTime(1000);
             * phoneorder.Click_UnpaidInvoiceTab_OnCustDetailsPopup();
             * delayWithGivenTime(1000);
             *
             * phoneorder.Click_LastRowCheckbox_OnUnpaidInvoiceTable_UnpaidInvoiceTab();
             *
             * phoneorder.Click_MakePaymentBtn_InvoicePopup_OnUnpaidInvoiceTab();
             *
             * softassert.assertTrue(phoneorder.
             * Verify_InvoicePaymentDetails_UnpaidInvoiceTab_OnCustDetailsPopup()
             * ,"Test Step - 14 - Invoice payment details section is not displayed on phone order page"
             * ); delayWithGivenTime(2000);
             * phoneorder.Select_PaymentMethod_OnUnpaidInvoiceTab("Cash");
             * phoneorder.Select_CashRegistry_OnUnpaidInvoiceTab("Cash Register2");
             *
             * phoneorder.Enter_PaymentAmount_InvoiceDetails_UnpaidInvoiceTab("50");
             * phoneorder.Click_SubmitButton_InvoiceDetails_UnpaidInvoiceTab();
             * delayWithGivenTime(1000);
             * phoneorder.ClickBackButton_ConfirmationSection_UnpaidInvoiceTab();
             * delayWithGivenTime(1000);
             * softassert.assertTrue(phoneorder.Verify_UnpaidInvoiceTable_UnpaidInvoiceTab()
             * ,"Test Step - 21 - Unpaid invoice table is not displayed on unpaid invoice tab in customer details popup on phone order page"
             * ); delayWithGivenTime(1000);
             */
        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}
