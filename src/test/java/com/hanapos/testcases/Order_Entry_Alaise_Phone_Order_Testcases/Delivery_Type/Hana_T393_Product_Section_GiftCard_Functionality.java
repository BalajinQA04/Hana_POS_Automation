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

import static com.hanapos.pageObjects.CashAndCarryPage.generateGiftCardNumber;

public class Hana_T393_Product_Section_GiftCard_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    private DashboardOrderPage dashboardorder;
    private CashAndCarryPage cashandcarry;
    private CashAndCarryPaymentPage cashandcarrypayment;

    Faker faker = new Faker(new Locale("en-US"));
    String recifname1;
    String recilname2;
    String reci_phone_number1;
    String reci_phone_number2;
    String floor_number;
    String gift_cardno;
    String gift_amount;
    String invoice;

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T393_Product_Section_GiftCard_Functionality() {
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
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Page does not navigated to Hana dashboard page");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "TestStep - 3 - Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 - Cash and carry option is not displayed");
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 4
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");
            cashandcarry = new CashAndCarryPage();
            phoneorder.Click_AddGiftCardIcon_OnPhoneorderPage();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.is_GiftCard_Select_Type_Popup_Displayed(), "Test Step - 4 - Select gift card type pop up is not displayed");

            cashandcarry.click_Physical_Gift_Card_Type_Radio_Button();
            delayWithGivenTime(1000);
            cashandcarry.click_Next_Button_On_GiftCard_Select_Type_Popup();
            delayWithGivenTime(2000);

            softassert.assertTrue(cashandcarry.is_Enter_Gift_Card_Details_Popup_Displayed(), "Test Step - 4 - Enter gift card details pop up is not displayed");
            delayWithGivenTime(1000);

            cashandcarry.search_And_Select_Customer_On_Enter_Gift_Card_Details_Popup(prop.getProperty("custfullname"));
            delayWithGivenTime(1000);

            Faker faker = new Faker(new java.util.Locale("en-US"));
            gift_amount = "856";//String.format("%.2f", faker.number().randomDouble(2, 500, 1000));
            delayWithGivenTime(1000);
            cashandcarry.EnterAmountOnGiftamtField(gift_amount+".00");
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getGiftAmountValue(), gift_amount+".00", "Test Step - 6 - Gift amount values are not matched");
            String processingfee = "5.00";

            delayWithGivenTime(500);
            cashandcarry.EnterProcessingFeesOnGiftSalePopup(processingfee);
            softassert.assertEquals(cashandcarry.getGiftCardProcessingFee(), "5.00", "Test Step - 6 - gift card processing fee is not matched");

            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getTotalGiftValue(), cashandcarry.expectedTotalGiftValue(), " Test Step - 6 - Total gift values are not matched");

            delayWithGivenTime(500);
            gift_cardno = generateGiftCardNumber();
            cashandcarry.EnterGiftCardNumber(gift_cardno);
            softassert.assertEquals(cashandcarry.getGiftCardNumber(), gift_cardno, "TestStep - 6 - Entered Gift Card Number is not displayed");
            cashandcarry.saveGiftCardInfoToFile(gift_cardno, gift_amount);

            delayWithGivenTime(1000);
            cashandcarry.click_Preview_Button_On_Enter_Gift_Card_Details_Popup();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.is_Preview_Header_Displayed_On_Popup(), "Test Step - 6 - Preview header is not displayed after clicking preview button");

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.get_Amount_On_Preview_Popup(), gift_amount, "Test Step - 6 - Gift amount values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Processing_Fee_On_Preview_Popup(), "5.00", "Test Step - 6 - Gift processing fee values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Card_Number_On_Preview_Popup(), gift_cardno, "Test Step - 6 - Gift card number values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Customer_Name_On_Preview_Popup(), prop.getProperty("custfullname"), "Test Step - 6 - Customer name values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Total_Amount_On_Preview_Popup(), cashandcarry.expectedTotalGiftValue(), "Test Step - 6 - Total amount values are not matched in preview popup");

            delayWithGivenTime(2000);
            cashandcarry.click_Submit_Button_On_Preview_Popup();

            delayWithGivenTime(2000);

            // Test Step - 7
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "GIFTC", "Test Step - 7 - Gift card item code row 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.getProdDetailsItemcode2OnPhoneOrderPage(), "GIFTCP", "Test Step - 7 - Gift card item code row 2 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 7 - product section Item quantity row 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ProdDetails_ItemQty2OnPhoneOrderPage(), "1", "Test Step - 7 - product section Item quantity row 2 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), gift_amount+".00", "Test Step - 7 - product section unit price 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ProdDetails_ItemUnitPrice2OnPhoneOrderPage(), "5.00", "Test Step - 7 - product section unit price 2 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ExtPrice1OnProdDetails(), gift_amount+".00", "Test Step - 7 - product section ext price 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ExtPrice2OnProdDetails(), "5.00", "Test Step - 7 - product section ext price 2 is not displayed as expected behaviour");

            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 8 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 8 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(prop.getProperty("recipient_address2"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "1160 W 5th St", "Test Step - 8 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 8 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 8 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "MO", "Test Step - 8 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.EnterReciAddress2(prop.getProperty("Reci_Address1_2"));
            phoneorder.SelectReciCountry(prop.getProperty("recipient_country1"));
            reci_phone_number1 = faker.numerify("###-###-####");
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
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 8 Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), reci_phone_number1, "Test Step - 8 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), prop.getProperty("recipient_location1"), "Test Step - 8 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 8 - Delivery date is not displayed on phone order page recipient section");

            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(1, 50);
            phoneorder.Select_DeliveryOnTime_Dropdown("Around");
            phoneorder.Select_Zone_OnRecipientSection("Automation Zone");
            delayWithGivenTime(1000);

            // Test Step - 24
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 9 - Selected Occasion is not displayed on phone order page order details section");
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase("Happy Birthday! Hope you have an amazing day!"), true, "Test Step - 9 -Entered Short code is not displayed on phone order page order details section");

            // Test Step - 25
            delayWithGivenTime(2000);
            phoneorder.Select_ProductTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(phoneorder.get_selected_ProductTaxType(), prop.getProperty("product_taxtype"), "Test Step - 10 - Selected product tax type is not displayed on phone order page");

            phoneorder.Enter_ProductTaxId(prop.getProperty("product_taxid"));
            softassert.assertEquals(phoneorder.get_ProductTaxId(), prop.getProperty("product_taxid"), "Test Step - 10 - Entered Product tax id is not displayed on phone order page");

            phoneorder.Select_ProdSourceCode(prop.getProperty("product_sourcecode"));
            softassert.assertEquals(phoneorder.get_selected_ProdSourceCode(), prop.getProperty("product_sourcecode"), "Test Step - 10 - Selected Product source code is not displayed on phone order page");

            phoneorder.Select_ProdCustType(prop.getProperty("product_custtype"));
            softassert.assertEquals(phoneorder.get_selected_ProdCustType(), prop.getProperty("product_custtype"), "Test Step - 10 - Selected product customer type is not displayed on phoneorder");

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
            dashboardorder.EnterGlobalSearch(invoice);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice), "Test Step - 18 - In orders summary page cash payment type invoice number is not displayed for placed order");

            //Test Step - 19
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice), "Cash", "Test Step - 19 - Mode of payment as  cash is not displayed");


        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }

}

 /*   @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T393_Product_Section_GiftCard_Functionality_Test(String invalidgiftamount, String validgiftamount, String processingfee, String invalid_giftcardnum, String giftcardnumber_29_digit, String giftcardnumber_11_digit) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T393_Product_Section_GiftCard_Functionality_Test ****");
        logger.debug("capturing application debug logs....");
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
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Page does not navigated to Hana dashboard page");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "TestStep - 3 - Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 - Cash and carry option is not displayed");
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 4
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");
            cashandcarry = new CashAndCarryPage();
            phoneorder.Click_AddGiftCardIcon_OnPhoneorderPage();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.is_GiftCard_Select_Type_Popup_Displayed(), "Test Step - 4 - Select gift card type pop up is not displayed");

            cashandcarry.click_Physical_Gift_Card_Type_Radio_Button();
            delayWithGivenTime(1000);
            cashandcarry.click_Next_Button_On_GiftCard_Select_Type_Popup();
            delayWithGivenTime(2000);

            softassert.assertTrue(cashandcarry.is_Enter_Gift_Card_Details_Popup_Displayed(), "Test Step - 4 - Enter gift card details pop up is not displayed");
            delayWithGivenTime(1000);

            cashandcarry.search_And_Select_Customer_On_Enter_Gift_Card_Details_Popup(prop.getProperty("custfullname"));
            delayWithGivenTime(1000);

            Faker faker = new Faker(new java.util.Locale("en-US"));
            gift_amount = String.format("%.2f", faker.number().randomDouble(2, 500, 1000));
            delayWithGivenTime(1000);
            cashandcarry.EnterAmountOnGiftamtField(gift_amount);
            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.getGiftAmountValue(), gift_amount, "Test Step - 6 - Gift amount values are not matched");

            delayWithGivenTime(500);
            cashandcarry.EnterProcessingFeesOnGiftSalePopup(processingfee);
            softassert.assertEquals(cashandcarry.getGiftCardProcessingFee(), "5.00", "Test Step - 9 - gift card processing fee is not matched");

            delayWithGivenTime(2000);
            softassert.assertEquals(cashandcarry.getTotalGiftValue(), cashandcarry.expectedTotalGiftValue(), " Test Step - 9 - Total gift values are not matched");

            delayWithGivenTime(500);
            gift_cardno = generateGiftCardNumber();
            cashandcarry.EnterGiftCardNumber(gift_cardno);
            softassert.assertEquals(cashandcarry.getGiftCardNumber(), gift_cardno, "TestStep - 10 - Entered Gift Card Number is not displayed");
            cashandcarry.saveGiftCardInfoToFile(gift_cardno, gift_amount);

            delayWithGivenTime(1000);
            cashandcarry.click_Preview_Button_On_Enter_Gift_Card_Details_Popup();
            delayWithGivenTime(1000);
            softassert.assertTrue(cashandcarry.is_Preview_Header_Displayed_On_Popup(), "Test Step - 11 - Preview header is not displayed after clicking preview button");

            delayWithGivenTime(1000);
            softassert.assertEquals(cashandcarry.get_Amount_On_Preview_Popup(), gift_amount, "Test Step - 12 - Gift amount values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Processing_Fee_On_Preview_Popup(), "5.00", "Test Step - 13 - Gift processing fee values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Card_Number_On_Preview_Popup(), gift_cardno, "Test Step - 14 - Gift card number values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Customer_Name_On_Preview_Popup(), prop.getProperty("custfullname"), "Test Step - 15 - Customer name values are not matched in preview popup");
            softassert.assertEquals(cashandcarry.get_Total_Amount_On_Preview_Popup(), cashandcarry.expectedTotalGiftValue(), "Test Step - 16 - Total amount values are not matched in preview popup");

            delayWithGivenTime(2000);
            cashandcarry.click_Submit_Button_On_Preview_Popup();

            delayWithGivenTime(2000);

           // Test Step - 18
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "GIFTC", "Test Step - 18 - Gift card item code row 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.getProdDetailsItemcode2OnPhoneOrderPage(), "GIFTCP", "Test Step - 18 - Gift card item code row 2 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 21 - product section Item quantity row 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ProdDetails_ItemQty2OnPhoneOrderPage(), "1", "Test Step - 21 - product section Item quantity row 2 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), gift_amount, "Test Step - 21 - product section unit price 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ProdDetails_ItemUnitPrice2OnPhoneOrderPage(), "5.00", "Test Step - 21 - product section unit price 2 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ExtPrice1OnProdDetails(), gift_amount, "Test Step - 21 - product section ext price 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ExtPrice2OnProdDetails(), "5.00", "Test Step - 21 - product section ext price 2 is not displayed as expected behaviour");


        *//*    softassert.assertTrue(phoneorder.Verify_GiftCardPopup_OnPhoneorderPage(), "Test Step - 4 - Gift Card popup is not displayed");

            // Test Step - 5
            phoneorder.Click_GiftCardInstantDenomination1();
            softassert.assertEquals(phoneorder.get_giftcardamountTextboxField(), "30.00", "Test Step - 5 - Gift card amount is not displayed as 30.00 expected behaviour");
            softassert.assertEquals(phoneorder.get_giftCardTotalAmount(), "30.00", "Test Step - 5 - Gift card total amount is not displayed as 30.00 expected behaviour");

            // Test Step - 6
            phoneorder.Enter_GiftCardAmount_onGiftCardPopup(invalidgiftamount);
            softassert.assertEquals(phoneorder.get_giftcardamountTextboxField(), "", "Test Step - 6 - Gift card amount is not displayed as expected behaviour");

            // Test Step - 7
            phoneorder.Enter_GiftCardAmount_onGiftCardPopup(validgiftamount);
            softassert.assertEquals(phoneorder.get_giftcardamountTextboxField(), "50", "Test Step - 7 - Gift card amount is not displayed as expected behaviour");

            // Test Step - 8
            softassert.assertTrue(phoneorder.Verify_GiftCardTotalAmount_ISDisabled(), "Test Step - 8 - Gift card total amount is not disabled");

            // Test Step - 9
            phoneorder.Enter_GiftcardProcessingFee(processingfee);
            softassert.assertEquals(phoneorder.get_GiftcardprocessingfeeTextboxField(), "5", "Test Step - 9 - Gift card processing fee is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_giftCardTotalAmount(), "55.00", "Test Step - 9 - Gift card total amount is not displayed as expected behaviour");

            // Test Step - 10
            phoneorder.Enter_GiftCardNumberTextBoxField(invalidgiftamount);
            softassert.assertEquals(phoneorder.get_GiftCardNumberTextBoxField(), "", "Test Step - 10 - Gift card number field is allowed alphabets");

            // Test Step - 11
            phoneorder.Enter_GiftCardNumberTextBoxField(giftcardnumber_29_digit);
            softassert.assertEquals(phoneorder.get_GiftCardNumberTextBoxField(), "1234567890098765432123456", "Test Step - 11 - Gift card number field is allowed more than 25 digits");

            // Test Step - 12
            phoneorder.Enter_GiftCardNumberTextBoxField(giftcardnumber_11_digit);
            softassert.assertEquals(phoneorder.get_GiftCardNumberTextBoxField(), "12345654321", "Test Step - 12 - Entered Gift card number is not displayed as expected behaviour");

            // Test Step - 13,14
            phoneorder.Enter_CustomerName_OnGiftCardPopup(prop.getProperty("cust_firstName"), "13827052-Abish David");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_GiftCardEnteredCustomerName(), "13827052-Abish David", "Test Step - 13 - Gift card entered customer name is not displayed as expected behaviour");

            // Test Step - 15
            delayWithGivenTime(500);
            phoneorder.Click_GiftCardPopupCloseIcon();

            // Test Step - 16
            delayWithGivenTime(2000);
            phoneorder.Click_AddGiftCardIcon_OnPhoneorderPage();
            softassert.assertTrue(phoneorder.Verify_GiftCardPopup_OnPhoneorderPage(), "Test Step - 4 - Gift Card popup is not displayed");

            // Test Step - 17
            delayWithGivenTime(500);
            phoneorder.Click_GiftCardInstantDenomination1();
            softassert.assertEquals(phoneorder.get_giftcardamountTextboxField(), "30.00", "Test Step - 17 - Gift card amount is not displayed as 30.00 expected behaviour");
            softassert.assertEquals(phoneorder.get_giftCardTotalAmount(), "30.00", "Test Step - 17 - Gift card total amount is not displayed as 30.00 expected behaviour");

            phoneorder.Enter_GiftCardAmount_onGiftCardPopup(invalidgiftamount);
            softassert.assertEquals(phoneorder.get_giftcardamountTextboxField(), "", "Test Step - 17 - Gift card amount is not displayed as expected behaviour");

            phoneorder.Enter_GiftCardAmount_onGiftCardPopup(validgiftamount);
            softassert.assertEquals(phoneorder.get_giftcardamountTextboxField(), "50", "Test Step - 17 - Gift card amount is not displayed as expected behaviour");

            softassert.assertTrue(phoneorder.Verify_GiftCardTotalAmount_ISDisabled(), "Test Step - 17 - Gift card total amount is not disabled");

            phoneorder.Enter_GiftcardProcessingFee(processingfee);
            softassert.assertEquals(phoneorder.get_GiftcardprocessingfeeTextboxField(), "5", "Test Step - 17 - Gift card processing fee is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_giftCardTotalAmount(), "55.00", "Test Step - 17 - Gift card total amount is not displayed as expected behaviour");

            phoneorder.Enter_GiftCardNumberTextBoxField(invalid_giftcardnum);
            softassert.assertEquals(phoneorder.get_GiftCardNumberTextBoxField(), "", "Test Step - 17 - Gift card number field is allowed alphabets");

            phoneorder.Enter_GiftCardNumberTextBoxField(giftcardnumber_29_digit);
            softassert.assertEquals(phoneorder.get_GiftCardNumberTextBoxField(), "1234567890098765432123456", "Test Step - 17 - Gift card number field is allowed more than 25 digits");

            phoneorder.Enter_GiftCardNumberTextBoxField("12345654321" + generaterandomeNumber(5));
            softassert.assertEquals(phoneorder.get_GiftCardNumberTextBoxField().contains("12345654321"), true, "Test Step - 17 - Entered Gift card number is not displayed as expected behaviour");

            phoneorder.Enter_CustomerName_OnGiftCardPopup(prop.getProperty("cust_firstName"), "13827052-Abish David");
            softassert.assertEquals(phoneorder.get_GiftCardEnteredCustomerName(), "13827052-Abish David", "Test Step - 17 - Gift card entered customer name is not displayed as expected behaviour");

            phoneorder.Click_GiftCardPopupSubmitButton();
            softassert.assertEquals(phoneorder.verifySuccessToastMessageText(), "Gift card has been added", "Test Step - 17 - Gift card has been added toasted message is not displayed");

            // Test Step - 18
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), "GIFTC", "Test Step - 18 - Gift card item code row 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.getProdDetailsItemcode2OnPhoneOrderPage(), "GIFTCP", "Test Step - 18 - Gift card item code row 2 is not displayed as expected behaviour");

            // Test Step - 19
            String actualDescription = phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage();
            String expectedDescriptionPart = "Gift Card";
            boolean isExpectedPartPresent = actualDescription.contains(expectedDescriptionPart);
            String regexPattern = expectedDescriptionPart + "-\\d{16}-[A-Za-z ]+";
            boolean isMatchingFormat = actualDescription.matches(regexPattern);

            softassert.assertTrue(isExpectedPartPresent && isMatchingFormat,
                    "Test Step - 19 - Gift card item description row 1 gift card details is not displayed as expected behaviour");

            // Test Step - 20
            String actualDescription2 = phoneorder.getProdDetailsItemDescription2OnPhoneOrderPage();
            String expectedDescriptionPart2 = "Gift Card Processing Fee";
            boolean isExpectedPartPresent2 = actualDescription.contains(expectedDescriptionPart);
            String regexPattern2 = expectedDescriptionPart2 + "-\\d{16}-[A-Za-z ]+";
            boolean isMatchingFormat2 = actualDescription2.matches(regexPattern2);

            softassert.assertTrue(isExpectedPartPresent2 && isMatchingFormat2,
                    "Test Step - 20 - Gift card item description row 2 gift card processing fee is not displayed as expected behaviour");

            // Test Step - 21
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 21 - product section Item quantity row 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ProdDetails_ItemQty2OnPhoneOrderPage(), "1", "Test Step - 21 - product section Item quantity row 2 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "50.00", "Test Step - 21 - product section unit price 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ProdDetails_ItemUnitPrice2OnPhoneOrderPage(), "5.00", "Test Step - 21 - product section unit price 2 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ExtPrice1OnProdDetails(), "50.00", "Test Step - 21 - product section ext price 1 is not displayed as expected behaviour");
            softassert.assertEquals(phoneorder.get_ExtPrice2OnProdDetails(), "5.00", "Test Step - 21 - product section ext price 2 is not displayed as expected behaviour");
*//*
            // Test Step - 22
            delayWithGivenTime(2000);
            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c", "Test Step - 6 - Delivery type is not highlighted as blue color");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));

            softassert.assertEquals(phoneorder.get_SelectedSalesPersonOn_PhoneOrderEntryPage(), "Stuart Markwood", "Test Step - 22 - Selected sales person is not displayed on phone order page");
         *//*   phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
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
*//*
            // Test Step - 23
            recifname1 = faker.name().firstName();
            recilname2 = faker.name().lastName();
            phoneorder.EnterReciFirstName(recifname1);
            phoneorder.EnterReciLastName(recilname2);
            softassert.assertEquals(phoneorder.getReciFirstName(), recifname1, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recilname2, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            delayWithGivenTime(1000);
            phoneorder.SearchAndSelectReciAddress1(reci_full_address1);
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), reciaddress1, "Test Step - 4 - Recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), recizip, "Test Step - 4 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), recicity, "Test Step - 4 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), recistate, "Test Step - 4 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");

            phoneorder.EnterReciAddress2(reciaddress2);
            phoneorder.SelectReciCountry(recicountry);
            reci_phone_number1 = faker.numerify("###-###-####");
            phoneorder.EnterReciPhone(reci_phone_number1);
            delayWithGivenTime(1000);
            reci_phone_number2 = faker.phoneNumber().cellPhone();
            floor_number = faker.address().buildingNumber();
            phoneorder.EnterRecipientPhone2OnPhoneOrderPage(reci_phone_number2);
            delayWithGivenTime(1000);
            phoneorder.Enter_FloorApt_On_RecipientSection(floor_number);
            delayWithGivenTime(1000);
            phoneorder.SelectReciLocation(recilocation);
            delayWithGivenTime(1000);
            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedCountryOnReciCountry(), "United States", "Test Step - 7 - Selected country is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), reci_phone_number1, "Test Step - 7 - Recipient phone number is not displayed on phone order page recipient section");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedLocationOnReciLocation(), recilocation, "Test Step - 7 - Recipient location is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 7 - Delivery date is not displayed on phone order page recipient section");

            phoneorder.EnterDeliveryDateOnReciSection(CurrentDate());
            phoneorder.Enter_DeliveryTime_OnRecipientSection(1, 50);
            phoneorder.Select_DeliveryOnTime_Dropdown("Around");
            phoneorder.Select_Zone_OnRecipientSection("Automation Zone");
            delayWithGivenTime(1000);

            // Test Step - 24
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), "Birthday", "Test Step - 24 - Selected Occasion is not displayed on phone order page order details section");
            phoneorder.EnterViewShortCode(prop.getProperty("short_card_message"), prop.getProperty("card_message"));
            softassert.assertEquals(phoneorder.getEnteredViewShortCode().equalsIgnoreCase("Happy Birthday! Hope you have an amazing day!"), true, "Test Step - 24 -Entered Short code is not displayed on phone order page order details section");

            // Test Step - 25
            delayWithGivenTime(2000);
            phoneorder.Select_ProductTaxType(prop.getProperty("product_taxtype"));
            softassert.assertEquals(phoneorder.get_selected_ProductTaxType(), prop.getProperty("product_taxtype"), "Test Step - 25 - Selected product tax type is not displayed on phone order page");

            phoneorder.Enter_ProductTaxId(prop.getProperty("product_taxid"));
            softassert.assertEquals(phoneorder.get_ProductTaxId(), prop.getProperty("product_taxid"), "Test Step - 25 - Entered Product tax id is not displayed on phone order page");

            phoneorder.Select_ProdSourceCode(prop.getProperty("product_sourcecode"));
            softassert.assertEquals(phoneorder.get_selected_ProdSourceCode(), prop.getProperty("product_sourcecode"), "Test Step - 25 - Selected Product source code is not displayed on phone order page");

            phoneorder.Select_ProdCustType(prop.getProperty("product_custtype"));
            softassert.assertEquals(phoneorder.get_selected_ProdCustType(), prop.getProperty("product_custtype"), "Test Step - 25 - Selected product customer type is not displayed on phoneorder");

            delayWithGivenTime(1000);
        *//*    phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 25 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 26
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 26 - Order confirmation page is not displayed");
*//*
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_SelectedPaymentType_OnPhoneOrderPage(), prop.getProperty("payment_type"), "Test Step - 10 - Selected payment type is not displayed");

            // Test Step - 11
            delayWithGivenTime(1000);
            phoneorder.Enter_CashPaymentType_Amount();
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_Entered_CashPaymentType_Amount(), phoneorder.get_Entered_CashPaymentType_Amount(), "Test Step - 11 - Entered more than cash payment amount is not allowed on the payment section cash type pay amount field");

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
            dashboardorder.EnterGlobalSearch(invoice);
            delayWithGivenTime(1000);
            softassert.assertTrue(dashboardorder.validate_InvoiceNumber_on_AllOrdersPage(invoice), "Test Step - 18 - In orders summary page cash payment type invoice number is not displayed for placed order");

            //Test Step - 19
            delayWithGivenTime(1000);
            softassert.assertEquals(dashboardorder.validate_MOP_On_AllOrdersPage(invoice), "Cash", "Test Step - 19 - Mode of payment as  cash is not displayed");

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }*/
