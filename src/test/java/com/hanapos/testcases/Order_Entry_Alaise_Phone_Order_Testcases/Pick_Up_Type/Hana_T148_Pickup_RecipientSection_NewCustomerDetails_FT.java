package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Pick_Up_Type;

import com.github.javafaker.Faker;
import com.hanapos.utilities.LoggerUtil;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;

public class Hana_T148_Pickup_RecipientSection_NewCustomerDetails_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;
    String recipient_firstname;
    String recipient_lastname;
    String recipient_firstname2;
    String recipient_lastname2;
    public static LoggerUtil logger_Util;

    @Epic("Phone Order Module - Pickup Type")
    @Test(enabled = true, groups = {"Regression"})
    public void Validate_Hana_T148_Pickup_RecipientSection_NewCustomerDetails_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Hana_T148_Pickup_RecipientSection_NewCustomerDetails_FT  ****");
        logger.debug("capturing application debug logs....");
        logger_Util = new LoggerUtil();
        String testCaseName = getCurrentTestName();
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1: Login page is not displayed");
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

            Faker usFaker = new Faker(new java.util.Locale("en-US"));
            Faker canadaFaker = new Faker(new java.util.Locale("en-CA"));
            // Generate random US customer details
            String usFirstName = usFaker.name().firstName();
            String usLastName = usFaker.name().lastName();
            String usFullName = usFirstName + " " + usLastName;

            String companyname = Generate_CompanyName();
            String usPhoneNumber = GenerateUsPhoneNumber();
            String caPhoneNumber = GenerateCAPhoneNumber();
            String usStreetAddress = usFaker.address().streetAddress();
            String usCity = usFaker.address().city();
            String usState = usFaker.address().state();
            String usZipCode = usFaker.address().zipCode();
            String usFullAddress = usStreetAddress + ", " + usCity + ", " + usState + " " + usZipCode;

            phoneorder.EnterCustomerFirstName(usFirstName);
            phoneorder.EnterCustomerLastName(usLastName);
            phoneorder.EnterAddress1(prop.getProperty("cust_address1"));
            phoneorder.Search_And_Select_Customer_Address1(prop.getProperty("customer_city_state_country"), prop.getProperty("customer_full_address"));

            delayWithGivenTime(2000);
            phoneorder.EnterPhoneNumber(caPhoneNumber);
            delayWithGivenTime(2000);
            String cust_firstname = phoneorder.getFirstnameOnPhoneOrderPage();
            String cust_lastname = phoneorder.getLastnameOnPhoneOrderPage();
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage().contains(usFirstName), true, "Test Step - 6 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage().contains(usLastName), true, "Test Step - 6 - Last name is not displayed on phone order page");

            delayWithGivenTime(2000);

            if (phoneorder.getAddress1OnPhoneOrderPage().contains(prop.getProperty("cust_address1"))) {
                softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), prop.getProperty("cust_address1"), "Test Step - 6 - address 1 is not displayed on phone order page");
            } else if (phoneorder.getAddress1OnPhoneOrderPage().contains("2715 N 35th Ave")) {
                softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage(), "2715 N 35th Ave", "Test Step - 6 - address 1 is not displayed on phone order page");
            }

            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), prop.getProperty("cust_zipcode"), "Test Step - 6 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getCityOnPhoneOrderPage(), prop.getProperty("cust_city"), "Test Step - 6 - city is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), caPhoneNumber, "Test Step - 6 - phone number 1 is not displayed on phone order page");

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 6: Pickup type is not highlighted in blue color");

            phoneorder.Click_Recipient_FirstName();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_NewCustomer_Label_Appears(), "Test Step - 6 - New customer label is not displayed on phone order page");

            // Test Step - 7
            softassert.assertEquals(phoneorder.getReciFirstName(), usFirstName, "Test Step - 7 - Customer first name is not matched or autopopulated with customer first name on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), usLastName, "Test Step - 7 - Customer last name is not matched or autopopulated with customer last name on phone order page recipient section");

            // Test Step - 8
            softassert.assertEquals(phoneorder.getReciAddress1(), "114 N CHURCH ST", "Test Step - 8 - Shop address 1 is not matched or not autopopulated field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "PICK UP", "Test Step - 8 - PICKUP address 2 is not autopopulated or not matched with customer address 2 field on phone order page recipient section");

            // Test Step - 9
            softassert.assertEquals(phoneorder.getReciPhone(), caPhoneNumber, "Test Step - 9 - Customer phone number is not displayed or not autopopulated on recipient section phone number 1 field");

            // Test Step - 10
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "", "Test Step - 10 - Customer alt phone number is not displayed or not autopopulated on recipient section alt phone number field");

            // Test Step - 11
            // Generate random US customer details
            String us_FirstName = usFaker.name().firstName();
            String us_LastName = usFaker.name().lastName();
            String us_FullName = usFirstName + " " + usLastName;

            String company_name = Generate_CompanyName();
            String us_PhoneNumber = GenerateUsPhoneNumber();
            String ca_PhoneNumber = GenerateCAPhoneNumber();
            String us_StreetAddress = usFaker.address().streetAddress();
            String us_City = usFaker.address().city();
            String us_State = usFaker.address().state();
            String us_ZipCode = usFaker.address().zipCode();
            String us_FullAddress = usStreetAddress + ", " + usCity + ", " + usState + " " + usZipCode;

            ThreadWait(3000);
            phoneorder.EnterCustomerFirstName(us_FirstName);
            phoneorder.EnterCustomerLastName(us_LastName);

            phoneorder.EnterAddress1(prop.getProperty("cust_address1"));
            phoneorder.Search_And_Select_Customer_Address1(prop.getProperty("customer_city_state_country"), prop.getProperty("customer_full_address"));

            phoneorder.EnterZipCode(prop.getProperty("cust_zipcode"));
            phoneorder.EnterPhoneNumber(prop.getProperty("customer_phonenumber"));
            phoneorder.EnterAltPhoneNumber(prop.getProperty("customer_altphonenumber"));
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage(), us_FirstName, "Test Step - 11 - Customer First name is not displayed or not autopopulated on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage(), us_LastName, "Test Step - 11 - Customer Last name is not displayed or not autopopulated on phone order page");

            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage().contains(prop.getProperty("cust_address1")), true, "Test Step - 11 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), prop.getProperty("cust_zipcode"), "Test Step - 11 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), prop.getProperty("customer_phonenumber"), "Test Step - 11 - phone number 1 is not displayed on phone order page");

            phoneorder.ClickPickupTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getHighlightedColorOnPickupTypeOnPhoneOrderPage(), "#2f9bc8", "Test Step - 6: Pickup type is not highlighted in blue color");
            phoneorder.Click_Recipient_FirstName();
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_NewCustomer_Label_Appears(), "Test Step - 11 - New customer label is not displayed on phone order page");

            // Test Step - 12
            softassert.assertEquals(phoneorder.getReciFirstName(), us_FirstName, "Test Step - 12 - Customer first name is not displayed or not autopopulated on recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), us_LastName, "Test Step - 12 - Customer last name is not displayed or not autopopulated on recipient section again afer creating new customer on the phone order page");

            // Test Step - 13
            softassert.assertEquals(phoneorder.getReciAddress1(), "114 N CHURCH ST", "Test Step - 13 - Shop recipient address 1 is not autopopulated or not matched with customer address 1 field on phone order page recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "PICK UP", "Test Step - 13 - PICKUP recipient address 2 is not autopopulated or not matched with customer address 2 on phone order page recipient section");

            // Test Step - 14
            softassert.assertEquals(phoneorder.getReciPhone(), prop.getProperty("customer_phonenumber"), "Test Step - 14 - Customer phone number is not autopopulated or not displayed on recipient section phone number field");

            // Test Step - 15
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), prop.getProperty("customer_altphonenumber"), "Test Step - 15 - Customer alt phone number is not autopopulated or not displayed on recipient section alt phone number field");

        } catch (Exception e) {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            softassert.fail(e.getMessage());
        } finally {
            logger_Util = new LoggerUtil();
            logger_Util.attachNetworkLogs(testCaseName);
            softassert.assertAll();
        }
    }
}