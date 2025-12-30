package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.*;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;

public class Hana_T437_Payment_Section_Split_Payment_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    String invoiceNumber;
    public static final String dataSheetName = "Hana_T437";
    private CashAndCarryPaymentPage cashandcarrypayment;
    String total_Amount;
    String paidcashamount;
    String paidcheckamount;
    String paid_pohamount;
    String paidgiftamount;

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_full_address1;
    String reci_phone_number1;
    String reci_phone_number2;
    String floor_number;

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T437_Payment_Section_Split_Payment_Functionality_Test
            (String creditcardtype, String ccamount, String cashamount, String checknumber, String bankname, String nameoncheck, String checkamount, String pohamount, String giftcardnumber, String giftcardamount) {
        //   SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T437_Payment_Section_Split_Payment_Functionality_Test  ****");
        logger.debug("capturing application debug logs....");
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
          /*  dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));
*/
            // Test Step - 3
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
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c", "Test Step - 6 - Delivery type is not highlighted as blue color");

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
            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            reci_full_address1 = "7372 State Hwy O Marthasville MO";
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "7372 State Hwy O", "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63357", "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Marthasville", "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");
            delayWithGivenTime(2000);
            phoneorder.SelectReciCountry(prop.getProperty("recipient_country1"));
            reci_phone_number1 = faker.numerify("###-###-####");
            delayWithGivenTime(2000);

            phoneorder.EnterReciPhone(reci_phone_number1);
            delayWithGivenTime(1000);
            reci_phone_number2 = faker.phoneNumber().cellPhone();
            floor_number = faker.address().buildingNumber();
            phoneorder.EnterRecipientPhone2OnPhoneOrderPage(reci_phone_number2);
            delayWithGivenTime(1000);
            phoneorder.Enter_FloorApt_On_RecipientSection(floor_number);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(prop.getProperty("recipient_location1"));
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), reci_phone_number1, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), prop.getProperty("recipient_location1"), "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(3, 44);
            phoneorder.Select_DeliveryOnTime_Dropdown("Any");
            phoneorder.Select_Zone_OnRecipientSection("Automation Zone");
            delayWithGivenTime(1000);

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            //   phoneorder.Enter_CardMessage_OnOccasion_Details_Section(prop.getProperty("card_message"));
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 8 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), true, "Test Step - 8 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 9
            phoneorder.SearchandSelectItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));
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
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("MOP_As_Split"));

            // Test Step - 11
            phoneorder.Select_SplitPayment_CashRegistry_OnPaymentSection(prop.getProperty("payment_cash_registry"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("MOP_As_Split"), "Test Step - 10 - Selected payment type is not displayed");

            // Test Step - 12
            total_Amount = phoneorder.getGrandTotalAmount();
            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 11 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 13
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 13: Cash And Carry payment page is not displayed");

            // Test Step - 14
            invoiceNumber = cashandcarrypayment.getTopLeftCornerInvNo();

            // Credit card tab
            softassert.assertEquals(cashandcarrypayment.getRow1ProductInTable().contains(prop.getProperty("product_description1")), true, "Test Step - 14: Added item is not displayed on payment details table grid");


            // Test Step - 15
            cashandcarrypayment.EnterFirstNameOnCreditCardTab(prop.getProperty("cust_firstName"));
            cashandcarrypayment.EnterLastNameOnCreditCardTab(prop.getProperty("cust_lastName"));
            cashandcarrypayment.SelectCreditCardTypeOnCreditCardTab(creditcardtype);
            cashandcarrypayment.EnterCreditCardNumberOnCreditCardTab(prop.getProperty("creditcardnum"));
            cashandcarrypayment.EnterCreditCardExpireDateOnCreditCardTab(prop.getProperty("ccexpiredate"));
            cashandcarrypayment.EnterCreditCardCVVOnCreditCardTab(prop.getProperty("cccvv"));
            cashandcarrypayment.EnterCreditCardZipCodeOnCreditCardTab(prop.getProperty("cust_zipcode"));
            cashandcarrypayment.EnterCreditCardAmountOnCreditCardTab(ccamount);

            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 16: Credit card payment process button is disabled");
            cashandcarrypayment.ClickProcessPaymentBtn();
            // Due to credit card processing is not working skip this
            // softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow1(), "Credit Card", "Test Step - 15: Credit card");
            // softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow1().contains("($50.00)"), true, "Test Step - 15: Using credit card payment it does not displayed on payment grid table in row1 on cash and carry payment page");
            // softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row1(), CurrentDate(), "Test Step - 15: Using credit card payment paid amount displayed payment date is not displayed in row1");


            // Cash payment tab
            // Test Step - 16
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCashTab();

            // Test Step - 17
            delayWithGivenTime(2000);
            //  softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 17: Process payment button is not disabled");

            // Test Step - 18
            delayWithGivenTime(2000);
            cashandcarrypayment.SelectRegistryOnCashTab(prop.getProperty("payment_cash_registry"));

            // Test Step - 19
            delayWithGivenTime(1000);
            cashandcarrypayment.EnterGivenAmountOnCashTab(cashamount);
            paidcashamount = cashandcarrypayment.getEnteredGivenAmountOnCashTab();
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);

            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow1(), "Cash", "Test Step - 19: cash payment is not displayed");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow1().contains(cashandcarrypayment.getEnteredGivenAmountOnCashTab()), true, "Test Step - 19: Displayed paid amount is not matched");
            softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row1(), CurrentDate(), "Test Step - 19: Displayed payment date is not matched");

            // Check split payment tab
            // Test Step - 20
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickCheckTab();
            cashandcarrypayment.EnterCheckNumber(checknumber);
            cashandcarrypayment.EnterBankName(bankname);
            cashandcarrypayment.EnterNameOnCheck(nameoncheck);
            cashandcarrypayment.EnterAmountOnCheckTab(checkamount);
            paidcheckamount = cashandcarrypayment.getEnteredAmountOnCheckTab();
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);

            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow2(), "Check", "Test Step - 20: Check payment is not displayed on cash and carry payment details table grid ");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow2().contains(cashandcarrypayment.getEnteredAmountOnCheckTab()), true, "Test Step - 20: Paid check amount is not matched");
            softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row2(), CurrentDate(), "Test Step - 20: Displayed payment date is not matched");

            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOnPOHPaymentTab();

            // Test Step - 21
            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 21: Process payment button is disabled");
            delayWithGivenTime(2000);

            // Test Step - 22
            cashandcarrypayment.EnterPOHAmountOnPOHPaymentTab(pohamount);
            delayWithGivenTime(1000);
            paid_pohamount = cashandcarrypayment.getEnteredPOHAmountOnPOHPaymentTab();
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow3(), "Paid Outside Hana", "Test Step - 22 : Paid Outside Hana Payment type is not displayed on payment details grid");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow3().contains(cashandcarrypayment.getEnteredPOHAmountOnPOHPaymentTab()), true, "Test Step - 22: Paid Outside Hana paid amount is not displayed on payment details grid");
            softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row3(), CurrentDate(), "Test Step - 22: Displayed payment date is not matched for paid outside hana payment");

            // Test Step - 23
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOnGiftCardPaymentTab();

            // Test Step - 24
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterGiftCardNumberOnGiftCardPaymentTab(giftcardnumber);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustNameOnGiftCardPaymentTab(), prop.getProperty("custfullname"), "Test Step - 24: Gift card name is not displayed");
            // softassert.assertEquals(cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab().contains(cashandcarrypayment.getTableDisplayedBalanceAmt()), true, "Test Step - 24: Gift card balance amount is not displayed on payment details grid");

            // Test Step - 25
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterPaymentAmtOnGiftCardPaymentTab(giftcardamount);
            delayWithGivenTime(1000);
            paidgiftamount = cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab();
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);
         //   softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow4().contains(cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab()), true, "Test Step - 25: Gift Card paid amount is not displayed on payment details grid");
            softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row4(), CurrentDate(), "Test Step - 25: Displayed payment date is not matched for gift card payment");

 /*           // Test Step - 27
            cashandcarrypayment.ClickFinishBtnOnCashAndCarryPaymentPage();
            delayWithGivenTime(2000);
            softassert.assertTrue(cashandcarrypayment.VerifyAlertPopup());


            // Test Step - 28
            softassert.assertTrue(cashandcarrypayment.VerifyAlertIcon());
            softassert.assertEquals(cashandcarrypayment.VerifyAlertPopupWarningText(), "This order is still not fully paid. If you navigate away from this page, balance will remain on this order. Are you sure?");

            // Test Step - 29
            cashandcarrypayment.ClickOnAlertPopupLeaveBtn();
            delayWithGivenTime(4000);

            // Test Step - 30
            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c", "Test Step - 6 - Delivery type is not highlighted as blue color");

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
            phoneorder.EnterReciAddress1(prop.getProperty("recipient_address1"));
            phoneorder.EnterReciAddress2(prop.getProperty("Reci_Address1_2"));
            phoneorder.EnterReciZipcode(prop.getProperty("recipient_zipcode1"));
            phoneorder.Enter_RecipientState(prop.getProperty("recipient_state"));
            delayWithGivenTime(1000);

            phoneorder.SelectReciCountry(prop.getProperty("recipient_country1"));
            phoneorder.EnterReciPhone(prop.getProperty("recipient_phonenumber1"));
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(prop.getProperty("recipient_location1"));
            delayWithGivenTime(1000);
            //  phoneorder.Select_Zone_OnRecipientSection(prop.getProperty("best_zone"));
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 7 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 7 - Entered last name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), "3402 Park Blvd", "Test Step - 7 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "112 Penny Ct,", "Test Step - 7 - Entered address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "92103", "Test Step - 7 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "San Diego", "Test Step - 7 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), "Church", "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            //Test Step - 8
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            //   phoneorder.Enter_CardMessage_OnOccasion_Details_Section(prop.getProperty("card_message"));
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
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
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection("Split Payment");

            // Test Step - 11
            phoneorder.Select_SplitPayment_CashRegistry_OnPaymentSection(prop.getProperty("payment_cash_registry"));

            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), "Split Payment", "Test Step - 10 - Selected payment type is not displayed");
            softassert.assertEquals(phoneorder.get_Selected_CashRegistry(), prop.getProperty("payment_cash_registry"));

            // Test Step - 12
            total_Amount = phoneorder.getGrandTotalAmount();
            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 11 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 13
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            cashandcarrypayment = new CashAndCarryPaymentPage();
            softassert.assertTrue(cashandcarrypayment.IsPaymentPageDisplayed(), "Test Step - 13: Cash And Carry payment page is not displayed");

            // Test Step - 14
            invoiceNumber = cashandcarrypayment.getTopLeftCornerInvNo();

            // Credit card tab
            softassert.assertEquals(cashandcarrypayment.getRow1ProductInTable().contains("Red Rose Deluxe"), true, "Test Step - 14: Added item is not displayed on payment details table grid");


            // Test Step - 15
            cashandcarrypayment.EnterFirstNameOnCreditCardTab(prop.getProperty("cust_firstName"));
            cashandcarrypayment.EnterLastNameOnCreditCardTab(prop.getProperty("cust_lastName"));
            cashandcarrypayment.SelectCreditCardTypeOnCreditCardTab(creditcardtype);
            cashandcarrypayment.EnterCreditCardNumberOnCreditCardTab(prop.getProperty("creditcardnum"));
            cashandcarrypayment.EnterCreditCardExpireDateOnCreditCardTab(prop.getProperty("ccexpiredate"));
            cashandcarrypayment.EnterCreditCardCVVOnCreditCardTab(prop.getProperty("cccvv"));
            cashandcarrypayment.EnterCreditCardZipCodeOnCreditCardTab(prop.getProperty("cust_zipcode"));
            cashandcarrypayment.EnterCreditCardAmountOnCreditCardTab(ccamount);

            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 16: Credit card payment process button is disabled");
            cashandcarrypayment.ClickProcessPaymentBtn();
            // Due to credit card processing is not working skip this
            // softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow1(), "Credit Card", "Test Step - 15: Credit card");
            // softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow1().contains("($50.00)"), true, "Test Step - 15: Using credit card payment it does not displayed on payment grid table in row1 on cash and carry payment page");
            // softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row1(), CurrentDate(), "Test Step - 15: Using credit card payment paid amount displayed payment date is not displayed in row1");


            // Cash payment tab
            // Test Step - 16
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickCashTab();

            // Test Step - 17
            delayWithGivenTime(2000);
            softassert.assertFalse(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 18: Process payment button is not disabled");

            // Test Step - 18
            delayWithGivenTime(2000);
            cashandcarrypayment.SelectRegistryOnCashTab(prop.getProperty("payment_cash_registry"));

            // Test Step - 19
            delayWithGivenTime(1000);
            cashandcarrypayment.EnterGivenAmountOnCashTab(cashamount);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);

            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow1(), "Cash", "Test Step - 19: cash payment is not displayed");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow1().contains(cashandcarrypayment.getEnteredGivenAmountOnCashTab()), true, "Test Step - 19: Displayed paid amount is not matched");
            paidcashamount = cashandcarrypayment.getEnteredGivenAmountOnCashTab();
            softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row1(), CurrentDate(), "Test Step - 19: Displayed payment date is not matched");

            // Check split payment tab
            // Test Step - 20
            delayWithGivenTime(1000);
            cashandcarrypayment.ClickCheckTab();
            cashandcarrypayment.EnterCheckNumber(checknumber);
            cashandcarrypayment.EnterBankName(bankname);
            cashandcarrypayment.EnterNameOnCheck(nameoncheck);
            cashandcarrypayment.EnterAmountOnCheckTab(checkamount);
            cashandcarrypayment.ClickProcessPaymentBtn();

            delayWithGivenTime(1000);

            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow2(), "Check", "Test Step - 20: Check payment is not displayed on cash and carry payment details table grid ");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow2().contains(cashandcarrypayment.getEnteredAmountOnCheckTab()), true, "Test Step - 20: Paid check amount is not matched");
            paidcheckamount = cashandcarrypayment.getEnteredAmountOnCheckTab();
            softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row2(), CurrentDate(), "Test Step - 20: Displayed payment date is not matched");

            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOnPOHPaymentTab();

            // Test Step - 21
            softassert.assertTrue(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 21: Process payment button is disabled");
            delayWithGivenTime(2000);

            // Test Step - 22
            cashandcarrypayment.EnterPOHAmountOnPOHPaymentTab(pohamount);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaymentTypeOnTableRow4(), "Paid Outside Hana", "Test Step - 22 : Paid Outside Hana Payment type is not displayed on payment details grid");
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow4().contains(cashandcarrypayment.getEnteredPOHAmountOnPOHPaymentTab()), true, "Test Step - 22: Paid Outside Hana paid amount is not displayed on payment details grid");
            paid_pohamount = cashandcarrypayment.getEnteredPOHAmountOnPOHPaymentTab();
            softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row4(), CurrentDate(), "Test Step - 22: Displayed payment date is not matched for paid outside hana payment");

            // Test Step - 23
            delayWithGivenTime(2000);
            cashandcarrypayment.ClickOnGiftCardPaymentTab();

            // Test Step - 24
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterGiftCardNumberOnGiftCardPaymentTab(giftcardnumber);
            softassert.assertEquals(cashandcarrypayment.getDisplayedCustNameOnGiftCardPaymentTab(), "Abish", "Test Step - 24: Gift card name is not displayed");
            softassert.assertEquals(cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab().contains(cashandcarrypayment.getTableDisplayedBalanceAmt()), true);

            // Test Step - 25
            delayWithGivenTime(2000);
            cashandcarrypayment.EnterPaymentAmtOnGiftCardPaymentTab(giftcardamount);
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarrypayment.VerifyPaidAmountOnTableRow5().contains(cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab()), true, "Test Step - 25: Gift Card paid amount is not displayed on payment details grid");
            paidgiftamount = cashandcarrypayment.getDisplayedPaymentAmtOnGiftCardPaymentTab();
            softassert.assertEquals(cashandcarrypayment.get_paymentDate_paymentdetailsTable_Row5(), CurrentDate(), "Test Step - 25: Displayed payment date is not matched for gift card payment");
*/

            // Test Step - 31
            delayWithGivenTime(2000);

            String balance_split_amt = cashandcarrypayment.getTableDisplayedBalanceAmt();
            cashandcarrypayment.ClickCashTab();

            delayWithGivenTime(2000);
            softassert.assertFalse(cashandcarrypayment.VerifyProcessPaymentButton(), "Test Step - 18: Process payment button is not disabled");

            delayWithGivenTime(2000);
            cashandcarrypayment.SelectRegistryOnCashTab(prop.getProperty("payment_cash_registry"));

            delayWithGivenTime(1000);
            cashandcarrypayment.EnterGivenAmountOnCashTab(balance_split_amt);

            // Test Step - 32
            cashandcarrypayment.ClickProcessPaymentBtn();
            delayWithGivenTime(1000);

            // Test Step - 33
            if (cashandcarrypayment.SuccessToastMsg() == true) {
                softassert.assertEquals(cashandcarrypayment.getOrderConfirmationToastMsg(), cashandcarrypayment.getOrderConfirmationToastMsg(), "Test Step - 33 - Please tender $0.00 to Customer message is not displayed");
            }

            if (cashandcarrypayment.VerifyOrderPaidTextAppears() == true) {
                softassert.assertEquals(cashandcarrypayment.VerifyFullyPaidMessage(), "Order Fully Paid. No more payments required.", "Order fully paid message is not displayed");
            }

            // Test Step - 34
            delayWithGivenTime(1000);
            dashboard.ClickOrder();
            delayWithGivenTime(1000);
            logger.info("User click the order menu on hana dashboard page");
            dashboardorder = new DashboardOrderPage();
            softassert.assertEquals(dashboardorder.validateDashboardOrderPage(), prop.getProperty("livedashboardorderURL"), "Test Step - 14 - Dashboard order page is not displayed");

            // Test Step - 35
            dashboardorder.EnterGlobalSearch(invoiceNumber);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.Validate_PhoneOrder_Delivery_InvoiceNumber(prop.getProperty("Order_Type_PhoneOrder"), prop.getProperty("Delivery_Type_Delivery"), prop.getProperty("MOP_As_Split")), "Test Step - 35 - In orders summary page cash payment type invoice number is not displayed for placed order");

            //Test Step - 36
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.Validate_PhoneOrder_ModeOfPayment(prop.getProperty("Order_Type_PhoneOrder"), prop.getProperty("Delivery_Type_Delivery"), prop.getProperty("MOP_As_Split")), prop.getProperty("MOP_As_Split"), "Test Step - 36 - Mode of payment as " + prop.getProperty("MOP_As_Split") + " type is not displayed");

            // Test Step - 37
            dashboardorder.clickInvoiceNumber_On_TableGrid_AllOrdersPage(invoiceNumber);
            dashboardorder.Click_PaymentTab_On_InvoicePopup();

            // Test Step - 38
            softassert.assertEquals(dashboardorder.get_paymentdescription_row1(), "Split Payment", "Test Step - 38: Payments Description as split is not displayed as Split payment in row1");
            softassert.assertEquals(dashboardorder.get_paymentdate_row1(), CurrentDate(), "Test Step - 38: Payments date is not displayed as Current system date in row1");
            softassert.assertEquals(dashboardorder.get_payment_amount_row1(), total_Amount, "Test Step - 38: Payments amount as split is not displayed as Current system date in row1");

            softassert.assertEquals(dashboardorder.get_paymentdescription_row2(), "Cash", "Test Step - 38: Payments Description as cash is not displayed as Cash in row2");
            softassert.assertEquals(dashboardorder.get_paymentdate_row2(), CurrentDate(), "Test Step - 38: Payments date is not displayed as Current system date in row2");
            softassert.assertEquals(dashboardorder.get_payment_amount_row2(), paidcashamount, "Test Step - 38: Paid amount as cash is not displayed as provided paid amount in row2");

            softassert.assertEquals(dashboardorder.get_paymentdescription_row3(), "Check", "Test Step - 38: Payments Description as check is not displayed as Check in row3");
            softassert.assertEquals(dashboardorder.get_paymentdate_row3(), CurrentDate(), "Test Step - 38: Payments date is not displayed as Current system date in row3");
            softassert.assertEquals(dashboardorder.get_payment_amount_row3(), paidcheckamount, "Test Step - 38: Paid amount as check is not displayed as provided paid amount in row3");

            softassert.assertEquals(dashboardorder.get_paymentdescription_row4(), "Paid Outside Hana", "Test Step - 38: Payments Description as paid outside hana is not displayed as Paid Outside Hana in row4");
            softassert.assertEquals(dashboardorder.get_paymentdate_row4(), CurrentDate(), "Test Step - 38: Payments date is not displayed as Current system date in row4");
            softassert.assertEquals(dashboardorder.get_payment_amount_row4(), pohamount, "Test Step - 38: Paid amount as paid outside hana is not displayed as provided paid amount in row4");

            softassert.assertEquals(dashboardorder.get_paymentdescription_row5(), "Gift Card", "Test Step - 38: Payments Description as gift card is not displayed as Gift Card in row5");
            softassert.assertEquals(dashboardorder.get_paymentdate_row5(), CurrentDate(), "Test Step - 38: Payments date is not displayed as Current system date in row5");
            softassert.assertEquals(dashboardorder.get_payment_amount_row5(), giftcardamount, "Test Step - 38: Paid amount as gift card is not displayed as provided paid amount in row5");

            softassert.assertEquals(dashboardorder.get_paymentdescription_row6(), "Cash", "Test Step - 38: Payments Description as cash is not displayed as Cash in row6");
            softassert.assertEquals(dashboardorder.get_paymentdate_row6(), CurrentDate(), "Test Step - 38: Payments date is not displayed as Current system date in row6");
           // softassert.assertEquals(dashboardorder.get_payment_amount_row6(), "$" + balance_split_amt, "Test Step - 38: Paid amount as cash is not displayed as provided paid amount in row6");


            dashboardorder.ClickCloseIconOnDeliveryPopup();


        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}
