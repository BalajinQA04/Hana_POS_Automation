package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.WireIn_Type;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T463_RecipientSection_AttentionFieldLookup_Functionality extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Wire In Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T463_RecipientSection_AttentionFieldLookup_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T463_RecipientSection_AttentionFieldLookup_Functionality_Test ****");
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
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2 - Hana dashboard page is not displayed");
            logger.info("User navigated to hana dashboard page");
            dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));

            // Test Step - 3
            delayWithGivenTime(2000);
            softassert.assertTrue(dashboard.VerifyOrderEntryOptionIsDisplayed(), "Test Step - 3 - Order entry option is not displayed");
            softassert.assertTrue(dashboard.Verify_Cashandcarry_OptionIsDisplayed(), "Test Step - 3 - Cash and carry option is not displayed");

            // Test Step - 4
            dashboard.ClickOrderEntry();
            logger.info("User hover the mouse on New order and clicked on order entry");

            // Test Step - 5
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("shopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("shopname"), "Test Step - 6 - Shop name is not displayed in the WireIn section of phone order page");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("salesperson"));
            softassert.assertEquals(phoneorder.get_SelectedSalesPersonOn_PhoneOrderEntryPage(), prop.getProperty("salesperson"), "Selected Sales Person " + prop.getProperty("salesperson") + " is not displayed on phone order page");
            phoneorder.Click_WireIn_DeliveryType_OnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnWireInTypeOnPhoneOrderPage(), "#676a6c", "Wire In as delivery type is not highlighted in blue color");

            // Test Step - 6
            phoneorder.Enter_WireIn_Fname(prop.getProperty("wirein_cust_firstName"));
            phoneorder.Enter_WireIn_Lname(prop.getProperty("wirein_cust_lastName"));
            phoneorder.Enter_WireIn_ShopCode(prop.getProperty("wirein_shopcode"));
            phoneorder.Enter_WireIn_ShopName(prop.getProperty("wirein_shopname"));
            phoneorder.Select_WireInMethod(prop.getProperty("wirein_type"));
            phoneorder.Enter_WireIn_OrderNumber(prop.getProperty("wirein_ordernumber"));
            phoneorder.Enter_WireIn_PhoneNumber(prop.getProperty("wirein_phonenumber"));
            phoneorder.Enter_WireIn_Email(prop.getProperty("wirein_email"));
            phoneorder.Enter_WireIn_ShopAddress(prop.getProperty("wirein_shopaddress"));
            phoneorder.Enter_WireIn_Comments(prop.getProperty("wirein_comments"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Fname(), "Abish", "Test Step - 7 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Lname(), "David", "Test Step - 7 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopCode(), "1100", "Test Step - 7 - Entered WireIn shop code is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopName(), "Automation Test Flower Shop", "Test Step - 7 - Entered WireIn shop name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Selected_WireInMethod(), "FSN", "Test Step - 7 - Selected WireIn method is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_OrderNumber(), "1122FSN33", "Test Step - 7 - Entered WireIn order number is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_PhoneNumber(), "996-955-0706", "Test Step - 7 - Entered phone number on customer details popup is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Email(), "hanaposqateam@gmail.com", "Test Step - 7 - Entered shop Email id on customer details popup is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_ShopAddress(), "2715 35th Avenue Greeley, CO, USA", "Test Step - 7 - Entered shop address  is not displayed on phone order page");
            softassert.assertEquals(phoneorder.get_Entered_WireIn_Comments(), "Test Automation Wire In Order", "Test Step - 7 - Shop Wire In Comments is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 7
            phoneorder.ClickReciNameOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciFirstName(), "", "Test Step - 8 - Displayed first name is not matched with customer firstname on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "", "Test Step - 8 - Displayed last name is not matched with customer lastname on phone order page recipient section");

            if (phoneorder.getReciAddress2().equals("")) {
                softassert.assertEquals(phoneorder.getReciAddress1(), "", "Test Step - 8 - Displayed recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            } else if (phoneorder.getReciAddress2().equals(prop.getProperty("default_address1"))) {
                softassert.assertEquals(phoneorder.getReciAddress1(), prop.getProperty("default_address1"), "Test Step - 8 - Displayed recipient address 1 is not matched with customer address 1 field on phone order page recipient section");
            }

            if (phoneorder.getReciAddress2().equals("")) {
                softassert.assertEquals(phoneorder.getReciAddress2(), "", "Test Step - 8 - Displayed recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            } else if (phoneorder.getReciAddress2().equals(prop.getProperty("default_address2"))) {
                softassert.assertEquals(phoneorder.getReciAddress2(), prop.getProperty("default_address2"), "Test Step - 8 - Displayed recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            }
            if (phoneorder.getReciPhone().equals(prop.getProperty("cust_phoneNumber"))) {
                softassert.assertEquals(phoneorder.getReciPhone(), prop.getProperty("cust_phoneNumber"), "Test Step - 8 - Recipient phone number is not auto populated on recipient section");
            } else if (phoneorder.getReciPhone().equals(prop.getProperty("default_phone_number1"))) {
                softassert.assertEquals(phoneorder.getReciPhone(), prop.getProperty("default_phone_number1"), "Test Step - 8 - Recipient phone number is not auto populated on recipient section");
            }

            if (phoneorder.getRecipientPhone2OnPhoneOrderPage().equals(prop.getProperty("cust_phoneNumber"))) {
                softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), prop.getProperty("cust_phoneNumber"), "Test Step - 8 - Recipient alt phone number is not auto populated on recipient section");
            } else if (phoneorder.getRecipientPhone2OnPhoneOrderPage().equals("")) {
                softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "", "Test Step - 8 - Recipient alt phone number is not auto populated on recipient section");
            }

            // Test Step - 8
            softassert.assertTrue(phoneorder.Verify_RecipientAttentionField_IsDisplayed(), "Test Step - 8 - Attention field on recipient section is not displayed on phone order page");

            // Test Step - 9
            phoneorder.EnterRecipientAttention(prop.getProperty("recipient_Attention1"));
            delayWithGivenTime(1000);
            softassert.assertEquals(phoneorder.get_RecipientAttention(), "Chicago", "Test Step - 9 - Recipient attention is not matched with Attention field on phone order page recipient section");

            // Test Step - 10
            phoneorder.EnterRecipientAttention(prop.getProperty("recipient_Attention1"));
            delayWithGivenTime(3000);
            softassert.assertTrue(phoneorder.is_Attention_Field_Autosuggestion_Displayed(), "Test Step - 10 - Recipient attention field autosuggestions are not displayed on phone order page");

            // Test Step - 11
            phoneorder.EnterRecipientAttention("4610 N Clark St");
            phoneorder.SearchAndSelect_ReciAttention("Staples, 4610 North Clark Street, Chicago, IL, USA");

            delayWithGivenTime(2000);
            //  softassert.assertEquals(phoneorder.get_RecipientAttention(), "Washington Square Park", "Test Step - 11 - Recipient attention is not matched with Attention field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress1(), "4610 N Clark St", "Test Step - 11 - Recipient attention is not matched with Attention field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciZipcode(), "60640", "Test Step - 11 - Recipient address 2 is not matched with customer address 2 on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciCity(), "Chicago", "Test Step - 11 - Recipient city is not matched with customer city on phone order page recipient section");
            softassert.assertEquals(phoneorder.getRecipientState(), "IL", "Test Step - 11 - Recipient phone number is not auto populated on phone order page recipient section");
            //  softassert.assertEquals(phoneorder.getReciPhone(), "3127427896", "Test Step - 11 - Recipient phone number is not auto populated on phone order page recipient section");
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