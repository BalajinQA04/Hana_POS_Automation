package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.CustomerPage;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T101_Pickup_CustomerSection_Edit_Customer_CustomerDetails_CreditCard_AddNewCard_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    private CustomerPage customerpage = new CustomerPage();

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T101_Pickup_CustomerSection_Edit_Customer_CustomerDetails_CreditCard_AddNewCard_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana_T101_Pickup_CustomerSection_Edit_Customer_CustomerDetails_CreditCard_AddNewCard_FT  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("bestuname"));
            softassert.assertEquals(lp.get_entered_username(), prop.getProperty("bestuname"), "Test Step - 2: Entered username " + prop.getProperty("bestuname") + " is not matching with expected username");
            logger.info("User entered the username as " + prop.getProperty("bestuname"));

            lp.EnterPassword(prop.getProperty("bestpass"));
            softassert.assertEquals(lp.get_entered_password(), prop.getProperty("bestpass"), "Test Step - 2: Entered password " + prop.getProperty("bestpass") + " is not matching with expected password");
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
            delayWithGivenTime(2000);
            phoneorder = new OrderEntry_Alais_PhoneOrderPage();
            phoneorder.Select_ShopName_On_PhoneOrder_Page(prop.getProperty("bestshopname"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_selected_shopname_on_phoneorder_page(), prop.getProperty("bestshopname"), "Test Step - 4 - Selected the shop name on phoneorder page is not displayed properly as expected");

            phoneorder.Select_SalesPersonOn_PhoneOrderEntryPage(prop.getProperty("bestemployee"));
            delayWithGivenTime(2000);

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 5 - Pickup type is not highlighted in blue color");

            //Test Step - 6
            softassert.assertTrue(phoneorder.VerifyCustSectionAppears(), "Test Step - 6 - Customer section is not displayed on phone order page");

            // Test Step - 7
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_firstName"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), "Abish", "Test Step - 7 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), "David", "Test Step - 7 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCompanyNameOnPhoneOrderPage(), "Hana_Sisterchicks", "Test Step - 7 - Company name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getEmailIdOnPhoneOrderPage(), "hanaposqateam@gmail.com", "Test Step -7 - email id is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "3402 Park Blvd", "Test Step - 7 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress2OnPhoneOrderPage(), "", "Test Step - 7- Address 2 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "92103", "Test Step -7 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), "San Diego", "Test Step -7 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 7 - phone number 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAltPhoneNumberOnPhoneOrderPage(), "956-655-0756", "Test Step - 7 - Alt phone number is not displayed on phone order page");
            delayWithGivenTime(2000);

            // Test Step - 8
            softassert.assertTrue(phoneorder.Verify_CustEditIcon_Appears(), "Test Step - 8 - Customer clear button is not displayed on phone order page");
            phoneorder.Click_CustEditIcon();
            softassert.assertTrue(phoneorder.Verify_CustomerDetailsPopupAppears(), "Test Step - 9 - Customer details popup on is not displayed on phone order page");

            // Test Step - 9
            delayWithGivenTime(1000);
            phoneorder.Click_CreditCardTab_OnCustDetailsPopup();
            delayWithGivenTime(1000);

            // Test Step - 10
            customerpage = new CustomerPage();
            if (phoneorder.Verify_grid1_creditcard_num_IsDisplayed()) {
                customerpage.Click_OnCreditCard_DeleteIcon_OnCustomerDetailsPopup();
                delayWithGivenTime(2000);
                customerpage.Click_OnCreditCard_DeleteIcon_DeleteConfirmation();
            }

            delayWithGivenTime(3000);
            phoneorder.Click_AddNewBtn_CreditCardTab_OnCustDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_AddNewInformation_Popup(), "Test Step - 10 - Add new information popup on credit card tab is not displayed on phone order page");

            // Test Step - 11
            delayWithGivenTime(2000);
            phoneorder.Enter_CreditCardTab_FirstName_OnCustDetailsPopup(prop.getProperty("cust_firstName"));
            softassert.assertEquals(phoneorder.get_CreditCardTab_EnteredFirstName_OnCustDetailsPopup(), "Abish", "Test Step - 11 - Entered first name on customer details popup at credit card tab is not displayed on phone order page");

            // Test Step - 12
            phoneorder.Enter_CreditCardTab_LastName_OnCustDetailsPopup(prop.getProperty("cust_lastName"));
            softassert.assertEquals(phoneorder.get_CreditCardTab_EnteredLastName_OnCustDetailsPopup(), "David", "Test Step - 12 - Entered last name on customer details popup at credit card tab is not displayed on phone order page");

            //Test Step - 13
            phoneorder.Enter_CreditCardTab_CreditCardNumber_OnCustDetailsPopup(prop.getProperty("creditcardnum"));
            softassert.assertEquals(phoneorder.get_CreditCardTab_EnteredCreditCardNumber_OnCustDetailsPopup(), prop.getProperty("creditcardnum"), "Test Step - 13 - Entered credit card number on customer details popup at credit card tab is not displayed on phone order page");

            //Test Step - 14
            phoneorder.Enter_CreditCardTab_ExpDate_OnCustDetailsPopup(prop.getProperty("ccexpiredate"));
            softassert.assertEquals(phoneorder.get_CreditCardTab_EnteredExpDate_OnCustDetailsPopup(), prop.getProperty("ccexpiredate"), "Test Step - 14 - Entered exp date on customer details popup at credit card tab is not displayed on phone order page");

            // Test Step - 15
            phoneorder.Enter_CreditCardTab_CVV_OnCustDetailsPopup(prop.getProperty("cccvv"));
            softassert.assertEquals(phoneorder.get_CreditCardTab_EnteredCVV_OnCustDetailsPopup(), prop.getProperty("cccvv"), "Test Step - 15 - Entered CVV on customer details popup at credit card tab is not displayed on phone order page");

            // Test Step - 16
            phoneorder.Enter_CreditCardTab_Zipcode_OnCustDetailsPopup(prop.getProperty("cust_zipcode"));
            softassert.assertEquals(phoneorder.get_CreditCardTab_EnteredZipcode_OnCustDetailsPopup(), prop.getProperty("cust_zipcode"), "Test Step - 16 - Entered zipcode on customer details popup at credit card tab is not displayed on phone order page");

            //Test Step - 17
            phoneorder.Click_SaveBtn_CreditCardTab_OnCustDetailsPopup();
            softassert.assertTrue(phoneorder.Verify_SavedInformation_Popup(), "Test Step - 17 - Saved information popup on credit card tab is not displayed on phone order page");

            // Test Step - 18
            delayWithGivenTime(2000);
            phoneorder.Click_CloseIcon_CreditCardTab_OnCustDetailsPopup();

            // Test Step - 19
            if (phoneorder.Verify_grid1_creditcard_num_IsDisplayed()) {
                softassert.assertTrue(phoneorder.Verify_grid1_creditcard_num_IsDisplayed(), "Test Step - 19 - Added New credit card is not displayed on grid on phone order page");
                softassert.assertTrue(phoneorder.verify_credit_card_number_isDispalyed_on_CreditCardTab_OnCustDetailsPopup(), "Test Step - 19 - Added New credit card number is not displayed on grid row 1 - Credit Card Tab - Customer details popup on phone order page");
                softassert.assertTrue(phoneorder.verify_credit_card_expiredate_isDispalyed_on_CreditCardTab_OnCustDetailsPopup(), "Test Step - 19 - Added New credit card expire date is not displayed on grid row 1 - Credit Card Tab - Customer details popup on phone order page");
            }

            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_grid1_creditcard_num_IsDisplayed(), "Test Step - 19 - Added New credit card is not displayed on grid on phone order page");
            delayWithGivenTime(2000);
            customerpage.Click_OnCreditCard_DeleteIcon_OnCustomerDetailsPopup();
            delayWithGivenTime(2000);
            // This method will avoid the junk data
            customerpage.Click_OnCreditCard_DeleteIcon_DeleteConfirmation();

        } catch (Exception e) {
            softassert.fail("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}