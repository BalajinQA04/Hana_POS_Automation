package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import com.hanapos.pageObjects.Order_Confirmation_Page;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T121_Pickup_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_PaymentAmountSearchFilter_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    String invoiceNumber;
    String pay_amount;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T121_Pickup_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_PaymentAmountSearchFilter_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Hana_T121_Pickup_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_PaymentAmountSearchFilter_FT  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("username"), "Test Step - 2: Entered username is not matching with expected username");
            logger.info("User entered the username as " + prop.getProperty("username"));

            lp.EnterPassword(prop.getProperty("password"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("password"), "Test Step - 2: Entered password is not matching with expected password");
            logger.info("User entered the password as " + prop.getProperty("password"));

            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2: Page did not navigate to hana dashboard page");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3: Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3: Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Pickup type is not highlighted in blue color");

            // Test Step - 6
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
            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            phoneorder.SelectReciCountry(prop.getProperty("recipient_country1"));
            phoneorder.EnterReciPhone(prop.getProperty("recipient_phonenumber1"));
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(prop.getProperty("recipient_location1"));
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 7 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 7 - Entered last name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), prop.getProperty("recipient_address1"), "Test Step - 7 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "PICK UP", "Test Step - 7 - Entered address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), prop.getProperty("recipient_zipcode1"), "Test Step - 7 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), prop.getProperty("recipient_city1"), "Test Step - 7 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), prop.getProperty("recipient_phonenumber1"), "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
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
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.get_ExtPrice1OnProdDetails(), prop.getProperty("product_ext_price"), "Test Step - 9 - Item ext price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 10
            // Cash - payment method
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("payment_type"), "Test Step - 10 - Selected payment type " + prop.getProperty("payment_type") + " is not displayed");

            // Test Step - 11
            delayWithGivenTime(1000);
            phoneorder.EnterCashAmount(phoneorder.get_GrandTotal_Amount_with_single_Decimal());
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Entered_CashPaymentType_Amount(), phoneorder.get_GrandTotal_Amount_with_single_Decimal(), "Test Step - 11 - Entered more than cash payment amount is not allowed on the payment section cash type pay amount field");

            // Test Step - 12
            phoneorder.SelectCashRegistry_On_CashPaymentType(prop.getProperty("payment_cash_registry"));

            // Test Step - 13
            delayWithGivenTime(1000);
            //  softassert.assertEquals(phoneorder.Verify_Change_GivenBackTo_Customer_amount_IsDisplayed(), "100.00", "Test Step - 12 - Change given back to customer amount label is not displayed under the cash payment type select registered dropdown");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Selected_CashRegistry(), prop.getProperty("payment_cash_registry"), "Test Step - 13 - Selected cash payment registered is not displayed");
            pay_amount = phoneorder.get_GrandTotal_Amount_with_single_Decimal();

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
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3: Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3: Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Pickup type is not highlighted in blue color");

            //Test Step - 6
            delayWithGivenTime(2000);
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("custfullname"));
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
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 8 - Customer details popup on is not displayed on phone order page");

            // Test Step - 8
            delayWithGivenTime(1000);
            phoneorder.ClickOn_PaymentDetails_CustomerDetailsPopup();
            delayWithGivenTime(4000);
            softassert.assertTrue(phoneorder.Verify_TableGridOnPaymentDetailsTab_IsAppear(), "Test Step - 9 - payment details tab webtable grid is not displayed");

            // Test Step - 9
            delayWithGivenTime(1000);
            phoneorder.Enter_PayAmountSearchBox_OnPaymentDetailsTab(pay_amount);

            // Test Step - 10
            //RobotPressEnter();
            delayWithGivenTime(3000);
            softassert.assertTrue(phoneorder.Verify_PayAmount_Paymentdetailstab("$" + pay_amount), "Known Issue - Test Step - 10 - Filtered Pay Amount is not displayed on the payment details tab at customer details popup");

            // Test Step - 11
            phoneorder.Enter_PayAmountSearchBox_OnPaymentDetailsTab("50");

            // Test Step - 12
            // RobotPressEnter();
            delayWithGivenTime(3000);
            softassert.assertTrue(phoneorder.Verify_PayAmount_Paymentdetailstab("$50"), "Known Issue - Test Step - 12 - Filtered Pay Amount is not displayed on the payment details tab at customer details popup");

            // Test Step - 13
            phoneorder.Enter_PayAmountSearchBox_OnPaymentDetailsTab("11111");
            //  RobotPressEnter();
            delayWithGivenTime(3000);
            softassert.assertTrue(phoneorder.Verify_NoCustomerPaymentsFound_PaymentDetailsTab(), "Test Step - 13 - Filtered Pay Amount is not displayed on the payment details tab at customer details popup");

            // Test Step - 14
            softassert.assertTrue(phoneorder.Verify_PayAmountSearchBox_ClearButton_Appears(), "Test Step - 14 - Clear button in Pay Amount search box is not displayed");

            // Test Step - 15
            phoneorder.Click_PayAmountSearchBox_ClearButton();
            softassert.assertEquals(phoneorder.get_PayAmount_Paymentdetailstab(), "", "Test Step - 15 - In Pay Amount searchbox entered data is not cleared");

            //======================================= Pre - requisite for Test Case ===================================================
            phoneorder.Click_Customer_DetailsPopupCloseBtn();
            delayWithGivenTime(2000);
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));

            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
            delayWithGivenTime(2000);

            phoneorder.EnterReciFirstName(prop.getProperty("cust_firstName"));
            phoneorder.EnterReciLastName(prop.getProperty("cust_lastName"));
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            phoneorder.EnterViewShortCode();
            delayWithGivenTime(2000);
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(
                    prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
            delayWithGivenTime(2000);
            phoneorder.
                    SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type_invoice"));
            delayWithGivenTime(1000);

            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);
            getDriver().switchTo().activeElement();
            delayWithGivenTime(2000);
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new
                    Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(),
                    "Test Step - 13 - Order confirmation page is not displayed");
            delayWithGivenTime(2000);

            dashboard.ClickOnHomeIcon();
            delayWithGivenTime(2000);
            dashboard.ClickOrderEntry();
            delayWithGivenTime(2000);
            phoneorder = new
                    OrderEntry_Alais_PhoneOrderPage();
            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.
                            getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8",
                    "Test Step - 5 - Pickup type is not highlighted in blue color");

            delayWithGivenTime(2000);
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
            delayWithGivenTime(2000);
            phoneorder.Click_CustEditIcon();

            delayWithGivenTime(1000);
            phoneorder.Click_UnpaidInvoiceTab_OnCustDetailsPopup();
            delayWithGivenTime(1000);

            phoneorder.Click_LastRowCheckbox_OnUnpaidInvoiceTable_UnpaidInvoiceTab();

            phoneorder.Click_MakePaymentBtn_InvoicePopup_OnUnpaidInvoiceTab();

            softassert.assertTrue(phoneorder.
                            Verify_InvoicePaymentDetails_UnpaidInvoiceTab_OnCustDetailsPopup()
                    , "Test Step - 14 - Invoice payment details section is not displayed on phone order page"
            );
            delayWithGivenTime(2000);
            phoneorder.Select_PaymentMethod_OnUnpaidInvoiceTab(prop.getProperty("payment_type"));
            phoneorder.Select_CashRegistry_OnUnpaidInvoiceTab(prop.getProperty("clerkname"));

            phoneorder.Enter_PaymentAmount_InvoiceDetails_UnpaidInvoiceTab("50");
            phoneorder.Click_SubmitButton_InvoiceDetails_UnpaidInvoiceTab();
            delayWithGivenTime(1000);
            phoneorder.ClickBackButton_ConfirmationSection_UnpaidInvoiceTab();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_UnpaidInvoiceTable_UnpaidInvoiceTab()
                    , "Test Step - 21 - Unpaid invoice table is not displayed on unpaid invoice tab in customer details popup on phone order page");
            delayWithGivenTime(1000);


        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}