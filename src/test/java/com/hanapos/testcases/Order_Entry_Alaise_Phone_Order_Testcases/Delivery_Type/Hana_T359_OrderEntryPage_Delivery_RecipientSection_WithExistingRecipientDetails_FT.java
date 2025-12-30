package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.pageObjects.Order_Confirmation_Page;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.Locale;

public class Hana_T359_OrderEntryPage_Delivery_RecipientSection_WithExistingRecipientDetails_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private Order_Confirmation_Page orderconfirmationpage;
    Faker faker = new Faker(new Locale("en-US"));
    String recipient_firstname;
    String recipient_lastname;
    String recipientphone;
    String recipientfulladdress1;

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T359_OrderEntryPage_Delivery_RecipientSection_WithExistingRecipientDetails_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T359_OrderEntryPage_Delivery_RecipientSection_WithExistingRecipientDetails_Functionality_Test  ****");
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
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 : Hana Dashboard Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");

            // Test Step - 3
            delayWithGivenTime(2000);
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
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c",
                    "Test Step - 5 - Delivery type is not highlighted as blue color");

            //Test Step - 6
            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 6 - Customer section is not displayed on phone order page");
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
            phoneorder.EnterReciFirstName(prop.getProperty("short_reci_fname"));
            softassert.assertEquals(phoneorder.getReciFirstName(), prop.getProperty("short_reci_fname"), "Test Step - 7 - Entered Recipient First name is not displayed on phone order page");

            // Test Step - 8
            softassert.assertTrue(phoneorder.Verify_RecipientName_Autosuggestion(), "Test Step - 8 - Recipient first name autosuggestions are not displayed on phone order page");

            delayWithGivenTime(1000);
            recipient_firstname = faker.name().firstName();
            recipient_lastname = faker.name().lastName();
            recipientphone = faker.numerify("###-###-####");
            recipientfulladdress1 = prop.getProperty("recipient_address2");
            phoneorder.EnterReciFirstName(recipient_firstname);
            phoneorder.EnterReciLastName(recipient_lastname);
            softassert.assertEquals(phoneorder.getReciFirstName(), recipient_firstname, "Test Step - 4 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), recipient_lastname, "Test Step - 4 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            // Test Step - 9
            phoneorder.SearchAndSelect_Recipient_OnRecipient_Section("Liam", "1160 W 5th St");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Liam", "Test Step - 9 - Entered first name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "Jack Benjamin", "Test Step - 9 - Entered last name is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), "1160 W 5th St", "Test Step - 9 - Entered address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "", "Test Step - 9 - Entered address 2 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "63090", "Test Step - 9 - Entered zipcode is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Washington", "Test Step - 9 - Entered city is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "956-000-0756", "Test Step - 9 - Recipient phone number is not displayed on phone order page recipient section");

            // Test Step - 10
            softassert.assertTrue(phoneorder.Verify_AddressverifiedByGoogle_ToastMsgAppears(), "Test Step - 10 - Address verified by google toast message is not displayed on phone order page recipient section");

            // Test Step - 11
            softassert.assertEquals(phoneorder.getDeliveryDateOnReciSection(), CurrentDate(), "Test Step - 11 - Delivery date is not displayed on phone order page recipient section");

            // Test Step - 12 & 13
            delayWithGivenTime(1000);
            phoneorder.EnterReciFirstName("Abish1");
            phoneorder.EnterReciLastName("David1");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish1", "Test Step - 13 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getReciLastName(), "David1", "Test Step - 13 - Last name is not displayed on phone order page");
            delayWithGivenTime(500);

            // Test Step - 14
            // Dynamically getting the distance and verifying
            //  softassert.assertEquals(phoneorder.Verify_RecipientKMS_Appears(), "2845.8 KM(Partially Verified)", "Test Step - 14 - Recipient distance kms is not displayed on phone order page");

            // Test Step - 15
            phoneorder.SelectOccasion_On_OrderDetails_In_PhoneOrderPage(prop.getProperty("occasion"));
            phoneorder.EnterViewShortCode();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getSelectedOccasionOnPhoneOrderPage(), prop.getProperty("occasion"), "Test Step - 15 - Selected Occasion is not displayed on phone order page order details section");
            softassert.assertTrue(phoneorder.getEnteredViewShortCode().equalsIgnoreCase(prop.getProperty("card_message")), "Test Step - 15 -Entered Short code is not displayed on phone order page order details section");
            delayWithGivenTime(2000);

            // Test Step - 16
            //	sometimes search and select product is not working
            phoneorder.SearchandSelect_ItemcodeOnPhoneOrderPage(prop.getProperty("product_itemcode1"), prop.getProperty("product_description1"));

            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getProdDetailsItemcode1OnPhoneOrderPage(), prop.getProperty("product_itemcode1"), "Test Step - 16 - Item code is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdDetailsItemDescription1OnPhoneOrderPage(), prop.getProperty("product_description1"), "Test Step - 16 - Item description is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getProdItemQty1OnPhoneOrderPage(), "1", "Test Step - 16 - Item quantity is not displayed on phone order page product details section");
            softassert.assertEquals(phoneorder.getUnitPriceOnProdDetails(), "299.00", "Test Step - 16 - Item price is not displayed on phone order page product details section");
            delayWithGivenTime(2000);

            // Test Step - 17
            phoneorder.SelectPaymentTypeOnPhoneOrderPage_PaymentSection(prop.getProperty("payment_type"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.getDisplayedPaymentTypeSelectedOption(), prop.getProperty("payment_type"), "Test Step - 17 - Selected Payment type is not displayed on phone order page payment section");
            delayWithGivenTime(2000);
            phoneorder.EnterCashAmount();
            delayWithGivenTime(3000);
            phoneorder.SelectCashRegistry_On_CashPaymentType(prop.getProperty("payment_cash_registry"));
            phoneorder.ClickPlaceOrderButton();

            // Test Step - 18
            delayWithGivenTime(1000);
            softassert.assertTrue(phoneorder.VerifyConfirmationPopupOnPhoneOrderPage(), "Test Step - 18 - Confirmation popup is not displayed on phone order page");
            delayWithGivenTime(2000);
            phoneorder.ClickSubmitButton_On_ConfirmationPopup();
            delayWithGivenTime(2000);
            orderconfirmationpage = new Order_Confirmation_Page();
            softassert.assertTrue(orderconfirmationpage.VerifyOrderConfirmationPage(), "Test Step - 18 - Order confirmation page is not displayed");

            // Test Step - 19
            delayWithGivenTime(2000);
            softassert.assertEquals(orderconfirmationpage.getRecipientFname(), "Abish1");
            softassert.assertEquals(orderconfirmationpage.getRecipientLname(), "David1");
        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}