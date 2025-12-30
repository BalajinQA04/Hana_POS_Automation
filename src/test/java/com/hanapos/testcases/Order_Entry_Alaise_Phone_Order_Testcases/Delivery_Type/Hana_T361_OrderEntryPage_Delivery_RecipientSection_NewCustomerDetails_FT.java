package com.hanapos.testcases.Order_Entry_Alaise_Phone_Order_Testcases.Delivery_Type;

import com.github.javafaker.Faker;
import com.hanapos.pageObjects.HanaDashBoardPage;
import com.hanapos.pageObjects.LoginPage;
import com.hanapos.pageObjects.OrderEntry_Alais_PhoneOrderPage;
import com.hanapos.seleniumProjectBase.TestBaseClass;
import com.hanapos.utilities.CustomSoftAssert;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

public class Hana_T361_OrderEntryPage_Delivery_RecipientSection_NewCustomerDetails_FT extends TestBaseClass {
    private LoginPage lp;
    private HanaDashBoardPage dashboard;
    private OrderEntry_Alais_PhoneOrderPage phoneorder;

    @Epic("Phone Order Module - Delivery Type")
    @Test(enabled = true, groups = {"Regression", "Sanity"})
    public void Validate_Hana_T361_OrderEntryPage_Delivery_RecipientSection_NewCustomerDetails_Functionality_Test() {
        // SoftAssert softassert = new SoftAssert(); - I have modified this to use CustomSoftAssert
        CustomSoftAssert softassert = new CustomSoftAssert();

        logger.info("**** Starting  Validate_Hana_T361_OrderEntryPage_Delivery_RecipientSection_NewCustomerDetails_Functionality_Test  ****");
        logger.debug("capturing application debug logs....");
        try {
            // Test Step - 1
            lp = new LoginPage();
            softassert.assertTrue(lp.LoginPageIsDisplayed(), "Test Step - 1: Login page is not displayed");
            logger.info("User on the hana pos login page");

            // Test Step - 2
            lp.EnterUserName(prop.getProperty("username"));
            logger.info("User entered the username as " + prop.getProperty("username"));
            lp.EnterPassword(prop.getProperty("password"));
            logger.info("User entered the password as " + prop.getProperty("password"));
            lp.ClickLoginButton();
            logger.info("User clicked on Login button");

            dashboard = new HanaDashBoardPage();
            softassert.assertTrue(dashboard.VerifyHanaDashBoardPage(), "Test Step - 2: Page does not navigated to hana dashboard page");
            logger.info("User navigated to hana dashboard page");
          /*  dashboard.SelectShopNameDropDown(prop.getProperty("shopname"));
            logger.info("User select the shopname on dashbaord page as " + prop.getProperty("shopname"));
*/
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

            phoneorder.ClickdeliveryTypeOnPhoneOrderPage();
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.get_HighlightedColor_OnDelivery_TypeOnPhoneOrderPage(), "#676a6c",
                    "Test Step - 5 - Delivery type is not highlighted as blue color");

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

            phoneorder.EnterReciFirstName("");
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_NewCustomer_Label_Appears(), "Test Step - 6 - New customer label is not displayed on phone order page");

            // Test Step - 7
            softassert.assertEquals(phoneorder.getReciFirstName(), "", "Test Step - 7 - Recipient first name is displayed on recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "", "Test Step - 7 - Recipient last name is displayed on recipient section");

            // Test Step - 8
            softassert.assertEquals(phoneorder.getReciAddress1(), "", "Test Step - 8 - Recipient address 1 is displayed on recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "", "Test Step - 8 - Recipient address 2 is displayed on recipient section");

            // Test Step - 9
            softassert.assertEquals(phoneorder.getReciPhone(), prop.getProperty("default_phone_number1"), "Test Step - 9 - Recipient phone number is displayed on recipient section");

            // Test Step - 10
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "", "Test Step - 10 - Recipient alt phone number is displayed on recipient section");

            // Test Step - 11
            ThreadWait(3000);
            phoneorder.EnterCustomerFirstName("Ben" + twodigitrandomeString());
            phoneorder.EnterCustomerLastName("Parker" + twodigitrandomeString());
            phoneorder.EnterAddress1("3402 Park Blvd");
            phoneorder.EnterZipCode("92103");
            phoneorder.EnterPhoneNumber("956-444-0756");
            phoneorder.EnterAltPhoneNumber("956-444-0756");
            delayWithGivenTime(2000);
            softassert.assertEquals(phoneorder.getFirstnameOnPhoneOrderPage().contains("Ben"), true, "Test Step - 11 - First name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getLastnameOnPhoneOrderPage().contains("Parker"), true, "Test Step - 11 - Last name is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getAddress1OnPhoneOrderPage().contains("3402 Park Blvd"), true, "Test Step - 11 - address 1 is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getZipCodeOnPhoneOrderPage(), "92103", "Test Step - 11 - Zipcode is not displayed on phone order page");
            softassert.assertEquals(phoneorder.getPhoneNumberOnPhoneOrderPage(), "956-444-0756", "Test Step - 11 - phone number 1 is not displayed on phone order page");
            phoneorder.EnterReciFirstName("");
            delayWithGivenTime(2000);
            softassert.assertTrue(phoneorder.Verify_NewCustomer_Label_Appears(), "Test Step - 11 - New customer label is not displayed on phone order page");

            // Test Step - 12
            softassert.assertEquals(phoneorder.getReciFirstName(), "", "Test Step - 12 - Recipient first name is not displayed on recipient section");
            softassert.assertEquals(phoneorder.getReciLastName(), "", "Test Step - 12 - Recipient last name is not displayed on recipient section again afer creating new customer on the phone order page");

            // Test Step - 13
            softassert.assertEquals(phoneorder.getReciAddress1(), "", "Test Step - 13 - Recipient address 1 is displayed on recipient section");
            softassert.assertEquals(phoneorder.getReciAddress2(), "", "Test Step - 13 - Recipient address 2 is displayed on recipient section");

            // Test Step - 14
            softassert.assertEquals(phoneorder.getReciPhone(), prop.getProperty("default_phone_number1"), "Test Step - 14 - Recipient phone number is displayed on recipient section");

            // Test Step - 15
            softassert.assertEquals(phoneorder.getRecipientPhone2OnPhoneOrderPage(), "", "Test Step - 15 - Recipient alt phone number is displayed on recipient section");

        } catch (Exception e) {
            e.printStackTrace();
            softassert.fail(e.getMessage());
            logger.error("Test case failed due to exception " + e.getMessage());
        } finally {
            softassert.assertAll();
        }
    }
}