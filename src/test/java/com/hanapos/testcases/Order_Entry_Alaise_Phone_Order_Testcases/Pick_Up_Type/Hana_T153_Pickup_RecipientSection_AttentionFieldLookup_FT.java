package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T153_Pickup_RecipientSection_AttentionFieldLookup_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T153_Pickup_RecipientSection_AttentionFieldLookup_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana_T153_Pickup_RecipientSection_AttentionFieldLookup_FT ****");
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

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Pickup type is not highlighted in blue color");

            // Test Step - 6
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
            phoneorder.ClickPickupTypeOnPhoneOrderPage();

            // Test Step - 7
            phoneorder.ClickReciNameOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 7 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 7 - Displayed last name is not matched with customer lastname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), "114 N CHURCH ST", "Test Step - 7 - Displayed recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "PICK UP", "Test Step - 7 - Displayed recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 7 - Recipient phone number is not auto populated on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "956-655-0756", "Test Step - 7 - Recipient alt phone number is not auto populated on phone order page recipient section");

            // Test Step - 8
            softassert.assertTrue(phoneorder.Verify_RecipientAttentionField_IsDisplayed(), "Test Step - 8 - Attention field on recipient section is not displayed on phone order page");

            // Test Step - 9
            phoneorder.EnterRecipientAttention(prop.getProperty("recipient_Attention1"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_RecipientAttention(), "Chicago", "Test Step - 9 - Recipient attention is not matched with Attention field on phone order page recipient section");

            // Test Step - 10
            phoneorder.EnterRecipientAttention(prop.getProperty("recipient_Attention1"));
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_Autosuggestion_Displayed_OnReciAttention(), "Test Step - 10 - Recipient attention field autosuggestions are not displayed on phone order page");

            // Test Step - 11
            phoneorder.EnterRecipientAttention(prop.getProperty("recipient_Attention2"));
            phoneorder.SearchAndSelect_ReciAttention(prop.getProperty("attention_city_state_country"));

            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_RecipientAttention(), "Washington Square Park", "Test Step - 11 - Recipient attention is not matched with Attention field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), "901 N Clark St", "Test Step - 11 - Recipient attention is not matched with Attention field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "60610", "Test Step - 11 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Chicago", "Test Step - 11 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "IL", "Test Step - 11 - Recipient phone number is not auto populated on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "3127427896", "Test Step - 11 - Recipient phone number is not auto populated on phone order page recipient section");
            softassert.assertTrue(phoneorder.Verify_AddressverifiedByGoogle_ToastMsgAppears(), "Test Step - 11 -  Success toast message for address verification is not displayed on recipient section in phone order page");

            // Test Step - 12
            delayWithGivenTime(1000);
            phoneorder.EnterRecipientAttention(prop.getProperty("recipient_Attention3"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_RecipientAttention(), prop.getProperty("recipient_Attention3"), "Test Step - 12 - Recipient attention displayed value is not matched with expected Attention field on phone order page recipient section");

            // Test Step - 13
            delayWithGivenTime(1000);
            phoneorder.EnterRecipientAttention(prop.getProperty("Full_reci_Attention2"));
            phoneorder.SearchAndSelect_ReciAttention(prop.getProperty("Full_reci_Attention2"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_RecipientAttention(), "Chicago Midway International Airport", "Test Step - 13 - Recipient attention is not matched with Attention field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), "5700 S Cicero Ave", "Test Step - 13 - Recipient attention is not matched with Attention field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "60638", "Test Step - 13 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Chicago", "Test Step - 13 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "IL", "Test Step - 13 - Recipient phone number is not matched with customer phonenumber 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciPhone(), "7738380600", "Test Step - 13 - Recipient phone number is not auto populated on phone order page recipient section");

        } catch (Exception e) {
            softassert.fail(e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}