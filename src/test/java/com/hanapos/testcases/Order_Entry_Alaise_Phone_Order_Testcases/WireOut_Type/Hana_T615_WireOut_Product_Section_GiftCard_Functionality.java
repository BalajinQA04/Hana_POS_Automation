package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireOut_Type;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.pageObjects.Order_Confirmation_Page;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import com.hanapos.utilities.DataLibrary;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Hana_T615_WireOut_Product_Section_GiftCard_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    public static final String dataSheetName = "Hana_T179";

    @DataProvider(name = "fetch_Excel_Data")
    public Object[][] fetchData() throws IOException {
        return DataLibrary.readExcelData(dataSheetName);
    }

    @Epic("Phone Order Module - Wire out Type")
    @Test(enabled = false, groups = {"Regression"}, dataProvider = "fetch_Excel_Data")
    public void Validate_Hana_T615_WireOut_Product_Section_GiftCard_Functionality_Test(String invalidgiftamount, String validgiftamount, String processingfee, String invalid_giftcardnum, String giftcardnumber_29_digit, String giftcardnumber_11_digit) {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T615_WireOut_Product_Section_GiftCard_Functionality_Test ****");
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
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "TestStep - 3 - Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 - Cash and carry option is not displayed");
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 4
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Click_WireOut_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_On_WireOut_PhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Wire out Delivery type is not highlighted in blue color");

            phoneorder.Click_AddGiftCardIcon_OnPhoneorderPage();
            softassert.assertTrue(phoneorder.Verify_GiftCardPopup_OnPhoneorderPage(), "Test Step - 4 - Gift Card popup is not displayed");

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

            // Test Step - 22
            delayWithGivenTime(2000);
            phoneorder.Click_WireOut_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_On_WireOut_PhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Wire out Delivery type is not highlighted in blue color");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));

            softassert.assertEquals(phoneorder.get_SelectedSalesPersonOn_PhoneOrderEntryPage(), "Stuart Markwood", "Test Step - 22 - Selected sales person is not displayed on phone order page");
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
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
            phoneorder.EnterReciFirstName(prop.getProperty("recipient_firstName1"));
            phoneorder.EnterReciLastName(prop.getProperty("recipient_lastName1"));
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
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type_invoice"));
            delayWithGivenTime(1000);
            phoneorder.Select_WireOut_PaymentMethod(prop.getProperty("wireout_payment_method"));
            phoneorder.Enter_WireoutFlorist(prop.getProperty("wireout_choose_florist"));
            phoneorder.set_Amount_wireout_paymentsection();
            phoneorder.ClickPlaceOrderButton();
            delayWithGivenTime(1000);
            getDriver().switchTo().activeElement();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 10 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            phoneorder.ClickPlaceOrderButton();
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 25 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 26
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 26 - Order confirmation page is not displayed");
            logger.info("**** Finished  Validate_Hana_T615_WireOut_Product_Section_GiftCard_Functionality_Test ****");

        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail("Test case failed due to exception " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}