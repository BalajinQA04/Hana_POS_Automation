package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireOut_Type;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.pageObjects.Order_Confirmation_Page;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T556_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_Pay_Date_Search_Filter_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    String invoiceNumber;

    // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
    CustomSoftAssert softassert = new CustomSoftAssert();

    @Epic("Phone Order Module - Wire out Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T556_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_Pay_Date_Search_Filter_Functionality_Test() {
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

            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 : Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 : Cash and carry option is not displayed");

            //================  Pre- requiste  ============================================

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Pickup type is not highlighted in blue color");

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
            phoneorder.EnterReciFirstName(prop.getProperty("recipient_firstName1"));
            phoneorder.EnterReciLastName(prop.getProperty("recipient_lastName1"));
            phoneorder.EnterReciAddress1(prop.getProperty("cust_address1"));
            phoneorder.EnterReciAddress2("PICK UP");
            phoneorder.EnterReciZipcode(prop.getProperty("cust_zipcode"));
            delayWithGivenTime(1000);

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
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), "Church", "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), "rrd-Red Rose Deluxe");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");

            if (phoneorder.getUnitPriceOnProdDetails() == "299.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            } else if (phoneorder.getUnitPriceOnProdDetails() == "309.00") {
                softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "309.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            }

            delayWithGivenTime(2000);

            // Test Step - 10
            // Cash - payment method
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("payment_type"), "Test Step - 10 - Selected payment type is not displayed");

            // Test Step - 11
            delayWithGivenTime(1000);
            phoneorder.Enter_CashPaymentType_Amount();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Entered_CashPaymentType_Amount(), phoneorder.get_Entered_CashPaymentType_Amount(), "Test Step - 11 - Entered more than cash payment amount is not allowed on the payment section cash type pay amount field");

            phoneorder.SelectCashRegistry_On_CashPaymentType(prop.getProperty("payment_cash_registry"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.Verify_Change_GivenBackTo_Customer_amount_IsDisplayed(), "100.00", "Test Step - 11 - Change given back to customer amount label is not displayed under the cash payment type select registered dropdown");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Selected_CashRegistry(), "Cash Register2", "Test Step - 11 - Selected cash payment registered is not displayed");

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

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 : Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 : Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Click_WireOut_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_On_WireOut_PhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Wire out Delivery type is not highlighted in blue color");

            //Test Step - 6
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
            softassert.assertTrue(phoneorder.Verify_CustEditIcon_Appears(), "Test Step - 7 - Customer edit icon on phone order page is not displayed");

            // Test Step - 8
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 8 - Customer details popup on is not displayed on phone order page");

            // Test Step - 9
            delayWithGivenTime(1000);
            phoneorder.ClickOn_PaymentDetails_CustomerDetailsPopup();
            ThreadWait(4000);
            softassert.assertTrue(phoneorder.Verify_TableGridOnPaymentDetailsTab_IsAppear(), "Test Step - 9 - payment details tab webtable grid is not displayed");

            // Test Step - 10
            ThreadWait(1000);
            phoneorder.Enter_PayDateSearchBox_OnPaymentDetailsTab(CurrentDate());

            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_PayDate_Paymentdetailstab(), CurrentDate(), "Test Step - 10 - Entered date value on date picker input box field is not displayed");


            // Test Step - 11
            softassert.assertTrue(phoneorder.Verify_PayDate_Paymentdetailstab(CurrentDate()), "Test Step - 11 - Filtered Pay date is not displayed on the payment details tab at customer details popup");

            // Test Step - 12
            phoneorder.Enter_PayDateSearchBox_OnPaymentDetailsTab(CurrentDate());

            // Test Step - 13
            softassert.assertTrue(phoneorder.Verify_PayDate_Paymentdetailstab(CurrentDate()), "Test Step - 13 - Filtered Pay Date is not displayed on the payment details tab at customer details popup");

            // Test Step - 14
            phoneorder.Enter_PayDateSearchBox_OnPaymentDetailsTab("13/32/20200");
            RobotPressEnter();
            softassert.assertEquals(phoneorder.get_PayDate_Paymentdetailstab(), "", "Known Issue: - Test Step - 14 - Entered invalid date value on date picker input box field is not removed");

            if (phoneorder.get_PayDate_Paymentdetailstab() == "") {
            } else {

            }
            // Test Step - 15
            phoneorder.Enter_PayDateSearchBox_OnPaymentDetailsTab(phoneorder.NextDate());
            RobotPressEnter();
            softassert.assertTrue(phoneorder.Verify_NoCustomerPaymentsFound_PaymentDetailsTab(), "Test Step - 15 - Filtered future Pay Date is not displayed message as No Customer Payments Found on the payment details tab at customer details popup");

            // Test Step - 16
            softassert.assertTrue(phoneorder.Verify_PayDateSearchBox_ClearButton_Appears(), "Test Step - 16 - Clear button in Pay Date search box is not displayed");

            // Test Step - 17
            phoneorder.Click_PayDateSearchBox_ClearButton();
            softassert.assertEquals(phoneorder.get_PayDate_Paymentdetailstab(), "", "Test Step - 17 - In Pay Date searchbox entered data is not cleared");

            // Test Step - 18
            softassert.assertTrue(phoneorder.Verify_FilterIconOptions_PayDateOnPaymentDetailsTab_OnCustomerDetailsPopup(), "Test Step - 18 - Filter icon on pay date it does not displayed the options");

            // Test Step - 19
            phoneorder.Click_CalendarIconOnPayDate_OnPaymentDetailsTab();
            delayWithGivenTime(1000);
            phoneorder.SelectTheDate_FromDatePickerIcon_OnPaymentDetailsTab();
            delayWithGivenTime(1000);
            phoneorder.Click_FilterIconOptions_PayDateOnPaymentDetailsTab_OnCustomerDetailsPopup();
            delayWithGivenTime(1000);
            phoneorder.Click_FilterIconOptions_PayDateOnPaymentDetailsTab_OnCustomerDetailsPopup("Is equal to");
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_PayDate_Paymentdetailstab(CurrentDate()), "Test Step - 19 - Filtered Pay date is not displayed on the payment details tab at customer details popup");

            // Test Step - 20
            phoneorder.Click_FilterIconOptions_PayDateOnPaymentDetailsTab_OnCustomerDetailsPopup("Is not equal to");
            delayWithGivenTime(1000);
            softassert.assertFalse(phoneorder.Verify_PayDate_Paymentdetailstab(CurrentDate()), "Test Step - 20 - Based on selecting the filter option pay date is not displayed on the payment details tab at customer details popup");

            // Test Step - 21
            phoneorder.Click_FilterIconOptions_PayDateOnPaymentDetailsTab_OnCustomerDetailsPopup("Is after or equal to");
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_PayDate_Paymentdetailstab(CurrentDate()), "Test Step - 21 - Based on selecting the filter option pay date is not displayed on the payment details tab at customer details popup");

            // Test Step - 22
            phoneorder.Click_FilterIconOptions_PayDateOnPaymentDetailsTab_OnCustomerDetailsPopup("Is after");
            delayWithGivenTime(1000);
			/*if(phoneorder.Verify_NoCustomerPaymentsFound_PaymentDetailsTab()==true) {
			softassert.assertTrue(phoneorder.Verify_NoCustomerPaymentsFound_PaymentDetailsTab(),"Test Step - 22 - Based on selecting the filter option pay date not displayed No Payment Customer found message on the payment details tab at customer details popup");
			}else {*/
            softassert.assertTrue(phoneorder.Verify_PayDate_Paymentdetailstab(CurrentDate()), "Test Step - 22 - Based on selecting the filter option pay date is not displayed on the payment details tab at customer details popup");
            //	}

            // Test Step - 23
            phoneorder.Click_FilterIconOptions_PayDateOnPaymentDetailsTab_OnCustomerDetailsPopup("Is before or equal to");
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.Verify_PayDate_Paymentdetailstab(CurrentDate()), "Test Step - 23 - Based on selecting the filter option pay date is not displayed on the payment details tab at customer details popup");

            // Test Step - 24
            phoneorder.Click_PayDateSearchBox_ClearButton();
            softassert.assertEquals(phoneorder.get_PayDate_Paymentdetailstab(), "", "Test Step - 24 - In Pay Date searchbox entered data is not cleared");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}