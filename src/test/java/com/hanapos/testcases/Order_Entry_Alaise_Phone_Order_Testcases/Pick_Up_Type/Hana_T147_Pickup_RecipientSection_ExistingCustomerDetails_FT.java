package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T147_Pickup_RecipientSection_ExistingCustomerDetails_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    public static LoggerUtil logger_Util;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T147_Pickup_RecipientSection_ExistingCustomerDetails_Functionality_Test() {
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana_T147_Pickup_RecipientSection_ExistingCustomerDetails_FT  ****");
        logger.debug("capturing application debug logs....");
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1 : Login page is not displayed");
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

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step 5 - Pickup type is not highlighted in blue color");

            // Test Step - 7
            softassert.assertEquals(phoneorder.getReciFirstName(), "Abish", "Test Step - 7 - Customer first name is not auto-populated on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "David", "Test Step - 7 - Customer last name is not auto-populated on phone order page recipient section");

            // Test Step - 8
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciAddress1(), "114 N CHURCH ST", "Test Step - 8 - Shop address 1 is not matched on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "PICK UP", "Test Step - 8 - Customer address 2 - Pickup is not matched on phone order page recipient section");

            // Test Step - 9
            softassert.assertEquals(phoneorder.getReciPhone(), "956-655-0756", "Test Step - 9 - Customer phone number 1 is not matched or autopopulated on phone order page recipient section");

            // Test Step - 10
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "956-655-0756", "Test Step - 10 - Customer alt phone number 2 is not matched or autopopulated on phone order page recipient section");

            // Test Step - 11
            delayWithGivenTime(2000);
            phoneorder.SearchAndSelectCustomerOnCust_Section(prop.getProperty("cust_fullname2"));
            phoneorder.ClickPickupTypeOnPhoneOrderPage();

            // Test Step - 12
            softassert.assertEquals(phoneorder.getReciFirstName(), "Liam", "Test Step - 12 - Customer first name is not auto-populated on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "Benjamin", "Test Step - 12 - Customer last name is not auto-populated on phone order page recipient section");

            // Test Step - 13
            softassert.assertEquals(phoneorder.getReciAddress1(), "114 N CHURCH ST", "Test Step - 13 - recipient address 1 is not displayed on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "PICK UP", "Test Step - 13 - recipient address 2 is not displayed on phone order page recipient section");

            // Test Step - 14
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getReciPhone(), prop.getProperty("liam_cust_phonenumber"), "Test Step - 14 - Customer phone number 1 is not autopopulated or not displayed on phone order page recipient section phone number 1 field");

            // Test Step - 15
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), prop.getProperty("liam_cust_altphonenumber"), "Test Step - 15 - Customer alt phone number is not autopopulated or displayed on phone order page recipient section alt phone number field");

        } catch (Exception e) {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            softassert.fail("Test case - Known Issue: " + e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            softassert.assertAll();
        }
    }
}