package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import java.io.IOException;

import com.hanapos.pageObjects.*;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;

public class Hana_T125_Pickup_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_CCNumber_SearchFilter_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    public static final String dataSheetName = "Hana_T125";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    String invoiceNumber;

    @Epic("Phone Order Module - Pickup Type")
    @Test(priority = 1, enabled = true, groups = {"Regression"})
    public void Pre_Requiste_Hana_T125_Pickup_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_CCNumber_SearchFilter_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Pre_Requiste_Hana_T125_Pickup_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_CCNumber_SearchFilter_Functionality_Test  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("bestuname"));
            logger.info("User entered the username as " + prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            logger.info("User entered the password as " + prop.getProperty("bestpass"));
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
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("bestshopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("bestshopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Pickup type is not highlighted in blue color");

            // Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("custfullname"));
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
            phoneorder.EnterReciAddress1(prop.getProperty("recipient_address1"));
            phoneorder.EnterReciAddress2(prop.getProperty("recipient_address2"));
            phoneorder.EnterReciZipcode(prop.getProperty("recipient_zipcode1"));
            delayWithGivenTime(1000);
            phoneorder.SelectReciCountry(prop.getProperty("recipient_country1"));
            phoneorder.Enter_RecipientState(prop.getProperty("recipient_state"));
            phoneorder.EnterReciPhone(prop.getProperty("recipient_phonenumber1"));
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(prop.getProperty("recipient_location1"));
            phoneorder.Select_Zone_OnRecipientSection(prop.getProperty("best_zone"));
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 7 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 7 - Entered last name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), "701 JC Center Ct", "Test Step - 7 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "PICK UP", "Test Step - 7 - Entered address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "55008", "Test Step - 7 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Cambridge", "Test Step - 7 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), "Church", "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            phoneorder.Enter_CardMessage_OnOccasion_Details_Section(prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("productfulldesc1"));
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
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("MOP_As_CC"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("MOP_As_CC"), "Test Step - 10 - Selected payment type is not displayed");

            // Test Step - 11
            softassert.assertEquals(phoneorder.get_autopopulated_creditcard_paymenttype_FirstName(), "Abish", "Test Step - 11 - First name textbox field Payment type as credit card section  is not autopopulated");

            // Test Step - 12
            softassert.assertEquals(phoneorder.get_autopopulated_creditcard_paymenttype_LastName(), "David", "Test Step - 12 - Last name textbox field Payment type as credit card section  is not autopopulated");

            // Test Step - 13
            softassert.assertEquals(phoneorder.get_autopopulated_creditcard_paymenttype_Zipcode(), "92103", "Test Step - 13 - Zipcode textbox field Payment type as credit card section  is not autopopulated");

            // Test Step - 14
            softassert.assertEquals(phoneorder.get_autopopulated_creditcard_paymenttype_City(), "San Diego", "Test Step - 14 - City textbox field Payment type as credit card section  is not autopopulated");

            // Test Step - 15
            softassert.assertEquals(phoneorder.get_autopopulated_creditcard_paymenttype_State(), "CA", "Test Step - 15 - State textbox field Payment type as credit card section  is not autopopulated");

            // Test Step - 16
            softassert.assertEquals(phoneorder.get_Selected_creditcard_paymenttype_Country(), "United States", "Test Step - 16 - Country textbox field Payment type as credit card section  is not autopopulated");

            // Test Step - 17
            phoneorder.Enter_CreditCardNumber_CCPaymentSection_On_PhoneOrderPage(prop.getProperty("creditcardnum"));
            softassert.assertEquals(phoneorder.get_entered_CreditcardNumber_CCPaymentSection_On_PhoneOrderPage(), "4111111111111111", "Test Step - 17 - Entered credit card number is not displayed on credit card payment section");

            // Test Step - 18
            phoneorder.Enter_ExpiredDate_CCPaymentSection_On_PhoneOrderPage(prop.getProperty("ccexpiredate"));
            softassert.assertEquals(phoneorder.get_entered_ExpiredDate_CCPaymentSection_On_PhoneOrderPage(), prop.getProperty("ccexpiredate"), "Test Step - 18 - Entered credit card expire date is not displayed on credit card payment section");

            // Test Step - 19
            phoneorder.Enter_CCV_CCPaymentSection_On_PhoneOrderPage(prop.getProperty("cccvv"));
            softassert.assertEquals(phoneorder.get_entered_CVV_CCPaymentSection_On_PhoneOrderPage(), prop.getProperty("cccvv"), "Test Step - 19 - Entered credit card CVV code is not displayed on credit card payment section");

            // Test Step - 20
            phoneorder.ClickPlaceOrderButton();

            // Test Step - 21
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 21 - Order confirmation page is not displayed");

            // Test Step - 22
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();
            softassert.assertEquals(orderconfirmationpage.get_PaymentType(), "Credit Card", "Test Step - 22 - Payment type is not displayed on order confirmation page");

            // Test Step - 23
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 23 - Dashboard order page is not displayed");

            // Test Step - 24
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Validate_PhoneOrder_CC_PaymentType_PickUp_InvoiceNumber(), "Test Step - 24 - In orders summary page Donation payment type invoice number is not displayed for placed order");
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.Validate_PhoneOrder_CC_PaymentType_PickUp_ModeOfPayment(), "Credit Card", "Test Step - 24 - Mode of payment as Donation type is not displayed");
        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }


    @Epic("Phone Order Module - Pickup Type")
    @Test(priority = 2, enabled = true, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T125_Pickup_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_CCNumber_SearchFilter_Functionality_Test(String valid_ccno, String three_digit_ccno, String invalid_ccno) {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting Hana_T125_Pickup_CustomerSection_Edit_Customer_CustomerDetails_PaymentDetails_CCNumber_SearchFilter_Functionality_Test  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("bestuname"));
            logger.info("User entered the username as " + prop.getProperty("bestuname"));
            lp.EnterPassword(prop.getProperty("bestpass"));
            logger.info("User entered the password as " + prop.getProperty("bestpass"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            retryAction(() -> softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Page did not navigate to hana dashboard page"), 3);
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            retryAction(() -> softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3: Order entry option is not displayed"), 3);
            retryAction(() -> softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3: Cash and carry option is not displayed"), 3);

            // Test Step - 4
            retryAction(() -> dashboard.ClickOrderEntry(), 3);
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("bestshopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("bestshopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Pickup type is not highlighted in blue color");

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
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 8 - Customer details popup on is not displayed on phone order page");

            // Test Step - 8
            delayWithGivenTime(1000);
            phoneorder.ClickOn_PaymentDetails_CustomerDetailsPopup();
            delayWithGivenTime(5000);
            softassert.assertTrue(phoneorder.Verify_TableGridOnPaymentDetailsTab_IsAppear(), "Test Step - 9 - payment details tab webtable grid is not displayed");

            // Test Step - 9
            delayWithGivenTime(2000);
            phoneorder.Enter_CCNumberSearchBox_OnPaymentDetailsTab(valid_ccno);
            softassert.assertEquals(phoneorder.get_CCNumberSearchBox_Paymentdetailstab(), valid_ccno, "Test Step - 11 - In Credit Card number searchbox entered data is not displayed on the payment details tab at customer details popup");

            // Test Step - 10
            RobotPressEnter();
            softassert.assertTrue(phoneorder.Verify_CCNumber_Paymentdetailstab(valid_ccno), "Test Step - 10 - Entered Credit Card number is not displayed on the payment details tab at customer details popup");

            // Test Step - 11
            phoneorder.Enter_CCNumberSearchBox_OnPaymentDetailsTab(three_digit_ccno);
            softassert.assertEquals(phoneorder.get_CCNumberSearchBox_Paymentdetailstab(), three_digit_ccno, "Test Step - 11 - In Credit Card number searchbox entered data is not displayed on the payment details tab at customer details popup");

            // Test Step - 12
            RobotPressEnter();
            softassert.assertTrue(phoneorder.Verify_CCNumber_Paymentdetailstab(valid_ccno), "Test Step - 12 - Entered Credit Card number is not displayed on the payment details tab at customer details popup");

            // Test Step - 13
            phoneorder.Enter_CCNumberSearchBox_OnPaymentDetailsTab(invalid_ccno);
            softassert.assertEquals(phoneorder.get_CCNumberSearchBox_Paymentdetailstab(), invalid_ccno, "Test Step - 13 - In Credit Card number searchbox entered data is not displayed on the payment details tab at customer details popup");

            RobotPressEnter();
            softassert.assertTrue(phoneorder.Verify_NoCustomerPaymentsFound_PaymentDetailsTab(), "Test Step - 13 - Entered invalid Credit Card number is displayed on the payment details tab at customer details popup");

            // Test Step - 14
            softassert.assertTrue(phoneorder.Verify_CCNumberSearchBox_ClearButton_Appears(), "Test Step - 14 - Clear button in Credit Card number search box is not displayed");

            // Test Step - 15
            phoneorder.Click_CCNumberSearchBox_ClearButton();
            softassert.assertEquals(phoneorder.get_CCNumberSearchBox_Paymentdetailstab(), "", "Test Step - 15 - In Credit Card number searchbox entered data is not cleared");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}