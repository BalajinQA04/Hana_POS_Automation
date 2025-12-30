package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.utilities.ConsoleLogUtils;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T177_OrderEntry_Pickup_PaymentSection_GiftCardPayment_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;
    private DashboardOrderPage dashboardorder;
    public static LoggerUtil logger_Util;

    String invoiceNumber;
    String gift_cardno;
    String gift_amount;
    String invoice;
    String processingfee = String.valueOf(5);
    String recipient_name;
    String recipient_email;
    String message;
    String e_gift_cardno;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Sanity", "Regression"})
    public void Validate_Hana_T177_Add_New_EGiftCard_Phone_Order_Pickup_PaymentSection_GiftCardPayment() {
        CustomSoftAssert softassert = new CustomSoftAssert();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
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

            // Test Step - 4
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.Click_AddGiftCardIcon_OnPhoneorderPage();
            delayWithGivenTime(2000);
            cashandcarry = new CashAndCarryPage();
            cashandcarry.click_EGiftCard_Radio_Button();
            delayWithGivenTime(1000);
            cashandcarry.click_Next_Button_On_GiftCard_Select_Type_Popup();
            delayWithGivenTime(2000);

            softassert.assertTrue(cashandcarry.is_EGift_Card_Details_Popup_Displayed(), "Test Step - 4 - E-gift card Enter gift card details pop up is not displayed");
            delayWithGivenTime(1000);

            cashandcarry.search_And_Select_Customer_On_Enter_Gift_Card_Details_Popup(prop.getProperty("custfullname"));
            delayWithGivenTime(1000);

            Faker faker = new Faker(new java.util.Locale("en-US"));
            recipient_name = faker.name().firstName();
            recipient_email = "hanaposqateam@gmail.com";
            String giftamount = String.valueOf(faker.number().numberBetween(400, 700));
            gift_amount = giftamount;
            delayWithGivenTime(1000);

            cashandcarry.enter_Recipient_Name_Egift_Card_Popup(recipient_name);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.get_Recipient_Name_Egift_Card_Popup(), recipient_name, "Test Step - 5 - Recipient name is not displayed");

            cashandcarry.set_Recipient_Email_Egift_Card_Popup(recipient_email);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.get_Recipient_Email_Egift_Card_Popup(), recipient_email, "Test Step - 6 - Recipient email is not displayed");

            cashandcarry.EnterAmountOnGiftamtField(gift_amount);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getGiftAmountValue(), gift_amount + ".00", "Test Step - 6 - Gift amount values are not matched");

            delayWithGivenTime(500);
            cashandcarry.EnterProcessingFeesOnGiftSalePopup(processingfee);
            softassert.assertEquals(cashandcarry.getGiftCardProcessingFee(), "5.00", "Test Step - 9 - gift card processing fee is not matched");

            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getTotalGiftValue(), cashandcarry.expectedTotalGiftValue(), " Test Step - 9 - Total gift values are not matched");

            delayWithGivenTime(500);
            gift_cardno = cashandcarry.getGiftCardNumber();
            message = "Dear " + recipient_name + ",\n" + "This is a gift card worth $" + gift_amount + ".00" + ".\n" + "Best wishes,\n" + "Hana POS Team";
            cashandcarry.enter_Message_Egift_Card_Popup(message);
            cashandcarry.saveGiftCardInfoToFile(gift_cardno, gift_amount);

            cashandcarry.click_Next_Button_On_EGiftCard_Popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.is_Select_Gift_Card_Design_Popup_Displayed(), "Test Step - 10 - Select gift card design pop up is not displayed after clicking next button");
            delayWithGivenTime(1000);
            cashandcarry.select_Occasion_Egift_Card_Popup("Birthday");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.get_Occasion_Egift_Card_Popup(), "Birthday", "Test Step - 11 - Occasion is not displayed");

            cashandcarry.click_Gift_Card_Design_Button();

            softassert.assertEquals(cashandcarry.get_Gift_Amount_On_Select_Gift_Card_Design_Popup(), "$" + gift_amount + ".00", "Test Step - 12 - Gift amount values are not matched in select gift card design popup");

            delayWithGivenTime(1000);
            cashandcarry.click_Preview_Button_On_Select_Gift_Card_Design_Popup();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarry.is_Gift_Card_Sale_Preview_Popup_for_Egiftcard_Displayed(), "Test Step - 11 - Gift card sale preview popup for egift card is not displayed");

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.get_Customer_Name_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), prop.getProperty("custfullname"), "Test Step - 15 - Customer name values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Recipient_Name_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), recipient_name, "Test Step - 12 - Recipient name values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Occasion_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), "Birthday", "Test Step - 13 - Selected Occasion are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Gift_Amount_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), gift_amount + ".00", "Test Step - 14 - Gift amount values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Processing_Fee_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), "5.00", "Test Step - 15 - Gift processing fee values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Total_Amount_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), cashandcarry.expectedTotalGiftValue(), "Test Step - 16 - Total amount values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Gift_Card_Number_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), gift_cardno, "Test Step - 17 - Gift card number values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Message_Gift_Card_Sale_Preview_Popup_for_Egiftcard(), message, "Test Step - 18 - Message values are not matched in preview popup");

            delayWithGivenTime(2000);
            cashandcarry.click_EGift_Card_Submit_Button_On_Preview_Popup();

            delayWithGivenTime(1000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertEquals(cashandcarrypayment.getSuccessToastMsg(), "Gift card has been added", "Test Step - 18 - Success toast message is not displayed after clicking submit button");

            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 21 - product section Item quantity row 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ProdDetails_ItemQty2OnPhoneOrderPage(), "1", "Test Step - 21 - product section Item quantity row 2 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), gift_amount + ".00", "Test Step - 21 - product section unit price 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ProdDetails_ItemUnitPrice2OnPhoneOrderPage(), "5.00", "Test Step - 21 - product section unit price 2 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ExtPrice1OnProdDetails(), gift_amount + ".00", "Test Step - 21 - product section ext price 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ExtPrice2OnProdDetails(), "5.00", "Test Step - 21 - product section ext price 2 is not displayed as expected behaviour");

            delayWithGivenTime(2000);
            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 22 - Pickup type is not highlighted in blue color");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_SelectedSalesPersonOn_PhoneOrderEntryPage(), "Stuart Markwood", "Test Step - 22 - Selected sales person is not displayed on phone order page");

            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Abish", "Test Step - 22 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "David", "Test Step - 22 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Hana_Sisterchicks", "Test Step - 22 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "hanaposqateam@gmail.com", "Test Step - 22 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "3402 Park Blvd", "Test Step - 22 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 22 - Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "92103", "Test Step - 22 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Diego", "Test Step - 22 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 22 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 22 - Alt phone number is not displayed on phone order page");

            // Test Step - 23
            phoneorder.ClickReciNameOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 23 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 23 - Displayed last name is not matched with customer lastname on phone order page recipient section");
            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(prop.getProperty("Full_Reci_Address1_1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "1160 W 5th St", "Test Step - 23 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 23 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 23 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 23 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");
            phoneorder.EnterDeliveryDateOnReciSection();
            phoneorder.Enter_DeliveryTime_OnRecipientSection();
            phoneorder.Select_DeliveryOnTime_Dropdown(prop.getProperty("deliveryOnTime"));
            delayWithGivenTime(1000);

            // Test Step - 10
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 24 - Selected Occasion is not displayed on phone order page order details section");
            phoneorder.EnterViewShortCode();
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase("Happy Birthday! Hope you have an amazing day!"), true, "Test Step - 24 -Entered Short code is not displayed on phone order page order details section");

            // Test Step - 11
            delayWithGivenTime(2000);
            phoneorder.Select_ProductTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(phoneorder.get_selected_ProductTaxType(), prop.getProperty("product_taxtype"), "Test Step - 25 - Selected product tax type is not displayed on phone order page");

            phoneorder.Enter_ProductTaxId(prop.getProperty("product_taxid"));
            softassert.assertEquals(phoneorder.get_ProductTaxId(), prop.getProperty("product_taxid"), "Test Step - 25 - Entered Product tax id is not displayed on phone order page");

            phoneorder.Select_ProdSourceCode(prop.getProperty("product_sourcecode"));
            softassert.assertEquals(phoneorder.get_selected_ProdSourceCode(), prop.getProperty("product_sourcecode"), "Test Step - 25 - Selected Product source code is not displayed on phone order page");
            delayWithGivenTime(2000);

            phoneorder.Select_ProdCustType(prop.getProperty("product_custtype"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_ProdCustType(), prop.getProperty("product_custtype"), "Test Step - 25 - Selected product customer type is not displayed on phoneorder");

            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("payment_type"), "Test Step - 10 - Selected payment type is not displayed");

            delayWithGivenTime(1000);
            phoneorder.Enter_CashPaymentType_Amount();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Entered_CashPaymentType_Amount(), phoneorder.get_Entered_CashPaymentType_Amount(), "Test Step - 10 - Entered more than cash payment amount is not allowed on the payment section cash type pay amount field");

            // Test Step - 12
            phoneorder.SelectCashRegistry_On_CashPaymentType(prop.getProperty("payment_cash_registry"));

            // Test Step - 13
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.Verify_Change_GivenBackTo_Customer_amount_IsDisplayed(), "100.00", "Test Step - 12 - Change given back to customer amount label is not displayed under the cash payment type select registered dropdown");
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Selected_CashRegistry(), prop.getProperty("payment_cash_registry"), "Test Step - 13 - Selected cash payment registered is not displayed");

            // Test Step - 14
            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 14 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 15
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 15 - Order confirmation page is not displayed");
            invoice = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 16
            softassert.assertEquals(orderconfirmationpage.get_PaymentType(), prop.getProperty("payment_type"), "Test Step - 16 - Payment type is not displayed on order confirmation page");

            // Test Step - 17
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);

            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 17 - Dashboard order page is not displayed");

            // Test Step - 18
            cashandcarry.saveGiftCardInfoToFile(gift_cardno, gift_amount, invoice, dashboardorder.validate_OrderType_On_AllOrdersPage(invoice), dashboardorder.validate_MOP_On_AllOrdersPage(invoice));
            dashboardorder.EnterGlobalSearch(invoice);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice), "Test Step - 18 - In orders summary page cash payment type invoice number is not displayed for placed order");

            //Test Step - 19
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice), "Cash", "Test Step - 19 - Mode of payment as  cash is not displayed");


        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }

    @Epic("Phone Order Module - Pickup Type")
    @Test(priority = 2, enabled = true, groups = {"Sanity", "Regression"})
    public void Validate_Hana_T177_OrderEntry_Pickup_PaymentSection_GiftCardPayment_Functionality() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T177_OrderEntry_Pickup_PaymentSection_GiftCardPayment_Functionality_Test ****");
        logger.debug("capturing application debug logs....");
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 - Login page is not displayed");
            logger.info("User on the hana pos login page");
            logger_Util.startNetworkLogging(testCaseName);

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
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

            // Test Step - 4
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Pickup type is not highlighted in blue color");
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));

            softassert.assertEquals(phoneorder.get_SelectedSalesPersonOn_PhoneOrderEntryPage(), "Stuart Markwood", "Test Step - 5 - Selected sales person is not displayed on phone order page");

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
            phoneorder.ClickReciNameOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 7 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 7 - Displayed last name is not matched with customer lastname on phone order page recipient section");
            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(prop.getProperty("Full_Reci_Address1_1"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "1160 W 5th St", "Test Step - 7 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 7 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 7 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 7 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection();
            phoneorder.Select_DeliveryOnTime_Dropdown(prop.getProperty("deliveryOnTime"));
            delayWithGivenTime(1000);

            // Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            phoneorder.EnterViewShortCode();
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase("Happy Birthday! Hope you have an amazing day!"), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), "rrd-Red Rose Deluxe");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "rrd", "Test Step - 9 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), "Red Rose Deluxe", "Test Step - 9 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 9 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 9 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            phoneorder.EnterSpecialInstructions_ProductDetailsSection(prop.getProperty("special_instruction"));
            softassert.assertEquals(phoneorder.get_SpecialInstructions_ProductDetailsSection(), prop.getProperty("special_instruction"), "Test Step - 9 - Special Instruction on product details section");

            phoneorder.EnterDriverInstructions_ProductDetailsSection(prop.getProperty("driver_instruction"));
            softassert.assertEquals(phoneorder.get_DriverInstructions_ProductDetailsSection(), prop.getProperty("driver_instruction"), "Test Step - 9 - Driver Instruction on product details section");

            phoneorder.EnterCustomerPrivateNotesInstructions_ProductDetailsSection(prop.getProperty("customer_private_notes"));
            softassert.assertEquals(phoneorder.get_CustomerPrivateNotesInstructions_ProductDetailsSection(), prop.getProperty("customer_private_notes"), "Test Step - 9 - Driver Instruction on product details section");
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Customer Notes updated successfully", "Test Step - 9 - Success toast message text as customer notes updated successfully is not displayed properly");

            phoneorder.Select_ProductTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(phoneorder.get_selected_ProductTaxType(), prop.getProperty("product_taxtype"), "Test Step - 9 - Selected product tax type is not displayed on phone order page");

            phoneorder.Enter_ProductTaxId(prop.getProperty("product_taxid"));
            softassert.assertEquals(phoneorder.get_ProductTaxId(), prop.getProperty("product_taxid"), "Test Step - 9 - Entered Product tax id is not displayed on phone order page");

            phoneorder.Select_ProdSourceCode(prop.getProperty("product_sourcecode"));
            softassert.assertEquals(phoneorder.get_selected_ProdSourceCode(), prop.getProperty("product_sourcecode"), "Test Step - 9 - Selected Product source code is not displayed on phone order page");
            delayWithGivenTime(3000);

            phoneorder.Select_ProdCustType(prop.getProperty("product_custtype"));
            delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.get_selected_ProdCustType(), prop.getProperty("product_custtype"), "Test Step - 9 - Selected product customer type is not displayed on phoneorder");

            // Test Step - 10
            delayWithGivenTime(3000);
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type_giftcard"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("payment_type_giftcard"), "Test Step - 10 - Selected payment type is not displayed on phone order page payment section");

            // Test Step - 11
            delayWithGivenTime(60000);
            e_gift_cardno = cashandcarrypayment.fetchGiftCardFromEmail("E-Gift Card", invoice);
            delayWithGivenTime(2000);

            phoneorder.Enter_GiftCardNumber_OnPhoneOrderPage(e_gift_cardno);
            softassert.assertEquals(phoneorder.get_GiftCardNumber_OnPhoneOrderPage(), e_gift_cardno, "Test Step - 11 - Entered E-gift card number is not displayed on payment type section");

            // Test Step - 12
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_GiftCardDisplayedCustName_OnPhoneOrderPage(), "Abish David", "Test Step - 12 - For added Gift card number customer name is not displayed on payment type section");

            // Test Step - 13
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_GiftCardDisplayed_Amount_OnPhoneOrderPage(), "269.10", "Test Step - 13 - Amount is not autopopulated as correct");

            // Test Step - 14
            //delayWithGivenTime(3000);
            softassert.assertEquals(phoneorder.get_GiftCardCurrentBalanceDisplayed_OnPhoneOrderPage(), gift_amount + ".00", "Test Step - 14 - Amount is not autopopulated as correct");
            delayWithGivenTime(1000);
            //   softassert.assertEquals(phoneorder.get_GiftCardCurrentBalanceDisplayed_OnPhoneOrderPage(), phoneorder.get_GiftCardCurrentBalanceDisplayed_OnPhoneOrderPage(), "Test Step - 14 - For added gift card number current balance is not displayed on payment type section");

            // Test Step - 15
            delayWithGivenTime(1000);
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 15 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 16
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 16 - Order confirmation page is not displayed");
            invoiceNumber = orderconfirmationpage.get_invoiceNumber_on_OrderConfirmation_Page();

            // Test Step - 17
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 17 - Dashboard order page is not displayed");

            // Test Step - 18
            cashandcarry.saveGiftCardInfoToFile(e_gift_cardno, gift_amount, invoiceNumber, dashboardorder.validate_OrderType_On_AllOrdersPage(invoiceNumber), dashboardorder.validate_MOP_On_AllOrdersPage(invoiceNumber));
            delayWithGivenTime(2000);
            dashboardorder.EnterGlobalSearch(invoiceNumber);

            delayWithGivenTime(2000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoiceNumber), "Test Step - 18 - In orders summary page cash payment type invoice number is not displayed for placed order");
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoiceNumber), "Gift Card", "Test Step - 19 - Mode of payment as Gift Card is not displayed");
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